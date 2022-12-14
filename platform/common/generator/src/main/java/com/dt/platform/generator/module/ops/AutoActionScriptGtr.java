package com.dt.platform.generator.module.ops;

import com.dt.platform.constants.db.OpsTables;
import com.dt.platform.constants.enums.common.StatusEnableEnum;
import com.dt.platform.constants.enums.ops.OpsAutoActionTypeEnum;
import com.dt.platform.constants.enums.ops.OpsAutoExecuteTypeEnum;
import com.dt.platform.domain.ops.Host;
import com.dt.platform.domain.ops.MonitorNode;
import com.dt.platform.domain.ops.MonitorTpl;
import com.dt.platform.domain.ops.ServiceInfo;
import com.dt.platform.generator.config.Config;
import com.dt.platform.ops.page.AutoActionScriptPageController;
import com.dt.platform.proxy.ops.AutoActionScriptServiceProxy;
import com.github.foxnic.generator.config.WriteMode;
import org.github.foxnic.web.domain.system.DictItem;

public class AutoActionScriptGtr extends BaseCodeGenerator{


    public AutoActionScriptGtr() {
        super(OpsTables.OPS_AUTO_ACTION_SCRIPT.$TABLE,"613727231390253056");
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());

        cfg.view().field(OpsTables.OPS_AUTO_ACTION.NAME).search().fuzzySearch();
        cfg.view().field(OpsTables.OPS_AUTO_ACTION.NOTES).search().fuzzySearch();
        cfg.view().search().inputLayout(
                new Object[]{
                        OpsTables.OPS_AUTO_ACTION_SCRIPT.NAME,
                        OpsTables.OPS_AUTO_ACTION_SCRIPT.NOTES,
                }
        );


        cfg.view().search().rowsDisplay(1);
        cfg.view().field(OpsTables.OPS_AUTO_ACTION_SCRIPT.ID).basic().hidden(true);
        cfg.view().field(OpsTables.OPS_AUTO_ACTION_SCRIPT.NAME).form().validate().required();
        cfg.view().field(OpsTables.OPS_AUTO_ACTION_SCRIPT.FILE_NAME).form().validate().required();
        cfg.view().field(OpsTables.OPS_AUTO_ACTION_SCRIPT.FILE_ID).table().disable(true);
        cfg.view().field(OpsTables.OPS_AUTO_ACTION_SCRIPT.FILE_ID).table().form().validate().required().form().upload().acceptSingleFile().maxFileCount(1).acceptAllType();
        cfg.view().field(OpsTables.OPS_AUTO_ACTION_SCRIPT.NOTES).table().form().textArea().height(Config.textAreaHeight);

        cfg.view().formWindow().bottomSpace(80);
        cfg.view().formWindow().width(Config.baseFormWidth);;
        cfg.view().form().addGroup(null,
                new Object[] {
                        OpsTables.OPS_AUTO_ACTION_SCRIPT.NAME,
                        OpsTables.OPS_AUTO_ACTION_SCRIPT.FILE_NAME,
                        OpsTables.OPS_AUTO_ACTION_SCRIPT.FILE_ID,
                        OpsTables.OPS_AUTO_ACTION_SCRIPT.NOTES,
                }
        );


        //A???B???????????? ???AA???
        cfg.setRelationField(MonitorNode.class,OpsTables.OPS_MONITOR_NODE_TPL_ITEM.NODE_ID, MonitorTpl.class, OpsTables.OPS_MONITOR_NODE_TPL_ITEM.TPL_CODE,true);
        //cfg.setRelationField(Host.class,OpsTables.OPS_HOST_MID.HOST_ID, ServiceInfo.class, OpsTables.OPS_HOST_MID.SERVICE_INFO_ID,true);

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
        AutoActionScriptGtr g=new AutoActionScriptGtr();
        //????????????
        g.generateCode();
       //g.generateMenu(AutoActionScriptServiceProxy.class, AutoActionScriptPageController.class);
    }
}
