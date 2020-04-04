/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : admin

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2020-03-31 21:51:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods_info
-- ----------------------------
DROP TABLE IF EXISTS `goods_info`;
CREATE TABLE `goods_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(255) DEFAULT NULL,
  `office_name` varchar(255) DEFAULT NULL,
  `goods_describe` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `manufacture_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `quality_period` varchar(255) DEFAULT NULL,
  `product_ids` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_info
-- ----------------------------
INSERT INTO `goods_info` VALUES ('2', '122', '11', '111', '2020-03-13 22:54:40', '2020-05-03 00:00:00', '2020-03-14 18:00:14', '11', '', '1');
INSERT INTO `goods_info` VALUES ('3', '122', '11', '111', '2020-03-13 22:55:02', '2020-05-03 00:00:00', '2020-03-14 18:20:11', '11', '5,6,7', '1');

-- ----------------------------
-- Table structure for invitation
-- ----------------------------
DROP TABLE IF EXISTS `invitation`;
CREATE TABLE `invitation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `invitation_content` text COMMENT '规则',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `user_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='新闻表';

-- ----------------------------
-- Records of invitation
-- ----------------------------
INSERT INTO `invitation` VALUES ('1', 'uyuyu667', '2020-03-28 23:33:31', '2020-03-28 23:33:31', '2');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `catogry_id` int(10) DEFAULT NULL,
  `news_author` varchar(50) DEFAULT NULL,
  `click_rate` int(10) DEFAULT NULL,
  `news_content` text COMMENT '规则',
  `news_title` longtext COMMENT '分组类型',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `news_sort` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='新闻表';

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('2', '3', '4wewrewr', null, 'aaaadssfdfdfdfs', '123243', '2020-03-22 11:47:58', '2020-03-22 11:47:58', null);
INSERT INTO `news` VALUES ('3', '1', '环球网', null, '<div class=\"video-2017\" id=\"videoList0\" style=\"margin:0px auto;padding:0px;color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	<div class=\"video-info\" style=\"margin:0px;padding:20px 0px 26px;text-align:center;\">\n		<a href=\"https://k.sina.cn/article_6362632222_m17b3e101e03300nmv8.html?from=news&amp;subch=onews\" target=\"_blank\">塞尔维亚总理：打算为中塞友谊竖一座纪念碑</a>\n	</div>\n</div>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	3月27日，塞尔维亚总理布尔纳比奇在接受采访时称，在塞尔维亚发现首例新冠肺炎确诊病例后，中国是第一个向塞方提供帮助的国家。塞总理称，她确实打算在疫情结束后，“为中塞钢铁般的友谊竖一座纪念碑，这意味着我们与中国的伙伴关系有多么重要。”\n</p>', '塞尔维亚总理称打算为中塞两国友谊竖纪念碑', '2020-03-29 22:10:24', '2020-03-29 22:10:24', null);
INSERT INTO `news` VALUES ('4', '1', '央视', null, '<span style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\"> 习近平总书记29日在浙江考察调研。当天下午，他先后来到宁波舟山港穿山港区码头、北仑大碶高端汽配模具园区，了解港口和园区企业复工复产情况。</span>', '习近平在浙江考察调研', '2020-03-29 22:12:42', '2020-03-29 22:12:42', null);
INSERT INTO `news` VALUES ('5', '1', '《财经》杂志', null, '<span style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\"> 习近平总书记29日在浙江考察调研。当天下午，他先后来到宁波舟山港穿山港区码头、北仑大碶高端汽配模具园区，了解港口和园区企业复工复产情况。\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	原标题：全球抢购中国呼吸机：订单排到六月，元器件、物流制约产能\n</p>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	文 |《财经》记者&nbsp;&nbsp;张欣培&nbsp;&nbsp;\n</p>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	“现在我每天都接到很多购买呼吸机的电话，公司成立至今17年，这是第一次遇到这种情况。”在南京一家医疗器械公司上班的张雪告诉《财经》记者，前几天意大利贝加莫客户要购买呼吸机，希望能给他匀出几台就行，但是她真的无能为力。公司的产能已经达到最大化，一批订单正在急着交货。\n</p>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	贝加莫是意大利受到疫情影响最严重的城市。随着重症患者不断增多，在无特效药之下，呼吸机成为他们的救命机。\n</p>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	3月27日，英国首相约翰逊新冠病毒检测呈阳性。据悉，美国总统特朗普打电话慰问，约翰逊说的第一句话就是“我们需要呼吸机。”纽约州州长安德鲁·库默说：“关键是呼吸机、呼吸机、呼吸机。这是最迫切的需要”。他说，该州“已安排了在中国的人员去采购呼吸机”。\n</p>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	海外疫情的发展超出预期，医疗物资缺口大得惊人，救命呼吸机更是陷入全球性缺货。中国作为全球重要的呼吸机生产国，世界各国都来中国抢购呼吸机。\n</p>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	截至发稿，全球新冠肺炎确诊病例超66万例，累计死亡病例超3万例。根据最新数据，国外现存累计确诊已经超过58万例，现有确诊486048例，累计死亡27489例。其中，美国累计确诊病例达到了122666例。意大利累计确认70065例，死亡人数10023例，死亡率达到了14.31%，居于全球之首。\n</p>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	根据美国重症医学会估计，美国总共将有96万名患者由于感染新冠病毒而需要使用呼吸机，但美国只有大约20万台。在疫情造成死亡人数最多的意大利，呼吸机的严重短缺已经迫使医生忍痛放弃对一些患者的救治。\n</p>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	谊安医疗公司董事长助理李凯说：“世界上没有一个国家不想立即从中国买到呼吸机。我们有成千上万的订单在等着交付。问题是我们能够多快地生产出这些呼吸机。”\n</p>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	但即便中国工厂开足马力，也难以满足海外对呼吸机的需求。根据《财经》记者了解，一些大型工厂的订单早已排到六七月份，小型工厂的订单亦十分紧张。原材料的短缺以及物流的不畅都影响了呼吸机的生产速度。\n</p>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	在呼吸机缺口日益严重之下，各国开始鼓励企业转型生产呼吸机。\n</p>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	<span style=\"font-weight:700 !important;\"> 订单接不完&nbsp;排产到六月</span>\n</p>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	3月26日整个上午，刘明一直在到处寻找可以马上出现货的呼吸机厂家。“买家是西班牙政府，他们打算搭载第二天白云机场的航班运回国内。刚联系到一家可能有货的，但是准备合同、报关等材料，时间完全来不及。”刘明告诉《财经》记者。\n</p>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	最后协商的结果是，西班牙政府官员自己到工厂提货，其他所有手续工厂不用负责。但即便如此，他们还是没有拿到货。因为100台现货在前一天晚上已全部卖出。\n</p>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	根据英国媒体报道，意大利的呼吸机数量只能满足不到四分之一的需求。面对急剧增长的患者以及异常紧缺的医疗设备，他们正全力以赴在中国寻找呼吸机。\n</p>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	3月25日，西班牙对外银行向鱼跃医疗（002223.SZ）紧急采购了2000台无创呼吸机与400台医用制氧机；3月24日，谊安医疗第一批145台呼吸机已经开始启程飞往塞尔维亚；3月19日，纽约州州长安德鲁·库默在召开记者会时表示，纽约急需2万余台呼吸机，已经派人到中国采购。\n</p>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	“现在订单基本上都是出口，询问最多的是欧洲疫情严重的国家，比如意大利、西班牙。但是没办法，订单实在排不过来。”医疗器械经销商张军告诉《财经》记者。\n</p>\n<p style=\"color:#4D4F53;font-family:&quot;font-size:18px;background-color:#FFFFFF;\">\n	天眼查数据显示，目前国内在业、经营范围包括呼吸机的公司，共计50家。其中规模较大的有谊安医疗、迈瑞医疗（ 300760.SZ）、鱼跃医疗（002223.SZ）、航天长峰（600855）等。\n</p>\n</span>', '全球抢购中国呼吸机：订单排到六月 元器件物流制约产能', '2020-03-29 22:14:04', '2020-03-29 22:14:04', null);

