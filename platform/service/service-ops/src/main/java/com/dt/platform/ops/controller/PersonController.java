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
import com.dt.platform.proxy.ops.PersonServiceProxy;
import com.dt.platform.domain.ops.meta.PersonVOMeta;
import com.dt.platform.domain.ops.Person;
import com.dt.platform.domain.ops.PersonVO;
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
import com.dt.platform.domain.ops.meta.PersonMeta;
import org.github.foxnic.web.domain.system.DictItem;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IPersonService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-12 21:51:48
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsPersonController")
public class PersonController extends SuperController {

    @Autowired
    private IPersonService personService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PersonVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "591581191656701952"),
		@ApiImplicitParam(name = PersonVOMeta.COMPANY_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = PersonVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = PersonVOMeta.PERSON_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "business"),
		@ApiImplicitParam(name = PersonVOMeta.CONTACT_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class, example = "13726588768"),
		@ApiImplicitParam(name = PersonVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PersonVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = PersonServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PersonServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(PersonVO personVO) {
        Result result = personService.insert(personVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PersonVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "591581191656701952")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = PersonServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PersonServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  personService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = personService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PersonVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = PersonServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PersonServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = personService.hasRefers(ids);
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
            Result result = personService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = personService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = PersonVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "591581191656701952"),
		@ApiImplicitParam(name = PersonVOMeta.COMPANY_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = PersonVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = PersonVOMeta.PERSON_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "business"),
		@ApiImplicitParam(name = PersonVOMeta.CONTACT_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class, example = "13726588768"),
		@ApiImplicitParam(name = PersonVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PersonVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { PersonVOMeta.PAGE_INDEX, PersonVOMeta.PAGE_SIZE, PersonVOMeta.SEARCH_FIELD, PersonVOMeta.FUZZY_FIELD, PersonVOMeta.SEARCH_VALUE, PersonVOMeta.DIRTY_FIELDS, PersonVOMeta.SORT_FIELD, PersonVOMeta.SORT_TYPE, PersonVOMeta.IDS })
    @SentinelResource(value = PersonServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PersonServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(PersonVO personVO) {
        Result result = personService.update(personVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PersonVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "591581191656701952"),
		@ApiImplicitParam(name = PersonVOMeta.COMPANY_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = PersonVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = PersonVOMeta.PERSON_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "business"),
		@ApiImplicitParam(name = PersonVOMeta.CONTACT_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class, example = "13726588768"),
		@ApiImplicitParam(name = PersonVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PersonVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { PersonVOMeta.PAGE_INDEX, PersonVOMeta.PAGE_SIZE, PersonVOMeta.SEARCH_FIELD, PersonVOMeta.FUZZY_FIELD, PersonVOMeta.SEARCH_VALUE, PersonVOMeta.DIRTY_FIELDS, PersonVOMeta.SORT_FIELD, PersonVOMeta.SORT_TYPE, PersonVOMeta.IDS })
    @SentinelResource(value = PersonServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PersonServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(PersonVO personVO) {
        Result result = personService.save(personVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PersonVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = PersonServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PersonServiceProxy.GET_BY_ID)
    public Result<Person> getById(String id) {
        Result<Person> result = new Result<>();
        Person person = personService.getById(id);
        // join ???????????????
        personService.dao().fill(person).with(PersonMeta.PERSON_TYPE_DICT).execute();
        result.success(true).data(person);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PersonVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = PersonServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PersonServiceProxy.GET_BY_IDS)
    public Result<List<Person>> getByIds(List<String> ids) {
        Result<List<Person>> result = new Result<>();
        List<Person> list = personService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PersonVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "591581191656701952"),
		@ApiImplicitParam(name = PersonVOMeta.COMPANY_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = PersonVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = PersonVOMeta.PERSON_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "business"),
		@ApiImplicitParam(name = PersonVOMeta.CONTACT_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class, example = "13726588768"),
		@ApiImplicitParam(name = PersonVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PersonVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { PersonVOMeta.PAGE_INDEX, PersonVOMeta.PAGE_SIZE })
    @SentinelResource(value = PersonServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PersonServiceProxy.QUERY_LIST)
    public Result<List<Person>> queryList(PersonVO sample) {
        Result<List<Person>> result = new Result<>();
        List<Person> list = personService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PersonVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "591581191656701952"),
		@ApiImplicitParam(name = PersonVOMeta.COMPANY_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = PersonVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = PersonVOMeta.PERSON_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "business"),
		@ApiImplicitParam(name = PersonVOMeta.CONTACT_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class, example = "13726588768"),
		@ApiImplicitParam(name = PersonVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PersonVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = PersonServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PersonServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<Person>> queryPagedList(PersonVO sample) {
        Result<PagedList<Person>> result = new Result<>();
        PagedList<Person> list = personService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        personService.dao().fill(list).with(PersonMeta.PERSON_TYPE_DICT).execute();
        result.success(true).data(list);
        return result;
    }
}
