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
import com.dt.platform.proxy.ops.CiphertextHistoryServiceProxy;
import com.dt.platform.domain.ops.meta.CiphertextHistoryVOMeta;
import com.dt.platform.domain.ops.CiphertextHistory;
import com.dt.platform.domain.ops.CiphertextHistoryVO;
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
import com.dt.platform.domain.ops.meta.CiphertextHistoryMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.ICiphertextHistoryService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-10-19 14:16:45
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsCiphertextHistoryController")
public class CiphertextHistoryController extends SuperController {

    @Autowired
    private ICiphertextHistoryService ciphertextHistoryService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "635053950277517312"),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "ciphertext_box_conf"),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.BOX_TYPE, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.SOURCE_VALUE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.ENCRYPTION_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "121212dsf")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = CiphertextHistoryServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextHistoryServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(CiphertextHistoryVO ciphertextHistoryVO) {
        Result result = ciphertextHistoryService.insert(ciphertextHistoryVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "635053950277517312")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = CiphertextHistoryServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextHistoryServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  ciphertextHistoryService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = ciphertextHistoryService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = CiphertextHistoryServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextHistoryServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = ciphertextHistoryService.hasRefers(ids);
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
            Result result = ciphertextHistoryService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = ciphertextHistoryService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "635053950277517312"),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "ciphertext_box_conf"),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.BOX_TYPE, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.SOURCE_VALUE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.ENCRYPTION_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "121212dsf")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { CiphertextHistoryVOMeta.PAGE_INDEX, CiphertextHistoryVOMeta.PAGE_SIZE, CiphertextHistoryVOMeta.SEARCH_FIELD, CiphertextHistoryVOMeta.FUZZY_FIELD, CiphertextHistoryVOMeta.SEARCH_VALUE, CiphertextHistoryVOMeta.DIRTY_FIELDS, CiphertextHistoryVOMeta.SORT_FIELD, CiphertextHistoryVOMeta.SORT_TYPE, CiphertextHistoryVOMeta.IDS })
    @SentinelResource(value = CiphertextHistoryServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextHistoryServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(CiphertextHistoryVO ciphertextHistoryVO) {
        Result result = ciphertextHistoryService.update(ciphertextHistoryVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "635053950277517312"),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "ciphertext_box_conf"),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.BOX_TYPE, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.SOURCE_VALUE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.ENCRYPTION_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "121212dsf")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { CiphertextHistoryVOMeta.PAGE_INDEX, CiphertextHistoryVOMeta.PAGE_SIZE, CiphertextHistoryVOMeta.SEARCH_FIELD, CiphertextHistoryVOMeta.FUZZY_FIELD, CiphertextHistoryVOMeta.SEARCH_VALUE, CiphertextHistoryVOMeta.DIRTY_FIELDS, CiphertextHistoryVOMeta.SORT_FIELD, CiphertextHistoryVOMeta.SORT_TYPE, CiphertextHistoryVOMeta.IDS })
    @SentinelResource(value = CiphertextHistoryServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextHistoryServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(CiphertextHistoryVO ciphertextHistoryVO) {
        Result result = ciphertextHistoryService.save(ciphertextHistoryVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = CiphertextHistoryServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextHistoryServiceProxy.GET_BY_ID)
    public Result<CiphertextHistory> getById(String id) {
        Result<CiphertextHistory> result = new Result<>();
        CiphertextHistory ciphertextHistory = ciphertextHistoryService.getById(id);
        result.success(true).data(ciphertextHistory);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = CiphertextHistoryServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextHistoryServiceProxy.GET_BY_IDS)
    public Result<List<CiphertextHistory>> getByIds(List<String> ids) {
        Result<List<CiphertextHistory>> result = new Result<>();
        List<CiphertextHistory> list = ciphertextHistoryService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "635053950277517312"),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "ciphertext_box_conf"),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.BOX_TYPE, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.SOURCE_VALUE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.ENCRYPTION_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "121212dsf")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { CiphertextHistoryVOMeta.PAGE_INDEX, CiphertextHistoryVOMeta.PAGE_SIZE })
    @SentinelResource(value = CiphertextHistoryServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextHistoryServiceProxy.QUERY_LIST)
    public Result<List<CiphertextHistory>> queryList(CiphertextHistoryVO sample) {
        Result<List<CiphertextHistory>> result = new Result<>();
        List<CiphertextHistory> list = ciphertextHistoryService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "635053950277517312"),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "ciphertext_box_conf"),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.BOX_TYPE, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.SOURCE_VALUE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = CiphertextHistoryVOMeta.ENCRYPTION_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "121212dsf")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = CiphertextHistoryServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextHistoryServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<CiphertextHistory>> queryPagedList(CiphertextHistoryVO sample) {
        Result<PagedList<CiphertextHistory>> result = new Result<>();
        PagedList<CiphertextHistory> list = ciphertextHistoryService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
