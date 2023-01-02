package com.dt.platform.domain.hr.meta;

import com.github.foxnic.api.bean.BeanProperty;
import com.dt.platform.domain.hr.PersonContractVO;
import java.util.List;
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
 * @sign BD9FE9BBEF290611FF2A8BB084DA579F
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

public class PersonContractVOMeta extends PersonContractMeta {
	
	/**
	 * 页码 , 类型: java.lang.Integer
	*/
	public static final String PAGE_INDEX="pageIndex";
	
	/**
	 * 页码 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.Integer> PAGE_INDEX_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,PAGE_INDEX, java.lang.Integer.class, "页码", "", java.lang.Integer.class, null);
	
	/**
	 * 分页大小 , 类型: java.lang.Integer
	*/
	public static final String PAGE_SIZE="pageSize";
	
	/**
	 * 分页大小 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.Integer> PAGE_SIZE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,PAGE_SIZE, java.lang.Integer.class, "分页大小", "", java.lang.Integer.class, null);
	
	/**
	 * 搜索字段 , 类型: java.lang.String
	*/
	public static final String SEARCH_FIELD="searchField";
	
	/**
	 * 搜索字段 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> SEARCH_FIELD_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,SEARCH_FIELD, java.lang.String.class, "搜索字段", "", java.lang.String.class, null);
	
	/**
	 * 模糊搜索字段 , 类型: java.lang.String
	*/
	public static final String FUZZY_FIELD="fuzzyField";
	
	/**
	 * 模糊搜索字段 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> FUZZY_FIELD_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,FUZZY_FIELD, java.lang.String.class, "模糊搜索字段", "", java.lang.String.class, null);
	
	/**
	 * 搜索的值 , 类型: java.lang.String
	*/
	public static final String SEARCH_VALUE="searchValue";
	
	/**
	 * 搜索的值 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> SEARCH_VALUE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,SEARCH_VALUE, java.lang.String.class, "搜索的值", "", java.lang.String.class, null);
	
	/**
	 * 已修改字段 , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final String DIRTY_FIELDS="dirtyFields";
	
	/**
	 * 已修改字段 , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> DIRTY_FIELDS_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,DIRTY_FIELDS, java.util.List.class, "已修改字段", "", java.lang.String.class, null);
	
	/**
	 * 排序字段 , 类型: java.lang.String
	*/
	public static final String SORT_FIELD="sortField";
	
	/**
	 * 排序字段 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> SORT_FIELD_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,SORT_FIELD, java.lang.String.class, "排序字段", "", java.lang.String.class, null);
	
	/**
	 * 排序方式 , 类型: java.lang.String
	*/
	public static final String SORT_TYPE="sortType";
	
	/**
	 * 排序方式 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> SORT_TYPE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,SORT_TYPE, java.lang.String.class, "排序方式", "", java.lang.String.class, null);
	
	/**
	 * 数据来源 , 前端指定不同的来源，后端按来源执行不同的逻辑 , 类型: java.lang.String
	*/
	public static final String DATA_ORIGIN="dataOrigin";
	
	/**
	 * 数据来源 , 前端指定不同的来源，后端按来源执行不同的逻辑 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> DATA_ORIGIN_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,DATA_ORIGIN, java.lang.String.class, "数据来源", "前端指定不同的来源，后端按来源执行不同的逻辑", java.lang.String.class, null);
	
	/**
	 * 查询逻辑 , 默认and，可指定 or  , 类型: java.lang.String
	*/
	public static final String QUERY_LOGIC="queryLogic";
	
