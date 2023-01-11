package com.dt.platform.relation.modules;


import com.dt.platform.constants.db.EAMTables;


import com.dt.platform.constants.db.OpsTables;
import com.dt.platform.domain.eam.meta.AssetCollectionReturnMeta;
import com.dt.platform.domain.eam.meta.AssetMeta;
import com.dt.platform.domain.eam.meta.MaintainTaskMeta;
import com.dt.platform.domain.eam.meta.TplFileMeta;
import com.dt.platform.domain.ops.*;
import com.dt.platform.domain.ops.meta.*;
import com.github.foxnic.dao.relation.RelationManager;
import org.checkerframework.checker.guieffect.qual.SafeType;
import org.github.foxnic.web.constants.db.FoxnicWeb;
import org.github.foxnic.web.domain.system.UserTenant;

import java.util.Iterator;

public class OpsRelationManager extends RelationManager {
    @Override
    protected void config() {

        this.setupRelations();
        this.setupProperties();
        this.setupOpsHost();
        this.setupOpsDbInstance();
        this.setupOpsServiceCategory();
        this.setupOpsServiceInfo();
        this.setupVoucherOwner();
        this.setupVoucher();
        this.setupVoucherPriv();
        this.setupInfoSystem();
        this.setupTplFile();
        this.setupPerson();
        this.setupCertificate();
        this.setupCertificateItem();
        this.setupSoftwareBaseVersion();
        this.setupMonitorObject();
        this.setupMonitorModel();
        this.setupIpAddressRange();
        this.setupSystemConsoleInfo();
        this.setupAutoNode();
        this.setupAutoAction();
        this.setupAutoTaskMlog();
        this.setupAutoTasklog();
        this.setupAutoTask();
        this.setupAutoBatch();
        this.setupDbInfo();
        this.setupDbBackupInfo();
        this.setupDbHostExcude();
        this.setupDbBackupRecord();
        this.setupSafetyBaseLine();
        this.setupCiphertextConf();
        this.setupOpsCmdbLog();
        this.setupOpsCmdbAttribute();
        this.setupOpsCmdbModel();
        this.setupOpsCmdbModelValue();

    }


    public void setupOpsCmdbModel() {

        this.property(CmdbModelMeta.CMDB_OBJ_ATTRIBUTE_LIST_PROP)
                .using(OpsTables.OPS_CMDB_MODEL.ID).join(OpsTables.OPS_CMDB_OBJ_ATTRIBUTE.MODEL_ID);


        this.property(CmdbModelMeta.CMDB_MODEL_V_LIST_PROP)
                .using(OpsTables.OPS_CMDB_MODEL.ID).join(OpsTables.OPS_CMDB_MODEL_V.MODEL_ID);


        this.property(CmdbModelMeta.CMDB_MODEL_PARENT_PROP)
                .using(OpsTables.OPS_CMDB_MODEL.PARENT_ID).join(OpsTables.OPS_CMDB_MODEL.ID);

        this.property(CmdbModelMeta.CMDB_OBJ_ATTRIBUTE_PARENT_LIST_PROP)
                .using(OpsTables.OPS_CMDB_MODEL.PARENT_ID).join(OpsTables.OPS_CMDB_MODEL.ID)
           .using(OpsTables.OPS_CMDB_MODEL.ID).join(OpsTables.OPS_CMDB_OBJ_ATTRIBUTE.MODEL_ID);



    }




    public void setupOpsCmdbModelValue() {

        this.property(CmdbModelVMeta.CMDB_MODEL_PROP)
                .using(OpsTables.OPS_CMDB_MODEL_V.MODEL_ID).join(OpsTables.OPS_CMDB_MODEL.ID);

        this.property(CmdbModelVMeta.CMDB_OBJ_ATTRIBUTE_LIST_PROP)
                .using(OpsTables.OPS_CMDB_MODEL_V.MODEL_ID).join(OpsTables.OPS_CMDB_MODEL.ID)
              .using(OpsTables.OPS_CMDB_MODEL.ID).join(OpsTables.OPS_CMDB_OBJ_ATTRIBUTE.ID);

        this.property(CmdbModelVMeta.CMDB_MODEL_V_PARENT_PROP)
                .using(OpsTables.OPS_CMDB_MODEL_V.PID).join(OpsTables.OPS_CMDB_MODEL_V.ID);

    }


