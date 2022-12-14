package com.dt.platform.generator.module.ops;

import com.dt.platform.constants.db.OpsTables;
import com.dt.platform.domain.ops.MonitorObjectTpl;
import com.dt.platform.generator.config.Config;
import com.dt.platform.ops.page.MonitorObjectTplPageController;
import com.dt.platform.proxy.ops.MonitorObjectTplServiceProxy;
import com.github.foxnic.generator.config.WriteMode;

public class MonitorObjectTplGtr extends BaseCodeGenerator{


    public MonitorObjectTplGtr() {
        super(OpsTables.OPS_MONITOR_OBJECT_TPL.$TABLE,MONITOR_MENU_ID);
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());

        cfg.view().search().inputLayout(
                new Object[]{
                        OpsTables.OPS_MONITOR_OBJECT_TPL.CODE,
                        OpsTables.OPS_MONITOR_OBJECT_TPL.SOURCE,
                }
        );

        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);



        cfg.view().field(OpsTables.OPS_MONITOR_OBJECT_TPL.ID).basic().hidden(true);
        cfg.view().field(OpsTables.OPS_MONITOR_OBJECT_TPL.ID).table().disable(true);
        cfg.view().field(OpsTables.OPS_MONITOR_OBJECT_TPL.CREATE_TIME).table().disable(true);



        cfg.view().field(OpsTables.OPS_MONITOR_OBJECT_TPL.CODE).search().fuzzySearch();

        cfg.view().field(OpsTables.OPS_MONITOR_OBJECT_TPL.SOURCE).search().fuzzySearch();
        cfg.view().field(OpsTables.OPS_MONITOR_OBJECT_TPL.CODE).form().validate().required();



       // cfg.view().list().disableBatchDelete();
        cfg.view().form().labelWidth(70);

        cfg.view().formWindow().width(Config.baseFormWidth);;
        cfg.view().formWindow().bottomSpace(20);
        cfg.view().form().addGroup(null,
                new Object[] {
                        OpsTables.OPS_MONITOR_OBJECT_TPL.CODE,
                        OpsTables.OPS_MONITOR_OBJECT_TPL.MODEL_ID,
                        OpsTables.OPS_MONITOR_OBJECT_TPL.SOURCE,
                }

        );

        //????????????????????????
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.COVER_EXISTS_FILE) //???????????????
                .setControllerAndAgent(WriteMode.COVER_EXISTS_FILE) //Rest
                .setPageController(WriteMode.COVER_EXISTS_FILE) //???????????????
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //??????HTML???
                .setListPage(WriteMode.COVER_EXISTS_FILE)//??????HTML???
                .setExtendJsFile(WriteMode.COVER_EXISTS_FILE); //??????HTML???
        //????????????
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        MonitorObjectTplGtr g=new MonitorObjectTplGtr();
        //????????????
        g.generateCode();
        //?????????????????????????????????????????????
        //g.removeByBatchId("478921035245158400");
    //    g.generateMenu(MonitorObjectTplServiceProxy.class, MonitorObjectTplPageController.class);
    }
}
