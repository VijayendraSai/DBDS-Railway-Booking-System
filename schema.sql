CREATE DATABASE  IF NOT EXISTS `railway_booking` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `railway_booking`;
-- MySQL dump 10.13  Distrib 8.0.40, for macos14 (x86_64)
--
-- Host: localhost    Database: railway_booking
-- ------------------------------------------------------
-- Server version	8.4.3

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `UKdwk6cx0afu8bs9o4t536v1j5v` (`email`),
  UNIQUE KEY `UKirnrrncatp2fvw52vp45j7rlw` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'john@gmail.com','John','Doe','john','john'),(2,'mike@gmail.com','Mike','Johnson','mike','mike'),(3,'sarah@gmail.com','Sarah','Williams','sarah','sarah'),(8,'sai@gmail.com','sai','sai','sai','sai');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_service`
--

DROP TABLE IF EXISTS `customer_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_service` (
  `service_id` int NOT NULL AUTO_INCREMENT,
  `answer` text,
  `answer_timestamp` datetime(6) DEFAULT NULL,
  `is_answered` bit(1) DEFAULT NULL,
  `question` text,
  `question_timestamp` datetime(6) DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`service_id`),
  KEY `FKkgf6lo6x392bcam3491xt4ye3` (`customer_id`),
  CONSTRAINT `FKkgf6lo6x392bcam3491xt4ye3` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_service`
--

