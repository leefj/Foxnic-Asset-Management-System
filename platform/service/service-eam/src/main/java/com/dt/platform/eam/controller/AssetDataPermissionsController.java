package com.dt.platform.eam.controller;

import java.util.*;
import org.github.foxnic.web.framework.web.SuperController;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.dao.entity.ReferCause;
import com.github.foxnic.api.swagger.InDoc;
import org.github.foxnic.web.framework.sentinel.SentinelExceptionUtil;
import com.github.foxnic.api.swagger.ApiParamSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;


import com.dt.platform.proxy.eam.AssetDataPermissionsServiceProxy;
import com.dt.platform.domain.eam.meta.AssetDataPermissionsVOMeta;
import com.dt.platform.domain.eam.AssetDataPermissions;
import com.dt.platform.domain.eam.AssetDataPermissionsVO;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.SaveMode;
import com.github.foxnic.dao.excel.ExcelWriter;
import com.github.foxnic.springboot.web.DownloadUtil;
import com.github.foxnic.dao.data.PagedList;
import java.util.Date;
import java.sql.Timestamp;
import com.github.foxnic.api.error.ErrorDesc;
import com.github.foxnic.commons.io.StreamUtil;
import java.util.Map;
import com.github.foxnic.dao.excel.ValidateResult;
import java.io.InputStream;
import com.dt.platform.domain.eam.meta.AssetDataPermissionsMeta;
import org.github.foxnic.web.domain.system.BusiRole;
import org.github.foxnic.web.domain.pcm.Catalog;
import org.github.foxnic.web.domain.hrm.Organization;
import com.dt.platform.domain.eam.Position;
import com.dt.platform.domain.eam.Warehouse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetDataPermissionsService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 资产数据权限接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-04-26 07:51:05
*/

@InDoc
@Api(tags = "资产数据权限")
@RestController("EamAssetDataPermissionsController")
public class AssetDataPermissionsController extends SuperController {

	@Autowired
	private IAssetDataPermissionsService assetDataPermissionsService;

