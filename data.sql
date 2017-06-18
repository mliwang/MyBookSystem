/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.17 : Database - mybooksystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mybooksystem` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mybooksystem`;

/*Table structure for table `bookinfo` */

DROP TABLE IF EXISTS `bookinfo`;

CREATE TABLE `bookinfo` (
  `bookid` int(11) NOT NULL AUTO_INCREMENT COMMENT '教材id',
  `bookName` varchar(50) DEFAULT NULL COMMENT '教材名称',
  `author` varchar(40) DEFAULT NULL COMMENT '作者',
  `edition` int(11) DEFAULT NULL COMMENT '版本',
  `publishUnit` varchar(50) DEFAULT NULL COMMENT '出版单位',
  `ISBN` varchar(10) DEFAULT NULL COMMENT 'ISBN编码',
  `inventory` int(11) DEFAULT NULL COMMENT '库存量',
  `supplier` varchar(50) DEFAULT NULL COMMENT '供货商',
  PRIMARY KEY (`bookid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `bookinfo` */

insert  into `bookinfo`(`bookid`,`bookName`,`author`,`edition`,`publishUnit`,`ISBN`,`inventory`,`supplier`) values (2,'高等数学一','李莉',2,'长江出版社','4',2222,'三立书业'),(3,'高等数学二','张亮',6,'浙江大学','2',6786,'长江出版社'),(4,'线性代数上','刘芳',4,'清华出版社','23',233,'长江出版社'),(5,'大学英语一','李阳',3,'清华大学出版社','127238',1,'三立书业'),(6,'大学英语二','李阳',3,'清华大学出版社','233',2,'三立书业'),(7,'C++程序设计','谭浩强',2,'清华大学出版社','3252324',1,'三立书业'),(8,'大学生心理修养','曹东',3,'高等教育出版社','233',2,'三立书业'),(9,'马克思主义','东平',6,'高等教育出版社','1333',4,'三立书业');

/*Table structure for table `bookrecords` */

DROP TABLE IF EXISTS `bookrecords`;

CREATE TABLE `bookrecords` (
  `bookMemuId` int(11) NOT NULL COMMENT '购书单号',
  `bookid` int(11) DEFAULT NULL COMMENT '教材id',
  `supplier` varchar(11) DEFAULT NULL COMMENT '供货商',
  `booknum` int(11) DEFAULT NULL COMMENT '数目',
  `purchaseTime` varchar(20) DEFAULT NULL COMMENT '购买时间',
  KEY `bookMemuId` (`bookMemuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bookrecords` */

/*Table structure for table `classinfo` */

DROP TABLE IF EXISTS `classinfo`;

CREATE TABLE `classinfo` (
  `classId` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级id',
  `className` varchar(30) DEFAULT NULL COMMENT '班级名称',
  `numOfClassStu` int(11) DEFAULT NULL COMMENT '班级人数',
  `gradeId` int(11) DEFAULT NULL COMMENT '年级id',
  `numOforderBookstu` int(11) DEFAULT NULL COMMENT '订书总人数',
  `getbooks` tinyint(1) DEFAULT '0' COMMENT '是否领取新书',
  PRIMARY KEY (`classId`),
  KEY `gradeId` (`gradeId`),
  CONSTRAINT `classinfo_ibfk_1` FOREIGN KEY (`gradeId`) REFERENCES `gradeinfo` (`gradeId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `classinfo` */

insert  into `classinfo`(`classId`,`className`,`numOfClassStu`,`gradeId`,`numOforderBookstu`,`getbooks`) values (2,'医学信息工程二班',42,1,42,NULL),(3,'英语一班',41,4,41,NULL),(18,'中西临床一班',79,6,79,NULL),(19,'中西临床三班',5,6,55,NULL),(20,'对外贸易二班',43,8,43,0),(21,'英语一班',54,16,54,0),(22,'医学信息工程一班',45,11,45,0),(23,'医学信息工程二班',50,11,50,0);

/*Table structure for table `gradeinfo` */

DROP TABLE IF EXISTS `gradeinfo`;

CREATE TABLE `gradeinfo` (
  `gradeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '年级id标示不同专业不同年级',
  `college` varchar(50) DEFAULT NULL COMMENT '学院',
  `grade` int(11) DEFAULT '2011' COMMENT '年级',
  `profession` varchar(50) DEFAULT NULL COMMENT '专业',
  `numOfClass` int(11) DEFAULT NULL COMMENT '班级数目',
  `numOfStu` int(11) DEFAULT NULL COMMENT '买书学生总人数',
  `campus` varchar(20) DEFAULT NULL COMMENT '校区',
  `schoolSystem` int(11) DEFAULT NULL COMMENT '该专业几年制',
  PRIMARY KEY (`gradeId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `gradeinfo` */

insert  into `gradeinfo`(`gradeId`,`college`,`grade`,`profession`,`numOfClass`,`numOfStu`,`campus`,`schoolSystem`) values (1,'信息工程学院',2013,'医学信息工程',2,42,'黄',4),(3,'信息工程学院',2015,'医学信息工程',2,0,'黄',4),(4,'人文学院',2011,'英语',2,41,'黄',4),(6,'基础医学院',2016,'中西临床',4,134,'黄',4),(7,'信息工程学院',2014,'医学信息工程',2,89,'黄',4),(8,'人文学院',2016,'对外贸易',2,43,'黄',4),(9,'信息工程学院',2016,'医学信息工程',2,0,'黄',4),(11,'信息工程学院',2017,'医学信息工程',2,95,'黄',4),(13,'信息工程学院',2017,'信息管理',2,0,'黄',4),(16,'人文学院',2017,'英语',2,54,'黄',4),(17,'人文学院',2017,'对外贸易',2,0,'黄',4);

/*Table structure for table `sendbookrecord` */

DROP TABLE IF EXISTS `sendbookrecord`;

CREATE TABLE `sendbookrecord` (
  `sendBookId` int(11) NOT NULL COMMENT '发书单编号',
  `classId` int(11) NOT NULL COMMENT '班级编号',
  `bookid` int(11) NOT NULL COMMENT '教材编号',
  `sendTime` varchar(40) NOT NULL COMMENT '发书时间',
  `principal` varchar(30) DEFAULT NULL COMMENT '领书人/负责人',
  `phoneOfprincipal` int(11) DEFAULT NULL COMMENT '领书人电话',
  `booksnum` int(11) DEFAULT NULL COMMENT '每种教材本书'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sendbookrecord` */

/*Table structure for table `teachingplan` */

DROP TABLE IF EXISTS `teachingplan`;

CREATE TABLE `teachingplan` (
  `planId` int(11) NOT NULL AUTO_INCREMENT COMMENT '教学计划id',
  `gradeId` int(11) DEFAULT NULL COMMENT '年级id',
  `courseName` varchar(40) DEFAULT NULL COMMENT '课程名称',
  `bookid` int(11) DEFAULT NULL COMMENT '教材id',
  `semester` int(11) DEFAULT NULL COMMENT '学期',
  PRIMARY KEY (`planId`),
  KEY `gradeId` (`gradeId`),
  KEY `bookid` (`bookid`),
  CONSTRAINT `teachingplan_ibfk_1` FOREIGN KEY (`gradeId`) REFERENCES `gradeinfo` (`gradeId`),
  CONSTRAINT `teachingplan_ibfk_2` FOREIGN KEY (`bookid`) REFERENCES `bookinfo` (`bookid`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `teachingplan` */

insert  into `teachingplan`(`planId`,`gradeId`,`courseName`,`bookid`,`semester`) values (2,1,'线性代数',4,3),(3,1,'大学英语',5,1),(4,1,'大学英语',6,2),(5,7,'高等数学',2,1),(6,7,'线性代数',4,2),(7,1,'大学生心理教育',8,1),(8,4,'大学英语',5,1),(9,7,'高等数学',3,2),(10,8,'高等数学',2,2),(11,9,'大学英语',5,1),(12,9,'高等数学',2,1),(13,9,'C++程序设计',7,1),(14,9,'高等数学',3,2),(15,9,'线性代数',4,2),(16,9,'大学生心理教育',8,3),(17,1,'高等数学',2,1),(36,11,'大学英语',5,1),(37,11,'高等数学',2,1),(38,11,'C++程序设计',7,1),(39,11,'大学生心理教育',8,1),(40,11,'高等数学',3,2),(41,11,'线性代数',4,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
