package com.dt.platform.contract.controller;

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
import com.dt.platform.proxy.contract.ContractSignerServiceProxy;
import com.dt.platform.domain.contract.meta.ContractSignerVOMeta;
import com.dt.platform.domain.contract.ContractSigner;
import com.dt.platform.domain.contract.ContractSignerVO;
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
import com.dt.platform.domain.contract.meta.ContractSignerMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.contract.service.IContractSignerService;

/**
 * <p>
 * ??????????????????????????????
 * </p>
 * @author ????????? , leefangjie@qq.com
 * @since 2022-10-21 15:39:33
 */
@InDoc
@Api(tags = "????????????/???????????????")
@RestController("ContContractSignerController")
public class ContractSignerController extends SuperController {

    @Autowired
    private IContractSignerService contractSignerService;

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ContractSignerVOMeta.ID, value = "id", required = true, dataTypeClass = String.class, example = "565281406238851072"),
		@ApiImplicitParam(name = ContractSignerVOMeta.CONTRACT_ID, value = "??????ID", required = false, dataTypeClass = String.class, example = "565281320981233664"),
		@ApiImplicitParam(name = ContractSignerVOMeta.SORT, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.ALIAS, value = "??????", required = false, dataTypeClass = String.class, example = "part_a"),
		@ApiImplicitParam(name = ContractSignerVOMeta.TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "enterprise"),
		@ApiImplicitParam(name = ContractSignerVOMeta.IDENTITY_CDOE, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractSignerVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractSignerVOMeta.LANDLINE_NUMBER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.FAX_NUMBER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.ADDRESS, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractSignerVOMeta.CONTACT_PERSON, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.CONTACT_PHONE, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    @ApiOperationSupport(order = 1, author = "????????? , leefangjie@qq.com")
    @SentinelResource(value = ContractSignerServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractSignerServiceProxy.INSERT)
    public Result insert(ContractSignerVO contractSignerVO) {
        Result result = contractSignerService.insert(contractSignerVO, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ContractSignerVOMeta.ID, value = "id", required = true, dataTypeClass = String.class, example = "565281406238851072")
	})
    @ApiOperationSupport(order = 2, author = "????????? , leefangjie@qq.com")
    @SentinelResource(value = ContractSignerServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractSignerServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  contractSignerService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = contractSignerService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ContractSignerVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3, author = "????????? , leefangjie@qq.com")
    @SentinelResource(value = ContractSignerServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractSignerServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = contractSignerService.hasRefers(ids);
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
            Result result = contractSignerService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = contractSignerService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = ContractSignerVOMeta.ID, value = "id", required = true, dataTypeClass = String.class, example = "565281406238851072"),
		@ApiImplicitParam(name = ContractSignerVOMeta.CONTRACT_ID, value = "??????ID", required = false, dataTypeClass = String.class, example = "565281320981233664"),
		@ApiImplicitParam(name = ContractSignerVOMeta.SORT, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.ALIAS, value = "??????", required = false, dataTypeClass = String.class, example = "part_a"),
		@ApiImplicitParam(name = ContractSignerVOMeta.TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "enterprise"),
		@ApiImplicitParam(name = ContractSignerVOMeta.IDENTITY_CDOE, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractSignerVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractSignerVOMeta.LANDLINE_NUMBER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.FAX_NUMBER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.ADDRESS, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractSignerVOMeta.CONTACT_PERSON, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.CONTACT_PHONE, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    @ApiOperationSupport(order = 4, author = "????????? , leefangjie@qq.com", ignoreParameters = { ContractSignerVOMeta.PAGE_INDEX, ContractSignerVOMeta.PAGE_SIZE, ContractSignerVOMeta.SEARCH_FIELD, ContractSignerVOMeta.FUZZY_FIELD, ContractSignerVOMeta.SEARCH_VALUE, ContractSignerVOMeta.DIRTY_FIELDS, ContractSignerVOMeta.SORT_FIELD, ContractSignerVOMeta.SORT_TYPE, ContractSignerVOMeta.IDS })
    @SentinelResource(value = ContractSignerServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractSignerServiceProxy.UPDATE)
    public Result update(ContractSignerVO contractSignerVO) {
        Result result = contractSignerService.update(contractSignerVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ContractSignerVOMeta.ID, value = "id", required = true, dataTypeClass = String.class, example = "565281406238851072"),
		@ApiImplicitParam(name = ContractSignerVOMeta.CONTRACT_ID, value = "??????ID", required = false, dataTypeClass = String.class, example = "565281320981233664"),
		@ApiImplicitParam(name = ContractSignerVOMeta.SORT, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.ALIAS, value = "??????", required = false, dataTypeClass = String.class, example = "part_a"),
		@ApiImplicitParam(name = ContractSignerVOMeta.TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "enterprise"),
		@ApiImplicitParam(name = ContractSignerVOMeta.IDENTITY_CDOE, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractSignerVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractSignerVOMeta.LANDLINE_NUMBER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.FAX_NUMBER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.ADDRESS, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractSignerVOMeta.CONTACT_PERSON, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.CONTACT_PHONE, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    @ApiOperationSupport(order = 5, ignoreParameters = { ContractSignerVOMeta.PAGE_INDEX, ContractSignerVOMeta.PAGE_SIZE, ContractSignerVOMeta.SEARCH_FIELD, ContractSignerVOMeta.FUZZY_FIELD, ContractSignerVOMeta.SEARCH_VALUE, ContractSignerVOMeta.DIRTY_FIELDS, ContractSignerVOMeta.SORT_FIELD, ContractSignerVOMeta.SORT_TYPE, ContractSignerVOMeta.IDS })
    @SentinelResource(value = ContractSignerServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractSignerServiceProxy.SAVE)
    public Result save(ContractSignerVO contractSignerVO) {
        Result result = contractSignerService.save(contractSignerVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ContractSignerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6, author = "????????? , leefangjie@qq.com")
    @SentinelResource(value = ContractSignerServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractSignerServiceProxy.GET_BY_ID)
    public Result<ContractSigner> getById(String id) {
        Result<ContractSigner> result = new Result<>();
        ContractSigner contractSigner = contractSignerService.getById(id);
        result.success(true).data(contractSigner);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ContractSignerVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3, author = "????????? , leefangjie@qq.com")
    @SentinelResource(value = ContractSignerServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractSignerServiceProxy.GET_BY_IDS)
    public Result<List<ContractSigner>> getByIds(List<String> ids) {
        Result<List<ContractSigner>> result = new Result<>();
        List<ContractSigner> list = contractSignerService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ContractSignerVOMeta.ID, value = "id", required = true, dataTypeClass = String.class, example = "565281406238851072"),
		@ApiImplicitParam(name = ContractSignerVOMeta.CONTRACT_ID, value = "??????ID", required = false, dataTypeClass = String.class, example = "565281320981233664"),
		@ApiImplicitParam(name = ContractSignerVOMeta.SORT, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.ALIAS, value = "??????", required = false, dataTypeClass = String.class, example = "part_a"),
		@ApiImplicitParam(name = ContractSignerVOMeta.TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "enterprise"),
		@ApiImplicitParam(name = ContractSignerVOMeta.IDENTITY_CDOE, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractSignerVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractSignerVOMeta.LANDLINE_NUMBER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.FAX_NUMBER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.ADDRESS, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractSignerVOMeta.CONTACT_PERSON, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.CONTACT_PHONE, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, author = "????????? , leefangjie@qq.com", ignoreParameters = { ContractSignerVOMeta.PAGE_INDEX, ContractSignerVOMeta.PAGE_SIZE })
    @SentinelResource(value = ContractSignerServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractSignerServiceProxy.QUERY_LIST)
    public Result<List<ContractSigner>> queryList(ContractSignerVO sample) {
        Result<List<ContractSigner>> result = new Result<>();
        List<ContractSigner> list = contractSignerService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ContractSignerVOMeta.ID, value = "id", required = true, dataTypeClass = String.class, example = "565281406238851072"),
		@ApiImplicitParam(name = ContractSignerVOMeta.CONTRACT_ID, value = "??????ID", required = false, dataTypeClass = String.class, example = "565281320981233664"),
		@ApiImplicitParam(name = ContractSignerVOMeta.SORT, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.ALIAS, value = "??????", required = false, dataTypeClass = String.class, example = "part_a"),
		@ApiImplicitParam(name = ContractSignerVOMeta.TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "enterprise"),
		@ApiImplicitParam(name = ContractSignerVOMeta.IDENTITY_CDOE, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractSignerVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractSignerVOMeta.LANDLINE_NUMBER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.FAX_NUMBER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.ADDRESS, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractSignerVOMeta.CONTACT_PERSON, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.CONTACT_PHONE, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ContractSignerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8, author = "????????? , leefangjie@qq.com")
    @SentinelResource(value = ContractSignerServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractSignerServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<ContractSigner>> queryPagedList(ContractSignerVO sample) {
        Result<PagedList<ContractSigner>> result = new Result<>();
        PagedList<ContractSigner> list = contractSignerService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
