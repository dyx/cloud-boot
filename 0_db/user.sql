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

create table usr_user
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    username    varchar(50)  default '' not null comment '用户名',
    password    varchar(255) default '' not null comment '用户密码',
    nickname    varchar(100) default '' null comment '用户昵称',
    email       varchar(100) default '' not null comment '用户邮箱',
    phone       varchar(20)  default '' null comment '手机号',
    status      int          default 1  null comment '用户状态',
    create_time datetime                null comment '创建时间',
    create_by   bigint       default 0  not null comment '创建人',
    update_time datetime                null comment '更新时间',
    update_by   bigint       default 0  not null comment '更新时间',
    constraint uk_username
        unique (username)
)
    comment '用户';

INSERT INTO usr_user (id, username, password, nickname, email, phone, status, create_time, create_by, update_time, update_by) VALUES (1, 'admin', '$2a$10$3eBNm9Gr7tStgkuVzEzamO7GGKvax/s4tfUc4IKF.qmt/XkVhceI.', '管理员', 'admin@cloud.com', '18812345678', 1, '2025-02-17 13:13:25', 0, '2025-02-17 13:13:28', 0);