	/**
	 * 添加资产数据权限
	*/
	@ApiOperation(value = "添加资产数据权限")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "523894324979568640"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "资产全局数据角色_管理员"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.STATUS , value = "权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWNER_CODE , value = "归属" , required = false , dataTypeClass=String.class , example = "asset"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CODE , value = "权限编码" , required = false , dataTypeClass=String.class , example = "data_perm_1"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ROLE_CODE , value = "业务角色" , required = false , dataTypeClass=String.class , example = "eam_data_perm_default_1"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_AUTHORITY_ENABLE , value = "所属权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_LOCAL_ENABLE , value = "所在所属状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_CASCADE_ENABLE , value = "所属联动状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_AUTHORITY_ENABLE , value = "组织权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_LOCAL_ENABLE , value = "所在组织状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_CASCADE_ENABLE , value = "组织联动状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_AUTHORITY_ENABLE , value = "分类权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_CASCADE_ENABLE , value = "分类级联状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_AUTHORITY_ENABLE , value = "位置权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.WAREHOUSE_AUTHORITY_ENABLE , value = "仓库" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.WAREHOUSE_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.PRIORITY , value = "优先级" , required = false , dataTypeClass=Integer.class , example = "100"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class , example = "备注"),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AssetDataPermissionsServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDataPermissionsServiceProxy.INSERT)
	public Result insert(AssetDataPermissionsVO assetDataPermissionsVO) {
		
		Result result=assetDataPermissionsService.insert(assetDataPermissionsVO,false);
		return result;
	}



	/**
	 * 删除资产数据权限
	*/
	@ApiOperation(value = "删除资产数据权限")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "523894324979568640")
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AssetDataPermissionsServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDataPermissionsServiceProxy.DELETE)
	public Result deleteById(String id) {
		
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  assetDataPermissionsService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=assetDataPermissionsService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除资产数据权限 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除资产数据权限")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = AssetDataPermissionsServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDataPermissionsServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {
		
		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = assetDataPermissionsService.hasRefers(ids);
		// 收集可以删除的ID值
		List<String> canDeleteIds = new ArrayList<>();
		for (Map.Entry<String, ReferCause> e : causeMap.entrySet()) {
			if (!e.getValue().hasRefer()) {
				canDeleteIds.add(e.getKey());
			}
		}

		// 执行删除
		if (canDeleteIds.isEmpty()) {
			// 如果没有一行可以被删除
			return ErrorDesc.failure().message("无法删除您选中的数据行：").data(0)
				.addErrors(CollectorUtil.collectArray(CollectorUtil.filter(causeMap.values(),(e)->{return e.hasRefer();}),ReferCause::message,String.class))
				.messageLevel4Confirm();
		} else if (canDeleteIds.size() == ids.size()) {
			// 如果全部可以删除
			Result result=assetDataPermissionsService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=assetDataPermissionsService.deleteByIdsLogical(canDeleteIds);
			if (result.failure()) {
				return result;
			} else {
				return ErrorDesc.success().message("已删除 " + canDeleteIds.size() + " 行，但另有 " + (ids.size() - canDeleteIds.size()) + " 行数据无法删除").data(canDeleteIds.size())
				.addErrors(CollectorUtil.collectArray(CollectorUtil.filter(causeMap.values(),(e)->{return e.hasRefer();}),ReferCause::message,String.class))
				.messageLevel4Confirm();
			}
		} else {
			// 理论上，这个分支不存在
			return ErrorDesc.success().message("数据删除未处理");
		}
	}

	/**
	 * 更新资产数据权限
	*/
	@ApiOperation(value = "更新资产数据权限")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "523894324979568640"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "资产全局数据角色_管理员"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.STATUS , value = "权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWNER_CODE , value = "归属" , required = false , dataTypeClass=String.class , example = "asset"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CODE , value = "权限编码" , required = false , dataTypeClass=String.class , example = "data_perm_1"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ROLE_CODE , value = "业务角色" , required = false , dataTypeClass=String.class , example = "eam_data_perm_default_1"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_AUTHORITY_ENABLE , value = "所属权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_LOCAL_ENABLE , value = "所在所属状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_CASCADE_ENABLE , value = "所属联动状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_AUTHORITY_ENABLE , value = "组织权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_LOCAL_ENABLE , value = "所在组织状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_CASCADE_ENABLE , value = "组织联动状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_AUTHORITY_ENABLE , value = "分类权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_CASCADE_ENABLE , value = "分类级联状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_AUTHORITY_ENABLE , value = "位置权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.WAREHOUSE_AUTHORITY_ENABLE , value = "仓库" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.WAREHOUSE_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.PRIORITY , value = "优先级" , required = false , dataTypeClass=Integer.class , example = "100"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class , example = "备注"),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { AssetDataPermissionsVOMeta.PAGE_INDEX , AssetDataPermissionsVOMeta.PAGE_SIZE , AssetDataPermissionsVOMeta.SEARCH_FIELD , AssetDataPermissionsVOMeta.FUZZY_FIELD , AssetDataPermissionsVOMeta.SEARCH_VALUE , AssetDataPermissionsVOMeta.DIRTY_FIELDS , AssetDataPermissionsVOMeta.SORT_FIELD , AssetDataPermissionsVOMeta.SORT_TYPE , AssetDataPermissionsVOMeta.DATA_ORIGIN , AssetDataPermissionsVOMeta.QUERY_LOGIC , AssetDataPermissionsVOMeta.REQUEST_ACTION , AssetDataPermissionsVOMeta.IDS } )
	@SentinelResource(value = AssetDataPermissionsServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDataPermissionsServiceProxy.UPDATE)
	public Result update(AssetDataPermissionsVO assetDataPermissionsVO) {
		
		Result result=assetDataPermissionsService.update(assetDataPermissionsVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存资产数据权限
	*/
	@ApiOperation(value = "保存资产数据权限")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "523894324979568640"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "资产全局数据角色_管理员"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.STATUS , value = "权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWNER_CODE , value = "归属" , required = false , dataTypeClass=String.class , example = "asset"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CODE , value = "权限编码" , required = false , dataTypeClass=String.class , example = "data_perm_1"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ROLE_CODE , value = "业务角色" , required = false , dataTypeClass=String.class , example = "eam_data_perm_default_1"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_AUTHORITY_ENABLE , value = "所属权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_LOCAL_ENABLE , value = "所在所属状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_CASCADE_ENABLE , value = "所属联动状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_AUTHORITY_ENABLE , value = "组织权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_LOCAL_ENABLE , value = "所在组织状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_CASCADE_ENABLE , value = "组织联动状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_AUTHORITY_ENABLE , value = "分类权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_CASCADE_ENABLE , value = "分类级联状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_AUTHORITY_ENABLE , value = "位置权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.WAREHOUSE_AUTHORITY_ENABLE , value = "仓库" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.WAREHOUSE_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.PRIORITY , value = "优先级" , required = false , dataTypeClass=Integer.class , example = "100"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class , example = "备注"),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { AssetDataPermissionsVOMeta.PAGE_INDEX , AssetDataPermissionsVOMeta.PAGE_SIZE , AssetDataPermissionsVOMeta.SEARCH_FIELD , AssetDataPermissionsVOMeta.FUZZY_FIELD , AssetDataPermissionsVOMeta.SEARCH_VALUE , AssetDataPermissionsVOMeta.DIRTY_FIELDS , AssetDataPermissionsVOMeta.SORT_FIELD , AssetDataPermissionsVOMeta.SORT_TYPE , AssetDataPermissionsVOMeta.DATA_ORIGIN , AssetDataPermissionsVOMeta.QUERY_LOGIC , AssetDataPermissionsVOMeta.REQUEST_ACTION , AssetDataPermissionsVOMeta.IDS } )
	@SentinelResource(value = AssetDataPermissionsServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDataPermissionsServiceProxy.SAVE)
	public Result save(AssetDataPermissionsVO assetDataPermissionsVO) {
		
		Result result=assetDataPermissionsService.save(assetDataPermissionsVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取资产数据权限
	*/
	@ApiOperation(value = "获取资产数据权限")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AssetDataPermissionsServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDataPermissionsServiceProxy.GET_BY_ID)
	public Result<AssetDataPermissions> getById(String id) {
		
		Result<AssetDataPermissions> result=new Result<>();
		AssetDataPermissions assetDataPermissions=assetDataPermissionsService.getById(id);
		// join 关联的对象
		assetDataPermissionsService.dao().fill(assetDataPermissions)
			.with("organization")
			.with("ownOrganization")
			.with(AssetDataPermissionsMeta.BUSI_ROLE)
			.with(AssetDataPermissionsMeta.POSITION)
			.with(AssetDataPermissionsMeta.WAREHOUSE)
			.with(AssetDataPermissionsMeta.CATEGORY)
			.execute();
		result.success(true).data(assetDataPermissions);
		return result;
	}


	/**
	 * 批量获取资产数据权限 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取资产数据权限")
		@ApiImplicitParams({
				@ApiImplicitParam(name = AssetDataPermissionsVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = AssetDataPermissionsServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDataPermissionsServiceProxy.GET_BY_IDS)
	public Result<List<AssetDataPermissions>> getByIds(List<String> ids) {
		
		Result<List<AssetDataPermissions>> result=new Result<>();
		List<AssetDataPermissions> list=assetDataPermissionsService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询资产数据权限
	*/
	@ApiOperation(value = "查询资产数据权限")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "523894324979568640"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "资产全局数据角色_管理员"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.STATUS , value = "权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWNER_CODE , value = "归属" , required = false , dataTypeClass=String.class , example = "asset"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CODE , value = "权限编码" , required = false , dataTypeClass=String.class , example = "data_perm_1"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ROLE_CODE , value = "业务角色" , required = false , dataTypeClass=String.class , example = "eam_data_perm_default_1"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_AUTHORITY_ENABLE , value = "所属权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_LOCAL_ENABLE , value = "所在所属状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_CASCADE_ENABLE , value = "所属联动状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_AUTHORITY_ENABLE , value = "组织权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_LOCAL_ENABLE , value = "所在组织状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_CASCADE_ENABLE , value = "组织联动状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_AUTHORITY_ENABLE , value = "分类权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_CASCADE_ENABLE , value = "分类级联状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_AUTHORITY_ENABLE , value = "位置权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.WAREHOUSE_AUTHORITY_ENABLE , value = "仓库" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.WAREHOUSE_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.PRIORITY , value = "优先级" , required = false , dataTypeClass=Integer.class , example = "100"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class , example = "备注"),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { AssetDataPermissionsVOMeta.PAGE_INDEX , AssetDataPermissionsVOMeta.PAGE_SIZE } )
	@SentinelResource(value = AssetDataPermissionsServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDataPermissionsServiceProxy.QUERY_LIST)
	public Result<List<AssetDataPermissions>> queryList(AssetDataPermissionsVO sample) {
		
		Result<List<AssetDataPermissions>> result=new Result<>();
		List<AssetDataPermissions> list=assetDataPermissionsService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询资产数据权限
	*/
	@ApiOperation(value = "分页查询资产数据权限")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "523894324979568640"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "资产全局数据角色_管理员"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.STATUS , value = "权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWNER_CODE , value = "归属" , required = false , dataTypeClass=String.class , example = "asset"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CODE , value = "权限编码" , required = false , dataTypeClass=String.class , example = "data_perm_1"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ROLE_CODE , value = "业务角色" , required = false , dataTypeClass=String.class , example = "eam_data_perm_default_1"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_AUTHORITY_ENABLE , value = "所属权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_LOCAL_ENABLE , value = "所在所属状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_CASCADE_ENABLE , value = "所属联动状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_AUTHORITY_ENABLE , value = "组织权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_LOCAL_ENABLE , value = "所在组织状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_CASCADE_ENABLE , value = "组织联动状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_AUTHORITY_ENABLE , value = "分类权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_CASCADE_ENABLE , value = "分类级联状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_AUTHORITY_ENABLE , value = "位置权限状态" , required = false , dataTypeClass=String.class , example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.WAREHOUSE_AUTHORITY_ENABLE , value = "仓库" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.WAREHOUSE_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.PRIORITY , value = "优先级" , required = false , dataTypeClass=Integer.class , example = "100"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class , example = "备注"),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AssetDataPermissionsServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDataPermissionsServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<AssetDataPermissions>> queryPagedList(AssetDataPermissionsVO sample) {
		
		Result<PagedList<AssetDataPermissions>> result=new Result<>();
		PagedList<AssetDataPermissions> list=assetDataPermissionsService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		// join 关联的对象
		assetDataPermissionsService.dao().fill(list)
			.with("organization")
			.with("ownOrganization")
			.with(AssetDataPermissionsMeta.BUSI_ROLE)
			.with(AssetDataPermissionsMeta.POSITION)
			.with(AssetDataPermissionsMeta.WAREHOUSE)
			.with(AssetDataPermissionsMeta.CATEGORY)
			.execute();
		result.success(true).data(list);
		return result;
	}





}