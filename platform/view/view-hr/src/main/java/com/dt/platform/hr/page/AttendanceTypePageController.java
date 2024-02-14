package com.dt.platform.hr.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.hr.AttendanceTypeServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 考勤类型模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2024-02-13 12:52:21
*/

@Controller("HrAttendanceTypePageController")
@RequestMapping(AttendanceTypePageController.prefix)
public class AttendanceTypePageController extends ViewController {

	public static final String prefix="business/hr/attendance_type";

	private AttendanceTypeServiceProxy proxy;

	/**
	 * 获得代理对象<br>
	 * 1、单体应用时，在应用内部调用；<br>
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br>
	 * 3、微服务时，通过feign调用; <br>
	 * */
	public AttendanceTypeServiceProxy proxy() {
		if(proxy==null) {
			proxy=AttendanceTypeServiceProxy.api();
		}
		return proxy;
	}

	/**
	 * 考勤类型 功能主页面
	 */
	@RequestMapping("/attendance_type_list.html")
	public String list(Model model,HttpServletRequest request) {
		return getTemplatePath(prefix,"attendance_type_list");
	}

	/**
	 * 考勤类型 表单页面
	 */
	@RequestMapping("/attendance_type_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return getTemplatePath(prefix,"attendance_type_form");
	}
}