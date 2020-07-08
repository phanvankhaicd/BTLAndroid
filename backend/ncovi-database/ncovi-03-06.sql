-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: localhost    Database: ncovi
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_username` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `account_password` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `account_name` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `account_status` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `account_type` int(11) NOT NULL,
  `account_created_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  KEY `fk_account_user_idx` (`user_id`),
  CONSTRAINT `fk_account_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `health_monitor`
--

DROP TABLE IF EXISTS `health_monitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `health_monitor` (
  `health_monitor_id` int(11) NOT NULL AUTO_INCREMENT,
  `health_monitor_sick` int(6) DEFAULT NULL,
  `health_monitor_cough` int(6) DEFAULT NULL,
  `health_monitor_sultry` int(6) DEFAULT NULL,
  `health_monitor_tired` int(6) DEFAULT NULL,
  `health_monitor_good` int(6) DEFAULT NULL,
  `health_monitor_status` int(6) DEFAULT NULL,
  `health_monitor_description` varchar(50) DEFAULT NULL,
  `health_monitor_create_at` timestamp NULL DEFAULT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`health_monitor_id`),
  KEY `FK_health_monitor_user` (`user`),
  CONSTRAINT `FK_health_monitor_user` FOREIGN KEY (`user`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `health_monitor`
--

LOCK TABLES `health_monitor` WRITE;
/*!40000 ALTER TABLE `health_monitor` DISABLE KEYS */;
/*!40000 ALTER TABLE `health_monitor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (2),(2),(2);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `notification` (
  `notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `notification_title` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `notification_body` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
  `notification_data` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `notification_created_at` timestamp(6) NOT NULL,
  `notification_status` int(11) NOT NULL,
  PRIMARY KEY (`notification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `question` (
  `question_id` int(11) NOT NULL,
  `question_name` varchar(1000) DEFAULT NULL,
  `question_result` int(6) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reflection`
--

DROP TABLE IF EXISTS `reflection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reflection` (
  `reflection_id` int(11) NOT NULL AUTO_INCREMENT,
  `reflection_contact_question_1` int(11) DEFAULT NULL,
  `reflection_contact_question_2` int(11) DEFAULT NULL,
  `reflection_contact_question_3` int(11) DEFAULT NULL,
  `reflection_info_question_1` int(11) DEFAULT NULL,
  `reflection_info_question_2` int(11) DEFAULT NULL,
  `reflection_info_question_3` int(11) DEFAULT NULL,
  `reflection_question_description` varchar(256) DEFAULT NULL,
  `reflection_type` int(11) DEFAULT NULL,
  `reflection_created_at` timestamp(6) NULL DEFAULT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`reflection_id`),
  KEY `FK6l87jpeoy8ccpwuvs7w789kmb` (`user`),
  CONSTRAINT `FK6l87jpeoy8ccpwuvs7w789kmb` FOREIGN KEY (`user`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reflection`
--

LOCK TABLES `reflection` WRITE;
/*!40000 ALTER TABLE `reflection` DISABLE KEYS */;
/*!40000 ALTER TABLE `reflection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(50) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `role_description` varchar(50) DEFAULT NULL,
  `role_created_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `Index 2` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN','ROLE_ADMIN',NULL,NULL),(2,'ROLE_USER','ROLE_USER',NULL,NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(100) DEFAULT NULL,
  `user_fullname` varchar(100) DEFAULT NULL,
  `user_birthday` timestamp NULL DEFAULT NULL,
  `user_address` varchar(100) DEFAULT NULL,
  `user_phone_no` varchar(100) DEFAULT NULL,
  `user_cmt` varchar(100) DEFAULT NULL,
  `user_bhxh` varchar(100) DEFAULT NULL,
  `user_gender` varchar(100) DEFAULT NULL,
  `user_created_at` timestamp NULL DEFAULT NULL,
  `role` int(11) NOT NULL,
  `user_device_token` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_user_role` (`role`),
  CONSTRAINT `FK_user_role` FOREIGN KEY (`role`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ncovi'
--

--
-- Dumping routines for database 'ncovi'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-05 17:01:11
