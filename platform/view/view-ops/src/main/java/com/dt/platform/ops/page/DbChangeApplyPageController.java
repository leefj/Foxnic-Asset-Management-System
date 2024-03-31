package com.dt.platform.ops.page;

import com.dt.platform.proxy.ops.DbApplyExecuteServiceProxy;
import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.ops.DbChangeApplyServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 数据库变更申请模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2024-03-29 22:23:38
*/

@Controller("OpsDbChangeApplyPageController")
@RequestMapping(DbChangeApplyPageController.prefix)
public class DbChangeApplyPageController extends ViewController {

	public static final String prefix="business/ops/db_change_apply";

	private DbChangeApplyServiceProxy proxy;

	/**
	 * 获得代理对象<br>
	 * 1、单体应用时，在应用内部调用；<br>
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br>
	 * 3、微服务时，通过feign调用; <br>
	 * */
	public DbChangeApplyServiceProxy proxy() {
		if(proxy==null) {
			proxy=DbChangeApplyServiceProxy.api();
		}
		return proxy;
	}

	/**
	 * 数据库变更申请 功能主页面
	 */
	@RequestMapping("/db_change_apply_list.html")
	public String list(Model model,HttpServletRequest request) {
		return getTemplatePath(prefix,"db_change_apply_list");
	}

	/**
	 * 数据库变更申请 表单页面
	 */
	@RequestMapping("/db_change_apply_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		model.addAttribute("associatedSystem",DbApplyExecuteServiceProxy.api().queryCurAssociatedSystem());
		return getTemplatePath(prefix,"db_change_apply_form");
	}
}