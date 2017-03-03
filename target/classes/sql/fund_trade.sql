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

-- ----------------------------
-- 产品信息表
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_productcode` varchar(6) DEFAULT NULL COMMENT '产品代码',
  `vc_productname` varchar(64) DEFAULT NULL COMMENT '产品名称',
  `vc_tacode` varchar(8) NOT NULL COMMENT 'ta编号',
  `c_productcategory` char(1) NOT NULL COMMENT '产品类别【0：公募，1：私募，2：专户，3：集合理财】',
  `c_productoperation` char(1) NOT NULL COMMENT '运作方式【0：开放式，1：封闭式，2：ETF,3：LOF】',
  `c_investregion` char(1) NOT NULL COMMENT '投资区域【0：境内，1：境外】',
  `c_investdirection` char(1) NOT NULL COMMENT '投资方向【0：股票，1：指数，2：货币，3：保本，4：混合，5：短期理财】',
  `c_moneytype` varchar(3) DEFAULT NULL COMMENT '币种【105：人民币，344：港币，840美元，978：欧元】',
  `en_manageratio` decimal(5,4) DEFAULT NULL COMMENT '管理费率',
  `c_chargetype` char(1) NOT NULL COMMENT '收费类型【0：前收费，1：后收费，2：水平收费】',
  `c_productstatus` char(1) NOT NULL COMMENT '产品状态【0：认购，1：申购，2：基金终止】',
  `c_productrisklevel` char(1) DEFAULT NULL COMMENT '风险水平【0：低，1：中，2：高】',
  `vc_bankacco` varchar(28) DEFAULT '' COMMENT 'Ta清算账户',
  `c_dividendmethod` char(1) DEFAULT NULL COMMENT '分红方式【0：红利再投，1：现金红利】',
  `vc_issuestartdate` varchar(10) DEFAULT NULL COMMENT '成立日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sale_iproductinfo` (`vc_productcode`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;