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
import com.dt.platform.proxy.ops.MonitorNodeDbServiceProxy;
import com.dt.platform.domain.ops.meta.MonitorNodeDbVOMeta;
import com.dt.platform.domain.ops.MonitorNodeDb;
import com.dt.platform.domain.ops.MonitorNodeDbVO;
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
import com.dt.platform.domain.ops.meta.MonitorNodeDbMeta;
import java.math.BigDecimal;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IMonitorNodeDbService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ??????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-02-08 13:14:41
 */
@Api(tags = "???????????????")
@ApiSort(0)
@RestController("OpsMonitorNodeDbController")
public class MonitorNodeDbController extends SuperController {

    @Autowired
    private IMonitorNodeDbService monitorNodeDbService;

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_NAME, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_INSTANCE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_VERION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.CONNECT_COUNT, value = "?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_SIZE, value = "???????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MonitorNodeDbServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeDbServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MonitorNodeDbVO monitorNodeDbVO) {
        Result result = monitorNodeDbService.insert(monitorNodeDbVO, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MonitorNodeDbServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeDbServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = monitorNodeDbService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorNodeDbServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeDbServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = monitorNodeDbService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_NAME, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_INSTANCE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_VERION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.CONNECT_COUNT, value = "?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_SIZE, value = "???????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MonitorNodeDbVOMeta.PAGE_INDEX, MonitorNodeDbVOMeta.PAGE_SIZE, MonitorNodeDbVOMeta.SEARCH_FIELD, MonitorNodeDbVOMeta.FUZZY_FIELD, MonitorNodeDbVOMeta.SEARCH_VALUE, MonitorNodeDbVOMeta.DIRTY_FIELDS, MonitorNodeDbVOMeta.SORT_FIELD, MonitorNodeDbVOMeta.SORT_TYPE, MonitorNodeDbVOMeta.IDS })
    @SentinelResource(value = MonitorNodeDbServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeDbServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MonitorNodeDbVO monitorNodeDbVO) {
        Result result = monitorNodeDbService.update(monitorNodeDbVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_NAME, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_INSTANCE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_VERION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.CONNECT_COUNT, value = "?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_SIZE, value = "???????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorNodeDbVOMeta.PAGE_INDEX, MonitorNodeDbVOMeta.PAGE_SIZE, MonitorNodeDbVOMeta.SEARCH_FIELD, MonitorNodeDbVOMeta.FUZZY_FIELD, MonitorNodeDbVOMeta.SEARCH_VALUE, MonitorNodeDbVOMeta.DIRTY_FIELDS, MonitorNodeDbVOMeta.SORT_FIELD, MonitorNodeDbVOMeta.SORT_TYPE, MonitorNodeDbVOMeta.IDS })
    @SentinelResource(value = MonitorNodeDbServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeDbServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MonitorNodeDbVO monitorNodeDbVO) {
        Result result = monitorNodeDbService.save(monitorNodeDbVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MonitorNodeDbServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeDbServiceProxy.GET_BY_ID)
    public Result<MonitorNodeDb> getById(String id) {
        Result<MonitorNodeDb> result = new Result<>();
        MonitorNodeDb monitorNodeDb = monitorNodeDbService.getById(id);
        result.success(true).data(monitorNodeDb);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorNodeDbServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeDbServiceProxy.GET_BY_IDS)
    public Result<List<MonitorNodeDb>> getByIds(List<String> ids) {
        Result<List<MonitorNodeDb>> result = new Result<>();
        List<MonitorNodeDb> list = monitorNodeDbService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_NAME, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_INSTANCE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_VERION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.CONNECT_COUNT, value = "?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_SIZE, value = "???????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorNodeDbVOMeta.PAGE_INDEX, MonitorNodeDbVOMeta.PAGE_SIZE })
    @SentinelResource(value = MonitorNodeDbServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeDbServiceProxy.QUERY_LIST)
    public Result<List<MonitorNodeDb>> queryList(MonitorNodeDbVO sample) {
        Result<List<MonitorNodeDb>> result = new Result<>();
        List<MonitorNodeDb> list = monitorNodeDbService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_NAME, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_INSTANCE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_VERION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.CONNECT_COUNT, value = "?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.DB_SIZE, value = "???????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeDbVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MonitorNodeDbServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeDbServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MonitorNodeDb>> queryPagedList(MonitorNodeDbVO sample) {
        Result<PagedList<MonitorNodeDb>> result = new Result<>();
        PagedList<MonitorNodeDb> list = monitorNodeDbService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = MonitorNodeDbServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeDbServiceProxy.EXPORT_EXCEL)
    public void exportExcel(MonitorNodeDbVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorNodeDbService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = MonitorNodeDbServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeDbServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorNodeDbService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = MonitorNodeDbServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeDbServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = monitorNodeDbService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
