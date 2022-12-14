package com.dt.platform.generator.module.ops;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.generator.config.Config;
import com.dt.platform.ops.page.MonitorVoucherPageController;
import com.dt.platform.proxy.ops.MonitorVoucherServiceProxy;
import com.github.foxnic.generator.config.WriteMode;
import com.dt.platform.constants.db.OpsTables;
public class MonitorVoucherGtr extends BaseCodeGenerator{


    public MonitorVoucherGtr() {
        super(OpsTables.OPS_MONITOR_VOUCHER.$TABLE,MONITOR_MENU_ID);
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());

        cfg.view().search().inputLayout(
                new Object[]{
                        OpsTables.OPS_MONITOR_VOUCHER.NAME,
                        OpsTables.OPS_MONITOR_VOUCHER.ACCOUNT,
                        OpsTables.OPS_MONITOR_VOUCHER.NOTES,
                }
        );

        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);
        cfg.view().field(OpsTables.OPS_MONITOR_VOUCHER.NAME).search().fuzzySearch();
        cfg.view().field(OpsTables.OPS_MONITOR_VOUCHER.ACCOUNT).search().fuzzySearch();
        cfg.view().field(OpsTables.OPS_MONITOR_VOUCHER.NOTES).search().fuzzySearch();
        cfg.view().field(OpsTables.OPS_MONITOR_VOUCHER.ID).basic().hidden(true);
        cfg.view().field(OpsTables.OPS_MONITOR_VOUCHER.ID).table().disable(true);
        cfg.view().field(OpsTables.OPS_MONITOR_VOUCHER.CREATE_TIME).table().disable(true);
        cfg.view().field(OpsTables.OPS_MONITOR_VOUCHER.NAME).form().validate().required();
        cfg.view().field(OpsTables.OPS_MONITOR_VOUCHER.ACCOUNT).form().validate().required();
        cfg.view().field(OpsTables.OPS_MONITOR_VOUCHER.VOUCHER).form().validate().required();

        cfg.view().field(OpsTables.OPS_MONITOR_VOUCHER.NOTES).form().textArea().height(Config.textAreaHeight);
        cfg.view().formWindow().width(Config.baseFormWidth);
        cfg.view().formWindow().bottomSpace(120);

        cfg.view().form().addGroup(null,
                new Object[] {
                        OpsTables.OPS_MONITOR_VOUCHER.NAME,
                        OpsTables.OPS_MONITOR_VOUCHER.ACCOUNT,
                        OpsTables.OPS_MONITOR_VOUCHER.VOUCHER,
                        OpsTables.OPS_MONITOR_VOUCHER.NOTES,
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
        MonitorVoucherGtr g=new MonitorVoucherGtr();
        //????????????
        g.generateCode();
        //?????????????????????????????????????????????
        //g.removeByBatchId("478921035245158400");
       // g.generateMenu(MonitorVoucherServiceProxy.class, MonitorVoucherPageController.class);
    }
}
