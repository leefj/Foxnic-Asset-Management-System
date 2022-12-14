package com.dt.platform.generator.module.ops;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.constants.db.OpsTables;
import com.dt.platform.domain.ops.MonitorNodeGroup;
import com.dt.platform.generator.config.Config;
import com.dt.platform.ops.page.MonitorNodeGroupPageController;
import com.dt.platform.proxy.ops.MonitorNodeGroupServiceProxy;
import com.github.foxnic.generator.config.WriteMode;

public class MonitorNodeGroupGtr extends BaseCodeGenerator{


    public MonitorNodeGroupGtr() {
        super(OpsTables.OPS_MONITOR_NODE_GROUP.$TABLE,MONITOR_MENU_ID);
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());


        cfg.view().search().inputLayout(
                new Object[]{
                        OpsTables.OPS_MONITOR_NODE_GROUP.NAME,
                        OpsTables.OPS_MONITOR_NODE_GROUP.NOTES,

                }

        );

        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);

        cfg.view().field(OpsTables.OPS_MONITOR_NODE_GROUP.NAME).search().fuzzySearch();
        cfg.view().field(OpsTables.OPS_MONITOR_NODE_GROUP.NOTES).search().fuzzySearch();

        cfg.view().field(OpsTables.OPS_MONITOR_NODE_GROUP.ID).basic().hidden(true);
        cfg.view().field(OpsTables.OPS_MONITOR_NODE_GROUP.ID).table().disable(true);
        cfg.view().field(OpsTables.OPS_MONITOR_NODE_GROUP.CREATE_TIME).table().disable(true);





        cfg.view().field(OpsTables.OPS_MONITOR_NODE_GROUP.NAME).form().validate().required();

       // cfg.view().list().disableBatchDelete();

        cfg.view().formWindow().bottomSpace(120);
        cfg.view().field(OpsTables.OPS_MONITOR_NODE_GROUP.NOTES).form().textArea().height(Config.textAreaHeight);

        cfg.view().formWindow().width(Config.baseFormWidth);
        cfg.view().form().addGroup(null,
                new Object[] {
                        OpsTables.OPS_MONITOR_NODE_GROUP.NAME,
                        OpsTables.OPS_MONITOR_NODE_GROUP.NOTES,
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
        MonitorNodeGroupGtr g=new MonitorNodeGroupGtr();
        //????????????
        g.generateCode();
        //?????????????????????????????????????????????
        //g.removeByBatchId("478921035245158400");
        //g.generateMenu(MonitorNodeGroupServiceProxy.class, MonitorNodeGroupPageController.class);
    }
}
