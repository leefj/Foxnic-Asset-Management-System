package com.dt.platform.domain.hr;

import com.github.foxnic.dao.entity.Entity;
import io.swagger.annotations.ApiModel;
import javax.persistence.Table;
import com.github.foxnic.sql.meta.DBTable;
import com.dt.platform.constants.db.HrTables.HR_ATTENDANCE_RECORD;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Transient;
import com.github.foxnic.api.swagger.EnumFor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.foxnic.commons.lang.DataParser;
import java.util.Map;
import com.github.foxnic.dao.entity.EntityContext;
import com.dt.platform.domain.hr.meta.AttendanceRecordMeta;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 考勤记录
 * <p>考勤记录 , 数据表 hr_attendance_record 的PO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:24:00
 * @sign 2CA37B867AC5DBAA72F8A8C5C8B365A6
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@Table(name = "hr_attendance_record")
@ApiModel(description = "考勤记录 ; 考勤记录 , 数据表 hr_attendance_record 的PO类型")
public class AttendanceRecord extends Entity {

	private static final long serialVersionUID = 1L;

	public static final DBTable TABLE =HR_ATTENDANCE_RECORD.$TABLE;
	
	/**
	 * 主键：主键
	*/
	@Id
	@ApiModelProperty(required = true,value="主键" , notes = "主键")
	private String id;
	
	/**
	 * 人员：人员
	*/
	@ApiModelProperty(required = false,value="人员" , notes = "人员")
	private String employeeId;
	
	/**
	 * 姓名：姓名
	*/
	@ApiModelProperty(required = false,value="姓名" , notes = "姓名")
	private String employeeName;
	
	/**
	 * 工号：工号
	*/
	@ApiModelProperty(required = false,value="工号" , notes = "工号")
	private String employeeNumber;
	
	/**
	 * 记录时间：记录时间
	*/
	@ApiModelProperty(required = false,value="记录时间" , notes = "记录时间")
	private Date rcdTime;
	
	/**
	 * 来源：来源
	*/
	@ApiModelProperty(required = false,value="来源" , notes = "来源")
	private String source;
	
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
	 * 数据版本号：数据版本号
	*/
	@ApiModelProperty(required = true,value="数据版本号" , notes = "数据版本号")
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
	public AttendanceRecord setId(String id) {
		this.id=id;
		return this;
	}
	
	/**
	 * 获得 人员<br>
	 * 人员
	 * @return 人员
	*/
	public String getEmployeeId() {
		return employeeId;
	}
	
	/**
	 * 设置 人员
	 * @param employeeId 人员
	 * @return 当前对象
	*/
	public AttendanceRecord setEmployeeId(String employeeId) {
		this.employeeId=employeeId;
		return this;
	}
	
	/**
	 * 获得 姓名<br>
	 * 姓名
	 * @return 姓名
	*/
	public String getEmployeeName() {
		return employeeName;
	}
	
	/**
	 * 设置 姓名
	 * @param employeeName 姓名
	 * @return 当前对象
	*/
	public AttendanceRecord setEmployeeName(String employeeName) {
		this.employeeName=employeeName;
		return this;
	}
	
	/**
	 * 获得 工号<br>
	 * 工号
	 * @return 工号
	*/
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	
	/**
	 * 设置 工号
	 * @param employeeNumber 工号
	 * @return 当前对象
	*/
	public AttendanceRecord setEmployeeNumber(String employeeNumber) {
		this.employeeNumber=employeeNumber;
		return this;
	}
	
	/**
	 * 获得 记录时间<br>
	 * 记录时间
	 * @return 记录时间
	*/
	public Date getRcdTime() {
		return rcdTime;
	}
	
	/**
	 * 设置 记录时间
	 * @param rcdTime 记录时间
	 * @return 当前对象
	*/
	public AttendanceRecord setRcdTime(Date rcdTime) {
		this.rcdTime=rcdTime;
		return this;
	}
	
	/**
	 * 获得 来源<br>
	 * 来源
	 * @return 来源
	*/
	public String getSource() {
		return source;
	}
	
