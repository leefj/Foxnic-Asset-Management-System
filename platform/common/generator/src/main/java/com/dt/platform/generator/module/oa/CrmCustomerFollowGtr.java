package com.dt.platform.generator.module.oa;

import com.dt.platform.constants.db.OaTables;
import com.dt.platform.domain.oa.CrmCustomer;
import com.dt.platform.domain.oa.meta.CrmCustomerFollowMeta;
import com.dt.platform.domain.oa.meta.CrmCustomerMeta;
import com.dt.platform.domain.oa.meta.CrmCustomerRecordMeta;
import com.dt.platform.generator.config.Config;
import com.dt.platform.oa.page.CrmCustomerFollowPageController;
import com.dt.platform.proxy.oa.CrmCustomerFollowServiceProxy;
import com.dt.platform.proxy.oa.CrmCustomerServiceProxy;
import com.github.foxnic.generator.config.WriteMode;
import org.github.foxnic.web.domain.hrm.Employee;

public class CrmCustomerFollowGtr extends BaseCodeGenerator {


    //754084671628771328
    public CrmCustomerFollowGtr() {
        super(OaTables.OA_CRM_CUSTOMER_FOLLOW.$TABLE,"616265395568902144");
    }

    public void generateCode() throws Exception {
        System.out.println(this.getClass().getName());

        cfg.getPoClassFile().addSimpleProperty(CrmCustomer.class,"crmCustomer","crmCustomer","crmCustomer");
        cfg.getPoClassFile().addSimpleProperty(Employee.class,"originator","originator","originator");


        cfg.view().field(OaTables.OA_CRM_CUSTOMER_FOLLOW.ID).basic().hidden(true);
        cfg.view().list().disableBatchDelete();
        cfg.view().search().disable();

        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().labelWidth(3,Config.searchLabelWidth);
        cfg.view().search().labelWidth(4,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);

        cfg.view().field(OaTables.OA_CRM_CUSTOMER_FOLLOW.CUSTOMER_ID)
                .basic().label("客户")
                .form().selectBox().queryApi(CrmCustomerServiceProxy.QUERY_PAGED_LIST).paging(true).filter(true).toolbar(false)
                .valueField(CrmCustomerMeta.ID).textField(CrmCustomerMeta.NAME).fillWith(CrmCustomerFollowMeta.CRM_CUSTOMER).muliti(false);

        cfg.view().field(OaTables.OA_CRM_CUSTOMER_FOLLOW.CONTENT).form().validate().required();

        cfg.view().search().rowsDisplay(1);
        cfg.view().field(OaTables.OA_CRM_CUSTOMER_FOLLOW.ORIGINATOR_ID).table().fillBy("originator","name");
        cfg.view().field(OaTables.OA_CRM_CUSTOMER_FOLLOW.ORIGINATOR_ID).form()
                .button().chooseEmployee(true);

        cfg.view().field(OaTables.OA_CRM_CUSTOMER_FOLLOW.NOTES).form().textArea().height(120);

        cfg.view().field(OaTables.OA_CRM_CUSTOMER_FOLLOW.FILE_ID).table().disable(true);
        cfg.view().field(OaTables.OA_CRM_CUSTOMER_FOLLOW.FILE_ID).form().upload().acceptAllType().maxFileCount(6);

        cfg.view().formWindow().bottomSpace(80);
        cfg.view().formWindow().width(Config.baseFormWidth);
        cfg.view().form().addGroup(null,
                new Object[] {
                        OaTables.OA_CRM_CUSTOMER_FOLLOW.CONTENT,
                        OaTables.OA_CRM_CUSTOMER_FOLLOW.FILE_ID,
                        OaTables.OA_CRM_CUSTOMER_FOLLOW.NOTES,
                }
        );

        cfg.view().form().addJsVariable("CUSTOMER_ID","[[${customerId}]]","customerId");
        cfg.view().list().addJsVariable("CUSTOMER_ID","[[${customerId}]]","customerId");

        //文件生成覆盖模式
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.IGNORE) //服务与接口
                .setControllerAndAgent(WriteMode.IGNORE) //Rest
                .setPageController(WriteMode.IGNORE) //页面控制器
                .setFormPage(WriteMode.COVER_EXISTS_FILE) //表单HTML页
                .setListPage(WriteMode.COVER_EXISTS_FILE)
                .setExtendJsFile(WriteMode.IGNORE); //列表HTML页
        //列表HTML页
        //生成代码
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        CrmCustomerFollowGtr g=new CrmCustomerFollowGtr();
        //生成代码
        g.generateCode();

        //移除之前生成的菜单，视情况执行
//        g.removeByBatchId("471622036347682816");
        //生成菜单
    //   g.generateMenu(CrmCustomerFollowServiceProxy.class, CrmCustomerFollowPageController.class);
    }

}