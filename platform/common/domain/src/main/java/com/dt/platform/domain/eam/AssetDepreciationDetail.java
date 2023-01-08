package com.dt.platform.domain.eam;

import com.github.foxnic.dao.entity.Entity;
import io.swagger.annotations.ApiModel;
import javax.persistence.Table;
import com.github.foxnic.sql.meta.DBTable;
import com.dt.platform.constants.db.EAMTables.EAM_ASSET_DEPRECIATION_DETAIL;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.Transient;
import com.github.foxnic.api.swagger.EnumFor;
import java.util.List;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.hrm.Organization;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.foxnic.commons.lang.DataParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import com.github.foxnic.dao.entity.EntityContext;
import com.dt.platform.domain.eam.meta.AssetDepreciationDetailMeta;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 折旧明细
 * <p>折旧明细 , 数据表 eam_asset_depreciation_detail 的PO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-08 11:31:29
 * @sign 68AE6EB92174B775D3971C2CC734A3CB
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@Table(name = "eam_asset_depreciation_detail")
@ApiModel(description = "折旧明细 ; 折旧明细 , 数据表 eam_asset_depreciation_detail 的PO类型")
public class AssetDepreciationDetail extends Entity {

	private static final long serialVersionUID = 1L;

	public static final DBTable TABLE =EAM_ASSET_DEPRECIATION_DETAIL.$TABLE;
	
	/**
	 * 主键：主键
	*/
	@Id
	@ApiModelProperty(required = true,value="主键" , notes = "主键" , example = "664427663485566976")
	private String id;
	
	/**
	 * 折旧方案：折旧方案
	*/
	@ApiModelProperty(required = false,value="折旧方案" , notes = "折旧方案" , example = "647736203386290176")
	private String depreciationId;
	
	/**
	 * 折旧操作：折旧操作
	*/
	@ApiModelProperty(required = false,value="折旧操作" , notes = "折旧操作" , example = "664111029461975040")
	private String operId;
	
	/**
	 * 折旧动作：折旧动作
	*/
	@ApiModelProperty(required = false,value="折旧动作" , notes = "折旧动作")
	private String actionCode;
	
	/**
	 * 首次折旧方式：首次折旧方式
	*/
	@ApiModelProperty(required = false,value="首次折旧方式" , notes = "首次折旧方式" , example = "register_next_month")
	private String firstDepreciationMethod;
	
	/**
	 * 折旧方式：折旧方式
	*/
	@ApiModelProperty(required = false,value="折旧方式" , notes = "折旧方式" , example = "average_age")
	private String depreciationMethod;
	
	/**
	 * 业务日期：业务日期
	*/
	@ApiModelProperty(required = false,value="业务日期" , notes = "业务日期" , example = "2023-01-07 12:00:00")
	private Date businessDate;
	
	/**
	 * 折旧规则：折旧规则
	*/
	@ApiModelProperty(required = false,value="折旧规则" , notes = "折旧规则" , example = "depreciation_first")
	private String result;
	
	/**
	 * 折旧结果：折旧结果
	*/
	@ApiModelProperty(required = false,value="折旧结果" , notes = "折旧结果" , example = "success")
	private String resultStatus;
	
	/**
	 * 结果明细：结果明细
	*/
	@ApiModelProperty(required = false,value="结果明细" , notes = "结果明细" , example = "操作成功")
	private String resultDetail;
	
	/**
	 * 资产：资产
	*/
	@ApiModelProperty(required = false,value="资产" , notes = "资产" , example = "664110928148561920")
	private String assetId;
	
	/**
	 * 资产类别：资产类别
	*/
	@ApiModelProperty(required = false,value="资产类别" , notes = "资产类别")
	private String assetCategoryId;
	
	/**
	 * 资产类别：资产类别
	*/
	@ApiModelProperty(required = false,value="资产类别" , notes = "资产类别" , example = "办公设备")
	private String assetCategoryName;
	
	/**
	 * 财务分类：财务分类
	*/
	@ApiModelProperty(required = false,value="财务分类" , notes = "财务分类" , example = "649152215159668736")
	private String assetFinanceCategoryId;
	
	/**
	 * 财务分类：财务分类
	*/
	@ApiModelProperty(required = false,value="财务分类" , notes = "财务分类" , example = "电子设备")
	private String assetFinanceCategoryName;
	
	/**
	 * 资产编码：资产编码
	*/
	@ApiModelProperty(required = false,value="资产编码" , notes = "资产编码" , example = "AS16209")
	private String assetCode;
	
	/**
	 * 资产名称：资产名称
	*/
	@ApiModelProperty(required = false,value="资产名称" , notes = "资产名称" , example = "1212")
	private String assetName;
	
	/**
	 * 资产型号：资产型号
	*/
	@ApiModelProperty(required = false,value="资产型号" , notes = "资产型号")
	private String assetModel;
	
	/**
	 * 资产状态：资产状态
	*/
	@ApiModelProperty(required = false,value="资产状态" , notes = "资产状态" , example = "闲置")
	private String assetStatusName;
	
	/**
	 * 采购日期：采购日期
	*/
	@ApiModelProperty(required = false,value="采购日期" , notes = "采购日期" , example = "2023-01-05 12:00:00")
	private Date assetPurchaseDate;
	
	/**
	 * 入账日期：入账日期
	*/
	@ApiModelProperty(required = false,value="入账日期" , notes = "入账日期" , example = "2023-01-07 12:00:00")
	private Date assetRegisterDate;
	
	/**
	 * 资产原值：资产原值
	*/
	@ApiModelProperty(required = false,value="资产原值" , notes = "资产原值" , example = "1211.00")
	private BigDecimal assetOriginalUnitPrice;
	
	/**
	 * 含税单价：含税单价
	*/
	@ApiModelProperty(required = false,value="含税单价" , notes = "含税单价")
	private BigDecimal assetPurchaseUnitPrice;
	
	/**
	 * 资产净值：(当前)
	*/
	@ApiModelProperty(required = false,value="资产净值" , notes = "(当前)")
	private BigDecimal assetNavPrice;
	
	/**
	 * 税额：税额
	*/
	@ApiModelProperty(required = false,value="税额" , notes = "税额")
	private BigDecimal assetTaxAmountRate;
	
	/**
	 * 可使用期限(资产)：月
	*/
	@ApiModelProperty(required = false,value="可使用期限(资产)" , notes = "月" , example = "12.00")
	private BigDecimal assetServiceLife;
	
	/**
	 * 可使用期限(财务)：月
	*/
	@ApiModelProperty(required = false,value="可使用期限(财务)" , notes = "月" , example = "36.00")
	private BigDecimal assetFinanceServiceLife;
	
	/**
	 * 本期残值率：本期残值率
	*/
	@ApiModelProperty(required = false,value="本期残值率" , notes = "本期残值率" , example = "5.00")
	private BigDecimal assetResidualsRate;
	
	/**
	 * 本期残值：本期残值
	*/
	@ApiModelProperty(required = false,value="本期残值" , notes = "本期残值" , example = "60.55")
	private BigDecimal assetResidualsPrice;
	
	/**
	 * (期初)期初原值：(期初)期初原值
	*/
	@ApiModelProperty(required = false,value="(期初)期初原值" , notes = "(期初)期初原值" , example = "0.00")
	private BigDecimal sOriginalPrice;
	
	/**
	 * (期初)期初累计折旧：(期初)期初累计折旧
	*/
	@ApiModelProperty(required = false,value="(期初)期初累计折旧" , notes = "(期初)期初累计折旧" , example = "0.00")
	private BigDecimal sDepreciationAmount;
	
	/**
	 * (期初)期初净值：(期初)期初净值
	*/
	@ApiModelProperty(required = false,value="(期初)期初净值" , notes = "(期初)期初净值" , example = "0.00")
	private BigDecimal sNavAmount;
	
	/**
	 * (期初)期初可回收净额：(期初)期初可回收净额
	*/
	@ApiModelProperty(required = false,value="(期初)期初可回收净额" , notes = "(期初)期初可回收净额" , example = "0.00")
	private BigDecimal sRecoverableAmount;
	
	/**
	 * 已使用期限：月
	*/
	@ApiModelProperty(required = false,value="已使用期限" , notes = "月" , example = "0.00")
	private BigDecimal cUsedServiceLife;
	
	/**
	 * (本期发生)原值增加：(本期发生)原值增加
	*/
	@ApiModelProperty(required = false,value="(本期发生)原值增加" , notes = "(本期发生)原值增加" , example = "1211.00")
	private BigDecimal cOriginalPriceIncrease;
	
	/**
	 * (本期发生)本期折旧额：(本期发生)本期折旧额
	*/
	@ApiModelProperty(required = false,value="(本期发生)本期折旧额" , notes = "(本期发生)本期折旧额" , example = "0.00")
	private BigDecimal cDepreciationAmount;
	
	/**
	 * (本期发生)本年累计折旧额：(本期发生)本年累计折旧额
	*/
	@ApiModelProperty(required = false,value="(本期发生)本年累计折旧额" , notes = "(本期发生)本年累计折旧额" , example = "0.00")
	private BigDecimal cYearDepreciationAmount;
	
	/**
	 * (期末)期末原值：(期末)期末原值
	*/
	@ApiModelProperty(required = false,value="(期末)期末原值" , notes = "(期末)期末原值" , example = "1211.00")
	private BigDecimal eOriginalPrice;
	
	/**
	 * (期末)期末累计折旧：(期末)期末累计折旧
	*/
	@ApiModelProperty(required = false,value="(期末)期末累计折旧" , notes = "(期末)期末累计折旧" , example = "0.00")
	private BigDecimal eDepreciationAmount;
	
	/**
	 * (期末)期末净值：(期末)期末净值
	*/
	@ApiModelProperty(required = false,value="(期末)期末净值" , notes = "(期末)期末净值" , example = "1211.00")
	private BigDecimal eNavAmount;
	
	/**
	 * (期末)期末可回收金额：(期末)期末可回收金额
	*/
	@ApiModelProperty(required = false,value="(期末)期末可回收金额" , notes = "(期末)期末可回收金额" , example = "1211.00")
	private BigDecimal eRecoverableAmount;
	
	/**
	 * 会计期间已使用期限：月
	*/
	@ApiModelProperty(required = false,value="会计期间已使用期限" , notes = "月" , example = "0.00")
	private BigDecimal accountingServiceLife;
	
	/**
	 * 首次折旧：0否,1是
	*/
	@ApiModelProperty(required = false,value="首次折旧" , notes = "0否,1是" , example = "1")
	private String firstDepreciation;
	
