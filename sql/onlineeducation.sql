/*
 Navicat Premium Data Transfer

 Source Server         : zj-center
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 112.126.74.242:3306
 Source Schema         : onlineeducation

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 13/05/2020 11:48:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `AdminID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AdminName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`AdminID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'zj', '123');

-- ----------------------------
-- Table structure for classification
-- ----------------------------
DROP TABLE IF EXISTS `classification`;
CREATE TABLE `classification`  (
  `ClassID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ClassName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ClassID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classification
-- ----------------------------
INSERT INTO `classification` VALUES (47, '计算机类');
INSERT INTO `classification` VALUES (49, '外语类');
INSERT INTO `classification` VALUES (50, '建筑类');
INSERT INTO `classification` VALUES (51, '艺术类');
INSERT INTO `classification` VALUES (52, '电子类');
INSERT INTO `classification` VALUES (61, '技术类');

-- ----------------------------
-- Table structure for resourceinformationobtain
-- ----------------------------
DROP TABLE IF EXISTS `resourceinformationobtain`;
CREATE TABLE `resourceinformationobtain`  (
  `ResourceID` bigint(255) NOT NULL AUTO_INCREMENT,
  `CourseName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Lecturer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ClassIfication` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NumberEpisodes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CourseInformation` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CourseIntroduction` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CoverPath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Reference` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`ResourceID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resourceinformationobtain
-- ----------------------------
INSERT INTO `resourceinformationobtain` VALUES (48, 'Java程序设计', '何一', '计算机类', '10', 'Java是十分牛逼的语言', 'Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程 。\r\n\r\nJava具有简单性、面向对象、分布式、健壮性、安全性、平台独立与可移植性、多线程、动态性等特点 。Java可以编写桌面应用程序、Web应用程序、分布式系统和嵌入式系统应用程序等 。', '微信图片_20190418134918.jpg', 0);
INSERT INTO `resourceinformationobtain` VALUES (49, '3DMAX', '张程', '计算机类', '10', '3dMAX是非常牛逼的课程', '3D Studio Max，常简称为3ds Max或MAX，是Discreet公司开发的(后被Autodesk公司合并)基于PC系统的三维动画渲染和制作软件。其前身是基于DOS操作系统的3D Studio系列软件。在Windows NT出现以前，工业级的CG制作被SGI图形工作站所垄断。3D Studio Max + Windows NT组合的出现一下子降低了CG制作的门槛，首先开始运用在电脑游戏中的动画制作，后更进一步开始参与影视片的特效制作，例如X战警II，最后的武士等。在Discreet 3Ds max 7后，正式更名为Autodesk 3ds Max,，最新版本是3ds max 2020。', '微信图片_20190418134939.jpg', 1);
INSERT INTO `resourceinformationobtain` VALUES (50, '操作系统', '张坤', '计算机类', '15', '让操作系统操作你', '操作系统是管理计算机硬件资源，控制其他程序运行并为用户提供交互操作界面的系统软件的集合。操作系统是计算机系统的关键组成部分，负责管理与配置内存、决定系统资源供需的优先次序、控制输入与输出设备、操作网络与管理文件系统等基本任务。操作系统的种类很多，各种设备安装的操作系统可从简单到复杂，可从手机的嵌入式操作系统到超级计算机的大型操作系统。目前流行的现代操作系统主要有Android、BSD、iOS、Linux、Mac OS X、Windows、Windows Phone和z/OS等，除了Windows和z/OS等少数操作系统，大部分操作系统都为类Unix操作系统。', '微信图片_20190418134954.jpg', 0);
INSERT INTO `resourceinformationobtain` VALUES (51, '高级英语', '英语老师', '外语类', '20', '英语，是能让你哭的一门课', '英语（English）属于印欧语系中日耳曼语族下的西日耳曼语支，由古代从欧洲大陆移民大不列颠岛的盎格鲁、撒克逊和朱特部落的日耳曼人所说的语言演变而来，并通过英国的殖民活动传播到世界各地。根据以英语作为母语的人数计算，英语是最多国家使用的官方语言，英语也是世界上最广泛的第二语言，也是欧盟，最多国际组织和英联邦国家的官方语言之一。但仅拥有世界第三位的母语使用者，少于官话汉语和西班牙语。上两个世纪英国和美国在文化、经济、军事、政治和科学上的领先地位使得英语成为一种国际语言。如今，许多国际场合都使用英语做为沟通媒介。英语也是与电脑联系最密切的语言，大多数编程语言都与英语有联系，而且随着网络的使用，使英文的使用更普及。英语是联合国的工作语言之一。', '微信图片_20190418134959.jpg', 1);
INSERT INTO `resourceinformationobtain` VALUES (52, 'UI设计', '敖磊', '计算机类', '10', '敖老师带你学UI', 'UI设计(或称界面设计)是指对软件的人机交互、操作逻辑、界面美观的整体设计，也叫界面设计。UI设计分为实体UI和虚拟UI，互联网说的UI设计是虚拟UI，UI即User Interface(用户界面)的简称。\r\n\r\n好的UI设计不仅是让软件变得有个性有品位，还要让软件的操作变得舒适简单、自由，充分体现软件的定位和特点。', '微信图片_20190418134950.jpg', 1);

-- ----------------------------
-- Table structure for userinformation
-- ----------------------------
DROP TABLE IF EXISTS `userinformation`;
CREATE TABLE `userinformation`  (
  `UserID` bigint(255) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UimagePath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`UserID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinformation
-- ----------------------------
INSERT INTO `userinformation` VALUES (25, 'wlxzz', '201613052', '202cb962ac59075b964b07152d234b70', NULL);
INSERT INTO `userinformation` VALUES (27, 'cccc', '123456', 'e10adc3949ba59abbe56e057f20f883e', NULL);

-- ----------------------------
-- Table structure for userlikeresource
-- ----------------------------
DROP TABLE IF EXISTS `userlikeresource`;
CREATE TABLE `userlikeresource`  (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `UserID` bigint(255) NOT NULL,
  `History` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userlikeresource
-- ----------------------------
INSERT INTO `userlikeresource` VALUES (5, 26, NULL);
INSERT INTO `userlikeresource` VALUES (6, 27, '34,38,');

SET FOREIGN_KEY_CHECKS = 1;
