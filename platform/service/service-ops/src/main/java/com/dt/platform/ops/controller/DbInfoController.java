package com.dt.platform.ops.controller;

import java.util.*;
import org.github.foxnic.web.framework.web.SuperController;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.dao.entity.ReferCause;
import com.github.foxnic.api.swagger.InDoc;
import org.github.foxnic.web.framework.sentinel.SentinelExceptionUtil;
import com.github.foxnic.api.swagger.ApiParamSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.proxy.ops.DbInfoServiceProxy;
import com.dt.platform.domain.ops.meta.DbInfoVOMeta;
import com.dt.platform.domain.ops.DbInfo;
import com.dt.platform.domain.ops.DbInfoVO;
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
import com.dt.platform.domain.ops.meta.DbInfoMeta;
import java.math.BigDecimal;
import com.dt.platform.domain.ops.DbBackupInfo;
import com.dt.platform.domain.ops.Host;
import com.dt.platform.domain.ops.ServiceInfo;
import org.github.foxnic.web.domain.system.DictItem;
import com.dt.platform.domain.ops.meta.HostMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IDbInfoService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * ????????????????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-11-17 19:12:06
 */
@InDoc
@Api(tags = "?????????")
@RestController("OpsDbInfoController")
public class DbInfoController extends SuperController {

