

/*Table structure for table `sys_permission` */
CREATE TABLE t_sys_permission (
  id varchar(32) NOT NULL,
  parent_id varchar(32) DEFAULT NULL, /* '父菜单ID',*/
  menu_name varchar(50) DEFAULT NULL, /* '菜单名称',*/
  p_type varchar(10) DEFAULT NULL,
  icon varchar(50) DEFAULT NULL, /* '菜单图标',*/
  title varchar(50) DEFAULT NULL, /*  '菜单标题',*/
  menu_level number DEFAULT NULL, /*  '菜单层级',*/
  menu_order number DEFAULT NULL, /*  '排序',*/
  create_user_id varchar(32) DEFAULT NULL,
  create_time Date DEFAULT NULL,
  update_user_id varchar(32) DEFAULT NULL,
  update_time Date DEFAULT NULL,
  PRIMARY KEY (id)
);

/*Data for the table `t_sys_permission` */

insert  into t_sys_permission(id,parent_id,menu_name,p_type,icon,title,menu_level,menu_order,create_user_id,create_time,update_user_id,update_time) values ('00a6357c7ab14d9d9720124632dc948d','ab5dd0bc078443d9b7163567474526c9','sys.role.view','2','ion-search','查看',3,4,'96169ca8adb04ca6b63e0d492a9b2807',to_date('2007/6/18 12:12:12','yyyy-mm-dd hh24:mi:ss'),NULL,NULL);
insert  into t_sys_permission(id,parent_id,menu_name,p_type,icon,title,menu_level,menu_order,create_user_id,create_time,update_user_id,update_time) values('07ba4e5b9888403780856184e6e30c6d','#','KT Admin','0',NULL,'平台菜单',0,0,NULL,NULL,'96169ca8adb04ca6b63e0d492a9b2807',to_date('2007/6/19 12:12:12','yyyy-mm-dd hh24:mi:ss'));
insert  into t_sys_permission(id,parent_id,menu_name,p_type,icon,title,menu_level,menu_order,create_user_id,create_time,update_user_id,update_time) values ('0d3e4a35ad03449ba409223d99963aae','745d991060c54cd8a9c490409c4aa903','sys.permission.add','2','ion-plus-round','新增',3,1,'96169ca8adb04ca6b63e0d492a9b2807',to_date('2007/6/11 12:12:12','yyyy-mm-dd hh24:mi:ss'),NULL,NULL);
insert  into t_sys_permission(id,parent_id,menu_name,p_type,icon,title,menu_level,menu_order,create_user_id,create_time,update_user_id,update_time) values ('1efd3ab941164ac9a8f87aa5148d1820','d1ec24bffa654aa888fdfb331ede091a','dict.dictclass.edit','2','ion-edit','编辑',3,2,'96169ca8adb04ca6b63e0d492a9b2807',to_date('2007/6/15 12:12:12','yyyy-mm-dd hh24:mi:ss'),'96169ca8adb04ca6b63e0d492a9b2807',to_date('2007/6/16 12:12:12','yyyy-mm-dd hh24:mi:ss'));
insert  into t_sys_permission(id,parent_id,menu_name,p_type,icon,title,menu_level,menu_order,create_user_id,create_time,update_user_id,update_time) values('36e07c711c034e8da6040153e6493aa7','f0dbb3052721482d90df80128dfcff18','sys.user.delete','2','ion-trash-a','删除',2,3,NULL,NULL,NULL,NULL);
insert  into t_sys_permission(id,parent_id,menu_name,p_type,icon,title,menu_level,menu_order,create_user_id,create_time,update_user_id,update_time) values ('375d52d5fa4a44d690e613ad740aa60d','745d991060c54cd8a9c490409c4aa903','sys.permission.delete','2','ion-trash-a','删除',3,3,'16ca5e0d3d4a4f988f748a5879c9ca98',to_date('2007/6/16 12:12:12','yyyy-mm-dd hh24:mi:ss'),'16ca5e0d3d4a4f988f748a5879c9ca98',to_date('2007/6/17 12:12:12','yyyy-mm-dd hh24:mi:ss'));
insert  into t_sys_permission(id,parent_id,menu_name,p_type,icon,title,menu_level,menu_order,create_user_id,create_time,update_user_id,update_time) values('3aa370c18b8841b784a4ea616576a849','ab5dd0bc078443d9b7163567474526c9','sys.role.edit','2','ion-edit','编辑',3,2,NULL,NULL,NULL,NULL);
insert  into t_sys_permission(id,parent_id,menu_name,p_type,icon,title,menu_level,menu_order,create_user_id,create_time,update_user_id,update_time) values('48ec7769aff1400186bdeb5da1dfe745','ab5dd0bc078443d9b7163567474526c9','sys.role.add','2','ion-plus-round','新增',3,1,NULL,NULL,NULL,NULL);
insert  into t_sys_permission(id,parent_id,menu_name,p_type,icon,title,menu_level,menu_order,create_user_id,create_time,update_user_id,update_time) values('4a4b2b5bf36945b89bbafd7ecef9b9b2','f0dbb3052721482d90df80128dfcff18','sys.user.view','2','ion-search','查看',2,4,NULL,NULL,NULL,NULL);
insert  into t_sys_permission(id,parent_id,menu_name,p_type,icon,title,menu_level,menu_order,create_user_id,create_time,update_user_id,update_time) values('4c7f081cf62a420ea7980901a29d0ec4','f0dbb3052721482d90df80128dfcff18','sys.user.edit','2','ion-edit','编辑',2,2,NULL,NULL,NULL,NULL);


