CREATE DATABASE  IF NOT EXISTS `gametest` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gametest`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: gametest
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tblfriend`
--

DROP TABLE IF EXISTS `tblfriend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblfriend` (
  `id` int NOT NULL AUTO_INCREMENT,
  `request` int DEFAULT NULL,
  `accept` int DEFAULT NULL,
  `daterequest` varchar(50) DEFAULT NULL,
  `dateaccept` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idrequest_idx` (`request`),
  KEY `idaccept_idx` (`accept`),
  CONSTRAINT `idaccept` FOREIGN KEY (`accept`) REFERENCES `tblplayer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idrequest` FOREIGN KEY (`request`) REFERENCES `tblplayer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblfriend`
--

LOCK TABLES `tblfriend` WRITE;
/*!40000 ALTER TABLE `tblfriend` DISABLE KEYS */;
INSERT INTO `tblfriend` VALUES (1,2,1,'2021-09-26 00:00:00','2021-09-27 00:00:00'),(3,1,4,'2021-09-26 00:00:00','2021-09-27 00:00:00'),(19,10,1,'2021-10-12 16:06:22','2021-10-12 16:06:41'),(45,3,1,'2021-11-07 22:08:35','2021-11-07 22:09:01'),(46,10,2,'2021-11-09 09:49:17','2021-11-09 09:49:23'),(47,14,1,'2021-11-09 15:57:06','2021-11-09 15:57:14'),(48,10,13,'2021-11-23 08:52:38','2021-11-23 08:54:05'),(55,15,10,'2021-11-23 13:13:01','2021-11-23 13:13:31'),(56,15,3,'2021-11-23 13:13:11','2021-11-23 13:13:23'),(57,10,3,'2021-11-23 13:14:58','2021-11-23 13:15:06'),(58,14,15,'2021-11-23 13:29:51','2021-11-23 13:30:01');
/*!40000 ALTER TABLE `tblfriend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblgame`
--

DROP TABLE IF EXISTS `tblgame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblgame` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idparticipant1` int DEFAULT NULL,
  `idparticipant2` int DEFAULT NULL,
  `se1` int DEFAULT NULL,
  `se2` int DEFAULT NULL,
  `result` varchar(50) DEFAULT NULL,
  `starttime` varchar(50) DEFAULT NULL,
  `endtime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `statplayer_idx` (`idparticipant1`),
  KEY `player2_idx` (`idparticipant2`),
  CONSTRAINT `player2` FOREIGN KEY (`idparticipant2`) REFERENCES `tblparticipant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `statplayer` FOREIGN KEY (`idparticipant1`) REFERENCES `tblparticipant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblgame`
--

LOCK TABLES `tblgame` WRITE;
/*!40000 ALTER TABLE `tblgame` DISABLE KEYS */;
INSERT INTO `tblgame` VALUES (44,NULL,NULL,NULL,NULL,'test win','2021-11-19 20:49:47','2021-11-19 20:50:03'),(45,NULL,NULL,NULL,NULL,'test win','2021-11-19 20:52:32','2021-11-19 20:52:36'),(46,NULL,NULL,NULL,NULL,'test2 win','2021-11-19 20:59:42','2021-11-19 20:59:57'),(47,NULL,NULL,NULL,NULL,'thangngo win','2021-11-19 21:02:19','2021-11-19 21:02:34'),(48,NULL,NULL,NULL,NULL,'Draw. same weapon','2021-11-19 21:07:40','2021-11-19 21:07:56'),(49,NULL,NULL,NULL,NULL,'test win','2021-11-19 21:10:05','2021-11-19 21:10:20'),(50,NULL,NULL,NULL,NULL,'Draw. same weapon','2021-11-19 21:13:34','2021-11-19 21:13:49'),(51,NULL,NULL,NULL,NULL,'thangngo win','2021-11-19 21:15:00','2021-11-19 21:15:05'),(52,NULL,NULL,NULL,NULL,'Draw. same weapon','2021-11-19 21:17:46','2021-11-19 21:17:51'),(53,NULL,NULL,NULL,NULL,'test win','2021-11-19 21:20:05','2021-11-19 21:20:20'),(54,NULL,NULL,NULL,NULL,'Draw. same weapon','2021-11-19 21:24:52','2021-11-19 21:25:07'),(55,NULL,NULL,NULL,NULL,'thangngo win','2021-11-20 08:51:23','2021-11-20 08:51:29'),(56,NULL,NULL,NULL,NULL,'Draw. same weapon','2021-11-20 08:56:19','2021-11-20 08:56:28'),(57,NULL,NULL,NULL,NULL,'thangngo win','2021-11-20 09:10:04','2021-11-20 09:10:18'),(58,NULL,NULL,NULL,NULL,'Draw. same weapon','2021-11-20 16:28:21','2021-11-20 16:28:34'),(59,NULL,NULL,NULL,NULL,'Draw. same weapon','2021-11-20 16:28:28','2021-11-20 16:28:36'),(60,NULL,NULL,NULL,NULL,'rmitcp win','2021-11-20 16:34:15','2021-11-20 16:34:25'),(61,NULL,NULL,NULL,NULL,'thangngo win','2021-11-20 16:34:34','2021-11-20 16:34:38'),(62,NULL,NULL,NULL,NULL,'nt win','2021-11-23 13:16:06','2021-11-23 13:16:18'),(63,NULL,NULL,NULL,NULL,'thangngo win','2021-11-23 13:25:36','2021-11-23 13:25:45'),(64,NULL,NULL,NULL,NULL,'nt win','2021-11-23 13:30:19','2021-11-23 13:30:27'),(65,NULL,NULL,NULL,NULL,'thangngo win','2021-11-25 13:48:12','2021-11-25 13:48:27'),(66,NULL,NULL,NULL,NULL,'Draw. same weapon','2021-11-25 15:51:16','2021-11-25 15:51:23'),(67,NULL,NULL,NULL,NULL,NULL,'2021-11-25 18:29:59',NULL),(68,NULL,NULL,NULL,NULL,'thangngo win','2021-11-25 18:31:04','2021-11-25 18:31:19'),(75,134,135,0,2,NULL,'2021-12-14 14:04:20',NULL),(76,136,137,0,3,NULL,'2021-12-14 14:19:21',NULL),(77,138,139,0,2,NULL,'2021-12-14 14:23:28',NULL),(78,140,141,0,0,NULL,'2021-12-14 14:27:21',NULL),(79,142,143,0,0,NULL,'2021-12-14 14:30:36',NULL),(80,144,145,0,0,NULL,'2021-12-14 15:52:41',NULL),(81,146,147,0,0,NULL,'2021-12-14 16:07:24',NULL),(82,148,149,0,0,NULL,'2021-12-14 16:10:58',NULL),(83,150,151,0,0,NULL,'2021-12-14 16:15:46',NULL),(84,152,153,0,2,'Draw. same weapon','2021-12-14 16:30:45','2021-12-14 16:30:48'),(85,154,155,1,3,NULL,'2021-12-14 16:38:00',NULL),(86,156,157,1,2,'nt win','2021-12-14 16:40:24','2021-12-14 16:40:28'),(87,158,159,2,1,'nt win','2021-12-14 16:41:50','2021-12-14 16:42:05'),(88,160,161,2,3,'test win','2021-12-14 20:04:20','2021-12-14 20:04:23'),(89,162,163,2,2,'Draw. same weapon','2021-12-14 20:06:16','2021-12-14 20:06:19'),(90,164,165,3,1,'thangngo win','2021-12-20 20:47:10','2021-12-20 20:47:26'),(91,166,167,1,1,'Draw. same weapon','2021-12-21 06:39:45','2021-12-21 06:40:00'),(92,168,169,1,3,'thangngo win','2021-12-21 11:28:25','2021-12-21 11:28:29'),(93,170,171,1,2,'test2 win','2021-12-21 16:07:59','2021-12-21 16:08:07'),(94,172,173,1,1,'Draw. same weapon','2021-12-21 16:09:27','2021-12-21 16:09:43');
/*!40000 ALTER TABLE `tblgame` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblparticipant`
--

DROP TABLE IF EXISTS `tblparticipant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblparticipant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idplayer` int DEFAULT NULL,
  `score` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_playerid_idx` (`idplayer`),
  CONSTRAINT `fk_playerid` FOREIGN KEY (`idplayer`) REFERENCES `tblplayer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblparticipant`
--

LOCK TABLES `tblparticipant` WRITE;
/*!40000 ALTER TABLE `tblparticipant` DISABLE KEYS */;
INSERT INTO `tblparticipant` VALUES (134,15,0),(135,10,0),(136,15,0),(137,10,0),(138,15,0),(139,10,0),(140,15,0),(141,10,0),(142,15,0),(143,10,0),(144,10,0),(145,15,0),(146,15,0),(147,10,0),(148,15,0),(149,10,0),(150,15,0),(151,10,0),(152,10,0.5),(153,15,0.5),(154,10,1),(155,15,0),(156,10,0),(157,15,1),(158,15,1),(159,10,0),(160,10,0),(161,1,1),(162,1,0.5),(163,10,0.5),(164,2,0),(165,10,1),(166,10,0.5),(167,2,0.5),(168,10,1),(169,2,0),(170,10,0),(171,2,1),(172,10,0.5),(173,1,0.5);
/*!40000 ALTER TABLE `tblparticipant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblplayer`
--

DROP TABLE IF EXISTS `tblplayer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblplayer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `rating` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblplayer`
--

LOCK TABLES `tblplayer` WRITE;
/*!40000 ALTER TABLE `tblplayer` DISABLE KEYS */;
INSERT INTO `tblplayer` VALUES (1,'test','123456',NULL),(2,'test2','123456',NULL),(3,'test3','123456',NULL),(4,'test4','123456',NULL),(5,'test10','123456',NULL),(6,'test11','123456',NULL),(10,'thangngo','123456',NULL),(11,'tcpudp','123456',NULL),(12,'rmi','123456',NULL),(13,'rmi2','123456',NULL),(14,'rmitcp','123456',NULL),(15,'nt','123456',NULL);
/*!40000 ALTER TABLE `tblplayer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-26 17:14:31
