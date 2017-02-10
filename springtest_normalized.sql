drop table offers;
drop table user_roles;
drop table users;

create table springtest.users (
	username varchar(60) not null,
	password varchar(9999) null,
	authority varchar(45) null,
	name varchar(100) not null,
	enabled tinyint(1) null default 1,
	email varchar(60) not null,
	primary key(username)
);

create table springtest.offers(
	id int not null auto_increment,
	text text not null,
	username varchar(60) not null,
	primary key(id,username),
	index fk_offers_users_idx(username asc),
	constraint fk_offers_users
		foreign key(username)
		references springtest.users(username)
		on delete no action
		on update no action
);

create table springunittesting.users (
	username varchar(60) not null,
	password varchar(60) null,
	authority varchar(45) null,
	name varchar(100) not null,
	enabled tinyint(1) null default 1,
	email varchar(60) not null,
	primary key(username)
);

create table springunittesting.offers(
	id int not null auto_increment,
	text text not null,
	username varchar(60) not null,
	primary key(id,username),
	index fk_offers_users_idx(username asc),
	constraint fk_offers_users
		foreign key(username)
		references springunittesting.users(username)
		on delete no action
		on update no action
);