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
import com.dt.platform.domain.ops.meta.DbRecoveryRecVOMeta;
import com.github.foxnic.commons.lang.DataParser;
import java.util.Date;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 数据库恢复VO类型
 * <p>数据库恢复 , 数据表 ops_db_recovery_rec 的通用VO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-07-14 20:47:35
 * @sign 1789BC405404F91DF4F30E728FE9425B
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@ApiModel(description = "数据库恢复VO类型 ; 数据库恢复 , 数据表 ops_db_recovery_rec 的通用VO类型" , parent = DbRecoveryRec.class)
public class DbRecoveryRecVO extends DbRecoveryRec {

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
	 * 数据来源：前端指定不同的来源，后端可按来源执行不同的逻辑
	*/
	@ApiModelProperty(required = false,value="数据来源" , notes = "前端指定不同的来源，后端可按来源执行不同的逻辑")
	private String dataOrigin;
	
	/**
	 * 查询逻辑：默认and，可指定 or 
	*/
	@ApiModelProperty(required = false,value="查询逻辑" , notes = "默认and，可指定 or ")
	private String queryLogic;
	
	/**
	 * 请求动作：前端指定不同的Action，后端可Action执行不同的逻辑
	*/
	@ApiModelProperty(required = false,value="请求动作" , notes = "前端指定不同的Action，后端可Action执行不同的逻辑")
	private String requestAction;
	
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
	public DbRecoveryRecVO setPageIndex(Integer pageIndex) {
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
	public DbRecoveryRecVO setPageSize(Integer pageSize) {
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
	public DbRecoveryRecVO setSearchField(String searchField) {
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
	public DbRecoveryRecVO setFuzzyField(String fuzzyField) {
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
	public DbRecoveryRecVO setSearchValue(String searchValue) {
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
	public DbRecoveryRecVO setDirtyFields(List<String> dirtyFields) {
		this.dirtyFields=dirtyFields;
		return this;
	}
	
	/**
	 * 添加 已修改字段
	 * @param dirtyField 已修改字段
	 * @return 当前对象
	*/
	public DbRecoveryRecVO addDirtyField(String... dirtyField) {
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
	public DbRecoveryRecVO setSortField(String sortField) {
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
	public DbRecoveryRecVO setSortType(String sortType) {
		this.sortType=sortType;
		return this;
	}
	
	/**
	 * 获得 数据来源<br>
	 * 前端指定不同的来源，后端可按来源执行不同的逻辑
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
	public DbRecoveryRecVO setDataOrigin(String dataOrigin) {
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
	public DbRecoveryRecVO setQueryLogic(String queryLogic) {
		this.queryLogic=queryLogic;
		return this;
	}
	
	/**
	 * 获得 请求动作<br>
	 * 前端指定不同的Action，后端可Action执行不同的逻辑
	 * @return 请求动作
	*/
	public String getRequestAction() {
		return requestAction;
	}
	
	/**
	 * 设置 请求动作
	 * @param requestAction 请求动作
	 * @return 当前对象
	*/
	public DbRecoveryRecVO setRequestAction(String requestAction) {
		this.requestAction=requestAction;
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
	public DbRecoveryRecVO setIds(List<String> ids) {
		this.ids=ids;
		return this;
	}
	
	/**
	 * 添加 主键清单
	 * @param id 主键清单
	 * @return 当前对象
	*/
	public DbRecoveryRecVO addId(String... id) {
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
	 * @return DbRecoveryRecVO , 转换好的 DbRecoveryRecVO 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return DbRecoveryRecVO , 转换好的 PoJo 对象
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
	public DbRecoveryRecVO clone() {
		return duplicate(true);
	}

	/**
	 * 复制当前对象
	 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
	*/
	@Transient
	public DbRecoveryRecVO duplicate(boolean all) {
		com.dt.platform.domain.ops.meta.DbRecoveryRecVOMeta.$$proxy$$ inst = new com.dt.platform.domain.ops.meta.DbRecoveryRecVOMeta.$$proxy$$();
		inst.setNotes(this.getNotes());
		inst.setDbInstanceId(this.getDbInstanceId());
		inst.setUpdateTime(this.getUpdateTime());
		inst.setType(this.getType());
		inst.setRecDate(this.getRecDate());
		inst.setVersion(this.getVersion());
		inst.setCreateBy(this.getCreateBy());
		inst.setDeleted(this.getDeleted());
		inst.setCreateTime(this.getCreateTime());
		inst.setUpdateBy(this.getUpdateBy());
		inst.setDeleteTime(this.getDeleteTime());
		inst.setName(this.getName());
		inst.setDeleteBy(this.getDeleteBy());
		inst.setId(this.getId());
		inst.setFileId(this.getFileId());
		if(all) {
			inst.setDbInstance(this.getDbInstance());
			inst.setSearchField(this.getSearchField());
			inst.setRequestAction(this.getRequestAction());
			inst.setFuzzyField(this.getFuzzyField());
			inst.setPageSize(this.getPageSize());
			inst.setPageIndex(this.getPageIndex());
			inst.setSortType(this.getSortType());
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

	/**
	 * 克隆当前对象
	*/
	@Transient
	public DbRecoveryRecVO clone(boolean deep) {
		return EntityContext.clone(DbRecoveryRecVO.class,this,deep);
	}

	/**
	 * 将 Map 转换成 DbRecoveryRecVO
	 * @param dbRecoveryRecMap 包含实体信息的 Map 对象
	 * @return DbRecoveryRecVO , 转换好的的 DbRecoveryRec 对象
	*/
	@Transient
	public static DbRecoveryRecVO createFrom(Map<String,Object> dbRecoveryRecMap) {
		if(dbRecoveryRecMap==null) return null;
		DbRecoveryRecVO vo = create();
		EntityContext.copyProperties(vo,dbRecoveryRecMap);
		vo.clearModifies();
		return vo;
	}

	/**
	 * 将 Pojo 转换成 DbRecoveryRecVO
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return DbRecoveryRecVO , 转换好的的 DbRecoveryRec 对象
	*/
	@Transient
	public static DbRecoveryRecVO createFrom(Object pojo) {
		if(pojo==null) return null;
		DbRecoveryRecVO vo = create();
		EntityContext.copyProperties(vo,pojo);
		vo.clearModifies();
		return vo;
	}

	/**
	 * 创建一个 DbRecoveryRecVO，等同于 new
	 * @return DbRecoveryRecVO 对象
	*/
	@Transient
	public static DbRecoveryRecVO create() {
		return new com.dt.platform.domain.ops.meta.DbRecoveryRecVOMeta.$$proxy$$();
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
			this.setNotes(DataParser.parse(String.class, map.get(DbRecoveryRecVOMeta.NOTES)));
			this.setDbInstanceId(DataParser.parse(String.class, map.get(DbRecoveryRecVOMeta.DB_INSTANCE_ID)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(DbRecoveryRecVOMeta.UPDATE_TIME)));
			this.setType(DataParser.parse(String.class, map.get(DbRecoveryRecVOMeta.TYPE)));
			this.setRecDate(DataParser.parse(Date.class, map.get(DbRecoveryRecVOMeta.REC_DATE)));
			this.setVersion(DataParser.parse(Integer.class, map.get(DbRecoveryRecVOMeta.VERSION)));
			this.setCreateBy(DataParser.parse(String.class, map.get(DbRecoveryRecVOMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(DbRecoveryRecVOMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(DbRecoveryRecVOMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(DbRecoveryRecVOMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(DbRecoveryRecVOMeta.DELETE_TIME)));
			this.setName(DataParser.parse(String.class, map.get(DbRecoveryRecVOMeta.NAME)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(DbRecoveryRecVOMeta.DELETE_BY)));
			this.setId(DataParser.parse(String.class, map.get(DbRecoveryRecVOMeta.ID)));
			this.setFileId(DataParser.parse(String.class, map.get(DbRecoveryRecVOMeta.FILE_ID)));
			// others
			this.setDbInstance(DataParser.parse(DbInstance.class, map.get(DbRecoveryRecVOMeta.DB_INSTANCE)));
			this.setSearchField(DataParser.parse(String.class, map.get(DbRecoveryRecVOMeta.SEARCH_FIELD)));
			this.setRequestAction(DataParser.parse(String.class, map.get(DbRecoveryRecVOMeta.REQUEST_ACTION)));
			this.setFuzzyField(DataParser.parse(String.class, map.get(DbRecoveryRecVOMeta.FUZZY_FIELD)));
			this.setPageSize(DataParser.parse(Integer.class, map.get(DbRecoveryRecVOMeta.PAGE_SIZE)));
			this.setPageIndex(DataParser.parse(Integer.class, map.get(DbRecoveryRecVOMeta.PAGE_INDEX)));
			this.setSortType(DataParser.parse(String.class, map.get(DbRecoveryRecVOMeta.SORT_TYPE)));
			this.setSortField(DataParser.parse(String.class, map.get(DbRecoveryRecVOMeta.SORT_FIELD)));
			this.setDataOrigin(DataParser.parse(String.class, map.get(DbRecoveryRecVOMeta.DATA_ORIGIN)));
			this.setQueryLogic(DataParser.parse(String.class, map.get(DbRecoveryRecVOMeta.QUERY_LOGIC)));
			this.setSearchValue(DataParser.parse(String.class, map.get(DbRecoveryRecVOMeta.SEARCH_VALUE)));
			return true;
		} else {
			try {
				this.setNotes( (String)map.get(DbRecoveryRecVOMeta.NOTES));
				this.setDbInstanceId( (String)map.get(DbRecoveryRecVOMeta.DB_INSTANCE_ID));
				this.setUpdateTime( (Date)map.get(DbRecoveryRecVOMeta.UPDATE_TIME));
				this.setType( (String)map.get(DbRecoveryRecVOMeta.TYPE));
				this.setRecDate( (Date)map.get(DbRecoveryRecVOMeta.REC_DATE));
				this.setVersion( (Integer)map.get(DbRecoveryRecVOMeta.VERSION));
				this.setCreateBy( (String)map.get(DbRecoveryRecVOMeta.CREATE_BY));
				this.setDeleted( (Integer)map.get(DbRecoveryRecVOMeta.DELETED));
				this.setCreateTime( (Date)map.get(DbRecoveryRecVOMeta.CREATE_TIME));
				this.setUpdateBy( (String)map.get(DbRecoveryRecVOMeta.UPDATE_BY));
				this.setDeleteTime( (Date)map.get(DbRecoveryRecVOMeta.DELETE_TIME));
				this.setName( (String)map.get(DbRecoveryRecVOMeta.NAME));
				this.setDeleteBy( (String)map.get(DbRecoveryRecVOMeta.DELETE_BY));
				this.setId( (String)map.get(DbRecoveryRecVOMeta.ID));
				this.setFileId( (String)map.get(DbRecoveryRecVOMeta.FILE_ID));
				// others
				this.setDbInstance( (DbInstance)map.get(DbRecoveryRecVOMeta.DB_INSTANCE));
				this.setSearchField( (String)map.get(DbRecoveryRecVOMeta.SEARCH_FIELD));
				this.setRequestAction( (String)map.get(DbRecoveryRecVOMeta.REQUEST_ACTION));
				this.setFuzzyField( (String)map.get(DbRecoveryRecVOMeta.FUZZY_FIELD));
				this.setPageSize( (Integer)map.get(DbRecoveryRecVOMeta.PAGE_SIZE));
				this.setPageIndex( (Integer)map.get(DbRecoveryRecVOMeta.PAGE_INDEX));
				this.setSortType( (String)map.get(DbRecoveryRecVOMeta.SORT_TYPE));
				this.setSortField( (String)map.get(DbRecoveryRecVOMeta.SORT_FIELD));
				this.setDataOrigin( (String)map.get(DbRecoveryRecVOMeta.DATA_ORIGIN));
				this.setQueryLogic( (String)map.get(DbRecoveryRecVOMeta.QUERY_LOGIC));
				this.setSearchValue( (String)map.get(DbRecoveryRecVOMeta.SEARCH_VALUE));
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
			this.setNotes(DataParser.parse(String.class, r.getValue(DbRecoveryRecVOMeta.NOTES)));
			this.setDbInstanceId(DataParser.parse(String.class, r.getValue(DbRecoveryRecVOMeta.DB_INSTANCE_ID)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(DbRecoveryRecVOMeta.UPDATE_TIME)));
			this.setType(DataParser.parse(String.class, r.getValue(DbRecoveryRecVOMeta.TYPE)));
			this.setRecDate(DataParser.parse(Date.class, r.getValue(DbRecoveryRecVOMeta.REC_DATE)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(DbRecoveryRecVOMeta.VERSION)));
			this.setCreateBy(DataParser.parse(String.class, r.getValue(DbRecoveryRecVOMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(DbRecoveryRecVOMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(DbRecoveryRecVOMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(DbRecoveryRecVOMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(DbRecoveryRecVOMeta.DELETE_TIME)));
			this.setName(DataParser.parse(String.class, r.getValue(DbRecoveryRecVOMeta.NAME)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(DbRecoveryRecVOMeta.DELETE_BY)));
			this.setId(DataParser.parse(String.class, r.getValue(DbRecoveryRecVOMeta.ID)));
			this.setFileId(DataParser.parse(String.class, r.getValue(DbRecoveryRecVOMeta.FILE_ID)));
			return true;
		} else {
			try {
				this.setNotes( (String)r.getValue(DbRecoveryRecVOMeta.NOTES));
				this.setDbInstanceId( (String)r.getValue(DbRecoveryRecVOMeta.DB_INSTANCE_ID));
				this.setUpdateTime( (Date)r.getValue(DbRecoveryRecVOMeta.UPDATE_TIME));
				this.setType( (String)r.getValue(DbRecoveryRecVOMeta.TYPE));
				this.setRecDate( (Date)r.getValue(DbRecoveryRecVOMeta.REC_DATE));
				this.setVersion( (Integer)r.getValue(DbRecoveryRecVOMeta.VERSION));
				this.setCreateBy( (String)r.getValue(DbRecoveryRecVOMeta.CREATE_BY));
				this.setDeleted( (Integer)r.getValue(DbRecoveryRecVOMeta.DELETED));
				this.setCreateTime( (Date)r.getValue(DbRecoveryRecVOMeta.CREATE_TIME));
				this.setUpdateBy( (String)r.getValue(DbRecoveryRecVOMeta.UPDATE_BY));
				this.setDeleteTime( (Date)r.getValue(DbRecoveryRecVOMeta.DELETE_TIME));
				this.setName( (String)r.getValue(DbRecoveryRecVOMeta.NAME));
				this.setDeleteBy( (String)r.getValue(DbRecoveryRecVOMeta.DELETE_BY));
				this.setId( (String)r.getValue(DbRecoveryRecVOMeta.ID));
				this.setFileId( (String)r.getValue(DbRecoveryRecVOMeta.FILE_ID));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}