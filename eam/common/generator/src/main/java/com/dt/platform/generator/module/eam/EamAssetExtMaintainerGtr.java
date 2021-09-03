package com.dt.platform.generator.module.eam;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.domain.eam.Asset;
import com.dt.platform.domain.eam.AssetExtFinancial;
import com.dt.platform.domain.eam.AssetExtMaintainer;
import com.dt.platform.domain.eam.Maintainer;
import com.dt.platform.domain.eam.meta.AssetExtFinancialMeta;
import com.dt.platform.domain.eam.meta.AssetExtMaintainerMeta;
import com.dt.platform.domain.eam.meta.MaintainerMeta;
import com.dt.platform.domain.eam.meta.SupplierMeta;
import com.dt.platform.eam.page.AssetExtMaintainerPageController;
import com.dt.platform.proxy.eam.AssetExtMaintainerServiceProxy;
import com.dt.platform.proxy.eam.MaintainerServiceProxy;
import com.dt.platform.proxy.eam.SupplierServiceProxy;
import com.github.foxnic.generator.config.WriteMode;

public class EamAssetExtMaintainerGtr extends BaseCodeGenerator {

    public EamAssetExtMaintainerGtr() {
        super(EAMTables.EAM_ASSET_EXT_MAINTAINER.$TABLE,BASIC_DATA_MENU_ID);
    }

    public void generateCode() throws Exception {

        System.out.println(this.getClass().getName());

        cfg.getPoClassFile().addSimpleProperty(Maintainer.class,"maintnainer","维保商","维保商");

        cfg.getPoClassFile().addSimpleProperty(Asset.class,"asset","资产","资产");

        cfg.view().field(EAMTables.EAM_ASSET_EXT_MAINTAINER.ID).basic().hidden();


        cfg.view().field(EAMTables.EAM_ASSET_EXT_MAINTAINER.MAINTENANCE_START_TIME).basic().form().label("开始时间");
        cfg.view().field(EAMTables.EAM_ASSET_EXT_MAINTAINER.MAINTENANCE_END_TIME).basic().form().label("结束时间");


        cfg.view().field(EAMTables.EAM_ASSET_EXT_MAINTAINER.MAINTENANCE_START_TIME).search().range();
        cfg.view().field(EAMTables.EAM_ASSET_EXT_MAINTAINER.MAINTENANCE_END_TIME).search().range();
        cfg.view().field(EAMTables.EAM_ASSET_EXT_MAINTAINER.DIRECTOR).search().fuzzySearch();
        cfg.view().field(EAMTables.EAM_ASSET_EXT_MAINTAINER.CONTACTS).search().fuzzySearch();
        cfg.view().field(EAMTables.EAM_ASSET_EXT_MAINTAINER.MAINTENANCE_DESCRIPTION).search().fuzzySearch();


        cfg.view().field(EAMTables.EAM_ASSET_EXT_MAINTAINER.MAINTAINER_ID)
                .basic().label("维保商")
               // .form().validate().required()
                .form().selectBox().queryApi(MaintainerServiceProxy.QUERY_LIST)
                .paging(false).filter(true).toolbar(false)
                .valueField(MaintainerMeta.ID).
                textField(MaintainerMeta.MAINTAINER_NAME).
                fillBy(AssetExtMaintainerMeta.MAINTNAINER).muliti(false);

        cfg.view().field(EAMTables.EAM_ASSET_EXT_MAINTAINER.MAINTENANCE_DESCRIPTION).form().textArea().height(30);
        cfg.view().search().inputLayout(
                new Object[]{
                        EAMTables.EAM_ASSET_EXT_MAINTAINER.MAINTAINER_ID,
                        EAMTables.EAM_ASSET_EXT_MAINTAINER.DIRECTOR,
                        EAMTables.EAM_ASSET_EXT_MAINTAINER.CONTACTS,
                        EAMTables.EAM_ASSET_EXT_MAINTAINER.CONTACT_INFORMATION,
                        EAMTables.EAM_ASSET_EXT_MAINTAINER.MAINTENANCE_DESCRIPTION,
                },
                new Object[]{
                        EAMTables.EAM_ASSET_EXT_MAINTAINER.MAINTENANCE_START_TIME,
                        EAMTables.EAM_ASSET_EXT_MAINTAINER.MAINTENANCE_END_TIME
                }

        );


        //分成分组布局
        cfg.view().formWindow().width("85%");
        cfg.view().form().addGroup(null,
                new Object[] {
                        EAMTables.EAM_ASSET_EXT_MAINTAINER.MAINTAINER_ID,
                        EAMTables.EAM_ASSET_EXT_MAINTAINER.DIRECTOR,
                }, new Object[] {
                        EAMTables.EAM_ASSET_EXT_MAINTAINER.CONTACTS,
                        EAMTables.EAM_ASSET_EXT_MAINTAINER.CONTACT_INFORMATION,

                }
                , new Object[] {
                        EAMTables.EAM_ASSET_EXT_MAINTAINER.MAINTENANCE_START_TIME,
                        EAMTables.EAM_ASSET_EXT_MAINTAINER.MAINTENANCE_END_TIME
                }
        );

        cfg.view().form().addGroup(null,
                new Object[] {
                        EAMTables.EAM_ASSET_EXT_MAINTAINER.MAINTENANCE_DESCRIPTION
                }

        );

        cfg.view().form().disableFooter();


        //文件生成覆盖模式
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.COVER_EXISTS_FILE) //服务与接口
                .setControllerAndAgent(WriteMode.COVER_EXISTS_FILE) //Rest
                .setPageController(WriteMode.COVER_EXISTS_FILE) //页面控制器
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //表单HTML页
                .setListPage(WriteMode.COVER_EXISTS_FILE); //列表HTML页
        cfg.buildAll();
    }
    public static void main(String[] args) throws Exception {
        EamAssetExtMaintainerGtr g=new EamAssetExtMaintainerGtr();

         g.generateCode();
        //g.generateMenu(AssetExtMaintainerServiceProxy.class, AssetExtMaintainerPageController.class);

    }

}