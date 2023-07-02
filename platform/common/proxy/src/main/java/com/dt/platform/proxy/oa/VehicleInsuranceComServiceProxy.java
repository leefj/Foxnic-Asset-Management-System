package com.dt.platform.proxy.oa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.oa.VehicleInsuranceCom;
import com.dt.platform.domain.oa.VehicleInsuranceComVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 车辆保险公司 控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-06-10 13:27:53
 */
@FeignClient(value = ServiceNames.OA, contextId = VehicleInsuranceComServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface VehicleInsuranceComServiceProxy {

    /**
     * 基础路径 , service-oa
     */
    public static final String API_BASIC_PATH = "service-oa";

    /**
     * API 上下文路径 , oa-vehicle-insurance-com
     */
    public static final String API_CONTEXT_PATH = "oa-vehicle-insurance-com";

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
    @RequestMapping(VehicleInsuranceComServiceProxy.INSERT)
    Result insert(@RequestParam(name = "vehicleInsuranceComVO") VehicleInsuranceComVO vehicleInsuranceComVO);

    /**
     * 删除车辆保险公司
     */
    @RequestMapping(VehicleInsuranceComServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除车辆保险公司
     */
    @RequestMapping(VehicleInsuranceComServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新车辆保险公司
     */
    @RequestMapping(VehicleInsuranceComServiceProxy.UPDATE)
    Result update(@RequestParam(name = "vehicleInsuranceComVO") VehicleInsuranceComVO vehicleInsuranceComVO);

    /**
     * 更新车辆保险公司
     */
    @RequestMapping(VehicleInsuranceComServiceProxy.SAVE)
    Result save(@RequestParam(name = "vehicleInsuranceComVO") VehicleInsuranceComVO vehicleInsuranceComVO);

    /**
     * 获取车辆保险公司
     */
    @RequestMapping(VehicleInsuranceComServiceProxy.GET_BY_ID)
    Result<VehicleInsuranceCom> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个车辆保险公司
     */
    @RequestMapping(VehicleInsuranceComServiceProxy.GET_BY_IDS)
    Result<List<VehicleInsuranceCom>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询车辆保险公司
     */
    @RequestMapping(VehicleInsuranceComServiceProxy.QUERY_LIST)
    Result<List<VehicleInsuranceCom>> queryList(@RequestParam(name = "sample") VehicleInsuranceComVO sample);

    /**
     * 分页查询车辆保险公司
     */
    @RequestMapping(VehicleInsuranceComServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<VehicleInsuranceCom>> queryPagedList(@RequestParam(name = "sample") VehicleInsuranceComVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.oa.controller.VehicleInsuranceComController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static VehicleInsuranceComServiceProxy api() {
        return APIProxy.get(VehicleInsuranceComServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}