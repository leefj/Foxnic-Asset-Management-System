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
import com.dt.platform.proxy.ops.MonitorNodeSubtypeServiceProxy;
import com.dt.platform.domain.ops.meta.MonitorNodeSubtypeVOMeta;
import com.dt.platform.domain.ops.MonitorNodeSubtype;
import com.dt.platform.domain.ops.MonitorNodeSubtypeVO;
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
import com.dt.platform.domain.ops.meta.MonitorNodeSubtypeMeta;
import com.dt.platform.domain.ops.MonitorNodeType;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IMonitorNodeSubtypeService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ??????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-02-08 13:14:48
 */
@Api(tags = "???????????????")
@ApiSort(0)
@RestController("OpsMonitorNodeSubtypeController")
public class MonitorNodeSubtypeController extends SuperController {

    @Autowired
    private IMonitorNodeSubtypeService monitorNodeSubtypeService;

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "os"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "RedHat"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "Redhat"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MonitorNodeSubtypeServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeSubtypeServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MonitorNodeSubtypeVO monitorNodeSubtypeVO) {
        Result result = monitorNodeSubtypeService.insert(monitorNodeSubtypeVO, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MonitorNodeSubtypeServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeSubtypeServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = monitorNodeSubtypeService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorNodeSubtypeServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeSubtypeServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = monitorNodeSubtypeService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "os"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "RedHat"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "Redhat"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MonitorNodeSubtypeVOMeta.PAGE_INDEX, MonitorNodeSubtypeVOMeta.PAGE_SIZE, MonitorNodeSubtypeVOMeta.SEARCH_FIELD, MonitorNodeSubtypeVOMeta.FUZZY_FIELD, MonitorNodeSubtypeVOMeta.SEARCH_VALUE, MonitorNodeSubtypeVOMeta.DIRTY_FIELDS, MonitorNodeSubtypeVOMeta.SORT_FIELD, MonitorNodeSubtypeVOMeta.SORT_TYPE, MonitorNodeSubtypeVOMeta.IDS })
    @SentinelResource(value = MonitorNodeSubtypeServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeSubtypeServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MonitorNodeSubtypeVO monitorNodeSubtypeVO) {
        Result result = monitorNodeSubtypeService.update(monitorNodeSubtypeVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "os"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "RedHat"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "Redhat"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorNodeSubtypeVOMeta.PAGE_INDEX, MonitorNodeSubtypeVOMeta.PAGE_SIZE, MonitorNodeSubtypeVOMeta.SEARCH_FIELD, MonitorNodeSubtypeVOMeta.FUZZY_FIELD, MonitorNodeSubtypeVOMeta.SEARCH_VALUE, MonitorNodeSubtypeVOMeta.DIRTY_FIELDS, MonitorNodeSubtypeVOMeta.SORT_FIELD, MonitorNodeSubtypeVOMeta.SORT_TYPE, MonitorNodeSubtypeVOMeta.IDS })
    @SentinelResource(value = MonitorNodeSubtypeServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeSubtypeServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MonitorNodeSubtypeVO monitorNodeSubtypeVO) {
        Result result = monitorNodeSubtypeService.save(monitorNodeSubtypeVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MonitorNodeSubtypeServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeSubtypeServiceProxy.GET_BY_ID)
    public Result<MonitorNodeSubtype> getById(String id) {
        Result<MonitorNodeSubtype> result = new Result<>();
        MonitorNodeSubtype monitorNodeSubtype = monitorNodeSubtypeService.getById(id);
        // join ???????????????
        monitorNodeSubtypeService.dao().fill(monitorNodeSubtype).with(MonitorNodeSubtypeMeta.MONITOR_NODE_TYPE).execute();
        result.success(true).data(monitorNodeSubtype);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorNodeSubtypeServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeSubtypeServiceProxy.GET_BY_IDS)
    public Result<List<MonitorNodeSubtype>> getByIds(List<String> ids) {
        Result<List<MonitorNodeSubtype>> result = new Result<>();
        List<MonitorNodeSubtype> list = monitorNodeSubtypeService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "os"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "RedHat"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "Redhat"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorNodeSubtypeVOMeta.PAGE_INDEX, MonitorNodeSubtypeVOMeta.PAGE_SIZE })
    @SentinelResource(value = MonitorNodeSubtypeServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeSubtypeServiceProxy.QUERY_LIST)
    public Result<List<MonitorNodeSubtype>> queryList(MonitorNodeSubtypeVO sample) {
        Result<List<MonitorNodeSubtype>> result = new Result<>();
        List<MonitorNodeSubtype> list = monitorNodeSubtypeService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "os"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "RedHat"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "Redhat"),
		@ApiImplicitParam(name = MonitorNodeSubtypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MonitorNodeSubtypeServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeSubtypeServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MonitorNodeSubtype>> queryPagedList(MonitorNodeSubtypeVO sample) {
        Result<PagedList<MonitorNodeSubtype>> result = new Result<>();
        PagedList<MonitorNodeSubtype> list = monitorNodeSubtypeService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        monitorNodeSubtypeService.dao().fill(list).with(MonitorNodeSubtypeMeta.MONITOR_NODE_TYPE).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = MonitorNodeSubtypeServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeSubtypeServiceProxy.EXPORT_EXCEL)
    public void exportExcel(MonitorNodeSubtypeVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorNodeSubtypeService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = MonitorNodeSubtypeServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeSubtypeServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorNodeSubtypeService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = MonitorNodeSubtypeServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeSubtypeServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = monitorNodeSubtypeService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