	/**
	 * 查询逻辑 , 默认and，可指定 or  , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> QUERY_LOGIC_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,QUERY_LOGIC, java.lang.String.class, "查询逻辑", "默认and，可指定 or ", java.lang.String.class, null);
	
	/**
	 * 主键清单 , 用于接收批量主键参数 , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final String IDS="ids";
	
	/**
	 * 主键清单 , 用于接收批量主键参数 , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> IDS_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,IDS, java.util.List.class, "主键清单", "用于接收批量主键参数", java.lang.String.class, null);
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final String ID="id";
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> ID_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,ID, java.lang.String.class, "主键", "主键", java.lang.String.class, null);
	
	/**
	 * 人员 , 类型: java.lang.String
	*/
	public static final String EMPLOYEE_ID="employeeId";
	
	/**
	 * 人员 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> EMPLOYEE_ID_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,EMPLOYEE_ID, java.lang.String.class, "人员", "人员", java.lang.String.class, null);
	
	/**
	 * 合同编号 , 类型: java.lang.String
	*/
	public static final String BUSINESS_CODE="businessCode";
	
	/**
	 * 合同编号 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> BUSINESS_CODE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,BUSINESS_CODE, java.lang.String.class, "合同编号", "合同编号", java.lang.String.class, null);
	
	/**
	 * 合同类型 , 类型: java.lang.String
	*/
	public static final String TYPE="type";
	
	/**
	 * 合同类型 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> TYPE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,TYPE, java.lang.String.class, "合同类型", "合同类型", java.lang.String.class, null);
	
	/**
	 * 状态 , 类型: java.lang.String
	*/
	public static final String STATUS="status";
	
	/**
	 * 状态 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> STATUS_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,STATUS, java.lang.String.class, "状态", "状态", java.lang.String.class, null);
	
	/**
	 * 合同期限 , 月 , 类型: java.math.BigDecimal
	*/
	public static final String CONTRACT_DURATION="contractDuration";
	
	/**
	 * 合同期限 , 月 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.math.BigDecimal> CONTRACT_DURATION_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,CONTRACT_DURATION, java.math.BigDecimal.class, "合同期限", "月", java.math.BigDecimal.class, null);
	
	/**
	 * 合同年份 , 类型: java.lang.String
	*/
	public static final String CONTRACT_YEAR="contractYear";
	
	/**
	 * 合同年份 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> CONTRACT_YEAR_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,CONTRACT_YEAR, java.lang.String.class, "合同年份", "合同年份", java.lang.String.class, null);
	
	/**
	 * 是否转正 , 类型: java.lang.String
	*/
	public static final String TRANSFER_TO_REGULAR="transferToRegular";
	
	/**
	 * 是否转正 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> TRANSFER_TO_REGULAR_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,TRANSFER_TO_REGULAR, java.lang.String.class, "是否转正", "是否转正", java.lang.String.class, null);
	
	/**
	 * 试用期工资 , 类型: java.math.BigDecimal
	*/
	public static final String PROBATION_SALARY="probationSalary";
	
	/**
	 * 试用期工资 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.math.BigDecimal> PROBATION_SALARY_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,PROBATION_SALARY, java.math.BigDecimal.class, "试用期工资", "试用期工资", java.math.BigDecimal.class, null);
	
	/**
	 * 试用期生效时间 , 类型: java.util.Date
	*/
	public static final String PROBATION_START_DATE="probationStartDate";
	
	/**
	 * 试用期生效时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.util.Date> PROBATION_START_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,PROBATION_START_DATE, java.util.Date.class, "试用期生效时间", "试用期生效时间", java.util.Date.class, null);
	
	/**
	 * 试用期到期时间 , 类型: java.lang.String
	*/
	public static final String PROBATION_FINISH_DATE="probationFinishDate";
	
	/**
	 * 试用期到期时间 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> PROBATION_FINISH_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,PROBATION_FINISH_DATE, java.lang.String.class, "试用期到期时间", "试用期到期时间", java.lang.String.class, null);
	
	/**
	 * 生效时间 , 类型: java.util.Date
	*/
	public static final String CONTRACT_START_DATE="contractStartDate";
	
