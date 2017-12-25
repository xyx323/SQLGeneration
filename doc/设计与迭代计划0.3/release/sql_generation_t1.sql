/*
Navicat MySQL Data Transfer

Source Server         : 202.120.40.28_33060
Source Server Version : 50624
Source Host           : 202.120.40.28:33060
Source Database       : sql_generation_t1

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-12-20 16:44:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for data_field
-- ----------------------------
DROP TABLE IF EXISTS `data_field`;
CREATE TABLE `data_field` (
  `field_id` int(11) NOT NULL AUTO_INCREMENT,
  `table_id` int(11) DEFAULT NULL,
  `field_name` varchar(100) DEFAULT NULL,
  `field_type` varchar(100) DEFAULT NULL,
  `field_length` int(11) DEFAULT NULL,
  `is_pk` tinyint(1) DEFAULT NULL,
  `is_fk` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`field_id`),
  KEY `FK_Relationship_12` (`table_id`),
  CONSTRAINT `FK_Relationship_12` FOREIGN KEY (`table_id`) REFERENCES `data_table` (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_field
-- ----------------------------
INSERT INTO `data_field` VALUES ('59', '1', 'customerNumber', 'int', '11', '1', '0');
INSERT INTO `data_field` VALUES ('60', '1', 'customerName', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('61', '1', 'contactLastName', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('62', '1', 'contactFirstName', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('63', '1', 'phone', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('64', '1', 'addressLine1', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('65', '1', 'addressLine2', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('66', '1', 'city', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('67', '1', 'state', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('68', '1', 'postalCode', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('69', '1', 'country', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('70', '1', 'employeeNumber', 'varchar', '50', '0', '1');
INSERT INTO `data_field` VALUES ('71', '1', 'creditLimit', 'decimal', '10', '0', '0');
INSERT INTO `data_field` VALUES ('72', '2', 'employeeNumber', 'int', '11', '1', '0');
INSERT INTO `data_field` VALUES ('73', '2', 'lastName', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('74', '2', 'firstName', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('75', '2', 'extension', 'varchar', '10', '0', '0');
INSERT INTO `data_field` VALUES ('76', '2', 'email', 'varchar', '100', '0', '0');
INSERT INTO `data_field` VALUES ('77', '2', 'officeCode', 'varchar', '10', '0', '1');
INSERT INTO `data_field` VALUES ('78', '2', 'reportsTo', 'int', '11', '0', '1');
INSERT INTO `data_field` VALUES ('79', '2', 'jobTitle', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('80', '3', 'officeCode', 'varchar', '10', '1', '0');
INSERT INTO `data_field` VALUES ('81', '3', 'city', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('82', '3', 'phone', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('83', '3', 'addressLine1', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('84', '3', 'addressLine2', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('85', '3', 'state', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('86', '3', 'country', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('87', '3', 'postalCode', 'varchar', '15', '0', '0');
INSERT INTO `data_field` VALUES ('88', '3', 'territory', 'varchar', '10', '0', '0');
INSERT INTO `data_field` VALUES ('89', '4', 'orderNumber', 'int', '11', '1', '0');
INSERT INTO `data_field` VALUES ('90', '4', 'orderDate', 'date', null, '0', '0');
INSERT INTO `data_field` VALUES ('91', '4', 'requiredDate', 'date', null, '0', '0');
INSERT INTO `data_field` VALUES ('92', '4', 'shippedDate', 'date', null, '0', '0');
INSERT INTO `data_field` VALUES ('93', '4', 'status', 'varchar', '15', '0', '0');
INSERT INTO `data_field` VALUES ('94', '4', 'comments', 'text', null, '0', '0');
INSERT INTO `data_field` VALUES ('95', '4', 'customerNumber', 'int', '11', '0', '1');
INSERT INTO `data_field` VALUES ('96', '5', 'orderNumber', 'int', '11', '1', '1');
INSERT INTO `data_field` VALUES ('97', '5', 'productCode', 'varchar', '15', '1', '1');
INSERT INTO `data_field` VALUES ('98', '5', 'quantityQrdered', 'int', '11', '0', '0');
INSERT INTO `data_field` VALUES ('99', '5', 'priceEach', 'decimal', '10', '0', '0');
INSERT INTO `data_field` VALUES ('100', '5', 'orderLineNumber', 'smallint', '6', '0', '0');
INSERT INTO `data_field` VALUES ('101', '6', 'customerNumber', 'int', '11', '1', '1');
INSERT INTO `data_field` VALUES ('102', '6', 'checkNumber', 'varchar', '50', '1', '0');
INSERT INTO `data_field` VALUES ('103', '6', 'paymentDate', 'date', null, '0', '0');
INSERT INTO `data_field` VALUES ('104', '6', 'amount', 'decimal', '10', '0', '0');
INSERT INTO `data_field` VALUES ('105', '7', 'productCode', 'varchar', '15', '1', '0');
INSERT INTO `data_field` VALUES ('106', '7', 'productName', 'varchar', '70', '0', '0');
INSERT INTO `data_field` VALUES ('107', '7', 'productLine', 'varchar', '50', '0', '1');
INSERT INTO `data_field` VALUES ('108', '7', 'productScale', 'varchar', '10', '0', '0');
INSERT INTO `data_field` VALUES ('109', '7', 'productVendor', 'varchar', '50', '0', '0');
INSERT INTO `data_field` VALUES ('110', '7', 'productDescription', 'text', null, '0', '0');
INSERT INTO `data_field` VALUES ('111', '7', 'quantityInStock', 'smallint', '6', '0', '0');
INSERT INTO `data_field` VALUES ('112', '7', 'buyPrice', 'decimal', '10', '0', '0');
INSERT INTO `data_field` VALUES ('113', '7', 'MSRP', 'decimal', '10', '0', '0');
INSERT INTO `data_field` VALUES ('114', '8', 'productLine', 'varchar', '50', '1', '0');
INSERT INTO `data_field` VALUES ('115', '8', 'textDescription', 'varchar', '4000', '0', '0');
INSERT INTO `data_field` VALUES ('116', '8', 'htmlDescription', 'mediumtext', null, '0', '0');
INSERT INTO `data_field` VALUES ('117', '8', 'image', 'mediumblob', null, '0', '0');

-- ----------------------------
-- Table structure for data_foundation
-- ----------------------------
DROP TABLE IF EXISTS `data_foundation`;
CREATE TABLE `data_foundation` (
  `df_id` int(11) NOT NULL AUTO_INCREMENT,
  `df_name` varchar(100) DEFAULT NULL,
  `df_creator` varchar(100) DEFAULT NULL,
  `df_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `df_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`df_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_foundation
-- ----------------------------
INSERT INTO `data_foundation` VALUES ('1', 'testdf', 'xyx', '2017-12-12 12:35:29', 'testdf');

-- ----------------------------
-- Table structure for data_relation
-- ----------------------------
DROP TABLE IF EXISTS `data_relation`;
CREATE TABLE `data_relation` (
  `data_relation_id` int(11) NOT NULL AUTO_INCREMENT,
  `table1_id` int(11) DEFAULT NULL,
  `table2_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`data_relation_id`),
  KEY `FK_Relationship_15` (`table1_id`),
  KEY `FK_Relationship_16` (`table2_id`),
  CONSTRAINT `FK_Relationship_15` FOREIGN KEY (`table1_id`) REFERENCES `data_table` (`table_id`),
  CONSTRAINT `FK_Relationship_16` FOREIGN KEY (`table2_id`) REFERENCES `data_table` (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_relation
-- ----------------------------

-- ----------------------------
-- Table structure for data_table
-- ----------------------------
DROP TABLE IF EXISTS `data_table`;
CREATE TABLE `data_table` (
  `table_id` int(11) NOT NULL AUTO_INCREMENT,
  `df_id` int(11) DEFAULT NULL,
  `table_name` varchar(100) DEFAULT NULL,
  `table_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`table_id`),
  KEY `FK_Reference_10` (`df_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`df_id`) REFERENCES `data_foundation` (`df_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_table
-- ----------------------------
INSERT INTO `data_table` VALUES ('1', '1', 'customers', '客户信息');
INSERT INTO `data_table` VALUES ('2', '1', 'employees', '雇员信息');
INSERT INTO `data_table` VALUES ('3', '1', 'offices', '办公室信息');
INSERT INTO `data_table` VALUES ('4', '1', 'orders', '订单信息');
INSERT INTO `data_table` VALUES ('5', '1', 'orderdetails', '订单详细信息');
INSERT INTO `data_table` VALUES ('6', '1', 'payments', '付款信息');
INSERT INTO `data_table` VALUES ('7', '1', 'products', '产品信息');
INSERT INTO `data_table` VALUES ('8', '1', 'productlines', '产品线信息');

-- ----------------------------
-- Table structure for db_connection
-- ----------------------------
DROP TABLE IF EXISTS `db_connection`;
CREATE TABLE `db_connection` (
  `dbc_id` int(11) NOT NULL AUTO_INCREMENT,
  `db_name` varchar(100) DEFAULT NULL,
  `db_location` varchar(50) DEFAULT NULL,
  `db_port` varchar(10) DEFAULT NULL,
  `db_account` varchar(50) DEFAULT NULL,
  `db_pwd` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dbc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_connection
-- ----------------------------
INSERT INTO `db_connection` VALUES ('1', 'classicmodels', '202.120.40.28', '33060', 'admin', 'se1405lab');

-- ----------------------------
-- Table structure for filter
-- ----------------------------
DROP TABLE IF EXISTS `filter`;
CREATE TABLE `filter` (
  `filter_id` int(11) NOT NULL AUTO_INCREMENT,
  `folder_id` int(11) DEFAULT NULL,
  `object_id` int(11) DEFAULT NULL,
  `filter_name` varchar(100) DEFAULT NULL,
  `operator` int(11) DEFAULT NULL,
  `operand_type` int(11) DEFAULT NULL,
  `operands` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`filter_id`),
  KEY `FK_Relationship_17` (`object_id`),
  KEY `FK_Relationship_8` (`folder_id`),
  CONSTRAINT `FK_Relationship_17` FOREIGN KEY (`object_id`) REFERENCES `object` (`object_id`),
  CONSTRAINT `FK_Relationship_8` FOREIGN KEY (`folder_id`) REFERENCES `folder` (`folder_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of filter
-- ----------------------------
INSERT INTO `filter` VALUES ('1', '1', '1', 'A博士', '1', '1', '\'Atelier graphique\'');
INSERT INTO `filter` VALUES ('2', '1', '2', '美国', '1', '1', '\'USA\'');
INSERT INTO `filter` VALUES ('3', '1', '6', '1号办公室', '1', '1', '1');

-- ----------------------------
-- Table structure for folder
-- ----------------------------
DROP TABLE IF EXISTS `folder`;
CREATE TABLE `folder` (
  `folder_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `folder_name` varchar(100) DEFAULT NULL,
  `folder_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`folder_id`),
  KEY `FK_Relationship_6` (`u_id`),
  CONSTRAINT `FK_Relationship_6` FOREIGN KEY (`u_id`) REFERENCES `universe` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of folder
-- ----------------------------
INSERT INTO `folder` VALUES ('1', '1', 'test_folder', '测试用文件夹');

-- ----------------------------
-- Table structure for object
-- ----------------------------
DROP TABLE IF EXISTS `object`;
CREATE TABLE `object` (
  `object_id` int(11) NOT NULL AUTO_INCREMENT,
  `folder_id` int(11) DEFAULT NULL,
  `object_name` varchar(100) DEFAULT NULL,
  `object_description` varchar(200) DEFAULT NULL,
  `object_type` int(11) DEFAULT NULL,
  `related_field` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`object_id`),
  KEY `FK_Relationship_7` (`folder_id`),
  CONSTRAINT `FK_Relationship_7` FOREIGN KEY (`folder_id`) REFERENCES `folder` (`folder_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of object
-- ----------------------------
INSERT INTO `object` VALUES ('1', '1', 'customer name', '顾客姓名', '1', '60');
INSERT INTO `object` VALUES ('2', '1', 'customer country', '顾客国籍', '1', '69');
INSERT INTO `object` VALUES ('3', '1', 'customer number', '顾客编号', '1', '59');
INSERT INTO `object` VALUES ('4', '1', 'total buy price', '总购买金额', '2', 'sum(products.buyPrice)');
INSERT INTO `object` VALUES ('5', '1', 'avg buy price', '平均购买金额', '2', 'avg(products.buyPrice)');
INSERT INTO `object` VALUES ('6', '1', 'office id', '办公室', '1', '77');
INSERT INTO `object` VALUES ('7', '1', 'product price', '商品价格', '1', '112');
INSERT INTO `object` VALUES ('8', '1', 'customer phone number', '顾客电话号码', '1', '63');

-- ----------------------------
-- Table structure for query_statement
-- ----------------------------
DROP TABLE IF EXISTS `query_statement`;
CREATE TABLE `query_statement` (
  `qs_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `qs` varchar(100) DEFAULT NULL,
  `qs_type` int(11) DEFAULT NULL,
  `qs_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`qs_id`),
  KEY `Relationship_6_FK` (`u_id`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`u_id`) REFERENCES `universe` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of query_statement
-- ----------------------------
INSERT INTO `query_statement` VALUES ('1', '1', 'SELECT avg(products.buyPrice) from products', '1', '商品平均价格');
INSERT INTO `query_statement` VALUES ('2', '1', 'SELECT avg(products.buyPrice) + 10 from products', '1', '商品平均价格加10');

-- ----------------------------
-- Table structure for universe
-- ----------------------------
DROP TABLE IF EXISTS `universe`;
CREATE TABLE `universe` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `df_id` int(11) DEFAULT NULL,
  `dbc_id` int(11) DEFAULT NULL,
  `u_creator` varchar(100) DEFAULT NULL,
  `u_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `u_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`u_id`),
  KEY `FK_Relationship_4` (`df_id`),
  KEY `FK_Relationship_5` (`dbc_id`),
  CONSTRAINT `FK_Relationship_4` FOREIGN KEY (`df_id`) REFERENCES `data_foundation` (`df_id`),
  CONSTRAINT `FK_Relationship_5` FOREIGN KEY (`dbc_id`) REFERENCES `db_connection` (`dbc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of universe
-- ----------------------------
INSERT INTO `universe` VALUES ('1', '1', '1', 'xyx', '2017-12-12 12:36:06', 'testUniverse');