/*Table structure for table `t_sys_role` */

CREATE TABLE t_sys_role (
  id varchar(32) NOT NULL,
  role_name varchar(100) DEFAULT NULL, /* '角色名称'*/
  remark varchar(100) DEFAULT NULL, /* '备注'*/
  create_user_id varchar(32) DEFAULT NULL,
  create_time Date DEFAULT NULL,
  update_user_id varchar(32) DEFAULT NULL,
  update_time Date DEFAULT NULL,
  PRIMARY KEY (id)
);

/*Data for the table `t_sys_role` */

insert  into t_sys_role(id,role_name,remark,create_user_id,create_time,update_user_id,update_time) values 
('0074d75da58445249417c76111a4b018','admin','系统管理员',NULL,NULL,'96169ca8adb04ca6b63e0d492a9b2807',to_date('2007/6/24 12:12:12','yyyy-mm-dd hh24:mi:ss'));
insert  into t_sys_role(id,role_name,remark,create_user_id,create_time,update_user_id,update_time) values 
('1eb2ee3af13c4effb70c6c1c7456ac3b','user1','普通用户1',NULL,NULL,'96169ca8adb04ca6b63e0d492a9b2807',to_date('2007/6/25 12:12:12','yyyy-mm-dd hh24:mi:ss'));


CREATE TABLE t_sys_role_permission (
  id varchar(32) NOT NULL,
  role_id varchar(32) DEFAULT NULL , /*'角色ID',*/
  permission_id varchar(32) DEFAULT NULL , /*'菜单ID',*/
  create_user_id varchar(32) DEFAULT NULL,
  create_time Date DEFAULT NULL,
  update_user_id varchar(32) DEFAULT NULL,
  update_time Date DEFAULT NULL,
  PRIMARY KEY (id)
) ;

/*Data for the table t_sys_role_permission */