	/**
	 * 使用人ID：使用人ID
	*/
	@ApiModelProperty(required = false,value="使用人ID" , notes = "使用人ID")
	private String useUserId;
	
	/**
	 * 使用人：使用人
	*/
	@ApiModelProperty(required = false,value="使用人" , notes = "使用人")
	private String useUserName;
	
	/**
	 * 管理人员：管理人员
	*/
	@ApiModelProperty(required = false,value="管理人员" , notes = "管理人员")
	private String managerId;
	
	/**
	 * 管理人员：管理人员
	*/
	@ApiModelProperty(required = false,value="管理人员" , notes = "管理人员")
	private String managerName;
	
	/**
	 * 部门ID：部门ID
	*/
	@ApiModelProperty(required = false,value="部门ID" , notes = "部门ID")
	private String useOrgId;
	
	/**
	 * 使用部门：使用部门
	*/
	@ApiModelProperty(required = false,value="使用部门" , notes = "使用部门")
	private String useOrgName;
	
	/**
	 * 财务选项KEY：财务选项KEY
	*/
	@ApiModelProperty(required = false,value="财务选项KEY" , notes = "财务选项KEY")
	private String financialOptionKey;
	
	/**
	 * 费用项目KEY：费用项目KEY
	*/
	@ApiModelProperty(required = false,value="费用项目KEY" , notes = "费用项目KEY")
	private String expenseItemKey;
	
	/**
	 * 财务选项：财务选项
	*/
	@ApiModelProperty(required = false,value="财务选项" , notes = "财务选项")
	private String financialOptionName;
	
	/**
	 * 费用项目：费用项目
	*/
	@ApiModelProperty(required = false,value="费用项目" , notes = "费用项目")
	private String expenseItemName;
	
	/**
	 * 客户情况：客户情况
	*/
	@ApiModelProperty(required = false,value="客户情况" , notes = "客户情况")
	private String customerInfo;
	
	/**
	 * 源资产：源资产
	*/
	@ApiModelProperty(required = false,value="源资产" , notes = "源资产" , example = "664110928148561920")
	private String detailIdSource;
	
	/**
	 * 目标资产：目标资产
	*/
	@ApiModelProperty(required = false,value="目标资产" , notes = "目标资产")
	private String detailIdTarget;
	
	/**
	 * 上次折旧单据：上次折旧单据
	*/
	@ApiModelProperty(required = false,value="上次折旧单据" , notes = "上次折旧单据")
	private String lastOperId;
	
	/**
	 * 上次折旧时间：上次折旧时间
	*/
	@ApiModelProperty(required = false,value="上次折旧时间" , notes = "上次折旧时间")
	private Date lastOperTime;
	
	/**
	 * 标签：标签
	*/
	@ApiModelProperty(required = false,value="标签" , notes = "标签")
	private String label;
	
	/**
	 * 创建人ID：创建人ID
	*/
	@ApiModelProperty(required = true,value="创建人ID" , notes = "创建人ID" , example = "E001")
	private String createBy;
	
	/**
	 * 创建时间：创建时间
	*/
	@ApiModelProperty(required = false,value="创建时间" , notes = "创建时间" , example = "2023-01-08 11:18:19")
	private Date createTime;
	
	/**
	 * 修改人ID：修改人ID
	*/
	@ApiModelProperty(required = false,value="修改人ID" , notes = "修改人ID")
	private String updateBy;
	
	/**
	 * 修改时间：修改时间
	*/
	@ApiModelProperty(required = false,value="修改时间" , notes = "修改时间" , example = "2023-01-08 11:18:20")
	private Date updateTime;
	
	/**
	 * 是否已删除：是否已删除
	*/
	@ApiModelProperty(required = true,value="是否已删除" , notes = "是否已删除" , example = "0")
	private Integer deleted;
	@Transient
	@EnumFor("deleted")
	private Boolean deletedBool;
	
	/**
	 * 删除人ID：删除人ID
	*/
	@ApiModelProperty(required = false,value="删除人ID" , notes = "删除人ID")
	private String deleteBy;
	
	/**
	 * 删除时间：删除时间
	*/
	@ApiModelProperty(required = false,value="删除时间" , notes = "删除时间")
	private Date deleteTime;
	
	/**
	 * version：version
	*/
	@ApiModelProperty(required = true,value="version" , notes = "version" , example = "1")
	private Integer version;
	
	/**
	 * 结果字符串：结果字符串
	*/
	@ApiModelProperty(required = false,value="结果字符串" , notes = "结果字符串")
	private String resultValueStr;
	
	/**
	 * 结果浮点：结果浮点
	*/
	@ApiModelProperty(required = false,value="结果浮点" , notes = "结果浮点")
	private BigDecimal resultValueFloat;
	
	/**
	 * 资产：资产
	*/
	@ApiModelProperty(required = false,value="资产" , notes = "资产")
	private Asset assetSource;
	
	/**
	 * 资产：资产
	*/
	@ApiModelProperty(required = false,value="资产" , notes = "资产")
	private Asset assetTarget;
	
	/**
	 * 资产：资产
	*/
	@ApiModelProperty(required = false,value="资产" , notes = "资产")
	private Asset asset;
	
	/**
	 * 上一期折旧数据：上一期折旧数据
	*/
	@ApiModelProperty(required = false,value="上一期折旧数据" , notes = "上一期折旧数据")
	private AssetDepreciationDetail lastAssetDepreciationDetail;
	
	/**
	 * 方案：方案
	*/
	@ApiModelProperty(required = false,value="方案" , notes = "方案")
	private AssetDepreciation assetDepreciation;
	
	/**
	 * calRuleList：calRuleList
	*/
	@ApiModelProperty(required = false,value="calRuleList" , notes = "calRuleList")
	private List<AssetDepreciationCalRule> calRuleList;
	
	/**
	 * calRuleIds：calRuleIds
	*/
	@ApiModelProperty(required = false,value="calRuleIds" , notes = "calRuleIds")
	private List<String> calRuleIds;
	
	/**
	 * 操作：操作
	*/
	@ApiModelProperty(required = false,value="操作" , notes = "操作")
	private AssetDepreciationOper assetDepreciationOper;
	
	/**
	 * 财务选项：财务选项
	*/
	@ApiModelProperty(required = false,value="财务选项" , notes = "财务选项")
	private DictItem financialOptionDict;
	
	/**
	 * 费用项目：费用项目
	*/
	@ApiModelProperty(required = false,value="费用项目" , notes = "费用项目")
	private DictItem expenseItemDict;
	
	/**
	 * 使用人员：使用人员
	*/
	@ApiModelProperty(required = false,value="使用人员" , notes = "使用人员")
	private Employee useUser;
	
	/**
	 * 使用公司/部门：使用公司/部门
	*/
	@ApiModelProperty(required = false,value="使用公司/部门" , notes = "使用公司/部门")
	private Organization useOrganization;
	
	/**
	 * 管理人员：管理人员
	*/
	@ApiModelProperty(required = false,value="管理人员" , notes = "管理人员")
	private Employee manager;
	
	/**
	 * 获得 主键<br>
	 * 主键
	 * @return 主键
	*/
	public String getId() {
		return id;
	}
	
	/**
	 * 设置 主键
	 * @param id 主键
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setId(String id) {
		this.id=id;
		return this;
	}
	
	/**
	 * 获得 折旧方案<br>
	 * 折旧方案
	 * @return 折旧方案
	*/
	public String getDepreciationId() {
		return depreciationId;
	}
	
	/**
	 * 设置 折旧方案
	 * @param depreciationId 折旧方案
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setDepreciationId(String depreciationId) {
		this.depreciationId=depreciationId;
		return this;
	}
	
	/**
	 * 获得 折旧操作<br>
	 * 折旧操作
	 * @return 折旧操作
	*/
	public String getOperId() {
		return operId;
	}
	
	/**
	 * 设置 折旧操作
	 * @param operId 折旧操作
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setOperId(String operId) {
		this.operId=operId;
		return this;
	}
	
	/**
	 * 获得 折旧动作<br>
	 * 折旧动作
	 * @return 折旧动作
	*/
	public String getActionCode() {
		return actionCode;
	}
	
	/**
	 * 设置 折旧动作
	 * @param actionCode 折旧动作
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setActionCode(String actionCode) {
		this.actionCode=actionCode;
		return this;
	}
	
	/**
	 * 获得 首次折旧方式<br>
	 * 首次折旧方式
	 * @return 首次折旧方式
	*/
	public String getFirstDepreciationMethod() {
		return firstDepreciationMethod;
	}
	
	/**
	 * 设置 首次折旧方式
	 * @param firstDepreciationMethod 首次折旧方式
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setFirstDepreciationMethod(String firstDepreciationMethod) {
		this.firstDepreciationMethod=firstDepreciationMethod;
		return this;
	}
	
	/**
	 * 获得 折旧方式<br>
	 * 折旧方式
	 * @return 折旧方式
	*/
	public String getDepreciationMethod() {
		return depreciationMethod;
	}
	
	/**
	 * 设置 折旧方式
	 * @param depreciationMethod 折旧方式
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setDepreciationMethod(String depreciationMethod) {
		this.depreciationMethod=depreciationMethod;
		return this;
	}
	
	/**
	 * 获得 业务日期<br>
	 * 业务日期
	 * @return 业务日期
	*/
	public Date getBusinessDate() {
		return businessDate;
	}
	
	/**
	 * 设置 业务日期
	 * @param businessDate 业务日期
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setBusinessDate(Date businessDate) {
		this.businessDate=businessDate;
		return this;
	}
	
	/**
	 * 获得 折旧规则<br>
	 * 折旧规则
	 * @return 折旧规则
	*/
	public String getResult() {
		return result;
	}
	
	/**
	 * 设置 折旧规则
	 * @param result 折旧规则
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setResult(String result) {
		this.result=result;
		return this;
	}
	
	/**
	 * 获得 折旧结果<br>
	 * 折旧结果
	 * @return 折旧结果
	*/
	public String getResultStatus() {
		return resultStatus;
	}
	
	/**
	 * 设置 折旧结果
	 * @param resultStatus 折旧结果
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setResultStatus(String resultStatus) {
		this.resultStatus=resultStatus;
		return this;
	}
	
	/**
	 * 获得 结果明细<br>
	 * 结果明细
	 * @return 结果明细
	*/
	public String getResultDetail() {
		return resultDetail;
	}
	
