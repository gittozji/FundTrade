-- ----------------------------
-- 用户
-- ----------------------------
INSERT INTO `user`(username, password, nikename, type) VALUES ('0001', 'gdyb21LQTcIANtvYMT7QVQ==', '客户', 0);
INSERT INTO `user`(username, password, nikename, type) VALUES ('0002', 'gdyb21LQTcIANtvYMT7QVQ==', '管理业务流程', 1);
INSERT INTO `user`(username, password, nikename, type) VALUES ('0003', 'gdyb21LQTcIANtvYMT7QVQ==', '管理业务维护', 1);
INSERT INTO `user`(username, password, nikename, type) VALUES ('0004', 'gdyb21LQTcIANtvYMT7QVQ==', '管理业务交易', 1);
INSERT INTO `user`(username, password, nikename, type) VALUES ('0005', 'gdyb21LQTcIANtvYMT7QVQ==', '管理账号信息', 1);
INSERT INTO `user`(username, password, nikename, type) VALUES ('0006', 'gdyb21LQTcIANtvYMT7QVQ==', '客户', 0);

-- ----------------------------
-- 角色
-- ----------------------------
INSERT INTO `role` VALUES (null, 'custom', '客户');
INSERT INTO `role` VALUES (null, 'admin', '管理业务流程');
INSERT INTO `role` VALUES (null, 'admin_process', '管理业务流程');
INSERT INTO `role` VALUES (null, 'admin_maintain', '管理业务维护');
INSERT INTO `role` VALUES (null, 'admin_trade', '管理业务交易');
INSERT INTO `role` VALUES (null, 'admin_user', '管理账号信息');

-- ----------------------------
-- 角色表
-- ----------------------------
INSERT INTO `user_role` VALUES (null, '1', '1');
INSERT INTO `user_role` VALUES (null, '2', '2');
INSERT INTO `user_role` VALUES (null, '3', '2');
INSERT INTO `user_role` VALUES (null, '4', '2');
INSERT INTO `user_role` VALUES (null, '5', '2');
INSERT INTO `user_role` VALUES (null, '2', '3');
INSERT INTO `user_role` VALUES (null, '3', '4');
INSERT INTO `user_role` VALUES (null, '4', '5');
INSERT INTO `user_role` VALUES (null, '5', '6');
INSERT INTO `user_role` VALUES (null, '6', '1');

-- ----------------------------
-- 网点
-- ----------------------------
INSERT INTO `netstation`(vc_netno, vc_netname, vc_address) VALUES ('0001', '直销网点', '浙江杭州');

-- ----------------------------
-- 流程控制
-- ----------------------------
INSERT INTO `deal_process`(vc_procedurcode, vc_name) VALUES ('dayinit', '日初始化');
INSERT INTO `deal_process`(vc_procedurcode, vc_name) VALUES ('receivemarket', '接收行情');
INSERT INTO `deal_process`(vc_procedurcode, vc_name) VALUES ('starttuxedo', '启动交易');
INSERT INTO `deal_process`(vc_procedurcode, vc_name) VALUES ('downtuxedo', '停止柜台交易');
INSERT INTO `deal_process`(vc_procedurcode, vc_name) VALUES ('checkdata', '交易预处理');
INSERT INTO `deal_process`(vc_procedurcode, vc_name) VALUES ('exprequest', '导出申请数据');
INSERT INTO `deal_process`(vc_procedurcode, vc_name) VALUES ('importdata', '导入确认数据  ');
INSERT INTO `deal_process`(vc_procedurcode, vc_name) VALUES ('dealdata', '处理确认数据');
INSERT INTO `deal_process`(vc_procedurcode, vc_name) VALUES ('liqcarryover', '清算结转');
