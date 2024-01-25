/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.26-log : Database - studb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`studb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `studb`;

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `provinceID` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `city` */

insert  into `city`(`id`,`provinceID`,`name`) values (1,1,'广州市'),(2,1,'深圳市'),(3,1,'佛山市'),(4,2,'长沙市'),(5,2,'常德市'),(6,2,'株洲市'),(7,3,'武汉市'),(8,3,'宜昌市'),(9,3,'荆州市');

/*Table structure for table `gradeinfo` */

DROP TABLE IF EXISTS `gradeinfo`;

CREATE TABLE `gradeinfo` (
  `gradeID` int(4) NOT NULL AUTO_INCREMENT,
  `gradeName` varchar(50) NOT NULL,
  PRIMARY KEY (`gradeID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `gradeinfo` */

insert  into `gradeinfo`(`gradeID`,`gradeName`) values (1,'大一'),(2,'大二'),(3,'大三'),(4,'大四');

/*Table structure for table `province` */

DROP TABLE IF EXISTS `province`;

CREATE TABLE `province` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `province` */

insert  into `province`(`id`,`name`) values (1,'广东省'),(2,'湖南省'),(3,'湖北省');

/*Table structure for table `resultinfo` */

DROP TABLE IF EXISTS `resultinfo`;

CREATE TABLE `resultinfo` (
  `studentNo` int(4) NOT NULL,
  `subjectNo` int(4) NOT NULL,
  `examDate` datetime NOT NULL,
  `studentResult` int(4) NOT NULL,
  KEY `FK_subjectinfo_subjectNo` (`subjectNo`),
  KEY `FK_studentInfo_StudentNo` (`studentNo`),
  CONSTRAINT `FK_studentInfo_StudentNo` FOREIGN KEY (`studentNo`) REFERENCES `studentinfo` (`studentNo`),
  CONSTRAINT `FK_subjectinfo_subjectNo` FOREIGN KEY (`subjectNo`) REFERENCES `subjectinfo` (`subjectNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `resultinfo` */

insert  into `resultinfo`(`studentNo`,`subjectNo`,`examDate`,`studentResult`) values (1001,2,'2021-01-14 13:03:53',95),(1002,1,'2020-11-10 13:04:02',87),(1003,2,'2021-01-14 13:04:12',99),(1004,3,'2020-08-16 13:04:22',66),(1005,2,'0000-00-00 00:00:00',0),(1006,1,'0000-00-00 00:00:00',100),(1007,3,'0000-00-00 00:00:00',99),(1008,1,'0000-00-00 00:00:00',75),(1009,1,'0000-00-00 00:00:00',63);

/*Table structure for table `studentinfo` */

DROP TABLE IF EXISTS `studentinfo`;

CREATE TABLE `studentinfo` (
  `studentNo` int(4) NOT NULL AUTO_INCREMENT,
  `loginPwd` varchar(20) NOT NULL,
  `studentName` varchar(50) NOT NULL,
  `sex` char(2) NOT NULL,
  `gradeID` int(4) NOT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `identityCard` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`studentNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1012 DEFAULT CHARSET=utf8;

/*Data for the table `studentinfo` */

insert  into `studentinfo`(`studentNo`,`loginPwd`,`studentName`,`sex`,`gradeID`,`phone`,`address`,`birthday`,`email`,`identityCard`) values (1001,'123','张三','男',1,'15362193603','中山路1号','1995-01-01 15:48:07','hutr@163.net','379105729672751918'),(1002,'123456','李四','女',3,NULL,NULL,'1994-11-15 15:48:07','edwwksdwminmig@china.com','510835981545827396'),(1003,'abc','王五','女',2,NULL,'不详','1995-04-13 15:48:07',NULL,'509723579116036528'),(1004,'aaa','王建国','男',4,'15867517593',NULL,'1995-01-01 15:48:07',NULL,'259435128378589490'),(1005,'sfse546','李建军','男',4,'17530328426','中山路100号','1994-11-15 15:48:07','jnmdcrjgu@265.com','897032123397056515'),(1006,'S14DRdfgGS','赵抗美','男',3,'19864281449',NULL,'1994-12-19 15:48:07','juudsrkick@yahoo.com.cn','467476898714695365'),(1007,'353515','钱援朝','女',1,'13700725450',NULL,'1994-11-21 15:48:07',NULL,'384947292359133668'),(1008,'sdgdrh','张丽','女',2,'13054975881','解放路95号','1995-01-10 15:48:07','oqwiitt@sina.com','570847473212406978'),(1009,'gykgtk','李伟','男',1,'18862795080',NULL,'1995-02-02 15:48:07',NULL,'819621630495633592'),(1010,'cruwe','吴强','男',2,'18067405550',NULL,'1995-03-24 15:48:07',NULL,'893777911827461699'),(1011,'DSdfh54','周倩','女',3,'13810485078','人民路6号502','1994-11-22 15:48:07',NULL,'773687699152851232');

/*Table structure for table `subjectinfo` */

DROP TABLE IF EXISTS `subjectinfo`;

CREATE TABLE `subjectinfo` (
  `subjectNo` int(4) NOT NULL AUTO_INCREMENT,
  `subjectName` varchar(50) DEFAULT NULL,
  `classHour` int(4) DEFAULT NULL,
  `gradeID` int(4) DEFAULT NULL,
  PRIMARY KEY (`subjectNo`),
  KEY `FK_gradeInfo_gradeID` (`gradeID`),
  CONSTRAINT `FK_gradeInfo_gradeID` FOREIGN KEY (`gradeID`) REFERENCES `gradeinfo` (`gradeID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `subjectinfo` */

insert  into `subjectinfo`(`subjectNo`,`subjectName`,`classHour`,`gradeID`) values (1,'JAVA',200,1),(2,'HTML',250,2),(3,'JavaScript',133,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
