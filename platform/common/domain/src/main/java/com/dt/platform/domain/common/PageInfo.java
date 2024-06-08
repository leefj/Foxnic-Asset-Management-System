package com.dt.platform.domain.common;

import com.github.foxnic.dao.entity.Entity;
import io.swagger.annotations.ApiModel;
import javax.persistence.Table;
import com.github.foxnic.sql.meta.DBTable;
import com.dt.platform.constants.db.SysTables.SYS_PAGE_INFO;
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
import com.dt.platform.domain.common.meta.PageInfoMeta;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 页面开发
 * <p>页面开发 , 数据表 sys_page_info 的PO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2024-05-20 12:29:27
 * @sign 68A8C50BE6A7D1A4F9E02CF533BDB078
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@Table(name = "sys_page_info")
@ApiModel(description = "页面开发 ; 页面开发 , 数据表 sys_page_info 的PO类型")
public class PageInfo extends Entity {

	private static final long serialVersionUID = 1L;

	public static final DBTable TABLE =SYS_PAGE_INFO.$TABLE;
	
	/**
	 * 主键：主键
	*/
	@Id
	@ApiModelProperty(required = true,value="主键" , notes = "主键" , example = "841996736372146176")
	private String id;
	
	/**
	 * 编码：编码
	*/
	@ApiModelProperty(required = false,value="编码" , notes = "编码" , example = "system_code_mgr")
	private String code;
	
	/**
	 * 名称：名称
	*/
	@ApiModelProperty(required = false,value="名称" , notes = "名称" , example = "编码管理页面")
	private String name;
	
	/**
	 * 标签：标签
	*/
	@ApiModelProperty(required = false,value="标签" , notes = "标签")
	private String labelCode;
	
	/**
	 * 定义：定义
	*/
	@ApiModelProperty(required = false,value="定义" , notes = "定义" , example = "")
	private String defJson;
	
	/**
	 * 备注：备注
	*/
	@ApiModelProperty(required = false,value="备注" , notes = "备注")
	private String notes;
	
	/**
	 * 创建人ID：创建人ID
	*/
	@ApiModelProperty(required = false,value="创建人ID" , notes = "创建人ID" , example = "110588348101165911")
	private String createBy;
	
	/**
	 * 创建时间：创建时间
	*/
	@ApiModelProperty(required = false,value="创建时间" , notes = "创建时间" , example = "2024-05-12 11:14:25")
	private Date createTime;
	
	/**
	 * 修改人ID：修改人ID
	*/
	@ApiModelProperty(required = false,value="修改人ID" , notes = "修改人ID" , example = "110588348101165911")
	private String updateBy;
	
	/**
	 * 修改时间：修改时间
	*/
	@ApiModelProperty(required = false,value="修改时间" , notes = "修改时间" , example = "2024-05-18 07:09:20")
	private Date updateTime;
	
	/**
	 * 是否已删除：是否已删除
	*/
	@ApiModelProperty(required = true,value="是否已删除" , notes = "是否已删除" , example = "0")
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
	 * version：version
	*/
	@ApiModelProperty(required = false,value="version" , notes = "version" , example = "30")
	private Integer version;
	
	/**
	 * 租户：租户
	*/
	@ApiModelProperty(required = false,value="租户" , notes = "租户" , example = "T001")
	private String tenantId;
	
	/**
	 * labelDict：labelDict
	*/
	@ApiModelProperty(required = false,value="labelDict" , notes = "labelDict")
	private DictItem labelDict;
	
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
	public PageInfo setId(String id) {
		this.id=id;
		return this;
	}
	
	/**
	 * 获得 编码<br>
	 * 编码
	 * @return 编码
	*/
	public String getCode() {
		return code;
	}
	
	/**
	 * 设置 编码
	 * @param code 编码
	 * @return 当前对象
	*/
	public PageInfo setCode(String code) {
		this.code=code;
		return this;
	}
	
	/**
	 * 获得 名称<br>
	 * 名称
	 * @return 名称
	*/
	public String getName() {
		return name;
	}
	
	/**
	 * 设置 名称
	 * @param name 名称
	 * @return 当前对象
	*/
	public PageInfo setName(String name) {
		this.name=name;
		return this;
	}
	
	/**
	 * 获得 标签<br>
	 * 标签
	 * @return 标签
	*/
	public String getLabelCode() {
		return labelCode;
	}
	
	/**
	 * 设置 标签
	 * @param labelCode 标签
	 * @return 当前对象
	*/
	public PageInfo setLabelCode(String labelCode) {
		this.labelCode=labelCode;
		return this;
	}
	
	/**
	 * 获得 定义<br>
	 * 定义
	 * @return 定义
	*/
	public String getDefJson() {
		return defJson;
	}
	
