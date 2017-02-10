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

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.OffersDao;
import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/caveofprogramming/spring/web/config/dao-context.xml",
		"classpath:com/caveofprogramming/spring/web/config/security-context.xml",
		"classpath:com/caveofprogramming/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OffersDaoTest {
	@Autowired
	private OffersDao offersDao;

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
	public void testOffers() {
		User user = new User("testcaseuser2", "John Doe 2", "heyhopassword", "asdfa@asdf.de", true, "ROLE_USER");
		usersDao.create(user);

		Offer offer = new Offer(user, "doing everything you want, if you pay enough");
		
		assertTrue("The offer is created successfully", offersDao.create(offer));

		List<Offer> offerList = offersDao.getOffers();
		assertEquals("Number of Offers should be 1.", 1, offerList.size());
		assertEquals("Created user should be identical to retrieved user.", offer, offerList.get(0));

		assertEquals("The offer is retrieved successfully", offer, offersDao.getOffer(offerList.get(0).getId()));

		offersDao.delete(offerList.get(0).getId());
		offerList = offersDao.getOffers();

		assertEquals("Number of Offers should be 0.", 0, offerList.size());
		
		User anotherUser = new User("testcaseuser3", "John Doe 3", "heyhopassword", "asdfa@asdf.de", true, "ROLE_USER");
		usersDao.create(anotherUser);
		for (int i = 0; i < 10; i++) {
			Offer anotherOffer = new Offer(anotherUser, "doing the task number " + i + "for free");
			offersDao.create(anotherOffer);
		}
		List<Offer> anotherOfferList = offersDao.getOffers(anotherUser.getUsername());
		assertEquals("Number of Offers should be 10 for this one.", 10, anotherOfferList.size());
		
	}
}
