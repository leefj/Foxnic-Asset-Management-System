package com.dt.platform.domain.eam;

import com.github.foxnic.dao.entity.Entity;
import javax.persistence.Table;
import com.github.foxnic.sql.meta.DBTable;
import com.dt.platform.constants.db.EAMTables.EAM_ASSET_RACK_INFO;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Transient;
import org.github.foxnic.web.domain.system.DictItem;
import com.github.foxnic.commons.lang.DataParser;
import java.util.Map;
import com.github.foxnic.dao.entity.EntityContext;



/**
 * 机柜
 * @author 金杰 , maillank@qq.com
 * @since 2022-08-27 23:10:12
 * @sign F2153D99FF02AFCE6267F07D27B13158
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@Table(name = "eam_asset_rack_info")
public class AssetRackInfo extends Entity {

	private static final long serialVersionUID = 1L;

	public static final DBTable TABLE =EAM_ASSET_RACK_INFO.$TABLE;
	
	/**
	 * 主键：主键
	*/
	@Id
	@ApiModelProperty(required = true,value="主键" , notes = "主键")
	private String id;
	
	/**
	 * 编码：编码
	*/
	@ApiModelProperty(required = false,value="编码" , notes = "编码")
	private String rackCode;
	
	/**
	 * 类型：类型
	*/
	@ApiModelProperty(required = false,value="类型" , notes = "类型")
	private String rackType;
	
	/**
	 * 名称：名称
	*/
	@ApiModelProperty(required = false,value="名称" , notes = "名称")
	private String rackName;
	
	/**
	 * 状态：状态
	*/
	@ApiModelProperty(required = false,value="状态" , notes = "状态")
	private String status;
	
	/**
	 * 环境：环境
	*/
	@ApiModelProperty(required = false,value="环境" , notes = "环境")
	private String environment;
	
	/**
	 * 使用分类：使用分类
	*/
	@ApiModelProperty(required = false,value="使用分类" , notes = "使用分类")
	private String rackUsedType;
	
	/**
	 * 容量：容量
	*/
	@ApiModelProperty(required = false,value="容量" , notes = "容量")
	private BigDecimal rackCaptical;
	
	/**
	 * U位数量：U位数量
	*/
	@ApiModelProperty(required = false,value="U位数量" , notes = "U位数量")
	private Integer uPostionNumber;
	
	/**
	 * PDU数量：PDU数量
	*/
	@ApiModelProperty(required = false,value="PDU数量" , notes = "PDU数量")
	private Integer pduNumber;
	
	/**
	 * 跳线数：跳线数
	*/
	@ApiModelProperty(required = false,value="跳线数" , notes = "跳线数")
	private Integer jumperNumber;
	
	/**
	 * 合同电力：合同电力
	*/
	@ApiModelProperty(required = false,value="合同电力" , notes = "合同电力")
	private Integer contractPower;
	
	/**
	 * 设备数量：设备数量
	*/
	@ApiModelProperty(required = false,value="设备数量" , notes = "设备数量")
	private Integer equipmentNumber;
	
	/**
	 * 到期日期：到期日期
	*/
	@ApiModelProperty(required = false,value="到期日期" , notes = "到期日期")
	private Date expireDate;
	
	/**
	 * 标签1：标签1
	*/
	@ApiModelProperty(required = false,value="标签1" , notes = "标签1")
	private String rackLabel1;
	
	/**
	 * 标签2：标签2
	*/
	@ApiModelProperty(required = false,value="标签2" , notes = "标签2")
	private String rackLabel2;
	
	/**
	 * 备注：备注
	*/
	@ApiModelProperty(required = false,value="备注" , notes = "备注")
	private String rackNotes;
	
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
	 * 状态
	*/
	@ApiModelProperty(required = false,value="状态" , notes = "")
	private DictItem statusDict;
	
	/**
	 * 类型
	*/
	@ApiModelProperty(required = false,value="类型" , notes = "")
	private DictItem typeDict;
	
	/**
	 * 使用类型
	*/
	@ApiModelProperty(required = false,value="使用类型" , notes = "")
	private DictItem usedTypeDict;
	
	/**
	 * 环境
	*/
	@ApiModelProperty(required = false,value="环境" , notes = "")
	private DictItem environmentDict;
	
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
	public AssetRackInfo setId(String id) {
		this.id=id;
		return this;
	}
	
	/**
	 * 获得 编码<br>
	 * 编码
	 * @return 编码
	*/
	public String getRackCode() {
		return rackCode;
	}
	
	/**
	 * 设置 编码
	 * @param rackCode 编码
	 * @return 当前对象
	*/
	public AssetRackInfo setRackCode(String rackCode) {
		this.rackCode=rackCode;
		return this;
	}
	
	/**
	 * 获得 类型<br>
	 * 类型
	 * @return 类型
	*/
	public String getRackType() {
		return rackType;
	}
	
	/**
	 * 设置 类型
	 * @param rackType 类型
	 * @return 当前对象
	*/
	public AssetRackInfo setRackType(String rackType) {
		this.rackType=rackType;
		return this;
	}
	
	/**
	 * 获得 名称<br>
	 * 名称
	 * @return 名称
	*/
	public String getRackName() {
		return rackName;
	}
	
	/**
	 * 设置 名称
	 * @param rackName 名称
	 * @return 当前对象
	*/
	public AssetRackInfo setRackName(String rackName) {
		this.rackName=rackName;
		return this;
	}
	
	/**
	 * 获得 状态<br>
	 * 状态
	 * @return 状态
	*/
	public String getStatus() {
		return status;
	}
	
	/**
	 * 设置 状态
	 * @param status 状态
	 * @return 当前对象
	*/
	public AssetRackInfo setStatus(String status) {
		this.status=status;
		return this;
	}
	
	/**
	 * 获得 环境<br>
	 * 环境
	 * @return 环境
	*/
	public String getEnvironment() {
		return environment;
	}
	
	/**
	 * 设置 环境
	 * @param environment 环境
	 * @return 当前对象
	*/
	public AssetRackInfo setEnvironment(String environment) {
		this.environment=environment;
		return this;
	}
	
	/**
	 * 获得 使用分类<br>
	 * 使用分类
	 * @return 使用分类
	*/
	public String getRackUsedType() {
		return rackUsedType;
	}
	
	/**
	 * 设置 使用分类
	 * @param rackUsedType 使用分类
	 * @return 当前对象
	*/
	public AssetRackInfo setRackUsedType(String rackUsedType) {
		this.rackUsedType=rackUsedType;
		return this;
	}
	
	/**
	 * 获得 容量<br>
	 * 容量
	 * @return 容量
	*/
	public BigDecimal getRackCaptical() {
		return rackCaptical;
	}
	
	/**
	 * 设置 容量
	 * @param rackCaptical 容量
	 * @return 当前对象
	*/
	public AssetRackInfo setRackCaptical(BigDecimal rackCaptical) {
		this.rackCaptical=rackCaptical;
		return this;
	}
	
	/**
	 * 获得 U位数量<br>
	 * U位数量
	 * @return U位数量
	*/
	public Integer getUPostionNumber() {
		return uPostionNumber;
	}
	
	/**
	 * 设置 U位数量
	 * @param uPostionNumber U位数量
	 * @return 当前对象
	*/
	public AssetRackInfo setUPostionNumber(Integer uPostionNumber) {
		this.uPostionNumber=uPostionNumber;
		return this;
	}
	
	/**
	 * 获得 PDU数量<br>
	 * PDU数量
	 * @return PDU数量
	*/
	public Integer getPduNumber() {
		return pduNumber;
	}
	
	/**
	 * 设置 PDU数量
	 * @param pduNumber PDU数量
	 * @return 当前对象
	*/
	public AssetRackInfo setPduNumber(Integer pduNumber) {
		this.pduNumber=pduNumber;
		return this;
	}
	
	/**
	 * 获得 跳线数<br>
	 * 跳线数
	 * @return 跳线数
	*/
	public Integer getJumperNumber() {
		return jumperNumber;
	}
	
	/**
	 * 设置 跳线数
	 * @param jumperNumber 跳线数
	 * @return 当前对象
	*/
	public AssetRackInfo setJumperNumber(Integer jumperNumber) {
		this.jumperNumber=jumperNumber;
		return this;
	}
	
	/**
	 * 获得 合同电力<br>
	 * 合同电力
	 * @return 合同电力
	*/
	public Integer getContractPower() {
		return contractPower;
	}
	
	/**
	 * 设置 合同电力
	 * @param contractPower 合同电力
	 * @return 当前对象
	*/
	public AssetRackInfo setContractPower(Integer contractPower) {
		this.contractPower=contractPower;
		return this;
	}
	
	/**
	 * 获得 设备数量<br>
	 * 设备数量
	 * @return 设备数量
	*/
	public Integer getEquipmentNumber() {
		return equipmentNumber;
	}
	
	/**
	 * 设置 设备数量
	 * @param equipmentNumber 设备数量
	 * @return 当前对象
	*/
	public AssetRackInfo setEquipmentNumber(Integer equipmentNumber) {
		this.equipmentNumber=equipmentNumber;
		return this;
	}
	
	/**
	 * 获得 到期日期<br>
	 * 到期日期
	 * @return 到期日期
	*/
	public Date getExpireDate() {
		return expireDate;
	}
	
	/**
	 * 设置 到期日期
	 * @param expireDate 到期日期
	 * @return 当前对象
	*/
	public AssetRackInfo setExpireDate(Date expireDate) {
		this.expireDate=expireDate;
		return this;
	}
	
	/**
	 * 获得 标签1<br>
	 * 标签1
	 * @return 标签1
	*/
	public String getRackLabel1() {
		return rackLabel1;
	}
	
	/**
	 * 设置 标签1
	 * @param rackLabel1 标签1
	 * @return 当前对象
	*/
	public AssetRackInfo setRackLabel1(String rackLabel1) {
		this.rackLabel1=rackLabel1;
		return this;
	}
	
	/**
	 * 获得 标签2<br>
	 * 标签2
	 * @return 标签2
	*/
	public String getRackLabel2() {
		return rackLabel2;
	}
	
	/**
	 * 设置 标签2
	 * @param rackLabel2 标签2
	 * @return 当前对象
	*/
	public AssetRackInfo setRackLabel2(String rackLabel2) {
		this.rackLabel2=rackLabel2;
		return this;
	}
	
	/**
	 * 获得 备注<br>
	 * 备注
	 * @return 备注
	*/
	public String getRackNotes() {
		return rackNotes;
	}
	
	/**
	 * 设置 备注
	 * @param rackNotes 备注
	 * @return 当前对象
	*/
	public AssetRackInfo setRackNotes(String rackNotes) {
		this.rackNotes=rackNotes;
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
	public AssetRackInfo setCreateBy(String createBy) {
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
	public AssetRackInfo setCreateTime(Date createTime) {
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
	public AssetRackInfo setUpdateBy(String updateBy) {
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
	public AssetRackInfo setUpdateTime(Date updateTime) {
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
	public AssetRackInfo setDeleted(Integer deleted) {
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
	public AssetRackInfo setDeleted(Boolean deletedBool) {
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
	public AssetRackInfo setDeleteBy(String deleteBy) {
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
	public AssetRackInfo setDeleteTime(Date deleteTime) {
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
	public AssetRackInfo setVersion(Integer version) {
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
	public AssetRackInfo setTenantId(String tenantId) {
		this.tenantId=tenantId;
		return this;
	}
	
	/**
	 * 获得 状态<br>
	 * @return 状态
	*/
	public DictItem getStatusDict() {
		return statusDict;
	}
	
	/**
	 * 设置 状态
	 * @param statusDict 状态
	 * @return 当前对象
	*/
	public AssetRackInfo setStatusDict(DictItem statusDict) {
		this.statusDict=statusDict;
		return this;
	}
	
	/**
	 * 获得 类型<br>
	 * @return 类型
	*/
	public DictItem getTypeDict() {
		return typeDict;
	}
	
	/**
	 * 设置 类型
	 * @param typeDict 类型
	 * @return 当前对象
	*/
	public AssetRackInfo setTypeDict(DictItem typeDict) {
		this.typeDict=typeDict;
		return this;
	}
	
	/**
	 * 获得 使用类型<br>
	 * @return 使用类型
	*/
	public DictItem getUsedTypeDict() {
		return usedTypeDict;
	}
	
	/**
	 * 设置 使用类型
	 * @param usedTypeDict 使用类型
	 * @return 当前对象
	*/
	public AssetRackInfo setUsedTypeDict(DictItem usedTypeDict) {
		this.usedTypeDict=usedTypeDict;
		return this;
	}
	
	/**
	 * 获得 环境<br>
	 * @return 环境
	*/
	public DictItem getEnvironmentDict() {
		return environmentDict;
	}
	
	/**
	 * 设置 环境
	 * @param environmentDict 环境
	 * @return 当前对象
	*/
	public AssetRackInfo setEnvironmentDict(DictItem environmentDict) {
		this.environmentDict=environmentDict;
		return this;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return AssetRackInfo , 转换好的 AssetRackInfo 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return AssetRackInfo , 转换好的 PoJo 对象
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
	public AssetRackInfo clone() {
		return EntityContext.clone(AssetRackInfo.class,this);
	}

	/**
	 * 克隆当前对象
	*/
	@Transient
	public AssetRackInfo clone(boolean deep) {
		return EntityContext.clone(AssetRackInfo.class,this,deep);
	}

	/**
	 * 将 Map 转换成 AssetRackInfo
	 * @param assetRackInfoMap 包含实体信息的 Map 对象
	 * @return AssetRackInfo , 转换好的的 AssetRackInfo 对象
	*/
	@Transient
	public static AssetRackInfo createFrom(Map<String,Object> assetRackInfoMap) {
		if(assetRackInfoMap==null) return null;
		AssetRackInfo po = EntityContext.create(AssetRackInfo.class, assetRackInfoMap);
		return po;
	}

	/**
	 * 将 Pojo 转换成 AssetRackInfo
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return AssetRackInfo , 转换好的的 AssetRackInfo 对象
	*/
	@Transient
	public static AssetRackInfo createFrom(Object pojo) {
		if(pojo==null) return null;
		AssetRackInfo po = EntityContext.create(AssetRackInfo.class,pojo);
		return po;
	}

	/**
	 * 创建一个 AssetRackInfo，等同于 new
	 * @return AssetRackInfo 对象
	*/
	@Transient
	public static AssetRackInfo create() {
		return EntityContext.create(AssetRackInfo.class);
	}
}