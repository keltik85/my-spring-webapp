package com.caveofprogramming.spring.web.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message implements Serializable {



	private static final long serialVersionUID = -5935168427674716171L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String subject;
	private String content;

	// name of user sending message
	private String name;

	// senders email
	private String email;

	// sending Message to this user
	private String username;

	public Message() {
		this.subject = "Subject goes here";
		this.content = "content goes here";
		this.name = "name goes here";
		this.email = "email goes here";
		this.username = "username goes here";
	}

	public Message(String subject, String content, String name, String email, String username) {
		this.subject = subject;
		this.content = content;
		this.name = name;
		this.email = email;
		this.username = username;
	}
}
