package com.dt.platform.eam.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.eam.AssetStatusRuleVServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 状态规则值 模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-08-07 07:20:18
*/

@Controller("EamAssetStatusRuleVPageController")
@RequestMapping(AssetStatusRuleVPageController.prefix)
public class AssetStatusRuleVPageController extends ViewController {
	
	public static final String prefix="business/eam/asset_status_rule_v";

	private AssetStatusRuleVServiceProxy proxy;
	
	/**
	 * 获得代理对象<br> 
	 * 1、单体应用时，在应用内部调用；<br> 
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br> 
	 * 3、微服务时，通过feign调用; <br> 
	 * */
	public AssetStatusRuleVServiceProxy proxy() {
		if(proxy==null) {
			proxy=AssetStatusRuleVServiceProxy.api();
		}
		return proxy;
	}
	
	/**
	 * 状态规则值 功能主页面
	 */
	@RequestMapping("/asset_status_rule_v_list.html")
	public String list(Model model,HttpServletRequest request,String operCode) {
		model.addAttribute("operCode",operCode);
		return prefix+"/asset_status_rule_v_list";
	}

	/**
	 * 状态规则值 表单页面
	 */
	@RequestMapping("/asset_status_rule_v_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return prefix+"/asset_status_rule_v_form";
	}
}