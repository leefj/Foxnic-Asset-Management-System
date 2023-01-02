package com.dt.platform.hr.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.hr.AttendanceDataServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 考勤汇总模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:22:47
*/

@Controller("HrAttendanceDataPageController")
@RequestMapping(AttendanceDataPageController.prefix)
public class AttendanceDataPageController extends ViewController {
	
	public static final String prefix="business/hr/attendance_data";

	private AttendanceDataServiceProxy proxy;
	
	/**
	 * 获得代理对象<br> 
	 * 1、单体应用时，在应用内部调用；<br> 
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br> 
	 * 3、微服务时，通过feign调用; <br> 
	 * */
	public AttendanceDataServiceProxy proxy() {
		if(proxy==null) {
			proxy=AttendanceDataServiceProxy.api();
		}
		return proxy;
	}
	
	/**
	 * 考勤汇总 功能主页面
	 */
	@RequestMapping("/attendance_data_list.html")
	public String list(Model model,HttpServletRequest request) {
		return prefix+"/attendance_data_list";
	}

	/**
	 * 考勤汇总 表单页面
	 */
	@RequestMapping("/attendance_data_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return prefix+"/attendance_data_form";
	}
}