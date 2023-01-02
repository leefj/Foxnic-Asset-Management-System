package com.dt.platform.proxy.hr;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.hr.PersonContract;
import com.dt.platform.domain.hr.PersonContractVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 人员合同 控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:12:02
 */
@FeignClient(value = ServiceNames.HR, contextId = PersonContractServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface PersonContractServiceProxy {

    /**
     * 基础路径 , service-hr
     */
    public static final String API_BASIC_PATH = "service-hr";

    /**
     * API 上下文路径 , hr-person-contract
     */
    public static final String API_CONTEXT_PATH = "hr-person-contract";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加人员合同
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除人员合同
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除人员合同
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新人员合同
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存人员合同
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个人员合同
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个人员合同
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询人员合同
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询人员合同
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 添加人员合同
     */
    @RequestMapping(PersonContractServiceProxy.INSERT)
    Result insert(@RequestParam(name = "personContractVO") PersonContractVO personContractVO);

    /**
     * 删除人员合同
     */
    @RequestMapping(PersonContractServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除人员合同
     */
    @RequestMapping(PersonContractServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新人员合同
     */
    @RequestMapping(PersonContractServiceProxy.UPDATE)
    Result update(@RequestParam(name = "personContractVO") PersonContractVO personContractVO);

    /**
     * 更新人员合同
     */
    @RequestMapping(PersonContractServiceProxy.SAVE)
    Result save(@RequestParam(name = "personContractVO") PersonContractVO personContractVO);

    /**
     * 获取人员合同
     */
    @RequestMapping(PersonContractServiceProxy.GET_BY_ID)
    Result<PersonContract> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个人员合同
     */
    @RequestMapping(PersonContractServiceProxy.GET_BY_IDS)
    Result<List<PersonContract>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询人员合同
     */
    @RequestMapping(PersonContractServiceProxy.QUERY_LIST)
    Result<List<PersonContract>> queryList(@RequestParam(name = "sample") PersonContractVO sample);

    /**
     * 分页查询人员合同
     */
    @RequestMapping(PersonContractServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<PersonContract>> queryPagedList(@RequestParam(name = "sample") PersonContractVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.hr.controller.PersonContractController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static PersonContractServiceProxy api() {
        return APIProxy.get(PersonContractServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
