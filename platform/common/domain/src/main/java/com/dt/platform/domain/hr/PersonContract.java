package com.dt.platform.domain.hr;

import com.github.foxnic.dao.entity.Entity;
import io.swagger.annotations.ApiModel;
import javax.persistence.Table;
import com.github.foxnic.sql.meta.DBTable;
import com.dt.platform.constants.db.HrTables.HR_PERSON_CONTRACT;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Transient;
import com.github.foxnic.api.swagger.EnumFor;
import org.github.foxnic.web.domain.system.DictItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.foxnic.commons.lang.DataParser;
import java.util.Map;
import com.github.foxnic.dao.entity.EntityContext;
import com.dt.platform.domain.hr.meta.PersonContractMeta;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 人员合同
 * <p>人员合同 , 数据表 hr_person_contract 的PO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-12-29 15:52:03
 * @sign 69106E93419AB85D5AEC800FB78E5E93
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@Table(name = "hr_person_contract")
@ApiModel(description = "人员合同 ; 人员合同 , 数据表 hr_person_contract 的PO类型")
public class PersonContract extends Entity {

	private static final long serialVersionUID = 1L;

	public static final DBTable TABLE =HR_PERSON_CONTRACT.$TABLE;
	
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
	private String personId;
	
	/**
	 * 状态：状态
	*/
	@ApiModelProperty(required = false,value="状态" , notes = "状态")
	private String status;
	
	/**
	 * 合同周期：合同周期
	*/
	@ApiModelProperty(required = true,value="合同周期" , notes = "合同周期")
	private String contractDuration;
	
	/**
	 * 合同开始时间：合同开始时间
	*/
	@ApiModelProperty(required = true,value="合同开始时间" , notes = "合同开始时间")
	private Date contractStartDate;
	
	/**
	 * 合同结束时间：合同结束时间
	*/
	@ApiModelProperty(required = true,value="合同结束时间" , notes = "合同结束时间")
	private Date contractFinishDate;
	
	/**
	 * 合同：合同
	*/
	@ApiModelProperty(required = true,value="合同" , notes = "合同")
	private String fileId;
	