    @Autowired
    private IDbInfoService dbInfoService;

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = DbInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "620984665599115264"),
		@ApiImplicitParam(name = DbInfoVOMeta.HOST_ID, value = "??????", required = false, dataTypeClass = String.class, example = "607860248228663296"),
		@ApiImplicitParam(name = DbInfoVOMeta.TYPE_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "495613594017202176"),
		@ApiImplicitParam(name = DbInfoVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "cw3"),
		@ApiImplicitParam(name = DbInfoVOMeta.STATUS, value = "???????????????", required = false, dataTypeClass = String.class, example = "online"),
		@ApiImplicitParam(name = DbInfoVOMeta.BACKUP_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "backup"),
		@ApiImplicitParam(name = DbInfoVOMeta.DEPLOY_MODE, value = "????????????", required = false, dataTypeClass = String.class, example = "single"),
		@ApiImplicitParam(name = DbInfoVOMeta.BACKUP_STRATEGY, value = "??????????????????", required = false, dataTypeClass = String.class, example = "???"),
		@ApiImplicitParam(name = DbInfoVOMeta.DB_SIZE, value = "??????(M)", required = false, dataTypeClass = BigDecimal.class, example = "10923.00"),
		@ApiImplicitParam(name = DbInfoVOMeta.LOG_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "no_arch"),
		@ApiImplicitParam(name = DbInfoVOMeta.ADMIN_USER_LIST, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.APP_USER_LIST, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.OTHER_USER_LIST, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.VOUCHER_STR, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.DB_PORT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = DbInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "1662883885000"),
		@ApiImplicitParam(name = DbInfoVOMeta.TOOL_STRATEGY, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.DISASTER_RECOVERY_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.CLEAR_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.OPS_USER_LIST, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.USER_USE_INFO, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    @ApiOperationSupport(order = 1, author = "?????? , maillank@qq.com")
    @SentinelResource(value = DbInfoServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInfoServiceProxy.INSERT)
    public Result insert(DbInfoVO dbInfoVO) {
        Result result = dbInfoService.insert(dbInfoVO, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = DbInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "620984665599115264")
	})
    @ApiOperationSupport(order = 2, author = "?????? , maillank@qq.com")
    @SentinelResource(value = DbInfoServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInfoServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause = dbInfoService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????" + cause.message(), false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult().messageLevel4Confirm();
        }
        Result result = dbInfoService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = DbInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3, author = "?????? , maillank@qq.com")
    @SentinelResource(value = DbInfoServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInfoServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = dbInfoService.hasRefers(ids);
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
            return ErrorDesc.failure().message("????????????????????????????????????").data(0).addErrors(CollectorUtil.collectArray(CollectorUtil.filter(causeMap.values(), (e) -> {
                return e.hasRefer();
            }), ReferCause::message, String.class)).messageLevel4Confirm();
        } else if (canDeleteIds.size() == ids.size()) {
            // ????????????????????????
            Result result = dbInfoService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = dbInfoService.deleteByIdsLogical(canDeleteIds);
            if (result.failure()) {
                return result;
            } else {
                return ErrorDesc.success().message("????????? " + canDeleteIds.size() + " ??????????????? " + (ids.size() - canDeleteIds.size()) + " ?????????????????????").data(canDeleteIds.size()).addErrors(CollectorUtil.collectArray(CollectorUtil.filter(causeMap.values(), (e) -> {
                    return e.hasRefer();
                }), ReferCause::message, String.class)).messageLevel4Confirm();
            }
        } else {
            // ?????????????????????????????????
            return ErrorDesc.success().message("?????????????????????");
        }
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = DbInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "620984665599115264"),
		@ApiImplicitParam(name = DbInfoVOMeta.HOST_ID, value = "??????", required = false, dataTypeClass = String.class, example = "607860248228663296"),
		@ApiImplicitParam(name = DbInfoVOMeta.TYPE_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "495613594017202176"),
		@ApiImplicitParam(name = DbInfoVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "cw3"),
		@ApiImplicitParam(name = DbInfoVOMeta.STATUS, value = "???????????????", required = false, dataTypeClass = String.class, example = "online"),
		@ApiImplicitParam(name = DbInfoVOMeta.BACKUP_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "backup"),
		@ApiImplicitParam(name = DbInfoVOMeta.DEPLOY_MODE, value = "????????????", required = false, dataTypeClass = String.class, example = "single"),
		@ApiImplicitParam(name = DbInfoVOMeta.BACKUP_STRATEGY, value = "??????????????????", required = false, dataTypeClass = String.class, example = "???"),
		@ApiImplicitParam(name = DbInfoVOMeta.DB_SIZE, value = "??????(M)", required = false, dataTypeClass = BigDecimal.class, example = "10923.00"),
		@ApiImplicitParam(name = DbInfoVOMeta.LOG_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "no_arch"),
		@ApiImplicitParam(name = DbInfoVOMeta.ADMIN_USER_LIST, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.APP_USER_LIST, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.OTHER_USER_LIST, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.VOUCHER_STR, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.DB_PORT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = DbInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "1662883885000"),
		@ApiImplicitParam(name = DbInfoVOMeta.TOOL_STRATEGY, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.DISASTER_RECOVERY_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.CLEAR_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.OPS_USER_LIST, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.USER_USE_INFO, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    @ApiOperationSupport(order = 4, author = "?????? , maillank@qq.com", ignoreParameters = { DbInfoVOMeta.PAGE_INDEX, DbInfoVOMeta.PAGE_SIZE, DbInfoVOMeta.SEARCH_FIELD, DbInfoVOMeta.FUZZY_FIELD, DbInfoVOMeta.SEARCH_VALUE, DbInfoVOMeta.DIRTY_FIELDS, DbInfoVOMeta.SORT_FIELD, DbInfoVOMeta.SORT_TYPE, DbInfoVOMeta.IDS })
    @SentinelResource(value = DbInfoServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInfoServiceProxy.UPDATE)
    public Result update(DbInfoVO dbInfoVO) {
        Result result = dbInfoService.update(dbInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = DbInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "620984665599115264"),
		@ApiImplicitParam(name = DbInfoVOMeta.HOST_ID, value = "??????", required = false, dataTypeClass = String.class, example = "607860248228663296"),
		@ApiImplicitParam(name = DbInfoVOMeta.TYPE_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "495613594017202176"),
		@ApiImplicitParam(name = DbInfoVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "cw3"),
		@ApiImplicitParam(name = DbInfoVOMeta.STATUS, value = "???????????????", required = false, dataTypeClass = String.class, example = "online"),
		@ApiImplicitParam(name = DbInfoVOMeta.BACKUP_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "backup"),
		@ApiImplicitParam(name = DbInfoVOMeta.DEPLOY_MODE, value = "????????????", required = false, dataTypeClass = String.class, example = "single"),
		@ApiImplicitParam(name = DbInfoVOMeta.BACKUP_STRATEGY, value = "??????????????????", required = false, dataTypeClass = String.class, example = "???"),
		@ApiImplicitParam(name = DbInfoVOMeta.DB_SIZE, value = "??????(M)", required = false, dataTypeClass = BigDecimal.class, example = "10923.00"),
		@ApiImplicitParam(name = DbInfoVOMeta.LOG_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "no_arch"),
		@ApiImplicitParam(name = DbInfoVOMeta.ADMIN_USER_LIST, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.APP_USER_LIST, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.OTHER_USER_LIST, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.VOUCHER_STR, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.DB_PORT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = DbInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "1662883885000"),
		@ApiImplicitParam(name = DbInfoVOMeta.TOOL_STRATEGY, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.DISASTER_RECOVERY_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.CLEAR_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.OPS_USER_LIST, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.USER_USE_INFO, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    @ApiOperationSupport(order = 5, ignoreParameters = { DbInfoVOMeta.PAGE_INDEX, DbInfoVOMeta.PAGE_SIZE, DbInfoVOMeta.SEARCH_FIELD, DbInfoVOMeta.FUZZY_FIELD, DbInfoVOMeta.SEARCH_VALUE, DbInfoVOMeta.DIRTY_FIELDS, DbInfoVOMeta.SORT_FIELD, DbInfoVOMeta.SORT_TYPE, DbInfoVOMeta.IDS })
    @SentinelResource(value = DbInfoServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInfoServiceProxy.SAVE)
    public Result save(DbInfoVO dbInfoVO) {
        Result result = dbInfoService.save(dbInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = DbInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6, author = "?????? , maillank@qq.com")
    @SentinelResource(value = DbInfoServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInfoServiceProxy.GET_BY_ID)
    public Result<DbInfo> getById(String id) {
        Result<DbInfo> result = new Result<>();
        DbInfo dbInfo = dbInfoService.getById(id);
        // join ???????????????
        dbInfoService.dao().fill(dbInfo).with(DbInfoMeta.HOST).with(DbInfoMeta.HOST).with(DbInfoMeta.TYPE).with(DbInfoMeta.DEPLOY_MODE_DICT).with(DbInfoMeta.LABEL_LIST).execute();
        result.success(true).data(dbInfo);
        return result;
    }

    /**
     * ????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = DbInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3, author = "?????? , maillank@qq.com")
    @SentinelResource(value = DbInfoServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInfoServiceProxy.GET_BY_IDS)
    public Result<List<DbInfo>> getByIds(List<String> ids) {
        Result<List<DbInfo>> result = new Result<>();
        List<DbInfo> list = dbInfoService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = DbInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "620984665599115264"),
		@ApiImplicitParam(name = DbInfoVOMeta.HOST_ID, value = "??????", required = false, dataTypeClass = String.class, example = "607860248228663296"),
		@ApiImplicitParam(name = DbInfoVOMeta.TYPE_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "495613594017202176"),
		@ApiImplicitParam(name = DbInfoVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "cw3"),
		@ApiImplicitParam(name = DbInfoVOMeta.STATUS, value = "???????????????", required = false, dataTypeClass = String.class, example = "online"),
		@ApiImplicitParam(name = DbInfoVOMeta.BACKUP_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "backup"),
		@ApiImplicitParam(name = DbInfoVOMeta.DEPLOY_MODE, value = "????????????", required = false, dataTypeClass = String.class, example = "single"),
		@ApiImplicitParam(name = DbInfoVOMeta.BACKUP_STRATEGY, value = "??????????????????", required = false, dataTypeClass = String.class, example = "???"),
		@ApiImplicitParam(name = DbInfoVOMeta.DB_SIZE, value = "??????(M)", required = false, dataTypeClass = BigDecimal.class, example = "10923.00"),
		@ApiImplicitParam(name = DbInfoVOMeta.LOG_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "no_arch"),
		@ApiImplicitParam(name = DbInfoVOMeta.ADMIN_USER_LIST, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.APP_USER_LIST, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.OTHER_USER_LIST, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.VOUCHER_STR, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.DB_PORT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = DbInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "1662883885000"),
		@ApiImplicitParam(name = DbInfoVOMeta.TOOL_STRATEGY, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.DISASTER_RECOVERY_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.CLEAR_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.OPS_USER_LIST, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.USER_USE_INFO, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, author = "?????? , maillank@qq.com", ignoreParameters = { DbInfoVOMeta.PAGE_INDEX, DbInfoVOMeta.PAGE_SIZE })
    @SentinelResource(value = DbInfoServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInfoServiceProxy.QUERY_LIST)
    public Result<List<DbInfo>> queryList(DbInfoVO sample) {
        Result<List<DbInfo>> result = new Result<>();
        List<DbInfo> list = dbInfoService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = DbInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "620984665599115264"),
		@ApiImplicitParam(name = DbInfoVOMeta.HOST_ID, value = "??????", required = false, dataTypeClass = String.class, example = "607860248228663296"),
		@ApiImplicitParam(name = DbInfoVOMeta.TYPE_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "495613594017202176"),
		@ApiImplicitParam(name = DbInfoVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "cw3"),
		@ApiImplicitParam(name = DbInfoVOMeta.STATUS, value = "???????????????", required = false, dataTypeClass = String.class, example = "online"),
		@ApiImplicitParam(name = DbInfoVOMeta.BACKUP_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "backup"),
		@ApiImplicitParam(name = DbInfoVOMeta.DEPLOY_MODE, value = "????????????", required = false, dataTypeClass = String.class, example = "single"),
		@ApiImplicitParam(name = DbInfoVOMeta.BACKUP_STRATEGY, value = "??????????????????", required = false, dataTypeClass = String.class, example = "???"),
		@ApiImplicitParam(name = DbInfoVOMeta.DB_SIZE, value = "??????(M)", required = false, dataTypeClass = BigDecimal.class, example = "10923.00"),
		@ApiImplicitParam(name = DbInfoVOMeta.LOG_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "no_arch"),
		@ApiImplicitParam(name = DbInfoVOMeta.ADMIN_USER_LIST, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.APP_USER_LIST, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.OTHER_USER_LIST, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.VOUCHER_STR, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.DB_PORT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = DbInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "1662883885000"),
		@ApiImplicitParam(name = DbInfoVOMeta.TOOL_STRATEGY, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.DISASTER_RECOVERY_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.CLEAR_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.OPS_USER_LIST, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = DbInfoVOMeta.USER_USE_INFO, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8, author = "?????? , maillank@qq.com")
    @SentinelResource(value = DbInfoServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(DbInfoServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<DbInfo>> queryPagedList(DbInfoVO sample) {
        Result<PagedList<DbInfo>> result = new Result<>();
        PagedList<DbInfo> list = dbInfoService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        dbInfoService.dao().fill(list).with(DbInfoMeta.HOST).with(DbInfoMeta.HOST).with(DbInfoMeta.TYPE).with(DbInfoMeta.DEPLOY_MODE_DICT).with(DbInfoMeta.LABEL_LIST).execute();
        result.success(true).data(list);
        return result;
    }
}
