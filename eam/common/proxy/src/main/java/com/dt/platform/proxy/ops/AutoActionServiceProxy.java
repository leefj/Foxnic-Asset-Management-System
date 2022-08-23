package com.dt.platform.proxy.ops;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.ops.AutoAction;
import com.dt.platform.domain.ops.AutoActionVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 执行任务  控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-08-23 15:52:11
 */
@FeignClient(value = ServiceNames.OPS, contextId = AutoActionServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface AutoActionServiceProxy {

    /**
     * 基础路径 , service-ops
     */
    public static final String API_BASIC_PATH = "service-ops";

    /**
     * API 上下文路径 , ops-auto-action
     */
    public static final String API_CONTEXT_PATH = "ops-auto-action";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加执行任务
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除执行任务
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除执行任务
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新执行任务
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存执行任务
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个执行任务
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个执行任务
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询执行任务
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询执行任务
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 添加执行任务
     */
    @RequestMapping(AutoActionServiceProxy.INSERT)
    Result insert(@RequestParam(name = "autoActionVO") AutoActionVO autoActionVO);

    /**
     * 删除执行任务
     */
    @RequestMapping(AutoActionServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除执行任务
     */
    @RequestMapping(AutoActionServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新执行任务
     */
    @RequestMapping(AutoActionServiceProxy.UPDATE)
    Result update(@RequestParam(name = "autoActionVO") AutoActionVO autoActionVO);

    /**
     * 更新执行任务
     */
    @RequestMapping(AutoActionServiceProxy.SAVE)
    Result save(@RequestParam(name = "autoActionVO") AutoActionVO autoActionVO);

    /**
     * 获取执行任务
     */
    @RequestMapping(AutoActionServiceProxy.GET_BY_ID)
    Result<AutoAction> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个执行任务
     */
    @RequestMapping(AutoActionServiceProxy.GET_BY_IDS)
    Result<List<AutoAction>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询执行任务
     */
    @RequestMapping(AutoActionServiceProxy.QUERY_LIST)
    Result<List<AutoAction>> queryList(@RequestParam(name = "sample") AutoActionVO sample);

    /**
     * 分页查询执行任务
     */
    @RequestMapping(AutoActionServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<AutoAction>> queryPagedList(@RequestParam(name = "sample") AutoActionVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.ops.controller.AutoActionController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static AutoActionServiceProxy api() {
        return APIProxy.get(AutoActionServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}