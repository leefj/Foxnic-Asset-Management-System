package com.dt.platform.ops.controller;

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
import com.dt.platform.proxy.ops.MonitorTplGraphItemServiceProxy;
import com.dt.platform.domain.ops.meta.MonitorTplGraphItemVOMeta;
import com.dt.platform.domain.ops.MonitorTplGraphItem;
import com.dt.platform.domain.ops.MonitorTplGraphItemVO;
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
import com.dt.platform.domain.ops.meta.MonitorTplGraphItemMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IMonitorTplGraphItemService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-02-13 20:47:04
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsMonitorTplGraphItemController")
public class MonitorTplGraphItemController extends SuperController {

    @Autowired
    private IMonitorTplGraphItemService monitorTplGraphItemService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.GRAPH_ID, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.cpu"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "CPU?????????"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.ROUTE, value = "??????", required = false, dataTypeClass = String.class, example = "cpu_used")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MonitorTplGraphItemServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphItemServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MonitorTplGraphItemVO monitorTplGraphItemVO) {
        Result result = monitorTplGraphItemService.insert(monitorTplGraphItemVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MonitorTplGraphItemServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphItemServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = monitorTplGraphItemService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorTplGraphItemServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphItemServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = monitorTplGraphItemService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.GRAPH_ID, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.cpu"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "CPU?????????"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.ROUTE, value = "??????", required = false, dataTypeClass = String.class, example = "cpu_used")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MonitorTplGraphItemVOMeta.PAGE_INDEX, MonitorTplGraphItemVOMeta.PAGE_SIZE, MonitorTplGraphItemVOMeta.SEARCH_FIELD, MonitorTplGraphItemVOMeta.FUZZY_FIELD, MonitorTplGraphItemVOMeta.SEARCH_VALUE, MonitorTplGraphItemVOMeta.DIRTY_FIELDS, MonitorTplGraphItemVOMeta.SORT_FIELD, MonitorTplGraphItemVOMeta.SORT_TYPE, MonitorTplGraphItemVOMeta.IDS })
    @SentinelResource(value = MonitorTplGraphItemServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphItemServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MonitorTplGraphItemVO monitorTplGraphItemVO) {
        Result result = monitorTplGraphItemService.update(monitorTplGraphItemVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.GRAPH_ID, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.cpu"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "CPU?????????"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.ROUTE, value = "??????", required = false, dataTypeClass = String.class, example = "cpu_used")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorTplGraphItemVOMeta.PAGE_INDEX, MonitorTplGraphItemVOMeta.PAGE_SIZE, MonitorTplGraphItemVOMeta.SEARCH_FIELD, MonitorTplGraphItemVOMeta.FUZZY_FIELD, MonitorTplGraphItemVOMeta.SEARCH_VALUE, MonitorTplGraphItemVOMeta.DIRTY_FIELDS, MonitorTplGraphItemVOMeta.SORT_FIELD, MonitorTplGraphItemVOMeta.SORT_TYPE, MonitorTplGraphItemVOMeta.IDS })
    @SentinelResource(value = MonitorTplGraphItemServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphItemServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MonitorTplGraphItemVO monitorTplGraphItemVO) {
        Result result = monitorTplGraphItemService.save(monitorTplGraphItemVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MonitorTplGraphItemServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphItemServiceProxy.GET_BY_ID)
    public Result<MonitorTplGraphItem> getById(String id) {
        Result<MonitorTplGraphItem> result = new Result<>();
        MonitorTplGraphItem monitorTplGraphItem = monitorTplGraphItemService.getById(id);
        result.success(true).data(monitorTplGraphItem);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorTplGraphItemServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphItemServiceProxy.GET_BY_IDS)
    public Result<List<MonitorTplGraphItem>> getByIds(List<String> ids) {
        Result<List<MonitorTplGraphItem>> result = new Result<>();
        List<MonitorTplGraphItem> list = monitorTplGraphItemService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.GRAPH_ID, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.cpu"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "CPU?????????"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.ROUTE, value = "??????", required = false, dataTypeClass = String.class, example = "cpu_used")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorTplGraphItemVOMeta.PAGE_INDEX, MonitorTplGraphItemVOMeta.PAGE_SIZE })
    @SentinelResource(value = MonitorTplGraphItemServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphItemServiceProxy.QUERY_LIST)
    public Result<List<MonitorTplGraphItem>> queryList(MonitorTplGraphItemVO sample) {
        Result<List<MonitorTplGraphItem>> result = new Result<>();
        List<MonitorTplGraphItem> list = monitorTplGraphItemService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.GRAPH_ID, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.cpu"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "CPU?????????"),
		@ApiImplicitParam(name = MonitorTplGraphItemVOMeta.ROUTE, value = "??????", required = false, dataTypeClass = String.class, example = "cpu_used")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MonitorTplGraphItemServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphItemServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MonitorTplGraphItem>> queryPagedList(MonitorTplGraphItemVO sample) {
        Result<PagedList<MonitorTplGraphItem>> result = new Result<>();
        PagedList<MonitorTplGraphItem> list = monitorTplGraphItemService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = MonitorTplGraphItemServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorTplGraphItemServiceProxy.EXPORT_EXCEL)
    public void exportExcel(MonitorTplGraphItemVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorTplGraphItemService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = MonitorTplGraphItemServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorTplGraphItemServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorTplGraphItemService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = MonitorTplGraphItemServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorTplGraphItemServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = monitorTplGraphItemService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
