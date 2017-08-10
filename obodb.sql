-- MySQL dump 10.13  Distrib 5.7.19, for macos10.12 (x86_64)
--
-- Host: localhost    Database: obodb
-- ------------------------------------------------------
-- Server version	5.7.19

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

-- all the drops
drop database if exists `obodb`;
create database `obodb`;
use `obodb`;

drop user if exists 'hr'@'%';
drop user if exists 'finance'@'%';
drop user if exists 'salesManager'@'%';
drop user if exists 'talentManager'@'%';

  --
  -- Table structure for table `assignment`
  --

  DROP TABLE IF EXISTS `assignment`;
  /*!40101 SET @saved_cs_client     = @@character_set_client */;
  /*!40101 SET character_set_client = utf8 */;
  CREATE TABLE `assignment` (
    `EmployeeID` smallint(6) NOT NULL,
    `ProjectID` smallint(6) NOT NULL,
    `StartDate` date NOT NULL,
    `EndDate` date NOT NULL,
    PRIMARY KEY (`EmployeeID`,`ProjectID`,`StartDate`),
    KEY `ProjectID` (`ProjectID`),
    CONSTRAINT `assignment_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `Employee` (`EmployeeID`),
    CONSTRAINT `assignment_ibfk_2` FOREIGN KEY (`ProjectID`) REFERENCES `Project` (`ProjectID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
  /*!40101 SET character_set_client = @saved_cs_client */;

  --
  -- Dumping data for table `assignment`
  --

LOCK TABLES `assignment` WRITE;
/*!40000 ALTER TABLE `assignment` DISABLE KEYS */;
INSERT INTO `assignment` VALUES (1,1,'2009-12-12','2010-12-12');
/*!40000 ALTER TABLE `assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `DepartmentID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `Department Name` varchar(30) NOT NULL,
  PRIMARY KEY (`DepartmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department`(`Department Name`) VALUES ('Department of Things');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `EmployeeID` smallint(6) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `DepartmentID` tinyint(4) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `NIN` char(9) NOT NULL,
  `Bank Number` varchar(34) NOT NULL,
  `Starting Salary` int(11) NOT NULL,
  PRIMARY KEY (`EmployeeID`),
  KEY `DepartmentID` (`DepartmentID`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`DepartmentID`) REFERENCES `department` (`DepartmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Johnny Test',1,'Some Address','SD23GH45G','234a456',100000);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `ProjectID` smallint(6) NOT NULL AUTO_INCREMENT,
  `ProjectName` varchar(30) NOT NULL,
  PRIMARY KEY (`ProjectID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'Some Project');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesEmployee`
--

DROP TABLE IF EXISTS `salesEmployee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salesEmployee` (
  `EmployeeID` smallint(6) NOT NULL,
  `CommissionRate` float NOT NULL,
  `TotalSales` bigint(20) NOT NULL,
  KEY `EmployeeID` (`EmployeeID`),
  CONSTRAINT `salesemployee_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `Employee` (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesEmployee`
--

LOCK TABLES `salesEmployee` WRITE;
/*!40000 ALTER TABLE `salesEmployee` DISABLE KEYS */;
INSERT INTO `salesEmployee` VALUES (1,2,1000);
/*!40000 ALTER TABLE `salesEmployee` ENABLE KEYS */;
UNLOCK TABLES;

-- create users

create user 'hr'@'%' identified by 'hrPassword';
create user 'finance'@'%' identified by 'financePassword';
create user 'salesManager'@'%' identified by 'salesManagerPassword';
create user 'talentManager'@'%' identified by 'talentManagerPassword';

-- grant permis

-- hr
grant insert, select, update on obodb.employee to 'hr'@'%';
grant insert, select, update on obodb.department to 'hr'@'%';
grant insert, select, update on obodb.salesEmployee to 'hr'@'%';

-- finance
grant select on obodb.employee to 'finance'@'%';
grant select on obodb.salesEmployee to 'finance'@'%';

-- salesManager
grant select on obodb.employee to 'salesManager'@'%';
grant select on obodb.salesEmployee to 'salesManager'@'%';

-- talentManager
grant insert, select, update on obodb.assignment to 'talentManager'@'%';
grant insert, select, update on obodb.project to 'talentManager'@'%';
grant select on obodb.employee to 'talentManager'@'%';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-10 12:50:46
