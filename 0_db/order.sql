create table ord_order
(
    id           bigint auto_increment comment '主键ID'
        primary key,
    user_id      bigint         default 0                 not null comment '用户ID',
    order_no     varchar(32)    default ''                not null comment '订单编号',
    total_amount decimal(18, 2) default 0.00              not null comment '订单总金额',
    order_status char           default '0'               not null comment '订单状态(0:待支付 1:已支付 2:已发货 3:已完成 4:已取消)',
    create_by    bigint         default 0                 not null comment '创建人',
    create_time  datetime       default CURRENT_TIMESTAMP null comment '创建时间',
    update_by    bigint         default 0                 not null comment '更新人',
    update_time  datetime                                 null on update CURRENT_TIMESTAMP comment '更新时间',
    delete_time  bigint         default 0                 not null comment '删除时间',
    constraint order_no
        unique (order_no)
)
    comment '订单';

create table ord_order_item
(
    id           bigint auto_increment comment '主键ID'
        primary key,
    order_id     bigint         default 0                 not null comment '订单ID',
    product_id   bigint         default 0                 not null comment '商品ID',
    product_name varchar(255)   default ''                not null comment '商品名称',
    quantity     int            default 0                 not null comment '商品数量',
    price        decimal(18, 2) default 0.00              not null comment '商品单价',
    total_price  decimal(18, 2) default 0.00              not null comment '商品总价',
    create_by    bigint         default 0                 not null comment '创建人',
    create_time  datetime       default CURRENT_TIMESTAMP null comment '创建时间',
    update_by    bigint         default 0                 not null comment '更新人',
    update_time  datetime                                 null on update CURRENT_TIMESTAMP comment '更新时间',
    delete_time  bigint         default 0                 not null comment '删除时间'
)
    comment '订单明细';

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

