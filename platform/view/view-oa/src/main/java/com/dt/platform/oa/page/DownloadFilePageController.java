package com.dt.platform.oa.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.oa.DownloadFileServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 常用下载模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-05-15 11:59:36
*/

@Controller("OaDownloadFilePageController")
@RequestMapping(DownloadFilePageController.prefix)
public class DownloadFilePageController extends ViewController {

	public static final String prefix="business/oa/download_file";

	private DownloadFileServiceProxy proxy;

	/**
	 * 获得代理对象<br>
	 * 1、单体应用时，在应用内部调用；<br>
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br>
	 * 3、微服务时，通过feign调用; <br>
	 * */
	public DownloadFileServiceProxy proxy() {
		if(proxy==null) {
			proxy=DownloadFileServiceProxy.api();
		}
		return proxy;
	}

	/**
	 * 常用下载 功能主页面
	 */
	@RequestMapping("/download_file_list.html")
	public String list(Model model,HttpServletRequest request) {
		return getTemplatePath(prefix,"download_file_list");
	}

	/**
	 * 常用下载 表单页面
	 */
	@RequestMapping("/download_file_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return getTemplatePath(prefix,"download_file_form");
	}
}