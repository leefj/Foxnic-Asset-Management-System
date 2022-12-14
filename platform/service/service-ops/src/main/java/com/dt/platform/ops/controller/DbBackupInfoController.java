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
import com.dt.platform.proxy.ops.DbBackupInfoServiceProxy;
import com.dt.platform.domain.ops.meta.DbBackupInfoVOMeta;
import com.dt.platform.domain.ops.DbBackupInfo;
import com.dt.platform.domain.ops.DbBackupInfoVO;
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
import com.dt.platform.domain.ops.meta.DbBackupInfoMeta;
import java.math.BigDecimal;
import com.dt.platform.domain.ops.Host;
import com.dt.platform.domain.ops.DbInfo;
import com.dt.platform.domain.ops.ServiceInfo;
import com.dt.platform.domain.ops.meta.HostMeta;
import com.dt.platform.domain.ops.meta.DbInfoMeta;
import com.dt.platform.domain.ops.meta.ServiceInfoMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IDbBackupInfoService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ??????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-09-13 20:38:56
 */
@Api(tags = "???????????????")
@ApiSort(0)
@RestController("OpsDbBackupInfoController")
public class DbBackupInfoController extends SuperController {

    @Autowired
    private IDbBackupInfoService dbBackupInfoService;

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbBackupInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "621369753377701888"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.DATABASE_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "621350881815691264"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "online"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "physical"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "full"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_DATAKEEP, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_RESULT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_SOURCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_SIZE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_RESULT_CT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.STORAGE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = DbBackupInfoServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupInfoServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(DbBackupInfoVO dbBackupInfoVO) {
        Result result = dbBackupInfoService.insert(dbBackupInfoVO, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbBackupInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "621369753377701888")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = DbBackupInfoServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupInfoServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  dbBackupInfoService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = dbBackupInfoService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbBackupInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = DbBackupInfoServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupInfoServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = dbBackupInfoService.hasRefers(ids);
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
            Result result = dbBackupInfoService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = dbBackupInfoService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = DbBackupInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "621369753377701888"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.DATABASE_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "621350881815691264"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "online"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "physical"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "full"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_DATAKEEP, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_RESULT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_SOURCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_SIZE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_RESULT_CT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.STORAGE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { DbBackupInfoVOMeta.PAGE_INDEX, DbBackupInfoVOMeta.PAGE_SIZE, DbBackupInfoVOMeta.SEARCH_FIELD, DbBackupInfoVOMeta.FUZZY_FIELD, DbBackupInfoVOMeta.SEARCH_VALUE, DbBackupInfoVOMeta.DIRTY_FIELDS, DbBackupInfoVOMeta.SORT_FIELD, DbBackupInfoVOMeta.SORT_TYPE, DbBackupInfoVOMeta.IDS })
    @SentinelResource(value = DbBackupInfoServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupInfoServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(DbBackupInfoVO dbBackupInfoVO) {
        Result result = dbBackupInfoService.update(dbBackupInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbBackupInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "621369753377701888"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.DATABASE_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "621350881815691264"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "online"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "physical"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "full"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_DATAKEEP, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_RESULT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_SOURCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_SIZE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_RESULT_CT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.STORAGE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { DbBackupInfoVOMeta.PAGE_INDEX, DbBackupInfoVOMeta.PAGE_SIZE, DbBackupInfoVOMeta.SEARCH_FIELD, DbBackupInfoVOMeta.FUZZY_FIELD, DbBackupInfoVOMeta.SEARCH_VALUE, DbBackupInfoVOMeta.DIRTY_FIELDS, DbBackupInfoVOMeta.SORT_FIELD, DbBackupInfoVOMeta.SORT_TYPE, DbBackupInfoVOMeta.IDS })
    @SentinelResource(value = DbBackupInfoServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupInfoServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(DbBackupInfoVO dbBackupInfoVO) {
        Result result = dbBackupInfoService.save(dbBackupInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbBackupInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = DbBackupInfoServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupInfoServiceProxy.GET_BY_ID)
    public Result<DbBackupInfo> getById(String id) {
        Result<DbBackupInfo> result = new Result<>();
        DbBackupInfo dbBackupInfo = dbBackupInfoService.getById(id);
        // join ???????????????
        dbBackupInfoService.dao().fill(dbBackupInfo).with(DbBackupInfoMeta.HOST).with(DbBackupInfoMeta.DB).with(DbBackupInfoMeta.DB_TYPE).execute();
        result.success(true).data(dbBackupInfo);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbBackupInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = DbBackupInfoServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupInfoServiceProxy.GET_BY_IDS)
    public Result<List<DbBackupInfo>> getByIds(List<String> ids) {
        Result<List<DbBackupInfo>> result = new Result<>();
        List<DbBackupInfo> list = dbBackupInfoService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbBackupInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "621369753377701888"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.DATABASE_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "621350881815691264"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "online"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "physical"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "full"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_DATAKEEP, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_RESULT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_SOURCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_SIZE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_RESULT_CT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.STORAGE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { DbBackupInfoVOMeta.PAGE_INDEX, DbBackupInfoVOMeta.PAGE_SIZE })
    @SentinelResource(value = DbBackupInfoServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupInfoServiceProxy.QUERY_LIST)
    public Result<List<DbBackupInfo>> queryList(DbBackupInfoVO sample) {
        Result<List<DbBackupInfo>> result = new Result<>();
        List<DbBackupInfo> list = dbBackupInfoService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbBackupInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "621369753377701888"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.DATABASE_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "621350881815691264"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "online"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "physical"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "full"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_DATAKEEP, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_RESULT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_SOURCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_SIZE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.BACKUP_RESULT_CT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.STORAGE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = DbBackupInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = DbBackupInfoServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupInfoServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<DbBackupInfo>> queryPagedList(DbBackupInfoVO sample) {
        Result<PagedList<DbBackupInfo>> result = new Result<>();
        PagedList<DbBackupInfo> list = dbBackupInfoService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        dbBackupInfoService.dao().fill(list).with(DbBackupInfoMeta.HOST).with(DbBackupInfoMeta.DB).with(DbBackupInfoMeta.DB_TYPE).execute();
        result.success(true).data(list);
        return result;
    }
}
