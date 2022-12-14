package com.dt.platform.generator.module.mobile;

import com.dt.platform.constants.db.MobileTables;
import com.dt.platform.constants.enums.common.StatusEnableEnum;
import com.dt.platform.domain.mobile.SoftwareGroup;
import com.dt.platform.domain.mobile.SoftwareInfo;
import com.dt.platform.domain.mobile.meta.ModuleGroupMeta;
import com.dt.platform.domain.mobile.meta.ModuleInfoMeta;
import com.dt.platform.generator.config.Config;
import com.dt.platform.mobile.page.SoftwareGroupPageController;
import com.dt.platform.proxy.mobile.ModuleGroupServiceProxy;
import com.dt.platform.proxy.mobile.SoftwareGroupServiceProxy;
import com.github.foxnic.generator.config.WriteMode;

public class AppSoftwareGroupGtr extends BaseCodeGenerator {


    public AppSoftwareGroupGtr() {
        super(MobileTables.APP_SOFTWARE_GROUP.$TABLE,"593340389855854592");
    }
    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());
        cfg.view().field(MobileTables.APP_SOFTWARE_GROUP.ID).basic().hidden(true);
        cfg.view().field(MobileTables.APP_SOFTWARE_GROUP.NAME).search().fuzzySearch();
        cfg.view().field(MobileTables.APP_SOFTWARE_GROUP.CODE).search().fuzzySearch();

        cfg.getPoClassFile().addListProperty(SoftwareInfo.class,"softwareInfoList","softwareInfoList","softwareInfoList");


        cfg.view().search().inputLayout(
                new Object[]{
                        MobileTables.APP_SOFTWARE_GROUP.CODE,
                        MobileTables.APP_SOFTWARE_GROUP.NAME,
                }
        );

        cfg.view().field(MobileTables.APP_SOFTWARE_GROUP.NAME).form().validate().required();
        cfg.view().field(MobileTables.APP_SOFTWARE_GROUP.CODE).form().validate().required();
        cfg.view().field(MobileTables.APP_SOFTWARE_GROUP.NOTES).form().textArea().height(50);

        cfg.view().formWindow().width("75%");
        cfg.view().formWindow().bottomSpace(80);
        cfg.view().list().disableBatchDelete();
        cfg.view().form().addGroup(null,
                new Object[]{
                        MobileTables.APP_SOFTWARE_GROUP.NAME,
                },
                new Object[]{
                        MobileTables.APP_SOFTWARE_GROUP.CODE,
                }
        );
        cfg.view().form().addGroup(null,
                new Object[]{
                        MobileTables.APP_SOFTWARE_GROUP.NOTES,
                }
        );

        cfg.view().search().inputWidth(Config.searchInputWidth);

        //????????????????????????
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.COVER_EXISTS_FILE) //???????????????
                .setControllerAndAgent(WriteMode.IGNORE) //Rest
                .setPageController(WriteMode.IGNORE) //???????????????
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //??????HTML???
                .setListPage(WriteMode.COVER_EXISTS_FILE)
                .setExtendJsFile(WriteMode.COVER_EXISTS_FILE); //??????HTML???
        ; //??????HTML???
        //????????????
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        AppSoftwareGroupGtr g=new AppSoftwareGroupGtr();
        //????????????
        g.generateCode();
       // g.generateMenu(SoftwareGroupServiceProxy.class, SoftwareGroupPageController.class);
        //????????????

    }

}
