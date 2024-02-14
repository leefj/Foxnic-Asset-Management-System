package com.dt.platform.hr.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.hr.ScoreIndicatorServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 评价指标模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2024-02-12 18:44:27
*/

@Controller("HrScoreIndicatorPageController")
@RequestMapping(ScoreIndicatorPageController.prefix)
public class ScoreIndicatorPageController extends ViewController {

	public static final String prefix="business/hr/score_indicator";

	private ScoreIndicatorServiceProxy proxy;

	/**
	 * 获得代理对象<br>
	 * 1、单体应用时，在应用内部调用；<br>
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br>
	 * 3、微服务时，通过feign调用; <br>
	 * */
	public ScoreIndicatorServiceProxy proxy() {
		if(proxy==null) {
			proxy=ScoreIndicatorServiceProxy.api();
		}
		return proxy;
	}

	/**
	 * 评价指标 功能主页面
	 */
	@RequestMapping("/score_indicator_list.html")
	public String list(Model model,HttpServletRequest request) {
		return getTemplatePath(prefix,"score_indicator_list");
	}

	/**
	 * 评价指标 表单页面
	 */
	@RequestMapping("/score_indicator_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return getTemplatePath(prefix,"score_indicator_form");
	}
}