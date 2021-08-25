package com.dt.platform.generator.module.eam;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.eam.page.InventoryPageController;
import com.dt.platform.eam.page.InventoryUserPageController;
import com.dt.platform.proxy.eam.InventoryServiceProxy;
import com.dt.platform.proxy.eam.InventoryUserServiceProxy;
import com.github.foxnic.generator.config.WriteMode;

public class EamInventoryUserGtr extends BaseCodeGenerator{

    public EamInventoryUserGtr() {
        super(EAMTables.EAM_INVENTORY_USER.$TABLE,BASIC_DATA_MENU_ID);
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());
        cfg.view().field(EAMTables.EAM_INVENTORY_USER.ID).basic().hidden(true);

        //eam_asset_change_data
        cfg.view().search().inputLayout(
                new Object[]{
                        EAMTables.EAM_INVENTORY_USER.ID,
                }
        );

        cfg.setRelationField(EAMTables.EAM_INVENTORY_USER.INVENTORY_ID, EAMTables.EAM_INVENTORY_USER.USER_ID,true);


        //文件生成覆盖模式
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.COVER_EXISTS_FILE) //服务与接口
                .setControllerAndAgent(WriteMode.COVER_EXISTS_FILE) //Rest
                .setPageController(WriteMode.COVER_EXISTS_FILE) //页面控制器
                .setFormPage(WriteMode.IGNORE) //表单HTML页
                .setListPage(WriteMode.IGNORE); //列表HTML页
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        EamInventoryUserGtr g=new EamInventoryUserGtr();

        g.generateCode();

        //生成菜单
        //g.generateMenu(InventoryUserServiceProxy.class, InventoryUserPageController.class);
    }
}
