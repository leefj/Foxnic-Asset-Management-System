package com.dt.platform.common.controller;


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


import com.dt.platform.proxy.common.CodeAttrServiceProxy;
import com.dt.platform.domain.common.meta.CodeAttrVOMeta;
import com.dt.platform.domain.common.CodeAttr;
import com.dt.platform.domain.common.CodeAttrVO;
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
import com.dt.platform.domain.common.meta.CodeAttrMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.common.service.ICodeAttrService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * ???????????????????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-10-25 09:57:38
*/

@InDoc
@Api(tags = "????????????")
@RestController("SysCodeAttrController")
public class CodeAttrController extends SuperController {

	@Autowired
	private ICodeAttrService codeAttrService;


	/**
	 * ??????????????????
	*/
	@ApiOperation(value = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CodeAttrVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "476322206368923648"),
		@ApiImplicitParam(name = CodeAttrVOMeta.NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "???-(2021)"),
		@ApiImplicitParam(name = CodeAttrVOMeta.TYPE , value = "????????????" , required = false , dataTypeClass=String.class , example = "time"),
		@ApiImplicitParam(name = CodeAttrVOMeta.CODE , value = "?????????" , required = false , dataTypeClass=String.class , example = "${time,yyyy}"),
		@ApiImplicitParam(name = CodeAttrVOMeta.SORT , value = "??????" , required = false , dataTypeClass=Integer.class , example = "9999"),
		@ApiImplicitParam(name = CodeAttrVOMeta.NOTES , value = "??????" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true,ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="?????? , maillank@qq.com")
	@SentinelResource(value = CodeAttrServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CodeAttrServiceProxy.INSERT)
	public Result insert(CodeAttrVO codeAttrVO) {
		Result result=codeAttrService.insert(codeAttrVO,false);
		return result;
	}



