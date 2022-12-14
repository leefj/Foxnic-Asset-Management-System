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
import com.dt.platform.proxy.ops.CiphertextBoxServiceProxy;
import com.dt.platform.domain.ops.meta.CiphertextBoxVOMeta;
import com.dt.platform.domain.ops.CiphertextBox;
import com.dt.platform.domain.ops.CiphertextBoxVO;
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
import com.dt.platform.domain.ops.meta.CiphertextBoxMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.ICiphertextBoxService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-10-19 14:45:36
 */
@Api(tags = "?????????")
@ApiSort(0)
@RestController("OpsCiphertextBoxController")
public class CiphertextBoxController extends SuperController {

    @Autowired
    private ICiphertextBoxService ciphertextBoxService;

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextBoxVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "635054036994752512"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "database"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.ENCRYPTION_KEY, value = "KEY", required = false, dataTypeClass = String.class, example = "box"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = CiphertextBoxServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(CiphertextBoxVO ciphertextBoxVO) {
        Result result = ciphertextBoxService.insert(ciphertextBoxVO, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextBoxVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "635054036994752512")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = CiphertextBoxServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  ciphertextBoxService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = ciphertextBoxService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextBoxVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = CiphertextBoxServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = ciphertextBoxService.hasRefers(ids);
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
            Result result = ciphertextBoxService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = ciphertextBoxService.deleteByIdsLogical(canDeleteIds);
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
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextBoxVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "635054036994752512"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "database"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.ENCRYPTION_KEY, value = "KEY", required = false, dataTypeClass = String.class, example = "box"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { CiphertextBoxVOMeta.PAGE_INDEX, CiphertextBoxVOMeta.PAGE_SIZE, CiphertextBoxVOMeta.SEARCH_FIELD, CiphertextBoxVOMeta.FUZZY_FIELD, CiphertextBoxVOMeta.SEARCH_VALUE, CiphertextBoxVOMeta.DIRTY_FIELDS, CiphertextBoxVOMeta.SORT_FIELD, CiphertextBoxVOMeta.SORT_TYPE, CiphertextBoxVOMeta.IDS })
    @SentinelResource(value = CiphertextBoxServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(CiphertextBoxVO ciphertextBoxVO) {
        Result result = ciphertextBoxService.update(ciphertextBoxVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextBoxVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "635054036994752512"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "database"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.ENCRYPTION_KEY, value = "KEY", required = false, dataTypeClass = String.class, example = "box"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { CiphertextBoxVOMeta.PAGE_INDEX, CiphertextBoxVOMeta.PAGE_SIZE, CiphertextBoxVOMeta.SEARCH_FIELD, CiphertextBoxVOMeta.FUZZY_FIELD, CiphertextBoxVOMeta.SEARCH_VALUE, CiphertextBoxVOMeta.DIRTY_FIELDS, CiphertextBoxVOMeta.SORT_FIELD, CiphertextBoxVOMeta.SORT_TYPE, CiphertextBoxVOMeta.IDS })
    @SentinelResource(value = CiphertextBoxServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(CiphertextBoxVO ciphertextBoxVO) {
        Result result = ciphertextBoxService.save(ciphertextBoxVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextBoxVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = CiphertextBoxServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxServiceProxy.GET_BY_ID)
    public Result<CiphertextBox> getById(String id) {
        Result<CiphertextBox> result = new Result<>();
        CiphertextBox ciphertextBox = ciphertextBoxService.getById(id);
        result.success(true).data(ciphertextBox);
        return result;
    }

    /**
     * ????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextBoxVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = CiphertextBoxServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxServiceProxy.GET_BY_IDS)
    public Result<List<CiphertextBox>> getByIds(List<String> ids) {
        Result<List<CiphertextBox>> result = new Result<>();
        List<CiphertextBox> list = ciphertextBoxService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextBoxVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "635054036994752512"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "database"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.ENCRYPTION_KEY, value = "KEY", required = false, dataTypeClass = String.class, example = "box"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { CiphertextBoxVOMeta.PAGE_INDEX, CiphertextBoxVOMeta.PAGE_SIZE })
    @SentinelResource(value = CiphertextBoxServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxServiceProxy.QUERY_LIST)
    public Result<List<CiphertextBox>> queryList(CiphertextBoxVO sample) {
        Result<List<CiphertextBox>> result = new Result<>();
        List<CiphertextBox> list = ciphertextBoxService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextBoxVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "635054036994752512"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "database"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.ENCRYPTION_KEY, value = "KEY", required = false, dataTypeClass = String.class, example = "box"),
		@ApiImplicitParam(name = CiphertextBoxVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = CiphertextBoxServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<CiphertextBox>> queryPagedList(CiphertextBoxVO sample) {
        Result<PagedList<CiphertextBox>> result = new Result<>();
        PagedList<CiphertextBox> list = ciphertextBoxService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "")
    @ApiImplicitParams({})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = CiphertextBoxServiceProxy.USER_EN_DE_PERM_BY_BOXTYPE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxServiceProxy.USER_EN_DE_PERM_BY_BOXTYPE)
    public Result<String> queryPagedList(String boxType) {
        Result<String> res = new Result<String>();
        String flag = "false";
        if (ciphertextBoxService.userEnDePermByBoxType(boxType)) {
            flag = "true";
        }
        res.success();
        res.data(flag);
        return res;
    }
}
