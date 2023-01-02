package com.dt.platform.hr.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.hr.BusinessTravelDataServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 人员出差模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:31:45
*/

@Controller("HrBusinessTravelDataPageController")
@RequestMapping(BusinessTravelDataPageController.prefix)
public class BusinessTravelDataPageController extends ViewController {
	
	public static final String prefix="business/hr/business_travel_data";

	private BusinessTravelDataServiceProxy proxy;
	
	/**
	 * 获得代理对象<br> 
	 * 1、单体应用时，在应用内部调用；<br> 
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br> 
	 * 3、微服务时，通过feign调用; <br> 
	 * */
	public BusinessTravelDataServiceProxy proxy() {
		if(proxy==null) {
			proxy=BusinessTravelDataServiceProxy.api();
		}
		return proxy;
	}
	
	/**
	 * 人员出差 功能主页面
	 */
	@RequestMapping("/business_travel_data_list.html")
	public String list(Model model,HttpServletRequest request) {
		return prefix+"/business_travel_data_list";
	}

	/**
	 * 人员出差 表单页面
	 */
	@RequestMapping("/business_travel_data_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return prefix+"/business_travel_data_form";
	}
}