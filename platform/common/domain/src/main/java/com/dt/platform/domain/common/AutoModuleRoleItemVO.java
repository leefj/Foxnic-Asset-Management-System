package com.dt.platform.domain.common;

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
import com.dt.platform.domain.common.meta.AutoModuleRoleItemVOMeta;
import com.github.foxnic.commons.lang.DataParser;
import java.util.Date;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 赋权明细VO类型
 * <p>赋权明细 , 数据表 sys_auto_module_role_item 的通用VO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-27 14:04:25
 * @sign 0A8C07AAF5A11B67265E24BF03B7A8E2
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@ApiModel(description = "赋权明细VO类型 ; 赋权明细 , 数据表 sys_auto_module_role_item 的通用VO类型" , parent = AutoModuleRoleItem.class)
public class AutoModuleRoleItemVO extends AutoModuleRoleItem {

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
	public AutoModuleRoleItemVO setPageIndex(Integer pageIndex) {
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
	public AutoModuleRoleItemVO setPageSize(Integer pageSize) {
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
	public AutoModuleRoleItemVO setSearchField(String searchField) {
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
	public AutoModuleRoleItemVO setFuzzyField(String fuzzyField) {
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
	public AutoModuleRoleItemVO setSearchValue(String searchValue) {
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
	public AutoModuleRoleItemVO setDirtyFields(List<String> dirtyFields) {
		this.dirtyFields=dirtyFields;
		return this;
	}
	
	/**
	 * 添加 已修改字段
	 * @param dirtyField 已修改字段
	 * @return 当前对象
	*/
	public AutoModuleRoleItemVO addDirtyField(String... dirtyField) {
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
	public AutoModuleRoleItemVO setSortField(String sortField) {
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
	public AutoModuleRoleItemVO setSortType(String sortType) {
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
	public AutoModuleRoleItemVO setDataOrigin(String dataOrigin) {
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
	public AutoModuleRoleItemVO setQueryLogic(String queryLogic) {
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
	public AutoModuleRoleItemVO setIds(List<String> ids) {
		this.ids=ids;
		return this;
	}
	
	/**
	 * 添加 主键清单
	 * @param id 主键清单
	 * @return 当前对象
	*/
	public AutoModuleRoleItemVO addId(String... id) {
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
	 * @return AutoModuleRoleItemVO , 转换好的 AutoModuleRoleItemVO 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return AutoModuleRoleItemVO , 转换好的 PoJo 对象
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
	public AutoModuleRoleItemVO clone() {
		return duplicate(true);
	}

	/**
	 * 复制当前对象
	 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
	*/
	@Transient
	public AutoModuleRoleItemVO duplicate(boolean all) {
		com.dt.platform.domain.common.meta.AutoModuleRoleItemVOMeta.$$proxy$$ inst = new com.dt.platform.domain.common.meta.AutoModuleRoleItemVOMeta.$$proxy$$();
		inst.setDeleted(this.getDeleted());
		inst.setCreateTime(this.getCreateTime());
		inst.setUpdateBy(this.getUpdateBy());
		inst.setDeleteTime(this.getDeleteTime());
		inst.setRoleId(this.getRoleId());
		inst.setRoleName(this.getRoleName());
		inst.setModuleRoleId(this.getModuleRoleId());
		inst.setDeleteBy(this.getDeleteBy());
		inst.setUpdateTime(this.getUpdateTime());
		inst.setId(this.getId());
		inst.setVersion(this.getVersion());
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
	public AutoModuleRoleItemVO clone(boolean deep) {
		return EntityContext.clone(AutoModuleRoleItemVO.class,this,deep);
	}

	/**
	 * 将 Map 转换成 AutoModuleRoleItemVO
	 * @param autoModuleRoleItemMap 包含实体信息的 Map 对象
	 * @return AutoModuleRoleItemVO , 转换好的的 AutoModuleRoleItem 对象
	*/
	@Transient
	public static AutoModuleRoleItemVO createFrom(Map<String,Object> autoModuleRoleItemMap) {
		if(autoModuleRoleItemMap==null) return null;
		AutoModuleRoleItemVO vo = create();
		EntityContext.copyProperties(vo,autoModuleRoleItemMap);
		vo.clearModifies();
		return vo;
	}

	/**
	 * 将 Pojo 转换成 AutoModuleRoleItemVO
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return AutoModuleRoleItemVO , 转换好的的 AutoModuleRoleItem 对象
	*/
	@Transient
	public static AutoModuleRoleItemVO createFrom(Object pojo) {
		if(pojo==null) return null;
		AutoModuleRoleItemVO vo = create();
		EntityContext.copyProperties(vo,pojo);
		vo.clearModifies();
		return vo;
	}

	/**
	 * 创建一个 AutoModuleRoleItemVO，等同于 new
	 * @return AutoModuleRoleItemVO 对象
	*/
	@Transient
	public static AutoModuleRoleItemVO create() {
		return new com.dt.platform.domain.common.meta.AutoModuleRoleItemVOMeta.$$proxy$$();
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
			this.setDeleted(DataParser.parse(Integer.class, map.get(AutoModuleRoleItemVOMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(AutoModuleRoleItemVOMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(AutoModuleRoleItemVOMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(AutoModuleRoleItemVOMeta.DELETE_TIME)));
			this.setRoleId(DataParser.parse(String.class, map.get(AutoModuleRoleItemVOMeta.ROLE_ID)));
			this.setRoleName(DataParser.parse(String.class, map.get(AutoModuleRoleItemVOMeta.ROLE_NAME)));
			this.setModuleRoleId(DataParser.parse(String.class, map.get(AutoModuleRoleItemVOMeta.MODULE_ROLE_ID)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(AutoModuleRoleItemVOMeta.DELETE_BY)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(AutoModuleRoleItemVOMeta.UPDATE_TIME)));
			this.setId(DataParser.parse(String.class, map.get(AutoModuleRoleItemVOMeta.ID)));
			this.setVersion(DataParser.parse(Integer.class, map.get(AutoModuleRoleItemVOMeta.VERSION)));
			// others
			this.setSearchField(DataParser.parse(String.class, map.get(AutoModuleRoleItemVOMeta.SEARCH_FIELD)));
			this.setPageIndex(DataParser.parse(Integer.class, map.get(AutoModuleRoleItemVOMeta.PAGE_INDEX)));
			this.setSortType(DataParser.parse(String.class, map.get(AutoModuleRoleItemVOMeta.SORT_TYPE)));
			this.setFuzzyField(DataParser.parse(String.class, map.get(AutoModuleRoleItemVOMeta.FUZZY_FIELD)));
			this.setSortField(DataParser.parse(String.class, map.get(AutoModuleRoleItemVOMeta.SORT_FIELD)));
			this.setPageSize(DataParser.parse(Integer.class, map.get(AutoModuleRoleItemVOMeta.PAGE_SIZE)));
			this.setDataOrigin(DataParser.parse(String.class, map.get(AutoModuleRoleItemVOMeta.DATA_ORIGIN)));
			this.setQueryLogic(DataParser.parse(String.class, map.get(AutoModuleRoleItemVOMeta.QUERY_LOGIC)));
			this.setSearchValue(DataParser.parse(String.class, map.get(AutoModuleRoleItemVOMeta.SEARCH_VALUE)));
			return true;
		} else {
			try {
				this.setDeleted( (Integer)map.get(AutoModuleRoleItemVOMeta.DELETED));
				this.setCreateTime( (Date)map.get(AutoModuleRoleItemVOMeta.CREATE_TIME));
				this.setUpdateBy( (String)map.get(AutoModuleRoleItemVOMeta.UPDATE_BY));
				this.setDeleteTime( (Date)map.get(AutoModuleRoleItemVOMeta.DELETE_TIME));
				this.setRoleId( (String)map.get(AutoModuleRoleItemVOMeta.ROLE_ID));
				this.setRoleName( (String)map.get(AutoModuleRoleItemVOMeta.ROLE_NAME));
				this.setModuleRoleId( (String)map.get(AutoModuleRoleItemVOMeta.MODULE_ROLE_ID));
				this.setDeleteBy( (String)map.get(AutoModuleRoleItemVOMeta.DELETE_BY));
				this.setUpdateTime( (Date)map.get(AutoModuleRoleItemVOMeta.UPDATE_TIME));
				this.setId( (String)map.get(AutoModuleRoleItemVOMeta.ID));
				this.setVersion( (Integer)map.get(AutoModuleRoleItemVOMeta.VERSION));
				// others
				this.setSearchField( (String)map.get(AutoModuleRoleItemVOMeta.SEARCH_FIELD));
				this.setPageIndex( (Integer)map.get(AutoModuleRoleItemVOMeta.PAGE_INDEX));
				this.setSortType( (String)map.get(AutoModuleRoleItemVOMeta.SORT_TYPE));
				this.setFuzzyField( (String)map.get(AutoModuleRoleItemVOMeta.FUZZY_FIELD));
				this.setSortField( (String)map.get(AutoModuleRoleItemVOMeta.SORT_FIELD));
				this.setPageSize( (Integer)map.get(AutoModuleRoleItemVOMeta.PAGE_SIZE));
				this.setDataOrigin( (String)map.get(AutoModuleRoleItemVOMeta.DATA_ORIGIN));
				this.setQueryLogic( (String)map.get(AutoModuleRoleItemVOMeta.QUERY_LOGIC));
				this.setSearchValue( (String)map.get(AutoModuleRoleItemVOMeta.SEARCH_VALUE));
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
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(AutoModuleRoleItemVOMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(AutoModuleRoleItemVOMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(AutoModuleRoleItemVOMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(AutoModuleRoleItemVOMeta.DELETE_TIME)));
			this.setRoleId(DataParser.parse(String.class, r.getValue(AutoModuleRoleItemVOMeta.ROLE_ID)));
			this.setRoleName(DataParser.parse(String.class, r.getValue(AutoModuleRoleItemVOMeta.ROLE_NAME)));
			this.setModuleRoleId(DataParser.parse(String.class, r.getValue(AutoModuleRoleItemVOMeta.MODULE_ROLE_ID)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(AutoModuleRoleItemVOMeta.DELETE_BY)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(AutoModuleRoleItemVOMeta.UPDATE_TIME)));
			this.setId(DataParser.parse(String.class, r.getValue(AutoModuleRoleItemVOMeta.ID)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(AutoModuleRoleItemVOMeta.VERSION)));
			return true;
		} else {
			try {
				this.setDeleted( (Integer)r.getValue(AutoModuleRoleItemVOMeta.DELETED));
				this.setCreateTime( (Date)r.getValue(AutoModuleRoleItemVOMeta.CREATE_TIME));
				this.setUpdateBy( (String)r.getValue(AutoModuleRoleItemVOMeta.UPDATE_BY));
				this.setDeleteTime( (Date)r.getValue(AutoModuleRoleItemVOMeta.DELETE_TIME));
				this.setRoleId( (String)r.getValue(AutoModuleRoleItemVOMeta.ROLE_ID));
				this.setRoleName( (String)r.getValue(AutoModuleRoleItemVOMeta.ROLE_NAME));
				this.setModuleRoleId( (String)r.getValue(AutoModuleRoleItemVOMeta.MODULE_ROLE_ID));
				this.setDeleteBy( (String)r.getValue(AutoModuleRoleItemVOMeta.DELETE_BY));
				this.setUpdateTime( (Date)r.getValue(AutoModuleRoleItemVOMeta.UPDATE_TIME));
				this.setId( (String)r.getValue(AutoModuleRoleItemVOMeta.ID));
				this.setVersion( (Integer)r.getValue(AutoModuleRoleItemVOMeta.VERSION));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}