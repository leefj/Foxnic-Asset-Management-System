package com.dt.platform.ops.controller;


import java.util.List;
import java.util.ArrayList;


import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.dao.entity.ReferCause;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.github.foxnic.api.swagger.InDoc;
import org.github.foxnic.web.framework.web.SuperController;
import org.github.foxnic.web.framework.sentinel.SentinelExceptionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.github.foxnic.api.swagger.ApiParamSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;


import com.dt.platform.proxy.ops.CmdbModelVServiceProxy;
import com.dt.platform.domain.ops.meta.CmdbModelVVOMeta;
import com.dt.platform.domain.ops.CmdbModelV;
import com.dt.platform.domain.ops.CmdbModelVVO;
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
import com.dt.platform.domain.ops.meta.CmdbModelVMeta;
import java.math.BigDecimal;
import com.dt.platform.domain.ops.CmdbModel;
import com.dt.platform.domain.ops.CmdbObjAttribute;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.ICmdbModelVService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * ????????????????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-10-24 13:33:56
*/

@InDoc
@Api(tags = "?????????")
@RestController("OpsCmdbModelVController")
public class CmdbModelVController extends SuperController {

	@Autowired
	private ICmdbModelVService cmdbModelVService;


	/**
	 * ???????????????
	*/
	@ApiOperation(value = "???????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CmdbModelVVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "636683116160745472"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.PID , value = "???" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.MODEL_ID , value = "??????" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.MODEL_CODE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DATA_TRACE_CODE , value = "????????????" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.V_STATUS , value = "??????" , required = false , dataTypeClass=String.class , example = "enable"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.V_VERSION , value = "??????" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.OBJ_SOURCE_ID , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.NAME , value = "??????" , required = false , dataTypeClass=String.class , example = "121212"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DESC , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S1 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S2 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S3 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S4 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S5 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S6 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S7 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S8 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M1 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M2 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M3 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M4 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M5 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M6 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M7 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M8 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M9 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M10 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.BS1 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.BS2 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.BS3 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I1 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I2 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I3 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I4 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I5 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I6 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I7 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I8 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I9 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I10 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D1 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D2 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D3 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D4 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D5 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D6 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D7 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D8 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DA1 , value = "??????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DA2 , value = "??????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DA3 , value = "??????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.NOTES , value = "??????" , required = false , dataTypeClass=String.class , example = "1212"),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true,ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="?????? , maillank@qq.com")
	@SentinelResource(value = CmdbModelVServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CmdbModelVServiceProxy.INSERT)
	public Result insert(CmdbModelVVO cmdbModelVVO) {
		Result result=cmdbModelVService.insert(cmdbModelVVO,false);
		return result;
	}



	/**
	 * ???????????????
	*/
	@ApiOperation(value = "???????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CmdbModelVVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "636683116160745472")
	})
	@ApiOperationSupport(order=2 , author="?????? , maillank@qq.com")
	@SentinelResource(value = CmdbModelVServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CmdbModelVServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("??????id???");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// ????????????
		ReferCause cause =  cmdbModelVService.hasRefers(id);
		// ????????????????????????
		this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		Result result=cmdbModelVService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * ????????????????????? <br>
	 * ???????????????????????????????????????
	*/
	@ApiOperation(value = "?????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CmdbModelVVOMeta.IDS , value = "????????????" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="?????? , maillank@qq.com") 
	@SentinelResource(value = CmdbModelVServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CmdbModelVServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// ????????????
		this.validator().asserts(ids).require("??????ids??????");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// ????????????
		Map<String, ReferCause> causeMap = cmdbModelVService.hasRefers(ids);
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
			Result result=cmdbModelVService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// ???????????????????????????
			Result result=cmdbModelVService.deleteByIdsLogical(canDeleteIds);
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
	 * ???????????????
	*/
	@ApiOperation(value = "???????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CmdbModelVVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "636683116160745472"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.PID , value = "???" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.MODEL_ID , value = "??????" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.MODEL_CODE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DATA_TRACE_CODE , value = "????????????" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.V_STATUS , value = "??????" , required = false , dataTypeClass=String.class , example = "enable"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.V_VERSION , value = "??????" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.OBJ_SOURCE_ID , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.NAME , value = "??????" , required = false , dataTypeClass=String.class , example = "121212"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DESC , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S1 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S2 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S3 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S4 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S5 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S6 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S7 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S8 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M1 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M2 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M3 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M4 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M5 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M6 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M7 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M8 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M9 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M10 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.BS1 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.BS2 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.BS3 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I1 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I2 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I3 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I4 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I5 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I6 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I7 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I8 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I9 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I10 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D1 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D2 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D3 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D4 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D5 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D6 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D7 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D8 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DA1 , value = "??????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DA2 , value = "??????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DA3 , value = "??????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.NOTES , value = "??????" , required = false , dataTypeClass=String.class , example = "1212"),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true,ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="?????? , maillank@qq.com" ,  ignoreParameters = { CmdbModelVVOMeta.PAGE_INDEX , CmdbModelVVOMeta.PAGE_SIZE , CmdbModelVVOMeta.SEARCH_FIELD , CmdbModelVVOMeta.FUZZY_FIELD , CmdbModelVVOMeta.SEARCH_VALUE , CmdbModelVVOMeta.DIRTY_FIELDS , CmdbModelVVOMeta.SORT_FIELD , CmdbModelVVOMeta.SORT_TYPE , CmdbModelVVOMeta.IDS } )
	@SentinelResource(value = CmdbModelVServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CmdbModelVServiceProxy.UPDATE)
	public Result update(CmdbModelVVO cmdbModelVVO) {
		Result result=cmdbModelVService.update(cmdbModelVVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * ???????????????
	*/
	@ApiOperation(value = "???????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CmdbModelVVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "636683116160745472"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.PID , value = "???" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.MODEL_ID , value = "??????" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.MODEL_CODE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DATA_TRACE_CODE , value = "????????????" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.V_STATUS , value = "??????" , required = false , dataTypeClass=String.class , example = "enable"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.V_VERSION , value = "??????" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.OBJ_SOURCE_ID , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.NAME , value = "??????" , required = false , dataTypeClass=String.class , example = "121212"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DESC , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S1 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S2 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S3 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S4 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S5 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S6 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S7 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S8 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M1 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M2 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M3 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M4 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M5 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M6 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M7 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M8 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M9 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M10 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.BS1 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.BS2 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.BS3 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I1 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I2 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I3 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I4 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I5 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I6 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I7 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I8 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I9 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I10 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D1 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D2 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D3 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D4 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D5 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D6 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D7 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D8 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DA1 , value = "??????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DA2 , value = "??????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DA3 , value = "??????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.NOTES , value = "??????" , required = false , dataTypeClass=String.class , example = "1212"),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true,ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { CmdbModelVVOMeta.PAGE_INDEX , CmdbModelVVOMeta.PAGE_SIZE , CmdbModelVVOMeta.SEARCH_FIELD , CmdbModelVVOMeta.FUZZY_FIELD , CmdbModelVVOMeta.SEARCH_VALUE , CmdbModelVVOMeta.DIRTY_FIELDS , CmdbModelVVOMeta.SORT_FIELD , CmdbModelVVOMeta.SORT_TYPE , CmdbModelVVOMeta.IDS } )
	@SentinelResource(value = CmdbModelVServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CmdbModelVServiceProxy.SAVE)
	public Result save(CmdbModelVVO cmdbModelVVO) {
		Result result=cmdbModelVService.save(cmdbModelVVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * ???????????????
	*/
	@ApiOperation(value = "???????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CmdbModelVVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="?????? , maillank@qq.com")
	@SentinelResource(value = CmdbModelVServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CmdbModelVServiceProxy.GET_BY_ID)
	public Result<CmdbModelV> getById(String id) {
		Result<CmdbModelV> result=new Result<>();
		CmdbModelV cmdbModelV=cmdbModelVService.getById(id);
		result.success(true).data(cmdbModelV);
		return result;
	}


	/**
	 * ????????????????????? <br>
	 * ???????????????????????????????????????
	*/
		@ApiOperation(value = "?????????????????????")
		@ApiImplicitParams({
				@ApiImplicitParam(name = CmdbModelVVOMeta.IDS , value = "????????????" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="?????? , maillank@qq.com") 
		@SentinelResource(value = CmdbModelVServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CmdbModelVServiceProxy.GET_BY_IDS)
	public Result<List<CmdbModelV>> getByIds(List<String> ids) {
		Result<List<CmdbModelV>> result=new Result<>();
		List<CmdbModelV> list=cmdbModelVService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * ???????????????
	*/
	@ApiOperation(value = "???????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CmdbModelVVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "636683116160745472"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.PID , value = "???" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.MODEL_ID , value = "??????" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.MODEL_CODE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DATA_TRACE_CODE , value = "????????????" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.V_STATUS , value = "??????" , required = false , dataTypeClass=String.class , example = "enable"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.V_VERSION , value = "??????" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.OBJ_SOURCE_ID , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.NAME , value = "??????" , required = false , dataTypeClass=String.class , example = "121212"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DESC , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S1 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S2 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S3 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S4 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S5 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S6 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S7 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S8 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M1 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M2 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M3 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M4 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M5 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M6 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M7 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M8 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M9 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M10 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.BS1 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.BS2 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.BS3 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I1 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I2 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I3 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I4 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I5 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I6 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I7 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I8 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I9 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I10 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D1 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D2 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D3 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D4 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D5 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D6 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D7 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D8 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DA1 , value = "??????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DA2 , value = "??????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DA3 , value = "??????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.NOTES , value = "??????" , required = false , dataTypeClass=String.class , example = "1212"),
	})
	@ApiOperationSupport(order=5 , author="?????? , maillank@qq.com" ,  ignoreParameters = { CmdbModelVVOMeta.PAGE_INDEX , CmdbModelVVOMeta.PAGE_SIZE } )
	@SentinelResource(value = CmdbModelVServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CmdbModelVServiceProxy.QUERY_LIST)
	public Result<List<CmdbModelV>> queryList(CmdbModelVVO sample) {
		Result<List<CmdbModelV>> result=new Result<>();
		List<CmdbModelV> list=cmdbModelVService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * ?????????????????????
	*/
	@ApiOperation(value = "?????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CmdbModelVVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "636683116160745472"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.PID , value = "???" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.MODEL_ID , value = "??????" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.MODEL_CODE , value = "????????????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DATA_TRACE_CODE , value = "????????????" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.V_STATUS , value = "??????" , required = false , dataTypeClass=String.class , example = "enable"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.V_VERSION , value = "??????" , required = false , dataTypeClass=String.class , example = "0"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.OBJ_SOURCE_ID , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.NAME , value = "??????" , required = false , dataTypeClass=String.class , example = "121212"),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DESC , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S1 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S2 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S3 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S4 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S5 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S6 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S7 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.S8 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M1 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M2 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M3 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M4 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M5 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M6 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M7 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M8 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M9 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.M10 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.BS1 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.BS2 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.BS3 , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I1 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I2 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I3 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I4 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I5 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I6 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I7 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I8 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I9 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.I10 , value = "??????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D1 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D2 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D3 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D4 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D5 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D6 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D7 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.D8 , value = "??????" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DA1 , value = "??????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DA2 , value = "??????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.DA3 , value = "??????" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = CmdbModelVVOMeta.NOTES , value = "??????" , required = false , dataTypeClass=String.class , example = "1212"),
	})
	@ApiOperationSupport(order=8 , author="?????? , maillank@qq.com")
	@SentinelResource(value = CmdbModelVServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CmdbModelVServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<CmdbModelV>> queryPagedList(CmdbModelVVO sample) {
		Result<PagedList<CmdbModelV>> result=new Result<>();
		PagedList<CmdbModelV> list=cmdbModelVService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}






}