    public void setupOpsCmdbAttribute() {

        this.property(CmdbObjAttributeMeta.CMDB_MODEL_PROP)
                .using(OpsTables.OPS_CMDB_OBJ_ATTRIBUTE.MODEL_ID).join(OpsTables.OPS_CMDB_LOG.ID);

        this.property(CmdbObjAttributeMeta.CMDB_ATTRIBUTE_C_PROP)
                .using(OpsTables.OPS_CMDB_OBJ_ATTRIBUTE.ATTRIBUTE_COL_FILL).join(OpsTables.OPS_CMDB_ATTRIBUTE_C.CODE);

    }


    public void setupOpsCmdbLog() {
        this.property(CmdbLogMeta.CMDB_MODEL_PROP)
                .using(OpsTables.OPS_CMDB_LOG.ID).join(OpsTables.OPS_CMDB_MODEL.ID);

        this.property(CmdbLogMeta.CMDB_MODEL_V_PROP)
                .using(OpsTables.OPS_CMDB_LOG.ID).join(OpsTables.OPS_CMDB_MODEL_V.ID);
    }


    public void setupCiphertextConf() {
        this.property(CiphertextConfMeta.USER_PROP)
                .using(OpsTables.OPS_CIPHERTEXT_CONF.USER_ID).join(FoxnicWeb.HRM_EMPLOYEE.ID);

        this.property(CiphertextConfMeta.BOX_PROP)
                .using(OpsTables.OPS_CIPHERTEXT_CONF.BOX_ID).join(OpsTables.OPS_CIPHERTEXT_BOX.ID);

    }

    public void setupSafetyBaseLine() {
        this.property(SafetyBaselineMeta.BASE_TYPE_PROP)
                .using(OpsTables.OPS_SAFETY_BASELINE.BASE_TYPE_ID).join(OpsTables.OPS_SOFTWARE_BASE_TYPE.ID);


    }


    public void setupDbBackupRecord() {

        this.property(DbBackupRecordMeta.DB_PROP)
                .using(OpsTables.OPS_DB_BACKUP_RECORD.DB_ID).join(OpsTables.OPS_DB_INFO.ID);


        this.property(DbBackupRecordMeta.DB_TYPE_PROP)
                .using(OpsTables.OPS_DB_BACKUP_RECORD.DB_ID).join(OpsTables.OPS_DB_INFO.ID)
               .using(OpsTables.OPS_DB_INFO.TYPE_ID).join(OpsTables.OPS_SERVICE_INFO.ID);


        this.property(DbBackupRecordMeta.HOST_PROP)
                .using(OpsTables.OPS_DB_BACKUP_RECORD.DB_ID).join(OpsTables.OPS_DB_INFO.ID)
                .using(OpsTables.OPS_DB_INFO.HOST_ID).join(OpsTables.OPS_HOST.ID);

        this.property(DbBackupRecordMeta.BACKUP_INFO_PROP)
                .using(OpsTables.OPS_DB_BACKUP_RECORD.DB_BK_ID).join(OpsTables.OPS_DB_BACKUP_INFO.ID);


    }
    public void setupDbHostExcude() {
        this.property(HostExByDbMeta.HOST_PROP)
                .using(OpsTables.OPS_HOST_EX_BY_DB.HOST_ID).join(OpsTables.OPS_HOST.ID);
    }

