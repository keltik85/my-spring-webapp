package com.caveofprogramming.spring.web.test.tests;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caveofprogramming.spring.web.dao.Message;
import com.caveofprogramming.spring.web.dao.MessagesDao;
import com.caveofprogramming.spring.web.dao.OffersDao;
import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/caveofprogramming/spring/web/config/dao-context.xml",
		"classpath:com/caveofprogramming/spring/web/config/security-context.xml",
		"classpath:com/caveofprogramming/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessagesDaoTest {
	@Autowired
	private OffersDao offersDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private MessagesDao messagesDao;

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
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}

	@Test
	public void testSave() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		Message message1 = new Message("Some Test Subject 1", "Some Test content 1", "Peter Parker",
				"peterparker@spiderman.net", user1.getUsername());
		
		messagesDao.saveOrUpdate(message1);
	}
}
