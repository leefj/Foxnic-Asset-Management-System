package com.dt.platform.proxy.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.common.ScreenDsApiS;
import com.dt.platform.domain.common.ScreenDsApiSVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * API选择 控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-10-28 07:30:08
 */
@FeignClient(value = ServiceNames.COMMON, contextId = ScreenDsApiSServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface ScreenDsApiSServiceProxy {

    /**
     * 基础路径 , service-common
     */
    public static final String API_BASIC_PATH = "service-common";

    /**
     * API 上下文路径 , sys-screen-ds-api-s
     */
    public static final String API_CONTEXT_PATH = "sys-screen-ds-api-s";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加API选择
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除API选择
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除API选择
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新API选择
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存API选择
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个API选择
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个API选择
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询API选择
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询API选择
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 添加API选择
     */
    @RequestMapping(ScreenDsApiSServiceProxy.INSERT)
    Result insert(@RequestParam(name = "screenDsApiSVO") ScreenDsApiSVO screenDsApiSVO);

    /**
     * 删除API选择
     */
    @RequestMapping(ScreenDsApiSServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除API选择
     */
    @RequestMapping(ScreenDsApiSServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新API选择
     */
    @RequestMapping(ScreenDsApiSServiceProxy.UPDATE)
    Result update(@RequestParam(name = "screenDsApiSVO") ScreenDsApiSVO screenDsApiSVO);

    /**
     * 更新API选择
     */
    @RequestMapping(ScreenDsApiSServiceProxy.SAVE)
    Result save(@RequestParam(name = "screenDsApiSVO") ScreenDsApiSVO screenDsApiSVO);

    /**
     * 获取API选择
     */
    @RequestMapping(ScreenDsApiSServiceProxy.GET_BY_ID)
    Result<ScreenDsApiS> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个API选择
     */
    @RequestMapping(ScreenDsApiSServiceProxy.GET_BY_IDS)
    Result<List<ScreenDsApiS>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询API选择
     */
    @RequestMapping(ScreenDsApiSServiceProxy.QUERY_LIST)
    Result<List<ScreenDsApiS>> queryList(@RequestParam(name = "sample") ScreenDsApiSVO sample);

    /**
     * 分页查询API选择
     */
    @RequestMapping(ScreenDsApiSServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<ScreenDsApiS>> queryPagedList(@RequestParam(name = "sample") ScreenDsApiSVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.common.controller.ScreenDsApiSController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static ScreenDsApiSServiceProxy api() {
        return APIProxy.get(ScreenDsApiSServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
