package com.dt.platform.oa.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.oa.VehicleSelectItemServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 车辆数据模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-06-10 13:50:38
*/

@Controller("OaVehicleSelectItemPageController")
@RequestMapping(VehicleSelectItemPageController.prefix)
public class VehicleSelectItemPageController extends ViewController {

	public static final String prefix="business/oa/vehicle_select_item";

	private VehicleSelectItemServiceProxy proxy;

	/**
	 * 获得代理对象<br>
	 * 1、单体应用时，在应用内部调用；<br>
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br>
	 * 3、微服务时，通过feign调用; <br>
	 * */
	public VehicleSelectItemServiceProxy proxy() {
		if(proxy==null) {
			proxy=VehicleSelectItemServiceProxy.api();
		}
		return proxy;
	}

	/**
	 * 车辆数据 功能主页面
	 */
	@RequestMapping("/vehicle_select_item_list.html")
	public String list(Model model,HttpServletRequest request) {
		return getTemplatePath(prefix,"vehicle_select_item_list");
	}

	/**
	 * 车辆数据 表单页面
	 */
	@RequestMapping("/vehicle_select_item_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return getTemplatePath(prefix,"vehicle_select_item_form");
	}
}