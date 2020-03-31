-- -----------------------------------------------------
-- 用户表
-- -----------------------------------------------------
CREATE TABLE `om_user` (
  `user_id` INT NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `user_name` VARCHAR(50) NULL COMMENT '员工姓名',
  `password` VARCHAR(100) NULL COMMENT '登录密码',
  `mobile` VARCHAR(20) NULL COMMENT '手机号',
  `status` TINYINT NOT NULL COMMENT '用户状态：1、启用，2、禁用\n',
  `remark` VARCHAR(500) NULL DEFAULT '' COMMENT '备注',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`))
   ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '用户表';
  
  
  CREATE TABLE `user_info` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `user_name` VARCHAR(50) NULL COMMENT '昵称',
  `password` VARCHAR(100) NULL COMMENT '登录密码',
  `mobile` VARCHAR(20) NULL COMMENT '手机号',
  `status` TINYINT DEFAULT 1 COMMENT '用户状态：0、禁言，1、发表',
  `score` int(20) NULL DEFAULT 0 COMMENT '积分',
  `grade` TINYINT DEFAULT 0 COMMENT '用户状态：0、普通用户，1、发布者',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`))
   ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '用户端用户表';


-- -----------------------------------------------------
-- 角色表
-- -----------------------------------------------------
CREATE TABLE  `om_role` (
  `role_id` INT NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_code` VARCHAR(20) NOT NULL COMMENT '角色code',
  `role_name` VARCHAR(30) NOT NULL COMMENT '角色名称',
  `remark` VARCHAR(500) NOT NULL DEFAULT '' COMMENT '备注',
  `type` TINYINT NOT NULL COMMENT '1、系统管理员\n2、一般运营角色',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '角色表';


-- -----------------------------------------------------
-- 权限表
-- -----------------------------------------------------
CREATE TABLE  `om_permis` (
  `permis_id` INT NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `parent_permis_id` INT NOT NULL,
  `permis_name` VARCHAR(80) NOT NULL COMMENT '权限名称',
  `menu_permis_flag` TINYINT NOT NULL COMMENT '1:菜单权限\n2:功能权限',
  `permis_code` VARCHAR(100) NOT NULL COMMENT '权限code',
  `remark` VARCHAR(500) NOT NULL DEFAULT '' COMMENT '备注',
  `permis_order` INT NOT NULL DEFAULT 0 COMMENT '权限顺序, 通常一级权限才需要设置',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`permis_id`),
  UNIQUE INDEX `permis_code_UNIQUE` (`permis_code` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '权限表';


-- -----------------------------------------------------
-- 角色-权限表
-- -----------------------------------------------------
CREATE TABLE  `role_permis` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` INT NOT NULL COMMENT '角色id',
  `permis_id` INT NOT NULL COMMENT '权限id',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_permis_UNIQUE` (`role_id`, `permis_id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '角色权限关联表';


-- -----------------------------------------------------
-- 用户角色关联表
-- -----------------------------------------------------
CREATE TABLE  `user_role` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` INT NOT NULL COMMENT '角色id',
  `user_id` INT NOT NULL COMMENT '用户id',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_user_UNIQUE`  (`role_id`, `user_id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '用户角色关联表';


-- -----------------------------------------------------
-- Table `om_menu`
-- -----------------------------------------------------
CREATE TABLE  `om_menu` (
  `menu_id` INT NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `parent_id` INT NOT NULL COMMENT '上一级菜单id',
  `menu_text` VARCHAR(256) NOT NULL COMMENT '菜单文本',
  `menu_url` VARCHAR(256) NOT NULL COMMENT '菜单url',
  `permis_id` INT NOT NULL COMMENT '权限id',
  `enable_flag` TINYINT NOT NULL DEFAULT 1 COMMENT '启用标示,1、启用，2、禁用',
  `menu_order` INT NOT NULL DEFAULT 0 COMMENT '菜单顺序. 通常一级菜单才需要设置',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '菜单表';
  
-- -----------------------------------------------------
-- 业务表
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) DEFAULT NULL,
  `origin_place` varchar(255) DEFAULT NULL,
  `product_describe` varchar(255) DEFAULT NULL,
  `create_time` DATETIME NOT NULL,
	`planting_time` DATETIME NOT NULL,
	`harvest_time` DATETIME NOT NULL,
  `update_time` DATETIME NOT NULL,
	`describe` varchar(255) DEFAULT NULL,
	`user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `goods_info`;
CREATE TABLE `goods_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(255) DEFAULT NULL,
  `office_name` varchar(255) DEFAULT NULL,
  `goods_describe` varchar(255) DEFAULT NULL,
  `create_time` DATETIME NOT NULL,
	`manufacture_time` DATETIME NOT NULL,
  `update_time` DATETIME NOT NULL,
	`quality_period` varchar(255) DEFAULT NULL,
  `product_ids` varchar(255) DEFAULT NULL,
	`user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sellers_info`;
CREATE TABLE `sellers_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sellers_name` varchar(255) DEFAULT NULL,
  `create_time` DATETIME NOT NULL,
  `update_time` DATETIME NOT NULL,
  `goods_id` varchar(255) DEFAULT NULL,
	`user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `user_name` VARCHAR(50) NULL COMMENT '昵称',
  `password` VARCHAR(100) NULL COMMENT '登录密码',
  `mobile` VARCHAR(20) NULL COMMENT '手机号',
  `status` TINYINT DEFAULT 1 COMMENT '用户状态：0、禁言，1、发表',
  `score` int(20) NULL DEFAULT 0 COMMENT '积分',
  `grade` TINYINT DEFAULT 0 COMMENT '用户状态：0、普通用户，1、发布者',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL COMMENT '修改时间',
	`audience` int(2) DEFAULT 0,
 `audience_reason` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
   ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '用户表';
  
 DROP TABLE IF EXISTS `news_category`;
CREATE TABLE `news_category` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `category_name` VARCHAR(50) NULL COMMENT '分类',
  `code` VARCHAR(50) NULL COMMENT '编号',
  `status` TINYINT DEFAULT 0 COMMENT '删除：0、未删除，1、删除',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` DATETIME NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`))
   ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '新闻分类表';
  
