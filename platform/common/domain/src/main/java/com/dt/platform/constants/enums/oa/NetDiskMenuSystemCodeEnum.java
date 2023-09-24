package com.dt.platform.constants.enums.oa;

import com.github.foxnic.api.constant.CodeTextEnum;


/**
 * @since 2021-07-24 10:25:37
 * @author 金杰 , maillank@qq.com
 * 从 select code,name from sys_dict WHERE deleted=0 and module in ('eam','hrm') 生成
 * 此文件由工具自动生成，请勿修改。若表结构变动，请使用工具重新生成
*/


public enum NetDiskMenuSystemCodeEnum implements CodeTextEnum {

	ROOT("root" , "位置"),
	my_favorite("my_favorite" , "我的收藏"),
	my_space("my_space" , "我的空间"),
	share_to_me("share_to_me" , "分享给我"),
	my_share("my_share" , "我的分享"),
	file_type("file_type" , "文件类型"),
	file_doc("file_doc" , "文档"),
	file_music("file_music" , "音乐"),
	file_picture("file_picture" , "图片"),
	recycle("recycle" , "回收站"),
	;
	private String code;
	private String text;
	private NetDiskMenuSystemCodeEnum(String code, String text)  {
		this.code=code;
		this.text=text;
	}
	
	public String code() {
		return code;
	}
	
	public String text() {
		return text;
	}
}