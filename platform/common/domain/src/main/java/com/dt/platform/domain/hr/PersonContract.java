package com.dt.platform.domain.hr;

import com.github.foxnic.dao.entity.Entity;
import io.swagger.annotations.ApiModel;
import javax.persistence.Table;
import com.github.foxnic.sql.meta.DBTable;
import com.dt.platform.constants.db.HrTables.HR_PERSON_CONTRACT;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Transient;
import com.github.foxnic.api.swagger.EnumFor;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.hrm.Employee;
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
 * @since 2023-01-02 14:12:02
 * @sign 78520C27FE87601F67F16F4042D9CC38
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
	private String employeeId;
	
	/**
	 * 合同编号：合同编号
	*/
	@ApiModelProperty(required = false,value="合同编号" , notes = "合同编号")
	private String businessCode;
	
	/**
	 * 合同类型：合同类型
	*/
	@ApiModelProperty(required = false,value="合同类型" , notes = "合同类型")
	private String type;
	
	/**
	 * 状态：状态
	*/
	@ApiModelProperty(required = false,value="状态" , notes = "状态")
	private String status;
	
	/**
	 * 合同期限：月
	*/
	@ApiModelProperty(required = false,value="合同期限" , notes = "月")
	private BigDecimal contractDuration;
	
	/**
	 * 合同年份：合同年份
	*/
	@ApiModelProperty(required = false,value="合同年份" , notes = "合同年份")
	private String contractYear;
	
	/**
	 * 是否转正：是否转正
	*/
	@ApiModelProperty(required = false,value="是否转正" , notes = "是否转正")
	private String transferToRegular;
	
	/**
	 * 试用期工资：试用期工资
	*/
	@ApiModelProperty(required = false,value="试用期工资" , notes = "试用期工资")
	private BigDecimal probationSalary;
	
	/**
	 * 试用期生效时间：试用期生效时间
	*/
	@ApiModelProperty(required = false,value="试用期生效时间" , notes = "试用期生效时间")
	private Date probationStartDate;
	
	/**
	 * 试用期到期时间：试用期到期时间
	*/
	@ApiModelProperty(required = false,value="试用期到期时间" , notes = "试用期到期时间")
	private String probationFinishDate;
	
	/**
	 * 生效时间：生效时间
	*/
	@ApiModelProperty(required = false,value="生效时间" , notes = "生效时间")
	private Date contractStartDate;
	
	/**
	 * 到期时间：到期时间
	*/
	@ApiModelProperty(required = false,value="到期时间" , notes = "到期时间")
	private Date contractFinishDate;
	
	/**
	 * 工资：工资
	*/
	@ApiModelProperty(required = false,value="工资" , notes = "工资")
	private BigDecimal salary;
	
	/**
	 * 合同内容：合同内容
	*/
	@ApiModelProperty(required = false,value="合同内容" , notes = "合同内容")
	private String content;
	
	/**
	 * 备注：备注
	*/
	@ApiModelProperty(required = false,value="备注" , notes = "备注")
	private String notes;
	
	/**
	 * 合同附件：合同附件
	*/
	@ApiModelProperty(required = false,value="合同附件" , notes = "合同附件")
	private String fileId;
	
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
	 * contractTypeData：contractTypeData
	*/
	@ApiModelProperty(required = false,value="contractTypeData" , notes = "contractTypeData")
	private DictItem contractTypeData;
	
	/**
	 * contractYearData：contractYearData
	*/
	@ApiModelProperty(required = false,value="contractYearData" , notes = "contractYearData")
	private DictItem contractYearData;
	
	/**
	 * employee：employee
	*/
	@ApiModelProperty(required = false,value="employee" , notes = "employee")
	private Employee employee;
	
	/**
	 * person：person
	*/
	@ApiModelProperty(required = false,value="person" , notes = "person")
	private Person person;
	
	/**
	 * employeeName：employeeName
	*/
	@ApiModelProperty(required = false,value="employeeName" , notes = "employeeName")
	private String employeeName;
	
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
	public String getEmployeeId() {
		return employeeId;
	}
	
	/**
	 * 设置 人员
	 * @param employeeId 人员
	 * @return 当前对象
	*/
	public PersonContract setEmployeeId(String employeeId) {
		this.employeeId=employeeId;
		return this;
	}
	
	/**
	 * 获得 合同编号<br>
	 * 合同编号
	 * @return 合同编号
	*/
	public String getBusinessCode() {
		return businessCode;
	}
	
	/**
	 * 设置 合同编号
	 * @param businessCode 合同编号
	 * @return 当前对象
	*/
	public PersonContract setBusinessCode(String businessCode) {
		this.businessCode=businessCode;
		return this;
	}
	
	/**
	 * 获得 合同类型<br>
	 * 合同类型
	 * @return 合同类型
	*/
	public String getType() {
		return type;
	}
	
	/**
	 * 设置 合同类型
	 * @param type 合同类型
	 * @return 当前对象
	*/
	public PersonContract setType(String type) {
		this.type=type;
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
	 * 获得 合同期限<br>
	 * 月
	 * @return 合同期限
	*/
	public BigDecimal getContractDuration() {
		return contractDuration;
	}
	
	/**
	 * 设置 合同期限
	 * @param contractDuration 合同期限
	 * @return 当前对象
	*/
	public PersonContract setContractDuration(BigDecimal contractDuration) {
		this.contractDuration=contractDuration;
		return this;
	}
	
	/**
	 * 获得 合同年份<br>
	 * 合同年份
	 * @return 合同年份
	*/
	public String getContractYear() {
		return contractYear;
	}
	
	/**
	 * 设置 合同年份
	 * @param contractYear 合同年份
	 * @return 当前对象
	*/
	public PersonContract setContractYear(String contractYear) {
		this.contractYear=contractYear;
		return this;
	}
	
	/**
	 * 获得 是否转正<br>
	 * 是否转正
	 * @return 是否转正
	*/
	public String getTransferToRegular() {
		return transferToRegular;
	}
	
	/**
	 * 设置 是否转正
	 * @param transferToRegular 是否转正
	 * @return 当前对象
	*/
	public PersonContract setTransferToRegular(String transferToRegular) {
		this.transferToRegular=transferToRegular;
		return this;
	}
	
	/**
	 * 获得 试用期工资<br>
	 * 试用期工资
	 * @return 试用期工资
	*/
	public BigDecimal getProbationSalary() {
		return probationSalary;
	}
	
	/**
	 * 设置 试用期工资
	 * @param probationSalary 试用期工资
	 * @return 当前对象
	*/
	public PersonContract setProbationSalary(BigDecimal probationSalary) {
		this.probationSalary=probationSalary;
		return this;
	}
	
	/**
	 * 获得 试用期生效时间<br>
	 * 试用期生效时间
	 * @return 试用期生效时间
	*/
	public Date getProbationStartDate() {
		return probationStartDate;
	}
	
	/**
	 * 设置 试用期生效时间
	 * @param probationStartDate 试用期生效时间
	 * @return 当前对象
	*/
	public PersonContract setProbationStartDate(Date probationStartDate) {
		this.probationStartDate=probationStartDate;
		return this;
	}
	
	/**
	 * 获得 试用期到期时间<br>
	 * 试用期到期时间
	 * @return 试用期到期时间
	*/
	public String getProbationFinishDate() {
		return probationFinishDate;
	}
	
	/**
	 * 设置 试用期到期时间
	 * @param probationFinishDate 试用期到期时间
	 * @return 当前对象
	*/
	public PersonContract setProbationFinishDate(String probationFinishDate) {
		this.probationFinishDate=probationFinishDate;
		return this;
	}
	
	/**
	 * 获得 生效时间<br>
	 * 生效时间
	 * @return 生效时间
	*/
	public Date getContractStartDate() {
		return contractStartDate;
	}
	
	/**
	 * 设置 生效时间
	 * @param contractStartDate 生效时间
	 * @return 当前对象
	*/
	public PersonContract setContractStartDate(Date contractStartDate) {
		this.contractStartDate=contractStartDate;
		return this;
	}
	
	/**
	 * 获得 到期时间<br>
	 * 到期时间
	 * @return 到期时间
	*/
	public Date getContractFinishDate() {
		return contractFinishDate;
	}
	
	/**
	 * 设置 到期时间
	 * @param contractFinishDate 到期时间
	 * @return 当前对象
	*/
	public PersonContract setContractFinishDate(Date contractFinishDate) {
		this.contractFinishDate=contractFinishDate;
		return this;
	}
	
	/**
	 * 获得 工资<br>
	 * 工资
	 * @return 工资
	*/
	public BigDecimal getSalary() {
		return salary;
	}
	
	/**
	 * 设置 工资
	 * @param salary 工资
	 * @return 当前对象
	*/
	public PersonContract setSalary(BigDecimal salary) {
		this.salary=salary;
		return this;
	}
	
	/**
	 * 获得 合同内容<br>
	 * 合同内容
	 * @return 合同内容
	*/
	public String getContent() {
		return content;
	}
	
	/**
	 * 设置 合同内容
	 * @param content 合同内容
	 * @return 当前对象
	*/
	public PersonContract setContent(String content) {
		this.content=content;
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
	 * 获得 合同附件<br>
	 * 合同附件
	 * @return 合同附件
	*/
	public String getFileId() {
		return fileId;
	}
	
	/**
	 * 设置 合同附件
	 * @param fileId 合同附件
	 * @return 当前对象
	*/
	public PersonContract setFileId(String fileId) {
		this.fileId=fileId;
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
	public PersonContract setTenantId(String tenantId) {
		this.tenantId=tenantId;
		return this;
	}
	
	/**
	 * 获得 contractTypeData<br>
	 * contractTypeData
	 * @return contractTypeData
	*/
	public DictItem getContractTypeData() {
		return contractTypeData;
	}
	
	/**
	 * 设置 contractTypeData
	 * @param contractTypeData contractTypeData
	 * @return 当前对象
	*/
	public PersonContract setContractTypeData(DictItem contractTypeData) {
		this.contractTypeData=contractTypeData;
		return this;
	}
	
	/**
	 * 获得 contractYearData<br>
	 * contractYearData
	 * @return contractYearData
	*/
	public DictItem getContractYearData() {
		return contractYearData;
	}
	
	/**
	 * 设置 contractYearData
	 * @param contractYearData contractYearData
	 * @return 当前对象
	*/
	public PersonContract setContractYearData(DictItem contractYearData) {
		this.contractYearData=contractYearData;
		return this;
	}
	
	/**
	 * 获得 employee<br>
	 * employee
	 * @return employee
	*/
	public Employee getEmployee() {
		return employee;
	}
	
	/**
	 * 设置 employee
	 * @param employee employee
	 * @return 当前对象
	*/
	public PersonContract setEmployee(Employee employee) {
		this.employee=employee;
		return this;
	}
	
	/**
	 * 获得 person<br>
	 * person
	 * @return person
	*/
	public Person getPerson() {
		return person;
	}
	
	/**
	 * 设置 person
	 * @param person person
	 * @return 当前对象
	*/
	public PersonContract setPerson(Person person) {
		this.person=person;
		return this;
	}
	
	/**
	 * 获得 employeeName<br>
	 * employeeName
	 * @return employeeName
	*/
	public String getEmployeeName() {
		return employeeName;
	}
	
	/**
	 * 设置 employeeName
	 * @param employeeName employeeName
	 * @return 当前对象
	*/
	public PersonContract setEmployeeName(String employeeName) {
		this.employeeName=employeeName;
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
		inst.setContractYear(this.getContractYear());
		inst.setNotes(this.getNotes());
		inst.setContractDuration(this.getContractDuration());
		inst.setContractFinishDate(this.getContractFinishDate());
		inst.setType(this.getType());
		inst.setSalary(this.getSalary());
		inst.setContent(this.getContent());
		inst.setBusinessCode(this.getBusinessCode());
		inst.setUpdateBy(this.getUpdateBy());
		inst.setProbationFinishDate(this.getProbationFinishDate());
		inst.setId(this.getId());
		inst.setProbationStartDate(this.getProbationStartDate());
		inst.setProbationSalary(this.getProbationSalary());
		inst.setContractStartDate(this.getContractStartDate());
		inst.setEmployeeId(this.getEmployeeId());
		inst.setUpdateTime(this.getUpdateTime());
		inst.setVersion(this.getVersion());
		inst.setCreateBy(this.getCreateBy());
		inst.setDeleted(this.getDeleted());
		inst.setCreateTime(this.getCreateTime());
		inst.setDeleteTime(this.getDeleteTime());
		inst.setTransferToRegular(this.getTransferToRegular());
		inst.setTenantId(this.getTenantId());
		inst.setDeleteBy(this.getDeleteBy());
		inst.setStatus(this.getStatus());
		inst.setFileId(this.getFileId());
		if(all) {
			inst.setContractTypeData(this.getContractTypeData());
			inst.setEmployeeName(this.getEmployeeName());
			inst.setPerson(this.getPerson());
			inst.setContractYearData(this.getContractYearData());
			inst.setEmployee(this.getEmployee());
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
			this.setContractYear(DataParser.parse(String.class, map.get(PersonContractMeta.CONTRACT_YEAR)));
			this.setNotes(DataParser.parse(String.class, map.get(PersonContractMeta.NOTES)));
			this.setContractDuration(DataParser.parse(BigDecimal.class, map.get(PersonContractMeta.CONTRACT_DURATION)));
			this.setContractFinishDate(DataParser.parse(Date.class, map.get(PersonContractMeta.CONTRACT_FINISH_DATE)));
			this.setType(DataParser.parse(String.class, map.get(PersonContractMeta.TYPE)));
			this.setSalary(DataParser.parse(BigDecimal.class, map.get(PersonContractMeta.SALARY)));
			this.setContent(DataParser.parse(String.class, map.get(PersonContractMeta.CONTENT)));
			this.setBusinessCode(DataParser.parse(String.class, map.get(PersonContractMeta.BUSINESS_CODE)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(PersonContractMeta.UPDATE_BY)));
			this.setProbationFinishDate(DataParser.parse(String.class, map.get(PersonContractMeta.PROBATION_FINISH_DATE)));
			this.setId(DataParser.parse(String.class, map.get(PersonContractMeta.ID)));
			this.setProbationStartDate(DataParser.parse(Date.class, map.get(PersonContractMeta.PROBATION_START_DATE)));
			this.setProbationSalary(DataParser.parse(BigDecimal.class, map.get(PersonContractMeta.PROBATION_SALARY)));
			this.setContractStartDate(DataParser.parse(Date.class, map.get(PersonContractMeta.CONTRACT_START_DATE)));
			this.setEmployeeId(DataParser.parse(String.class, map.get(PersonContractMeta.EMPLOYEE_ID)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(PersonContractMeta.UPDATE_TIME)));
			this.setVersion(DataParser.parse(Integer.class, map.get(PersonContractMeta.VERSION)));
			this.setCreateBy(DataParser.parse(String.class, map.get(PersonContractMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(PersonContractMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(PersonContractMeta.CREATE_TIME)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(PersonContractMeta.DELETE_TIME)));
			this.setTransferToRegular(DataParser.parse(String.class, map.get(PersonContractMeta.TRANSFER_TO_REGULAR)));
			this.setTenantId(DataParser.parse(String.class, map.get(PersonContractMeta.TENANT_ID)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(PersonContractMeta.DELETE_BY)));
			this.setStatus(DataParser.parse(String.class, map.get(PersonContractMeta.STATUS)));
			this.setFileId(DataParser.parse(String.class, map.get(PersonContractMeta.FILE_ID)));
			// others
			this.setContractTypeData(DataParser.parse(DictItem.class, map.get(PersonContractMeta.CONTRACT_TYPE_DATA)));
			this.setEmployeeName(DataParser.parse(String.class, map.get(PersonContractMeta.EMPLOYEE_NAME)));
			this.setPerson(DataParser.parse(Person.class, map.get(PersonContractMeta.PERSON)));
			this.setContractYearData(DataParser.parse(DictItem.class, map.get(PersonContractMeta.CONTRACT_YEAR_DATA)));
			this.setEmployee(DataParser.parse(Employee.class, map.get(PersonContractMeta.EMPLOYEE)));
			return true;
		} else {
			try {
				this.setContractYear( (String)map.get(PersonContractMeta.CONTRACT_YEAR));
				this.setNotes( (String)map.get(PersonContractMeta.NOTES));
				this.setContractDuration( (BigDecimal)map.get(PersonContractMeta.CONTRACT_DURATION));
				this.setContractFinishDate( (Date)map.get(PersonContractMeta.CONTRACT_FINISH_DATE));
				this.setType( (String)map.get(PersonContractMeta.TYPE));
				this.setSalary( (BigDecimal)map.get(PersonContractMeta.SALARY));
				this.setContent( (String)map.get(PersonContractMeta.CONTENT));
				this.setBusinessCode( (String)map.get(PersonContractMeta.BUSINESS_CODE));
				this.setUpdateBy( (String)map.get(PersonContractMeta.UPDATE_BY));
				this.setProbationFinishDate( (String)map.get(PersonContractMeta.PROBATION_FINISH_DATE));
				this.setId( (String)map.get(PersonContractMeta.ID));
				this.setProbationStartDate( (Date)map.get(PersonContractMeta.PROBATION_START_DATE));
				this.setProbationSalary( (BigDecimal)map.get(PersonContractMeta.PROBATION_SALARY));
				this.setContractStartDate( (Date)map.get(PersonContractMeta.CONTRACT_START_DATE));
				this.setEmployeeId( (String)map.get(PersonContractMeta.EMPLOYEE_ID));
				this.setUpdateTime( (Date)map.get(PersonContractMeta.UPDATE_TIME));
				this.setVersion( (Integer)map.get(PersonContractMeta.VERSION));
				this.setCreateBy( (String)map.get(PersonContractMeta.CREATE_BY));
				this.setDeleted( (Integer)map.get(PersonContractMeta.DELETED));
				this.setCreateTime( (Date)map.get(PersonContractMeta.CREATE_TIME));
				this.setDeleteTime( (Date)map.get(PersonContractMeta.DELETE_TIME));
				this.setTransferToRegular( (String)map.get(PersonContractMeta.TRANSFER_TO_REGULAR));
				this.setTenantId( (String)map.get(PersonContractMeta.TENANT_ID));
				this.setDeleteBy( (String)map.get(PersonContractMeta.DELETE_BY));
				this.setStatus( (String)map.get(PersonContractMeta.STATUS));
				this.setFileId( (String)map.get(PersonContractMeta.FILE_ID));
				// others
				this.setContractTypeData( (DictItem)map.get(PersonContractMeta.CONTRACT_TYPE_DATA));
				this.setEmployeeName( (String)map.get(PersonContractMeta.EMPLOYEE_NAME));
				this.setPerson( (Person)map.get(PersonContractMeta.PERSON));
				this.setContractYearData( (DictItem)map.get(PersonContractMeta.CONTRACT_YEAR_DATA));
				this.setEmployee( (Employee)map.get(PersonContractMeta.EMPLOYEE));
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
			this.setContractYear(DataParser.parse(String.class, r.getValue(PersonContractMeta.CONTRACT_YEAR)));
			this.setNotes(DataParser.parse(String.class, r.getValue(PersonContractMeta.NOTES)));
			this.setContractDuration(DataParser.parse(BigDecimal.class, r.getValue(PersonContractMeta.CONTRACT_DURATION)));
			this.setContractFinishDate(DataParser.parse(Date.class, r.getValue(PersonContractMeta.CONTRACT_FINISH_DATE)));
			this.setType(DataParser.parse(String.class, r.getValue(PersonContractMeta.TYPE)));
			this.setSalary(DataParser.parse(BigDecimal.class, r.getValue(PersonContractMeta.SALARY)));
			this.setContent(DataParser.parse(String.class, r.getValue(PersonContractMeta.CONTENT)));
			this.setBusinessCode(DataParser.parse(String.class, r.getValue(PersonContractMeta.BUSINESS_CODE)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(PersonContractMeta.UPDATE_BY)));
			this.setProbationFinishDate(DataParser.parse(String.class, r.getValue(PersonContractMeta.PROBATION_FINISH_DATE)));
			this.setId(DataParser.parse(String.class, r.getValue(PersonContractMeta.ID)));
			this.setProbationStartDate(DataParser.parse(Date.class, r.getValue(PersonContractMeta.PROBATION_START_DATE)));
			this.setProbationSalary(DataParser.parse(BigDecimal.class, r.getValue(PersonContractMeta.PROBATION_SALARY)));
			this.setContractStartDate(DataParser.parse(Date.class, r.getValue(PersonContractMeta.CONTRACT_START_DATE)));
			this.setEmployeeId(DataParser.parse(String.class, r.getValue(PersonContractMeta.EMPLOYEE_ID)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(PersonContractMeta.UPDATE_TIME)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(PersonContractMeta.VERSION)));
			this.setCreateBy(DataParser.parse(String.class, r.getValue(PersonContractMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(PersonContractMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(PersonContractMeta.CREATE_TIME)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(PersonContractMeta.DELETE_TIME)));
			this.setTransferToRegular(DataParser.parse(String.class, r.getValue(PersonContractMeta.TRANSFER_TO_REGULAR)));
			this.setTenantId(DataParser.parse(String.class, r.getValue(PersonContractMeta.TENANT_ID)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(PersonContractMeta.DELETE_BY)));
			this.setStatus(DataParser.parse(String.class, r.getValue(PersonContractMeta.STATUS)));
			this.setFileId(DataParser.parse(String.class, r.getValue(PersonContractMeta.FILE_ID)));
			return true;
		} else {
			try {
				this.setContractYear( (String)r.getValue(PersonContractMeta.CONTRACT_YEAR));
				this.setNotes( (String)r.getValue(PersonContractMeta.NOTES));
				this.setContractDuration( (BigDecimal)r.getValue(PersonContractMeta.CONTRACT_DURATION));
				this.setContractFinishDate( (Date)r.getValue(PersonContractMeta.CONTRACT_FINISH_DATE));
				this.setType( (String)r.getValue(PersonContractMeta.TYPE));
				this.setSalary( (BigDecimal)r.getValue(PersonContractMeta.SALARY));
				this.setContent( (String)r.getValue(PersonContractMeta.CONTENT));
				this.setBusinessCode( (String)r.getValue(PersonContractMeta.BUSINESS_CODE));
				this.setUpdateBy( (String)r.getValue(PersonContractMeta.UPDATE_BY));
				this.setProbationFinishDate( (String)r.getValue(PersonContractMeta.PROBATION_FINISH_DATE));
				this.setId( (String)r.getValue(PersonContractMeta.ID));
				this.setProbationStartDate( (Date)r.getValue(PersonContractMeta.PROBATION_START_DATE));
				this.setProbationSalary( (BigDecimal)r.getValue(PersonContractMeta.PROBATION_SALARY));
				this.setContractStartDate( (Date)r.getValue(PersonContractMeta.CONTRACT_START_DATE));
				this.setEmployeeId( (String)r.getValue(PersonContractMeta.EMPLOYEE_ID));
				this.setUpdateTime( (Date)r.getValue(PersonContractMeta.UPDATE_TIME));
				this.setVersion( (Integer)r.getValue(PersonContractMeta.VERSION));
				this.setCreateBy( (String)r.getValue(PersonContractMeta.CREATE_BY));
				this.setDeleted( (Integer)r.getValue(PersonContractMeta.DELETED));
				this.setCreateTime( (Date)r.getValue(PersonContractMeta.CREATE_TIME));
				this.setDeleteTime( (Date)r.getValue(PersonContractMeta.DELETE_TIME));
				this.setTransferToRegular( (String)r.getValue(PersonContractMeta.TRANSFER_TO_REGULAR));
				this.setTenantId( (String)r.getValue(PersonContractMeta.TENANT_ID));
				this.setDeleteBy( (String)r.getValue(PersonContractMeta.DELETE_BY));
				this.setStatus( (String)r.getValue(PersonContractMeta.STATUS));
				this.setFileId( (String)r.getValue(PersonContractMeta.FILE_ID));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}