DROP TABLE IF EXISTS t_supervisor;
CREATE TABLE `t_supervisor` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `supervisor_name` varchar(20) NOT NULL COMMENT '用户名',
  `supervisor_phone` varchar(20) DEFAULT NULL COMMENT '用户手机号',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `sex` enum('男','女') DEFAULT '男' COMMENT '性别' ,
  `birthday` date DEFAULT NULL COMMENT '生日',
  `email` varchar(50) DEFAULT NULL comment 'email',
  `head_image_path` varchar(100) DEFAULT NULL COMMENT '头像地址',
  `status` enum('NORMAL','LOCKED','DISABLE') DEFAULT 'NORMAL' COMMENT '正常/锁住/停用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间',
	`update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `index_supervisor_phone` (`supervisor_phone`),
  KEY `index_supervisor_name` (`supervisor_name`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS t_subject;
CREATE TABLE `t_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id' ,
  `subject_name` varchar(50) DEFAULT NULL COMMENT '专题名字',
  `url` varchar(50) DEFAULT NULL COMMENT '专题的url',
  `description` varchar(100) DEFAULT NULL COMMENT '专题描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间',
	`update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态：0-上线中 1-下架 -1：审核中',
  `view_count` bigint(20) DEFAULT NULL COMMENT '浏览量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专题表';

DROP TABLE IF EXISTS t_dealer;
CREATE TABLE `t_dealer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `agency_name` varchar(200) DEFAULT NULL COMMENT '经销商名字',
	`area_id` int(11) DEFAULT NULL COMMENT '区域id,对应dt_area表id',
  `agency_address` varchar(200) DEFAULT NULL comment '地址',
  `connect_tel` varchar(45) DEFAULT NULL COMMENT '联系电话',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间',
	`update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS t_order_detail;
CREATE TABLE `t_order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) NOT NULL comment '姓名',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `mobile` varchar(20) NOT NULL comment '手机号码',
  `province` varchar(20) NOT NULL COMMENT '省',
  `city` varchar(30) NOT NULL comment '市',
  `agentName` varchar(100) NOT NULL COMMENT '经销商名字',
  `agentCode` int(4) DEFAULT NULL COMMENT '经销商code',
  `carType` varchar(100) DEFAULT NULL COMMENT '车型',
  `carTypeCode` int(4) DEFAULT NULL COMMENT '车型code',
  `mediaName` varchar(200) DEFAULT NULL COMMENT '媒体名字',
  `mediaUrl` varchar(255) DEFAULT NULL COMMENT '媒体url',
  `subject` varchar(255) DEFAULT NULL COMMENT '专题主题',
  `terminal` varchar(10) DEFAULT NULL COMMENT '途径',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  `is_activity` tinyint(4) DEFAULT '0' COMMENT '是否活动专题',
  PRIMARY KEY (`id`),
  KEY `INDEX_PROVINCE` (`province`),
  KEY `index_city` (`city`),
  KEY `index_agent` (`agentName`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS dt_area;
CREATE TABLE `dt_area` (
  `id` mediumint(6) unsigned NOT NULL COMMENT '区域id,6位数字',
  `province_id` tinyint(2) unsigned NOT NULL COMMENT '省级ID',
  `city_id` tinyint(2) unsigned NOT NULL COMMENT '市级ID',
  `county_id` tinyint(2) unsigned NOT NULL COMMENT '县级ID',
  `name` char(15) NOT NULL COMMENT '名称,最多15个字符',
  `province_name` char(15) DEFAULT NULL COMMENT '省级名称,最多15个字符',
  `city_name` char(15) DEFAULT NULL COMMENT '市级名称,最多15个字符',
  `county_name` char(15) DEFAULT NULL COMMENT '县级名称,最多15个字符',
  `level` enum('SHENG','SHI','XIAN') NOT NULL COMMENT '级别,SHENG:省级;SHI:市级;XIAN:县级',
  `jian_pin` char(15) DEFAULT NULL COMMENT '简拼,最多15个字符',
  `status` enum('QY','TY') NOT NULL DEFAULT 'QY' COMMENT '启用状态,QY:启用;TY:停用;',
  PRIMARY KEY (`id`),
  KEY `F02` (`province_id`) USING BTREE,
  KEY `F03` (`city_id`) USING BTREE,
  KEY `F04` (`county_id`) USING BTREE,
  KEY `F13` (`status`) USING BTREE,
  KEY `F12` (`jian_pin`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='中国行政区划';

