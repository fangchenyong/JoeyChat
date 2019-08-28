# 基于Netty+WebSocket简易版微信-慕课网

### 1. 技术栈

**前端：MUI 、H5PLUS**

**后端：SpringBoot 、Netty、WebSocket、Mysql、FastDFS、Nginx、Mybatis**

### 2. 数据库建表

#### 表：users

| 表名 | users          | 表名中文 | 用户表  |      |      |      |        |      |
| ---- | :------------- | -------- | ------- | ---- | ---- | ---- | ------ | ---- |
| No   | 列名           | 列名中文 | 类型    | 长度 | Key  | Null | 默认值 | 备注 |
| 1    | id             | 主键     | varchar | 64   | Y    | N    |        |      |
| 2    | username       | 名称     | varchar | 20   |      | N    |        |      |
| 3    | password       | 密码     | varchar | 64   |      | M    |        |      |
| 4    | face_image     | 头像     | varchar | 255  |      | N    |        |      |
| 5    | face_image_big | 大头像   | varchar | 255  |      | N    |        |      |
| 6    | nickname       | 昵称     | varchar | 20   |      | N    |        |      |
| 7    | qrcode         | 二维码   | varchar | 255  |      | N    |        |      |
| 8    | cid            | 设备ID   | varchar | 64   |      | N    |        |      |

#### 表：friends_request

| 表名 | friends_request   | 表名中文 | 用户请求表 |      |      |      |        |      |
| ---- | ----------------- | -------- | ---------- | ---- | ---- | ---- | ------ | ---- |
| No   | 列名              | 列名中文 | 类型       | 长度 | Key  | Null | 默认值 | 备注 |
| 1    | id                | 主键     | varchar    | 64   | Y    | N    |        |      |
| 2    | send_user_id      | 发送者ID | varchar    | 64   |      | N    |        |      |
| 3    | accept_user_id    | 接收者ID | varchar    | 64   |      | N    |        |      |
| 4    | request_date_time | 接收时间 | datetime   | 0    |      | N    |        |      |

#### 表：my_friends

| 表名 | my_friends        | 表名中文 | 好友中间表 |      |      |      |        |      |
| ---- | ----------------- | -------- | ---------- | ---- | ---- | ---- | ------ | ---- |
| No   | 列名              | 列名中文 | 类型       | 长度 | Key  | Null | 默认值 | 备注 |
| 1    | id                | 主键     | varchar    | 64   | Y    | N    |        |      |
| 2    | my_user_id        | 我的ID   | varchar    | 64   |      | N    |        |      |
| 3    | my_friend_user_id | 好友的ID | varchar    | 64   |      | N    |        |      |

#### 表：chat_msg

| 表名 | chat_msg       | 表名中文 | 好友中间表 |      |      |      |        |      |
| ---- | -------------- | -------- | ---------- | ---- | ---- | ---- | ------ | ---- |
| No   | 列名           | 列名中文 | 类型       | 长度 | Key  | Null | 默认值 | 备注 |
| 1    | id             | 主键     | varchar    | 64   | Y    | N    |        |      |
| 2    | send_user_id   | 我的ID   | varchar    | 64   |      | N    |        |      |
| 3    | accept_user_id | 好友的ID | varchar    | 64   |      | N    |        |      |
| 4    | msg            | 消息     | varchar    | 255  |      | N    |        |      |
| 5    | sign_flag      | 已读未读 | int        | 1    |      | N    |        |      |
| 6    | create_time    | 发送时间 | datetime   | 0    |      | N    |        |      |

```sql
create TABLE user(
	id varchar(64)  not null COMMENT '主键',
	username varchar(20)  null COMMENT '名称',
	password varchar(64)  null COMMENT '密码',
	face_image varchar(255)  null COMMENT '头像',
	face_image_big  varchar(255)  null COMMENT '大头像',
	nickname varchar(20)  null COMMENT '昵称',
	qrcode varchar(255)  null COMMENT '二维码',
	cid VARCHAR(64)  null COMMENT '设备ID',
	PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
create TABLE friends_request(
	id varchar(64)  not null COMMENT '主键',
	send_user_id varchar(64)  null COMMENT '发送者ID',
	accept_user_id varchar(64)  null COMMENT '接收者ID',
	request_date_time datetime  null COMMENT '接收时间',
	PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
create TABLE my_friends(
	id varchar(64)  not null COMMENT '主键',
	my_user_id varchar(64)  null COMMENT '我的ID',
	my_friend_user_id varchar(64)  null COMMENT '好友的ID',
	PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
create TABLE chat_msg(
	id varchar(64)  not null COMMENT '主键',
	send_user_id varchar(64)  null COMMENT '我的ID',
	accept_user_id varchar(64)  null COMMENT '好友的ID',
	msg varchar(255)   null COMMENT '消息',
    sign_flag int null COMMENT '已读未读',
    create_time datetime  null COMMENT '发送时间',
	PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
```

### 问题解决记录

- [安装fastDFS+nginx实现文件存储服务器](https://fangchenyong.top/2019/08/14/install_FastDFS+Nginx/)
- [The type org.springframework.context.ConfigurableApplicationContext cannot be resolved.](https://blog.csdn.net/svneclipse/article/details/80719480)
- [Spring配置事务的传播行为（PROPAGATION)和隔离级别(Isolation)（终于懂了）](https://www.iteye.com/blog/1615714)
- [mui.ajax() abort](https://ask.dcloud.net.cn/question/8161)
- [Uncaught SyntaxError: Unexpected token o](https://blog.csdn.net/bmw601055/article/details/77414855) 

*注：安装测试需要更改application.properties中fastdfs配置以及数据库连接，修改前端app.js配置以及后端新增用户产生二维码的文件目录和文件上传目录*

