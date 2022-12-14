package com.dt.platform.generator.module.eam;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.constants.enums.eam.AssetMaintenanceUpdateHandleStatusEnum;
import com.dt.platform.constants.enums.eam.AssetStatusTypeEnum;
import com.dt.platform.domain.eam.meta.AssetScrapMeta;
import com.dt.platform.eam.page.AssetMaintenanceUpdatePageController;
import com.dt.platform.generator.config.Config;
import com.dt.platform.proxy.eam.AssetMaintenanceUpdateServiceProxy;
import com.github.foxnic.generator.config.WriteMode;

public class EamAssetMaintenanceUpdateGtr extends BaseCodeGenerator{
    public EamAssetMaintenanceUpdateGtr() {
        super(EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.$TABLE,"638424019464355840");
    }

    public void generateCode() throws Exception {

        System.out.println(this.getClass().getName());
        cfg.view().field(EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.ID).basic().hidden(true);
        cfg.view().field(EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.NAME).search().fuzzySearch();
        cfg.view().field(EAMTables.EAM_ASSET.CREATE_TIME).search().range();
        cfg.view().search().inputLayout(
                new Object[]{
                        EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.HANDLE_STATUS,
                        EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.NAME,
                        EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.CREATE_TIME,
                }
        );

        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().labelWidth(3,Config.searchLabelWidth);
        cfg.view().search().labelWidth(4,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);
        cfg.view().field(EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.NAME).form().validate().required();
        cfg.view().field(EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.NOTES).form().textArea().height(Config.textAreaHeight);
        cfg.view().field(EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.HANDLE_STATUS).form().radioBox().enumType(AssetMaintenanceUpdateHandleStatusEnum.class).defaultIndex(1);


        cfg.view().field(EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.CONTENT).form().textArea().height(Config.textAreaHeight_60);
        cfg.view().field(EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.REASON).form().textArea().height(Config.textAreaHeight_60);
        cfg.view().field(EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.ATTACH_ID).form().upload().acceptAllType().acceptSingleFile();
        cfg.view().field(EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.ATTACH_ID).table().disable(true);
        cfg.view().list().operationColumn().addActionButton("????????????","updateDetail","update-detail-button","eam_asset_maintenance_update:dtl");

        cfg.view().list().disableBatchDelete();
        //??????????????????
        cfg.view().formWindow().bottomSpace(20);
        cfg.view().formWindow().width(Config.baseFormWidth);
        cfg.view().form().addGroup(null,
                new Object[] {
                        EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.NAME,
                        EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.REASON,
                        EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.CONTENT,
                        EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.ATTACH_ID,
                        EAMTables.EAM_ASSET_MAINTENANCE_UPDATE.NOTES,
                }
        );
        //????????????????????????
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.IGNORE) //???????????????
                .setControllerAndAgent(WriteMode.IGNORE) //Rest
                .setPageController(WriteMode.IGNORE) //???????????????
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //??????HTML???
                .setListPage(WriteMode.COVER_EXISTS_FILE)//??????HTML???
                .setExtendJsFile(WriteMode.IGNORE); //??????HTML???
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        EamAssetMaintenanceUpdateGtr g=new EamAssetMaintenanceUpdateGtr();
        //????????????
         g.generateCode();
        //????????????
        //  g.removeByBatchId("");
       //g.generateMenu(AssetMaintenanceUpdateServiceProxy.class, AssetMaintenanceUpdatePageController.class);
    }
}
