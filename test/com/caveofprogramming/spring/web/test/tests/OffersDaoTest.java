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

	private User user1 = new User("qwerqwer", "qwer qwer", "qwerqwer", "qwer@qwer.de", true, "ROLE_USER");
	private User user2 = new User("asdfasdf", "asdf asdf", "asdfasdf", "asdf@asdf.de", true, "ROLE_ADMIN");
	private User user3 = new User("yxcvyxcv", "yxcv yxcv", "yxcvyxcv", "yxcv@yxcv.de", true, "ROLE_USER");
	private User user4 = new User("tzuitzui", "tzui tzui", "tzuitzui", "tzui@tzui.de", false, "user");

	private Offer offer1 = new Offer(user1, "This is a Test offer.");
	private Offer offer2 = new Offer(user1, "This is another test offer.");
	private Offer offer3 = new Offer(user2, "This is another test offer. 2");
	private Offer offer4 = new Offer(user3, "This is another test offer. 3");
	private Offer offer5 = new Offer(user3, "This is another test offer. 4");
	private Offer offer6 = new Offer(user3, "This is another test offer. 5");
	private Offer offer7 = new Offer(user4, "This is another test offer. 6");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}

	@Test
	public void testCreate() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		offersDao.saveOrUpdate(offer1);

		List<Offer> offers1 = offersDao.getOffers();
		assertEquals("Should be 1 offers! ", 1, offers1.size());
		assertEquals("Retrieved offer should be inserted offer!", offer1, offers1.get(0));

		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);

		List<Offer> offers2 = offersDao.getOffers();
		assertEquals("Should be 6 offers for enabled users!", 6, offers2.size());
	}

	@Test
	public void testGetByUsername() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);

		List<Offer> offers1 = offersDao.getOffers(user3.getUsername());
		assertEquals("Should be 3 offers for user3!", 3, offers1.size());

		List<Offer> offers2 = offersDao.getOffers("Peter Parker");
		assertEquals("Should be 0 offers for non existing user!", 0, offers2.size());

		List<Offer> offers3 = offersDao.getOffers(user2.getUsername());
		assertEquals("Should be 0 offers for non existing user!", 1, offers3.size());
	}

	@Test
	public void testUpdate() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);

		offer3.setText("This offer has updated text!");
		offersDao.saveOrUpdate(offer3);

		Offer offerRetreived = offersDao.getOffer(offer3.getId());

		assertEquals("Retrieved offer should have updated text!", offer3.getText(), offerRetreived.getText());
	}

	@Test
	public void testDelete() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);

		Offer retreived1 = offersDao.getOffer(offer2.getId());
		assertEquals("Offer2 has been deleted, so it must'nt be null", offer2, retreived1);

		offersDao.delete(offer2.getId());

		Offer retreived2 = offersDao.getOffer(offer2.getId());
		assertEquals("Offer2 has been deleted, so it must be null", null, retreived2);
	}

	@Test
	public void testGetById() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);

		Offer retreived1 = offersDao.getOffer(offer1.getId());
		assertEquals("offer1 is not null!", offer1, retreived1);
		
		Offer retreived2 = offersDao.getOffer(offer7.getId());
		assertEquals("Offer7 is null because user4 is disabled!", null, retreived2);
		
	}

	@Test
	public void testOffers() {
		User user = new User("testcaseuser2", "John Doe 2", "heyhopassword", "asdfa@asdf.de", true, "ROLE_USER");
		usersDao.create(user);

		Offer offer = new Offer(user, "doing everything you want, if you pay enough");

		offersDao.saveOrUpdate(offer);

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
			offersDao.saveOrUpdate(anotherOffer);
		}
		List<Offer> anotherOfferList = offersDao.getOffers(anotherUser.getUsername());
		assertEquals("Number of Offers should be 10 for this one.", 10, anotherOfferList.size());

	}
}
