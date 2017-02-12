-- ----------------------------
-- 用户
-- ----------------------------
INSERT INTO `user`(username, password, nikename) VALUES ('001', '1234', '客户');
INSERT INTO `user`(username, password, nikename) VALUES ('002', '1234', '管理业务流程');
INSERT INTO `user`(username, password, nikename) VALUES ('003', '1234', '管理业务维护');
INSERT INTO `user`(username, password, nikename) VALUES ('004', '1234', '管理业务交易');
INSERT INTO `user`(username, password, nikename) VALUES ('005', '1234', '管理账号信息');

-- ----------------------------
-- 角色
-- ----------------------------
INSERT INTO `role` VALUES (null, 'custom', '客户');
INSERT INTO `role` VALUES (null, 'admin_process', '管理业务流程');
INSERT INTO `role` VALUES (null, 'admin_maintain', '管理业务维护');
INSERT INTO `role` VALUES (null, 'admin__trade', '管理业务交易');
INSERT INTO `role` VALUES (null, 'admin_user', '管理账号信息');

-- ----------------------------
-- 角色表
-- ----------------------------
INSERT INTO `user_role` VALUES (null, '1', '1');
INSERT INTO `user_role` VALUES (null, '2', '2');
INSERT INTO `user_role` VALUES (null, '3', '3');
INSERT INTO `user_role` VALUES (null, '4', '4');
INSERT INTO `user_role` VALUES (null, '5', '5');