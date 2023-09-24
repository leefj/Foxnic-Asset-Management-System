package com.dt.platform.proxy.oa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.oa.CrmCustomerLevel;
import com.dt.platform.domain.oa.CrmCustomerLevelVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 客户等级 控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-09-13 07:31:32
 */
@FeignClient(value = ServiceNames.OA, contextId = CrmCustomerLevelServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface CrmCustomerLevelServiceProxy {

    /**
     * 基础路径 , service-oa
     */
    public static final String API_BASIC_PATH = "service-oa";

    /**
     * API 上下文路径 , oa-crm-customer-level
     */
    public static final String API_CONTEXT_PATH = "oa-crm-customer-level";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加客户等级
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除客户等级
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除客户等级
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新客户等级
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存客户等级
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个客户等级
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个客户等级
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询客户等级
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询客户等级
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 添加客户等级
     */
    @RequestMapping(CrmCustomerLevelServiceProxy.INSERT)
    Result insert(@RequestParam(name = "crmCustomerLevelVO") CrmCustomerLevelVO crmCustomerLevelVO);

    /**
     * 删除客户等级
     */
    @RequestMapping(CrmCustomerLevelServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除客户等级
     */
    @RequestMapping(CrmCustomerLevelServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新客户等级
     */
    @RequestMapping(CrmCustomerLevelServiceProxy.UPDATE)
    Result update(@RequestParam(name = "crmCustomerLevelVO") CrmCustomerLevelVO crmCustomerLevelVO);

    /**
     * 更新客户等级
     */
    @RequestMapping(CrmCustomerLevelServiceProxy.SAVE)
    Result save(@RequestParam(name = "crmCustomerLevelVO") CrmCustomerLevelVO crmCustomerLevelVO);

    /**
     * 获取客户等级
     */
    @RequestMapping(CrmCustomerLevelServiceProxy.GET_BY_ID)
    Result<CrmCustomerLevel> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个客户等级
     */
    @RequestMapping(CrmCustomerLevelServiceProxy.GET_BY_IDS)
    Result<List<CrmCustomerLevel>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询客户等级
     */
    @RequestMapping(CrmCustomerLevelServiceProxy.QUERY_LIST)
    Result<List<CrmCustomerLevel>> queryList(@RequestParam(name = "sample") CrmCustomerLevelVO sample);

    /**
     * 分页查询客户等级
     */
    @RequestMapping(CrmCustomerLevelServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<CrmCustomerLevel>> queryPagedList(@RequestParam(name = "sample") CrmCustomerLevelVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.oa.controller.CrmCustomerLevelController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static CrmCustomerLevelServiceProxy api() {
        return APIProxy.get(CrmCustomerLevelServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}