package com.dt.platform.domain.ops;

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
import com.dt.platform.domain.ops.meta.DbInfoVOMeta;
import com.github.foxnic.commons.lang.DataParser;
import java.util.Date;
import java.math.BigDecimal;
import org.github.foxnic.web.domain.system.DictItem;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 数据库VO类型
 * <p>数据库 , 数据表 ops_db_info 的通用VO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-11 11:52:07
 * @sign 62DFC6503B8B14397E15FD04C9DAC032
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@ApiModel(description = "数据库VO类型 ; 数据库 , 数据表 ops_db_info 的通用VO类型" , parent = DbInfo.class)
public class DbInfoVO extends DbInfo {

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
	public DbInfoVO setPageIndex(Integer pageIndex) {
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
	public DbInfoVO setPageSize(Integer pageSize) {
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
	public DbInfoVO setSearchField(String searchField) {
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
	public DbInfoVO setFuzzyField(String fuzzyField) {
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
	public DbInfoVO setSearchValue(String searchValue) {
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
	public DbInfoVO setDirtyFields(List<String> dirtyFields) {
		this.dirtyFields=dirtyFields;
		return this;
	}
	
	/**
	 * 添加 已修改字段
	 * @param dirtyField 已修改字段
	 * @return 当前对象
	*/
	public DbInfoVO addDirtyField(String... dirtyField) {
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
	public DbInfoVO setSortField(String sortField) {
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
	public DbInfoVO setSortType(String sortType) {
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
	public DbInfoVO setDataOrigin(String dataOrigin) {
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
	public DbInfoVO setQueryLogic(String queryLogic) {
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
	public DbInfoVO setIds(List<String> ids) {
		this.ids=ids;
		return this;
	}
	
	/**
	 * 添加 主键清单
	 * @param id 主键清单
	 * @return 当前对象
	*/
	public DbInfoVO addId(String... id) {
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
	 * @return DbInfoVO , 转换好的 DbInfoVO 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return DbInfoVO , 转换好的 PoJo 对象
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
	public DbInfoVO clone() {
		return duplicate(true);
	}

	/**
	 * 复制当前对象
	 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
	*/
	@Transient
	public DbInfoVO duplicate(boolean all) {
		com.dt.platform.domain.ops.meta.DbInfoVOMeta.$$proxy$$ inst = new com.dt.platform.domain.ops.meta.DbInfoVOMeta.$$proxy$$();
		inst.setNotes(this.getNotes());
		inst.setLogMethod(this.getLogMethod());
		inst.setSelectedCode(this.getSelectedCode());
		inst.setBackupStatus(this.getBackupStatus());
		inst.setBackupStrategy(this.getBackupStrategy());
		inst.setUpdateBy(this.getUpdateBy());
		inst.setVoucherStr(this.getVoucherStr());
		inst.setId(this.getId());
		inst.setAdminUserList(this.getAdminUserList());
		inst.setBackupInfo(this.getBackupInfo());
		inst.setUserUseInfo(this.getUserUseInfo());
		inst.setDisasterRecoveryStrategy(this.getDisasterRecoveryStrategy());
		inst.setToolStrategy(this.getToolStrategy());
		inst.setAppUserList(this.getAppUserList());
		inst.setDeployMode(this.getDeployMode());
		inst.setOpsUserList(this.getOpsUserList());
		inst.setOtherUserList(this.getOtherUserList());
		inst.setHostId(this.getHostId());
		inst.setUpdateTime(this.getUpdateTime());
		inst.setVersion(this.getVersion());
		inst.setDbSize(this.getDbSize());
		inst.setClearStrategy(this.getClearStrategy());
		inst.setDbPort(this.getDbPort());
		inst.setCreateBy(this.getCreateBy());
		inst.setDataLoc(this.getDataLoc());
		inst.setDeleted(this.getDeleted());
		inst.setCreateTime(this.getCreateTime());
		inst.setDeleteTime(this.getDeleteTime());
		inst.setName(this.getName());
		inst.setDeleteBy(this.getDeleteBy());
		inst.setTypeId(this.getTypeId());
		inst.setStatus(this.getStatus());
		if(all) {
			inst.setSearchField(this.getSearchField());
			inst.setBackupInfoList(this.getBackupInfoList());
			inst.setFuzzyField(this.getFuzzyField());
			inst.setPageSize(this.getPageSize());
			inst.setType(this.getType());
			inst.setLabelList(this.getLabelList());
			inst.setLabelIds(this.getLabelIds());
			inst.setDataLocData(this.getDataLocData());
			inst.setPageIndex(this.getPageIndex());
			inst.setSortType(this.getSortType());
			inst.setHost(this.getHost());
			inst.setDirtyFields(this.getDirtyFields());
			inst.setSortField(this.getSortField());
			inst.setDataOrigin(this.getDataOrigin());
			inst.setIds(this.getIds());
			inst.setBackupInfoIds(this.getBackupInfoIds());
			inst.setQueryLogic(this.getQueryLogic());
			inst.setDeployModeDict(this.getDeployModeDict());
			inst.setSearchValue(this.getSearchValue());
			inst.setDataLocIds(this.getDataLocIds());
		}
		inst.clearModifies();
		return inst;
	}

	/**
	 * 克隆当前对象
	*/
	@Transient
	public DbInfoVO clone(boolean deep) {
		return EntityContext.clone(DbInfoVO.class,this,deep);
	}

	/**
	 * 将 Map 转换成 DbInfoVO
	 * @param dbInfoMap 包含实体信息的 Map 对象
	 * @return DbInfoVO , 转换好的的 DbInfo 对象
	*/
	@Transient
	public static DbInfoVO createFrom(Map<String,Object> dbInfoMap) {
		if(dbInfoMap==null) return null;
		DbInfoVO vo = create();
		EntityContext.copyProperties(vo,dbInfoMap);
		vo.clearModifies();
		return vo;
	}

	/**
	 * 将 Pojo 转换成 DbInfoVO
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return DbInfoVO , 转换好的的 DbInfo 对象
	*/
	@Transient
	public static DbInfoVO createFrom(Object pojo) {
		if(pojo==null) return null;
		DbInfoVO vo = create();
		EntityContext.copyProperties(vo,pojo);
		vo.clearModifies();
		return vo;
	}

	/**
	 * 创建一个 DbInfoVO，等同于 new
	 * @return DbInfoVO 对象
	*/
	@Transient
	public static DbInfoVO create() {
		return new com.dt.platform.domain.ops.meta.DbInfoVOMeta.$$proxy$$();
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
			this.setNotes(DataParser.parse(String.class, map.get(DbInfoVOMeta.NOTES)));
			this.setLogMethod(DataParser.parse(String.class, map.get(DbInfoVOMeta.LOG_METHOD)));
			this.setSelectedCode(DataParser.parse(String.class, map.get(DbInfoVOMeta.SELECTED_CODE)));
			this.setBackupStatus(DataParser.parse(String.class, map.get(DbInfoVOMeta.BACKUP_STATUS)));
			this.setBackupStrategy(DataParser.parse(String.class, map.get(DbInfoVOMeta.BACKUP_STRATEGY)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(DbInfoVOMeta.UPDATE_BY)));
			this.setVoucherStr(DataParser.parse(String.class, map.get(DbInfoVOMeta.VOUCHER_STR)));
			this.setId(DataParser.parse(String.class, map.get(DbInfoVOMeta.ID)));
			this.setAdminUserList(DataParser.parse(String.class, map.get(DbInfoVOMeta.ADMIN_USER_LIST)));
			this.setBackupInfo(DataParser.parse(String.class, map.get(DbInfoVOMeta.BACKUP_INFO)));
			this.setUserUseInfo(DataParser.parse(String.class, map.get(DbInfoVOMeta.USER_USE_INFO)));
			this.setDisasterRecoveryStrategy(DataParser.parse(String.class, map.get(DbInfoVOMeta.DISASTER_RECOVERY_STRATEGY)));
			this.setToolStrategy(DataParser.parse(String.class, map.get(DbInfoVOMeta.TOOL_STRATEGY)));
			this.setAppUserList(DataParser.parse(String.class, map.get(DbInfoVOMeta.APP_USER_LIST)));
			this.setDeployMode(DataParser.parse(String.class, map.get(DbInfoVOMeta.DEPLOY_MODE)));
			this.setOpsUserList(DataParser.parse(String.class, map.get(DbInfoVOMeta.OPS_USER_LIST)));
			this.setOtherUserList(DataParser.parse(String.class, map.get(DbInfoVOMeta.OTHER_USER_LIST)));
			this.setHostId(DataParser.parse(String.class, map.get(DbInfoVOMeta.HOST_ID)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(DbInfoVOMeta.UPDATE_TIME)));
			this.setVersion(DataParser.parse(Integer.class, map.get(DbInfoVOMeta.VERSION)));
			this.setDbSize(DataParser.parse(BigDecimal.class, map.get(DbInfoVOMeta.DB_SIZE)));
			this.setClearStrategy(DataParser.parse(String.class, map.get(DbInfoVOMeta.CLEAR_STRATEGY)));
			this.setDbPort(DataParser.parse(String.class, map.get(DbInfoVOMeta.DB_PORT)));
			this.setCreateBy(DataParser.parse(String.class, map.get(DbInfoVOMeta.CREATE_BY)));
			this.setDataLoc(DataParser.parse(String.class, map.get(DbInfoVOMeta.DATA_LOC)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(DbInfoVOMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(DbInfoVOMeta.CREATE_TIME)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(DbInfoVOMeta.DELETE_TIME)));
			this.setName(DataParser.parse(String.class, map.get(DbInfoVOMeta.NAME)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(DbInfoVOMeta.DELETE_BY)));
			this.setTypeId(DataParser.parse(String.class, map.get(DbInfoVOMeta.TYPE_ID)));
			this.setStatus(DataParser.parse(String.class, map.get(DbInfoVOMeta.STATUS)));
			// others
			this.setSearchField(DataParser.parse(String.class, map.get(DbInfoVOMeta.SEARCH_FIELD)));
			this.setFuzzyField(DataParser.parse(String.class, map.get(DbInfoVOMeta.FUZZY_FIELD)));
			this.setPageSize(DataParser.parse(Integer.class, map.get(DbInfoVOMeta.PAGE_SIZE)));
			this.setType(DataParser.parse(ServiceInfo.class, map.get(DbInfoVOMeta.TYPE)));
			this.setPageIndex(DataParser.parse(Integer.class, map.get(DbInfoVOMeta.PAGE_INDEX)));
			this.setSortType(DataParser.parse(String.class, map.get(DbInfoVOMeta.SORT_TYPE)));
			this.setHost(DataParser.parse(Host.class, map.get(DbInfoVOMeta.HOST)));
			this.setSortField(DataParser.parse(String.class, map.get(DbInfoVOMeta.SORT_FIELD)));
			this.setDataOrigin(DataParser.parse(String.class, map.get(DbInfoVOMeta.DATA_ORIGIN)));
			this.setQueryLogic(DataParser.parse(String.class, map.get(DbInfoVOMeta.QUERY_LOGIC)));
			this.setDeployModeDict(DataParser.parse(DictItem.class, map.get(DbInfoVOMeta.DEPLOY_MODE_DICT)));
			this.setSearchValue(DataParser.parse(String.class, map.get(DbInfoVOMeta.SEARCH_VALUE)));
			return true;
		} else {
			try {
				this.setNotes( (String)map.get(DbInfoVOMeta.NOTES));
				this.setLogMethod( (String)map.get(DbInfoVOMeta.LOG_METHOD));
				this.setSelectedCode( (String)map.get(DbInfoVOMeta.SELECTED_CODE));
				this.setBackupStatus( (String)map.get(DbInfoVOMeta.BACKUP_STATUS));
				this.setBackupStrategy( (String)map.get(DbInfoVOMeta.BACKUP_STRATEGY));
				this.setUpdateBy( (String)map.get(DbInfoVOMeta.UPDATE_BY));
				this.setVoucherStr( (String)map.get(DbInfoVOMeta.VOUCHER_STR));
				this.setId( (String)map.get(DbInfoVOMeta.ID));
				this.setAdminUserList( (String)map.get(DbInfoVOMeta.ADMIN_USER_LIST));
				this.setBackupInfo( (String)map.get(DbInfoVOMeta.BACKUP_INFO));
				this.setUserUseInfo( (String)map.get(DbInfoVOMeta.USER_USE_INFO));
				this.setDisasterRecoveryStrategy( (String)map.get(DbInfoVOMeta.DISASTER_RECOVERY_STRATEGY));
				this.setToolStrategy( (String)map.get(DbInfoVOMeta.TOOL_STRATEGY));
				this.setAppUserList( (String)map.get(DbInfoVOMeta.APP_USER_LIST));
				this.setDeployMode( (String)map.get(DbInfoVOMeta.DEPLOY_MODE));
				this.setOpsUserList( (String)map.get(DbInfoVOMeta.OPS_USER_LIST));
				this.setOtherUserList( (String)map.get(DbInfoVOMeta.OTHER_USER_LIST));
				this.setHostId( (String)map.get(DbInfoVOMeta.HOST_ID));
				this.setUpdateTime( (Date)map.get(DbInfoVOMeta.UPDATE_TIME));
				this.setVersion( (Integer)map.get(DbInfoVOMeta.VERSION));
				this.setDbSize( (BigDecimal)map.get(DbInfoVOMeta.DB_SIZE));
				this.setClearStrategy( (String)map.get(DbInfoVOMeta.CLEAR_STRATEGY));
				this.setDbPort( (String)map.get(DbInfoVOMeta.DB_PORT));
				this.setCreateBy( (String)map.get(DbInfoVOMeta.CREATE_BY));
				this.setDataLoc( (String)map.get(DbInfoVOMeta.DATA_LOC));
				this.setDeleted( (Integer)map.get(DbInfoVOMeta.DELETED));
				this.setCreateTime( (Date)map.get(DbInfoVOMeta.CREATE_TIME));
				this.setDeleteTime( (Date)map.get(DbInfoVOMeta.DELETE_TIME));
				this.setName( (String)map.get(DbInfoVOMeta.NAME));
				this.setDeleteBy( (String)map.get(DbInfoVOMeta.DELETE_BY));
				this.setTypeId( (String)map.get(DbInfoVOMeta.TYPE_ID));
				this.setStatus( (String)map.get(DbInfoVOMeta.STATUS));
				// others
				this.setSearchField( (String)map.get(DbInfoVOMeta.SEARCH_FIELD));
				this.setFuzzyField( (String)map.get(DbInfoVOMeta.FUZZY_FIELD));
				this.setPageSize( (Integer)map.get(DbInfoVOMeta.PAGE_SIZE));
				this.setType( (ServiceInfo)map.get(DbInfoVOMeta.TYPE));
				this.setPageIndex( (Integer)map.get(DbInfoVOMeta.PAGE_INDEX));
				this.setSortType( (String)map.get(DbInfoVOMeta.SORT_TYPE));
				this.setHost( (Host)map.get(DbInfoVOMeta.HOST));
				this.setSortField( (String)map.get(DbInfoVOMeta.SORT_FIELD));
				this.setDataOrigin( (String)map.get(DbInfoVOMeta.DATA_ORIGIN));
				this.setQueryLogic( (String)map.get(DbInfoVOMeta.QUERY_LOGIC));
				this.setDeployModeDict( (DictItem)map.get(DbInfoVOMeta.DEPLOY_MODE_DICT));
				this.setSearchValue( (String)map.get(DbInfoVOMeta.SEARCH_VALUE));
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
			this.setNotes(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.NOTES)));
			this.setLogMethod(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.LOG_METHOD)));
			this.setSelectedCode(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.SELECTED_CODE)));
			this.setBackupStatus(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.BACKUP_STATUS)));
			this.setBackupStrategy(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.BACKUP_STRATEGY)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.UPDATE_BY)));
			this.setVoucherStr(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.VOUCHER_STR)));
			this.setId(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.ID)));
			this.setAdminUserList(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.ADMIN_USER_LIST)));
			this.setBackupInfo(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.BACKUP_INFO)));
			this.setUserUseInfo(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.USER_USE_INFO)));
			this.setDisasterRecoveryStrategy(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.DISASTER_RECOVERY_STRATEGY)));
			this.setToolStrategy(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.TOOL_STRATEGY)));
			this.setAppUserList(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.APP_USER_LIST)));
			this.setDeployMode(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.DEPLOY_MODE)));
			this.setOpsUserList(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.OPS_USER_LIST)));
			this.setOtherUserList(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.OTHER_USER_LIST)));
			this.setHostId(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.HOST_ID)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(DbInfoVOMeta.UPDATE_TIME)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(DbInfoVOMeta.VERSION)));
			this.setDbSize(DataParser.parse(BigDecimal.class, r.getValue(DbInfoVOMeta.DB_SIZE)));
			this.setClearStrategy(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.CLEAR_STRATEGY)));
			this.setDbPort(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.DB_PORT)));
			this.setCreateBy(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.CREATE_BY)));
			this.setDataLoc(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.DATA_LOC)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(DbInfoVOMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(DbInfoVOMeta.CREATE_TIME)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(DbInfoVOMeta.DELETE_TIME)));
			this.setName(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.NAME)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.DELETE_BY)));
			this.setTypeId(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.TYPE_ID)));
			this.setStatus(DataParser.parse(String.class, r.getValue(DbInfoVOMeta.STATUS)));
			return true;
		} else {
			try {
				this.setNotes( (String)r.getValue(DbInfoVOMeta.NOTES));
				this.setLogMethod( (String)r.getValue(DbInfoVOMeta.LOG_METHOD));
				this.setSelectedCode( (String)r.getValue(DbInfoVOMeta.SELECTED_CODE));
				this.setBackupStatus( (String)r.getValue(DbInfoVOMeta.BACKUP_STATUS));
				this.setBackupStrategy( (String)r.getValue(DbInfoVOMeta.BACKUP_STRATEGY));
				this.setUpdateBy( (String)r.getValue(DbInfoVOMeta.UPDATE_BY));
				this.setVoucherStr( (String)r.getValue(DbInfoVOMeta.VOUCHER_STR));
				this.setId( (String)r.getValue(DbInfoVOMeta.ID));
				this.setAdminUserList( (String)r.getValue(DbInfoVOMeta.ADMIN_USER_LIST));
				this.setBackupInfo( (String)r.getValue(DbInfoVOMeta.BACKUP_INFO));
				this.setUserUseInfo( (String)r.getValue(DbInfoVOMeta.USER_USE_INFO));
				this.setDisasterRecoveryStrategy( (String)r.getValue(DbInfoVOMeta.DISASTER_RECOVERY_STRATEGY));
				this.setToolStrategy( (String)r.getValue(DbInfoVOMeta.TOOL_STRATEGY));
				this.setAppUserList( (String)r.getValue(DbInfoVOMeta.APP_USER_LIST));
				this.setDeployMode( (String)r.getValue(DbInfoVOMeta.DEPLOY_MODE));
				this.setOpsUserList( (String)r.getValue(DbInfoVOMeta.OPS_USER_LIST));
				this.setOtherUserList( (String)r.getValue(DbInfoVOMeta.OTHER_USER_LIST));
				this.setHostId( (String)r.getValue(DbInfoVOMeta.HOST_ID));
				this.setUpdateTime( (Date)r.getValue(DbInfoVOMeta.UPDATE_TIME));
				this.setVersion( (Integer)r.getValue(DbInfoVOMeta.VERSION));
				this.setDbSize( (BigDecimal)r.getValue(DbInfoVOMeta.DB_SIZE));
				this.setClearStrategy( (String)r.getValue(DbInfoVOMeta.CLEAR_STRATEGY));
				this.setDbPort( (String)r.getValue(DbInfoVOMeta.DB_PORT));
				this.setCreateBy( (String)r.getValue(DbInfoVOMeta.CREATE_BY));
				this.setDataLoc( (String)r.getValue(DbInfoVOMeta.DATA_LOC));
				this.setDeleted( (Integer)r.getValue(DbInfoVOMeta.DELETED));
				this.setCreateTime( (Date)r.getValue(DbInfoVOMeta.CREATE_TIME));
				this.setDeleteTime( (Date)r.getValue(DbInfoVOMeta.DELETE_TIME));
				this.setName( (String)r.getValue(DbInfoVOMeta.NAME));
				this.setDeleteBy( (String)r.getValue(DbInfoVOMeta.DELETE_BY));
				this.setTypeId( (String)r.getValue(DbInfoVOMeta.TYPE_ID));
				this.setStatus( (String)r.getValue(DbInfoVOMeta.STATUS));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}