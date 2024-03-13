package com.dt.platform.hr.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.hr.AssessmentPlanServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 考核计划模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2024-03-10 15:10:36
*/

@Controller("HrAssessmentPlanPageController")
@RequestMapping(AssessmentPlanPageController.prefix)
public class AssessmentPlanPageController extends ViewController {

	public static final String prefix="business/hr/assessment_plan";

	private AssessmentPlanServiceProxy proxy;

	/**
	 * 获得代理对象<br>
	 * 1、单体应用时，在应用内部调用；<br>
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br>
	 * 3、微服务时，通过feign调用; <br>
	 * */
	public AssessmentPlanServiceProxy proxy() {
		if(proxy==null) {
			proxy=AssessmentPlanServiceProxy.api();
		}
		return proxy;
	}

	/**
	 * 考核计划 功能主页面
	 */
	@RequestMapping("/assessment_plan_list.html")
	public String list(Model model,HttpServletRequest request) {
		return getTemplatePath(prefix,"assessment_plan_list");
	}

	/**
	 * 考核计划 表单页面
	 */
	@RequestMapping("/assessment_plan_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return getTemplatePath(prefix,"assessment_plan_form");
	}
}