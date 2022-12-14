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
import com.dt.platform.proxy.ops.MonitorNodeTplItemServiceProxy;
import com.dt.platform.domain.ops.meta.MonitorNodeTplItemVOMeta;
import com.dt.platform.domain.ops.MonitorNodeTplItem;
import com.dt.platform.domain.ops.MonitorNodeTplItemVO;
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
import com.dt.platform.domain.ops.meta.MonitorNodeTplItemMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IMonitorNodeTplItemService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-02-08 13:14:55
 */
@Api(tags = "??????????????????")
@ApiSort(0)
@RestController("OpsMonitorNodeTplItemController")
public class MonitorNodeTplItemController extends SuperController {

    @Autowired
    private IMonitorNodeTplItemService monitorNodeTplItemService;

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.TPL_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MonitorNodeTplItemServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeTplItemServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MonitorNodeTplItemVO monitorNodeTplItemVO) {
        Result result = monitorNodeTplItemService.insert(monitorNodeTplItemVO, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MonitorNodeTplItemServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeTplItemServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = monitorNodeTplItemService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorNodeTplItemServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeTplItemServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = monitorNodeTplItemService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.TPL_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MonitorNodeTplItemVOMeta.PAGE_INDEX, MonitorNodeTplItemVOMeta.PAGE_SIZE, MonitorNodeTplItemVOMeta.SEARCH_FIELD, MonitorNodeTplItemVOMeta.FUZZY_FIELD, MonitorNodeTplItemVOMeta.SEARCH_VALUE, MonitorNodeTplItemVOMeta.DIRTY_FIELDS, MonitorNodeTplItemVOMeta.SORT_FIELD, MonitorNodeTplItemVOMeta.SORT_TYPE, MonitorNodeTplItemVOMeta.IDS })
    @SentinelResource(value = MonitorNodeTplItemServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeTplItemServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MonitorNodeTplItemVO monitorNodeTplItemVO) {
        Result result = monitorNodeTplItemService.update(monitorNodeTplItemVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.TPL_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorNodeTplItemVOMeta.PAGE_INDEX, MonitorNodeTplItemVOMeta.PAGE_SIZE, MonitorNodeTplItemVOMeta.SEARCH_FIELD, MonitorNodeTplItemVOMeta.FUZZY_FIELD, MonitorNodeTplItemVOMeta.SEARCH_VALUE, MonitorNodeTplItemVOMeta.DIRTY_FIELDS, MonitorNodeTplItemVOMeta.SORT_FIELD, MonitorNodeTplItemVOMeta.SORT_TYPE, MonitorNodeTplItemVOMeta.IDS })
    @SentinelResource(value = MonitorNodeTplItemServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeTplItemServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MonitorNodeTplItemVO monitorNodeTplItemVO) {
        Result result = monitorNodeTplItemService.save(monitorNodeTplItemVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MonitorNodeTplItemServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeTplItemServiceProxy.GET_BY_ID)
    public Result<MonitorNodeTplItem> getById(String id) {
        Result<MonitorNodeTplItem> result = new Result<>();
        MonitorNodeTplItem monitorNodeTplItem = monitorNodeTplItemService.getById(id);
        result.success(true).data(monitorNodeTplItem);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorNodeTplItemServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeTplItemServiceProxy.GET_BY_IDS)
    public Result<List<MonitorNodeTplItem>> getByIds(List<String> ids) {
        Result<List<MonitorNodeTplItem>> result = new Result<>();
        List<MonitorNodeTplItem> list = monitorNodeTplItemService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.TPL_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorNodeTplItemVOMeta.PAGE_INDEX, MonitorNodeTplItemVOMeta.PAGE_SIZE })
    @SentinelResource(value = MonitorNodeTplItemServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeTplItemServiceProxy.QUERY_LIST)
    public Result<List<MonitorNodeTplItem>> queryList(MonitorNodeTplItemVO sample) {
        Result<List<MonitorNodeTplItem>> result = new Result<>();
        List<MonitorNodeTplItem> list = monitorNodeTplItemService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeTplItemVOMeta.TPL_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MonitorNodeTplItemServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeTplItemServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MonitorNodeTplItem>> queryPagedList(MonitorNodeTplItemVO sample) {
        Result<PagedList<MonitorNodeTplItem>> result = new Result<>();
        PagedList<MonitorNodeTplItem> list = monitorNodeTplItemService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = MonitorNodeTplItemServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeTplItemServiceProxy.EXPORT_EXCEL)
    public void exportExcel(MonitorNodeTplItemVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorNodeTplItemService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = MonitorNodeTplItemServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeTplItemServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorNodeTplItemService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = MonitorNodeTplItemServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeTplItemServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = monitorNodeTplItemService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
