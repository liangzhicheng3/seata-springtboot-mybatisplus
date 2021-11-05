#Account
DROP SCHEMA IF EXISTS db_account;
CREATE SCHEMA db_account;
USE db_account;

CREATE TABLE `t_account` (
`id`        INT(11) NOT NULL AUTO_INCREMENT,
`user_id`   VARCHAR(6) DEFAULT NULL,
`money`     DECIMAL(10,2) DEFAULT 0,
PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;
INSERT INTO t_account (id, user_id, money) VALUES (1, '1001', 1000);
INSERT INTO t_account (id, user_id, money) VALUES (2, '1002', 1000);

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
