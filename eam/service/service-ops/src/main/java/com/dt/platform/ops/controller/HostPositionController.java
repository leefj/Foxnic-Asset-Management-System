package com.dt.platform.ops.controller;


import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.github.foxnic.web.framework.web.SuperController;
import org.github.foxnic.web.framework.sentinel.SentinelExceptionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.alibaba.csp.sentinel.annotation.SentinelResource;


import com.dt.platform.proxy.ops.HostPositionServiceProxy;
import com.dt.platform.domain.ops.meta.HostPositionVOMeta;
import com.dt.platform.domain.ops.HostPosition;
import com.dt.platform.domain.ops.HostPositionVO;
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
import com.dt.platform.domain.ops.meta.HostPositionMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IHostPositionService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 主机位置 接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-06-21 10:03:49
*/

@Api(tags = "主机位置")
@ApiSort(0)
@RestController("OpsHostPositionController")
public class HostPositionController extends SuperController {

	@Autowired
	private IHostPositionService hostPositionService;


	/**
	 * 添加主机位置
	*/
	@ApiOperation(value = "添加主机位置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = HostPositionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "478242968168304640"),
		@ApiImplicitParam(name = HostPositionVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "位置1"),
		@ApiImplicitParam(name = HostPositionVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=1)
	@SentinelResource(value = HostPositionServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(HostPositionServiceProxy.INSERT)
	public Result insert(HostPositionVO hostPositionVO) {
		Result result=hostPositionService.insert(hostPositionVO,false);
		return result;
	}



	/**
	 * 删除主机位置
	*/
	@ApiOperation(value = "删除主机位置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = HostPositionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "478242968168304640")
	})
	@ApiOperationSupport(order=2)
	@NotNull(name = HostPositionVOMeta.ID)
	@SentinelResource(value = HostPositionServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(HostPositionServiceProxy.DELETE)
	public Result deleteById(String id) {
		Result result=hostPositionService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除主机位置 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除主机位置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = HostPositionVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3) 
	@NotNull(name = HostPositionVOMeta.IDS)
	@SentinelResource(value = HostPositionServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(HostPositionServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {
		Result result=hostPositionService.deleteByIdsLogical(ids);
		return result;
	}

	/**
	 * 更新主机位置
	*/
	@ApiOperation(value = "更新主机位置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = HostPositionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "478242968168304640"),
		@ApiImplicitParam(name = HostPositionVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "位置1"),
		@ApiImplicitParam(name = HostPositionVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport( order=4 , ignoreParameters = { HostPositionVOMeta.PAGE_INDEX , HostPositionVOMeta.PAGE_SIZE , HostPositionVOMeta.SEARCH_FIELD , HostPositionVOMeta.FUZZY_FIELD , HostPositionVOMeta.SEARCH_VALUE , HostPositionVOMeta.DIRTY_FIELDS , HostPositionVOMeta.SORT_FIELD , HostPositionVOMeta.SORT_TYPE , HostPositionVOMeta.IDS } )
	@NotNull(name = HostPositionVOMeta.ID)
	@SentinelResource(value = HostPositionServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(HostPositionServiceProxy.UPDATE)
	public Result update(HostPositionVO hostPositionVO) {
		Result result=hostPositionService.update(hostPositionVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存主机位置
	*/
	@ApiOperation(value = "保存主机位置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = HostPositionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "478242968168304640"),
		@ApiImplicitParam(name = HostPositionVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "位置1"),
		@ApiImplicitParam(name = HostPositionVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 ,  ignoreParameters = { HostPositionVOMeta.PAGE_INDEX , HostPositionVOMeta.PAGE_SIZE , HostPositionVOMeta.SEARCH_FIELD , HostPositionVOMeta.FUZZY_FIELD , HostPositionVOMeta.SEARCH_VALUE , HostPositionVOMeta.DIRTY_FIELDS , HostPositionVOMeta.SORT_FIELD , HostPositionVOMeta.SORT_TYPE , HostPositionVOMeta.IDS } )
	@NotNull(name = HostPositionVOMeta.ID)
	@SentinelResource(value = HostPositionServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(HostPositionServiceProxy.SAVE)
	public Result save(HostPositionVO hostPositionVO) {
		Result result=hostPositionService.save(hostPositionVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取主机位置
	*/
	@ApiOperation(value = "获取主机位置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = HostPositionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6)
	@NotNull(name = HostPositionVOMeta.ID)
	@SentinelResource(value = HostPositionServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(HostPositionServiceProxy.GET_BY_ID)
	public Result<HostPosition> getById(String id) {
		Result<HostPosition> result=new Result<>();
		HostPosition hostPosition=hostPositionService.getById(id);
		result.success(true).data(hostPosition);
		return result;
	}


	/**
	 * 批量获取主机位置 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取主机位置")
		@ApiImplicitParams({
				@ApiImplicitParam(name = HostPositionVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3) 
		@NotNull(name = HostPositionVOMeta.IDS)
		@SentinelResource(value = HostPositionServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(HostPositionServiceProxy.GET_BY_IDS)
	public Result<List<HostPosition>> getByIds(List<String> ids) {
		Result<List<HostPosition>> result=new Result<>();
		List<HostPosition> list=hostPositionService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询主机位置
	*/
	@ApiOperation(value = "查询主机位置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = HostPositionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "478242968168304640"),
		@ApiImplicitParam(name = HostPositionVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "位置1"),
		@ApiImplicitParam(name = HostPositionVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 ,  ignoreParameters = { HostPositionVOMeta.PAGE_INDEX , HostPositionVOMeta.PAGE_SIZE } )
	@SentinelResource(value = HostPositionServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(HostPositionServiceProxy.QUERY_LIST)
	public Result<List<HostPosition>> queryList(HostPositionVO sample) {
		Result<List<HostPosition>> result=new Result<>();
		List<HostPosition> list=hostPositionService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询主机位置
	*/
	@ApiOperation(value = "分页查询主机位置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = HostPositionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "478242968168304640"),
		@ApiImplicitParam(name = HostPositionVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "位置1"),
		@ApiImplicitParam(name = HostPositionVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8)
	@SentinelResource(value = HostPositionServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(HostPositionServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<HostPosition>> queryPagedList(HostPositionVO sample) {
		Result<PagedList<HostPosition>> result=new Result<>();
		PagedList<HostPosition> list=hostPositionService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}



	/**
	 * 导出 Excel
	 * */
	@SentinelResource(value = HostPositionServiceProxy.EXPORT_EXCEL , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@RequestMapping(HostPositionServiceProxy.EXPORT_EXCEL)
	public void exportExcel(HostPositionVO  sample,HttpServletResponse response) throws Exception {
		try{
			//生成 Excel 数据
			ExcelWriter ew=hostPositionService.exportExcel(sample);
			//下载
			DownloadUtil.writeToOutput(response,ew.getWorkBook(),ew.getWorkBookName());
		} catch (Exception e) {
			DownloadUtil.writeDownloadError(response,e);
		}
	}


	/**
	 * 导出 Excel 模板
	 * */
	@SentinelResource(value = HostPositionServiceProxy.EXPORT_EXCEL_TEMPLATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@RequestMapping(HostPositionServiceProxy.EXPORT_EXCEL_TEMPLATE)
	public void exportExcelTemplate(HttpServletResponse response) throws Exception {
		try{
			//生成 Excel 模版
			ExcelWriter ew=hostPositionService.exportExcelTemplate();
			//下载
			DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
		} catch (Exception e) {
			DownloadUtil.writeDownloadError(response,e);
		}
	}



	@SentinelResource(value = HostPositionServiceProxy.IMPORT_EXCEL , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@RequestMapping(HostPositionServiceProxy.IMPORT_EXCEL)
	public Result importExcel(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {

		//获得上传的文件
		Map<String, MultipartFile> map = request.getFileMap();
		InputStream input=null;
		for (MultipartFile mf : map.values()) {
			input=StreamUtil.bytes2input(mf.getBytes());
			break;
		}

		if(input==null) {
			return ErrorDesc.failure().message("缺少上传的文件");
		}

		List<ValidateResult> errors=hostPositionService.importExcel(input,0,true);
		if(errors==null || errors.isEmpty()) {
			return ErrorDesc.success();
		} else {
			return ErrorDesc.failure().message("导入失败").data(errors);
		}
	}


}