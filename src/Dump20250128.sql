CREATE DATABASE  IF NOT EXISTS `projektarbeit` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `projektarbeit`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: projektarbeit
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `ID Patient` int NOT NULL,
  `Vorname` varchar(45) NOT NULL,
  `Nachname` varchar(45) NOT NULL,
  `Alter` int NOT NULL,
  `Geschlecht` varchar(45) NOT NULL,
  `SVN Nummer` varchar(10) NOT NULL,
  `Geburtsdatum` date NOT NULL,
  `Adresse` varchar(45) NOT NULL,
  `Telefonnummer` varchar(45) NOT NULL,
  PRIMARY KEY (`ID Patient`,`SVN Nummer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1,'Jan','Hesch',21,'Männlich','6523041103','2003-11-04','Heiplweg 4','06642831735'),(2,'Hans','Guckindieluft',46,'Männlich','8924010179','1979-01-01','Kunstrasenweg 5','06642074983'),(3,'Lukas','Meier',35,'Männlich','1234567890','1989-04-15','Musterstaße 12','015123456789'),(4,'Emma','Schneider',29,'Weiblich','1234092141','1995-08-03','Hauptstraße 45','0176982432'),(5,'Leon','Fischer',42,'Männlich','2657211182','1982-11-21','Gartenweg 7','0664193227'),(6,'Mia','Weber',50,'Weiblich','4274120175','1975-01-12','Blumenstraße 18','06641720381'),(7,'Elias','Becker',61,'Männlich','8726090963','1963-09-09','Schulweg 9','06641928402'),(8,'Sofia','Hoffman',19,'Weiblich','6723220305','2005-03-22','Waldstraße 22','06646729832'),(9,'Paul','Schmitt',70,'Männlich','8926180654','1954-06-18','Kirchplatz 3','06649284724'),(10,'Lina','Wagner',28,'Weiblich','7629021296','1996-12-02','Wiesenweg 6','06649867312'),(11,'Lukas','Meier',35,'Männlich','1234567897','1989-04-15','Musterstraße 12, 10115 Berlin','0151-23456789'),(12,'Emma','Schneider',29,'Weiblich','2345678901','1995-08-03','Hauptstraße 45, 20095 Hamburg','0176-98765432'),(13,'Leon','Fischer',42,'Männlich','3456789012','1982-11-21','Gartenweg 7, 50667 Köln','0157-34567890'),(14,'Mia','Weber',50,'Weiblich','4567801234','1975-01-12','Blumenstraße 18, 80331 München','0172-56789012'),(15,'Elias','Becker',61,'Männlich','5678902345','1963-09-09','Schulweg 9, 04109 Leipzig','0151-67890123'),(16,'Sofia','Hoffmann',19,'Weiblich','6789123456','2005-03-22','Waldstraße 22, 89073 Ulm','0175-78901234'),(17,'Paul','Schmitt',70,'Männlich','7890134567','1954-06-18','Kirchplatz 3, 68159 Mannheim','0160-89012345'),(18,'Lina','Wagner',28,'Weiblich','8901234578','1996-12-02','Wiesenweg 6, 55116 Mainz','0179-90123456'),(19,'Finn','Schulz',34,'Männlich','9012456789','1990-07-14','Am Markt 4, 39104 Magdeburg','0159-01234567'),(20,'Anna','Bauer',48,'Weiblich','0234567890','1976-05-11','Brunnenstraße 10, 70173 Stuttgart','0174-12345678'),(21,'Noah','Klein',37,'Männlich','1123567890','1987-09-30','Bergstraße 25, 19053 Schwerin','0156-23456789'),(22,'Marie','Keller',25,'Weiblich','1223467890','1999-02-05','Lindenstraße 8, 06108 Halle','0178-34567890'),(23,'Ben','Maier',65,'Männlich','1334567890','1959-03-27','Sonnenallee 19, 14467 Potsdam','0153-45678901'),(24,'Lea','Hartmann',41,'Weiblich','1434567890','1983-10-19','Nordstraße 14, 30159 Hannover','0170-56789012'),(25,'Jonas','König',52,'Männlich','1523456890','1972-04-07','Südallee 3, 04109 Leipzig','0163-67890123'),(26,'Clara','Frank',46,'Weiblich','1634567890','1978-01-23','Dorfstraße 12, 40213 Düsseldorf','0172-78901234'),(27,'Felix','Weiß',36,'Männlich','1723456790','1988-08-10','Feldweg 2, 01069 Dresden','0155-89012345'),(28,'Lina','Richter',20,'Weiblich','1823567890','2004-12-25','Kastanienweg 7, 93047 Regensburg','0177-90123456'),(29,'Julian','Wolf',55,'Männlich','1923456890','1969-11-04','Mühlweg 1, 38100 Braunschweig','0152-01234567'),(30,'Charlotte','Neumann',39,'Weiblich','2024567890','1985-06-13','Heidestraße 20, 17489 Greifswald','0171-12345678'),(31,'Max','Braun',23,'Männlich','2123456790','2001-09-08','Seestraße 10, 68163 Mannheim','0151-67891234'),(32,'Ella','Zimmermann',26,'Weiblich','2224567890','1998-03-01','Mühlenweg 4, 48143 Münster','0173-98765432'),(33,'Luis','Hahn',60,'Männlich','2323457890','1964-08-30','Parkstraße 18, 99084 Erfurt','0154-78901234'),(34,'Nora','Krüger',35,'Weiblich','2434567890','1989-12-15','Schulstraße 7, 37073 Göttingen','0172-01234567'),(35,'Tim','Stein',43,'Männlich','2523456890','1981-04-23','Marktplatz 3, 89077 Ulm','0158-34567890'),(36,'Lena','Schwarz',32,'Weiblich','2624567890','1992-07-19','Hauptplatz 1, 74072 Heilbronn','0179-56789012'),(37,'Henry','Lang',44,'Männlich','2723467890','1980-11-29','Gartenstraße 5, 99084 Erfurt','0153-67890123'),(38,'Sarah','Brandt',22,'Weiblich','2834567890','2002-06-21','Ringstraße 8, 79104 Freiburg','0176-89012345'),(39,'Tom','Peters',30,'Männlich','2924567890','1994-03-09','Bahnhofstraße 6, 44135 Dortmund','0157-90123456'),(40,'Mila','Seidel',27,'Weiblich','3023456790','1997-05-05','Weggasse 12, 55116 Mainz','0174-23456789'),(41,'Theo','Fink',31,'Männlich','3123456790','1993-11-17','Neustadt 9, 96047 Bamberg','0159-34567890'),(42,'Hanna','Vogel',38,'Weiblich','3234567890','1986-01-29','Breitestraße 2, 85049 Ingolstadt','0175-45678901');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'projektarbeit'
--

--
-- Dumping routines for database 'projektarbeit'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-28 21:51:45
