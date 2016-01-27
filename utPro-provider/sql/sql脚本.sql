CREATE TABLE `T_B_PERSON` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `age` int(11) NOT NULL COMMENT '年龄',
  `sex` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='人(cc)';

CREATE TABLE `T_SYS_TASK_CONFIG` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `arguments` varchar(255) DEFAULT NULL,
  `className` varchar(250) NOT NULL,
  `cronExpression` varchar(100) NOT NULL,
  `description` varchar(350) DEFAULT NULL,
  `groupName` varchar(30) NOT NULL,
  `isAvailable` bit(1) DEFAULT NULL,
  `isConcurrent` bit(1) DEFAULT NULL,
  `jobCode` varchar(64) NOT NULL,
  `methodName` varchar(150) NOT NULL,
  `triggerCode` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `triggerCode` (`triggerCode`),
  UNIQUE KEY `jobCode` (`jobCode`)
) ENGINE=InnoDB AUTO_INCREMENT=235 DEFAULT CHARSET=utf8;
