package com.dt.platform.hr.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.hr.AssessmentGradeServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 考核评级模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:11:51
*/

@Controller("HrAssessmentGradePageController")
@RequestMapping(AssessmentGradePageController.prefix)
public class AssessmentGradePageController extends ViewController {
	
	public static final String prefix="business/hr/assessment_grade";

	private AssessmentGradeServiceProxy proxy;
	
	/**
	 * 获得代理对象<br> 
	 * 1、单体应用时，在应用内部调用；<br> 
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br> 
	 * 3、微服务时，通过feign调用; <br> 
	 * */
	public AssessmentGradeServiceProxy proxy() {
		if(proxy==null) {
			proxy=AssessmentGradeServiceProxy.api();
		}
		return proxy;
	}
	
	/**
	 * 考核评级 功能主页面
	 */
	@RequestMapping("/assessment_grade_list.html")
	public String list(Model model,HttpServletRequest request) {
		return prefix+"/assessment_grade_list";
	}

	/**
	 * 考核评级 表单页面
	 */
	@RequestMapping("/assessment_grade_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return prefix+"/assessment_grade_form";
	}
}