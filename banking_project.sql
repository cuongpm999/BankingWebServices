-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: banking_project
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `EmployeeId` int NOT NULL,
  `Username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKAccount18917` (`EmployeeId`),
  CONSTRAINT `FKAccount18917` FOREIGN KEY (`EmployeeId`) REFERENCES `employee` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (2,2,'cuongpham','$2a$10$2yiae9.Vt.69/1Vp73SwGuH81B/XbzVq1LmevpOD6CmqhnPwawu1W'),(3,3,'thuha','$2a$10$2Vu4d8frErBxLUhzzj4cduSG/6qPu.3PEO4dBQFFoYn4oDnpQAMwa'),(4,4,'phananh','$2a$10$gSZPx/n/zre4703KITTUKuHvyCA.cgN479YVpR.qyYsN/TVA.Ouy.'),(5,5,'quan','$2a$10$lDS6Ui/lUnPA5VsAKWWxhO35Va6Gm1v6SaJsQStFPMxGKAAN/zH7a'),(6,6,'thduong','$2a$10$B4ioEnLvzun1D3M4xhPEJe1WkDrR0tI1Da57GHNaBAU3ekxAMSWwm'),(7,7,'thhang','$2a$10$y4a.DrCmnL2rK3iYOuirIudRdirKO2J.vgntEEcm5guIoHBW.WFaa');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bankaccount`
--

DROP TABLE IF EXISTS `bankaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bankaccount` (
  `Id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BankAccountTypeId` int NOT NULL,
  `Balance` double NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKBankAccoun597855` (`BankAccountTypeId`),
  CONSTRAINT `FKBankAccoun597855` FOREIGN KEY (`BankAccountTypeId`) REFERENCES `bankaccounttype` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bankaccount`
--

LOCK TABLES `bankaccount` WRITE;
/*!40000 ALTER TABLE `bankaccount` DISABLE KEYS */;
INSERT INTO `bankaccount` VALUES ('18727409988066',4,1000000,1),('24287938923402',1,0,1),('31047017984162',5,10000,1),('38915117578712',5,5000000,1),('39135280632675',5,3360000,1),('41629451367527',2,0,1),('43022291439594',4,6999999,1),('52413784531573',1,5500000,1),('54133078555720',2,20000,1),('72358008834044',2,0,1),('89173617975489',1,100000,1);
/*!40000 ALTER TABLE `bankaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bankaccounttype`
--

DROP TABLE IF EXISTS `bankaccounttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bankaccounttype` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bankaccounttype`
--