-- ----------------------------
-- Table structure for news_category
-- ----------------------------
DROP TABLE IF EXISTS `news_category`;
CREATE TABLE `news_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `category_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `code` varchar(50) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT '1' COMMENT '删除：0、未删除，1、删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='新闻分类表';

-- ----------------------------
-- Records of news_category
-- ----------------------------
INSERT INTO `news_category` VALUES ('1', '军事', null, '1', '2020-03-17 22:40:19', '2020-03-17 22:40:23');
INSERT INTO `news_category` VALUES ('2', '悬疑1', null, null, '2020-03-17 22:59:19', '2020-03-17 23:06:43');
INSERT INTO `news_category` VALUES ('3', '悬疑', null, null, '2020-03-17 23:12:52', '2020-03-17 23:12:52');

-- ----------------------------
-- Table structure for news_collection
-- ----------------------------
DROP TABLE IF EXISTS `news_collection`;
CREATE TABLE `news_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `news_id` int(10) DEFAULT NULL COMMENT '新闻id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新闻收藏表';

-- ----------------------------
-- Records of news_collection
-- ----------------------------

-- ----------------------------
-- Table structure for news_comment
-- ----------------------------
DROP TABLE IF EXISTS `news_comment`;
CREATE TABLE `news_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `comment` mediumtext COMMENT '评论',
  `news_id` int(10) DEFAULT NULL COMMENT '新闻id',
  `status` tinyint(4) DEFAULT '0' COMMENT '删除：0、未删除，1、删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `user_id` int(10) DEFAULT NULL COMMENT '修改时间',
  `report` int(2) DEFAULT '0' COMMENT '举报状态：0、未举报，1举报',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新闻评论表';

