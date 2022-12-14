package com.dt.platform.generator.module.ops;

import com.dt.platform.constants.db.OpsTables;
import com.dt.platform.constants.enums.common.StatusEnableEnum;
import com.dt.platform.constants.enums.ops.OpsAutoTaskRunStatusEnum;
import com.dt.platform.domain.ops.*;
import com.dt.platform.domain.ops.meta.*;
import com.dt.platform.generator.config.Config;
import com.dt.platform.ops.page.AutoTaskPageController;
import com.dt.platform.proxy.ops.AutoActionServiceProxy;
import com.dt.platform.proxy.ops.AutoBatchServiceProxy;
import com.dt.platform.proxy.ops.AutoGroupServiceProxy;
import com.dt.platform.proxy.ops.AutoTaskServiceProxy;
import com.github.foxnic.generator.config.WriteMode;

public class AutoTaskGtr extends BaseCodeGenerator{


    public AutoTaskGtr() {
        super(OpsTables.OPS_AUTO_TASK.$TABLE,"613652361268690944");
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());

        cfg.view().field(OpsTables.OPS_AUTO_TASK.NOTES).search().fuzzySearch();

        cfg.getPoClassFile().addSimpleProperty(AutoGroup.class,"group","group","group");
        cfg.getPoClassFile().addSimpleProperty(AutoBatch.class,"batch","batch","batch");
        cfg.getPoClassFile().addSimpleProperty(AutoAction.class,"action","action","action");

        cfg.getPoClassFile().addSimpleProperty(String.class,"actionConfContent","actionConfContent","actionConfContent");
        cfg.getPoClassFile().addSimpleProperty(String.class,"actionExecuteContent","actionExecuteContent","actionExecuteContent");
        cfg.getPoClassFile().addSimpleProperty(String.class,"actionInfo","actionInfo","actionInfo");
        cfg.getPoClassFile().addSimpleProperty(String.class,"actionSupport","actionSupport","actionSupport");

        cfg.getPoClassFile().addListProperty(AutoNode.class,"nodeList","nodeList","nodeList");
        cfg.getPoClassFile().addListProperty(String.class,"nodeIds","nodeIds","nodeIds");


        cfg.view().search().inputLayout(
                new Object[]{
                        OpsTables.OPS_AUTO_TASK.STATUS,
                        OpsTables.OPS_AUTO_TASK.GROUP_ID,
                        OpsTables.OPS_AUTO_TASK.ACTION_ID,
                        OpsTables.OPS_AUTO_TASK.NAME,
                },
                new Object[]{
                        OpsTables.OPS_AUTO_TASK.NOTES,
                }
        );
        cfg.view().search().rowsDisplay(1);
        cfg.view().field(OpsTables.OPS_AUTO_TASK.ID).basic().hidden(true);

        cfg.view().field(OpsTables.OPS_AUTO_TASK.RUN_STATUS).table().form().radioBox().enumType(OpsAutoTaskRunStatusEnum.class).defaultIndex(0);

        cfg.view().field(OpsTables.OPS_AUTO_TASK.STATUS).table().form().validate().required().form().radioBox().enumType(StatusEnableEnum.class).defaultIndex(0);
        cfg.view().field(OpsTables.OPS_AUTO_TASK.OWNER_ID).table().disable(true);
        cfg.view().field(OpsTables.OPS_AUTO_TASK.NAME).table().form().validate().required();
        cfg.view().field(OpsTables.OPS_AUTO_TASK.NOTES).table().form().textInput();
        cfg.view().field(OpsTables.OPS_AUTO_TASK.CONF_CONTENT).table().form().textArea().height(Config.textAreaHeight*2);
        cfg.view().field(OpsTables.OPS_AUTO_TASK.GROUP_ID).table().disable(true);
        cfg.view().field(OpsTables.OPS_AUTO_TASK.SELECTED_CODE).table().disable(true);

        cfg.view().field(AutoTaskMeta.ACTION_CONF_CONTENT).table().disable(true);
        cfg.view().field(AutoTaskMeta.ACTION_EXECUTE_CONTENT).table().disable(true);
        cfg.view().field(AutoTaskMeta.ACTION_INFO).table().disable(true);
        cfg.view().field(AutoTaskMeta.ACTION_SUPPORT).table().disable(true);

        cfg.view().field(OpsTables.OPS_AUTO_TASK.OWNER_ID).table().disable(true);
        cfg.view().field(OpsTables.OPS_AUTO_TASK.CONF_CONTENT).table().disable(true);


