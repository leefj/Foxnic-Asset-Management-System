package com.dt.platform.generator.module.ops;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.constants.db.OpsTables;
import com.dt.platform.constants.enums.DictEnum;
import com.dt.platform.constants.enums.ops.MonitorWarnLevelEnum;
import com.dt.platform.constants.enums.ops.MonitorWarnProcessStatusEnum;
import com.dt.platform.constants.enums.ops.OpsDbBackupStatusEnum;
import com.dt.platform.constants.enums.ops.OpsDbStatusEnum;
import com.dt.platform.domain.eam.meta.AssetMeta;
import com.dt.platform.domain.ops.DbBackupInfo;
import com.dt.platform.domain.ops.Host;
import com.dt.platform.domain.ops.ServiceInfo;
import com.dt.platform.domain.ops.meta.*;
import com.dt.platform.generator.config.Config;
import com.dt.platform.ops.page.DbInfoPageController;
import com.dt.platform.ops.page.ServiceGroupPageController;
import com.dt.platform.proxy.ops.DbInfoServiceProxy;
import com.dt.platform.proxy.ops.HostServiceProxy;
import com.dt.platform.proxy.ops.ServiceGroupServiceProxy;
import com.dt.platform.proxy.ops.ServiceInfoServiceProxy;
import com.github.foxnic.generator.config.WriteMode;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.system.meta.DictItemMeta;
import org.github.foxnic.web.proxy.system.DictItemServiceProxy;

public class OpsDbInfoGtr extends BaseCodeGenerator{


    public OpsDbInfoGtr() {
        super(OpsTables.OPS_DB_INFO.$TABLE,"478178015856033792");
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());


        cfg.getPoClassFile().addListProperty(DbBackupInfo.class,"backupInfoList","backupInfoList","backupInfoList");
        cfg.getPoClassFile().addListProperty(String.class,"backupInfoIds","backupInfoIds","backupInfoIds");
        cfg.getPoClassFile().addSimpleProperty(Host.class,"host","host","host");
        cfg.getPoClassFile().addSimpleProperty(ServiceInfo.class,"type","type","type");
        cfg.getPoClassFile().addSimpleProperty(DictItem.class,"deployModeDict","deployModeDict","deployModeDict");
        cfg.getPoClassFile().addListProperty(DictItem.class,"labelList","labelList","labelList");
        cfg.getPoClassFile().addListProperty(String.class,"labelIds","labelIds","labelIds");

        cfg.getPoClassFile().addListProperty(DictItem.class,"dataLocData","dataLocData","dataLocData");
        cfg.getPoClassFile().addListProperty(String.class,"dataLocIds","dataLocIds","dataLocIds");

        cfg.view().field(OpsTables.OPS_DB_INFO.NAME).search().fuzzySearch();
        cfg.view().field(OpsTables.OPS_DB_INFO.NOTES).search().fuzzySearch();
        cfg.view().field(OpsTables.OPS_DB_INFO.CREATE_TIME).search().range();
        cfg.view().field(OpsTables.OPS_DB_INFO.ID).basic().hidden(true);
        cfg.view().search().inputLayout(
                new Object[]{
                        DbInfoMeta.LABEL_IDS,
                        OpsTables.OPS_DB_INFO.STATUS,
                        OpsTables.OPS_DB_INFO.BACKUP_STATUS,
                        OpsTables.OPS_DB_INFO.NAME,
                },
                new Object[]{
                        OpsTables.OPS_DB_INFO.DEPLOY_MODE,
                        OpsTables.OPS_DB_INFO.CREATE_TIME,
                        OpsTables.OPS_DB_INFO.NOTES,

                }
        );

