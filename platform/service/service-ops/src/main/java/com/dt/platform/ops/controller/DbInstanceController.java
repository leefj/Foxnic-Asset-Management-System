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
import com.dt.platform.proxy.ops.DbInstanceServiceProxy;
import com.dt.platform.domain.ops.meta.DbInstanceVOMeta;
import com.dt.platform.domain.ops.DbInstance;
import com.dt.platform.domain.ops.DbInstanceVO;
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
import com.dt.platform.domain.ops.meta.DbInstanceMeta;
import java.math.BigDecimal;
import com.dt.platform.domain.ops.Host;
import com.dt.platform.domain.ops.ServiceInfo;
import com.dt.platform.domain.ops.meta.HostMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IDbInstanceService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ??????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-08-04 06:17:08
 */
@Api(tags = "???????????????")
@ApiSort(0)
@RestController("OpsDbInstanceController")
public class DbInstanceController extends SuperController {

    @Autowired
    private IDbInstanceService dbInstanceService;

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbInstanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "599945424333176832"),
		@ApiImplicitParam(name = DbInstanceVOMeta.HOST_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "599944128997883904"),
		@ApiImplicitParam(name = DbInstanceVOMeta.DATABASE_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "473626988658032641"),
		@ApiImplicitParam(name = DbInstanceVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "ORCL"),
		@ApiImplicitParam(name = DbInstanceVOMeta.LOG_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "metholog"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "physical"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_DATAKEEP, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "success"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "incr7"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_SIZE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "12.00"),
		@ApiImplicitParam(name = DbInstanceVOMeta.LABELS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = DbInstanceServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInstanceServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(DbInstanceVO dbInstanceVO) {
        Result result = dbInstanceService.insert(dbInstanceVO, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbInstanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "599945424333176832")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = DbInstanceServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInstanceServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  dbInstanceService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = dbInstanceService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbInstanceVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = DbInstanceServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInstanceServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = dbInstanceService.hasRefers(ids);
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
            Result result = dbInstanceService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = dbInstanceService.deleteByIdsLogical(canDeleteIds);
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
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbInstanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "599945424333176832"),
		@ApiImplicitParam(name = DbInstanceVOMeta.HOST_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "599944128997883904"),
		@ApiImplicitParam(name = DbInstanceVOMeta.DATABASE_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "473626988658032641"),
		@ApiImplicitParam(name = DbInstanceVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "ORCL"),
		@ApiImplicitParam(name = DbInstanceVOMeta.LOG_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "metholog"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "physical"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_DATAKEEP, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "success"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "incr7"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_SIZE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "12.00"),
		@ApiImplicitParam(name = DbInstanceVOMeta.LABELS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { DbInstanceVOMeta.PAGE_INDEX, DbInstanceVOMeta.PAGE_SIZE, DbInstanceVOMeta.SEARCH_FIELD, DbInstanceVOMeta.FUZZY_FIELD, DbInstanceVOMeta.SEARCH_VALUE, DbInstanceVOMeta.DIRTY_FIELDS, DbInstanceVOMeta.SORT_FIELD, DbInstanceVOMeta.SORT_TYPE, DbInstanceVOMeta.IDS })
    @SentinelResource(value = DbInstanceServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInstanceServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(DbInstanceVO dbInstanceVO) {
        Result result = dbInstanceService.update(dbInstanceVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbInstanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "599945424333176832"),
		@ApiImplicitParam(name = DbInstanceVOMeta.HOST_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "599944128997883904"),
		@ApiImplicitParam(name = DbInstanceVOMeta.DATABASE_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "473626988658032641"),
		@ApiImplicitParam(name = DbInstanceVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "ORCL"),
		@ApiImplicitParam(name = DbInstanceVOMeta.LOG_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "metholog"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "physical"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_DATAKEEP, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "success"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "incr7"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_SIZE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "12.00"),
		@ApiImplicitParam(name = DbInstanceVOMeta.LABELS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { DbInstanceVOMeta.PAGE_INDEX, DbInstanceVOMeta.PAGE_SIZE, DbInstanceVOMeta.SEARCH_FIELD, DbInstanceVOMeta.FUZZY_FIELD, DbInstanceVOMeta.SEARCH_VALUE, DbInstanceVOMeta.DIRTY_FIELDS, DbInstanceVOMeta.SORT_FIELD, DbInstanceVOMeta.SORT_TYPE, DbInstanceVOMeta.IDS })
    @SentinelResource(value = DbInstanceServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInstanceServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(DbInstanceVO dbInstanceVO) {
        Result result = dbInstanceService.save(dbInstanceVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbInstanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = DbInstanceServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInstanceServiceProxy.GET_BY_ID)
    public Result<DbInstance> getById(String id) {
        Result<DbInstance> result = new Result<>();
        DbInstance dbInstance = dbInstanceService.getById(id);
        // join ???????????????
        dbInstanceService.dao().fill(dbInstance).with(DbInstanceMeta.HOST).with(DbInstanceMeta.HOST).with(DbInstanceMeta.DATABASE).execute();
        result.success(true).data(dbInstance);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbInstanceVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = DbInstanceServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInstanceServiceProxy.GET_BY_IDS)
    public Result<List<DbInstance>> getByIds(List<String> ids) {
        Result<List<DbInstance>> result = new Result<>();
        List<DbInstance> list = dbInstanceService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbInstanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "599945424333176832"),
		@ApiImplicitParam(name = DbInstanceVOMeta.HOST_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "599944128997883904"),
		@ApiImplicitParam(name = DbInstanceVOMeta.DATABASE_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "473626988658032641"),
		@ApiImplicitParam(name = DbInstanceVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "ORCL"),
		@ApiImplicitParam(name = DbInstanceVOMeta.LOG_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "metholog"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "physical"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_DATAKEEP, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "success"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "incr7"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_SIZE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "12.00"),
		@ApiImplicitParam(name = DbInstanceVOMeta.LABELS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { DbInstanceVOMeta.PAGE_INDEX, DbInstanceVOMeta.PAGE_SIZE })
    @SentinelResource(value = DbInstanceServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInstanceServiceProxy.QUERY_LIST)
    public Result<List<DbInstance>> queryList(DbInstanceVO sample) {
        Result<List<DbInstance>> result = new Result<>();
        List<DbInstance> list = dbInstanceService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbInstanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "599945424333176832"),
		@ApiImplicitParam(name = DbInstanceVOMeta.HOST_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "599944128997883904"),
		@ApiImplicitParam(name = DbInstanceVOMeta.DATABASE_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "473626988658032641"),
		@ApiImplicitParam(name = DbInstanceVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "ORCL"),
		@ApiImplicitParam(name = DbInstanceVOMeta.LOG_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "metholog"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "physical"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_DATAKEEP, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "success"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "incr7"),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.BACKUP_SIZE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "12.00"),
		@ApiImplicitParam(name = DbInstanceVOMeta.LABELS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInstanceVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = DbInstanceServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInstanceServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<DbInstance>> queryPagedList(DbInstanceVO sample) {
        Result<PagedList<DbInstance>> result = new Result<>();
        PagedList<DbInstance> list = dbInstanceService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        dbInstanceService.dao().fill(list).with(DbInstanceMeta.HOST).with(DbInstanceMeta.HOST).with(DbInstanceMeta.DATABASE).execute();
        result.success(true).data(list);
        return result;
    }
}
