package com.dt.platform.domain.eam;

import com.github.foxnic.dao.entity.Entity;
import javax.persistence.Table;
import com.github.foxnic.sql.meta.DBTable;
import com.dt.platform.constants.db.EAMTables.EAM_ASSET_BORROW;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Transient;
import java.util.List;
import org.github.foxnic.web.domain.hrm.Employee;
import com.github.foxnic.commons.lang.DataParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import com.github.foxnic.dao.entity.EntityContext;



/**
 * 资产借用
 * @author 金杰 , maillank@qq.com
 * @since 2022-07-03 15:18:00
 * @sign FFC9530A81E9D39921843FED06D06485
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@Table(name = "eam_asset_borrow")
public class AssetBorrow extends Entity {

	private static final long serialVersionUID = 1L;

	public static final DBTable TABLE =EAM_ASSET_BORROW.$TABLE;
	
	/**
	 * 主键：主键
	*/
	@Id
	@ApiModelProperty(required = true,value="主键" , notes = "主键")
	private String id;
	
	/**
	 * 业务编号：业务编号
	*/
	@ApiModelProperty(required = false,value="业务编号" , notes = "业务编号")
	private String businessCode;
	
	/**
	 * 流程：流程
	*/
	@ApiModelProperty(required = false,value="流程" , notes = "流程")
	private String procId;
	
	/**
	 * 办理状态：办理状态
	*/
	@ApiModelProperty(required = false,value="办理状态" , notes = "办理状态")
	private String status;
	
	/**
	 * 借用状态：借用状态
	*/
	@ApiModelProperty(required = false,value="借用状态" , notes = "借用状态")
	private String borrowStatus;
	
	/**
	 * 业务名称：业务名称
	*/
	@ApiModelProperty(required = false,value="业务名称" , notes = "业务名称")
	private String name;
	
	/**
	 * 借用人：借用人
	*/
	@ApiModelProperty(required = false,value="借用人" , notes = "借用人")
	private String borrowerId;
	
	/**
	 * 借出时间：借出时间
	*/
	@ApiModelProperty(required = false,value="借出时间" , notes = "借出时间")
	private Date borrowTime;
	
	/**
	 * 预计归还时间：预计归还时间
	*/
	@ApiModelProperty(required = false,value="预计归还时间" , notes = "预计归还时间")
	private Date planReturnDate;
	
	/**
	 * 归还时间：归还时间
	*/
	@ApiModelProperty(required = false,value="归还时间" , notes = "归还时间")
	private Date returnDate;
	
	/**
	 * 借出说明：借出说明
	*/
	@ApiModelProperty(required = false,value="借出说明" , notes = "借出说明")
	private String content;
	
	/**
	 * 制单人：制单人
	*/
	@ApiModelProperty(required = false,value="制单人" , notes = "制单人")
	private String originatorId;
	
	/**
	 * 业务日期：业务日期
	*/
	@ApiModelProperty(required = false,value="业务日期" , notes = "业务日期")
	private Date businessDate;
	
	/**
	 * 附件：附件
	*/
	@ApiModelProperty(required = false,value="附件" , notes = "附件")
	private String attach;
	
	/**
	 * 创建人ID：创建人ID
	*/
	@ApiModelProperty(required = false,value="创建人ID" , notes = "创建人ID")
	private String createBy;
	
	/**
	 * 创建时间：创建时间
	*/
	@ApiModelProperty(required = false,value="创建时间" , notes = "创建时间")
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
	@ApiModelProperty(required = true,value="是否已删除" , notes = "是否已删除")
	private Integer deleted;
	@Transient
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
	@ApiModelProperty(required = true,value="version" , notes = "version")
	private Integer version;
	
	/**
	 * 租户：租户
	*/
	@ApiModelProperty(required = false,value="租户" , notes = "租户")
	private String tenantId;
	
	/**
	 * 选择数据：选择数据
	*/
	@ApiModelProperty(required = false,value="选择数据" , notes = "选择数据")
	private String selectedCode;
	
	/**
	 * 资产：资产
	*/
	@ApiModelProperty(required = false,value="资产" , notes = "资产")
	private List<Asset> assetList;
	
	/**
	 * 资产列表：资产列表
	*/
	@ApiModelProperty(required = false,value="资产列表" , notes = "资产列表")
	private List<String> assetIds;
	
	/**
	 * 资产列表：资产列表
	*/
	@ApiModelProperty(required = false,value="资产列表" , notes = "资产列表")
	private List<AssetItem> assetItemList;
	
	/**
	 * 申请人：申请人
	*/
	@ApiModelProperty(required = false,value="申请人" , notes = "申请人")
	private String originatorUserName;
	
	/**
	 * 制单人：制单人
	*/
	@ApiModelProperty(required = false,value="制单人" , notes = "制单人")
	private Employee originator;
	
	/**
	 * 借用人：借用人
	*/
	@ApiModelProperty(required = false,value="借用人" , notes = "借用人")
	private Employee borrower;
	
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
	public AssetBorrow setId(String id) {
		this.id=id;
		return this;
	}
	
	/**
	 * 获得 业务编号<br>
	 * 业务编号
	 * @return 业务编号
	*/
	public String getBusinessCode() {
		return businessCode;
	}
	
	/**
	 * 设置 业务编号
	 * @param businessCode 业务编号
	 * @return 当前对象
	*/
	public AssetBorrow setBusinessCode(String businessCode) {
		this.businessCode=businessCode;
		return this;
	}
	
	/**
	 * 获得 流程<br>
	 * 流程
	 * @return 流程
	*/
	public String getProcId() {
		return procId;
	}
	
	/**
	 * 设置 流程
	 * @param procId 流程
	 * @return 当前对象
	*/
	public AssetBorrow setProcId(String procId) {
		this.procId=procId;
		return this;
	}
	
	/**
	 * 获得 办理状态<br>
	 * 办理状态
	 * @return 办理状态
	*/
	public String getStatus() {
		return status;
	}
	
	/**
	 * 设置 办理状态
	 * @param status 办理状态
	 * @return 当前对象
	*/
	public AssetBorrow setStatus(String status) {
		this.status=status;
		return this;
	}
	
	/**
	 * 获得 借用状态<br>
	 * 借用状态
	 * @return 借用状态
	*/
	public String getBorrowStatus() {
		return borrowStatus;
	}
	
	/**
	 * 设置 借用状态
	 * @param borrowStatus 借用状态
	 * @return 当前对象
	*/
	public AssetBorrow setBorrowStatus(String borrowStatus) {
		this.borrowStatus=borrowStatus;
		return this;
	}
	
	/**
	 * 获得 业务名称<br>
	 * 业务名称
	 * @return 业务名称
	*/
	public String getName() {
		return name;
	}
	
	/**
	 * 设置 业务名称
	 * @param name 业务名称
	 * @return 当前对象
	*/
	public AssetBorrow setName(String name) {
		this.name=name;
		return this;
	}
	
	/**
	 * 获得 借用人<br>
	 * 借用人
	 * @return 借用人
	*/
	public String getBorrowerId() {
		return borrowerId;
	}
	
	/**
	 * 设置 借用人
	 * @param borrowerId 借用人
	 * @return 当前对象
	*/
	public AssetBorrow setBorrowerId(String borrowerId) {
		this.borrowerId=borrowerId;
		return this;
	}
	
	/**
	 * 获得 借出时间<br>
	 * 借出时间
	 * @return 借出时间
	*/
	public Date getBorrowTime() {
		return borrowTime;
	}
	
	/**
	 * 设置 借出时间
	 * @param borrowTime 借出时间
	 * @return 当前对象
	*/
	public AssetBorrow setBorrowTime(Date borrowTime) {
		this.borrowTime=borrowTime;
		return this;
	}
	
	/**
	 * 获得 预计归还时间<br>
	 * 预计归还时间
	 * @return 预计归还时间
	*/
	public Date getPlanReturnDate() {
		return planReturnDate;
	}
	
	/**
	 * 设置 预计归还时间
	 * @param planReturnDate 预计归还时间
	 * @return 当前对象
	*/
	public AssetBorrow setPlanReturnDate(Date planReturnDate) {
		this.planReturnDate=planReturnDate;
		return this;
	}
	
	/**
	 * 获得 归还时间<br>
	 * 归还时间
	 * @return 归还时间
	*/
	public Date getReturnDate() {
		return returnDate;
	}
	
	/**
	 * 设置 归还时间
	 * @param returnDate 归还时间
	 * @return 当前对象
	*/
	public AssetBorrow setReturnDate(Date returnDate) {
		this.returnDate=returnDate;
		return this;
	}
	
	/**
	 * 获得 借出说明<br>
	 * 借出说明
	 * @return 借出说明
	*/
	public String getContent() {
		return content;
	}
	
	/**
	 * 设置 借出说明
	 * @param content 借出说明
	 * @return 当前对象
	*/
	public AssetBorrow setContent(String content) {
		this.content=content;
		return this;
	}
	
	/**
	 * 获得 制单人<br>
	 * 制单人
	 * @return 制单人
	*/
	public String getOriginatorId() {
		return originatorId;
	}
	
	/**
	 * 设置 制单人
	 * @param originatorId 制单人
	 * @return 当前对象
	*/
	public AssetBorrow setOriginatorId(String originatorId) {
		this.originatorId=originatorId;
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
	public AssetBorrow setBusinessDate(Date businessDate) {
		this.businessDate=businessDate;
		return this;
	}
	
	/**
	 * 获得 附件<br>
	 * 附件
	 * @return 附件
	*/
	public String getAttach() {
		return attach;
	}
	
	/**
	 * 设置 附件
	 * @param attach 附件
	 * @return 当前对象
	*/
	public AssetBorrow setAttach(String attach) {
		this.attach=attach;
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
	public AssetBorrow setCreateBy(String createBy) {
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
	public AssetBorrow setCreateTime(Date createTime) {
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
	public AssetBorrow setUpdateBy(String updateBy) {
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
	public AssetBorrow setUpdateTime(Date updateTime) {
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
	public AssetBorrow setDeleted(Integer deleted) {
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
	public AssetBorrow setDeleted(Boolean deletedBool) {
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
	public AssetBorrow setDeleteBy(String deleteBy) {
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
	public AssetBorrow setDeleteTime(Date deleteTime) {
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
	public AssetBorrow setVersion(Integer version) {
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
	public AssetBorrow setTenantId(String tenantId) {
		this.tenantId=tenantId;
		return this;
	}
	
	/**
	 * 获得 选择数据<br>
	 * 选择数据
	 * @return 选择数据
	*/
	public String getSelectedCode() {
		return selectedCode;
	}
	
	/**
	 * 设置 选择数据
	 * @param selectedCode 选择数据
	 * @return 当前对象
	*/
	public AssetBorrow setSelectedCode(String selectedCode) {
		this.selectedCode=selectedCode;
		return this;
	}
	
	/**
	 * 获得 资产<br>
	 * 资产
	 * @return 资产
	*/
	public List<Asset> getAssetList() {
		return assetList;
	}
	
	/**
	 * 设置 资产
	 * @param assetList 资产
	 * @return 当前对象
	*/
	public AssetBorrow setAssetList(List<Asset> assetList) {
		this.assetList=assetList;
		return this;
	}
	
	/**
	 * 添加 资产
	 * @param asset 资产
	 * @return 当前对象
	*/
	public AssetBorrow addAsset(Asset... asset) {
		if(this.assetList==null) assetList=new ArrayList<>();
		this.assetList.addAll(Arrays.asList(asset));
		return this;
	}
	
	/**
	 * 获得 资产列表<br>
	 * 资产列表
	 * @return 资产列表
	*/
	public List<String> getAssetIds() {
		return assetIds;
	}
	
	/**
	 * 设置 资产列表
	 * @param assetIds 资产列表
	 * @return 当前对象
	*/
	public AssetBorrow setAssetIds(List<String> assetIds) {
		this.assetIds=assetIds;
		return this;
	}
	
	/**
	 * 添加 资产列表
	 * @param assetId 资产列表
	 * @return 当前对象
	*/
	public AssetBorrow addAssetId(String... assetId) {
		if(this.assetIds==null) assetIds=new ArrayList<>();
		this.assetIds.addAll(Arrays.asList(assetId));
		return this;
	}
	
	/**
	 * 获得 资产列表<br>
	 * 资产列表
	 * @return 资产列表
	*/
	public List<AssetItem> getAssetItemList() {
		return assetItemList;
	}
	
	/**
	 * 设置 资产列表
	 * @param assetItemList 资产列表
	 * @return 当前对象
	*/
	public AssetBorrow setAssetItemList(List<AssetItem> assetItemList) {
		this.assetItemList=assetItemList;
		return this;
	}
	
	/**
	 * 添加 资产列表
	 * @param assetItem 资产列表
	 * @return 当前对象
	*/
	public AssetBorrow addAssetItem(AssetItem... assetItem) {
		if(this.assetItemList==null) assetItemList=new ArrayList<>();
		this.assetItemList.addAll(Arrays.asList(assetItem));
		return this;
	}
	
	/**
	 * 获得 申请人<br>
	 * 申请人
	 * @return 申请人
	*/
	public String getOriginatorUserName() {
		return originatorUserName;
	}
	
	/**
	 * 设置 申请人
	 * @param originatorUserName 申请人
	 * @return 当前对象
	*/
	public AssetBorrow setOriginatorUserName(String originatorUserName) {
		this.originatorUserName=originatorUserName;
		return this;
	}
	
	/**
	 * 获得 制单人<br>
	 * 制单人
	 * @return 制单人
	*/
	public Employee getOriginator() {
		return originator;
	}
	
	/**
	 * 设置 制单人
	 * @param originator 制单人
	 * @return 当前对象
	*/
	public AssetBorrow setOriginator(Employee originator) {
		this.originator=originator;
		return this;
	}
	
	/**
	 * 获得 借用人<br>
	 * 借用人
	 * @return 借用人
	*/
	public Employee getBorrower() {
		return borrower;
	}
	
	/**
	 * 设置 借用人
	 * @param borrower 借用人
	 * @return 当前对象
	*/
	public AssetBorrow setBorrower(Employee borrower) {
		this.borrower=borrower;
		return this;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return AssetBorrow , 转换好的 AssetBorrow 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return AssetBorrow , 转换好的 PoJo 对象
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
	 * 将 Map 转换成 AssetBorrow
	 * @param assetBorrowMap 包含实体信息的 Map 对象
	 * @return AssetBorrow , 转换好的的 AssetBorrow 对象
	*/
	@Transient
	public static AssetBorrow createFrom(Map<String,Object> assetBorrowMap) {
		if(assetBorrowMap==null) return null;
		AssetBorrow po = EntityContext.create(AssetBorrow.class, assetBorrowMap);
		return po;
	}

	/**
	 * 将 Pojo 转换成 AssetBorrow
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return AssetBorrow , 转换好的的 AssetBorrow 对象
	*/
	@Transient
	public static AssetBorrow createFrom(Object pojo) {
		if(pojo==null) return null;
		AssetBorrow po = EntityContext.create(AssetBorrow.class,pojo);
		return po;
	}

	/**
	 * 创建一个 AssetBorrow，等同于 new
	 * @return AssetBorrow 对象
	*/
	@Transient
	public static AssetBorrow create() {
		return EntityContext.create(AssetBorrow.class);
	}
}