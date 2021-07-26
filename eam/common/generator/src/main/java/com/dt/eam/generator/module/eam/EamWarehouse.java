package com.dt.eam.generator.module.eam;

import com.dt.eam.constants.db.EAMTables;
import com.dt.eam.eam.page.SupplierPageController;
import com.dt.eam.eam.page.WarehousePageController;
import com.dt.eam.proxy.eam.SupplierServiceProxy;
import com.dt.eam.proxy.eam.WarehouseServiceProxy;
import com.github.foxnic.generator.config.WriteMode;

public class EamWarehouse  extends BaseCodeGenerator{
    public EamWarehouse() {
        super(EAMTables.EAM_WAREHOUSE.$TABLE,BASIC_DATA_MENU_ID);
    }

    public void generateCode() throws Exception {
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
        EamWarehouse g=new EamWarehouse();
        //生成代码
      //  g.generateCode();


        //生成菜单
      //  g.removeByBatchId("");
        g.generateMenu(WarehouseServiceProxy.class, WarehousePageController.class);
    }
}