	/**
	 * 备注：备注
	*/
	@ApiModelProperty(required = true,value="备注" , notes = "备注")
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
	 * contractDurationDict：contractDurationDict
	*/
	@ApiModelProperty(required = false,value="contractDurationDict" , notes = "contractDurationDict")
	private DictItem contractDurationDict;
	
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
	public PersonContract setId(String id) {
		this.id=id;
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
	public PersonContract setPersonId(String personId) {
		this.personId=personId;
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
	public PersonContract setStatus(String status) {
		this.status=status;
		return this;
	}
	
	/**
	 * 获得 合同周期<br>
	 * 合同周期
	 * @return 合同周期
	*/
	public String getContractDuration() {
		return contractDuration;
	}
	
	/**
	 * 设置 合同周期
	 * @param contractDuration 合同周期
	 * @return 当前对象
	*/
	public PersonContract setContractDuration(String contractDuration) {
		this.contractDuration=contractDuration;
		return this;
	}
	
	/**
	 * 获得 合同开始时间<br>
	 * 合同开始时间
	 * @return 合同开始时间
	*/
	public Date getContractStartDate() {
		return contractStartDate;
	}
	
	/**
	 * 设置 合同开始时间
	 * @param contractStartDate 合同开始时间
	 * @return 当前对象
	*/
	public PersonContract setContractStartDate(Date contractStartDate) {
		this.contractStartDate=contractStartDate;
		return this;
	}
	
	/**
	 * 获得 合同结束时间<br>
	 * 合同结束时间
	 * @return 合同结束时间
	*/
	public Date getContractFinishDate() {
		return contractFinishDate;
	}
	
	/**
	 * 设置 合同结束时间
	 * @param contractFinishDate 合同结束时间
	 * @return 当前对象
	*/
	public PersonContract setContractFinishDate(Date contractFinishDate) {
		this.contractFinishDate=contractFinishDate;
		return this;
	}
	
	/**
	 * 获得 合同<br>
	 * 合同
	 * @return 合同
	*/
	public String getFileId() {
		return fileId;
	}
	
	/**
	 * 设置 合同
	 * @param fileId 合同
	 * @return 当前对象
	*/
	public PersonContract setFileId(String fileId) {
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
	public PersonContract setNotes(String notes) {
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
	public PersonContract setCreateBy(String createBy) {
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
	public PersonContract setCreateTime(Date createTime) {
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
	public PersonContract setUpdateBy(String updateBy) {
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
	public PersonContract setUpdateTime(Date updateTime) {
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
	public PersonContract setDeleted(Integer deleted) {
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
	public PersonContract setDeleted(Boolean deletedBool) {
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
	public PersonContract setDeleteBy(String deleteBy) {
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
	public PersonContract setDeleteTime(Date deleteTime) {
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
	public PersonContract setVersion(Integer version) {
		this.version=version;
		return this;
	}
	
	/**
	 * 获得 contractDurationDict<br>
	 * contractDurationDict
	 * @return contractDurationDict
	*/
	public DictItem getContractDurationDict() {
		return contractDurationDict;
	}
	
	/**
	 * 设置 contractDurationDict
	 * @param contractDurationDict contractDurationDict
	 * @return 当前对象
	*/
	public PersonContract setContractDurationDict(DictItem contractDurationDict) {
		this.contractDurationDict=contractDurationDict;
		return this;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return PersonContract , 转换好的 PersonContract 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return PersonContract , 转换好的 PoJo 对象
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
	public PersonContract clone() {
		return duplicate(true);
	}

	/**
	 * 复制当前对象
	 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
	*/
	@Transient
	public PersonContract duplicate(boolean all) {
		com.dt.platform.domain.hr.meta.PersonContractMeta.$$proxy$$ inst = new com.dt.platform.domain.hr.meta.PersonContractMeta.$$proxy$$();
		inst.setNotes(this.getNotes());
		inst.setContractDuration(this.getContractDuration());
		inst.setContractStartDate(this.getContractStartDate());
		inst.setContractFinishDate(this.getContractFinishDate());
		inst.setUpdateTime(this.getUpdateTime());
		inst.setVersion(this.getVersion());
		inst.setCreateBy(this.getCreateBy());
		inst.setDeleted(this.getDeleted());
		inst.setCreateTime(this.getCreateTime());
		inst.setUpdateBy(this.getUpdateBy());
		inst.setDeleteTime(this.getDeleteTime());
		inst.setDeleteBy(this.getDeleteBy());
		inst.setPersonId(this.getPersonId());
		inst.setId(this.getId());
		inst.setStatus(this.getStatus());
		inst.setFileId(this.getFileId());
		if(all) {
			inst.setContractDurationDict(this.getContractDurationDict());
		}
		inst.clearModifies();
		return inst;
	}

	/**
	 * 克隆当前对象
	*/
	@Transient
	public PersonContract clone(boolean deep) {
		return EntityContext.clone(PersonContract.class,this,deep);
	}

	/**
	 * 将 Map 转换成 PersonContract
	 * @param personContractMap 包含实体信息的 Map 对象
	 * @return PersonContract , 转换好的的 PersonContract 对象
	*/
	@Transient
	public static PersonContract createFrom(Map<String,Object> personContractMap) {
		if(personContractMap==null) return null;
		PersonContract po = create();
		EntityContext.copyProperties(po,personContractMap);
		po.clearModifies();
		return po;
	}

	/**
	 * 将 Pojo 转换成 PersonContract
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return PersonContract , 转换好的的 PersonContract 对象
	*/
	@Transient
	public static PersonContract createFrom(Object pojo) {
		if(pojo==null) return null;
		PersonContract po = create();
		EntityContext.copyProperties(po,pojo);
		po.clearModifies();
		return po;
	}

	/**
	 * 创建一个 PersonContract，等同于 new
	 * @return PersonContract 对象
	*/
	@Transient
	public static PersonContract create() {
		return new com.dt.platform.domain.hr.meta.PersonContractMeta.$$proxy$$();
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
			this.setNotes(DataParser.parse(String.class, map.get(PersonContractMeta.NOTES)));
			this.setContractDuration(DataParser.parse(String.class, map.get(PersonContractMeta.CONTRACT_DURATION)));
			this.setContractStartDate(DataParser.parse(Date.class, map.get(PersonContractMeta.CONTRACT_START_DATE)));
			this.setContractFinishDate(DataParser.parse(Date.class, map.get(PersonContractMeta.CONTRACT_FINISH_DATE)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(PersonContractMeta.UPDATE_TIME)));
			this.setVersion(DataParser.parse(Integer.class, map.get(PersonContractMeta.VERSION)));
			this.setCreateBy(DataParser.parse(String.class, map.get(PersonContractMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(PersonContractMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(PersonContractMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(PersonContractMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(PersonContractMeta.DELETE_TIME)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(PersonContractMeta.DELETE_BY)));
			this.setPersonId(DataParser.parse(String.class, map.get(PersonContractMeta.PERSON_ID)));
			this.setId(DataParser.parse(String.class, map.get(PersonContractMeta.ID)));
			this.setStatus(DataParser.parse(String.class, map.get(PersonContractMeta.STATUS)));
			this.setFileId(DataParser.parse(String.class, map.get(PersonContractMeta.FILE_ID)));
			// others
			this.setContractDurationDict(DataParser.parse(DictItem.class, map.get(PersonContractMeta.CONTRACT_DURATION_DICT)));
			return true;
		} else {
			try {
				this.setNotes( (String)map.get(PersonContractMeta.NOTES));
				this.setContractDuration( (String)map.get(PersonContractMeta.CONTRACT_DURATION));
				this.setContractStartDate( (Date)map.get(PersonContractMeta.CONTRACT_START_DATE));
				this.setContractFinishDate( (Date)map.get(PersonContractMeta.CONTRACT_FINISH_DATE));
				this.setUpdateTime( (Date)map.get(PersonContractMeta.UPDATE_TIME));
				this.setVersion( (Integer)map.get(PersonContractMeta.VERSION));
				this.setCreateBy( (String)map.get(PersonContractMeta.CREATE_BY));
				this.setDeleted( (Integer)map.get(PersonContractMeta.DELETED));
				this.setCreateTime( (Date)map.get(PersonContractMeta.CREATE_TIME));
				this.setUpdateBy( (String)map.get(PersonContractMeta.UPDATE_BY));
				this.setDeleteTime( (Date)map.get(PersonContractMeta.DELETE_TIME));
				this.setDeleteBy( (String)map.get(PersonContractMeta.DELETE_BY));
				this.setPersonId( (String)map.get(PersonContractMeta.PERSON_ID));
				this.setId( (String)map.get(PersonContractMeta.ID));
				this.setStatus( (String)map.get(PersonContractMeta.STATUS));
				this.setFileId( (String)map.get(PersonContractMeta.FILE_ID));
				// others
				this.setContractDurationDict( (DictItem)map.get(PersonContractMeta.CONTRACT_DURATION_DICT));
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
			this.setNotes(DataParser.parse(String.class, r.getValue(PersonContractMeta.NOTES)));
			this.setContractDuration(DataParser.parse(String.class, r.getValue(PersonContractMeta.CONTRACT_DURATION)));
			this.setContractStartDate(DataParser.parse(Date.class, r.getValue(PersonContractMeta.CONTRACT_START_DATE)));
			this.setContractFinishDate(DataParser.parse(Date.class, r.getValue(PersonContractMeta.CONTRACT_FINISH_DATE)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(PersonContractMeta.UPDATE_TIME)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(PersonContractMeta.VERSION)));
			this.setCreateBy(DataParser.parse(String.class, r.getValue(PersonContractMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(PersonContractMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(PersonContractMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(PersonContractMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(PersonContractMeta.DELETE_TIME)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(PersonContractMeta.DELETE_BY)));
			this.setPersonId(DataParser.parse(String.class, r.getValue(PersonContractMeta.PERSON_ID)));
			this.setId(DataParser.parse(String.class, r.getValue(PersonContractMeta.ID)));
			this.setStatus(DataParser.parse(String.class, r.getValue(PersonContractMeta.STATUS)));
			this.setFileId(DataParser.parse(String.class, r.getValue(PersonContractMeta.FILE_ID)));
			return true;
		} else {
			try {
				this.setNotes( (String)r.getValue(PersonContractMeta.NOTES));
				this.setContractDuration( (String)r.getValue(PersonContractMeta.CONTRACT_DURATION));
				this.setContractStartDate( (Date)r.getValue(PersonContractMeta.CONTRACT_START_DATE));
				this.setContractFinishDate( (Date)r.getValue(PersonContractMeta.CONTRACT_FINISH_DATE));
				this.setUpdateTime( (Date)r.getValue(PersonContractMeta.UPDATE_TIME));
				this.setVersion( (Integer)r.getValue(PersonContractMeta.VERSION));
				this.setCreateBy( (String)r.getValue(PersonContractMeta.CREATE_BY));
				this.setDeleted( (Integer)r.getValue(PersonContractMeta.DELETED));
				this.setCreateTime( (Date)r.getValue(PersonContractMeta.CREATE_TIME));
				this.setUpdateBy( (String)r.getValue(PersonContractMeta.UPDATE_BY));
				this.setDeleteTime( (Date)r.getValue(PersonContractMeta.DELETE_TIME));
				this.setDeleteBy( (String)r.getValue(PersonContractMeta.DELETE_BY));
				this.setPersonId( (String)r.getValue(PersonContractMeta.PERSON_ID));
				this.setId( (String)r.getValue(PersonContractMeta.ID));
				this.setStatus( (String)r.getValue(PersonContractMeta.STATUS));
				this.setFileId( (String)r.getValue(PersonContractMeta.FILE_ID));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}