package com.dt.platform.proxy.eam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.eam.AssetStatusRule;
import com.dt.platform.domain.eam.AssetStatusRuleVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 状态规则 控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2024-04-30 13:29:34
 */
@FeignClient(value = ServiceNames.EAM, contextId = AssetStatusRuleServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface AssetStatusRuleServiceProxy {

    /**
     * 基础路径 , service-eam
     */
    public static final String API_BASIC_PATH = "service-eam";

    /**
     * API 上下文路径 , eam-asset-status-rule
     */
    public static final String API_CONTEXT_PATH = "eam-asset-status-rule";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加状态规则
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除状态规则
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除状态规则
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新状态规则
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存状态规则
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个状态规则
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个状态规则
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询状态规则
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询状态规则
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 添加状态规则
     */
    @RequestMapping(AssetStatusRuleServiceProxy.INSERT)
    Result insert(@RequestParam(name = "assetStatusRuleVO") AssetStatusRuleVO assetStatusRuleVO);

    /**
     * 删除状态规则
     */
    @RequestMapping(AssetStatusRuleServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除状态规则
     */
    @RequestMapping(AssetStatusRuleServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新状态规则
     */
    @RequestMapping(AssetStatusRuleServiceProxy.UPDATE)
    Result update(@RequestParam(name = "assetStatusRuleVO") AssetStatusRuleVO assetStatusRuleVO);

    /**
     * 更新状态规则
     */
    @RequestMapping(AssetStatusRuleServiceProxy.SAVE)
    Result save(@RequestParam(name = "assetStatusRuleVO") AssetStatusRuleVO assetStatusRuleVO);

    /**
     * 获取状态规则
     */
    @RequestMapping(AssetStatusRuleServiceProxy.GET_BY_ID)
    Result<AssetStatusRule> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个状态规则
     */
    @RequestMapping(AssetStatusRuleServiceProxy.GET_BY_IDS)
    Result<List<AssetStatusRule>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询状态规则
     */
    @RequestMapping(AssetStatusRuleServiceProxy.QUERY_LIST)
    Result<List<AssetStatusRule>> queryList(@RequestParam(name = "sample") AssetStatusRuleVO sample);

    /**
     * 分页查询状态规则
     */
    @RequestMapping(AssetStatusRuleServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<AssetStatusRule>> queryPagedList(@RequestParam(name = "sample") AssetStatusRuleVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.eam.controller.AssetStatusRuleController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static AssetStatusRuleServiceProxy api() {
        return APIProxy.get(AssetStatusRuleServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
