DROP TABLE IF EXISTS tb_user;
CREATE TABLE tb_user (
                         id BIGINT(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '用户唯一ID',
                         name VARCHAR(20) COMMENT '用户昵称',
                         account VARCHAR(20) COMMENT '账号',
                         password VARCHAR(128) COMMENT '密码',
                         phone VARCHAR(20) COMMENT '手机号',
                         email VARCHAR(55) COMMENT '邮箱',
                         info VARCHAR(200) COMMENT '用户自定义信息'
);

DROP TABLE IF EXISTS tb_order;
CREATE TABLE tb_order (
                          id BIGINT(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '订单ID',
                          user_id BIGINT(20) COMMENT '用户ID',
                          state INT(2) COMMENT '状态\n1：已下单\n2：打包完成\n3：转运中\n4：等待中\n5：已发出\n6：已到达\n7：派送中\n8：已签收\n9：异常',
                          create_ts TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          update_ts TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

DROP TABLE IF EXISTS tb_order_detail;
CREATE TABLE tb_order_detail (
                                 id BIGINT(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
                                 order_id BIGINT(20) COMMENT '订单ID',
                                 state INT(2) COMMENT '状态\n1：已下单\n2：打包完成\n3：转运中\n4：等待中\n5：已发出\n6：已到达\n7：派送中\n8：已签收\n9：异常',
                                 info VARCHAR(255) COMMENT '状态信息',
                                 create_ts TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
);