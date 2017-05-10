-- MySQL dump 10.13  Distrib 5.6.30, for Linux (x86_64)
--
-- Host: localhost    Database: OurCompany
-- ------------------------------------------------------
-- Server version	5.6.30

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
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Account` (
  `UserID` varchar(15) NOT NULL DEFAULT '',
  `Password` varchar(150) NOT NULL,
  `Subname` varchar(20) DEFAULT NULL,
  `Name` varchar(10) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `Email` varchar(30) NOT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
INSERT INTO `Account` VALUES ('admin','*B7BCAF7C7CCE96D8B7168674E5D812FA73C349B6','admin','admin','','');
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AnimalCondition`
--

DROP TABLE IF EXISTS `AnimalCondition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AnimalCondition` (
  `SerialNo` int(11) NOT NULL,
  `AvgTemp` float NOT NULL,
  `AvgHeart` int(11) NOT NULL,
  `Step` int(11) NOT NULL,
  `CheckTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY `SerialNo` (`SerialNo`),
  CONSTRAINT `AnimalCondition_ibfk_1` FOREIGN KEY (`SerialNo`) REFERENCES `Product` (`SerialNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AnimalCondition`
--

LOCK TABLES `AnimalCondition` WRITE;
/*!40000 ALTER TABLE `AnimalCondition` DISABLE KEYS */;
INSERT INTO `AnimalCondition` VALUES (1,36.5,100,360,'2017-05-07 11:52:44');
/*!40000 ALTER TABLE `AnimalCondition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AnimalInfo`
--

DROP TABLE IF EXISTS `AnimalInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AnimalInfo` (
  `AnimalNo` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` varchar(15) NOT NULL,
  `AnimalIndex` int(11) NOT NULL,
  `SerialNo` int(11) DEFAULT NULL,
  `Name` varchar(20) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Birth` date DEFAULT NULL,
  `Photo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`AnimalNo`),
  KEY `UserID` (`UserID`),
  KEY `AnimalIndex` (`AnimalIndex`),
  KEY `SerialNo` (`SerialNo`),
  CONSTRAINT `AnimalInfo_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `Account` (`UserID`),
  CONSTRAINT `AnimalInfo_ibfk_2` FOREIGN KEY (`AnimalIndex`) REFERENCES `AnimalKind` (`AnimalIndex`),
  CONSTRAINT `AnimalInfo_ibfk_3` FOREIGN KEY (`SerialNo`) REFERENCES `Product` (`SerialNo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AnimalInfo`
--

LOCK TABLES `AnimalInfo` WRITE;
/*!40000 ALTER TABLE `AnimalInfo` DISABLE KEYS */;
INSERT INTO `AnimalInfo` VALUES (3,'admin',-1,1,'admin','admin',NULL,NULL);
/*!40000 ALTER TABLE `AnimalInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AnimalKind`
--

DROP TABLE IF EXISTS `AnimalKind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AnimalKind` (
  `AnimalIndex` int(11) NOT NULL AUTO_INCREMENT,
  `AnimalType` varchar(20) NOT NULL,
  `AnimalRace` varchar(20) NOT NULL,
  PRIMARY KEY (`AnimalIndex`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AnimalKind`
--

