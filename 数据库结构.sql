/*
Navicat MySQL Data Transfer

Source Server         : 【生】54.169.251.215
Source Server Version : 50721
Source Host           : 54.169.251.215:3306
Source Database       : sdc

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-04-24 16:23:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `login`
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_no` varchar(512) DEFAULT NULL,
  `login_pass` varchar(512) DEFAULT NULL,
  `login_name` varchar(512) DEFAULT NULL,
  `cdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `login_type` varchar(512) DEFAULT '1' COMMENT '1 订单管理者 2 商品管理 3 订单+商品管理 99 屌炸天',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `sdc_chn`
-- ----------------------------
DROP TABLE IF EXISTS `sdc_chn`;
CREATE TABLE `sdc_chn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chnname` varchar(100) DEFAULT NULL COMMENT '渠道名称',
  `chncode` varchar(100) DEFAULT NULL COMMENT '渠道编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `sdc_collect`
-- ----------------------------
DROP TABLE IF EXISTS `sdc_collect`;
CREATE TABLE `sdc_collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proinitperson` varchar(200) DEFAULT NULL COMMENT '项目发启人',
  `collectname` varchar(200) DEFAULT NULL COMMENT '募集名称',
  `quantityed` varchar(100) DEFAULT NULL COMMENT '已募数量',
  `quantityre` varchar(100) DEFAULT NULL COMMENT '剩余数量',
  `quantitytotal` varchar(100) DEFAULT NULL COMMENT '总募集数量',
  `imgurl` varchar(500) DEFAULT NULL COMMENT '主图片',
  `content` varchar(2000) DEFAULT NULL COMMENT '内容',
  `cdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `iscurrent` varchar(10) DEFAULT '0' COMMENT '是否当前正在募集的项目（0：不是；1：是）',
  `ratioid` varchar(100) DEFAULT NULL COMMENT '比例id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `sdc_ratio`
-- ----------------------------
DROP TABLE IF EXISTS `sdc_ratio`;
CREATE TABLE `sdc_ratio` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `ratioVal` varchar(50) NOT NULL COMMENT '比例值',
  `isuse` varchar(20) DEFAULT '0' COMMENT '是否当前在用（0：不在用；1：在用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `trans_collect_sdcsend`
-- ----------------------------
DROP TABLE IF EXISTS `trans_collect_sdcsend`;
CREATE TABLE `trans_collect_sdcsend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sdclockcontractAdd` varchar(100) DEFAULT '0x445d7bb16fe3aada6a89673aa15919306af07c9f' COMMENT 'sdc锁仓合约地址',
  `receipthash` varchar(100) DEFAULT NULL COMMENT '募集回执hash',
  `lockNum` varchar(255) DEFAULT NULL COMMENT '锁仓期数',
  `useraddress` varchar(100) DEFAULT NULL COMMENT '用户账户地址',
  `locktime` varchar(100) DEFAULT NULL COMMENT '锁仓时间（秒）',
  `value` varchar(100) DEFAULT NULL COMMENT 'SDC锁仓数量',
  `proStatus` varchar(20) DEFAULT '0' COMMENT '处理状态（0：未处理；1：已处理）',
  `txStatus` varchar(20) DEFAULT '3' COMMENT '交易状态（0：成功；1：失败；2：未核验）',
  `lockhash` varchar(100) DEFAULT NULL COMMENT '锁仓交易hash',
  `txdata` datetime DEFAULT NULL COMMENT '交易时间',
  `checkdata` datetime DEFAULT NULL COMMENT '核验时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `trans_main`
-- ----------------------------
DROP TABLE IF EXISTS `trans_main`;
CREATE TABLE `trans_main` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blockNumber` varchar(50) DEFAULT NULL,
  `timeStamp` varchar(50) DEFAULT NULL,
  `hash` varchar(100) DEFAULT NULL,
  `nonce` varchar(50) DEFAULT NULL,
  `blockHash` varchar(100) DEFAULT NULL,
  `transactionIndex` varchar(50) DEFAULT NULL,
  `from` varchar(100) DEFAULT NULL,
  `to` varchar(100) DEFAULT NULL,
  `value` varchar(200) DEFAULT NULL,
  `gas` varchar(100) DEFAULT NULL,
  `gasPrice` varchar(100) DEFAULT NULL,
  `isError` varchar(20) DEFAULT NULL,
  `txreceipt_status` varchar(20) DEFAULT NULL,
  `input` text,
  `contractAddress` varchar(100) DEFAULT NULL,
  `cumulativeGasUsed` varchar(100) DEFAULT NULL,
  `gasUsed` varchar(100) DEFAULT NULL,
  `confirmations` varchar(100) DEFAULT NULL,
  `iscollect` varchar(20) DEFAULT '1' COMMENT '是否募集交易（0：不是；1：是）',
  `collectstatus` varchar(20) DEFAULT '1' COMMENT '募集状态（1：用户已转账；2：已向用户转账）',
  `type` varchar(20) DEFAULT '1' COMMENT '类型（1：未处理；2：已处理；3：不处理）',
  `sdcquantity` varchar(100) DEFAULT NULL COMMENT 'sdc募集数量',
  `unlocktime` timestamp NULL DEFAULT NULL COMMENT 'sdc解锁时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1330 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `trans_record`
-- ----------------------------
DROP TABLE IF EXISTS `trans_record`;
CREATE TABLE `trans_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ratioid` varchar(100) DEFAULT NULL COMMENT '兑换比率id',
  `txhash` varchar(100) DEFAULT NULL,
  `age` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `from` varchar(100) DEFAULT NULL,
  `from1` varchar(100) DEFAULT NULL,
  `to` varchar(100) DEFAULT NULL,
  `to1` varchar(100) DEFAULT NULL,
  `currency` varchar(20) DEFAULT NULL COMMENT '待验证币种',
  `currency1` varchar(20) DEFAULT NULL COMMENT '已验证币种',
  `quantity` varchar(100) DEFAULT NULL COMMENT '待验证交易数量',
  `quantity1` varchar(100) DEFAULT NULL COMMENT '已验证交易数量',
  `txstatus` varchar(20) DEFAULT NULL COMMENT '交易状态（0：Ok；1：Rejected/Cancelled；2：校验异常；3：api未返回结果）',
  `txtype` varchar(20) DEFAULT NULL COMMENT '交易类型（1：转账；2：募集；3：募集回执）',
  `collectstatus` varchar(20) DEFAULT '0' COMMENT '募集状态（0：非募集；1：用户已转账；2：已向用户转账）',
  `userhash` varchar(100) DEFAULT NULL COMMENT '用户转ETH的txhash',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `Invitationcode` varchar(20) DEFAULT NULL COMMENT '邀请码',
  `targetaddress` varchar(100) DEFAULT NULL COMMENT '募集目标地址（向特定地址转账）',
  `lockhash` varchar(3000) DEFAULT NULL COMMENT '调用锁仓合约时返回的hash',
  `sdclockcontractAdd` varchar(200) DEFAULT '0x445d7bb16fe3aada6a89673aa15919306af07c9f' COMMENT '锁仓合约地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8;
