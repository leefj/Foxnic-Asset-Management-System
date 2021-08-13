package com.dt.platform.eam.controller;

 
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


import com.dt.platform.proxy.eam.AssetServiceProxy;
import com.dt.platform.domain.eam.meta.AssetVOMeta;
import com.dt.platform.domain.eam.Asset;
import com.dt.platform.domain.eam.AssetVO;
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
import com.dt.platform.domain.eam.meta.AssetMeta;
import com.dt.platform.domain.eam.AssetExtFinancial;
import com.dt.platform.domain.eam.AssetExtMaintainer;
import com.dt.platform.domain.eam.AssetExtEquipment;
import com.dt.platform.domain.eam.Goods;
import com.dt.platform.domain.eam.Category;
import com.dt.platform.domain.eam.Maintainer;
import com.dt.platform.domain.eam.Manufacturer;
import com.dt.platform.domain.eam.Brand;
import com.dt.platform.domain.eam.Area;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 资产表 接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2021-08-13 13:57:17
*/

@Api(tags = "资产")
@ApiSort(0)
@RestController("EamAssetController")
public class AssetController extends SuperController {

	@Autowired
	private IAssetService assetService;

	
	/**
	 * 添加资产
	*/
	@ApiOperation(value = "添加资产")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.BUSI_CODE , value = "单据编号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.BATCH_CODE , value = "批次代码" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.STATUS , value = "资产状态" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_CODE , value = "资产编号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_ID , value = "分类ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_ID , value = "标准型号ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.NAME , value = "标准型号资产名称" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.MANUFACTURER_ID , value = "标准型号厂商" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.BRAND_ID , value = "标准型号品牌" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.MODEL , value = "标准型号规格型号" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.PICTURE_ID , value = "标准型号物品图片" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.UNIT , value = "标准型号计量单位" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.RFID , value = "资产RFID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.SN , value = "资产序列号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NUMBER , value = "资产数量" , required = true , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_ID , value = "来源" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_DETAIL , value = "来源详情" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.AREA_ID , value = "存放区域" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.PLACE_DETAIL , value = "存放地点" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.USER_ID , value = "使用人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.PRODUCTION_DATE , value = "生产日期" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetVOMeta.STORAGE_TIME , value = "入库时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetVOMeta.DISPLAY , value = "是否显示" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.SCRAP , value = "是否报废" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.INSERT_TYPE , value = "插入方式" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSETS_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=1)
	@NotNull(name = AssetVOMeta.ID)
	@NotNull(name = AssetVOMeta.BATCH_CODE)
	@NotNull(name = AssetVOMeta.MODEL)
	@NotNull(name = AssetVOMeta.ASSET_NUMBER)
	@NotNull(name = AssetVOMeta.SCRAP)
	@SentinelResource(value = AssetServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetServiceProxy.INSERT)
	public Result insert(AssetVO assetVO) {
		Result result=assetService.insert(assetVO);
		return result;
	}

	
	/**
	 * 删除资产
	*/
	@ApiOperation(value = "删除资产")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2)
	@NotNull(name = AssetVOMeta.ID)
	@SentinelResource(value = AssetServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetServiceProxy.DELETE)
	public Result deleteById(String id) {
		Result result=assetService.deleteByIdLogical(id);
		return result;
	}
	
	
	/**
	 * 批量删除资产 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除资产")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3) 
	@NotNull(name = AssetVOMeta.IDS)
	@SentinelResource(value = AssetServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {
		Result result=assetService.deleteByIdsLogical(ids);
		return result;
	}
	
	/**
	 * 更新资产
	*/
	@ApiOperation(value = "更新资产")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.BUSI_CODE , value = "单据编号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.BATCH_CODE , value = "批次代码" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.STATUS , value = "资产状态" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_CODE , value = "资产编号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_ID , value = "分类ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_ID , value = "标准型号ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.NAME , value = "标准型号资产名称" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.MANUFACTURER_ID , value = "标准型号厂商" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.BRAND_ID , value = "标准型号品牌" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.MODEL , value = "标准型号规格型号" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.PICTURE_ID , value = "标准型号物品图片" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.UNIT , value = "标准型号计量单位" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.RFID , value = "资产RFID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.SN , value = "资产序列号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NUMBER , value = "资产数量" , required = true , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_ID , value = "来源" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_DETAIL , value = "来源详情" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.AREA_ID , value = "存放区域" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.PLACE_DETAIL , value = "存放地点" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.USER_ID , value = "使用人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.PRODUCTION_DATE , value = "生产日期" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetVOMeta.STORAGE_TIME , value = "入库时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetVOMeta.DISPLAY , value = "是否显示" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.SCRAP , value = "是否报废" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.INSERT_TYPE , value = "插入方式" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSETS_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport( order=4 , ignoreParameters = { AssetVOMeta.PAGE_INDEX , AssetVOMeta.PAGE_SIZE , AssetVOMeta.SEARCH_FIELD , AssetVOMeta.FUZZY_FIELD , AssetVOMeta.SEARCH_VALUE , AssetVOMeta.SORT_FIELD , AssetVOMeta.SORT_TYPE , AssetVOMeta.IDS } ) 
	@NotNull(name = AssetVOMeta.ID)
	@NotNull(name = AssetVOMeta.BATCH_CODE)
	@NotNull(name = AssetVOMeta.MODEL)
	@NotNull(name = AssetVOMeta.ASSET_NUMBER)
	@NotNull(name = AssetVOMeta.SCRAP)
	@SentinelResource(value = AssetServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetServiceProxy.UPDATE)
	public Result update(AssetVO assetVO) {
		Result result=assetService.update(assetVO,SaveMode.NOT_NULL_FIELDS);
		return result;
	}
	
	
	/**
	 * 保存资产
	*/
	@ApiOperation(value = "保存资产")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.BUSI_CODE , value = "单据编号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.BATCH_CODE , value = "批次代码" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.STATUS , value = "资产状态" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_CODE , value = "资产编号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_ID , value = "分类ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_ID , value = "标准型号ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.NAME , value = "标准型号资产名称" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.MANUFACTURER_ID , value = "标准型号厂商" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.BRAND_ID , value = "标准型号品牌" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.MODEL , value = "标准型号规格型号" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.PICTURE_ID , value = "标准型号物品图片" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.UNIT , value = "标准型号计量单位" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.RFID , value = "资产RFID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.SN , value = "资产序列号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NUMBER , value = "资产数量" , required = true , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_ID , value = "来源" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_DETAIL , value = "来源详情" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.AREA_ID , value = "存放区域" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.PLACE_DETAIL , value = "存放地点" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.USER_ID , value = "使用人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.PRODUCTION_DATE , value = "生产日期" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetVOMeta.STORAGE_TIME , value = "入库时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetVOMeta.DISPLAY , value = "是否显示" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.SCRAP , value = "是否报废" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.INSERT_TYPE , value = "插入方式" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSETS_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 ,  ignoreParameters = { AssetVOMeta.PAGE_INDEX , AssetVOMeta.PAGE_SIZE , AssetVOMeta.SEARCH_FIELD , AssetVOMeta.FUZZY_FIELD , AssetVOMeta.SEARCH_VALUE , AssetVOMeta.SORT_FIELD , AssetVOMeta.SORT_TYPE , AssetVOMeta.IDS } )
	@NotNull(name = AssetVOMeta.ID)
	@NotNull(name = AssetVOMeta.BATCH_CODE)
	@NotNull(name = AssetVOMeta.MODEL)
	@NotNull(name = AssetVOMeta.ASSET_NUMBER)
	@NotNull(name = AssetVOMeta.SCRAP)
	@SentinelResource(value = AssetServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetServiceProxy.SAVE)
	public Result save(AssetVO assetVO) {
		Result result=assetService.save(assetVO,SaveMode.NOT_NULL_FIELDS);
		return result;
	}

	
	/**
	 * 获取资产
	*/
	@ApiOperation(value = "获取资产")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6)
	@NotNull(name = AssetVOMeta.ID)
	@SentinelResource(value = AssetServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetServiceProxy.GET_BY_ID)
	public Result<Asset> getById(String id) {
		Result<Asset> result=new Result<>();
		Asset asset=assetService.getById(id);
		// 关联出 分类 数据
		assetService.join(asset,AssetMeta.CATEGORY);
		// 关联出 物品档案 数据
		assetService.join(asset,AssetMeta.GOODS);
		// 关联出 厂商 数据
		assetService.join(asset,AssetMeta.MANUFACTURER);
		// 关联出 品牌 数据
		assetService.join(asset,AssetMeta.BRAND);
		// 关联出 区域 数据
		assetService.join(asset,AssetMeta.AREA);
		result.success(true).data(asset);
		return result;
	}


	/**
	 * 批量删除资产 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量删除资产")
		@ApiImplicitParams({
				@ApiImplicitParam(name = AssetVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3) 
		@NotNull(name = AssetVOMeta.IDS)
		@SentinelResource(value = AssetServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetServiceProxy.GET_BY_IDS)
	public Result<List<Asset>> getByIds(List<String> ids) {
		Result<List<Asset>> result=new Result<>();
		List<Asset> list=assetService.getByIds(ids);
		result.success(true).data(list);
		return result;
	}

	
	/**
	 * 查询资产
	*/
	@ApiOperation(value = "查询资产")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.BUSI_CODE , value = "单据编号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.BATCH_CODE , value = "批次代码" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.STATUS , value = "资产状态" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_CODE , value = "资产编号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_ID , value = "分类ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_ID , value = "标准型号ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.NAME , value = "标准型号资产名称" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.MANUFACTURER_ID , value = "标准型号厂商" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.BRAND_ID , value = "标准型号品牌" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.MODEL , value = "标准型号规格型号" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.PICTURE_ID , value = "标准型号物品图片" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.UNIT , value = "标准型号计量单位" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.RFID , value = "资产RFID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.SN , value = "资产序列号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NUMBER , value = "资产数量" , required = true , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_ID , value = "来源" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_DETAIL , value = "来源详情" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.AREA_ID , value = "存放区域" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.PLACE_DETAIL , value = "存放地点" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.USER_ID , value = "使用人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.PRODUCTION_DATE , value = "生产日期" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetVOMeta.STORAGE_TIME , value = "入库时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetVOMeta.DISPLAY , value = "是否显示" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.SCRAP , value = "是否报废" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.INSERT_TYPE , value = "插入方式" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSETS_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 ,  ignoreParameters = { AssetVOMeta.PAGE_INDEX , AssetVOMeta.PAGE_SIZE } )
	@SentinelResource(value = AssetServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetServiceProxy.QUERY_LIST)
	public Result<List<Asset>> queryList(AssetVO sample) {
		Result<List<Asset>> result=new Result<>();
		List<Asset> list=assetService.queryList(sample);
		result.success(true).data(list);
		return result;
	}

	
	/**
	 * 分页查询资产
	*/
	@ApiOperation(value = "分页查询资产")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.BUSI_CODE , value = "单据编号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.BATCH_CODE , value = "批次代码" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.STATUS , value = "资产状态" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_CODE , value = "资产编号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_ID , value = "分类ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_ID , value = "标准型号ID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.NAME , value = "标准型号资产名称" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.MANUFACTURER_ID , value = "标准型号厂商" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.BRAND_ID , value = "标准型号品牌" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.MODEL , value = "标准型号规格型号" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.PICTURE_ID , value = "标准型号物品图片" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.UNIT , value = "标准型号计量单位" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.RFID , value = "资产RFID" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.SN , value = "资产序列号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NUMBER , value = "资产数量" , required = true , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_ID , value = "来源" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_DETAIL , value = "来源详情" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.AREA_ID , value = "存放区域" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.PLACE_DETAIL , value = "存放地点" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.USER_ID , value = "使用人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.PRODUCTION_DATE , value = "生产日期" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetVOMeta.STORAGE_TIME , value = "入库时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AssetVOMeta.DISPLAY , value = "是否显示" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.SCRAP , value = "是否报废" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.INSERT_TYPE , value = "插入方式" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSETS_NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8)
	@SentinelResource(value = AssetServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssetServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<Asset>> queryPagedList(AssetVO sample) {
		Result<PagedList<Asset>> result=new Result<>();
		PagedList<Asset> list=assetService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		// 关联出 分类 数据
		assetService.join(list,AssetMeta.CATEGORY);
		// 关联出 物品档案 数据
		assetService.join(list,AssetMeta.GOODS);
		// 关联出 厂商 数据
		assetService.join(list,AssetMeta.MANUFACTURER);
		// 关联出 品牌 数据
		assetService.join(list,AssetMeta.BRAND);
		// 关联出 区域 数据
		assetService.join(list,AssetMeta.AREA);
		result.success(true).data(list);
		return result;
	}



	/**
	 * 导出 Excel
	 * */
	@SentinelResource(value = AssetServiceProxy.EXPORT_EXCEL , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@RequestMapping(AssetServiceProxy.EXPORT_EXCEL)
	public void exportExcel(AssetVO  sample,HttpServletResponse response) throws Exception {
			//生成 Excel 数据
			ExcelWriter ew=assetService.exportExcel(sample);
			//下载
			DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
	}


	/**
	 * 导出 Excel 模板
	 * */
	@SentinelResource(value = AssetServiceProxy.EXPORT_EXCEL_TEMPLATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@RequestMapping(AssetServiceProxy.EXPORT_EXCEL_TEMPLATE)
	public void exportExcelTemplate(HttpServletResponse response) throws Exception {
			//生成 Excel 模版
			ExcelWriter ew=assetService.exportExcelTemplate();
			//下载
			DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
		}




	@SentinelResource(value = AssetServiceProxy.IMPORT_EXCEL , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@RequestMapping(AssetServiceProxy.IMPORT_EXCEL)
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

			List<ValidateResult> errors=assetService.importExcel(input,0,true);
			if(errors==null || errors.isEmpty()) {
				return ErrorDesc.success();
			} else {
				return ErrorDesc.failure().message("导入失败").data(errors);
			}
		}


}