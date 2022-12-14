package com.dt.platform.ops.controller;

import java.util.List;
import java.util.ArrayList;
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
import com.dt.platform.proxy.ops.AutoBatchServiceProxy;
import com.dt.platform.domain.ops.meta.AutoBatchVOMeta;
import com.dt.platform.domain.ops.AutoBatch;
import com.dt.platform.domain.ops.AutoBatchVO;
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
import com.dt.platform.domain.ops.meta.AutoBatchMeta;
import com.dt.platform.domain.ops.AutoNode;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IAutoBatchService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-08-22 14:26:33
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsAutoBatchController")
public class AutoBatchController extends SuperController {

    @Autowired
    private IAutoBatchService autoBatchService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoBatchVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "614051736079826944"),
		@ApiImplicitParam(name = AutoBatchVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????2"),
		@ApiImplicitParam(name = AutoBatchVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AutoBatchVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AutoBatchVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AutoBatchServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoBatchServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AutoBatchVO autoBatchVO) {
        Result result = autoBatchService.insert(autoBatchVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoBatchVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "614051736079826944")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AutoBatchServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoBatchServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  autoBatchService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = autoBatchService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoBatchVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AutoBatchServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoBatchServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = autoBatchService.hasRefers(ids);
        // ?????????????????????ID???
        List<String> canDeleteIds = new ArrayList<>();
        for (Map.Entry<String, ReferCause> e : causeMap.entrySet()) {
            if (!e.getValue().hasRefer()) {
                canDeleteIds.add(e.getKey());
            }
        }
        // ????????????
        if (canDeleteIds.isEmpty()) {
            // ?????????????????????????????????
            return ErrorDesc.failure().message("????????????????????????????????????").data(0)
				.addErrors(CollectorUtil.collectArray(CollectorUtil.filter(causeMap.values(),(e)->{return e.hasRefer();}),ReferCause::message,String.class))
				.messageLevel4Confirm();
        } else if (canDeleteIds.size() == ids.size()) {
            // ????????????????????????
            Result result = autoBatchService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = autoBatchService.deleteByIdsLogical(canDeleteIds);
            if (result.failure()) {
                return result;
            } else {
                return ErrorDesc.success().message("????????? " + canDeleteIds.size() + " ??????????????? " + (ids.size() - canDeleteIds.size()) + " ?????????????????????").data(canDeleteIds.size())
					.addErrors(CollectorUtil.collectArray(CollectorUtil.filter(causeMap.values(),(e)->{return e.hasRefer();}),ReferCause::message,String.class))
					.messageLevel4Confirm();
            }
        } else {
            // ?????????????????????????????????
            return ErrorDesc.success().message("?????????????????????");
        }
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoBatchVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "614051736079826944"),
		@ApiImplicitParam(name = AutoBatchVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????2"),
		@ApiImplicitParam(name = AutoBatchVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AutoBatchVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AutoBatchVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AutoBatchVOMeta.PAGE_INDEX, AutoBatchVOMeta.PAGE_SIZE, AutoBatchVOMeta.SEARCH_FIELD, AutoBatchVOMeta.FUZZY_FIELD, AutoBatchVOMeta.SEARCH_VALUE, AutoBatchVOMeta.DIRTY_FIELDS, AutoBatchVOMeta.SORT_FIELD, AutoBatchVOMeta.SORT_TYPE, AutoBatchVOMeta.IDS })
    @SentinelResource(value = AutoBatchServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoBatchServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AutoBatchVO autoBatchVO) {
        Result result = autoBatchService.update(autoBatchVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoBatchVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "614051736079826944"),
		@ApiImplicitParam(name = AutoBatchVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????2"),
		@ApiImplicitParam(name = AutoBatchVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AutoBatchVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AutoBatchVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AutoBatchVOMeta.PAGE_INDEX, AutoBatchVOMeta.PAGE_SIZE, AutoBatchVOMeta.SEARCH_FIELD, AutoBatchVOMeta.FUZZY_FIELD, AutoBatchVOMeta.SEARCH_VALUE, AutoBatchVOMeta.DIRTY_FIELDS, AutoBatchVOMeta.SORT_FIELD, AutoBatchVOMeta.SORT_TYPE, AutoBatchVOMeta.IDS })
    @SentinelResource(value = AutoBatchServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoBatchServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AutoBatchVO autoBatchVO) {
        Result result = autoBatchService.save(autoBatchVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoBatchVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AutoBatchServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoBatchServiceProxy.GET_BY_ID)
    public Result<AutoBatch> getById(String id) {
        Result<AutoBatch> result = new Result<>();
        AutoBatch autoBatch = autoBatchService.getById(id);
        result.success(true).data(autoBatch);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoBatchVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AutoBatchServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoBatchServiceProxy.GET_BY_IDS)
    public Result<List<AutoBatch>> getByIds(List<String> ids) {
        Result<List<AutoBatch>> result = new Result<>();
        List<AutoBatch> list = autoBatchService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoBatchVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "614051736079826944"),
		@ApiImplicitParam(name = AutoBatchVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????2"),
		@ApiImplicitParam(name = AutoBatchVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AutoBatchVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AutoBatchVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AutoBatchVOMeta.PAGE_INDEX, AutoBatchVOMeta.PAGE_SIZE })
    @SentinelResource(value = AutoBatchServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoBatchServiceProxy.QUERY_LIST)
    public Result<List<AutoBatch>> queryList(AutoBatchVO sample) {
        Result<List<AutoBatch>> result = new Result<>();
        List<AutoBatch> list = autoBatchService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoBatchVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "614051736079826944"),
		@ApiImplicitParam(name = AutoBatchVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????2"),
		@ApiImplicitParam(name = AutoBatchVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AutoBatchVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AutoBatchVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AutoBatchServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoBatchServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AutoBatch>> queryPagedList(AutoBatchVO sample) {
        Result<PagedList<AutoBatch>> result = new Result<>();
        PagedList<AutoBatch> list = autoBatchService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
