package com.dt.platform.hr.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.hr.VacationDataServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 人员休假模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:35:28
*/

@Controller("HrVacationDataPageController")
@RequestMapping(VacationDataPageController.prefix)
public class VacationDataPageController extends ViewController {
	
	public static final String prefix="business/hr/vacation_data";

	private VacationDataServiceProxy proxy;
	
	/**
	 * 获得代理对象<br> 
	 * 1、单体应用时，在应用内部调用；<br> 
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br> 
	 * 3、微服务时，通过feign调用; <br> 
	 * */
	public VacationDataServiceProxy proxy() {
		if(proxy==null) {
			proxy=VacationDataServiceProxy.api();
		}
		return proxy;
	}
	
	/**
	 * 人员休假 功能主页面
	 */
	@RequestMapping("/vacation_data_list.html")
	public String list(Model model,HttpServletRequest request) {
		return prefix+"/vacation_data_list";
	}

	/**
	 * 人员休假 表单页面
	 */
	@RequestMapping("/vacation_data_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return prefix+"/vacation_data_form";
	}
}