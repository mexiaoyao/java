/*
日志：
1:2022.10.13 创建此文件
https://www.cnblogs.com/lh1125314/p/15624072.html
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_finance_intro
-- des 股票简介表
-- ----------------------------
DROP TABLE IF EXISTS `t_finance_intro`;
CREATE TABLE `t_finance_intro`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'uuid32位',
  `index_type` tinyint(1) UNSIGNED NOT NULL COMMENT '类型[1:沪指SH,2:深指SZ]',
  `code_number` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '股票代码',
  `shares_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '股票名称',
  `shares_alise` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '股票别名',
  `shares_total_number` bigint(11) NULL DEFAULT NULL COMMENT '股票总股数',
  `shares_allow_total_number` bigint(11) NULL DEFAULT NULL COMMENT '可流动股票股数',
  `status` tinyint(1) UNSIGNED NOT NULL COMMENT '状态[1:上线,2:下线]',
  `load_time` datetime NULL DEFAULT NULL COMMENT '最后更新',
  `create_time` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for t_finance_update
-- des 股票更新表
-- ----------------------------
DROP TABLE IF EXISTS `t_finance_update`;
CREATE TABLE `t_finance_update`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'uuid32位',
  `shares_id` varchar(32) NOT NULL COMMENT '股票ID-对应t_finance_intro的id',
  `status` tinyint(1) UNSIGNED NOT NULL COMMENT '状态[1:成功,2:失败]',
  `create_time` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `fail_num` int(2) UNSIGNED ZEROFILL NOT NULL COMMENT '失败次数',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for t_shares_000000_sh
-- des 股票记录表
---股票振幅 股票振幅就是股票开盘后的当日最高价和最低价之间的差的绝对值与昨日收盘价的百分比 数值越大证明越活越
---股票换手率 如某只股票在一天内成交了2000万股，而该股票的流通股为1亿股，则该股票在这个月的换手率为20%。
-- ----------------------------

DROP TABLE IF EXISTS `t_shares_******_**`;
CREATE TABLE `t_shares_******_**`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'uuid32位',
  `shares_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '股票id对应t_finance_intro的id',
  `shares_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '股票名字',
  `code_number` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '股票编码',
  `today_open_price` double(6, 2) NULL DEFAULT NULL COMMENT '今日开盘价',
  `yesterday_close_price` double(6, 2) NULL DEFAULT NULL COMMENT '昨日收盘价',
  `today_max_price` double(6, 2) NULL DEFAULT NULL COMMENT '今日最高价',
  `today_min_price` double(6, 2) NULL DEFAULT NULL COMMENT '今日最低价',
  `today_average_price` double(6, 2) NULL DEFAULT NULL COMMENT '今日平均价',
  `deal_amount` bigint(11) NULL DEFAULT NULL COMMENT '成交金额',
  `source` tinyint(1) UNSIGNED NOT NULL COMMENT '数据来源[1:定时器,2:导入,3:手动添加]',
  `shares_total_number` bigint(11) NULL DEFAULT NULL COMMENT '股票总数',
  `shares_allow_total_number` bigint(11) NULL DEFAULT NULL COMMENT '可流动股票总数',
  `deal_shares_number` bigint(11) NULL DEFAULT NULL COMMENT '成交的股票数',
  `amplitude` double(6, 2) NULL DEFAULT NULL COMMENT '股票振幅',
  `turnover_rate` double(6, 2) NULL DEFAULT NULL COMMENT '股票换手率',
  `shares_date` date NULL DEFAULT NULL COMMENT '记录时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- Table t_grade_dict
-- des 年级字典
-- ----------------------------
DROP TABLE IF EXISTS `t_grade_dict`;
CREATE TABLE `t_grade_dict`  (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id',
    `type` tinyint(1) UNSIGNED NOT NULL DEFAULT 2 COMMENT '状态[1:考题类型,2:考题来源,3:所属年级]',
    `dict_name` varchar(32) NOT NULL COMMENT '字典名称',
    `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父级ID',
    `create_time` datetime NULL DEFAULT NULL COMMENT '添加时间',
    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    `create_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table t_grade_question
-- des 年级题目
-- ----------------------------
DROP TABLE IF EXISTS `t_grade_question`;
CREATE TABLE `t_grade_question`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'uuid32位（年级ID）',
  `dict_task_path` varchar(55) NOT NULL COMMENT '字典ID-试卷种类，对应t_grade_dict的id，例如：0,1,3,t_grade_dict中id',
  `dict_task_path_name` varchar(150) NOT NULL COMMENT '字典ID-试卷种类，对应t_grade_dict的id，例如：0,1,3,t_grade_dict中id',
  `dict_grade_path` varchar(55) NOT NULL COMMENT '字典ID-答题年级，对应t_grade_dict的id，例如：0,1,3,t_grade_dict中id',
  `dict_grade_path_name` varchar(150) NOT NULL COMMENT '字典ID-答题年级，对应t_grade_dict的id，例如：0,1,3,t_grade_dict中id',
  `dict_source_path` varchar(55) NOT NULL COMMENT '字典ID-答题来源，对应t_grade_dict的id，例如：0,1,3,t_grade_dict中id',
  `dict_source_path_name` varchar(150) NOT NULL COMMENT '字典ID-答题来源，对应t_grade_dict的id，例如：0,1,3,t_grade_dict中id',
  `dict_type_path` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典ID-答题类型，对应t_grade_dict的id，例如：0,1,3,t_grade_dict中dict_name',
  `dict_type_path_name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典ID-答题类型，对应t_grade_dict的id，例如：0,1,3,t_grade_dict中dict_name',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '介绍',
  `question` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题',
  `answers` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '答案',
  `answer_right` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '正确答案',
  `type` tinyint(1) UNSIGNED NOT NULL COMMENT '状态[1:根据拼音写汉字,2:看汉字写拼音]',
  `used_num` int(11) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '使用次数',
  `good_num` int(11) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '点赞数',
  `poor_num` int(11) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '踩数',
  `create_time` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `share_num` int(11) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '分享次数',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `create_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Records of t_grade_dict
-- ----------------------------
INSERT INTO `t_grade_dict` VALUES ('1', '1', '语文', '0', '2022-12-08 21:49:54', '2022-12-08 21:49:54', 'admin');
INSERT INTO `t_grade_dict` VALUES ('2', '1', '数学', '0', '2022-12-08 21:50:07', '2022-12-08 21:50:07', 'admin');
INSERT INTO `t_grade_dict` VALUES ('3', '1', '英语', '0', '2022-12-08 21:50:25', '2022-12-08 21:50:25', 'admin');
INSERT INTO `t_grade_dict` VALUES ('4', '2', '一年级', '0', '2022-12-08 21:50:38', '2022-12-08 21:50:38', 'admin');
INSERT INTO `t_grade_dict` VALUES ('5', '2', '二年级', '0', '2022-12-08 21:50:44', '2022-12-08 21:50:44', 'admin');
INSERT INTO `t_grade_dict` VALUES ('6', '2', '三年级', '0', '2022-12-08 21:50:50', '2022-12-08 21:50:50', 'admin');
INSERT INTO `t_grade_dict` VALUES ('7', '2', '四年级', '0', '2022-12-08 21:50:56', '2022-12-08 21:50:56', 'admin');
INSERT INTO `t_grade_dict` VALUES ('8', '2', '五年级', '0', '2022-12-08 21:51:03', '2022-12-08 21:51:03', 'admin');
INSERT INTO `t_grade_dict` VALUES ('9', '3', '苏教版', '0', '2022-12-08 21:52:00', '2022-12-08 21:52:00', 'admin');
INSERT INTO `t_grade_dict` VALUES ('10', '3', '人教版', '0', '2022-12-08 21:52:07', '2022-12-08 21:52:07', 'admin');
INSERT INTO `t_grade_dict` VALUES ('11', '3', '江苏省沭阳县北丁集乡中心小学', '0', '2022-12-08 21:52:47', '2022-12-08 21:52:47', 'admin');
INSERT INTO `t_grade_dict` VALUES ('12', '3', '一年级', '11', '2022-12-08 21:52:57', '2022-12-08 21:52:57', 'admin');
INSERT INTO `t_grade_dict` VALUES ('13', '3', '二年级', '11', '2022-12-08 21:53:06', '2022-12-08 21:53:06', 'admin');
INSERT INTO `t_grade_dict` VALUES ('14', '3', '三年级', '11', '2022-12-08 21:53:15', '2022-12-08 21:53:15', 'admin');
INSERT INTO `t_grade_dict` VALUES ('15', '3', '四年级', '11', '2022-12-08 21:53:23', '2022-12-08 21:53:23', 'admin');
INSERT INTO `t_grade_dict` VALUES ('16', '3', '期中考试', '15', '2022-12-08 21:53:52', '2022-12-08 21:53:52', 'admin');
INSERT INTO `t_grade_dict` VALUES ('17', '3', '期末考试', '15', '2022-12-08 21:54:00', '2022-12-08 21:54:00', 'admin');
INSERT INTO `t_grade_dict` VALUES ('18', '4', '一题', '0', '2022-12-08 21:54:36', '2022-12-08 21:54:36', 'admin');
INSERT INTO `t_grade_dict` VALUES ('19', '4', '二题', '0', '2022-12-08 21:54:47', '2022-12-08 21:54:47', 'admin');
INSERT INTO `t_grade_dict` VALUES ('20', '4', '三题', '0', '2022-12-08 21:55:00', '2022-12-08 21:55:00', 'admin');
INSERT INTO `t_grade_dict` VALUES ('21', '4', '四题', '0', '2022-12-08 21:55:21', '2022-12-08 21:55:21', 'admin');
INSERT INTO `t_grade_dict` VALUES ('22', '4', '五题', '0', '2022-12-08 21:55:33', '2022-12-08 21:55:33', 'admin');
INSERT INTO `t_grade_dict` VALUES ('23', '4', '填空题', '18', '2022-12-08 21:55:52', '2022-12-08 21:55:52', 'admin');



-- ----------------------------
-- Records of t_grade_question
-- ----------------------------
INSERT INTO `t_grade_question` VALUES ('0117830c2bd84b0bad327098ffd892c9', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, 'áng', '昂', '昂', '1', '00000000000', '00000000000', '00000000000', '2022-12-09 23:01:20', '2022-12-09 23:01:20', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('03edc16e6f6c44dd8da15cabefc314d9', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, 'yì', '屹', '屹', '1', '00000000000', '00000000000', '00000000000', '2022-12-09 23:01:20', '2022-12-09 23:01:20', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('288fa19b0ab340cea81b68121c5dd1f0', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, '浩', 'hào', 'hào', '2', '00000000000', '00000000000', '00000000000', '2022-12-09 23:02:42', '2022-12-09 23:02:42', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('338cd1d9fa9c4da0bba388af2f4817af', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, '霎', 'shà', 'shà', '2', '00000000000', '00000000000', '00000000000', '2022-12-09 23:02:42', '2022-12-09 23:02:42', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('38ade71db0574a8bb1ddadbb43849557', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, 'zhèn', '震', '震', '1', '00000000000', '00000000000', '00000000000', '2022-12-09 23:01:20', '2022-12-09 23:01:20', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('5196e95e2f8c4c49a889ab50d5e32f2f', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, 'guàn', '贯', '贯', '1', '00000000000', '00000000000', '00000000000', '2022-12-09 23:01:20', '2022-12-09 23:01:20', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('5475049e594f4bb58f10ffd2811a71e0', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, 'bó', '薄', '薄', '1', '00000000000', '00000000000', '00000000000', '2022-12-09 23:01:20', '2022-12-09 23:01:20', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('67e241905b1441f4aa7602fb9e64b6c0', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, 'fèi', '沸', '沸', '1', '00000000000', '00000000000', '00000000000', '2022-12-09 23:01:20', '2022-12-09 23:01:20', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('75245d80335c488088fd8fefe25293c0', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, '屹', 'yì', 'yì', '2', '00000000000', '00000000000', '00000000000', '2022-12-09 23:02:42', '2022-12-09 23:02:42', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('77d9fc90c8d4465ea37933b0e9c4e3e0', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, '鼎', 'dǐng', 'dǐng', '2', '00000000000', '00000000000', '00000000000', '2022-12-09 23:02:42', '2022-12-09 23:02:42', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('84a51f7a6c2f469b929099195d8cf321', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, '薄', 'bó', 'bó', '2', '00000000000', '00000000000', '00000000000', '2022-12-09 23:02:42', '2022-12-09 23:02:42', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('929b53aaf3134d2281fb42455630ee24', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, '余', 'yú', 'yú', '2', '00000000000', '00000000000', '00000000000', '2022-12-09 23:02:42', '2022-12-09 23:02:42', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('93d17f44cc3a4b3e84a5b0d241f66d51', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, 'dǐng', '鼎', '鼎', '1', '00000000000', '00000000000', '00000000000', '2022-12-09 23:01:20', '2022-12-09 23:01:20', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('9442adb89e5142dba0310cf104376478', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, '震', 'zhèn', 'zhèn', '2', '00000000000', '00000000000', '00000000000', '2022-12-09 23:02:42', '2022-12-09 23:02:42', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('9d5640a156044c93b0a2e5db609a833b', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, '沸', 'fèi', 'fèi', '2', '00000000000', '00000000000', '00000000000', '2022-12-09 23:02:42', '2022-12-09 23:02:42', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('9ec3a399228648a7a2f1a04f93b8efa2', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, '贯', 'guàn', 'guàn', '2', '00000000000', '00000000000', '00000000000', '2022-12-09 23:02:42', '2022-12-09 23:02:42', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('ad4d1e7936d6499e9c955c93396b945e', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, '昂', 'áng', 'áng', '2', '00000000000', '00000000000', '00000000000', '2022-12-09 23:02:42', '2022-12-09 23:02:42', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('afc4a6c2d9144cb889e3d06b1aed19b8', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, 'yán', '盐', '盐', '1', '00000000000', '00000000000', '00000000000', '2022-12-09 23:01:20', '2022-12-09 23:01:20', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('c508fab6cd6146ea89e5043084bca795', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, 'yú', '余', '余', '1', '00000000000', '00000000000', '00000000000', '2022-12-09 23:01:20', '2022-12-09 23:01:20', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('c9713e1e16824f3a8bfcd84035b718a1', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, '盐', 'yán', 'yán', '2', '00000000000', '00000000000', '00000000000', '2022-12-09 23:02:42', '2022-12-09 23:02:42', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('d1a307e2e3a049e7b331323c6ce70464', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, 'bēng', '崩', '崩', '1', '00000000000', '00000000000', '00000000000', '2022-12-09 23:01:20', '2022-12-09 23:01:20', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('e295a70f4eec45f18d60dba7767186dc', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, '崩', 'bēng', 'bēng', '2', '00000000000', '00000000000', '00000000000', '2022-12-09 23:02:42', '2022-12-09 23:02:42', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('e6e0241235e24763930819f59513b49e', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, 'shà', '霎', '霎', '1', '00000000000', '00000000000', '00000000000', '2022-12-09 23:01:20', '2022-12-09 23:01:20', '00000000000', null, 'admin', null);
INSERT INTO `t_grade_question` VALUES ('ea17e8f176f74cfcaecc1b72e7bd45a0', '1', '语文', '6', '三年级', '9,24,25', '苏教版/第一课/课外汉字', '18,23', '一题/填空题', null, 'hào', '浩', '浩', '1', '00000000000', '00000000000', '00000000000', '2022-12-09 23:01:20', '2022-12-09 23:01:20', '00000000000', null, 'admin', null);
