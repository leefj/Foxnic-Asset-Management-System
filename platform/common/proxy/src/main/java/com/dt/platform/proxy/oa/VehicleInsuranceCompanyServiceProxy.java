package com.dt.platform.proxy.oa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.oa.VehicleInsuranceCompany;
import com.dt.platform.domain.oa.VehicleInsuranceCompanyVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 车辆保险公司 控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-06-26 07:44:20
 */
@FeignClient(value = ServiceNames.OA, contextId = VehicleInsuranceCompanyServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface VehicleInsuranceCompanyServiceProxy {

    /**
     * 基础路径 , service-oa
     */
    public static final String API_BASIC_PATH = "service-oa";

    /**
     * API 上下文路径 , oa-vehicle-insurance-company
     */
    public static final String API_CONTEXT_PATH = "oa-vehicle-insurance-company";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加车辆保险公司
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除车辆保险公司
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除车辆保险公司
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新车辆保险公司
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存车辆保险公司
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个车辆保险公司
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个车辆保险公司
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询车辆保险公司
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询车辆保险公司
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 添加车辆保险公司
     */
    @RequestMapping(VehicleInsuranceCompanyServiceProxy.INSERT)
    Result insert(@RequestParam(name = "vehicleInsuranceCompanyVO") VehicleInsuranceCompanyVO vehicleInsuranceCompanyVO);

    /**
     * 删除车辆保险公司
     */
    @RequestMapping(VehicleInsuranceCompanyServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除车辆保险公司
     */
    @RequestMapping(VehicleInsuranceCompanyServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新车辆保险公司
     */
    @RequestMapping(VehicleInsuranceCompanyServiceProxy.UPDATE)
    Result update(@RequestParam(name = "vehicleInsuranceCompanyVO") VehicleInsuranceCompanyVO vehicleInsuranceCompanyVO);

    /**
     * 更新车辆保险公司
     */
    @RequestMapping(VehicleInsuranceCompanyServiceProxy.SAVE)
    Result save(@RequestParam(name = "vehicleInsuranceCompanyVO") VehicleInsuranceCompanyVO vehicleInsuranceCompanyVO);

    /**
     * 获取车辆保险公司
     */
    @RequestMapping(VehicleInsuranceCompanyServiceProxy.GET_BY_ID)
    Result<VehicleInsuranceCompany> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个车辆保险公司
     */
    @RequestMapping(VehicleInsuranceCompanyServiceProxy.GET_BY_IDS)
    Result<List<VehicleInsuranceCompany>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询车辆保险公司
     */
    @RequestMapping(VehicleInsuranceCompanyServiceProxy.QUERY_LIST)
    Result<List<VehicleInsuranceCompany>> queryList(@RequestParam(name = "sample") VehicleInsuranceCompanyVO sample);

    /**
     * 分页查询车辆保险公司
     */
    @RequestMapping(VehicleInsuranceCompanyServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<VehicleInsuranceCompany>> queryPagedList(@RequestParam(name = "sample") VehicleInsuranceCompanyVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.oa.controller.VehicleInsuranceCompanyController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static VehicleInsuranceCompanyServiceProxy api() {
        return APIProxy.get(VehicleInsuranceCompanyServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
