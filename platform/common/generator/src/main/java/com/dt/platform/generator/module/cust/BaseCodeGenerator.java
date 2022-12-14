package com.dt.platform.generator.module.cust;

import com.dt.platform.generator.config.PlatformConfigs;
import com.dt.platform.generator.menu.MenuGenerator;
import com.dt.platform.proxy.ServiceNames;
import com.github.foxnic.dao.data.RcdSet;
import com.github.foxnic.dao.spec.DAO;
import com.github.foxnic.generator.config.ModuleContext;
import com.github.foxnic.sql.meta.DBTable;

public class BaseCodeGenerator {

    public static final String BASIC_DATA_MENU_ID="472025257461415936";
    public static final String BASIC_RACK_MENU_ID="473228449763295232";


    //
    private String appId="service-customer";

    private String appConfigPrefix= "service-customer";

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    private String tablePrefix="cust_";

    private PlatformConfigs configs;

    private DBTable table;

    private String parentMenuId;

    protected ModuleContext cfg;

    private Class proxyType;
    private Class pageType;
    public BaseCodeGenerator(Class proxyType, Class pageType, DBTable table, String parentMenuId) {
        this.pageType=pageType;
        this.proxyType=proxyType;
        this.table=table;
        this.parentMenuId=parentMenuId;
        configs=new PlatformConfigs(appConfigPrefix);
        cfg=createModuleConfig();
    }
    public BaseCodeGenerator(Class proxyType, Class pageType, DBTable table, String parentMenuId, String tabPrefix) {
        this.pageType=pageType;
        this.proxyType=proxyType;

        if(tablePrefix!=null){
            this.tablePrefix=tabPrefix;
        }
        this.table=table;
        this.parentMenuId=parentMenuId;
        configs=new PlatformConfigs(appConfigPrefix);
        cfg=createModuleConfig();
    }
    public void generateMenu(){
        generateMenu(proxyType,pageType);
    }
    public void reGenerateMenu(){
        DAO dao= this.configs.getDAO();
        System.out.println("current table:"+ this.table.name());
        RcdSet resource_rs= dao.query("select * from sys_resourze where table_name=? and deleted=0",this.table.name());
        if(resource_rs.size()>0){
            String batch_id=resource_rs.getRcd(0).getString("batch_id");
            System.out.println("current batch_id:"+batch_id);
            RcdSet menu_rs=dao.query("select * from sys_menu where batch_id=?",batch_id);
            if(menu_rs.size()>0){
                String parent_id=menu_rs.getRcd(0).getString("parent_id");
                System.out.println("current parent_id:"+parent_id);
                removeByBatchId(batch_id);
                this.parentMenuId=parent_id;
                generateMenu(proxyType,pageType);
            }
        }
    }






    public BaseCodeGenerator(DBTable table, String parentMenuId) {

        this.table=table;
        this.parentMenuId=parentMenuId;
        configs=new PlatformConfigs(appConfigPrefix);
        cfg=createModuleConfig();
    }
    public BaseCodeGenerator(DBTable table, String parentMenuId, String tabPrefix) {
        if(tablePrefix!=null){
            this.tablePrefix=tabPrefix;
        }
        this.table=table;
        this.parentMenuId=parentMenuId;
        configs=new PlatformConfigs(appConfigPrefix);
        cfg=createModuleConfig();
    }

    public ModuleContext createModuleConfig(DBTable table, String tablePrefix, int apiSort) {

        //????????????
        PlatformConfigs.ProjectConfigs procfg=this.configs.getProjectConfigs();

        ModuleContext mdu=new ModuleContext(this.configs.getSettings(),table,tablePrefix,procfg.getAppPackageName());
        //?????????????????????????????????
        mdu.setViewPrefixPath(procfg.getAppViewPrefixPath());
        //???????????????????????????URI
        mdu.setViewPrefixURI(procfg.getAppViewPrefixURI());
        //?????? DAO
        mdu.setDAO(this.configs.getDAO());
        //?????? Domain Project
        mdu.setDomainProject(this.configs.getDomianProject());
        //?????? Proxy Project
        mdu.setProxyProject(this.configs.getProxyProject());
        //?????? Service Project
        mdu.setServiceProject(this.configs.getServiceProject());
        //?????? View Project
        mdu.setViewProject(this.configs.getViewProject());
        //?????? Wrapper Project
        mdu.setWrapperProject(this.configs.getWrapperProject());
        //??????DAO????????????
        mdu.setDAONameConsts(procfg.getDAONameConst());
        //???????????????????????????
        mdu.setMicroServiceNameConst(ServiceNames.class.getName()+"."+procfg.getAppMicroServiceNameConst());
        return mdu;
    }

    public ModuleContext createModuleConfig(int apiSort) {
        return createModuleConfig(this.table, tablePrefix, apiSort);
    }

    public ModuleContext createModuleConfig() {

        return createModuleConfig(this.table, tablePrefix, 1);
    }

    public void removeByBatchId(String batchId) {
        MenuGenerator mg=new MenuGenerator(appId,MenuGenerator.SUPER_ADMIN_ROLE_ID,table, null, null);
        mg.removeByBatchId(batchId);
    }


    public void generateMenu(Class proxyType, Class pageType){
        MenuGenerator mg=new MenuGenerator(appId,MenuGenerator.SUPER_ADMIN_ROLE_ID,table, proxyType, pageType);
        mg.generate(parentMenuId);
    }






}
