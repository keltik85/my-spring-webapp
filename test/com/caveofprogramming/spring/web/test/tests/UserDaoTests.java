package com.caveofprogramming.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
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

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");

	}

	@Test
	public void testCreateUser() {
		User user = new User("testcaseuser", "John Doe","heyhopassword", "asdf@asdf.de", true, "ROLE_USER");
	

		assertTrue("User is created successfully", usersDao.create(user));
		
		List<User> userList = usersDao.getAllUsers();
		
		assertEquals("Number of Users should be 1.", 1, userList.size());
		assertTrue("The user should exist.", usersDao.exists(user.getUsername()));
		assertEquals("Created user should be identical to retrieved user.", user, userList.get(0));
	}
}
