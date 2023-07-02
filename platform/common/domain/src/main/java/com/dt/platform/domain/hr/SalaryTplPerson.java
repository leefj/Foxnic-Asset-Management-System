package com.dt.platform.domain.hr;

import com.github.foxnic.dao.entity.Entity;
import io.swagger.annotations.ApiModel;
import javax.persistence.Table;
import com.github.foxnic.sql.meta.DBTable;
import com.dt.platform.constants.db.HrTables.HR_SALARY_TPL_PERSON;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Transient;
import com.github.foxnic.api.swagger.EnumFor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.foxnic.commons.lang.DataParser;
import java.util.Map;
import com.github.foxnic.dao.entity.EntityContext;
import com.dt.platform.domain.hr.meta.SalaryTplPersonMeta;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 薪酬人员
 * <p>薪酬人员 , 数据表 hr_salary_tpl_person 的PO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-06-03 23:36:04
 * @sign 6F4B2E5CCAFFA7DC5EC871B8F5FC61F9
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@Table(name = "hr_salary_tpl_person")
@ApiModel(description = "薪酬人员 ; 薪酬人员 , 数据表 hr_salary_tpl_person 的PO类型")
public class SalaryTplPerson extends Entity {

	private static final long serialVersionUID = 1L;

	public static final DBTable TABLE =HR_SALARY_TPL_PERSON.$TABLE;
	
	/**
	 * 主键：主键
	*/
	@Id
	@ApiModelProperty(required = true,value="主键" , notes = "主键")
	private String id;
	
	/**
	 * 模版：模版
	*/
	@ApiModelProperty(required = false,value="模版" , notes = "模版")
	private String tplId;
	
	/**
	 * 人员：人员
	*/
	@ApiModelProperty(required = false,value="人员" , notes = "人员")
	private String personId;
	
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
	 * 租户：租户
	*/
	@ApiModelProperty(required = false,value="租户" , notes = "租户")
	private String tenantId;
	
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
	public SalaryTplPerson setId(String id) {
		this.id=id;
		return this;
	}
	
	/**
	 * 获得 模版<br>
	 * 模版
	 * @return 模版
	*/
	public String getTplId() {
		return tplId;
	}
	
	/**
	 * 设置 模版
	 * @param tplId 模版
	 * @return 当前对象
	*/
	public SalaryTplPerson setTplId(String tplId) {
		this.tplId=tplId;
		return this;
	}
	
	/**
	 * 获得 人员<br>
	 * 人员
	 * @return 人员
	*/
	public String getPersonId() {
		return personId;
	}
	
