package com.dt.platform.proxy.hr;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.hr.AttendanceData;
import com.dt.platform.domain.hr.AttendanceDataVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 考勤汇总 控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:22:47
 */
@FeignClient(value = ServiceNames.HR, contextId = AttendanceDataServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface AttendanceDataServiceProxy {

    /**
     * 基础路径 , service-hr
     */
    public static final String API_BASIC_PATH = "service-hr";

    /**
     * API 上下文路径 , hr-attendance-data
     */
    public static final String API_CONTEXT_PATH = "hr-attendance-data";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加考勤汇总
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除考勤汇总
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除考勤汇总
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新考勤汇总
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存考勤汇总
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个考勤汇总
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个考勤汇总
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询考勤汇总
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询考勤汇总
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 添加考勤汇总
     */
    @RequestMapping(AttendanceDataServiceProxy.INSERT)
    Result insert(@RequestParam(name = "attendanceDataVO") AttendanceDataVO attendanceDataVO);

    /**
     * 删除考勤汇总
     */
    @RequestMapping(AttendanceDataServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除考勤汇总
     */
    @RequestMapping(AttendanceDataServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新考勤汇总
     */
    @RequestMapping(AttendanceDataServiceProxy.UPDATE)
    Result update(@RequestParam(name = "attendanceDataVO") AttendanceDataVO attendanceDataVO);

    /**
     * 更新考勤汇总
     */
    @RequestMapping(AttendanceDataServiceProxy.SAVE)
    Result save(@RequestParam(name = "attendanceDataVO") AttendanceDataVO attendanceDataVO);

    /**
     * 获取考勤汇总
     */
    @RequestMapping(AttendanceDataServiceProxy.GET_BY_ID)
    Result<AttendanceData> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个考勤汇总
     */
    @RequestMapping(AttendanceDataServiceProxy.GET_BY_IDS)
    Result<List<AttendanceData>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询考勤汇总
     */
    @RequestMapping(AttendanceDataServiceProxy.QUERY_LIST)
    Result<List<AttendanceData>> queryList(@RequestParam(name = "sample") AttendanceDataVO sample);

    /**
     * 分页查询考勤汇总
     */
    @RequestMapping(AttendanceDataServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<AttendanceData>> queryPagedList(@RequestParam(name = "sample") AttendanceDataVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.hr.controller.AttendanceDataController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static AttendanceDataServiceProxy api() {
        return APIProxy.get(AttendanceDataServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