        cfg.view().search().labelWidth(1,Config.searchLabelWidth+20);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth+20);
        cfg.view().search().labelWidth(3,Config.searchLabelWidth+20);
        cfg.view().search().labelWidth(4,Config.searchLabelWidth+20);
        cfg.view().search().inputWidth(Config.searchInputWidth);

        cfg.view().search().rowsDisplay(1);
        cfg.view().field(OpsTables.OPS_DB_INFO.HOST_ID)
                .basic().label("主机名称")
                .table().sort(false)
                  .form().selectBox().queryApi(HostServiceProxy.QUERY_PAGED_LIST)
                .valueField(HostMeta.ID).textField(HostMeta.HOST_NAME)
                .toolbar(false).paging(true)
                .fillWith(DbInfoMeta.HOST).muliti(false).defaultIndex(0);

        cfg.view().field(OpsTables.OPS_DB_INFO.TYPE_ID)
                .basic().label("库类型")
                .table().sort(false)
                .form().validate().required().form().selectBox().queryApi(ServiceInfoServiceProxy.QUERY_PAGED_LIST+"?groupId=db")
                .valueField(ServiceInfoMeta.ID).textField(ServiceInfoMeta.NAME)
                .toolbar(false).paging(true)
                .fillWith(DbInfoMeta.TYPE).muliti(false).defaultIndex(0);


        cfg.view().field(OpsTables.OPS_DB_INFO.DEPLOY_MODE)
                .basic().label("部署模式")
                .table().sort(false)
                .form().validate().required().form().selectBox().queryApi(DictItemServiceProxy.QUERY_LIST+"?dictCode=ops_db_deploy_mode")
                .valueField(DictItemMeta.CODE).textField(DictItemMeta.LABEL)
                .toolbar(false).paging(false)
                .fillWith(DbInfoMeta.DEPLOY_MODE_DICT).muliti(false).defaultValue("single");



        cfg.view().field(OpsTables.OPS_DB_INFO.SELECTED_CODE).table().disable(true);

        cfg.view().field(OpsTables.OPS_DB_INFO.NOTES).form().textInput();

        cfg.view().field(OpsTables.OPS_DB_INFO.DATA_LOC).table().disable(true);

        cfg.view().field(OpsTables.OPS_DB_INFO.BACKUP_INFO).form().textArea().height(Config.textAreaHeight);
        cfg.view().field(OpsTables.OPS_DB_INFO.NAME).form().validate().required();
        cfg.view().field(OpsTables.OPS_DB_INFO.DB_SIZE).form().numberInput().defaultValue(0.0).integer();
        cfg.view().field(OpsTables.OPS_DB_INFO.STATUS).form().validate().required().form()
                 .radioBox().enumType(OpsDbStatusEnum.class).defaultIndex(0);
        cfg.view().field(OpsTables.OPS_DB_INFO.VOUCHER_STR).form().textArea().height(60);
        cfg.view().field(OpsTables.OPS_DB_INFO.BACKUP_STATUS).form().validate().required().form()
                .radioBox().enumType(OpsDbBackupStatusEnum.class).defaultIndex(0);

        cfg.view().field(OpsTables.OPS_DB_INFO.LOG_METHOD).basic().label("日志模式")
                .form().validate().required().form().radioBox().dict(DictEnum.OPS_DB_LOG_METHOD).defaultIndex(0);

        String resourceNameField="res_"+OpsTables.OPS_HOST.HOST_IP;
        cfg.view().field(resourceNameField)
                .basic().label("IP")
                .table().fillBy(DbInfoMeta.HOST, HostMeta.HOST_IP);

        cfg.view().field(DbInfoMeta.LABEL_IDS)
                .basic().label("标签")
                .table().sort(false)
                .form().selectBox().queryApi(DictItemServiceProxy.QUERY_LIST+"?dictCode=ops_db_label")
                               .paging(false).filter(false).toolbar(false)
                .valueField(ServiceInfoMeta.ID).textField(ServiceInfoMeta.NAME)
                .toolbar(false).paging(false)
                        .valueField(DictItemMeta.CODE).
                        textField(DictItemMeta.LABEL).
                fillWith(DbInfoMeta.LABEL_LIST).muliti(true);



        cfg.view().field(DbInfoMeta.DATA_LOC_IDS)
                .basic().label("数据存放")
                .table().sort(false)
                .form().selectBox().queryApi(DictItemServiceProxy.QUERY_LIST+"?dictCode=ops_db_data_loc")
                .valueField(DictItemMeta.CODE).textField(DictItemMeta.LABEL)
                .toolbar(false).paging(false)
                .fillWith(DbInfoMeta.DATA_LOC_DATA).muliti(true);


        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);
        cfg.view().formWindow().width(Config.baseFormWidth);
        cfg.view().formWindow().bottomSpace(50);
        cfg.view().list().disableBatchDelete();
        cfg.view().form().addGroup(null,
                new Object[] {
                        OpsTables.OPS_DB_INFO.HOST_ID,
                        OpsTables.OPS_DB_INFO.NAME,
                        OpsTables.OPS_DB_INFO.STATUS,
                        OpsTables.OPS_DB_INFO.TYPE_ID,
                        DbInfoMeta.LABEL_IDS,
                },
                new Object[] {

                        OpsTables.OPS_DB_INFO.DEPLOY_MODE,
                        OpsTables.OPS_DB_INFO.LOG_METHOD,
                        OpsTables.OPS_DB_INFO.DB_PORT,
                        OpsTables.OPS_DB_INFO.DB_SIZE,

                }
        );

        cfg.view().form().addGroup(null,
                new Object[] {
                        OpsTables.OPS_DB_INFO.NOTES,
                }
        );

        cfg.view().form().addGroup("用户及凭证",
                new Object[]{
                        OpsTables.OPS_DB_INFO.ADMIN_USER_LIST,
                        OpsTables.OPS_DB_INFO.APP_USER_LIST,
                },
                new Object[] {
                        OpsTables.OPS_DB_INFO.OPS_USER_LIST,
                        OpsTables.OPS_DB_INFO.OTHER_USER_LIST,
                }

        );
        cfg.view().form().addGroup(null,
                new Object[]{
                        OpsTables.OPS_DB_INFO.USER_USE_INFO,
                        OpsTables.OPS_DB_INFO.VOUCHER_STR,
                }
        );


        cfg.view().form().addGroup("备份信息",
                new Object[]{
                        OpsTables.OPS_DB_INFO.BACKUP_STATUS,
                },
                new Object[]{
                        DbInfoMeta.DATA_LOC_IDS,
                }
        );

        cfg.view().form().addGroup(null,
                new Object[]{
                        OpsTables.OPS_DB_INFO.BACKUP_STRATEGY,
                        OpsTables.OPS_DB_INFO.TOOL_STRATEGY,
                },
                new Object[] {
                        OpsTables.OPS_DB_INFO.DISASTER_RECOVERY_STRATEGY,
                        OpsTables.OPS_DB_INFO.CLEAR_STRATEGY,
                }
        );


        cfg.view().form().addGroup(null,
                new Object[]{
                        OpsTables.OPS_DB_INFO.BACKUP_INFO,
                }
        );


        cfg.view().list().operationColumn().addActionButton("备份记录","openBackupWindow",null,"ops_db_info:backup");
        cfg.view().list().operationColumn().addActionButton("密文箱","boxWindow",null,"ops_db_info:box");


        cfg.view().form().addPage("备份情况","backInfoList");

      //  cfg.view().list().operationColumn().addActionButton("备份记录","backupRecord","backupRecord","ops_auto_task:check");

        //文件生成覆盖模式
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.IGNORE) //服务与接口
                .setControllerAndAgent(WriteMode.IGNORE) //Rest
                .setPageController(WriteMode.IGNORE) //页面控制器
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //表单HTML页
                .setListPage(WriteMode.COVER_EXISTS_FILE)//列表HTML页
                .setExtendJsFile(WriteMode.IGNORE); //列表HTML页
        //生成代码
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        OpsDbInfoGtr g=new OpsDbInfoGtr();
        //生成代码
        g.generateCode();

        //移除之前生成的菜单，视情况执行
       // g.removeByBatchId("474335473372758016");
      ///  g.generateMenu(DbInfoServiceProxy.class, DbInfoPageController.class);
    }
}
