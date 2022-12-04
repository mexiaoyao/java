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
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


SET FOREIGN_KEY_CHECKS = 1;