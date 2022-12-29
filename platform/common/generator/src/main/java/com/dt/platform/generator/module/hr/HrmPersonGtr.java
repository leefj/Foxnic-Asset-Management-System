package com.dt.platform.generator.module.hr;


import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.constants.db.HrTables;
import com.dt.platform.domain.eam.meta.AssetMeta;
import com.dt.platform.domain.hr.Position;
import com.dt.platform.domain.hr.PositionType;
import com.dt.platform.domain.hr.ProfessionalLevel;
import com.dt.platform.domain.hr.Rank;
import com.dt.platform.domain.hr.meta.*;
import com.dt.platform.generator.config.Config;


import com.dt.platform.hr.page.PersonPageController;
import com.dt.platform.proxy.hr.*;
import com.github.foxnic.generator.config.WriteMode;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.system.meta.DictItemMeta;
import org.github.foxnic.web.proxy.system.DictItemServiceProxy;


public class HrmPersonGtr extends BaseCodeGenerator {
    public HrmPersonGtr() {
        super(HrTables.HR_PERSON.$TABLE,"623153794699493376");
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());

        cfg.getPoClassFile().addSimpleProperty(Position.class,"position","position","position");
        cfg.getPoClassFile().addSimpleProperty(Rank.class,"rank","rank","rank");
        cfg.getPoClassFile().addSimpleProperty(ProfessionalLevel.class,"professionalLevel","professionalLevel","professionalLevel");
        cfg.getPoClassFile().addSimpleProperty(DictItem.class,"bloodTypeDict","bloodTypeDict","bloodTypeDict");
        cfg.getPoClassFile().addSimpleProperty(DictItem.class,"sexDict","sexDict","sexDict");
        cfg.getPoClassFile().addSimpleProperty(DictItem.class,"maritalStatusDict","maritalStatusDict","maritalStatusDict");
        cfg.getPoClassFile().addSimpleProperty(DictItem.class,"employeeOwnerTypeDict","employeeOwnerType","employeeOwnerType");
        cfg.getPoClassFile().addSimpleProperty(Employee.class,"employee","employee","employee");

        cfg.view().field(HrTables.HR_PERSON.ID).basic().hidden(true);
        cfg.view().field(HrTables.HR_PERSON.NAME).search().fuzzySearch();
        cfg.view().field(HrTables.HR_PERSON.IDENTITY_CARD).search().fuzzySearch();
        cfg.view().field(HrTables.HR_PERSON.JOB_NUMBER).search().fuzzySearch();

        cfg.view().search().inputLayout(
                new Object[]{
                        HrTables.HR_PERSON.EMPLOYEE_STATUS,
                        HrTables.HR_PERSON.JOB_NUMBER,
                        HrTables.HR_PERSON.NAME,
                        HrTables.HR_PERSON.IDENTITY_CARD,
                },
                new Object[]{
                        HrTables.HR_PERSON.EMPLOYMENT_DATE,
                }
        );

        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().labelWidth(3,Config.searchLabelWidth);
        cfg.view().search().labelWidth(4,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);


        cfg.view().field(HrTables.HR_PERSON.BIRTHDAY).form().dateInput().format("yyyy-MM-dd").search().range();
        cfg.view().field(HrTables.HR_PERSON.EMPLOYMENT_CONFIRM_DATE).form().dateInput().format("yyyy-MM-dd").search().range();
        cfg.view().field(HrTables.HR_PERSON.EMPLOYMENT_DATE).form().dateInput().format("yyyy-MM-dd").search().range();
        cfg.view().field(HrTables.HR_PERSON.FIRST_WORK_DATE).form().dateInput().format("yyyy-MM-dd").search().range();
        cfg.view().field(HrTables.HR_PERSON.JOIN_PART_DATE).form().dateInput().format("yyyy-MM-dd").search().range();
        cfg.view().field(HrTables.HR_PERSON.FIRST_EMPLOYMENT_DATE).form().dateInput().format("yyyy-MM-dd").search().range();
        cfg.view().field(HrTables.HR_PERSON.LEAVE_DATE).form().dateInput().format("yyyy-MM-dd").search().range();