	/**
	 * 设置 结果明细
	 * @param resultDetail 结果明细
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setResultDetail(String resultDetail) {
		this.resultDetail=resultDetail;
		return this;
	}
	
	/**
	 * 获得 资产<br>
	 * 资产
	 * @return 资产
	*/
	public String getAssetId() {
		return assetId;
	}
	
	/**
	 * 设置 资产
	 * @param assetId 资产
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetId(String assetId) {
		this.assetId=assetId;
		return this;
	}
	
	/**
	 * 获得 资产类别<br>
	 * 资产类别
	 * @return 资产类别
	*/
	public String getAssetCategoryId() {
		return assetCategoryId;
	}
	
	/**
	 * 设置 资产类别
	 * @param assetCategoryId 资产类别
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetCategoryId(String assetCategoryId) {
		this.assetCategoryId=assetCategoryId;
		return this;
	}
	
	/**
	 * 获得 资产类别<br>
	 * 资产类别
	 * @return 资产类别
	*/
	public String getAssetCategoryName() {
		return assetCategoryName;
	}
	
	/**
	 * 设置 资产类别
	 * @param assetCategoryName 资产类别
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetCategoryName(String assetCategoryName) {
		this.assetCategoryName=assetCategoryName;
		return this;
	}
	
	/**
	 * 获得 财务分类<br>
	 * 财务分类
	 * @return 财务分类
	*/
	public String getAssetFinanceCategoryId() {
		return assetFinanceCategoryId;
	}
	
	/**
	 * 设置 财务分类
	 * @param assetFinanceCategoryId 财务分类
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetFinanceCategoryId(String assetFinanceCategoryId) {
		this.assetFinanceCategoryId=assetFinanceCategoryId;
		return this;
	}
	
	/**
	 * 获得 财务分类<br>
	 * 财务分类
	 * @return 财务分类
	*/
	public String getAssetFinanceCategoryName() {
		return assetFinanceCategoryName;
	}
	
	/**
	 * 设置 财务分类
	 * @param assetFinanceCategoryName 财务分类
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetFinanceCategoryName(String assetFinanceCategoryName) {
		this.assetFinanceCategoryName=assetFinanceCategoryName;
		return this;
	}
	
	/**
	 * 获得 资产编码<br>
	 * 资产编码
	 * @return 资产编码
	*/
	public String getAssetCode() {
		return assetCode;
	}
	
	/**
	 * 设置 资产编码
	 * @param assetCode 资产编码
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetCode(String assetCode) {
		this.assetCode=assetCode;
		return this;
	}
	
	/**
	 * 获得 资产名称<br>
	 * 资产名称
	 * @return 资产名称
	*/
	public String getAssetName() {
		return assetName;
	}
	
	/**
	 * 设置 资产名称
	 * @param assetName 资产名称
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetName(String assetName) {
		this.assetName=assetName;
		return this;
	}
	
	/**
	 * 获得 资产型号<br>
	 * 资产型号
	 * @return 资产型号
	*/
	public String getAssetModel() {
		return assetModel;
	}
	
	/**
	 * 设置 资产型号
	 * @param assetModel 资产型号
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetModel(String assetModel) {
		this.assetModel=assetModel;
		return this;
	}
	
	/**
	 * 获得 资产状态<br>
	 * 资产状态
	 * @return 资产状态
	*/
	public String getAssetStatusName() {
		return assetStatusName;
	}
	
	/**
	 * 设置 资产状态
	 * @param assetStatusName 资产状态
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetStatusName(String assetStatusName) {
		this.assetStatusName=assetStatusName;
		return this;
	}
	
	/**
	 * 获得 采购日期<br>
	 * 采购日期
	 * @return 采购日期
	*/
	public Date getAssetPurchaseDate() {
		return assetPurchaseDate;
	}
	
	/**
	 * 设置 采购日期
	 * @param assetPurchaseDate 采购日期
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetPurchaseDate(Date assetPurchaseDate) {
		this.assetPurchaseDate=assetPurchaseDate;
		return this;
	}
	
	/**
	 * 获得 入账日期<br>
	 * 入账日期
	 * @return 入账日期
	*/
	public Date getAssetRegisterDate() {
		return assetRegisterDate;
	}
	
	/**
	 * 设置 入账日期
	 * @param assetRegisterDate 入账日期
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetRegisterDate(Date assetRegisterDate) {
		this.assetRegisterDate=assetRegisterDate;
		return this;
	}
	
	/**
	 * 获得 资产原值<br>
	 * 资产原值
	 * @return 资产原值
	*/
	public BigDecimal getAssetOriginalUnitPrice() {
		return assetOriginalUnitPrice;
	}
	
	/**
	 * 设置 资产原值
	 * @param assetOriginalUnitPrice 资产原值
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetOriginalUnitPrice(BigDecimal assetOriginalUnitPrice) {
		this.assetOriginalUnitPrice=assetOriginalUnitPrice;
		return this;
	}
	
	/**
	 * 获得 含税单价<br>
	 * 含税单价
	 * @return 含税单价
	*/
	public BigDecimal getAssetPurchaseUnitPrice() {
		return assetPurchaseUnitPrice;
	}
	
	/**
	 * 设置 含税单价
	 * @param assetPurchaseUnitPrice 含税单价
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetPurchaseUnitPrice(BigDecimal assetPurchaseUnitPrice) {
		this.assetPurchaseUnitPrice=assetPurchaseUnitPrice;
		return this;
	}
	
	/**
	 * 获得 资产净值<br>
	 * (当前)
	 * @return 资产净值
	*/
	public BigDecimal getAssetNavPrice() {
		return assetNavPrice;
	}
	
	/**
	 * 设置 资产净值
	 * @param assetNavPrice 资产净值
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetNavPrice(BigDecimal assetNavPrice) {
		this.assetNavPrice=assetNavPrice;
		return this;
	}
	
	/**
	 * 获得 税额<br>
	 * 税额
	 * @return 税额
	*/
	public BigDecimal getAssetTaxAmountRate() {
		return assetTaxAmountRate;
	}
	
	/**
	 * 设置 税额
	 * @param assetTaxAmountRate 税额
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetTaxAmountRate(BigDecimal assetTaxAmountRate) {
		this.assetTaxAmountRate=assetTaxAmountRate;
		return this;
	}
	
	/**
	 * 获得 可使用期限(资产)<br>
	 * 月
	 * @return 可使用期限(资产)
	*/
	public BigDecimal getAssetServiceLife() {
		return assetServiceLife;
	}
	
	/**
	 * 设置 可使用期限(资产)
	 * @param assetServiceLife 可使用期限(资产)
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetServiceLife(BigDecimal assetServiceLife) {
		this.assetServiceLife=assetServiceLife;
		return this;
	}
	
	/**
	 * 获得 可使用期限(财务)<br>
	 * 月
	 * @return 可使用期限(财务)
	*/
	public BigDecimal getAssetFinanceServiceLife() {
		return assetFinanceServiceLife;
	}
	
	/**
	 * 设置 可使用期限(财务)
	 * @param assetFinanceServiceLife 可使用期限(财务)
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetFinanceServiceLife(BigDecimal assetFinanceServiceLife) {
		this.assetFinanceServiceLife=assetFinanceServiceLife;
		return this;
	}
	
	/**
	 * 获得 本期残值率<br>
	 * 本期残值率
	 * @return 本期残值率
	*/
	public BigDecimal getAssetResidualsRate() {
		return assetResidualsRate;
	}
	
	/**
	 * 设置 本期残值率
	 * @param assetResidualsRate 本期残值率
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetResidualsRate(BigDecimal assetResidualsRate) {
		this.assetResidualsRate=assetResidualsRate;
		return this;
	}
	
	/**
	 * 获得 本期残值<br>
	 * 本期残值
	 * @return 本期残值
	*/
	public BigDecimal getAssetResidualsPrice() {
		return assetResidualsPrice;
	}
	
	/**
	 * 设置 本期残值
	 * @param assetResidualsPrice 本期残值
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetResidualsPrice(BigDecimal assetResidualsPrice) {
		this.assetResidualsPrice=assetResidualsPrice;
		return this;
	}
	
	/**
	 * 获得 (期初)期初原值<br>
	 * (期初)期初原值
	 * @return (期初)期初原值
	*/
	public BigDecimal getSOriginalPrice() {
		return sOriginalPrice;
	}
	
	/**
	 * 设置 (期初)期初原值
	 * @param sOriginalPrice (期初)期初原值
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setSOriginalPrice(BigDecimal sOriginalPrice) {
		this.sOriginalPrice=sOriginalPrice;
		return this;
	}
	
	/**
	 * 获得 (期初)期初累计折旧<br>
	 * (期初)期初累计折旧
	 * @return (期初)期初累计折旧
	*/
	public BigDecimal getSDepreciationAmount() {
		return sDepreciationAmount;
	}
	
	/**
	 * 设置 (期初)期初累计折旧
	 * @param sDepreciationAmount (期初)期初累计折旧
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setSDepreciationAmount(BigDecimal sDepreciationAmount) {
		this.sDepreciationAmount=sDepreciationAmount;
		return this;
	}
	
	/**
	 * 获得 (期初)期初净值<br>
	 * (期初)期初净值
	 * @return (期初)期初净值
	*/
	public BigDecimal getSNavAmount() {
		return sNavAmount;
	}
	
	/**
	 * 设置 (期初)期初净值
	 * @param sNavAmount (期初)期初净值
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setSNavAmount(BigDecimal sNavAmount) {
		this.sNavAmount=sNavAmount;
		return this;
	}
	
	/**
	 * 获得 (期初)期初可回收净额<br>
	 * (期初)期初可回收净额
	 * @return (期初)期初可回收净额
	*/
	public BigDecimal getSRecoverableAmount() {
		return sRecoverableAmount;
	}
	
	/**
	 * 设置 (期初)期初可回收净额
	 * @param sRecoverableAmount (期初)期初可回收净额
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setSRecoverableAmount(BigDecimal sRecoverableAmount) {
		this.sRecoverableAmount=sRecoverableAmount;
		return this;
	}
	
	/**
	 * 获得 已使用期限<br>
	 * 月
	 * @return 已使用期限
	*/
	public BigDecimal getCUsedServiceLife() {
		return cUsedServiceLife;
	}
	
	/**
	 * 设置 已使用期限
	 * @param cUsedServiceLife 已使用期限
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setCUsedServiceLife(BigDecimal cUsedServiceLife) {
		this.cUsedServiceLife=cUsedServiceLife;
		return this;
	}
	
	/**
	 * 获得 (本期发生)原值增加<br>
	 * (本期发生)原值增加
	 * @return (本期发生)原值增加
	*/
	public BigDecimal getCOriginalPriceIncrease() {
		return cOriginalPriceIncrease;
	}
	
