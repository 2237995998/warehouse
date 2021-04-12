/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : warehouse

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2021-04-12 22:11:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for equipment
-- ----------------------------
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment` (
  `equipment_id` int NOT NULL AUTO_INCREMENT,
  `equipment_status` int NOT NULL,
  `equipment_category_id` int NOT NULL,
  PRIMARY KEY (`equipment_id`),
  KEY `ec_id_con` (`equipment_category_id`),
  CONSTRAINT `ec_id_con` FOREIGN KEY (`equipment_category_id`) REFERENCES `equipment_category` (`equipment_category_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equipment
-- ----------------------------
INSERT INTO `equipment` VALUES ('1', '1', '2');
INSERT INTO `equipment` VALUES ('2', '0', '3');
INSERT INTO `equipment` VALUES ('3', '1', '3');
INSERT INTO `equipment` VALUES ('4', '0', '2');
INSERT INTO `equipment` VALUES ('5', '1', '2');

-- ----------------------------
-- Table structure for equipment_category
-- ----------------------------
DROP TABLE IF EXISTS `equipment_category`;
CREATE TABLE `equipment_category` (
  `equipment_category_id` int NOT NULL AUTO_INCREMENT,
  `equipment_category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `equipment_category_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `equipment_category_status` int NOT NULL,
  PRIMARY KEY (`equipment_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equipment_category
-- ----------------------------
INSERT INTO `equipment_category` VALUES ('2', '消防栓', 'http://localhost:8080/warehouse/WM/equipmentPicture/772eb75f2221416badcb0254d4765aec.png', '1');
INSERT INTO `equipment_category` VALUES ('3', '灭火器A型', 'http://localhost:8080/warehouse/WM/equipmentPicture/3124f430d7aa4e639e133fb47c52c246.png', '1');
INSERT INTO `equipment_category` VALUES ('4', '灭火器B型', 'http://localhost:8080/warehouse/WM/equipmentPicture/ac2927f04d9c4dbda4d2d9b0842f5a67.png', '1');
INSERT INTO `equipment_category` VALUES ('5', '222', 'http://localhost:8080/warehouse/WM/equipmentPicture/2d42bb1eeab043e685b74e855ce437cc.png', '1');

-- ----------------------------
-- Table structure for equipment_record
-- ----------------------------
DROP TABLE IF EXISTS `equipment_record`;
CREATE TABLE `equipment_record` (
  `equipment_id` int NOT NULL,
  `buy_time` datetime DEFAULT NULL,
  `destroy_time` datetime DEFAULT NULL,
  `warehouse_manager_buy_id` int DEFAULT NULL,
  `warehouse_manager_destroy_id` int DEFAULT NULL,
  PRIMARY KEY (`equipment_id`),
  KEY `wm_id_con2` (`warehouse_manager_destroy_id`),
  KEY `wm_id_con` (`warehouse_manager_buy_id`),
  CONSTRAINT `e_id_con1` FOREIGN KEY (`equipment_id`) REFERENCES `equipment` (`equipment_id`) ON UPDATE CASCADE,
  CONSTRAINT `wm_id_con` FOREIGN KEY (`warehouse_manager_buy_id`) REFERENCES `warehouse_manager` (`warehouse_manager_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `wm_id_con2` FOREIGN KEY (`warehouse_manager_destroy_id`) REFERENCES `warehouse_manager` (`warehouse_manager_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equipment_record
-- ----------------------------
INSERT INTO `equipment_record` VALUES ('1', '2021-04-02 13:11:18', null, '1', null);
INSERT INTO `equipment_record` VALUES ('2', '2021-04-02 13:44:41', '2021-04-02 15:41:41', '1', '1');
INSERT INTO `equipment_record` VALUES ('3', '2021-04-02 13:44:41', null, '1', null);
INSERT INTO `equipment_record` VALUES ('4', '2021-04-02 14:35:23', '2021-04-10 13:29:56', '1', '1');
INSERT INTO `equipment_record` VALUES ('5', '2021-04-02 14:35:23', null, '1', null);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_id` int NOT NULL AUTO_INCREMENT,
  `goods_status` int NOT NULL,
  `goods_category_id` int NOT NULL,
  `goods_location` int DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `gc_id_con` (`goods_category_id`),
  CONSTRAINT `gc_id_con` FOREIGN KEY (`goods_category_id`) REFERENCES `goods_category` (`goods_category_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '4', '1', '4');
INSERT INTO `goods` VALUES ('2', '2', '5', null);
INSERT INTO `goods` VALUES ('3', '3', '5', null);
INSERT INTO `goods` VALUES ('4', '4', '5', '3');
INSERT INTO `goods` VALUES ('5', '1', '5', null);
INSERT INTO `goods` VALUES ('6', '3', '5', null);
INSERT INTO `goods` VALUES ('7', '1', '5', null);
INSERT INTO `goods` VALUES ('8', '1', '6', null);
INSERT INTO `goods` VALUES ('9', '4', '6', '5');
INSERT INTO `goods` VALUES ('10', '1', '6', null);
INSERT INTO `goods` VALUES ('11', '1', '5', null);
INSERT INTO `goods` VALUES ('12', '1', '5', null);
INSERT INTO `goods` VALUES ('13', '1', '5', null);
INSERT INTO `goods` VALUES ('14', '1', '5', null);
INSERT INTO `goods` VALUES ('16', '1', '1', null);
INSERT INTO `goods` VALUES ('17', '1', '1', null);
INSERT INTO `goods` VALUES ('18', '1', '1', null);
INSERT INTO `goods` VALUES ('19', '1', '1', null);
INSERT INTO `goods` VALUES ('20', '1', '1', null);
INSERT INTO `goods` VALUES ('21', '4', '6', '4');
INSERT INTO `goods` VALUES ('22', '6', '5', '10');
INSERT INTO `goods` VALUES ('23', '2', '5', null);
INSERT INTO `goods` VALUES ('24', '1', '1', null);
INSERT INTO `goods` VALUES ('25', '1', '1', null);
INSERT INTO `goods` VALUES ('26', '1', '5', null);
INSERT INTO `goods` VALUES ('27', '1', '5', null);
INSERT INTO `goods` VALUES ('28', '1', '6', null);
INSERT INTO `goods` VALUES ('29', '1', '1', null);
INSERT INTO `goods` VALUES ('30', '1', '1', null);
INSERT INTO `goods` VALUES ('31', '1', '1', null);
INSERT INTO `goods` VALUES ('32', '1', '1', null);
INSERT INTO `goods` VALUES ('33', '1', '5', null);
INSERT INTO `goods` VALUES ('34', '8', '5', '0');
INSERT INTO `goods` VALUES ('35', '1', '5', null);
INSERT INTO `goods` VALUES ('36', '8', '9', '0');

-- ----------------------------
-- Table structure for goods_apply_record
-- ----------------------------
DROP TABLE IF EXISTS `goods_apply_record`;
CREATE TABLE `goods_apply_record` (
  `goods_id` int NOT NULL,
  `in_apply_time` datetime DEFAULT NULL,
  `out_apply_time` datetime DEFAULT NULL,
  `goods_man_in_id` int DEFAULT NULL,
  `goods_man_out_id` int DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `gm_id_con1` (`goods_man_in_id`),
  KEY `gm_id_con2` (`goods_man_out_id`),
  CONSTRAINT `g_id_con2` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `gm_id_con1` FOREIGN KEY (`goods_man_in_id`) REFERENCES `goods_man` (`goods_man_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `gm_id_con2` FOREIGN KEY (`goods_man_out_id`) REFERENCES `goods_man` (`goods_man_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_apply_record
-- ----------------------------
INSERT INTO `goods_apply_record` VALUES ('1', '2021-03-28 16:27:04', '2021-03-28 16:27:04', '1', null);
INSERT INTO `goods_apply_record` VALUES ('2', '2021-03-28 16:27:04', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('3', '2021-03-28 16:27:04', '2021-03-30 17:22:09', '1', '1');
INSERT INTO `goods_apply_record` VALUES ('4', '2021-03-28 16:27:04', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('5', '2021-03-28 16:27:04', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('6', '2021-03-28 16:27:04', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('7', '2021-03-28 16:27:04', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('8', '2021-03-28 16:27:41', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('9', '2021-03-28 16:27:41', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('10', '2021-03-28 16:27:41', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('11', '2021-03-28 16:28:00', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('12', '2021-03-28 16:28:00', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('13', '2021-03-30 10:55:31', '2021-03-30 16:42:54', '1', '1');
INSERT INTO `goods_apply_record` VALUES ('14', '2021-03-30 10:55:31', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('16', '2021-03-30 17:32:42', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('17', '2021-03-30 17:32:42', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('18', '2021-03-30 17:32:42', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('19', '2021-03-30 17:32:42', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('20', '2021-03-30 17:32:42', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('21', '2021-03-31 17:42:25', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('22', '2021-03-31 17:53:19', '2021-04-01 14:48:06', '1', '1');
INSERT INTO `goods_apply_record` VALUES ('23', '2021-03-31 17:53:19', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('24', '2021-04-01 14:22:24', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('25', '2021-04-01 14:22:24', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('26', '2021-04-01 14:43:09', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('27', '2021-04-01 14:43:09', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('28', '2021-04-01 14:43:58', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('29', '2021-04-01 14:45:00', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('30', '2021-04-01 14:47:20', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('31', '2021-04-01 14:48:34', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('32', '2021-04-01 14:48:34', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('33', '2021-04-01 14:49:08', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('34', '2021-04-07 12:23:31', '2021-04-07 12:24:32', '1', '1');
INSERT INTO `goods_apply_record` VALUES ('35', '2021-04-07 12:23:31', null, '1', null);
INSERT INTO `goods_apply_record` VALUES ('36', '2021-04-12 17:25:39', '2021-04-12 17:27:21', '1', '1');

-- ----------------------------
-- Table structure for goods_category
-- ----------------------------
DROP TABLE IF EXISTS `goods_category`;
CREATE TABLE `goods_category` (
  `goods_category_id` int NOT NULL AUTO_INCREMENT,
  `goods_category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goods_category_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goods_category_status` int NOT NULL,
  PRIMARY KEY (`goods_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_category
-- ----------------------------
INSERT INTO `goods_category` VALUES ('1', '冰箱', 'http://localhost:8080/warehouse/SM/goodsPicture/10aed7afd9cf460eb6faead5d7556459.png', '1');
INSERT INTO `goods_category` VALUES ('5', '洗衣机', 'http://localhost:8080/warehouse/SM/goodsPicture/5ef50b00552c4a15b3e2cdddd03ae38a.png', '1');
INSERT INTO `goods_category` VALUES ('6', '电视机', 'http://localhost:8080/warehouse/SM/goodsPicture/bb8bc7d6d41e46b4b2a3182dfcfa9ef7.png', '1');
INSERT INTO `goods_category` VALUES ('7', '就将计就计', 'http://localhost:8080/warehouse/SM/goodsPicture/42715ef479d54006b3bd8f671b1dee08.png', '0');
INSERT INTO `goods_category` VALUES ('8', '空调', 'http://localhost:8080/warehouse/SM/goodsPicture/fe7d9167d2c84899ab42c082aaebe0f8.png', '1');
INSERT INTO `goods_category` VALUES ('9', 'ce', 'http://localhost:8080/warehouse/SM/goodsPicture/13caa33bf8684dd8bb03867cc4e6ecd9.png', '1');

-- ----------------------------
-- Table structure for goods_house_record
-- ----------------------------
DROP TABLE IF EXISTS `goods_house_record`;
CREATE TABLE `goods_house_record` (
  `goods_id` int NOT NULL,
  `in_time` datetime DEFAULT NULL,
  `out_time` datetime DEFAULT NULL,
  `warehouse_manager_in_id` int DEFAULT NULL,
  `warehouse_manager_out_id` int DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `wm_id_con3` (`warehouse_manager_in_id`),
  CONSTRAINT `g_id_con1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `wm_id_con1` FOREIGN KEY (`warehouse_manager_in_id`) REFERENCES `warehouse_manager` (`warehouse_manager_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `wm_id_con3` FOREIGN KEY (`warehouse_manager_in_id`) REFERENCES `warehouse_manager` (`warehouse_manager_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_house_record
-- ----------------------------
INSERT INTO `goods_house_record` VALUES ('1', '2021-03-31 17:49:23', null, '1', null);
INSERT INTO `goods_house_record` VALUES ('4', '2021-03-31 17:40:20', null, '1', null);
INSERT INTO `goods_house_record` VALUES ('9', '2021-03-31 17:47:59', null, '1', null);
INSERT INTO `goods_house_record` VALUES ('21', '2021-03-31 17:45:08', null, '1', null);
INSERT INTO `goods_house_record` VALUES ('22', '2021-04-01 10:27:59', null, '1', null);
INSERT INTO `goods_house_record` VALUES ('34', '2021-04-07 12:24:17', '2021-04-07 12:24:53', '1', '1');
INSERT INTO `goods_house_record` VALUES ('36', '2021-04-12 17:26:27', '2021-04-12 17:27:56', '1', '1');

-- ----------------------------
-- Table structure for goods_man
-- ----------------------------
DROP TABLE IF EXISTS `goods_man`;
CREATE TABLE `goods_man` (
  `goods_man_id` int NOT NULL AUTO_INCREMENT,
  `goods_man_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goods_man_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goods_man_header` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goods_man_status` int NOT NULL,
  `goods_man_tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`goods_man_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_man
-- ----------------------------
INSERT INTO `goods_man` VALUES ('1', '1', '测试商品运输员', 'http://localhost:8080/warehouse/header/e681897e06bb4c94a18adb2a85140848.png', '1', '123555');
INSERT INTO `goods_man` VALUES ('8', '123', '运输', 'http://localhost:8080/warehouse/header/f3bbafb3203742109e55afe834fc2e83.jpg', '1', '135454');
INSERT INTO `goods_man` VALUES ('9', '666', '甲方', 'http://localhost:8080/warehouse/header/4.png', '1', '133345');

-- ----------------------------
-- Table structure for goods_quality_record
-- ----------------------------
DROP TABLE IF EXISTS `goods_quality_record`;
CREATE TABLE `goods_quality_record` (
  `goods_id` int NOT NULL,
  `goods_quality_in_result` int DEFAULT NULL,
  `goods_quality_in_time` datetime DEFAULT NULL,
  `quality_man_in_id` int DEFAULT NULL,
  `goods_quality_out_result` int DEFAULT NULL,
  `goods_quality_out_time` datetime DEFAULT NULL,
  `quality_man_out_id` int DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `gm_id_con` (`quality_man_in_id`) USING BTREE,
  KEY `gm_id_con3` (`quality_man_out_id`),
  CONSTRAINT `g_id_con` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `gm_id_con` FOREIGN KEY (`quality_man_in_id`) REFERENCES `quality_man` (`quality_man_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `gm_id_con3` FOREIGN KEY (`quality_man_out_id`) REFERENCES `quality_man` (`quality_man_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_quality_record
-- ----------------------------
INSERT INTO `goods_quality_record` VALUES ('1', '1', '2021-03-31 17:44:05', '1', null, null, null);
INSERT INTO `goods_quality_record` VALUES ('2', '1', '2021-04-01 10:27:16', '1', null, null, null);
INSERT INTO `goods_quality_record` VALUES ('3', '1', '2021-03-30 17:19:31', '1', '1', '2021-03-30 17:25:56', '1');
INSERT INTO `goods_quality_record` VALUES ('4', '1', '2021-03-30 17:33:31', '1', null, null, null);
INSERT INTO `goods_quality_record` VALUES ('6', '1', '2021-03-30 17:33:40', '1', null, null, null);
INSERT INTO `goods_quality_record` VALUES ('9', '1', '2021-03-31 17:44:11', '1', null, null, null);
INSERT INTO `goods_quality_record` VALUES ('21', '1', '2021-03-31 17:44:31', '1', null, null, null);
INSERT INTO `goods_quality_record` VALUES ('22', '1', '2021-03-31 17:53:35', '1', '1', '2021-04-02 15:55:05', '1');
INSERT INTO `goods_quality_record` VALUES ('23', '1', '2021-03-31 17:53:38', '1', null, null, null);
INSERT INTO `goods_quality_record` VALUES ('34', '1', '2021-04-07 12:23:50', '1', '1', '2021-04-07 12:24:42', '1');
INSERT INTO `goods_quality_record` VALUES ('36', '1', '2021-04-12 17:26:02', '1', '1', '2021-04-12 17:27:38', '1');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `message_id` int NOT NULL AUTO_INCREMENT,
  `from_id` int NOT NULL,
  `from_role` int NOT NULL,
  `to_id` int NOT NULL,
  `to_role` int NOT NULL,
  `conversation_id` varchar(45) NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int NOT NULL DEFAULT '0' COMMENT '0-未读;1-已读;2-删除;',
  `create_time` timestamp NOT NULL,
  PRIMARY KEY (`message_id`),
  KEY `index_from_id` (`from_id`),
  KEY `index_to_id` (`to_id`),
  KEY `index_conversation_id` (`conversation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=379 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '1', '1', '1', '0', '0_1_1_1', '你好', '1', '2021-04-06 16:30:57');
INSERT INTO `message` VALUES ('2', '1', '0', '1', '1', '0_1_1_1', 'hello', '1', '2021-04-06 16:50:29');
INSERT INTO `message` VALUES ('3', '1', '1', '1', '2', '1_2_1_1', 'www', '0', '2021-04-06 21:55:41');
INSERT INTO `message` VALUES ('365', '1', '0', '1', '1', '0_1_1_1', '22222', '1', '2021-04-06 20:24:51');
INSERT INTO `message` VALUES ('366', '1', '1', '1', '0', '0_1_1_1', '今天天气真好', '1', '2021-04-06 20:26:45');
INSERT INTO `message` VALUES ('367', '1', '1', '1', '0', '0_1_1_1', '你在干嘛', '1', '2021-04-06 20:26:58');
INSERT INTO `message` VALUES ('368', '1', '0', '1', '1', '0_1_1_1', '我在敲代码', '1', '2021-04-06 20:27:51');
INSERT INTO `message` VALUES ('369', '1', '0', '1', '1', '0_1_1_1', 'ddididi', '1', '2021-04-06 20:31:18');
INSERT INTO `message` VALUES ('370', '1', '0', '1', '1', '0_1_1_1', '哈哈哈', '1', '2021-04-06 22:34:48');
INSERT INTO `message` VALUES ('371', '1', '0', '1', '1', '0_1_1_1', '111', '1', '2021-04-06 22:47:24');
INSERT INTO `message` VALUES ('372', '1', '0', '1', '1', '0_1_1_1', 'hhhh', '1', '2021-04-06 22:47:33');
INSERT INTO `message` VALUES ('373', '1', '0', '1', '3', '0_3_1_1', '你好呀', '0', '2021-04-06 23:05:07');
INSERT INTO `message` VALUES ('374', '1', '0', '1', '3', '0_3_1_1', '我是商品运输员', '0', '2021-04-06 23:07:24');
INSERT INTO `message` VALUES ('375', '1', '0', '1', '2', '0_2_1_1', '2222', '0', '2021-04-06 23:17:14');
INSERT INTO `message` VALUES ('376', '1', '0', '1', '2', '0_2_1_1', '21', '0', '2021-04-06 23:17:22');
INSERT INTO `message` VALUES ('377', '1', '0', '1', '2', '0_2_1_1', '哇哇哇哇', '0', '2021-04-06 23:21:27');
INSERT INTO `message` VALUES ('378', '1', '1', '1', '2', '1_2_1_1', '今天星期几呀', '0', '2021-04-07 12:19:58');

-- ----------------------------
-- Table structure for quality_man
-- ----------------------------
DROP TABLE IF EXISTS `quality_man`;
CREATE TABLE `quality_man` (
  `quality_man_id` int NOT NULL AUTO_INCREMENT,
  `quality_man_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `quality_man_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `quality_man_header` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `quality_man_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `quality_man_tel` varchar(255) NOT NULL,
  PRIMARY KEY (`quality_man_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quality_man
-- ----------------------------
INSERT INTO `quality_man` VALUES ('1', '1', '测试质检员', 'http://localhost:8080/warehouse/header/f3bbafb3203742109e55afe834fc2e83.jpg', '1', '1545451');
INSERT INTO `quality_man` VALUES ('2', '123', '中国', 'http://localhost:8080/warehouse/header/4.png', '1', '1515532');
INSERT INTO `quality_man` VALUES ('3', '123', '李', 'http://localhost:8080/warehouse/header/2.png', '1', '1144454');
INSERT INTO `quality_man` VALUES ('4', '1', '小王', 'http://localhost:8080/warehouse/header/4.png', '1', '1121585');
INSERT INTO `quality_man` VALUES ('5', '1', '小李', 'http://localhost:8080/warehouse/header/4.png', '1', '111335');
INSERT INTO `quality_man` VALUES ('6', '123', 'xx', 'http://localhost:8080/warehouse/header/6.png', '1', '111112');

-- ----------------------------
-- Table structure for system_manager
-- ----------------------------
DROP TABLE IF EXISTS `system_manager`;
CREATE TABLE `system_manager` (
  `system_manager_id` int NOT NULL AUTO_INCREMENT,
  `system_manager_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `system_manager_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `system_manager_header` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `system_manager_status` int NOT NULL,
  `system_manager_tel` varchar(255) NOT NULL,
  PRIMARY KEY (`system_manager_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_manager
-- ----------------------------
INSERT INTO `system_manager` VALUES ('1', '1', '系统管理员', 'http://localhost:8080/warehouse/header/78ca8c7695e549c29e19b429396a2716.png', '1', '18632251');

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse` (
  `warehouse_id` int NOT NULL AUTO_INCREMENT,
  `usable_capacity` int NOT NULL,
  `total_capacity` int NOT NULL,
  `system_manager_id` int DEFAULT NULL,
  PRIMARY KEY (`warehouse_id`),
  KEY `sm_id_con` (`system_manager_id`),
  CONSTRAINT `sm_id_con` FOREIGN KEY (`system_manager_id`) REFERENCES `system_manager` (`system_manager_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES ('1', '998', '1000', '1');

-- ----------------------------
-- Table structure for warehouse_manager
-- ----------------------------
DROP TABLE IF EXISTS `warehouse_manager`;
CREATE TABLE `warehouse_manager` (
  `warehouse_manager_id` int NOT NULL AUTO_INCREMENT,
  `warehouse_manager_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `warehouse_manager_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `warehouse_manager_header` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `warehouse_manager_status` int NOT NULL,
  `warehouse_manager_tel` varchar(255) NOT NULL,
  PRIMARY KEY (`warehouse_manager_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of warehouse_manager
-- ----------------------------
INSERT INTO `warehouse_manager` VALUES ('1', '1', '测试仓库管理员', 'http://localhost:8080/warehouse/header/7a804dafa810458a8d7ab5c7d368c235.jpg', '1', '15151512');
INSERT INTO `warehouse_manager` VALUES ('2', '1', 'lili', 'http://localhost:8080/warehouse/header/5.png', '1', '15188825');