LOCK TABLES `AnimalKind` WRITE;
/*!40000 ALTER TABLE `AnimalKind` DISABLE KEYS */;
INSERT INTO `AnimalKind` VALUES (-1,'','');
/*!40000 ALTER TABLE `AnimalKind` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AnimalMeal`
--

DROP TABLE IF EXISTS `AnimalMeal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AnimalMeal` (
  `SerialNo` int(11) NOT NULL,
  `Date` date NOT NULL,
  `note` varchar(100) DEFAULT NULL,
  KEY `SerialNo` (`SerialNo`),
  CONSTRAINT `AnimalMeal_ibfk_1` FOREIGN KEY (`SerialNo`) REFERENCES `Product` (`SerialNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AnimalMeal`
--

LOCK TABLES `AnimalMeal` WRITE;
/*!40000 ALTER TABLE `AnimalMeal` DISABLE KEYS */;
/*!40000 ALTER TABLE `AnimalMeal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AnimalWalk`
--

DROP TABLE IF EXISTS `AnimalWalk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AnimalWalk` (
  `SerialNo` int(11) NOT NULL,
  `STime` datetime NOT NULL,
  `ETime` datetime NOT NULL,
  `Distance` int(11) NOT NULL,
  KEY `SerialNo` (`SerialNo`),
  CONSTRAINT `AnimalWalk_ibfk_1` FOREIGN KEY (`SerialNo`) REFERENCES `Product` (`SerialNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AnimalWalk`
--

LOCK TABLES `AnimalWalk` WRITE;
/*!40000 ALTER TABLE `AnimalWalk` DISABLE KEYS */;
/*!40000 ALTER TABLE `AnimalWalk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Board`
--

DROP TABLE IF EXISTS `Board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Board` (
  `BoardNo` int(11) NOT NULL AUTO_INCREMENT,
  `BoardType` int(11) NOT NULL,
  `UserID` varchar(15) NOT NULL,
  `Title` varchar(50) NOT NULL,
  `Comment` varchar(100) NOT NULL,
  `Date` datetime NOT NULL,
  `Visit` int(11) NOT NULL DEFAULT '0',
  `Good` int(11) NOT NULL DEFAULT '0',
  `Visible` tinyint(1) NOT NULL DEFAULT '1',
  `Report` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`BoardNo`),
  KEY `BoardType` (`BoardType`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `Board_ibfk_1` FOREIGN KEY (`BoardType`) REFERENCES `BoardTitle` (`BoardType`),
  CONSTRAINT `Board_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `Account` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Board`
--

LOCK TABLES `Board` WRITE;
/*!40000 ALTER TABLE `Board` DISABLE KEYS */;
/*!40000 ALTER TABLE `Board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BoardTitle`
--

DROP TABLE IF EXISTS `BoardTitle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BoardTitle` (
  `BoardType` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `SubName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`BoardType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BoardTitle`
--

LOCK TABLES `BoardTitle` WRITE;
/*!40000 ALTER TABLE `BoardTitle` DISABLE KEYS */;
/*!40000 ALTER TABLE `BoardTitle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PetList`
--

DROP TABLE IF EXISTS `PetList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PetList` (
  `No` int(11) NOT NULL,
  `AnimalNo` int(11) NOT NULL,
  KEY `No` (`No`),
  KEY `AnimalNo` (`AnimalNo`),
  CONSTRAINT `PetList_ibfk_1` FOREIGN KEY (`No`) REFERENCES `PetSitter` (`No`),
  CONSTRAINT `PetList_ibfk_2` FOREIGN KEY (`AnimalNo`) REFERENCES `AnimalInfo` (`AnimalNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PetList`
--

LOCK TABLES `PetList` WRITE;
/*!40000 ALTER TABLE `PetList` DISABLE KEYS */;
/*!40000 ALTER TABLE `PetList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PetSitter`
--

DROP TABLE IF EXISTS `PetSitter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PetSitter` (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` varchar(15) NOT NULL,
  `Title` varchar(50) NOT NULL,
  `Date` date NOT NULL,
  `Term` date NOT NULL,
  `Feedback` varchar(500) DEFAULT NULL,
  `Close` tinyint(1) NOT NULL DEFAULT '0',
  `Visible` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`No`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `PetSitter_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `Account` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PetSitter`
--

LOCK TABLES `PetSitter` WRITE;
/*!40000 ALTER TABLE `PetSitter` DISABLE KEYS */;
/*!40000 ALTER TABLE `PetSitter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product` (
  `SerialNo` int(11) NOT NULL AUTO_INCREMENT,
  `Version` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`SerialNo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` VALUES (1,'admin');
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reply`
--

DROP TABLE IF EXISTS `Reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reply` (
  `BoardNo` int(11) NOT NULL,
  `UserID` char(15) NOT NULL,
  `Reply` varchar(200) NOT NULL,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Visible` tinyint(1) NOT NULL DEFAULT '1',
  KEY `BoardNo` (`BoardNo`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `Reply_ibfk_1` FOREIGN KEY (`BoardNo`) REFERENCES `Board` (`BoardNo`),
  CONSTRAINT `Reply_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `Account` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reply`
--

LOCK TABLES `Reply` WRITE;
/*!40000 ALTER TABLE `Reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Vaccine`
--

DROP TABLE IF EXISTS `Vaccine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Vaccine` (
  `AnimalNo` int(11) NOT NULL,
  `Count` int(11) NOT NULL,
  `Date` date NOT NULL,
  KEY `AnimalNo` (`AnimalNo`),
  CONSTRAINT `Vaccine_ibfk_1` FOREIGN KEY (`AnimalNo`) REFERENCES `AnimalInfo` (`AnimalNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Vaccine`
--

LOCK TABLES `Vaccine` WRITE;
/*!40000 ALTER TABLE `Vaccine` DISABLE KEYS */;
/*!40000 ALTER TABLE `Vaccine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VaccineCycle`
--

DROP TABLE IF EXISTS `VaccineCycle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VaccineCycle` (
  `VaccineNo` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Count` int(11) NOT NULL,
  PRIMARY KEY (`VaccineNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VaccineCycle`
--

LOCK TABLES `VaccineCycle` WRITE;
/*!40000 ALTER TABLE `VaccineCycle` DISABLE KEYS */;
/*!40000 ALTER TABLE `VaccineCycle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-08  0:24:43
