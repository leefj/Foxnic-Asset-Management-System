package com.dt.platform.generator.module.ops;

import com.dt.platform.constants.db.OpsTables;
import com.dt.platform.constants.enums.ops.OpsCiphertextHistoryDataTypeEnum;
import com.dt.platform.constants.enums.ops.OpsCiphertextTypeEnum;
import com.dt.platform.generator.config.Config;
import com.dt.platform.ops.page.CiphertextHistoryPageController;
import com.dt.platform.proxy.ops.CiphertextHistoryServiceProxy;
import com.github.foxnic.generator.config.WriteMode;

public class OpsCiphertextHistoryBoxGtr extends BaseCodeGenerator{


    public OpsCiphertextHistoryBoxGtr() {
        super(OpsTables.OPS_CIPHERTEXT_HISTORY.$TABLE,"635020464892149760");
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());

        cfg.view().search().inputLayout(
                new Object[]{
                        OpsTables.OPS_CIPHERTEXT_HISTORY.TYPE,
                }

        );

        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);

        cfg.view().field(OpsTables.OPS_CIPHERTEXT_HISTORY.ID).basic().hidden(true);
        cfg.view().field(OpsTables.OPS_CIPHERTEXT_HISTORY.TYPE).form().selectBox().enumType(OpsCiphertextHistoryDataTypeEnum.class);

        cfg.view().field(OpsTables.OPS_CIPHERTEXT_HISTORY.BOX_TYPE).form().selectBox().enumType(OpsCiphertextTypeEnum.class);

        cfg.view().field(OpsTables.OPS_CIPHERTEXT_HISTORY.CONTENT).form().textArea().height(300);
        cfg.view().field(OpsTables.OPS_CIPHERTEXT_HISTORY.ENCRYPTION_CONTENT).form().textArea().height(300);



        cfg.view().list().disableBatchDelete();
        cfg.view().list().disableModify();
        cfg.view().list().disableCreateNew();
        cfg.view().list().disableSingleDelete();


        cfg.view().formWindow().bottomSpace(120);
        cfg.view().formWindow().width(Config.baseFormWidth);
        cfg.view().form().addGroup(null,
                new Object[] {
                        OpsTables.OPS_CIPHERTEXT_HISTORY.TYPE,
                        OpsTables.OPS_CIPHERTEXT_HISTORY.CONTENT,
                        OpsTables.OPS_CIPHERTEXT_HISTORY.ENCRYPTION_CONTENT,
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
        OpsCiphertextHistoryBoxGtr g=new OpsCiphertextHistoryBoxGtr();
        //????????????
        g.generateCode();
        //?????????????????????????????????????????????
        //g.removeByBatchId("478921035245158400");
       // g.generateMenu(CiphertextHistoryServiceProxy.class, CiphertextHistoryPageController.class);
    }
}
