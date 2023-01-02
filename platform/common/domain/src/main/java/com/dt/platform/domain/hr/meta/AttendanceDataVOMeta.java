package com.dt.platform.domain.hr.meta;

import com.github.foxnic.api.bean.BeanProperty;
import com.dt.platform.domain.hr.AttendanceDataVO;
import java.util.List;
import com.dt.platform.domain.hr.AttendanceData;
import java.util.Date;
import javax.persistence.Transient;



/**
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:22:47
 * @sign 7D3A17025427F09B3F172266E333EB82
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

public class AttendanceDataVOMeta extends AttendanceDataMeta {
	
	/**
	 * 页码 , 类型: java.lang.Integer
	*/
	public static final String PAGE_INDEX="pageIndex";
	
	/**
	 * 页码 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.Integer> PAGE_INDEX_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,PAGE_INDEX, java.lang.Integer.class, "页码", "", java.lang.Integer.class, null);
	
	/**
	 * 分页大小 , 类型: java.lang.Integer
	*/
	public static final String PAGE_SIZE="pageSize";
	
	/**
	 * 分页大小 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.Integer> PAGE_SIZE_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,PAGE_SIZE, java.lang.Integer.class, "分页大小", "", java.lang.Integer.class, null);
	
	/**
	 * 搜索字段 , 类型: java.lang.String
	*/
	public static final String SEARCH_FIELD="searchField";
	
	/**
	 * 搜索字段 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> SEARCH_FIELD_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,SEARCH_FIELD, java.lang.String.class, "搜索字段", "", java.lang.String.class, null);
	
	/**
	 * 模糊搜索字段 , 类型: java.lang.String
	*/
	public static final String FUZZY_FIELD="fuzzyField";
	
	/**
	 * 模糊搜索字段 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> FUZZY_FIELD_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,FUZZY_FIELD, java.lang.String.class, "模糊搜索字段", "", java.lang.String.class, null);
	
	/**
	 * 搜索的值 , 类型: java.lang.String
	*/
	public static final String SEARCH_VALUE="searchValue";
	
	/**
	 * 搜索的值 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> SEARCH_VALUE_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,SEARCH_VALUE, java.lang.String.class, "搜索的值", "", java.lang.String.class, null);
	
	/**
	 * 已修改字段 , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final String DIRTY_FIELDS="dirtyFields";
	
	/**
	 * 已修改字段 , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> DIRTY_FIELDS_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,DIRTY_FIELDS, java.util.List.class, "已修改字段", "", java.lang.String.class, null);
	
	/**
	 * 排序字段 , 类型: java.lang.String
	*/
	public static final String SORT_FIELD="sortField";
	
	/**
	 * 排序字段 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> SORT_FIELD_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,SORT_FIELD, java.lang.String.class, "排序字段", "", java.lang.String.class, null);
	
	/**
	 * 排序方式 , 类型: java.lang.String
	*/
	public static final String SORT_TYPE="sortType";
	
	/**
	 * 排序方式 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> SORT_TYPE_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,SORT_TYPE, java.lang.String.class, "排序方式", "", java.lang.String.class, null);
	
	/**
	 * 数据来源 , 前端指定不同的来源，后端按来源执行不同的逻辑 , 类型: java.lang.String
	*/
	public static final String DATA_ORIGIN="dataOrigin";
	
	/**
	 * 数据来源 , 前端指定不同的来源，后端按来源执行不同的逻辑 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> DATA_ORIGIN_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,DATA_ORIGIN, java.lang.String.class, "数据来源", "前端指定不同的来源，后端按来源执行不同的逻辑", java.lang.String.class, null);
	
	/**
	 * 查询逻辑 , 默认and，可指定 or  , 类型: java.lang.String
	*/
	public static final String QUERY_LOGIC="queryLogic";
	
	/**
	 * 查询逻辑 , 默认and，可指定 or  , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> QUERY_LOGIC_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,QUERY_LOGIC, java.lang.String.class, "查询逻辑", "默认and，可指定 or ", java.lang.String.class, null);
	
	/**
	 * 主键清单 , 用于接收批量主键参数 , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final String IDS="ids";
	
	/**
	 * 主键清单 , 用于接收批量主键参数 , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> IDS_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,IDS, java.util.List.class, "主键清单", "用于接收批量主键参数", java.lang.String.class, null);
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final String ID="id";
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> ID_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,ID, java.lang.String.class, "主键", "主键", java.lang.String.class, null);
	
	/**
	 * 人员 , 类型: java.lang.String
	*/
	public static final String EMPLOYEE_ID="employeeId";
	
	/**
	 * 人员 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> EMPLOYEE_ID_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,EMPLOYEE_ID, java.lang.String.class, "人员", "人员", java.lang.String.class, null);
	
	/**
	 * 姓名 , 类型: java.lang.String
	*/
	public static final String EMPLOYEE_NAME="employeeName";
	
	/**
	 * 姓名 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> EMPLOYEE_NAME_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,EMPLOYEE_NAME, java.lang.String.class, "姓名", "姓名", java.lang.String.class, null);
	
	/**
	 * 工号 , 类型: java.lang.String
	*/
	public static final String EMPLOYEE_NUMBER="employeeNumber";
	
	/**
	 * 工号 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> EMPLOYEE_NUMBER_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,EMPLOYEE_NUMBER, java.lang.String.class, "工号", "工号", java.lang.String.class, null);
	
	/**
	 * 考勤日期 , 类型: java.util.Date
	*/
	public static final String ATTENDANCE_DATE="attendanceDate";
	
	/**
	 * 考勤日期 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.util.Date> ATTENDANCE_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,ATTENDANCE_DATE, java.util.Date.class, "考勤日期", "考勤日期", java.util.Date.class, null);
	
	/**
	 * 上班打卡 , 类型: java.util.Date
	*/
	public static final String ON_WORK_TIME="onWorkTime";
	
	/**
	 * 上班打卡 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.util.Date> ON_WORK_TIME_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,ON_WORK_TIME, java.util.Date.class, "上班打卡", "上班打卡", java.util.Date.class, null);
	
	/**
	 * 上班打卡2 , 类型: java.util.Date
	*/
	public static final String ON_WORK_TIME2="onWorkTime2";
	
	/**
	 * 上班打卡2 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.util.Date> ON_WORK_TIME2_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,ON_WORK_TIME2, java.util.Date.class, "上班打卡2", "上班打卡2", java.util.Date.class, null);
	
	/**
	 * 下班打卡 , 类型: java.util.Date
	*/
	public static final String OFF_WORK_TIME="offWorkTime";
	
	/**
	 * 下班打卡 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.util.Date> OFF_WORK_TIME_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,OFF_WORK_TIME, java.util.Date.class, "下班打卡", "下班打卡", java.util.Date.class, null);
	
	/**
	 * 下班打卡2 , 类型: java.util.Date
	*/
	public static final String OFF_WORK_TIME2="offWorkTime2";
	
	/**
	 * 下班打卡2 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.util.Date> OFF_WORK_TIME2_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,OFF_WORK_TIME2, java.util.Date.class, "下班打卡2", "下班打卡2", java.util.Date.class, null);
	
	/**
	 * 早退 , 类型: java.lang.Integer
	*/
	public static final String LEAVE_EARLY="leaveEarly";
	
	/**
	 * 早退 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.Integer> LEAVE_EARLY_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,LEAVE_EARLY, java.lang.Integer.class, "早退", "早退", java.lang.Integer.class, null);
	
	/**
	 * 晚退 , 类型: java.lang.Integer
	*/
	public static final String LEAVE_LATE="leaveLate";
	
	/**
	 * 晚退 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.Integer> LEAVE_LATE_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,LEAVE_LATE, java.lang.Integer.class, "晚退", "晚退", java.lang.Integer.class, null);
	
	/**
	 * 矿工 , 类型: java.lang.Integer
	*/
	public static final String SKIP_WORK="skipWork";
	
	/**
	 * 矿工 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.Integer> SKIP_WORK_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,SKIP_WORK, java.lang.Integer.class, "矿工", "矿工", java.lang.Integer.class, null);
	
	/**
	 * 正常 , 类型: java.lang.Integer
	*/
	public static final String NORMAL_WORK="normalWork";
	
	/**
	 * 正常 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.Integer> NORMAL_WORK_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,NORMAL_WORK, java.lang.Integer.class, "正常", "正常", java.lang.Integer.class, null);
	
	/**
	 * 备注 , 类型: java.lang.String
	*/
	public static final String NOTES="notes";
	
	/**
	 * 备注 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> NOTES_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,NOTES, java.lang.String.class, "备注", "备注", java.lang.String.class, null);
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final String CREATE_BY="createBy";
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> CREATE_BY_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,CREATE_BY, java.lang.String.class, "创建人ID", "创建人ID", java.lang.String.class, null);
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final String CREATE_TIME="createTime";
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.util.Date> CREATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,CREATE_TIME, java.util.Date.class, "创建时间", "创建时间", java.util.Date.class, null);
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final String UPDATE_BY="updateBy";
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> UPDATE_BY_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,UPDATE_BY, java.lang.String.class, "修改人ID", "修改人ID", java.lang.String.class, null);
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final String UPDATE_TIME="updateTime";
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.util.Date> UPDATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,UPDATE_TIME, java.util.Date.class, "修改时间", "修改时间", java.util.Date.class, null);
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final String DELETED="deleted";
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.Integer> DELETED_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,DELETED, java.lang.Integer.class, "是否已删除", "是否已删除", java.lang.Integer.class, null);
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final String DELETE_BY="deleteBy";
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> DELETE_BY_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,DELETE_BY, java.lang.String.class, "删除人ID", "删除人ID", java.lang.String.class, null);
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final String DELETE_TIME="deleteTime";
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.util.Date> DELETE_TIME_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,DELETE_TIME, java.util.Date.class, "删除时间", "删除时间", java.util.Date.class, null);
	
	/**
	 * 数据版本号 , 类型: java.lang.Integer
	*/
	public static final String VERSION="version";
	
	/**
	 * 数据版本号 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.Integer> VERSION_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,VERSION, java.lang.Integer.class, "数据版本号", "数据版本号", java.lang.Integer.class, null);
	
	/**
	 * 租户 , 类型: java.lang.String
	*/
	public static final String TENANT_ID="tenantId";
	
	/**
	 * 租户 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.AttendanceDataVO,java.lang.String> TENANT_ID_PROP = new BeanProperty(com.dt.platform.domain.hr.AttendanceDataVO.class ,TENANT_ID, java.lang.String.class, "租户", "租户", java.lang.String.class, null);
	
	/**
	 * 全部属性清单
	*/
	public static final String[] $PROPS={ PAGE_INDEX , PAGE_SIZE , SEARCH_FIELD , FUZZY_FIELD , SEARCH_VALUE , DIRTY_FIELDS , SORT_FIELD , SORT_TYPE , DATA_ORIGIN , QUERY_LOGIC , IDS , ID , EMPLOYEE_ID , EMPLOYEE_NAME , EMPLOYEE_NUMBER , ATTENDANCE_DATE , ON_WORK_TIME , ON_WORK_TIME2 , OFF_WORK_TIME , OFF_WORK_TIME2 , LEAVE_EARLY , LEAVE_LATE , SKIP_WORK , NORMAL_WORK , NOTES , CREATE_BY , CREATE_TIME , UPDATE_BY , UPDATE_TIME , DELETED , DELETE_BY , DELETE_TIME , VERSION , TENANT_ID };
	
	/**
	 * 代理类
	*/
	public static class $$proxy$$ extends com.dt.platform.domain.hr.AttendanceDataVO {

		private static final long serialVersionUID = 1L;

		
		/**
		 * 设置 页码
		 * @param pageIndex 页码
		 * @return 当前对象
		*/
		public AttendanceDataVO setPageIndex(Integer pageIndex) {
			super.change(PAGE_INDEX,super.getPageIndex(),pageIndex);
			super.setPageIndex(pageIndex);
			return this;
		}
		
		/**
		 * 设置 分页大小
		 * @param pageSize 分页大小
		 * @return 当前对象
		*/
		public AttendanceDataVO setPageSize(Integer pageSize) {
			super.change(PAGE_SIZE,super.getPageSize(),pageSize);
			super.setPageSize(pageSize);
			return this;
		}
		
		/**
		 * 设置 搜索字段
		 * @param searchField 搜索字段
		 * @return 当前对象
		*/
		public AttendanceDataVO setSearchField(String searchField) {
			super.change(SEARCH_FIELD,super.getSearchField(),searchField);
			super.setSearchField(searchField);
			return this;
		}
		
		/**
		 * 设置 模糊搜索字段
		 * @param fuzzyField 模糊搜索字段
		 * @return 当前对象
		*/
		public AttendanceDataVO setFuzzyField(String fuzzyField) {
			super.change(FUZZY_FIELD,super.getFuzzyField(),fuzzyField);
			super.setFuzzyField(fuzzyField);
			return this;
		}
		
		/**
		 * 设置 搜索的值
		 * @param searchValue 搜索的值
		 * @return 当前对象
		*/
		public AttendanceDataVO setSearchValue(String searchValue) {
			super.change(SEARCH_VALUE,super.getSearchValue(),searchValue);
			super.setSearchValue(searchValue);
			return this;
		}
		
		/**
		 * 设置 已修改字段
		 * @param dirtyFields 已修改字段
		 * @return 当前对象
		*/
		public AttendanceDataVO setDirtyFields(List<String> dirtyFields) {
			super.change(DIRTY_FIELDS,super.getDirtyFields(),dirtyFields);
			super.setDirtyFields(dirtyFields);
			return this;
		}
		
		/**
		 * 设置 排序字段
		 * @param sortField 排序字段
		 * @return 当前对象
		*/
		public AttendanceDataVO setSortField(String sortField) {
			super.change(SORT_FIELD,super.getSortField(),sortField);
			super.setSortField(sortField);
			return this;
		}
		
		/**
		 * 设置 排序方式
		 * @param sortType 排序方式
		 * @return 当前对象
		*/
		public AttendanceDataVO setSortType(String sortType) {
			super.change(SORT_TYPE,super.getSortType(),sortType);
			super.setSortType(sortType);
			return this;
		}
		
		/**
		 * 设置 数据来源
		 * @param dataOrigin 数据来源
		 * @return 当前对象
		*/
		public AttendanceDataVO setDataOrigin(String dataOrigin) {
			super.change(DATA_ORIGIN,super.getDataOrigin(),dataOrigin);
			super.setDataOrigin(dataOrigin);
			return this;
		}
		
		/**
		 * 设置 查询逻辑
		 * @param queryLogic 查询逻辑
		 * @return 当前对象
		*/
		public AttendanceDataVO setQueryLogic(String queryLogic) {
			super.change(QUERY_LOGIC,super.getQueryLogic(),queryLogic);
			super.setQueryLogic(queryLogic);
			return this;
		}
		
		/**
		 * 设置 主键清单
		 * @param ids 主键清单
		 * @return 当前对象
		*/
		public AttendanceDataVO setIds(List<String> ids) {
			super.change(IDS,super.getIds(),ids);
			super.setIds(ids);
			return this;
		}
		
		/**
		 * 设置 主键
		 * @param id 主键
		 * @return 当前对象
		*/
		public AttendanceData setId(String id) {
			super.change(ID,super.getId(),id);
			super.setId(id);
			return this;
		}
		
		/**
		 * 设置 人员
		 * @param employeeId 人员
		 * @return 当前对象
		*/
		public AttendanceData setEmployeeId(String employeeId) {
			super.change(EMPLOYEE_ID,super.getEmployeeId(),employeeId);
			super.setEmployeeId(employeeId);
			return this;
		}
		
		/**
		 * 设置 姓名
		 * @param employeeName 姓名
		 * @return 当前对象
		*/
		public AttendanceData setEmployeeName(String employeeName) {
			super.change(EMPLOYEE_NAME,super.getEmployeeName(),employeeName);
			super.setEmployeeName(employeeName);
			return this;
		}
		
		/**
		 * 设置 工号
		 * @param employeeNumber 工号
		 * @return 当前对象
		*/
		public AttendanceData setEmployeeNumber(String employeeNumber) {
			super.change(EMPLOYEE_NUMBER,super.getEmployeeNumber(),employeeNumber);
			super.setEmployeeNumber(employeeNumber);
			return this;
		}
		
		/**
		 * 设置 考勤日期
		 * @param attendanceDate 考勤日期
		 * @return 当前对象
		*/
		public AttendanceData setAttendanceDate(Date attendanceDate) {
			super.change(ATTENDANCE_DATE,super.getAttendanceDate(),attendanceDate);
			super.setAttendanceDate(attendanceDate);
			return this;
		}
		
		/**
		 * 设置 上班打卡
		 * @param onWorkTime 上班打卡
		 * @return 当前对象
		*/
		public AttendanceData setOnWorkTime(Date onWorkTime) {
			super.change(ON_WORK_TIME,super.getOnWorkTime(),onWorkTime);
			super.setOnWorkTime(onWorkTime);
			return this;
		}
		
		/**
		 * 设置 上班打卡2
		 * @param onWorkTime2 上班打卡2
		 * @return 当前对象
		*/
		public AttendanceData setOnWorkTime2(Date onWorkTime2) {
			super.change(ON_WORK_TIME2,super.getOnWorkTime2(),onWorkTime2);
			super.setOnWorkTime2(onWorkTime2);
			return this;
		}
		
		/**
		 * 设置 下班打卡
		 * @param offWorkTime 下班打卡
		 * @return 当前对象
		*/
		public AttendanceData setOffWorkTime(Date offWorkTime) {
			super.change(OFF_WORK_TIME,super.getOffWorkTime(),offWorkTime);
			super.setOffWorkTime(offWorkTime);
			return this;
		}
		
		/**
		 * 设置 下班打卡2
		 * @param offWorkTime2 下班打卡2
		 * @return 当前对象
		*/
		public AttendanceData setOffWorkTime2(Date offWorkTime2) {
			super.change(OFF_WORK_TIME2,super.getOffWorkTime2(),offWorkTime2);
			super.setOffWorkTime2(offWorkTime2);
			return this;
		}
		
		/**
		 * 设置 早退
		 * @param leaveEarly 早退
		 * @return 当前对象
		*/
		public AttendanceData setLeaveEarly(Integer leaveEarly) {
			super.change(LEAVE_EARLY,super.getLeaveEarly(),leaveEarly);
			super.setLeaveEarly(leaveEarly);
			return this;
		}
		
		/**
		 * 设置 晚退
		 * @param leaveLate 晚退
		 * @return 当前对象
		*/
		public AttendanceData setLeaveLate(Integer leaveLate) {
			super.change(LEAVE_LATE,super.getLeaveLate(),leaveLate);
			super.setLeaveLate(leaveLate);
			return this;
		}
		
		/**
		 * 设置 矿工
		 * @param skipWork 矿工
		 * @return 当前对象
		*/
		public AttendanceData setSkipWork(Integer skipWork) {
			super.change(SKIP_WORK,super.getSkipWork(),skipWork);
			super.setSkipWork(skipWork);
			return this;
		}
		
		/**
		 * 设置 正常
		 * @param normalWork 正常
		 * @return 当前对象
		*/
		public AttendanceData setNormalWork(Integer normalWork) {
			super.change(NORMAL_WORK,super.getNormalWork(),normalWork);
			super.setNormalWork(normalWork);
			return this;
		}
		
		/**
		 * 设置 备注
		 * @param notes 备注
		 * @return 当前对象
		*/
		public AttendanceData setNotes(String notes) {
			super.change(NOTES,super.getNotes(),notes);
			super.setNotes(notes);
			return this;
		}
		
		/**
		 * 设置 创建人ID
		 * @param createBy 创建人ID
		 * @return 当前对象
		*/
		public AttendanceData setCreateBy(String createBy) {
			super.change(CREATE_BY,super.getCreateBy(),createBy);
			super.setCreateBy(createBy);
			return this;
		}
		
		/**
		 * 设置 创建时间
		 * @param createTime 创建时间
		 * @return 当前对象
		*/
		public AttendanceData setCreateTime(Date createTime) {
			super.change(CREATE_TIME,super.getCreateTime(),createTime);
			super.setCreateTime(createTime);
			return this;
		}
		
		/**
		 * 设置 修改人ID
		 * @param updateBy 修改人ID
		 * @return 当前对象
		*/
		public AttendanceData setUpdateBy(String updateBy) {
			super.change(UPDATE_BY,super.getUpdateBy(),updateBy);
			super.setUpdateBy(updateBy);
			return this;
		}
		
		/**
		 * 设置 修改时间
		 * @param updateTime 修改时间
		 * @return 当前对象
		*/
		public AttendanceData setUpdateTime(Date updateTime) {
			super.change(UPDATE_TIME,super.getUpdateTime(),updateTime);
			super.setUpdateTime(updateTime);
			return this;
		}
		
		/**
		 * 设置 是否已删除
		 * @param deleted 是否已删除
		 * @return 当前对象
		*/
		public AttendanceData setDeleted(Integer deleted) {
			super.change(DELETED,super.getDeleted(),deleted);
			super.setDeleted(deleted);
			return this;
		}
		
		/**
		 * 设置 删除人ID
		 * @param deleteBy 删除人ID
		 * @return 当前对象
		*/
		public AttendanceData setDeleteBy(String deleteBy) {
			super.change(DELETE_BY,super.getDeleteBy(),deleteBy);
			super.setDeleteBy(deleteBy);
			return this;
		}
		
		/**
		 * 设置 删除时间
		 * @param deleteTime 删除时间
		 * @return 当前对象
		*/
		public AttendanceData setDeleteTime(Date deleteTime) {
			super.change(DELETE_TIME,super.getDeleteTime(),deleteTime);
			super.setDeleteTime(deleteTime);
			return this;
		}
		
		/**
		 * 设置 数据版本号
		 * @param version 数据版本号
		 * @return 当前对象
		*/
		public AttendanceData setVersion(Integer version) {
			super.change(VERSION,super.getVersion(),version);
			super.setVersion(version);
			return this;
		}
		
		/**
		 * 设置 租户
		 * @param tenantId 租户
		 * @return 当前对象
		*/
		public AttendanceData setTenantId(String tenantId) {
			super.change(TENANT_ID,super.getTenantId(),tenantId);
			super.setTenantId(tenantId);
			return this;
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
			$$proxy$$ inst=new $$proxy$$();
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

	}
}