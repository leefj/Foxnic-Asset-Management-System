package com.dt.platform.eam.controller;


import java.util.*;
import org.github.foxnic.web.framework.web.SuperController;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.dao.entity.ReferCause;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.github.foxnic.api.swagger.InDoc;
import org.github.foxnic.web.framework.sentinel.SentinelExceptionUtil;
import com.github.foxnic.api.swagger.ApiParamSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;


import com.dt.platform.proxy.eam.AssetMaintenanceRecordUServiceProxy;
import com.dt.platform.domain.eam.meta.AssetMaintenanceRecordUVOMeta;
import com.dt.platform.domain.eam.AssetMaintenanceRecordU;
import com.dt.platform.domain.eam.AssetMaintenanceRecordUVO;
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
import com.dt.platform.domain.eam.meta.AssetMaintenanceRecordUMeta;
import com.dt.platform.domain.eam.Maintainer;
import org.github.foxnic.web.domain.system.DictItem;
import com.dt.platform.domain.eam.AssetMaintenanceUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetMaintenanceRecordUService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * ?????????????????????????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-10-29 15:12:32
*/

@InDoc
@Api(tags = "??????????????????")
@RestController("EamAssetMaintenanceRecordUController")
public class AssetMaintenanceRecordUController extends SuperController {

	@Autowired
	private IAssetMaintenanceRecordUService assetMaintenanceRecordUService;