	/**
	 * 设置 人员
	 * @param personId 人员
	 * @return 当前对象
	*/
	public SalaryTplPerson setPersonId(String personId) {
		this.personId=personId;
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
	public SalaryTplPerson setNotes(String notes) {
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
	public SalaryTplPerson setCreateBy(String createBy) {
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
	public SalaryTplPerson setCreateTime(Date createTime) {
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
	public SalaryTplPerson setUpdateBy(String updateBy) {
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
	public SalaryTplPerson setUpdateTime(Date updateTime) {
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
	public SalaryTplPerson setDeleted(Integer deleted) {
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
	public SalaryTplPerson setDeleted(Boolean deletedBool) {
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
	public SalaryTplPerson setDeleteBy(String deleteBy) {
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
	public SalaryTplPerson setDeleteTime(Date deleteTime) {
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
	public SalaryTplPerson setVersion(Integer version) {
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
	public SalaryTplPerson setTenantId(String tenantId) {
		this.tenantId=tenantId;
		return this;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return SalaryTplPerson , 转换好的 SalaryTplPerson 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return SalaryTplPerson , 转换好的 PoJo 对象
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
	public SalaryTplPerson clone() {
		return duplicate(true);
	}

	/**
	 * 复制当前对象
	 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
	*/
	@Transient
	public SalaryTplPerson duplicate(boolean all) {
		com.dt.platform.domain.hr.meta.SalaryTplPersonMeta.$$proxy$$ inst = new com.dt.platform.domain.hr.meta.SalaryTplPersonMeta.$$proxy$$();
		inst.setNotes(this.getNotes());
		inst.setUpdateTime(this.getUpdateTime());
		inst.setVersion(this.getVersion());
		inst.setCreateBy(this.getCreateBy());
		inst.setDeleted(this.getDeleted());
		inst.setCreateTime(this.getCreateTime());
		inst.setUpdateBy(this.getUpdateBy());
		inst.setDeleteTime(this.getDeleteTime());
		inst.setTenantId(this.getTenantId());
		inst.setDeleteBy(this.getDeleteBy());
		inst.setPersonId(this.getPersonId());
		inst.setId(this.getId());
		inst.setTplId(this.getTplId());
		inst.clearModifies();
		return inst;
	}

	/**
	 * 克隆当前对象
	*/
	@Transient
	public SalaryTplPerson clone(boolean deep) {
		return EntityContext.clone(SalaryTplPerson.class,this,deep);
	}

	/**
	 * 将 Map 转换成 SalaryTplPerson
	 * @param salaryTplPersonMap 包含实体信息的 Map 对象
	 * @return SalaryTplPerson , 转换好的的 SalaryTplPerson 对象
	*/
	@Transient
	public static SalaryTplPerson createFrom(Map<String,Object> salaryTplPersonMap) {
		if(salaryTplPersonMap==null) return null;
		SalaryTplPerson po = create();
		EntityContext.copyProperties(po,salaryTplPersonMap);
		po.clearModifies();
		return po;
	}

	/**
	 * 将 Pojo 转换成 SalaryTplPerson
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return SalaryTplPerson , 转换好的的 SalaryTplPerson 对象
	*/
	@Transient
	public static SalaryTplPerson createFrom(Object pojo) {
		if(pojo==null) return null;
		SalaryTplPerson po = create();
		EntityContext.copyProperties(po,pojo);
		po.clearModifies();
		return po;
	}

	/**
	 * 创建一个 SalaryTplPerson，等同于 new
	 * @return SalaryTplPerson 对象
	*/
	@Transient
	public static SalaryTplPerson create() {
		return new com.dt.platform.domain.hr.meta.SalaryTplPersonMeta.$$proxy$$();
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
			this.setNotes(DataParser.parse(String.class, map.get(SalaryTplPersonMeta.NOTES)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(SalaryTplPersonMeta.UPDATE_TIME)));
			this.setVersion(DataParser.parse(Integer.class, map.get(SalaryTplPersonMeta.VERSION)));
			this.setCreateBy(DataParser.parse(String.class, map.get(SalaryTplPersonMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(SalaryTplPersonMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(SalaryTplPersonMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(SalaryTplPersonMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(SalaryTplPersonMeta.DELETE_TIME)));
			this.setTenantId(DataParser.parse(String.class, map.get(SalaryTplPersonMeta.TENANT_ID)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(SalaryTplPersonMeta.DELETE_BY)));
			this.setPersonId(DataParser.parse(String.class, map.get(SalaryTplPersonMeta.PERSON_ID)));
			this.setId(DataParser.parse(String.class, map.get(SalaryTplPersonMeta.ID)));
			this.setTplId(DataParser.parse(String.class, map.get(SalaryTplPersonMeta.TPL_ID)));
			// others
			return true;
		} else {
			try {
				this.setNotes( (String)map.get(SalaryTplPersonMeta.NOTES));
				this.setUpdateTime( (Date)map.get(SalaryTplPersonMeta.UPDATE_TIME));
				this.setVersion( (Integer)map.get(SalaryTplPersonMeta.VERSION));
				this.setCreateBy( (String)map.get(SalaryTplPersonMeta.CREATE_BY));
				this.setDeleted( (Integer)map.get(SalaryTplPersonMeta.DELETED));
				this.setCreateTime( (Date)map.get(SalaryTplPersonMeta.CREATE_TIME));
				this.setUpdateBy( (String)map.get(SalaryTplPersonMeta.UPDATE_BY));
				this.setDeleteTime( (Date)map.get(SalaryTplPersonMeta.DELETE_TIME));
				this.setTenantId( (String)map.get(SalaryTplPersonMeta.TENANT_ID));
				this.setDeleteBy( (String)map.get(SalaryTplPersonMeta.DELETE_BY));
				this.setPersonId( (String)map.get(SalaryTplPersonMeta.PERSON_ID));
				this.setId( (String)map.get(SalaryTplPersonMeta.ID));
				this.setTplId( (String)map.get(SalaryTplPersonMeta.TPL_ID));
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
			this.setNotes(DataParser.parse(String.class, r.getValue(SalaryTplPersonMeta.NOTES)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(SalaryTplPersonMeta.UPDATE_TIME)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(SalaryTplPersonMeta.VERSION)));
			this.setCreateBy(DataParser.parse(String.class, r.getValue(SalaryTplPersonMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(SalaryTplPersonMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(SalaryTplPersonMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(SalaryTplPersonMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(SalaryTplPersonMeta.DELETE_TIME)));
			this.setTenantId(DataParser.parse(String.class, r.getValue(SalaryTplPersonMeta.TENANT_ID)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(SalaryTplPersonMeta.DELETE_BY)));
			this.setPersonId(DataParser.parse(String.class, r.getValue(SalaryTplPersonMeta.PERSON_ID)));
			this.setId(DataParser.parse(String.class, r.getValue(SalaryTplPersonMeta.ID)));
			this.setTplId(DataParser.parse(String.class, r.getValue(SalaryTplPersonMeta.TPL_ID)));
			return true;
		} else {
			try {
				this.setNotes( (String)r.getValue(SalaryTplPersonMeta.NOTES));
				this.setUpdateTime( (Date)r.getValue(SalaryTplPersonMeta.UPDATE_TIME));
				this.setVersion( (Integer)r.getValue(SalaryTplPersonMeta.VERSION));
				this.setCreateBy( (String)r.getValue(SalaryTplPersonMeta.CREATE_BY));
				this.setDeleted( (Integer)r.getValue(SalaryTplPersonMeta.DELETED));
				this.setCreateTime( (Date)r.getValue(SalaryTplPersonMeta.CREATE_TIME));
				this.setUpdateBy( (String)r.getValue(SalaryTplPersonMeta.UPDATE_BY));
				this.setDeleteTime( (Date)r.getValue(SalaryTplPersonMeta.DELETE_TIME));
				this.setTenantId( (String)r.getValue(SalaryTplPersonMeta.TENANT_ID));
				this.setDeleteBy( (String)r.getValue(SalaryTplPersonMeta.DELETE_BY));
				this.setPersonId( (String)r.getValue(SalaryTplPersonMeta.PERSON_ID));
				this.setId( (String)r.getValue(SalaryTplPersonMeta.ID));
				this.setTplId( (String)r.getValue(SalaryTplPersonMeta.TPL_ID));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}