    public void setupDbInfo() {
        this.property(DbInfoMeta.LABEL_LIST_PROP)
                .using(OpsTables.OPS_DB_INFO.ID).join(OpsTables.OPS_DB_INFO_LABEL.DB_ID)
                .using(OpsTables.OPS_DB_INFO_LABEL.LABEL).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
                .condition("dict_code='ops_db_label'");

        this.property(DbInfoMeta.DEPLOY_MODE_DICT_PROP)
                .using(OpsTables.OPS_DB_INFO.DEPLOY_MODE).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
                .condition("dict_code='ops_db_deploy_mode'");

        this.property(DbInfoMeta.DATA_LOC_DATA_PROP)
                .using(OpsTables.OPS_DB_INFO.ID).join(OpsTables.OPS_DB_DATA_LOC.DB_INFO_ID)
                .using(OpsTables.OPS_DB_DATA_LOC.LOC_ID).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
                .condition("dict_code='ops_db_data_loc'");



        this.property(DbInfoMeta.BACKUP_INFO_LIST_PROP)
                .using(OpsTables.OPS_DB_INFO.ID).join(OpsTables.OPS_DB_BACKUP_INFO.DATABASE_ID);

        this.property(DbInfoMeta.HOST_PROP)
                .using(OpsTables.OPS_DB_INFO.HOST_ID).join(OpsTables.OPS_HOST.ID);

        this.property(DbInfoMeta.TYPE_PROP)
                .using(OpsTables.OPS_DB_INFO.TYPE_ID).join(OpsTables.OPS_SERVICE_INFO.ID);

    }

    public void setupDbBackupInfo() {

        this.property(DbBackupInfoMeta.HOST_PROP)
                .using(OpsTables.OPS_DB_BACKUP_INFO.DATABASE_ID).join(OpsTables.OPS_DB_INFO.ID)
                .using(OpsTables.OPS_DB_INFO.HOST_ID).join(OpsTables.OPS_HOST.ID);

        this.property(DbBackupInfoMeta.DB_PROP)
                .using(OpsTables.OPS_DB_BACKUP_INFO.DATABASE_ID).join(OpsTables.OPS_DB_INFO.ID);

        this.property(DbBackupInfoMeta.DB_TYPE_PROP)
                .using(OpsTables.OPS_DB_BACKUP_INFO.DATABASE_ID).join(OpsTables.OPS_DB_INFO.ID)
                .using(OpsTables.OPS_DB_INFO.TYPE_ID).join(OpsTables.OPS_SERVICE_INFO.ID);


    }

    public void setupAutoBatch() {
        this.property(AutoBatchMeta.NODE_LIST_PROP)
                .using(OpsTables.OPS_AUTO_BATCH.ID).join(OpsTables.OPS_AUTO_NODE_SELECT.OWNER_ID)
                .using(OpsTables.OPS_AUTO_NODE_SELECT.NODE_ID).join(OpsTables.OPS_AUTO_NODE.ID);

    }
    public void setupAutoTask() {
        this.property(AutoTaskMeta.ACTION_PROP)
                .using(OpsTables.OPS_AUTO_TASK.ACTION_ID).join(OpsTables.OPS_AUTO_ACTION.ID);

        this.property(AutoTaskMeta.GROUP_PROP)
                .using(OpsTables.OPS_AUTO_TASK.GROUP_ID).join(OpsTables.OPS_AUTO_GROUP.ID);

        this.property(AutoTaskMeta.BATCH_PROP)
                .using(OpsTables.OPS_AUTO_TASK.BATCH_ID).join(OpsTables.OPS_AUTO_BATCH.ID);

        this.property(AutoTaskMeta.NODE_LIST_PROP)
                .using(OpsTables.OPS_AUTO_TASK.ID).join(OpsTables.OPS_AUTO_NODE_SELECT.OWNER_ID)
               .using(OpsTables.OPS_AUTO_NODE_SELECT.NODE_ID).join(OpsTables.OPS_AUTO_NODE.ID);

    }

