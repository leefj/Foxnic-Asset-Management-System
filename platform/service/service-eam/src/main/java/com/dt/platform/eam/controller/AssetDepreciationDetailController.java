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


import com.dt.platform.proxy.eam.AssetDepreciationDetailServiceProxy;
import com.dt.platform.domain.eam.meta.AssetDepreciationDetailVOMeta;
import com.dt.platform.domain.eam.AssetDepreciationDetail;
import com.dt.platform.domain.eam.AssetDepreciationDetailVO;
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
import com.dt.platform.domain.eam.meta.AssetDepreciationDetailMeta;
import java.math.BigDecimal;
import com.dt.platform.domain.eam.Asset;
import com.dt.platform.domain.eam.AssetDepreciation;
import com.dt.platform.domain.eam.AssetDepreciationCalRule;
import com.dt.platform.domain.eam.AssetDepreciationOper;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.hrm.Organization;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetDepreciationDetailService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * ???????????????????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-12-10 14:40:49
*/

@InDoc
@Api(tags = "????????????")
@RestController("EamAssetDepreciationDetailController")
public class AssetDepreciationDetailController extends SuperController {

	@Autowired
	private IAssetDepreciationDetailService assetDepreciationDetailService;


	/**
	 * ??????????????????
	*/
	@ApiOperation(value = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "648956390479495168"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_ID , value = "????????????" , required = false , dataTypeClass=String.class , example = "647736203386290176"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.OPER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACTION_CODE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION_METHOD , value = "??????????????????" , required = false , dataTypeClass=String.class , example = "purchase_next_month"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_METHOD , value = "????????????" , required = false , dataTypeClass=String.class , example = "average_age"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.BUSINESS_DATE , value = "????????????" , required = false , dataTypeClass=Date.class , example = "2022-11-25 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT , value = "????????????" , required = false , dataTypeClass=String.class , example = "wait_calculate"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_DETAIL , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ID , value = "??????" , required = false , dataTypeClass=String.class , example = "648874943362105344"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "????????????"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CODE , value = "????????????" , required = false , dataTypeClass=String.class , example = "AS2022112609345"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "001"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_MODEL , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_STATUS_NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "idle"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_DATE , value = "????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_REGISTER_DATE , value = "????????????" , required = false , dataTypeClass=Date.class , example = "2022-11-25 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ORIGINAL_UNIT_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class , example = "7682.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_UNIT_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAV_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_TAX_AMOUNT_RATE , value = "??????" , required = false , dataTypeClass=BigDecimal.class , example = "2.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_SERVICE_LIFE , value = "???????????????(??????)" , required = false , dataTypeClass=BigDecimal.class , example = "12.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_SERVICE_LIFE , value = "???????????????(??????)" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_RATE , value = "???????????????" , required = false , dataTypeClass=BigDecimal.class , example = "5.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_ORIGINAL_PRICE , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_DEPRECIATION_AMOUNT , value = "(??????)??????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_NAV_AMOUNT , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_RECOVERABLE_AMOUNT , value = "(??????)?????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_USED_SERVICE_LIFE , value = "???????????????" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_ORIGINAL_PRICE_INCREASE , value = "(????????????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_DEPRECIATION_AMOUNT , value = "(????????????)???????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_YEAR_DEPRECIATION_AMOUNT , value = "(????????????)?????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_ORIGINAL_PRICE , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_DEPRECIATION_AMOUNT , value = "(??????)??????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_NAV_AMOUNT , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_RECOVERABLE_AMOUNT , value = "(??????)?????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACCOUNTING_SERVICE_LIFE , value = "???????????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_ID , value = "?????????ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_NAME , value = "?????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_ID , value = "??????ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_KEY , value = "????????????KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_KEY , value = "????????????KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.CUSTOMER_INFO , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_SOURCE , value = "?????????" , required = false , dataTypeClass=String.class , example = "648956390253002752"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_TARGET , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_ID , value = "??????????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_TIME , value = "??????????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LABEL , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_STR , value = "???????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_FLOAT , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="?????? , maillank@qq.com")
	@SentinelResource(value = AssetDepreciationDetailServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDepreciationDetailServiceProxy.INSERT)
	public Result insert(AssetDepreciationDetailVO assetDepreciationDetailVO) {
		Result result=assetDepreciationDetailService.insert(assetDepreciationDetailVO,false);
		return result;
	}



	/**
	 * ??????????????????
	*/
	@ApiOperation(value = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "648956390479495168")
	})
	@ApiOperationSupport(order=2 , author="?????? , maillank@qq.com")
	@SentinelResource(value = AssetDepreciationDetailServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDepreciationDetailServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("??????id???");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// ????????????
		ReferCause cause =  assetDepreciationDetailService.hasRefers(id);
		// ????????????????????????
		this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=assetDepreciationDetailService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * ???????????????????????? <br>
	 * ???????????????????????????????????????
	*/
	@ApiOperation(value = "????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.IDS , value = "????????????" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="?????? , maillank@qq.com") 
	@SentinelResource(value = AssetDepreciationDetailServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDepreciationDetailServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// ????????????
		this.validator().asserts(ids).require("??????ids??????");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// ????????????
		Map<String, ReferCause> causeMap = assetDepreciationDetailService.hasRefers(ids);
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
			Result result=assetDepreciationDetailService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// ???????????????????????????
			Result result=assetDepreciationDetailService.deleteByIdsLogical(canDeleteIds);
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
	 * ??????????????????
	*/
	@ApiOperation(value = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "648956390479495168"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_ID , value = "????????????" , required = false , dataTypeClass=String.class , example = "647736203386290176"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.OPER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACTION_CODE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION_METHOD , value = "??????????????????" , required = false , dataTypeClass=String.class , example = "purchase_next_month"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_METHOD , value = "????????????" , required = false , dataTypeClass=String.class , example = "average_age"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.BUSINESS_DATE , value = "????????????" , required = false , dataTypeClass=Date.class , example = "2022-11-25 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT , value = "????????????" , required = false , dataTypeClass=String.class , example = "wait_calculate"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_DETAIL , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ID , value = "??????" , required = false , dataTypeClass=String.class , example = "648874943362105344"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "????????????"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CODE , value = "????????????" , required = false , dataTypeClass=String.class , example = "AS2022112609345"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "001"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_MODEL , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_STATUS_NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "idle"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_DATE , value = "????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_REGISTER_DATE , value = "????????????" , required = false , dataTypeClass=Date.class , example = "2022-11-25 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ORIGINAL_UNIT_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class , example = "7682.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_UNIT_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAV_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_TAX_AMOUNT_RATE , value = "??????" , required = false , dataTypeClass=BigDecimal.class , example = "2.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_SERVICE_LIFE , value = "???????????????(??????)" , required = false , dataTypeClass=BigDecimal.class , example = "12.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_SERVICE_LIFE , value = "???????????????(??????)" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_RATE , value = "???????????????" , required = false , dataTypeClass=BigDecimal.class , example = "5.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_ORIGINAL_PRICE , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_DEPRECIATION_AMOUNT , value = "(??????)??????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_NAV_AMOUNT , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_RECOVERABLE_AMOUNT , value = "(??????)?????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_USED_SERVICE_LIFE , value = "???????????????" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_ORIGINAL_PRICE_INCREASE , value = "(????????????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_DEPRECIATION_AMOUNT , value = "(????????????)???????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_YEAR_DEPRECIATION_AMOUNT , value = "(????????????)?????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_ORIGINAL_PRICE , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_DEPRECIATION_AMOUNT , value = "(??????)??????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_NAV_AMOUNT , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_RECOVERABLE_AMOUNT , value = "(??????)?????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACCOUNTING_SERVICE_LIFE , value = "???????????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_ID , value = "?????????ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_NAME , value = "?????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_ID , value = "??????ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_KEY , value = "????????????KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_KEY , value = "????????????KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.CUSTOMER_INFO , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_SOURCE , value = "?????????" , required = false , dataTypeClass=String.class , example = "648956390253002752"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_TARGET , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_ID , value = "??????????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_TIME , value = "??????????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LABEL , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_STR , value = "???????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_FLOAT , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="?????? , maillank@qq.com" ,  ignoreParameters = { AssetDepreciationDetailVOMeta.PAGE_INDEX , AssetDepreciationDetailVOMeta.PAGE_SIZE , AssetDepreciationDetailVOMeta.SEARCH_FIELD , AssetDepreciationDetailVOMeta.FUZZY_FIELD , AssetDepreciationDetailVOMeta.SEARCH_VALUE , AssetDepreciationDetailVOMeta.DIRTY_FIELDS , AssetDepreciationDetailVOMeta.SORT_FIELD , AssetDepreciationDetailVOMeta.SORT_TYPE , AssetDepreciationDetailVOMeta.DATA_ORIGIN , AssetDepreciationDetailVOMeta.QUERY_LOGIC , AssetDepreciationDetailVOMeta.IDS } )
	@SentinelResource(value = AssetDepreciationDetailServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDepreciationDetailServiceProxy.UPDATE)
	public Result update(AssetDepreciationDetailVO assetDepreciationDetailVO) {
		Result result=assetDepreciationDetailService.update(assetDepreciationDetailVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * ??????????????????
	*/
	@ApiOperation(value = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "648956390479495168"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_ID , value = "????????????" , required = false , dataTypeClass=String.class , example = "647736203386290176"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.OPER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACTION_CODE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION_METHOD , value = "??????????????????" , required = false , dataTypeClass=String.class , example = "purchase_next_month"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_METHOD , value = "????????????" , required = false , dataTypeClass=String.class , example = "average_age"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.BUSINESS_DATE , value = "????????????" , required = false , dataTypeClass=Date.class , example = "2022-11-25 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT , value = "????????????" , required = false , dataTypeClass=String.class , example = "wait_calculate"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_DETAIL , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ID , value = "??????" , required = false , dataTypeClass=String.class , example = "648874943362105344"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "????????????"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CODE , value = "????????????" , required = false , dataTypeClass=String.class , example = "AS2022112609345"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "001"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_MODEL , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_STATUS_NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "idle"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_DATE , value = "????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_REGISTER_DATE , value = "????????????" , required = false , dataTypeClass=Date.class , example = "2022-11-25 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ORIGINAL_UNIT_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class , example = "7682.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_UNIT_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAV_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_TAX_AMOUNT_RATE , value = "??????" , required = false , dataTypeClass=BigDecimal.class , example = "2.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_SERVICE_LIFE , value = "???????????????(??????)" , required = false , dataTypeClass=BigDecimal.class , example = "12.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_SERVICE_LIFE , value = "???????????????(??????)" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_RATE , value = "???????????????" , required = false , dataTypeClass=BigDecimal.class , example = "5.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_ORIGINAL_PRICE , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_DEPRECIATION_AMOUNT , value = "(??????)??????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_NAV_AMOUNT , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_RECOVERABLE_AMOUNT , value = "(??????)?????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_USED_SERVICE_LIFE , value = "???????????????" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_ORIGINAL_PRICE_INCREASE , value = "(????????????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_DEPRECIATION_AMOUNT , value = "(????????????)???????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_YEAR_DEPRECIATION_AMOUNT , value = "(????????????)?????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_ORIGINAL_PRICE , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_DEPRECIATION_AMOUNT , value = "(??????)??????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_NAV_AMOUNT , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_RECOVERABLE_AMOUNT , value = "(??????)?????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACCOUNTING_SERVICE_LIFE , value = "???????????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_ID , value = "?????????ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_NAME , value = "?????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_ID , value = "??????ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_KEY , value = "????????????KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_KEY , value = "????????????KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.CUSTOMER_INFO , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_SOURCE , value = "?????????" , required = false , dataTypeClass=String.class , example = "648956390253002752"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_TARGET , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_ID , value = "??????????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_TIME , value = "??????????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LABEL , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_STR , value = "???????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_FLOAT , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { AssetDepreciationDetailVOMeta.PAGE_INDEX , AssetDepreciationDetailVOMeta.PAGE_SIZE , AssetDepreciationDetailVOMeta.SEARCH_FIELD , AssetDepreciationDetailVOMeta.FUZZY_FIELD , AssetDepreciationDetailVOMeta.SEARCH_VALUE , AssetDepreciationDetailVOMeta.DIRTY_FIELDS , AssetDepreciationDetailVOMeta.SORT_FIELD , AssetDepreciationDetailVOMeta.SORT_TYPE , AssetDepreciationDetailVOMeta.DATA_ORIGIN , AssetDepreciationDetailVOMeta.QUERY_LOGIC , AssetDepreciationDetailVOMeta.IDS } )
	@SentinelResource(value = AssetDepreciationDetailServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDepreciationDetailServiceProxy.SAVE)
	public Result save(AssetDepreciationDetailVO assetDepreciationDetailVO) {
		Result result=assetDepreciationDetailService.save(assetDepreciationDetailVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * ??????????????????
	*/
	@ApiOperation(value = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="?????? , maillank@qq.com")
	@SentinelResource(value = AssetDepreciationDetailServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDepreciationDetailServiceProxy.GET_BY_ID)
	public Result<AssetDepreciationDetail> getById(String id) {
		Result<AssetDepreciationDetail> result=new Result<>();
		AssetDepreciationDetail assetDepreciationDetail=assetDepreciationDetailService.getById(id);
		// join ???????????????
		assetDepreciationDetailService.dao().fill(assetDepreciationDetail)
			.with("useOrganization")
			.with("useUser")
			.with("manager")
			.with(AssetDepreciationDetailMeta.FINANCIAL_OPTION_DICT)
			.with(AssetDepreciationDetailMeta.EXPENSE_ITEM_DICT)
			.with(AssetDepreciationDetailMeta.ASSET_DEPRECIATION)
			.with(AssetDepreciationDetailMeta.ASSET_DEPRECIATION_OPER)
			.execute();
		result.success(true).data(assetDepreciationDetail);
		return result;
	}


	/**
	 * ???????????????????????? <br>
	 * ???????????????????????????????????????
	*/
		@ApiOperation(value = "????????????????????????")
		@ApiImplicitParams({
				@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.IDS , value = "????????????" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="?????? , maillank@qq.com") 
		@SentinelResource(value = AssetDepreciationDetailServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDepreciationDetailServiceProxy.GET_BY_IDS)
	public Result<List<AssetDepreciationDetail>> getByIds(List<String> ids) {
		Result<List<AssetDepreciationDetail>> result=new Result<>();
		List<AssetDepreciationDetail> list=assetDepreciationDetailService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * ??????????????????
	*/
	@ApiOperation(value = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "648956390479495168"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_ID , value = "????????????" , required = false , dataTypeClass=String.class , example = "647736203386290176"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.OPER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACTION_CODE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION_METHOD , value = "??????????????????" , required = false , dataTypeClass=String.class , example = "purchase_next_month"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_METHOD , value = "????????????" , required = false , dataTypeClass=String.class , example = "average_age"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.BUSINESS_DATE , value = "????????????" , required = false , dataTypeClass=Date.class , example = "2022-11-25 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT , value = "????????????" , required = false , dataTypeClass=String.class , example = "wait_calculate"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_DETAIL , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ID , value = "??????" , required = false , dataTypeClass=String.class , example = "648874943362105344"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "????????????"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CODE , value = "????????????" , required = false , dataTypeClass=String.class , example = "AS2022112609345"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "001"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_MODEL , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_STATUS_NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "idle"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_DATE , value = "????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_REGISTER_DATE , value = "????????????" , required = false , dataTypeClass=Date.class , example = "2022-11-25 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ORIGINAL_UNIT_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class , example = "7682.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_UNIT_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAV_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_TAX_AMOUNT_RATE , value = "??????" , required = false , dataTypeClass=BigDecimal.class , example = "2.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_SERVICE_LIFE , value = "???????????????(??????)" , required = false , dataTypeClass=BigDecimal.class , example = "12.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_SERVICE_LIFE , value = "???????????????(??????)" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_RATE , value = "???????????????" , required = false , dataTypeClass=BigDecimal.class , example = "5.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_ORIGINAL_PRICE , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_DEPRECIATION_AMOUNT , value = "(??????)??????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_NAV_AMOUNT , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_RECOVERABLE_AMOUNT , value = "(??????)?????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_USED_SERVICE_LIFE , value = "???????????????" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_ORIGINAL_PRICE_INCREASE , value = "(????????????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_DEPRECIATION_AMOUNT , value = "(????????????)???????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_YEAR_DEPRECIATION_AMOUNT , value = "(????????????)?????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_ORIGINAL_PRICE , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_DEPRECIATION_AMOUNT , value = "(??????)??????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_NAV_AMOUNT , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_RECOVERABLE_AMOUNT , value = "(??????)?????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACCOUNTING_SERVICE_LIFE , value = "???????????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_ID , value = "?????????ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_NAME , value = "?????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_ID , value = "??????ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_KEY , value = "????????????KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_KEY , value = "????????????KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.CUSTOMER_INFO , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_SOURCE , value = "?????????" , required = false , dataTypeClass=String.class , example = "648956390253002752"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_TARGET , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_ID , value = "??????????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_TIME , value = "??????????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LABEL , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_STR , value = "???????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_FLOAT , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
	})
	@ApiOperationSupport(order=5 , author="?????? , maillank@qq.com" ,  ignoreParameters = { AssetDepreciationDetailVOMeta.PAGE_INDEX , AssetDepreciationDetailVOMeta.PAGE_SIZE } )
	@SentinelResource(value = AssetDepreciationDetailServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDepreciationDetailServiceProxy.QUERY_LIST)
	public Result<List<AssetDepreciationDetail>> queryList(AssetDepreciationDetailVO sample) {
		Result<List<AssetDepreciationDetail>> result=new Result<>();
		List<AssetDepreciationDetail> list=assetDepreciationDetailService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * ????????????????????????
	*/
	@ApiOperation(value = "????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "648956390479495168"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_ID , value = "????????????" , required = false , dataTypeClass=String.class , example = "647736203386290176"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.OPER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACTION_CODE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION_METHOD , value = "??????????????????" , required = false , dataTypeClass=String.class , example = "purchase_next_month"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_METHOD , value = "????????????" , required = false , dataTypeClass=String.class , example = "average_age"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.BUSINESS_DATE , value = "????????????" , required = false , dataTypeClass=Date.class , example = "2022-11-25 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT , value = "????????????" , required = false , dataTypeClass=String.class , example = "wait_calculate"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_DETAIL , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ID , value = "??????" , required = false , dataTypeClass=String.class , example = "648874943362105344"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "????????????"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CODE , value = "????????????" , required = false , dataTypeClass=String.class , example = "AS2022112609345"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "001"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_MODEL , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_STATUS_NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "idle"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_DATE , value = "????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_REGISTER_DATE , value = "????????????" , required = false , dataTypeClass=Date.class , example = "2022-11-25 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ORIGINAL_UNIT_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class , example = "7682.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_UNIT_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAV_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_TAX_AMOUNT_RATE , value = "??????" , required = false , dataTypeClass=BigDecimal.class , example = "2.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_SERVICE_LIFE , value = "???????????????(??????)" , required = false , dataTypeClass=BigDecimal.class , example = "12.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_SERVICE_LIFE , value = "???????????????(??????)" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_RATE , value = "???????????????" , required = false , dataTypeClass=BigDecimal.class , example = "5.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_ORIGINAL_PRICE , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_DEPRECIATION_AMOUNT , value = "(??????)??????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_NAV_AMOUNT , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_RECOVERABLE_AMOUNT , value = "(??????)?????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_USED_SERVICE_LIFE , value = "???????????????" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_ORIGINAL_PRICE_INCREASE , value = "(????????????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_DEPRECIATION_AMOUNT , value = "(????????????)???????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_YEAR_DEPRECIATION_AMOUNT , value = "(????????????)?????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_ORIGINAL_PRICE , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_DEPRECIATION_AMOUNT , value = "(??????)??????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_NAV_AMOUNT , value = "(??????)????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_RECOVERABLE_AMOUNT , value = "(??????)?????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACCOUNTING_SERVICE_LIFE , value = "???????????????????????????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_ID , value = "?????????ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_NAME , value = "?????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_ID , value = "??????ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_KEY , value = "????????????KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_KEY , value = "????????????KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.CUSTOMER_INFO , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_SOURCE , value = "?????????" , required = false , dataTypeClass=String.class , example = "648956390253002752"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_TARGET , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_ID , value = "??????????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_TIME , value = "??????????????????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LABEL , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_STR , value = "???????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_FLOAT , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
	})
	@ApiOperationSupport(order=8 , author="?????? , maillank@qq.com")
	@SentinelResource(value = AssetDepreciationDetailServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDepreciationDetailServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<AssetDepreciationDetail>> queryPagedList(AssetDepreciationDetailVO sample) {
		Result<PagedList<AssetDepreciationDetail>> result=new Result<>();
		PagedList<AssetDepreciationDetail> list=assetDepreciationDetailService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		// join ???????????????
		assetDepreciationDetailService.dao().fill(list)
			.with("useOrganization")
			.with("useUser")
			.with("manager")
			.with(AssetDepreciationDetailMeta.FINANCIAL_OPTION_DICT)
			.with(AssetDepreciationDetailMeta.EXPENSE_ITEM_DICT)
			.with(AssetDepreciationDetailMeta.ASSET_DEPRECIATION)
			.with(AssetDepreciationDetailMeta.ASSET_DEPRECIATION_OPER)
			.execute();
		result.success(true).data(list);
		return result;
	}






}