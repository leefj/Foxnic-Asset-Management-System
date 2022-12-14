package com.dt.platform.generator.module.ops;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.constants.db.OpsTables;
import com.dt.platform.constants.enums.ops.MonitorEnableEnum;
import com.dt.platform.domain.ops.MonitorTplGraph;
import com.dt.platform.domain.ops.MonitorTplIndicator;
import com.dt.platform.domain.ops.MonitorTplType;
import com.dt.platform.domain.ops.SoftwareBaseType;
import com.dt.platform.domain.ops.meta.MonitorTplMeta;
import com.dt.platform.domain.ops.meta.MonitorTplTypeMeta;
import com.dt.platform.generator.config.Config;
import com.dt.platform.ops.page.SoftwareBaseTypePageController;
import com.dt.platform.proxy.ops.MonitorTplTypeServiceProxy;
import com.dt.platform.proxy.ops.SoftwareBaseTypeServiceProxy;
import com.github.foxnic.generator.config.WriteMode;

public class SoftwareBaseTypeGtr extends BaseCodeGenerator{


    public SoftwareBaseTypeGtr() {
        super(OpsTables.OPS_SOFTWARE_BASE_TYPE.$TABLE,"593811567112364032");
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());
        cfg.view().field(OpsTables.OPS_SOFTWARE_BASE_TYPE.NAME).search().fuzzySearch();

        cfg.view().search().inputLayout(
                new Object[]{
                        OpsTables.OPS_SOFTWARE_BASE_TYPE.NAME,

                }
        );
        cfg.view().field(OpsTables.OPS_SOFTWARE_BASE_TYPE.CREATE_TIME).table().disable(true);
        cfg.view().field(OpsTables.OPS_SOFTWARE_BASE_TYPE.ID).basic().hidden(true);
        cfg.view().field(OpsTables.OPS_SOFTWARE_BASE_TYPE.ID).table().disable(true);
        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);

        cfg.view().field(OpsTables.OPS_SOFTWARE_BASE_TYPE.NAME).form().validate().required();
        cfg.view().field(OpsTables.OPS_SOFTWARE_BASE_TYPE.NOTES).form().textArea().height(Config.textAreaHeight);



        cfg.view().list().disableBatchDelete();
        cfg.view().formWindow().width(Config.baseFormWidth);
        cfg.view().formWindow().bottomSpace(20);
        cfg.view().form().addGroup(null,
                new Object[] {
                        OpsTables.OPS_SOFTWARE_BASE_TYPE.NAME,
                        OpsTables.OPS_SOFTWARE_BASE_TYPE.NOTES,
                }
        );

        //????????????????????????
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.COVER_EXISTS_FILE) //???????????????
                .setControllerAndAgent(WriteMode.COVER_EXISTS_FILE) //Rest
                .setPageController(WriteMode.COVER_EXISTS_FILE) //???????????????
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //??????HTML???
                .setListPage(WriteMode.COVER_EXISTS_FILE)//??????HTML???
                .setExtendJsFile(WriteMode.COVER_EXISTS_FILE); //??????HTML???
        //????????????
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        SoftwareBaseTypeGtr g=new SoftwareBaseTypeGtr();
        //????????????
        g.generateCode();

        //?????????????????????????????????????????????
     //   g.generateMenu(SoftwareBaseTypeServiceProxy.class, SoftwareBaseTypePageController.class);
//        delete from sys_menu_resource where resource_id in (select id from sys_resourze where batch_id='541696318553194496');
//        delete from sys_role_menu where menu_id in (select id from sys_menu where batch_id='541696318553194496');
//        delete from sys_menu where batch_id='541696318553194496';
//        delete from sys_resourze where batch_id='541696318553194496'

    }
}
