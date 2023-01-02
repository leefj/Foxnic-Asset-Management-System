package com.dt.platform.generator.module.hr;


import com.dt.platform.constants.db.HrTables;
import com.dt.platform.constants.enums.hr.ContractStatusEnum;
import com.dt.platform.constants.enums.hr.ContractTransferToRegularEnum;
import com.dt.platform.domain.hr.PersonContract;
import com.dt.platform.domain.hr.meta.PersonContractMeta;
import com.dt.platform.domain.hr.meta.PersonMeta;
import com.dt.platform.domain.hr.meta.PersonVOMeta;
import com.dt.platform.generator.config.Config;
import com.dt.platform.hr.page.PersonContractPageController;
import com.dt.platform.proxy.hr.PersonContractServiceProxy;
import com.github.foxnic.generator.config.WriteMode;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.system.meta.DictItemMeta;
import org.github.foxnic.web.proxy.system.DictItemServiceProxy;
import com.dt.platform.domain.hr.Person;

public class HrmContactGtr extends BaseCodeGenerator {
    public HrmContactGtr() {
        super(HrTables.HR_PERSON_CONTRACT.$TABLE,"660861237046804480");
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());


        cfg.view().field(HrTables.HR_PERSON_CONTRACT.ID).basic().hidden(true);

        cfg.view().field(HrTables.HR_PERSON.CONTRACT_START_DATE).form().dateInput().format("yyyy-MM-dd").search().range();
        cfg.view().field(HrTables.HR_PERSON.CONTRACT_FINISH_DATE).form().dateInput().format("yyyy-MM-dd").search().range();
    //    cfg.getPoClassFile().addSimpleProperty(DictItem.class,"contractDurationData","contractDurationData","contractDurationData");
        cfg.getPoClassFile().addSimpleProperty(DictItem.class,"contractTypeData","contractTypeData","contractTypeData");
        cfg.getPoClassFile().addSimpleProperty(DictItem.class,"contractYearData","contractYearData","contractYearData");

        cfg.getPoClassFile().addSimpleProperty(Employee.class,"employee","employee","employee");
        cfg.getPoClassFile().addSimpleProperty(Person.class,"person","person","person");

        cfg.getPoClassFile().addSimpleProperty(String.class,"employeeName","employeeName","employeeName");

        cfg.view().search().inputLayout(
                new Object[]{
                        HrTables.HR_PERSON_CONTRACT.BUSINESS_CODE,
                        HrTables.HR_PERSON_CONTRACT.STATUS,
                        HrTables.HR_PERSON_CONTRACT.TRANSFER_TO_REGULAR,
                        HrTables.HR_PERSON_CONTRACT.TYPE,
                },
                new Object[]{
                        HrTables.HR_PERSON_CONTRACT.CONTRACT_YEAR,
                        HrTables.HR_PERSON_CONTRACT.CONTRACT_START_DATE,
                        HrTables.HR_PERSON_CONTRACT.CONTRACT_FINISH_DATE,
                }
        );



        cfg.view().field(HrTables.HR_PERSON_CONTRACT.TRANSFER_TO_REGULAR).form().validate().required().form().radioBox().enumType(ContractTransferToRegularEnum.class).defaultIndex(0);


        cfg.view().field(HrTables.HR_PERSON_CONTRACT.TYPE).form().validate().required().form()
                .form().selectBox().queryApi(DictItemServiceProxy.QUERY_LIST+"?dictCode=hr_contract_type")
                .paging(false).filter(false).toolbar(false)
                .valueField(DictItemMeta.CODE).
                textField(DictItemMeta.LABEL).
                fillWith(PersonContractMeta.CONTRACT_TYPE_DATA).muliti(false);


        cfg.view().field(HrTables.HR_PERSON_CONTRACT.CONTRACT_YEAR).form().validate().required().form()
                .form().selectBox().queryApi(DictItemServiceProxy.QUERY_LIST+"?dictCode=hr_contract_year")
                .paging(false).filter(false).toolbar(false)
                .valueField(DictItemMeta.CODE).
                textField(DictItemMeta.LABEL).
                fillWith(PersonContractMeta.CONTRACT_YEAR_DATA).muliti(false);

        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().labelWidth(3,Config.searchLabelWidth);
        cfg.view().search().labelWidth(4,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);
        cfg.view().search().rowsDisplay(1);
        cfg.view().field(HrTables.HR_PERSON_CONTRACT.STATUS).form().validate().required().form().radioBox().enumType(ContractStatusEnum.class);
        cfg.view().field(HrTables.HR_PERSON_CONTRACT.FILE_ID).form().upload().maxFileCount(6);
        cfg.view().field(HrTables.HR_PERSON_CONTRACT.NOTES).form().textArea().height(Config.textAreaHeight);


        cfg.view().field( PersonContractMeta.EMPLOYEE_NAME).basic().label("姓名");
        cfg.view().formWindow().width("80%");
        cfg.view().formWindow().bottomSpace(20);

        cfg.view().form().addGroup("员工信息",
                new Object[] {
                        PersonContractMeta.EMPLOYEE_NAME,
                }
//                new Object[] {
//                        PersonContractMeta.EMPLOYEE_NAME,
//                }
        );
        cfg.view().form().addGroup("合同信息",
                new Object[] {
                        HrTables.HR_PERSON_CONTRACT.BUSINESS_CODE,
                        HrTables.HR_PERSON_CONTRACT.STATUS,
                },
                new Object[] {
                        HrTables.HR_PERSON_CONTRACT.TYPE,
                        HrTables.HR_PERSON_CONTRACT.CONTRACT_DURATION,

                },
                new Object[] {
                        HrTables.HR_PERSON_CONTRACT.CONTRACT_YEAR,
                        HrTables.HR_PERSON_CONTRACT.TRANSFER_TO_REGULAR,
                }
        );

        cfg.view().form().addGroup("试用期信息",
                new Object[] {
                        HrTables.HR_PERSON_CONTRACT.PROBATION_SALARY,
                },
                new Object[] {
                        HrTables.HR_PERSON_CONTRACT.PROBATION_START_DATE,
                },
                new Object[] {
                        HrTables.HR_PERSON_CONTRACT.PROBATION_FINISH_DATE,
                }
        );

        cfg.view().form().addGroup("转正信息",
                new Object[] {
                        HrTables.HR_PERSON_CONTRACT.SALARY,
                },
                new Object[] {
                        HrTables.HR_PERSON_CONTRACT.CONTRACT_START_DATE,
                },
                new Object[] {
                        HrTables.HR_PERSON_CONTRACT.CONTRACT_FINISH_DATE,
                }
        );


        cfg.view().form().addGroup("其他信息",
                new Object[] {
                        HrTables.HR_PERSON_CONTRACT.NOTES,
                        HrTables.HR_PERSON_CONTRACT.FILE_ID,
                }
        );



        //文件生成覆盖模式
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.COVER_EXISTS_FILE) //服务与接口
                .setControllerAndAgent(WriteMode.COVER_EXISTS_FILE) //Rest
                .setPageController(WriteMode.IGNORE) //页面控制器
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
