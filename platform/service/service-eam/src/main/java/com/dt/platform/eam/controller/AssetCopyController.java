package com.dt.platform.eam.controller;


import java.util.List;
import java.util.ArrayList;


import com.dt.platform.eam.service.IAssetService;
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


import com.dt.platform.proxy.eam.AssetCopyServiceProxy;
import com.dt.platform.domain.eam.meta.AssetCopyVOMeta;
import com.dt.platform.domain.eam.AssetCopy;
import com.dt.platform.domain.eam.AssetCopyVO;
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
import com.dt.platform.domain.eam.meta.AssetCopyMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetCopyService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * ???????????????????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-10-26 10:34:21
*/

@InDoc
@Api(tags = "????????????")
@RestController("EamAssetCopyController")
public class AssetCopyController extends SuperController {

	@Autowired
	private IAssetCopyService assetCopyService;

	@Autowired
	private IAssetService assetService;
	/**
	 * ??????????????????
	*/
	@ApiOperation(value = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetCopyVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyVOMeta.ASSET_ID , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyVOMeta.ASSET_NUMBER , value = "????????????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AssetCopyVOMeta.NOTES , value = "??????" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true,ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="?????? , maillank@qq.com")
	@SentinelResource(value = AssetCopyServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyServiceProxy.INSERT)
	public Result insert(AssetCopyVO assetCopyVO) {
		return assetService.assetCopy(assetCopyVO.getAssetId(),assetCopyVO.getAssetNumber());

	}



	/**
	 * ??????????????????
	*/
	@ApiOperation(value = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetCopyVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2 , author="?????? , maillank@qq.com")
	@SentinelResource(value = AssetCopyServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("??????id???");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// ????????????
		ReferCause cause =  assetCopyService.hasRefers(id);
		// ????????????????????????
		this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		Result result=assetCopyService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * ???????????????????????? <br>
	 * ???????????????????????????????????????
	*/
	@ApiOperation(value = "????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetCopyVOMeta.IDS , value = "????????????" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="?????? , maillank@qq.com") 
	@SentinelResource(value = AssetCopyServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// ????????????
		this.validator().asserts(ids).require("??????ids??????");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// ????????????
		Map<String, ReferCause> causeMap = assetCopyService.hasRefers(ids);
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
			Result result=assetCopyService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// ???????????????????????????
			Result result=assetCopyService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = AssetCopyVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyVOMeta.ASSET_ID , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyVOMeta.ASSET_NUMBER , value = "????????????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AssetCopyVOMeta.NOTES , value = "??????" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true,ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="?????? , maillank@qq.com" ,  ignoreParameters = { AssetCopyVOMeta.PAGE_INDEX , AssetCopyVOMeta.PAGE_SIZE , AssetCopyVOMeta.SEARCH_FIELD , AssetCopyVOMeta.FUZZY_FIELD , AssetCopyVOMeta.SEARCH_VALUE , AssetCopyVOMeta.DIRTY_FIELDS , AssetCopyVOMeta.SORT_FIELD , AssetCopyVOMeta.SORT_TYPE , AssetCopyVOMeta.IDS } )
	@SentinelResource(value = AssetCopyServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyServiceProxy.UPDATE)
	public Result update(AssetCopyVO assetCopyVO) {
		Result result=assetCopyService.update(assetCopyVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * ??????????????????
	*/
	@ApiOperation(value = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetCopyVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyVOMeta.ASSET_ID , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyVOMeta.ASSET_NUMBER , value = "????????????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AssetCopyVOMeta.NOTES , value = "??????" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true,ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { AssetCopyVOMeta.PAGE_INDEX , AssetCopyVOMeta.PAGE_SIZE , AssetCopyVOMeta.SEARCH_FIELD , AssetCopyVOMeta.FUZZY_FIELD , AssetCopyVOMeta.SEARCH_VALUE , AssetCopyVOMeta.DIRTY_FIELDS , AssetCopyVOMeta.SORT_FIELD , AssetCopyVOMeta.SORT_TYPE , AssetCopyVOMeta.IDS } )
	@SentinelResource(value = AssetCopyServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyServiceProxy.SAVE)
	public Result save(AssetCopyVO assetCopyVO) {
		Result result=assetCopyService.save(assetCopyVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * ??????????????????
	*/
	@ApiOperation(value = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetCopyVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="?????? , maillank@qq.com")
	@SentinelResource(value = AssetCopyServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyServiceProxy.GET_BY_ID)
	public Result<AssetCopy> getById(String id) {
		Result<AssetCopy> result=new Result<>();
		AssetCopy assetCopy=assetCopyService.getById(id);
		result.success(true).data(assetCopy);
		return result;
	}


	/**
	 * ???????????????????????? <br>
	 * ???????????????????????????????????????
	*/
		@ApiOperation(value = "????????????????????????")
		@ApiImplicitParams({
				@ApiImplicitParam(name = AssetCopyVOMeta.IDS , value = "????????????" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="?????? , maillank@qq.com") 
		@SentinelResource(value = AssetCopyServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyServiceProxy.GET_BY_IDS)
	public Result<List<AssetCopy>> getByIds(List<String> ids) {
		Result<List<AssetCopy>> result=new Result<>();
		List<AssetCopy> list=assetCopyService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * ??????????????????
	*/
	@ApiOperation(value = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetCopyVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyVOMeta.ASSET_ID , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyVOMeta.ASSET_NUMBER , value = "????????????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AssetCopyVOMeta.NOTES , value = "??????" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="?????? , maillank@qq.com" ,  ignoreParameters = { AssetCopyVOMeta.PAGE_INDEX , AssetCopyVOMeta.PAGE_SIZE } )
	@SentinelResource(value = AssetCopyServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyServiceProxy.QUERY_LIST)
	public Result<List<AssetCopy>> queryList(AssetCopyVO sample) {
		Result<List<AssetCopy>> result=new Result<>();
		List<AssetCopy> list=assetCopyService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * ????????????????????????
	*/
	@ApiOperation(value = "????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetCopyVOMeta.ID , value = "??????" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyVOMeta.ASSET_ID , value = "??????" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyVOMeta.ASSET_NUMBER , value = "????????????" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AssetCopyVOMeta.NOTES , value = "??????" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="?????? , maillank@qq.com")
	@SentinelResource(value = AssetCopyServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<AssetCopy>> queryPagedList(AssetCopyVO sample) {
		Result<PagedList<AssetCopy>> result=new Result<>();
		PagedList<AssetCopy> list=assetCopyService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}






}