	/**
	 * ??????????????????
	*/
	@ApiOperation(value = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CodeAttrVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "476322206368923648")
	})
	@ApiOperationSupport(order=2 , author="?????? , maillank@qq.com")
	@SentinelResource(value = CodeAttrServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CodeAttrServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("??????id???");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// ????????????
		ReferCause cause =  codeAttrService.hasRefers(id);
		// ????????????????????????
		this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		Result result=codeAttrService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * ???????????????????????? <br>
	 * ???????????????????????????????????????
	*/
	@ApiOperation(value = "????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CodeAttrVOMeta.IDS , value = "????????????" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="?????? , maillank@qq.com") 
	@SentinelResource(value = CodeAttrServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CodeAttrServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// ????????????
		this.validator().asserts(ids).require("??????ids??????");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// ????????????
		Map<String, ReferCause> causeMap = codeAttrService.hasRefers(ids);
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
			Result result=codeAttrService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// ???????????????????????????
			Result result=codeAttrService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = CodeAttrVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "476322206368923648"),
		@ApiImplicitParam(name = CodeAttrVOMeta.NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "???-(2021)"),
		@ApiImplicitParam(name = CodeAttrVOMeta.TYPE , value = "????????????" , required = false , dataTypeClass=String.class , example = "time"),
		@ApiImplicitParam(name = CodeAttrVOMeta.CODE , value = "?????????" , required = false , dataTypeClass=String.class , example = "${time,yyyy}"),
		@ApiImplicitParam(name = CodeAttrVOMeta.SORT , value = "??????" , required = false , dataTypeClass=Integer.class , example = "9999"),
		@ApiImplicitParam(name = CodeAttrVOMeta.NOTES , value = "??????" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true,ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="?????? , maillank@qq.com" ,  ignoreParameters = { CodeAttrVOMeta.PAGE_INDEX , CodeAttrVOMeta.PAGE_SIZE , CodeAttrVOMeta.SEARCH_FIELD , CodeAttrVOMeta.FUZZY_FIELD , CodeAttrVOMeta.SEARCH_VALUE , CodeAttrVOMeta.DIRTY_FIELDS , CodeAttrVOMeta.SORT_FIELD , CodeAttrVOMeta.SORT_TYPE , CodeAttrVOMeta.IDS } )
	@SentinelResource(value = CodeAttrServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CodeAttrServiceProxy.UPDATE)
	public Result update(CodeAttrVO codeAttrVO) {
		Result result=codeAttrService.update(codeAttrVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * ??????????????????
	*/
	@ApiOperation(value = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CodeAttrVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "476322206368923648"),
		@ApiImplicitParam(name = CodeAttrVOMeta.NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "???-(2021)"),
		@ApiImplicitParam(name = CodeAttrVOMeta.TYPE , value = "????????????" , required = false , dataTypeClass=String.class , example = "time"),
		@ApiImplicitParam(name = CodeAttrVOMeta.CODE , value = "?????????" , required = false , dataTypeClass=String.class , example = "${time,yyyy}"),
		@ApiImplicitParam(name = CodeAttrVOMeta.SORT , value = "??????" , required = false , dataTypeClass=Integer.class , example = "9999"),
		@ApiImplicitParam(name = CodeAttrVOMeta.NOTES , value = "??????" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true,ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { CodeAttrVOMeta.PAGE_INDEX , CodeAttrVOMeta.PAGE_SIZE , CodeAttrVOMeta.SEARCH_FIELD , CodeAttrVOMeta.FUZZY_FIELD , CodeAttrVOMeta.SEARCH_VALUE , CodeAttrVOMeta.DIRTY_FIELDS , CodeAttrVOMeta.SORT_FIELD , CodeAttrVOMeta.SORT_TYPE , CodeAttrVOMeta.IDS } )
	@SentinelResource(value = CodeAttrServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CodeAttrServiceProxy.SAVE)
	public Result save(CodeAttrVO codeAttrVO) {
		Result result=codeAttrService.save(codeAttrVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * ??????????????????
	*/
	@ApiOperation(value = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CodeAttrVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="?????? , maillank@qq.com")
	@SentinelResource(value = CodeAttrServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CodeAttrServiceProxy.GET_BY_ID)
	public Result<CodeAttr> getById(String id) {
		Result<CodeAttr> result=new Result<>();
		CodeAttr codeAttr=codeAttrService.getById(id);
		result.success(true).data(codeAttr);
		return result;
	}


	/**
	 * ???????????????????????? <br>
	 * ???????????????????????????????????????
	*/
		@ApiOperation(value = "????????????????????????")
		@ApiImplicitParams({
				@ApiImplicitParam(name = CodeAttrVOMeta.IDS , value = "????????????" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="?????? , maillank@qq.com") 
		@SentinelResource(value = CodeAttrServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CodeAttrServiceProxy.GET_BY_IDS)
	public Result<List<CodeAttr>> getByIds(List<String> ids) {
		Result<List<CodeAttr>> result=new Result<>();
		List<CodeAttr> list=codeAttrService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * ??????????????????
	*/
	@ApiOperation(value = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CodeAttrVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "476322206368923648"),
		@ApiImplicitParam(name = CodeAttrVOMeta.NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "???-(2021)"),
		@ApiImplicitParam(name = CodeAttrVOMeta.TYPE , value = "????????????" , required = false , dataTypeClass=String.class , example = "time"),
		@ApiImplicitParam(name = CodeAttrVOMeta.CODE , value = "?????????" , required = false , dataTypeClass=String.class , example = "${time,yyyy}"),
		@ApiImplicitParam(name = CodeAttrVOMeta.SORT , value = "??????" , required = false , dataTypeClass=Integer.class , example = "9999"),
		@ApiImplicitParam(name = CodeAttrVOMeta.NOTES , value = "??????" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="?????? , maillank@qq.com" ,  ignoreParameters = { CodeAttrVOMeta.PAGE_INDEX , CodeAttrVOMeta.PAGE_SIZE } )
	@SentinelResource(value = CodeAttrServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CodeAttrServiceProxy.QUERY_LIST)
	public Result<List<CodeAttr>> queryList(CodeAttrVO sample) {
		Result<List<CodeAttr>> result=new Result<>();
		List<CodeAttr> list=codeAttrService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * ????????????????????????
	*/
	@ApiOperation(value = "????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CodeAttrVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "476322206368923648"),
		@ApiImplicitParam(name = CodeAttrVOMeta.NAME , value = "????????????" , required = false , dataTypeClass=String.class , example = "???-(2021)"),
		@ApiImplicitParam(name = CodeAttrVOMeta.TYPE , value = "????????????" , required = false , dataTypeClass=String.class , example = "time"),
		@ApiImplicitParam(name = CodeAttrVOMeta.CODE , value = "?????????" , required = false , dataTypeClass=String.class , example = "${time,yyyy}"),
		@ApiImplicitParam(name = CodeAttrVOMeta.SORT , value = "??????" , required = false , dataTypeClass=Integer.class , example = "9999"),
		@ApiImplicitParam(name = CodeAttrVOMeta.NOTES , value = "??????" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="?????? , maillank@qq.com")
	@SentinelResource(value = CodeAttrServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CodeAttrServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<CodeAttr>> queryPagedList(CodeAttrVO sample) {
		Result<PagedList<CodeAttr>> result=new Result<>();
		PagedList<CodeAttr> list=codeAttrService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}






}