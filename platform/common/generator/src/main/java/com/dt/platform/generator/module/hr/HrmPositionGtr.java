package com.dt.platform.generator.module.hr;


import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.constants.db.HrTables;
import com.dt.platform.domain.eam.AssetExtData;
import com.dt.platform.domain.eam.meta.AssetMeta;
import com.dt.platform.domain.eam.meta.AssetRackMeta;
import com.dt.platform.domain.hr.Position;
import com.dt.platform.domain.hr.PositionType;
import com.dt.platform.domain.hr.meta.PositionMeta;
import com.dt.platform.domain.hr.meta.PositionTypeMeta;
import com.dt.platform.generator.config.Config;
import com.dt.platform.hr.page.PositionPageController;
import com.dt.platform.proxy.eam.AssetRackServiceProxy;
import com.dt.platform.proxy.hr.PositionServiceProxy;
import com.dt.platform.proxy.hr.PositionTypeServiceProxy;
import com.github.foxnic.generator.config.WriteMode;


public class HrmPositionGtr extends BaseCodeGenerator {
    public HrmPositionGtr() {
        super(HrTables.HR_POSITION.$TABLE,"660485960290533376");
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());
        cfg.getPoClassFile().addSimpleProperty(PositionType.class,"positionType","positionType","positionType");

        cfg.view().field(HrTables.HR_POSITION.ID).basic().hidden(true);

        cfg.view().field(HrTables.HR_POSITION.NAME).search().fuzzySearch();
        cfg.view().search().inputLayout(
                new Object[]{
                        HrTables.HR_POSITION.TYPE,
                        HrTables.HR_POSITION.NAME,
                }
        );

        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().labelWidth(3,Config.searchLabelWidth);
        cfg.view().search().labelWidth(4,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);



        cfg.view().field(HrTables.HR_POSITION.NAME).form().validate().required();
        cfg.view().field(HrTables.HR_POSITION.TYPE).form().validate().required()
                .form().selectBox().queryApi(PositionTypeServiceProxy.QUERY_PAGED_LIST)
                .paging(true).filter(true).toolbar(false)
                .valueField(PositionTypeMeta.ID).
                textField(PositionTypeMeta.NAME).
                fillWith(PositionMeta.POSITION_TYPE).muliti(false);


        cfg.view().formWindow().width("65%");;
        cfg.view().formWindow().bottomSpace(200);
        cfg.view().form().addGroup(null,
                new Object[] {
                        HrTables.HR_POSITION.NAME,
                        HrTables.HR_POSITION.TYPE,
                        HrTables.HR_POSITION.NOTE,
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
        HrmPositionGtr g=new HrmPositionGtr();
        //生成代码
        g.generateCode();

        //移除之前生成的菜单，视情况执行
       // System.out.println("############"+g.getTablePrefix());
        //g.removeByBatchId("470160949404237824");
      //  //生成菜单
      // g.generateMenu(PositionServiceProxy.class, PositionPageController.class);
    }
}