	/**
	 * 生效时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.util.Date> CONTRACT_START_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,CONTRACT_START_DATE, java.util.Date.class, "生效时间", "生效时间", java.util.Date.class, null);
	
	/**
	 * 到期时间 , 类型: java.util.Date
	*/
	public static final String CONTRACT_FINISH_DATE="contractFinishDate";
	
	/**
	 * 到期时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.util.Date> CONTRACT_FINISH_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,CONTRACT_FINISH_DATE, java.util.Date.class, "到期时间", "到期时间", java.util.Date.class, null);
	
	/**
	 * 工资 , 类型: java.math.BigDecimal
	*/
	public static final String SALARY="salary";
	
	/**
	 * 工资 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.math.BigDecimal> SALARY_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,SALARY, java.math.BigDecimal.class, "工资", "工资", java.math.BigDecimal.class, null);
	
	/**
	 * 合同内容 , 类型: java.lang.String
	*/
	public static final String CONTENT="content";
	
	/**
	 * 合同内容 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> CONTENT_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,CONTENT, java.lang.String.class, "合同内容", "合同内容", java.lang.String.class, null);
	
	/**
	 * 备注 , 类型: java.lang.String
	*/
	public static final String NOTES="notes";
	
	/**
	 * 备注 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> NOTES_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,NOTES, java.lang.String.class, "备注", "备注", java.lang.String.class, null);
	
	/**
	 * 合同附件 , 类型: java.lang.String
	*/
	public static final String FILE_ID="fileId";
	
	/**
	 * 合同附件 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> FILE_ID_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,FILE_ID, java.lang.String.class, "合同附件", "合同附件", java.lang.String.class, null);
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final String CREATE_BY="createBy";
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> CREATE_BY_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,CREATE_BY, java.lang.String.class, "创建人ID", "创建人ID", java.lang.String.class, null);
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final String CREATE_TIME="createTime";
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.util.Date> CREATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,CREATE_TIME, java.util.Date.class, "创建时间", "创建时间", java.util.Date.class, null);
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final String UPDATE_BY="updateBy";
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> UPDATE_BY_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,UPDATE_BY, java.lang.String.class, "修改人ID", "修改人ID", java.lang.String.class, null);
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final String UPDATE_TIME="updateTime";
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.util.Date> UPDATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,UPDATE_TIME, java.util.Date.class, "修改时间", "修改时间", java.util.Date.class, null);
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final String DELETED="deleted";
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.Integer> DELETED_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,DELETED, java.lang.Integer.class, "是否已删除", "是否已删除", java.lang.Integer.class, null);
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final String DELETE_BY="deleteBy";
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> DELETE_BY_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,DELETE_BY, java.lang.String.class, "删除人ID", "删除人ID", java.lang.String.class, null);
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final String DELETE_TIME="deleteTime";
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.util.Date> DELETE_TIME_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,DELETE_TIME, java.util.Date.class, "删除时间", "删除时间", java.util.Date.class, null);
	
	/**
	 * 数据版本号 , 类型: java.lang.Integer
	*/
	public static final String VERSION="version";
	
	/**
	 * 数据版本号 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.Integer> VERSION_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,VERSION, java.lang.Integer.class, "数据版本号", "数据版本号", java.lang.Integer.class, null);
	
	/**
	 * 租户 , 类型: java.lang.String
	*/
	public static final String TENANT_ID="tenantId";
	