-- ----------------------------
-- Records of news_comment
-- ----------------------------

-- ----------------------------
-- Table structure for om_menu
-- ----------------------------
DROP TABLE IF EXISTS `om_menu`;
CREATE TABLE `om_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `parent_id` int(11) NOT NULL COMMENT '上一级菜单id',
  `menu_text` varchar(256) NOT NULL COMMENT '菜单文本',
  `menu_url` varchar(256) NOT NULL COMMENT '菜单url',
  `permis_id` int(11) NOT NULL COMMENT '权限id',
  `enable_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '启用标示,1、启用，2、禁用',
  `menu_order` int(11) NOT NULL DEFAULT '0' COMMENT '菜单顺序. 通常一级菜单才需要设置',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of om_menu
-- ----------------------------

-- ----------------------------
-- Table structure for om_permis
-- ----------------------------
DROP TABLE IF EXISTS `om_permis`;
CREATE TABLE `om_permis` (
  `permis_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `parent_permis_id` int(11) NOT NULL,
  `permis_name` varchar(80) NOT NULL COMMENT '权限名称',
  `menu_permis_flag` tinyint(4) NOT NULL COMMENT '1:菜单权限\n2:功能权限',
  `permis_code` varchar(100) NOT NULL COMMENT '权限code',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `permis_order` int(11) NOT NULL DEFAULT '0' COMMENT '权限顺序, 通常一级权限才需要设置',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`permis_id`),
  UNIQUE KEY `permis_code_UNIQUE` (`permis_code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of om_permis
-- ----------------------------
INSERT INTO `om_permis` VALUES ('1', '0', '人事部', '1', 'personnel_department', '', '1', '2020-03-01 20:35:17', '2020-03-01 20:35:17');
INSERT INTO `om_permis` VALUES ('2', '0', '财务部', '1', 'accounting_department', '', '2', '2020-03-01 20:35:17', '2020-03-01 20:35:17');
INSERT INTO `om_permis` VALUES ('3', '0', '行政部', '1', 'administrative_department', '', '3', '2020-03-01 20:35:17', '2020-03-01 20:35:17');
INSERT INTO `om_permis` VALUES ('4', '0', '运维部', '1', 'operation_department', '', '4', '2020-03-01 20:35:17', '2020-03-01 20:35:17');
INSERT INTO `om_permis` VALUES ('5', '0', '测试部', '1', 'test_department', '', '5', '2020-03-01 20:35:17', '2020-03-01 20:35:17');
INSERT INTO `om_permis` VALUES ('6', '0', '市场部', '1', 'marketing_department', '', '6', '2020-03-01 20:35:17', '2020-03-01 20:35:17');

-- ----------------------------
-- Table structure for om_role
-- ----------------------------
DROP TABLE IF EXISTS `om_role`;
CREATE TABLE `om_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_code` varchar(20) NOT NULL COMMENT '角色code',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `type` tinyint(4) NOT NULL COMMENT '1、系统管理员\n2、一般运营角色',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of om_role
-- ----------------------------
INSERT INTO `om_role` VALUES ('1', 'super_admin_role', '超级管理员权限', '超级管理员权限', '1', '2020-03-01 20:35:17', '2020-03-01 20:35:17');
INSERT INTO `om_role` VALUES ('2', 'middle_admin_role', '中级管理员权限', '中级管理员权限', '1', '2020-03-01 20:35:17', '2020-03-01 20:35:17');
INSERT INTO `om_role` VALUES ('3', 'common_admin_role', '普通管理员权限', '普通管理员权限', '1', '2020-03-01 20:35:17', '2020-03-01 20:35:17');

-- ----------------------------
-- Table structure for om_user
-- ----------------------------
DROP TABLE IF EXISTS `om_user`;
CREATE TABLE `om_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `user_name` varchar(50) DEFAULT NULL COMMENT '员工姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '登录密码',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) NOT NULL COMMENT '用户状态：1、启用，2、禁用\n',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of om_user
-- ----------------------------
INSERT INTO `om_user` VALUES ('1', 'super_admin', 'e10adc3949ba59abbe56e057f20f883e', '10000000000', '1', '超级管理员', '2020-03-01 20:35:16', '2020-03-01 20:35:16');
INSERT INTO `om_user` VALUES ('2', 'middle_admin', 'e10adc3949ba59abbe56e057f20f883e', '20000000000', '1', '中级管理员', '2020-03-01 20:35:16', '2020-03-01 20:35:16');
INSERT INTO `om_user` VALUES ('3', 'common_admin', 'e10adc3949ba59abbe56e057f20f883e', '30000000000', '1', '普通管理员', '2020-03-01 20:35:17', '2020-03-01 20:35:17');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) DEFAULT NULL,
  `origin_place` varchar(255) DEFAULT NULL,
  `product_describe` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `planting_time` datetime NOT NULL,
  `harvest_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('2', '1111111', '222222', '5', '2020-03-10 21:38:08', '2020-10-03 00:00:00', '2022-04-03 00:00:00', '2020-03-10 23:11:51', '1');
INSERT INTO `product_info` VALUES ('3', '1111111', '222222', '22122', '2020-03-10 21:38:26', '2020-11-03 00:00:00', '2020-11-03 00:00:00', '2020-03-11 21:25:22', '1');
INSERT INTO `product_info` VALUES ('4', '大米', '黑龙江', '圆粒大米', '2020-03-11 22:32:04', '2020-03-03 00:00:00', '2022-04-03 00:00:00', '2020-03-11 22:32:04', '1');
INSERT INTO `product_info` VALUES ('5', '白菜', '黑龙江', '圆白菜', '2020-03-11 22:32:33', '2020-04-03 00:00:00', '2022-04-03 00:00:00', '2020-03-11 22:32:33', '1');
INSERT INTO `product_info` VALUES ('6', '冬瓜', '黑河', '白色', '2020-03-11 22:33:00', '2020-12-03 00:00:00', '2022-03-03 00:00:00', '2020-03-11 22:33:00', '1');
INSERT INTO `product_info` VALUES ('7', '小黄米', '邯郸', '无农药', '2020-03-11 22:33:28', '2020-05-02 00:00:00', '2022-03-03 00:00:00', '2020-03-11 22:33:28', '1');

-- ----------------------------
-- Table structure for role_permis
-- ----------------------------
DROP TABLE IF EXISTS `role_permis`;
CREATE TABLE `role_permis` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permis_id` int(11) NOT NULL COMMENT '权限id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_permis_UNIQUE` (`role_id`,`permis_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

-- ----------------------------
-- Records of role_permis
-- ----------------------------

-- ----------------------------
-- Table structure for score_rules
-- ----------------------------
DROP TABLE IF EXISTS `score_rules`;
CREATE TABLE `score_rules` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `rule_value` varchar(500) DEFAULT NULL COMMENT '规则',
  `group_value` int(10) DEFAULT NULL COMMENT '分组类型',
  `update_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='积分规则表';

-- ----------------------------
-- Records of score_rules
-- ----------------------------
INSERT INTO `score_rules` VALUES ('1', '{\"deductionScore\":\"2\",\"upgrade\":\"150\",\"orignalScore\":\"100\",\"days\":\"2\",\"scoles\":\"2\"}', null, '2020-03-22 11:56:37');

-- ----------------------------
-- Table structure for sellers_info
-- ----------------------------
DROP TABLE IF EXISTS `sellers_info`;
CREATE TABLE `sellers_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sellers_name` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `goods_id` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sellers_info
-- ----------------------------
INSERT INTO `sellers_info` VALUES ('1', '122', '2020-03-14 20:47:35', '2020-03-14 20:47:35', '3', '1');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `user_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `password` varchar(100) DEFAULT NULL COMMENT '登录密码',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT '1' COMMENT '用户状态：0、禁言，1、发表',
  `score` int(20) DEFAULT '0' COMMENT '积分',
  `grade` tinyint(4) DEFAULT '0' COMMENT '用户状态：0、普通用户，1、发布者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `audience` int(2) DEFAULT '0',
  `audience_reason` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '小明', '11111', '1111111', '0', '160', '1', '2020-03-17 21:21:21', '2020-03-17 22:08:28', '1', null);
INSERT INTO `user_info` VALUES ('2', 'degn', 'e10adc3949ba59abbe56e057f20f883e', '15227914451', '0', '100', '1', '2020-03-22 20:36:33', '2020-03-28 12:42:16', null, '11111');
INSERT INTO `user_info` VALUES ('28', null, '96e79218965eb72c92a549dd5a330112', '15227914452', '0', '100', '0', '2020-03-22 22:34:41', '2020-03-22 22:34:41', null, null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_user_UNIQUE` (`role_id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1', '2020-03-01 21:17:36', '2020-03-01 21:17:40');
ALTER TABLE invitation ADD COLUMN rate int (10) DEFAULT 0;
/*添加字段定时发送积分时间  2020年4月4日10:00:02*/
ALTER TABLE user_info ADD COLUMN send_score_time datetime DEFAULT NULL;