	/**
	 * 设置 来源
	 * @param source 来源
	 * @return 当前对象
	*/
	public AttendanceRecord setSource(String source) {
		this.source=source;
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
	public AttendanceRecord setNotes(String notes) {
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
	public AttendanceRecord setCreateBy(String createBy) {
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
	public AttendanceRecord setCreateTime(Date createTime) {
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
	public AttendanceRecord setUpdateBy(String updateBy) {
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
	public AttendanceRecord setUpdateTime(Date updateTime) {
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
	public AttendanceRecord setDeleted(Integer deleted) {
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
	public AttendanceRecord setDeleted(Boolean deletedBool) {
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
	public AttendanceRecord setDeleteBy(String deleteBy) {
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
	public AttendanceRecord setDeleteTime(Date deleteTime) {
		this.deleteTime=deleteTime;
		return this;
	}
	
	/**
	 * 获得 数据版本号<br>
	 * 数据版本号
	 * @return 数据版本号
	*/
	public Integer getVersion() {
		return version;
	}
	
	/**
	 * 设置 数据版本号
	 * @param version 数据版本号
	 * @return 当前对象
	*/
	public AttendanceRecord setVersion(Integer version) {
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
	public AttendanceRecord setTenantId(String tenantId) {
		this.tenantId=tenantId;
		return this;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return AttendanceRecord , 转换好的 AttendanceRecord 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return AttendanceRecord , 转换好的 PoJo 对象
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
	public AttendanceRecord clone() {
		return duplicate(true);
	}

	/**
	 * 复制当前对象
	 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
	*/
	@Transient
	public AttendanceRecord duplicate(boolean all) {
		com.dt.platform.domain.hr.meta.AttendanceRecordMeta.$$proxy$$ inst = new com.dt.platform.domain.hr.meta.AttendanceRecordMeta.$$proxy$$();
		inst.setEmployeeName(this.getEmployeeName());
		inst.setNotes(this.getNotes());
		inst.setEmployeeId(this.getEmployeeId());
		inst.setUpdateTime(this.getUpdateTime());
		inst.setSource(this.getSource());
		inst.setVersion(this.getVersion());
		inst.setEmployeeNumber(this.getEmployeeNumber());
		inst.setCreateBy(this.getCreateBy());
		inst.setDeleted(this.getDeleted());
		inst.setCreateTime(this.getCreateTime());
		inst.setUpdateBy(this.getUpdateBy());
		inst.setDeleteTime(this.getDeleteTime());
		inst.setTenantId(this.getTenantId());
		inst.setDeleteBy(this.getDeleteBy());
		inst.setId(this.getId());
		inst.setRcdTime(this.getRcdTime());
		inst.clearModifies();
		return inst;
	}

	/**
	 * 克隆当前对象
	*/
	@Transient
	public AttendanceRecord clone(boolean deep) {
		return EntityContext.clone(AttendanceRecord.class,this,deep);
	}

	/**
	 * 将 Map 转换成 AttendanceRecord
	 * @param attendanceRecordMap 包含实体信息的 Map 对象
	 * @return AttendanceRecord , 转换好的的 AttendanceRecord 对象
	*/
	@Transient
	public static AttendanceRecord createFrom(Map<String,Object> attendanceRecordMap) {
		if(attendanceRecordMap==null) return null;
		AttendanceRecord po = create();
		EntityContext.copyProperties(po,attendanceRecordMap);
		po.clearModifies();
		return po;
	}

	/**
	 * 将 Pojo 转换成 AttendanceRecord
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return AttendanceRecord , 转换好的的 AttendanceRecord 对象
	*/
	@Transient
	public static AttendanceRecord createFrom(Object pojo) {
		if(pojo==null) return null;
		AttendanceRecord po = create();
		EntityContext.copyProperties(po,pojo);
		po.clearModifies();
		return po;
	}

	/**
	 * 创建一个 AttendanceRecord，等同于 new
	 * @return AttendanceRecord 对象
	*/
	@Transient
	public static AttendanceRecord create() {
		return new com.dt.platform.domain.hr.meta.AttendanceRecordMeta.$$proxy$$();
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
			this.setEmployeeName(DataParser.parse(String.class, map.get(AttendanceRecordMeta.EMPLOYEE_NAME)));
			this.setNotes(DataParser.parse(String.class, map.get(AttendanceRecordMeta.NOTES)));
			this.setEmployeeId(DataParser.parse(String.class, map.get(AttendanceRecordMeta.EMPLOYEE_ID)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(AttendanceRecordMeta.UPDATE_TIME)));
			this.setSource(DataParser.parse(String.class, map.get(AttendanceRecordMeta.SOURCE)));
			this.setVersion(DataParser.parse(Integer.class, map.get(AttendanceRecordMeta.VERSION)));
			this.setEmployeeNumber(DataParser.parse(String.class, map.get(AttendanceRecordMeta.EMPLOYEE_NUMBER)));
			this.setCreateBy(DataParser.parse(String.class, map.get(AttendanceRecordMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(AttendanceRecordMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(AttendanceRecordMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(AttendanceRecordMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(AttendanceRecordMeta.DELETE_TIME)));
			this.setTenantId(DataParser.parse(String.class, map.get(AttendanceRecordMeta.TENANT_ID)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(AttendanceRecordMeta.DELETE_BY)));
			this.setId(DataParser.parse(String.class, map.get(AttendanceRecordMeta.ID)));
			this.setRcdTime(DataParser.parse(Date.class, map.get(AttendanceRecordMeta.RCD_TIME)));
			// others
			return true;
		} else {
			try {
				this.setEmployeeName( (String)map.get(AttendanceRecordMeta.EMPLOYEE_NAME));
				this.setNotes( (String)map.get(AttendanceRecordMeta.NOTES));
				this.setEmployeeId( (String)map.get(AttendanceRecordMeta.EMPLOYEE_ID));
				this.setUpdateTime( (Date)map.get(AttendanceRecordMeta.UPDATE_TIME));
				this.setSource( (String)map.get(AttendanceRecordMeta.SOURCE));
				this.setVersion( (Integer)map.get(AttendanceRecordMeta.VERSION));
				this.setEmployeeNumber( (String)map.get(AttendanceRecordMeta.EMPLOYEE_NUMBER));
				this.setCreateBy( (String)map.get(AttendanceRecordMeta.CREATE_BY));
				this.setDeleted( (Integer)map.get(AttendanceRecordMeta.DELETED));
				this.setCreateTime( (Date)map.get(AttendanceRecordMeta.CREATE_TIME));
				this.setUpdateBy( (String)map.get(AttendanceRecordMeta.UPDATE_BY));
				this.setDeleteTime( (Date)map.get(AttendanceRecordMeta.DELETE_TIME));
				this.setTenantId( (String)map.get(AttendanceRecordMeta.TENANT_ID));
				this.setDeleteBy( (String)map.get(AttendanceRecordMeta.DELETE_BY));
				this.setId( (String)map.get(AttendanceRecordMeta.ID));
				this.setRcdTime( (Date)map.get(AttendanceRecordMeta.RCD_TIME));
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
			this.setEmployeeName(DataParser.parse(String.class, r.getValue(AttendanceRecordMeta.EMPLOYEE_NAME)));
			this.setNotes(DataParser.parse(String.class, r.getValue(AttendanceRecordMeta.NOTES)));
			this.setEmployeeId(DataParser.parse(String.class, r.getValue(AttendanceRecordMeta.EMPLOYEE_ID)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(AttendanceRecordMeta.UPDATE_TIME)));
			this.setSource(DataParser.parse(String.class, r.getValue(AttendanceRecordMeta.SOURCE)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(AttendanceRecordMeta.VERSION)));
			this.setEmployeeNumber(DataParser.parse(String.class, r.getValue(AttendanceRecordMeta.EMPLOYEE_NUMBER)));
			this.setCreateBy(DataParser.parse(String.class, r.getValue(AttendanceRecordMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(AttendanceRecordMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(AttendanceRecordMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(AttendanceRecordMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(AttendanceRecordMeta.DELETE_TIME)));
			this.setTenantId(DataParser.parse(String.class, r.getValue(AttendanceRecordMeta.TENANT_ID)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(AttendanceRecordMeta.DELETE_BY)));
			this.setId(DataParser.parse(String.class, r.getValue(AttendanceRecordMeta.ID)));
			this.setRcdTime(DataParser.parse(Date.class, r.getValue(AttendanceRecordMeta.RCD_TIME)));
			return true;
		} else {
			try {
				this.setEmployeeName( (String)r.getValue(AttendanceRecordMeta.EMPLOYEE_NAME));
				this.setNotes( (String)r.getValue(AttendanceRecordMeta.NOTES));
				this.setEmployeeId( (String)r.getValue(AttendanceRecordMeta.EMPLOYEE_ID));
				this.setUpdateTime( (Date)r.getValue(AttendanceRecordMeta.UPDATE_TIME));
				this.setSource( (String)r.getValue(AttendanceRecordMeta.SOURCE));
				this.setVersion( (Integer)r.getValue(AttendanceRecordMeta.VERSION));
				this.setEmployeeNumber( (String)r.getValue(AttendanceRecordMeta.EMPLOYEE_NUMBER));
				this.setCreateBy( (String)r.getValue(AttendanceRecordMeta.CREATE_BY));
				this.setDeleted( (Integer)r.getValue(AttendanceRecordMeta.DELETED));
				this.setCreateTime( (Date)r.getValue(AttendanceRecordMeta.CREATE_TIME));
				this.setUpdateBy( (String)r.getValue(AttendanceRecordMeta.UPDATE_BY));
				this.setDeleteTime( (Date)r.getValue(AttendanceRecordMeta.DELETE_TIME));
				this.setTenantId( (String)r.getValue(AttendanceRecordMeta.TENANT_ID));
				this.setDeleteBy( (String)r.getValue(AttendanceRecordMeta.DELETE_BY));
				this.setId( (String)r.getValue(AttendanceRecordMeta.ID));
				this.setRcdTime( (Date)r.getValue(AttendanceRecordMeta.RCD_TIME));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}