        cfg.view().field(HrTables.HR_PERSON.EMPLOYEE_ID).table().fillBy("employee","name");
        cfg.view().field(HrTables.HR_PERSON.EMPLOYEE_ID).form()
                .button().chooseEmployee(true);


        cfg.view().field(HrTables.HR_PERSON.WORK_KIND_CODE).table().disable(true);
        cfg.view().field(HrTables.HR_PERSON.PERSON_PICTURE_ID).table().disable(true);
        cfg.view().field(HrTables.HR_PERSON.FILE_ID).table().disable(true);

        cfg.view().field(HrTables.HR_PERSON.PERSON_PICTURE_ID).form().upload().acceptImageType().buttonLabel("选择图片").maxFileCount(3);
        cfg.view().field(HrTables.HR_PERSON.FILE_ID).form().upload().maxFileCount(6);

        cfg.view().field(HrTables.HR_PERSON.NAME).form().validate().required();
        cfg.view().field(HrTables.HR_PERSON.IDENTITY_CARD).form().validate().required();

        cfg.view().field(HrTables.HR_PERSON.NOTE).form().textArea().height(Config.textAreaHeight);

        cfg.view().field(HrTables.HR_PERSON.POSITION_CODE)
                .form().selectBox().queryApi(PositionServiceProxy.QUERY_PAGED_LIST)
                .paging(true).filter(true).toolbar(false)
                .valueField(PositionMeta.ID).
                textField(PositionMeta.NAME).
                fillWith(PersonMeta.POSITION).muliti(false);


        cfg.view().field(HrTables.HR_PERSON.EMPLOYEE_TITLE_CODE)
                .form().selectBox().queryApi(ProfessionalLevelServiceProxy.QUERY_PAGED_LIST)
                .paging(true).filter(true).toolbar(false)
                .valueField(ProfessionalLevelMeta.ID).
                textField(ProfessionalLevelMeta.NAME).
                fillWith(PersonMeta.PROFESSIONAL_LEVEL).muliti(false);



        cfg.view().field(HrTables.HR_PERSON.RANK_CODE)
                .form().selectBox().queryApi(RankServiceProxy.QUERY_PAGED_LIST)
                .paging(true).filter(true).toolbar(false)
                .valueField(RankMeta.ID).
                textField(RankMeta.CODE).
                fillWith(PersonMeta.RANK).muliti(false);

        cfg.view().field(HrTables.HR_PERSON.BLOOD_TYPE)
                .form().selectBox().queryApi(DictItemServiceProxy.QUERY_LIST+"?dictCode=hr_blood_type")
                .paging(false).filter(false).toolbar(false)
                .valueField(DictItemMeta.CODE).
                textField(DictItemMeta.LABEL).
                fillWith(PersonMeta.BLOOD_TYPE_DICT).muliti(false);

        cfg.view().field(HrTables.HR_PERSON.SEX_CODE)
                .form().selectBox().queryApi(DictItemServiceProxy.QUERY_LIST+"?dictCode=sex")
                .paging(false).filter(false).toolbar(false)
                .valueField(DictItemMeta.CODE).
                textField(DictItemMeta.LABEL).
                fillWith(PersonMeta.SEX_DICT).muliti(false);


        cfg.view().field(HrTables.HR_PERSON.MARITAL_STATUS)
                .form().selectBox().queryApi(DictItemServiceProxy.QUERY_LIST+"?dictCode=hr_marital_status")
                .paging(false).filter(false).toolbar(false)
                .valueField(DictItemMeta.CODE).
                textField(DictItemMeta.LABEL).
                fillWith(PersonMeta.MARITAL_STATUS_DICT).muliti(false);

        cfg.view().field(HrTables.HR_PERSON.EMPLOYEE_TYPE_CODE)
                .form().selectBox().queryApi(DictItemServiceProxy.QUERY_LIST+"?dictCode=hr_employee_owner_type")
                .paging(false).filter(false).toolbar(false)
                .valueField(DictItemMeta.CODE).
                textField(DictItemMeta.LABEL).
                fillWith(PersonMeta.EMPLOYEE_OWNER_TYPE_DICT).muliti(false);

