package com.dt.platform.ops.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.deepoove.poi.util.PoitlIOUtils;
import com.dt.platform.constants.enums.eam.AssetOperateEnum;
import com.dt.platform.constants.enums.ops.OpsOperateEnum;
import com.dt.platform.ops.service.IOpsDataService;
import com.dt.platform.proxy.common.TplFileServiceProxy;
import com.github.foxnic.commons.log.Logger;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
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
import com.dt.platform.proxy.ops.HostServiceProxy;
import com.dt.platform.domain.ops.meta.HostVOMeta;
import com.dt.platform.domain.ops.Host;
import com.dt.platform.domain.ops.HostVO;
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
import com.dt.platform.domain.ops.meta.HostMeta;
import java.math.BigDecimal;
import com.dt.platform.domain.ops.Voucher;
import com.dt.platform.domain.ops.InformationSystem;
import com.dt.platform.domain.ops.HostPosition;
import com.dt.platform.domain.ops.ServiceInfo;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IHostService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-09-26 11:16:53
 */
@Api(tags = "??????")
@ApiSort(0)
@RestController("OpsHostController")
public class HostController extends SuperController {

    @Autowired
    private IHostService hostService;

    @Autowired
    private IOpsDataService opsDataService;

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "491382383862353920"),
		@ApiImplicitParam(name = HostVOMeta.SYSTEM_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "491353803505799168"),
		@ApiImplicitParam(name = HostVOMeta.HOST_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "business"),
		@ApiImplicitParam(name = HostVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = HostVOMeta.HOST_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.HOST_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "1.1.1.1"),
		@ApiImplicitParam(name = HostVOMeta.HOST_VIP, value = "??????VIP", required = false, dataTypeClass = String.class, example = "1.1.1.1"),
		@ApiImplicitParam(name = HostVOMeta.ENVIRONMENT, value = "????????????", required = false, dataTypeClass = String.class, example = "prod"),
		@ApiImplicitParam(name = HostVOMeta.POSITION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "478242968168304640"),
		@ApiImplicitParam(name = HostVOMeta.MONITOR_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = HostVOMeta.DIRECTOR_USERNAME, value = "?????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.HOST_MEMORY, value = "??????", required = false, dataTypeClass = BigDecimal.class, example = "1212"),
		@ApiImplicitParam(name = HostVOMeta.HOST_CPU, value = "CPU", required = false, dataTypeClass = BigDecimal.class, example = "1212"),
		@ApiImplicitParam(name = HostVOMeta.HOST_CONF, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_OS_ADMIN, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.USER_DB_ADMIN, value = "??????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_DB_USED, value = "?????????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_APP_USED, value = "??????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_OPS_OPER, value = "??????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_OTHER, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.PASSWORD_STRATEGY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "unchange"),
		@ApiImplicitParam(name = HostVOMeta.HOST_BACKUP_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.HOST_BACKUP_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.OFFLINE_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-09-04 12:00:00"),
		@ApiImplicitParam(name = HostVOMeta.ONLINE_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-09-11 12:00:00"),
		@ApiImplicitParam(name = HostVOMeta.ARCH, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.LABELS, value = "??????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = HostVOMeta.HOST_NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "??????")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = HostServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(HostVO hostVO) {
        Result result = hostService.insert(hostVO);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "491382383862353920")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = HostServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = hostService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = HostServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = hostService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "491382383862353920"),
		@ApiImplicitParam(name = HostVOMeta.SYSTEM_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "491353803505799168"),
		@ApiImplicitParam(name = HostVOMeta.HOST_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "business"),
		@ApiImplicitParam(name = HostVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = HostVOMeta.HOST_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.HOST_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "1.1.1.1"),
		@ApiImplicitParam(name = HostVOMeta.HOST_VIP, value = "??????VIP", required = false, dataTypeClass = String.class, example = "1.1.1.1"),
		@ApiImplicitParam(name = HostVOMeta.ENVIRONMENT, value = "????????????", required = false, dataTypeClass = String.class, example = "prod"),
		@ApiImplicitParam(name = HostVOMeta.POSITION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "478242968168304640"),
		@ApiImplicitParam(name = HostVOMeta.MONITOR_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = HostVOMeta.DIRECTOR_USERNAME, value = "?????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.HOST_MEMORY, value = "??????", required = false, dataTypeClass = BigDecimal.class, example = "1212"),
		@ApiImplicitParam(name = HostVOMeta.HOST_CPU, value = "CPU", required = false, dataTypeClass = BigDecimal.class, example = "1212"),
		@ApiImplicitParam(name = HostVOMeta.HOST_CONF, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_OS_ADMIN, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.USER_DB_ADMIN, value = "??????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_DB_USED, value = "?????????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_APP_USED, value = "??????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_OPS_OPER, value = "??????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_OTHER, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.PASSWORD_STRATEGY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "unchange"),
		@ApiImplicitParam(name = HostVOMeta.HOST_BACKUP_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.HOST_BACKUP_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.OFFLINE_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-09-04 12:00:00"),
		@ApiImplicitParam(name = HostVOMeta.ONLINE_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-09-11 12:00:00"),
		@ApiImplicitParam(name = HostVOMeta.ARCH, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.LABELS, value = "??????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = HostVOMeta.HOST_NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "??????")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { HostVOMeta.PAGE_INDEX, HostVOMeta.PAGE_SIZE, HostVOMeta.SEARCH_FIELD, HostVOMeta.FUZZY_FIELD, HostVOMeta.SEARCH_VALUE, HostVOMeta.SORT_FIELD, HostVOMeta.SORT_TYPE, HostVOMeta.IDS })
    @SentinelResource(value = HostServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(HostVO hostVO) {
        Result result = hostService.update(hostVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "491382383862353920"),
		@ApiImplicitParam(name = HostVOMeta.SYSTEM_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "491353803505799168"),
		@ApiImplicitParam(name = HostVOMeta.HOST_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "business"),
		@ApiImplicitParam(name = HostVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = HostVOMeta.HOST_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.HOST_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "1.1.1.1"),
		@ApiImplicitParam(name = HostVOMeta.HOST_VIP, value = "??????VIP", required = false, dataTypeClass = String.class, example = "1.1.1.1"),
		@ApiImplicitParam(name = HostVOMeta.ENVIRONMENT, value = "????????????", required = false, dataTypeClass = String.class, example = "prod"),
		@ApiImplicitParam(name = HostVOMeta.POSITION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "478242968168304640"),
		@ApiImplicitParam(name = HostVOMeta.MONITOR_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = HostVOMeta.DIRECTOR_USERNAME, value = "?????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.HOST_MEMORY, value = "??????", required = false, dataTypeClass = BigDecimal.class, example = "1212"),
		@ApiImplicitParam(name = HostVOMeta.HOST_CPU, value = "CPU", required = false, dataTypeClass = BigDecimal.class, example = "1212"),
		@ApiImplicitParam(name = HostVOMeta.HOST_CONF, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_OS_ADMIN, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.USER_DB_ADMIN, value = "??????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_DB_USED, value = "?????????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_APP_USED, value = "??????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_OPS_OPER, value = "??????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_OTHER, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.PASSWORD_STRATEGY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "unchange"),
		@ApiImplicitParam(name = HostVOMeta.HOST_BACKUP_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.HOST_BACKUP_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.OFFLINE_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-09-04 12:00:00"),
		@ApiImplicitParam(name = HostVOMeta.ONLINE_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-09-11 12:00:00"),
		@ApiImplicitParam(name = HostVOMeta.ARCH, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.LABELS, value = "??????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = HostVOMeta.HOST_NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "??????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { HostVOMeta.PAGE_INDEX, HostVOMeta.PAGE_SIZE, HostVOMeta.SEARCH_FIELD, HostVOMeta.FUZZY_FIELD, HostVOMeta.SEARCH_VALUE, HostVOMeta.SORT_FIELD, HostVOMeta.SORT_TYPE, HostVOMeta.IDS })
    @SentinelResource(value = HostServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(HostVO hostVO) {
        Result result = hostService.save(hostVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = HostServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostServiceProxy.GET_BY_ID)
    public Result<Host> getById(String id) {
        Result<Host> result = new Result<>();
        Host host = hostService.getById(id);
        // ????????? ???????????? ??????
        hostService.join(host, HostMeta.INFO_SYSTEM);
        // ????????? ???????????? ??????
        hostService.join(host, HostMeta.POSITION);
        // ????????? ????????? ??????
        hostService.join(host, HostMeta.HOST_DB_LIST);
        // ????????? ????????? ??????
        hostService.join(host, HostMeta.HOST_MIDDLEWARE_LIST);
        // ????????? ???????????? ??????
        hostService.join(host, HostMeta.HOST_OS_LIST);
        // ????????? ???????????? ??????
        hostService.join(host, HostMeta.VOUCHER_LIST);
        hostService.join(host, HostMeta.BACKUP_METHOD);
        result.success(true).data(host);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = HostServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostServiceProxy.GET_BY_IDS)
    public Result<List<Host>> getByIds(List<String> ids) {
        Result<List<Host>> result = new Result<>();
        List<Host> list = hostService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "491382383862353920"),
		@ApiImplicitParam(name = HostVOMeta.SYSTEM_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "491353803505799168"),
		@ApiImplicitParam(name = HostVOMeta.HOST_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "business"),
		@ApiImplicitParam(name = HostVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = HostVOMeta.HOST_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.HOST_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "1.1.1.1"),
		@ApiImplicitParam(name = HostVOMeta.HOST_VIP, value = "??????VIP", required = false, dataTypeClass = String.class, example = "1.1.1.1"),
		@ApiImplicitParam(name = HostVOMeta.ENVIRONMENT, value = "????????????", required = false, dataTypeClass = String.class, example = "prod"),
		@ApiImplicitParam(name = HostVOMeta.POSITION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "478242968168304640"),
		@ApiImplicitParam(name = HostVOMeta.MONITOR_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = HostVOMeta.DIRECTOR_USERNAME, value = "?????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.HOST_MEMORY, value = "??????", required = false, dataTypeClass = BigDecimal.class, example = "1212"),
		@ApiImplicitParam(name = HostVOMeta.HOST_CPU, value = "CPU", required = false, dataTypeClass = BigDecimal.class, example = "1212"),
		@ApiImplicitParam(name = HostVOMeta.HOST_CONF, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_OS_ADMIN, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.USER_DB_ADMIN, value = "??????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_DB_USED, value = "?????????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_APP_USED, value = "??????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_OPS_OPER, value = "??????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_OTHER, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.PASSWORD_STRATEGY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "unchange"),
		@ApiImplicitParam(name = HostVOMeta.HOST_BACKUP_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.HOST_BACKUP_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.OFFLINE_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-09-04 12:00:00"),
		@ApiImplicitParam(name = HostVOMeta.ONLINE_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-09-11 12:00:00"),
		@ApiImplicitParam(name = HostVOMeta.ARCH, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.LABELS, value = "??????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = HostVOMeta.HOST_NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "??????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { HostVOMeta.PAGE_INDEX, HostVOMeta.PAGE_SIZE })
    @SentinelResource(value = HostServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostServiceProxy.QUERY_LIST)
    public Result<List<Host>> queryList(HostVO sample) {
        Result<List<Host>> result = new Result<>();
        List<Host> list = hostService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "491382383862353920"),
		@ApiImplicitParam(name = HostVOMeta.SYSTEM_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "491353803505799168"),
		@ApiImplicitParam(name = HostVOMeta.HOST_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "business"),
		@ApiImplicitParam(name = HostVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = HostVOMeta.HOST_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.HOST_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "1.1.1.1"),
		@ApiImplicitParam(name = HostVOMeta.HOST_VIP, value = "??????VIP", required = false, dataTypeClass = String.class, example = "1.1.1.1"),
		@ApiImplicitParam(name = HostVOMeta.ENVIRONMENT, value = "????????????", required = false, dataTypeClass = String.class, example = "prod"),
		@ApiImplicitParam(name = HostVOMeta.POSITION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "478242968168304640"),
		@ApiImplicitParam(name = HostVOMeta.MONITOR_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = HostVOMeta.DIRECTOR_USERNAME, value = "?????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.HOST_MEMORY, value = "??????", required = false, dataTypeClass = BigDecimal.class, example = "1212"),
		@ApiImplicitParam(name = HostVOMeta.HOST_CPU, value = "CPU", required = false, dataTypeClass = BigDecimal.class, example = "1212"),
		@ApiImplicitParam(name = HostVOMeta.HOST_CONF, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_OS_ADMIN, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.USER_DB_ADMIN, value = "??????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_DB_USED, value = "?????????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_APP_USED, value = "??????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_OPS_OPER, value = "??????????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.USER_OTHER, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = HostVOMeta.PASSWORD_STRATEGY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "unchange"),
		@ApiImplicitParam(name = HostVOMeta.HOST_BACKUP_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.HOST_BACKUP_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.OFFLINE_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-09-04 12:00:00"),
		@ApiImplicitParam(name = HostVOMeta.ONLINE_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-09-11 12:00:00"),
		@ApiImplicitParam(name = HostVOMeta.ARCH, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostVOMeta.LABELS, value = "??????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = HostVOMeta.HOST_NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "??????")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = HostServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<Host>> queryPagedList(HostVO sample) {
        Result<PagedList<Host>> result = new Result<>();
        PagedList<Host> list = hostService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // ????????? ???????????? ??????
        hostService.join(list, HostMeta.INFO_SYSTEM);
        // ????????? ???????????? ??????
        hostService.join(list, HostMeta.POSITION);
        // ????????? ????????? ??????
        hostService.join(list, HostMeta.HOST_DB_LIST);
        // ????????? ????????? ??????
        hostService.join(list, HostMeta.HOST_MIDDLEWARE_LIST);
        // ????????? ???????????? ??????
        hostService.join(list, HostMeta.HOST_OS_LIST);
        // ????????? ???????????? ??????
        hostService.join(list, HostMeta.VOUCHER_LIST);
        hostService.join(list, HostMeta.BACKUP_METHOD);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = HostServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(HostServiceProxy.EXPORT_EXCEL)
    public Result exportExcel(HostVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        String code = OpsOperateEnum.OPS_DOWNLOAD_HOST.code();
        InputStream inputstream = TplFileServiceProxy.api().getTplFileStreamByCode(code);
        if (inputstream == null) {
            return ErrorDesc.failure().message("????????????????????????");
        }
        File f = opsDataService.saveTempFile(inputstream, "TMP_" + code + ".xls");
        Map<String, Object> map = opsDataService.queryHostMap(opsDataService.queryHostList(sample));
        TemplateExportParams templateExportParams = new TemplateExportParams(f.getPath());
        Workbook workbook = ExcelExportUtil.exportExcel(templateExportParams, map);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("????????????.xls", "UTF-8"))));
        response.setContentType("application/vnd.ms-excel");
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        workbook.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(workbook, bos, out);
        return ErrorDesc.success();
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = HostServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(HostServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public Result exportExcelTemplate(HttpServletResponse response, HostVO sample) throws Exception {
        // ?????? Excel ??????
        String code = OpsOperateEnum.OPS_DOWNLOAD_HOST.code();
        InputStream inputstream = TplFileServiceProxy.api().getTplFileStreamByCode(code);
        if (inputstream == null) {
            return ErrorDesc.failure().message("????????????????????????");
        }
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("????????????.xls", "UTF-8"))));
        response.setContentType("application/vnd.ms-excel");
        OutputStream out = response.getOutputStream();
        IOUtils.copy(inputstream, out);
        out.flush();
        return ErrorDesc.success();
    }

    @SentinelResource(value = HostServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(HostServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = hostService.importExcel(input, 0, true, false);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            Logger.info("import Result:");
            for (int i = 0; i < errors.size(); i++) {
                Logger.info(i + ":" + errors.get(i).message);
            }
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