    public void setupAutoTaskMlog() {
        this.property(AutoTaskMLogMeta.LOG_LIST_PROP)
                .using(OpsTables.OPS_AUTO_TASK_M_LOG.ID).join(OpsTables.OPS_AUTO_TASK_LOG.M_LOG_ID);


        this.property(AutoTaskMLogMeta.TASK_PROP)
                .using(OpsTables.OPS_AUTO_TASK_M_LOG.TASK_ID).join(OpsTables.OPS_AUTO_TASK.ID);


        this.property(AutoTaskMLogMeta.ACTION_PROP)
                .using(OpsTables.OPS_AUTO_TASK_M_LOG.ACTION_ID).join(OpsTables.OPS_AUTO_ACTION.ID);

    }
    public void setupAutoTasklog() {

        this.property(AutoTaskLogMeta.TASK_PROP)
                .using(OpsTables.OPS_AUTO_TASK_LOG.TASK_ID).join(OpsTables.OPS_AUTO_TASK.ID);


        this.property(AutoTaskLogMeta.ACTION_PROP)
                .using(OpsTables.OPS_AUTO_TASK_LOG.ACTION_ID).join(OpsTables.OPS_AUTO_ACTION.ID);

        this.property(AutoTaskLogMeta.NODE_PROP)
                .using(OpsTables.OPS_AUTO_TASK_LOG.NODE_ID).join(OpsTables.OPS_AUTO_NODE.ID);

        this.property(AutoTaskLogMeta.M_LOG_PROP)
                .using(OpsTables.OPS_AUTO_TASK_LOG.M_LOG_ID).join(OpsTables.OPS_AUTO_TASK_M_LOG.ID);
    }

    public void setupAutoAction() {
        this.property(AutoActionMeta.AUTO_ACTION_FILE_LIST_PROP)
                .using(OpsTables.OPS_AUTO_ACTION.ID).join(OpsTables.OPS_AUTO_ACTION_S_FILE.OWNER_ID)
                .using(OpsTables.OPS_AUTO_ACTION_S_FILE.FILE_ID).join(OpsTables.OPS_AUTO_ACTION_FILE.ID);

        this.property(AutoActionMeta.AUTO_ACTION_SCRIPT_LIST_PROP)
                .using(OpsTables.OPS_AUTO_ACTION.ID).join(OpsTables.OPS_AUTO_ACTION_S_SCRIPT.OWNER_ID)
                .using(OpsTables.OPS_AUTO_ACTION_S_SCRIPT.SCRIPT_ID).join(OpsTables.OPS_AUTO_ACTION_SCRIPT.ID);
    }


    public void setupAutoNode() {
        this.property(AutoNodeMeta.TYPE_DICT_PROP)
                .using(OpsTables.OPS_AUTO_NODE.TYPE).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
                .condition("dict_code='ops_auto_node_type'");

        this.property(AutoNodeMeta.GROUP_PROP)
                .using(OpsTables.OPS_AUTO_NODE.GROUP_ID).join(OpsTables.OPS_AUTO_GROUP.ID);

        this.property(AutoNodeMeta.VOUCHER_PROP)
                .using(OpsTables.OPS_AUTO_NODE.VOUCHER_ID).join(OpsTables.OPS_AUTO_VOUCHER.ID);

    }

    public void setupSystemConsoleInfo() {

        this.property(SystemConsoleInfoMeta.TYPE_DICT_PROP)
                .using(OpsTables.OPS_SYSTEM_CONSOLE_INFO.TYPE).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
                .condition("dict_code='ops_system_console_type'");

    }


