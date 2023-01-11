package com.dt.platform.generator.module.ops;

import com.dt.platform.constants.db.OpsTables;
import com.dt.platform.constants.enums.ops.MonitorEnableEnum;
import com.dt.platform.domain.ops.MonitorTplGraph;
import com.dt.platform.domain.ops.MonitorTplIndicator;
import com.dt.platform.domain.ops.MonitorTplType;
import com.dt.platform.domain.ops.meta.MonitorTplMeta;
import com.dt.platform.domain.ops.meta.MonitorTplTypeMeta;
import com.dt.platform.generator.config.Config;
import com.dt.platform.proxy.ops.MonitorTplTypeServiceProxy;
import com.github.foxnic.generator.config.WriteMode;

public class OpsDbLocGtr extends BaseCodeGenerator{


    public OpsDbLocGtr() {
        super(OpsTables.OPS_DB_DATA_LOC.$TABLE,MONITOR_MENU_ID);
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());

        //node type

        cfg.view().search().inputLayout(
                new Object[]{
                        OpsTables.OPS_DB_DATA_LOC.LOC_ID,
                }
        );

        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);



        cfg.view().formWindow().width(Config.baseFormWidth);;
        cfg.view().form().addGroup(null,
                new Object[] {
                        OpsTables.OPS_DB_DATA_LOC.LOC_ID,
                }
        );

        //文件生成覆盖模式
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.COVER_EXISTS_FILE) //服务与接口
                .setControllerAndAgent(WriteMode.COVER_EXISTS_FILE) //Rest
                .setPageController(WriteMode.COVER_EXISTS_FILE) //页面控制器
                .setBpmEventAdaptor(WriteMode.IGNORE) //页面控制器
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //表单HTML页
                .setListPage(WriteMode.COVER_EXISTS_FILE)//列表HTML页
                .setExtendJsFile(WriteMode.COVER_EXISTS_FILE); //列表HTML页
        //生成代码
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        OpsDbLocGtr g=new OpsDbLocGtr();
        //生成代码
        g.generateCode();

        //移除之前生成的菜单，视情况执行
       // g.generateMenu(MonitorTplServiceProxy.class, MonitorTplPageController.class);
//        delete from sys_menu_resource where resource_id in (select id from sys_resourze where batch_id='541696318553194496');
//        delete from sys_role_menu where menu_id in (select id from sys_menu where batch_id='541696318553194496');
//        delete from sys_menu where batch_id='541696318553194496';
//        delete from sys_resourze where batch_id='541696318553194496'

    }
}