insert  into t_sys_role_permission(id,role_id,permission_id,create_user_id,create_time,update_user_id,update_time) values ('0833ea43b8e84e7284888b7d6672f80f','0074d75da58445249417c76111a4b018','1efd3ab941164ac9a8f87aa5148d1820',NULL,NULL,NULL,NULL);
insert  into t_sys_role_permission(id,role_id,permission_id,create_user_id,create_time,update_user_id,update_time) values ('0bf91e9a0bb14113824d3d5f511f82c3','0074d75da58445249417c76111a4b018','3aa370c18b8841b784a4ea616576a849',NULL,NULL,NULL,NULL);
insert  into t_sys_role_permission(id,role_id,permission_id,create_user_id,create_time,update_user_id,update_time) values ('0d495ee552fe401ea5a0353a04228844','0074d75da58445249417c76111a4b018','ef9e6d3e2d984667945228a0c7157831',NULL,NULL,NULL,NULL);
insert  into t_sys_role_permission(id,role_id,permission_id,create_user_id,create_time,update_user_id,update_time) values ('0d5d78c94af3495c909490a41ed738f6','0074d75da58445249417c76111a4b018','4a4b2b5bf36945b89bbafd7ecef9b9b2',NULL,NULL,NULL,NULL);
insert  into t_sys_role_permission(id,role_id,permission_id,create_user_id,create_time,update_user_id,update_time) values ('14331359a259415eb1edd419cbfea243','0074d75da58445249417c76111a4b018','ab5dd0bc078443d9b7163567474526c9',NULL,NULL,NULL,NULL);
insert  into t_sys_role_permission(id,role_id,permission_id,create_user_id,create_time,update_user_id,update_time) values ('2515ac44435842afae5bf3f69cfce323','0074d75da58445249417c76111a4b018','f5efec389be0435d9e365e7b8c498071',NULL,NULL,NULL,NULL);
insert  into t_sys_role_permission(id,role_id,permission_id,create_user_id,create_time,update_user_id,update_time) values ('293108ca0db44d31b337bf60384c004e','0074d75da58445249417c76111a4b018','48ec7769aff1400186bdeb5da1dfe745',NULL,NULL,NULL,NULL);
insert  into t_sys_role_permission(id,role_id,permission_id,create_user_id,create_time,update_user_id,update_time) values ('2a43054695e9483393e1bae7c395802a','0074d75da58445249417c76111a4b018','375d52d5fa4a44d690e613ad740aa60d',NULL,NULL,NULL,NULL);
insert  into t_sys_role_permission(id,role_id,permission_id,create_user_id,create_time,update_user_id,update_time) values ('2e831d32f93d4e8987e6971237ffd3b6','0074d75da58445249417c76111a4b018','68c4c8f6bdee4dbe9d3d2a546e9ae0a9',NULL,NULL,NULL,NULL);
insert  into t_sys_role_permission(id,role_id,permission_id,create_user_id,create_time,update_user_id,update_time) values ('32bd6e9c90d0444396301b3c57725a7f','0074d75da58445249417c76111a4b018','aeafd2a9381e48268948358c4b35276f',NULL,NULL,NULL,NULL);
insert  into t_sys_role_permission(id,role_id,permission_id,create_user_id,create_time,update_user_id,update_time) values ('46a5c488753148c58d39f7594a75cf9d','0074d75da58445249417c76111a4b018','d1ec24bffa654aa888fdfb331ede091a',NULL,NULL,NULL,NULL);
insert  into t_sys_role_permission(id,role_id,permission_id,create_user_id,create_time,update_user_id,update_time) values ('50c135a25c024163bfb69ffa0ae6080e','0074d75da58445249417c76111a4b018','c20c19ec0c9a4954abeca8948da17d13',NULL,NULL,NULL,NULL);
insert  into t_sys_role_permission(id,role_id,permission_id,create_user_id,create_time,update_user_id,update_time) values ('545f0f4196b14d959bd4ac44ff181a7c','0074d75da58445249417c76111a4b018','07ba4e5b9888403780856184e6e30c6d',NULL,NULL,NULL,NULL);
insert  into t_sys_role_permission(id,role_id,permission_id,create_user_id,create_time,update_user_id,update_time) values ('5a0d739a61d5452898049821fd6e634c','0074d75da58445249417c76111a4b018','d54e5e8cf6d14ba194c14a938e6915bd',NULL,NULL,NULL,NULL);
insert  into t_sys_role_permission(id,role_id,permission_id,create_user_id,create_time,update_user_id,update_time) values ('61df740299d94085bf7fcedb32ca5ff8','0074d75da58445249417c76111a4b018','745d991060c54cd8a9c490409c4aa903',NULL,NULL,NULL,NULL);
insert  into t_sys_role_permission(id,role_id,permission_id,create_user_id,create_time,update_user_id,update_time) values ('73574a7cb7064cdf8ced0da9e368d533','0074d75da58445249417c76111a4b018','00a6357c7ab14d9d9720124632dc948d',NULL,NULL,NULL,NULL);
insert  into t_sys_role_permission(id,role_id,permission_id,create_user_id,create_time,update_user_id,update_time) values ('765ed0b807dc4e90b5ce30ccbddf0317','0074d75da58445249417c76111a4b018','0d3e4a35ad03449ba409223d99963aae',NULL,NULL,NULL,NULL);



CREATE TABLE t_sys_user (
  id varchar(32) NOT NULL,
  username varchar(50) NOT NULL ,/* '用户名',*/
  password varchar(100) DEFAULT NULL ,/* '密码',*/
  email varchar(100) DEFAULT NULL ,/* '邮箱',*/
  mobile varchar(100) DEFAULT NULL ,/* '手机号',*/
  status varchar(10) DEFAULT NULL,
  create_user_id varchar(32) DEFAULT NULL,
  create_time Date DEFAULT NULL,
  update_user_id varchar(32) DEFAULT NULL,
  update_time Date DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE  (username)
) ;

/*Data for the table t_sys_user */

