package com.dt.platform.domain.hr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import com.github.foxnic.api.model.CompositeParameter;
import javax.persistence.Transient;
import com.github.foxnic.commons.bean.BeanUtil;
import com.github.foxnic.dao.entity.EntityContext;
import com.github.foxnic.dao.entity.Entity;
import java.util.Map;
import com.dt.platform.domain.hr.meta.PersonVOMeta;
import com.github.foxnic.commons.lang.DataParser;
import java.util.Date;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.hrm.Employee;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 人员信息VO类型
 * <p>人员信息 , 数据表 hr_person 的通用VO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 15:21:55
 * @sign A897D79572BDA58814D64EEDB389EFD6
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@ApiModel(description = "人员信息VO类型 ; 人员信息 , 数据表 hr_person 的通用VO类型" , parent = Person.class)
public class PersonVO extends Person {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 页码
	*/
	@ApiModelProperty(required = false,value="页码" , notes = "")
	private Integer pageIndex;
	
	/**
	 * 分页大小
	*/
	@ApiModelProperty(required = false,value="分页大小" , notes = "")
	private Integer pageSize;
	
	/**
	 * 搜索字段
	*/
	@ApiModelProperty(required = false,value="搜索字段" , notes = "")
	private String searchField;
	
	/**
	 * 模糊搜索字段
	*/
	@ApiModelProperty(required = false,value="模糊搜索字段" , notes = "")
	private String fuzzyField;
	
	/**
	 * 搜索的值
	*/
	@ApiModelProperty(required = false,value="搜索的值" , notes = "")
	private String searchValue;
	
	/**
	 * 已修改字段
	*/
	@ApiModelProperty(required = false,value="已修改字段" , notes = "")
	private List<String> dirtyFields;
	
	/**
	 * 排序字段
	*/
	@ApiModelProperty(required = false,value="排序字段" , notes = "")
	private String sortField;
	
	/**
	 * 排序方式
	*/
	@ApiModelProperty(required = false,value="排序方式" , notes = "")
	private String sortType;
	
	/**
	 * 数据来源：前端指定不同的来源，后端按来源执行不同的逻辑
	*/
	@ApiModelProperty(required = false,value="数据来源" , notes = "前端指定不同的来源，后端按来源执行不同的逻辑")
	private String dataOrigin;
	
	/**
	 * 查询逻辑：默认and，可指定 or 
	*/
	@ApiModelProperty(required = false,value="查询逻辑" , notes = "默认and，可指定 or ")
	private String queryLogic;
	
	/**
	 * 主键清单：用于接收批量主键参数
	*/
	@ApiModelProperty(required = false,value="主键清单" , notes = "用于接收批量主键参数")
	private List<String> ids;
	
	/**
	 * 获得 页码<br>
	 * @return 页码
	*/
	public Integer getPageIndex() {
		return pageIndex;
	}
	
	/**
	 * 设置 页码
	 * @param pageIndex 页码
	 * @return 当前对象
	*/
	public PersonVO setPageIndex(Integer pageIndex) {
		this.pageIndex=pageIndex;
		return this;
	}
	
	/**
	 * 获得 分页大小<br>
	 * @return 分页大小
	*/
	public Integer getPageSize() {
		return pageSize;
	}
	
	/**
	 * 设置 分页大小
	 * @param pageSize 分页大小
	 * @return 当前对象
	*/
	public PersonVO setPageSize(Integer pageSize) {
		this.pageSize=pageSize;
		return this;
	}
	
	/**
	 * 获得 搜索字段<br>
	 * @return 搜索字段
	*/
	public String getSearchField() {
		return searchField;
	}
	
	/**
	 * 设置 搜索字段
	 * @param searchField 搜索字段
	 * @return 当前对象
	*/
	public PersonVO setSearchField(String searchField) {
		this.searchField=searchField;
		return this;
	}
	
	/**
	 * 获得 模糊搜索字段<br>
	 * @return 模糊搜索字段
	*/
	public String getFuzzyField() {
		return fuzzyField;
	}
	
	/**
	 * 设置 模糊搜索字段
	 * @param fuzzyField 模糊搜索字段
	 * @return 当前对象
	*/
	public PersonVO setFuzzyField(String fuzzyField) {
		this.fuzzyField=fuzzyField;
		return this;
	}
	
	/**
	 * 获得 搜索的值<br>
	 * @return 搜索的值
	*/
	public String getSearchValue() {
		return searchValue;
	}
	
	/**
	 * 设置 搜索的值
	 * @param searchValue 搜索的值
	 * @return 当前对象
	*/
	public PersonVO setSearchValue(String searchValue) {
		this.searchValue=searchValue;
		return this;
	}
	
	/**
	 * 获得 已修改字段<br>
	 * @return 已修改字段
	*/
	public List<String> getDirtyFields() {
		return dirtyFields;
	}
	
	/**
	 * 设置 已修改字段
	 * @param dirtyFields 已修改字段
	 * @return 当前对象
	*/
	public PersonVO setDirtyFields(List<String> dirtyFields) {
		this.dirtyFields=dirtyFields;
		return this;
	}
	
	/**
	 * 添加 已修改字段
	 * @param dirtyField 已修改字段
	 * @return 当前对象
	*/
	public PersonVO addDirtyField(String... dirtyField) {
		if(this.dirtyFields==null) dirtyFields=new ArrayList<>();
		this.dirtyFields.addAll(Arrays.asList(dirtyField));
		return this;
	}
	
	/**
	 * 获得 排序字段<br>
	 * @return 排序字段
	*/
	public String getSortField() {
		return sortField;
	}
	