DROP TABLE IF EXISTS `news_comment`;
CREATE TABLE `news_comment` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `comment` mediumtext  NULL COMMENT '评论',
  `news_id` int(10) NULL COMMENT '新闻id',
  `status` TINYINT DEFAULT 0 COMMENT '删除：0、未删除，1、删除',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `user_id` INT(10) NULL COMMENT '修改时间',
  `report` INT(2) DEFAULT 0 COMMENT '举报状态：0、未举报，1举报',
  PRIMARY KEY (`id`))
   ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '新闻评论表';
  
 DROP TABLE IF EXISTS `score_rules`;
CREATE TABLE `score_rules` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `rule_value` VARCHAR (500) NULL COMMENT '规则',
  `group_value` int (10) NULL COMMENT '分组类型',
  `update_time` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`))
   ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '积分规则表';
INSERT INTO `score_rules` (`id`, `rule_value`, `group_value`, `update_time`) VALUES ('1', '{\"deductionScore\":\"2\",\"upgrade\":\"150\",\"orignalScore\":\"100\",\"days\":\"2\",\"scoles\":\"2\"}', NULL, '2020-03-20 22:23:31');

DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键id',
 `catogry_id` INT(10) NULL,
`news_author` VARCHAR(50) DEFAULT NULL,
`click_rate` INT(10) NULL,
  `news_content` text  NULL COMMENT '规则',
  `news_title` LONGTEXT  NULL COMMENT '分组类型',
  `update_time` DATETIME NOT NULL COMMENT '修改时间',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `news_sort` INT(10) NULL,
  PRIMARY KEY (`id`))
   ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '新闻表';
  
DROP TABLE IF EXISTS `news_collection`;
CREATE TABLE `news_collection` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `news_id` int(10) NULL COMMENT '新闻id',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `user_id` INT(10) NULL COMMENT '用户id',
  PRIMARY KEY (`id`))
   ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '新闻收藏表';
  
  DROP TABLE IF EXISTS `invitation`;
CREATE TABLE `invitation` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `invitation_content` text  NULL COMMENT '规则',
  `update_time` DATETIME NOT NULL COMMENT '修改时间',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `user_id` INT(10) NULL,
  PRIMARY KEY (`id`))
   ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '新闻表';