        cfg.view().list().operationColumn().addActionButton("????????????","autoTaskCheck","auto-task-check","ops_auto_task:check");
        cfg.view().list().operationColumn().addActionButton("??????","autoTaskExecute","auto-task-execute","ops_auto_task:execute");
        cfg.view().list().operationColumn().addActionButton("??????","autoTaskLog",null,"ops_auto_task:log");
        cfg.view().list().operationColumn().width(250);

        cfg.view().field(AutoTaskMeta.ACTION_CONF_CONTENT).basic().label("????????????").form().readOnly().textArea().height(Config.textAreaHeight*2);
        cfg.view().field(AutoTaskMeta.ACTION_EXECUTE_CONTENT).basic().label("??????????????????").form().readOnly().textArea().height(Config.textAreaHeight*2);
        cfg.view().field(AutoTaskMeta.ACTION_INFO).basic().label("????????????").form().readOnly().textInput();
        cfg.view().field(AutoTaskMeta.ACTION_SUPPORT).basic().label("????????????").form().readOnly().textInput();


        cfg.view().field(OpsTables.OPS_AUTO_TASK.ACTION_ID)
                .form().validate().required().form().selectBox().queryApi(AutoActionServiceProxy.QUERY_PAGED_LIST)
                .paging(true).filter(true).toolbar(false)
                .valueField(AutoActionMeta.ID).
                textField(AutoActionMeta.NAME).
                fillWith(AutoTaskMeta.ACTION).muliti(false);


        cfg.view().field(OpsTables.OPS_AUTO_TASK.GROUP_ID)
                .form().selectBox().queryApi(AutoGroupServiceProxy.QUERY_PAGED_LIST)
                .paging(true).filter(false).toolbar(false)
                .valueField(AutoGroupMeta.ID).
                textField(AutoGroupMeta.NAME).
                fillWith(AutoTaskMeta.GROUP).muliti(false);

        cfg.view().field(OpsTables.OPS_AUTO_TASK.BATCH_ID)
                .form().selectBox().queryApi(AutoBatchServiceProxy.QUERY_PAGED_LIST)
                .paging(true).filter(false).toolbar(false)
                .valueField(AutoBatchMeta.ID).
                textField(AutoBatchMeta.NAME).
                fillWith(AutoTaskMeta.BATCH).muliti(false);

        cfg.view().formWindow().bottomSpace(80);
        cfg.view().formWindow().width(Config.baseFormWidth_95);;
        cfg.view().form().addGroup(null,
                new Object[] {
                        OpsTables.OPS_AUTO_TASK.NAME,

                },
                new Object[] {
                        OpsTables.OPS_AUTO_TASK.STATUS,

                }
        );
        cfg.view().form().addGroup(null,
                new Object[] {
                        OpsTables.OPS_AUTO_TASK.NOTES,
                }
        );
        cfg.view().form().addGroup("????????????",
                new Object[] {
                        OpsTables.OPS_AUTO_TASK.ACTION_ID,
                        AutoTaskMeta.ACTION_INFO,
                        AutoTaskMeta.ACTION_SUPPORT,
                }
        );

        cfg.view().form().addGroup(null,
                new Object[] {
                        AutoTaskMeta.ACTION_CONF_CONTENT,
                        OpsTables.OPS_AUTO_TASK.CONF_CONTENT,
                },
                new Object[] {
                        AutoTaskMeta.ACTION_EXECUTE_CONTENT,
                }
        );


        cfg.view().form().addGroup("????????????",
                new Object[] {
                        OpsTables.OPS_AUTO_TASK.BATCH_ID,
                }
        );
        cfg.view().form().addPage("????????????","nodeListSelect");

        //????????????????????????
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.IGNORE) //???????????????
                .setControllerAndAgent(WriteMode.IGNORE) //Rest
                .setPageController(WriteMode.COVER_EXISTS_FILE) //???????????????
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //??????HTML???
                .setListPage(WriteMode.COVER_EXISTS_FILE)//??????HTML???
                .setExtendJsFile(WriteMode.IGNORE); //??????HTML???
        //????????????
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        AutoTaskGtr g=new AutoTaskGtr();
        //????????????
        g.generateCode();
      //  g.generateMenu(AutoTaskServiceProxy.class, AutoTaskPageController.class);
    }
}
