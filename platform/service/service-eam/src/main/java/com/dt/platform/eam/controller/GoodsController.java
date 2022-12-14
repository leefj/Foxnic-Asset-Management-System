package com.dt.platform.eam.controller;

import java.util.List;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.dao.entity.ReferCause;
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
import com.dt.platform.proxy.eam.GoodsServiceProxy;
import com.dt.platform.domain.eam.meta.GoodsVOMeta;
import com.dt.platform.domain.eam.Goods;
import com.dt.platform.domain.eam.GoodsVO;
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
import com.dt.platform.domain.eam.meta.GoodsMeta;
import java.math.BigDecimal;
import com.dt.platform.domain.eam.Category;
import com.dt.platform.domain.eam.Manufacturer;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IGoodsService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-10-26 15:27:56
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamGoodsController")
public class GoodsController extends SuperController {

    @Autowired
    private IGoodsService goodsService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "474282035079282688"),
		@ApiImplicitParam(name = GoodsVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "474275619551576064"),
		@ApiImplicitParam(name = GoodsVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.MODEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.MANUFACTURER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.UNIT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.REFERENCE_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsVOMeta.PICTURE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = GoodsServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(GoodsVO goodsVO) {
        Result result = goodsService.insert(goodsVO);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "474282035079282688")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = GoodsServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = goodsService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = GoodsServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = goodsService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "474282035079282688"),
		@ApiImplicitParam(name = GoodsVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "474275619551576064"),
		@ApiImplicitParam(name = GoodsVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.MODEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.MANUFACTURER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.UNIT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.REFERENCE_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsVOMeta.PICTURE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { GoodsVOMeta.PAGE_INDEX, GoodsVOMeta.PAGE_SIZE, GoodsVOMeta.SEARCH_FIELD, GoodsVOMeta.FUZZY_FIELD, GoodsVOMeta.SEARCH_VALUE, GoodsVOMeta.SORT_FIELD, GoodsVOMeta.SORT_TYPE, GoodsVOMeta.IDS })
    @SentinelResource(value = GoodsServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(GoodsVO goodsVO) {
        Result result = goodsService.update(goodsVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "474282035079282688"),
		@ApiImplicitParam(name = GoodsVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "474275619551576064"),
		@ApiImplicitParam(name = GoodsVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.MODEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.MANUFACTURER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.UNIT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.REFERENCE_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsVOMeta.PICTURE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { GoodsVOMeta.PAGE_INDEX, GoodsVOMeta.PAGE_SIZE, GoodsVOMeta.SEARCH_FIELD, GoodsVOMeta.FUZZY_FIELD, GoodsVOMeta.SEARCH_VALUE, GoodsVOMeta.SORT_FIELD, GoodsVOMeta.SORT_TYPE, GoodsVOMeta.IDS })
    @SentinelResource(value = GoodsServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(GoodsVO goodsVO) {
        Result result = goodsService.save(goodsVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = GoodsServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsServiceProxy.GET_BY_ID)
    public Result<Goods> getById(String id) {
        Result<Goods> result = new Result<>();
        Goods goods = goodsService.getById(id);
        // join ???????????????
        goodsService.dao().fill(goods).with(GoodsMeta.CATEGORY).with(GoodsMeta.MANUFACTURER).execute();
        result.success(true).data(goods);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = GoodsServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsServiceProxy.GET_BY_IDS)
    public Result<List<Goods>> getByIds(List<String> ids) {
        Result<List<Goods>> result = new Result<>();
        List<Goods> list = goodsService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "474282035079282688"),
		@ApiImplicitParam(name = GoodsVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "474275619551576064"),
		@ApiImplicitParam(name = GoodsVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.MODEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.MANUFACTURER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.UNIT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.REFERENCE_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsVOMeta.PICTURE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { GoodsVOMeta.PAGE_INDEX, GoodsVOMeta.PAGE_SIZE })
    @SentinelResource(value = GoodsServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsServiceProxy.QUERY_LIST)
    public Result<List<Goods>> queryList(GoodsVO sample) {
        Result<List<Goods>> result = new Result<>();
        List<Goods> list = goodsService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "474282035079282688"),
		@ApiImplicitParam(name = GoodsVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "474275619551576064"),
		@ApiImplicitParam(name = GoodsVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.MODEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.MANUFACTURER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.UNIT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.REFERENCE_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsVOMeta.PICTURE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = GoodsServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<Goods>> queryPagedList(GoodsVO sample) {
        Result<PagedList<Goods>> result = new Result<>();
        PagedList<Goods> list = goodsService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        goodsService.dao().fill(list).with(GoodsMeta.CATEGORY).with(GoodsMeta.MANUFACTURER).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = GoodsServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(GoodsServiceProxy.EXPORT_EXCEL)
    public void exportExcel(GoodsVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = goodsService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = GoodsServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(GoodsServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = goodsService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    @SentinelResource(value = GoodsServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(GoodsServiceProxy.IMPORT_EXCEL)
    public Result importExcel(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
        // ?????????????????????
        Map<String, MultipartFile> map = request.getFileMap();
        InputStream input = null;
        for (MultipartFile mf : map.values()) {
            input = StreamUtil.bytes2input(mf.getBytes());
            break;
        }
        if (input == null) {
            return ErrorDesc.failure().message("?????????????????????");
        }
        List<ValidateResult> errors = goodsService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
