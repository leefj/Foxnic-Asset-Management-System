package com.dt.platform.generator.module.dc;

import com.dt.platform.constants.db.EAMTables;


import com.dt.platform.constants.enums.datacenter.AreaTypeEnum;
import com.dt.platform.datacenter.page.AreaPageController;
import com.dt.platform.proxy.datacenter.AreaServiceProxy;
import com.github.foxnic.generator.config.WriteMode;
import org.github.foxnic.web.constants.enums.DictEnum;


public class DcAreaGtr extends BaseCodeGenerator {
//
//    public DcAreaGtr() {
//        super(EAMTables.DC_AREA.$TABLE,BASIC_DATA_MENU_ID);
//    }

    public DcAreaGtr() {
        super(AreaServiceProxy.class, AreaPageController.class,EAMTables.DC_AREA.$TABLE,BASIC_DATA_MENU_ID);
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());
        cfg.view().field(EAMTables.DC_AREA.ID).basic().hidden(true);

        cfg.view().field(EAMTables.DC_AREA.NAME).search().fuzzySearch();
        cfg.view().field(EAMTables.DC_AREA.POSITION).search().fuzzySearch();
        cfg.view().field(EAMTables.DC_AREA.NOTES).search().fuzzySearch();


        cfg.view().field(EAMTables.DC_AREA.TYPE).basic().label("类型")
              .form().validate().required().form().radioBox().enumType(AreaTypeEnum.class);

        cfg.view().field(EAMTables.DC_AREA.NAME).form().validate().required();

        //文件生成覆盖模式
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.COVER_EXISTS_FILE) //服务与接口
                .setControllerAndAgent(WriteMode.COVER_EXISTS_FILE) //Rest
                .setPageController(WriteMode.COVER_EXISTS_FILE) //页面控制器
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //表单HTML页
                .setListPage(WriteMode.COVER_EXISTS_FILE); //列表HTML页
        //生成代码
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        DcAreaGtr g=new DcAreaGtr();
       // g.reGenerateMenu();
        //生成代码
        g.generateCode();
        //生成菜单
     //  g.generateMenu(AreaServiceProxy.class, AreaPageController.class);
     //  g.reGenerateMenu(AreaServiceProxy.class,AreaPageController.class);
    }
}
