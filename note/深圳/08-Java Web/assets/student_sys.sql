/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.26-log : Database - student_sys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`student_sys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `student_sys`;

/*Table structure for table `t_student` */

DROP TABLE IF EXISTS `t_student`;

CREATE TABLE `t_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `grade` varchar(100) DEFAULT NULL,
  `introduce` varchar(1000) DEFAULT NULL,
  `photo` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `t_student` */

insert  into `t_student`(`id`,`name`,`sex`,`age`,`grade`,`introduce`,`photo`) values (1,'张三','男',18,'学士后','张三自我介绍自我介绍自我介绍自我介绍',NULL),(2,'李四','女',20,'Y2','李四自我介绍自我介绍自我介绍自我介绍自我介绍',NULL),(3,'王五','男',22,'S1','王五自我介绍自我介绍自我介绍',NULL),(4,'赵六','男',19,'S2','赵六自我介绍自我介绍自我介绍自我介绍自我介绍自我介绍',NULL),(5,'对对对','男',18,'学士后','电饭锅电饭锅',NULL),(6,'sefsefsef','男',22,'学士后','水电费水电费水电费',NULL),(7,'up9p','男',18,'学士后','水电费水电费水电费',NULL),(8,'rtj','女',19,'S1','水电费水电费水电费',NULL),(9,'puiop','女',19,'S2','sfgsegs',NULL),(10,'oui','女',18,'Y2','ffsef',NULL),(11,'dhft','男',20,'S2','sesefgs',NULL),(12,'drthy','女',20,'S1','srseg',NULL),(13,'sfeghs','女',21,'S2','sesef',NULL),(14,'ghj','男',21,'学士后','efsefse',NULL),(15,'sef','女',22,'S2','ffsef',NULL),(16,'fth','男',20,'Y2','sefe',NULL),(17,'sdrg','女',22,'S1','ssefsef',NULL),(18,'drg','男',20,'S2','sef',NULL),(19,'hyukl','男',20,'学士后','sefsef',NULL),(20,'ty','女',22,'S2','sefsef',NULL),(21,'hjgyj','男',20,'S1','sefefes',NULL),(22,'对对对','男',18,'学士后','非官方大哥大','/upload/ef8cb55d-da05-4a6a-9f35-fe92aa32607c.jpg'),(23,'对对对','男',18,'Y2','水电费水电费水电费水电费水电费','/upload/c28e2e8e-6d05-44ba-9af9-44c7b3088927.jpg'),(24,'发发发','男',18,'S1','山东分公司的水电费水电费','/upload/41f66253-6515-4454-b014-be90393f7dc3.jpg');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`password`) values (1,'root','root');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
