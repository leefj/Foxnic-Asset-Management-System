package com.dt.platform.domain.eam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import com.github.foxnic.api.model.CompositeParameter;
import javax.persistence.Transient;
import com.github.foxnic.commons.bean.BeanUtil;
import com.github.foxnic.dao.entity.EntityContext;
import com.github.foxnic.dao.entity.Entity;
import java.util.Map;
import com.dt.platform.domain.eam.meta.AssetDepreciationDetailVOMeta;
import com.github.foxnic.commons.lang.DataParser;
import java.math.BigDecimal;
import java.util.Date;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.hrm.Organization;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 折旧明细VO类型
 * <p>折旧明细 , 数据表 eam_asset_depreciation_detail 的通用VO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-08 11:31:29
 * @sign C386213B2181FC0EFABC94BB0C40B318
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@ApiModel(description = "折旧明细VO类型 ; 折旧明细 , 数据表 eam_asset_depreciation_detail 的通用VO类型" , parent = AssetDepreciationDetail.class)
public class AssetDepreciationDetailVO extends AssetDepreciationDetail {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 页码
	*/
	@ApiModelProperty(required = false,value="页码" , notes = "")
	private Integer pageIndex;
	
	/**
	 * 分页大小
	*/
	@ApiModelProperty(required = false,value="分页大小" , notes = "")
	private Integer pageSize;
	
	/**
	 * 搜索字段
	*/
	@ApiModelProperty(required = false,value="搜索字段" , notes = "")
	private String searchField;
	
	/**
	 * 模糊搜索字段
	*/
	@ApiModelProperty(required = false,value="模糊搜索字段" , notes = "")
	private String fuzzyField;
	
	/**
	 * 搜索的值
	*/
	@ApiModelProperty(required = false,value="搜索的值" , notes = "")
	private String searchValue;
	
	/**
	 * 已修改字段
	*/
	@ApiModelProperty(required = false,value="已修改字段" , notes = "")
	private List<String> dirtyFields;
	
	/**
	 * 排序字段
	*/
	@ApiModelProperty(required = false,value="排序字段" , notes = "")
	private String sortField;
	
	/**
	 * 排序方式
	*/
	@ApiModelProperty(required = false,value="排序方式" , notes = "")
	private String sortType;
	
	/**
	 * 数据来源：前端指定不同的来源，后端按来源执行不同的逻辑
	*/
	@ApiModelProperty(required = false,value="数据来源" , notes = "前端指定不同的来源，后端按来源执行不同的逻辑")
	private String dataOrigin;
	
	/**
	 * 查询逻辑：默认and，可指定 or 
	*/
	@ApiModelProperty(required = false,value="查询逻辑" , notes = "默认and，可指定 or ")
	private String queryLogic;
	
	/**
	 * 主键清单：用于接收批量主键参数
	*/
	@ApiModelProperty(required = false,value="主键清单" , notes = "用于接收批量主键参数")
	private List<String> ids;
	
	/**
	 * 获得 页码<br>
	 * @return 页码
	*/
	public Integer getPageIndex() {
		return pageIndex;
	}
	
	/**
	 * 设置 页码
	 * @param pageIndex 页码
	 * @return 当前对象
	*/
	public AssetDepreciationDetailVO setPageIndex(Integer pageIndex) {
		this.pageIndex=pageIndex;
		return this;
	}
	
	/**
	 * 获得 分页大小<br>
	 * @return 分页大小
	*/
	public Integer getPageSize() {
		return pageSize;
	}
	
	/**
	 * 设置 分页大小
	 * @param pageSize 分页大小
	 * @return 当前对象
	*/
	public AssetDepreciationDetailVO setPageSize(Integer pageSize) {
		this.pageSize=pageSize;
		return this;
	}
	
	/**
	 * 获得 搜索字段<br>
	 * @return 搜索字段
	*/
	public String getSearchField() {
		return searchField;
	}
	
	/**
	 * 设置 搜索字段
	 * @param searchField 搜索字段
	 * @return 当前对象
	*/
	public AssetDepreciationDetailVO setSearchField(String searchField) {
		this.searchField=searchField;
		return this;
	}
	
	/**
	 * 获得 模糊搜索字段<br>
	 * @return 模糊搜索字段
	*/
	public String getFuzzyField() {
		return fuzzyField;
	}
	
	/**
	 * 设置 模糊搜索字段
	 * @param fuzzyField 模糊搜索字段
	 * @return 当前对象
	*/
	public AssetDepreciationDetailVO setFuzzyField(String fuzzyField) {
		this.fuzzyField=fuzzyField;
		return this;
	}
	
	/**
	 * 获得 搜索的值<br>
	 * @return 搜索的值
	*/
	public String getSearchValue() {
		return searchValue;
	}
	
	/**
	 * 设置 搜索的值
	 * @param searchValue 搜索的值
	 * @return 当前对象
	*/
	public AssetDepreciationDetailVO setSearchValue(String searchValue) {
		this.searchValue=searchValue;
		return this;
	}
	
	/**
	 * 获得 已修改字段<br>
	 * @return 已修改字段
	*/
	public List<String> getDirtyFields() {
		return dirtyFields;
	}
	
