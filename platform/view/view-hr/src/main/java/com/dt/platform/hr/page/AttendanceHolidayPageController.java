package com.dt.platform.hr.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.hr.AttendanceHolidayServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 休假管理模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2024-02-15 21:08:53
*/

@Controller("HrAttendanceHolidayPageController")
@RequestMapping(AttendanceHolidayPageController.prefix)
public class AttendanceHolidayPageController extends ViewController {

	public static final String prefix="business/hr/attendance_holiday";

	private AttendanceHolidayServiceProxy proxy;

	/**
	 * 获得代理对象<br>
	 * 1、单体应用时，在应用内部调用；<br>
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br>
	 * 3、微服务时，通过feign调用; <br>
	 * */
	public AttendanceHolidayServiceProxy proxy() {
		if(proxy==null) {
			proxy=AttendanceHolidayServiceProxy.api();
		}
		return proxy;
	}


	/**
	 * 休假管理 功能主页面
	 */
	@RequestMapping("/attendance_tree.html")
	public String tree(Model model,HttpServletRequest request) {
		return getTemplatePath(prefix,"attendance_tree");
	}


	/**
	 * 休假管理 功能主页面
	 */
	@RequestMapping("/attendance_holiday_list.html")
	public String list(Model model,HttpServletRequest request) {
		return getTemplatePath(prefix,"attendance_holiday_list");
	}

	/**
	 * 休假管理 表单页面
	 */
	@RequestMapping("/attendance_holiday_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return getTemplatePath(prefix,"attendance_holiday_form");
	}
}