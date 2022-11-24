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
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.hrm.Organization;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.foxnic.commons.lang.DataParser;
import java.util.Map;
import com.github.foxnic.dao.entity.EntityContext;
import com.dt.platform.domain.eam.meta.AssetDepreciationDetailMeta;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 折旧明细
 * <p>折旧明细 , 数据表 eam_asset_depreciation_detail 的PO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-11-24 19:55:16
 * @sign 294EA57BB4C5D0E901558BBA6B240B6C
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
	@ApiModelProperty(required = true,value="主键" , notes = "主键" , example = "648246665265807360")
	private String id;
	
	/**
	 * 折旧方案：折旧方案
	*/
	@ApiModelProperty(required = false,value="折旧方案" , notes = "折旧方案" , example = "647736203386290176")
	private String depreciationId;
	
	/**
	 * 折旧操作：折旧操作
	*/
	@ApiModelProperty(required = false,value="折旧操作" , notes = "折旧操作" , example = "648121898315546624")
	private String operId;
	
	/**
	 * 折旧方式：折旧方式
	*/
	@ApiModelProperty(required = false,value="折旧方式" , notes = "折旧方式" , example = "average_age")
	private String depreciationMethod;
	
	/**
	 * 折旧结果：折旧结果
	*/
	@ApiModelProperty(required = false,value="折旧结果" , notes = "折旧结果" , example = "wait_calculate")
	private String result;
	
	/**
	 * 折旧结果明细：折旧结果明细
	*/
	@ApiModelProperty(required = false,value="折旧结果明细" , notes = "折旧结果明细")
	private String resultDetail;
	
	/**
	 * 资产：资产
	*/
	@ApiModelProperty(required = false,value="资产" , notes = "资产" , example = "572681223449608192")
	private String assetId;
	
	/**
	 * 资产类别：资产类别
	*/
	@ApiModelProperty(required = false,value="资产类别" , notes = "资产类别")
	private String assetCategoryName;
	
	/**
	 * 资产编码：资产编码
	*/
	@ApiModelProperty(required = false,value="资产编码" , notes = "资产编码" , example = "FZQH-09077")
	private String assetCode;
	
	/**
	 * 资产名称：资产名称
	*/
	@ApiModelProperty(required = false,value="资产名称" , notes = "资产名称" , example = "深信服AD")
	private String assetName;
	
	/**
	 * 资产型号：资产型号
	*/
	@ApiModelProperty(required = false,value="资产型号" , notes = "资产型号" , example = "model3")
	private String assetModel;
	
	/**
	 * 资产状态：资产状态
	*/
	@ApiModelProperty(required = false,value="资产状态" , notes = "资产状态")
	private String assetStatusName;
	
	/**
	 * 采购日期：采购日期
	*/
	@ApiModelProperty(required = false,value="采购日期" , notes = "采购日期")
	private Date purchaseDate;
	
	/**
	 * 启用日期：启用日期
	*/
	@ApiModelProperty(required = false,value="启用日期" , notes = "启用日期")
	private Date registerDate;
	
	/**
	 * 业务日期：业务日期
	*/
	@ApiModelProperty(required = false,value="业务日期" , notes = "业务日期")
	private Date businessDate;
	
	/**
	 * 资产原值：资产原值
	*/
	@ApiModelProperty(required = false,value="资产原值" , notes = "资产原值")
	private BigDecimal originalUnitPrice;
	
	/**
	 * 使用寿命：使用寿命
	*/
	@ApiModelProperty(required = false,value="使用寿命" , notes = "使用寿命")
	private BigDecimal serviceLife;
	
	/**
	 * 已用寿命：已用寿命
	*/
	@ApiModelProperty(required = false,value="已用寿命" , notes = "已用寿命")
	private BigDecimal usedServiceLife;
	
	/**
	 * 本期残值率：本期残值率
	*/
	@ApiModelProperty(required = false,value="本期残值率" , notes = "本期残值率" , example = "5.00")
	private BigDecimal residualRate;
	
	/**
	 * 本期残值：本期残值
	*/
	@ApiModelProperty(required = false,value="本期残值" , notes = "本期残值")
	private BigDecimal redidualPrice;
	
	/**
	 * (期初)期初原值：(期初)期初原值
	*/
	@ApiModelProperty(required = false,value="(期初)期初原值" , notes = "(期初)期初原值")
	private BigDecimal sOriginalPrice;
	
	/**
	 * (期初)期初累计折旧：(期初)期初累计折旧
	*/
	@ApiModelProperty(required = false,value="(期初)期初累计折旧" , notes = "(期初)期初累计折旧")
	private BigDecimal sDepreciationAmount;
	
	/**
	 * (期初)期初净值：(期初)期初净值
	*/
	@ApiModelProperty(required = false,value="(期初)期初净值" , notes = "(期初)期初净值")
	private BigDecimal sNavAmount;
	
	/**
	 * (期初)期初可回收净额：(期初)期初可回收净额
	*/
	@ApiModelProperty(required = false,value="(期初)期初可回收净额" , notes = "(期初)期初可回收净额")
	private BigDecimal sRecoverableAmount;
	
	/**
	 * (本期发生)原值增加：(本期发生)原值增加
	*/
	@ApiModelProperty(required = false,value="(本期发生)原值增加" , notes = "(本期发生)原值增加")
	private BigDecimal cOriginalPriceIncrease;
	
	/**
	 * (本期发生)本期折旧额：(本期发生)本期折旧额
	*/
	@ApiModelProperty(required = false,value="(本期发生)本期折旧额" , notes = "(本期发生)本期折旧额")
	private BigDecimal cDepreciationAmount;
	
	/**
	 * (本期发生)本年累计折旧额：(本期发生)本年累计折旧额
	*/
	@ApiModelProperty(required = false,value="(本期发生)本年累计折旧额" , notes = "(本期发生)本年累计折旧额")
	private BigDecimal cYearDepreciationAmount;
	
	/**
	 * (期末)期末原值：(期末)期末原值
	*/
	@ApiModelProperty(required = false,value="(期末)期末原值" , notes = "(期末)期末原值")
	private BigDecimal eOriginalPrice;
	
	/**
	 * (期末)期末累计折旧：(期末)期末累计折旧
	*/
	@ApiModelProperty(required = false,value="(期末)期末累计折旧" , notes = "(期末)期末累计折旧")
	private BigDecimal eDepreciationAmount;
	
	/**
	 * (期末)期末净值：(期末)期末净值
	*/
	@ApiModelProperty(required = false,value="(期末)期末净值" , notes = "(期末)期末净值")
	private BigDecimal eNavAmount;
	
	/**
	 * (期末)期末可回收金额：(期末)期末可回收金额
	*/
	@ApiModelProperty(required = false,value="(期末)期末可回收金额" , notes = "(期末)期末可回收金额")
	private BigDecimal eRecoverableAmount;
	
	/**
	 * 使用人：使用人
	*/
	@ApiModelProperty(required = false,value="使用人" , notes = "使用人")
	private String useUserId;
	
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
	 * 折旧前：折旧前
	*/
	@ApiModelProperty(required = false,value="折旧前" , notes = "折旧前" , example = "648246655920898048")
	private String detailIdSource;
	
	/**
	 * 折旧后：折旧后
	*/
	@ApiModelProperty(required = false,value="折旧后" , notes = "折旧后")
	private String detailIdTarget;
	
	/**
	 * 采购价格：采购价格
	*/
	@ApiModelProperty(required = false,value="采购价格" , notes = "采购价格")
	private BigDecimal purchaseUnitPrice;
	
	/**
	 * 本期折旧：本期折旧
	*/
	@ApiModelProperty(required = false,value="本期折旧" , notes = "本期折旧")
	private BigDecimal depreciationPrice;
	
	/**
	 * 当前净值：当前净值
	*/
	@ApiModelProperty(required = false,value="当前净值" , notes = "当前净值" , example = "200.00")
	private BigDecimal curPrice;
	
	/**
	 * 折旧前净值：折旧前净值
	*/
	@ApiModelProperty(required = false,value="折旧前净值" , notes = "折旧前净值" , example = "200.00")
	private BigDecimal beforePrice;
	
	/**
	 * 折旧后净值：折旧后净值
	*/
	@ApiModelProperty(required = false,value="折旧后净值" , notes = "折旧后净值")
	private BigDecimal afterPrice;
	
	/**
	 * 创建人ID：创建人ID
	*/
	@ApiModelProperty(required = true,value="创建人ID" , notes = "创建人ID" , example = "110588348101165911")
	private String createBy;
	
	/**
	 * 创建时间：创建时间
	*/
	@ApiModelProperty(required = false,value="创建时间" , notes = "创建时间" , example = "2022-11-24 07:40:49")
	private Date createTime;
	
	/**
	 * 修改人ID：修改人ID
	*/
	@ApiModelProperty(required = false,value="修改人ID" , notes = "修改人ID")
	private String updateBy;
	
	/**
	 * 修改时间：修改时间
	*/
	@ApiModelProperty(required = false,value="修改时间" , notes = "修改时间")
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
	 * 租户：租户
	*/
	@ApiModelProperty(required = false,value="租户" , notes = "租户" , example = "T001")
	private String tenantId;
	
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
	 * 方案：方案
	*/
	@ApiModelProperty(required = false,value="方案" , notes = "方案")
	private AssetDepreciation assetDepreciation;
	
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
	 * 获得 折旧结果<br>
	 * 折旧结果
	 * @return 折旧结果
	*/
	public String getResult() {
		return result;
	}
	
	/**
	 * 设置 折旧结果
	 * @param result 折旧结果
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setResult(String result) {
		this.result=result;
		return this;
	}
	
	/**
	 * 获得 折旧结果明细<br>
	 * 折旧结果明细
	 * @return 折旧结果明细
	*/
	public String getResultDetail() {
		return resultDetail;
	}
	
	/**
	 * 设置 折旧结果明细
	 * @param resultDetail 折旧结果明细
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
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	
	/**
	 * 设置 采购日期
	 * @param purchaseDate 采购日期
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setPurchaseDate(Date purchaseDate) {
		this.purchaseDate=purchaseDate;
		return this;
	}
	
	/**
	 * 获得 启用日期<br>
	 * 启用日期
	 * @return 启用日期
	*/
	public Date getRegisterDate() {
		return registerDate;
	}
	
	/**
	 * 设置 启用日期
	 * @param registerDate 启用日期
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setRegisterDate(Date registerDate) {
		this.registerDate=registerDate;
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
	 * 获得 资产原值<br>
	 * 资产原值
	 * @return 资产原值
	*/
	public BigDecimal getOriginalUnitPrice() {
		return originalUnitPrice;
	}
	
	/**
	 * 设置 资产原值
	 * @param originalUnitPrice 资产原值
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setOriginalUnitPrice(BigDecimal originalUnitPrice) {
		this.originalUnitPrice=originalUnitPrice;
		return this;
	}
	
	/**
	 * 获得 使用寿命<br>
	 * 使用寿命
	 * @return 使用寿命
	*/
	public BigDecimal getServiceLife() {
		return serviceLife;
	}
	
	/**
	 * 设置 使用寿命
	 * @param serviceLife 使用寿命
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setServiceLife(BigDecimal serviceLife) {
		this.serviceLife=serviceLife;
		return this;
	}
	
	/**
	 * 获得 已用寿命<br>
	 * 已用寿命
	 * @return 已用寿命
	*/
	public BigDecimal getUsedServiceLife() {
		return usedServiceLife;
	}
	
	/**
	 * 设置 已用寿命
	 * @param usedServiceLife 已用寿命
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setUsedServiceLife(BigDecimal usedServiceLife) {
		this.usedServiceLife=usedServiceLife;
		return this;
	}
	
	/**
	 * 获得 本期残值率<br>
	 * 本期残值率
	 * @return 本期残值率
	*/
	public BigDecimal getResidualRate() {
		return residualRate;
	}
	
	/**
	 * 设置 本期残值率
	 * @param residualRate 本期残值率
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setResidualRate(BigDecimal residualRate) {
		this.residualRate=residualRate;
		return this;
	}
	
	/**
	 * 获得 本期残值<br>
	 * 本期残值
	 * @return 本期残值
	*/
	public BigDecimal getRedidualPrice() {
		return redidualPrice;
	}
	
	/**
	 * 设置 本期残值
	 * @param redidualPrice 本期残值
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setRedidualPrice(BigDecimal redidualPrice) {
		this.redidualPrice=redidualPrice;
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
	 * 获得 使用人<br>
	 * 使用人
	 * @return 使用人
	*/
	public String getUseUserId() {
		return useUserId;
	}
	
	/**
	 * 设置 使用人
	 * @param useUserId 使用人
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setUseUserId(String useUserId) {
		this.useUserId=useUserId;
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
	 * 获得 折旧前<br>
	 * 折旧前
	 * @return 折旧前
	*/
	public String getDetailIdSource() {
		return detailIdSource;
	}
	
	/**
	 * 设置 折旧前
	 * @param detailIdSource 折旧前
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setDetailIdSource(String detailIdSource) {
		this.detailIdSource=detailIdSource;
		return this;
	}
	
	/**
	 * 获得 折旧后<br>
	 * 折旧后
	 * @return 折旧后
	*/
	public String getDetailIdTarget() {
		return detailIdTarget;
	}
	
	/**
	 * 设置 折旧后
	 * @param detailIdTarget 折旧后
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setDetailIdTarget(String detailIdTarget) {
		this.detailIdTarget=detailIdTarget;
		return this;
	}
	
	/**
	 * 获得 采购价格<br>
	 * 采购价格
	 * @return 采购价格
	*/
	public BigDecimal getPurchaseUnitPrice() {
		return purchaseUnitPrice;
	}
	
	/**
	 * 设置 采购价格
	 * @param purchaseUnitPrice 采购价格
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setPurchaseUnitPrice(BigDecimal purchaseUnitPrice) {
		this.purchaseUnitPrice=purchaseUnitPrice;
		return this;
	}
	
	/**
	 * 获得 本期折旧<br>
	 * 本期折旧
	 * @return 本期折旧
	*/
	public BigDecimal getDepreciationPrice() {
		return depreciationPrice;
	}
	
	/**
	 * 设置 本期折旧
	 * @param depreciationPrice 本期折旧
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setDepreciationPrice(BigDecimal depreciationPrice) {
		this.depreciationPrice=depreciationPrice;
		return this;
	}
	
	/**
	 * 获得 当前净值<br>
	 * 当前净值
	 * @return 当前净值
	*/
	public BigDecimal getCurPrice() {
		return curPrice;
	}
	
	/**
	 * 设置 当前净值
	 * @param curPrice 当前净值
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setCurPrice(BigDecimal curPrice) {
		this.curPrice=curPrice;
		return this;
	}
	
	/**
	 * 获得 折旧前净值<br>
	 * 折旧前净值
	 * @return 折旧前净值
	*/
	public BigDecimal getBeforePrice() {
		return beforePrice;
	}
	
	/**
	 * 设置 折旧前净值
	 * @param beforePrice 折旧前净值
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setBeforePrice(BigDecimal beforePrice) {
		this.beforePrice=beforePrice;
		return this;
	}
	
	/**
	 * 获得 折旧后净值<br>
	 * 折旧后净值
	 * @return 折旧后净值
	*/
	public BigDecimal getAfterPrice() {
		return afterPrice;
	}
	
	/**
	 * 设置 折旧后净值
	 * @param afterPrice 折旧后净值
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setAfterPrice(BigDecimal afterPrice) {
		this.afterPrice=afterPrice;
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
	 * 获得 租户<br>
	 * 租户
	 * @return 租户
	*/
	public String getTenantId() {
		return tenantId;
	}
	
	/**
	 * 设置 租户
	 * @param tenantId 租户
	 * @return 当前对象
	*/
	public AssetDepreciationDetail setTenantId(String tenantId) {
		this.tenantId=tenantId;
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
		inst.setUseOrgName(this.getUseOrgName());
		inst.setERecoverableAmount(this.getERecoverableAmount());
		inst.setDepreciationMethod(this.getDepreciationMethod());
		inst.setExpenseItemKey(this.getExpenseItemKey());
		inst.setCustomerInfo(this.getCustomerInfo());
		inst.setPurchaseUnitPrice(this.getPurchaseUnitPrice());
		inst.setDepreciationPrice(this.getDepreciationPrice());
		inst.setExpenseItemName(this.getExpenseItemName());
		inst.setENavAmount(this.getENavAmount());
		inst.setDetailIdSource(this.getDetailIdSource());
		inst.setSDepreciationAmount(this.getSDepreciationAmount());
		inst.setRedidualPrice(this.getRedidualPrice());
		inst.setId(this.getId());
		inst.setCurPrice(this.getCurPrice());
		inst.setCYearDepreciationAmount(this.getCYearDepreciationAmount());
		inst.setUsedServiceLife(this.getUsedServiceLife());
		inst.setAssetCode(this.getAssetCode());
		inst.setFinancialOptionKey(this.getFinancialOptionKey());
		inst.setVersion(this.getVersion());
		inst.setDeleteTime(this.getDeleteTime());
		inst.setEDepreciationAmount(this.getEDepreciationAmount());
		inst.setFinancialOptionName(this.getFinancialOptionName());
		inst.setSOriginalPrice(this.getSOriginalPrice());
		inst.setDeleteBy(this.getDeleteBy());
		inst.setAssetCategoryName(this.getAssetCategoryName());
		inst.setSNavAmount(this.getSNavAmount());
		inst.setAssetStatusName(this.getAssetStatusName());
		inst.setPurchaseDate(this.getPurchaseDate());
		inst.setOperId(this.getOperId());
		inst.setSRecoverableAmount(this.getSRecoverableAmount());
		inst.setEOriginalPrice(this.getEOriginalPrice());
		inst.setResult(this.getResult());
		inst.setOriginalUnitPrice(this.getOriginalUnitPrice());
		inst.setBusinessDate(this.getBusinessDate());
		inst.setDepreciationId(this.getDepreciationId());
		inst.setUpdateBy(this.getUpdateBy());
		inst.setAssetId(this.getAssetId());
		inst.setAfterPrice(this.getAfterPrice());
		inst.setServiceLife(this.getServiceLife());
		inst.setRegisterDate(this.getRegisterDate());
		inst.setCOriginalPriceIncrease(this.getCOriginalPriceIncrease());
		inst.setBeforePrice(this.getBeforePrice());
		inst.setUseOrgId(this.getUseOrgId());
		inst.setAssetModel(this.getAssetModel());
		inst.setUpdateTime(this.getUpdateTime());
		inst.setCDepreciationAmount(this.getCDepreciationAmount());
		inst.setCreateBy(this.getCreateBy());
		inst.setDeleted(this.getDeleted());
		inst.setResultDetail(this.getResultDetail());
		inst.setCreateTime(this.getCreateTime());
		inst.setResidualRate(this.getResidualRate());
		inst.setTenantId(this.getTenantId());
		inst.setAssetName(this.getAssetName());
		inst.setDetailIdTarget(this.getDetailIdTarget());
		inst.setUseUserId(this.getUseUserId());
		if(all) {
			inst.setUseOrganization(this.getUseOrganization());
			inst.setAssetTarget(this.getAssetTarget());
			inst.setExpenseItemDict(this.getExpenseItemDict());
			inst.setAssetDepreciationOper(this.getAssetDepreciationOper());
			inst.setAssetSource(this.getAssetSource());
			inst.setAsset(this.getAsset());
			inst.setUseUser(this.getUseUser());
			inst.setAssetDepreciation(this.getAssetDepreciation());
			inst.setFinancialOptionDict(this.getFinancialOptionDict());
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
			this.setUseOrgName(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.USE_ORG_NAME)));
			this.setERecoverableAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.E_RECOVERABLE_AMOUNT)));
			this.setDepreciationMethod(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.DEPRECIATION_METHOD)));
			this.setExpenseItemKey(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.EXPENSE_ITEM_KEY)));
			this.setCustomerInfo(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.CUSTOMER_INFO)));
			this.setPurchaseUnitPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.PURCHASE_UNIT_PRICE)));
			this.setDepreciationPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.DEPRECIATION_PRICE)));
			this.setExpenseItemName(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.EXPENSE_ITEM_NAME)));
			this.setENavAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.E_NAV_AMOUNT)));
			this.setDetailIdSource(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.DETAIL_ID_SOURCE)));
			this.setSDepreciationAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.S_DEPRECIATION_AMOUNT)));
			this.setRedidualPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.REDIDUAL_PRICE)));
			this.setId(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ID)));
			this.setCurPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.CUR_PRICE)));
			this.setCYearDepreciationAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.C_YEAR_DEPRECIATION_AMOUNT)));
			this.setUsedServiceLife(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.USED_SERVICE_LIFE)));
			this.setAssetCode(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ASSET_CODE)));
			this.setFinancialOptionKey(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.FINANCIAL_OPTION_KEY)));
			this.setVersion(DataParser.parse(Integer.class, map.get(AssetDepreciationDetailMeta.VERSION)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(AssetDepreciationDetailMeta.DELETE_TIME)));
			this.setEDepreciationAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.E_DEPRECIATION_AMOUNT)));
			this.setFinancialOptionName(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.FINANCIAL_OPTION_NAME)));
			this.setSOriginalPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.S_ORIGINAL_PRICE)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.DELETE_BY)));
			this.setAssetCategoryName(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ASSET_CATEGORY_NAME)));
			this.setSNavAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.S_NAV_AMOUNT)));
			this.setAssetStatusName(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ASSET_STATUS_NAME)));
			this.setPurchaseDate(DataParser.parse(Date.class, map.get(AssetDepreciationDetailMeta.PURCHASE_DATE)));
			this.setOperId(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.OPER_ID)));
			this.setSRecoverableAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.S_RECOVERABLE_AMOUNT)));
			this.setEOriginalPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.E_ORIGINAL_PRICE)));
			this.setResult(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.RESULT)));
			this.setOriginalUnitPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.ORIGINAL_UNIT_PRICE)));
			this.setBusinessDate(DataParser.parse(Date.class, map.get(AssetDepreciationDetailMeta.BUSINESS_DATE)));
			this.setDepreciationId(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.DEPRECIATION_ID)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.UPDATE_BY)));
			this.setAssetId(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ASSET_ID)));
			this.setAfterPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.AFTER_PRICE)));
			this.setServiceLife(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.SERVICE_LIFE)));
			this.setRegisterDate(DataParser.parse(Date.class, map.get(AssetDepreciationDetailMeta.REGISTER_DATE)));
			this.setCOriginalPriceIncrease(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.C_ORIGINAL_PRICE_INCREASE)));
			this.setBeforePrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.BEFORE_PRICE)));
			this.setUseOrgId(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.USE_ORG_ID)));
			this.setAssetModel(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ASSET_MODEL)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(AssetDepreciationDetailMeta.UPDATE_TIME)));
			this.setCDepreciationAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.C_DEPRECIATION_AMOUNT)));
			this.setCreateBy(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(AssetDepreciationDetailMeta.DELETED)));
			this.setResultDetail(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.RESULT_DETAIL)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(AssetDepreciationDetailMeta.CREATE_TIME)));
			this.setResidualRate(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailMeta.RESIDUAL_RATE)));
			this.setTenantId(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.TENANT_ID)));
			this.setAssetName(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.ASSET_NAME)));
			this.setDetailIdTarget(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.DETAIL_ID_TARGET)));
			this.setUseUserId(DataParser.parse(String.class, map.get(AssetDepreciationDetailMeta.USE_USER_ID)));
			// others
			this.setUseOrganization(DataParser.parse(Organization.class, map.get(AssetDepreciationDetailMeta.USE_ORGANIZATION)));
			this.setAssetTarget(DataParser.parse(Asset.class, map.get(AssetDepreciationDetailMeta.ASSET_TARGET)));
			this.setExpenseItemDict(DataParser.parse(DictItem.class, map.get(AssetDepreciationDetailMeta.EXPENSE_ITEM_DICT)));
			this.setAssetDepreciationOper(DataParser.parse(AssetDepreciationOper.class, map.get(AssetDepreciationDetailMeta.ASSET_DEPRECIATION_OPER)));
			this.setAssetSource(DataParser.parse(Asset.class, map.get(AssetDepreciationDetailMeta.ASSET_SOURCE)));
			this.setAsset(DataParser.parse(Asset.class, map.get(AssetDepreciationDetailMeta.ASSET)));
			this.setUseUser(DataParser.parse(Employee.class, map.get(AssetDepreciationDetailMeta.USE_USER)));
			this.setAssetDepreciation(DataParser.parse(AssetDepreciation.class, map.get(AssetDepreciationDetailMeta.ASSET_DEPRECIATION)));
			this.setFinancialOptionDict(DataParser.parse(DictItem.class, map.get(AssetDepreciationDetailMeta.FINANCIAL_OPTION_DICT)));
			return true;
		} else {
			try {
				this.setUseOrgName( (String)map.get(AssetDepreciationDetailMeta.USE_ORG_NAME));
				this.setERecoverableAmount( (BigDecimal)map.get(AssetDepreciationDetailMeta.E_RECOVERABLE_AMOUNT));
				this.setDepreciationMethod( (String)map.get(AssetDepreciationDetailMeta.DEPRECIATION_METHOD));
				this.setExpenseItemKey( (String)map.get(AssetDepreciationDetailMeta.EXPENSE_ITEM_KEY));
				this.setCustomerInfo( (String)map.get(AssetDepreciationDetailMeta.CUSTOMER_INFO));
				this.setPurchaseUnitPrice( (BigDecimal)map.get(AssetDepreciationDetailMeta.PURCHASE_UNIT_PRICE));
				this.setDepreciationPrice( (BigDecimal)map.get(AssetDepreciationDetailMeta.DEPRECIATION_PRICE));
				this.setExpenseItemName( (String)map.get(AssetDepreciationDetailMeta.EXPENSE_ITEM_NAME));
				this.setENavAmount( (BigDecimal)map.get(AssetDepreciationDetailMeta.E_NAV_AMOUNT));
				this.setDetailIdSource( (String)map.get(AssetDepreciationDetailMeta.DETAIL_ID_SOURCE));
				this.setSDepreciationAmount( (BigDecimal)map.get(AssetDepreciationDetailMeta.S_DEPRECIATION_AMOUNT));
				this.setRedidualPrice( (BigDecimal)map.get(AssetDepreciationDetailMeta.REDIDUAL_PRICE));
				this.setId( (String)map.get(AssetDepreciationDetailMeta.ID));
				this.setCurPrice( (BigDecimal)map.get(AssetDepreciationDetailMeta.CUR_PRICE));
				this.setCYearDepreciationAmount( (BigDecimal)map.get(AssetDepreciationDetailMeta.C_YEAR_DEPRECIATION_AMOUNT));
				this.setUsedServiceLife( (BigDecimal)map.get(AssetDepreciationDetailMeta.USED_SERVICE_LIFE));
				this.setAssetCode( (String)map.get(AssetDepreciationDetailMeta.ASSET_CODE));
				this.setFinancialOptionKey( (String)map.get(AssetDepreciationDetailMeta.FINANCIAL_OPTION_KEY));
				this.setVersion( (Integer)map.get(AssetDepreciationDetailMeta.VERSION));
				this.setDeleteTime( (Date)map.get(AssetDepreciationDetailMeta.DELETE_TIME));
				this.setEDepreciationAmount( (BigDecimal)map.get(AssetDepreciationDetailMeta.E_DEPRECIATION_AMOUNT));
				this.setFinancialOptionName( (String)map.get(AssetDepreciationDetailMeta.FINANCIAL_OPTION_NAME));
				this.setSOriginalPrice( (BigDecimal)map.get(AssetDepreciationDetailMeta.S_ORIGINAL_PRICE));
				this.setDeleteBy( (String)map.get(AssetDepreciationDetailMeta.DELETE_BY));
				this.setAssetCategoryName( (String)map.get(AssetDepreciationDetailMeta.ASSET_CATEGORY_NAME));
				this.setSNavAmount( (BigDecimal)map.get(AssetDepreciationDetailMeta.S_NAV_AMOUNT));
				this.setAssetStatusName( (String)map.get(AssetDepreciationDetailMeta.ASSET_STATUS_NAME));
				this.setPurchaseDate( (Date)map.get(AssetDepreciationDetailMeta.PURCHASE_DATE));
				this.setOperId( (String)map.get(AssetDepreciationDetailMeta.OPER_ID));
				this.setSRecoverableAmount( (BigDecimal)map.get(AssetDepreciationDetailMeta.S_RECOVERABLE_AMOUNT));
				this.setEOriginalPrice( (BigDecimal)map.get(AssetDepreciationDetailMeta.E_ORIGINAL_PRICE));
				this.setResult( (String)map.get(AssetDepreciationDetailMeta.RESULT));
				this.setOriginalUnitPrice( (BigDecimal)map.get(AssetDepreciationDetailMeta.ORIGINAL_UNIT_PRICE));
				this.setBusinessDate( (Date)map.get(AssetDepreciationDetailMeta.BUSINESS_DATE));
				this.setDepreciationId( (String)map.get(AssetDepreciationDetailMeta.DEPRECIATION_ID));
				this.setUpdateBy( (String)map.get(AssetDepreciationDetailMeta.UPDATE_BY));
				this.setAssetId( (String)map.get(AssetDepreciationDetailMeta.ASSET_ID));
				this.setAfterPrice( (BigDecimal)map.get(AssetDepreciationDetailMeta.AFTER_PRICE));
				this.setServiceLife( (BigDecimal)map.get(AssetDepreciationDetailMeta.SERVICE_LIFE));
				this.setRegisterDate( (Date)map.get(AssetDepreciationDetailMeta.REGISTER_DATE));
				this.setCOriginalPriceIncrease( (BigDecimal)map.get(AssetDepreciationDetailMeta.C_ORIGINAL_PRICE_INCREASE));
				this.setBeforePrice( (BigDecimal)map.get(AssetDepreciationDetailMeta.BEFORE_PRICE));
				this.setUseOrgId( (String)map.get(AssetDepreciationDetailMeta.USE_ORG_ID));
				this.setAssetModel( (String)map.get(AssetDepreciationDetailMeta.ASSET_MODEL));
				this.setUpdateTime( (Date)map.get(AssetDepreciationDetailMeta.UPDATE_TIME));
				this.setCDepreciationAmount( (BigDecimal)map.get(AssetDepreciationDetailMeta.C_DEPRECIATION_AMOUNT));
				this.setCreateBy( (String)map.get(AssetDepreciationDetailMeta.CREATE_BY));
				this.setDeleted( (Integer)map.get(AssetDepreciationDetailMeta.DELETED));
				this.setResultDetail( (String)map.get(AssetDepreciationDetailMeta.RESULT_DETAIL));
				this.setCreateTime( (Date)map.get(AssetDepreciationDetailMeta.CREATE_TIME));
				this.setResidualRate( (BigDecimal)map.get(AssetDepreciationDetailMeta.RESIDUAL_RATE));
				this.setTenantId( (String)map.get(AssetDepreciationDetailMeta.TENANT_ID));
				this.setAssetName( (String)map.get(AssetDepreciationDetailMeta.ASSET_NAME));
				this.setDetailIdTarget( (String)map.get(AssetDepreciationDetailMeta.DETAIL_ID_TARGET));
				this.setUseUserId( (String)map.get(AssetDepreciationDetailMeta.USE_USER_ID));
				// others
				this.setUseOrganization( (Organization)map.get(AssetDepreciationDetailMeta.USE_ORGANIZATION));
				this.setAssetTarget( (Asset)map.get(AssetDepreciationDetailMeta.ASSET_TARGET));
				this.setExpenseItemDict( (DictItem)map.get(AssetDepreciationDetailMeta.EXPENSE_ITEM_DICT));
				this.setAssetDepreciationOper( (AssetDepreciationOper)map.get(AssetDepreciationDetailMeta.ASSET_DEPRECIATION_OPER));
				this.setAssetSource( (Asset)map.get(AssetDepreciationDetailMeta.ASSET_SOURCE));
				this.setAsset( (Asset)map.get(AssetDepreciationDetailMeta.ASSET));
				this.setUseUser( (Employee)map.get(AssetDepreciationDetailMeta.USE_USER));
				this.setAssetDepreciation( (AssetDepreciation)map.get(AssetDepreciationDetailMeta.ASSET_DEPRECIATION));
				this.setFinancialOptionDict( (DictItem)map.get(AssetDepreciationDetailMeta.FINANCIAL_OPTION_DICT));
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
			this.setUseOrgName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.USE_ORG_NAME)));
			this.setERecoverableAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.E_RECOVERABLE_AMOUNT)));
			this.setDepreciationMethod(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.DEPRECIATION_METHOD)));
			this.setExpenseItemKey(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.EXPENSE_ITEM_KEY)));
			this.setCustomerInfo(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.CUSTOMER_INFO)));
			this.setPurchaseUnitPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.PURCHASE_UNIT_PRICE)));
			this.setDepreciationPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.DEPRECIATION_PRICE)));
			this.setExpenseItemName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.EXPENSE_ITEM_NAME)));
			this.setENavAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.E_NAV_AMOUNT)));
			this.setDetailIdSource(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.DETAIL_ID_SOURCE)));
			this.setSDepreciationAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.S_DEPRECIATION_AMOUNT)));
			this.setRedidualPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.REDIDUAL_PRICE)));
			this.setId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ID)));
			this.setCurPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.CUR_PRICE)));
			this.setCYearDepreciationAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.C_YEAR_DEPRECIATION_AMOUNT)));
			this.setUsedServiceLife(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.USED_SERVICE_LIFE)));
			this.setAssetCode(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ASSET_CODE)));
			this.setFinancialOptionKey(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.FINANCIAL_OPTION_KEY)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(AssetDepreciationDetailMeta.VERSION)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailMeta.DELETE_TIME)));
			this.setEDepreciationAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.E_DEPRECIATION_AMOUNT)));
			this.setFinancialOptionName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.FINANCIAL_OPTION_NAME)));
			this.setSOriginalPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.S_ORIGINAL_PRICE)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.DELETE_BY)));
			this.setAssetCategoryName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ASSET_CATEGORY_NAME)));
			this.setSNavAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.S_NAV_AMOUNT)));
			this.setAssetStatusName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ASSET_STATUS_NAME)));
			this.setPurchaseDate(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailMeta.PURCHASE_DATE)));
			this.setOperId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.OPER_ID)));
			this.setSRecoverableAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.S_RECOVERABLE_AMOUNT)));
			this.setEOriginalPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.E_ORIGINAL_PRICE)));
			this.setResult(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.RESULT)));
			this.setOriginalUnitPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.ORIGINAL_UNIT_PRICE)));
			this.setBusinessDate(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailMeta.BUSINESS_DATE)));
			this.setDepreciationId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.DEPRECIATION_ID)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.UPDATE_BY)));
			this.setAssetId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ASSET_ID)));
			this.setAfterPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.AFTER_PRICE)));
			this.setServiceLife(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.SERVICE_LIFE)));
			this.setRegisterDate(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailMeta.REGISTER_DATE)));
			this.setCOriginalPriceIncrease(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.C_ORIGINAL_PRICE_INCREASE)));
			this.setBeforePrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.BEFORE_PRICE)));
			this.setUseOrgId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.USE_ORG_ID)));
			this.setAssetModel(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ASSET_MODEL)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailMeta.UPDATE_TIME)));
			this.setCDepreciationAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.C_DEPRECIATION_AMOUNT)));
			this.setCreateBy(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(AssetDepreciationDetailMeta.DELETED)));
			this.setResultDetail(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.RESULT_DETAIL)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailMeta.CREATE_TIME)));
			this.setResidualRate(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailMeta.RESIDUAL_RATE)));
			this.setTenantId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.TENANT_ID)));
			this.setAssetName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.ASSET_NAME)));
			this.setDetailIdTarget(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.DETAIL_ID_TARGET)));
			this.setUseUserId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailMeta.USE_USER_ID)));
			return true;
		} else {
			try {
				this.setUseOrgName( (String)r.getValue(AssetDepreciationDetailMeta.USE_ORG_NAME));
				this.setERecoverableAmount( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.E_RECOVERABLE_AMOUNT));
				this.setDepreciationMethod( (String)r.getValue(AssetDepreciationDetailMeta.DEPRECIATION_METHOD));
				this.setExpenseItemKey( (String)r.getValue(AssetDepreciationDetailMeta.EXPENSE_ITEM_KEY));
				this.setCustomerInfo( (String)r.getValue(AssetDepreciationDetailMeta.CUSTOMER_INFO));
				this.setPurchaseUnitPrice( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.PURCHASE_UNIT_PRICE));
				this.setDepreciationPrice( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.DEPRECIATION_PRICE));
				this.setExpenseItemName( (String)r.getValue(AssetDepreciationDetailMeta.EXPENSE_ITEM_NAME));
				this.setENavAmount( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.E_NAV_AMOUNT));
				this.setDetailIdSource( (String)r.getValue(AssetDepreciationDetailMeta.DETAIL_ID_SOURCE));
				this.setSDepreciationAmount( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.S_DEPRECIATION_AMOUNT));
				this.setRedidualPrice( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.REDIDUAL_PRICE));
				this.setId( (String)r.getValue(AssetDepreciationDetailMeta.ID));
				this.setCurPrice( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.CUR_PRICE));
				this.setCYearDepreciationAmount( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.C_YEAR_DEPRECIATION_AMOUNT));
				this.setUsedServiceLife( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.USED_SERVICE_LIFE));
				this.setAssetCode( (String)r.getValue(AssetDepreciationDetailMeta.ASSET_CODE));
				this.setFinancialOptionKey( (String)r.getValue(AssetDepreciationDetailMeta.FINANCIAL_OPTION_KEY));
				this.setVersion( (Integer)r.getValue(AssetDepreciationDetailMeta.VERSION));
				this.setDeleteTime( (Date)r.getValue(AssetDepreciationDetailMeta.DELETE_TIME));
				this.setEDepreciationAmount( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.E_DEPRECIATION_AMOUNT));
				this.setFinancialOptionName( (String)r.getValue(AssetDepreciationDetailMeta.FINANCIAL_OPTION_NAME));
				this.setSOriginalPrice( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.S_ORIGINAL_PRICE));
				this.setDeleteBy( (String)r.getValue(AssetDepreciationDetailMeta.DELETE_BY));
				this.setAssetCategoryName( (String)r.getValue(AssetDepreciationDetailMeta.ASSET_CATEGORY_NAME));
				this.setSNavAmount( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.S_NAV_AMOUNT));
				this.setAssetStatusName( (String)r.getValue(AssetDepreciationDetailMeta.ASSET_STATUS_NAME));
				this.setPurchaseDate( (Date)r.getValue(AssetDepreciationDetailMeta.PURCHASE_DATE));
				this.setOperId( (String)r.getValue(AssetDepreciationDetailMeta.OPER_ID));
				this.setSRecoverableAmount( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.S_RECOVERABLE_AMOUNT));
				this.setEOriginalPrice( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.E_ORIGINAL_PRICE));
				this.setResult( (String)r.getValue(AssetDepreciationDetailMeta.RESULT));
				this.setOriginalUnitPrice( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.ORIGINAL_UNIT_PRICE));
				this.setBusinessDate( (Date)r.getValue(AssetDepreciationDetailMeta.BUSINESS_DATE));
				this.setDepreciationId( (String)r.getValue(AssetDepreciationDetailMeta.DEPRECIATION_ID));
				this.setUpdateBy( (String)r.getValue(AssetDepreciationDetailMeta.UPDATE_BY));
				this.setAssetId( (String)r.getValue(AssetDepreciationDetailMeta.ASSET_ID));
				this.setAfterPrice( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.AFTER_PRICE));
				this.setServiceLife( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.SERVICE_LIFE));
				this.setRegisterDate( (Date)r.getValue(AssetDepreciationDetailMeta.REGISTER_DATE));
				this.setCOriginalPriceIncrease( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.C_ORIGINAL_PRICE_INCREASE));
				this.setBeforePrice( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.BEFORE_PRICE));
				this.setUseOrgId( (String)r.getValue(AssetDepreciationDetailMeta.USE_ORG_ID));
				this.setAssetModel( (String)r.getValue(AssetDepreciationDetailMeta.ASSET_MODEL));
				this.setUpdateTime( (Date)r.getValue(AssetDepreciationDetailMeta.UPDATE_TIME));
				this.setCDepreciationAmount( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.C_DEPRECIATION_AMOUNT));
				this.setCreateBy( (String)r.getValue(AssetDepreciationDetailMeta.CREATE_BY));
				this.setDeleted( (Integer)r.getValue(AssetDepreciationDetailMeta.DELETED));
				this.setResultDetail( (String)r.getValue(AssetDepreciationDetailMeta.RESULT_DETAIL));
				this.setCreateTime( (Date)r.getValue(AssetDepreciationDetailMeta.CREATE_TIME));
				this.setResidualRate( (BigDecimal)r.getValue(AssetDepreciationDetailMeta.RESIDUAL_RATE));
				this.setTenantId( (String)r.getValue(AssetDepreciationDetailMeta.TENANT_ID));
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