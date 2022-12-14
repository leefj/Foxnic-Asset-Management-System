package com.dt.platform.eam.controller;

import java.util.List;
import com.github.foxnic.commons.collection.CollectorUtil;
import org.github.foxnic.web.domain.hrm.Person;
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
import com.dt.platform.proxy.eam.RepairOrderActServiceProxy;
import com.dt.platform.domain.eam.meta.RepairOrderActVOMeta;
import com.dt.platform.domain.eam.RepairOrderAct;
import com.dt.platform.domain.eam.RepairOrderActVO;
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
import com.dt.platform.domain.eam.meta.RepairOrderActMeta;
import java.math.BigDecimal;
import com.dt.platform.domain.eam.RepairOrder;
import org.github.foxnic.web.domain.hrm.Employee;
import com.dt.platform.domain.eam.RepairGroup;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IRepairOrderActService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-05-31 21:56:15
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamRepairOrderActController")
public class RepairOrderActController extends SuperController {

    @Autowired
    private IRepairOrderActService repairOrderActService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderActVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584022872884772864"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.ORDER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "583634707950862336"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.EXECUTOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "576130520052662272"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.REPAIR_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.START_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.FINISH_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = RepairOrderActServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderActServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(RepairOrderActVO repairOrderActVO) {
        Result result = repairOrderActService.insert(repairOrderActVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderActVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584022872884772864")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = RepairOrderActServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderActServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = repairOrderActService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderActVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = RepairOrderActServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderActServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = repairOrderActService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderActVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584022872884772864"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.ORDER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "583634707950862336"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.EXECUTOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "576130520052662272"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.REPAIR_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.START_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.FINISH_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { RepairOrderActVOMeta.PAGE_INDEX, RepairOrderActVOMeta.PAGE_SIZE, RepairOrderActVOMeta.SEARCH_FIELD, RepairOrderActVOMeta.FUZZY_FIELD, RepairOrderActVOMeta.SEARCH_VALUE, RepairOrderActVOMeta.DIRTY_FIELDS, RepairOrderActVOMeta.SORT_FIELD, RepairOrderActVOMeta.SORT_TYPE, RepairOrderActVOMeta.IDS })
    @SentinelResource(value = RepairOrderActServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderActServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(RepairOrderActVO repairOrderActVO) {
        Result result = repairOrderActService.update(repairOrderActVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderActVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584022872884772864"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.ORDER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "583634707950862336"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.EXECUTOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "576130520052662272"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.REPAIR_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.START_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.FINISH_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { RepairOrderActVOMeta.PAGE_INDEX, RepairOrderActVOMeta.PAGE_SIZE, RepairOrderActVOMeta.SEARCH_FIELD, RepairOrderActVOMeta.FUZZY_FIELD, RepairOrderActVOMeta.SEARCH_VALUE, RepairOrderActVOMeta.DIRTY_FIELDS, RepairOrderActVOMeta.SORT_FIELD, RepairOrderActVOMeta.SORT_TYPE, RepairOrderActVOMeta.IDS })
    @SentinelResource(value = RepairOrderActServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderActServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(RepairOrderActVO repairOrderActVO) {
        Result result = repairOrderActService.save(repairOrderActVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderActVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = RepairOrderActServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderActServiceProxy.GET_BY_ID)
    public Result<RepairOrderAct> getById(String id) {
        Result<RepairOrderAct> result = new Result<>();
        RepairOrderAct repairOrderAct = repairOrderActService.getById(id);
        // join ???????????????
        repairOrderActService.dao().fill(repairOrderAct).with(RepairOrderActMeta.REPAIR_GROUP).with(RepairOrderActMeta.EXECUTOR).with(RepairOrderActMeta.ORDER).execute();
        repairOrderActService.dao().join(repairOrderAct.getExecutor(), Person.class);
        repairOrderActService.dao().join(repairOrderAct.getOriginator(), Person.class);
        result.success(true).data(repairOrderAct);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderActVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = RepairOrderActServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderActServiceProxy.GET_BY_IDS)
    public Result<List<RepairOrderAct>> getByIds(List<String> ids) {
        Result<List<RepairOrderAct>> result = new Result<>();
        List<RepairOrderAct> list = repairOrderActService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderActVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584022872884772864"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.ORDER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "583634707950862336"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.EXECUTOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "576130520052662272"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.REPAIR_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.START_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.FINISH_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { RepairOrderActVOMeta.PAGE_INDEX, RepairOrderActVOMeta.PAGE_SIZE })
    @SentinelResource(value = RepairOrderActServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderActServiceProxy.QUERY_LIST)
    public Result<List<RepairOrderAct>> queryList(RepairOrderActVO sample) {
        Result<List<RepairOrderAct>> result = new Result<>();
        List<RepairOrderAct> list = repairOrderActService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderActVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584022872884772864"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.ORDER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "583634707950862336"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.EXECUTOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "576130520052662272"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.REPAIR_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = RepairOrderActVOMeta.START_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.FINISH_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderActVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = RepairOrderActServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderActServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<RepairOrderAct>> queryPagedList(RepairOrderActVO sample) {
        Result<PagedList<RepairOrderAct>> result = new Result<>();
        PagedList<RepairOrderAct> list = repairOrderActService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        repairOrderActService.dao().fill(list).with(RepairOrderActMeta.REPAIR_GROUP).with(RepairOrderActMeta.EXECUTOR).with(RepairOrderActMeta.ORDER).execute();
        List<Employee> originator = CollectorUtil.collectList(list.getList(), RepairOrderAct::getOriginator);
        repairOrderActService.dao().join(originator, Person.class);
        List<Employee> executor = CollectorUtil.collectList(list.getList(), RepairOrderAct::getExecutor);
        repairOrderActService.dao().join(executor, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     */
    @ApiOperation(value = "")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderActVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584022872884772864")
	})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = RepairOrderActServiceProxy.CANCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderActServiceProxy.CANCEL)
    public Result cancel(String id) {
        Result result = repairOrderActService.cancel(id);
        return result;
    }

    @ApiOperation(value = "")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderActVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584022872884772864")
	})
    @ApiOperationSupport(order = 10)
    @SentinelResource(value = RepairOrderActServiceProxy.START, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderActServiceProxy.START)
    public Result start(String id) {
        Result result = repairOrderActService.start(id);
        return result;
    }

    @ApiOperation(value = "")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderActVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584022872884772864")
	})
    @ApiOperationSupport(order = 11)
    @SentinelResource(value = RepairOrderActServiceProxy.FINISH, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderActServiceProxy.FINISH)
    public Result finish(String id) {
        Result result = repairOrderActService.finish(id);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = RepairOrderActServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairOrderActServiceProxy.EXPORT_EXCEL)
    public void exportExcel(RepairOrderActVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = repairOrderActService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = RepairOrderActServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairOrderActServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = repairOrderActService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = RepairOrderActServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairOrderActServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = repairOrderActService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