    public void setupIpAddressRange() {

        this.property(IpAddressRangeMeta.PROD_DICT_PROP)
                .using(OpsTables.OPS_IP_ADDRESS_RANGE.PROD_ID).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
                .condition("dict_code='ops_network_ip_prod'");


        this.property(IpAddressRangeMeta.REGION_DICT_PROP)
                .using(OpsTables.OPS_IP_ADDRESS_RANGE.REGION_ID).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
                .condition("dict_code='ops_network_ip_region'");


        this.property(IpAddressRangeMeta.CLASS_DICT_PROP)
                .using(OpsTables.OPS_IP_ADDRESS_RANGE.CLASS_ID).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
                .condition("dict_code='ops_network_ip_class'");


    }



    public void setupMonitorObject() {
        this.property(MonitorObjectMeta.GROUP_PROP)
                .using(OpsTables.OPS_MONITOR_OBJECT.GROUP_CODE).join(OpsTables.OPS_MONITOR_OBJECT_GROUP.CODE);

        this.property(MonitorObjectMeta.OBJECT_MODEL_PROP)
                .using(OpsTables.OPS_MONITOR_OBJECT.OBJECT_MODEL_ID).join(OpsTables.OPS_MONITOR_OBJECT_MODEL.ID);


    }

    public void setupMonitorModel() {

        this.property(MonitorObjectModelMeta.GROUP_PROP)
                .using(OpsTables.OPS_MONITOR_OBJECT_MODEL.GROUP_ID).join(OpsTables.OPS_MONITOR_OBJECT_GROUP.ID);

        this.property(MonitorObjectModelMeta.GRAFANA_PROP)
                .using(OpsTables.OPS_MONITOR_OBJECT_MODEL.GRAFANA_ID).join(OpsTables.OPS_MONITOR_OBJECT_GRAFANA.ID);


    }

    public void setupPerson() {

    }


    public void setupSoftwareBaseVersion() {
        this.property(SoftwareBaseVersionMeta.SOFTWARE_BASE_TYPE_PROP)
                .using(OpsTables.OPS_SOFTWARE_BASE_VERSION.SOFTWARE_TYPE_ID).join(OpsTables.OPS_SOFTWARE_BASE_TYPE.ID);

    }

    public void setupCertificate() {
        this.property(CertificateMeta.ORIGINATOR_PROP)
                .using(OpsTables.OPS_CERTIFICATE.ORIGINATOR_ID).join(FoxnicWeb.HRM_EMPLOYEE.ID);

        this.property(CertificateMeta.CERTIFICATE_TYPE_PROP)
                .using(OpsTables.OPS_CERTIFICATE.TYPE).join(OpsTables.OPS_CERTIFICATE_TYPE.ID);

        this.property(CertificateMeta.CERTIFICATE_POSITION_PROP)
                .using(OpsTables.OPS_CERTIFICATE.POSITION_ID).join(OpsTables.OPS_CERTIFICATE_POSITION.ID);

    }

    public void setupCertificateItem() {
        this.property(CertificateItemMeta.OPER_USER_PROP)
                .using(OpsTables.OPS_CERTIFICATE_ITEM.OPER_USER_ID).join(FoxnicWeb.HRM_EMPLOYEE.ID);
    }


    public void setupTplFile() {

    }



    public void setupProperties() {

    }

    private void setupRelations() {
        this.property(PersonMeta.PERSON_TYPE_DICT_PROP)
                .using(OpsTables.OPS_PERSON.PERSON_TYPE).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
                .condition("dict_code='ops_person_type'");
    }


    private void setupOpsServiceCategory() {

        //关联服务分组
        this.property(ServiceCategoryMeta.GROUP_PROP)
                .using(OpsTables.OPS_SERVICE_CATEGORY.GROUP_ID).join(OpsTables.OPS_SERVICE_GROUP.CODE);

        this.property(ServiceCategoryMeta.LABEL_LIST_PROP)
                .using(OpsTables.OPS_SERVICE_CATEGORY.ID).join(OpsTables.OPS_SERVICE_CATEGORY_LABEL.CATEGORY_ID)
                .using(OpsTables.OPS_SERVICE_CATEGORY_LABEL.LABEL_CODE).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
                .condition("dict_code='ops_service_category_label'");

    }