	/**
	 * 设置 排序字段
	 * @param sortField 排序字段
	 * @return 当前对象
	*/
	public PersonVO setSortField(String sortField) {
		this.sortField=sortField;
		return this;
	}
	
	/**
	 * 获得 排序方式<br>
	 * @return 排序方式
	*/
	public String getSortType() {
		return sortType;
	}
	
	/**
	 * 设置 排序方式
	 * @param sortType 排序方式
	 * @return 当前对象
	*/
	public PersonVO setSortType(String sortType) {
		this.sortType=sortType;
		return this;
	}
	
	/**
	 * 获得 数据来源<br>
	 * 前端指定不同的来源，后端按来源执行不同的逻辑
	 * @return 数据来源
	*/
	public String getDataOrigin() {
		return dataOrigin;
	}
	
	/**
	 * 设置 数据来源
	 * @param dataOrigin 数据来源
	 * @return 当前对象
	*/
	public PersonVO setDataOrigin(String dataOrigin) {
		this.dataOrigin=dataOrigin;
		return this;
	}
	
	/**
	 * 获得 查询逻辑<br>
	 * 默认and，可指定 or 
	 * @return 查询逻辑
	*/
	public String getQueryLogic() {
		return queryLogic;
	}
	
	/**
	 * 设置 查询逻辑
	 * @param queryLogic 查询逻辑
	 * @return 当前对象
	*/
	public PersonVO setQueryLogic(String queryLogic) {
		this.queryLogic=queryLogic;
		return this;
	}
	
	/**
	 * 获得 主键清单<br>
	 * 用于接收批量主键参数
	 * @return 主键清单
	*/
	public List<String> getIds() {
		return ids;
	}
	
	/**
	 * 设置 主键清单
	 * @param ids 主键清单
	 * @return 当前对象
	*/
	public PersonVO setIds(List<String> ids) {
		this.ids=ids;
		return this;
	}
	