	/**
	 * 租户 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> TENANT_ID_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,TENANT_ID, java.lang.String.class, "租户", "租户", java.lang.String.class, null);
	
	/**
	 * contractTypeData , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final String CONTRACT_TYPE_DATA="contractTypeData";
	
	/**
	 * contractTypeData , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,org.github.foxnic.web.domain.system.DictItem> CONTRACT_TYPE_DATA_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,CONTRACT_TYPE_DATA, org.github.foxnic.web.domain.system.DictItem.class, "contractTypeData", "contractTypeData", org.github.foxnic.web.domain.system.DictItem.class, null);
	
	/**
	 * contractYearData , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final String CONTRACT_YEAR_DATA="contractYearData";
	
	/**
	 * contractYearData , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,org.github.foxnic.web.domain.system.DictItem> CONTRACT_YEAR_DATA_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,CONTRACT_YEAR_DATA, org.github.foxnic.web.domain.system.DictItem.class, "contractYearData", "contractYearData", org.github.foxnic.web.domain.system.DictItem.class, null);
	
	/**
	 * employee , 类型: org.github.foxnic.web.domain.hrm.Employee
	*/
	public static final String EMPLOYEE="employee";
	
	/**
	 * employee , 类型: org.github.foxnic.web.domain.hrm.Employee
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,org.github.foxnic.web.domain.hrm.Employee> EMPLOYEE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,EMPLOYEE, org.github.foxnic.web.domain.hrm.Employee.class, "employee", "employee", org.github.foxnic.web.domain.hrm.Employee.class, null);
	
	/**
	 * person , 类型: com.dt.platform.domain.hr.Person
	*/
	public static final String PERSON="person";
	
	/**
	 * person , 类型: com.dt.platform.domain.hr.Person
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,com.dt.platform.domain.hr.Person> PERSON_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,PERSON, com.dt.platform.domain.hr.Person.class, "person", "person", com.dt.platform.domain.hr.Person.class, null);
	
	/**
	 * employeeName , 类型: java.lang.String
	*/
	public static final String EMPLOYEE_NAME="employeeName";
	
	/**
	 * employeeName , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContractVO,java.lang.String> EMPLOYEE_NAME_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContractVO.class ,EMPLOYEE_NAME, java.lang.String.class, "employeeName", "employeeName", java.lang.String.class, null);
	
	/**
	 * 全部属性清单
	*/
	public static final String[] $PROPS={ PAGE_INDEX , PAGE_SIZE , SEARCH_FIELD , FUZZY_FIELD , SEARCH_VALUE , DIRTY_FIELDS , SORT_FIELD , SORT_TYPE , DATA_ORIGIN , QUERY_LOGIC , IDS , ID , EMPLOYEE_ID , BUSINESS_CODE , TYPE , STATUS , CONTRACT_DURATION , CONTRACT_YEAR , TRANSFER_TO_REGULAR , PROBATION_SALARY , PROBATION_START_DATE , PROBATION_FINISH_DATE , CONTRACT_START_DATE , CONTRACT_FINISH_DATE , SALARY , CONTENT , NOTES , FILE_ID , CREATE_BY , CREATE_TIME , UPDATE_BY , UPDATE_TIME , DELETED , DELETE_BY , DELETE_TIME , VERSION , TENANT_ID , CONTRACT_TYPE_DATA , CONTRACT_YEAR_DATA , EMPLOYEE , PERSON , EMPLOYEE_NAME };
	
	/**
	 * 代理类
	*/
	public static class $$proxy$$ extends com.dt.platform.domain.hr.PersonContractVO {

		private static final long serialVersionUID = 1L;

		
		/**
		 * 设置 页码
		 * @param pageIndex 页码
		 * @return 当前对象
		*/
		public PersonContractVO setPageIndex(Integer pageIndex) {
			super.change(PAGE_INDEX,super.getPageIndex(),pageIndex);
			super.setPageIndex(pageIndex);
			return this;
		}
		
		/**
		 * 设置 分页大小
		 * @param pageSize 分页大小
		 * @return 当前对象
		*/
		public PersonContractVO setPageSize(Integer pageSize) {
			super.change(PAGE_SIZE,super.getPageSize(),pageSize);
			super.setPageSize(pageSize);
			return this;
		}
		
