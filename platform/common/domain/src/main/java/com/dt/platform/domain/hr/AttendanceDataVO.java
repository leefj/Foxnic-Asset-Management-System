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
import com.dt.platform.domain.hr.meta.AttendanceDataVOMeta;
import com.github.foxnic.commons.lang.DataParser;
import java.util.Date;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 考勤汇总VO类型
 * <p>考勤汇总 , 数据表 hr_attendance_data 的通用VO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:22:47
 * @sign 7D3A17025427F09B3F172266E333EB82
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@ApiModel(description = "考勤汇总VO类型 ; 考勤汇总 , 数据表 hr_attendance_data 的通用VO类型" , parent = AttendanceData.class)
public class AttendanceDataVO extends AttendanceData {

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
	public AttendanceDataVO setPageIndex(Integer pageIndex) {
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
	public AttendanceDataVO setPageSize(Integer pageSize) {
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
	public AttendanceDataVO setSearchField(String searchField) {
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
	public AttendanceDataVO setFuzzyField(String fuzzyField) {
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
	public AttendanceDataVO setSearchValue(String searchValue) {
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
	public AttendanceDataVO setDirtyFields(List<String> dirtyFields) {
		this.dirtyFields=dirtyFields;
		return this;
	}
	
	/**
	 * 添加 已修改字段
	 * @param dirtyField 已修改字段
	 * @return 当前对象
	*/
	public AttendanceDataVO addDirtyField(String... dirtyField) {
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
	public AttendanceDataVO setSortField(String sortField) {
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
	public AttendanceDataVO setSortType(String sortType) {
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
	public AttendanceDataVO setDataOrigin(String dataOrigin) {
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
	public AttendanceDataVO setQueryLogic(String queryLogic) {
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
	public AttendanceDataVO setIds(List<String> ids) {
		this.ids=ids;
		return this;
	}
	
	/**
	 * 添加 主键清单
	 * @param id 主键清单
	 * @return 当前对象
	*/
	public AttendanceDataVO addId(String... id) {
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
	 * @return AttendanceDataVO , 转换好的 AttendanceDataVO 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return AttendanceDataVO , 转换好的 PoJo 对象
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
	public AttendanceDataVO clone() {
		return duplicate(true);
	}

	/**
	 * 复制当前对象
	 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
	*/
	@Transient
	public AttendanceDataVO duplicate(boolean all) {
		com.dt.platform.domain.hr.meta.AttendanceDataVOMeta.$$proxy$$ inst = new com.dt.platform.domain.hr.meta.AttendanceDataVOMeta.$$proxy$$();
		inst.setEmployeeName(this.getEmployeeName());
		inst.setNotes(this.getNotes());
		inst.setEmployeeId(this.getEmployeeId());
		inst.setUpdateTime(this.getUpdateTime());
		inst.setLeaveEarly(this.getLeaveEarly());
		inst.setVersion(this.getVersion());
		inst.setEmployeeNumber(this.getEmployeeNumber());
		inst.setOnWorkTime(this.getOnWorkTime());
		inst.setNormalWork(this.getNormalWork());
		inst.setSkipWork(this.getSkipWork());
		inst.setCreateBy(this.getCreateBy());
		inst.setOffWorkTime(this.getOffWorkTime());
		inst.setDeleted(this.getDeleted());
		inst.setCreateTime(this.getCreateTime());
		inst.setUpdateBy(this.getUpdateBy());
		inst.setDeleteTime(this.getDeleteTime());
		inst.setTenantId(this.getTenantId());
		inst.setDeleteBy(this.getDeleteBy());
		inst.setId(this.getId());
		inst.setLeaveLate(this.getLeaveLate());
		inst.setAttendanceDate(this.getAttendanceDate());
		inst.setOffWorkTime2(this.getOffWorkTime2());
		inst.setOnWorkTime2(this.getOnWorkTime2());
		if(all) {
			inst.setSearchField(this.getSearchField());
			inst.setPageIndex(this.getPageIndex());
			inst.setSortType(this.getSortType());
			inst.setFuzzyField(this.getFuzzyField());
			inst.setDirtyFields(this.getDirtyFields());
			inst.setSortField(this.getSortField());
			inst.setPageSize(this.getPageSize());
			inst.setDataOrigin(this.getDataOrigin());
			inst.setIds(this.getIds());
			inst.setQueryLogic(this.getQueryLogic());
			inst.setSearchValue(this.getSearchValue());
		}
		inst.clearModifies();
		return inst;
	}

	/**
	 * 克隆当前对象
	*/
	@Transient
	public AttendanceDataVO clone(boolean deep) {
		return EntityContext.clone(AttendanceDataVO.class,this,deep);
	}

	/**
	 * 将 Map 转换成 AttendanceDataVO
	 * @param attendanceDataMap 包含实体信息的 Map 对象
	 * @return AttendanceDataVO , 转换好的的 AttendanceData 对象
	*/
	@Transient
	public static AttendanceDataVO createFrom(Map<String,Object> attendanceDataMap) {
		if(attendanceDataMap==null) return null;
		AttendanceDataVO vo = create();
		EntityContext.copyProperties(vo,attendanceDataMap);
		vo.clearModifies();
		return vo;
	}

	/**
	 * 将 Pojo 转换成 AttendanceDataVO
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return AttendanceDataVO , 转换好的的 AttendanceData 对象
	*/
	@Transient
	public static AttendanceDataVO createFrom(Object pojo) {
		if(pojo==null) return null;
		AttendanceDataVO vo = create();
		EntityContext.copyProperties(vo,pojo);
		vo.clearModifies();
		return vo;
	}

	/**
	 * 创建一个 AttendanceDataVO，等同于 new
	 * @return AttendanceDataVO 对象
	*/
	@Transient
	public static AttendanceDataVO create() {
		return new com.dt.platform.domain.hr.meta.AttendanceDataVOMeta.$$proxy$$();
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
			this.setEmployeeName(DataParser.parse(String.class, map.get(AttendanceDataVOMeta.EMPLOYEE_NAME)));
			this.setNotes(DataParser.parse(String.class, map.get(AttendanceDataVOMeta.NOTES)));
			this.setEmployeeId(DataParser.parse(String.class, map.get(AttendanceDataVOMeta.EMPLOYEE_ID)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(AttendanceDataVOMeta.UPDATE_TIME)));
			this.setLeaveEarly(DataParser.parse(Integer.class, map.get(AttendanceDataVOMeta.LEAVE_EARLY)));
			this.setVersion(DataParser.parse(Integer.class, map.get(AttendanceDataVOMeta.VERSION)));
			this.setEmployeeNumber(DataParser.parse(String.class, map.get(AttendanceDataVOMeta.EMPLOYEE_NUMBER)));
			this.setOnWorkTime(DataParser.parse(Date.class, map.get(AttendanceDataVOMeta.ON_WORK_TIME)));
			this.setNormalWork(DataParser.parse(Integer.class, map.get(AttendanceDataVOMeta.NORMAL_WORK)));
			this.setSkipWork(DataParser.parse(Integer.class, map.get(AttendanceDataVOMeta.SKIP_WORK)));
			this.setCreateBy(DataParser.parse(String.class, map.get(AttendanceDataVOMeta.CREATE_BY)));
			this.setOffWorkTime(DataParser.parse(Date.class, map.get(AttendanceDataVOMeta.OFF_WORK_TIME)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(AttendanceDataVOMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(AttendanceDataVOMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(AttendanceDataVOMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(AttendanceDataVOMeta.DELETE_TIME)));
			this.setTenantId(DataParser.parse(String.class, map.get(AttendanceDataVOMeta.TENANT_ID)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(AttendanceDataVOMeta.DELETE_BY)));
			this.setId(DataParser.parse(String.class, map.get(AttendanceDataVOMeta.ID)));
			this.setLeaveLate(DataParser.parse(Integer.class, map.get(AttendanceDataVOMeta.LEAVE_LATE)));
			this.setAttendanceDate(DataParser.parse(Date.class, map.get(AttendanceDataVOMeta.ATTENDANCE_DATE)));
			this.setOffWorkTime2(DataParser.parse(Date.class, map.get(AttendanceDataVOMeta.OFF_WORK_TIME2)));
			this.setOnWorkTime2(DataParser.parse(Date.class, map.get(AttendanceDataVOMeta.ON_WORK_TIME2)));
			// others
			this.setSearchField(DataParser.parse(String.class, map.get(AttendanceDataVOMeta.SEARCH_FIELD)));
			this.setPageIndex(DataParser.parse(Integer.class, map.get(AttendanceDataVOMeta.PAGE_INDEX)));
			this.setSortType(DataParser.parse(String.class, map.get(AttendanceDataVOMeta.SORT_TYPE)));
			this.setFuzzyField(DataParser.parse(String.class, map.get(AttendanceDataVOMeta.FUZZY_FIELD)));
			this.setSortField(DataParser.parse(String.class, map.get(AttendanceDataVOMeta.SORT_FIELD)));
			this.setPageSize(DataParser.parse(Integer.class, map.get(AttendanceDataVOMeta.PAGE_SIZE)));
			this.setDataOrigin(DataParser.parse(String.class, map.get(AttendanceDataVOMeta.DATA_ORIGIN)));
			this.setQueryLogic(DataParser.parse(String.class, map.get(AttendanceDataVOMeta.QUERY_LOGIC)));
			this.setSearchValue(DataParser.parse(String.class, map.get(AttendanceDataVOMeta.SEARCH_VALUE)));
			return true;
		} else {
			try {
				this.setEmployeeName( (String)map.get(AttendanceDataVOMeta.EMPLOYEE_NAME));
				this.setNotes( (String)map.get(AttendanceDataVOMeta.NOTES));
				this.setEmployeeId( (String)map.get(AttendanceDataVOMeta.EMPLOYEE_ID));
				this.setUpdateTime( (Date)map.get(AttendanceDataVOMeta.UPDATE_TIME));
				this.setLeaveEarly( (Integer)map.get(AttendanceDataVOMeta.LEAVE_EARLY));
				this.setVersion( (Integer)map.get(AttendanceDataVOMeta.VERSION));
				this.setEmployeeNumber( (String)map.get(AttendanceDataVOMeta.EMPLOYEE_NUMBER));
				this.setOnWorkTime( (Date)map.get(AttendanceDataVOMeta.ON_WORK_TIME));
				this.setNormalWork( (Integer)map.get(AttendanceDataVOMeta.NORMAL_WORK));
				this.setSkipWork( (Integer)map.get(AttendanceDataVOMeta.SKIP_WORK));
				this.setCreateBy( (String)map.get(AttendanceDataVOMeta.CREATE_BY));
				this.setOffWorkTime( (Date)map.get(AttendanceDataVOMeta.OFF_WORK_TIME));
				this.setDeleted( (Integer)map.get(AttendanceDataVOMeta.DELETED));
				this.setCreateTime( (Date)map.get(AttendanceDataVOMeta.CREATE_TIME));
				this.setUpdateBy( (String)map.get(AttendanceDataVOMeta.UPDATE_BY));
				this.setDeleteTime( (Date)map.get(AttendanceDataVOMeta.DELETE_TIME));
				this.setTenantId( (String)map.get(AttendanceDataVOMeta.TENANT_ID));
				this.setDeleteBy( (String)map.get(AttendanceDataVOMeta.DELETE_BY));
				this.setId( (String)map.get(AttendanceDataVOMeta.ID));
				this.setLeaveLate( (Integer)map.get(AttendanceDataVOMeta.LEAVE_LATE));
				this.setAttendanceDate( (Date)map.get(AttendanceDataVOMeta.ATTENDANCE_DATE));
				this.setOffWorkTime2( (Date)map.get(AttendanceDataVOMeta.OFF_WORK_TIME2));
				this.setOnWorkTime2( (Date)map.get(AttendanceDataVOMeta.ON_WORK_TIME2));
				// others
				this.setSearchField( (String)map.get(AttendanceDataVOMeta.SEARCH_FIELD));
				this.setPageIndex( (Integer)map.get(AttendanceDataVOMeta.PAGE_INDEX));
				this.setSortType( (String)map.get(AttendanceDataVOMeta.SORT_TYPE));
				this.setFuzzyField( (String)map.get(AttendanceDataVOMeta.FUZZY_FIELD));
				this.setSortField( (String)map.get(AttendanceDataVOMeta.SORT_FIELD));
				this.setPageSize( (Integer)map.get(AttendanceDataVOMeta.PAGE_SIZE));
				this.setDataOrigin( (String)map.get(AttendanceDataVOMeta.DATA_ORIGIN));
				this.setQueryLogic( (String)map.get(AttendanceDataVOMeta.QUERY_LOGIC));
				this.setSearchValue( (String)map.get(AttendanceDataVOMeta.SEARCH_VALUE));
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
			this.setEmployeeName(DataParser.parse(String.class, r.getValue(AttendanceDataVOMeta.EMPLOYEE_NAME)));
			this.setNotes(DataParser.parse(String.class, r.getValue(AttendanceDataVOMeta.NOTES)));
			this.setEmployeeId(DataParser.parse(String.class, r.getValue(AttendanceDataVOMeta.EMPLOYEE_ID)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(AttendanceDataVOMeta.UPDATE_TIME)));
			this.setLeaveEarly(DataParser.parse(Integer.class, r.getValue(AttendanceDataVOMeta.LEAVE_EARLY)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(AttendanceDataVOMeta.VERSION)));
			this.setEmployeeNumber(DataParser.parse(String.class, r.getValue(AttendanceDataVOMeta.EMPLOYEE_NUMBER)));
			this.setOnWorkTime(DataParser.parse(Date.class, r.getValue(AttendanceDataVOMeta.ON_WORK_TIME)));
			this.setNormalWork(DataParser.parse(Integer.class, r.getValue(AttendanceDataVOMeta.NORMAL_WORK)));
			this.setSkipWork(DataParser.parse(Integer.class, r.getValue(AttendanceDataVOMeta.SKIP_WORK)));
			this.setCreateBy(DataParser.parse(String.class, r.getValue(AttendanceDataVOMeta.CREATE_BY)));
			this.setOffWorkTime(DataParser.parse(Date.class, r.getValue(AttendanceDataVOMeta.OFF_WORK_TIME)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(AttendanceDataVOMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(AttendanceDataVOMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(AttendanceDataVOMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(AttendanceDataVOMeta.DELETE_TIME)));
			this.setTenantId(DataParser.parse(String.class, r.getValue(AttendanceDataVOMeta.TENANT_ID)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(AttendanceDataVOMeta.DELETE_BY)));
			this.setId(DataParser.parse(String.class, r.getValue(AttendanceDataVOMeta.ID)));
			this.setLeaveLate(DataParser.parse(Integer.class, r.getValue(AttendanceDataVOMeta.LEAVE_LATE)));
			this.setAttendanceDate(DataParser.parse(Date.class, r.getValue(AttendanceDataVOMeta.ATTENDANCE_DATE)));
			this.setOffWorkTime2(DataParser.parse(Date.class, r.getValue(AttendanceDataVOMeta.OFF_WORK_TIME2)));
			this.setOnWorkTime2(DataParser.parse(Date.class, r.getValue(AttendanceDataVOMeta.ON_WORK_TIME2)));
			return true;
		} else {
			try {
				this.setEmployeeName( (String)r.getValue(AttendanceDataVOMeta.EMPLOYEE_NAME));
				this.setNotes( (String)r.getValue(AttendanceDataVOMeta.NOTES));
				this.setEmployeeId( (String)r.getValue(AttendanceDataVOMeta.EMPLOYEE_ID));
				this.setUpdateTime( (Date)r.getValue(AttendanceDataVOMeta.UPDATE_TIME));
				this.setLeaveEarly( (Integer)r.getValue(AttendanceDataVOMeta.LEAVE_EARLY));
				this.setVersion( (Integer)r.getValue(AttendanceDataVOMeta.VERSION));
				this.setEmployeeNumber( (String)r.getValue(AttendanceDataVOMeta.EMPLOYEE_NUMBER));
				this.setOnWorkTime( (Date)r.getValue(AttendanceDataVOMeta.ON_WORK_TIME));
				this.setNormalWork( (Integer)r.getValue(AttendanceDataVOMeta.NORMAL_WORK));
				this.setSkipWork( (Integer)r.getValue(AttendanceDataVOMeta.SKIP_WORK));
				this.setCreateBy( (String)r.getValue(AttendanceDataVOMeta.CREATE_BY));
				this.setOffWorkTime( (Date)r.getValue(AttendanceDataVOMeta.OFF_WORK_TIME));
				this.setDeleted( (Integer)r.getValue(AttendanceDataVOMeta.DELETED));
				this.setCreateTime( (Date)r.getValue(AttendanceDataVOMeta.CREATE_TIME));
				this.setUpdateBy( (String)r.getValue(AttendanceDataVOMeta.UPDATE_BY));
				this.setDeleteTime( (Date)r.getValue(AttendanceDataVOMeta.DELETE_TIME));
				this.setTenantId( (String)r.getValue(AttendanceDataVOMeta.TENANT_ID));
				this.setDeleteBy( (String)r.getValue(AttendanceDataVOMeta.DELETE_BY));
				this.setId( (String)r.getValue(AttendanceDataVOMeta.ID));
				this.setLeaveLate( (Integer)r.getValue(AttendanceDataVOMeta.LEAVE_LATE));
				this.setAttendanceDate( (Date)r.getValue(AttendanceDataVOMeta.ATTENDANCE_DATE));
				this.setOffWorkTime2( (Date)r.getValue(AttendanceDataVOMeta.OFF_WORK_TIME2));
				this.setOnWorkTime2( (Date)r.getValue(AttendanceDataVOMeta.ON_WORK_TIME2));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}