insert  into t_sys_user(id,username,password,email,mobile,status,create_user_id,create_time,update_user_id,update_time) values ('16ca5e0d3d4a4f988f748a5879c9ca98','kt','99d3fd0fa8fe115ef5983b5472cc95a88f2790a6fa89f8785da5afbe7b548bba','504406577@qq.com','13672336315','1',NULL,NULL,'96169ca8adb04ca6b63e0d492a9b2807',to_date('2017/6/25 12:12:12','yyyy-mm-dd hh24:mi:ss'));
insert  into t_sys_user(id,username,password,email,mobile,status,create_user_id,create_time,update_user_id,update_time) values ('65909bf4abe8440cae422e5aa6e7f13c','admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','1234567890@qq.com',NULL,'1','96169ca8adb04ca6b63e0d492a9b2807',to_date('2017/6/25 11:12:12','yyyy-mm-dd hh24:mi:ss'),'96169ca8adb04ca6b63e0d492a9b2807',to_date('2017/6/25 11:12:12','yyyy-mm-dd hh24:mi:ss'));
insert  into t_sys_user(id,username,password,email,mobile,status,create_user_id,create_time,update_user_id,update_time) values ('6e4dd5065cf74e45a48145357c77e642','user3','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','123456@111.com',NULL,NULL,NULL,NULL,NULL,NULL);
insert  into t_sys_user(id,username,password,email,mobile,status,create_user_id,create_time,update_user_id,update_time) values ('9e48b78d6de247da9042a090c7bc69d8','user','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','1234567890@qq.com',NULL,'1','96169ca8adb04ca6b63e0d492a9b2807',to_date('2017/6/25 12:12:12','yyyy-mm-dd hh24:mi:ss'),'65909bf4abe8440cae422e5aa6e7f13c',to_date('2017/7/25 12:12:12','yyyy-mm-dd hh24:mi:ss'));
insert  into t_sys_user(id,username,password,email,mobile,status,create_user_id,create_time,update_user_id,update_time) values ('efd171f2f6724e04b3e1a591301d4409','user1','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','1234567890@qq.com',NULL,'1','65909bf4abe8440cae422e5aa6e7f13c',to_date('2017/6/21 12:12:12','yyyy-mm-dd hh24:mi:ss'),'65909bf4abe8440cae422e5aa6e7f13c',to_date('2017/6/22 12:12:12','yyyy-mm-dd hh24:mi:ss'));



/*Table structure for table `t_sys_user_role` */

CREATE TABLE t_sys_user_role (
  id varchar(32) NOT NULL,
  user_id varchar(32) DEFAULT NULL ,/* '用户ID',*/
  role_id varchar(32) DEFAULT NULL ,/* '角色ID',*/
  create_user_id varchar(32) DEFAULT NULL,
  create_time Date DEFAULT NULL,
  update_user_id varchar(32) DEFAULT NULL,
  update_time Date DEFAULT NULL,
  PRIMARY KEY (id)
) ;

/*Data for the table t_t_sys_user_role */

insert  into t_sys_user_role(id,user_id,role_id,create_user_id,create_time,update_user_id,update_time) values ('ab98e75894cb485997c592822c4cad41','efd171f2f6724e04b3e1a591301d4409','1eb2ee3af13c4effb70c6c1c7456ac3b',NULL,NULL,NULL,NULL);
insert  into t_sys_user_role(id,user_id,role_id,create_user_id,create_time,update_user_id,update_time) values ('3ae7c14a0a5c457aa3145eb8f8153546','16ca5e0d3d4a4f988f748a5879c9ca98','1eb2ee3af13c4effb70c6c1c7456ac3b',NULL,NULL,NULL,NULL);
insert  into t_sys_user_role(id,user_id,role_id,create_user_id,create_time,update_user_id,update_time) values ('041ce509b0374825b77306fb6a3979f7','65909bf4abe8440cae422e5aa6e7f13c','0074d75da58445249417c76111a4b018',NULL,NULL,NULL,NULL);
insert  into t_sys_user_role(id,user_id,role_id,create_user_id,create_time,update_user_id,update_time) values ('35f7c546aa754a078c8edb1001cde765','6e4dd5065cf74e45a48145357c77e642','1eb2ee3af13c4effb70c6c1c7456ac3b',NULL,NULL,NULL,NULL);
insert  into t_sys_user_role(id,user_id,role_id,create_user_id,create_time,update_user_id,update_time) values ('8472055b15974ca685e885053d19b872','9e48b78d6de247da9042a090c7bc69d8','7b3ddb7091004a40ad6391f414d50a65',NULL,NULL,NULL,NULL);



/*  定时任务  */
CREATE TABLE t_sys_schedule_job (
  id varchar(32) NOT NULL,				/* 调度ID */
  name varchar(255) NOT NULL,			/* 调度名称 */
  class_name varchar(255) NOT NULL,		/* 调度执行类 */
  times varchar(255) NOT NULL,			/* 触发时间表达式 */
  auto_start char(1) NOT NULL,			/* 是否自动启动 0:否 1:是*/
  status varchar(10) DEFAULT NULL,		/* 状态 0:未启动 1:已启动  */
  last_runtime Date DEFAULT NULL,	/* 最后执行时间  */
  comments varchar(2000) DEFAULT NULL,	
  create_user_id varchar(32) DEFAULT NULL,
  create_time Date DEFAULT NULL,
  update_user_id varchar(32) DEFAULT NULL,
  update_time Date DEFAULT NULL,	/* 备注 */
 PRIMARY KEY (id)
) ;
