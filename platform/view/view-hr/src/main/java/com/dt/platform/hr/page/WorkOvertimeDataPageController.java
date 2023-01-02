package com.dt.platform.hr.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.hr.WorkOvertimeDataServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 人员加班模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:37:42
*/

@Controller("HrWorkOvertimeDataPageController")
@RequestMapping(WorkOvertimeDataPageController.prefix)
public class WorkOvertimeDataPageController extends ViewController {
	
	public static final String prefix="business/hr/work_overtime_data";

	private WorkOvertimeDataServiceProxy proxy;
	
	/**
	 * 获得代理对象<br> 
	 * 1、单体应用时，在应用内部调用；<br> 
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br> 
	 * 3、微服务时，通过feign调用; <br> 
	 * */
	public WorkOvertimeDataServiceProxy proxy() {
		if(proxy==null) {
			proxy=WorkOvertimeDataServiceProxy.api();
		}
		return proxy;
	}
	
	/**
	 * 人员加班 功能主页面
	 */
	@RequestMapping("/work_overtime_data_list.html")
	public String list(Model model,HttpServletRequest request) {
		return prefix+"/work_overtime_data_list";
	}

	/**
	 * 人员加班 表单页面
	 */
	@RequestMapping("/work_overtime_data_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return prefix+"/work_overtime_data_form";
	}
}