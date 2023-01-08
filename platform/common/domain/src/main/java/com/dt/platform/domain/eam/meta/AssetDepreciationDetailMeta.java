package com.dt.platform.domain.eam.meta;

import com.github.foxnic.api.bean.BeanProperty;
import com.dt.platform.domain.eam.AssetDepreciationDetail;
import java.util.Date;
import java.math.BigDecimal;
import com.dt.platform.domain.eam.Asset;
import com.dt.platform.domain.eam.AssetDepreciation;
import com.dt.platform.domain.eam.AssetDepreciationCalRule;
import java.util.List;
import com.dt.platform.domain.eam.AssetDepreciationOper;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.hrm.Organization;
import javax.persistence.Transient;



/**
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-08 11:31:29
 * @sign 68AE6EB92174B775D3971C2CC734A3CB
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

public class AssetDepreciationDetailMeta {
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final String ID="id";
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> ID_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ID, java.lang.String.class, "主键", "主键", java.lang.String.class, null);
	
	/**
	 * 折旧方案 , 类型: java.lang.String
	*/
	public static final String DEPRECIATION_ID="depreciationId";
	
	/**
	 * 折旧方案 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> DEPRECIATION_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,DEPRECIATION_ID, java.lang.String.class, "折旧方案", "折旧方案", java.lang.String.class, null);
	
	/**
	 * 折旧操作 , 类型: java.lang.String
	*/
	public static final String OPER_ID="operId";
	
	/**
	 * 折旧操作 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> OPER_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,OPER_ID, java.lang.String.class, "折旧操作", "折旧操作", java.lang.String.class, null);
	
	/**
	 * 折旧动作 , 类型: java.lang.String
	*/
	public static final String ACTION_CODE="actionCode";
	
	/**
	 * 折旧动作 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> ACTION_CODE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ACTION_CODE, java.lang.String.class, "折旧动作", "折旧动作", java.lang.String.class, null);
	
	/**
	 * 首次折旧方式 , 类型: java.lang.String
	*/
	public static final String FIRST_DEPRECIATION_METHOD="firstDepreciationMethod";
	
	/**
	 * 首次折旧方式 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> FIRST_DEPRECIATION_METHOD_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,FIRST_DEPRECIATION_METHOD, java.lang.String.class, "首次折旧方式", "首次折旧方式", java.lang.String.class, null);
	
	/**
	 * 折旧方式 , 类型: java.lang.String
	*/
	public static final String DEPRECIATION_METHOD="depreciationMethod";
	
	/**
	 * 折旧方式 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> DEPRECIATION_METHOD_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,DEPRECIATION_METHOD, java.lang.String.class, "折旧方式", "折旧方式", java.lang.String.class, null);
	
	/**
	 * 业务日期 , 类型: java.util.Date
	*/
	public static final String BUSINESS_DATE="businessDate";
	
	/**
	 * 业务日期 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.util.Date> BUSINESS_DATE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,BUSINESS_DATE, java.util.Date.class, "业务日期", "业务日期", java.util.Date.class, null);
	
	/**
	 * 折旧规则 , 类型: java.lang.String
	*/
	public static final String RESULT="result";
	
	/**
	 * 折旧规则 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> RESULT_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,RESULT, java.lang.String.class, "折旧规则", "折旧规则", java.lang.String.class, null);
	
	/**
	 * 折旧结果 , 类型: java.lang.String
	*/
	public static final String RESULT_STATUS="resultStatus";
	
	/**
	 * 折旧结果 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> RESULT_STATUS_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,RESULT_STATUS, java.lang.String.class, "折旧结果", "折旧结果", java.lang.String.class, null);
	
	/**
	 * 结果明细 , 类型: java.lang.String
	*/
	public static final String RESULT_DETAIL="resultDetail";
	
	/**
	 * 结果明细 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> RESULT_DETAIL_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,RESULT_DETAIL, java.lang.String.class, "结果明细", "结果明细", java.lang.String.class, null);
	
	/**
	 * 资产 , 类型: java.lang.String
	*/
	public static final String ASSET_ID="assetId";
	
	/**
	 * 资产 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> ASSET_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_ID, java.lang.String.class, "资产", "资产", java.lang.String.class, null);
	
	/**
	 * 资产类别 , 类型: java.lang.String
	*/
	public static final String ASSET_CATEGORY_ID="assetCategoryId";
	
	/**
	 * 资产类别 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> ASSET_CATEGORY_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_CATEGORY_ID, java.lang.String.class, "资产类别", "资产类别", java.lang.String.class, null);
	
	/**
	 * 资产类别 , 类型: java.lang.String
	*/
	public static final String ASSET_CATEGORY_NAME="assetCategoryName";
	
	/**
	 * 资产类别 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> ASSET_CATEGORY_NAME_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_CATEGORY_NAME, java.lang.String.class, "资产类别", "资产类别", java.lang.String.class, null);
	
	/**
	 * 财务分类 , 类型: java.lang.String
	*/
	public static final String ASSET_FINANCE_CATEGORY_ID="assetFinanceCategoryId";
	
	/**
	 * 财务分类 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> ASSET_FINANCE_CATEGORY_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_FINANCE_CATEGORY_ID, java.lang.String.class, "财务分类", "财务分类", java.lang.String.class, null);
	
	/**
	 * 财务分类 , 类型: java.lang.String
	*/
	public static final String ASSET_FINANCE_CATEGORY_NAME="assetFinanceCategoryName";
	
	/**
	 * 财务分类 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> ASSET_FINANCE_CATEGORY_NAME_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_FINANCE_CATEGORY_NAME, java.lang.String.class, "财务分类", "财务分类", java.lang.String.class, null);
	
	/**
	 * 资产编码 , 类型: java.lang.String
	*/
	public static final String ASSET_CODE="assetCode";
	
	/**
	 * 资产编码 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> ASSET_CODE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_CODE, java.lang.String.class, "资产编码", "资产编码", java.lang.String.class, null);
	
	/**
	 * 资产名称 , 类型: java.lang.String
	*/
	public static final String ASSET_NAME="assetName";
	
	/**
	 * 资产名称 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> ASSET_NAME_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_NAME, java.lang.String.class, "资产名称", "资产名称", java.lang.String.class, null);
	
	/**
	 * 资产型号 , 类型: java.lang.String
	*/
	public static final String ASSET_MODEL="assetModel";
	
	/**
	 * 资产型号 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> ASSET_MODEL_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_MODEL, java.lang.String.class, "资产型号", "资产型号", java.lang.String.class, null);
	
	/**
	 * 资产状态 , 类型: java.lang.String
	*/
	public static final String ASSET_STATUS_NAME="assetStatusName";
	
	/**
	 * 资产状态 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> ASSET_STATUS_NAME_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_STATUS_NAME, java.lang.String.class, "资产状态", "资产状态", java.lang.String.class, null);
	
	/**
	 * 采购日期 , 类型: java.util.Date
	*/
	public static final String ASSET_PURCHASE_DATE="assetPurchaseDate";
	
	/**
	 * 采购日期 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.util.Date> ASSET_PURCHASE_DATE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_PURCHASE_DATE, java.util.Date.class, "采购日期", "采购日期", java.util.Date.class, null);
	
	/**
	 * 入账日期 , 类型: java.util.Date
	*/
	public static final String ASSET_REGISTER_DATE="assetRegisterDate";
	
	/**
	 * 入账日期 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.util.Date> ASSET_REGISTER_DATE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_REGISTER_DATE, java.util.Date.class, "入账日期", "入账日期", java.util.Date.class, null);
	
	/**
	 * 资产原值 , 类型: java.math.BigDecimal
	*/
	public static final String ASSET_ORIGINAL_UNIT_PRICE="assetOriginalUnitPrice";
	
	/**
	 * 资产原值 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> ASSET_ORIGINAL_UNIT_PRICE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_ORIGINAL_UNIT_PRICE, java.math.BigDecimal.class, "资产原值", "资产原值", java.math.BigDecimal.class, null);
	
	/**
	 * 含税单价 , 类型: java.math.BigDecimal
	*/
	public static final String ASSET_PURCHASE_UNIT_PRICE="assetPurchaseUnitPrice";
	
	/**
	 * 含税单价 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> ASSET_PURCHASE_UNIT_PRICE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_PURCHASE_UNIT_PRICE, java.math.BigDecimal.class, "含税单价", "含税单价", java.math.BigDecimal.class, null);
	
	/**
	 * 资产净值 , (当前) , 类型: java.math.BigDecimal
	*/
	public static final String ASSET_NAV_PRICE="assetNavPrice";
	
	/**
	 * 资产净值 , (当前) , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> ASSET_NAV_PRICE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_NAV_PRICE, java.math.BigDecimal.class, "资产净值", "(当前)", java.math.BigDecimal.class, null);
	
	/**
	 * 税额 , 类型: java.math.BigDecimal
	*/
	public static final String ASSET_TAX_AMOUNT_RATE="assetTaxAmountRate";
	
	/**
	 * 税额 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> ASSET_TAX_AMOUNT_RATE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_TAX_AMOUNT_RATE, java.math.BigDecimal.class, "税额", "税额", java.math.BigDecimal.class, null);
	
	/**
	 * 可使用期限(资产) , 月 , 类型: java.math.BigDecimal
	*/
	public static final String ASSET_SERVICE_LIFE="assetServiceLife";
	
	/**
	 * 可使用期限(资产) , 月 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> ASSET_SERVICE_LIFE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_SERVICE_LIFE, java.math.BigDecimal.class, "可使用期限(资产)", "月", java.math.BigDecimal.class, null);
	
	/**
	 * 可使用期限(财务) , 月 , 类型: java.math.BigDecimal
	*/
	public static final String ASSET_FINANCE_SERVICE_LIFE="assetFinanceServiceLife";
	
	/**
	 * 可使用期限(财务) , 月 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> ASSET_FINANCE_SERVICE_LIFE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_FINANCE_SERVICE_LIFE, java.math.BigDecimal.class, "可使用期限(财务)", "月", java.math.BigDecimal.class, null);
	
	/**
	 * 本期残值率 , 类型: java.math.BigDecimal
	*/
	public static final String ASSET_RESIDUALS_RATE="assetResidualsRate";
	
	/**
	 * 本期残值率 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> ASSET_RESIDUALS_RATE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_RESIDUALS_RATE, java.math.BigDecimal.class, "本期残值率", "本期残值率", java.math.BigDecimal.class, null);
	
	/**
	 * 本期残值 , 类型: java.math.BigDecimal
	*/
	public static final String ASSET_RESIDUALS_PRICE="assetResidualsPrice";
	
	/**
	 * 本期残值 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> ASSET_RESIDUALS_PRICE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_RESIDUALS_PRICE, java.math.BigDecimal.class, "本期残值", "本期残值", java.math.BigDecimal.class, null);
	
	/**
	 * (期初)期初原值 , 类型: java.math.BigDecimal
	*/
	public static final String S_ORIGINAL_PRICE="sOriginalPrice";
	
	/**
	 * (期初)期初原值 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> S_ORIGINAL_PRICE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,S_ORIGINAL_PRICE, java.math.BigDecimal.class, "(期初)期初原值", "(期初)期初原值", java.math.BigDecimal.class, null);
	
	/**
	 * (期初)期初累计折旧 , 类型: java.math.BigDecimal
	*/
	public static final String S_DEPRECIATION_AMOUNT="sDepreciationAmount";
	
	/**
	 * (期初)期初累计折旧 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> S_DEPRECIATION_AMOUNT_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,S_DEPRECIATION_AMOUNT, java.math.BigDecimal.class, "(期初)期初累计折旧", "(期初)期初累计折旧", java.math.BigDecimal.class, null);
	
	/**
	 * (期初)期初净值 , 类型: java.math.BigDecimal
	*/
	public static final String S_NAV_AMOUNT="sNavAmount";
	
	/**
	 * (期初)期初净值 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> S_NAV_AMOUNT_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,S_NAV_AMOUNT, java.math.BigDecimal.class, "(期初)期初净值", "(期初)期初净值", java.math.BigDecimal.class, null);
	
	/**
	 * (期初)期初可回收净额 , 类型: java.math.BigDecimal
	*/
	public static final String S_RECOVERABLE_AMOUNT="sRecoverableAmount";
	
	/**
	 * (期初)期初可回收净额 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> S_RECOVERABLE_AMOUNT_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,S_RECOVERABLE_AMOUNT, java.math.BigDecimal.class, "(期初)期初可回收净额", "(期初)期初可回收净额", java.math.BigDecimal.class, null);
	
	/**
	 * 已使用期限 , 月 , 类型: java.math.BigDecimal
	*/
	public static final String C_USED_SERVICE_LIFE="cUsedServiceLife";
	
	/**
	 * 已使用期限 , 月 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> C_USED_SERVICE_LIFE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,C_USED_SERVICE_LIFE, java.math.BigDecimal.class, "已使用期限", "月", java.math.BigDecimal.class, null);
	
	/**
	 * (本期发生)原值增加 , 类型: java.math.BigDecimal
	*/
	public static final String C_ORIGINAL_PRICE_INCREASE="cOriginalPriceIncrease";
	
	/**
	 * (本期发生)原值增加 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> C_ORIGINAL_PRICE_INCREASE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,C_ORIGINAL_PRICE_INCREASE, java.math.BigDecimal.class, "(本期发生)原值增加", "(本期发生)原值增加", java.math.BigDecimal.class, null);
	
	/**
	 * (本期发生)本期折旧额 , 类型: java.math.BigDecimal
	*/
	public static final String C_DEPRECIATION_AMOUNT="cDepreciationAmount";
	
	/**
	 * (本期发生)本期折旧额 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> C_DEPRECIATION_AMOUNT_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,C_DEPRECIATION_AMOUNT, java.math.BigDecimal.class, "(本期发生)本期折旧额", "(本期发生)本期折旧额", java.math.BigDecimal.class, null);
	
	/**
	 * (本期发生)本年累计折旧额 , 类型: java.math.BigDecimal
	*/
	public static final String C_YEAR_DEPRECIATION_AMOUNT="cYearDepreciationAmount";
	
	/**
	 * (本期发生)本年累计折旧额 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> C_YEAR_DEPRECIATION_AMOUNT_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,C_YEAR_DEPRECIATION_AMOUNT, java.math.BigDecimal.class, "(本期发生)本年累计折旧额", "(本期发生)本年累计折旧额", java.math.BigDecimal.class, null);
	
	/**
	 * (期末)期末原值 , 类型: java.math.BigDecimal
	*/
	public static final String E_ORIGINAL_PRICE="eOriginalPrice";
	
	/**
	 * (期末)期末原值 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> E_ORIGINAL_PRICE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,E_ORIGINAL_PRICE, java.math.BigDecimal.class, "(期末)期末原值", "(期末)期末原值", java.math.BigDecimal.class, null);
	
	/**
	 * (期末)期末累计折旧 , 类型: java.math.BigDecimal
	*/
	public static final String E_DEPRECIATION_AMOUNT="eDepreciationAmount";
	
	/**
	 * (期末)期末累计折旧 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> E_DEPRECIATION_AMOUNT_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,E_DEPRECIATION_AMOUNT, java.math.BigDecimal.class, "(期末)期末累计折旧", "(期末)期末累计折旧", java.math.BigDecimal.class, null);
	
	/**
	 * (期末)期末净值 , 类型: java.math.BigDecimal
	*/
	public static final String E_NAV_AMOUNT="eNavAmount";
	
	/**
	 * (期末)期末净值 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> E_NAV_AMOUNT_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,E_NAV_AMOUNT, java.math.BigDecimal.class, "(期末)期末净值", "(期末)期末净值", java.math.BigDecimal.class, null);
	
	/**
	 * (期末)期末可回收金额 , 类型: java.math.BigDecimal
	*/
	public static final String E_RECOVERABLE_AMOUNT="eRecoverableAmount";
	
	/**
	 * (期末)期末可回收金额 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> E_RECOVERABLE_AMOUNT_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,E_RECOVERABLE_AMOUNT, java.math.BigDecimal.class, "(期末)期末可回收金额", "(期末)期末可回收金额", java.math.BigDecimal.class, null);
	
	/**
	 * 会计期间已使用期限 , 月 , 类型: java.math.BigDecimal
	*/
	public static final String ACCOUNTING_SERVICE_LIFE="accountingServiceLife";
	
	/**
	 * 会计期间已使用期限 , 月 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> ACCOUNTING_SERVICE_LIFE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ACCOUNTING_SERVICE_LIFE, java.math.BigDecimal.class, "会计期间已使用期限", "月", java.math.BigDecimal.class, null);
	
	/**
	 * 首次折旧 , 0否,1是 , 类型: java.lang.String
	*/
	public static final String FIRST_DEPRECIATION="firstDepreciation";
	
	/**
	 * 首次折旧 , 0否,1是 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> FIRST_DEPRECIATION_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,FIRST_DEPRECIATION, java.lang.String.class, "首次折旧", "0否,1是", java.lang.String.class, null);
	
	/**
	 * 使用人ID , 类型: java.lang.String
	*/
	public static final String USE_USER_ID="useUserId";
	
	/**
	 * 使用人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> USE_USER_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,USE_USER_ID, java.lang.String.class, "使用人ID", "使用人ID", java.lang.String.class, null);
	
	/**
	 * 使用人 , 类型: java.lang.String
	*/
	public static final String USE_USER_NAME="useUserName";
	
	/**
	 * 使用人 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> USE_USER_NAME_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,USE_USER_NAME, java.lang.String.class, "使用人", "使用人", java.lang.String.class, null);
	
	/**
	 * 管理人员 , 类型: java.lang.String
	*/
	public static final String MANAGER_ID="managerId";
	
	/**
	 * 管理人员 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> MANAGER_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,MANAGER_ID, java.lang.String.class, "管理人员", "管理人员", java.lang.String.class, null);
	
	/**
	 * 管理人员 , 类型: java.lang.String
	*/
	public static final String MANAGER_NAME="managerName";
	
	/**
	 * 管理人员 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> MANAGER_NAME_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,MANAGER_NAME, java.lang.String.class, "管理人员", "管理人员", java.lang.String.class, null);
	
	/**
	 * 部门ID , 类型: java.lang.String
	*/
	public static final String USE_ORG_ID="useOrgId";
	
	/**
	 * 部门ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> USE_ORG_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,USE_ORG_ID, java.lang.String.class, "部门ID", "部门ID", java.lang.String.class, null);
	
	/**
	 * 使用部门 , 类型: java.lang.String
	*/
	public static final String USE_ORG_NAME="useOrgName";
	
	/**
	 * 使用部门 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> USE_ORG_NAME_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,USE_ORG_NAME, java.lang.String.class, "使用部门", "使用部门", java.lang.String.class, null);
	
	/**
	 * 财务选项KEY , 类型: java.lang.String
	*/
	public static final String FINANCIAL_OPTION_KEY="financialOptionKey";
	
	/**
	 * 财务选项KEY , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> FINANCIAL_OPTION_KEY_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,FINANCIAL_OPTION_KEY, java.lang.String.class, "财务选项KEY", "财务选项KEY", java.lang.String.class, null);
	
	/**
	 * 费用项目KEY , 类型: java.lang.String
	*/
	public static final String EXPENSE_ITEM_KEY="expenseItemKey";
	
	/**
	 * 费用项目KEY , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> EXPENSE_ITEM_KEY_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,EXPENSE_ITEM_KEY, java.lang.String.class, "费用项目KEY", "费用项目KEY", java.lang.String.class, null);
	
	/**
	 * 财务选项 , 类型: java.lang.String
	*/
	public static final String FINANCIAL_OPTION_NAME="financialOptionName";
	
	/**
	 * 财务选项 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> FINANCIAL_OPTION_NAME_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,FINANCIAL_OPTION_NAME, java.lang.String.class, "财务选项", "财务选项", java.lang.String.class, null);
	
	/**
	 * 费用项目 , 类型: java.lang.String
	*/
	public static final String EXPENSE_ITEM_NAME="expenseItemName";
	
	/**
	 * 费用项目 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> EXPENSE_ITEM_NAME_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,EXPENSE_ITEM_NAME, java.lang.String.class, "费用项目", "费用项目", java.lang.String.class, null);
	
	/**
	 * 客户情况 , 类型: java.lang.String
	*/
	public static final String CUSTOMER_INFO="customerInfo";
	
	/**
	 * 客户情况 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> CUSTOMER_INFO_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,CUSTOMER_INFO, java.lang.String.class, "客户情况", "客户情况", java.lang.String.class, null);
	
	/**
	 * 源资产 , 类型: java.lang.String
	*/
	public static final String DETAIL_ID_SOURCE="detailIdSource";
	
	/**
	 * 源资产 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> DETAIL_ID_SOURCE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,DETAIL_ID_SOURCE, java.lang.String.class, "源资产", "源资产", java.lang.String.class, null);
	
	/**
	 * 目标资产 , 类型: java.lang.String
	*/
	public static final String DETAIL_ID_TARGET="detailIdTarget";
	
	/**
	 * 目标资产 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> DETAIL_ID_TARGET_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,DETAIL_ID_TARGET, java.lang.String.class, "目标资产", "目标资产", java.lang.String.class, null);
	
	/**
	 * 上次折旧单据 , 类型: java.lang.String
	*/
	public static final String LAST_OPER_ID="lastOperId";
	
	/**
	 * 上次折旧单据 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> LAST_OPER_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,LAST_OPER_ID, java.lang.String.class, "上次折旧单据", "上次折旧单据", java.lang.String.class, null);
	
	/**
	 * 上次折旧时间 , 类型: java.util.Date
	*/
	public static final String LAST_OPER_TIME="lastOperTime";
	
	/**
	 * 上次折旧时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.util.Date> LAST_OPER_TIME_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,LAST_OPER_TIME, java.util.Date.class, "上次折旧时间", "上次折旧时间", java.util.Date.class, null);
	
	/**
	 * 标签 , 类型: java.lang.String
	*/
	public static final String LABEL="label";
	
	/**
	 * 标签 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> LABEL_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,LABEL, java.lang.String.class, "标签", "标签", java.lang.String.class, null);
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final String CREATE_BY="createBy";
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> CREATE_BY_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,CREATE_BY, java.lang.String.class, "创建人ID", "创建人ID", java.lang.String.class, null);
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final String CREATE_TIME="createTime";
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.util.Date> CREATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,CREATE_TIME, java.util.Date.class, "创建时间", "创建时间", java.util.Date.class, null);
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final String UPDATE_BY="updateBy";
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> UPDATE_BY_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,UPDATE_BY, java.lang.String.class, "修改人ID", "修改人ID", java.lang.String.class, null);
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final String UPDATE_TIME="updateTime";
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.util.Date> UPDATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,UPDATE_TIME, java.util.Date.class, "修改时间", "修改时间", java.util.Date.class, null);
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final String DELETED="deleted";
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.Integer> DELETED_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,DELETED, java.lang.Integer.class, "是否已删除", "是否已删除", java.lang.Integer.class, null);
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final String DELETE_BY="deleteBy";
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> DELETE_BY_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,DELETE_BY, java.lang.String.class, "删除人ID", "删除人ID", java.lang.String.class, null);
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final String DELETE_TIME="deleteTime";
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.util.Date> DELETE_TIME_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,DELETE_TIME, java.util.Date.class, "删除时间", "删除时间", java.util.Date.class, null);
	
	/**
	 * version , 类型: java.lang.Integer
	*/
	public static final String VERSION="version";
	
	/**
	 * version , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.Integer> VERSION_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,VERSION, java.lang.Integer.class, "version", "version", java.lang.Integer.class, null);
	
	/**
	 * 结果字符串 , 类型: java.lang.String
	*/
	public static final String RESULT_VALUE_STR="resultValueStr";
	
	/**
	 * 结果字符串 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> RESULT_VALUE_STR_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,RESULT_VALUE_STR, java.lang.String.class, "结果字符串", "结果字符串", java.lang.String.class, null);
	
	/**
	 * 结果浮点 , 类型: java.math.BigDecimal
	*/
	public static final String RESULT_VALUE_FLOAT="resultValueFloat";
	
	/**
	 * 结果浮点 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.math.BigDecimal> RESULT_VALUE_FLOAT_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,RESULT_VALUE_FLOAT, java.math.BigDecimal.class, "结果浮点", "结果浮点", java.math.BigDecimal.class, null);
	
	/**
	 * 资产 , 类型: com.dt.platform.domain.eam.Asset
	*/
	public static final String ASSET_SOURCE="assetSource";
	
	/**
	 * 资产 , 类型: com.dt.platform.domain.eam.Asset
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,com.dt.platform.domain.eam.Asset> ASSET_SOURCE_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_SOURCE, com.dt.platform.domain.eam.Asset.class, "资产", "资产", com.dt.platform.domain.eam.Asset.class, null);
	
	/**
	 * 资产 , 类型: com.dt.platform.domain.eam.Asset
	*/
	public static final String ASSET_TARGET="assetTarget";
	
	/**
	 * 资产 , 类型: com.dt.platform.domain.eam.Asset
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,com.dt.platform.domain.eam.Asset> ASSET_TARGET_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_TARGET, com.dt.platform.domain.eam.Asset.class, "资产", "资产", com.dt.platform.domain.eam.Asset.class, null);
	
	/**
	 * 资产 , 类型: com.dt.platform.domain.eam.Asset
	*/
	public static final String ASSET="asset";
	
	/**
	 * 资产 , 类型: com.dt.platform.domain.eam.Asset
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,com.dt.platform.domain.eam.Asset> ASSET_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET, com.dt.platform.domain.eam.Asset.class, "资产", "资产", com.dt.platform.domain.eam.Asset.class, null);
	
	/**
	 * 上一期折旧数据 , 类型: com.dt.platform.domain.eam.AssetDepreciationDetail
	*/
	public static final String LAST_ASSET_DEPRECIATION_DETAIL="lastAssetDepreciationDetail";
	
	/**
	 * 上一期折旧数据 , 类型: com.dt.platform.domain.eam.AssetDepreciationDetail
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,com.dt.platform.domain.eam.AssetDepreciationDetail> LAST_ASSET_DEPRECIATION_DETAIL_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,LAST_ASSET_DEPRECIATION_DETAIL, com.dt.platform.domain.eam.AssetDepreciationDetail.class, "上一期折旧数据", "上一期折旧数据", com.dt.platform.domain.eam.AssetDepreciationDetail.class, null);
	
	/**
	 * 方案 , 类型: com.dt.platform.domain.eam.AssetDepreciation
	*/
	public static final String ASSET_DEPRECIATION="assetDepreciation";
	
	/**
	 * 方案 , 类型: com.dt.platform.domain.eam.AssetDepreciation
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,com.dt.platform.domain.eam.AssetDepreciation> ASSET_DEPRECIATION_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_DEPRECIATION, com.dt.platform.domain.eam.AssetDepreciation.class, "方案", "方案", com.dt.platform.domain.eam.AssetDepreciation.class, null);
	
	/**
	 * calRuleList , 集合类型: LIST , 类型: com.dt.platform.domain.eam.AssetDepreciationCalRule
	*/
	public static final String CAL_RULE_LIST="calRuleList";
	
	/**
	 * calRuleList , 集合类型: LIST , 类型: com.dt.platform.domain.eam.AssetDepreciationCalRule
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,com.dt.platform.domain.eam.AssetDepreciationCalRule> CAL_RULE_LIST_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,CAL_RULE_LIST, java.util.List.class, "calRuleList", "calRuleList", com.dt.platform.domain.eam.AssetDepreciationCalRule.class, null);
	
	/**
	 * calRuleIds , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final String CAL_RULE_IDS="calRuleIds";
	
	/**
	 * calRuleIds , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,java.lang.String> CAL_RULE_IDS_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,CAL_RULE_IDS, java.util.List.class, "calRuleIds", "calRuleIds", java.lang.String.class, null);
	
	/**
	 * 操作 , 类型: com.dt.platform.domain.eam.AssetDepreciationOper
	*/
	public static final String ASSET_DEPRECIATION_OPER="assetDepreciationOper";
	
	/**
	 * 操作 , 类型: com.dt.platform.domain.eam.AssetDepreciationOper
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,com.dt.platform.domain.eam.AssetDepreciationOper> ASSET_DEPRECIATION_OPER_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,ASSET_DEPRECIATION_OPER, com.dt.platform.domain.eam.AssetDepreciationOper.class, "操作", "操作", com.dt.platform.domain.eam.AssetDepreciationOper.class, null);
	
	/**
	 * 财务选项 , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final String FINANCIAL_OPTION_DICT="financialOptionDict";
	
	/**
	 * 财务选项 , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,org.github.foxnic.web.domain.system.DictItem> FINANCIAL_OPTION_DICT_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,FINANCIAL_OPTION_DICT, org.github.foxnic.web.domain.system.DictItem.class, "财务选项", "财务选项", org.github.foxnic.web.domain.system.DictItem.class, null);
	
	/**
	 * 费用项目 , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final String EXPENSE_ITEM_DICT="expenseItemDict";
	
	/**
	 * 费用项目 , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,org.github.foxnic.web.domain.system.DictItem> EXPENSE_ITEM_DICT_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,EXPENSE_ITEM_DICT, org.github.foxnic.web.domain.system.DictItem.class, "费用项目", "费用项目", org.github.foxnic.web.domain.system.DictItem.class, null);
	
	/**
	 * 使用人员 , 类型: org.github.foxnic.web.domain.hrm.Employee
	*/
	public static final String USE_USER="useUser";
	
	/**
	 * 使用人员 , 类型: org.github.foxnic.web.domain.hrm.Employee
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,org.github.foxnic.web.domain.hrm.Employee> USE_USER_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,USE_USER, org.github.foxnic.web.domain.hrm.Employee.class, "使用人员", "使用人员", org.github.foxnic.web.domain.hrm.Employee.class, null);
	
	/**
	 * 使用公司/部门 , 类型: org.github.foxnic.web.domain.hrm.Organization
	*/
	public static final String USE_ORGANIZATION="useOrganization";
	
	/**
	 * 使用公司/部门 , 类型: org.github.foxnic.web.domain.hrm.Organization
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,org.github.foxnic.web.domain.hrm.Organization> USE_ORGANIZATION_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,USE_ORGANIZATION, org.github.foxnic.web.domain.hrm.Organization.class, "使用公司/部门", "使用公司/部门", org.github.foxnic.web.domain.hrm.Organization.class, null);
	
	/**
	 * 管理人员 , 类型: org.github.foxnic.web.domain.hrm.Employee
	*/
	public static final String MANAGER="manager";
	
	/**
	 * 管理人员 , 类型: org.github.foxnic.web.domain.hrm.Employee
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.AssetDepreciationDetail,org.github.foxnic.web.domain.hrm.Employee> MANAGER_PROP = new BeanProperty(com.dt.platform.domain.eam.AssetDepreciationDetail.class ,MANAGER, org.github.foxnic.web.domain.hrm.Employee.class, "管理人员", "管理人员", org.github.foxnic.web.domain.hrm.Employee.class, null);
	
	/**
	 * 全部属性清单
	*/
	public static final String[] $PROPS={ ID , DEPRECIATION_ID , OPER_ID , ACTION_CODE , FIRST_DEPRECIATION_METHOD , DEPRECIATION_METHOD , BUSINESS_DATE , RESULT , RESULT_STATUS , RESULT_DETAIL , ASSET_ID , ASSET_CATEGORY_ID , ASSET_CATEGORY_NAME , ASSET_FINANCE_CATEGORY_ID , ASSET_FINANCE_CATEGORY_NAME , ASSET_CODE , ASSET_NAME , ASSET_MODEL , ASSET_STATUS_NAME , ASSET_PURCHASE_DATE , ASSET_REGISTER_DATE , ASSET_ORIGINAL_UNIT_PRICE , ASSET_PURCHASE_UNIT_PRICE , ASSET_NAV_PRICE , ASSET_TAX_AMOUNT_RATE , ASSET_SERVICE_LIFE , ASSET_FINANCE_SERVICE_LIFE , ASSET_RESIDUALS_RATE , ASSET_RESIDUALS_PRICE , S_ORIGINAL_PRICE , S_DEPRECIATION_AMOUNT , S_NAV_AMOUNT , S_RECOVERABLE_AMOUNT , C_USED_SERVICE_LIFE , C_ORIGINAL_PRICE_INCREASE , C_DEPRECIATION_AMOUNT , C_YEAR_DEPRECIATION_AMOUNT , E_ORIGINAL_PRICE , E_DEPRECIATION_AMOUNT , E_NAV_AMOUNT , E_RECOVERABLE_AMOUNT , ACCOUNTING_SERVICE_LIFE , FIRST_DEPRECIATION , USE_USER_ID , USE_USER_NAME , MANAGER_ID , MANAGER_NAME , USE_ORG_ID , USE_ORG_NAME , FINANCIAL_OPTION_KEY , EXPENSE_ITEM_KEY , FINANCIAL_OPTION_NAME , EXPENSE_ITEM_NAME , CUSTOMER_INFO , DETAIL_ID_SOURCE , DETAIL_ID_TARGET , LAST_OPER_ID , LAST_OPER_TIME , LABEL , CREATE_BY , CREATE_TIME , UPDATE_BY , UPDATE_TIME , DELETED , DELETE_BY , DELETE_TIME , VERSION , RESULT_VALUE_STR , RESULT_VALUE_FLOAT , ASSET_SOURCE , ASSET_TARGET , ASSET , LAST_ASSET_DEPRECIATION_DETAIL , ASSET_DEPRECIATION , CAL_RULE_LIST , CAL_RULE_IDS , ASSET_DEPRECIATION_OPER , FINANCIAL_OPTION_DICT , EXPENSE_ITEM_DICT , USE_USER , USE_ORGANIZATION , MANAGER };
	
	/**
	 * 代理类
	*/
	public static class $$proxy$$ extends com.dt.platform.domain.eam.AssetDepreciationDetail {

		private static final long serialVersionUID = 1L;

		
		/**
		 * 设置 主键
		 * @param id 主键
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setId(String id) {
			super.change(ID,super.getId(),id);
			super.setId(id);
			return this;
		}
		
		/**
		 * 设置 折旧方案
		 * @param depreciationId 折旧方案
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setDepreciationId(String depreciationId) {
			super.change(DEPRECIATION_ID,super.getDepreciationId(),depreciationId);
			super.setDepreciationId(depreciationId);
			return this;
		}
		
		/**
		 * 设置 折旧操作
		 * @param operId 折旧操作
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setOperId(String operId) {
			super.change(OPER_ID,super.getOperId(),operId);
			super.setOperId(operId);
			return this;
		}
		
		/**
		 * 设置 折旧动作
		 * @param actionCode 折旧动作
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setActionCode(String actionCode) {
			super.change(ACTION_CODE,super.getActionCode(),actionCode);
			super.setActionCode(actionCode);
			return this;
		}
		
		/**
		 * 设置 首次折旧方式
		 * @param firstDepreciationMethod 首次折旧方式
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setFirstDepreciationMethod(String firstDepreciationMethod) {
			super.change(FIRST_DEPRECIATION_METHOD,super.getFirstDepreciationMethod(),firstDepreciationMethod);
			super.setFirstDepreciationMethod(firstDepreciationMethod);
			return this;
		}
		
		/**
		 * 设置 折旧方式
		 * @param depreciationMethod 折旧方式
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setDepreciationMethod(String depreciationMethod) {
			super.change(DEPRECIATION_METHOD,super.getDepreciationMethod(),depreciationMethod);
			super.setDepreciationMethod(depreciationMethod);
			return this;
		}
		
		/**
		 * 设置 业务日期
		 * @param businessDate 业务日期
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setBusinessDate(Date businessDate) {
			super.change(BUSINESS_DATE,super.getBusinessDate(),businessDate);
			super.setBusinessDate(businessDate);
			return this;
		}
		
		/**
		 * 设置 折旧规则
		 * @param result 折旧规则
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setResult(String result) {
			super.change(RESULT,super.getResult(),result);
			super.setResult(result);
			return this;
		}
		
		/**
		 * 设置 折旧结果
		 * @param resultStatus 折旧结果
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setResultStatus(String resultStatus) {
			super.change(RESULT_STATUS,super.getResultStatus(),resultStatus);
			super.setResultStatus(resultStatus);
			return this;
		}
		
		/**
		 * 设置 结果明细
		 * @param resultDetail 结果明细
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setResultDetail(String resultDetail) {
			super.change(RESULT_DETAIL,super.getResultDetail(),resultDetail);
			super.setResultDetail(resultDetail);
			return this;
		}
		
		/**
		 * 设置 资产
		 * @param assetId 资产
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetId(String assetId) {
			super.change(ASSET_ID,super.getAssetId(),assetId);
			super.setAssetId(assetId);
			return this;
		}
		
		/**
		 * 设置 资产类别
		 * @param assetCategoryId 资产类别
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetCategoryId(String assetCategoryId) {
			super.change(ASSET_CATEGORY_ID,super.getAssetCategoryId(),assetCategoryId);
			super.setAssetCategoryId(assetCategoryId);
			return this;
		}
		
		/**
		 * 设置 资产类别
		 * @param assetCategoryName 资产类别
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetCategoryName(String assetCategoryName) {
			super.change(ASSET_CATEGORY_NAME,super.getAssetCategoryName(),assetCategoryName);
			super.setAssetCategoryName(assetCategoryName);
			return this;
		}
		
		/**
		 * 设置 财务分类
		 * @param assetFinanceCategoryId 财务分类
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetFinanceCategoryId(String assetFinanceCategoryId) {
			super.change(ASSET_FINANCE_CATEGORY_ID,super.getAssetFinanceCategoryId(),assetFinanceCategoryId);
			super.setAssetFinanceCategoryId(assetFinanceCategoryId);
			return this;
		}
		
		/**
		 * 设置 财务分类
		 * @param assetFinanceCategoryName 财务分类
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetFinanceCategoryName(String assetFinanceCategoryName) {
			super.change(ASSET_FINANCE_CATEGORY_NAME,super.getAssetFinanceCategoryName(),assetFinanceCategoryName);
			super.setAssetFinanceCategoryName(assetFinanceCategoryName);
			return this;
		}
		
		/**
		 * 设置 资产编码
		 * @param assetCode 资产编码
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetCode(String assetCode) {
			super.change(ASSET_CODE,super.getAssetCode(),assetCode);
			super.setAssetCode(assetCode);
			return this;
		}
		
		/**
		 * 设置 资产名称
		 * @param assetName 资产名称
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetName(String assetName) {
			super.change(ASSET_NAME,super.getAssetName(),assetName);
			super.setAssetName(assetName);
			return this;
		}
		
		/**
		 * 设置 资产型号
		 * @param assetModel 资产型号
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetModel(String assetModel) {
			super.change(ASSET_MODEL,super.getAssetModel(),assetModel);
			super.setAssetModel(assetModel);
			return this;
		}
		
		/**
		 * 设置 资产状态
		 * @param assetStatusName 资产状态
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetStatusName(String assetStatusName) {
			super.change(ASSET_STATUS_NAME,super.getAssetStatusName(),assetStatusName);
			super.setAssetStatusName(assetStatusName);
			return this;
		}
		
		/**
		 * 设置 采购日期
		 * @param assetPurchaseDate 采购日期
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetPurchaseDate(Date assetPurchaseDate) {
			super.change(ASSET_PURCHASE_DATE,super.getAssetPurchaseDate(),assetPurchaseDate);
			super.setAssetPurchaseDate(assetPurchaseDate);
			return this;
		}
		
		/**
		 * 设置 入账日期
		 * @param assetRegisterDate 入账日期
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetRegisterDate(Date assetRegisterDate) {
			super.change(ASSET_REGISTER_DATE,super.getAssetRegisterDate(),assetRegisterDate);
			super.setAssetRegisterDate(assetRegisterDate);
			return this;
		}
		
		/**
		 * 设置 资产原值
		 * @param assetOriginalUnitPrice 资产原值
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetOriginalUnitPrice(BigDecimal assetOriginalUnitPrice) {
			super.change(ASSET_ORIGINAL_UNIT_PRICE,super.getAssetOriginalUnitPrice(),assetOriginalUnitPrice);
			super.setAssetOriginalUnitPrice(assetOriginalUnitPrice);
			return this;
		}
		
		/**
		 * 设置 含税单价
		 * @param assetPurchaseUnitPrice 含税单价
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetPurchaseUnitPrice(BigDecimal assetPurchaseUnitPrice) {
			super.change(ASSET_PURCHASE_UNIT_PRICE,super.getAssetPurchaseUnitPrice(),assetPurchaseUnitPrice);
			super.setAssetPurchaseUnitPrice(assetPurchaseUnitPrice);
			return this;
		}
		
		/**
		 * 设置 资产净值
		 * @param assetNavPrice 资产净值
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetNavPrice(BigDecimal assetNavPrice) {
			super.change(ASSET_NAV_PRICE,super.getAssetNavPrice(),assetNavPrice);
			super.setAssetNavPrice(assetNavPrice);
			return this;
		}
		
		/**
		 * 设置 税额
		 * @param assetTaxAmountRate 税额
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetTaxAmountRate(BigDecimal assetTaxAmountRate) {
			super.change(ASSET_TAX_AMOUNT_RATE,super.getAssetTaxAmountRate(),assetTaxAmountRate);
			super.setAssetTaxAmountRate(assetTaxAmountRate);
			return this;
		}
		
		/**
		 * 设置 可使用期限(资产)
		 * @param assetServiceLife 可使用期限(资产)
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetServiceLife(BigDecimal assetServiceLife) {
			super.change(ASSET_SERVICE_LIFE,super.getAssetServiceLife(),assetServiceLife);
			super.setAssetServiceLife(assetServiceLife);
			return this;
		}
		
		/**
		 * 设置 可使用期限(财务)
		 * @param assetFinanceServiceLife 可使用期限(财务)
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetFinanceServiceLife(BigDecimal assetFinanceServiceLife) {
			super.change(ASSET_FINANCE_SERVICE_LIFE,super.getAssetFinanceServiceLife(),assetFinanceServiceLife);
			super.setAssetFinanceServiceLife(assetFinanceServiceLife);
			return this;
		}
		
		/**
		 * 设置 本期残值率
		 * @param assetResidualsRate 本期残值率
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetResidualsRate(BigDecimal assetResidualsRate) {
			super.change(ASSET_RESIDUALS_RATE,super.getAssetResidualsRate(),assetResidualsRate);
			super.setAssetResidualsRate(assetResidualsRate);
			return this;
		}
		
		/**
		 * 设置 本期残值
		 * @param assetResidualsPrice 本期残值
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetResidualsPrice(BigDecimal assetResidualsPrice) {
			super.change(ASSET_RESIDUALS_PRICE,super.getAssetResidualsPrice(),assetResidualsPrice);
			super.setAssetResidualsPrice(assetResidualsPrice);
			return this;
		}
		
		/**
		 * 设置 (期初)期初原值
		 * @param sOriginalPrice (期初)期初原值
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setSOriginalPrice(BigDecimal sOriginalPrice) {
			super.change(S_ORIGINAL_PRICE,super.getSOriginalPrice(),sOriginalPrice);
			super.setSOriginalPrice(sOriginalPrice);
			return this;
		}
		
		/**
		 * 设置 (期初)期初累计折旧
		 * @param sDepreciationAmount (期初)期初累计折旧
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setSDepreciationAmount(BigDecimal sDepreciationAmount) {
			super.change(S_DEPRECIATION_AMOUNT,super.getSDepreciationAmount(),sDepreciationAmount);
			super.setSDepreciationAmount(sDepreciationAmount);
			return this;
		}
		
		/**
		 * 设置 (期初)期初净值
		 * @param sNavAmount (期初)期初净值
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setSNavAmount(BigDecimal sNavAmount) {
			super.change(S_NAV_AMOUNT,super.getSNavAmount(),sNavAmount);
			super.setSNavAmount(sNavAmount);
			return this;
		}
		
		/**
		 * 设置 (期初)期初可回收净额
		 * @param sRecoverableAmount (期初)期初可回收净额
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setSRecoverableAmount(BigDecimal sRecoverableAmount) {
			super.change(S_RECOVERABLE_AMOUNT,super.getSRecoverableAmount(),sRecoverableAmount);
			super.setSRecoverableAmount(sRecoverableAmount);
			return this;
		}
		
		/**
		 * 设置 已使用期限
		 * @param cUsedServiceLife 已使用期限
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setCUsedServiceLife(BigDecimal cUsedServiceLife) {
			super.change(C_USED_SERVICE_LIFE,super.getCUsedServiceLife(),cUsedServiceLife);
			super.setCUsedServiceLife(cUsedServiceLife);
			return this;
		}
		
		/**
		 * 设置 (本期发生)原值增加
		 * @param cOriginalPriceIncrease (本期发生)原值增加
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setCOriginalPriceIncrease(BigDecimal cOriginalPriceIncrease) {
			super.change(C_ORIGINAL_PRICE_INCREASE,super.getCOriginalPriceIncrease(),cOriginalPriceIncrease);
			super.setCOriginalPriceIncrease(cOriginalPriceIncrease);
			return this;
		}
		
		/**
		 * 设置 (本期发生)本期折旧额
		 * @param cDepreciationAmount (本期发生)本期折旧额
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setCDepreciationAmount(BigDecimal cDepreciationAmount) {
			super.change(C_DEPRECIATION_AMOUNT,super.getCDepreciationAmount(),cDepreciationAmount);
			super.setCDepreciationAmount(cDepreciationAmount);
			return this;
		}
		
		/**
		 * 设置 (本期发生)本年累计折旧额
		 * @param cYearDepreciationAmount (本期发生)本年累计折旧额
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setCYearDepreciationAmount(BigDecimal cYearDepreciationAmount) {
			super.change(C_YEAR_DEPRECIATION_AMOUNT,super.getCYearDepreciationAmount(),cYearDepreciationAmount);
			super.setCYearDepreciationAmount(cYearDepreciationAmount);
			return this;
		}
		
		/**
		 * 设置 (期末)期末原值
		 * @param eOriginalPrice (期末)期末原值
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setEOriginalPrice(BigDecimal eOriginalPrice) {
			super.change(E_ORIGINAL_PRICE,super.getEOriginalPrice(),eOriginalPrice);
			super.setEOriginalPrice(eOriginalPrice);
			return this;
		}
		
		/**
		 * 设置 (期末)期末累计折旧
		 * @param eDepreciationAmount (期末)期末累计折旧
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setEDepreciationAmount(BigDecimal eDepreciationAmount) {
			super.change(E_DEPRECIATION_AMOUNT,super.getEDepreciationAmount(),eDepreciationAmount);
			super.setEDepreciationAmount(eDepreciationAmount);
			return this;
		}
		
		/**
		 * 设置 (期末)期末净值
		 * @param eNavAmount (期末)期末净值
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setENavAmount(BigDecimal eNavAmount) {
			super.change(E_NAV_AMOUNT,super.getENavAmount(),eNavAmount);
			super.setENavAmount(eNavAmount);
			return this;
		}
		
		/**
		 * 设置 (期末)期末可回收金额
		 * @param eRecoverableAmount (期末)期末可回收金额
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setERecoverableAmount(BigDecimal eRecoverableAmount) {
			super.change(E_RECOVERABLE_AMOUNT,super.getERecoverableAmount(),eRecoverableAmount);
			super.setERecoverableAmount(eRecoverableAmount);
			return this;
		}
		
		/**
		 * 设置 会计期间已使用期限
		 * @param accountingServiceLife 会计期间已使用期限
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAccountingServiceLife(BigDecimal accountingServiceLife) {
			super.change(ACCOUNTING_SERVICE_LIFE,super.getAccountingServiceLife(),accountingServiceLife);
			super.setAccountingServiceLife(accountingServiceLife);
			return this;
		}
		
		/**
		 * 设置 首次折旧
		 * @param firstDepreciation 首次折旧
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setFirstDepreciation(String firstDepreciation) {
			super.change(FIRST_DEPRECIATION,super.getFirstDepreciation(),firstDepreciation);
			super.setFirstDepreciation(firstDepreciation);
			return this;
		}
		
		/**
		 * 设置 使用人ID
		 * @param useUserId 使用人ID
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setUseUserId(String useUserId) {
			super.change(USE_USER_ID,super.getUseUserId(),useUserId);
			super.setUseUserId(useUserId);
			return this;
		}
		
		/**
		 * 设置 使用人
		 * @param useUserName 使用人
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setUseUserName(String useUserName) {
			super.change(USE_USER_NAME,super.getUseUserName(),useUserName);
			super.setUseUserName(useUserName);
			return this;
		}
		
		/**
		 * 设置 管理人员
		 * @param managerId 管理人员
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setManagerId(String managerId) {
			super.change(MANAGER_ID,super.getManagerId(),managerId);
			super.setManagerId(managerId);
			return this;
		}
		
		/**
		 * 设置 管理人员
		 * @param managerName 管理人员
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setManagerName(String managerName) {
			super.change(MANAGER_NAME,super.getManagerName(),managerName);
			super.setManagerName(managerName);
			return this;
		}
		
		/**
		 * 设置 部门ID
		 * @param useOrgId 部门ID
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setUseOrgId(String useOrgId) {
			super.change(USE_ORG_ID,super.getUseOrgId(),useOrgId);
			super.setUseOrgId(useOrgId);
			return this;
		}
		
		/**
		 * 设置 使用部门
		 * @param useOrgName 使用部门
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setUseOrgName(String useOrgName) {
			super.change(USE_ORG_NAME,super.getUseOrgName(),useOrgName);
			super.setUseOrgName(useOrgName);
			return this;
		}
		
		/**
		 * 设置 财务选项KEY
		 * @param financialOptionKey 财务选项KEY
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setFinancialOptionKey(String financialOptionKey) {
			super.change(FINANCIAL_OPTION_KEY,super.getFinancialOptionKey(),financialOptionKey);
			super.setFinancialOptionKey(financialOptionKey);
			return this;
		}
		
		/**
		 * 设置 费用项目KEY
		 * @param expenseItemKey 费用项目KEY
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setExpenseItemKey(String expenseItemKey) {
			super.change(EXPENSE_ITEM_KEY,super.getExpenseItemKey(),expenseItemKey);
			super.setExpenseItemKey(expenseItemKey);
			return this;
		}
		
		/**
		 * 设置 财务选项
		 * @param financialOptionName 财务选项
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setFinancialOptionName(String financialOptionName) {
			super.change(FINANCIAL_OPTION_NAME,super.getFinancialOptionName(),financialOptionName);
			super.setFinancialOptionName(financialOptionName);
			return this;
		}
		
		/**
		 * 设置 费用项目
		 * @param expenseItemName 费用项目
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setExpenseItemName(String expenseItemName) {
			super.change(EXPENSE_ITEM_NAME,super.getExpenseItemName(),expenseItemName);
			super.setExpenseItemName(expenseItemName);
			return this;
		}
		
		/**
		 * 设置 客户情况
		 * @param customerInfo 客户情况
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setCustomerInfo(String customerInfo) {
			super.change(CUSTOMER_INFO,super.getCustomerInfo(),customerInfo);
			super.setCustomerInfo(customerInfo);
			return this;
		}
		
		/**
		 * 设置 源资产
		 * @param detailIdSource 源资产
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setDetailIdSource(String detailIdSource) {
			super.change(DETAIL_ID_SOURCE,super.getDetailIdSource(),detailIdSource);
			super.setDetailIdSource(detailIdSource);
			return this;
		}
		
		/**
		 * 设置 目标资产
		 * @param detailIdTarget 目标资产
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setDetailIdTarget(String detailIdTarget) {
			super.change(DETAIL_ID_TARGET,super.getDetailIdTarget(),detailIdTarget);
			super.setDetailIdTarget(detailIdTarget);
			return this;
		}
		
		/**
		 * 设置 上次折旧单据
		 * @param lastOperId 上次折旧单据
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setLastOperId(String lastOperId) {
			super.change(LAST_OPER_ID,super.getLastOperId(),lastOperId);
			super.setLastOperId(lastOperId);
			return this;
		}
		
		/**
		 * 设置 上次折旧时间
		 * @param lastOperTime 上次折旧时间
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setLastOperTime(Date lastOperTime) {
			super.change(LAST_OPER_TIME,super.getLastOperTime(),lastOperTime);
			super.setLastOperTime(lastOperTime);
			return this;
		}
		
		/**
		 * 设置 标签
		 * @param label 标签
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setLabel(String label) {
			super.change(LABEL,super.getLabel(),label);
			super.setLabel(label);
			return this;
		}
		
		/**
		 * 设置 创建人ID
		 * @param createBy 创建人ID
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setCreateBy(String createBy) {
			super.change(CREATE_BY,super.getCreateBy(),createBy);
			super.setCreateBy(createBy);
			return this;
		}
		
		/**
		 * 设置 创建时间
		 * @param createTime 创建时间
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setCreateTime(Date createTime) {
			super.change(CREATE_TIME,super.getCreateTime(),createTime);
			super.setCreateTime(createTime);
			return this;
		}
		
		/**
		 * 设置 修改人ID
		 * @param updateBy 修改人ID
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setUpdateBy(String updateBy) {
			super.change(UPDATE_BY,super.getUpdateBy(),updateBy);
			super.setUpdateBy(updateBy);
			return this;
		}
		
		/**
		 * 设置 修改时间
		 * @param updateTime 修改时间
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setUpdateTime(Date updateTime) {
			super.change(UPDATE_TIME,super.getUpdateTime(),updateTime);
			super.setUpdateTime(updateTime);
			return this;
		}
		
		/**
		 * 设置 是否已删除
		 * @param deleted 是否已删除
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setDeleted(Integer deleted) {
			super.change(DELETED,super.getDeleted(),deleted);
			super.setDeleted(deleted);
			return this;
		}
		
		/**
		 * 设置 删除人ID
		 * @param deleteBy 删除人ID
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setDeleteBy(String deleteBy) {
			super.change(DELETE_BY,super.getDeleteBy(),deleteBy);
			super.setDeleteBy(deleteBy);
			return this;
		}
		
		/**
		 * 设置 删除时间
		 * @param deleteTime 删除时间
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setDeleteTime(Date deleteTime) {
			super.change(DELETE_TIME,super.getDeleteTime(),deleteTime);
			super.setDeleteTime(deleteTime);
			return this;
		}
		
		/**
		 * 设置 version
		 * @param version version
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setVersion(Integer version) {
			super.change(VERSION,super.getVersion(),version);
			super.setVersion(version);
			return this;
		}
		
		/**
		 * 设置 结果字符串
		 * @param resultValueStr 结果字符串
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setResultValueStr(String resultValueStr) {
			super.change(RESULT_VALUE_STR,super.getResultValueStr(),resultValueStr);
			super.setResultValueStr(resultValueStr);
			return this;
		}
		
		/**
		 * 设置 结果浮点
		 * @param resultValueFloat 结果浮点
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setResultValueFloat(BigDecimal resultValueFloat) {
			super.change(RESULT_VALUE_FLOAT,super.getResultValueFloat(),resultValueFloat);
			super.setResultValueFloat(resultValueFloat);
			return this;
		}
		
		/**
		 * 设置 资产
		 * @param assetSource 资产
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetSource(Asset assetSource) {
			super.change(ASSET_SOURCE,super.getAssetSource(),assetSource);
			super.setAssetSource(assetSource);
			return this;
		}
		
		/**
		 * 设置 资产
		 * @param assetTarget 资产
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetTarget(Asset assetTarget) {
			super.change(ASSET_TARGET,super.getAssetTarget(),assetTarget);
			super.setAssetTarget(assetTarget);
			return this;
		}
		
		/**
		 * 设置 资产
		 * @param asset 资产
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAsset(Asset asset) {
			super.change(ASSET,super.getAsset(),asset);
			super.setAsset(asset);
			return this;
		}
		
		/**
		 * 设置 上一期折旧数据
		 * @param lastAssetDepreciationDetail 上一期折旧数据
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setLastAssetDepreciationDetail(AssetDepreciationDetail lastAssetDepreciationDetail) {
			super.change(LAST_ASSET_DEPRECIATION_DETAIL,super.getLastAssetDepreciationDetail(),lastAssetDepreciationDetail);
			super.setLastAssetDepreciationDetail(lastAssetDepreciationDetail);
			return this;
		}
		
		/**
		 * 设置 方案
		 * @param assetDepreciation 方案
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetDepreciation(AssetDepreciation assetDepreciation) {
			super.change(ASSET_DEPRECIATION,super.getAssetDepreciation(),assetDepreciation);
			super.setAssetDepreciation(assetDepreciation);
			return this;
		}
		
		/**
		 * 设置 calRuleList
		 * @param calRuleList calRuleList
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setCalRuleList(List<AssetDepreciationCalRule> calRuleList) {
			super.change(CAL_RULE_LIST,super.getCalRuleList(),calRuleList);
			super.setCalRuleList(calRuleList);
			return this;
		}
		
		/**
		 * 设置 calRuleIds
		 * @param calRuleIds calRuleIds
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setCalRuleIds(List<String> calRuleIds) {
			super.change(CAL_RULE_IDS,super.getCalRuleIds(),calRuleIds);
			super.setCalRuleIds(calRuleIds);
			return this;
		}
		
		/**
		 * 设置 操作
		 * @param assetDepreciationOper 操作
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setAssetDepreciationOper(AssetDepreciationOper assetDepreciationOper) {
			super.change(ASSET_DEPRECIATION_OPER,super.getAssetDepreciationOper(),assetDepreciationOper);
			super.setAssetDepreciationOper(assetDepreciationOper);
			return this;
		}
		
		/**
		 * 设置 财务选项
		 * @param financialOptionDict 财务选项
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setFinancialOptionDict(DictItem financialOptionDict) {
			super.change(FINANCIAL_OPTION_DICT,super.getFinancialOptionDict(),financialOptionDict);
			super.setFinancialOptionDict(financialOptionDict);
			return this;
		}
		
		/**
		 * 设置 费用项目
		 * @param expenseItemDict 费用项目
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setExpenseItemDict(DictItem expenseItemDict) {
			super.change(EXPENSE_ITEM_DICT,super.getExpenseItemDict(),expenseItemDict);
			super.setExpenseItemDict(expenseItemDict);
			return this;
		}
		
		/**
		 * 设置 使用人员
		 * @param useUser 使用人员
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setUseUser(Employee useUser) {
			super.change(USE_USER,super.getUseUser(),useUser);
			super.setUseUser(useUser);
			return this;
		}
		
		/**
		 * 设置 使用公司/部门
		 * @param useOrganization 使用公司/部门
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setUseOrganization(Organization useOrganization) {
			super.change(USE_ORGANIZATION,super.getUseOrganization(),useOrganization);
			super.setUseOrganization(useOrganization);
			return this;
		}
		
		/**
		 * 设置 管理人员
		 * @param manager 管理人员
		 * @return 当前对象
		*/
		public AssetDepreciationDetail setManager(Employee manager) {
			super.change(MANAGER,super.getManager(),manager);
			super.setManager(manager);
			return this;
		}

		/**
		 * 克隆当前对象
		*/
		@Transient
		public AssetDepreciationDetail clone() {
			return duplicate(true);
		}

		/**
		 * 复制当前对象
		 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
		*/
		@Transient
		public AssetDepreciationDetail duplicate(boolean all) {
			$$proxy$$ inst=new $$proxy$$();
			inst.setAssetResidualsRate(this.getAssetResidualsRate());
			inst.setCUsedServiceLife(this.getCUsedServiceLife());
			inst.setUseOrgName(this.getUseOrgName());
			inst.setERecoverableAmount(this.getERecoverableAmount());
			inst.setDepreciationMethod(this.getDepreciationMethod());
			inst.setExpenseItemKey(this.getExpenseItemKey());
			inst.setCustomerInfo(this.getCustomerInfo());
			inst.setFirstDepreciation(this.getFirstDepreciation());
			inst.setManagerName(this.getManagerName());
			inst.setAssetNavPrice(this.getAssetNavPrice());
			inst.setExpenseItemName(this.getExpenseItemName());
			inst.setENavAmount(this.getENavAmount());
			inst.setDetailIdSource(this.getDetailIdSource());
			inst.setSDepreciationAmount(this.getSDepreciationAmount());
			inst.setActionCode(this.getActionCode());
			inst.setId(this.getId());
			inst.setCYearDepreciationAmount(this.getCYearDepreciationAmount());
			inst.setAssetFinanceServiceLife(this.getAssetFinanceServiceLife());
			inst.setAssetCode(this.getAssetCode());
			inst.setFinancialOptionKey(this.getFinancialOptionKey());
			inst.setVersion(this.getVersion());
			inst.setFirstDepreciationMethod(this.getFirstDepreciationMethod());
			inst.setAssetFinanceCategoryName(this.getAssetFinanceCategoryName());
			inst.setResultValueStr(this.getResultValueStr());
			inst.setLastOperId(this.getLastOperId());
			inst.setDeleteTime(this.getDeleteTime());
			inst.setEDepreciationAmount(this.getEDepreciationAmount());
			inst.setFinancialOptionName(this.getFinancialOptionName());
			inst.setAssetCategoryId(this.getAssetCategoryId());
			inst.setSOriginalPrice(this.getSOriginalPrice());
			inst.setDeleteBy(this.getDeleteBy());
			inst.setResultValueFloat(this.getResultValueFloat());
			inst.setAssetCategoryName(this.getAssetCategoryName());
			inst.setSNavAmount(this.getSNavAmount());
			inst.setAssetStatusName(this.getAssetStatusName());
			inst.setAssetTaxAmountRate(this.getAssetTaxAmountRate());
			inst.setOperId(this.getOperId());
			inst.setAssetRegisterDate(this.getAssetRegisterDate());
			inst.setSRecoverableAmount(this.getSRecoverableAmount());
			inst.setAssetPurchaseUnitPrice(this.getAssetPurchaseUnitPrice());
			inst.setEOriginalPrice(this.getEOriginalPrice());
			inst.setResult(this.getResult());
			inst.setBusinessDate(this.getBusinessDate());
			inst.setDepreciationId(this.getDepreciationId());
			inst.setAssetResidualsPrice(this.getAssetResidualsPrice());
			inst.setUpdateBy(this.getUpdateBy());
			inst.setAssetId(this.getAssetId());
			inst.setAssetServiceLife(this.getAssetServiceLife());
			inst.setCOriginalPriceIncrease(this.getCOriginalPriceIncrease());
			inst.setAccountingServiceLife(this.getAccountingServiceLife());
			inst.setUseUserName(this.getUseUserName());
			inst.setUseOrgId(this.getUseOrgId());
			inst.setAssetModel(this.getAssetModel());
			inst.setLastOperTime(this.getLastOperTime());
			inst.setUpdateTime(this.getUpdateTime());
			inst.setManagerId(this.getManagerId());
			inst.setLabel(this.getLabel());
			inst.setCDepreciationAmount(this.getCDepreciationAmount());
			inst.setResultStatus(this.getResultStatus());
			inst.setAssetFinanceCategoryId(this.getAssetFinanceCategoryId());
			inst.setCreateBy(this.getCreateBy());
			inst.setAssetPurchaseDate(this.getAssetPurchaseDate());
			inst.setDeleted(this.getDeleted());
			inst.setAssetOriginalUnitPrice(this.getAssetOriginalUnitPrice());
			inst.setResultDetail(this.getResultDetail());
			inst.setCreateTime(this.getCreateTime());
			inst.setAssetName(this.getAssetName());
			inst.setDetailIdTarget(this.getDetailIdTarget());
			inst.setUseUserId(this.getUseUserId());
			if(all) {
				inst.setManager(this.getManager());
				inst.setAssetSource(this.getAssetSource());
				inst.setLastAssetDepreciationDetail(this.getLastAssetDepreciationDetail());
				inst.setAssetDepreciation(this.getAssetDepreciation());
				inst.setFinancialOptionDict(this.getFinancialOptionDict());
				inst.setUseOrganization(this.getUseOrganization());
				inst.setAssetTarget(this.getAssetTarget());
				inst.setCalRuleList(this.getCalRuleList());
				inst.setCalRuleIds(this.getCalRuleIds());
				inst.setExpenseItemDict(this.getExpenseItemDict());
				inst.setAssetDepreciationOper(this.getAssetDepreciationOper());
				inst.setAsset(this.getAsset());
				inst.setUseUser(this.getUseUser());
			}
			inst.clearModifies();
			return inst;
		}

	}
}