package com.dt.platform.domain.hr.meta;

import com.github.foxnic.api.bean.BeanProperty;
import com.dt.platform.domain.hr.PersonContract;
import java.math.BigDecimal;
import java.util.Date;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.hrm.Employee;
import com.dt.platform.domain.hr.Person;
import javax.persistence.Transient;



/**
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:12:02
 * @sign 78520C27FE87601F67F16F4042D9CC38
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

public class PersonContractMeta {
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final String ID="id";
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> ID_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,ID, java.lang.String.class, "主键", "主键", java.lang.String.class, null);
	
	/**
	 * 人员 , 类型: java.lang.String
	*/
	public static final String EMPLOYEE_ID="employeeId";
	
	/**
	 * 人员 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> EMPLOYEE_ID_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,EMPLOYEE_ID, java.lang.String.class, "人员", "人员", java.lang.String.class, null);
	
	/**
	 * 合同编号 , 类型: java.lang.String
	*/
	public static final String BUSINESS_CODE="businessCode";
	
	/**
	 * 合同编号 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> BUSINESS_CODE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,BUSINESS_CODE, java.lang.String.class, "合同编号", "合同编号", java.lang.String.class, null);
	
	/**
	 * 合同类型 , 类型: java.lang.String
	*/
	public static final String TYPE="type";
	
	/**
	 * 合同类型 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> TYPE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,TYPE, java.lang.String.class, "合同类型", "合同类型", java.lang.String.class, null);
	
	/**
	 * 状态 , 类型: java.lang.String
	*/
	public static final String STATUS="status";
	
	/**
	 * 状态 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> STATUS_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,STATUS, java.lang.String.class, "状态", "状态", java.lang.String.class, null);
	
	/**
	 * 合同期限 , 月 , 类型: java.math.BigDecimal
	*/
	public static final String CONTRACT_DURATION="contractDuration";
	
	/**
	 * 合同期限 , 月 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.math.BigDecimal> CONTRACT_DURATION_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,CONTRACT_DURATION, java.math.BigDecimal.class, "合同期限", "月", java.math.BigDecimal.class, null);
	
	/**
	 * 合同年份 , 类型: java.lang.String
	*/
	public static final String CONTRACT_YEAR="contractYear";
	
	/**
	 * 合同年份 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> CONTRACT_YEAR_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,CONTRACT_YEAR, java.lang.String.class, "合同年份", "合同年份", java.lang.String.class, null);
	
	/**
	 * 是否转正 , 类型: java.lang.String
	*/
	public static final String TRANSFER_TO_REGULAR="transferToRegular";
	
	/**
	 * 是否转正 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> TRANSFER_TO_REGULAR_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,TRANSFER_TO_REGULAR, java.lang.String.class, "是否转正", "是否转正", java.lang.String.class, null);
	
	/**
	 * 试用期工资 , 类型: java.math.BigDecimal
	*/
	public static final String PROBATION_SALARY="probationSalary";
	
	/**
	 * 试用期工资 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.math.BigDecimal> PROBATION_SALARY_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,PROBATION_SALARY, java.math.BigDecimal.class, "试用期工资", "试用期工资", java.math.BigDecimal.class, null);
	
	/**
	 * 试用期生效时间 , 类型: java.util.Date
	*/
	public static final String PROBATION_START_DATE="probationStartDate";
	
	/**
	 * 试用期生效时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.util.Date> PROBATION_START_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,PROBATION_START_DATE, java.util.Date.class, "试用期生效时间", "试用期生效时间", java.util.Date.class, null);
	
	/**
	 * 试用期到期时间 , 类型: java.lang.String
	*/
	public static final String PROBATION_FINISH_DATE="probationFinishDate";
	
	/**
	 * 试用期到期时间 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> PROBATION_FINISH_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,PROBATION_FINISH_DATE, java.lang.String.class, "试用期到期时间", "试用期到期时间", java.lang.String.class, null);
	
	/**
	 * 生效时间 , 类型: java.util.Date
	*/
	public static final String CONTRACT_START_DATE="contractStartDate";
	
	/**
	 * 生效时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.util.Date> CONTRACT_START_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,CONTRACT_START_DATE, java.util.Date.class, "生效时间", "生效时间", java.util.Date.class, null);
	
	/**
	 * 到期时间 , 类型: java.util.Date
	*/
	public static final String CONTRACT_FINISH_DATE="contractFinishDate";
	
	/**
	 * 到期时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.util.Date> CONTRACT_FINISH_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,CONTRACT_FINISH_DATE, java.util.Date.class, "到期时间", "到期时间", java.util.Date.class, null);
	
	/**
	 * 工资 , 类型: java.math.BigDecimal
	*/
	public static final String SALARY="salary";
	
	/**
	 * 工资 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.math.BigDecimal> SALARY_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,SALARY, java.math.BigDecimal.class, "工资", "工资", java.math.BigDecimal.class, null);
	
	/**
	 * 合同内容 , 类型: java.lang.String
	*/
	public static final String CONTENT="content";
	
	/**
	 * 合同内容 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> CONTENT_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,CONTENT, java.lang.String.class, "合同内容", "合同内容", java.lang.String.class, null);
	
	/**
	 * 备注 , 类型: java.lang.String
	*/
	public static final String NOTES="notes";
	
	/**
	 * 备注 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> NOTES_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,NOTES, java.lang.String.class, "备注", "备注", java.lang.String.class, null);
	
	/**
	 * 合同附件 , 类型: java.lang.String
	*/
	public static final String FILE_ID="fileId";
	
	/**
	 * 合同附件 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> FILE_ID_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,FILE_ID, java.lang.String.class, "合同附件", "合同附件", java.lang.String.class, null);
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final String CREATE_BY="createBy";
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> CREATE_BY_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,CREATE_BY, java.lang.String.class, "创建人ID", "创建人ID", java.lang.String.class, null);
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final String CREATE_TIME="createTime";
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.util.Date> CREATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,CREATE_TIME, java.util.Date.class, "创建时间", "创建时间", java.util.Date.class, null);
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final String UPDATE_BY="updateBy";
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> UPDATE_BY_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,UPDATE_BY, java.lang.String.class, "修改人ID", "修改人ID", java.lang.String.class, null);
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final String UPDATE_TIME="updateTime";
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.util.Date> UPDATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,UPDATE_TIME, java.util.Date.class, "修改时间", "修改时间", java.util.Date.class, null);
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final String DELETED="deleted";
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.Integer> DELETED_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,DELETED, java.lang.Integer.class, "是否已删除", "是否已删除", java.lang.Integer.class, null);
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final String DELETE_BY="deleteBy";
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> DELETE_BY_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,DELETE_BY, java.lang.String.class, "删除人ID", "删除人ID", java.lang.String.class, null);
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final String DELETE_TIME="deleteTime";
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.util.Date> DELETE_TIME_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,DELETE_TIME, java.util.Date.class, "删除时间", "删除时间", java.util.Date.class, null);
	
	/**
	 * 数据版本号 , 类型: java.lang.Integer
	*/
	public static final String VERSION="version";
	
	/**
	 * 数据版本号 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.Integer> VERSION_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,VERSION, java.lang.Integer.class, "数据版本号", "数据版本号", java.lang.Integer.class, null);
	
	/**
	 * 租户 , 类型: java.lang.String
	*/
	public static final String TENANT_ID="tenantId";
	
	/**
	 * 租户 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> TENANT_ID_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,TENANT_ID, java.lang.String.class, "租户", "租户", java.lang.String.class, null);
	
	/**
	 * contractTypeData , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final String CONTRACT_TYPE_DATA="contractTypeData";
	
	/**
	 * contractTypeData , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,org.github.foxnic.web.domain.system.DictItem> CONTRACT_TYPE_DATA_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,CONTRACT_TYPE_DATA, org.github.foxnic.web.domain.system.DictItem.class, "contractTypeData", "contractTypeData", org.github.foxnic.web.domain.system.DictItem.class, null);
	
	/**
	 * contractYearData , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final String CONTRACT_YEAR_DATA="contractYearData";
	
	/**
	 * contractYearData , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,org.github.foxnic.web.domain.system.DictItem> CONTRACT_YEAR_DATA_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,CONTRACT_YEAR_DATA, org.github.foxnic.web.domain.system.DictItem.class, "contractYearData", "contractYearData", org.github.foxnic.web.domain.system.DictItem.class, null);
	
	/**
	 * employee , 类型: org.github.foxnic.web.domain.hrm.Employee
	*/
	public static final String EMPLOYEE="employee";
	
	/**
	 * employee , 类型: org.github.foxnic.web.domain.hrm.Employee
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,org.github.foxnic.web.domain.hrm.Employee> EMPLOYEE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,EMPLOYEE, org.github.foxnic.web.domain.hrm.Employee.class, "employee", "employee", org.github.foxnic.web.domain.hrm.Employee.class, null);
	
	/**
	 * person , 类型: com.dt.platform.domain.hr.Person
	*/
	public static final String PERSON="person";
	
	/**
	 * person , 类型: com.dt.platform.domain.hr.Person
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,com.dt.platform.domain.hr.Person> PERSON_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,PERSON, com.dt.platform.domain.hr.Person.class, "person", "person", com.dt.platform.domain.hr.Person.class, null);
	
	/**
	 * employeeName , 类型: java.lang.String
	*/
	public static final String EMPLOYEE_NAME="employeeName";
	
	/**
	 * employeeName , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> EMPLOYEE_NAME_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,EMPLOYEE_NAME, java.lang.String.class, "employeeName", "employeeName", java.lang.String.class, null);
	
	/**
	 * 全部属性清单
	*/
	public static final String[] $PROPS={ ID , EMPLOYEE_ID , BUSINESS_CODE , TYPE , STATUS , CONTRACT_DURATION , CONTRACT_YEAR , TRANSFER_TO_REGULAR , PROBATION_SALARY , PROBATION_START_DATE , PROBATION_FINISH_DATE , CONTRACT_START_DATE , CONTRACT_FINISH_DATE , SALARY , CONTENT , NOTES , FILE_ID , CREATE_BY , CREATE_TIME , UPDATE_BY , UPDATE_TIME , DELETED , DELETE_BY , DELETE_TIME , VERSION , TENANT_ID , CONTRACT_TYPE_DATA , CONTRACT_YEAR_DATA , EMPLOYEE , PERSON , EMPLOYEE_NAME };
	
	/**
	 * 代理类
	*/
	public static class $$proxy$$ extends com.dt.platform.domain.hr.PersonContract {

		private static final long serialVersionUID = 1L;

		
		/**
		 * 设置 主键
		 * @param id 主键
		 * @return 当前对象
		*/
		public PersonContract setId(String id) {
			super.change(ID,super.getId(),id);
			super.setId(id);
			return this;
		}
		
		/**
		 * 设置 人员
		 * @param employeeId 人员
		 * @return 当前对象
		*/
		public PersonContract setEmployeeId(String employeeId) {
			super.change(EMPLOYEE_ID,super.getEmployeeId(),employeeId);
			super.setEmployeeId(employeeId);
			return this;
		}
		
		/**
		 * 设置 合同编号
		 * @param businessCode 合同编号
		 * @return 当前对象
		*/
		public PersonContract setBusinessCode(String businessCode) {
			super.change(BUSINESS_CODE,super.getBusinessCode(),businessCode);
			super.setBusinessCode(businessCode);
			return this;
		}
		
		/**
		 * 设置 合同类型
		 * @param type 合同类型
		 * @return 当前对象
		*/
		public PersonContract setType(String type) {
			super.change(TYPE,super.getType(),type);
			super.setType(type);
			return this;
		}
		
		/**
		 * 设置 状态
		 * @param status 状态
		 * @return 当前对象
		*/
		public PersonContract setStatus(String status) {
			super.change(STATUS,super.getStatus(),status);
			super.setStatus(status);
			return this;
		}
		
		/**
		 * 设置 合同期限
		 * @param contractDuration 合同期限
		 * @return 当前对象
		*/
		public PersonContract setContractDuration(BigDecimal contractDuration) {
			super.change(CONTRACT_DURATION,super.getContractDuration(),contractDuration);
			super.setContractDuration(contractDuration);
			return this;
		}
		
		/**
		 * 设置 合同年份
		 * @param contractYear 合同年份
		 * @return 当前对象
		*/
		public PersonContract setContractYear(String contractYear) {
			super.change(CONTRACT_YEAR,super.getContractYear(),contractYear);
			super.setContractYear(contractYear);
			return this;
		}
		
		/**
		 * 设置 是否转正
		 * @param transferToRegular 是否转正
		 * @return 当前对象
		*/
		public PersonContract setTransferToRegular(String transferToRegular) {
			super.change(TRANSFER_TO_REGULAR,super.getTransferToRegular(),transferToRegular);
			super.setTransferToRegular(transferToRegular);
			return this;
		}
		
		/**
		 * 设置 试用期工资
		 * @param probationSalary 试用期工资
		 * @return 当前对象
		*/
		public PersonContract setProbationSalary(BigDecimal probationSalary) {
			super.change(PROBATION_SALARY,super.getProbationSalary(),probationSalary);
			super.setProbationSalary(probationSalary);
			return this;
		}
		
		/**
		 * 设置 试用期生效时间
		 * @param probationStartDate 试用期生效时间
		 * @return 当前对象
		*/
		public PersonContract setProbationStartDate(Date probationStartDate) {
			super.change(PROBATION_START_DATE,super.getProbationStartDate(),probationStartDate);
			super.setProbationStartDate(probationStartDate);
			return this;
		}
		
		/**
		 * 设置 试用期到期时间
		 * @param probationFinishDate 试用期到期时间
		 * @return 当前对象
		*/
		public PersonContract setProbationFinishDate(String probationFinishDate) {
			super.change(PROBATION_FINISH_DATE,super.getProbationFinishDate(),probationFinishDate);
			super.setProbationFinishDate(probationFinishDate);
			return this;
		}
		
		/**
		 * 设置 生效时间
		 * @param contractStartDate 生效时间
		 * @return 当前对象
		*/
		public PersonContract setContractStartDate(Date contractStartDate) {
			super.change(CONTRACT_START_DATE,super.getContractStartDate(),contractStartDate);
			super.setContractStartDate(contractStartDate);
			return this;
		}
		
		/**
		 * 设置 到期时间
		 * @param contractFinishDate 到期时间
		 * @return 当前对象
		*/
		public PersonContract setContractFinishDate(Date contractFinishDate) {
			super.change(CONTRACT_FINISH_DATE,super.getContractFinishDate(),contractFinishDate);
			super.setContractFinishDate(contractFinishDate);
			return this;
		}
		
		/**
		 * 设置 工资
		 * @param salary 工资
		 * @return 当前对象
		*/
		public PersonContract setSalary(BigDecimal salary) {
			super.change(SALARY,super.getSalary(),salary);
			super.setSalary(salary);
			return this;
		}
		
		/**
		 * 设置 合同内容
		 * @param content 合同内容
		 * @return 当前对象
		*/
		public PersonContract setContent(String content) {
			super.change(CONTENT,super.getContent(),content);
			super.setContent(content);
			return this;
		}
		
		/**
		 * 设置 备注
		 * @param notes 备注
		 * @return 当前对象
		*/
		public PersonContract setNotes(String notes) {
			super.change(NOTES,super.getNotes(),notes);
			super.setNotes(notes);
			return this;
		}
		
		/**
		 * 设置 合同附件
		 * @param fileId 合同附件
		 * @return 当前对象
		*/
		public PersonContract setFileId(String fileId) {
			super.change(FILE_ID,super.getFileId(),fileId);
			super.setFileId(fileId);
			return this;
		}
		
		/**
		 * 设置 创建人ID
		 * @param createBy 创建人ID
		 * @return 当前对象
		*/
		public PersonContract setCreateBy(String createBy) {
			super.change(CREATE_BY,super.getCreateBy(),createBy);
			super.setCreateBy(createBy);
			return this;
		}
		
		/**
		 * 设置 创建时间
		 * @param createTime 创建时间
		 * @return 当前对象
		*/
		public PersonContract setCreateTime(Date createTime) {
			super.change(CREATE_TIME,super.getCreateTime(),createTime);
			super.setCreateTime(createTime);
			return this;
		}
		
		/**
		 * 设置 修改人ID
		 * @param updateBy 修改人ID
		 * @return 当前对象
		*/
		public PersonContract setUpdateBy(String updateBy) {
			super.change(UPDATE_BY,super.getUpdateBy(),updateBy);
			super.setUpdateBy(updateBy);
			return this;
		}
		
		/**
		 * 设置 修改时间
		 * @param updateTime 修改时间
		 * @return 当前对象
		*/
		public PersonContract setUpdateTime(Date updateTime) {
			super.change(UPDATE_TIME,super.getUpdateTime(),updateTime);
			super.setUpdateTime(updateTime);
			return this;
		}
		
		/**
		 * 设置 是否已删除
		 * @param deleted 是否已删除
		 * @return 当前对象
		*/
		public PersonContract setDeleted(Integer deleted) {
			super.change(DELETED,super.getDeleted(),deleted);
			super.setDeleted(deleted);
			return this;
		}
		
		/**
		 * 设置 删除人ID
		 * @param deleteBy 删除人ID
		 * @return 当前对象
		*/
		public PersonContract setDeleteBy(String deleteBy) {
			super.change(DELETE_BY,super.getDeleteBy(),deleteBy);
			super.setDeleteBy(deleteBy);
			return this;
		}
		
		/**
		 * 设置 删除时间
		 * @param deleteTime 删除时间
		 * @return 当前对象
		*/
		public PersonContract setDeleteTime(Date deleteTime) {
			super.change(DELETE_TIME,super.getDeleteTime(),deleteTime);
			super.setDeleteTime(deleteTime);
			return this;
		}
		
		/**
		 * 设置 数据版本号
		 * @param version 数据版本号
		 * @return 当前对象
		*/
		public PersonContract setVersion(Integer version) {
			super.change(VERSION,super.getVersion(),version);
			super.setVersion(version);
			return this;
		}
		
		/**
		 * 设置 租户
		 * @param tenantId 租户
		 * @return 当前对象
		*/
		public PersonContract setTenantId(String tenantId) {
			super.change(TENANT_ID,super.getTenantId(),tenantId);
			super.setTenantId(tenantId);
			return this;
		}
		
		/**
		 * 设置 contractTypeData
		 * @param contractTypeData contractTypeData
		 * @return 当前对象
		*/
		public PersonContract setContractTypeData(DictItem contractTypeData) {
			super.change(CONTRACT_TYPE_DATA,super.getContractTypeData(),contractTypeData);
			super.setContractTypeData(contractTypeData);
			return this;
		}
		
		/**
		 * 设置 contractYearData
		 * @param contractYearData contractYearData
		 * @return 当前对象
		*/
		public PersonContract setContractYearData(DictItem contractYearData) {
			super.change(CONTRACT_YEAR_DATA,super.getContractYearData(),contractYearData);
			super.setContractYearData(contractYearData);
			return this;
		}
		
		/**
		 * 设置 employee
		 * @param employee employee
		 * @return 当前对象
		*/
		public PersonContract setEmployee(Employee employee) {
			super.change(EMPLOYEE,super.getEmployee(),employee);
			super.setEmployee(employee);
			return this;
		}
		
		/**
		 * 设置 person
		 * @param person person
		 * @return 当前对象
		*/
		public PersonContract setPerson(Person person) {
			super.change(PERSON,super.getPerson(),person);
			super.setPerson(person);
			return this;
		}
		
		/**
		 * 设置 employeeName
		 * @param employeeName employeeName
		 * @return 当前对象
		*/
		public PersonContract setEmployeeName(String employeeName) {
			super.change(EMPLOYEE_NAME,super.getEmployeeName(),employeeName);
			super.setEmployeeName(employeeName);
			return this;
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
			$$proxy$$ inst=new $$proxy$$();
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

	}
}