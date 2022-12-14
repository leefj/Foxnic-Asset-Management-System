package com.dt.platform.generator.module.eam;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.generator.config.Config;
import com.github.foxnic.generator.config.WriteMode;

public class AssetCopyRecordGtr extends BaseCodeGenerator{
    public AssetCopyRecordGtr() {
        super(EAMTables.EAM_ASSET_COPY_RECORD.$TABLE,BASIC_SETTING_MENU_ID);
    }

    public void generateCode() throws Exception {

        System.out.println(this.getClass().getName());

        cfg.view().field(EAMTables.EAM_ASSET_COPY_RECORD.ID).basic().hidden(true);
        cfg.view().search().inputLayout(
                new Object[]{
                        EAMTables.EAM_ASSET_COPY_RECORD.COPY_ID,
                }
        );

        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().labelWidth(3,Config.searchLabelWidth);
        cfg.view().search().labelWidth(4,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);

        cfg.view().formWindow().width(Config.baseFormWidth);;
        cfg.view().formWindow().bottomSpace(20);

        cfg.view().form().addGroup(null,
                new Object[] {
                        EAMTables.EAM_ASSET_COPY_RECORD.COPY_ID,
                        EAMTables.EAM_ASSET_COPY_RECORD.COPY_SOURCE_ASSET_ID,
                        EAMTables.EAM_ASSET_COPY_RECORD.COPY_TARGET_ASSET_ID,
                }
        );
        //文件生成覆盖模式
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.COVER_EXISTS_FILE) //服务与接口
                .setControllerAndAgent(WriteMode.COVER_EXISTS_FILE) //Rest
                .setPageController(WriteMode.COVER_EXISTS_FILE) //页面控制器
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //表单HTML页
                .setListPage(WriteMode.COVER_EXISTS_FILE)//列表HTML页
                .setExtendJsFile(WriteMode.COVER_EXISTS_FILE); //列表HTML页
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        AssetCopyRecordGtr g=new AssetCopyRecordGtr();
        //生成代码
         g.generateCode();
        //生成菜单
        //  g.removeByBatchId("");
       // g.generateMenu(CodeRuleServiceProxy.class, CodeRulePageController.class);
    }
}
