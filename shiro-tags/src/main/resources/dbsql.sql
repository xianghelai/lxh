/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.17-log : Database - testdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `testdb`;

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `rid` int(20) DEFAULT NULL,
  `pid` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_permission` */

insert  into `role_permission`(`rid`,`pid`) values (1,1),(1,2),(1,3),(2,1),(2,2),(3,1),(1,4);

/*Table structure for table `table_permission` */

DROP TABLE IF EXISTS `table_permission`;

CREATE TABLE `table_permission` (
  `pid` int(20) NOT NULL AUTO_INCREMENT,
  `p_name` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `p_url` varchar(200) DEFAULT NULL COMMENT '权限url',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `table_permission` */

insert  into `table_permission`(`pid`,`p_name`,`p_url`) values (1,'user:add','/user/add'),(2,'user:update','/user/update'),(3,'admin:del','/admin/del'),(4,'user:del','/user/del');

/*Table structure for table `table_role` */

DROP TABLE IF EXISTS `table_role`;

CREATE TABLE `table_role` (
  `rid` int(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `table_role` */

insert  into `table_role`(`rid`,`role_name`) values (1,'super'),(2,'user'),(3,'guest');

/*Table structure for table `table_user` */

DROP TABLE IF EXISTS `table_user`;

CREATE TABLE `table_user` (
  `User_Id` int(19) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `Login_Name` varchar(50) NOT NULL COMMENT '登陆名',
  `Login_Password` varchar(32) NOT NULL COMMENT '登陆密码。32md5加密',
  `Gender` char(1) DEFAULT NULL COMMENT '性别',
  `Contact_phone` varchar(50) DEFAULT NULL COMMENT '联系人电话',
  `Contact_Email` varchar(50) NOT NULL COMMENT '联系人email',
  `User_Name` varchar(50) NOT NULL COMMENT '真实姓名',
  `Birthday` date DEFAULT NULL COMMENT '出生日期',
  `Contact_address` varchar(500) DEFAULT NULL COMMENT '联系人地址',
  `Post_Code` varchar(6) DEFAULT NULL COMMENT '邮编',
  `Register_Time` datetime NOT NULL COMMENT '注册时间',
  `User_Status` char(1) NOT NULL COMMENT '1 启用，0 禁止',
  `Province` varchar(10) DEFAULT NULL COMMENT '省份的拼音首字母大写',
  `Last_Modefy_Time` datetime DEFAULT NULL COMMENT '最后修改日期',
  PRIMARY KEY (`User_Id`),
  UNIQUE KEY `unique` (`Login_Name`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `table_user` */

insert  into `table_user`(`User_Id`,`Login_Name`,`Login_Password`,`Gender`,`Contact_phone`,`Contact_Email`,`User_Name`,`Birthday`,`Contact_address`,`Post_Code`,`Register_Time`,`User_Status`,`Province`,`Last_Modefy_Time`) values (1,'bbdw','f8327b26a178ba557a59ace3378c2f95','1','183783738264','1059653473@qq.com1','lailai','2019-09-04','dsioa','3742','2019-10-08 00:00:00','1','GD','2019-10-12 08:59:21'),(2,'csair','e10adc3949ba59abbe56e057f20f883e','1','123245555','105963@qq.com','lailai','2019-10-05','dsioa','455','2019-10-08 00:00:00','1',NULL,'2019-10-12 08:59:13'),(3,'sada','e10adc3949ba59abbe56e057f20f883e','1','18378232334','admin@qq.com','admin','2019-02-14','的撒好看',NULL,'2019-09-12 00:00:00','1',NULL,'2019-10-14 08:28:49'),(4,'root','e10adc3949ba59abbe56e057f20f883e','0','13283484833','admin@qq.com','admin','2019-10-14','的方式','34781','2019-09-12 00:00:00','1','DGD','2019-10-14 08:58:39'),(7,'root2','e10adc3949ba59abbe56e057f20f883e','0',NULL,'admin@qq.com','admin',NULL,NULL,NULL,'2019-09-12 00:00:00','1',NULL,NULL),(8,'lailaila','e10adc3949ba59abbe56e057f20f883e','0','1323234555','la0000000i@qq.com','admin','2019-10-14','143455',NULL,'2019-09-12 04:30:33','1','GD','2019-10-14 22:53:49'),(9,'yellow','e10adc3949ba59abbe56e057f20f883e','0',NULL,'lai@qq.com','真实姓名',NULL,NULL,NULL,'2019-09-12 04:30:33','1',NULL,NULL),(10,'honggong','e10adc3949ba59abbe56e057f20f883e','0','18378332878','lai@qq.com','真实姓名','1998-09-05','广州白云','33891','2019-09-12 04:30:33','1','GD',NULL),(11,'system','e10adc3949ba59abbe56e057f20f883e','0','17386493883','10387240@qq.com','MrBird',NULL,NULL,'364821','2019-10-09 16:03:00','1','GD',NULL),(13,'333','e10adc3949ba59abbe56e057f20f883e','1','','232333333333','MrBird','2019-10-09',NULL,'','2019-10-10 16:32:42','1','',NULL),(14,'4545','e10adc3949ba59abbe56e057f20f883e','0','183836482382','','sds','2019-10-09','null','','2019-10-10 17:04:00','0','','2019-10-14 10:28:10'),(18,'wuwu6','e10adc3949ba59abbe56e057f20f883e','0','18378352343','1232894@qq.com','五五','1991-10-14','白云','25638','2019-10-14 08:30:26','1','GD','2019-10-14 08:46:10');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `uid` int(20) DEFAULT NULL COMMENT 'userid',
  `rid` int(20) DEFAULT NULL COMMENT 'roleid'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`uid`,`rid`) values (1,1),(1,2),(2,2),(14,3),(14,1),(18,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
