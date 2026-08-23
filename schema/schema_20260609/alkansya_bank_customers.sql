CREATE DATABASE  IF NOT EXISTS `alkansya` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `alkansya`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: alkansya
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `bank_customers`
--

DROP TABLE IF EXISTS `bank_customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_customers` (
  `customer_number` varchar(45) NOT NULL,
  `bday` varchar(45) NOT NULL,
  `cst_firstname` varchar(45) NOT NULL,
  `cst_lastname` varchar(45) NOT NULL,
  `mobile_nbr` varchar(45) NOT NULL,
  `modified_by` varchar(45) NOT NULL,
  `is_active` char(1) NOT NULL,
  `date_created` datetime NOT NULL,
  `date_last_updated` datetime NOT NULL,
  PRIMARY KEY (`customer_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_customers`
--

LOCK TABLES `bank_customers` WRITE;
/*!40000 ALTER TABLE `bank_customers` DISABLE KEYS */;
INSERT INTO `bank_customers` VALUES ('256397c989d643d0b1a04e4771e1c8d7','1996-03-08','Mark','Eleriaaaa','09690254110','ASD','Y','2026-05-11 01:25:10','2026-06-01 12:25:07'),('2a4bcf59000b49c18ec62c03080f31d6','1998-05-21','Juan','Dela Cruz','09171234567','admin','Y','2026-05-06 17:12:06','2026-05-06 17:12:06'),('dcdef25e0d214f77bf98aec822c3e265','1996-03-08','A','Eleria','09690254110','SYSTEM','Y','2026-05-11 01:22:37','2026-06-01 12:02:53'),('ef3c7837cbf24530ab3f4e209272d8dd','1996-03-08','Mark Angel','Eleria','09690254110','SYSTEM','Y','2026-05-06 17:14:14','2026-05-06 17:14:14');
/*!40000 ALTER TABLE `bank_customers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-09 17:17:13
