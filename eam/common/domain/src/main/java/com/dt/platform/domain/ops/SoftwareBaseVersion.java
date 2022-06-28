package com.dt.platform.domain.ops;

import com.github.foxnic.dao.entity.Entity;
import javax.persistence.Table;
import com.github.foxnic.sql.meta.DBTable;
import com.dt.platform.constants.db.OpsTables.OPS_SOFTWARE_BASE_VERSION;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Transient;
import java.util.List;
import com.github.foxnic.commons.lang.DataParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import com.github.foxnic.dao.entity.EntityContext;



/**
 * 软件基线版本
 * @author 金杰 , maillank@qq.com
 * @since 2022-06-27 20:16:21
 * @sign D30A23C000A34EE3602A784FFF527907
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@Table(name = "ops_software_base_version")
public class SoftwareBaseVersion extends Entity {

	private static final long serialVersionUID = 1L;

	public static final DBTable TABLE =OPS_SOFTWARE_BASE_VERSION.$TABLE;
	
	/**
	 * 主键：主键
	*/
	@Id
	@ApiModelProperty(required = true,value="主键" , notes = "主键")
	private String id;
	
	/**
	 * 类型：类型
	*/
	@ApiModelProperty(required = false,value="类型" , notes = "类型")
	private String softwareTypeId;
	
	/**
	 * 名称：名称
	*/
	@ApiModelProperty(required = false,value="名称" , notes = "名称")
	private String name;
	
	/**
	 * 版本：版本
	*/
	@ApiModelProperty(required = false,value="版本" , notes = "版本")
	private String softwareVersion;
	
	/**
	 * 补丁：补丁
	*/
	@ApiModelProperty(required = false,value="补丁" , notes = "补丁")
	private String patchInfo;
	
	/**
	 * 状态：状态
	*/
	@ApiModelProperty(required = false,value="状态" , notes = "状态")
	private String status;
	
	/**
	 * 使用场景：使用场景
	*/
	@ApiModelProperty(required = false,value="使用场景" , notes = "使用场景")
	private String usageScenarios;
	
	/**
	 * 软件：软件
	*/
	@ApiModelProperty(required = false,value="软件" , notes = "软件")
	private String fileId;
	
	/**
	 * 备注：备注
	*/
	@ApiModelProperty(required = false,value="备注" , notes = "备注")
	private String notes;
	
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
	 * softwareBaseType：softwareBaseType
	*/
	@ApiModelProperty(required = false,value="softwareBaseType" , notes = "softwareBaseType")
	private List<SoftwareBaseType> softwareBaseType;
	
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
	public SoftwareBaseVersion setId(String id) {
		this.id=id;
		return this;
	}
	
	/**
	 * 获得 类型<br>
	 * 类型
	 * @return 类型
	*/
	public String getSoftwareTypeId() {
		return softwareTypeId;
	}
	
	/**
	 * 设置 类型
	 * @param softwareTypeId 类型
	 * @return 当前对象
	*/
	public SoftwareBaseVersion setSoftwareTypeId(String softwareTypeId) {
		this.softwareTypeId=softwareTypeId;
		return this;
	}
	
	/**
	 * 获得 名称<br>
	 * 名称
	 * @return 名称
	*/
	public String getName() {
		return name;
	}
	
	/**
	 * 设置 名称
	 * @param name 名称
	 * @return 当前对象
	*/
	public SoftwareBaseVersion setName(String name) {
		this.name=name;
		return this;
	}
	
	/**
	 * 获得 版本<br>
	 * 版本
	 * @return 版本
	*/
	public String getSoftwareVersion() {
		return softwareVersion;
	}
	
	/**
	 * 设置 版本
	 * @param softwareVersion 版本
	 * @return 当前对象
	*/
	public SoftwareBaseVersion setSoftwareVersion(String softwareVersion) {
		this.softwareVersion=softwareVersion;
		return this;
	}
	
	/**
	 * 获得 补丁<br>
	 * 补丁
	 * @return 补丁
	*/
	public String getPatchInfo() {
		return patchInfo;
	}
	
	/**
	 * 设置 补丁
	 * @param patchInfo 补丁
	 * @return 当前对象
	*/
	public SoftwareBaseVersion setPatchInfo(String patchInfo) {
		this.patchInfo=patchInfo;
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
	public SoftwareBaseVersion setStatus(String status) {
		this.status=status;
		return this;
	}
	
	/**
	 * 获得 使用场景<br>
	 * 使用场景
	 * @return 使用场景
	*/
	public String getUsageScenarios() {
		return usageScenarios;
	}
	
	/**
	 * 设置 使用场景
	 * @param usageScenarios 使用场景
	 * @return 当前对象
	*/
	public SoftwareBaseVersion setUsageScenarios(String usageScenarios) {
		this.usageScenarios=usageScenarios;
		return this;
	}
	
	/**
	 * 获得 软件<br>
	 * 软件
	 * @return 软件
	*/
	public String getFileId() {
		return fileId;
	}
	
	/**
	 * 设置 软件
	 * @param fileId 软件
	 * @return 当前对象
	*/
	public SoftwareBaseVersion setFileId(String fileId) {
		this.fileId=fileId;
		return this;
	}
	
	/**
	 * 获得 备注<br>
	 * 备注
	 * @return 备注
	*/
	public String getNotes() {
		return notes;
	}
	
	/**
	 * 设置 备注
	 * @param notes 备注
	 * @return 当前对象
	*/
	public SoftwareBaseVersion setNotes(String notes) {
		this.notes=notes;
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
	public SoftwareBaseVersion setCreateBy(String createBy) {
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
	public SoftwareBaseVersion setCreateTime(Date createTime) {
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
	public SoftwareBaseVersion setUpdateBy(String updateBy) {
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
	public SoftwareBaseVersion setUpdateTime(Date updateTime) {
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
	public SoftwareBaseVersion setDeleted(Integer deleted) {
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
	public SoftwareBaseVersion setDeleted(Boolean deletedBool) {
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
	public SoftwareBaseVersion setDeleteBy(String deleteBy) {
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
	public SoftwareBaseVersion setDeleteTime(Date deleteTime) {
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
	public SoftwareBaseVersion setVersion(Integer version) {
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
	public SoftwareBaseVersion setTenantId(String tenantId) {
		this.tenantId=tenantId;
		return this;
	}
	
	/**
	 * 获得 softwareBaseType<br>
	 * softwareBaseType
	 * @return softwareBaseType
	*/
	public List<SoftwareBaseType> getSoftwareBaseType() {
		return softwareBaseType;
	}
	
	/**
	 * 设置 softwareBaseType
	 * @param softwareBaseType softwareBaseType
	 * @return 当前对象
	*/
	public SoftwareBaseVersion setSoftwareBaseType(List<SoftwareBaseType> softwareBaseType) {
		this.softwareBaseType=softwareBaseType;
		return this;
	}
	
	/**
	 * 添加 softwareBaseType
	 * @param entity softwareBaseType
	 * @return 当前对象
	*/
	public SoftwareBaseVersion addSoftwareBaseType(SoftwareBaseType... entity) {
		if(this.softwareBaseType==null) softwareBaseType=new ArrayList<>();
		this.softwareBaseType.addAll(Arrays.asList(entity));
		return this;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return SoftwareBaseVersion , 转换好的 SoftwareBaseVersion 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return SoftwareBaseVersion , 转换好的 PoJo 对象
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
	 * 将 Map 转换成 SoftwareBaseVersion
	 * @param softwareBaseVersionMap 包含实体信息的 Map 对象
	 * @return SoftwareBaseVersion , 转换好的的 SoftwareBaseVersion 对象
	*/
	@Transient
	public static SoftwareBaseVersion createFrom(Map<String,Object> softwareBaseVersionMap) {
		if(softwareBaseVersionMap==null) return null;
		SoftwareBaseVersion po = EntityContext.create(SoftwareBaseVersion.class, softwareBaseVersionMap);
		return po;
	}

	/**
	 * 将 Pojo 转换成 SoftwareBaseVersion
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return SoftwareBaseVersion , 转换好的的 SoftwareBaseVersion 对象
	*/
	@Transient
	public static SoftwareBaseVersion createFrom(Object pojo) {
		if(pojo==null) return null;
		SoftwareBaseVersion po = EntityContext.create(SoftwareBaseVersion.class,pojo);
		return po;
	}

	/**
	 * 创建一个 SoftwareBaseVersion，等同于 new
	 * @return SoftwareBaseVersion 对象
	*/
	@Transient
	public static SoftwareBaseVersion create() {
		return EntityContext.create(SoftwareBaseVersion.class);
	}
}