-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: us-cdbr-east-04.cleardb.com    Database: heroku_3c475d904185207
-- ------------------------------------------------------
-- Server version	5.6.50-log

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
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `EmployeeId` int(11) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKAccount18917` (`EmployeeId`),
  CONSTRAINT `FKAccount18917` FOREIGN KEY (`EmployeeId`) REFERENCES `employee` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (2,2,'cuongpham','$2a$10$2yiae9.Vt.69/1Vp73SwGuH81B/XbzVq1LmevpOD6CmqhnPwawu1W'),(3,3,'thuha','$2a$10$2Vu4d8frErBxLUhzzj4cduSG/6qPu.3PEO4dBQFFoYn4oDnpQAMwa'),(4,4,'phananh','$2a$10$gSZPx/n/zre4703KITTUKuHvyCA.cgN479YVpR.qyYsN/TVA.Ouy.'),(5,5,'quan','$2a$10$lDS6Ui/lUnPA5VsAKWWxhO35Va6Gm1v6SaJsQStFPMxGKAAN/zH7a'),(6,6,'thduong','$2a$10$B4ioEnLvzun1D3M4xhPEJe1WkDrR0tI1Da57GHNaBAU3ekxAMSWwm'),(7,7,'thhang','$2a$10$y4a.DrCmnL2rK3iYOuirIudRdirKO2J.vgntEEcm5guIoHBW.WFaa'),(15,15,'tuyen','$2a$10$ltlfFsaF3u7SnESsKmo0ie0xaByYp4hUxzFQKdUDICukMMfIMrsKm');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bankaccount`
--

