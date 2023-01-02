package com.dt.platform.proxy.hr;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.hr.AssessmentGrade;
import com.dt.platform.domain.hr.AssessmentGradeVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 考核评级 控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:11:51
 */
@FeignClient(value = ServiceNames.HR, contextId = AssessmentGradeServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface AssessmentGradeServiceProxy {

    /**
     * 基础路径 , service-hr
     */
    public static final String API_BASIC_PATH = "service-hr";

    /**
     * API 上下文路径 , hr-assessment-grade
     */
    public static final String API_CONTEXT_PATH = "hr-assessment-grade";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加考核评级
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除考核评级
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除考核评级
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新考核评级
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存考核评级
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个考核评级
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个考核评级
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询考核评级
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询考核评级
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 添加考核评级
     */
    @RequestMapping(AssessmentGradeServiceProxy.INSERT)
    Result insert(@RequestParam(name = "assessmentGradeVO") AssessmentGradeVO assessmentGradeVO);

    /**
     * 删除考核评级
     */
    @RequestMapping(AssessmentGradeServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除考核评级
     */
    @RequestMapping(AssessmentGradeServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新考核评级
     */
    @RequestMapping(AssessmentGradeServiceProxy.UPDATE)
    Result update(@RequestParam(name = "assessmentGradeVO") AssessmentGradeVO assessmentGradeVO);

    /**
     * 更新考核评级
     */
    @RequestMapping(AssessmentGradeServiceProxy.SAVE)
    Result save(@RequestParam(name = "assessmentGradeVO") AssessmentGradeVO assessmentGradeVO);

    /**
     * 获取考核评级
     */
    @RequestMapping(AssessmentGradeServiceProxy.GET_BY_ID)
    Result<AssessmentGrade> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个考核评级
     */
    @RequestMapping(AssessmentGradeServiceProxy.GET_BY_IDS)
    Result<List<AssessmentGrade>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询考核评级
     */
    @RequestMapping(AssessmentGradeServiceProxy.QUERY_LIST)
    Result<List<AssessmentGrade>> queryList(@RequestParam(name = "sample") AssessmentGradeVO sample);

    /**
     * 分页查询考核评级
     */
    @RequestMapping(AssessmentGradeServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<AssessmentGrade>> queryPagedList(@RequestParam(name = "sample") AssessmentGradeVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.hr.controller.AssessmentGradeController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static AssessmentGradeServiceProxy api() {
        return APIProxy.get(AssessmentGradeServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
