package com.dt.platform.generator.module.hr;


import com.dt.platform.constants.db.HrTables;
import com.dt.platform.domain.hr.PersonContract;
import com.dt.platform.domain.hr.meta.PersonContractMeta;
import com.dt.platform.domain.hr.meta.PersonMeta;
import com.dt.platform.generator.config.Config;
import com.dt.platform.hr.page.PersonContractPageController;
import com.dt.platform.proxy.hr.PersonContractServiceProxy;
import com.github.foxnic.generator.config.WriteMode;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.system.meta.DictItemMeta;
import org.github.foxnic.web.proxy.system.DictItemServiceProxy;


public class HrmContactGtr extends BaseCodeGenerator {
    public HrmContactGtr() {
        super(HrTables.HR_PERSON_CONTRACT.$TABLE,"660861237046804480");
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());


        cfg.view().field(HrTables.HR_PERSON_CONTRACT.ID).basic().hidden(true);



        cfg.view().field(HrTables.HR_PERSON.CONTRACT_START_DATE).form().dateInput().format("yyyy-MM-dd").search().range();
        cfg.view().field(HrTables.HR_PERSON.CONTRACT_FINISH_DATE).form().dateInput().format("yyyy-MM-dd").search().range();
        cfg.getPoClassFile().addSimpleProperty(DictItem.class,"contractDurationDict","contractDurationDict","contractDurationDict");


    //
        cfg.view().search().inputLayout(
                new Object[]{
                        HrTables.HR_PERSON_CONTRACT.STATUS,
                        HrTables.HR_PERSON_CONTRACT.CONTRACT_DURATION,

                },
                new Object[]{
                        HrTables.HR_PERSON_CONTRACT.CONTRACT_START_DATE,
                        HrTables.HR_PERSON_CONTRACT.CONTRACT_FINISH_DATE,
                }
        );


        cfg.view().field(HrTables.HR_PERSON_CONTRACT.CONTRACT_DURATION).form().validate().required().form()
                .form().selectBox().queryApi(DictItemServiceProxy.QUERY_LIST+"?dictCode=hr_contract_duration")
                .paging(false).filter(false).toolbar(false)
                .valueField(DictItemMeta.CODE).
                textField(DictItemMeta.LABEL).
                fillWith(PersonContractMeta.CONTRACT_DURATION_DICT).muliti(false);


        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().labelWidth(3,Config.searchLabelWidth);
        cfg.view().search().labelWidth(4,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);
        cfg.view().search().rowsDisplay(1);
        cfg.view().field(HrTables.HR_PERSON_CONTRACT.FILE_ID).form().upload().maxFileCount(6);
        cfg.view().field(HrTables.HR_PERSON_CONTRACT.NOTES).form().textArea().height(Config.textAreaHeight);
        cfg.view().formWindow().width("65%");;
        cfg.view().formWindow().bottomSpace(20);
        cfg.view().form().addGroup(null,
                new Object[] {
                        HrTables.HR_PERSON_CONTRACT.STATUS,
                        HrTables.HR_PERSON_CONTRACT.CONTRACT_DURATION,
                },
                new Object[] {
                        HrTables.HR_PERSON_CONTRACT.CONTRACT_START_DATE,
                        HrTables.HR_PERSON_CONTRACT.CONTRACT_FINISH_DATE,
                }
        );

        cfg.view().form().addGroup(null,
                new Object[] {
                        HrTables.HR_PERSON_CONTRACT.NOTES,
                        HrTables.HR_PERSON_CONTRACT.FILE_ID,
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
        HrmContactGtr g=new HrmContactGtr();
        //生成代码
        g.generateCode();

        //移除之前生成的菜单，视情况执行
       // System.out.println("############"+g.getTablePrefix());
        //g.removeByBatchId("470160949404237824");
      //  //生成菜单
    //  g.generateMenu(PersonContractServiceProxy.class, PersonContractPageController.class);
    }
}
