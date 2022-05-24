package com.dt.platform.eam.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.eam.AssetLabelTplServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 标签模版 模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-05-24 08:08:21
*/

@Controller("EamAssetLabelTplPageController")
@RequestMapping(AssetLabelTplPageController.prefix)
public class AssetLabelTplPageController extends ViewController {
	
	public static final String prefix="business/eam/asset_label_tpl";

	private AssetLabelTplServiceProxy proxy;
	
	/**
	 * 获得代理对象<br> 
	 * 1、单体应用时，在应用内部调用；<br> 
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br> 
	 * 3、微服务时，通过feign调用; <br> 
	 * */
	public AssetLabelTplServiceProxy proxy() {
		if(proxy==null) {
			proxy=AssetLabelTplServiceProxy.api();
		}
		return proxy;
	}
	
	/**
	 * 标签模版 功能主页面
	 */
	@RequestMapping("/asset_label_tpl_list.html")
	public String list(Model model,HttpServletRequest request) {
		return prefix+"/asset_label_tpl_list";
	}

	/**
	 * 标签模版 表单页面
	 */
	@RequestMapping("/asset_label_tpl_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return prefix+"/asset_label_tpl_form";
	}
}