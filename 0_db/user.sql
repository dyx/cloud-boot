create table sys_dict
(
    id           bigint auto_increment comment '主键ID'
        primary key,
    dict_type_id bigint      default 0                 not null comment '字典类型ID',
    code         varchar(32) default ''                not null comment '编码',
    value        varchar(32) default ''                not null comment '值',
    label        varchar(64) default ''                not null comment '标签',
    order_num    int         default 0                 not null comment '排序号',
    description  varchar(64)                           null comment '描述',
    create_by    bigint      default 0                 not null comment '创建人',
    create_time  datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    update_by    bigint      default 0                 not null comment '修改人',
    update_time  datetime                              null on update CURRENT_TIMESTAMP comment '修改时间',
    delete_time  bigint      default 0                 not null comment '删除时间'
)
    comment '字典';

INSERT INTO sys_dict (id, dict_type_id, code, value, label, order_num, description, create_by, create_time, update_by, update_time, delete_time) VALUES (1, 1, 'user_status', '1', '启用', 0, null, 1, '2025-03-19 10:55:57', 0, '2025-03-19 12:14:33', 0);
INSERT INTO sys_dict (id, dict_type_id, code, value, label, order_num, description, create_by, create_time, update_by, update_time, delete_time) VALUES (2, 1, 'user_status', '2', '停用', 0, null, 1, '2025-03-19 10:56:13', 0, '2025-03-19 12:14:33', 0);

create table sys_dict_type
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    code        varchar(32) default ''                not null comment '编码',
    name        varchar(64) default ''                not null comment '名称',
    description varchar(64)                           null comment '描述',
    create_by   bigint      default 0                 not null comment '创建人',
    create_time datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    update_by   bigint      default 0                 not null comment '修改人',
    update_time datetime                              null on update CURRENT_TIMESTAMP comment '修改时间',
    delete_time bigint      default 0                 not null comment '删除时间'
)
    comment '字典类型';

create table sys_menu
(
    id          bigint                                not null comment '主键ID'
        primary key,
    parent_id   bigint      default 0                 not null comment '父菜单ID',
    name        varchar(64) default ''                not null comment '菜单名称',
    type        char        default '0'               not null comment '菜单类型，1目录，2菜单，3按钮',
    permission  varchar(32)                           null comment '权限标识',
    path        varchar(128)                          null comment '路由路径',
    icon        varchar(64)                           null comment '菜单图标',
    order_num   int         default 1                 not null comment '排序号',
    create_by   bigint      default 0                 not null comment '创建人',
    create_time datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    update_by   bigint      default 0                 not null comment '修改人',
    update_time datetime                              null on update CURRENT_TIMESTAMP comment '修改时间',
    delete_time bigint      default 0                 not null comment '删除时间'
)
    comment '菜单权限';

create table sys_operation_log
(
    id                   bigint auto_increment comment '主键ID'
        primary key,
    module               varchar(50) default '' not null comment '操作模块',
    type                 varchar(20) default '' not null comment '操作类型（新增/修改/删除等）',
    operator_id          bigint      default 0  not null comment '操作人ID',
    operation_time       datetime               not null comment '操作时间',
    operation_ip         varchar(50)            null comment '操作IP',
    operation_user_agent varchar(256)           null,
    method               varchar(200)           null comment '请求方法',
    params               text                   null comment '请求参数',
    result               text                   null comment '返回结果',
    status               char        default '' null comment '操作状态（1成功 2失败）',
    elapsed_time         bigint                 null comment '操作耗时（毫秒）',
    error_msg            text                   null comment '错误信息'
);

create table sys_role
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    name        varchar(32) default ''                not null comment '角色名称',
    description varchar(512)                          null comment '描述',
    create_by   bigint      default 0                 not null comment '创建人',
    create_time datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    update_by   bigint      default 0                 not null comment '修改人',
    update_time datetime                              null on update CURRENT_TIMESTAMP comment '修改时间',
    delete_time bigint      default 0                 not null comment '删除时间',
    is_preset   tinyint     default 0                 not null comment '是否预置数据',
    constraint uk_name
        unique (name, delete_time)
)
    comment '角色';

create table sys_role_menu
(
    id      bigint auto_increment comment '主键ID'
        primary key,
    role_id bigint default 0 not null comment '角色ID',
    menu_id bigint default 0 not null comment '菜单权限ID'
)
    comment '角色菜单权限';

create table sys_user
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    username    varchar(50)  default ''      not null comment '用户名',
    password    varchar(255) default ''      not null comment '用户密码',
    name        varchar(32)  default ''      not null comment '用户姓名',
    nickname    varchar(64)  default ''      not null comment '用户昵称',
    avatar      varchar(255) default ''      not null comment '用户头像',
    email       varchar(100) default ''      not null comment '用户邮箱',
    phone       varchar(20)  default ''      not null comment '手机号',
    status      char         default '1'     null comment '用户状态 1启用 2停用',
    create_by   bigint       default 0       not null comment '创建人',
    create_time datetime     default (now()) null comment '创建时间',
    update_by   bigint       default 0       not null comment '修改人',
    update_time datetime                     null on update CURRENT_TIMESTAMP comment '修改时间',
    delete_time bigint       default 0       not null comment '删除时间',
    is_preset   tinyint      default 0       not null comment '是否预置数据',
    constraint uk_username_delete_time
        unique (username, delete_time)
)
    comment '用户';

INSERT INTO sys_user (id, username, password, name, nickname, avatar, email, phone, status, create_by, create_time, update_by, update_time, delete_time, is_preset) VALUES (1, 'admin', '$2a$10$3eBNm9Gr7tStgkuVzEzamO7GGKvax/s4tfUc4IKF.qmt/XkVhceI.', '管理员', '管理员', '', 'admin@cloud.com', '18812345678', '1', 0, '2025-02-17 13:13:25', 0, '2025-03-18 11:36:16', 0, 1);

create table sys_user_role
(
    id      bigint auto_increment comment '主键ID'
        primary key,
    user_id bigint default 0 not null comment '用户ID',
    role_id bigint default 0 not null comment '角色ID'
)
    comment '用户角色';

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

