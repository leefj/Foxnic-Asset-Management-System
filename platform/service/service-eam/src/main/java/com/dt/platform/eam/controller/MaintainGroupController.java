package com.dt.platform.eam.controller;

import java.util.ArrayList;
import java.util.List;
import com.dt.platform.domain.eam.InspectionGroup;
import com.dt.platform.domain.eam.meta.InspectionGroupMeta;
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
import com.dt.platform.proxy.eam.MaintainGroupServiceProxy;
import com.dt.platform.domain.eam.meta.MaintainGroupVOMeta;
import com.dt.platform.domain.eam.MaintainGroup;
import com.dt.platform.domain.eam.MaintainGroupVO;
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
import com.dt.platform.domain.eam.meta.MaintainGroupMeta;
import org.github.foxnic.web.domain.hrm.Employee;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IMaintainGroupService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-06-02 19:14:19
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamMaintainGroupController")
public class MaintainGroupController extends SuperController {

    @Autowired
    private IMaintainGroupService maintainGroupService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584133248423034880"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.LEADER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MaintainGroupServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainGroupServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MaintainGroupVO maintainGroupVO) {
        Result result = maintainGroupService.insert(maintainGroupVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584133248423034880")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MaintainGroupServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainGroupServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = maintainGroupService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainGroupVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MaintainGroupServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainGroupServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = maintainGroupService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584133248423034880"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.LEADER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MaintainGroupVOMeta.PAGE_INDEX, MaintainGroupVOMeta.PAGE_SIZE, MaintainGroupVOMeta.SEARCH_FIELD, MaintainGroupVOMeta.FUZZY_FIELD, MaintainGroupVOMeta.SEARCH_VALUE, MaintainGroupVOMeta.DIRTY_FIELDS, MaintainGroupVOMeta.SORT_FIELD, MaintainGroupVOMeta.SORT_TYPE, MaintainGroupVOMeta.IDS })
    @SentinelResource(value = MaintainGroupServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainGroupServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MaintainGroupVO maintainGroupVO) {
        Result result = maintainGroupService.update(maintainGroupVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584133248423034880"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.LEADER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MaintainGroupVOMeta.PAGE_INDEX, MaintainGroupVOMeta.PAGE_SIZE, MaintainGroupVOMeta.SEARCH_FIELD, MaintainGroupVOMeta.FUZZY_FIELD, MaintainGroupVOMeta.SEARCH_VALUE, MaintainGroupVOMeta.DIRTY_FIELDS, MaintainGroupVOMeta.SORT_FIELD, MaintainGroupVOMeta.SORT_TYPE, MaintainGroupVOMeta.IDS })
    @SentinelResource(value = MaintainGroupServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainGroupServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MaintainGroupVO maintainGroupVO) {
        Result result = maintainGroupService.save(maintainGroupVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MaintainGroupServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainGroupServiceProxy.GET_BY_ID)
    public Result<MaintainGroup> getById(String id) {
        Result<MaintainGroup> result = new Result<>();
        MaintainGroup maintainGroup = maintainGroupService.getById(id);
        // join ???????????????
        maintainGroupService.dao().fill(maintainGroup).with("leader").with(InspectionGroupMeta.MEMBER_LIST).execute();
        maintainGroupService.dao().join(maintainGroup.getLeader(), Person.class);
        maintainGroupService.dao().join(maintainGroup.getMemberList(), Person.class);
        result.success(true).data(maintainGroup);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainGroupVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MaintainGroupServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainGroupServiceProxy.GET_BY_IDS)
    public Result<List<MaintainGroup>> getByIds(List<String> ids) {
        Result<List<MaintainGroup>> result = new Result<>();
        List<MaintainGroup> list = maintainGroupService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584133248423034880"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.LEADER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MaintainGroupVOMeta.PAGE_INDEX, MaintainGroupVOMeta.PAGE_SIZE })
    @SentinelResource(value = MaintainGroupServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainGroupServiceProxy.QUERY_LIST)
    public Result<List<MaintainGroup>> queryList(MaintainGroupVO sample) {
        Result<List<MaintainGroup>> result = new Result<>();
        List<MaintainGroup> list = maintainGroupService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584133248423034880"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.LEADER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = MaintainGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MaintainGroupServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainGroupServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MaintainGroup>> queryPagedList(MaintainGroupVO sample) {
        Result<PagedList<MaintainGroup>> result = new Result<>();
        PagedList<MaintainGroup> list = maintainGroupService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        maintainGroupService.dao().fill(list).with("leader").with(InspectionGroupMeta.MEMBER_LIST).execute();
        List<Employee> leader = CollectorUtil.collectList(list.getList(), MaintainGroup::getLeader);
        maintainGroupService.dao().join(leader, Person.class);
        List<List<Employee>> memberList = CollectorUtil.collectList(list.getList(), MaintainGroup::getMemberList);
        List<Employee> member = memberList.stream().collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
        maintainGroupService.dao().join(member, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = MaintainGroupServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MaintainGroupServiceProxy.EXPORT_EXCEL)
    public void exportExcel(MaintainGroupVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = maintainGroupService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = MaintainGroupServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MaintainGroupServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = maintainGroupService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = MaintainGroupServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MaintainGroupServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = maintainGroupService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