	/**
	 * 设置 已修改字段
	 * @param dirtyFields 已修改字段
	 * @return 当前对象
	*/
	public AssetDepreciationDetailVO setDirtyFields(List<String> dirtyFields) {
		this.dirtyFields=dirtyFields;
		return this;
	}
	
	/**
	 * 添加 已修改字段
	 * @param dirtyField 已修改字段
	 * @return 当前对象
	*/
	public AssetDepreciationDetailVO addDirtyField(String... dirtyField) {
		if(this.dirtyFields==null) dirtyFields=new ArrayList<>();
		this.dirtyFields.addAll(Arrays.asList(dirtyField));
		return this;
	}
	
	/**
	 * 获得 排序字段<br>
	 * @return 排序字段
	*/
	public String getSortField() {
		return sortField;
	}
	
	/**
	 * 设置 排序字段
	 * @param sortField 排序字段
	 * @return 当前对象
	*/
	public AssetDepreciationDetailVO setSortField(String sortField) {
		this.sortField=sortField;
		return this;
	}
	
	/**
	 * 获得 排序方式<br>
	 * @return 排序方式
	*/
	public String getSortType() {
		return sortType;
	}
	
	/**
	 * 设置 排序方式
	 * @param sortType 排序方式
	 * @return 当前对象
	*/
	public AssetDepreciationDetailVO setSortType(String sortType) {
		this.sortType=sortType;
		return this;
	}
	
	/**
	 * 获得 数据来源<br>
	 * 前端指定不同的来源，后端按来源执行不同的逻辑
	 * @return 数据来源
	*/
	public String getDataOrigin() {
		return dataOrigin;
	}
	
	/**
	 * 设置 数据来源
	 * @param dataOrigin 数据来源
	 * @return 当前对象
	*/
	public AssetDepreciationDetailVO setDataOrigin(String dataOrigin) {
		this.dataOrigin=dataOrigin;
		return this;
	}
	
	/**
	 * 获得 查询逻辑<br>
	 * 默认and，可指定 or 
	 * @return 查询逻辑
	*/
	public String getQueryLogic() {
		return queryLogic;
	}
	
	/**
	 * 设置 查询逻辑
	 * @param queryLogic 查询逻辑
	 * @return 当前对象
	*/
	public AssetDepreciationDetailVO setQueryLogic(String queryLogic) {
		this.queryLogic=queryLogic;
		return this;
	}
	
	/**
	 * 获得 主键清单<br>
	 * 用于接收批量主键参数
	 * @return 主键清单
	*/
	public List<String> getIds() {
		return ids;
	}
	
	/**
	 * 设置 主键清单
	 * @param ids 主键清单
	 * @return 当前对象
	*/
	public AssetDepreciationDetailVO setIds(List<String> ids) {
		this.ids=ids;
		return this;
	}
	
	/**
	 * 添加 主键清单
	 * @param id 主键清单
	 * @return 当前对象
	*/
	public AssetDepreciationDetailVO addId(String... id) {
		if(this.ids==null) ids=new ArrayList<>();
		this.ids.addAll(Arrays.asList(id));
		return this;
	}
	@Transient
	private transient CompositeParameter $compositeParameter;
	/**
	 * 获得解析后的复合查询参数
	 */
	@Transient
	public CompositeParameter getCompositeParameter() {
		if($compositeParameter!=null) return  $compositeParameter;
		$compositeParameter=new CompositeParameter(this.getSearchValue(),BeanUtil.toMap(this));
		return  $compositeParameter;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return AssetDepreciationDetailVO , 转换好的 AssetDepreciationDetailVO 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return AssetDepreciationDetailVO , 转换好的 PoJo 对象
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
	public AssetDepreciationDetailVO clone() {
		return duplicate(true);
	}

	/**
	 * 复制当前对象
	 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
	*/
	@Transient
	public AssetDepreciationDetailVO duplicate(boolean all) {
		com.dt.platform.domain.eam.meta.AssetDepreciationDetailVOMeta.$$proxy$$ inst = new com.dt.platform.domain.eam.meta.AssetDepreciationDetailVOMeta.$$proxy$$();
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
			inst.setSearchField(this.getSearchField());
			inst.setFuzzyField(this.getFuzzyField());
			inst.setAssetSource(this.getAssetSource());
			inst.setPageSize(this.getPageSize());
			inst.setLastAssetDepreciationDetail(this.getLastAssetDepreciationDetail());
			inst.setAssetDepreciation(this.getAssetDepreciation());
			inst.setFinancialOptionDict(this.getFinancialOptionDict());
			inst.setUseOrganization(this.getUseOrganization());
			inst.setAssetTarget(this.getAssetTarget());
			inst.setCalRuleList(this.getCalRuleList());
			inst.setCalRuleIds(this.getCalRuleIds());
			inst.setExpenseItemDict(this.getExpenseItemDict());
			inst.setPageIndex(this.getPageIndex());
			inst.setSortType(this.getSortType());
			inst.setAssetDepreciationOper(this.getAssetDepreciationOper());
			inst.setDirtyFields(this.getDirtyFields());
			inst.setSortField(this.getSortField());
			inst.setDataOrigin(this.getDataOrigin());
			inst.setIds(this.getIds());
			inst.setQueryLogic(this.getQueryLogic());
			inst.setAsset(this.getAsset());
			inst.setUseUser(this.getUseUser());
			inst.setSearchValue(this.getSearchValue());
		}
		inst.clearModifies();
		return inst;
	}