DROP TABLE IF EXISTS `bankaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bankaccount` (
  `Id` varchar(255) NOT NULL,
  `BankAccountTypeId` int(11) NOT NULL,
  `Balance` double NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKBankAccoun597855` (`BankAccountTypeId`),
  CONSTRAINT `FKBankAccoun597855` FOREIGN KEY (`BankAccountTypeId`) REFERENCES `bankaccounttype` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bankaccount`
--

LOCK TABLES `bankaccount` WRITE;
/*!40000 ALTER TABLE `bankaccount` DISABLE KEYS */;
INSERT INTO `bankaccount` VALUES ('03112628387171',1,256000,1),('04545044938713',1,50000,1),('05064156775472',1,2012000,1),('09563434440261',1,200000,1),('11758823951403',1,35000,1),('11941079948685',1,700000,1),('13502352759657',4,10000000,1),('14866681216031',4,102300,1),('17962646248116',5,0,1),('18727409988066',4,10855999,1),('20025345774654',2,4600205.33,1),('24287938923402',1,1000000,1),('27739202186857',1,0,1),('31047017984162',5,10000,0),('35642354779232',1,600000,1),('38915117578712',5,5105000,1),('39135280632675',5,4042190,1),('41629451367527',2,200000,1),('43022291439594',4,6949999,1),('51941167615654',1,500000,1),('52413784531573',1,3283000,1),('52436320481252',5,0,1),('53092704808078',1,2464700,1),('54133078555720',2,300000,1),('54331759325290',2,0,1),('70652794958440',1,1550000,1),('72358008834044',2,24793314,1),('78491851613791',1,50000000,0),('80030350077108',1,30000,1),('88872154161134',2,5075000,1),('89173617975489',1,212940,1),('95105016406939',2,6090000,1),('99624231464710',2,1471750,1);
/*!40000 ALTER TABLE `bankaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bankaccounttype`
--

DROP TABLE IF EXISTS `bankaccounttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bankaccounttype` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Note` varchar(255) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bankaccounttype`
--

LOCK TABLES `bankaccounttype` WRITE;
/*!40000 ALTER TABLE `bankaccounttype` DISABLE KEYS */;
INSERT INTO `bankaccounttype` VALUES (1,'Silver','bạc',1),(2,'Gold','vàng',1),(4,'Platinum','bạch kim',1),(5,'Diamond','kim cương',1),(6,'aa','',0),(15,'Bronze','đồng',0);
/*!40000 ALTER TABLE `bankaccounttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `createdbankaccount`
--

DROP TABLE IF EXISTS `createdbankaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `createdbankaccount` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CustomerId` int(11) NOT NULL,
  `EmployeeId` int(11) NOT NULL,
  `BankAccountId` varchar(255) NOT NULL,
  `DateCreate` datetime NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `BankAccountId_UNIQUE` (`BankAccountId`),
  KEY `FKCreatedBan431903` (`EmployeeId`),
  KEY `FKCreatedBan24935` (`CustomerId`),
  CONSTRAINT `CreatedBankAccount_ibfk_1` FOREIGN KEY (`BankAccountId`) REFERENCES `bankaccount` (`Id`),
  CONSTRAINT `FKCreatedBan24935` FOREIGN KEY (`CustomerId`) REFERENCES `customer` (`Id`),
  CONSTRAINT `FKCreatedBan431903` FOREIGN KEY (`EmployeeId`) REFERENCES `employee` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=235 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `createdbankaccount`
--

LOCK TABLES `createdbankaccount` WRITE;
/*!40000 ALTER TABLE `createdbankaccount` DISABLE KEYS */;
INSERT INTO `createdbankaccount` VALUES (1,21,2,'24287938923402','2021-11-20 22:29:49'),(2,21,2,'18727409988066','2021-11-21 00:55:26'),(3,21,2,'39135280632675','2021-11-21 11:52:35'),(4,21,2,'52413784531573','2021-11-21 12:31:13'),(5,22,2,'89173617975489','2021-11-22 13:19:52'),(6,22,2,'54133078555720','2021-11-24 19:46:09'),(7,21,2,'41629451367527','2021-11-25 15:11:11'),(8,22,2,'72358008834044','2021-11-25 22:39:52'),(9,22,2,'38915117578712','2021-11-25 22:40:52'),(10,23,2,'31047017984162','2021-11-25 22:41:22'),(11,23,2,'43022291439594','2021-11-25 22:41:35'),(15,22,4,'54331759325290','2021-11-26 15:40:21'),(25,45,15,'05064156775472','2021-11-26 16:17:27'),(35,45,15,'09563434440261','2021-11-26 16:18:44'),(45,45,15,'88872154161134','2021-11-26 16:20:07'),(55,55,15,'20025345774654','2021-11-26 16:20:33'),(65,65,15,'95105016406939','2021-11-26 16:20:53'),(75,75,15,'99624231464710','2021-11-26 16:21:11'),(85,85,15,'53092704808078','2021-11-26 16:21:31'),(95,55,15,'11941079948685','2021-11-26 16:21:58'),(105,65,15,'70652794958440','2021-11-26 16:22:18'),(115,75,15,'35642354779232','2021-11-26 16:22:32'),(125,85,15,'51941167615654','2021-11-26 16:22:49'),(135,30,2,'14866681216031','2021-11-28 00:11:11'),(145,23,4,'27739202186857','2021-12-03 16:02:34'),(155,23,4,'78491851613791','2021-12-03 16:11:57'),(165,23,4,'03112628387171','2021-12-03 16:22:45'),(175,31,2,'17962646248116','2021-12-03 22:28:45'),(185,31,2,'13502352759657','2021-12-03 22:29:46'),(195,31,2,'80030350077108','2021-12-03 22:43:54'),(205,26,2,'52436320481252','2021-12-07 17:10:28'),(215,24,2,'04545044938713','2021-12-07 18:41:16'),(225,24,2,'11758823951403','2021-12-07 18:54:30');
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
  `BankAccountId` varchar(255) NOT NULL,
  PRIMARY KEY (`BankAccountId`),
  CONSTRAINT `FKCreditAcco491922` FOREIGN KEY (`BankAccountId`) REFERENCES `bankaccount` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditaccount`
--

LOCK TABLES `creditaccount` WRITE;
/*!40000 ALTER TABLE `creditaccount` DISABLE KEYS */;
INSERT INTO `creditaccount` VALUES (10000000,'09563434440261'),(10000000,'11941079948685'),(100000000,'17962646248116'),(200000000,'18727409988066'),(10000000,'24287938923402'),(10000000,'27739202186857'),(100000000,'31047017984162'),(10000000,'35642354779232'),(10000000,'43022291439594'),(10000000,'51941167615654'),(20000000,'52436320481252'),(100000000,'54133078555720'),(12500000,'54331759325290'),(10000000,'70652794958440');
/*!40000 ALTER TABLE `creditaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `FullName` varchar(255) NOT NULL,
  `IdCard` varchar(255) NOT NULL,
  `DateOfBirth` date NOT NULL,
  `Address` varchar(255) NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `Email` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1135 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (21,'Cường Mạnh Phạm','123444455612','1998-11-10','Hải Dương',1,'phamcuongth2000@gmail.com'),(22,'Lê Trọng Phan','434534534458','2000-04-22','Hà Nội',1,'leducphananh@gmail.com'),(23,'Nguyên Mạnh Trung','453478912347','1986-12-14','Hà Nội',1,'leducphananhz@gmail.com'),(24,'Nguyễn Thanh Tuyến','945643154112','2000-01-06','Hà Nội',1,'phamcuongth2000@gmail.com'),(25,'Trần Đức Nam','131314414414','2015-03-26','Hà Nam',1,'namtd@gmail.com'),(26,'Trịnh Vinh Toàn','851123020044','2000-03-08','Hưng Yên',1,'toantv@gmal.com'),(27,'Dương Ngọc Duy','561632187844','2000-10-15','Hà Nội',1,'duydn@gmail.com'),(28,'Lê Văn Sang','123123412455','1999-02-25','Bắc Ninh',1,'sanglv@gmail.com'),(29,'Đào Đức Thành','100018946475','2000-11-20','Hải Dương',1,'thanhdd@gmail.com'),(30,'Hồ Quang Hiếu','765746465646','2003-02-13','Bắc Giang',1,'hieuhq@gmail.com'),(31,'Vũ Thế Dương','074631385219','2000-07-29','Hà Nội',1,'phamcuongth2000@gmail.com'),(35,'Cường Mạnh Phạm','121300014666','2000-05-19','Hà Nội',0,'cuongpm@gmail.com'),(45,'Phạm Quang Anh','142587482913','2000-05-05','Thái Bình',1,'anhpq@gmail.com'),(55,'Lê Sỹ Văn','142868592341','2000-10-03','Thanh Hóa',1,'vanls@gmail.com'),(65,'Nguyễn Danh Thắng','142958423111','2000-09-12','Thanh Hóa',1,'thangnd@gmail.com'),(75,'Nguyễn Văn Thử','140786429131','2000-09-20','Ninh Bình',1,'thunv@gmail.com'),(85,'Đặng Đức Long','142864239305','2000-01-30','Hải Dương',1,'longdd@gmail.com'),(95,'Nguyễn Phương Nam','121300014111','2021-11-03','Thanh Hóa',1,'namnp@gmail.com'),(105,'Cường Mạnh Phạm','123444455612','1998-11-10','Hải Dương',0,'phamcm@gmail.com'),(115,'Cường Mạnh Phạm','123444455612','1998-11-10','Hải Dương',0,'phamcm@gmail.com'),(125,'Lê Đức Phan Anh 1','111111111111','2021-12-05','Hà Nội',0,'anhldp1@gmail.com'),(135,'Gilbertine Vellender','538151225632','2007-11-16','Vitoria-Gasteiz',1,'gvellender0@nsw.gov.au'),(145,'Darell Godsafe','995069231597','2003-07-27','Standerton',1,'dgodsafe1@slideshare.net'),(155,'Honoria Chedgey','033469844838','2008-12-14','Wanmao',1,'hchedgey2@linkedin.com'),(165,'Cass Walewski','884612185059','1967-09-13','Longuita',1,'cwalewski3@netvibes.com'),(175,'Frasier Mandres','432886906461','1972-03-17','Alajuela',1,'fmandres4@mashable.com'),(185,'Cris Crippes','164172176060','1975-08-27','Blessington',1,'ccrippes5@ow.ly'),(195,'Milka McMennum','128725884174','1994-05-09','Kamieniec Podolski',1,'mmcmennum6@intel.com'),(205,'Cati Bartosek','114358974086','1984-10-06','Yangqing',1,'cbartosek7@nationalgeographic.com'),(215,'Bethena Valentinetti','384804418858','1993-03-22','Kassiri',1,'bvalentinetti8@arizona.edu'),(225,'Othilia Whoolehan','462872952444','2006-08-22','Stony Plain',1,'owhoolehan9@oracle.com'),(235,'Tish Le Huquet','272247130949','1995-11-10','Buchach',1,'tlea@friendfeed.com'),(245,'Llewellyn Ilyukhov','554217686548','1979-05-09','Banjarejo',1,'lilyukhovb@mlb.com'),(255,'Christoforo Abbis','025379908493','1977-05-15','Xiamujiao',1,'cabbisc@irs.gov'),(265,'Stirling Blinder','374255255748','1986-08-01','Wellington',1,'sblinderd@ca.gov'),(275,'Howard Dimitriades','428155454217','1966-11-17','San Miguel',1,'hdimitriadese@buzzfeed.com'),(285,'Lexine Flanders','925126558544','1970-07-12','Kizema',1,'lflandersf@vkontakte.ru'),(295,'Terencio Borthwick','238486893212','1992-07-23','Cisarap',1,'tborthwickg@ted.com'),(305,'Jordan Lidgley','895090914210','1976-07-16','Roma',1,'jlidgleyh@hao123.com'),(315,'Gifford Plumridge','703954758042','1997-08-17','Jangdam',1,'gplumridgei@fc2.com'),(325,'Roy Fantonetti','407872517741','2005-10-23','Ash Shuqayrah',1,'rfantonettij@microsoft.com'),(335,'Bald Bachanski','812258641776','1998-09-09','Kongjiang',1,'bbachanskik@europa.eu'),(345,'Eugen Melluish','045847336550','1970-03-03','Independencia',1,'emelluishl@msu.edu'),(355,'Deni Christoffels','443510340601','2007-01-28','Gelap',1,'dchristoffelsm@indiatimes.com'),(365,'Shirlene Tripony','321575075636','1974-11-25','Kysyl-Syr',1,'striponyn@facebook.com'),(375,'Archibold Flaherty','261699411566','1978-11-07','Virovitica',1,'aflahertyo@google.com'),(385,'Virginia Butterley','405564065026','1998-07-29','Wenquan',1,'vbutterleyp@psu.edu'),(395,'Nonah Bridden','785089549567','1975-12-01','Giado',1,'nbriddenq@ebay.com'),(405,'Hanan Larvor','744037376692','2001-09-29','Dongshi',1,'hlarvorr@shutterfly.com'),(415,'Elita Falkus','193081167912','1996-06-21','Apeldoorn',1,'efalkuss@acquirethisname.com'),(425,'Casar Freen','168148334469','1962-06-28','Krzczonów',1,'cfreent@people.com.cn'),(435,'Anica Rosenkrantz','457887240344','1991-11-07','Umm as Summāq',1,'arosenkrantzu@nytimes.com'),(445,'Myrtice Russel','814060572966','1991-12-10','Limoges',1,'mrusselv@biglobe.ne.jp'),(455,'Elfie Stratley','201260796088','2003-07-07','Augustów',1,'estratleyw@howstuffworks.com'),(465,'Ange Oganesian','966513503758','1979-10-30','Kanbe',1,'aoganesianx@pcworld.com'),(475,'Felipe Corteney','698911738700','1980-07-02','Otofuke',1,'fcorteneyy@w3.org'),(485,'Esmaria Tunmore','648257260036','1968-03-23','Ruda-Huta',1,'etunmorez@wired.com'),(495,'Dolores Bohman','992911953288','1962-07-04','Gufeng',1,'dbohman10@admin.ch'),(505,'Thaddeus Wattisham','994794056247','2001-08-24','Qiangtou',1,'twattisham11@webs.com'),(515,'Dawna Ivashin','838749333547','1962-03-20','Chengkan',1,'divashin12@wordpress.org'),(525,'Cheston Caroline','297107109655','1983-10-14','Baoping',1,'ccaroline13@people.com.cn'),(535,'Anthony Scaplehorn','771146695482','1970-11-17','Trbovlje',1,'ascaplehorn14@cocolog-nifty.com'),(545,'Dani Mayoh','882535150802','1985-01-22','Soi Dao',1,'dmayoh15@va.gov'),(555,'Inigo Collecott','899775262365','2007-02-28','Bārkhān',1,'icollecott16@ucoz.ru'),(565,'Brewster Sorensen','933558909149','1992-06-28','Balakhta',1,'bsorensen17@joomla.org'),(575,'Hulda Spurritt','802525559435','1987-01-04','Rominimbang',1,'hspurritt18@wikipedia.org'),(585,'Betsey Mosdall','794438441950','2002-08-30','Ron Phibun',1,'bmosdall19@umich.edu'),(595,'Cristobal Orford','339971304215','1988-05-10','Longxian Chengguanzhen',1,'corford1a@alexa.com'),(605,'Bondon Hyrons','516719419267','1972-03-03','Taiping',1,'bhyrons1b@tumblr.com'),(615,'Kev MacCambridge','192007476350','1974-05-11','Hamburg Harvestehude',1,'kmaccambridge1c@independent.co.uk'),(625,'Chrystel Haryngton','399957112967','1988-01-06','Bilbays',1,'charyngton1d@techcrunch.com'),(635,'Idalia Terrett','009281437811','1963-04-23','Qianzhou',1,'iterrett1e@umn.edu'),(645,'Allan Falco','390902565936','1993-09-10','Marovoay',1,'afalco1f@java.com'),(655,'Cynthea Dreinan','666226567190','2003-01-19','Xin’an',1,'cdreinan1g@dell.com'),(665,'Romain Tomblings','382030030451','1974-11-02','Malax',1,'rtomblings1h@statcounter.com'),(675,'Karyl Feldhammer','490099159469','1987-03-25','Inta',1,'kfeldhammer1i@oakley.com'),(685,'Herbert Tearny','361919069792','1991-03-06','Banatski Despotovac',1,'htearny1j@bandcamp.com'),(695,'Gratia Witt','827901904154','1968-05-22','Yousheng',1,'gwitt1k@mediafire.com'),(705,'Euphemia Pharro','292983106690','2010-10-08','Wonorejo',1,'epharro1l@nih.gov'),(715,'Miranda Choak','791020123644','1963-05-10','Delareyville',1,'mchoak1m@etsy.com'),(725,'Ruby Budnk','926389782362','1998-01-18','Ballyboden',1,'rbudnk1n@fc2.com'),(735,'Hermon Gouinlock','846051327186','2002-12-16','Wiesbaden',1,'hgouinlock1o@alexa.com'),(745,'Rosita McGahey','452496368477','1963-09-02','San Jacinto',1,'rmcgahey1p@boston.com'),(755,'Merill Thatcham','989952492802','1962-10-06','Luo’ao',1,'mthatcham1q@nationalgeographic.com'),(765,'Claire Childerhouse','214778677457','2000-04-18','Agbannawag',1,'cchilderhouse1r@mit.edu'),(775,'Jonathan Oade','159765313753','1966-01-07','Laolong',1,'joade1s@xrea.com'),(785,'Crosby Giaomozzo','497715406365','1971-03-12','Студеничани',1,'cgiaomozzo1t@psu.edu'),(795,'Brittany Dabling','299209479027','2003-09-23','Nangewer',1,'bdabling1u@macromedia.com'),(805,'Griz Woodhams','010782427262','1986-04-19','Gobernador',1,'gwoodhams1v@comcast.net'),(815,'Tabbi Bassindale','840750346065','1984-02-17','Paris 13',1,'tbassindale1w@zimbio.com'),(825,'Tish Pele','711784943684','1977-10-12','Embu',1,'tpele1x@psu.edu'),(835,'Joaquin Van Leeuwen','111677426124','1979-10-04','Macamic',1,'jvan1y@usatoday.com'),(845,'Ashia de Keep','283210587321','2004-11-11','Phoenix',1,'ade1z@blog.com'),(855,'Rosella Witcherley','387060749469','2004-04-07','Cotabambas',1,'rwitcherley20@spiegel.de'),(865,'Holly Edmundson','976388826561','1965-11-16','Litian',1,'hedmundson21@t.co'),(875,'Kynthia Yoxall','404777782634','2005-11-16','Sukpak',1,'kyoxall22@spiegel.de'),(885,'Kellie Toffolo','341549614566','1975-09-19','Jindong',1,'ktoffolo23@imageshack.us'),(895,'Kimball Rudwell','059694062185','2001-09-17','Rerawere',1,'krudwell24@hc360.com'),(905,'Britta Bahike','189378435771','1961-04-07','Metsovo',1,'bbahike25@edublogs.org'),(915,'Rustie Flescher','577631610678','2002-01-16','Mariatana',1,'rflescher26@loc.gov'),(925,'Veradis MacConnel','654008237358','1980-04-07','Xianshuigu',1,'vmacconnel27@cocolog-nifty.com'),(935,'Adolph Witheford','274951520874','1985-12-02','Lhoknga',1,'awitheford28@plala.or.jp'),(945,'Nicol Douthwaite','792247073357','1993-12-16','Mstsislaw',1,'ndouthwaite29@360.cn'),(955,'Donovan McTrustram','985211314392','2007-01-18','Ribeira',1,'dmctrustram2a@stanford.edu'),(965,'Randy Lasselle','030538079362','1965-04-19','Si Prachan',1,'rlasselle2b@csmonitor.com'),(975,'Papageno Wooddisse','057248086054','1969-04-27','Taguing',1,'pwooddisse2c@wsj.com'),(985,'Pippa Dinley','292480043666','1968-04-21','Culasian',1,'pdinley2d@ucoz.com'),(995,'Andres Crome','045225721935','2006-05-14','Kolirerek',1,'acrome2e@nationalgeographic.com'),(1005,'Shirl Cottrill','656375733910','1987-10-28','Kisoro',1,'scottrill2f@marketwatch.com'),(1015,'Jacklin Bearfoot','214848176958','1961-08-03','Zhongshu',1,'jbearfoot2g@dagondesign.com'),(1025,'Darice Beevor','713359425298','1973-07-31','Ina',1,'dbeevor2h@dmoz.org'),(1035,'Horatio Piggens','341030676736','1987-04-16','Maricá',1,'hpiggens2i@salon.com'),(1045,'Enoch Eannetta','788262562549','1991-05-23','Solntsevo',1,'eeannetta2j@cafepress.com'),(1055,'Burch Marton','018228243245','1987-12-14','Chenhu',1,'bmarton2k@oracle.com'),(1065,'Paquito McGill','886871741959','1999-11-10','Centro Habana',1,'pmcgill2l@howstuffworks.com'),(1075,'Tabby Sutworth','407096983891','2004-10-24','Ciénaga',1,'tsutworth2m@studiopress.com'),(1085,'Cele Ogilvy','017166298249','1962-10-05','Shaozhai',1,'cogilvy2n@columbia.edu'),(1095,'Josee Ducarne','303691133302','1966-08-07','Linton',1,'jducarne2o@acquirethisname.com'),(1105,'Gaylor Drewes','358379515276','1981-01-03','Nový Knín',1,'gdrewes2p@hp.com'),(1115,'Olivie Wastling','755986112776','1973-11-27','Tuchlovice',1,'owastling2q@wix.com'),(1125,'Morry Smeal','057487813039','1986-11-19','Lille',1,'msmeal2r@over-blog.com');
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
  `BankAccountId` varchar(255) NOT NULL,
  PRIMARY KEY (`BankAccountId`),
  CONSTRAINT `FKDepositAcc917662` FOREIGN KEY (`BankAccountId`) REFERENCES `bankaccount` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `depositaccount`
--

LOCK TABLES `depositaccount` WRITE;
/*!40000 ALTER TABLE `depositaccount` DISABLE KEYS */;
INSERT INTO `depositaccount` VALUES (1.5,200000,'03112628387171'),(0.7,50000,'04545044938713'),(0.6,50000,'05064156775472'),(0.2,30000,'11758823951403'),(1.2,100000,'13502352759657'),(2.3,100000,'14866681216031'),(1.5,100000,'20025345774654'),(2.1,200000,'38915117578712'),(3.33,200000,'39135280632675'),(1.5,100000,'41629451367527'),(0.6,50000,'52413784531573'),(0.6,50000,'53092704808078'),(1.4,100000,'72358008834044'),(1.5,200000,'78491851613791'),(1.2,20000,'80030350077108'),(1.5,100000,'88872154161134'),(1.4,50000,'89173617975489'),(1.5,100000,'95105016406939'),(1.5,100000,'99624231464710');
/*!40000 ALTER TABLE `depositaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdCard` varchar(255) NOT NULL,
  `FullName` varchar(255) NOT NULL,
  `DateOfBirth` date NOT NULL,
  `Address` varchar(255) NOT NULL,
  `Level` int(11) NOT NULL,
  `Seniority` int(11) NOT NULL,
  `Position` varchar(255) NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (2,'001200014666','Phạm Cường','2000-01-06','Hải Dương',12,10,'MANAGER',1),(3,'001200014666','Thu Hà','2000-11-17','Hà Nội',12,12,'TELLER',1),(4,'001200014669','Lê Đức Phan Anh','2000-04-22','Hà Nội',12,5,'MANAGER',1),(5,'121300014666','Trần Văn Quân','2000-03-26','Hà Nội',5,1,'TELLER',1),(6,'768700043543','Lê Thùy Dương','2000-11-08','Hưng Yên',5,2,'TELLER',1),(7,'987700021214','Nguyễn Thanh Hằng','2008-06-26','Quảng Ninh',12,5,'TELLER',1),(15,'555500014666','Nguyễn Thanh Tuyến','2000-02-26','Hải Dương',15,5,'MANAGER',1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary`
--

DROP TABLE IF EXISTS `salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salary` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `EmployeeId` int(11) NOT NULL,
  `BasicSalary` double NOT NULL,
  `BonusSalary` double NOT NULL,
  `DateSalary` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKSalary764021` (`EmployeeId`),
  CONSTRAINT `FKSalary764021` FOREIGN KEY (`EmployeeId`) REFERENCES `employee` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
INSERT INTO `salary` VALUES (15,2,15000000,3641688.88,'11/2021'),(45,3,7000000,0,'11/2021');
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(255) NOT NULL,
  `DepositAccountId` varchar(255) DEFAULT NULL,
  `EmployeeId` int(11) DEFAULT NULL,
  `CreditAccountId` varchar(255) DEFAULT NULL,
  `CustomerId` int(11) DEFAULT NULL,
  `DateCreate` datetime NOT NULL,
  `Money` double NOT NULL,
  `AfterBalanceDeposit` double DEFAULT NULL,
  `AfterBalanceCredit` double DEFAULT NULL,
  `Note` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKTransactio250053` (`CustomerId`),
  KEY `FKTransactio206785` (`EmployeeId`),
  KEY `FKTransactio651323` (`DepositAccountId`),
  KEY `FKTransactio688416` (`CreditAccountId`),
  CONSTRAINT `FKTransactio651323` FOREIGN KEY (`DepositAccountId`) REFERENCES `depositaccount` (`BankAccountId`),
  CONSTRAINT `FKTransactio688416` FOREIGN KEY (`CreditAccountId`) REFERENCES `creditaccount` (`BankAccountId`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`EmployeeId`) REFERENCES `employee` (`Id`),
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`CustomerId`) REFERENCES `customer` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=835 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (12,'DEPOSIT','39135280632675',4,NULL,21,'2021-11-21 19:38:07',1000000,2500000,0,''),(13,'DEPOSIT','52413784531573',4,NULL,21,'2021-11-21 19:57:12',100000,300000,0,''),(14,'DEPOSIT','52413784531573',4,NULL,21,'2021-11-21 20:00:26',100000,400000,0,''),(15,'DEPOSIT','52413784531573',4,NULL,21,'2021-11-21 20:03:56',100000,500000,0,''),(17,'CREDIT',NULL,4,'18727409988066',21,'2021-11-21 20:37:30',100000,0,100000,''),(18,'CREDIT',NULL,4,'18727409988066',21,'2021-11-21 20:41:18',100000,0,200000,''),(19,'DEPOSIT','39135280632675',4,NULL,21,'2021-11-21 21:17:06',1000000,3500000,0,''),(20,'CREDIT',NULL,4,'24287938923402',21,'2021-11-21 21:29:27',100000,0,100000,''),(21,'PAYMENT','39135280632675',2,'24287938923402',21,'2021-11-22 00:47:59',50000,3450000,50000,''),(22,'PAYMENT','39135280632675',2,'24287938923402',21,'2021-11-22 00:52:28',10000,3440000,40000,''),(23,'PAYMENT','39135280632675',2,'24287938923402',21,'2021-11-22 00:53:15',40000,3400000,0,''),(24,'PAYMENT','39135280632675',2,'24287938923402',21,'2021-11-22 01:04:43',0,3400000,0,''),(25,'PAYMENT','39135280632675',2,'18727409988066',21,'2021-11-22 01:10:18',0,3400000,20000000,'3500000'),(26,'PAYMENT','39135280632675',2,'18727409988066',21,'2021-11-22 01:29:02',30000,3370000,19970000,''),(27,'PAYMENT','39135280632675',2,'18727409988066',21,'2021-11-22 09:52:06',20000,3350000,0,''),(28,'DEPOSIT','39135280632675',2,NULL,21,'2021-11-22 10:36:49',10000,3360000,0,''),(29,'CREDIT',NULL,2,'18727409988066',21,'2021-11-22 11:07:01',1000000,0,1000000,''),(30,'DEPOSIT','39135280632675',2,NULL,21,'2021-11-22 11:41:05',0,3360000,0,''),(31,'CREDIT',NULL,2,'54133078555720',22,'2021-11-24 19:46:46',20000,0,20000,''),(32,'DEPOSIT','52413784531573',2,NULL,21,'2021-11-25 12:02:24',5000000,5500000,0,''),(33,'CREDIT',NULL,2,'31047017984162',23,'2021-11-25 22:42:25',10000,0,10000,''),(34,'DEPOSIT','38915117578712',2,NULL,22,'2021-11-25 22:42:58',5000000,5000000,0,''),(35,'CREDIT',NULL,2,'43022291439594',23,'2021-11-25 22:48:51',6999999,0,6999999,''),(45,'CREDIT',NULL,4,'54331759325290',22,'2021-11-26 15:40:59',189000,0,189000,''),(55,'DEPOSIT','72358008834044',4,NULL,22,'2021-11-26 15:41:48',25000000,25000000,0,''),(65,'PAYMENT','72358008834044',4,'54331759325290',22,'2021-11-26 15:45:21',189000,24811000,0,''),(75,'DEPOSIT','05064156775472',2,NULL,45,'2021-11-27 16:17:21',2000000,2000000,0,'gửi tiền vô tài khoản'),(85,'DEPOSIT','88872154161134',2,NULL,45,'2021-11-27 16:17:59',5000000,5000000,0,''),(95,'DEPOSIT','20025345774654',2,NULL,55,'2021-11-27 16:19:04',4532222,4532222,0,''),(105,'DEPOSIT','53092704808078',2,NULL,85,'2021-11-27 16:19:38',450000,450000,0,''),(115,'DEPOSIT','99624231464710',2,NULL,75,'2021-11-27 16:21:36',450000,450000,0,''),(125,'DEPOSIT','95105016406939',2,NULL,65,'2021-11-27 16:22:07',6000000,6000000,0,''),(135,'CREDIT',NULL,2,'18727409988066',21,'2021-11-27 23:44:12',10000000,0,11000000,''),(145,'DEPOSIT','14866681216031',2,NULL,30,'2021-11-28 00:11:52',100000,100000,0,''),(155,'CREDIT',NULL,15,'51941167615654',85,'2021-11-28 03:01:16',500000,0,500000,'Thanh toán mua hàng online'),(165,'CREDIT',NULL,15,'11941079948685',55,'2021-11-28 03:02:17',200000,0,200000,'Mua thẻ điện thoại'),(175,'CREDIT',NULL,15,'70652794958440',65,'2021-11-28 03:03:48',1000000,0,1000000,'Trả tiền điện tháng 11'),(185,'CREDIT',NULL,15,'35642354779232',75,'2021-11-28 03:04:23',600000,0,600000,'Thanh toán mua hàng online'),(195,'DEPOSIT','99624231464710',15,NULL,75,'2021-11-28 03:10:05',1000000,1450000,0,'Gửi tiền vào tài khoản'),(205,'DEPOSIT','53092704808078',15,NULL,85,'2021-11-28 03:10:39',2000000,2450000,0,'Gửi tiền vào tài khoản'),(215,'CREDIT',NULL,15,'11941079948685',55,'2021-11-28 03:12:49',500000,0,700000,'Mua thẻ game'),(225,'CREDIT',NULL,15,'70652794958440',65,'2021-11-28 03:35:37',550000,0,1550000,'Trả tiền nước'),(235,'CREDIT',NULL,15,'09563434440261',45,'2021-11-28 16:24:26',200000,0,200000,'Mua thẻ điện thoại'),(245,'PAYMENT','89173617975489',4,'54133078555720',22,'2021-12-02 21:03:30',20000,80000,0,''),(255,'CREDIT',NULL,4,'54133078555720',22,'2021-12-02 21:06:21',1000000,0,1000000,''),(265,'PAYMENT','72358008834044',4,'54133078555720',22,'2021-12-02 21:07:39',20000,24791000,980000,''),(275,'PAYMENT','72358008834044',4,'54133078555720',22,'2021-12-02 21:13:24',20000,24771000,960000,''),(285,'PAYMENT','72358008834044',4,'54133078555720',22,'2021-12-02 21:37:44',20000,24751000,940000,''),(295,'PAYMENT','72358008834044',4,'54133078555720',22,'2021-12-02 22:34:03',20000,24731000,920000,''),(305,'PAYMENT','72358008834044',4,'54133078555720',22,'2021-12-02 23:10:37',20000,24711000,900000,''),(315,'PAYMENT','72358008834044',4,'54133078555720',22,'2021-12-02 23:20:17',30000,24681000,870000,''),(325,'PAYMENT','72358008834044',4,'54133078555720',22,'2021-12-02 23:22:34',30000,24651000,840000,''),(335,'PAYMENT','72358008834044',4,'54133078555720',22,'2021-12-02 23:28:18',40000,24611000,800000,''),(345,'PAYMENT','72358008834044',4,'54133078555720',22,'2021-12-02 23:32:45',50000,24561000,750000,''),(355,'PAYMENT','72358008834044',4,'54133078555720',22,'2021-12-02 23:36:38',60000,24501000,690000,''),(365,'PAYMENT','72358008834044',4,'43022291439594',22,'2021-12-03 14:28:35',20000,24481000,6979999,''),(375,'PAYMENT','72358008834044',4,'43022291439594',22,'2021-12-03 14:36:51',30000,24451000,6949999,''),(385,'CREDIT',NULL,4,'54133078555720',22,'2021-12-03 15:40:58',100000,0,790000,''),(395,'DEPOSIT','89173617975489',4,NULL,22,'2021-12-03 15:43:40',100000,180000,0,''),(405,'DEPOSIT','89173617975489',4,NULL,22,'2021-12-03 15:45:06',10000,190000,0,''),(415,'DEPOSIT','89173617975489',4,NULL,22,'2021-12-03 15:47:15',20000,210000,0,''),(425,'PAYMENT','39135280632675',4,'54133078555720',21,'2021-12-03 16:14:50',25000,4335000,765000,''),(435,'PAYMENT','39135280632675',4,'54133078555720',21,'2021-12-03 16:17:54',35000,4300000,730000,''),(445,'PAYMENT','39135280632675',4,'54133078555720',21,'2021-12-03 16:21:34',30000,4270000,700000,''),(455,'INTEREST','03112628387171',NULL,NULL,NULL,'2021-12-03 18:30:22',0,0,0,'Cộng tiền lãi suất hàng tháng'),(465,'INTEREST','05064156775472',NULL,NULL,NULL,'2021-12-03 18:30:23',12000,2012000,0,'Cộng tiền lãi suất hàng tháng'),(475,'INTEREST','14866681216031',NULL,NULL,NULL,'2021-12-03 18:30:24',2299.9999999999995,102300,0,'Cộng tiền lãi suất hàng tháng'),(485,'INTEREST','20025345774654',NULL,NULL,NULL,'2021-12-03 18:30:25',67983.33,4600205.33,0,'Cộng tiền lãi suất hàng tháng'),(495,'INTEREST','38915117578712',NULL,NULL,NULL,'2021-12-03 18:30:26',105000,5105000,0,'Cộng tiền lãi suất hàng tháng'),(505,'INTEREST','39135280632675',NULL,NULL,NULL,'2021-12-03 18:30:27',142191,4412191,0,'Cộng tiền lãi suất hàng tháng'),(515,'INTEREST','41629451367527',NULL,NULL,NULL,'2021-12-03 18:30:28',0,0,0,'Cộng tiền lãi suất hàng tháng'),(525,'INTEREST','52413784531573',NULL,NULL,NULL,'2021-12-03 18:30:29',33000,5533000,0,'Cộng tiền lãi suất hàng tháng'),(535,'INTEREST','53092704808078',NULL,NULL,NULL,'2021-12-03 18:30:30',14700,2464700,0,'Cộng tiền lãi suất hàng tháng'),(545,'INTEREST','72358008834044',NULL,NULL,NULL,'2021-12-03 18:30:30',342314,24793314,0,'Cộng tiền lãi suất hàng tháng'),(555,'INTEREST','88872154161134',NULL,NULL,NULL,'2021-12-03 18:30:31',75000,5075000,0,'Cộng tiền lãi suất hàng tháng'),(565,'INTEREST','89173617975489',NULL,NULL,NULL,'2021-12-03 18:30:32',2940,212940,0,'Cộng tiền lãi suất hàng tháng'),(575,'INTEREST','95105016406939',NULL,NULL,NULL,'2021-12-03 18:30:33',90000,6090000,0,'Cộng tiền lãi suất hàng tháng'),(585,'INTEREST','99624231464710',NULL,NULL,NULL,'2021-12-03 18:30:34',21750,1471750,0,'Cộng tiền lãi suất hàng tháng'),(595,'CREDIT',NULL,4,'54133078555720',22,'2021-12-03 19:51:12',50000,0,750000,'Thanh toán tiền xăng'),(605,'CREDIT',NULL,4,'24287938923402',21,'2021-12-03 20:13:51',50000,0,50000,'trả tiền xăng'),(615,'PAYMENT','52413784531573',4,'54133078555720',21,'2021-12-03 20:15:11',50000,5483000,700000,'Cường trả 50k cho Phan Anh'),(625,'PAYMENT','52413784531573',4,'54133078555720',21,'2021-12-03 20:18:05',100000,5383000,600000,'Trả mày 100k'),(675,'DEPOSIT','41629451367527',2,NULL,21,'2021-12-03 22:06:17',200000,200000,0,'gửi tiền phát'),(685,'PAYMENT','39135280632675',2,'24287938923402',21,'2021-12-03 22:18:54',10000,4402191,40000,'trả tiền hộ nhé nhân một ngày đẹp trời trong xanh'),(695,'CREDIT',NULL,2,'24287938923402',21,'2021-12-03 22:25:23',20000,0,60000,'trả tiền xăng'),(705,'DEPOSIT','03112628387171',2,NULL,23,'2021-12-03 22:49:20',300000,300000,0,''),(715,'DEPOSIT','13502352759657',2,NULL,31,'2021-12-03 22:50:35',10000000,10000000,0,''),(725,'DEPOSIT','80030350077108',2,NULL,31,'2021-12-03 22:51:36',30000,30000,0,''),(735,'PAYMENT','52413784531573',2,'54133078555720',21,'2021-12-03 17:01:28',100000,5283000,500000,'trả tiền gửi xăng'),(745,'PAYMENT','39135280632675',2,'24287938923402',21,'2021-12-07 16:46:43',60000,4342191,0,'trừ tiền bus'),(755,'PAYMENT','39135280632675',2,'18727409988066',21,'2021-12-07 16:55:15',100001,4242190,10899999,'trả tiền đề'),(765,'PAYMENT','03112628387171',2,'18727409988066',23,'2021-12-07 17:08:11',44000,256000,10855999,'chuyển lần đầu cho biết'),(775,'CREDIT',NULL,2,'52436320481252',26,'2021-12-07 17:16:56',2000000,0,2000000,''),(785,'PAYMENT','52413784531573',2,'52436320481252',21,'2021-12-07 17:20:44',2000000,3283000,0,''),(795,'PAYMENT','39135280632675',2,'54133078555720',21,'2021-12-07 17:57:12',200000,4042190,300000,''),(805,'CREDIT',NULL,2,'24287938923402',21,'2021-12-07 18:38:51',1000000,0,1000000,''),(815,'DEPOSIT','04545044938713',2,NULL,24,'2021-12-07 18:48:39',50000,50000,0,''),(825,'DEPOSIT','11758823951403',2,NULL,24,'2021-12-07 18:58:20',35000,35000,0,'');
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

-- Dump completed on 2021-12-07 21:32:12
