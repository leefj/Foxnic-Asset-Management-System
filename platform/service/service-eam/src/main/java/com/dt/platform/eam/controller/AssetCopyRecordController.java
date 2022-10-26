package com.dt.platform.eam.controller;


import java.util.List;
import java.util.ArrayList;


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


import com.dt.platform.proxy.eam.AssetCopyRecordServiceProxy;
import com.dt.platform.domain.eam.meta.AssetCopyRecordVOMeta;
import com.dt.platform.domain.eam.AssetCopyRecord;
import com.dt.platform.domain.eam.AssetCopyRecordVO;
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
import com.dt.platform.domain.eam.meta.AssetCopyRecordMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetCopyRecordService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 资产复制记录接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-10-26 11:37:43
*/

@InDoc
@Api(tags = "资产复制记录")
@RestController("EamAssetCopyRecordController")
public class AssetCopyRecordController extends SuperController {

	@Autowired
	private IAssetCopyRecordService assetCopyRecordService;


	/**
	 * 添加资产复制记录
	*/
	@ApiOperation(value = "添加资产复制记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.COPY_ID , value = "复制" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.COPY_SOURCE_ASSET_ID , value = "资产" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.COPY_TARGET_ASSET_ID , value = "资产" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true,ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AssetCopyRecordServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyRecordServiceProxy.INSERT)
	public Result insert(AssetCopyRecordVO assetCopyRecordVO) {
		Result result=assetCopyRecordService.insert(assetCopyRecordVO,false);
		return result;
	}



	/**
	 * 删除资产复制记录
	*/
	@ApiOperation(value = "删除资产复制记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AssetCopyRecordServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyRecordServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		Boolean hasRefer = assetCopyRecordService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(hasRefer).requireEqual("不允许删除当前记录",false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		Result result=assetCopyRecordService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除资产复制记录 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除资产复制记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = AssetCopyRecordServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyRecordServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, Boolean> hasRefersMap = assetCopyRecordService.hasRefers(ids);
		// 收集可以删除的ID值
		List<String> canDeleteIds = new ArrayList<>();
		for (Map.Entry<String, Boolean> e : hasRefersMap.entrySet()) {
			if (!e.getValue()) {
				canDeleteIds.add(e.getKey());
			}
		}

		// 执行删除
		if (canDeleteIds.isEmpty()) {
			// 如果没有一行可以被删除
			return ErrorDesc.failure().message("无法删除您选中的数据行");
		} else if (canDeleteIds.size() == ids.size()) {
			// 如果全部可以删除
			Result result=assetCopyRecordService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=assetCopyRecordService.deleteByIdsLogical(canDeleteIds);
			if (result.failure()) {
				return result;
			} else {
				return ErrorDesc.success().message("已删除 " + canDeleteIds.size() + " 行，但另有 " + (ids.size() - canDeleteIds.size()) + " 行数据无法删除").messageLevel4Confirm();
			}
		} else {
			// 理论上，这个分支不存在
			return ErrorDesc.success().message("数据删除未处理");
		}
	}

	/**
	 * 更新资产复制记录
	*/
	@ApiOperation(value = "更新资产复制记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.COPY_ID , value = "复制" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.COPY_SOURCE_ASSET_ID , value = "资产" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.COPY_TARGET_ASSET_ID , value = "资产" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true,ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { AssetCopyRecordVOMeta.PAGE_INDEX , AssetCopyRecordVOMeta.PAGE_SIZE , AssetCopyRecordVOMeta.SEARCH_FIELD , AssetCopyRecordVOMeta.FUZZY_FIELD , AssetCopyRecordVOMeta.SEARCH_VALUE , AssetCopyRecordVOMeta.DIRTY_FIELDS , AssetCopyRecordVOMeta.SORT_FIELD , AssetCopyRecordVOMeta.SORT_TYPE , AssetCopyRecordVOMeta.IDS } )
	@SentinelResource(value = AssetCopyRecordServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyRecordServiceProxy.UPDATE)
	public Result update(AssetCopyRecordVO assetCopyRecordVO) {
		Result result=assetCopyRecordService.update(assetCopyRecordVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存资产复制记录
	*/
	@ApiOperation(value = "保存资产复制记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.COPY_ID , value = "复制" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.COPY_SOURCE_ASSET_ID , value = "资产" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.COPY_TARGET_ASSET_ID , value = "资产" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true,ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { AssetCopyRecordVOMeta.PAGE_INDEX , AssetCopyRecordVOMeta.PAGE_SIZE , AssetCopyRecordVOMeta.SEARCH_FIELD , AssetCopyRecordVOMeta.FUZZY_FIELD , AssetCopyRecordVOMeta.SEARCH_VALUE , AssetCopyRecordVOMeta.DIRTY_FIELDS , AssetCopyRecordVOMeta.SORT_FIELD , AssetCopyRecordVOMeta.SORT_TYPE , AssetCopyRecordVOMeta.IDS } )
	@SentinelResource(value = AssetCopyRecordServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyRecordServiceProxy.SAVE)
	public Result save(AssetCopyRecordVO assetCopyRecordVO) {
		Result result=assetCopyRecordService.save(assetCopyRecordVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取资产复制记录
	*/
	@ApiOperation(value = "获取资产复制记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AssetCopyRecordServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyRecordServiceProxy.GET_BY_ID)
	public Result<AssetCopyRecord> getById(String id) {
		Result<AssetCopyRecord> result=new Result<>();
		AssetCopyRecord assetCopyRecord=assetCopyRecordService.getById(id);
		result.success(true).data(assetCopyRecord);
		return result;
	}


	/**
	 * 批量获取资产复制记录 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取资产复制记录")
		@ApiImplicitParams({
				@ApiImplicitParam(name = AssetCopyRecordVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = AssetCopyRecordServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyRecordServiceProxy.GET_BY_IDS)
	public Result<List<AssetCopyRecord>> getByIds(List<String> ids) {
		Result<List<AssetCopyRecord>> result=new Result<>();
		List<AssetCopyRecord> list=assetCopyRecordService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询资产复制记录
	*/
	@ApiOperation(value = "查询资产复制记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.COPY_ID , value = "复制" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.COPY_SOURCE_ASSET_ID , value = "资产" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.COPY_TARGET_ASSET_ID , value = "资产" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { AssetCopyRecordVOMeta.PAGE_INDEX , AssetCopyRecordVOMeta.PAGE_SIZE } )
	@SentinelResource(value = AssetCopyRecordServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyRecordServiceProxy.QUERY_LIST)
	public Result<List<AssetCopyRecord>> queryList(AssetCopyRecordVO sample) {
		Result<List<AssetCopyRecord>> result=new Result<>();
		List<AssetCopyRecord> list=assetCopyRecordService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询资产复制记录
	*/
	@ApiOperation(value = "分页查询资产复制记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.COPY_ID , value = "复制" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.COPY_SOURCE_ASSET_ID , value = "资产" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetCopyRecordVOMeta.COPY_TARGET_ASSET_ID , value = "资产" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AssetCopyRecordServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetCopyRecordServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<AssetCopyRecord>> queryPagedList(AssetCopyRecordVO sample) {
		Result<PagedList<AssetCopyRecord>> result=new Result<>();
		PagedList<AssetCopyRecord> list=assetCopyRecordService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}






}