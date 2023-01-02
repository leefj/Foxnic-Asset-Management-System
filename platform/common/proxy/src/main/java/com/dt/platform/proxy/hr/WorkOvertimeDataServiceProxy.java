package com.dt.platform.proxy.hr;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.hr.WorkOvertimeData;
import com.dt.platform.domain.hr.WorkOvertimeDataVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 人员加班 控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:37:42
 */
@FeignClient(value = ServiceNames.HR, contextId = WorkOvertimeDataServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface WorkOvertimeDataServiceProxy {

    /**
     * 基础路径 , service-hr
     */
    public static final String API_BASIC_PATH = "service-hr";

    /**
     * API 上下文路径 , hr-work-overtime-data
     */
    public static final String API_CONTEXT_PATH = "hr-work-overtime-data";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加人员加班
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除人员加班
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除人员加班
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新人员加班
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存人员加班
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个人员加班
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个人员加班
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询人员加班
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询人员加班
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 添加人员加班
     */
    @RequestMapping(WorkOvertimeDataServiceProxy.INSERT)
    Result insert(@RequestParam(name = "workOvertimeDataVO") WorkOvertimeDataVO workOvertimeDataVO);

    /**
     * 删除人员加班
     */
    @RequestMapping(WorkOvertimeDataServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除人员加班
     */
    @RequestMapping(WorkOvertimeDataServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新人员加班
     */
    @RequestMapping(WorkOvertimeDataServiceProxy.UPDATE)
    Result update(@RequestParam(name = "workOvertimeDataVO") WorkOvertimeDataVO workOvertimeDataVO);

    /**
     * 更新人员加班
     */
    @RequestMapping(WorkOvertimeDataServiceProxy.SAVE)
    Result save(@RequestParam(name = "workOvertimeDataVO") WorkOvertimeDataVO workOvertimeDataVO);

    /**
     * 获取人员加班
     */
    @RequestMapping(WorkOvertimeDataServiceProxy.GET_BY_ID)
    Result<WorkOvertimeData> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个人员加班
     */
    @RequestMapping(WorkOvertimeDataServiceProxy.GET_BY_IDS)
    Result<List<WorkOvertimeData>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询人员加班
     */
    @RequestMapping(WorkOvertimeDataServiceProxy.QUERY_LIST)
    Result<List<WorkOvertimeData>> queryList(@RequestParam(name = "sample") WorkOvertimeDataVO sample);

    /**
     * 分页查询人员加班
     */
    @RequestMapping(WorkOvertimeDataServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<WorkOvertimeData>> queryPagedList(@RequestParam(name = "sample") WorkOvertimeDataVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.hr.controller.WorkOvertimeDataController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static WorkOvertimeDataServiceProxy api() {
        return APIProxy.get(WorkOvertimeDataServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