        cfg.view().search().rowsDisplay(1);
        cfg.view().formWindow().width("65%");;
        cfg.view().formWindow().bottomSpace(20);
        cfg.view().form().addGroup("基本信息",
                new Object[] {
                        HrTables.HR_PERSON.NAME,
                        HrTables.HR_PERSON.IDENTITY_CARD,
                        HrTables.HR_PERSON.JOB_NUMBER,
                        HrTables.HR_PERSON.SEX_CODE,
                        HrTables.HR_PERSON.MARITAL_STATUS,
                },
                new Object[] {
                        HrTables.HR_PERSON.EMPLOYEE_TYPE_CODE,
                        HrTables.HR_PERSON.BIRTHDAY,
                        HrTables.HR_PERSON.NATION_CODE,
                        HrTables.HR_PERSON.NATIVE_PLACE_CODE,
                        HrTables.HR_PERSON.EMPLOYEE_ID,
                }
        );

        cfg.view().form().addGroup("个人情况",
                new Object[] {
                        HrTables.HR_PERSON.CONTACT_INFORMATION,
                        HrTables.HR_PERSON.EMERGENCY_CONTACT,
                        HrTables.HR_PERSON.HOME_ADDRESS,
                        HrTables.HR_PERSON.EDUCATION_CODE,
                        HrTables.HR_PERSON.FOREIGN_LANGUAGE,
                        HrTables.HR_PERSON.COMPUTER_ABILITY,
                        HrTables.HR_PERSON.POLITIC_COUNTENANCE_CODE,
                        HrTables.HR_PERSON.BLOOD_TYPE,
                        HrTables.HR_PERSON.BODY_WEIGHT,
                },
                new Object[] {
                        HrTables.HR_PERSON.EMAIL,
                        HrTables.HR_PERSON.EMERGENCY_CONTACT_NO,
                        HrTables.HR_PERSON.WEIXIN_ID,
                        HrTables.HR_PERSON.GRADUATION_SCHOOL,
                        HrTables.HR_PERSON.FOREIGN_LANGUAGE_LEVEL,
                        HrTables.HR_PERSON.COMPUTER_LEVEL,
                        HrTables.HR_PERSON.JOIN_PART_DATE,
                        HrTables.HR_PERSON.BODY_HEIGHT,
                }
        );
        cfg.view().form().addGroup("工作信息",
                new Object[] {
                        HrTables.HR_PERSON.EMPLOYMENT_DATE,
                        HrTables.HR_PERSON.EMPLOYMENT_CONFIRM_DATE,
//                        HrTables.HR_PERSON.ORG_ID,
                        HrTables.HR_PERSON.POSITION_CODE,
                        HrTables.HR_PERSON.LEAVE_DATE
                },
                new Object[] {
                        HrTables.HR_PERSON.FIRST_EMPLOYMENT_DATE,
                        HrTables.HR_PERSON.FIRST_WORK_DATE,
                        HrTables.HR_PERSON.EMPLOYEE_TITLE_CODE,
                        HrTables.HR_PERSON.RANK_CODE,

                }
        );
        cfg.view().form().addGroup(null,
                new Object[] {
                        HrTables.HR_PERSON.LEAVE_RESON
                }

        );



        cfg.view().form().addGroup("工资信息",
                new Object[] {
                        HrTables.HR_PERSON.PAYROLL_CARD_BANK_CODE
                },
                new Object[] {
                        HrTables.HR_PERSON.PAYROLL_CARD
                }
        );
        cfg.view().form().addGroup("其他信息",
                new Object[] {
                        HrTables.HR_PERSON.PERSON_PICTURE_ID,
                        HrTables.HR_PERSON.FILE_ID,
                        HrTables.HR_PERSON.NOTE
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
        HrmPersonGtr g=new HrmPersonGtr();
        //生成代码
        g.generateCode();

        //移除之前生成的菜单，视情况执行
       // System.out.println("############"+g.getTablePrefix());
        //g.removeByBatchId("470160949404237824");
      //  //生成菜单
     //  g.generateMenu(PersonServiceProxy.class, PersonPageController.class);
    }
}
