package com.dt.platform.domain.eam;

import com.github.foxnic.dao.entity.Entity;
import io.swagger.annotations.ApiModel;
import javax.persistence.Table;
import com.github.foxnic.sql.meta.DBTable;
import com.dt.platform.constants.db.EAMTables.EAM_C_CUST_REPAIR_TYPE;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Transient;
import com.github.foxnic.api.swagger.EnumFor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.foxnic.commons.lang.DataParser;
import java.util.Map;
import com.github.foxnic.dao.entity.EntityContext;
import com.dt.platform.domain.eam.meta.CCustRepairTypeMeta;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 报修类型
 * <p>报修类型 , 数据表 eam_c_cust_repair_type 的PO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-05-14 08:57:13
 * @sign A3102DE10EA87F61B0AB0A75EDCF1F7D
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@Table(name = "eam_c_cust_repair_type")
@ApiModel(description = "报修类型 ; 报修类型 , 数据表 eam_c_cust_repair_type 的PO类型")
public class CCustRepairType extends Entity {

	private static final long serialVersionUID = 1L;

	public static final DBTable TABLE =EAM_C_CUST_REPAIR_TYPE.$TABLE;
	
	/**
	 * 主键：主键
	*/
	@Id
	@ApiModelProperty(required = true,value="主键" , notes = "主键")
	private String id;
	
