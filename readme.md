Configure MySql Database:

```
mysql> create database springtest;
mysql> use springtest;

CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(9999) NOT NULL ,
  email varchar(120) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));

  
CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));

  
CREATE TABLE offers (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  email varchar(60) NOT NULL,
  text text NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO offers VALUES (1,'Bob','bob@nowhereatall.com','I will write Java for you'),(2,'Mike','mike@nowhereatall.com','Web design, very cheap'),(3,'Sue','sue@nowhereatall.com','PHP coding');

```

Configure MySql Database For unit testing:

```
mysql> create database springunittesting;
mysql> use springunittesting;
myuser@myhostname ~/workspace/my-spring-webapp $ mysqldump -u root -proot springtest > springtest.sql
myuser@myhostname ~/workspace/my-spring-webapp $ mysql -u root -proot springunittesting < springtest.sql
```