LOCK TABLES `customer_service` WRITE;
/*!40000 ALTER TABLE `customer_service` DISABLE KEYS */;
INSERT INTO `customer_service` VALUES (1,'The next train departs at 10:00 AM.','2024-12-09 09:15:00.000000',_binary '','What is the train schedule?','2024-12-09 09:00:00.000000',1),(2,'You can change your reservation online.','2024-12-08 14:45:00.000000',_binary '','How can I change my reservation?','2024-12-08 14:30:00.000000',1),(3,'Your train search details can be found on our website.','2024-12-09 19:10:06.607571',_binary '','Where can I search trains?','2024-12-09 18:59:10.719081',2),(4,'Your ticket details are sent to your email.','2024-12-09 10:15:00.000000',_binary '','Where can I find my ticket details?','2024-12-09 10:00:00.000000',1),(5,NULL,NULL,_binary '\0','What is the refund policy?','2024-12-09 11:30:00.000000',1),(6,'The train to Delhi departs at 2:00 PM.','2024-12-09 12:45:00.000000',_binary '','What time does the train to Delhi depart?','2024-12-09 12:30:00.000000',2),(7,NULL,NULL,_binary '\0','How can I add additional passengers to my booking?','2024-12-09 13:15:00.000000',1),(8,'You can contact customer support for ticket changes.','2024-12-09 14:00:00.000000',_binary '','Can I change the passenger name on my ticket?','2024-12-09 13:45:00.000000',2),(9,NULL,NULL,_binary '\0','What is the procedure for lost luggage?','2024-12-09 15:00:00.000000',1),(10,'Your train is on time and scheduled for departure.','2024-12-09 16:15:00.000000',_binary '','Is my train on time?','2024-12-09 16:00:00.000000',1),(11,'Yes, trains run on Christmas','2024-12-11 21:56:10.678434',_binary '','Are there Trains available on Christmas?','2024-12-11 21:49:16.325654',1),(12,'No, it is not delayed.','2024-12-11 22:11:24.271739',_binary '','Is train delayed?','2024-12-11 22:07:30.243052',1),(13,'In Railway Website','2024-12-11 22:33:29.205335',_binary '','Where do I cancel my reservation?','2024-12-11 22:29:00.836339',1),(14,'Yes, it is running.','2024-12-11 23:03:51.081476',_binary '','Is Train Padmavathi available?','2024-12-11 22:59:18.278319',1);
/*!40000 ALTER TABLE `customer_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `emp_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','REPRESENTATIVE') NOT NULL,
  `ssn` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `UKfopic1oh5oln2khj8eat6ino0` (`email`),
  UNIQUE KEY `UKf35rkopwr25n69dtp946lt3rh` (`ssn`),
  UNIQUE KEY `UKim8flsuftl52etbhgnr62d6wh` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'bob@gmail.com','Bob','Dol','bob','ADMIN','0000012345','bob'),(2,'mark.doe@example.com','Mark','Doe','mark','REPRESENTATIVE','0012345679','mark'),(3,'alice@gmail.com','Alice','Smith','alice','ADMIN','0000098765','alice'),(4,'james.bond@example.com','James','Bond','james','ADMIN','0000054321','james'),(6,'mike.brown@example.com','Mike','Brown','mike','REPRESENTATIVE','0056789012','mike');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `reservation_id` int NOT NULL AUTO_INCREMENT,
  `destination_arrival_time` time(6) DEFAULT NULL,
  `destination_station_name` varchar(255) DEFAULT NULL,
  `origin_arrival_time` time(6) DEFAULT NULL,
  `origin_station_name` varchar(255) DEFAULT NULL,
  `passenger_name` varchar(255) DEFAULT NULL,
  `passenger_type` varchar(255) DEFAULT NULL,
  `reservation_date` date DEFAULT NULL,
  `total_fare` float DEFAULT NULL,
  `train_name` varchar(255) DEFAULT NULL,
  `trip_type` varchar(255) DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `train_id` int DEFAULT NULL,
  `transit_line_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `FK41v6ueo0hiran65w8y1cta2c2` (`customer_id`),
  KEY `FK1dapn2n2425sb2gvys7me8g9w` (`train_id`),
  KEY `FKkqqqnuo32lavarkqr2qf6bydj` (`transit_line_name`),
  CONSTRAINT `FK1dapn2n2425sb2gvys7me8g9w` FOREIGN KEY (`train_id`) REFERENCES `train` (`train_id`),
  CONSTRAINT `FK41v6ueo0hiran65w8y1cta2c2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `FKkqqqnuo32lavarkqr2qf6bydj` FOREIGN KEY (`transit_line_name`) REFERENCES `transit_line` (`transit_line_name`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (13,'05:00:00.000000','Tirupati','03:00:00.000000','Lingampalli','John Doe','adult','2024-12-01',50,'Padmavathi','one-way',1,1111,'HYD_AP'),(14,'13:05:00.000000','Nellore','09:05:00.000000','Secunderabad','John Doe','senior','2024-12-24',39,'Hyderabad Express','round-trip',1,3333,'HYD_AP'),(15,'18:00:00.000000','Delhi','06:00:00.000000','Chennai','John Doe','disabled','2024-12-03',75,'Chennai Rajdhani','one-way',1,5555,'CHENNAI_DELHI'),(16,'14:10:00.000000','Bangalore','10:10:00.000000','Berhampur','John Doe','child','2024-12-26',56.25,'Bhubaneswar Mail','one-way',1,4444,'ODISHA_BLR'),(20,'12:00:00.000000','Bangalore','06:00:00.000000','Odisha','John Doe','adult','2024-12-26',200,'Odisha Express','round-trip',1,2222,'ODISHA_BLR'),(21,'05:00:00.000000','Tirupati','03:30:00.000000','Secunderabad','Mike Johnson','senior','2024-12-23',52,'Padmavathi Express','round-trip',2,1111,'HYD_AP'),(22,'10:00:00.000000','Anantapur','06:00:00.000000','Odisha','Mike Johnson','disabled','2024-12-26',75,'Odisha Express','round-trip',2,2222,'ODISHA_BLR'),(23,'05:00:00.000000','Delhi','22:00:00.000000','Chennai','Mike Johnson','adult','2024-12-20',150,'Chennai-Delhi Lightning Express','one-way',2,7777,'CHENNAI_DELHI'),(24,'05:00:00.000000','Delhi','22:00:00.000000','Chennai','John Doe','senior','2024-12-30',195,'Chennai-Delhi Lightning Express','round-trip',1,7777,'CHENNAI_DELHI'),(25,'11:05:00.000000','Guntur','07:05:00.000000','Lingampalli','Sarah Williams','adult','2024-12-17',30,'Hyderabad Express','one-way',3,3333,'HYD_AP'),(26,'10:00:00.000000','Anantapur','06:00:00.000000','Odisha','Sarah Williams','senior','2024-12-20',97.5,'Odisha Express','round-trip',3,2222,'ODISHA_BLR'),(27,'09:00:00.000000','Vellore','06:00:00.000000','Chennai','Sarah Williams','child','2024-12-28',56.25,'Chennai Rajdhani','one-way',3,5555,'CHENNAI_DELHI'),(28,'11:00:00.000000','Tirupati','08:00:00.000000','Lingampalli','John Doe','adult','2024-12-01',80,'Narayanadri Express','round-trip',1,8888,'HYD_AP_2'),(29,'13:05:00.000000','Nellore','09:05:00.000000','Secunderabad','John Doe','senior','2024-12-28',19.5,'Hyderabad Express','one-way',1,3333,'HYD_AP'),(30,'13:05:00.000000','Nellore','09:05:00.000000','Secunderabad','John Doe','child','2024-12-03',45,'Hyderabad Express','round-trip',1,3333,'HYD_AP'),(31,'08:00:00.000000','Berhampur','06:00:00.000000','Odisha','John Doe','adult','2024-12-29',50,'Odisha Express','one-way',1,2222,'ODISHA_BLR'),(32,'11:00:00.000000','Tirupati','08:00:00.000000','Lingampalli','Mike Johnson','adult','2024-12-01',80,'Narayanadri Express','round-trip',2,8888,'HYD_AP_2'),(33,'11:00:00.000000','Tirupati','08:00:00.000000','Lingampalli','Sarah Williams','adult','2024-12-28',40,'Narayanadri Express','one-way',3,8888,'HYD_AP_2'),(34,'15:05:00.000000','Tirupati','07:05:00.000000','Lingampalli','Mike Johnson','adult','2024-12-25',100,'Hyderabad Express','round-trip',2,3333,'HYD_AP'),(35,'11:00:00.000000','Tirupati','08:00:00.000000','Lingampalli','Mike Johnson','adult','2024-12-28',80,'Narayanadri Express','round-trip',2,8888,'HYD_AP_2'),(36,'15:05:00.000000','Tirupati','07:05:00.000000','Lingampalli','Sarah Williams','adult','2024-12-30',100,'Hyderabad Express','round-trip',3,3333,'HYD_AP'),(37,'05:00:00.000000','Tirupati','03:00:00.000000','Lingampalli','Sarah Williams','adult','2024-12-17',100,'Padmavathi Express','round-trip',3,1111,'HYD_AP'),(38,'08:00:00.000000','Berhampur','06:00:00.000000','Odisha','John Doe','disabled','2024-12-02',25,'Odisha Express','one-way',1,2222,'ODISHA_BLR'),(40,'04:00:00.000000','Guntur','03:00:00.000000','Lingampalli','Mike Johnson','disabled','2024-12-01',15,'Padmavathi Express','one-way',2,1111,'HYD_AP'),(41,'09:05:00.000000','Secunderabad','07:05:00.000000','Lingampalli','Mike Johnson','senior','2024-12-24',26,'Hyderabad Express','round-trip',2,3333,'HYD_AP'),(43,'13:05:00.000000','Nellore','07:05:00.000000','Lingampalli','Sarah Williams','adult','2024-12-24',40,'Hyderabad Express','one-way',3,3333,'HYD_AP');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `station_id` int NOT NULL,
  `train_id` int NOT NULL,
  `arrival_time` time(6) DEFAULT NULL,
  `departure_time` time(6) DEFAULT NULL,
  `stop_order` int DEFAULT NULL,
  PRIMARY KEY (`station_id`,`train_id`),
  KEY `fk_train` (`train_id`),
  CONSTRAINT `fk_station` FOREIGN KEY (`station_id`) REFERENCES `station` (`station_id`),
  CONSTRAINT `fk_train` FOREIGN KEY (`train_id`) REFERENCES `train` (`train_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (11111,1111,'03:00:00.000000','03:10:00.000000',1),(11111,3333,'07:05:00.000000','07:20:00.000000',1),(11111,8888,'08:00:00.000000','08:15:00.000000',1),(11112,1111,'03:30:00.000000','03:40:00.000000',2),(11112,3333,'09:05:00.000000','09:20:00.000000',2),(11113,1111,'04:00:00.000000','04:10:00.000000',3),(11113,3333,'11:05:00.000000','11:20:00.000000',3),(11114,1111,'04:30:00.000000','04:40:00.000000',4),(11114,3333,'13:05:00.000000','13:20:00.000000',4),(11115,1111,'05:00:00.000000','05:10:00.000000',5),(11115,3333,'15:05:00.000000','15:20:00.000000',5),(11115,8888,'11:00:00.000000','11:15:00.000000',3),(11116,8888,'09:30:00.000000','09:45:00.000000',2),(22221,2222,'06:00:00.000000','06:15:00.000000',1),(22221,4444,'08:10:00.000000','08:25:00.000000',1),(22222,2222,'08:00:00.000000','08:15:00.000000',2),(22222,4444,'10:10:00.000000','10:25:00.000000',2),(22223,2222,'10:00:00.000000','10:15:00.000000',3),(22223,4444,'12:10:00.000000','12:25:00.000000',3),(22224,2222,'12:00:00.000000','12:15:00.000000',4),(22224,4444,'14:10:00.000000','14:25:00.000000',4),(55551,5555,'06:00:00.000000','06:15:00.000000',1),(55551,7777,'22:00:00.000000','22:15:00.000000',1),(55552,5555,'09:00:00.000000','09:15:00.000000',2),(55552,7777,'23:00:00.000000','23:05:00.000000',2),(55553,5555,'14:00:00.000000','14:15:00.000000',3),(55553,7777,'02:30:00.000000','02:35:00.000000',3),(55554,5555,'18:00:00.000000','18:15:00.000000',4),(55554,7777,'05:00:00.000000','05:10:00.000000',4);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station` (
  `station_id` int NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`station_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55555 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (11111,'Hyderabad','Lingampalli','TL'),(11112,'Hyderabad','Secunderabad','TL'),(11113,'Guntur','Guntur','AP'),(11114,'Nellore','Nellore','AP'),(11115,'Tirupati','Tirupati','AP'),(22221,'Odisha','Odisha','OD'),(22222,'Berhampur','Berhampur','OD'),(22223,'Anantapur','Anantapur','AP'),(22224,'Bangalore','Bangalore','KA'),(55551,'Chennai','Chennai','TN'),(55552,'Vellore','Vellore','TN'),(55553,'Nagpur','Nagpur','MH'),(55554,'Delhi','Delhi','DL');
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train`
--

DROP TABLE IF EXISTS `train`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `train` (
  `train_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `transit_line_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`train_id`),
  KEY `fk_transit_line` (`transit_line_name`),
  CONSTRAINT `fk_transit_line` FOREIGN KEY (`transit_line_name`) REFERENCES `transit_line` (`transit_line_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8889 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train`
--

LOCK TABLES `train` WRITE;
/*!40000 ALTER TABLE `train` DISABLE KEYS */;
INSERT INTO `train` VALUES (1111,'Padmavathi Express','HYD_AP'),(2222,'Odisha Express','ODISHA_BLR'),(3333,'Hyderabad Express','HYD_AP'),(4444,'Bhubaneswar Mail','ODISHA_BLR'),(5555,'Chennai Rajdhani','CHENNAI_DELHI'),(7777,'Chennai-Delhi Lightning Express','CHENNAI_DELHI'),(8888,'Narayanadri Express','HYD_AP_2');
/*!40000 ALTER TABLE `train` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transit_line`
--

DROP TABLE IF EXISTS `transit_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transit_line` (
  `transit_line_name` varchar(255) NOT NULL,
  `base_fare` float NOT NULL,
  `total_stops` int NOT NULL,
  `destination_station_id` int DEFAULT NULL,
  `origin_station_id` int DEFAULT NULL,
  PRIMARY KEY (`transit_line_name`),
  KEY `fk_destination_station` (`destination_station_id`),
  KEY `fk_origin_station` (`origin_station_id`),
  CONSTRAINT `fk_destination_station` FOREIGN KEY (`destination_station_id`) REFERENCES `station` (`station_id`),
  CONSTRAINT `fk_origin_station` FOREIGN KEY (`origin_station_id`) REFERENCES `station` (`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transit_line`
--

LOCK TABLES `transit_line` WRITE;
/*!40000 ALTER TABLE `transit_line` DISABLE KEYS */;
INSERT INTO `transit_line` VALUES ('CHENNAI_DELHI',150,4,55554,55551),('HYD_AP',50,5,11115,11111),('HYD_AP_2',40,3,11115,11111),('ODISHA_BLR',100,4,22224,22221);
/*!40000 ALTER TABLE `transit_line` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-11 23:36:16
