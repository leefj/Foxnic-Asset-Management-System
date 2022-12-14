package com.dt.platform.ops.controller;

import java.util.List;
import java.util.ArrayList;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.dao.entity.ReferCause;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.github.foxnic.api.swagger.InDoc;
import org.github.foxnic.web.framework.web.SuperController;
import org.github.foxnic.web.framework.sentinel.SentinelExceptionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.github.foxnic.api.swagger.ApiParamSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.proxy.ops.CiphertextBoxDataServiceProxy;
import com.dt.platform.domain.ops.meta.CiphertextBoxDataVOMeta;
import com.dt.platform.domain.ops.CiphertextBoxData;
import com.dt.platform.domain.ops.CiphertextBoxDataVO;
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
import com.dt.platform.domain.ops.meta.CiphertextBoxDataMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.ICiphertextBoxDataService;

/**
 * <p>
 * ???????????????????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-10-23 20:34:32
 */
@InDoc
@Api(tags = "null/????????????")
@RestController("OpsCiphertextBoxDataController")
public class CiphertextBoxDataController extends SuperController {

    @Autowired
    private ICiphertextBoxDataService ciphertextBoxDataService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "636661362889588736"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.SOURCE_ID, value = "??????", required = true, dataTypeClass = String.class, example = "621815652788731904"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.BOX_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "635394284450742272"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.BOX_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "database_instance"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.PLAINTEXT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.CIPHERTEXT, value = "??????", required = false, dataTypeClass = String.class, example = "ukAuwv2qysEywWJSm4XBYA=="),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1222")
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    @ApiOperationSupport(order = 1, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CiphertextBoxDataServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxDataServiceProxy.INSERT)
    public Result insert(CiphertextBoxDataVO ciphertextBoxDataVO) {
        Result result = ciphertextBoxDataService.insert(ciphertextBoxDataVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "636661362889588736")
	})
    @ApiOperationSupport(order = 2, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CiphertextBoxDataServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxDataServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  ciphertextBoxDataService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = ciphertextBoxDataService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CiphertextBoxDataServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxDataServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = ciphertextBoxDataService.hasRefers(ids);
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
            Result result = ciphertextBoxDataService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = ciphertextBoxDataService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "636661362889588736"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.SOURCE_ID, value = "??????", required = true, dataTypeClass = String.class, example = "621815652788731904"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.BOX_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "635394284450742272"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.BOX_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "database_instance"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.PLAINTEXT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.CIPHERTEXT, value = "??????", required = false, dataTypeClass = String.class, example = "ukAuwv2qysEywWJSm4XBYA=="),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1222")
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    @ApiOperationSupport(order = 4, author = "?????? , maillank@qq.com", ignoreParameters = { CiphertextBoxDataVOMeta.PAGE_INDEX, CiphertextBoxDataVOMeta.PAGE_SIZE, CiphertextBoxDataVOMeta.SEARCH_FIELD, CiphertextBoxDataVOMeta.FUZZY_FIELD, CiphertextBoxDataVOMeta.SEARCH_VALUE, CiphertextBoxDataVOMeta.DIRTY_FIELDS, CiphertextBoxDataVOMeta.SORT_FIELD, CiphertextBoxDataVOMeta.SORT_TYPE, CiphertextBoxDataVOMeta.IDS })
    @SentinelResource(value = CiphertextBoxDataServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxDataServiceProxy.UPDATE)
    public Result update(CiphertextBoxDataVO ciphertextBoxDataVO) {
        Result result = ciphertextBoxDataService.update(ciphertextBoxDataVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "636661362889588736"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.SOURCE_ID, value = "??????", required = true, dataTypeClass = String.class, example = "621815652788731904"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.BOX_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "635394284450742272"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.BOX_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "database_instance"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.PLAINTEXT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.CIPHERTEXT, value = "??????", required = false, dataTypeClass = String.class, example = "ukAuwv2qysEywWJSm4XBYA=="),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1222")
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    @ApiOperationSupport(order = 5, ignoreParameters = { CiphertextBoxDataVOMeta.PAGE_INDEX, CiphertextBoxDataVOMeta.PAGE_SIZE, CiphertextBoxDataVOMeta.SEARCH_FIELD, CiphertextBoxDataVOMeta.FUZZY_FIELD, CiphertextBoxDataVOMeta.SEARCH_VALUE, CiphertextBoxDataVOMeta.DIRTY_FIELDS, CiphertextBoxDataVOMeta.SORT_FIELD, CiphertextBoxDataVOMeta.SORT_TYPE, CiphertextBoxDataVOMeta.IDS })
    @SentinelResource(value = CiphertextBoxDataServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxDataServiceProxy.SAVE)
    public Result save(CiphertextBoxDataVO ciphertextBoxDataVO) {
        Result result = ciphertextBoxDataService.save(ciphertextBoxDataVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CiphertextBoxDataServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxDataServiceProxy.GET_BY_ID)
    public Result<CiphertextBoxData> getById(String id) {
        Result<CiphertextBoxData> result = new Result<>();
        CiphertextBoxData ciphertextBoxData = ciphertextBoxDataService.getById(id);
        result.success(true).data(ciphertextBoxData);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CiphertextBoxDataServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxDataServiceProxy.GET_BY_IDS)
    public Result<List<CiphertextBoxData>> getByIds(List<String> ids) {
        Result<List<CiphertextBoxData>> result = new Result<>();
        List<CiphertextBoxData> list = ciphertextBoxDataService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "636661362889588736"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.SOURCE_ID, value = "??????", required = true, dataTypeClass = String.class, example = "621815652788731904"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.BOX_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "635394284450742272"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.BOX_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "database_instance"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.PLAINTEXT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.CIPHERTEXT, value = "??????", required = false, dataTypeClass = String.class, example = "ukAuwv2qysEywWJSm4XBYA=="),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1222")
	})
    @ApiOperationSupport(order = 5, author = "?????? , maillank@qq.com", ignoreParameters = { CiphertextBoxDataVOMeta.PAGE_INDEX, CiphertextBoxDataVOMeta.PAGE_SIZE })
    @SentinelResource(value = CiphertextBoxDataServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxDataServiceProxy.QUERY_LIST)
    public Result<List<CiphertextBoxData>> queryList(CiphertextBoxDataVO sample) {
        Result<List<CiphertextBoxData>> result = new Result<>();
        List<CiphertextBoxData> list = ciphertextBoxDataService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "636661362889588736"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.SOURCE_ID, value = "??????", required = true, dataTypeClass = String.class, example = "621815652788731904"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.BOX_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "635394284450742272"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.BOX_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "database_instance"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.PLAINTEXT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.CIPHERTEXT, value = "??????", required = false, dataTypeClass = String.class, example = "ukAuwv2qysEywWJSm4XBYA=="),
		@ApiImplicitParam(name = CiphertextBoxDataVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1222")
	})
    @ApiOperationSupport(order = 8, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CiphertextBoxDataServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CiphertextBoxDataServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<CiphertextBoxData>> queryPagedList(CiphertextBoxDataVO sample) {
        Result<PagedList<CiphertextBoxData>> result = new Result<>();
        PagedList<CiphertextBoxData> list = ciphertextBoxDataService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
