package com.dt.platform.generator.module.eam;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.constants.enums.common.StatusEnableEnum;
import com.dt.platform.constants.enums.eam.AssetEquipmentStatusEnum;
import com.dt.platform.constants.enums.eam.AssetOwnerCodeEnum;

import com.dt.platform.domain.eam.Position;
import com.dt.platform.domain.eam.meta.*;
import com.dt.platform.domain.ops.ServiceInfo;
import com.dt.platform.domain.ops.meta.HostMeta;
import com.dt.platform.domain.ops.meta.HostVOMeta;
import com.dt.platform.domain.ops.meta.ServiceInfoMeta;
import com.dt.platform.eam.page.AssetDataPermissionsPageController;
import com.dt.platform.eam.service.impl.AssetDataPermissionsCatalogServiceImpl;

import com.dt.platform.eam.service.impl.AssetDataPermissionsOOrgServiceImpl;
import com.dt.platform.eam.service.impl.AssetDataPermissionsOrgServiceImpl;

import com.dt.platform.eam.service.impl.AssetDataPermissionsPositionServiceImpl;
import com.dt.platform.generator.config.Config;
import com.dt.platform.ops.service.impl.HostDbServiceImpl;
import com.dt.platform.proxy.eam.AssetDataPermissionsServiceProxy;
import com.dt.platform.proxy.eam.PositionServiceProxy;
import com.dt.platform.proxy.eam.SupplierServiceProxy;
import com.dt.platform.proxy.ops.ServiceInfoServiceProxy;
import com.github.foxnic.generator.config.WriteMode;
import org.github.foxnic.web.domain.hrm.Organization;
import org.github.foxnic.web.domain.pcm.Catalog;
import org.github.foxnic.web.domain.pcm.meta.CatalogMeta;
import org.github.foxnic.web.domain.system.BusiRole;
import org.github.foxnic.web.domain.system.meta.BusiRoleMeta;
import org.github.foxnic.web.proxy.pcm.CatalogServiceProxy;
import org.github.foxnic.web.proxy.system.BusiRoleServiceProxy;
import org.github.foxnic.web.proxy.system.DictItemServiceProxy;

public class EamAssetDataPermGtr extends BaseCodeGenerator{
    public EamAssetDataPermGtr() {
        super(EAMTables.EAM_ASSET_DATA_PERMISSIONS.$TABLE,BASIC_DATA_MENU_ID);
    }

