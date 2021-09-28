package com.dt.platform.generator.module.ops;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.constants.enums.DictEnum;
import com.dt.platform.domain.ops.*;
import com.dt.platform.domain.ops.meta.InformationSystemMeta;
import com.dt.platform.domain.ops.meta.InformationSystemVOMeta;
import com.dt.platform.domain.ops.meta.VoucherOwnerMeta;
import com.dt.platform.proxy.ops.ServiceInfoServiceProxy;
import com.github.foxnic.generator.config.WriteMode;
import org.github.foxnic.web.domain.hrm.Organization;

public class OpsInformationSystemGtr extends BaseCodeGenerator{


    public OpsInformationSystemGtr() {
        super(EAMTables.OPS_INFORMATION_SYSTEM.$TABLE,INFOYSTEM_MENU_ID);
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());

//        cfg.getPoClassFile().addListProperty(Host.class,"hostList","主机信息","主机信息");
//        cfg.getPoClassFile().addListProperty(String.class,"hostIds","主机信息","主机信息");


        cfg.getPoClassFile().addListProperty(Voucher.class,"voucherList","凭证","凭证");
        cfg.getPoClassFile().addListProperty(String.class,"voucherIds","凭证","凭证");

        cfg.getPoClassFile().addSimpleProperty(Organization.class,"belongOrganization","所属公司/部门","所属公司/部门");


        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.ID).basic().hidden(true);
        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.PID).basic().hidden(true);

        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.NAME).search().fuzzySearch();
        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.NOTES).search().fuzzySearch();
        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.PROFILE).search().fuzzySearch();
        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.BUSINESS_CONTACT).search().fuzzySearch();
        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.TECHNICAL_CONTACT).search().fuzzySearch();

        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.CREATE_TIME).table().disable(true);
        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.OFFLINE_DATE).table().hidden(true);
        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.ARCH_METHOD).table().hidden(true);
        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.HARDWARE_INFO).table().hidden(true);
        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.BACKUP_INFO).table().hidden(true);
        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.DIFFPLACE_BACKUP_INFO).table().hidden(true);
        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.SAMEPLACE_BACUP_INFO).table().hidden(true);
        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.DEV_METHOD).table().hidden(true);
        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.OPS_METHOD).table().hidden(true);
        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.DB_INFO).table().hidden(true);
        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.OS_INFO).table().hidden(true);
        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.APP_INFO).table().hidden(true);

        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.LASTDRILL_DATE).table().hidden(true);

        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.PROFILE).table().hidden(true);


        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.BELONG_ORG_ID)
                .form().button().chooseOrganization(true);
        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.BELONG_ORG_ID).table().fillBy("belongOrganization","fullName");


        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.STATUS).basic().label("状态")
                .form().validate().required()
                .form().radioBox().dict(DictEnum.OPS_SYSTEM_STATUS).defaultIndex(0);

        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.OPS_METHOD).basic().label("运维模式")
                .form().selectBox().dict(DictEnum.OPS_SYSTEM_OPS_METHOD).paging(false);


        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.DEV_METHOD).basic().label("开发模式")
                .form().selectBox().dict(DictEnum.OPS_SYSTEM_DEV_METHOD).paging(false);

        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.GRADE).basic().label("系统分级")
                .form().selectBox().dict(DictEnum.OPS_SYSTEM_GRADE).paging(false);

        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.ONLINE_DATE).form().dateInput().format("yyyy-MM-dd").search().range();

        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.OFFLINE_DATE).form().dateInput().format("yyyy-MM-dd").search().range();

        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.LASTDRILL_DATE).basic().label("演练时间").form().
                dateInput().format("yyyy-MM-dd").search().range();

        cfg.view().field(EAMTables.OPS_INFORMATION_SYSTEM.NAME).form().validate().required();

        ;


        cfg.view().field(InformationSystemVOMeta.VOUCHER_IDS)
                .basic().label("用户凭证")
                .table().sort(false)
                .form().selectBox().queryApi(ServiceInfoServiceProxy.QUERY_LIST+"?groupId=os")
                .valueField("user_code").textField("voucher")
                .toolbar(false).paging(false)
                .fillBy(InformationSystemMeta.VOUCHER_LIST).muliti(true);


        //此设置用于覆盖字段的独立配置；清单中没有出现的，设置为隐藏；重复出现或不存在的字段将抛出异常；只接受 DBField 或 String 类型的元素
        cfg.view().search().inputLayout(
                new Object[]{
                        EAMTables.OPS_INFORMATION_SYSTEM.BELONG_ORG_ID,
                        EAMTables.OPS_INFORMATION_SYSTEM.STATUS,
                        EAMTables.OPS_INFORMATION_SYSTEM.GRADE,
                        EAMTables.OPS_INFORMATION_SYSTEM.NAME,

                },
                new Object[]{
                        EAMTables.OPS_INFORMATION_SYSTEM.BUSINESS_CONTACT,
                        EAMTables.OPS_INFORMATION_SYSTEM.TECHNICAL_CONTACT,
                        EAMTables.OPS_INFORMATION_SYSTEM.LABELS,
                        EAMTables.OPS_INFORMATION_SYSTEM.NOTES
                }

        );

        //分成分组布局
        cfg.view().formWindow().width("90%");
        cfg.view().formWindow().bottomSpace(80);
        cfg.view().form().addGroup("基本信息",
                new Object[] {
                        EAMTables.OPS_INFORMATION_SYSTEM.STATUS,
                        EAMTables.OPS_INFORMATION_SYSTEM.NAME,
                        EAMTables.OPS_INFORMATION_SYSTEM.GRADE,
                        EAMTables.OPS_INFORMATION_SYSTEM.PROFILE,

                }, new Object[] {
                        EAMTables.OPS_INFORMATION_SYSTEM.BELONG_ORG_ID,
                        EAMTables.OPS_INFORMATION_SYSTEM.ONLINE_DATE,
                        EAMTables.OPS_INFORMATION_SYSTEM.OFFLINE_DATE,
                        EAMTables.OPS_INFORMATION_SYSTEM.NOTES,
                },
                new Object[] {
                        EAMTables.OPS_INFORMATION_SYSTEM.TECHNICAL_CONTACT,
                        EAMTables.OPS_INFORMATION_SYSTEM.BUSINESS_CONTACT,
                        EAMTables.OPS_INFORMATION_SYSTEM.LABELS,
                }
        );
        cfg.view().form().addGroup("运维信息",
                new Object[] {
                        EAMTables.OPS_INFORMATION_SYSTEM.OPS_METHOD,
                        EAMTables.OPS_INFORMATION_SYSTEM.DEV_METHOD,
                        EAMTables.OPS_INFORMATION_SYSTEM.OS_INFO,
                        EAMTables.OPS_INFORMATION_SYSTEM.DB_INFO
                },
                new Object[] {
                        EAMTables.OPS_INFORMATION_SYSTEM.BACKUP_INFO,
                        EAMTables.OPS_INFORMATION_SYSTEM.HARDWARE_INFO,
                        EAMTables.OPS_INFORMATION_SYSTEM.SAMEPLACE_BACUP_INFO,
                        EAMTables.OPS_INFORMATION_SYSTEM.DIFFPLACE_BACKUP_INFO,
                }  ,
                new Object[] {
                        EAMTables.OPS_INFORMATION_SYSTEM.RPO,
                        EAMTables.OPS_INFORMATION_SYSTEM.RTO,
                        EAMTables.OPS_INFORMATION_SYSTEM.LASTDRILL_DATE
              }
        );


        cfg.view().list().operationColumn().addActionButton("凭证","openSystemVoucherWindow");
        cfg.view().list().operationColumn().addActionButton("主机","openHostWindow");
        cfg.view().list().operationColumn().width(290);

        //文件生成覆盖模式
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.CREATE_IF_NOT_EXISTS) //服务与接口
                .setControllerAndAgent(WriteMode.CREATE_IF_NOT_EXISTS) //Rest
                .setPageController(WriteMode.COVER_EXISTS_FILE) //页面控制器
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //表单HTML页
                .setListPage(WriteMode.COVER_EXISTS_FILE)//列表HTML页
                .setExtendJsFile(WriteMode.CREATE_IF_NOT_EXISTS); //列表HTML页
        //生成代码

        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        OpsInformationSystemGtr g=new OpsInformationSystemGtr();
        //生成代码
       g.generateCode();

        //移除之前生成的菜单，视情况执行
     //   g.removeByBatchId("478601852045230080");
      //  g.generateMenu(InformationSystemServiceProxy.class, InformationSystemPageController.class);
    }
}
