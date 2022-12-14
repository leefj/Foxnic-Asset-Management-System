package com.dt.platform.generator.module.ops;

import com.dt.platform.constants.db.OpsTables;
import com.dt.platform.generator.config.Config;
import com.dt.platform.ops.page.AutoActionFilePageController;
import com.dt.platform.proxy.ops.AutoActionFileServiceProxy;
import com.github.foxnic.generator.config.WriteMode;

public class AutoActionFileGtr extends BaseCodeGenerator{


    public AutoActionFileGtr() {
        super(OpsTables.OPS_AUTO_ACTION_FILE.$TABLE,"613727231390253056");
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());


        cfg.view().field(OpsTables.OPS_AUTO_ACTION.NAME).search().fuzzySearch();
        cfg.view().field(OpsTables.OPS_AUTO_ACTION.NOTES).search().fuzzySearch();


        cfg.view().search().inputLayout(
                new Object[]{
                        OpsTables.OPS_AUTO_ACTION_FILE.NAME,
                        OpsTables.OPS_AUTO_ACTION_FILE.NOTES,
                }
        );


        cfg.view().search().rowsDisplay(1);
        cfg.view().field(OpsTables.OPS_AUTO_ACTION_FILE.ID).basic().hidden(true);
        cfg.view().field(OpsTables.OPS_AUTO_ACTION_FILE.FILE_ID).table().disable(true);

        cfg.view().field(OpsTables.OPS_AUTO_ACTION_FILE.NAME).form().validate().required();
        cfg.view().field(OpsTables.OPS_AUTO_ACTION_FILE.FILE_NAME).form().validate().required();
        cfg.view().field(OpsTables.OPS_AUTO_ACTION_FILE.FILE_ID).table().form().validate().required().form().upload().acceptSingleFile().maxFileCount(1).acceptAllType();
        cfg.view().field(OpsTables.OPS_AUTO_ACTION_FILE.NOTES).table().form().textArea().height(Config.textAreaHeight);

        cfg.view().formWindow().bottomSpace(80);
        cfg.view().formWindow().width(Config.baseFormWidth);;
        cfg.view().form().addGroup(null,
                new Object[] {
                        OpsTables.OPS_AUTO_ACTION_FILE.NAME,
                        OpsTables.OPS_AUTO_ACTION_FILE.FILE_NAME,
                        OpsTables.OPS_AUTO_ACTION_FILE.FILE_ID,
                        OpsTables.OPS_AUTO_ACTION_FILE.NOTES,
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
        AutoActionFileGtr g=new AutoActionFileGtr();
        //????????????
        g.generateCode();
     //   g.generateMenu(AutoActionFileServiceProxy.class, AutoActionFilePageController.class);
    }
}