	/**
	 * 设置 (本期发生)原值增加
	 * @param cOriginalPriceIncrease (本期发生)原值增加
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setCOriginalPriceIncrease(BigDecimal cOriginalPriceIncrease) {
		this.cOriginalPriceIncrease=cOriginalPriceIncrease;
		return this;
	}
	
	/**
	 * 获得 (本期发生)本期折旧额<br>
	 * (本期发生)本期折旧额
	 * @return (本期发生)本期折旧额
	*/
	public BigDecimal getCDepreciationAmount() {
		return cDepreciationAmount;
	}
	
	/**
	 * 设置 (本期发生)本期折旧额
	 * @param cDepreciationAmount (本期发生)本期折旧额
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setCDepreciationAmount(BigDecimal cDepreciationAmount) {
		this.cDepreciationAmount=cDepreciationAmount;
		return this;
	}
	
	/**
	 * 获得 (本期发生)本年累计折旧额<br>
	 * (本期发生)本年累计折旧额
	 * @return (本期发生)本年累计折旧额
	*/
	public BigDecimal getCYearDepreciationAmount() {
		return cYearDepreciationAmount;
	}
	
	/**
	 * 设置 (本期发生)本年累计折旧额
	 * @param cYearDepreciationAmount (本期发生)本年累计折旧额
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setCYearDepreciationAmount(BigDecimal cYearDepreciationAmount) {
		this.cYearDepreciationAmount=cYearDepreciationAmount;
		return this;
	}
	
	/**
	 * 获得 (期末)期末原值<br>
	 * (期末)期末原值
	 * @return (期末)期末原值
	*/
	public BigDecimal getEOriginalPrice() {
		return eOriginalPrice;
	}
	
	/**
	 * 设置 (期末)期末原值
	 * @param eOriginalPrice (期末)期末原值
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setEOriginalPrice(BigDecimal eOriginalPrice) {
		this.eOriginalPrice=eOriginalPrice;
		return this;
	}
	
	/**
	 * 获得 (期末)期末累计折旧<br>
	 * (期末)期末累计折旧
	 * @return (期末)期末累计折旧
	*/
	public BigDecimal getEDepreciationAmount() {
		return eDepreciationAmount;
	}
	
	/**
	 * 设置 (期末)期末累计折旧
	 * @param eDepreciationAmount (期末)期末累计折旧
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setEDepreciationAmount(BigDecimal eDepreciationAmount) {
		this.eDepreciationAmount=eDepreciationAmount;
		return this;
	}
	
	/**
	 * 获得 (期末)期末净值<br>
	 * (期末)期末净值
	 * @return (期末)期末净值
	*/
	public BigDecimal getENavAmount() {
		return eNavAmount;
	}
	
	/**
	 * 设置 (期末)期末净值
	 * @param eNavAmount (期末)期末净值
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setENavAmount(BigDecimal eNavAmount) {
		this.eNavAmount=eNavAmount;
		return this;
	}
	
	/**
	 * 获得 (期末)期末可回收金额<br>
	 * (期末)期末可回收金额
	 * @return (期末)期末可回收金额
	*/
	public BigDecimal getERecoverableAmount() {
		return eRecoverableAmount;
	}
	
	/**
	 * 设置 (期末)期末可回收金额
	 * @param eRecoverableAmount (期末)期末可回收金额
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setERecoverableAmount(BigDecimal eRecoverableAmount) {
		this.eRecoverableAmount=eRecoverableAmount;
		return this;
	}
	
	/**
	 * 获得 会计期间已使用期限<br>
	 * 月
	 * @return 会计期间已使用期限
	*/
	public BigDecimal getAccountingServiceLife() {
		return accountingServiceLife;
	}
	
	/**
	 * 设置 会计期间已使用期限
	 * @param accountingServiceLife 会计期间已使用期限
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAccountingServiceLife(BigDecimal accountingServiceLife) {
		this.accountingServiceLife=accountingServiceLife;
		return this;
	}
	
	/**
	 * 获得 首次折旧<br>
	 * 0否,1是
	 * @return 首次折旧
	*/
	public String getFirstDepreciation() {
		return firstDepreciation;
	}
	
	/**
	 * 设置 首次折旧
	 * @param firstDepreciation 首次折旧
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setFirstDepreciation(String firstDepreciation) {
		this.firstDepreciation=firstDepreciation;
		return this;
	}
	
	/**
	 * 获得 使用人ID<br>
	 * 使用人ID
	 * @return 使用人ID
	*/
	public String getUseUserId() {
		return useUserId;
	}
	
	/**
	 * 设置 使用人ID
	 * @param useUserId 使用人ID
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setUseUserId(String useUserId) {
		this.useUserId=useUserId;
		return this;
	}
	
	/**
	 * 获得 使用人<br>
	 * 使用人
	 * @return 使用人
	*/
	public String getUseUserName() {
		return useUserName;
	}
	
	/**
	 * 设置 使用人
	 * @param useUserName 使用人
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setUseUserName(String useUserName) {
		this.useUserName=useUserName;
		return this;
	}
	
	/**
	 * 获得 管理人员<br>
	 * 管理人员
	 * @return 管理人员
	*/
	public String getManagerId() {
		return managerId;
	}
	
	/**
	 * 设置 管理人员
	 * @param managerId 管理人员
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setManagerId(String managerId) {
		this.managerId=managerId;
		return this;
	}
	
	/**
	 * 获得 管理人员<br>
	 * 管理人员
	 * @return 管理人员
	*/
	public String getManagerName() {
		return managerName;
	}
	
	/**
	 * 设置 管理人员
	 * @param managerName 管理人员
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setManagerName(String managerName) {
		this.managerName=managerName;
		return this;
	}
	
	/**
	 * 获得 部门ID<br>
	 * 部门ID
	 * @return 部门ID
	*/
	public String getUseOrgId() {
		return useOrgId;
	}
	
	/**
	 * 设置 部门ID
	 * @param useOrgId 部门ID
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setUseOrgId(String useOrgId) {
		this.useOrgId=useOrgId;
		return this;
	}
	
	/**
	 * 获得 使用部门<br>
	 * 使用部门
	 * @return 使用部门
	*/
	public String getUseOrgName() {
		return useOrgName;
	}
	
	/**
	 * 设置 使用部门
	 * @param useOrgName 使用部门
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setUseOrgName(String useOrgName) {
		this.useOrgName=useOrgName;
		return this;
	}
	
	/**
	 * 获得 财务选项KEY<br>
	 * 财务选项KEY
	 * @return 财务选项KEY
	*/
	public String getFinancialOptionKey() {
		return financialOptionKey;
	}
	
	/**
	 * 设置 财务选项KEY
	 * @param financialOptionKey 财务选项KEY
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setFinancialOptionKey(String financialOptionKey) {
		this.financialOptionKey=financialOptionKey;
		return this;
	}
	
	/**
	 * 获得 费用项目KEY<br>
	 * 费用项目KEY
	 * @return 费用项目KEY
	*/
	public String getExpenseItemKey() {
		return expenseItemKey;
	}
	
	/**
	 * 设置 费用项目KEY
	 * @param expenseItemKey 费用项目KEY
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setExpenseItemKey(String expenseItemKey) {
		this.expenseItemKey=expenseItemKey;
		return this;
	}
	
	/**
	 * 获得 财务选项<br>
	 * 财务选项
	 * @return 财务选项
	*/
	public String getFinancialOptionName() {
		return financialOptionName;
	}
	
	/**
	 * 设置 财务选项
	 * @param financialOptionName 财务选项
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setFinancialOptionName(String financialOptionName) {
		this.financialOptionName=financialOptionName;
		return this;
	}
	
	/**
	 * 获得 费用项目<br>
	 * 费用项目
	 * @return 费用项目
	*/
	public String getExpenseItemName() {
		return expenseItemName;
	}
	
	/**
	 * 设置 费用项目
	 * @param expenseItemName 费用项目
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setExpenseItemName(String expenseItemName) {
		this.expenseItemName=expenseItemName;
		return this;
	}
	
	/**
	 * 获得 客户情况<br>
	 * 客户情况
	 * @return 客户情况
	*/
	public String getCustomerInfo() {
		return customerInfo;
	}
	
	/**
	 * 设置 客户情况
	 * @param customerInfo 客户情况
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setCustomerInfo(String customerInfo) {
		this.customerInfo=customerInfo;
		return this;
	}
	
	/**
	 * 获得 源资产<br>
	 * 源资产
	 * @return 源资产
	*/
	public String getDetailIdSource() {
		return detailIdSource;
	}
	
	/**
	 * 设置 源资产
	 * @param detailIdSource 源资产
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setDetailIdSource(String detailIdSource) {
		this.detailIdSource=detailIdSource;
		return this;
	}
	
	/**
	 * 获得 目标资产<br>
	 * 目标资产
	 * @return 目标资产
	*/
	public String getDetailIdTarget() {
		return detailIdTarget;
	}
	
	/**
	 * 设置 目标资产
	 * @param detailIdTarget 目标资产
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setDetailIdTarget(String detailIdTarget) {
		this.detailIdTarget=detailIdTarget;
		return this;
	}
	
	/**
	 * 获得 上次折旧单据<br>
	 * 上次折旧单据
	 * @return 上次折旧单据
	*/
	public String getLastOperId() {
		return lastOperId;
	}
	
	/**
	 * 设置 上次折旧单据
	 * @param lastOperId 上次折旧单据
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setLastOperId(String lastOperId) {
		this.lastOperId=lastOperId;
		return this;
	}
	
	/**
	 * 获得 上次折旧时间<br>
	 * 上次折旧时间
	 * @return 上次折旧时间
	*/
	public Date getLastOperTime() {
		return lastOperTime;
	}
	
	/**
	 * 设置 上次折旧时间
	 * @param lastOperTime 上次折旧时间
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setLastOperTime(Date lastOperTime) {
		this.lastOperTime=lastOperTime;
		return this;
	}
	
	/**
	 * 获得 标签<br>
	 * 标签
	 * @return 标签
	*/
	public String getLabel() {
		return label;
	}
	
	/**
	 * 设置 标签
	 * @param label 标签
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setLabel(String label) {
		this.label=label;
		return this;
	}
	
	/**
	 * 获得 创建人ID<br>
	 * 创建人ID
	 * @return 创建人ID
	*/
	public String getCreateBy() {
		return createBy;
	}
	
