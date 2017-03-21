package com.caveofprogramming.spring.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("usersDao")
public class UsersDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	@Transactional
	public void create(User user) {

		// user.setAuthority("ROLE_USER");
		// user.setPassword(passwordEncoder.encode(user.getPassword()));
		// BeanPropertySqlParameterSource params = new
		// BeanPropertySqlParameterSource(user);
		//
		// return jdbc.update(
		// "insert into users (username, name, password, email, enabled,
		// authority) values (:username, :name, :password, :email, :enabled,
		// :authority)",
		// params) == 1;

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		this.getSession().save(user);
	}

	public boolean exists(String username) {
		// return jdbc.queryForObject("select count(*) from users where
		// username=:username",
		// new MapSqlParameterSource("username", username), Integer.class) > 0;

		return this.getUser(username) != null;

	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		// return jdbc.query("select * from users",
		// BeanPropertyRowMapper.newInstance(User.class));

		return this.getSession().createQuery("from User").getResultList();
	}

	public User getUser(String username) {
		Criteria crit = this.getSession().createCriteria(User.class);
		crit.add(Restrictions.idEq(username));
		User user = (User) crit.uniqueResult();
		return user;
	}

}
