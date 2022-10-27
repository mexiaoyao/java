/*
日志：
1:2022.10.13 创建此文件
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
  `shares_date` date NULL DEFAULT NULL COMMENT '记录时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;