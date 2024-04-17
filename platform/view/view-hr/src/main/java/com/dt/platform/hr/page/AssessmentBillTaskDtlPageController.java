package com.dt.platform.hr.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.hr.AssessmentBillTaskDtlServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 任务明细单模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2024-03-12 07:25:59
*/

@Controller("HrAssessmentBillTaskDtlPageController")
@RequestMapping(AssessmentBillTaskDtlPageController.prefix)
public class AssessmentBillTaskDtlPageController extends ViewController {

	public static final String prefix="business/hr/assessment_bill_task_dtl";

	private AssessmentBillTaskDtlServiceProxy proxy;

	/**
	 * 获得代理对象<br>
	 * 1、单体应用时，在应用内部调用；<br>
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br>
	 * 3、微服务时，通过feign调用; <br>
	 * */
	public AssessmentBillTaskDtlServiceProxy proxy() {
		if(proxy==null) {
			proxy=AssessmentBillTaskDtlServiceProxy.api();
		}
		return proxy;
	}

	/**
	 * 任务明细单 功能主页面
	 */
	@RequestMapping("/assessment_bill_task_dtl_list.html")
	public String list(Model model,HttpServletRequest request,String billTaskId,String billId) {
		model.addAttribute("billTaskId",billTaskId);
		model.addAttribute("billId",billId);
		return getTemplatePath(prefix,"assessment_bill_task_dtl_list");
	}

	/**
	 * 任务明细单 表单页面
	 */
	@RequestMapping("/assessment_bill_task_dtl_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return getTemplatePath(prefix,"assessment_bill_task_dtl_form");
	}
}