    private void setupOpsServiceInfo() {
        //关联服务类型
        this.property(ServiceInfoMeta.SERVICE_CATEGORY_PROP)
                .using(OpsTables.OPS_SERVICE_INFO.SERVICE_CATEGORY_ID).join(OpsTables.OPS_SERVICE_CATEGORY.ID);

        //关联服务分组
        this.property(ServiceInfoMeta.GROUP_PROP)
                .using(OpsTables.OPS_SERVICE_INFO.GROUP_ID).join(OpsTables.OPS_SERVICE_GROUP.CODE);

    }

    private void setupOpsDbInstance() {

        this.property(DbInstanceMeta.HOST_PROP)
                .using(OpsTables.OPS_DB_INSTANCE.HOST_ID).join(OpsTables.OPS_HOST.ID);

        this.property(DbInstanceMeta.DATABASE_PROP)
                .using(OpsTables.OPS_DB_INSTANCE.DATABASE_ID).join(OpsTables.OPS_SERVICE_INFO.ID);

    }



    private void setupInfoSystem() {

        this.property(InformationSystemMeta.BELONG_ORGANIZATION_PROP)
                .using(OpsTables.OPS_INFORMATION_SYSTEM.BELONG_ORG_ID).join(FoxnicWeb.HRM_ORGANIZATION.ID);

        //用户凭证
        this.property(InformationSystemMeta.VOUCHER_LIST_PROP)
                .using(OpsTables.OPS_INFORMATION_SYSTEM.ID).join(OpsTables.OPS_VOUCHER.OWNER_ID)
                .after((tag,voucherowner,voucher,m)->{
                    Iterator var2 = voucher.iterator();
                    while(var2.hasNext()) {
                        Voucher obj = (Voucher)var2.next();
                        String pwd=",******";
//                        String voucherStr=obj.getVoucher();
//                        if(voucherStr!=null&&voucherStr.length()>5){
//                            pwd=","+voucherStr.replaceFirst(voucherStr.substring(0,4),"***" )+"***";
//                        }
                        obj.setVoucher("("+obj.getUserCode()+pwd+")");
                    }
                    return voucher;
                });

        // 关联来源
        this.property(InformationSystemMeta.INFO_SYSTEM_STATUS_PROP)
                .using(OpsTables.OPS_INFORMATION_SYSTEM.STATUS).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
                .condition("dict_code='ops_system_status'");


        // 关联来源
        this.property(InformationSystemMeta.INFO_SYSTEM_GRADE_PROP)
                .using(OpsTables.OPS_INFORMATION_SYSTEM.GRADE).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
                .condition("dict_code='ops_system_grade'");

        // 关联来源
        this.property(InformationSystemMeta.INFO_SYSTEM_OPS_METHOD_PROP)
                .using(OpsTables.OPS_INFORMATION_SYSTEM.OPS_METHOD).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
                .condition("dict_code='ops_system_ops_method'");

        // 关联来源
        this.property(InformationSystemMeta.INFO_SYSTEM_DEV_METHOD_PROP)
                .using(OpsTables.OPS_INFORMATION_SYSTEM.DEV_METHOD).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
                .condition("dict_code='ops_system_dev_method'");


    }


    private void setupVoucherOwner() {
        //用户凭证
        this.property(VoucherOwnerMeta.VOUCHER_LIST_PROP)
                .using(OpsTables.OPS_VOUCHER_OWNER.ID).join(OpsTables.OPS_VOUCHER.OWNER_ID)
                .after((tag,voucherowner,voucher,m)->{
                    Iterator var2 = voucher.iterator();
                    while(var2.hasNext()) {
                        Voucher obj = (Voucher)var2.next();
                        String pwd=",******";
//                        String voucherStr=obj.getVoucher();
//                         if(voucherStr!=null&&voucherStr.length()>5){
//                             pwd=","+voucherStr.replaceFirst(voucherStr.substring(0,4),"***" )+"***";
//                         }
                        obj.setVoucher("("+obj.getUserCode()+pwd+")");
                    }
                    return voucher;
                });

        //凭证类型
        this.property(VoucherOwnerMeta.VOUCHER_CATEGORY_PROP)
                .using(OpsTables.OPS_VOUCHER_OWNER.CATEGORY_CODE).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
                .condition("dict_code='ops_voucher_type'");
    }

