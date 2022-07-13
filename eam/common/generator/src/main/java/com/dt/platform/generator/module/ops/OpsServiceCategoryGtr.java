package com.dt.platform.generator.module.ops;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.constants.enums.ops.ServiceTypeEnum;
import com.dt.platform.domain.ops.InformationSystem;
import com.dt.platform.domain.ops.ServiceGroup;
import com.dt.platform.domain.ops.meta.HostMeta;
import com.dt.platform.domain.ops.meta.ServiceCategoryMeta;
import com.dt.platform.domain.ops.meta.ServiceGroupMeta;
import com.dt.platform.domain.ops.meta.ServiceInfoMeta;
import com.dt.platform.generator.config.Config;
import com.dt.platform.ops.page.ServiceCategoryPageController;
import com.dt.platform.proxy.ops.ServiceCategoryServiceProxy;
import com.dt.platform.proxy.ops.ServiceGroupServiceProxy;
import com.dt.platform.proxy.ops.ServiceInfoServiceProxy;
import com.github.foxnic.generator.config.WriteMode;
import com.dt.platform.constants.db.OpsTables;
public class OpsServiceCategoryGtr extends BaseCodeGenerator{


    public OpsServiceCategoryGtr() {
        super(OpsTables.OPS_SERVICE_CATEGORY.$TABLE,BASIC_DATA_MENU_ID);
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());

        cfg.getPoClassFile().addSimpleProperty(ServiceGroup.class,"group","服务分组","服务分组");

        cfg.view().field(OpsTables.OPS_SERVICE_CATEGORY.ID).basic().hidden(true);
        cfg.view().field(OpsTables.OPS_SERVICE_CATEGORY.CREATE_TIME).table().hidden();
        cfg.view().field(OpsTables.OPS_SERVICE_CATEGORY.NAME).search().fuzzySearch();
        cfg.view().field(OpsTables.OPS_SERVICE_CATEGORY.NOTES).search().fuzzySearch();


        cfg.view().field(OpsTables.OPS_SERVICE_CATEGORY.NOTES).form().textArea().height(Config.textAreaHeight);
        cfg.view().field(OpsTables.OPS_SERVICE_CATEGORY.GROUP_ID)
                .basic().label("服务分组")
                .form().validate().required().form().selectBox().queryApi(ServiceGroupServiceProxy.QUERY_LIST)
                .valueField(ServiceGroupMeta.CODE).textField(ServiceGroupMeta.NAME)
                .toolbar(false).filter(true).paging(false).defaultIndex(0)
                .fillWith(ServiceCategoryMeta.GROUP).muliti(false);

        cfg.view().field(OpsTables.OPS_SERVICE_CATEGORY.NAME)
                .form().validate().required();

        cfg.view().formWindow().bottomSpace(80);
        cfg.view().formWindow().width(Config.baseFormWidth);

        cfg.view().search().inputLayout(
                new Object[]{
                        OpsTables.OPS_SERVICE_CATEGORY.GROUP_ID,
                        OpsTables.OPS_SERVICE_CATEGORY.NAME,
                        OpsTables.OPS_SERVICE_CATEGORY.NOTES
                }
        );
        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().labelWidth(3,Config.searchLabelWidth);

        cfg.view().search().inputWidth(Config.searchInputWidth);


        //文件生成覆盖模式
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.COVER_EXISTS_FILE) //服务与接口
                .setControllerAndAgent(WriteMode.COVER_EXISTS_FILE) //Rest
                .setPageController(WriteMode.COVER_EXISTS_FILE) //页面控制器
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //表单HTML页
                .setListPage(WriteMode.COVER_EXISTS_FILE)//列表HTML页
                .setExtendJsFile(WriteMode.COVER_EXISTS_FILE); //列表HTML页
        //生成代码
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        OpsServiceCategoryGtr g=new OpsServiceCategoryGtr();
        //生成代码
        g.generateCode();

        //移除之前生成的菜单，视情况执行
       // g.removeByBatchId("474335473372758016");
     //   g.generateMenu(ServiceCategoryServiceProxy.class, ServiceCategoryPageController.class);
    }
}
