package com.dt.platform.generator.module.eam;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.constants.enums.common.StatusEnableEnum;
import com.dt.platform.domain.eam.InspectionPoint;
import com.dt.platform.domain.eam.InspectionRoute;
import com.dt.platform.domain.eam.meta.InspectionPointMeta;
import com.dt.platform.domain.eam.meta.InspectionRouteMeta;
import com.dt.platform.domain.eam.meta.RepairCategoryMeta;
import com.dt.platform.domain.eam.meta.RepairCategoryTplMeta;
import com.dt.platform.eam.page.InspectionPointPageController;
import com.dt.platform.generator.config.Config;
import com.dt.platform.proxy.eam.InspectionPointServiceProxy;
import com.dt.platform.proxy.eam.InspectionRouteServiceProxy;
import com.dt.platform.proxy.eam.RepairCategoryServiceProxy;
import com.github.foxnic.generator.config.WriteMode;

public class InspPointGtr extends BaseCodeGenerator {


    public InspPointGtr() {
        super(EAMTables.EAM_INSPECTION_POINT.$TABLE,BASIC_INSP);
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());
        cfg.view().field(EAMTables.EAM_INSPECTION_POINT.ID).basic().hidden(true);

        cfg.view().field(EAMTables.EAM_INSPECTION_POINT.CODE).search().fuzzySearch();


        cfg.getPoClassFile().addSimpleProperty(InspectionRoute.class,"route","route","route");

        cfg.view().search().inputLayout(
                new Object[]{
                        EAMTables.EAM_INSPECTION_POINT.STATUS,
                        EAMTables.EAM_INSPECTION_POINT.CODE,
                        EAMTables.EAM_INSPECTION_POINT.NAME,
                }
        );


        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().labelWidth(3,Config.searchLabelWidth);
        cfg.view().search().labelWidth(4,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);


        cfg.view().field(EAMTables.EAM_INSPECTION_POINT.PICTURE_ID).table().disable(true);


        cfg.view().field(EAMTables.EAM_INSPECTION_POINT.SELECTED_CODE).table().disable(true);
        cfg.view().field(EAMTables.EAM_INSPECTION_POINT.STATUS).form().validate().required().form().selectBox().enumType(StatusEnableEnum.class).defaultIndex(0);
        cfg.view().field(EAMTables.EAM_INSPECTION_POINT.NAME).form().validate().required();
        cfg.view().field(EAMTables.EAM_INSPECTION_POINT.CODE).form().validate().required();
        cfg.view().field(EAMTables.EAM_INSPECTION_POINT.POS_LATITUDE).form().numberInput().defaultValue(0);
        cfg.view().field(EAMTables.EAM_INSPECTION_POINT.POS_LONGITUDE).form().numberInput().defaultValue(0);

        cfg.view().field(EAMTables.EAM_INSPECTION_POINT.NOTES).form().textArea().height(Config.textAreaHeight);
        cfg.view().field(EAMTables.EAM_INSPECTION_POINT.CONTENT).form().textArea().height(Config.textAreaHeight);
        cfg.view().field(EAMTables.EAM_INSPECTION_POINT.PICTURE_ID)
                .form().label("??????").upload().buttonLabel("????????????").acceptImageType().acceptSingleImage();


        cfg.view().field(EAMTables.EAM_INSPECTION_POINT.ROUTE_ID)
                .form().validate().required().form().selectBox().queryApi(InspectionRouteServiceProxy.QUERY_LIST)
                .paging(false).filter(true).toolbar(false)
                .valueField(InspectionRouteMeta.ID).
                textField(InspectionRouteMeta.NAME).
                fillWith(InspectionPointMeta.ROUTE).muliti(false).defaultIndex(0);


         cfg.view().formWindow().width(Config.baseFormWidth);;

        cfg.view().list().disableBatchDelete();
        cfg.view().formWindow().bottomSpace(20);
        cfg.view().form().addGroup(null,
                new Object[] {
                        EAMTables.EAM_INSPECTION_POINT.STATUS,
                        EAMTables.EAM_INSPECTION_POINT.CODE,
                        EAMTables.EAM_INSPECTION_POINT.NAME,
                },
                new Object[] {
                        EAMTables.EAM_INSPECTION_POINT.ROUTE_ID,
                        EAMTables.EAM_INSPECTION_POINT.RFID,
                },
                new Object[] {
                        EAMTables.EAM_INSPECTION_POINT.POS,
                        EAMTables.EAM_INSPECTION_POINT.POS_LONGITUDE,
                        EAMTables.EAM_INSPECTION_POINT.POS_LATITUDE,
                }
        );
        cfg.view().form().addGroup(null,
                new Object[] {
                        EAMTables.EAM_INSPECTION_POINT.CONTENT,
                        EAMTables.EAM_INSPECTION_POINT.NOTES,
                        EAMTables.EAM_INSPECTION_POINT.PICTURE_ID,

                }
        );



        //????????????????????????
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.IGNORE) //???????????????
                .setControllerAndAgent(WriteMode.IGNORE) //Rest
                .setPageController(WriteMode.IGNORE) //???????????????
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //??????HTML???
                .setListPage(WriteMode.COVER_EXISTS_FILE)
                .setExtendJsFile(WriteMode.COVER_EXISTS_FILE); //??????HTML???
        ; //??????HTML???
        //????????????
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        InspPointGtr g=new InspPointGtr();
        //????????????
       g.generateCode();

       // g.removeByBatchId("507635127677878272");
        //?????????????????????????????????????????????
       //g.generateMenu(InspectionPointServiceProxy.class, InspectionPointPageController.class);
        //????????????

    }

}