    public void generateCode() throws Exception {

        System.out.println(this.getClass().getName());

        cfg.getPoClassFile().addSimpleProperty(BusiRole.class,"busiRole","????????????","????????????");

        cfg.getPoClassFile().addListProperty(Catalog.class,"category","????????????","????????????");
        cfg.getPoClassFile().addListProperty(String.class,"categoryIds","????????????","????????????");

        cfg.getPoClassFile().addListProperty(Organization.class,"organization","????????????","????????????");
        cfg.getPoClassFile().addListProperty(String.class,"organizationIds","????????????","????????????");

        cfg.getPoClassFile().addListProperty(Organization.class,"ownOrganization","??????????????????","??????????????????");
        cfg.getPoClassFile().addListProperty(String.class,"ownOrganizationIds","??????????????????","??????????????????");

        cfg.getPoClassFile().addListProperty(Position.class,"position","????????????","????????????");
        cfg.getPoClassFile().addListProperty(String.class,"positionIds","????????????","????????????");

        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.ID).basic().hidden(true);
        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.CREATE_TIME).basic().hidden(true);
        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.NAME).search().fuzzySearch();
        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.CODE).search().fuzzySearch();
        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.ROLE_CODE).search();

        cfg.view().search().inputLayout(
                new Object[]{
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.NAME,
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.CODE,
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.ROLE_CODE,
                }
        );


        cfg.view().search().labelWidth(1,Config.searchLabelWidth);
        cfg.view().search().labelWidth(2,Config.searchLabelWidth);
        cfg.view().search().inputWidth(Config.searchInputWidth);


        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.CATALOG_NOTES).table().disable();
        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.ORG_NOTES).table().disable();
        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.OWN_ORG_NOTES).table().disable();


        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.NAME).form().validate().required();

        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.OWNER_CODE).form()
                .validate().required().form().label("??????").selectBox().enumType(AssetOwnerCodeEnum.class);

        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.CODE).form().validate().required().form().textInput();

        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.ROLE_CODE).form().validate().required().form()
                .label("????????????").selectBox().queryApi(BusiRoleServiceProxy.QUERY_PAGED_LIST).paging(true).filter(true).toolbar(false)
                .valueField(BusiRoleMeta.CODE).textField(BusiRoleMeta.NAME).fillWith(AssetDataPermissionsMeta.BUSI_ROLE).muliti(false);


        cfg.view().field(AssetDataPermissionsMeta.POSITION_IDS)
                .basic().label("????????????")
                .table().sort(false)
                .form().selectBox().queryApi(PositionServiceProxy.QUERY_PAGED_LIST)
                .valueField(PositionMeta.ID).textField(PositionMeta.HIERARCHY_NAME)
                .toolbar(false).paging(true).filter(true)
                .fillWith(AssetDataPermissionsMeta.POSITION).muliti(true);


        cfg.view().field(AssetDataPermissionsMeta.CATEGORY_IDS)
                .basic().label("????????????")
                .form().selectBox().queryApi(CatalogServiceProxy.QUERY_LIST)
                .paging(false).filter(false).toolbar(false)
                .valueField(CatalogMeta.ID).textField(CatalogMeta.NAME)
                .fillWith(AssetDataPermissionsMeta.CATEGORY).muliti(true);


        cfg.view().field(AssetDataPermissionsMeta.ORGANIZATION_IDS).basic().label("????????????")
                .form().button().chooseOrganization(false);
        cfg.view().field(AssetDataPermissionsMeta.ORGANIZATION_IDS).table().fillBy("organization","fullName");


        cfg.view().field(AssetDataPermissionsMeta.OWN_ORGANIZATION_IDS).basic().label("????????????")
                .form().button().chooseCompany(false);
        cfg.view().field(AssetDataPermissionsMeta.OWN_ORGANIZATION_IDS).table().fillBy("ownOrganization","fullName");


        cfg.view().form().addJsVariable("ASSET_CATEGORY_DATA","[[${assetCategoryData}]]","??????????????????");
        cfg.view().list().addJsVariable("CATEGORY_CODE","[[${categoryCode}]]","??????????????????");


        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.ORG_AUTHORITY_ENABLE).form().validate().required().form().radioBox().enumType(StatusEnableEnum.class);
        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.ORG_CASCADE_ENABLE).form().validate().required().form().radioBox().enumType(StatusEnableEnum.class);
        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.ORG_LOCAL_ENABLE).form().validate().required().form().radioBox().enumType(StatusEnableEnum.class);

        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.OWN_ORG_AUTHORITY_ENABLE).form().validate().required().form().radioBox().enumType(StatusEnableEnum.class);
        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.OWN_ORG_CASCADE_ENABLE).form().validate().required().form().radioBox().enumType(StatusEnableEnum.class);
        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.OWN_ORG_LOCAL_ENABLE).form().validate().required().form().radioBox().enumType(StatusEnableEnum.class);


        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.CATALOG_AUTHORITY_ENABLE).form().validate().required().form().radioBox().enumType(StatusEnableEnum.class);
        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.CATALOG_CASCADE_ENABLE).form().validate().required().form().radioBox().enumType(StatusEnableEnum.class);
        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.POSITION_AUTHORITY_ENABLE).form().validate().required().form().radioBox().enumType(StatusEnableEnum.class);
        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.STATUS).form().validate().required().form().radioBox().enumType(StatusEnableEnum.class);
        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.PRIORITY).form().validate().required().form().numberInput().integer().defaultValue(100);
        cfg.view().field(EAMTables.EAM_ASSET_DATA_PERMISSIONS.NOTES).form().textArea().height(Config.textAreaHeight);


        cfg.service().addRelationSaveAction(AssetDataPermissionsPositionServiceImpl.class,AssetDataPermissionsMeta.POSITION_IDS);
        cfg.service().addRelationSaveAction(AssetDataPermissionsCatalogServiceImpl.class,AssetDataPermissionsMeta.CATEGORY_IDS);
        cfg.service().addRelationSaveAction(AssetDataPermissionsOrgServiceImpl.class,AssetDataPermissionsMeta.ORGANIZATION_IDS);
        cfg.service().addRelationSaveAction(AssetDataPermissionsOOrgServiceImpl.class,AssetDataPermissionsMeta.OWN_ORGANIZATION_IDS);

        cfg.view().formWindow().bottomSpace(20);
        cfg.view().formWindow().width(Config.baseFormWidth);
        cfg.view().form().addGroup(null,
                new Object[] {
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.NAME,
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.CODE


                }, new Object[] {
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.STATUS,
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.PRIORITY,
                },
                new Object[] {
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.OWNER_CODE,
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.ROLE_CODE
                }
        );
        cfg.view().form().addGroup(null,
                new Object[] {
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.NOTES,
                }
        );

        cfg.view().form().addGroup("??????????????????",
                new Object[] {
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.CATALOG_AUTHORITY_ENABLE,
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.CATALOG_CASCADE_ENABLE,
                        AssetDataPermissionsMeta.CATEGORY_IDS,
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.CATALOG_NOTES,
                }
        );


        cfg.view().form().addGroup("????????????????????????",
                new Object[] {
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.OWN_ORG_AUTHORITY_ENABLE,
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.OWN_ORG_LOCAL_ENABLE,
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.OWN_ORG_CASCADE_ENABLE,
                        AssetDataPermissionsMeta.OWN_ORGANIZATION_IDS,
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.OWN_ORG_NOTES
                }

        );

        cfg.view().form().addGroup("????????????????????????",
                new Object[] {
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.ORG_AUTHORITY_ENABLE,
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.ORG_LOCAL_ENABLE,
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.ORG_CASCADE_ENABLE,
                        AssetDataPermissionsMeta.ORGANIZATION_IDS,
                        EAMTables.EAM_ASSET_DATA_PERMISSIONS.ORG_NOTES,
                }

        );



        cfg.view().form().addGroup("??????????????????",
                 new Object[] {
                         EAMTables.EAM_ASSET_DATA_PERMISSIONS.POSITION_AUTHORITY_ENABLE,
                         AssetDataPermissionsMeta.POSITION_IDS,
                         AssetDataPermissionsMeta.POSITION_NOTES,
                 }
        );



        //????????????????????????
        cfg.overrides()
                .setServiceIntfAnfImpl(WriteMode.COVER_EXISTS_FILE) //???????????????
                .setControllerAndAgent(WriteMode.COVER_EXISTS_FILE) //Rest
                .setPageController(WriteMode.IGNORE) //???????????????
                .setFormPage(WriteMode.WRITE_TEMP_FILE) //??????HTML???
                .setListPage(WriteMode.WRITE_TEMP_FILE)//??????HTML???
                .setExtendJsFile(WriteMode.IGNORE); //??????HTML???
        cfg.buildAll();
    }

    public static void main(String[] args) throws Exception {
        EamAssetDataPermGtr g=new EamAssetDataPermGtr();
        //????????????
         g.generateCode();

       // g.generateMenu(AssetDataPermissionsServiceProxy.class, AssetDataPermissionsPageController.class);
        //????????????
        //  g.removeByBatchId("");
       // g.generateMenu(PositionServiceProxy.class, PositionPageController.class);
    }
}
