update sys_config set name='参数' where id='673570483181977600';
delete from sys_profile where id<>'default';
delete from sys_config where profile_id<>'default';
update sys_config set value='2.6.0' where code='system.version.code';
update sys_config set value='PROD' where code='system.version.name';
update sys_config set value='PROD' where code='system.version.type';
update sys_config set value='1' where code='system.index.portal.enable';
update sys_config set value='0' where code='system.external.portal.enable';
update sys_config set value='{account:"",password:"",captcha:""}'  where code='system.login.default';
update sys_config set value=0 where code='system.login.captcha.any';
update sys_config set value='123456' where value='system.password.default';
update sys_config set value='/assets/images/bg_login_21.jpg' where code='system.login.background';
update sys_config set value='/pages/index/index' where code='eam.mobileLoginIndex';
update eam_asset_label set font_path='/app/app/bin/msyh.ttf' where id='1';


update sys_menu set css='fa fa-circle-o'  where type='page' and ( css is null or css='');

update sys_user set portrait_id ='T001_image_head' where 1=1;

-- setting some common sql
update sys_user set language ='zh_cn' where 1=1;

-- 协同
update sys_menu set hidden=1 where id='616256291005792256';
-- 功能演示
update sys_menu set hidden=1 where id='495201409763901440';
update sys_menu set hidden=1 where id='610152549789466624';
-- 回收站
update sys_menu set hidden=1 where id='473612721665867776';
-- 流程中心
-- update sys_menu set hidden=1 where id='577783476611198976';
-- 工单
update sys_menu set hidden=1 where id='472358185009676289';
-- 知识库
-- update sys_menu set hidden=1 where id='472036810977968129';
-- 合同管理
update sys_menu set hidden=1 where id='474157822892834817';
-- 运维
update sys_menu set hidden=1 where id='472036556115279872';
-- 监控
update sys_menu set hidden=1 where id='474155549546512385';
-- 设备
update sys_menu set hidden=1 where id='568076619713875968';
-- 车辆
update sys_menu set hidden=1 where id='562317041218879488';
-- 员工
-- update sys_menu set hidden=1 where id='592108832759488512';
update sys_menu set hidden=1 where id='592348823129427968';
-- 财务管理
-- update sys_menu set hidden=1 where id='475955437510852608';
-- 采购管理
-- update sys_menu set hidden=1 where id='566986193363795968';
-- 盘点
-- update sys_menu set hidden=1 where id='471985681850171393';
-- 资产字段配置，开发人员使用
update sys_menu set hidden=1 where id='487568362436558848';
update sys_menu set hidden=1 where id='487559533120454656';
-- 版本切换
update sys_menu set hidden=1 where id='578624635919138816';
-- 人员档案
update sys_menu set hidden=1 where id='623153794699493376';
-- 接口管理
update sys_menu set hidden=1 where id='622447317160558592';
