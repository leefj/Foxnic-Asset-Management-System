package com.dt.platform.proxy.hr;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.hr.AttendanceRecordImport;
import com.dt.platform.domain.hr.AttendanceRecordImportVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 考勤导入 控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:26:30
 */
@FeignClient(value = ServiceNames.HR, contextId = AttendanceRecordImportServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface AttendanceRecordImportServiceProxy {

    /**
     * 基础路径 , service-hr
     */
    public static final String API_BASIC_PATH = "service-hr";

    /**
     * API 上下文路径 , hr-attendance-record-import
     */
    public static final String API_CONTEXT_PATH = "hr-attendance-record-import";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加考勤导入
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除考勤导入
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除考勤导入
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新考勤导入
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存考勤导入
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个考勤导入
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个考勤导入
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询考勤导入
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询考勤导入
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 添加考勤导入
     */
    @RequestMapping(AttendanceRecordImportServiceProxy.INSERT)
    Result insert(@RequestParam(name = "attendanceRecordImportVO") AttendanceRecordImportVO attendanceRecordImportVO);

    /**
     * 删除考勤导入
     */
    @RequestMapping(AttendanceRecordImportServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除考勤导入
     */
    @RequestMapping(AttendanceRecordImportServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新考勤导入
     */
    @RequestMapping(AttendanceRecordImportServiceProxy.UPDATE)
    Result update(@RequestParam(name = "attendanceRecordImportVO") AttendanceRecordImportVO attendanceRecordImportVO);

    /**
     * 更新考勤导入
     */
    @RequestMapping(AttendanceRecordImportServiceProxy.SAVE)
    Result save(@RequestParam(name = "attendanceRecordImportVO") AttendanceRecordImportVO attendanceRecordImportVO);

    /**
     * 获取考勤导入
     */
    @RequestMapping(AttendanceRecordImportServiceProxy.GET_BY_ID)
    Result<AttendanceRecordImport> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个考勤导入
     */
    @RequestMapping(AttendanceRecordImportServiceProxy.GET_BY_IDS)
    Result<List<AttendanceRecordImport>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询考勤导入
     */
    @RequestMapping(AttendanceRecordImportServiceProxy.QUERY_LIST)
    Result<List<AttendanceRecordImport>> queryList(@RequestParam(name = "sample") AttendanceRecordImportVO sample);

    /**
     * 分页查询考勤导入
     */
    @RequestMapping(AttendanceRecordImportServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<AttendanceRecordImport>> queryPagedList(@RequestParam(name = "sample") AttendanceRecordImportVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.hr.controller.AttendanceRecordImportController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static AttendanceRecordImportServiceProxy api() {
        return APIProxy.get(AttendanceRecordImportServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
