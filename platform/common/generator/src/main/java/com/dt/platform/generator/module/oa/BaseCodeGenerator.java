package com.dt.platform.generator.module.oa;

import com.dt.platform.generator.config.PlatformConfigs;
import com.dt.platform.generator.menu.MenuGenerator;
import com.dt.platform.proxy.ServiceNames;
import com.github.foxnic.generator.config.ModuleContext;
import com.github.foxnic.sql.meta.DBTable;

public class BaseCodeGenerator {

    public static final String CAR_ID="616256885288337408";
    public static final String MEETING_ROOT_ID="616256779512184832";
    public static final String BASIC_SETTING_MENU_ID="616256406374318080";



    //
    private String appId="service-oa";

    private String appConfigPrefix= "service-oa";

    private String tablePrefix="oa_";

    private PlatformConfigs configs;

    private DBTable table;

    private String parentMenuId;

    protected ModuleContext cfg;

    public BaseCodeGenerator(DBTable table, String parentMenuId) {
        this.table=table;
        this.parentMenuId=parentMenuId;
        configs=new PlatformConfigs(appConfigPrefix);
        cfg=createModuleConfig();
    }

    public ModuleContext createModuleConfig(DBTable table, String tablePrefix, int apiSort) {

        //项目配置
        PlatformConfigs.ProjectConfigs procfg=this.configs.getProjectConfigs();

        ModuleContext mdu=new ModuleContext(this.configs.getSettings(),table,tablePrefix,procfg.getAppPackageName());
        //设置页面的代码文件路径
        mdu.setViewPrefixPath(procfg.getAppViewPrefixPath());
        //设置页面访问的基础URI
        mdu.setViewPrefixURI(procfg.getAppViewPrefixURI());
        //设置 DAO
        mdu.setDAO(this.configs.getDAO());
        //设置 Domain Project
        mdu.setDomainProject(this.configs.getDomianProject());
        //设置 Proxy Project
        mdu.setProxyProject(this.configs.getProxyProject());
        //设置 Service Project
        mdu.setServiceProject(this.configs.getServiceProject());
        //设置 View Project
        mdu.setViewProject(this.configs.getViewProject());
        //设置 Wrapper Project
        mdu.setWrapperProject(this.configs.getWrapperProject());
        //设置DAO名称常量
        mdu.setDAONameConsts(procfg.getDAONameConst());
        //设置微服务命名常量
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