	/**
	 * 添加 主键清单
	 * @param id 主键清单
	 * @return 当前对象
	*/
	public PersonVO addId(String... id) {
		if(this.ids==null) ids=new ArrayList<>();
		this.ids.addAll(Arrays.asList(id));
		return this;
	}
	@Transient
	private transient CompositeParameter $compositeParameter;
	/**
	 * 获得解析后的复合查询参数
	 */
	@Transient
	public CompositeParameter getCompositeParameter() {
		if($compositeParameter!=null) return  $compositeParameter;
		$compositeParameter=new CompositeParameter(this.getSearchValue(),BeanUtil.toMap(this));
		return  $compositeParameter;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return PersonVO , 转换好的 PersonVO 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return PersonVO , 转换好的 PoJo 对象
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
	public PersonVO clone() {
		return duplicate(true);
	}

	/**
	 * 复制当前对象
	 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
	*/
	@Transient
	public PersonVO duplicate(boolean all) {
		com.dt.platform.domain.hr.meta.PersonVOMeta.$$proxy$$ inst = new com.dt.platform.domain.hr.meta.PersonVOMeta.$$proxy$$();
		inst.setRankCode(this.getRankCode());
		inst.setEmergencyContactNo(this.getEmergencyContactNo());
		inst.setEmploymentConfirmDate(this.getEmploymentConfirmDate());
		inst.setNativePlaceCode(this.getNativePlaceCode());
		inst.setMajor(this.getMajor());
		inst.setComputerLevel(this.getComputerLevel());
		inst.setWorkKindCode(this.getWorkKindCode());
		inst.setId(this.getId());
		inst.setPayrollCardBankCode(this.getPayrollCardBankCode());
		inst.setHomeAddress(this.getHomeAddress());
		inst.setEmployeeTitleCode(this.getEmployeeTitleCode());
		inst.setForeignLanguageLevel(this.getForeignLanguageLevel());
		inst.setEmergencyContact(this.getEmergencyContact());
		inst.setGraduationSchool(this.getGraduationSchool());
		inst.setEmploymentDate(this.getEmploymentDate());
		inst.setContractStartDate(this.getContractStartDate());
		inst.setIdentityCard(this.getIdentityCard());
		inst.setVersion(this.getVersion());
		inst.setDeleteTime(this.getDeleteTime());
		inst.setName(this.getName());
		inst.setForeignLanguage(this.getForeignLanguage());
		inst.setFirstEmploymentDate(this.getFirstEmploymentDate());
		inst.setDeleteBy(this.getDeleteBy());
		inst.setJobNumber(this.getJobNumber());
		inst.setMaritalStatus(this.getMaritalStatus());
		inst.setFileId(this.getFileId());
		inst.setBirthday(this.getBirthday());
		inst.setNote(this.getNote());
		inst.setContractDuration(this.getContractDuration());
		inst.setContractFinishDate(this.getContractFinishDate());
		inst.setBodyHeight(this.getBodyHeight());
		inst.setPositionCode(this.getPositionCode());
		inst.setEducationCode(this.getEducationCode());
		inst.setComputerAbility(this.getComputerAbility());
		inst.setBloodType(this.getBloodType());
		inst.setEmployeeStatus(this.getEmployeeStatus());
		inst.setOrgId(this.getOrgId());
		inst.setUpdateBy(this.getUpdateBy());
		inst.setPersonPictureId(this.getPersonPictureId());
		inst.setEmail(this.getEmail());
		inst.setNationCode(this.getNationCode());
		inst.setFirstWorkDate(this.getFirstWorkDate());
		inst.setPayrollCard(this.getPayrollCard());
		inst.setWeixinId(this.getWeixinId());
		inst.setEmployeeId(this.getEmployeeId());
		inst.setUpdateTime(this.getUpdateTime());
		inst.setJoinPartDate(this.getJoinPartDate());
		inst.setLeaveReson(this.getLeaveReson());
		inst.setSexCode(this.getSexCode());
		inst.setCreateBy(this.getCreateBy());
		inst.setContactInformation(this.getContactInformation());
		inst.setDeleted(this.getDeleted());
		inst.setGraduationDate(this.getGraduationDate());
		inst.setCreateTime(this.getCreateTime());
		inst.setEmployeeTypeCode(this.getEmployeeTypeCode());
		inst.setLeaveDate(this.getLeaveDate());
		inst.setTenantId(this.getTenantId());
		inst.setPoliticCountenanceCode(this.getPoliticCountenanceCode());
		inst.setBodyWeight(this.getBodyWeight());
		if(all) {
			inst.setPoliticCountenanceData(this.getPoliticCountenanceData());
			inst.setSearchField(this.getSearchField());
			inst.setFuzzyField(this.getFuzzyField());
			inst.setProfessionalLevel(this.getProfessionalLevel());
			inst.setPageSize(this.getPageSize());
			inst.setEmployeeOwnerTypeDict(this.getEmployeeOwnerTypeDict());
			inst.setEmployee(this.getEmployee());
			inst.setEducationData(this.getEducationData());
			inst.setBloodTypeDict(this.getBloodTypeDict());
			inst.setPageIndex(this.getPageIndex());
			inst.setSortType(this.getSortType());
			inst.setDirtyFields(this.getDirtyFields());
			inst.setSortField(this.getSortField());
			inst.setRank(this.getRank());
			inst.setDataOrigin(this.getDataOrigin());
			inst.setIds(this.getIds());
			inst.setQueryLogic(this.getQueryLogic());
			inst.setPosition(this.getPosition());
			inst.setSexDict(this.getSexDict());
			inst.setSearchValue(this.getSearchValue());
			inst.setMaritalStatusDict(this.getMaritalStatusDict());
		}
		inst.clearModifies();
		return inst;
	}

	/**
	 * 克隆当前对象
	*/
	@Transient
	public PersonVO clone(boolean deep) {
		return EntityContext.clone(PersonVO.class,this,deep);
	}

	/**
	 * 将 Map 转换成 PersonVO
	 * @param personMap 包含实体信息的 Map 对象
	 * @return PersonVO , 转换好的的 Person 对象
	*/
	@Transient
	public static PersonVO createFrom(Map<String,Object> personMap) {
		if(personMap==null) return null;
		PersonVO vo = create();
		EntityContext.copyProperties(vo,personMap);
		vo.clearModifies();
		return vo;
	}

	/**
	 * 将 Pojo 转换成 PersonVO
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return PersonVO , 转换好的的 Person 对象
	*/
	@Transient
	public static PersonVO createFrom(Object pojo) {
		if(pojo==null) return null;
		PersonVO vo = create();
		EntityContext.copyProperties(vo,pojo);
		vo.clearModifies();
		return vo;
	}

	/**
	 * 创建一个 PersonVO，等同于 new
	 * @return PersonVO 对象
	*/
	@Transient
	public static PersonVO create() {
		return new com.dt.platform.domain.hr.meta.PersonVOMeta.$$proxy$$();
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
			this.setRankCode(DataParser.parse(String.class, map.get(PersonVOMeta.RANK_CODE)));
			this.setEmergencyContactNo(DataParser.parse(String.class, map.get(PersonVOMeta.EMERGENCY_CONTACT_NO)));
			this.setEmploymentConfirmDate(DataParser.parse(Date.class, map.get(PersonVOMeta.EMPLOYMENT_CONFIRM_DATE)));
			this.setNativePlaceCode(DataParser.parse(String.class, map.get(PersonVOMeta.NATIVE_PLACE_CODE)));
			this.setMajor(DataParser.parse(String.class, map.get(PersonVOMeta.MAJOR)));
			this.setComputerLevel(DataParser.parse(String.class, map.get(PersonVOMeta.COMPUTER_LEVEL)));
			this.setWorkKindCode(DataParser.parse(String.class, map.get(PersonVOMeta.WORK_KIND_CODE)));
			this.setId(DataParser.parse(String.class, map.get(PersonVOMeta.ID)));
			this.setPayrollCardBankCode(DataParser.parse(String.class, map.get(PersonVOMeta.PAYROLL_CARD_BANK_CODE)));
			this.setHomeAddress(DataParser.parse(String.class, map.get(PersonVOMeta.HOME_ADDRESS)));
			this.setEmployeeTitleCode(DataParser.parse(String.class, map.get(PersonVOMeta.EMPLOYEE_TITLE_CODE)));
			this.setForeignLanguageLevel(DataParser.parse(String.class, map.get(PersonVOMeta.FOREIGN_LANGUAGE_LEVEL)));
			this.setEmergencyContact(DataParser.parse(String.class, map.get(PersonVOMeta.EMERGENCY_CONTACT)));
			this.setGraduationSchool(DataParser.parse(String.class, map.get(PersonVOMeta.GRADUATION_SCHOOL)));
			this.setEmploymentDate(DataParser.parse(Date.class, map.get(PersonVOMeta.EMPLOYMENT_DATE)));
			this.setContractStartDate(DataParser.parse(Date.class, map.get(PersonVOMeta.CONTRACT_START_DATE)));
			this.setIdentityCard(DataParser.parse(String.class, map.get(PersonVOMeta.IDENTITY_CARD)));
			this.setVersion(DataParser.parse(Integer.class, map.get(PersonVOMeta.VERSION)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(PersonVOMeta.DELETE_TIME)));
			this.setName(DataParser.parse(String.class, map.get(PersonVOMeta.NAME)));
			this.setForeignLanguage(DataParser.parse(String.class, map.get(PersonVOMeta.FOREIGN_LANGUAGE)));
			this.setFirstEmploymentDate(DataParser.parse(Date.class, map.get(PersonVOMeta.FIRST_EMPLOYMENT_DATE)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(PersonVOMeta.DELETE_BY)));
			this.setJobNumber(DataParser.parse(String.class, map.get(PersonVOMeta.JOB_NUMBER)));
			this.setMaritalStatus(DataParser.parse(String.class, map.get(PersonVOMeta.MARITAL_STATUS)));
			this.setFileId(DataParser.parse(String.class, map.get(PersonVOMeta.FILE_ID)));
			this.setBirthday(DataParser.parse(Date.class, map.get(PersonVOMeta.BIRTHDAY)));
			this.setNote(DataParser.parse(String.class, map.get(PersonVOMeta.NOTE)));
			this.setContractDuration(DataParser.parse(String.class, map.get(PersonVOMeta.CONTRACT_DURATION)));
			this.setContractFinishDate(DataParser.parse(Date.class, map.get(PersonVOMeta.CONTRACT_FINISH_DATE)));
			this.setBodyHeight(DataParser.parse(Integer.class, map.get(PersonVOMeta.BODY_HEIGHT)));
			this.setPositionCode(DataParser.parse(String.class, map.get(PersonVOMeta.POSITION_CODE)));
			this.setEducationCode(DataParser.parse(String.class, map.get(PersonVOMeta.EDUCATION_CODE)));
			this.setComputerAbility(DataParser.parse(String.class, map.get(PersonVOMeta.COMPUTER_ABILITY)));
			this.setBloodType(DataParser.parse(String.class, map.get(PersonVOMeta.BLOOD_TYPE)));
			this.setEmployeeStatus(DataParser.parse(String.class, map.get(PersonVOMeta.EMPLOYEE_STATUS)));
			this.setOrgId(DataParser.parse(String.class, map.get(PersonVOMeta.ORG_ID)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(PersonVOMeta.UPDATE_BY)));
			this.setPersonPictureId(DataParser.parse(String.class, map.get(PersonVOMeta.PERSON_PICTURE_ID)));
			this.setEmail(DataParser.parse(String.class, map.get(PersonVOMeta.EMAIL)));
			this.setNationCode(DataParser.parse(String.class, map.get(PersonVOMeta.NATION_CODE)));
			this.setFirstWorkDate(DataParser.parse(Date.class, map.get(PersonVOMeta.FIRST_WORK_DATE)));
			this.setPayrollCard(DataParser.parse(String.class, map.get(PersonVOMeta.PAYROLL_CARD)));
			this.setWeixinId(DataParser.parse(String.class, map.get(PersonVOMeta.WEIXIN_ID)));
			this.setEmployeeId(DataParser.parse(String.class, map.get(PersonVOMeta.EMPLOYEE_ID)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(PersonVOMeta.UPDATE_TIME)));
			this.setJoinPartDate(DataParser.parse(Date.class, map.get(PersonVOMeta.JOIN_PART_DATE)));
			this.setLeaveReson(DataParser.parse(String.class, map.get(PersonVOMeta.LEAVE_RESON)));
			this.setSexCode(DataParser.parse(String.class, map.get(PersonVOMeta.SEX_CODE)));
			this.setCreateBy(DataParser.parse(String.class, map.get(PersonVOMeta.CREATE_BY)));
			this.setContactInformation(DataParser.parse(String.class, map.get(PersonVOMeta.CONTACT_INFORMATION)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(PersonVOMeta.DELETED)));
			this.setGraduationDate(DataParser.parse(Date.class, map.get(PersonVOMeta.GRADUATION_DATE)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(PersonVOMeta.CREATE_TIME)));
			this.setEmployeeTypeCode(DataParser.parse(String.class, map.get(PersonVOMeta.EMPLOYEE_TYPE_CODE)));
			this.setLeaveDate(DataParser.parse(Date.class, map.get(PersonVOMeta.LEAVE_DATE)));
			this.setTenantId(DataParser.parse(String.class, map.get(PersonVOMeta.TENANT_ID)));
			this.setPoliticCountenanceCode(DataParser.parse(String.class, map.get(PersonVOMeta.POLITIC_COUNTENANCE_CODE)));
			this.setBodyWeight(DataParser.parse(Integer.class, map.get(PersonVOMeta.BODY_WEIGHT)));
			// others
			this.setPoliticCountenanceData(DataParser.parse(DictItem.class, map.get(PersonVOMeta.POLITIC_COUNTENANCE_DATA)));
			this.setSearchField(DataParser.parse(String.class, map.get(PersonVOMeta.SEARCH_FIELD)));
			this.setFuzzyField(DataParser.parse(String.class, map.get(PersonVOMeta.FUZZY_FIELD)));
			this.setProfessionalLevel(DataParser.parse(ProfessionalLevel.class, map.get(PersonVOMeta.PROFESSIONAL_LEVEL)));
			this.setPageSize(DataParser.parse(Integer.class, map.get(PersonVOMeta.PAGE_SIZE)));
			this.setEmployeeOwnerTypeDict(DataParser.parse(DictItem.class, map.get(PersonVOMeta.EMPLOYEE_OWNER_TYPE_DICT)));
			this.setEmployee(DataParser.parse(Employee.class, map.get(PersonVOMeta.EMPLOYEE)));
			this.setEducationData(DataParser.parse(DictItem.class, map.get(PersonVOMeta.EDUCATION_DATA)));
			this.setBloodTypeDict(DataParser.parse(DictItem.class, map.get(PersonVOMeta.BLOOD_TYPE_DICT)));
			this.setPageIndex(DataParser.parse(Integer.class, map.get(PersonVOMeta.PAGE_INDEX)));
			this.setSortType(DataParser.parse(String.class, map.get(PersonVOMeta.SORT_TYPE)));
			this.setSortField(DataParser.parse(String.class, map.get(PersonVOMeta.SORT_FIELD)));
			this.setRank(DataParser.parse(Rank.class, map.get(PersonVOMeta.RANK)));
			this.setDataOrigin(DataParser.parse(String.class, map.get(PersonVOMeta.DATA_ORIGIN)));
			this.setQueryLogic(DataParser.parse(String.class, map.get(PersonVOMeta.QUERY_LOGIC)));
			this.setPosition(DataParser.parse(Position.class, map.get(PersonVOMeta.POSITION)));
			this.setSexDict(DataParser.parse(DictItem.class, map.get(PersonVOMeta.SEX_DICT)));
			this.setSearchValue(DataParser.parse(String.class, map.get(PersonVOMeta.SEARCH_VALUE)));
			this.setMaritalStatusDict(DataParser.parse(DictItem.class, map.get(PersonVOMeta.MARITAL_STATUS_DICT)));
			return true;
		} else {
			try {
				this.setRankCode( (String)map.get(PersonVOMeta.RANK_CODE));
				this.setEmergencyContactNo( (String)map.get(PersonVOMeta.EMERGENCY_CONTACT_NO));
				this.setEmploymentConfirmDate( (Date)map.get(PersonVOMeta.EMPLOYMENT_CONFIRM_DATE));
				this.setNativePlaceCode( (String)map.get(PersonVOMeta.NATIVE_PLACE_CODE));
				this.setMajor( (String)map.get(PersonVOMeta.MAJOR));
				this.setComputerLevel( (String)map.get(PersonVOMeta.COMPUTER_LEVEL));
				this.setWorkKindCode( (String)map.get(PersonVOMeta.WORK_KIND_CODE));
				this.setId( (String)map.get(PersonVOMeta.ID));
				this.setPayrollCardBankCode( (String)map.get(PersonVOMeta.PAYROLL_CARD_BANK_CODE));
				this.setHomeAddress( (String)map.get(PersonVOMeta.HOME_ADDRESS));
				this.setEmployeeTitleCode( (String)map.get(PersonVOMeta.EMPLOYEE_TITLE_CODE));
				this.setForeignLanguageLevel( (String)map.get(PersonVOMeta.FOREIGN_LANGUAGE_LEVEL));
				this.setEmergencyContact( (String)map.get(PersonVOMeta.EMERGENCY_CONTACT));
				this.setGraduationSchool( (String)map.get(PersonVOMeta.GRADUATION_SCHOOL));
				this.setEmploymentDate( (Date)map.get(PersonVOMeta.EMPLOYMENT_DATE));
				this.setContractStartDate( (Date)map.get(PersonVOMeta.CONTRACT_START_DATE));
				this.setIdentityCard( (String)map.get(PersonVOMeta.IDENTITY_CARD));
				this.setVersion( (Integer)map.get(PersonVOMeta.VERSION));
				this.setDeleteTime( (Date)map.get(PersonVOMeta.DELETE_TIME));
				this.setName( (String)map.get(PersonVOMeta.NAME));
				this.setForeignLanguage( (String)map.get(PersonVOMeta.FOREIGN_LANGUAGE));
				this.setFirstEmploymentDate( (Date)map.get(PersonVOMeta.FIRST_EMPLOYMENT_DATE));
				this.setDeleteBy( (String)map.get(PersonVOMeta.DELETE_BY));
				this.setJobNumber( (String)map.get(PersonVOMeta.JOB_NUMBER));
				this.setMaritalStatus( (String)map.get(PersonVOMeta.MARITAL_STATUS));
				this.setFileId( (String)map.get(PersonVOMeta.FILE_ID));
				this.setBirthday( (Date)map.get(PersonVOMeta.BIRTHDAY));
				this.setNote( (String)map.get(PersonVOMeta.NOTE));
				this.setContractDuration( (String)map.get(PersonVOMeta.CONTRACT_DURATION));
				this.setContractFinishDate( (Date)map.get(PersonVOMeta.CONTRACT_FINISH_DATE));
				this.setBodyHeight( (Integer)map.get(PersonVOMeta.BODY_HEIGHT));
				this.setPositionCode( (String)map.get(PersonVOMeta.POSITION_CODE));
				this.setEducationCode( (String)map.get(PersonVOMeta.EDUCATION_CODE));
				this.setComputerAbility( (String)map.get(PersonVOMeta.COMPUTER_ABILITY));
				this.setBloodType( (String)map.get(PersonVOMeta.BLOOD_TYPE));
				this.setEmployeeStatus( (String)map.get(PersonVOMeta.EMPLOYEE_STATUS));
				this.setOrgId( (String)map.get(PersonVOMeta.ORG_ID));
				this.setUpdateBy( (String)map.get(PersonVOMeta.UPDATE_BY));
				this.setPersonPictureId( (String)map.get(PersonVOMeta.PERSON_PICTURE_ID));
				this.setEmail( (String)map.get(PersonVOMeta.EMAIL));
				this.setNationCode( (String)map.get(PersonVOMeta.NATION_CODE));
				this.setFirstWorkDate( (Date)map.get(PersonVOMeta.FIRST_WORK_DATE));
				this.setPayrollCard( (String)map.get(PersonVOMeta.PAYROLL_CARD));
				this.setWeixinId( (String)map.get(PersonVOMeta.WEIXIN_ID));
				this.setEmployeeId( (String)map.get(PersonVOMeta.EMPLOYEE_ID));
				this.setUpdateTime( (Date)map.get(PersonVOMeta.UPDATE_TIME));
				this.setJoinPartDate( (Date)map.get(PersonVOMeta.JOIN_PART_DATE));
				this.setLeaveReson( (String)map.get(PersonVOMeta.LEAVE_RESON));
				this.setSexCode( (String)map.get(PersonVOMeta.SEX_CODE));
				this.setCreateBy( (String)map.get(PersonVOMeta.CREATE_BY));
				this.setContactInformation( (String)map.get(PersonVOMeta.CONTACT_INFORMATION));
				this.setDeleted( (Integer)map.get(PersonVOMeta.DELETED));
				this.setGraduationDate( (Date)map.get(PersonVOMeta.GRADUATION_DATE));
				this.setCreateTime( (Date)map.get(PersonVOMeta.CREATE_TIME));
				this.setEmployeeTypeCode( (String)map.get(PersonVOMeta.EMPLOYEE_TYPE_CODE));
				this.setLeaveDate( (Date)map.get(PersonVOMeta.LEAVE_DATE));
				this.setTenantId( (String)map.get(PersonVOMeta.TENANT_ID));
				this.setPoliticCountenanceCode( (String)map.get(PersonVOMeta.POLITIC_COUNTENANCE_CODE));
				this.setBodyWeight( (Integer)map.get(PersonVOMeta.BODY_WEIGHT));
				// others
				this.setPoliticCountenanceData( (DictItem)map.get(PersonVOMeta.POLITIC_COUNTENANCE_DATA));
				this.setSearchField( (String)map.get(PersonVOMeta.SEARCH_FIELD));
				this.setFuzzyField( (String)map.get(PersonVOMeta.FUZZY_FIELD));
				this.setProfessionalLevel( (ProfessionalLevel)map.get(PersonVOMeta.PROFESSIONAL_LEVEL));
				this.setPageSize( (Integer)map.get(PersonVOMeta.PAGE_SIZE));
				this.setEmployeeOwnerTypeDict( (DictItem)map.get(PersonVOMeta.EMPLOYEE_OWNER_TYPE_DICT));
				this.setEmployee( (Employee)map.get(PersonVOMeta.EMPLOYEE));
				this.setEducationData( (DictItem)map.get(PersonVOMeta.EDUCATION_DATA));
				this.setBloodTypeDict( (DictItem)map.get(PersonVOMeta.BLOOD_TYPE_DICT));
				this.setPageIndex( (Integer)map.get(PersonVOMeta.PAGE_INDEX));
				this.setSortType( (String)map.get(PersonVOMeta.SORT_TYPE));
				this.setSortField( (String)map.get(PersonVOMeta.SORT_FIELD));
				this.setRank( (Rank)map.get(PersonVOMeta.RANK));
				this.setDataOrigin( (String)map.get(PersonVOMeta.DATA_ORIGIN));
				this.setQueryLogic( (String)map.get(PersonVOMeta.QUERY_LOGIC));
				this.setPosition( (Position)map.get(PersonVOMeta.POSITION));
				this.setSexDict( (DictItem)map.get(PersonVOMeta.SEX_DICT));
				this.setSearchValue( (String)map.get(PersonVOMeta.SEARCH_VALUE));
				this.setMaritalStatusDict( (DictItem)map.get(PersonVOMeta.MARITAL_STATUS_DICT));
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
			this.setRankCode(DataParser.parse(String.class, r.getValue(PersonVOMeta.RANK_CODE)));
			this.setEmergencyContactNo(DataParser.parse(String.class, r.getValue(PersonVOMeta.EMERGENCY_CONTACT_NO)));
			this.setEmploymentConfirmDate(DataParser.parse(Date.class, r.getValue(PersonVOMeta.EMPLOYMENT_CONFIRM_DATE)));
			this.setNativePlaceCode(DataParser.parse(String.class, r.getValue(PersonVOMeta.NATIVE_PLACE_CODE)));
			this.setMajor(DataParser.parse(String.class, r.getValue(PersonVOMeta.MAJOR)));
			this.setComputerLevel(DataParser.parse(String.class, r.getValue(PersonVOMeta.COMPUTER_LEVEL)));
			this.setWorkKindCode(DataParser.parse(String.class, r.getValue(PersonVOMeta.WORK_KIND_CODE)));
			this.setId(DataParser.parse(String.class, r.getValue(PersonVOMeta.ID)));
			this.setPayrollCardBankCode(DataParser.parse(String.class, r.getValue(PersonVOMeta.PAYROLL_CARD_BANK_CODE)));
			this.setHomeAddress(DataParser.parse(String.class, r.getValue(PersonVOMeta.HOME_ADDRESS)));
			this.setEmployeeTitleCode(DataParser.parse(String.class, r.getValue(PersonVOMeta.EMPLOYEE_TITLE_CODE)));
			this.setForeignLanguageLevel(DataParser.parse(String.class, r.getValue(PersonVOMeta.FOREIGN_LANGUAGE_LEVEL)));
			this.setEmergencyContact(DataParser.parse(String.class, r.getValue(PersonVOMeta.EMERGENCY_CONTACT)));
			this.setGraduationSchool(DataParser.parse(String.class, r.getValue(PersonVOMeta.GRADUATION_SCHOOL)));
			this.setEmploymentDate(DataParser.parse(Date.class, r.getValue(PersonVOMeta.EMPLOYMENT_DATE)));
			this.setContractStartDate(DataParser.parse(Date.class, r.getValue(PersonVOMeta.CONTRACT_START_DATE)));
			this.setIdentityCard(DataParser.parse(String.class, r.getValue(PersonVOMeta.IDENTITY_CARD)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(PersonVOMeta.VERSION)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(PersonVOMeta.DELETE_TIME)));
			this.setName(DataParser.parse(String.class, r.getValue(PersonVOMeta.NAME)));
			this.setForeignLanguage(DataParser.parse(String.class, r.getValue(PersonVOMeta.FOREIGN_LANGUAGE)));
			this.setFirstEmploymentDate(DataParser.parse(Date.class, r.getValue(PersonVOMeta.FIRST_EMPLOYMENT_DATE)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(PersonVOMeta.DELETE_BY)));
			this.setJobNumber(DataParser.parse(String.class, r.getValue(PersonVOMeta.JOB_NUMBER)));
			this.setMaritalStatus(DataParser.parse(String.class, r.getValue(PersonVOMeta.MARITAL_STATUS)));
			this.setFileId(DataParser.parse(String.class, r.getValue(PersonVOMeta.FILE_ID)));
			this.setBirthday(DataParser.parse(Date.class, r.getValue(PersonVOMeta.BIRTHDAY)));
			this.setNote(DataParser.parse(String.class, r.getValue(PersonVOMeta.NOTE)));
			this.setContractDuration(DataParser.parse(String.class, r.getValue(PersonVOMeta.CONTRACT_DURATION)));
			this.setContractFinishDate(DataParser.parse(Date.class, r.getValue(PersonVOMeta.CONTRACT_FINISH_DATE)));
			this.setBodyHeight(DataParser.parse(Integer.class, r.getValue(PersonVOMeta.BODY_HEIGHT)));
			this.setPositionCode(DataParser.parse(String.class, r.getValue(PersonVOMeta.POSITION_CODE)));
			this.setEducationCode(DataParser.parse(String.class, r.getValue(PersonVOMeta.EDUCATION_CODE)));
			this.setComputerAbility(DataParser.parse(String.class, r.getValue(PersonVOMeta.COMPUTER_ABILITY)));
			this.setBloodType(DataParser.parse(String.class, r.getValue(PersonVOMeta.BLOOD_TYPE)));
			this.setEmployeeStatus(DataParser.parse(String.class, r.getValue(PersonVOMeta.EMPLOYEE_STATUS)));
			this.setOrgId(DataParser.parse(String.class, r.getValue(PersonVOMeta.ORG_ID)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(PersonVOMeta.UPDATE_BY)));
			this.setPersonPictureId(DataParser.parse(String.class, r.getValue(PersonVOMeta.PERSON_PICTURE_ID)));
			this.setEmail(DataParser.parse(String.class, r.getValue(PersonVOMeta.EMAIL)));
			this.setNationCode(DataParser.parse(String.class, r.getValue(PersonVOMeta.NATION_CODE)));
			this.setFirstWorkDate(DataParser.parse(Date.class, r.getValue(PersonVOMeta.FIRST_WORK_DATE)));
			this.setPayrollCard(DataParser.parse(String.class, r.getValue(PersonVOMeta.PAYROLL_CARD)));
			this.setWeixinId(DataParser.parse(String.class, r.getValue(PersonVOMeta.WEIXIN_ID)));
			this.setEmployeeId(DataParser.parse(String.class, r.getValue(PersonVOMeta.EMPLOYEE_ID)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(PersonVOMeta.UPDATE_TIME)));
			this.setJoinPartDate(DataParser.parse(Date.class, r.getValue(PersonVOMeta.JOIN_PART_DATE)));
			this.setLeaveReson(DataParser.parse(String.class, r.getValue(PersonVOMeta.LEAVE_RESON)));
			this.setSexCode(DataParser.parse(String.class, r.getValue(PersonVOMeta.SEX_CODE)));
			this.setCreateBy(DataParser.parse(String.class, r.getValue(PersonVOMeta.CREATE_BY)));
			this.setContactInformation(DataParser.parse(String.class, r.getValue(PersonVOMeta.CONTACT_INFORMATION)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(PersonVOMeta.DELETED)));
			this.setGraduationDate(DataParser.parse(Date.class, r.getValue(PersonVOMeta.GRADUATION_DATE)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(PersonVOMeta.CREATE_TIME)));
			this.setEmployeeTypeCode(DataParser.parse(String.class, r.getValue(PersonVOMeta.EMPLOYEE_TYPE_CODE)));
			this.setLeaveDate(DataParser.parse(Date.class, r.getValue(PersonVOMeta.LEAVE_DATE)));
			this.setTenantId(DataParser.parse(String.class, r.getValue(PersonVOMeta.TENANT_ID)));
			this.setPoliticCountenanceCode(DataParser.parse(String.class, r.getValue(PersonVOMeta.POLITIC_COUNTENANCE_CODE)));
			this.setBodyWeight(DataParser.parse(Integer.class, r.getValue(PersonVOMeta.BODY_WEIGHT)));
			return true;
		} else {
			try {
				this.setRankCode( (String)r.getValue(PersonVOMeta.RANK_CODE));
				this.setEmergencyContactNo( (String)r.getValue(PersonVOMeta.EMERGENCY_CONTACT_NO));
				this.setEmploymentConfirmDate( (Date)r.getValue(PersonVOMeta.EMPLOYMENT_CONFIRM_DATE));
				this.setNativePlaceCode( (String)r.getValue(PersonVOMeta.NATIVE_PLACE_CODE));
				this.setMajor( (String)r.getValue(PersonVOMeta.MAJOR));
				this.setComputerLevel( (String)r.getValue(PersonVOMeta.COMPUTER_LEVEL));
				this.setWorkKindCode( (String)r.getValue(PersonVOMeta.WORK_KIND_CODE));
				this.setId( (String)r.getValue(PersonVOMeta.ID));
				this.setPayrollCardBankCode( (String)r.getValue(PersonVOMeta.PAYROLL_CARD_BANK_CODE));
				this.setHomeAddress( (String)r.getValue(PersonVOMeta.HOME_ADDRESS));
				this.setEmployeeTitleCode( (String)r.getValue(PersonVOMeta.EMPLOYEE_TITLE_CODE));
				this.setForeignLanguageLevel( (String)r.getValue(PersonVOMeta.FOREIGN_LANGUAGE_LEVEL));
				this.setEmergencyContact( (String)r.getValue(PersonVOMeta.EMERGENCY_CONTACT));
				this.setGraduationSchool( (String)r.getValue(PersonVOMeta.GRADUATION_SCHOOL));
				this.setEmploymentDate( (Date)r.getValue(PersonVOMeta.EMPLOYMENT_DATE));
				this.setContractStartDate( (Date)r.getValue(PersonVOMeta.CONTRACT_START_DATE));
				this.setIdentityCard( (String)r.getValue(PersonVOMeta.IDENTITY_CARD));
				this.setVersion( (Integer)r.getValue(PersonVOMeta.VERSION));
				this.setDeleteTime( (Date)r.getValue(PersonVOMeta.DELETE_TIME));
				this.setName( (String)r.getValue(PersonVOMeta.NAME));
				this.setForeignLanguage( (String)r.getValue(PersonVOMeta.FOREIGN_LANGUAGE));
				this.setFirstEmploymentDate( (Date)r.getValue(PersonVOMeta.FIRST_EMPLOYMENT_DATE));
				this.setDeleteBy( (String)r.getValue(PersonVOMeta.DELETE_BY));
				this.setJobNumber( (String)r.getValue(PersonVOMeta.JOB_NUMBER));
				this.setMaritalStatus( (String)r.getValue(PersonVOMeta.MARITAL_STATUS));
				this.setFileId( (String)r.getValue(PersonVOMeta.FILE_ID));
				this.setBirthday( (Date)r.getValue(PersonVOMeta.BIRTHDAY));
				this.setNote( (String)r.getValue(PersonVOMeta.NOTE));
				this.setContractDuration( (String)r.getValue(PersonVOMeta.CONTRACT_DURATION));
				this.setContractFinishDate( (Date)r.getValue(PersonVOMeta.CONTRACT_FINISH_DATE));
				this.setBodyHeight( (Integer)r.getValue(PersonVOMeta.BODY_HEIGHT));
				this.setPositionCode( (String)r.getValue(PersonVOMeta.POSITION_CODE));
				this.setEducationCode( (String)r.getValue(PersonVOMeta.EDUCATION_CODE));
				this.setComputerAbility( (String)r.getValue(PersonVOMeta.COMPUTER_ABILITY));
				this.setBloodType( (String)r.getValue(PersonVOMeta.BLOOD_TYPE));
				this.setEmployeeStatus( (String)r.getValue(PersonVOMeta.EMPLOYEE_STATUS));
				this.setOrgId( (String)r.getValue(PersonVOMeta.ORG_ID));
				this.setUpdateBy( (String)r.getValue(PersonVOMeta.UPDATE_BY));
				this.setPersonPictureId( (String)r.getValue(PersonVOMeta.PERSON_PICTURE_ID));
				this.setEmail( (String)r.getValue(PersonVOMeta.EMAIL));
				this.setNationCode( (String)r.getValue(PersonVOMeta.NATION_CODE));
				this.setFirstWorkDate( (Date)r.getValue(PersonVOMeta.FIRST_WORK_DATE));
				this.setPayrollCard( (String)r.getValue(PersonVOMeta.PAYROLL_CARD));
				this.setWeixinId( (String)r.getValue(PersonVOMeta.WEIXIN_ID));
				this.setEmployeeId( (String)r.getValue(PersonVOMeta.EMPLOYEE_ID));
				this.setUpdateTime( (Date)r.getValue(PersonVOMeta.UPDATE_TIME));
				this.setJoinPartDate( (Date)r.getValue(PersonVOMeta.JOIN_PART_DATE));
				this.setLeaveReson( (String)r.getValue(PersonVOMeta.LEAVE_RESON));
				this.setSexCode( (String)r.getValue(PersonVOMeta.SEX_CODE));
				this.setCreateBy( (String)r.getValue(PersonVOMeta.CREATE_BY));
				this.setContactInformation( (String)r.getValue(PersonVOMeta.CONTACT_INFORMATION));
				this.setDeleted( (Integer)r.getValue(PersonVOMeta.DELETED));
				this.setGraduationDate( (Date)r.getValue(PersonVOMeta.GRADUATION_DATE));
				this.setCreateTime( (Date)r.getValue(PersonVOMeta.CREATE_TIME));
				this.setEmployeeTypeCode( (String)r.getValue(PersonVOMeta.EMPLOYEE_TYPE_CODE));
				this.setLeaveDate( (Date)r.getValue(PersonVOMeta.LEAVE_DATE));
				this.setTenantId( (String)r.getValue(PersonVOMeta.TENANT_ID));
				this.setPoliticCountenanceCode( (String)r.getValue(PersonVOMeta.POLITIC_COUNTENANCE_CODE));
				this.setBodyWeight( (Integer)r.getValue(PersonVOMeta.BODY_WEIGHT));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}