create table pdt_inventory
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    product_id  bigint   default 0       not null comment '商品ID',
    stock       int      default 0       not null comment '库存',
    version     int      default 0       not null comment '版本',
    create_by   bigint   default 0       not null comment '创建人',
    create_time datetime default (now()) null comment '创建时间',
    update_by   bigint   default 0       not null comment '更新人',
    update_time datetime                 null on update CURRENT_TIMESTAMP comment '更新时间',
    delete_time datetime                 null comment '删除时间'
)
    comment '库存';

INSERT INTO pdt_inventory (id, product_id, stock, version, create_by, create_time, update_by, update_time, delete_time) VALUES (1, 1, 100, 0, 0, '2025-02-22 09:57:12', 1, '2025-03-05 10:26:21', null);
INSERT INTO pdt_inventory (id, product_id, stock, version, create_by, create_time, update_by, update_time, delete_time) VALUES (2, 2, 50, 0, 0, '2025-02-22 09:57:12', 1, '2025-03-05 10:26:21', null);
INSERT INTO pdt_inventory (id, product_id, stock, version, create_by, create_time, update_by, update_time, delete_time) VALUES (3, 3, 0, 0, 0, '2025-02-22 09:57:12', 0, '2025-02-22 09:57:12', null);
INSERT INTO pdt_inventory (id, product_id, stock, version, create_by, create_time, update_by, update_time, delete_time) VALUES (4, 4, 200, 0, 0, '2025-02-22 09:57:12', 0, '2025-02-22 09:57:12', null);
INSERT INTO pdt_inventory (id, product_id, stock, version, create_by, create_time, update_by, update_time, delete_time) VALUES (5, 5, 300, 0, 0, '2025-02-22 09:57:12', 0, '2025-02-22 09:57:12', null);
INSERT INTO pdt_inventory (id, product_id, stock, version, create_by, create_time, update_by, update_time, delete_time) VALUES (6, 6, 500, 0, 0, '2025-02-22 09:57:12', 0, '2025-02-22 09:57:12', null);

create table pdt_product
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    name        varchar(255)   default ''                not null comment '名称',
    price       decimal(10, 2) default 0.00              not null comment '价格',
    status      char           default '0'               null comment '状态',
    create_by   bigint         default 0                 not null comment '创建人',
    create_time datetime       default CURRENT_TIMESTAMP null comment '创建时间',
    update_by   bigint         default 0                 not null comment '更新时间',
    update_time datetime                                 null on update CURRENT_TIMESTAMP comment '更新时间',
    delete_time datetime                                 null comment '删除时间'
)
    comment '商品';

INSERT INTO pdt_product (id, name, price, status, create_by, create_time, update_by, update_time, delete_time) VALUES (1, 'iPhone 15', 7999.00, '1', 0, '2025-02-22 09:56:57', 0, '2025-02-22 09:56:57', null);
INSERT INTO pdt_product (id, name, price, status, create_by, create_time, update_by, update_time, delete_time) VALUES (2, '小米电视', 2999.00, '1', 0, '2025-02-22 09:56:57', 0, '2025-02-22 09:56:57', null);
INSERT INTO pdt_product (id, name, price, status, create_by, create_time, update_by, update_time, delete_time) VALUES (3, '华为笔记本', 5999.00, '0', 0, '2025-02-22 09:56:57', 0, '2025-02-22 09:56:57', null);
INSERT INTO pdt_product (id, name, price, status, create_by, create_time, update_by, update_time, delete_time) VALUES (4, '戴尔显示器', 1299.00, '1', 0, '2025-02-22 09:56:57', 0, '2025-02-22 09:56:57', null);
INSERT INTO pdt_product (id, name, price, status, create_by, create_time, update_by, update_time, delete_time) VALUES (5, '索尼耳机', 399.00, '1', 0, '2025-02-22 09:56:57', 0, '2025-02-22 09:56:57', null);
INSERT INTO pdt_product (id, name, price, status, create_by, create_time, update_by, update_time, delete_time) VALUES (6, '联想鼠标', 59.00, '1', 0, '2025-02-22 09:56:57', 0, '2025-02-22 09:56:57', null);

create table undo_log
(
    id            bigint auto_increment
        primary key,
    branch_id     bigint       not null,
    xid           varchar(100) not null,
    context       varchar(128) not null,
    rollback_info longblob     not null,
    log_status    int          not null,
    log_created   datetime     not null,
    log_modified  datetime     not null,
    ext           varchar(100) null,
    constraint ux_undo_log
        unique (xid, branch_id)
)
    charset = utf8mb3;

