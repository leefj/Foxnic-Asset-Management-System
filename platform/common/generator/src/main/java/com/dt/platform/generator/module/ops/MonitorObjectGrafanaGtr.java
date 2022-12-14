package com.dt.platform.generator.module.ops;

import com.dt.platform.constants.db.OpsTables;
import com.dt.platform.domain.ops.MonitorObjectGrafana;
import com.dt.platform.generator.config.Config;
import com.dt.platform.ops.page.MonitorObjectGrafanaPageController;
import com.dt.platform.proxy.ops.MonitorObjectGrafanaServiceProxy;
import com.github.foxnic.generator.config.WriteMode;

public class MonitorObjectGrafanaGtr extends BaseCodeGenerator{


    public MonitorObjectGrafanaGtr() {
        super(OpsTables.OPS_MONITOR_OBJECT_GRAFANA.$TABLE,MONITOR_MENU_ID);
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());

        cfg.view().search().inputLayout(
                new Object[]{
                        OpsTables.OPS_MONITOR_OBJECT_GRAFANA.CODE,
                        OpsTables.OPS_MONITOR_OBJECT_GRAFANA.NAME,

                }
        );

        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);



        cfg.view().field(OpsTables.OPS_MONITOR_OBJECT_GRAFANA.ID).basic().hidden(true);
        cfg.view().field(OpsTables.OPS_MONITOR_OBJECT_GRAFANA.ID).table().disable(true);
        cfg.view().field(OpsTables.OPS_MONITOR_OBJECT_GRAFANA.CREATE_TIME).table().disable(true);
        cfg.view().field(OpsTables.OPS_MONITOR_OBJECT_GRAFANA.NAME).search().fuzzySearch();
        cfg.view().field(OpsTables.OPS_MONITOR_OBJECT_GRAFANA.CODE).search().fuzzySearch();

        cfg.view().field(OpsTables.OPS_MONITOR_OBJECT_GRAFANA.NAME).form().validate().required();
        cfg.view().field(OpsTables.OPS_MONITOR_OBJECT_GRAFANA.CODE).form().validate().required();
        cfg.view().field(OpsTables.OPS_MONITOR_OBJECT_GRAFANA.URL).form().validate().required();
        cfg.view().field(OpsTables.OPS_MONITOR_OBJECT_GRAFANA.NOTES).form().textArea().height(Config.textAreaHeight);
       // cfg.view().list().disableBatchDelete();

        cfg.view().form().labelWidth(70);
        cfg.view().formWindow().width(Config.baseFormWidth);;
        cfg.view().formWindow().bottomSpace(20);
        cfg.view().form().addGroup(null,
                new Object[] {
                        OpsTables.OPS_MONITOR_OBJECT_GRAFANA.NAME,
                        OpsTables.OPS_MONITOR_OBJECT_GRAFANA.CODE,
                        OpsTables.OPS_MONITOR_OBJECT_GRAFANA.URL,
                        OpsTables.OPS_MONITOR_OBJECT_GRAFANA.NOTES,
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
        MonitorObjectGrafanaGtr g=new MonitorObjectGrafanaGtr();
        //????????????
        g.generateCode();
        //?????????????????????????????????????????????
        //g.removeByBatchId("478921035245158400");
       // g.generateMenu(MonitorObjectGrafanaServiceProxy.class, MonitorObjectGrafanaPageController.class);
    }
}
