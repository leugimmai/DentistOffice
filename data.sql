# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.39)
# Database: dentistOffice
# Generation Time: 2018-03-24 20:32:56 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Appointments
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Appointments`;

CREATE TABLE `Appointments` (
  `apptDateTime` varchar(34) NOT NULL DEFAULT '',
  `patId` varchar(5) NOT NULL DEFAULT '',
  `dentId` varchar(5) DEFAULT NULL,
  `procCode` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`apptDateTime`,`patId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Appointments` WRITE;
/*!40000 ALTER TABLE `Appointments` DISABLE KEYS */;

INSERT INTO `Appointments` (`apptDateTime`, `patId`, `dentId`, `procCode`)
VALUES
	('December-31-1899 12:00am','A911','D201','P114'),
	('May 1, 2018, 11am','A910','D202','P114'),
	('May 1, 2018, 1pm','A902','D202','P114'),
	('May 1, 2018, 3pm','A905','D203','P650'),
	('May 1, 2018, 9am','A900','D201','P321'),
	('May 1, 2018, 9am','A903','D203','P114'),
	('May 2, 2018, 10am','A901','D201','P114'),
	('May 2, 2018, 11am','A901','D203','P114'),
	('May 3, 2018, 1pm','A906','A201','P790'),
	('May 3, 2018, 3pm','A909','D201','P910'),
	('May 3, 2018, 4pm','A907','D202','P122');

/*!40000 ALTER TABLE `Appointments` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Dentists
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Dentists`;

CREATE TABLE `Dentists` (
  `id` varchar(5) NOT NULL,
  `passwd` varchar(6) DEFAULT NULL,
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `office` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Dentists` WRITE;
/*!40000 ALTER TABLE `Dentists` DISABLE KEYS */;

INSERT INTO `Dentists` (`id`, `passwd`, `firstName`, `lastName`, `email`, `office`)
VALUES
	('D201','123456','Frank','Miller','frank@gmail.com','450'),
	('D202','12345','Susan','Cassidy','scass@yahoo.com','540'),
	('D203','99999','Jerry','York','jyork@hotmail.com','550'),
	('D204','9999','Wayne','Pettersen','wpatt@gmail.com','552');

/*!40000 ALTER TABLE `Dentists` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Patients
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Patients`;

CREATE TABLE `Patients` (
  `patId` varchar(5) NOT NULL,
  `passwd` varchar(6) DEFAULT NULL,
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `addr` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `insCo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`patId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Patients` WRITE;
/*!40000 ALTER TABLE `Patients` DISABLE KEYS */;

INSERT INTO `Patients` (`patId`, `passwd`, `firstName`, `lastName`, `addr`, `email`, `insCo`)
VALUES
	('A900','1234','Jimmy','Hawkins','Marietta','jhawk@yahoo.com','Cigna'),
	('A901','9999','Bill','Smith','Acworth','bsmith@gmail.com','Aetna'),
	('A902','8888','Teri','Smart','Atlanta','tsm@yahoo.com','Blue Cross'),
	('A903','7777','James','Roy','Acworth','jamesray@yahoo.com','Blue Cross'),
	('A904','5555','Mary','Wilson','Roswell','mwil@yahoo.com','Cigna'),
	('A905','1111','Faith','Adams','Roswell','faith@god.com','Cigna'),
	('A906','1111','Jerry','Jones','Dallas','jj@cowboys.com','Aetna'),
	('A907','9090','Carrie','Slater','Marietta','cslat@gmail.com','Cigna'),
	('A908','9898','Sara','Jefferson','Dallas','sjeff@yahoo.com','Blue Cross'),
	('A909','1234','Debbie','Johnson','Marietta','djohn@hotmail.com','Aetna'),
	('A910','1111','Martha','Stewart','Marietta','mstew@gmail.com','Blue Cross'),
	('A911','1234','John','Franco','Marietta','jfranco@yahoo.com','Blue Cross');

/*!40000 ALTER TABLE `Patients` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Procedures
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Procedures`;

CREATE TABLE `Procedures` (
  `procCode` varchar(4) NOT NULL,
  `procName` varchar(30) DEFAULT NULL,
  `procDesc` varchar(60) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `image` longtext,
  PRIMARY KEY (`procCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Procedures` WRITE;
/*!40000 ALTER TABLE `Procedures` DISABLE KEYS */;

INSERT INTO `Procedures` (`procCode`, `procName`, `procDesc`, `cost`, `image`)
VALUES
	('P114','Cleaning/Exam','Teeth Cleaning and a Dentist Exam',99.99,'http://mosaldentalcare.com/wp-content/uploads/2015/02/Professional-Teeth-Cleaning-Jackson-MS.jpg\n'),
	('P119','Xrays','Take Xrays of all teeth',320,'https://www.videodental.com/wp-content/uploads/2017/11/Digital-Intraoral-Sensors-Xray.jpg'),
	('P122','Whitening','Teeth Whitening',129.99,'http://5star-dental.com/wp-content/uploads/2014/09/white1.jpg'),
	('P321','Cavity','Fill a cavity',319,'http://drvenmar.com/newsite/wp-content/uploads/2013/07/cavity-tooth-decay-large.jpg'),
	('P650','Top Dentures','Add top dentures',1950,'http://cdn-img.instyle.com/sites/default/files/styles/684xflex/public/images/2015/05/052615-white-teeth-lead.jpg?itok=4g_Agrzv'),
	('P660','Bottom Dentures','Add bottom dentures',1950,'http://cdn-img.instyle.com/sites/default/files/styles/684xflex/public/images/2015/05/052615-white-teeth-lead.jpg?itok=4g_Agrzv'),
	('P780','Crown','Putting a crown on a bad tooth',795,'https://cosmeticdentistinformation.files.wordpress.com/2011/05/dental-crown.jpg'),
	('P790','Root Canal','Replace bad tooth with a new tooth',1019,'http://www.medifee.com/blog/wp-content/uploads/2016/03/endodontic-treatment-root-canal.png'),
	('P910','Replace Teeth','Replace every single tooth ',5000,'http://cdn-img.instyle.com/sites/default/files/styles/684xflex/public/images/2015/05/052615-white-teeth-lead.jpg?itok=4g_Agrzv');

/*!40000 ALTER TABLE `Procedures` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