    public void setupVoucher() {

        //凭证类型
        this.property(VoucherMeta.VOUCHER_TYPE_PROP)
                .using(OpsTables.OPS_VOUCHER.TYPE).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
                .condition("dict_code='ops_voucher_type'");;

    }


    public void setupVoucherPriv() {

        // 关联制单人
        this.property(VoucherPrivMeta.EMPLOYEE_PROP)
                .using(OpsTables.OPS_VOUCHER_PRIV.EMPL_ID).join(FoxnicWeb.HRM_EMPLOYEE.ID);
//                .using(FoxnicWeb.HRM_EMPLOYEE.PERSON_ID).join(FoxnicWeb.HRM_PERSON.ID);

    }




    private void setupOpsHost() {

        //用户凭证
        this.property(HostMeta.VOUCHER_LIST_PROP)
                .using(OpsTables.OPS_HOST.ID).join(OpsTables.OPS_VOUCHER.OWNER_ID)
                .after((tag,voucherowner,voucher,m)->{
                    Iterator var2 = voucher.iterator();
                    while(var2.hasNext()) {
                        Voucher obj = (Voucher)var2.next();
                        String pwd=",******";
                        String voucherStr=obj.getVoucher();
//                        if(voucherStr!=null&&voucherStr.length()>5){
//                            pwd=","+voucherStr.replaceFirst(voucherStr.substring(0,4),"***" )+"***";
//                        }
                        obj.setVoucher("("+obj.getUserCode()+pwd+")");
                    }
                    return voucher;
                });

        //数据库类别
        this.property(HostMeta.HOST_DB_LIST_PROP)
                .using(OpsTables.OPS_HOST.ID).join(OpsTables.OPS_HOST_DB.HOST_ID)
                .using(OpsTables.OPS_HOST_DB.SERVICE_INFO_ID).join(OpsTables.OPS_SERVICE_INFO.ID)
        .after((tag,host,dbs,m)->{
            return dbs;
        });

        //中间件列表
        this.property(HostMeta.HOST_MIDDLEWARE_LIST_PROP)
                .using(OpsTables.OPS_HOST.ID).join(OpsTables.OPS_HOST_MID.HOST_ID)
                .using(OpsTables.OPS_HOST_MID.SERVICE_INFO_ID).join(OpsTables.OPS_SERVICE_INFO.ID);

        //操作系统列表
        this.property(HostMeta.HOST_OS_LIST_PROP)
                .using(OpsTables.OPS_HOST.ID).join(OpsTables.OPS_HOST_OS.HOST_ID)
                .using(OpsTables.OPS_HOST_OS.SERVICE_INFO_ID).join(OpsTables.OPS_SERVICE_INFO.ID);



        //所在位置
        this.property(HostMeta.POSITION_PROP)
                .using(OpsTables.OPS_HOST.POSITION_ID).join(OpsTables.OPS_HOST_POSITION.ID);

        //信息系统
        this.property(HostMeta.INFO_SYSTEM_PROP)
                .using(OpsTables.OPS_HOST.SYSTEM_ID).join(OpsTables.OPS_INFORMATION_SYSTEM.ID);

        this.property(HostMeta.BACKUP_METHOD_PROP)
                .using(OpsTables.OPS_HOST.HOST_BACKUP_METHOD).join(FoxnicWeb.SYS_DICT_ITEM.CODE)
                .condition("dict_code='ops_host_backup_method'");

    }



}