	/**
	 * 克隆当前对象
	*/
	@Transient
	public AssetDepreciationDetailVO clone(boolean deep) {
		return EntityContext.clone(AssetDepreciationDetailVO.class,this,deep);
	}

	/**
	 * 将 Map 转换成 AssetDepreciationDetailVO
	 * @param assetDepreciationDetailMap 包含实体信息的 Map 对象
	 * @return AssetDepreciationDetailVO , 转换好的的 AssetDepreciationDetail 对象
	*/
	@Transient
	public static AssetDepreciationDetailVO createFrom(Map<String,Object> assetDepreciationDetailMap) {
		if(assetDepreciationDetailMap==null) return null;
		AssetDepreciationDetailVO vo = create();
		EntityContext.copyProperties(vo,assetDepreciationDetailMap);
		vo.clearModifies();
		return vo;
	}

	/**
	 * 将 Pojo 转换成 AssetDepreciationDetailVO
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return AssetDepreciationDetailVO , 转换好的的 AssetDepreciationDetail 对象
	*/
	@Transient
	public static AssetDepreciationDetailVO createFrom(Object pojo) {
		if(pojo==null) return null;
		AssetDepreciationDetailVO vo = create();
		EntityContext.copyProperties(vo,pojo);
		vo.clearModifies();
		return vo;
	}

