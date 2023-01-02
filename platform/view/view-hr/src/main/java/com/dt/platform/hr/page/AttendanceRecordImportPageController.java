package com.dt.platform.hr.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.hr.AttendanceRecordImportServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 考勤导入模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:26:30
*/

@Controller("HrAttendanceRecordImportPageController")
@RequestMapping(AttendanceRecordImportPageController.prefix)
public class AttendanceRecordImportPageController extends ViewController {
	
	public static final String prefix="business/hr/attendance_record_import";

	private AttendanceRecordImportServiceProxy proxy;
	
	/**
	 * 获得代理对象<br> 
	 * 1、单体应用时，在应用内部调用；<br> 
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br> 
	 * 3、微服务时，通过feign调用; <br> 
	 * */
	public AttendanceRecordImportServiceProxy proxy() {
		if(proxy==null) {
			proxy=AttendanceRecordImportServiceProxy.api();
		}
		return proxy;
	}
	
	/**
	 * 考勤导入 功能主页面
	 */
	@RequestMapping("/attendance_record_import_list.html")
	public String list(Model model,HttpServletRequest request) {
		return prefix+"/attendance_record_import_list";
	}

	/**
	 * 考勤导入 表单页面
	 */
	@RequestMapping("/attendance_record_import_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return prefix+"/attendance_record_import_form";
	}
}