	/**
	 * ????????????????????????
	*/
	@ApiOperation(value = "????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_UPDATE_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTAINER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTAINER_ID , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTAINER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTAINER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTAINER_NAME , value = "??????????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTAINER_NAME , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTAINER_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTAINER_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_STATUS , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_METHOD , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.CONTACT_INFORMATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_CONTACT_INFORMATION , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_CONTACT_INFORMATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_CONTACT_INFORMATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.SUGGEST_MAINTENANCE_METHOD , value = "??????????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_SUGGEST_MAINTENANCE_METHOD , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_SUGGEST_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_SUGGEST_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.CONTACTS , value = "?????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_CONTACTS , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_CONTACTS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_CONTACTS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.DIRECTOR , value = "?????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_DIRECTOR , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_DIRECTOR , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_DIRECTOR , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_START_DATE , value = "??????????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_START_DATE , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_START_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_START_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_END_DATE , value = "??????????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_END_DATE , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_END_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_END_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_NOTES , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_NOTES , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_NOTES , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_NOTES , value = "????????????" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="?????? , maillank@qq.com")
	@SentinelResource(value = AssetMaintenanceRecordUServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetMaintenanceRecordUServiceProxy.INSERT)
	public Result insert(AssetMaintenanceRecordUVO assetMaintenanceRecordUVO) {
		Result result=assetMaintenanceRecordUService.insert(assetMaintenanceRecordUVO,false);
		return result;
	}



	/**
	 * ????????????????????????
	*/
	@ApiOperation(value = "????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2 , author="?????? , maillank@qq.com")
	@SentinelResource(value = AssetMaintenanceRecordUServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetMaintenanceRecordUServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("??????id???");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// ????????????
		ReferCause cause =  assetMaintenanceRecordUService.hasRefers(id);
		// ????????????????????????
		this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		Result result=assetMaintenanceRecordUService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * ?????????????????????????????? <br>
	 * ???????????????????????????????????????
	*/
	@ApiOperation(value = "??????????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.IDS , value = "????????????" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="?????? , maillank@qq.com") 
	@SentinelResource(value = AssetMaintenanceRecordUServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetMaintenanceRecordUServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// ????????????
		this.validator().asserts(ids).require("??????ids??????");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// ????????????
		Map<String, ReferCause> causeMap = assetMaintenanceRecordUService.hasRefers(ids);
		// ?????????????????????ID???
		List<String> canDeleteIds = new ArrayList<>();
		for (Map.Entry<String, ReferCause> e : causeMap.entrySet()) {
			if (!e.getValue().hasRefer()) {
				canDeleteIds.add(e.getKey());
			}
		}

		// ????????????
		if (canDeleteIds.isEmpty()) {
			// ?????????????????????????????????
			return ErrorDesc.failure().message("????????????????????????????????????").data(0)
				.addErrors(CollectorUtil.collectArray(CollectorUtil.filter(causeMap.values(),(e)->{return e.hasRefer();}),ReferCause::message,String.class))
				.messageLevel4Confirm();
		} else if (canDeleteIds.size() == ids.size()) {
			// ????????????????????????
			Result result=assetMaintenanceRecordUService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// ???????????????????????????
			Result result=assetMaintenanceRecordUService.deleteByIdsLogical(canDeleteIds);
			if (result.failure()) {
				return result;
			} else {
				return ErrorDesc.success().message("????????? " + canDeleteIds.size() + " ??????????????? " + (ids.size() - canDeleteIds.size()) + " ?????????????????????").data(canDeleteIds.size())
					.addErrors(CollectorUtil.collectArray(CollectorUtil.filter(causeMap.values(),(e)->{return e.hasRefer();}),ReferCause::message,String.class))
					.messageLevel4Confirm();
			}
		} else {
			// ?????????????????????????????????
			return ErrorDesc.success().message("?????????????????????");
		}
	}

	/**
	 * ????????????????????????
	*/
	@ApiOperation(value = "????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_UPDATE_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTAINER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTAINER_ID , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTAINER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTAINER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTAINER_NAME , value = "??????????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTAINER_NAME , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTAINER_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTAINER_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_STATUS , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_METHOD , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.CONTACT_INFORMATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_CONTACT_INFORMATION , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_CONTACT_INFORMATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_CONTACT_INFORMATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.SUGGEST_MAINTENANCE_METHOD , value = "??????????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_SUGGEST_MAINTENANCE_METHOD , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_SUGGEST_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_SUGGEST_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.CONTACTS , value = "?????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_CONTACTS , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_CONTACTS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_CONTACTS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.DIRECTOR , value = "?????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_DIRECTOR , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_DIRECTOR , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_DIRECTOR , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_START_DATE , value = "??????????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_START_DATE , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_START_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_START_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_END_DATE , value = "??????????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_END_DATE , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_END_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_END_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_NOTES , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_NOTES , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_NOTES , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_NOTES , value = "????????????" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="?????? , maillank@qq.com" ,  ignoreParameters = { AssetMaintenanceRecordUVOMeta.PAGE_INDEX , AssetMaintenanceRecordUVOMeta.PAGE_SIZE , AssetMaintenanceRecordUVOMeta.SEARCH_FIELD , AssetMaintenanceRecordUVOMeta.FUZZY_FIELD , AssetMaintenanceRecordUVOMeta.SEARCH_VALUE , AssetMaintenanceRecordUVOMeta.DIRTY_FIELDS , AssetMaintenanceRecordUVOMeta.SORT_FIELD , AssetMaintenanceRecordUVOMeta.SORT_TYPE , AssetMaintenanceRecordUVOMeta.IDS } )
	@SentinelResource(value = AssetMaintenanceRecordUServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetMaintenanceRecordUServiceProxy.UPDATE)
	public Result update(AssetMaintenanceRecordUVO assetMaintenanceRecordUVO) {
		Result result=assetMaintenanceRecordUService.update(assetMaintenanceRecordUVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * ????????????????????????
	*/
	@ApiOperation(value = "????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_UPDATE_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTAINER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTAINER_ID , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTAINER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTAINER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTAINER_NAME , value = "??????????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTAINER_NAME , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTAINER_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTAINER_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_STATUS , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_METHOD , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.CONTACT_INFORMATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_CONTACT_INFORMATION , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_CONTACT_INFORMATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_CONTACT_INFORMATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.SUGGEST_MAINTENANCE_METHOD , value = "??????????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_SUGGEST_MAINTENANCE_METHOD , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_SUGGEST_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_SUGGEST_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.CONTACTS , value = "?????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_CONTACTS , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_CONTACTS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_CONTACTS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.DIRECTOR , value = "?????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_DIRECTOR , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_DIRECTOR , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_DIRECTOR , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_START_DATE , value = "??????????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_START_DATE , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_START_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_START_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_END_DATE , value = "??????????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_END_DATE , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_END_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_END_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_NOTES , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_NOTES , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_NOTES , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_NOTES , value = "????????????" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { AssetMaintenanceRecordUVOMeta.PAGE_INDEX , AssetMaintenanceRecordUVOMeta.PAGE_SIZE , AssetMaintenanceRecordUVOMeta.SEARCH_FIELD , AssetMaintenanceRecordUVOMeta.FUZZY_FIELD , AssetMaintenanceRecordUVOMeta.SEARCH_VALUE , AssetMaintenanceRecordUVOMeta.DIRTY_FIELDS , AssetMaintenanceRecordUVOMeta.SORT_FIELD , AssetMaintenanceRecordUVOMeta.SORT_TYPE , AssetMaintenanceRecordUVOMeta.IDS } )
	@SentinelResource(value = AssetMaintenanceRecordUServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetMaintenanceRecordUServiceProxy.SAVE)
	public Result save(AssetMaintenanceRecordUVO assetMaintenanceRecordUVO) {
		Result result=assetMaintenanceRecordUService.save(assetMaintenanceRecordUVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * ????????????????????????
	*/
	@ApiOperation(value = "????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="?????? , maillank@qq.com")
	@SentinelResource(value = AssetMaintenanceRecordUServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetMaintenanceRecordUServiceProxy.GET_BY_ID)
	public Result<AssetMaintenanceRecordU> getById(String id) {
		Result<AssetMaintenanceRecordU> result=new Result<>();
		AssetMaintenanceRecordU assetMaintenanceRecordU=assetMaintenanceRecordUService.getById(id);
		// join ???????????????
		assetMaintenanceRecordUService.dao().fill(assetMaintenanceRecordU)
			.with(AssetMaintenanceRecordUMeta.MAINTNAINER)
			.with(AssetMaintenanceRecordUMeta.ASSET_MAINTENANCE_STATUS)
			.with(AssetMaintenanceRecordUMeta.MAINTENANCE_METHOD_DATA)
			.with(AssetMaintenanceRecordUMeta.SUGGEST_MAINTENANCE_METHOD_DATA)
			.execute();
		result.success(true).data(assetMaintenanceRecordU);
		return result;
	}


	/**
	 * ?????????????????????????????? <br>
	 * ???????????????????????????????????????
	*/
		@ApiOperation(value = "??????????????????????????????")
		@ApiImplicitParams({
				@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.IDS , value = "????????????" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="?????? , maillank@qq.com") 
		@SentinelResource(value = AssetMaintenanceRecordUServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetMaintenanceRecordUServiceProxy.GET_BY_IDS)
	public Result<List<AssetMaintenanceRecordU>> getByIds(List<String> ids) {
		Result<List<AssetMaintenanceRecordU>> result=new Result<>();
		List<AssetMaintenanceRecordU> list=assetMaintenanceRecordUService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * ????????????????????????
	*/
	@ApiOperation(value = "????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_UPDATE_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTAINER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTAINER_ID , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTAINER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTAINER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTAINER_NAME , value = "??????????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTAINER_NAME , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTAINER_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTAINER_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_STATUS , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_METHOD , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.CONTACT_INFORMATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_CONTACT_INFORMATION , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_CONTACT_INFORMATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_CONTACT_INFORMATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.SUGGEST_MAINTENANCE_METHOD , value = "??????????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_SUGGEST_MAINTENANCE_METHOD , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_SUGGEST_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_SUGGEST_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.CONTACTS , value = "?????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_CONTACTS , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_CONTACTS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_CONTACTS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.DIRECTOR , value = "?????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_DIRECTOR , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_DIRECTOR , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_DIRECTOR , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_START_DATE , value = "??????????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_START_DATE , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_START_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_START_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_END_DATE , value = "??????????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_END_DATE , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_END_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_END_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_NOTES , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_NOTES , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_NOTES , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_NOTES , value = "????????????" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="?????? , maillank@qq.com" ,  ignoreParameters = { AssetMaintenanceRecordUVOMeta.PAGE_INDEX , AssetMaintenanceRecordUVOMeta.PAGE_SIZE } )
	@SentinelResource(value = AssetMaintenanceRecordUServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetMaintenanceRecordUServiceProxy.QUERY_LIST)
	public Result<List<AssetMaintenanceRecordU>> queryList(AssetMaintenanceRecordUVO sample) {
		Result<List<AssetMaintenanceRecordU>> result=new Result<>();
		List<AssetMaintenanceRecordU> list=assetMaintenanceRecordUService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * ??????????????????????????????
	*/
	@ApiOperation(value = "??????????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_UPDATE_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTAINER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTAINER_ID , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTAINER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTAINER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTAINER_NAME , value = "??????????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTAINER_NAME , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTAINER_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTAINER_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_STATUS , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_METHOD , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.CONTACT_INFORMATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_CONTACT_INFORMATION , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_CONTACT_INFORMATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_CONTACT_INFORMATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.SUGGEST_MAINTENANCE_METHOD , value = "??????????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_SUGGEST_MAINTENANCE_METHOD , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_SUGGEST_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_SUGGEST_MAINTENANCE_METHOD , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.CONTACTS , value = "?????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_CONTACTS , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_CONTACTS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_CONTACTS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.DIRECTOR , value = "?????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_DIRECTOR , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_DIRECTOR , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_DIRECTOR , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_START_DATE , value = "??????????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_START_DATE , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_START_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_START_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_END_DATE , value = "??????????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_END_DATE , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_END_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_END_DATE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.MAINTENANCE_NOTES , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.S_MAINTENANCE_NOTES , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_MAINTENANCE_NOTES , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetMaintenanceRecordUVOMeta.U_S_MAINTENANCE_NOTES , value = "????????????" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="?????? , maillank@qq.com")
	@SentinelResource(value = AssetMaintenanceRecordUServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetMaintenanceRecordUServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<AssetMaintenanceRecordU>> queryPagedList(AssetMaintenanceRecordUVO sample) {
		Result<PagedList<AssetMaintenanceRecordU>> result=new Result<>();
		PagedList<AssetMaintenanceRecordU> list=assetMaintenanceRecordUService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		// join ???????????????
		assetMaintenanceRecordUService.dao().fill(list)
			.with(AssetMaintenanceRecordUMeta.MAINTNAINER)
			.with(AssetMaintenanceRecordUMeta.ASSET_MAINTENANCE_STATUS)
			.with(AssetMaintenanceRecordUMeta.MAINTENANCE_METHOD_DATA)
			.with(AssetMaintenanceRecordUMeta.SUGGEST_MAINTENANCE_METHOD_DATA)
			.execute();
		result.success(true).data(list);
		return result;
	}






}