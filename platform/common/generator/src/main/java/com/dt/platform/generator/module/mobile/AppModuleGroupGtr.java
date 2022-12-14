package com.dt.platform.generator.module.mobile;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.constants.db.MobileTables;
import com.dt.platform.constants.db.OpsTables;
import com.dt.platform.constants.enums.common.StatusEnableEnum;

import com.dt.platform.constants.enums.mobile.moduleGroupTypeEnum;
import com.dt.platform.domain.mobile.ModuleGroup;
import com.dt.platform.domain.mobile.ModuleInfo;
import com.dt.platform.generator.config.Config;


import com.dt.platform.mobile.page.ModuleGroupPageController;
import com.dt.platform.proxy.mobile.ModuleGroupServiceProxy;
import com.github.foxnic.generator.config.WriteMode;


public class AppModuleGroupGtr extends BaseCodeGenerator {

    public AppModuleGroupGtr() {
        super(MobileTables.APP_MODULE_GROUP.$TABLE,BASIC_MOBILE);
    }
    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());
        cfg.view().field(MobileTables.APP_MODULE_GROUP.ID).basic().hidden(true);
        cfg.getPoClassFile().addListProperty(ModuleInfo.class,"moduleInfoList","ModuleInfo","ModuleInfo");
        cfg.view().field(MobileTables.APP_MODULE_GROUP.NAME).search().fuzzySearch();
        cfg.view().search().inputLayout(
                new Object[]{
                        MobileTables.APP_MODULE_GROUP.TYPE,
                        MobileTables.APP_MODULE_GROUP.STATUS,
                        MobileTables.APP_MODULE_GROUP.NAME,
                }
        );
        cfg.view().field(MobileTables.APP_MODULE_GROUP.STATUS).form().validate().required().form()
                .radioBox().enumType(StatusEnableEnum.class).defaultIndex(0);
        cfg.view().field(MobileTables.APP_MODULE_GROUP.TYPE).form().validate().required().form()
                .selectBox().enumType(moduleGroupTypeEnum.class).defaultIndex(0);
        cfg.view().field(MobileTables.APP_MODULE_GROUP.CODE).form().validate().required();
        cfg.view().field(MobileTables.APP_MODULE_GROUP.LABEL).form().validate().required();
        cfg.view().field(MobileTables.APP_MODULE_GROUP.NAME).form().validate().required();
        cfg.view().formWindow().bottomSpace(80);

        cfg.view().list().disableBatchDelete();
        cfg.view().formWindow().width(Config.baseFormWidth);
        cfg.view().form().addGroup(null,
                new Object[]{
                        MobileTables.APP_MODULE_GROUP.CODE,
                        MobileTables.APP_MODULE_GROUP.LABEL,
                        MobileTables.APP_MODULE_GROUP.NAME,
                },
                new Object[]{
                        MobileTables.APP_MODULE_GROUP.TYPE,
                        MobileTables.APP_MODULE_GROUP.STATUS,
                        MobileTables.APP_MODULE_GROUP.SORT,
                }
        );

        cfg.view().search().inputWidth(Config.searchInputWidth);
        //????????????????????????
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.COVER_EXISTS_FILE) //???????????????
                .setControllerAndAgent(WriteMode.IGNORE) //Rest
                .setPageController(WriteMode.COVER_EXISTS_FILE) //???????????????
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //??????HTML???
                .setListPage(WriteMode.COVER_EXISTS_FILE)
                .setExtendJsFile(WriteMode.COVER_EXISTS_FILE); //??????HTML???
        ; //??????HTML???
        //????????????
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        AppModuleGroupGtr g=new AppModuleGroupGtr();
        //????????????
       g.generateCode();

        //?????????????????????????????????????????????
      // g.generateMenu(ModuleGroupServiceProxy.class, ModuleGroupPageController.class);
        //????????????
    }

}