LOCK TABLES `bankaccounttype` WRITE;
/*!40000 ALTER TABLE `bankaccounttype` DISABLE KEYS */;
INSERT INTO `bankaccounttype` VALUES (1,'Silver','bạc',1),(2,'Gold','vàng',1),(4,'Platinum','bạch kim',1),(5,'Diamond','kim cương',1),(6,'aa','',0);
/*!40000 ALTER TABLE `bankaccounttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `createdbankaccount`
--

DROP TABLE IF EXISTS `createdbankaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `createdbankaccount` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `CustomerId` int NOT NULL,
  `EmployeeId` int NOT NULL,
  `BankAccountId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DateCreate` datetime NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `BankAccountId_UNIQUE` (`BankAccountId`),
  KEY `FKCreatedBan431903` (`EmployeeId`),
  KEY `FKCreatedBan24935` (`CustomerId`),
  CONSTRAINT `CreatedBankAccount_ibfk_1` FOREIGN KEY (`BankAccountId`) REFERENCES `bankaccount` (`Id`),
  CONSTRAINT `FKCreatedBan24935` FOREIGN KEY (`CustomerId`) REFERENCES `customer` (`Id`),
  CONSTRAINT `FKCreatedBan431903` FOREIGN KEY (`EmployeeId`) REFERENCES `employee` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `createdbankaccount`
--

LOCK TABLES `createdbankaccount` WRITE;
/*!40000 ALTER TABLE `createdbankaccount` DISABLE KEYS */;
INSERT INTO `createdbankaccount` VALUES (1,21,2,'24287938923402','2021-11-20 22:29:49'),(2,21,2,'18727409988066','2021-11-21 00:55:26'),(3,21,2,'39135280632675','2021-11-21 11:52:35'),(4,21,2,'52413784531573','2021-11-21 12:31:13'),(5,22,2,'89173617975489','2021-11-22 13:19:52'),(6,22,2,'54133078555720','2021-11-24 19:46:09'),(7,21,2,'41629451367527','2021-11-25 15:11:11'),(8,22,2,'72358008834044','2021-11-25 22:39:52'),(9,22,2,'38915117578712','2021-11-25 22:40:52'),(10,23,2,'31047017984162','2021-11-25 22:41:22'),(11,23,2,'43022291439594','2021-11-25 22:41:35');
/*!40000 ALTER TABLE `createdbankaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditaccount`
--

DROP TABLE IF EXISTS `creditaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creditaccount` (
  `LimitBalance` double NOT NULL,
  `BankAccountId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`BankAccountId`),
  CONSTRAINT `FKCreditAcco491922` FOREIGN KEY (`BankAccountId`) REFERENCES `bankaccount` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditaccount`
--

LOCK TABLES `creditaccount` WRITE;
/*!40000 ALTER TABLE `creditaccount` DISABLE KEYS */;
INSERT INTO `creditaccount` VALUES (100000000,'18727409988066'),(10000000,'24287938923402'),(100000000,'31047017984162'),(10000000,'43022291439594'),(100000000,'54133078555720');
/*!40000 ALTER TABLE `creditaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `FullName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IdCard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DateOfBirth` date NOT NULL,
  `Address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (21,'Cường Mạnh Phạm','123444455612','2021-11-10','Hải Dương',1),(22,'Lê Đức Phan Anh','434534534458','2000-04-22','Hà Nội',1),(23,'Nguyên Mạnh Trung','453478912347','1986-12-14','Hà Nội',1),(24,'Nguyễn Thanh Tuyến','945643154112','2000-01-06','Hà Nội',1),(25,'Trần Đức Nam','131314414414','2015-03-26','Hà Nam',1),(26,'Trịnh Vinh Toàn','851123020044','2000-03-08','Hưng Yên',1),(27,'Dương Ngọc Duy','561632187844','2000-10-15','Hà Nội',1),(28,'Lê Văn Sang','123123412455','2015-02-25','Bắc Ninh',1),(29,'Đào Đức Thành','100018946475','2000-11-20','Hải Dương',1),(30,'Hồ Quang Hiếu','765746465646','2003-02-13','Bắc Giang',1),(31,'Vũ Thế Dương','074631385219','2000-07-29','Nam Định',1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `depositaccount`
--

DROP TABLE IF EXISTS `depositaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `depositaccount` (
  `InterestRate` double NOT NULL,
  `MinimumBalance` double NOT NULL,
  `BankAccountId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`BankAccountId`),
  CONSTRAINT `FKDepositAcc917662` FOREIGN KEY (`BankAccountId`) REFERENCES `bankaccount` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `depositaccount`
--

LOCK TABLES `depositaccount` WRITE;
/*!40000 ALTER TABLE `depositaccount` DISABLE KEYS */;
INSERT INTO `depositaccount` VALUES (2.1,200000,'38915117578712'),(3.33,100000,'39135280632675'),(1.5,100000,'41629451367527'),(0.6,50000,'52413784531573'),(1.4,100000,'72358008834044'),(1.4,50000,'89173617975489');
/*!40000 ALTER TABLE `depositaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `IdCard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FullName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DateOfBirth` date NOT NULL,
  `Address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Level` int NOT NULL,
  `Seniority` int NOT NULL,
  `Position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (2,'001200014666','Phạm Cường','2000-01-06','Hải Dương',12,10,'MANAGER',1),(3,'001200014666','Thu Hà','2021-11-17','Hà Nội',12,12,'TELLER',1),(4,'001200014669','Lê Đức Phan Anh','2000-04-22','Hà Nội',12,5,'MANAGER',1),(5,'121300014666','Trần Văn Quân','2018-03-26','Hà Nội',5,1,'TELLER',1),(6,'768700043543','Lê Thùy Dương','2021-11-08','Hưng Yên',5,2,'TELLER',1),(7,'987700021214','Nguyễn Thanh Hằng','2008-06-26','Quảng Ninh',12,5,'TELLER',1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary`
--

DROP TABLE IF EXISTS `salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salary` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `EmployeeId` int NOT NULL,
  `BasicSalary` double NOT NULL,
  `BonusSalary` double NOT NULL,
  `DateStart` date NOT NULL,
  `DateEnd` date NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKSalary764021` (`EmployeeId`),
  CONSTRAINT `FKSalary764021` FOREIGN KEY (`EmployeeId`) REFERENCES `employee` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
INSERT INTO `salary` VALUES (1,2,15000000,2900400,'2021-11-01','2021-11-30'),(2,4,10000000,22000,'2021-11-01','2021-11-30'),(3,3,5000000,0,'2021-11-01','2021-11-30');
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Type` varchar(255) NOT NULL,
  `DepositAccountId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `EmployeeId` int NOT NULL,
  `CreditAccountId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CustomerId` int NOT NULL,
  `DateCreate` datetime NOT NULL,
  `Money` double NOT NULL,
  `AfterBalanceDeposit` double DEFAULT NULL,
  `AfterBalanceCredit` double DEFAULT NULL,
  `Note` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKTransactio250053` (`CustomerId`),
  KEY `FKTransactio206785` (`EmployeeId`),
  KEY `FKTransactio651323` (`DepositAccountId`),
  KEY `FKTransactio688416` (`CreditAccountId`),
  CONSTRAINT `FKTransactio206785` FOREIGN KEY (`EmployeeId`) REFERENCES `employee` (`Id`),
  CONSTRAINT `FKTransactio250053` FOREIGN KEY (`CustomerId`) REFERENCES `customer` (`Id`),
  CONSTRAINT `FKTransactio651323` FOREIGN KEY (`DepositAccountId`) REFERENCES `depositaccount` (`BankAccountId`),
  CONSTRAINT `FKTransactio688416` FOREIGN KEY (`CreditAccountId`) REFERENCES `creditaccount` (`BankAccountId`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (12,'DEPOSIT','39135280632675',4,NULL,21,'2021-11-21 19:38:07',1000000,2500000,0,''),(13,'DEPOSIT','52413784531573',4,NULL,21,'2021-11-21 19:57:12',100000,300000,0,''),(14,'DEPOSIT','52413784531573',4,NULL,21,'2021-11-21 20:00:26',100000,400000,0,''),(15,'DEPOSIT','52413784531573',4,NULL,21,'2021-11-21 20:03:56',100000,500000,0,''),(17,'CREDIT',NULL,4,'18727409988066',21,'2021-11-21 20:37:30',100000,0,100000,''),(18,'CREDIT',NULL,4,'18727409988066',21,'2021-11-21 20:41:18',100000,0,200000,''),(19,'DEPOSIT','39135280632675',4,NULL,21,'2021-11-21 21:17:06',1000000,3500000,0,''),(20,'CREDIT',NULL,4,'24287938923402',21,'2021-11-21 21:29:27',100000,0,100000,''),(21,'PAYMENT','39135280632675',2,'24287938923402',21,'2021-11-22 00:47:59',50000,3450000,50000,''),(22,'PAYMENT','39135280632675',2,'24287938923402',21,'2021-11-22 00:52:28',10000,3440000,40000,''),(23,'PAYMENT','39135280632675',2,'24287938923402',21,'2021-11-22 00:53:15',40000,3400000,0,''),(24,'PAYMENT','39135280632675',2,'24287938923402',21,'2021-11-22 01:04:43',0,3400000,0,''),(25,'PAYMENT','39135280632675',2,'18727409988066',21,'2021-11-22 01:10:18',0,3400000,20000000,'3500000'),(26,'PAYMENT','39135280632675',2,'18727409988066',21,'2021-11-22 01:29:02',30000,3370000,19970000,''),(27,'PAYMENT','39135280632675',2,'18727409988066',21,'2021-11-22 09:52:06',20000,3350000,0,''),(28,'DEPOSIT','39135280632675',2,NULL,21,'2021-11-22 10:36:49',10000,3360000,0,''),(29,'CREDIT',NULL,2,'18727409988066',21,'2021-11-22 11:07:01',1000000,0,1000000,''),(30,'DEPOSIT','39135280632675',2,NULL,21,'2021-11-22 11:41:05',0,3360000,0,''),(31,'CREDIT',NULL,2,'54133078555720',22,'2021-11-24 19:46:46',20000,0,20000,''),(32,'DEPOSIT','52413784531573',2,NULL,21,'2021-11-25 12:02:24',5000000,5500000,0,''),(33,'CREDIT',NULL,2,'31047017984162',23,'2021-11-25 22:42:25',10000,0,10000,''),(34,'DEPOSIT','38915117578712',2,NULL,22,'2021-11-25 22:42:58',5000000,5000000,0,''),(35,'CREDIT',NULL,2,'43022291439594',23,'2021-11-25 22:48:51',6999999,0,6999999,'');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-26 17:22:38
