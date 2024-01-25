/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.26-log : Database - jt99lzb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jt99lzb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `jt99lzb`;

/*Table structure for table `grade` */

DROP TABLE IF EXISTS `grade`;

CREATE TABLE `grade` (
  `GradeID` int(11) NOT NULL,
  `GradeName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`GradeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `grade` */

insert  into `grade`(`GradeID`,`GradeName`) values (1,'大一'),(2,'大二'),(3,'大三'),(4,'大四');

/*Table structure for table `num` */

DROP TABLE IF EXISTS `num`;

CREATE TABLE `num` (
  `i` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `num` */

insert  into `num`(`i`) values (1),(2),(3),(4),(5),(6),(7),(9),(10);

/*Table structure for table `result` */

DROP TABLE IF EXISTS `result`;

CREATE TABLE `result` (
  `StudentNo` int(11) DEFAULT NULL,
  `SubjectNo` int(11) DEFAULT NULL,
  `ExamDate` datetime DEFAULT NULL,
  `StudentResult` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `result` */

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `StudentNo` int(4) NOT NULL COMMENT '学号',
  `LoginPwd` varchar(20) DEFAULT NULL,
  `StudentName` varchar(20) DEFAULT NULL COMMENT '学生姓名',
  `Sex` tinyint(1) DEFAULT NULL COMMENT '性别，取值0或1',
  `GradeId` int(11) DEFAULT NULL COMMENT '年级编号',
  `Phone` varchar(50) NOT NULL COMMENT '联系电话，允许为空，即可选输入',
  `Address` varchar(255) NOT NULL COMMENT '地址，允许为空，即可选输入',
  `BornDate` datetime DEFAULT NULL COMMENT '出生时间',
  `Email` varchar(50) NOT NULL COMMENT '邮箱账号，允许为空，即可选输入',
  `IdentityCard` varchar(18) DEFAULT NULL COMMENT '身份证号',
  PRIMARY KEY (`StudentNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`StudentNo`,`LoginPwd`,`StudentName`,`Sex`,`GradeId`,`Phone`,`Address`,`BornDate`,`Email`,`IdentityCard`) values (1,'123456','张三',0,1,'15362193603','123','1995-07-01 19:21:11','oqwiitt@sina.com',NULL),(2,'aaaaaa','李四',0,3,'15563076310','sefse','1995-08-16 19:21:11','edwwksdwminmig@china.com',NULL),(3,'abc','盖芝宝',0,3,'14525905860','egwegwegwe','1995-06-12 19:21:11','bplmwbeicresfi@email.com.cn',NULL),(4,'666666','邢堂明',0,2,'13893679746','','1994-06-01 19:21:11','hwcjroebgnwv@263.net',NULL),(5,'1234578','崔峰冰',0,1,'13810485078','','1996-04-01 19:21:11','juudsrkick@yahoo.com.cn',NULL),(6,'53s1ef5','甘晨茗',0,5,'17530328426','','1995-07-24 19:21:11','hutr@163.net',NULL),(7,'sefsef','康固',0,2,'13054975881','sgesegsg','1995-06-01 19:21:11','jfkaooptsuae@163.net',NULL),(8,'dhweewrh','花罡波',0,3,'19841582582','','1995-09-15 19:21:11','voggrmkaqnkofv@21cn.com',NULL),(9,'dwetwye','成发天',0,4,'15867517593','','1995-03-22 19:21:11','gtdcaiwtbqav@hotmail.com',NULL),(10,'123','张三',0,1,'19864281449','erherhehthr','1998-05-07 19:21:11','jnmdcrjgu@265.com',NULL),(11,'123','王五',0,3,'17713185638','','1995-04-12 19:21:11','',NULL),(12,'123456','侯昭',0,4,'13700725450','segghdrtherh','1995-11-09 19:21:11','',NULL),(13,'abc123','宗江',0,3,'18862795080','','1995-05-15 19:21:11','mwmeltinmc@email.com.cn',NULL),(14,'AS45EFs6gse','宁贝红',0,2,'18067405550','','1995-10-05 19:21:11','',NULL);

/*Table structure for table `subject` */

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `SubjectNo` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程编号',
  `SubjectName` varchar(50) DEFAULT NULL COMMENT '课程名称',
  `ClassHour` int(4) DEFAULT NULL COMMENT '学时',
  `GradeID` int(4) DEFAULT NULL COMMENT '年级编号',
  PRIMARY KEY (`SubjectNo`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `subject` */

insert  into `subject`(`SubjectNo`,`SubjectName`,`ClassHour`,`GradeID`) values (1,'语文',100,1),(2,'数学',100,2),(3,'英语',100,3),(4,'历史',50,4),(5,'政治',60,5),(6,'地理',70,6),(7,'生物',80,7),(8,'化学',80,8),(9,'物理',85,9),(10,'JAVA',110,10),(11,'C语言',120,11),(12,'C++',130,12),(13,'Python',100,13),(14,'JavaScript',110,14);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