	/**
	 * 名称：名称
	*/
	@ApiModelProperty(required = false,value="名称" , notes = "名称")
	private String name;
	
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
	@ApiModelProperty(required = true,value="version" , notes = "version")
	private Integer version;
	
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
	public CCustRepairType setId(String id) {
		this.id=id;
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
	public CCustRepairType setName(String name) {
		this.name=name;
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
	public CCustRepairType setNotes(String notes) {
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
	public CCustRepairType setCreateBy(String createBy) {
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
	public CCustRepairType setCreateTime(Date createTime) {
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
	public CCustRepairType setUpdateBy(String updateBy) {
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
	public CCustRepairType setUpdateTime(Date updateTime) {
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
	public CCustRepairType setDeleted(Integer deleted) {
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
	public CCustRepairType setDeleted(Boolean deletedBool) {
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
	public CCustRepairType setDeleteBy(String deleteBy) {
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
	public CCustRepairType setDeleteTime(Date deleteTime) {
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
	public CCustRepairType setVersion(Integer version) {
		this.version=version;
		return this;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return CCustRepairType , 转换好的 CCustRepairType 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return CCustRepairType , 转换好的 PoJo 对象
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
	public CCustRepairType clone() {
		return duplicate(true);
	}

	/**
	 * 复制当前对象
	 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
	*/
	@Transient
	public CCustRepairType duplicate(boolean all) {
		com.dt.platform.domain.eam.meta.CCustRepairTypeMeta.$$proxy$$ inst = new com.dt.platform.domain.eam.meta.CCustRepairTypeMeta.$$proxy$$();
		inst.setCreateBy(this.getCreateBy());
		inst.setNotes(this.getNotes());
		inst.setDeleted(this.getDeleted());
		inst.setCreateTime(this.getCreateTime());
		inst.setUpdateBy(this.getUpdateBy());
		inst.setDeleteTime(this.getDeleteTime());
		inst.setName(this.getName());
		inst.setDeleteBy(this.getDeleteBy());
		inst.setUpdateTime(this.getUpdateTime());
		inst.setId(this.getId());
		inst.setVersion(this.getVersion());
		inst.clearModifies();
		return inst;
	}

	/**
	 * 克隆当前对象
	*/
	@Transient
	public CCustRepairType clone(boolean deep) {
		return EntityContext.clone(CCustRepairType.class,this,deep);
	}

	/**
	 * 将 Map 转换成 CCustRepairType
	 * @param cCustRepairTypeMap 包含实体信息的 Map 对象
	 * @return CCustRepairType , 转换好的的 CCustRepairType 对象
	*/
	@Transient
	public static CCustRepairType createFrom(Map<String,Object> cCustRepairTypeMap) {
		if(cCustRepairTypeMap==null) return null;
		CCustRepairType po = create();
		EntityContext.copyProperties(po,cCustRepairTypeMap);
		po.clearModifies();
		return po;
	}

	/**
	 * 将 Pojo 转换成 CCustRepairType
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return CCustRepairType , 转换好的的 CCustRepairType 对象
	*/
	@Transient
	public static CCustRepairType createFrom(Object pojo) {
		if(pojo==null) return null;
		CCustRepairType po = create();
		EntityContext.copyProperties(po,pojo);
		po.clearModifies();
		return po;
	}

	/**
	 * 创建一个 CCustRepairType，等同于 new
	 * @return CCustRepairType 对象
	*/
	@Transient
	public static CCustRepairType create() {
		return new com.dt.platform.domain.eam.meta.CCustRepairTypeMeta.$$proxy$$();
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
			this.setCreateBy(DataParser.parse(String.class, map.get(CCustRepairTypeMeta.CREATE_BY)));
			this.setNotes(DataParser.parse(String.class, map.get(CCustRepairTypeMeta.NOTES)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(CCustRepairTypeMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(CCustRepairTypeMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(CCustRepairTypeMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(CCustRepairTypeMeta.DELETE_TIME)));
			this.setName(DataParser.parse(String.class, map.get(CCustRepairTypeMeta.NAME)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(CCustRepairTypeMeta.DELETE_BY)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(CCustRepairTypeMeta.UPDATE_TIME)));
			this.setId(DataParser.parse(String.class, map.get(CCustRepairTypeMeta.ID)));
			this.setVersion(DataParser.parse(Integer.class, map.get(CCustRepairTypeMeta.VERSION)));
			// others
			return true;
		} else {
			try {
				this.setCreateBy( (String)map.get(CCustRepairTypeMeta.CREATE_BY));
				this.setNotes( (String)map.get(CCustRepairTypeMeta.NOTES));
				this.setDeleted( (Integer)map.get(CCustRepairTypeMeta.DELETED));
				this.setCreateTime( (Date)map.get(CCustRepairTypeMeta.CREATE_TIME));
				this.setUpdateBy( (String)map.get(CCustRepairTypeMeta.UPDATE_BY));
				this.setDeleteTime( (Date)map.get(CCustRepairTypeMeta.DELETE_TIME));
				this.setName( (String)map.get(CCustRepairTypeMeta.NAME));
				this.setDeleteBy( (String)map.get(CCustRepairTypeMeta.DELETE_BY));
				this.setUpdateTime( (Date)map.get(CCustRepairTypeMeta.UPDATE_TIME));
				this.setId( (String)map.get(CCustRepairTypeMeta.ID));
				this.setVersion( (Integer)map.get(CCustRepairTypeMeta.VERSION));
				// others
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
			this.setCreateBy(DataParser.parse(String.class, r.getValue(CCustRepairTypeMeta.CREATE_BY)));
			this.setNotes(DataParser.parse(String.class, r.getValue(CCustRepairTypeMeta.NOTES)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(CCustRepairTypeMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(CCustRepairTypeMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(CCustRepairTypeMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(CCustRepairTypeMeta.DELETE_TIME)));
			this.setName(DataParser.parse(String.class, r.getValue(CCustRepairTypeMeta.NAME)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(CCustRepairTypeMeta.DELETE_BY)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(CCustRepairTypeMeta.UPDATE_TIME)));
			this.setId(DataParser.parse(String.class, r.getValue(CCustRepairTypeMeta.ID)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(CCustRepairTypeMeta.VERSION)));
			return true;
		} else {
			try {
				this.setCreateBy( (String)r.getValue(CCustRepairTypeMeta.CREATE_BY));
				this.setNotes( (String)r.getValue(CCustRepairTypeMeta.NOTES));
				this.setDeleted( (Integer)r.getValue(CCustRepairTypeMeta.DELETED));
				this.setCreateTime( (Date)r.getValue(CCustRepairTypeMeta.CREATE_TIME));
				this.setUpdateBy( (String)r.getValue(CCustRepairTypeMeta.UPDATE_BY));
				this.setDeleteTime( (Date)r.getValue(CCustRepairTypeMeta.DELETE_TIME));
				this.setName( (String)r.getValue(CCustRepairTypeMeta.NAME));
				this.setDeleteBy( (String)r.getValue(CCustRepairTypeMeta.DELETE_BY));
				this.setUpdateTime( (Date)r.getValue(CCustRepairTypeMeta.UPDATE_TIME));
				this.setId( (String)r.getValue(CCustRepairTypeMeta.ID));
				this.setVersion( (Integer)r.getValue(CCustRepairTypeMeta.VERSION));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}