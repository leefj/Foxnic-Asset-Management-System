package com.dt.platform.proxy.hr;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.hr.PositionType;
import com.dt.platform.domain.hr.PositionTypeVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 岗位类型 控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:12:33
 */
@FeignClient(value = ServiceNames.HR, contextId = PositionTypeServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface PositionTypeServiceProxy {

    /**
     * 基础路径 , service-hr
     */
    public static final String API_BASIC_PATH = "service-hr";

    /**
     * API 上下文路径 , hr-position-type
     */
    public static final String API_CONTEXT_PATH = "hr-position-type";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加岗位类型
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除岗位类型
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除岗位类型
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新岗位类型
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存岗位类型
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个岗位类型
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个岗位类型
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询岗位类型
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询岗位类型
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 添加岗位类型
     */
    @RequestMapping(PositionTypeServiceProxy.INSERT)
    Result insert(@RequestParam(name = "positionTypeVO") PositionTypeVO positionTypeVO);

    /**
     * 删除岗位类型
     */
    @RequestMapping(PositionTypeServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除岗位类型
     */
    @RequestMapping(PositionTypeServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新岗位类型
     */
    @RequestMapping(PositionTypeServiceProxy.UPDATE)
    Result update(@RequestParam(name = "positionTypeVO") PositionTypeVO positionTypeVO);

    /**
     * 更新岗位类型
     */
    @RequestMapping(PositionTypeServiceProxy.SAVE)
    Result save(@RequestParam(name = "positionTypeVO") PositionTypeVO positionTypeVO);

    /**
     * 获取岗位类型
     */
    @RequestMapping(PositionTypeServiceProxy.GET_BY_ID)
    Result<PositionType> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个岗位类型
     */
    @RequestMapping(PositionTypeServiceProxy.GET_BY_IDS)
    Result<List<PositionType>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询岗位类型
     */
    @RequestMapping(PositionTypeServiceProxy.QUERY_LIST)
    Result<List<PositionType>> queryList(@RequestParam(name = "sample") PositionTypeVO sample);

    /**
     * 分页查询岗位类型
     */
    @RequestMapping(PositionTypeServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<PositionType>> queryPagedList(@RequestParam(name = "sample") PositionTypeVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.hr.controller.PositionTypeController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static PositionTypeServiceProxy api() {
        return APIProxy.get(PositionTypeServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
