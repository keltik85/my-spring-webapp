-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: springtest
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `offers`
--

DROP TABLE IF EXISTS `offers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` text NOT NULL,
  `username` varchar(60) NOT NULL,
  PRIMARY KEY (`id`,`username`),
  KEY `fk_offers_users_idx` (`username`),
  CONSTRAINT `fk_offers_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offers`
--

LOCK TABLES `offers` WRITE;
/*!40000 ALTER TABLE `offers` DISABLE KEYS */;
INSERT INTO `offers` VALUES (1,'If the price is high enough i will do anything!','asdfasdf'),(2,'Selling drugs on twitter to Trump!','myadmin1'),(4,'heyo ho','yxcvyxcv');
/*!40000 ALTER TABLE `offers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(60) NOT NULL,
  `password` varchar(9999) DEFAULT NULL,
  `authority` varchar(45) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `email` varchar(60) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('asdfasdf','46db6b163266a4512e0cb9fc30d24d518cac2ef72b87691e786ad8eea8748e100463512803473710','ROLE_USER','asdfasdf asdf',1,'asdf@asdf.de'),('cvbncvbn','746526867ac1cbc08b5198e5c8168848105e9daadf9bd63f36b6dc5c9a0935c507c8841c4a5d5607','ROLE_USER','cvbn cvbn',1,'cvbn@cvbn.de'),('dfghdfgh','e85816a616c9280438b2c1d8e376d056288983ae3a2968ced7f01edee2ff770e76a5b2c67e3d5006','ROLE_USER','dfgh dfgh ',1,'dfgh@dfgh.de'),('myadmin1','2371ac379c3da24537432b94a4b7e7e05d5caf88eaf368ad30a5d7462f1230bfe7b908a8edbe703b','ROLE_ADMIN','Altug tekin',1,'asdf@asdf.de'),('qwerqwer','6a0f3d4b6092c2dc340cdacd3ed3452b794552f4e3b78f77a6a058925d904ce04ddd4044498d04e4','ROLE_USER','qwer qwer',1,'qwer@qwer.de'),('tzuitzui','4774545d40bd3bd0c0a7fdf4d3a26335ad8a9bc211aae229f600d563513eb5b585713da6df5474cd','ROLE_USER','tzui tzui',1,'tzui@tzui.de'),('yxcvyxcv','738b34a3d110e94210f06c735f0502c9a6fc7bb7a7389d4c00f6db0859801d4a4c79c0ecdd0019c4','ROLE_USER','yxcv yxcv',1,'yxcv@yxcv.de');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-17 15:31:06
