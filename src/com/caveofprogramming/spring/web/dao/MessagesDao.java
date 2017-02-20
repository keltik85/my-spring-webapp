package com.caveofprogramming.spring.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("messagesDao")
public class MessagesDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public List<Message> getMessages() {
		Criteria crit = this.getSession().createCriteria(Message.class);
		return crit.list();
	}

	public List<Message> getMessages(String username) {
		Criteria crit = this.getSession().createCriteria(Message.class);
		crit.add(Restrictions.eq("username", username));

		return crit.list();
	}
	
	public void saveOrUpdate(Message message) {
		this.getSession().saveOrUpdate(message);
	}
	
	public boolean delete(int id) {
		Query query = this.getSession().createQuery("delete from Message where id=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}
	
	public Message getMessage(int id) {
		Criteria crit = this.getSession().createCriteria(Message.class);
		crit.add(Restrictions.idEq(id));
		return (Message) crit.uniqueResult();
	}
	
}
