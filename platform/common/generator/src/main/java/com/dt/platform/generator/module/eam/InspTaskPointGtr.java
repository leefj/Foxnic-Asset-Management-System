package com.dt.platform.generator.module.eam;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.constants.enums.common.StatusEnableEnum;
import com.dt.platform.constants.enums.eam.InspectionTaskPointStatusEnum;
import com.dt.platform.constants.enums.eam.InspectionTaskStatusEnum;
import com.dt.platform.constants.enums.eam.PointInspectMethodEnum;
import com.dt.platform.domain.eam.*;
import com.dt.platform.domain.eam.meta.*;
import com.dt.platform.eam.page.InspectionTaskPointPageController;
import com.dt.platform.generator.config.Config;
import com.dt.platform.proxy.eam.InspectionPointPosServiceProxy;
import com.dt.platform.proxy.eam.InspectionProcessActionServiceProxy;
import com.dt.platform.proxy.eam.InspectionRouteServiceProxy;
import com.dt.platform.proxy.eam.InspectionTaskPointServiceProxy;
import com.github.foxnic.generator.config.WriteMode;
import org.github.foxnic.web.domain.hrm.Employee;

public class InspTaskPointGtr extends BaseCodeGenerator {


    public InspTaskPointGtr() {
        super(EAMTables.EAM_INSPECTION_TASK_POINT.$TABLE,BASIC_INSP);
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());
        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.ID).basic().hidden(true);
        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.POINT_ID).table().disable(true);
        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.POINT_CODE).search().fuzzySearch();
        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.POINT_NAME).search().fuzzySearch();

        cfg.getPoClassFile().addSimpleProperty(InspectionRoute.class,"route","route","route");
        cfg.getPoClassFile().addSimpleProperty(InspectionTask.class,"task","task","task");
        cfg.getPoClassFile().addSimpleProperty(Employee.class,"operUser","operUser","operUser");
        cfg.getPoClassFile().addSimpleProperty(InspectionPointPos.class,"inspectionPointPos","inspectionPointPos","inspectionPointPos");
        cfg.getPoClassFile().addListProperty(CheckItem.class,"checkItemList","checkItemList","checkItemList");
        cfg.getPoClassFile().addListProperty(CheckSelect.class,"checkSelectList","checkSelectList","checkSelectList");
        cfg.getPoClassFile().addSimpleProperty(InspectionPoint.class,"inspectionPoint","inspectionPoint","inspectionPoint");
        cfg.getPoClassFile().addSimpleProperty(String.class,"itemCount","itemCount","itemCount");
        cfg.getPoClassFile().addSimpleProperty(Asset.class,"asset","asset","asset");
        cfg.getPoClassFile().addSimpleProperty(InspectionProcessAction.class,"inspectionProcessAction","inspectionProcessAction","inspectionProcessAction");

//        cfg.getPoClassFile().addSimpleProperty(String.class,"relAsset","relAsset","relAsset");

        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.OPER_ID).table().fillBy("operUser","name");
        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.OPER_ID).form()
                .button().chooseEmployee(true);

        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.CREATE_TIME).table().disable(true);


        cfg.view().search().inputLayout(
                new Object[]{
                        EAMTables.EAM_INSPECTION_TASK_POINT.POINT_POS_ID,
                        EAMTables.EAM_INSPECTION_TASK_POINT.POINT_CODE,
                        EAMTables.EAM_INSPECTION_TASK_POINT.POINT_NAME,
                        EAMTables.EAM_INSPECTION_TASK_POINT.POINT_POS,
                },
                new Object[]{
                        EAMTables.EAM_INSPECTION_TASK_POINT.POINT_STATUS,
                        EAMTables.EAM_INSPECTION_TASK_POINT.ACTION_LABEL,
                }

        );


        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().labelWidth(3,Config.searchLabelWidth);
        cfg.view().search().labelWidth(4,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);

        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.SELECTED_CODE).table().disable(true);

        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.TASK_ID).table().disable(true);

        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.NOTES).table().disable(true);
        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.POINT_POS).table().disable(true);
        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.POINT_CONTENT).table().disable(true);
        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.POINT_POS_LATITUDE).table().disable(true);
        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.POINT_POS_LONGITUDE).table().disable(true);
        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.POINT_RFID).table().disable(true);

        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.POINT_POS_LATITUDE).form().numberInput().defaultValue(0).decimal().scale(2);
        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.POINT_POS_LONGITUDE).form().numberInput().defaultValue(0).decimal().scale(2);


