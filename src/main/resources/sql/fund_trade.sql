-- ----------------------------
-- 用户
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL COMMENT '账号',
  `password` varchar(60) NOT NULL COMMENT '密码',
  `nikename` varchar(12) DEFAULT NULL COMMENT '昵称',
  `type` int(1) NOT NULL DEFAULT 0 COMMENT '类型【0：客户，1：管理员】',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态【保留字段】',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 管理员详细信息
-- ----------------------------
DROP TABLE IF EXISTS `admininfo`;
CREATE TABLE `admininfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL COMMENT '账号',
  `vc_netno` varchar(9) DEFAULT NULL COMMENT '网点编号',
  `mobile` varchar(15) DEFAULT NULL COMMENT '手机',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index_admininfo` (`username`),
  CONSTRAINT `fk_admininfo_user_id` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 网点
-- ----------------------------
DROP TABLE IF EXISTS `netstation`;
CREATE TABLE `netstation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_netno` varchar(9) NOT NULL COMMENT '编号',
  `vc_netname` varchar(64) DEFAULT NULL COMMENT '名称',
  `vc_address` varchar(60) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sale_inetstation` (`vc_netno`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 角色
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(200) NOT NULL COMMENT '代码',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index_role` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 用户-角色
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户',
  `role_id` int(11) NOT NULL COMMENT '角色',
  PRIMARY KEY (`id`),
  KEY `fk_user_role_user_id` (`user_id`),
  KEY `fk_user_role_role_id` (`role_id`),
  CONSTRAINT `fk_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 流程控制表
-- ----------------------------
DROP TABLE IF EXISTS `deal_process`;
CREATE TABLE `deal_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_procedurcode` varchar(40) NOT NULL COMMENT '代码',
  `vc_name` varchar(40) NOT NULL COMMENT '名称',
  `l_state` int(1) DEFAULT 0 COMMENT '状态【0：未执行，1：执行中，2：执行完毕】',
  PRIMARY KEY (`id`),
  KEY `fk_deal_process_id` (`vc_procedurcode`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 工作日表
-- ----------------------------
DROP TABLE IF EXISTS `day`;
CREATE TABLE `day` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_date` varchar(10) NOT NULL DEFAULT '' COMMENT '日期',
  `l_workflag` int(1) DEFAULT 0 COMMENT '状态【0：工作日，1：休息日】',
  PRIMARY KEY (`id`,`vc_date`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Ta表
-- ----------------------------
DROP TABLE IF EXISTS `ta`;
CREATE TABLE `ta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_tacode` varchar(8) NOT NULL COMMENT '编号',
  `vc_taname` varchar(64) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sale_itainfo` (`vc_tacode`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;