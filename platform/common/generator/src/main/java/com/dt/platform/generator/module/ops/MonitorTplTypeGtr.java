package com.dt.platform.generator.module.ops;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.generator.config.Config;
import com.dt.platform.ops.page.MonitorTplTypePageController;
import com.dt.platform.proxy.ops.MonitorTplTypeServiceProxy;
import com.github.foxnic.generator.config.WriteMode;
import com.dt.platform.constants.db.OpsTables;
public class MonitorTplTypeGtr extends BaseCodeGenerator{


    public MonitorTplTypeGtr() {
        super(OpsTables.OPS_MONITOR_TPL_TYPE.$TABLE,MONITOR_MENU_ID);
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());

        cfg.view().search().inputLayout(
                new Object[]{
                        OpsTables.OPS_MONITOR_TPL_TYPE.CODE,
                        OpsTables.OPS_MONITOR_TPL_TYPE.NAME,
                        OpsTables.OPS_MONITOR_TPL_TYPE.NOTES,

                }

        );

        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);
        cfg.view().field(OpsTables.OPS_MONITOR_TPL_TYPE.NAME).search().fuzzySearch();
        cfg.view().field(OpsTables.OPS_MONITOR_TPL_TYPE.CODE).search().fuzzySearch();
        cfg.view().field(OpsTables.OPS_MONITOR_TPL_TYPE.ID).basic().hidden(true);
        cfg.view().field(OpsTables.OPS_MONITOR_TPL_TYPE.ID).table().disable(true);
        cfg.view().field(OpsTables.OPS_MONITOR_TPL_TYPE.CREATE_TIME).table().disable(true);
        cfg.view().field(OpsTables.OPS_MONITOR_TPL_TYPE.CODE).form().validate().required();

        cfg.view().field(OpsTables.OPS_MONITOR_TPL_TYPE.NAME).form().validate().required();

        cfg.view().formWindow().bottomSpace(120);
        cfg.view().formWindow().width(Config.baseFormWidth);;
        cfg.view().field(OpsTables.OPS_MONITOR_TPL_TYPE.NOTES).form().textArea().height(Config.textAreaHeight);



        cfg.view().formWindow().bottomSpace(120);

        cfg.view().form().addGroup(null,
                new Object[] {
                        OpsTables.OPS_MONITOR_TPL_TYPE.NAME,
                        OpsTables.OPS_MONITOR_TPL_TYPE.CODE,
                        OpsTables.OPS_MONITOR_TPL_TYPE.NOTES,
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
        MonitorTplTypeGtr g=new MonitorTplTypeGtr();
        //????????????
        g.generateCode();
        //?????????????????????????????????????????????
        //g.removeByBatchId("478921035245158400");
       // g.generateMenu(MonitorTplTypeServiceProxy.class, MonitorTplTypePageController.class);
    }
}
