CREATE DATABASE  IF NOT EXISTS `navigator` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `navigator`;
-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: navigator
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.28-MariaDB

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
-- Table structure for table `bus_stations`
--

DROP TABLE IF EXISTS `bus_stations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus_stations` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `buses_id` bigint(20) unsigned NOT NULL,
  `stations_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bus_nodes_FK` (`buses_id`),
  KEY `bus_nodes_FK_1` (`stations_id`),
  CONSTRAINT `bus_nodes_FK` FOREIGN KEY (`buses_id`) REFERENCES `buses` (`id`),
  CONSTRAINT `bus_nodes_FK_1` FOREIGN KEY (`stations_id`) REFERENCES `stations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_stations`
--

LOCK TABLES `bus_stations` WRITE;
/*!40000 ALTER TABLE `bus_stations` DISABLE KEYS */;
INSERT INTO `bus_stations` VALUES (1,1,1),(2,1,2),(3,1,4),(4,1,3),(5,2,2),(6,2,4),(7,2,6),(9,3,6),(10,3,7),(11,3,5),(12,3,4);
/*!40000 ALTER TABLE `bus_stations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buses`
--

DROP TABLE IF EXISTS `buses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buses` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buses`
--

LOCK TABLES `buses` WRITE;
/*!40000 ALTER TABLE `buses` DISABLE KEYS */;
INSERT INTO `buses` VALUES (1,'A'),(2,'B'),(3,'C');
/*!40000 ALTER TABLE `buses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roads`
--

DROP TABLE IF EXISTS `roads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roads` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `distance` float NOT NULL,
  `from_stations_id` bigint(20) unsigned NOT NULL,
  `to_stations_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `routes_FK` (`from_stations_id`),
  KEY `routes_FK_1` (`to_stations_id`),
  CONSTRAINT `routes_FK` FOREIGN KEY (`from_stations_id`) REFERENCES `stations` (`id`),
  CONSTRAINT `routes_FK_1` FOREIGN KEY (`to_stations_id`) REFERENCES `stations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roads`
--

LOCK TABLES `roads` WRITE;
/*!40000 ALTER TABLE `roads` DISABLE KEYS */;
INSERT INTO `roads` VALUES (1,3,1,2),(2,3,2,1),(3,8,6,2),(4,3,2,4),(5,2,4,3),(6,10,3,1),(7,6,6,7),(8,6,7,6),(9,3,7,5),(11,6,5,4),(12,4,4,6);
/*!40000 ALTER TABLE `roads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stations`
--

DROP TABLE IF EXISTS `stations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stations` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stations`
--

LOCK TABLES `stations` WRITE;
/*!40000 ALTER TABLE `stations` DISABLE KEYS */;
INSERT INTO `stations` VALUES (1,'Jackson Street 140'),(2,'Oak Street 243'),(3,'Park Street 9'),(4,'Main Street 332'),(5,'Sunset Street 920'),(6,'Dogwood Street 93'),(7,'Green Street 204');
/*!40000 ALTER TABLE `stations` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-29  1:00:27
