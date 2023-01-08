package com.dt.platform.constants.enums.eam;

import com.github.foxnic.api.constant.CodeTextEnum;


/**
 * @since 2021-07-24 10:25:37
 * @author 金杰 , maillank@qq.com
 * 从 select code,name from sys_dict WHERE deleted=0 and module in ('eam','hrm') 生成
 * 此文件由工具自动生成，请勿修改。若表结构变动，请使用工具重新生成
*/


public enum AssetDepreciationRuleActionCodeEnum implements CodeTextEnum {
	PRECONDITIONS("preconditions" , "前置条件"),
	DEPRECIATION_FINISH("depreciation_finish" , "已折旧完"),
	DEPRECIATION_NORMAL("depreciation_normal" , "正常折旧"),
	DEPRECIATION_FIRST("depreciation_first" , "首次折旧"),
	DEPRECIATION_LAST("depreciation_last" , "最后折旧"),
	DEPRECIATION_IDLE("depreciation_idle" , "不在折旧");

	private String code;
	private String text;
	private AssetDepreciationRuleActionCodeEnum(String code, String text)  {
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