/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.26-log : Database - contact
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`contact` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `contact`;

/*Table structure for table `contact` */

DROP TABLE IF EXISTS `contact`;

CREATE TABLE `contact` (
  `id` bigint(8) NOT NULL AUTO_INCREMENT,
  `cname` varchar(32) NOT NULL,
  `mobile` varchar(32) DEFAULT NULL,
  `qq` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `address` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `contact` */

insert  into `contact`(`id`,`cname`,`mobile`,`qq`,`email`,`address`) values (5,'赵七','13712345678','4654615618','zhaoqi@qq.com','北京市朝阳区'),(6,'王八张','13912345678','1651489498','wangba@qq.com','北京市还定区'),(8,'三112','13131313','1536543','aaa@aaa.aaa','684354'),(9,'张54','54685416','65876','aaa@aaa.aaa','hjgyjghj'),(10,'sfasdf','13131313','1536543','aaa@aaa.aaa','的点点滴滴'),(11,'drgr','465456','46486','aaa@aaa.aaa','48684'),(12,'gjg','13131313','1536543','aaa@aaa.aaa','684354'),(13,'44','13131313','1536543','aaa@aaa.aaa','的点点滴滴'),(14,'dgrdr','486','465456','wangba@qq.com','684354'),(15,'kgy','13131313','8646','wangba@qq.com','1465'),(16,'gkku','465456','486548','wangba@qq.com','684354'),(17,'yfj','48','465456','wangba@qq.com','684354'),(18,'ser','8','84684','wangba@qq.com','4'),(19,'hlid','13131313','465456','wangba@qq.com','564'),(20,'hdr','465456','465456','wangba@qq.com','684354'),(23,'ryijy','13131313','465456','wangba@qq.com','684354'),(24,'tyi','648','648','wangba@qq.com','456'),(25,'uk)','13131313','465456','wangba@qq.com','684354');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`password`) values (1,'root','root');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
