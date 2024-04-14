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


import com.dt.platform.proxy.eam.ProductFunctionServiceProxy;
import com.dt.platform.domain.eam.meta.ProductFunctionVOMeta;
import com.dt.platform.domain.eam.ProductFunction;
import com.dt.platform.domain.eam.ProductFunctionVO;
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
import com.dt.platform.domain.eam.meta.ProductFunctionMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IProductFunctionService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 功能接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2024-04-07 21:12:13
*/

@InDoc
@Api(tags = "功能")
@RestController("IotProductFunctionController")
public class ProductFunctionController extends SuperController {

	@Autowired
	private IProductFunctionService productFunctionService;

	/**
	 * 添加功能
	*/
	@ApiOperation(value = "添加功能")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ProductFunctionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "829461874125832192"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.TYPE , value = "功能类型" , required = false , dataTypeClass=String.class , example = "property"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.PRODUCT_ID , value = "产品" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProductFunctionVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.IDENTIFIER , value = "标识符" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.FUNCTION_DESC , value = "描述" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.FUNCTION_UNIT , value = "单位" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.DATA_TYPE , value = "数据类型" , required = false , dataTypeClass=String.class , example = "int32"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.DATA_VALUE , value = "内容定义" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProductFunctionVOMeta.ACL , value = "读写" , required = false , dataTypeClass=String.class , example = "read_write"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.UPDATE_BY , value = "修改人ID" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = ProductFunctionServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProductFunctionServiceProxy.INSERT)
	public Result insert(ProductFunctionVO productFunctionVO) {
		
		Result result=productFunctionService.insert(productFunctionVO,false);
		return result;
	}



	/**
	 * 删除功能
	*/
	@ApiOperation(value = "删除功能")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ProductFunctionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "829461874125832192")
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = ProductFunctionServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProductFunctionServiceProxy.DELETE)
	public Result deleteById(String id) {
		
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  productFunctionService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=productFunctionService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除功能 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除功能")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ProductFunctionVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = ProductFunctionServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProductFunctionServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {
		
		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = productFunctionService.hasRefers(ids);
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
			Result result=productFunctionService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=productFunctionService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新功能
	*/
	@ApiOperation(value = "更新功能")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ProductFunctionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "829461874125832192"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.TYPE , value = "功能类型" , required = false , dataTypeClass=String.class , example = "property"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.PRODUCT_ID , value = "产品" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProductFunctionVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.IDENTIFIER , value = "标识符" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.FUNCTION_DESC , value = "描述" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.FUNCTION_UNIT , value = "单位" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.DATA_TYPE , value = "数据类型" , required = false , dataTypeClass=String.class , example = "int32"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.DATA_VALUE , value = "内容定义" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProductFunctionVOMeta.ACL , value = "读写" , required = false , dataTypeClass=String.class , example = "read_write"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.UPDATE_BY , value = "修改人ID" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { ProductFunctionVOMeta.PAGE_INDEX , ProductFunctionVOMeta.PAGE_SIZE , ProductFunctionVOMeta.SEARCH_FIELD , ProductFunctionVOMeta.FUZZY_FIELD , ProductFunctionVOMeta.SEARCH_VALUE , ProductFunctionVOMeta.DIRTY_FIELDS , ProductFunctionVOMeta.SORT_FIELD , ProductFunctionVOMeta.SORT_TYPE , ProductFunctionVOMeta.DATA_ORIGIN , ProductFunctionVOMeta.QUERY_LOGIC , ProductFunctionVOMeta.REQUEST_ACTION , ProductFunctionVOMeta.IDS } )
	@SentinelResource(value = ProductFunctionServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProductFunctionServiceProxy.UPDATE)
	public Result update(ProductFunctionVO productFunctionVO) {
		
		Result result=productFunctionService.update(productFunctionVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存功能
	*/
	@ApiOperation(value = "保存功能")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ProductFunctionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "829461874125832192"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.TYPE , value = "功能类型" , required = false , dataTypeClass=String.class , example = "property"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.PRODUCT_ID , value = "产品" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProductFunctionVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.IDENTIFIER , value = "标识符" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.FUNCTION_DESC , value = "描述" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.FUNCTION_UNIT , value = "单位" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.DATA_TYPE , value = "数据类型" , required = false , dataTypeClass=String.class , example = "int32"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.DATA_VALUE , value = "内容定义" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProductFunctionVOMeta.ACL , value = "读写" , required = false , dataTypeClass=String.class , example = "read_write"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.UPDATE_BY , value = "修改人ID" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { ProductFunctionVOMeta.PAGE_INDEX , ProductFunctionVOMeta.PAGE_SIZE , ProductFunctionVOMeta.SEARCH_FIELD , ProductFunctionVOMeta.FUZZY_FIELD , ProductFunctionVOMeta.SEARCH_VALUE , ProductFunctionVOMeta.DIRTY_FIELDS , ProductFunctionVOMeta.SORT_FIELD , ProductFunctionVOMeta.SORT_TYPE , ProductFunctionVOMeta.DATA_ORIGIN , ProductFunctionVOMeta.QUERY_LOGIC , ProductFunctionVOMeta.REQUEST_ACTION , ProductFunctionVOMeta.IDS } )
	@SentinelResource(value = ProductFunctionServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProductFunctionServiceProxy.SAVE)
	public Result save(ProductFunctionVO productFunctionVO) {
		
		Result result=productFunctionService.save(productFunctionVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取功能
	*/
	@ApiOperation(value = "获取功能")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ProductFunctionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = ProductFunctionServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProductFunctionServiceProxy.GET_BY_ID)
	public Result<ProductFunction> getById(String id) {
		
		Result<ProductFunction> result=new Result<>();
		ProductFunction productFunction=productFunctionService.getById(id);
		result.success(true).data(productFunction);
		return result;
	}


	/**
	 * 批量获取功能 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取功能")
		@ApiImplicitParams({
				@ApiImplicitParam(name = ProductFunctionVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = ProductFunctionServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProductFunctionServiceProxy.GET_BY_IDS)
	public Result<List<ProductFunction>> getByIds(List<String> ids) {
		
		Result<List<ProductFunction>> result=new Result<>();
		List<ProductFunction> list=productFunctionService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询功能
	*/
	@ApiOperation(value = "查询功能")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ProductFunctionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "829461874125832192"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.TYPE , value = "功能类型" , required = false , dataTypeClass=String.class , example = "property"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.PRODUCT_ID , value = "产品" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProductFunctionVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.IDENTIFIER , value = "标识符" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.FUNCTION_DESC , value = "描述" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.FUNCTION_UNIT , value = "单位" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.DATA_TYPE , value = "数据类型" , required = false , dataTypeClass=String.class , example = "int32"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.DATA_VALUE , value = "内容定义" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProductFunctionVOMeta.ACL , value = "读写" , required = false , dataTypeClass=String.class , example = "read_write"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.UPDATE_BY , value = "修改人ID" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { ProductFunctionVOMeta.PAGE_INDEX , ProductFunctionVOMeta.PAGE_SIZE } )
	@SentinelResource(value = ProductFunctionServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProductFunctionServiceProxy.QUERY_LIST)
	public Result<List<ProductFunction>> queryList(ProductFunctionVO sample) {
		
		Result<List<ProductFunction>> result=new Result<>();
		List<ProductFunction> list=productFunctionService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询功能
	*/
	@ApiOperation(value = "分页查询功能")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ProductFunctionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "829461874125832192"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.TYPE , value = "功能类型" , required = false , dataTypeClass=String.class , example = "property"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.PRODUCT_ID , value = "产品" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProductFunctionVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.IDENTIFIER , value = "标识符" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.FUNCTION_DESC , value = "描述" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.FUNCTION_UNIT , value = "单位" , required = false , dataTypeClass=String.class , example = "12"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.DATA_TYPE , value = "数据类型" , required = false , dataTypeClass=String.class , example = "int32"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.DATA_VALUE , value = "内容定义" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProductFunctionVOMeta.ACL , value = "读写" , required = false , dataTypeClass=String.class , example = "read_write"),
		@ApiImplicitParam(name = ProductFunctionVOMeta.UPDATE_BY , value = "修改人ID" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = ProductFunctionServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProductFunctionServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<ProductFunction>> queryPagedList(ProductFunctionVO sample) {
		
		Result<PagedList<ProductFunction>> result=new Result<>();
		PagedList<ProductFunction> list=productFunctionService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}





}