package com.dt.platform.proxy.hr;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.hr.ProfessionalLevel;
import com.dt.platform.domain.hr.ProfessionalLevelVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 职业评级 控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-12-28 19:17:56
 */
@FeignClient(value = ServiceNames.HR, contextId = ProfessionalLevelServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface ProfessionalLevelServiceProxy {

    /**
     * 基础路径 , service-hr
     */
    public static final String API_BASIC_PATH = "service-hr";

    /**
     * API 上下文路径 , hr-professional-level
     */
    public static final String API_CONTEXT_PATH = "hr-professional-level";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加职业评级
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除职业评级
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除职业评级
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新职业评级
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存职业评级
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个职业评级
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个职业评级
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询职业评级
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询职业评级
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 添加职业评级
     */
    @RequestMapping(ProfessionalLevelServiceProxy.INSERT)
    Result insert(@RequestParam(name = "professionalLevelVO") ProfessionalLevelVO professionalLevelVO);

    /**
     * 删除职业评级
     */
    @RequestMapping(ProfessionalLevelServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除职业评级
     */
    @RequestMapping(ProfessionalLevelServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新职业评级
     */
    @RequestMapping(ProfessionalLevelServiceProxy.UPDATE)
    Result update(@RequestParam(name = "professionalLevelVO") ProfessionalLevelVO professionalLevelVO);

    /**
     * 更新职业评级
     */
    @RequestMapping(ProfessionalLevelServiceProxy.SAVE)
    Result save(@RequestParam(name = "professionalLevelVO") ProfessionalLevelVO professionalLevelVO);

    /**
     * 获取职业评级
     */
    @RequestMapping(ProfessionalLevelServiceProxy.GET_BY_ID)
    Result<ProfessionalLevel> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个职业评级
     */
    @RequestMapping(ProfessionalLevelServiceProxy.GET_BY_IDS)
    Result<List<ProfessionalLevel>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询职业评级
     */
    @RequestMapping(ProfessionalLevelServiceProxy.QUERY_LIST)
    Result<List<ProfessionalLevel>> queryList(@RequestParam(name = "sample") ProfessionalLevelVO sample);

    /**
     * 分页查询职业评级
     */
    @RequestMapping(ProfessionalLevelServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<ProfessionalLevel>> queryPagedList(@RequestParam(name = "sample") ProfessionalLevelVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.hr.controller.ProfessionalLevelController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static ProfessionalLevelServiceProxy api() {
        return APIProxy.get(ProfessionalLevelServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
