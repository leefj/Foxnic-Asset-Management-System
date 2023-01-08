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
 * 折旧明细接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-08 11:31:30
*/

@InDoc
@Api(tags = "折旧明细")
@RestController("EamAssetDepreciationDetailController")
public class AssetDepreciationDetailController extends SuperController {

	@Autowired
	private IAssetDepreciationDetailService assetDepreciationDetailService;


	/**
	 * 添加折旧明细
	*/
	@ApiOperation(value = "添加折旧明细")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "664427663485566976"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_ID , value = "折旧方案" , required = false , dataTypeClass=String.class , example = "647736203386290176"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.OPER_ID , value = "折旧操作" , required = false , dataTypeClass=String.class , example = "664111029461975040"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACTION_CODE , value = "折旧动作" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION_METHOD , value = "首次折旧方式" , required = false , dataTypeClass=String.class , example = "register_next_month"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_METHOD , value = "折旧方式" , required = false , dataTypeClass=String.class , example = "average_age"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.BUSINESS_DATE , value = "业务日期" , required = false , dataTypeClass=Date.class , example = "2023-01-07 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT , value = "折旧规则" , required = false , dataTypeClass=String.class , example = "depreciation_first"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_STATUS , value = "折旧结果" , required = false , dataTypeClass=String.class , example = "success"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_DETAIL , value = "结果明细" , required = false , dataTypeClass=String.class , example = "操作成功"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ID , value = "资产" , required = false , dataTypeClass=String.class , example = "664110928148561920"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_ID , value = "资产类别" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_NAME , value = "资产类别" , required = false , dataTypeClass=String.class , example = "办公设备"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_ID , value = "财务分类" , required = false , dataTypeClass=String.class , example = "649152215159668736"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_NAME , value = "财务分类" , required = false , dataTypeClass=String.class , example = "电子设备"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CODE , value = "资产编码" , required = false , dataTypeClass=String.class , example = "AS16209"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAME , value = "资产名称" , required = false , dataTypeClass=String.class , example = "1212"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_MODEL , value = "资产型号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_STATUS_NAME , value = "资产状态" , required = false , dataTypeClass=String.class , example = "闲置"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_DATE , value = "采购日期" , required = false , dataTypeClass=Date.class , example = "2023-01-05 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_REGISTER_DATE , value = "入账日期" , required = false , dataTypeClass=Date.class , example = "2023-01-07 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ORIGINAL_UNIT_PRICE , value = "资产原值" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_UNIT_PRICE , value = "含税单价" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAV_PRICE , value = "资产净值" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_TAX_AMOUNT_RATE , value = "税额" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_SERVICE_LIFE , value = "可使用期限(资产)" , required = false , dataTypeClass=BigDecimal.class , example = "12.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_SERVICE_LIFE , value = "可使用期限(财务)" , required = false , dataTypeClass=BigDecimal.class , example = "36.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_RATE , value = "本期残值率" , required = false , dataTypeClass=BigDecimal.class , example = "5.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_PRICE , value = "本期残值" , required = false , dataTypeClass=BigDecimal.class , example = "60.55"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_ORIGINAL_PRICE , value = "(期初)期初原值" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_DEPRECIATION_AMOUNT , value = "(期初)期初累计折旧" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_NAV_AMOUNT , value = "(期初)期初净值" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_RECOVERABLE_AMOUNT , value = "(期初)期初可回收净额" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_USED_SERVICE_LIFE , value = "已使用期限" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_ORIGINAL_PRICE_INCREASE , value = "(本期发生)原值增加" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_DEPRECIATION_AMOUNT , value = "(本期发生)本期折旧额" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_YEAR_DEPRECIATION_AMOUNT , value = "(本期发生)本年累计折旧额" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_ORIGINAL_PRICE , value = "(期末)期末原值" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_DEPRECIATION_AMOUNT , value = "(期末)期末累计折旧" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_NAV_AMOUNT , value = "(期末)期末净值" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_RECOVERABLE_AMOUNT , value = "(期末)期末可回收金额" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACCOUNTING_SERVICE_LIFE , value = "会计期间已使用期限" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION , value = "首次折旧" , required = false , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_ID , value = "使用人ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_NAME , value = "使用人" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_ID , value = "管理人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_NAME , value = "管理人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_ID , value = "部门ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_NAME , value = "使用部门" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_KEY , value = "财务选项KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_KEY , value = "费用项目KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_NAME , value = "财务选项" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_NAME , value = "费用项目" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.CUSTOMER_INFO , value = "客户情况" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_SOURCE , value = "源资产" , required = false , dataTypeClass=String.class , example = "664110928148561920"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_TARGET , value = "目标资产" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_ID , value = "上次折旧单据" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_TIME , value = "上次折旧时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LABEL , value = "标签" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_STR , value = "结果字符串" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_FLOAT , value = "结果浮点" , required = false , dataTypeClass=BigDecimal.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AssetDepreciationDetailServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDepreciationDetailServiceProxy.INSERT)
	public Result insert(AssetDepreciationDetailVO assetDepreciationDetailVO) {
		Result result=assetDepreciationDetailService.insert(assetDepreciationDetailVO,false);
		return result;
	}



	/**
	 * 删除折旧明细
	*/
	@ApiOperation(value = "删除折旧明细")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "664427663485566976")
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AssetDepreciationDetailServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDepreciationDetailServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  assetDepreciationDetailService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=assetDepreciationDetailService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除折旧明细 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除折旧明细")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = AssetDepreciationDetailServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDepreciationDetailServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = assetDepreciationDetailService.hasRefers(ids);
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
			Result result=assetDepreciationDetailService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=assetDepreciationDetailService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新折旧明细
	*/
	@ApiOperation(value = "更新折旧明细")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "664427663485566976"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_ID , value = "折旧方案" , required = false , dataTypeClass=String.class , example = "647736203386290176"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.OPER_ID , value = "折旧操作" , required = false , dataTypeClass=String.class , example = "664111029461975040"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACTION_CODE , value = "折旧动作" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION_METHOD , value = "首次折旧方式" , required = false , dataTypeClass=String.class , example = "register_next_month"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_METHOD , value = "折旧方式" , required = false , dataTypeClass=String.class , example = "average_age"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.BUSINESS_DATE , value = "业务日期" , required = false , dataTypeClass=Date.class , example = "2023-01-07 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT , value = "折旧规则" , required = false , dataTypeClass=String.class , example = "depreciation_first"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_STATUS , value = "折旧结果" , required = false , dataTypeClass=String.class , example = "success"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_DETAIL , value = "结果明细" , required = false , dataTypeClass=String.class , example = "操作成功"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ID , value = "资产" , required = false , dataTypeClass=String.class , example = "664110928148561920"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_ID , value = "资产类别" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_NAME , value = "资产类别" , required = false , dataTypeClass=String.class , example = "办公设备"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_ID , value = "财务分类" , required = false , dataTypeClass=String.class , example = "649152215159668736"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_NAME , value = "财务分类" , required = false , dataTypeClass=String.class , example = "电子设备"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CODE , value = "资产编码" , required = false , dataTypeClass=String.class , example = "AS16209"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAME , value = "资产名称" , required = false , dataTypeClass=String.class , example = "1212"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_MODEL , value = "资产型号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_STATUS_NAME , value = "资产状态" , required = false , dataTypeClass=String.class , example = "闲置"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_DATE , value = "采购日期" , required = false , dataTypeClass=Date.class , example = "2023-01-05 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_REGISTER_DATE , value = "入账日期" , required = false , dataTypeClass=Date.class , example = "2023-01-07 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ORIGINAL_UNIT_PRICE , value = "资产原值" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_UNIT_PRICE , value = "含税单价" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAV_PRICE , value = "资产净值" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_TAX_AMOUNT_RATE , value = "税额" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_SERVICE_LIFE , value = "可使用期限(资产)" , required = false , dataTypeClass=BigDecimal.class , example = "12.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_SERVICE_LIFE , value = "可使用期限(财务)" , required = false , dataTypeClass=BigDecimal.class , example = "36.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_RATE , value = "本期残值率" , required = false , dataTypeClass=BigDecimal.class , example = "5.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_PRICE , value = "本期残值" , required = false , dataTypeClass=BigDecimal.class , example = "60.55"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_ORIGINAL_PRICE , value = "(期初)期初原值" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_DEPRECIATION_AMOUNT , value = "(期初)期初累计折旧" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_NAV_AMOUNT , value = "(期初)期初净值" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_RECOVERABLE_AMOUNT , value = "(期初)期初可回收净额" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_USED_SERVICE_LIFE , value = "已使用期限" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_ORIGINAL_PRICE_INCREASE , value = "(本期发生)原值增加" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_DEPRECIATION_AMOUNT , value = "(本期发生)本期折旧额" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_YEAR_DEPRECIATION_AMOUNT , value = "(本期发生)本年累计折旧额" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_ORIGINAL_PRICE , value = "(期末)期末原值" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_DEPRECIATION_AMOUNT , value = "(期末)期末累计折旧" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_NAV_AMOUNT , value = "(期末)期末净值" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_RECOVERABLE_AMOUNT , value = "(期末)期末可回收金额" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACCOUNTING_SERVICE_LIFE , value = "会计期间已使用期限" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION , value = "首次折旧" , required = false , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_ID , value = "使用人ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_NAME , value = "使用人" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_ID , value = "管理人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_NAME , value = "管理人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_ID , value = "部门ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_NAME , value = "使用部门" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_KEY , value = "财务选项KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_KEY , value = "费用项目KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_NAME , value = "财务选项" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_NAME , value = "费用项目" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.CUSTOMER_INFO , value = "客户情况" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_SOURCE , value = "源资产" , required = false , dataTypeClass=String.class , example = "664110928148561920"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_TARGET , value = "目标资产" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_ID , value = "上次折旧单据" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_TIME , value = "上次折旧时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LABEL , value = "标签" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_STR , value = "结果字符串" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_FLOAT , value = "结果浮点" , required = false , dataTypeClass=BigDecimal.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { AssetDepreciationDetailVOMeta.PAGE_INDEX , AssetDepreciationDetailVOMeta.PAGE_SIZE , AssetDepreciationDetailVOMeta.SEARCH_FIELD , AssetDepreciationDetailVOMeta.FUZZY_FIELD , AssetDepreciationDetailVOMeta.SEARCH_VALUE , AssetDepreciationDetailVOMeta.DIRTY_FIELDS , AssetDepreciationDetailVOMeta.SORT_FIELD , AssetDepreciationDetailVOMeta.SORT_TYPE , AssetDepreciationDetailVOMeta.DATA_ORIGIN , AssetDepreciationDetailVOMeta.QUERY_LOGIC , AssetDepreciationDetailVOMeta.IDS } )
	@SentinelResource(value = AssetDepreciationDetailServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDepreciationDetailServiceProxy.UPDATE)
	public Result update(AssetDepreciationDetailVO assetDepreciationDetailVO) {
		Result result=assetDepreciationDetailService.update(assetDepreciationDetailVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存折旧明细
	*/
	@ApiOperation(value = "保存折旧明细")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "664427663485566976"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_ID , value = "折旧方案" , required = false , dataTypeClass=String.class , example = "647736203386290176"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.OPER_ID , value = "折旧操作" , required = false , dataTypeClass=String.class , example = "664111029461975040"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACTION_CODE , value = "折旧动作" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION_METHOD , value = "首次折旧方式" , required = false , dataTypeClass=String.class , example = "register_next_month"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_METHOD , value = "折旧方式" , required = false , dataTypeClass=String.class , example = "average_age"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.BUSINESS_DATE , value = "业务日期" , required = false , dataTypeClass=Date.class , example = "2023-01-07 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT , value = "折旧规则" , required = false , dataTypeClass=String.class , example = "depreciation_first"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_STATUS , value = "折旧结果" , required = false , dataTypeClass=String.class , example = "success"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_DETAIL , value = "结果明细" , required = false , dataTypeClass=String.class , example = "操作成功"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ID , value = "资产" , required = false , dataTypeClass=String.class , example = "664110928148561920"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_ID , value = "资产类别" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_NAME , value = "资产类别" , required = false , dataTypeClass=String.class , example = "办公设备"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_ID , value = "财务分类" , required = false , dataTypeClass=String.class , example = "649152215159668736"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_NAME , value = "财务分类" , required = false , dataTypeClass=String.class , example = "电子设备"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CODE , value = "资产编码" , required = false , dataTypeClass=String.class , example = "AS16209"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAME , value = "资产名称" , required = false , dataTypeClass=String.class , example = "1212"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_MODEL , value = "资产型号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_STATUS_NAME , value = "资产状态" , required = false , dataTypeClass=String.class , example = "闲置"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_DATE , value = "采购日期" , required = false , dataTypeClass=Date.class , example = "2023-01-05 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_REGISTER_DATE , value = "入账日期" , required = false , dataTypeClass=Date.class , example = "2023-01-07 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ORIGINAL_UNIT_PRICE , value = "资产原值" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_UNIT_PRICE , value = "含税单价" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAV_PRICE , value = "资产净值" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_TAX_AMOUNT_RATE , value = "税额" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_SERVICE_LIFE , value = "可使用期限(资产)" , required = false , dataTypeClass=BigDecimal.class , example = "12.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_SERVICE_LIFE , value = "可使用期限(财务)" , required = false , dataTypeClass=BigDecimal.class , example = "36.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_RATE , value = "本期残值率" , required = false , dataTypeClass=BigDecimal.class , example = "5.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_PRICE , value = "本期残值" , required = false , dataTypeClass=BigDecimal.class , example = "60.55"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_ORIGINAL_PRICE , value = "(期初)期初原值" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_DEPRECIATION_AMOUNT , value = "(期初)期初累计折旧" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_NAV_AMOUNT , value = "(期初)期初净值" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_RECOVERABLE_AMOUNT , value = "(期初)期初可回收净额" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_USED_SERVICE_LIFE , value = "已使用期限" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_ORIGINAL_PRICE_INCREASE , value = "(本期发生)原值增加" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_DEPRECIATION_AMOUNT , value = "(本期发生)本期折旧额" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_YEAR_DEPRECIATION_AMOUNT , value = "(本期发生)本年累计折旧额" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_ORIGINAL_PRICE , value = "(期末)期末原值" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_DEPRECIATION_AMOUNT , value = "(期末)期末累计折旧" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_NAV_AMOUNT , value = "(期末)期末净值" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_RECOVERABLE_AMOUNT , value = "(期末)期末可回收金额" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACCOUNTING_SERVICE_LIFE , value = "会计期间已使用期限" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION , value = "首次折旧" , required = false , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_ID , value = "使用人ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_NAME , value = "使用人" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_ID , value = "管理人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_NAME , value = "管理人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_ID , value = "部门ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_NAME , value = "使用部门" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_KEY , value = "财务选项KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_KEY , value = "费用项目KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_NAME , value = "财务选项" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_NAME , value = "费用项目" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.CUSTOMER_INFO , value = "客户情况" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_SOURCE , value = "源资产" , required = false , dataTypeClass=String.class , example = "664110928148561920"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_TARGET , value = "目标资产" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_ID , value = "上次折旧单据" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_TIME , value = "上次折旧时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LABEL , value = "标签" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_STR , value = "结果字符串" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_FLOAT , value = "结果浮点" , required = false , dataTypeClass=BigDecimal.class),
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
	 * 获取折旧明细
	*/
	@ApiOperation(value = "获取折旧明细")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AssetDepreciationDetailServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDepreciationDetailServiceProxy.GET_BY_ID)
	public Result<AssetDepreciationDetail> getById(String id) {
		Result<AssetDepreciationDetail> result=new Result<>();
		AssetDepreciationDetail assetDepreciationDetail=assetDepreciationDetailService.getById(id);
		// join 关联的对象
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
	 * 批量获取折旧明细 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取折旧明细")
		@ApiImplicitParams({
				@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = AssetDepreciationDetailServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDepreciationDetailServiceProxy.GET_BY_IDS)
	public Result<List<AssetDepreciationDetail>> getByIds(List<String> ids) {
		Result<List<AssetDepreciationDetail>> result=new Result<>();
		List<AssetDepreciationDetail> list=assetDepreciationDetailService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询折旧明细
	*/
	@ApiOperation(value = "查询折旧明细")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "664427663485566976"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_ID , value = "折旧方案" , required = false , dataTypeClass=String.class , example = "647736203386290176"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.OPER_ID , value = "折旧操作" , required = false , dataTypeClass=String.class , example = "664111029461975040"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACTION_CODE , value = "折旧动作" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION_METHOD , value = "首次折旧方式" , required = false , dataTypeClass=String.class , example = "register_next_month"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_METHOD , value = "折旧方式" , required = false , dataTypeClass=String.class , example = "average_age"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.BUSINESS_DATE , value = "业务日期" , required = false , dataTypeClass=Date.class , example = "2023-01-07 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT , value = "折旧规则" , required = false , dataTypeClass=String.class , example = "depreciation_first"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_STATUS , value = "折旧结果" , required = false , dataTypeClass=String.class , example = "success"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_DETAIL , value = "结果明细" , required = false , dataTypeClass=String.class , example = "操作成功"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ID , value = "资产" , required = false , dataTypeClass=String.class , example = "664110928148561920"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_ID , value = "资产类别" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_NAME , value = "资产类别" , required = false , dataTypeClass=String.class , example = "办公设备"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_ID , value = "财务分类" , required = false , dataTypeClass=String.class , example = "649152215159668736"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_NAME , value = "财务分类" , required = false , dataTypeClass=String.class , example = "电子设备"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CODE , value = "资产编码" , required = false , dataTypeClass=String.class , example = "AS16209"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAME , value = "资产名称" , required = false , dataTypeClass=String.class , example = "1212"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_MODEL , value = "资产型号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_STATUS_NAME , value = "资产状态" , required = false , dataTypeClass=String.class , example = "闲置"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_DATE , value = "采购日期" , required = false , dataTypeClass=Date.class , example = "2023-01-05 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_REGISTER_DATE , value = "入账日期" , required = false , dataTypeClass=Date.class , example = "2023-01-07 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ORIGINAL_UNIT_PRICE , value = "资产原值" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_UNIT_PRICE , value = "含税单价" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAV_PRICE , value = "资产净值" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_TAX_AMOUNT_RATE , value = "税额" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_SERVICE_LIFE , value = "可使用期限(资产)" , required = false , dataTypeClass=BigDecimal.class , example = "12.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_SERVICE_LIFE , value = "可使用期限(财务)" , required = false , dataTypeClass=BigDecimal.class , example = "36.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_RATE , value = "本期残值率" , required = false , dataTypeClass=BigDecimal.class , example = "5.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_PRICE , value = "本期残值" , required = false , dataTypeClass=BigDecimal.class , example = "60.55"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_ORIGINAL_PRICE , value = "(期初)期初原值" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_DEPRECIATION_AMOUNT , value = "(期初)期初累计折旧" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_NAV_AMOUNT , value = "(期初)期初净值" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_RECOVERABLE_AMOUNT , value = "(期初)期初可回收净额" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_USED_SERVICE_LIFE , value = "已使用期限" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_ORIGINAL_PRICE_INCREASE , value = "(本期发生)原值增加" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_DEPRECIATION_AMOUNT , value = "(本期发生)本期折旧额" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_YEAR_DEPRECIATION_AMOUNT , value = "(本期发生)本年累计折旧额" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_ORIGINAL_PRICE , value = "(期末)期末原值" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_DEPRECIATION_AMOUNT , value = "(期末)期末累计折旧" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_NAV_AMOUNT , value = "(期末)期末净值" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_RECOVERABLE_AMOUNT , value = "(期末)期末可回收金额" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACCOUNTING_SERVICE_LIFE , value = "会计期间已使用期限" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION , value = "首次折旧" , required = false , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_ID , value = "使用人ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_NAME , value = "使用人" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_ID , value = "管理人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_NAME , value = "管理人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_ID , value = "部门ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_NAME , value = "使用部门" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_KEY , value = "财务选项KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_KEY , value = "费用项目KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_NAME , value = "财务选项" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_NAME , value = "费用项目" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.CUSTOMER_INFO , value = "客户情况" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_SOURCE , value = "源资产" , required = false , dataTypeClass=String.class , example = "664110928148561920"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_TARGET , value = "目标资产" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_ID , value = "上次折旧单据" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_TIME , value = "上次折旧时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LABEL , value = "标签" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_STR , value = "结果字符串" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_FLOAT , value = "结果浮点" , required = false , dataTypeClass=BigDecimal.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { AssetDepreciationDetailVOMeta.PAGE_INDEX , AssetDepreciationDetailVOMeta.PAGE_SIZE } )
	@SentinelResource(value = AssetDepreciationDetailServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDepreciationDetailServiceProxy.QUERY_LIST)
	public Result<List<AssetDepreciationDetail>> queryList(AssetDepreciationDetailVO sample) {
		Result<List<AssetDepreciationDetail>> result=new Result<>();
		List<AssetDepreciationDetail> list=assetDepreciationDetailService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询折旧明细
	*/
	@ApiOperation(value = "分页查询折旧明细")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "664427663485566976"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_ID , value = "折旧方案" , required = false , dataTypeClass=String.class , example = "647736203386290176"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.OPER_ID , value = "折旧操作" , required = false , dataTypeClass=String.class , example = "664111029461975040"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACTION_CODE , value = "折旧动作" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION_METHOD , value = "首次折旧方式" , required = false , dataTypeClass=String.class , example = "register_next_month"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_METHOD , value = "折旧方式" , required = false , dataTypeClass=String.class , example = "average_age"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.BUSINESS_DATE , value = "业务日期" , required = false , dataTypeClass=Date.class , example = "2023-01-07 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT , value = "折旧规则" , required = false , dataTypeClass=String.class , example = "depreciation_first"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_STATUS , value = "折旧结果" , required = false , dataTypeClass=String.class , example = "success"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_DETAIL , value = "结果明细" , required = false , dataTypeClass=String.class , example = "操作成功"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ID , value = "资产" , required = false , dataTypeClass=String.class , example = "664110928148561920"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_ID , value = "资产类别" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_NAME , value = "资产类别" , required = false , dataTypeClass=String.class , example = "办公设备"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_ID , value = "财务分类" , required = false , dataTypeClass=String.class , example = "649152215159668736"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_NAME , value = "财务分类" , required = false , dataTypeClass=String.class , example = "电子设备"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CODE , value = "资产编码" , required = false , dataTypeClass=String.class , example = "AS16209"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAME , value = "资产名称" , required = false , dataTypeClass=String.class , example = "1212"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_MODEL , value = "资产型号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_STATUS_NAME , value = "资产状态" , required = false , dataTypeClass=String.class , example = "闲置"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_DATE , value = "采购日期" , required = false , dataTypeClass=Date.class , example = "2023-01-05 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_REGISTER_DATE , value = "入账日期" , required = false , dataTypeClass=Date.class , example = "2023-01-07 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ORIGINAL_UNIT_PRICE , value = "资产原值" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_UNIT_PRICE , value = "含税单价" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAV_PRICE , value = "资产净值" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_TAX_AMOUNT_RATE , value = "税额" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_SERVICE_LIFE , value = "可使用期限(资产)" , required = false , dataTypeClass=BigDecimal.class , example = "12.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_FINANCE_SERVICE_LIFE , value = "可使用期限(财务)" , required = false , dataTypeClass=BigDecimal.class , example = "36.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_RATE , value = "本期残值率" , required = false , dataTypeClass=BigDecimal.class , example = "5.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_PRICE , value = "本期残值" , required = false , dataTypeClass=BigDecimal.class , example = "60.55"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_ORIGINAL_PRICE , value = "(期初)期初原值" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_DEPRECIATION_AMOUNT , value = "(期初)期初累计折旧" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_NAV_AMOUNT , value = "(期初)期初净值" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_RECOVERABLE_AMOUNT , value = "(期初)期初可回收净额" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_USED_SERVICE_LIFE , value = "已使用期限" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_ORIGINAL_PRICE_INCREASE , value = "(本期发生)原值增加" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_DEPRECIATION_AMOUNT , value = "(本期发生)本期折旧额" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_YEAR_DEPRECIATION_AMOUNT , value = "(本期发生)本年累计折旧额" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_ORIGINAL_PRICE , value = "(期末)期末原值" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_DEPRECIATION_AMOUNT , value = "(期末)期末累计折旧" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_NAV_AMOUNT , value = "(期末)期末净值" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_RECOVERABLE_AMOUNT , value = "(期末)期末可回收金额" , required = false , dataTypeClass=BigDecimal.class , example = "1211.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACCOUNTING_SERVICE_LIFE , value = "会计期间已使用期限" , required = false , dataTypeClass=BigDecimal.class , example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION , value = "首次折旧" , required = false , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_ID , value = "使用人ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_NAME , value = "使用人" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_ID , value = "管理人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.MANAGER_NAME , value = "管理人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_ID , value = "部门ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_NAME , value = "使用部门" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_KEY , value = "财务选项KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_KEY , value = "费用项目KEY" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_NAME , value = "财务选项" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_NAME , value = "费用项目" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.CUSTOMER_INFO , value = "客户情况" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_SOURCE , value = "源资产" , required = false , dataTypeClass=String.class , example = "664110928148561920"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_TARGET , value = "目标资产" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_ID , value = "上次折旧单据" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_TIME , value = "上次折旧时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LABEL , value = "标签" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_STR , value = "结果字符串" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_VALUE_FLOAT , value = "结果浮点" , required = false , dataTypeClass=BigDecimal.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AssetDepreciationDetailServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetDepreciationDetailServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<AssetDepreciationDetail>> queryPagedList(AssetDepreciationDetailVO sample) {
		Result<PagedList<AssetDepreciationDetail>> result=new Result<>();
		PagedList<AssetDepreciationDetail> list=assetDepreciationDetailService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		// join 关联的对象
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