		/**
		 * 设置 搜索字段
		 * @param searchField 搜索字段
		 * @return 当前对象
		*/
		public PersonContractVO setSearchField(String searchField) {
			super.change(SEARCH_FIELD,super.getSearchField(),searchField);
			super.setSearchField(searchField);
			return this;
		}
		
		/**
		 * 设置 模糊搜索字段
		 * @param fuzzyField 模糊搜索字段
		 * @return 当前对象
		*/
		public PersonContractVO setFuzzyField(String fuzzyField) {
			super.change(FUZZY_FIELD,super.getFuzzyField(),fuzzyField);
			super.setFuzzyField(fuzzyField);
			return this;
		}
		
		/**
		 * 设置 搜索的值
		 * @param searchValue 搜索的值
		 * @return 当前对象
		*/
		public PersonContractVO setSearchValue(String searchValue) {
			super.change(SEARCH_VALUE,super.getSearchValue(),searchValue);
			super.setSearchValue(searchValue);
			return this;
		}
		
		/**
		 * 设置 已修改字段
		 * @param dirtyFields 已修改字段
		 * @return 当前对象
		*/
		public PersonContractVO setDirtyFields(List<String> dirtyFields) {
			super.change(DIRTY_FIELDS,super.getDirtyFields(),dirtyFields);
			super.setDirtyFields(dirtyFields);
			return this;
		}
		
		/**
		 * 设置 排序字段
		 * @param sortField 排序字段
		 * @return 当前对象
		*/
		public PersonContractVO setSortField(String sortField) {
			super.change(SORT_FIELD,super.getSortField(),sortField);
			super.setSortField(sortField);
			return this;
		}
		
		/**
		 * 设置 排序方式
		 * @param sortType 排序方式
		 * @return 当前对象
		*/
		public PersonContractVO setSortType(String sortType) {
			super.change(SORT_TYPE,super.getSortType(),sortType);
			super.setSortType(sortType);
			return this;
		}
		
		/**
		 * 设置 数据来源
		 * @param dataOrigin 数据来源
		 * @return 当前对象
		*/
		public PersonContractVO setDataOrigin(String dataOrigin) {
			super.change(DATA_ORIGIN,super.getDataOrigin(),dataOrigin);
			super.setDataOrigin(dataOrigin);
			return this;
		}
		
		/**
		 * 设置 查询逻辑
		 * @param queryLogic 查询逻辑
		 * @return 当前对象
		*/
		public PersonContractVO setQueryLogic(String queryLogic) {
			super.change(QUERY_LOGIC,super.getQueryLogic(),queryLogic);
			super.setQueryLogic(queryLogic);
			return this;
		}
		
		/**
		 * 设置 主键清单
		 * @param ids 主键清单
		 * @return 当前对象
		*/
		public PersonContractVO setIds(List<String> ids) {
			super.change(IDS,super.getIds(),ids);
			super.setIds(ids);
			return this;
		}
		
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
		public PersonContractVO clone() {
			return duplicate(true);
		}

		/**
		 * 复制当前对象
		 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
		*/
		@Transient
		public PersonContractVO duplicate(boolean all) {
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
				inst.setEmployeeName(this.getEmployeeName());
				inst.setSearchField(this.getSearchField());
				inst.setFuzzyField(this.getFuzzyField());
				inst.setPageSize(this.getPageSize());
				inst.setEmployee(this.getEmployee());
				inst.setContractTypeData(this.getContractTypeData());
				inst.setPageIndex(this.getPageIndex());
				inst.setSortType(this.getSortType());
				inst.setPerson(this.getPerson());
				inst.setContractYearData(this.getContractYearData());
				inst.setDirtyFields(this.getDirtyFields());
				inst.setSortField(this.getSortField());
				inst.setDataOrigin(this.getDataOrigin());
				inst.setIds(this.getIds());
				inst.setQueryLogic(this.getQueryLogic());
				inst.setSearchValue(this.getSearchValue());
			}
			inst.clearModifies();
			return inst;
		}

	}
}