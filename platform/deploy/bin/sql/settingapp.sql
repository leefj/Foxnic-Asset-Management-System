-- foxnic config
update sys_config set value='2.5.0' where code='system.version.code';
update sys_config set value='PROD' where code='system.version.name';
update sys_config set value='PROD' where code='system.version.type';
update sys_config set value='PROD' where code='system.version.type';
update sys_config set value='0' where code='system.index.portal.enable';
update sys_config set value='0' where code='system.external.portal.enable';

update sys_config set value='{account:"",password:"",captcha:""}'  where code='system.login.default';
update sys_config set value=0 where code='system.login.captcha.any';
update sys_config set value='123456' where value='system.password.default';
update sys_config set value=0 where code='eam.assetDataPermissions';

update sys_config set value=1 where code='eam.assetDirectUpdateMode';
update sys_config set value=0 where code='eam.assetImportAssetCodeKeep';
update sys_config set value=0 where code='eam.assetImportDataReplenish';
update sys_config set value=0 where code='eam.assetSerialNumberIsUnique';
update sys_config set value=0 where code='eam.assetStatusColumnDisable';

update eam_asset_label set font_path='/app/app/bin/msyh.ttf' where id='1';

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



-- #########
-- 授权管理
delete from sys_menu where id='558628496125984768';

delete from sys_menu where id='616256291005792256';
delete from sys_menu where id='495201409763901440';
delete from sys_menu where id='610152549789466624';
delete from sys_menu where id='473612721665867776';
-- delete from sys_menu where id='577783476611198976';
delete from sys_menu where id='472358185009676289';
-- delete from sys_menu where id='472036810977968129';
delete from sys_menu where id='474157822892834817';
delete from sys_menu where id='472036556115279872';
delete from sys_menu where id='474155549546512385';
delete from sys_menu where id='568076619713875968';
delete from sys_menu where id='562317041218879488';
-- delete from sys_menu where id='592108832759488512';
delete from sys_menu where id='592348823129427968';
-- delete from sys_menu where id='475955437510852608';
-- delete from sys_menu where id='566986193363795968';
-- delete from sys_menu where id='471985681850171393';
delete from sys_menu where id='487568362436558848';
delete from sys_menu where id='487559533120454656';
delete from sys_menu where id='578624635919138816';


