#Stock
DROP SCHEMA IF EXISTS db_stock;
CREATE SCHEMA db_stock;
USE db_stock;

CREATE TABLE `t_stock`(
`id`          INT(11) NOT NULL AUTO_INCREMENT,
`goods_id`    VARCHAR(6) DEFAULT NULL,
`stock_num`   INT(11) DEFAULT '0',
PRIMARY KEY (`id`),
UNIQUE KEY `goods_id` (`goods_id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;
INSERT INTO t_stock (id, goods_id, stock_num) VALUES (1, '2001', 100);

CREATE TABLE `undo_log` (
`id`             BIGINT(20) NOT NULL AUTO_INCREMENT,
`branch_id`      BIGINT(20) NOT NULL,
`xid`            VARCHAR(100) NOT NULL,
`context`        VARCHAR(128) NOT NULL,
`rollback_info`  LONGBLOB NOT NULL,
`log_status`     INT(11) NOT NULL,
`log_created`    DATETIME NOT NULL,
`log_modified`   DATETIME NOT NULL,
`ext`            VARCHAR(100) DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;
