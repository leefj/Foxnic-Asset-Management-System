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
import com.dt.platform.proxy.ops.DbBackupRecordServiceProxy;
import com.dt.platform.domain.ops.meta.DbBackupRecordVOMeta;
import com.dt.platform.domain.ops.DbBackupRecord;
import com.dt.platform.domain.ops.DbBackupRecordVO;
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
import com.dt.platform.domain.ops.meta.DbBackupRecordMeta;
import java.math.BigDecimal;
import com.dt.platform.domain.ops.Host;
import com.dt.platform.domain.ops.DbInfo;
import com.dt.platform.domain.ops.DbBackupInfo;
import com.dt.platform.domain.ops.ServiceInfo;
import com.dt.platform.domain.ops.meta.HostMeta;
import com.dt.platform.domain.ops.meta.DbInfoMeta;
import com.dt.platform.domain.ops.meta.ServiceInfoMeta;
import com.dt.platform.domain.ops.meta.DbBackupInfoMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IDbBackupRecordService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-09-12 16:56:55
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsDbBackupRecordController")
public class DbBackupRecordController extends SuperController {

    @Autowired
    private IDbBackupRecordService dbBackupRecordService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbBackupRecordVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.DB_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "620984665599115264"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.DB_BK_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "621369753377701888"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.DB_NAME, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_RESULT, value = "????????????", required = false, dataTypeClass = String.class, example = "success"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_SOURCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_RESULT_CT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_STIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-09-10 12:00:00"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_ETIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_SIZE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = DbBackupRecordServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupRecordServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(DbBackupRecordVO dbBackupRecordVO) {
        Result result = dbBackupRecordService.insert(dbBackupRecordVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbBackupRecordVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = DbBackupRecordServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupRecordServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  dbBackupRecordService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = dbBackupRecordService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbBackupRecordVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = DbBackupRecordServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupRecordServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = dbBackupRecordService.hasRefers(ids);
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
            Result result = dbBackupRecordService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = dbBackupRecordService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = DbBackupRecordVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.DB_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "620984665599115264"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.DB_BK_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "621369753377701888"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.DB_NAME, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_RESULT, value = "????????????", required = false, dataTypeClass = String.class, example = "success"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_SOURCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_RESULT_CT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_STIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-09-10 12:00:00"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_ETIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_SIZE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { DbBackupRecordVOMeta.PAGE_INDEX, DbBackupRecordVOMeta.PAGE_SIZE, DbBackupRecordVOMeta.SEARCH_FIELD, DbBackupRecordVOMeta.FUZZY_FIELD, DbBackupRecordVOMeta.SEARCH_VALUE, DbBackupRecordVOMeta.DIRTY_FIELDS, DbBackupRecordVOMeta.SORT_FIELD, DbBackupRecordVOMeta.SORT_TYPE, DbBackupRecordVOMeta.IDS })
    @SentinelResource(value = DbBackupRecordServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupRecordServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(DbBackupRecordVO dbBackupRecordVO) {
        Result result = dbBackupRecordService.update(dbBackupRecordVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbBackupRecordVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.DB_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "620984665599115264"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.DB_BK_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "621369753377701888"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.DB_NAME, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_RESULT, value = "????????????", required = false, dataTypeClass = String.class, example = "success"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_SOURCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_RESULT_CT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_STIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-09-10 12:00:00"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_ETIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_SIZE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { DbBackupRecordVOMeta.PAGE_INDEX, DbBackupRecordVOMeta.PAGE_SIZE, DbBackupRecordVOMeta.SEARCH_FIELD, DbBackupRecordVOMeta.FUZZY_FIELD, DbBackupRecordVOMeta.SEARCH_VALUE, DbBackupRecordVOMeta.DIRTY_FIELDS, DbBackupRecordVOMeta.SORT_FIELD, DbBackupRecordVOMeta.SORT_TYPE, DbBackupRecordVOMeta.IDS })
    @SentinelResource(value = DbBackupRecordServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupRecordServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(DbBackupRecordVO dbBackupRecordVO) {
        Result result = dbBackupRecordService.save(dbBackupRecordVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbBackupRecordVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = DbBackupRecordServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupRecordServiceProxy.GET_BY_ID)
    public Result<DbBackupRecord> getById(String id) {
        Result<DbBackupRecord> result = new Result<>();
        DbBackupRecord dbBackupRecord = dbBackupRecordService.getById(id);
        // join ???????????????
        dbBackupRecordService.dao().fill(dbBackupRecord).with(DbBackupRecordMeta.HOST).with(DbBackupRecordMeta.DB).with(DbBackupRecordMeta.DB_TYPE).with(DbBackupRecordMeta.BACKUP_INFO).execute();
        result.success(true).data(dbBackupRecord);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbBackupRecordVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = DbBackupRecordServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupRecordServiceProxy.GET_BY_IDS)
    public Result<List<DbBackupRecord>> getByIds(List<String> ids) {
        Result<List<DbBackupRecord>> result = new Result<>();
        List<DbBackupRecord> list = dbBackupRecordService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbBackupRecordVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.DB_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "620984665599115264"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.DB_BK_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "621369753377701888"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.DB_NAME, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_RESULT, value = "????????????", required = false, dataTypeClass = String.class, example = "success"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_SOURCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_RESULT_CT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_STIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-09-10 12:00:00"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_ETIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_SIZE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { DbBackupRecordVOMeta.PAGE_INDEX, DbBackupRecordVOMeta.PAGE_SIZE })
    @SentinelResource(value = DbBackupRecordServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupRecordServiceProxy.QUERY_LIST)
    public Result<List<DbBackupRecord>> queryList(DbBackupRecordVO sample) {
        Result<List<DbBackupRecord>> result = new Result<>();
        List<DbBackupRecord> list = dbBackupRecordService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = DbBackupRecordVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.DB_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "620984665599115264"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.DB_BK_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "621369753377701888"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.DB_NAME, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_RESULT, value = "????????????", required = false, dataTypeClass = String.class, example = "success"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_SOURCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_RESULT_CT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_STIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-09-10 12:00:00"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_ETIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.BACKUP_SIZE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = DbBackupRecordVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = DbBackupRecordServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbBackupRecordServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<DbBackupRecord>> queryPagedList(DbBackupRecordVO sample) {
        Result<PagedList<DbBackupRecord>> result = new Result<>();
        PagedList<DbBackupRecord> list = dbBackupRecordService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        dbBackupRecordService.dao().fill(list).with(DbBackupRecordMeta.HOST).with(DbBackupRecordMeta.DB).with(DbBackupRecordMeta.DB_TYPE).with(DbBackupRecordMeta.BACKUP_INFO).execute();
        result.success(true).data(list);
        return result;
    }
}
