-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.8


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema searchEngine
--

CREATE DATABASE IF NOT EXISTS searchEngine;
USE searchEngine;

--
-- Definition of table `site`
--

DROP TABLE IF EXISTS `site`;
CREATE TABLE `site` (
  `SiteId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(80) NOT NULL,
  `Url` varchar(200) NOT NULL,
  `Active` int(2) NOT NULL,
  PRIMARY KEY (`SiteId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Dumping data for table `site`
--

/*!40000 ALTER TABLE `site` DISABLE KEYS */;
INSERT INTO `site` (`SiteId`,`Name`,`Url`,`Active`) VALUES
 (1, 'Google', 'https://www.google.ie/', 1),
 (2, 'Yahoo', 'https://ie.search.yahoo.com/', 1),
 (3, 'Duck Duck Go', 'https://duckduckgo.com/', 1),
 (4, 'Bing', 'https://www.bing.com/', 1),
 (5, 'Ask', 'http://uk.ask.com/', 0);
/*!40000 ALTER TABLE `site` ENABLE KEYS */;


--
-- Definition of table `personSite`
--

DROP TABLE IF EXISTS `personSite`;
CREATE TABLE `personSite` (
  `PersonSiteId` int(11) NOT NULL AUTO_INCREMENT,  
  `PersonId` int(11) DEFAULT NULL,
  `SiteId` int(11) NOT NULL,
  PRIMARY KEY (`PersonSiteId`),
  KEY `PersonSitePerson` (`PersonId`),
  KEY `PersonSiteSite` (`SiteId`),
  CONSTRAINT `PersonSitePerson` FOREIGN KEY (`PersonId`) REFERENCES `person` (`personId`),
  CONSTRAINT `PersonSiteSite` FOREIGN KEY (`SiteId`) REFERENCES `site` (`SiteId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `personSite`
--

/*!40000 ALTER TABLE `personSite` DISABLE KEYS */;
INSERT INTO `personsite` (`PersonSiteId`,`PersonId`,`SiteId`) VALUES
 (1, 1, 1),
 (2, 1, 2),
 (3, 1, 3),
 (4, 1, 5),
 (5, 2, 1),
 (6, 2, 2);
/*!40000 ALTER TABLE `personSite` ENABLE KEYS */;


--
-- Definition of table `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `PersonId` int(11) NOT NULL AUTO_INCREMENT,
  `First_Name` varchar(80) NOT NULL,
  `Last_Name` varchar(80) NOT NULL,
  `DateOfBirth` date NOT NULL,
  `Email` varchar(80) NOT NULL,
  `Login` varchar(25) DEFAULT NULL,
  `Password` varchar(40) DEFAULT NULL,  
  `Permission` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`PersonId`),
  UNIQUE KEY `Login` (`Login`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `person`
--

/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`PersonId`,`First_Name`,`Last_Name`,`DateOfBirth`,`Email`,`Login`,`Password`,`Permission`) VALUES
 (1, 'Peter', 'Brady', '1984-06-29', 'peter@email.com', 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997','ROLE_ADMIN'),
 (2, 'John', 'Smith', '2000-01-01', 'jsmith@email.com', 'person', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'ROLE_USER');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;


SELECT * FROM person;
SELECT * FROM personSite;

select
        personsite0_.SiteId as SiteId1_0_ 
    from
        personSite personsite0_ 
    where
        personsite0_.PersonId=1;
