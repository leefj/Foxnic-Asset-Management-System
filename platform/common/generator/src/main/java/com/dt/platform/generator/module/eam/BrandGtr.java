package com.dt.platform.generator.module.eam;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.constants.enums.common.StatusEnableEnum;
import com.dt.platform.constants.enums.eam.AssetStatusEnum;
import com.dt.platform.eam.page.BrandPageController;
import com.dt.platform.generator.config.Config;
import com.dt.platform.proxy.eam.BrandServiceProxy;
import com.github.foxnic.generator.config.WriteMode;

public class BrandGtr extends BaseCodeGenerator {


    public BrandGtr() {
        super(EAMTables.EAM_BRAND.$TABLE,BASIC_DATA_MENU_ID);
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());
        cfg.view().field(EAMTables.EAM_BRAND.ID).basic().hidden(true);

        cfg.view().field(EAMTables.EAM_BRAND.BRAND_NAME).search().fuzzySearch();
        cfg.view().field(EAMTables.EAM_BRAND.BRAND_CODE).search().fuzzySearch();
        cfg.view().field(EAMTables.EAM_BRAND.STATUS).search().fuzzySearch();

        cfg.view().field(EAMTables.EAM_BRAND.BRAND_NAME).form().validate().required();
        cfg.view().field(EAMTables.EAM_BRAND.BRAND_CODE).form().validate().required();


        cfg.view().field(EAMTables.EAM_BRAND.STATUS).form().validate().required().
                form().radioBox().enumType(StatusEnableEnum.class).defaultIndex(0);
;

        cfg.view().search().inputLayout(
                new Object[]{
                        EAMTables.EAM_BRAND.STATUS,
                        EAMTables.EAM_BRAND.BRAND_CODE,
                        EAMTables.EAM_BRAND.BRAND_NAME,
                }
        );

        cfg.view().formWindow().width(Config.baseFormWidth);;
        cfg.view().formWindow().bottomSpace(20);
        cfg.view().form().addGroup(null,
                new Object[] {
                        EAMTables.EAM_BRAND.STATUS,
                        EAMTables.EAM_BRAND.BRAND_CODE,
                        EAMTables.EAM_BRAND.BRAND_NAME,
                        EAMTables.EAM_BRAND.SORT
                }
        );

        cfg.view().search().inputWidth(Config.searchInputWidth);

        //????????????????????????
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.COVER_EXISTS_FILE) //???????????????
                .setControllerAndAgent(WriteMode.COVER_EXISTS_FILE) //Rest
                .setPageController(WriteMode.COVER_EXISTS_FILE) //???????????????
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //??????HTML???
                .setListPage(WriteMode.COVER_EXISTS_FILE)
                .setExtendJsFile(WriteMode.COVER_EXISTS_FILE); //??????HTML???
        ; //??????HTML???
        //????????????
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        BrandGtr g=new BrandGtr();
        //????????????
       g.generateCode();

     //   g.removeByBatchId("582429214846746624");
        //?????????????????????????????????????????????
    //  g.generateMenu(BrandServiceProxy.class, BrandPageController.class);
        //????????????

    }

}
