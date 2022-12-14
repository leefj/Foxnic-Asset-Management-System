package com.dt.platform.vehicle.controller;

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
import com.dt.platform.proxy.vehicle.ASelectItemServiceProxy;
import com.dt.platform.domain.vehicle.meta.ASelectItemVOMeta;
import com.dt.platform.domain.vehicle.ASelectItem;
import com.dt.platform.domain.vehicle.ASelectItemVO;
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
import com.dt.platform.domain.vehicle.meta.ASelectItemMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.vehicle.service.IASelectItemService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-04-02 19:42:21
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("VehicleASelectItemController")
public class ASelectItemController extends SuperController {

    @Autowired
    private IASelectItemService aSelectItemService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ASelectItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ASelectItemVOMeta.HANDLE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ASelectItemVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = ASelectItemServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ASelectItemServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(ASelectItemVO aSelectItemVO) {
        Result result = aSelectItemService.insert(aSelectItemVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ASelectItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = ASelectItemServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ASelectItemServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = aSelectItemService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ASelectItemVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ASelectItemServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ASelectItemServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = aSelectItemService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ASelectItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ASelectItemVOMeta.HANDLE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ASelectItemVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { ASelectItemVOMeta.PAGE_INDEX, ASelectItemVOMeta.PAGE_SIZE, ASelectItemVOMeta.SEARCH_FIELD, ASelectItemVOMeta.FUZZY_FIELD, ASelectItemVOMeta.SEARCH_VALUE, ASelectItemVOMeta.DIRTY_FIELDS, ASelectItemVOMeta.SORT_FIELD, ASelectItemVOMeta.SORT_TYPE, ASelectItemVOMeta.IDS })
    @SentinelResource(value = ASelectItemServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ASelectItemServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(ASelectItemVO aSelectItemVO) {
        Result result = aSelectItemService.update(aSelectItemVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ASelectItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ASelectItemVOMeta.HANDLE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ASelectItemVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ASelectItemVOMeta.PAGE_INDEX, ASelectItemVOMeta.PAGE_SIZE, ASelectItemVOMeta.SEARCH_FIELD, ASelectItemVOMeta.FUZZY_FIELD, ASelectItemVOMeta.SEARCH_VALUE, ASelectItemVOMeta.DIRTY_FIELDS, ASelectItemVOMeta.SORT_FIELD, ASelectItemVOMeta.SORT_TYPE, ASelectItemVOMeta.IDS })
    @SentinelResource(value = ASelectItemServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ASelectItemServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(ASelectItemVO aSelectItemVO) {
        Result result = aSelectItemService.save(aSelectItemVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ASelectItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = ASelectItemServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ASelectItemServiceProxy.GET_BY_ID)
    public Result<ASelectItem> getById(String id) {
        Result<ASelectItem> result = new Result<>();
        ASelectItem aSelectItem = aSelectItemService.getById(id);
        result.success(true).data(aSelectItem);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ASelectItemVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ASelectItemServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ASelectItemServiceProxy.GET_BY_IDS)
    public Result<List<ASelectItem>> getByIds(List<String> ids) {
        Result<List<ASelectItem>> result = new Result<>();
        List<ASelectItem> list = aSelectItemService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ASelectItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ASelectItemVOMeta.HANDLE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ASelectItemVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ASelectItemVOMeta.PAGE_INDEX, ASelectItemVOMeta.PAGE_SIZE })
    @SentinelResource(value = ASelectItemServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ASelectItemServiceProxy.QUERY_LIST)
    public Result<List<ASelectItem>> queryList(ASelectItemVO sample) {
        Result<List<ASelectItem>> result = new Result<>();
        List<ASelectItem> list = aSelectItemService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ASelectItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ASelectItemVOMeta.HANDLE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ASelectItemVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = ASelectItemServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ASelectItemServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<ASelectItem>> queryPagedList(ASelectItemVO sample) {
        Result<PagedList<ASelectItem>> result = new Result<>();
        PagedList<ASelectItem> list = aSelectItemService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = ASelectItemServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(ASelectItemServiceProxy.EXPORT_EXCEL)
    public void exportExcel(ASelectItemVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = aSelectItemService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = ASelectItemServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(ASelectItemServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = aSelectItemService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = ASelectItemServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(ASelectItemServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = aSelectItemService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