	/**
	 * 设置 创建人ID
	 * @param createBy 创建人ID
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setCreateBy(String createBy) {
		this.createBy=createBy;
		return this;
	}
	
	/**
	 * 获得 创建时间<br>
	 * 创建时间
	 * @return 创建时间
	*/
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置 创建时间
	 * @param createTime 创建时间
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setCreateTime(Date createTime) {
		this.createTime=createTime;
		return this;
	}
	
	/**
	 * 获得 修改人ID<br>
	 * 修改人ID
	 * @return 修改人ID
	*/
	public String getUpdateBy() {
		return updateBy;
	}
	
	/**
	 * 设置 修改人ID
	 * @param updateBy 修改人ID
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setUpdateBy(String updateBy) {
		this.updateBy=updateBy;
		return this;
	}
	
	/**
	 * 获得 修改时间<br>
	 * 修改时间
	 * @return 修改时间
	*/
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/**
	 * 设置 修改时间
	 * @param updateTime 修改时间
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setUpdateTime(Date updateTime) {
		this.updateTime=updateTime;
		return this;
	}
	
	/**
	 * 获得 是否已删除<br>
	 * 是否已删除
	 * @return 是否已删除
	*/
	public Integer getDeleted() {
		return deleted;
	}
	
	/**
	 * 获得 是否已删除 的投影属性<br>
	 * 等价于 getDeleted 方法，获得对应的枚举类型
	 * @return 是否已删除
	*/
	@Transient
	public Boolean isDeleted() {
		if(this.deletedBool==null) {
			this.deletedBool=DataParser.parseBoolean(deleted);
		}
		return this.deletedBool ;
	}
	
	/**
	 * 设置 是否已删除
	 * @param deleted 是否已删除
	 * @return 当前对象
	*/
	@JsonProperty("deleted")
	public AssetDepreciationDetail setDeleted(Integer deleted) {
		this.deleted=deleted;
		this.deletedBool=DataParser.parseBoolean(deleted);
		return this;
	}
	
	/**
	 * 设置 是否已删除的投影属性，等同于设置 是否已删除
	 * @param deletedBool 是否已删除
	 * @return 当前对象
	*/
	@Transient
	public AssetDepreciationDetail setDeleted(Boolean deletedBool) {
		if(deletedBool==null) {
			this.deleted=null;
		} else {
			this.deleted=deletedBool?1:0;
		}
		this.deletedBool=deletedBool;
		return this;
	}
	
	/**
	 * 获得 删除人ID<br>
	 * 删除人ID
	 * @return 删除人ID
	*/
	public String getDeleteBy() {
		return deleteBy;
	}
	
	/**
	 * 设置 删除人ID
	 * @param deleteBy 删除人ID
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setDeleteBy(String deleteBy) {
		this.deleteBy=deleteBy;
		return this;
	}
	
	/**
	 * 获得 删除时间<br>
	 * 删除时间
	 * @return 删除时间
	*/
	public Date getDeleteTime() {
		return deleteTime;
	}
	
	/**
	 * 设置 删除时间
	 * @param deleteTime 删除时间
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setDeleteTime(Date deleteTime) {
		this.deleteTime=deleteTime;
		return this;
	}
	
	/**
	 * 获得 version<br>
	 * version
	 * @return version
	*/
	public Integer getVersion() {
		return version;
	}
	
	/**
	 * 设置 version
	 * @param version version
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setVersion(Integer version) {
		this.version=version;
		return this;
	}
	
	/**
	 * 获得 结果字符串<br>
	 * 结果字符串
	 * @return 结果字符串
	*/
	public String getResultValueStr() {
		return resultValueStr;
	}
	
	/**
	 * 设置 结果字符串
	 * @param resultValueStr 结果字符串
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setResultValueStr(String resultValueStr) {
		this.resultValueStr=resultValueStr;
		return this;
	}
	
	/**
	 * 获得 结果浮点<br>
	 * 结果浮点
	 * @return 结果浮点
	*/
	public BigDecimal getResultValueFloat() {
		return resultValueFloat;
	}
	
	/**
	 * 设置 结果浮点
	 * @param resultValueFloat 结果浮点
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setResultValueFloat(BigDecimal resultValueFloat) {
		this.resultValueFloat=resultValueFloat;
		return this;
	}
	
	/**
	 * 获得 资产<br>
	 * 资产
	 * @return 资产
	*/
	public Asset getAssetSource() {
		return assetSource;
	}
	
	/**
	 * 设置 资产
	 * @param assetSource 资产
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetSource(Asset assetSource) {
		this.assetSource=assetSource;
		return this;
	}
	
	/**
	 * 获得 资产<br>
	 * 资产
	 * @return 资产
	*/
	public Asset getAssetTarget() {
		return assetTarget;
	}
	
	/**
	 * 设置 资产
	 * @param assetTarget 资产
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetTarget(Asset assetTarget) {
		this.assetTarget=assetTarget;
		return this;
	}
	
	/**
	 * 获得 资产<br>
	 * 资产
	 * @return 资产
	*/
	public Asset getAsset() {
		return asset;
	}
	
	/**
	 * 设置 资产
	 * @param asset 资产
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAsset(Asset asset) {
		this.asset=asset;
		return this;
	}
	
	/**
	 * 获得 上一期折旧数据<br>
	 * 上一期折旧数据
	 * @return 上一期折旧数据
	*/
	public AssetDepreciationDetail getLastAssetDepreciationDetail() {
		return lastAssetDepreciationDetail;
	}
	
	/**
	 * 设置 上一期折旧数据
	 * @param lastAssetDepreciationDetail 上一期折旧数据
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setLastAssetDepreciationDetail(AssetDepreciationDetail lastAssetDepreciationDetail) {
		this.lastAssetDepreciationDetail=lastAssetDepreciationDetail;
		return this;
	}
	
	/**
	 * 获得 方案<br>
	 * 方案
	 * @return 方案
	*/
	public AssetDepreciation getAssetDepreciation() {
		return assetDepreciation;
	}
	
	/**
	 * 设置 方案
	 * @param assetDepreciation 方案
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetDepreciation(AssetDepreciation assetDepreciation) {
		this.assetDepreciation=assetDepreciation;
		return this;
	}
	
	/**
	 * 获得 calRuleList<br>
	 * calRuleList
	 * @return calRuleList
	*/
	public List<AssetDepreciationCalRule> getCalRuleList() {
		return calRuleList;
	}
	
	/**
	 * 设置 calRuleList
	 * @param calRuleList calRuleList
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setCalRuleList(List<AssetDepreciationCalRule> calRuleList) {
		this.calRuleList=calRuleList;
		return this;
	}
	
	/**
	 * 添加 calRuleList
	 * @param calRule calRuleList
	 * @return 当前对象
	*/
	public AssetDepreciationDetail addCalRule(AssetDepreciationCalRule... calRule) {
		if(this.calRuleList==null) calRuleList=new ArrayList<>();
		this.calRuleList.addAll(Arrays.asList(calRule));
		return this;
	}
	
	/**
	 * 获得 calRuleIds<br>
	 * calRuleIds
	 * @return calRuleIds
	*/
	public List<String> getCalRuleIds() {
		return calRuleIds;
	}
	
	/**
	 * 设置 calRuleIds
	 * @param calRuleIds calRuleIds
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setCalRuleIds(List<String> calRuleIds) {
		this.calRuleIds=calRuleIds;
		return this;
	}
	
	/**
	 * 添加 calRuleIds
	 * @param calRuleId calRuleIds
	 * @return 当前对象
	*/
	public AssetDepreciationDetail addCalRuleId(String... calRuleId) {
		if(this.calRuleIds==null) calRuleIds=new ArrayList<>();
		this.calRuleIds.addAll(Arrays.asList(calRuleId));
		return this;
	}
	
	/**
	 * 获得 操作<br>
	 * 操作
	 * @return 操作
	*/
	public AssetDepreciationOper getAssetDepreciationOper() {
		return assetDepreciationOper;
	}
	
	/**
	 * 设置 操作
	 * @param assetDepreciationOper 操作
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAssetDepreciationOper(AssetDepreciationOper assetDepreciationOper) {
		this.assetDepreciationOper=assetDepreciationOper;
		return this;
	}
	
	/**
	 * 获得 财务选项<br>
	 * 财务选项
	 * @return 财务选项
	*/
	public DictItem getFinancialOptionDict() {
		return financialOptionDict;
	}
	
	/**
	 * 设置 财务选项
	 * @param financialOptionDict 财务选项
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setFinancialOptionDict(DictItem financialOptionDict) {
		this.financialOptionDict=financialOptionDict;
		return this;
	}
	
	/**
	 * 获得 费用项目<br>
	 * 费用项目
	 * @return 费用项目
	*/
	public DictItem getExpenseItemDict() {
		return expenseItemDict;
	}
	
	/**
	 * 设置 费用项目
	 * @param expenseItemDict 费用项目
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setExpenseItemDict(DictItem expenseItemDict) {
		this.expenseItemDict=expenseItemDict;
		return this;
	}
	
	/**
	 * 获得 使用人员<br>
	 * 使用人员
	 * @return 使用人员
	*/
	public Employee getUseUser() {
		return useUser;
	}
	
	/**
	 * 设置 使用人员
	 * @param useUser 使用人员
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setUseUser(Employee useUser) {
		this.useUser=useUser;
		return this;
	}
	
	/**
	 * 获得 使用公司/部门<br>
	 * 使用公司/部门
	 * @return 使用公司/部门
	*/
	public Organization getUseOrganization() {
		return useOrganization;
	}
	
	/**
	 * 设置 使用公司/部门
	 * @param useOrganization 使用公司/部门
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setUseOrganization(Organization useOrganization) {
		this.useOrganization=useOrganization;
		return this;
	}
	
	/**
	 * 获得 管理人员<br>
	 * 管理人员
	 * @return 管理人员
	*/
	public Employee getManager() {
		return manager;
	}
	
