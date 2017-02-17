package com.caveofprogramming.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/caveofprogramming/spring/web/config/dao-context.xml",
		"classpath:com/caveofprogramming/spring/web/config/security-context.xml",
		"classpath:com/caveofprogramming/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("qwerqwer", "qwer qwer", "qwerqwer", "qwer@qwer.de", true, "ROLE_USER");
	private User user2 = new User("asdfasdf", "asdf asdf", "asdfasdf", "asdf@asdf.de", true, "ROLE_ADMIN");
	private User user3 = new User("yxcvyxcv", "yxcv yxcv", "yxcvyxcv", "yxcv@yxcv.de", true, "ROLE_USER");
	private User user4 = new User("tzuitzui", "tzui tzui", "tzuitzui", "tzui@tzui.de", false, "user");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");

	}
	
	@Test
	public void testCreateRecieve(){
		usersDao.create(user1);
		List<User> users1 = usersDao.getAllUsers();
		assertEquals("One user was created and one user is also recieved!", 1, users1.size() );
		assertEquals("Inserted user is the same as the object!", user1, users1.get(0));
		
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		List<User> users2 = usersDao.getAllUsers();
		assertEquals("Four users have been created and four users are also recieved!", 4, users2.size() );
		
	}
	
	@Test
	public void testExists(){
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);

		assertTrue("The user should exist.", usersDao.exists(user2.getUsername()));
		assertFalse("The user should not exist.", usersDao.exists("Ronald McDonald"));
	}

	//TODO - Remimplement this
	@Test
	public void testCreateUser() {
		User user = new User("testcaseuser", "John Doe", "heyhopassword", "asdf@asdf.de", true, "ROLE_USER");

		// assertTrue("User is created successfully", usersDao.create(user));
		usersDao.create(user);

		List<User> userList = usersDao.getAllUsers();

		assertEquals("Number of Users should be 1.", 1, userList.size());
		assertTrue("The user should exist.", usersDao.exists(user.getUsername()));
		assertFalse("The user should not exist.", usersDao.exists("Ronald McDonald"));
		assertEquals("Created user should be identical to retrieved user.", user, userList.get(0));
	}
}
