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
import com.dt.platform.proxy.eam.InventoryManagerServiceProxy;
import com.dt.platform.domain.eam.meta.InventoryManagerVOMeta;
import com.dt.platform.domain.eam.InventoryManager;
import com.dt.platform.domain.eam.InventoryManagerVO;
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
import com.dt.platform.domain.eam.meta.InventoryManagerMeta;
import org.github.foxnic.web.domain.hrm.Employee;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IInventoryManagerService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ??????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-11-19 21:37:25
 */
@Api(tags = "???????????????")
@ApiSort(0)
@RestController("EamInventoryManagerController")
public class InventoryManagerController extends SuperController {

    @Autowired
    private IInventoryManagerService inventoryManagerService;

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryManagerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryManagerVOMeta.INVENTORY_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryManagerVOMeta.USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryManagerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = InventoryManagerServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryManagerServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(InventoryManagerVO inventoryManagerVO) {
        Result result = inventoryManagerService.insert(inventoryManagerVO, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryManagerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = InventoryManagerServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryManagerServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = inventoryManagerService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryManagerVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = InventoryManagerServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryManagerServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = inventoryManagerService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryManagerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryManagerVOMeta.INVENTORY_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryManagerVOMeta.USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryManagerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { InventoryManagerVOMeta.PAGE_INDEX, InventoryManagerVOMeta.PAGE_SIZE, InventoryManagerVOMeta.SEARCH_FIELD, InventoryManagerVOMeta.FUZZY_FIELD, InventoryManagerVOMeta.SEARCH_VALUE, InventoryManagerVOMeta.DIRTY_FIELDS, InventoryManagerVOMeta.SORT_FIELD, InventoryManagerVOMeta.SORT_TYPE, InventoryManagerVOMeta.IDS })
    @SentinelResource(value = InventoryManagerServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryManagerServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(InventoryManagerVO inventoryManagerVO) {
        Result result = inventoryManagerService.update(inventoryManagerVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryManagerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryManagerVOMeta.INVENTORY_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryManagerVOMeta.USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryManagerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { InventoryManagerVOMeta.PAGE_INDEX, InventoryManagerVOMeta.PAGE_SIZE, InventoryManagerVOMeta.SEARCH_FIELD, InventoryManagerVOMeta.FUZZY_FIELD, InventoryManagerVOMeta.SEARCH_VALUE, InventoryManagerVOMeta.DIRTY_FIELDS, InventoryManagerVOMeta.SORT_FIELD, InventoryManagerVOMeta.SORT_TYPE, InventoryManagerVOMeta.IDS })
    @SentinelResource(value = InventoryManagerServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryManagerServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(InventoryManagerVO inventoryManagerVO) {
        Result result = inventoryManagerService.save(inventoryManagerVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryManagerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = InventoryManagerServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryManagerServiceProxy.GET_BY_ID)
    public Result<InventoryManager> getById(String id) {
        Result<InventoryManager> result = new Result<>();
        InventoryManager inventoryManager = inventoryManagerService.getById(id);
        result.success(true).data(inventoryManager);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryManagerVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = InventoryManagerServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryManagerServiceProxy.GET_BY_IDS)
    public Result<List<InventoryManager>> getByIds(List<String> ids) {
        Result<List<InventoryManager>> result = new Result<>();
        List<InventoryManager> list = inventoryManagerService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryManagerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryManagerVOMeta.INVENTORY_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryManagerVOMeta.USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryManagerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { InventoryManagerVOMeta.PAGE_INDEX, InventoryManagerVOMeta.PAGE_SIZE })
    @SentinelResource(value = InventoryManagerServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryManagerServiceProxy.QUERY_LIST)
    public Result<List<InventoryManager>> queryList(InventoryManagerVO sample) {
        Result<List<InventoryManager>> result = new Result<>();
        List<InventoryManager> list = inventoryManagerService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryManagerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryManagerVOMeta.INVENTORY_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryManagerVOMeta.USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryManagerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = InventoryManagerServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryManagerServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<InventoryManager>> queryPagedList(InventoryManagerVO sample) {
        Result<PagedList<InventoryManager>> result = new Result<>();
        PagedList<InventoryManager> list = inventoryManagerService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = InventoryManagerServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InventoryManagerServiceProxy.EXPORT_EXCEL)
    public void exportExcel(InventoryManagerVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = inventoryManagerService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = InventoryManagerServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InventoryManagerServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = inventoryManagerService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = InventoryManagerServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InventoryManagerServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = inventoryManagerService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