//        cfg.view().field(InspectionTaskPointMeta.REL_ASSET).basic().label("关联设备").table().disable(false);
        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.IMAGE_ID).table().disable(true);
        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.IMAGE_ID).form().upload().acceptImageType().maxFileCount(3);

        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.POINT_STATUS).form().readOnly().form()
                .radioBox().enumType(InspectionTaskPointStatusEnum.class).defaultIndex(0);

        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.INSP_METHOD).table().disable(true);
        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.INSP_METHOD).form().form()
                .radioBox().enumType(PointInspectMethodEnum.class);
        

        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.POINT_ROUTE_ID)
                .form().selectBox().queryApi(InspectionRouteServiceProxy.QUERY_LIST)
                .paging(false).filter(true).toolbar(false)
                .valueField(InspectionRouteMeta.ID).
                textField(InspectionRouteMeta.NAME).
                fillWith(InspectionTaskPointMeta.ROUTE).muliti(false).defaultIndex(0);

        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.ACTION_LABEL)
                .form().selectBox().queryApi(InspectionProcessActionServiceProxy.QUERY_LIST)
                .paging(false).filter(true).toolbar(false)
                .valueField(InspectionProcessActionMeta.CODE).
                textField(InspectionProcessActionMeta.NAME).
                fillWith(InspectionTaskPointMeta.INSPECTION_PROCESS_ACTION).muliti(false);

        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.POINT_POS_ID)
                .form().selectBox().queryApi(InspectionPointPosServiceProxy.QUERY_PAGED_LIST)
                .paging(true).filter(true).toolbar(false)
                .valueField(InspectionPointPosMeta.ID).
                textField(InspectionPointPosMeta.HIERARCHY_NAME).
                fillWith(InspectionTaskPointMeta.INSPECTION_POINT_POS).muliti(false);

        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.CONTENT).form().textArea().height(150);
        cfg.view().field(EAMTables.EAM_INSPECTION_TASK_POINT.OPER_TIME).form().dateInput().format("yyyy-MM-dd HH:mm:ss").defaultNow();
         cfg.view().formWindow().width(Config.baseFormWidth);;
        cfg.view().list().disableBatchDelete();
        cfg.view().formWindow().bottomSpace(20);

        cfg.view().form().addGroup(null,
                new Object[] {
                        EAMTables.EAM_INSPECTION_TASK_POINT.POINT_STATUS,
                        EAMTables.EAM_INSPECTION_TASK_POINT.ACTION_LABEL,
                }
        );
        cfg.view().form().addGroup(null,
                new Object[] {
                        EAMTables.EAM_INSPECTION_TASK_POINT.CONTENT,
                        EAMTables.EAM_INSPECTION_TASK_POINT.IMAGE_ID
                }
        );
        cfg.view().form().addGroup("巡检点",
                new Object[] {
                        EAMTables.EAM_INSPECTION_TASK_POINT.POINT_CODE,
                        EAMTables.EAM_INSPECTION_TASK_POINT.POINT_NAME,
                },
                new Object[] {
                        EAMTables.EAM_INSPECTION_TASK_POINT.POINT_ROUTE_ID,
                        EAMTables.EAM_INSPECTION_TASK_POINT.POINT_NOTES,
                },
                new Object[] {
                        EAMTables.EAM_INSPECTION_TASK_POINT.POINT_POS,
                        EAMTables.EAM_INSPECTION_TASK_POINT.POINT_POS_LONGITUDE,
                        EAMTables.EAM_INSPECTION_TASK_POINT.POINT_POS_LATITUDE,
                }
        );

        cfg.view().list().addToolButton("导入","importData","import-data");
        cfg.view().list().addToolButton("导出","exportData","export-data");


        //文件生成覆盖模式
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.IGNORE) //服务与接口
                .setControllerAndAgent(WriteMode.IGNORE) //Rest
                .setPageController(WriteMode.IGNORE) //页面控制器
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //表单HTML页
                .setListPage(WriteMode.COVER_EXISTS_FILE)
                .setExtendJsFile(WriteMode.IGNORE); //列表HTML页
        ; //列表HTML页
        //生成代码
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        InspTaskPointGtr g=new InspTaskPointGtr();
        //生成代码
       g.generateCode();

       // g.removeByBatchId("507635127677878272");
        //移除之前生成的菜单，视情况执行
       // g.generateMenu(InspectionTaskPointServiceProxy.class, InspectionTaskPointPageController.class);
        //生成菜单

    }

}