	/**
	 * 设置 定义
	 * @param defJson 定义
	 * @return 当前对象
	*/
	public PageInfo setDefJson(String defJson) {
		this.defJson=defJson;
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
	public PageInfo setNotes(String notes) {
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
	public PageInfo setCreateBy(String createBy) {
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
	public PageInfo setCreateTime(Date createTime) {
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
	public PageInfo setUpdateBy(String updateBy) {
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
	public PageInfo setUpdateTime(Date updateTime) {
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
	public PageInfo setDeleted(Integer deleted) {
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
	public PageInfo setDeleted(Boolean deletedBool) {
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
	public PageInfo setDeleteBy(String deleteBy) {
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
	public PageInfo setDeleteTime(Date deleteTime) {
		this.deleteTime=deleteTime;
		return this;
	}
	
	/**
	 * 获得 version<br>
	 * version
	 * @return version
	*/
	public Integer getVersion() {
		return version;
	}
	
	/**
	 * 设置 version
	 * @param version version
	 * @return 当前对象
	*/
	public PageInfo setVersion(Integer version) {
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
	public PageInfo setTenantId(String tenantId) {
		this.tenantId=tenantId;
		return this;
	}
	
	/**
	 * 获得 labelDict<br>
	 * labelDict
	 * @return labelDict
	*/
	public DictItem getLabelDict() {
		return labelDict;
	}
	
	/**
	 * 设置 labelDict
	 * @param labelDict labelDict
	 * @return 当前对象
	*/
	public PageInfo setLabelDict(DictItem labelDict) {
		this.labelDict=labelDict;
		return this;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return PageInfo , 转换好的 PageInfo 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return PageInfo , 转换好的 PoJo 对象
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
	public PageInfo clone() {
		return duplicate(true);
	}

	/**
	 * 复制当前对象
	 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
	*/
	@Transient
	public PageInfo duplicate(boolean all) {
		com.dt.platform.domain.common.meta.PageInfoMeta.$$proxy$$ inst = new com.dt.platform.domain.common.meta.PageInfoMeta.$$proxy$$();
		inst.setCode(this.getCode());
		inst.setNotes(this.getNotes());
		inst.setDefJson(this.getDefJson());
		inst.setUpdateTime(this.getUpdateTime());
		inst.setVersion(this.getVersion());
		inst.setCreateBy(this.getCreateBy());
		inst.setDeleted(this.getDeleted());
		inst.setCreateTime(this.getCreateTime());
		inst.setUpdateBy(this.getUpdateBy());
		inst.setDeleteTime(this.getDeleteTime());
		inst.setName(this.getName());
		inst.setTenantId(this.getTenantId());
		inst.setDeleteBy(this.getDeleteBy());
		inst.setId(this.getId());
		inst.setLabelCode(this.getLabelCode());
		if(all) {
			inst.setLabelDict(this.getLabelDict());
		}
		inst.clearModifies();
		return inst;
	}

	/**
	 * 克隆当前对象
	*/
	@Transient
	public PageInfo clone(boolean deep) {
		return EntityContext.clone(PageInfo.class,this,deep);
	}

	/**
	 * 将 Map 转换成 PageInfo
	 * @param pageInfoMap 包含实体信息的 Map 对象
	 * @return PageInfo , 转换好的的 PageInfo 对象
	*/
	@Transient
	public static PageInfo createFrom(Map<String,Object> pageInfoMap) {
		if(pageInfoMap==null) return null;
		PageInfo po = create();
		EntityContext.copyProperties(po,pageInfoMap);
		po.clearModifies();
		return po;
	}

	/**
	 * 将 Pojo 转换成 PageInfo
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return PageInfo , 转换好的的 PageInfo 对象
	*/
	@Transient
	public static PageInfo createFrom(Object pojo) {
		if(pojo==null) return null;
		PageInfo po = create();
		EntityContext.copyProperties(po,pojo);
		po.clearModifies();
		return po;
	}

	/**
	 * 创建一个 PageInfo，等同于 new
	 * @return PageInfo 对象
	*/
	@Transient
	public static PageInfo create() {
		return new com.dt.platform.domain.common.meta.PageInfoMeta.$$proxy$$();
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
			this.setCode(DataParser.parse(String.class, map.get(PageInfoMeta.CODE)));
			this.setNotes(DataParser.parse(String.class, map.get(PageInfoMeta.NOTES)));
			this.setDefJson(DataParser.parse(String.class, map.get(PageInfoMeta.DEF_JSON)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(PageInfoMeta.UPDATE_TIME)));
			this.setVersion(DataParser.parse(Integer.class, map.get(PageInfoMeta.VERSION)));
			this.setCreateBy(DataParser.parse(String.class, map.get(PageInfoMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(PageInfoMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(PageInfoMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(PageInfoMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(PageInfoMeta.DELETE_TIME)));
			this.setName(DataParser.parse(String.class, map.get(PageInfoMeta.NAME)));
			this.setTenantId(DataParser.parse(String.class, map.get(PageInfoMeta.TENANT_ID)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(PageInfoMeta.DELETE_BY)));
			this.setId(DataParser.parse(String.class, map.get(PageInfoMeta.ID)));
			this.setLabelCode(DataParser.parse(String.class, map.get(PageInfoMeta.LABEL_CODE)));
			// others
			this.setLabelDict(DataParser.parse(DictItem.class, map.get(PageInfoMeta.LABEL_DICT)));
			return true;
		} else {
			try {
				this.setCode( (String)map.get(PageInfoMeta.CODE));
				this.setNotes( (String)map.get(PageInfoMeta.NOTES));
				this.setDefJson( (String)map.get(PageInfoMeta.DEF_JSON));
				this.setUpdateTime( (Date)map.get(PageInfoMeta.UPDATE_TIME));
				this.setVersion( (Integer)map.get(PageInfoMeta.VERSION));
				this.setCreateBy( (String)map.get(PageInfoMeta.CREATE_BY));
				this.setDeleted( (Integer)map.get(PageInfoMeta.DELETED));
				this.setCreateTime( (Date)map.get(PageInfoMeta.CREATE_TIME));
				this.setUpdateBy( (String)map.get(PageInfoMeta.UPDATE_BY));
				this.setDeleteTime( (Date)map.get(PageInfoMeta.DELETE_TIME));
				this.setName( (String)map.get(PageInfoMeta.NAME));
				this.setTenantId( (String)map.get(PageInfoMeta.TENANT_ID));
				this.setDeleteBy( (String)map.get(PageInfoMeta.DELETE_BY));
				this.setId( (String)map.get(PageInfoMeta.ID));
				this.setLabelCode( (String)map.get(PageInfoMeta.LABEL_CODE));
				// others
				this.setLabelDict( (DictItem)map.get(PageInfoMeta.LABEL_DICT));
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
			this.setCode(DataParser.parse(String.class, r.getValue(PageInfoMeta.CODE)));
			this.setNotes(DataParser.parse(String.class, r.getValue(PageInfoMeta.NOTES)));
			this.setDefJson(DataParser.parse(String.class, r.getValue(PageInfoMeta.DEF_JSON)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(PageInfoMeta.UPDATE_TIME)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(PageInfoMeta.VERSION)));
			this.setCreateBy(DataParser.parse(String.class, r.getValue(PageInfoMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(PageInfoMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(PageInfoMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(PageInfoMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(PageInfoMeta.DELETE_TIME)));
			this.setName(DataParser.parse(String.class, r.getValue(PageInfoMeta.NAME)));
			this.setTenantId(DataParser.parse(String.class, r.getValue(PageInfoMeta.TENANT_ID)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(PageInfoMeta.DELETE_BY)));
			this.setId(DataParser.parse(String.class, r.getValue(PageInfoMeta.ID)));
			this.setLabelCode(DataParser.parse(String.class, r.getValue(PageInfoMeta.LABEL_CODE)));
			return true;
		} else {
			try {
				this.setCode( (String)r.getValue(PageInfoMeta.CODE));
				this.setNotes( (String)r.getValue(PageInfoMeta.NOTES));
				this.setDefJson( (String)r.getValue(PageInfoMeta.DEF_JSON));
				this.setUpdateTime( (Date)r.getValue(PageInfoMeta.UPDATE_TIME));
				this.setVersion( (Integer)r.getValue(PageInfoMeta.VERSION));
				this.setCreateBy( (String)r.getValue(PageInfoMeta.CREATE_BY));
				this.setDeleted( (Integer)r.getValue(PageInfoMeta.DELETED));
				this.setCreateTime( (Date)r.getValue(PageInfoMeta.CREATE_TIME));
				this.setUpdateBy( (String)r.getValue(PageInfoMeta.UPDATE_BY));
				this.setDeleteTime( (Date)r.getValue(PageInfoMeta.DELETE_TIME));
				this.setName( (String)r.getValue(PageInfoMeta.NAME));
				this.setTenantId( (String)r.getValue(PageInfoMeta.TENANT_ID));
				this.setDeleteBy( (String)r.getValue(PageInfoMeta.DELETE_BY));
				this.setId( (String)r.getValue(PageInfoMeta.ID));
				this.setLabelCode( (String)r.getValue(PageInfoMeta.LABEL_CODE));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}