	/**
	 * 设置 管理人员
	 * @param manager 管理人员
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setManager(Employee manager) {
		this.manager=manager;
		return this;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return AssetDepreciationDetail , 转换好的 AssetDepreciationDetail 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return AssetDepreciationDetail , 转换好的 PoJo 对象
	*/
	@Transient
	public <T> T toPojo(Class<T> pojoType) {
		if(Entity.class.isAssignableFrom(pojoType)) {
			return (T)this.toPO((Class<Entity>)pojoType);
		}
		try {
			T pojo=pojoType.newInstance();
			EntityContext.copyProperties(pojo, this);
			return pojo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
		com.dt.platform.domain.eam.meta.AssetDepreciationDetailMeta.$$proxy$$ inst = new com.dt.platform.domain.eam.meta.AssetDepreciationDetailMeta.$$proxy$$();
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

	/**
	 * 克隆当前对象
	*/
	@Transient
	public AssetDepreciationDetail clone(boolean deep) {
		return EntityContext.clone(AssetDepreciationDetail.class,this,deep);
	}

	/**
	 * 将 Map 转换成 AssetDepreciationDetail
	 * @param assetDepreciationDetailMap 包含实体信息的 Map 对象
	 * @return AssetDepreciationDetail , 转换好的的 AssetDepreciationDetail 对象
	*/
	@Transient
	public static AssetDepreciationDetail createFrom(Map<String,Object> assetDepreciationDetailMap) {
		if(assetDepreciationDetailMap==null) return null;
		AssetDepreciationDetail po = create();
		EntityContext.copyProperties(po,assetDepreciationDetailMap);
		po.clearModifies();
		return po;
	}

	/**
	 * 将 Pojo 转换成 AssetDepreciationDetail
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return AssetDepreciationDetail , 转换好的的 AssetDepreciationDetail 对象
	*/
	@Transient
	public static AssetDepreciationDetail createFrom(Object pojo) {
		if(pojo==null) return null;
		AssetDepreciationDetail po = create();
		EntityContext.copyProperties(po,pojo);
		po.clearModifies();
		return po;
	}

	/**
	 * 创建一个 AssetDepreciationDetail，等同于 new
	 * @return AssetDepreciationDetail 对象
	*/
	@Transient
	public static AssetDepreciationDetail create() {
		return new com.dt.platform.domain.eam.meta.AssetDepreciationDetailMeta.$$proxy$$();
	}

	/**
	 * 从 Map 读取
	 * @param map 记录数据
	 * @param cast 是否用 DataParser 进行类型转换
	 * @return  是否读取成功
	*/
	public boolean read(Map<String, Object> map,boolean cast) {
		if(map==null) return false;
		if(cast) {
			this.setAssetResidualsRate(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.ASSET_RESIDUALS_RATE)));
			this.setCUsedServiceLife(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.C_USED_SERVICE_LIFE)));
			this.setUseOrgName(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.USE_ORG_NAME)));
			this.setERecoverableAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.E_RECOVERABLE_AMOUNT)));
			this.setDepreciationMethod(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.DEPRECIATION_METHOD)));
			this.setExpenseItemKey(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.EXPENSE_ITEM_KEY)));
			this.setCustomerInfo(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.CUSTOMER_INFO)));
			this.setFirstDepreciation(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.FIRST_DEPRECIATION)));
			this.setManagerName(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.MANAGER_NAME)));
			this.setAssetNavPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.ASSET_NAV_PRICE)));
			this.setExpenseItemName(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.EXPENSE_ITEM_NAME)));
			this.setENavAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.E_NAV_AMOUNT)));
			this.setDetailIdSource(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.DETAIL_ID_SOURCE)));
			this.setSDepreciationAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.S_DEPRECIATION_AMOUNT)));
			this.setActionCode(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ACTION_CODE)));
			this.setId(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ID)));
			this.setCYearDepreciationAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.C_YEAR_DEPRECIATION_AMOUNT)));
			this.setAssetFinanceServiceLife(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.ASSET_FINANCE_SERVICE_LIFE)));
			this.setAssetCode(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ASSET_CODE)));
			this.setFinancialOptionKey(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.FINANCIAL_OPTION_KEY)));
			this.setVersion(DataParser.parse(Integer.class, map.get(AssetDepreciationDetailMeta.VERSION)));
			this.setFirstDepreciationMethod(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.FIRST_DEPRECIATION_METHOD)));
			this.setAssetFinanceCategoryName(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ASSET_FINANCE_CATEGORY_NAME)));
			this.setResultValueStr(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.RESULT_VALUE_STR)));
			this.setLastOperId(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.LAST_OPER_ID)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(AssetDepreciationDetailMeta.DELETE_TIME)));
			this.setEDepreciationAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.E_DEPRECIATION_AMOUNT)));
			this.setFinancialOptionName(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.FINANCIAL_OPTION_NAME)));
			this.setAssetCategoryId(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ASSET_CATEGORY_ID)));
			this.setSOriginalPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.S_ORIGINAL_PRICE)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.DELETE_BY)));
			this.setResultValueFloat(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.RESULT_VALUE_FLOAT)));
			this.setAssetCategoryName(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ASSET_CATEGORY_NAME)));
			this.setSNavAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.S_NAV_AMOUNT)));
			this.setAssetStatusName(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ASSET_STATUS_NAME)));
			this.setAssetTaxAmountRate(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.ASSET_TAX_AMOUNT_RATE)));
			this.setOperId(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.OPER_ID)));
			this.setAssetRegisterDate(DataParser.parse(Date.class, map.get(AssetDepreciationDetailMeta.ASSET_REGISTER_DATE)));
			this.setSRecoverableAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.S_RECOVERABLE_AMOUNT)));
			this.setAssetPurchaseUnitPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.ASSET_PURCHASE_UNIT_PRICE)));
			this.setEOriginalPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.E_ORIGINAL_PRICE)));
			this.setResult(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.RESULT)));
			this.setBusinessDate(DataParser.parse(Date.class, map.get(AssetDepreciationDetailMeta.BUSINESS_DATE)));
			this.setDepreciationId(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.DEPRECIATION_ID)));
			this.setAssetResidualsPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.ASSET_RESIDUALS_PRICE)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.UPDATE_BY)));
			this.setAssetId(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ASSET_ID)));
			this.setAssetServiceLife(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.ASSET_SERVICE_LIFE)));
			this.setCOriginalPriceIncrease(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.C_ORIGINAL_PRICE_INCREASE)));
			this.setAccountingServiceLife(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.ACCOUNTING_SERVICE_LIFE)));
			this.setUseUserName(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.USE_USER_NAME)));
			this.setUseOrgId(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.USE_ORG_ID)));
			this.setAssetModel(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ASSET_MODEL)));
			this.setLastOperTime(DataParser.parse(Date.class, map.get(AssetDepreciationDetailMeta.LAST_OPER_TIME)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(AssetDepreciationDetailMeta.UPDATE_TIME)));
			this.setManagerId(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.MANAGER_ID)));
			this.setLabel(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.LABEL)));
			this.setCDepreciationAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.C_DEPRECIATION_AMOUNT)));
			this.setResultStatus(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.RESULT_STATUS)));
			this.setAssetFinanceCategoryId(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ASSET_FINANCE_CATEGORY_ID)));
			this.setCreateBy(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.CREATE_BY)));
			this.setAssetPurchaseDate(DataParser.parse(Date.class, map.get(AssetDepreciationDetailMeta.ASSET_PURCHASE_DATE)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(AssetDepreciationDetailMeta.DELETED)));
			this.setAssetOriginalUnitPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.ASSET_ORIGINAL_UNIT_PRICE)));
			this.setResultDetail(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.RESULT_DETAIL)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(AssetDepreciationDetailMeta.CREATE_TIME)));
			this.setAssetName(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ASSET_NAME)));
			this.setDetailIdTarget(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.DETAIL_ID_TARGET)));
			this.setUseUserId(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.USE_USER_ID)));
			// others
			this.setManager(DataParser.parse(Employee.class, map.get(AssetDepreciationDetailMeta.MANAGER)));
			this.setAssetSource(DataParser.parse(Asset.class, map.get(AssetDepreciationDetailMeta.ASSET_SOURCE)));
			this.setLastAssetDepreciationDetail(DataParser.parse(AssetDepreciationDetail.class, map.get(AssetDepreciationDetailMeta.LAST_ASSET_DEPRECIATION_DETAIL)));
			this.setAssetDepreciation(DataParser.parse(AssetDepreciation.class, map.get(AssetDepreciationDetailMeta.ASSET_DEPRECIATION)));
			this.setFinancialOptionDict(DataParser.parse(DictItem.class, map.get(AssetDepreciationDetailMeta.FINANCIAL_OPTION_DICT)));
			this.setUseOrganization(DataParser.parse(Organization.class, map.get(AssetDepreciationDetailMeta.USE_ORGANIZATION)));
			this.setAssetTarget(DataParser.parse(Asset.class, map.get(AssetDepreciationDetailMeta.ASSET_TARGET)));
			this.setExpenseItemDict(DataParser.parse(DictItem.class, map.get(AssetDepreciationDetailMeta.EXPENSE_ITEM_DICT)));
			this.setAssetDepreciationOper(DataParser.parse(AssetDepreciationOper.class, map.get(AssetDepreciationDetailMeta.ASSET_DEPRECIATION_OPER)));
			this.setAsset(DataParser.parse(Asset.class, map.get(AssetDepreciationDetailMeta.ASSET)));
			this.setUseUser(DataParser.parse(Employee.class, map.get(AssetDepreciationDetailMeta.USE_USER)));
			return true;
		} else {
			try {
				this.setAssetResidualsRate( (BigDecimal)map.get(AssetDepreciationDetailMeta.ASSET_RESIDUALS_RATE));
				this.setCUsedServiceLife( (BigDecimal)map.get(AssetDepreciationDetailMeta.C_USED_SERVICE_LIFE));
				this.setUseOrgName( (String)map.get(AssetDepreciationDetailMeta.USE_ORG_NAME));
				this.setERecoverableAmount( (BigDecimal)map.get(AssetDepreciationDetailMeta.E_RECOVERABLE_AMOUNT));
				this.setDepreciationMethod( (String)map.get(AssetDepreciationDetailMeta.DEPRECIATION_METHOD));
				this.setExpenseItemKey( (String)map.get(AssetDepreciationDetailMeta.EXPENSE_ITEM_KEY));
				this.setCustomerInfo( (String)map.get(AssetDepreciationDetailMeta.CUSTOMER_INFO));
				this.setFirstDepreciation( (String)map.get(AssetDepreciationDetailMeta.FIRST_DEPRECIATION));
				this.setManagerName( (String)map.get(AssetDepreciationDetailMeta.MANAGER_NAME));
				this.setAssetNavPrice( (BigDecimal)map.get(AssetDepreciationDetailMeta.ASSET_NAV_PRICE));
				this.setExpenseItemName( (String)map.get(AssetDepreciationDetailMeta.EXPENSE_ITEM_NAME));
				this.setENavAmount( (BigDecimal)map.get(AssetDepreciationDetailMeta.E_NAV_AMOUNT));
				this.setDetailIdSource( (String)map.get(AssetDepreciationDetailMeta.DETAIL_ID_SOURCE));
				this.setSDepreciationAmount( (BigDecimal)map.get(AssetDepreciationDetailMeta.S_DEPRECIATION_AMOUNT));
				this.setActionCode( (String)map.get(AssetDepreciationDetailMeta.ACTION_CODE));
				this.setId( (String)map.get(AssetDepreciationDetailMeta.ID));
				this.setCYearDepreciationAmount( (BigDecimal)map.get(AssetDepreciationDetailMeta.C_YEAR_DEPRECIATION_AMOUNT));
				this.setAssetFinanceServiceLife( (BigDecimal)map.get(AssetDepreciationDetailMeta.ASSET_FINANCE_SERVICE_LIFE));
				this.setAssetCode( (String)map.get(AssetDepreciationDetailMeta.ASSET_CODE));
				this.setFinancialOptionKey( (String)map.get(AssetDepreciationDetailMeta.FINANCIAL_OPTION_KEY));
				this.setVersion( (Integer)map.get(AssetDepreciationDetailMeta.VERSION));
				this.setFirstDepreciationMethod( (String)map.get(AssetDepreciationDetailMeta.FIRST_DEPRECIATION_METHOD));
				this.setAssetFinanceCategoryName( (String)map.get(AssetDepreciationDetailMeta.ASSET_FINANCE_CATEGORY_NAME));
				this.setResultValueStr( (String)map.get(AssetDepreciationDetailMeta.RESULT_VALUE_STR));
				this.setLastOperId( (String)map.get(AssetDepreciationDetailMeta.LAST_OPER_ID));
				this.setDeleteTime( (Date)map.get(AssetDepreciationDetailMeta.DELETE_TIME));
				this.setEDepreciationAmount( (BigDecimal)map.get(AssetDepreciationDetailMeta.E_DEPRECIATION_AMOUNT));
				this.setFinancialOptionName( (String)map.get(AssetDepreciationDetailMeta.FINANCIAL_OPTION_NAME));
				this.setAssetCategoryId( (String)map.get(AssetDepreciationDetailMeta.ASSET_CATEGORY_ID));
				this.setSOriginalPrice( (BigDecimal)map.get(AssetDepreciationDetailMeta.S_ORIGINAL_PRICE));
				this.setDeleteBy( (String)map.get(AssetDepreciationDetailMeta.DELETE_BY));
				this.setResultValueFloat( (BigDecimal)map.get(AssetDepreciationDetailMeta.RESULT_VALUE_FLOAT));
				this.setAssetCategoryName( (String)map.get(AssetDepreciationDetailMeta.ASSET_CATEGORY_NAME));
				this.setSNavAmount( (BigDecimal)map.get(AssetDepreciationDetailMeta.S_NAV_AMOUNT));
				this.setAssetStatusName( (String)map.get(AssetDepreciationDetailMeta.ASSET_STATUS_NAME));
				this.setAssetTaxAmountRate( (BigDecimal)map.get(AssetDepreciationDetailMeta.ASSET_TAX_AMOUNT_RATE));
				this.setOperId( (String)map.get(AssetDepreciationDetailMeta.OPER_ID));
				this.setAssetRegisterDate( (Date)map.get(AssetDepreciationDetailMeta.ASSET_REGISTER_DATE));
				this.setSRecoverableAmount( (BigDecimal)map.get(AssetDepreciationDetailMeta.S_RECOVERABLE_AMOUNT));
				this.setAssetPurchaseUnitPrice( (BigDecimal)map.get(AssetDepreciationDetailMeta.ASSET_PURCHASE_UNIT_PRICE));
				this.setEOriginalPrice( (BigDecimal)map.get(AssetDepreciationDetailMeta.E_ORIGINAL_PRICE));
				this.setResult( (String)map.get(AssetDepreciationDetailMeta.RESULT));
				this.setBusinessDate( (Date)map.get(AssetDepreciationDetailMeta.BUSINESS_DATE));
				this.setDepreciationId( (String)map.get(AssetDepreciationDetailMeta.DEPRECIATION_ID));
				this.setAssetResidualsPrice( (BigDecimal)map.get(AssetDepreciationDetailMeta.ASSET_RESIDUALS_PRICE));
				this.setUpdateBy( (String)map.get(AssetDepreciationDetailMeta.UPDATE_BY));
				this.setAssetId( (String)map.get(AssetDepreciationDetailMeta.ASSET_ID));
				this.setAssetServiceLife( (BigDecimal)map.get(AssetDepreciationDetailMeta.ASSET_SERVICE_LIFE));
				this.setCOriginalPriceIncrease( (BigDecimal)map.get(AssetDepreciationDetailMeta.C_ORIGINAL_PRICE_INCREASE));
				this.setAccountingServiceLife( (BigDecimal)map.get(AssetDepreciationDetailMeta.ACCOUNTING_SERVICE_LIFE));
				this.setUseUserName( (String)map.get(AssetDepreciationDetailMeta.USE_USER_NAME));
				this.setUseOrgId( (String)map.get(AssetDepreciationDetailMeta.USE_ORG_ID));
				this.setAssetModel( (String)map.get(AssetDepreciationDetailMeta.ASSET_MODEL));
				this.setLastOperTime( (Date)map.get(AssetDepreciationDetailMeta.LAST_OPER_TIME));
				this.setUpdateTime( (Date)map.get(AssetDepreciationDetailMeta.UPDATE_TIME));
				this.setManagerId( (String)map.get(AssetDepreciationDetailMeta.MANAGER_ID));
				this.setLabel( (String)map.get(AssetDepreciationDetailMeta.LABEL));
				this.setCDepreciationAmount( (BigDecimal)map.get(AssetDepreciationDetailMeta.C_DEPRECIATION_AMOUNT));
				this.setResultStatus( (String)map.get(AssetDepreciationDetailMeta.RESULT_STATUS));
				this.setAssetFinanceCategoryId( (String)map.get(AssetDepreciationDetailMeta.ASSET_FINANCE_CATEGORY_ID));
				this.setCreateBy( (String)map.get(AssetDepreciationDetailMeta.CREATE_BY));
				this.setAssetPurchaseDate( (Date)map.get(AssetDepreciationDetailMeta.ASSET_PURCHASE_DATE));
				this.setDeleted( (Integer)map.get(AssetDepreciationDetailMeta.DELETED));
				this.setAssetOriginalUnitPrice( (BigDecimal)map.get(AssetDepreciationDetailMeta.ASSET_ORIGINAL_UNIT_PRICE));
				this.setResultDetail( (String)map.get(AssetDepreciationDetailMeta.RESULT_DETAIL));
				this.setCreateTime( (Date)map.get(AssetDepreciationDetailMeta.CREATE_TIME));
				this.setAssetName( (String)map.get(AssetDepreciationDetailMeta.ASSET_NAME));
				this.setDetailIdTarget( (String)map.get(AssetDepreciationDetailMeta.DETAIL_ID_TARGET));
				this.setUseUserId( (String)map.get(AssetDepreciationDetailMeta.USE_USER_ID));
				// others
				this.setManager( (Employee)map.get(AssetDepreciationDetailMeta.MANAGER));
				this.setAssetSource( (Asset)map.get(AssetDepreciationDetailMeta.ASSET_SOURCE));
				this.setLastAssetDepreciationDetail( (AssetDepreciationDetail)map.get(AssetDepreciationDetailMeta.LAST_ASSET_DEPRECIATION_DETAIL));
				this.setAssetDepreciation( (AssetDepreciation)map.get(AssetDepreciationDetailMeta.ASSET_DEPRECIATION));
				this.setFinancialOptionDict( (DictItem)map.get(AssetDepreciationDetailMeta.FINANCIAL_OPTION_DICT));
				this.setUseOrganization( (Organization)map.get(AssetDepreciationDetailMeta.USE_ORGANIZATION));
				this.setAssetTarget( (Asset)map.get(AssetDepreciationDetailMeta.ASSET_TARGET));
				this.setExpenseItemDict( (DictItem)map.get(AssetDepreciationDetailMeta.EXPENSE_ITEM_DICT));
				this.setAssetDepreciationOper( (AssetDepreciationOper)map.get(AssetDepreciationDetailMeta.ASSET_DEPRECIATION_OPER));
				this.setAsset( (Asset)map.get(AssetDepreciationDetailMeta.ASSET));
				this.setUseUser( (Employee)map.get(AssetDepreciationDetailMeta.USE_USER));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}

	/**
	 * 从 Map 读取
	 * @param r 记录数据
	 * @param cast 是否用 DataParser 进行类型转换
	 * @return  是否读取成功
	*/
	public boolean read(ExprRcd r,boolean cast) {
		if(r==null) return false;
		if(cast) {
			this.setAssetResidualsRate(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.ASSET_RESIDUALS_RATE)));
			this.setCUsedServiceLife(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.C_USED_SERVICE_LIFE)));
			this.setUseOrgName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.USE_ORG_NAME)));
			this.setERecoverableAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.E_RECOVERABLE_AMOUNT)));
			this.setDepreciationMethod(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.DEPRECIATION_METHOD)));
			this.setExpenseItemKey(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.EXPENSE_ITEM_KEY)));
			this.setCustomerInfo(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.CUSTOMER_INFO)));
			this.setFirstDepreciation(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.FIRST_DEPRECIATION)));
			this.setManagerName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.MANAGER_NAME)));
			this.setAssetNavPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.ASSET_NAV_PRICE)));
			this.setExpenseItemName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.EXPENSE_ITEM_NAME)));
			this.setENavAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.E_NAV_AMOUNT)));
			this.setDetailIdSource(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.DETAIL_ID_SOURCE)));
			this.setSDepreciationAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.S_DEPRECIATION_AMOUNT)));
			this.setActionCode(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ACTION_CODE)));
			this.setId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ID)));
			this.setCYearDepreciationAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.C_YEAR_DEPRECIATION_AMOUNT)));
			this.setAssetFinanceServiceLife(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.ASSET_FINANCE_SERVICE_LIFE)));
			this.setAssetCode(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ASSET_CODE)));
			this.setFinancialOptionKey(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.FINANCIAL_OPTION_KEY)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(AssetDepreciationDetailMeta.VERSION)));
			this.setFirstDepreciationMethod(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.FIRST_DEPRECIATION_METHOD)));
			this.setAssetFinanceCategoryName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ASSET_FINANCE_CATEGORY_NAME)));
			this.setResultValueStr(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.RESULT_VALUE_STR)));
			this.setLastOperId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.LAST_OPER_ID)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailMeta.DELETE_TIME)));
			this.setEDepreciationAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.E_DEPRECIATION_AMOUNT)));
			this.setFinancialOptionName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.FINANCIAL_OPTION_NAME)));
			this.setAssetCategoryId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ASSET_CATEGORY_ID)));
			this.setSOriginalPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.S_ORIGINAL_PRICE)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.DELETE_BY)));
			this.setResultValueFloat(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.RESULT_VALUE_FLOAT)));
			this.setAssetCategoryName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ASSET_CATEGORY_NAME)));
			this.setSNavAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.S_NAV_AMOUNT)));
			this.setAssetStatusName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ASSET_STATUS_NAME)));
			this.setAssetTaxAmountRate(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.ASSET_TAX_AMOUNT_RATE)));
			this.setOperId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.OPER_ID)));
			this.setAssetRegisterDate(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailMeta.ASSET_REGISTER_DATE)));
			this.setSRecoverableAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.S_RECOVERABLE_AMOUNT)));
			this.setAssetPurchaseUnitPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.ASSET_PURCHASE_UNIT_PRICE)));
			this.setEOriginalPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.E_ORIGINAL_PRICE)));
			this.setResult(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.RESULT)));
			this.setBusinessDate(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailMeta.BUSINESS_DATE)));
			this.setDepreciationId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.DEPRECIATION_ID)));
			this.setAssetResidualsPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.ASSET_RESIDUALS_PRICE)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.UPDATE_BY)));
			this.setAssetId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ASSET_ID)));
			this.setAssetServiceLife(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.ASSET_SERVICE_LIFE)));
			this.setCOriginalPriceIncrease(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.C_ORIGINAL_PRICE_INCREASE)));
			this.setAccountingServiceLife(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.ACCOUNTING_SERVICE_LIFE)));
			this.setUseUserName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.USE_USER_NAME)));
			this.setUseOrgId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.USE_ORG_ID)));
			this.setAssetModel(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ASSET_MODEL)));
			this.setLastOperTime(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailMeta.LAST_OPER_TIME)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailMeta.UPDATE_TIME)));
			this.setManagerId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.MANAGER_ID)));
			this.setLabel(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.LABEL)));
			this.setCDepreciationAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.C_DEPRECIATION_AMOUNT)));
			this.setResultStatus(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.RESULT_STATUS)));
			this.setAssetFinanceCategoryId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ASSET_FINANCE_CATEGORY_ID)));
			this.setCreateBy(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.CREATE_BY)));
			this.setAssetPurchaseDate(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailMeta.ASSET_PURCHASE_DATE)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(AssetDepreciationDetailMeta.DELETED)));
			this.setAssetOriginalUnitPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.ASSET_ORIGINAL_UNIT_PRICE)));
			this.setResultDetail(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.RESULT_DETAIL)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailMeta.CREATE_TIME)));
			this.setAssetName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ASSET_NAME)));
			this.setDetailIdTarget(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.DETAIL_ID_TARGET)));
			this.setUseUserId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.USE_USER_ID)));
			return true;
		} else {
			try {
				this.setAssetResidualsRate( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.ASSET_RESIDUALS_RATE));
				this.setCUsedServiceLife( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.C_USED_SERVICE_LIFE));
				this.setUseOrgName( (String)r.getValue(AssetDepreciationDetailMeta.USE_ORG_NAME));
				this.setERecoverableAmount( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.E_RECOVERABLE_AMOUNT));
				this.setDepreciationMethod( (String)r.getValue(AssetDepreciationDetailMeta.DEPRECIATION_METHOD));
				this.setExpenseItemKey( (String)r.getValue(AssetDepreciationDetailMeta.EXPENSE_ITEM_KEY));
				this.setCustomerInfo( (String)r.getValue(AssetDepreciationDetailMeta.CUSTOMER_INFO));
				this.setFirstDepreciation( (String)r.getValue(AssetDepreciationDetailMeta.FIRST_DEPRECIATION));
				this.setManagerName( (String)r.getValue(AssetDepreciationDetailMeta.MANAGER_NAME));
				this.setAssetNavPrice( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.ASSET_NAV_PRICE));
				this.setExpenseItemName( (String)r.getValue(AssetDepreciationDetailMeta.EXPENSE_ITEM_NAME));
				this.setENavAmount( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.E_NAV_AMOUNT));
				this.setDetailIdSource( (String)r.getValue(AssetDepreciationDetailMeta.DETAIL_ID_SOURCE));
				this.setSDepreciationAmount( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.S_DEPRECIATION_AMOUNT));
				this.setActionCode( (String)r.getValue(AssetDepreciationDetailMeta.ACTION_CODE));
				this.setId( (String)r.getValue(AssetDepreciationDetailMeta.ID));
				this.setCYearDepreciationAmount( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.C_YEAR_DEPRECIATION_AMOUNT));
				this.setAssetFinanceServiceLife( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.ASSET_FINANCE_SERVICE_LIFE));
				this.setAssetCode( (String)r.getValue(AssetDepreciationDetailMeta.ASSET_CODE));
				this.setFinancialOptionKey( (String)r.getValue(AssetDepreciationDetailMeta.FINANCIAL_OPTION_KEY));
				this.setVersion( (Integer)r.getValue(AssetDepreciationDetailMeta.VERSION));
				this.setFirstDepreciationMethod( (String)r.getValue(AssetDepreciationDetailMeta.FIRST_DEPRECIATION_METHOD));
				this.setAssetFinanceCategoryName( (String)r.getValue(AssetDepreciationDetailMeta.ASSET_FINANCE_CATEGORY_NAME));
				this.setResultValueStr( (String)r.getValue(AssetDepreciationDetailMeta.RESULT_VALUE_STR));
				this.setLastOperId( (String)r.getValue(AssetDepreciationDetailMeta.LAST_OPER_ID));
				this.setDeleteTime( (Date)r.getValue(AssetDepreciationDetailMeta.DELETE_TIME));
				this.setEDepreciationAmount( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.E_DEPRECIATION_AMOUNT));
				this.setFinancialOptionName( (String)r.getValue(AssetDepreciationDetailMeta.FINANCIAL_OPTION_NAME));
				this.setAssetCategoryId( (String)r.getValue(AssetDepreciationDetailMeta.ASSET_CATEGORY_ID));
				this.setSOriginalPrice( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.S_ORIGINAL_PRICE));
				this.setDeleteBy( (String)r.getValue(AssetDepreciationDetailMeta.DELETE_BY));
				this.setResultValueFloat( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.RESULT_VALUE_FLOAT));
				this.setAssetCategoryName( (String)r.getValue(AssetDepreciationDetailMeta.ASSET_CATEGORY_NAME));
				this.setSNavAmount( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.S_NAV_AMOUNT));
				this.setAssetStatusName( (String)r.getValue(AssetDepreciationDetailMeta.ASSET_STATUS_NAME));
				this.setAssetTaxAmountRate( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.ASSET_TAX_AMOUNT_RATE));
				this.setOperId( (String)r.getValue(AssetDepreciationDetailMeta.OPER_ID));
				this.setAssetRegisterDate( (Date)r.getValue(AssetDepreciationDetailMeta.ASSET_REGISTER_DATE));
				this.setSRecoverableAmount( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.S_RECOVERABLE_AMOUNT));
				this.setAssetPurchaseUnitPrice( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.ASSET_PURCHASE_UNIT_PRICE));
				this.setEOriginalPrice( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.E_ORIGINAL_PRICE));
				this.setResult( (String)r.getValue(AssetDepreciationDetailMeta.RESULT));
				this.setBusinessDate( (Date)r.getValue(AssetDepreciationDetailMeta.BUSINESS_DATE));
				this.setDepreciationId( (String)r.getValue(AssetDepreciationDetailMeta.DEPRECIATION_ID));
				this.setAssetResidualsPrice( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.ASSET_RESIDUALS_PRICE));
				this.setUpdateBy( (String)r.getValue(AssetDepreciationDetailMeta.UPDATE_BY));
				this.setAssetId( (String)r.getValue(AssetDepreciationDetailMeta.ASSET_ID));
				this.setAssetServiceLife( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.ASSET_SERVICE_LIFE));
				this.setCOriginalPriceIncrease( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.C_ORIGINAL_PRICE_INCREASE));
				this.setAccountingServiceLife( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.ACCOUNTING_SERVICE_LIFE));
				this.setUseUserName( (String)r.getValue(AssetDepreciationDetailMeta.USE_USER_NAME));
				this.setUseOrgId( (String)r.getValue(AssetDepreciationDetailMeta.USE_ORG_ID));
				this.setAssetModel( (String)r.getValue(AssetDepreciationDetailMeta.ASSET_MODEL));
				this.setLastOperTime( (Date)r.getValue(AssetDepreciationDetailMeta.LAST_OPER_TIME));
				this.setUpdateTime( (Date)r.getValue(AssetDepreciationDetailMeta.UPDATE_TIME));
				this.setManagerId( (String)r.getValue(AssetDepreciationDetailMeta.MANAGER_ID));
				this.setLabel( (String)r.getValue(AssetDepreciationDetailMeta.LABEL));
				this.setCDepreciationAmount( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.C_DEPRECIATION_AMOUNT));
				this.setResultStatus( (String)r.getValue(AssetDepreciationDetailMeta.RESULT_STATUS));
				this.setAssetFinanceCategoryId( (String)r.getValue(AssetDepreciationDetailMeta.ASSET_FINANCE_CATEGORY_ID));
				this.setCreateBy( (String)r.getValue(AssetDepreciationDetailMeta.CREATE_BY));
				this.setAssetPurchaseDate( (Date)r.getValue(AssetDepreciationDetailMeta.ASSET_PURCHASE_DATE));
				this.setDeleted( (Integer)r.getValue(AssetDepreciationDetailMeta.DELETED));
				this.setAssetOriginalUnitPrice( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.ASSET_ORIGINAL_UNIT_PRICE));
				this.setResultDetail( (String)r.getValue(AssetDepreciationDetailMeta.RESULT_DETAIL));
				this.setCreateTime( (Date)r.getValue(AssetDepreciationDetailMeta.CREATE_TIME));
				this.setAssetName( (String)r.getValue(AssetDepreciationDetailMeta.ASSET_NAME));
				this.setDetailIdTarget( (String)r.getValue(AssetDepreciationDetailMeta.DETAIL_ID_TARGET));
				this.setUseUserId( (String)r.getValue(AssetDepreciationDetailMeta.USE_USER_ID));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}