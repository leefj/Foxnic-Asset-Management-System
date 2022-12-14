package com.dt.platform.generator.module.ops;

import com.dt.platform.constants.db.OpsTables;
import com.dt.platform.constants.enums.ops.MonitorWarnLevelEnum;
import com.dt.platform.constants.enums.ops.MonitorWarnProcessStatusEnum;
import com.dt.platform.constants.enums.ops.OpsCiphertextHistoryDataTypeEnum;
import com.dt.platform.constants.enums.ops.OpsCiphertextTypeEnum;
import com.dt.platform.domain.ops.CiphertextHistory;
import com.dt.platform.domain.ops.MonitorTpl;
import com.dt.platform.domain.ops.MonitorTplIndicator;
import com.dt.platform.generator.config.Config;
import com.dt.platform.ops.page.CiphertextBoxPageController;
import com.dt.platform.ops.page.CiphertextHistoryPageController;
import com.dt.platform.ops.page.MonitorTplTypePageController;
import com.dt.platform.proxy.ops.CiphertextBoxServiceProxy;
import com.dt.platform.proxy.ops.CiphertextHistoryServiceProxy;
import com.dt.platform.proxy.ops.MonitorTplTypeServiceProxy;
import com.github.foxnic.generator.config.WriteMode;

public class OpsCiphertextBoxGtr extends BaseCodeGenerator{


    public OpsCiphertextBoxGtr() {
        super(OpsTables.OPS_CIPHERTEXT_BOX.$TABLE,"635020464892149760");
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());

        cfg.view().search().inputLayout(
                new Object[]{
                        OpsTables.OPS_CIPHERTEXT_BOX.TYPE,
                }

        );
        cfg.view().field(OpsTables.OPS_CIPHERTEXT_BOX.ID).basic().hidden(true);
        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);


        cfg.view().field(OpsTables.OPS_CIPHERTEXT_BOX.TYPE).form().validate().required().form().selectBox().enumType(OpsCiphertextTypeEnum.class);
        cfg.view().field(OpsTables.OPS_CIPHERTEXT_BOX.NAME).form().validate().required().search().fuzzySearch();
        cfg.view().field(OpsTables.OPS_CIPHERTEXT_BOX.NOTES).form().textArea().height(Config.textAreaHeight);
        cfg.view().list().disableBatchDelete();

        cfg.view().formWindow().bottomSpace(120);
        cfg.view().formWindow().width(Config.baseFormWidth);
        cfg.view().form().addGroup(null,
                new Object[] {
                        OpsTables.OPS_CIPHERTEXT_BOX.NAME,
                        OpsTables.OPS_CIPHERTEXT_BOX.TYPE,
                        OpsTables.OPS_CIPHERTEXT_BOX.NOTES,
                }
        );

        //????????????????????????
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.IGNORE) //???????????????
                .setControllerAndAgent(WriteMode.IGNORE) //Rest
                .setPageController(WriteMode.COVER_EXISTS_FILE) //???????????????
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //??????HTML???
                .setListPage(WriteMode.COVER_EXISTS_FILE)//??????HTML???
                .setExtendJsFile(WriteMode.COVER_EXISTS_FILE); //??????HTML???
        //????????????
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        OpsCiphertextBoxGtr g=new OpsCiphertextBoxGtr();
        //????????????
        g.generateCode();
        //?????????????????????????????????????????????
        //g.removeByBatchId("478921035245158400");
       // g.generateMenu(CiphertextBoxServiceProxy.class, CiphertextBoxPageController.class);
    }
}