	/**
	 * 创建一个 AssetDepreciationDetailVO，等同于 new
	 * @return AssetDepreciationDetailVO 对象
	*/
	@Transient
	public static AssetDepreciationDetailVO create() {
		return new com.dt.platform.domain.eam.meta.AssetDepreciationDetailVOMeta.$$proxy$$();
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
			this.setAssetResidualsRate(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_RATE)));
			this.setCUsedServiceLife(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.C_USED_SERVICE_LIFE)));
			this.setUseOrgName(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.USE_ORG_NAME)));
			this.setERecoverableAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.E_RECOVERABLE_AMOUNT)));
			this.setDepreciationMethod(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.DEPRECIATION_METHOD)));
			this.setExpenseItemKey(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.EXPENSE_ITEM_KEY)));
			this.setCustomerInfo(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.CUSTOMER_INFO)));
			this.setFirstDepreciation(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION)));
			this.setManagerName(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.MANAGER_NAME)));
			this.setAssetNavPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.ASSET_NAV_PRICE)));
			this.setExpenseItemName(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.EXPENSE_ITEM_NAME)));
			this.setENavAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.E_NAV_AMOUNT)));
			this.setDetailIdSource(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.DETAIL_ID_SOURCE)));
			this.setSDepreciationAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.S_DEPRECIATION_AMOUNT)));
			this.setActionCode(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.ACTION_CODE)));
			this.setId(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.ID)));
			this.setCYearDepreciationAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.C_YEAR_DEPRECIATION_AMOUNT)));
			this.setAssetFinanceServiceLife(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.ASSET_FINANCE_SERVICE_LIFE)));
			this.setAssetCode(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.ASSET_CODE)));
			this.setFinancialOptionKey(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_KEY)));
			this.setVersion(DataParser.parse(Integer.class, map.get(AssetDepreciationDetailVOMeta.VERSION)));
			this.setFirstDepreciationMethod(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION_METHOD)));
			this.setAssetFinanceCategoryName(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_NAME)));
			this.setResultValueStr(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.RESULT_VALUE_STR)));
			this.setLastOperId(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.LAST_OPER_ID)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(AssetDepreciationDetailVOMeta.DELETE_TIME)));
			this.setEDepreciationAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.E_DEPRECIATION_AMOUNT)));
			this.setFinancialOptionName(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_NAME)));
			this.setAssetCategoryId(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.ASSET_CATEGORY_ID)));
			this.setSOriginalPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.S_ORIGINAL_PRICE)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.DELETE_BY)));
			this.setResultValueFloat(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.RESULT_VALUE_FLOAT)));
			this.setAssetCategoryName(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.ASSET_CATEGORY_NAME)));
			this.setSNavAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.S_NAV_AMOUNT)));
			this.setAssetStatusName(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.ASSET_STATUS_NAME)));
			this.setAssetTaxAmountRate(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.ASSET_TAX_AMOUNT_RATE)));
			this.setOperId(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.OPER_ID)));
			this.setAssetRegisterDate(DataParser.parse(Date.class, map.get(AssetDepreciationDetailVOMeta.ASSET_REGISTER_DATE)));
			this.setSRecoverableAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.S_RECOVERABLE_AMOUNT)));
			this.setAssetPurchaseUnitPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.ASSET_PURCHASE_UNIT_PRICE)));
			this.setEOriginalPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.E_ORIGINAL_PRICE)));
			this.setResult(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.RESULT)));
			this.setBusinessDate(DataParser.parse(Date.class, map.get(AssetDepreciationDetailVOMeta.BUSINESS_DATE)));
			this.setDepreciationId(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.DEPRECIATION_ID)));
			this.setAssetResidualsPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_PRICE)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.UPDATE_BY)));
			this.setAssetId(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.ASSET_ID)));
			this.setAssetServiceLife(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.ASSET_SERVICE_LIFE)));
			this.setCOriginalPriceIncrease(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.C_ORIGINAL_PRICE_INCREASE)));
			this.setAccountingServiceLife(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.ACCOUNTING_SERVICE_LIFE)));
			this.setUseUserName(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.USE_USER_NAME)));
			this.setUseOrgId(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.USE_ORG_ID)));
			this.setAssetModel(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.ASSET_MODEL)));
			this.setLastOperTime(DataParser.parse(Date.class, map.get(AssetDepreciationDetailVOMeta.LAST_OPER_TIME)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(AssetDepreciationDetailVOMeta.UPDATE_TIME)));
			this.setManagerId(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.MANAGER_ID)));
			this.setLabel(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.LABEL)));
			this.setCDepreciationAmount(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.C_DEPRECIATION_AMOUNT)));
			this.setResultStatus(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.RESULT_STATUS)));
			this.setAssetFinanceCategoryId(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_ID)));
			this.setCreateBy(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.CREATE_BY)));
			this.setAssetPurchaseDate(DataParser.parse(Date.class, map.get(AssetDepreciationDetailVOMeta.ASSET_PURCHASE_DATE)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(AssetDepreciationDetailVOMeta.DELETED)));
			this.setAssetOriginalUnitPrice(DataParser.parse(BigDecimal.class, map.get(AssetDepreciationDetailVOMeta.ASSET_ORIGINAL_UNIT_PRICE)));
			this.setResultDetail(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.RESULT_DETAIL)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(AssetDepreciationDetailVOMeta.CREATE_TIME)));
			this.setAssetName(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.ASSET_NAME)));
			this.setDetailIdTarget(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.DETAIL_ID_TARGET)));
			this.setUseUserId(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.USE_USER_ID)));
			// others
			this.setManager(DataParser.parse(Employee.class, map.get(AssetDepreciationDetailVOMeta.MANAGER)));
			this.setSearchField(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.SEARCH_FIELD)));
			this.setFuzzyField(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.FUZZY_FIELD)));
			this.setAssetSource(DataParser.parse(Asset.class, map.get(AssetDepreciationDetailVOMeta.ASSET_SOURCE)));
			this.setPageSize(DataParser.parse(Integer.class, map.get(AssetDepreciationDetailVOMeta.PAGE_SIZE)));
			this.setLastAssetDepreciationDetail(DataParser.parse(AssetDepreciationDetail.class, map.get(AssetDepreciationDetailVOMeta.LAST_ASSET_DEPRECIATION_DETAIL)));
			this.setAssetDepreciation(DataParser.parse(AssetDepreciation.class, map.get(AssetDepreciationDetailVOMeta.ASSET_DEPRECIATION)));
			this.setFinancialOptionDict(DataParser.parse(DictItem.class, map.get(AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_DICT)));
			this.setUseOrganization(DataParser.parse(Organization.class, map.get(AssetDepreciationDetailVOMeta.USE_ORGANIZATION)));
			this.setAssetTarget(DataParser.parse(Asset.class, map.get(AssetDepreciationDetailVOMeta.ASSET_TARGET)));
			this.setExpenseItemDict(DataParser.parse(DictItem.class, map.get(AssetDepreciationDetailVOMeta.EXPENSE_ITEM_DICT)));
			this.setPageIndex(DataParser.parse(Integer.class, map.get(AssetDepreciationDetailVOMeta.PAGE_INDEX)));
			this.setSortType(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.SORT_TYPE)));
			this.setAssetDepreciationOper(DataParser.parse(AssetDepreciationOper.class, map.get(AssetDepreciationDetailVOMeta.ASSET_DEPRECIATION_OPER)));
			this.setSortField(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.SORT_FIELD)));
			this.setDataOrigin(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.DATA_ORIGIN)));
			this.setQueryLogic(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.QUERY_LOGIC)));
			this.setAsset(DataParser.parse(Asset.class, map.get(AssetDepreciationDetailVOMeta.ASSET)));
			this.setUseUser(DataParser.parse(Employee.class, map.get(AssetDepreciationDetailVOMeta.USE_USER)));
			this.setSearchValue(DataParser.parse(String.class, map.get(AssetDepreciationDetailVOMeta.SEARCH_VALUE)));
			return true;
		} else {
			try {
				this.setAssetResidualsRate( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_RATE));
				this.setCUsedServiceLife( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.C_USED_SERVICE_LIFE));
				this.setUseOrgName( (String)map.get(AssetDepreciationDetailVOMeta.USE_ORG_NAME));
				this.setERecoverableAmount( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.E_RECOVERABLE_AMOUNT));
				this.setDepreciationMethod( (String)map.get(AssetDepreciationDetailVOMeta.DEPRECIATION_METHOD));
				this.setExpenseItemKey( (String)map.get(AssetDepreciationDetailVOMeta.EXPENSE_ITEM_KEY));
				this.setCustomerInfo( (String)map.get(AssetDepreciationDetailVOMeta.CUSTOMER_INFO));
				this.setFirstDepreciation( (String)map.get(AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION));
				this.setManagerName( (String)map.get(AssetDepreciationDetailVOMeta.MANAGER_NAME));
				this.setAssetNavPrice( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.ASSET_NAV_PRICE));
				this.setExpenseItemName( (String)map.get(AssetDepreciationDetailVOMeta.EXPENSE_ITEM_NAME));
				this.setENavAmount( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.E_NAV_AMOUNT));
				this.setDetailIdSource( (String)map.get(AssetDepreciationDetailVOMeta.DETAIL_ID_SOURCE));
				this.setSDepreciationAmount( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.S_DEPRECIATION_AMOUNT));
				this.setActionCode( (String)map.get(AssetDepreciationDetailVOMeta.ACTION_CODE));
				this.setId( (String)map.get(AssetDepreciationDetailVOMeta.ID));
				this.setCYearDepreciationAmount( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.C_YEAR_DEPRECIATION_AMOUNT));
				this.setAssetFinanceServiceLife( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.ASSET_FINANCE_SERVICE_LIFE));
				this.setAssetCode( (String)map.get(AssetDepreciationDetailVOMeta.ASSET_CODE));
				this.setFinancialOptionKey( (String)map.get(AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_KEY));
				this.setVersion( (Integer)map.get(AssetDepreciationDetailVOMeta.VERSION));
				this.setFirstDepreciationMethod( (String)map.get(AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION_METHOD));
				this.setAssetFinanceCategoryName( (String)map.get(AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_NAME));
				this.setResultValueStr( (String)map.get(AssetDepreciationDetailVOMeta.RESULT_VALUE_STR));
				this.setLastOperId( (String)map.get(AssetDepreciationDetailVOMeta.LAST_OPER_ID));
				this.setDeleteTime( (Date)map.get(AssetDepreciationDetailVOMeta.DELETE_TIME));
				this.setEDepreciationAmount( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.E_DEPRECIATION_AMOUNT));
				this.setFinancialOptionName( (String)map.get(AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_NAME));
				this.setAssetCategoryId( (String)map.get(AssetDepreciationDetailVOMeta.ASSET_CATEGORY_ID));
				this.setSOriginalPrice( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.S_ORIGINAL_PRICE));
				this.setDeleteBy( (String)map.get(AssetDepreciationDetailVOMeta.DELETE_BY));
				this.setResultValueFloat( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.RESULT_VALUE_FLOAT));
				this.setAssetCategoryName( (String)map.get(AssetDepreciationDetailVOMeta.ASSET_CATEGORY_NAME));
				this.setSNavAmount( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.S_NAV_AMOUNT));
				this.setAssetStatusName( (String)map.get(AssetDepreciationDetailVOMeta.ASSET_STATUS_NAME));
				this.setAssetTaxAmountRate( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.ASSET_TAX_AMOUNT_RATE));
				this.setOperId( (String)map.get(AssetDepreciationDetailVOMeta.OPER_ID));
				this.setAssetRegisterDate( (Date)map.get(AssetDepreciationDetailVOMeta.ASSET_REGISTER_DATE));
				this.setSRecoverableAmount( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.S_RECOVERABLE_AMOUNT));
				this.setAssetPurchaseUnitPrice( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.ASSET_PURCHASE_UNIT_PRICE));
				this.setEOriginalPrice( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.E_ORIGINAL_PRICE));
				this.setResult( (String)map.get(AssetDepreciationDetailVOMeta.RESULT));
				this.setBusinessDate( (Date)map.get(AssetDepreciationDetailVOMeta.BUSINESS_DATE));
				this.setDepreciationId( (String)map.get(AssetDepreciationDetailVOMeta.DEPRECIATION_ID));
				this.setAssetResidualsPrice( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_PRICE));
				this.setUpdateBy( (String)map.get(AssetDepreciationDetailVOMeta.UPDATE_BY));
				this.setAssetId( (String)map.get(AssetDepreciationDetailVOMeta.ASSET_ID));
				this.setAssetServiceLife( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.ASSET_SERVICE_LIFE));
				this.setCOriginalPriceIncrease( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.C_ORIGINAL_PRICE_INCREASE));
				this.setAccountingServiceLife( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.ACCOUNTING_SERVICE_LIFE));
				this.setUseUserName( (String)map.get(AssetDepreciationDetailVOMeta.USE_USER_NAME));
				this.setUseOrgId( (String)map.get(AssetDepreciationDetailVOMeta.USE_ORG_ID));
				this.setAssetModel( (String)map.get(AssetDepreciationDetailVOMeta.ASSET_MODEL));
				this.setLastOperTime( (Date)map.get(AssetDepreciationDetailVOMeta.LAST_OPER_TIME));
				this.setUpdateTime( (Date)map.get(AssetDepreciationDetailVOMeta.UPDATE_TIME));
				this.setManagerId( (String)map.get(AssetDepreciationDetailVOMeta.MANAGER_ID));
				this.setLabel( (String)map.get(AssetDepreciationDetailVOMeta.LABEL));
				this.setCDepreciationAmount( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.C_DEPRECIATION_AMOUNT));
				this.setResultStatus( (String)map.get(AssetDepreciationDetailVOMeta.RESULT_STATUS));
				this.setAssetFinanceCategoryId( (String)map.get(AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_ID));
				this.setCreateBy( (String)map.get(AssetDepreciationDetailVOMeta.CREATE_BY));
				this.setAssetPurchaseDate( (Date)map.get(AssetDepreciationDetailVOMeta.ASSET_PURCHASE_DATE));
				this.setDeleted( (Integer)map.get(AssetDepreciationDetailVOMeta.DELETED));
				this.setAssetOriginalUnitPrice( (BigDecimal)map.get(AssetDepreciationDetailVOMeta.ASSET_ORIGINAL_UNIT_PRICE));
				this.setResultDetail( (String)map.get(AssetDepreciationDetailVOMeta.RESULT_DETAIL));
				this.setCreateTime( (Date)map.get(AssetDepreciationDetailVOMeta.CREATE_TIME));
				this.setAssetName( (String)map.get(AssetDepreciationDetailVOMeta.ASSET_NAME));
				this.setDetailIdTarget( (String)map.get(AssetDepreciationDetailVOMeta.DETAIL_ID_TARGET));
				this.setUseUserId( (String)map.get(AssetDepreciationDetailVOMeta.USE_USER_ID));
				// others
				this.setManager( (Employee)map.get(AssetDepreciationDetailVOMeta.MANAGER));
				this.setSearchField( (String)map.get(AssetDepreciationDetailVOMeta.SEARCH_FIELD));
				this.setFuzzyField( (String)map.get(AssetDepreciationDetailVOMeta.FUZZY_FIELD));
				this.setAssetSource( (Asset)map.get(AssetDepreciationDetailVOMeta.ASSET_SOURCE));
				this.setPageSize( (Integer)map.get(AssetDepreciationDetailVOMeta.PAGE_SIZE));
				this.setLastAssetDepreciationDetail( (AssetDepreciationDetail)map.get(AssetDepreciationDetailVOMeta.LAST_ASSET_DEPRECIATION_DETAIL));
				this.setAssetDepreciation( (AssetDepreciation)map.get(AssetDepreciationDetailVOMeta.ASSET_DEPRECIATION));
				this.setFinancialOptionDict( (DictItem)map.get(AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_DICT));
				this.setUseOrganization( (Organization)map.get(AssetDepreciationDetailVOMeta.USE_ORGANIZATION));
				this.setAssetTarget( (Asset)map.get(AssetDepreciationDetailVOMeta.ASSET_TARGET));
				this.setExpenseItemDict( (DictItem)map.get(AssetDepreciationDetailVOMeta.EXPENSE_ITEM_DICT));
				this.setPageIndex( (Integer)map.get(AssetDepreciationDetailVOMeta.PAGE_INDEX));
				this.setSortType( (String)map.get(AssetDepreciationDetailVOMeta.SORT_TYPE));
				this.setAssetDepreciationOper( (AssetDepreciationOper)map.get(AssetDepreciationDetailVOMeta.ASSET_DEPRECIATION_OPER));
				this.setSortField( (String)map.get(AssetDepreciationDetailVOMeta.SORT_FIELD));
				this.setDataOrigin( (String)map.get(AssetDepreciationDetailVOMeta.DATA_ORIGIN));
				this.setQueryLogic( (String)map.get(AssetDepreciationDetailVOMeta.QUERY_LOGIC));
				this.setAsset( (Asset)map.get(AssetDepreciationDetailVOMeta.ASSET));
				this.setUseUser( (Employee)map.get(AssetDepreciationDetailVOMeta.USE_USER));
				this.setSearchValue( (String)map.get(AssetDepreciationDetailVOMeta.SEARCH_VALUE));
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
			this.setAssetResidualsRate(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_RATE)));
			this.setCUsedServiceLife(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.C_USED_SERVICE_LIFE)));
			this.setUseOrgName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.USE_ORG_NAME)));
			this.setERecoverableAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.E_RECOVERABLE_AMOUNT)));
			this.setDepreciationMethod(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.DEPRECIATION_METHOD)));
			this.setExpenseItemKey(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.EXPENSE_ITEM_KEY)));
			this.setCustomerInfo(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.CUSTOMER_INFO)));
			this.setFirstDepreciation(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION)));
			this.setManagerName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.MANAGER_NAME)));
			this.setAssetNavPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_NAV_PRICE)));
			this.setExpenseItemName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.EXPENSE_ITEM_NAME)));
			this.setENavAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.E_NAV_AMOUNT)));
			this.setDetailIdSource(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.DETAIL_ID_SOURCE)));
			this.setSDepreciationAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.S_DEPRECIATION_AMOUNT)));
			this.setActionCode(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.ACTION_CODE)));
			this.setId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.ID)));
			this.setCYearDepreciationAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.C_YEAR_DEPRECIATION_AMOUNT)));
			this.setAssetFinanceServiceLife(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_FINANCE_SERVICE_LIFE)));
			this.setAssetCode(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_CODE)));
			this.setFinancialOptionKey(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_KEY)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(AssetDepreciationDetailVOMeta.VERSION)));
			this.setFirstDepreciationMethod(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION_METHOD)));
			this.setAssetFinanceCategoryName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_NAME)));
			this.setResultValueStr(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.RESULT_VALUE_STR)));
			this.setLastOperId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.LAST_OPER_ID)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailVOMeta.DELETE_TIME)));
			this.setEDepreciationAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.E_DEPRECIATION_AMOUNT)));
			this.setFinancialOptionName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_NAME)));
			this.setAssetCategoryId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_CATEGORY_ID)));
			this.setSOriginalPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.S_ORIGINAL_PRICE)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.DELETE_BY)));
			this.setResultValueFloat(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.RESULT_VALUE_FLOAT)));
			this.setAssetCategoryName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_CATEGORY_NAME)));
			this.setSNavAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.S_NAV_AMOUNT)));
			this.setAssetStatusName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_STATUS_NAME)));
			this.setAssetTaxAmountRate(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_TAX_AMOUNT_RATE)));
			this.setOperId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.OPER_ID)));
			this.setAssetRegisterDate(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_REGISTER_DATE)));
			this.setSRecoverableAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.S_RECOVERABLE_AMOUNT)));
			this.setAssetPurchaseUnitPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_PURCHASE_UNIT_PRICE)));
			this.setEOriginalPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.E_ORIGINAL_PRICE)));
			this.setResult(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.RESULT)));
			this.setBusinessDate(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailVOMeta.BUSINESS_DATE)));
			this.setDepreciationId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.DEPRECIATION_ID)));
			this.setAssetResidualsPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_PRICE)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.UPDATE_BY)));
			this.setAssetId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_ID)));
			this.setAssetServiceLife(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_SERVICE_LIFE)));
			this.setCOriginalPriceIncrease(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.C_ORIGINAL_PRICE_INCREASE)));
			this.setAccountingServiceLife(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.ACCOUNTING_SERVICE_LIFE)));
			this.setUseUserName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.USE_USER_NAME)));
			this.setUseOrgId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.USE_ORG_ID)));
			this.setAssetModel(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_MODEL)));
			this.setLastOperTime(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailVOMeta.LAST_OPER_TIME)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailVOMeta.UPDATE_TIME)));
			this.setManagerId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.MANAGER_ID)));
			this.setLabel(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.LABEL)));
			this.setCDepreciationAmount(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.C_DEPRECIATION_AMOUNT)));
			this.setResultStatus(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.RESULT_STATUS)));
			this.setAssetFinanceCategoryId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_ID)));
			this.setCreateBy(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.CREATE_BY)));
			this.setAssetPurchaseDate(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_PURCHASE_DATE)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(AssetDepreciationDetailVOMeta.DELETED)));
			this.setAssetOriginalUnitPrice(DataParser.parse(BigDecimal.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_ORIGINAL_UNIT_PRICE)));
			this.setResultDetail(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.RESULT_DETAIL)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(AssetDepreciationDetailVOMeta.CREATE_TIME)));
			this.setAssetName(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.ASSET_NAME)));
			this.setDetailIdTarget(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.DETAIL_ID_TARGET)));
			this.setUseUserId(DataParser.parse(String.class, r.getValue(AssetDepreciationDetailVOMeta.USE_USER_ID)));
			return true;
		} else {
			try {
				this.setAssetResidualsRate( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_RATE));
				this.setCUsedServiceLife( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.C_USED_SERVICE_LIFE));
				this.setUseOrgName( (String)r.getValue(AssetDepreciationDetailVOMeta.USE_ORG_NAME));
				this.setERecoverableAmount( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.E_RECOVERABLE_AMOUNT));
				this.setDepreciationMethod( (String)r.getValue(AssetDepreciationDetailVOMeta.DEPRECIATION_METHOD));
				this.setExpenseItemKey( (String)r.getValue(AssetDepreciationDetailVOMeta.EXPENSE_ITEM_KEY));
				this.setCustomerInfo( (String)r.getValue(AssetDepreciationDetailVOMeta.CUSTOMER_INFO));
				this.setFirstDepreciation( (String)r.getValue(AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION));
				this.setManagerName( (String)r.getValue(AssetDepreciationDetailVOMeta.MANAGER_NAME));
				this.setAssetNavPrice( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.ASSET_NAV_PRICE));
				this.setExpenseItemName( (String)r.getValue(AssetDepreciationDetailVOMeta.EXPENSE_ITEM_NAME));
				this.setENavAmount( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.E_NAV_AMOUNT));
				this.setDetailIdSource( (String)r.getValue(AssetDepreciationDetailVOMeta.DETAIL_ID_SOURCE));
				this.setSDepreciationAmount( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.S_DEPRECIATION_AMOUNT));
				this.setActionCode( (String)r.getValue(AssetDepreciationDetailVOMeta.ACTION_CODE));
				this.setId( (String)r.getValue(AssetDepreciationDetailVOMeta.ID));
				this.setCYearDepreciationAmount( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.C_YEAR_DEPRECIATION_AMOUNT));
				this.setAssetFinanceServiceLife( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.ASSET_FINANCE_SERVICE_LIFE));
				this.setAssetCode( (String)r.getValue(AssetDepreciationDetailVOMeta.ASSET_CODE));
				this.setFinancialOptionKey( (String)r.getValue(AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_KEY));
				this.setVersion( (Integer)r.getValue(AssetDepreciationDetailVOMeta.VERSION));
				this.setFirstDepreciationMethod( (String)r.getValue(AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION_METHOD));
				this.setAssetFinanceCategoryName( (String)r.getValue(AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_NAME));
				this.setResultValueStr( (String)r.getValue(AssetDepreciationDetailVOMeta.RESULT_VALUE_STR));
				this.setLastOperId( (String)r.getValue(AssetDepreciationDetailVOMeta.LAST_OPER_ID));
				this.setDeleteTime( (Date)r.getValue(AssetDepreciationDetailVOMeta.DELETE_TIME));
				this.setEDepreciationAmount( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.E_DEPRECIATION_AMOUNT));
				this.setFinancialOptionName( (String)r.getValue(AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_NAME));
				this.setAssetCategoryId( (String)r.getValue(AssetDepreciationDetailVOMeta.ASSET_CATEGORY_ID));
				this.setSOriginalPrice( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.S_ORIGINAL_PRICE));
				this.setDeleteBy( (String)r.getValue(AssetDepreciationDetailVOMeta.DELETE_BY));
				this.setResultValueFloat( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.RESULT_VALUE_FLOAT));
				this.setAssetCategoryName( (String)r.getValue(AssetDepreciationDetailVOMeta.ASSET_CATEGORY_NAME));
				this.setSNavAmount( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.S_NAV_AMOUNT));
				this.setAssetStatusName( (String)r.getValue(AssetDepreciationDetailVOMeta.ASSET_STATUS_NAME));
				this.setAssetTaxAmountRate( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.ASSET_TAX_AMOUNT_RATE));
				this.setOperId( (String)r.getValue(AssetDepreciationDetailVOMeta.OPER_ID));
				this.setAssetRegisterDate( (Date)r.getValue(AssetDepreciationDetailVOMeta.ASSET_REGISTER_DATE));
				this.setSRecoverableAmount( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.S_RECOVERABLE_AMOUNT));
				this.setAssetPurchaseUnitPrice( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.ASSET_PURCHASE_UNIT_PRICE));
				this.setEOriginalPrice( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.E_ORIGINAL_PRICE));
				this.setResult( (String)r.getValue(AssetDepreciationDetailVOMeta.RESULT));
				this.setBusinessDate( (Date)r.getValue(AssetDepreciationDetailVOMeta.BUSINESS_DATE));
				this.setDepreciationId( (String)r.getValue(AssetDepreciationDetailVOMeta.DEPRECIATION_ID));
				this.setAssetResidualsPrice( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_PRICE));
				this.setUpdateBy( (String)r.getValue(AssetDepreciationDetailVOMeta.UPDATE_BY));
				this.setAssetId( (String)r.getValue(AssetDepreciationDetailVOMeta.ASSET_ID));
				this.setAssetServiceLife( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.ASSET_SERVICE_LIFE));
				this.setCOriginalPriceIncrease( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.C_ORIGINAL_PRICE_INCREASE));
				this.setAccountingServiceLife( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.ACCOUNTING_SERVICE_LIFE));
				this.setUseUserName( (String)r.getValue(AssetDepreciationDetailVOMeta.USE_USER_NAME));
				this.setUseOrgId( (String)r.getValue(AssetDepreciationDetailVOMeta.USE_ORG_ID));
				this.setAssetModel( (String)r.getValue(AssetDepreciationDetailVOMeta.ASSET_MODEL));
				this.setLastOperTime( (Date)r.getValue(AssetDepreciationDetailVOMeta.LAST_OPER_TIME));
				this.setUpdateTime( (Date)r.getValue(AssetDepreciationDetailVOMeta.UPDATE_TIME));
				this.setManagerId( (String)r.getValue(AssetDepreciationDetailVOMeta.MANAGER_ID));
				this.setLabel( (String)r.getValue(AssetDepreciationDetailVOMeta.LABEL));
				this.setCDepreciationAmount( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.C_DEPRECIATION_AMOUNT));
				this.setResultStatus( (String)r.getValue(AssetDepreciationDetailVOMeta.RESULT_STATUS));
				this.setAssetFinanceCategoryId( (String)r.getValue(AssetDepreciationDetailVOMeta.ASSET_FINANCE_CATEGORY_ID));
				this.setCreateBy( (String)r.getValue(AssetDepreciationDetailVOMeta.CREATE_BY));
				this.setAssetPurchaseDate( (Date)r.getValue(AssetDepreciationDetailVOMeta.ASSET_PURCHASE_DATE));
				this.setDeleted( (Integer)r.getValue(AssetDepreciationDetailVOMeta.DELETED));
				this.setAssetOriginalUnitPrice( (BigDecimal)r.getValue(AssetDepreciationDetailVOMeta.ASSET_ORIGINAL_UNIT_PRICE));
				this.setResultDetail( (String)r.getValue(AssetDepreciationDetailVOMeta.RESULT_DETAIL));
				this.setCreateTime( (Date)r.getValue(AssetDepreciationDetailVOMeta.CREATE_TIME));
				this.setAssetName( (String)r.getValue(AssetDepreciationDetailVOMeta.ASSET_NAME));
				this.setDetailIdTarget( (String)r.getValue(AssetDepreciationDetailVOMeta.DETAIL_ID_TARGET));
				this.setUseUserId( (String)r.getValue(AssetDepreciationDetailVOMeta.USE_USER_ID));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}