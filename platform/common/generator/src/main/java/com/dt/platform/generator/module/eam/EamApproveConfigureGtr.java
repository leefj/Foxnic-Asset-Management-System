package com.dt.platform.generator.module.eam;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.constants.enums.DictEnum;
import com.dt.platform.constants.enums.common.StatusEnableEnum;
import com.dt.platform.constants.enums.eam.AssetApprovalTypeEnum;
import com.dt.platform.constants.enums.eam.AssetHandleStatusEnum;
import com.dt.platform.eam.page.ApproveConfigurePageController;
import com.dt.platform.generator.config.Config;
import com.dt.platform.proxy.eam.ApproveConfigureServiceProxy;
import com.github.foxnic.generator.config.WriteMode;

public class EamApproveConfigureGtr extends BaseCodeGenerator{
    public EamApproveConfigureGtr() {
        super(EAMTables.EAM_APPROVE_CONFIGURE.$TABLE,BASIC_DATA_MENU_ID);
    }

    public void generateCode() throws Exception {

        System.out.println(this.getClass().getName());

        cfg.view().field(EAMTables.EAM_APPROVE_CONFIGURE.ID).basic().hidden(true);

        cfg.view().field(EAMTables.EAM_APPROVE_CONFIGURE.NAME).search().fuzzySearch();
        cfg.view().field(EAMTables.EAM_APPROVE_CONFIGURE.NOTES).search().fuzzySearch();


        cfg.view().field(EAMTables.EAM_APPROVE_CONFIGURE.NAME).form().validate().required();

        cfg.view().field(EAMTables.EAM_APPROVE_CONFIGURE.APPROVAL_TYPE)
                .form().validate().required().form().selectBox().enumType(AssetApprovalTypeEnum.class);


        cfg.view().search().inputLayout(
                new Object[]{
                        EAMTables.EAM_APPROVE_CONFIGURE.NAME,
                        EAMTables.EAM_APPROVE_CONFIGURE.APPROVAL_STATUS,
                        EAMTables.EAM_APPROVE_CONFIGURE.NOTES

                }
        );

        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().labelWidth(3,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);

        cfg.view().field(EAMTables.EAM_APPROVE_CONFIGURE.APPROVAL_STATUS)
                .form().validate().required().form().radioBox().enumType(StatusEnableEnum.class);

        cfg.view().field(EAMTables.EAM_APPROVE_CONFIGURE.APPROVAL_TYPE)
                .form().validate().required().form().selectBox().enumType(AssetApprovalTypeEnum.class);

        cfg.view().field(EAMTables.EAM_APPROVE_CONFIGURE.NOTES)
                .form().textArea().height(Config.textAreaHeight);

        //??????????????????
        cfg.view().formWindow().width("800px");
        cfg.view().formWindow().bottomSpace(250);
        cfg.view().form().addGroup(null,
                new Object[] {
                        EAMTables.EAM_APPROVE_CONFIGURE.NAME,
                        EAMTables.EAM_APPROVE_CONFIGURE.APPROVAL_STATUS
                },
                new Object[] {

                        EAMTables.EAM_APPROVE_CONFIGURE.APPROVAL_TYPE,

                }
        );

        cfg.view().form().addGroup(null,
                new Object[] {
                        EAMTables.EAM_APPROVE_CONFIGURE.NOTES,

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
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        EamApproveConfigureGtr g=new EamApproveConfigureGtr();
        //????????????
         g.generateCode();


        //????????????
         //g.removeByBatchId("");
       // AssetAppro
        //g.generateMenu(ApproveConfigureServiceProxy.class, ApproveConfigurePageController.class);
    }
}
