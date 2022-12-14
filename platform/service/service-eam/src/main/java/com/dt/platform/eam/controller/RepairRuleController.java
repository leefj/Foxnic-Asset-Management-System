package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.RepairOrder;
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
import com.dt.platform.proxy.eam.RepairRuleServiceProxy;
import com.dt.platform.domain.eam.meta.RepairRuleVOMeta;
import com.dt.platform.domain.eam.RepairRule;
import com.dt.platform.domain.eam.RepairRuleVO;
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
import com.dt.platform.domain.eam.meta.RepairRuleMeta;
import org.github.foxnic.web.domain.hrm.Employee;
import com.dt.platform.domain.eam.RepairGroup;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IRepairRuleService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-06-02 09:22:52
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamRepairRuleController")
public class RepairRuleController extends SuperController {

    @Autowired
    private IRepairRuleService repairRuleService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairRuleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584673109706014720"),
		@ApiImplicitParam(name = RepairRuleVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairRuleVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = RepairRuleVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = RepairRuleVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "583634707950862336"),
		@ApiImplicitParam(name = RepairRuleVOMeta.USER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = RepairRuleVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = RepairRuleVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = RepairRuleServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairRuleServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(RepairRuleVO repairRuleVO) {
        Result result = repairRuleService.insert(repairRuleVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairRuleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584673109706014720")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = RepairRuleServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairRuleServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = repairRuleService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairRuleVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = RepairRuleServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairRuleServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = repairRuleService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairRuleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584673109706014720"),
		@ApiImplicitParam(name = RepairRuleVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairRuleVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = RepairRuleVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = RepairRuleVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "583634707950862336"),
		@ApiImplicitParam(name = RepairRuleVOMeta.USER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = RepairRuleVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = RepairRuleVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { RepairRuleVOMeta.PAGE_INDEX, RepairRuleVOMeta.PAGE_SIZE, RepairRuleVOMeta.SEARCH_FIELD, RepairRuleVOMeta.FUZZY_FIELD, RepairRuleVOMeta.SEARCH_VALUE, RepairRuleVOMeta.DIRTY_FIELDS, RepairRuleVOMeta.SORT_FIELD, RepairRuleVOMeta.SORT_TYPE, RepairRuleVOMeta.IDS })
    @SentinelResource(value = RepairRuleServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairRuleServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(RepairRuleVO repairRuleVO) {
        Result result = repairRuleService.update(repairRuleVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairRuleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584673109706014720"),
		@ApiImplicitParam(name = RepairRuleVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairRuleVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = RepairRuleVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = RepairRuleVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "583634707950862336"),
		@ApiImplicitParam(name = RepairRuleVOMeta.USER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = RepairRuleVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = RepairRuleVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { RepairRuleVOMeta.PAGE_INDEX, RepairRuleVOMeta.PAGE_SIZE, RepairRuleVOMeta.SEARCH_FIELD, RepairRuleVOMeta.FUZZY_FIELD, RepairRuleVOMeta.SEARCH_VALUE, RepairRuleVOMeta.DIRTY_FIELDS, RepairRuleVOMeta.SORT_FIELD, RepairRuleVOMeta.SORT_TYPE, RepairRuleVOMeta.IDS })
    @SentinelResource(value = RepairRuleServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairRuleServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(RepairRuleVO repairRuleVO) {
        Result result = repairRuleService.save(repairRuleVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairRuleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = RepairRuleServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairRuleServiceProxy.GET_BY_ID)
    public Result<RepairRule> getById(String id) {
        Result<RepairRule> result = new Result<>();
        RepairRule repairRule = repairRuleService.getById(id);
        // join ???????????????
        repairRuleService.dao().fill(repairRule).with(RepairRuleMeta.REPAIR_GROUP).with(RepairRuleMeta.USER).execute();
        repairRuleService.dao().join(repairRule.getUser(), Person.class);
        result.success(true).data(repairRule);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairRuleVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = RepairRuleServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairRuleServiceProxy.GET_BY_IDS)
    public Result<List<RepairRule>> getByIds(List<String> ids) {
        Result<List<RepairRule>> result = new Result<>();
        List<RepairRule> list = repairRuleService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairRuleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584673109706014720"),
		@ApiImplicitParam(name = RepairRuleVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairRuleVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = RepairRuleVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = RepairRuleVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "583634707950862336"),
		@ApiImplicitParam(name = RepairRuleVOMeta.USER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = RepairRuleVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = RepairRuleVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { RepairRuleVOMeta.PAGE_INDEX, RepairRuleVOMeta.PAGE_SIZE })
    @SentinelResource(value = RepairRuleServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairRuleServiceProxy.QUERY_LIST)
    public Result<List<RepairRule>> queryList(RepairRuleVO sample) {
        Result<List<RepairRule>> result = new Result<>();
        List<RepairRule> list = repairRuleService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairRuleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584673109706014720"),
		@ApiImplicitParam(name = RepairRuleVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairRuleVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = RepairRuleVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = RepairRuleVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "583634707950862336"),
		@ApiImplicitParam(name = RepairRuleVOMeta.USER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = RepairRuleVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = RepairRuleVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = RepairRuleServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairRuleServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<RepairRule>> queryPagedList(RepairRuleVO sample) {
        Result<PagedList<RepairRule>> result = new Result<>();
        PagedList<RepairRule> list = repairRuleService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        repairRuleService.dao().fill(list).with(RepairRuleMeta.REPAIR_GROUP).with(RepairRuleMeta.USER).execute();
        List<Employee> userList = CollectorUtil.collectList(list.getList(), RepairRule::getUser);
        repairRuleService.dao().join(userList, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = RepairRuleServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairRuleServiceProxy.EXPORT_EXCEL)
    public void exportExcel(RepairRuleVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = repairRuleService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = RepairRuleServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairRuleServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = repairRuleService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = RepairRuleServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairRuleServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = repairRuleService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
