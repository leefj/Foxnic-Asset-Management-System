package com.dt.platform.generator.module.hr;


import com.dt.platform.constants.db.HrTables;
import com.dt.platform.domain.hr.BusinessTravelData;
import com.dt.platform.generator.config.Config;
import com.dt.platform.hr.page.BusinessTravelDataPageController;
import com.dt.platform.proxy.hr.BusinessTravelDataServiceProxy;
import com.github.foxnic.generator.config.WriteMode;


public class HrmBusinessTravelGtr extends BaseCodeGenerator {
    public HrmBusinessTravelGtr() {
        super(HrTables.HR_BUSINESS_TRAVEL_DATA.$TABLE,"662281190966820864");
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());


        cfg.view().field(HrTables.HR_BUSINESS_TRAVEL_DATA.ID).basic().hidden(true);

        cfg.view().search().inputLayout(
                new Object[]{
                        HrTables.HR_BUSINESS_TRAVEL_DATA.EMPLOYEE_ID,
                        HrTables.HR_BUSINESS_TRAVEL_DATA.NOTES,
                }

        );

        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().labelWidth(3,Config.searchLabelWidth);
        cfg.view().search().labelWidth(4,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);

        cfg.view().formWindow().width("65%");;
        cfg.view().formWindow().bottomSpace(20);
        cfg.view().form().addGroup(null,
                new Object[] {
                        HrTables.HR_BUSINESS_TRAVEL_DATA.EMPLOYEE_ID,
                        HrTables.HR_BUSINESS_TRAVEL_DATA.NOTES,
                }
        );




        //文件生成覆盖模式
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.COVER_EXISTS_FILE) //服务与接口
                .setControllerAndAgent(WriteMode.COVER_EXISTS_FILE) //Rest
                .setPageController(WriteMode.COVER_EXISTS_FILE) //页面控制器
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //表单HTML页
                .setListPage(WriteMode.COVER_EXISTS_FILE) //列表HTML页
                .setExtendJsFile(WriteMode.COVER_EXISTS_FILE);
        //生成代码
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        HrmBusinessTravelGtr g=new HrmBusinessTravelGtr();
        //生成代码
        g.generateCode();

        //移除之前生成的菜单，视情况执行
       // System.out.println("############"+g.getTablePrefix());
        //g.removeByBatchId("470160949404237824");
      //  //生成菜单
       //g.generateMenu(BusinessTravelDataServiceProxy.class, BusinessTravelDataPageController.class);

    }
}
