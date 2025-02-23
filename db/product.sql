create table pdt_inventory
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    product_id  bigint default 0 not null comment '商品ID',
    stock       int    default 0 not null comment '库存',
    version     int    default 0 not null comment '版本',
    create_time datetime         null comment '创建时间',
    create_by   bigint default 0 not null comment '创建人',
    update_time datetime         null comment '更新时间',
    update_by   bigint default 0 not null comment '更新人'
)
    comment '库存';

create table pdt_product
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    name        varchar(255)   default ''   not null comment '名称',
    price       decimal(10, 2) default 0.00 not null comment '价格',
    status      int            default 0    null comment '状态',
    create_time datetime                    null comment '创建时间',
    create_by   bigint         default 0    not null comment '创建人',
    update_time datetime                    null comment '更新时间',
    update_by   bigint         default 0    not null comment '更新时间',
    is_deleted  int            default 0    not null comment '删除标志'
)
    comment '商品';

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

