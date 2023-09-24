package com.dt.platform.domain.oa;

import com.github.foxnic.dao.entity.Entity;
import io.swagger.annotations.ApiModel;
import javax.persistence.Table;
import com.github.foxnic.sql.meta.DBTable;
import com.dt.platform.constants.db.OaTables.OA_NETDISK_RECYCLE;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Transient;
import com.github.foxnic.api.swagger.EnumFor;
import org.github.foxnic.web.domain.hrm.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.foxnic.commons.lang.DataParser;
import java.util.Map;
import com.github.foxnic.dao.entity.EntityContext;
import com.dt.platform.domain.oa.meta.NetdiskRecycleMeta;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 回收站
 * <p>回收站 , 数据表 oa_netdisk_recycle 的PO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-09-21 14:38:46
 * @sign 9DC8B1A0FD64270D3124A3C8078EE780
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@Table(name = "oa_netdisk_recycle")
@ApiModel(description = "回收站 ; 回收站 , 数据表 oa_netdisk_recycle 的PO类型")
public class NetdiskRecycle extends Entity {

	private static final long serialVersionUID = 1L;

	public static final DBTable TABLE =OA_NETDISK_RECYCLE.$TABLE;
	
	/**
	 * 主键：主键
	*/
	@Id
	@ApiModelProperty(required = true,value="主键" , notes = "主键" , example = "757234358233858048")
	private String id;
	
	/**
	 * FD：FD
	*/
	@ApiModelProperty(required = false,value="FD" , notes = "FD" , example = "757234294681763841")
	private String fdId;
	
	/**
	 * 类型：类型
	*/
	@ApiModelProperty(required = false,value="类型" , notes = "类型" , example = "folder")
	private String fdType;
	
	/**
	 * 删除时间：删除时间
	*/
	@ApiModelProperty(required = false,value="删除时间" , notes = "删除时间" , example = "2023-09-21 01:39:00")
	private Date delTime;
	
	/**
	 * 用户：用户
	*/
	@ApiModelProperty(required = false,value="用户" , notes = "用户")
	private String userId;
	
	/**
	 * 版本：版本
	*/
	@ApiModelProperty(required = true,value="版本" , notes = "版本" , example = "1")
	private Integer version;
	
	/**
	 * 创建人ID：创建人ID
	*/
	@ApiModelProperty(required = false,value="创建人ID" , notes = "创建人ID" , example = "110588348101165911")
	private String createBy;
	
	/**
	 * 创建时间：创建时间
	*/
	@ApiModelProperty(required = false,value="创建时间" , notes = "创建时间" , example = "2023-09-21 01:39:00")
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
	 * 租户：租户
	*/
	@ApiModelProperty(required = false,value="租户" , notes = "租户" , example = "T001")
	private String tenantId;
	
	/**
	 * netdiskFile：netdiskFile
	*/
	@ApiModelProperty(required = false,value="netdiskFile" , notes = "netdiskFile")
	private NetdiskFile netdiskFile;
	
	/**
	 * netdiskOriginFile：netdiskOriginFile
	*/
	@ApiModelProperty(required = false,value="netdiskOriginFile" , notes = "netdiskOriginFile")
	private NetdiskOriginFile netdiskOriginFile;
	
	/**
	 * netdiskFolder：netdiskFolder
	*/
	@ApiModelProperty(required = false,value="netdiskFolder" , notes = "netdiskFolder")
	private NetdiskFolder netdiskFolder;
	
	/**
	 * ownerUser：ownerUser
	*/
	@ApiModelProperty(required = false,value="ownerUser" , notes = "ownerUser")
	private Employee ownerUser;
	
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
	public NetdiskRecycle setId(String id) {
		this.id=id;
		return this;
	}
	
	/**
	 * 获得 FD<br>
	 * FD
	 * @return FD
	*/
	public String getFdId() {
		return fdId;
	}
	
	/**
	 * 设置 FD
	 * @param fdId FD
	 * @return 当前对象
	*/
	public NetdiskRecycle setFdId(String fdId) {
		this.fdId=fdId;
		return this;
	}
	
	/**
	 * 获得 类型<br>
	 * 类型
	 * @return 类型
	*/
	public String getFdType() {
		return fdType;
	}
	
	/**
	 * 设置 类型
	 * @param fdType 类型
	 * @return 当前对象
	*/
	public NetdiskRecycle setFdType(String fdType) {
		this.fdType=fdType;
		return this;
	}
	
	/**
	 * 获得 删除时间<br>
	 * 删除时间
	 * @return 删除时间
	*/
	public Date getDelTime() {
		return delTime;
	}
	
	/**
	 * 设置 删除时间
	 * @param delTime 删除时间
	 * @return 当前对象
	*/
	public NetdiskRecycle setDelTime(Date delTime) {
		this.delTime=delTime;
		return this;
	}
	
	/**
	 * 获得 用户<br>
	 * 用户
	 * @return 用户
	*/
	public String getUserId() {
		return userId;
	}
	
	/**
	 * 设置 用户
	 * @param userId 用户
	 * @return 当前对象
	*/
	public NetdiskRecycle setUserId(String userId) {
		this.userId=userId;
		return this;
	}
	
	/**
	 * 获得 版本<br>
	 * 版本
	 * @return 版本
	*/
	public Integer getVersion() {
		return version;
	}
	
	/**
	 * 设置 版本
	 * @param version 版本
	 * @return 当前对象
	*/
	public NetdiskRecycle setVersion(Integer version) {
		this.version=version;
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
	public NetdiskRecycle setCreateBy(String createBy) {
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
	public NetdiskRecycle setCreateTime(Date createTime) {
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
	public NetdiskRecycle setUpdateBy(String updateBy) {
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
	public NetdiskRecycle setUpdateTime(Date updateTime) {
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
	public NetdiskRecycle setDeleted(Integer deleted) {
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
	public NetdiskRecycle setDeleted(Boolean deletedBool) {
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
	public NetdiskRecycle setDeleteBy(String deleteBy) {
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
	public NetdiskRecycle setDeleteTime(Date deleteTime) {
		this.deleteTime=deleteTime;
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
	public NetdiskRecycle setTenantId(String tenantId) {
		this.tenantId=tenantId;
		return this;
	}
	
	/**
	 * 获得 netdiskFile<br>
	 * netdiskFile
	 * @return netdiskFile
	*/
	public NetdiskFile getNetdiskFile() {
		return netdiskFile;
	}
	
	/**
	 * 设置 netdiskFile
	 * @param netdiskFile netdiskFile
	 * @return 当前对象
	*/
	public NetdiskRecycle setNetdiskFile(NetdiskFile netdiskFile) {
		this.netdiskFile=netdiskFile;
		return this;
	}
	
	/**
	 * 获得 netdiskOriginFile<br>
	 * netdiskOriginFile
	 * @return netdiskOriginFile
	*/
	public NetdiskOriginFile getNetdiskOriginFile() {
		return netdiskOriginFile;
	}
	
	/**
	 * 设置 netdiskOriginFile
	 * @param netdiskOriginFile netdiskOriginFile
	 * @return 当前对象
	*/
	public NetdiskRecycle setNetdiskOriginFile(NetdiskOriginFile netdiskOriginFile) {
		this.netdiskOriginFile=netdiskOriginFile;
		return this;
	}
	
	/**
	 * 获得 netdiskFolder<br>
	 * netdiskFolder
	 * @return netdiskFolder
	*/
	public NetdiskFolder getNetdiskFolder() {
		return netdiskFolder;
	}
	
	/**
	 * 设置 netdiskFolder
	 * @param netdiskFolder netdiskFolder
	 * @return 当前对象
	*/
	public NetdiskRecycle setNetdiskFolder(NetdiskFolder netdiskFolder) {
		this.netdiskFolder=netdiskFolder;
		return this;
	}
	
	/**
	 * 获得 ownerUser<br>
	 * ownerUser
	 * @return ownerUser
	*/
	public Employee getOwnerUser() {
		return ownerUser;
	}
	
	/**
	 * 设置 ownerUser
	 * @param ownerUser ownerUser
	 * @return 当前对象
	*/
	public NetdiskRecycle setOwnerUser(Employee ownerUser) {
		this.ownerUser=ownerUser;
		return this;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return NetdiskRecycle , 转换好的 NetdiskRecycle 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return NetdiskRecycle , 转换好的 PoJo 对象
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
	public NetdiskRecycle clone() {
		return duplicate(true);
	}

	/**
	 * 复制当前对象
	 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
	*/
	@Transient
	public NetdiskRecycle duplicate(boolean all) {
		com.dt.platform.domain.oa.meta.NetdiskRecycleMeta.$$proxy$$ inst = new com.dt.platform.domain.oa.meta.NetdiskRecycleMeta.$$proxy$$();
		inst.setUpdateTime(this.getUpdateTime());
		inst.setUserId(this.getUserId());
		inst.setVersion(this.getVersion());
		inst.setFdType(this.getFdType());
		inst.setCreateBy(this.getCreateBy());
		inst.setDeleted(this.getDeleted());
		inst.setCreateTime(this.getCreateTime());
		inst.setUpdateBy(this.getUpdateBy());
		inst.setDeleteTime(this.getDeleteTime());
		inst.setFdId(this.getFdId());
		inst.setTenantId(this.getTenantId());
		inst.setDeleteBy(this.getDeleteBy());
		inst.setDelTime(this.getDelTime());
		inst.setId(this.getId());
		if(all) {
			inst.setNetdiskFile(this.getNetdiskFile());
			inst.setNetdiskFolder(this.getNetdiskFolder());
			inst.setOwnerUser(this.getOwnerUser());
			inst.setNetdiskOriginFile(this.getNetdiskOriginFile());
		}
		inst.clearModifies();
		return inst;
	}

	/**
	 * 克隆当前对象
	*/
	@Transient
	public NetdiskRecycle clone(boolean deep) {
		return EntityContext.clone(NetdiskRecycle.class,this,deep);
	}

	/**
	 * 将 Map 转换成 NetdiskRecycle
	 * @param netdiskRecycleMap 包含实体信息的 Map 对象
	 * @return NetdiskRecycle , 转换好的的 NetdiskRecycle 对象
	*/
	@Transient
	public static NetdiskRecycle createFrom(Map<String,Object> netdiskRecycleMap) {
		if(netdiskRecycleMap==null) return null;
		NetdiskRecycle po = create();
		EntityContext.copyProperties(po,netdiskRecycleMap);
		po.clearModifies();
		return po;
	}

	/**
	 * 将 Pojo 转换成 NetdiskRecycle
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return NetdiskRecycle , 转换好的的 NetdiskRecycle 对象
	*/
	@Transient
	public static NetdiskRecycle createFrom(Object pojo) {
		if(pojo==null) return null;
		NetdiskRecycle po = create();
		EntityContext.copyProperties(po,pojo);
		po.clearModifies();
		return po;
	}

	/**
	 * 创建一个 NetdiskRecycle，等同于 new
	 * @return NetdiskRecycle 对象
	*/
	@Transient
	public static NetdiskRecycle create() {
		return new com.dt.platform.domain.oa.meta.NetdiskRecycleMeta.$$proxy$$();
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
			this.setUpdateTime(DataParser.parse(Date.class, map.get(NetdiskRecycleMeta.UPDATE_TIME)));
			this.setUserId(DataParser.parse(String.class, map.get(NetdiskRecycleMeta.USER_ID)));
			this.setVersion(DataParser.parse(Integer.class, map.get(NetdiskRecycleMeta.VERSION)));
			this.setFdType(DataParser.parse(String.class, map.get(NetdiskRecycleMeta.FD_TYPE)));
			this.setCreateBy(DataParser.parse(String.class, map.get(NetdiskRecycleMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(NetdiskRecycleMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(NetdiskRecycleMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(NetdiskRecycleMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(NetdiskRecycleMeta.DELETE_TIME)));
			this.setFdId(DataParser.parse(String.class, map.get(NetdiskRecycleMeta.FD_ID)));
			this.setTenantId(DataParser.parse(String.class, map.get(NetdiskRecycleMeta.TENANT_ID)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(NetdiskRecycleMeta.DELETE_BY)));
			this.setDelTime(DataParser.parse(Date.class, map.get(NetdiskRecycleMeta.DEL_TIME)));
			this.setId(DataParser.parse(String.class, map.get(NetdiskRecycleMeta.ID)));
			// others
			this.setNetdiskFile(DataParser.parse(NetdiskFile.class, map.get(NetdiskRecycleMeta.NETDISK_FILE)));
			this.setNetdiskFolder(DataParser.parse(NetdiskFolder.class, map.get(NetdiskRecycleMeta.NETDISK_FOLDER)));
			this.setOwnerUser(DataParser.parse(Employee.class, map.get(NetdiskRecycleMeta.OWNER_USER)));
			this.setNetdiskOriginFile(DataParser.parse(NetdiskOriginFile.class, map.get(NetdiskRecycleMeta.NETDISK_ORIGIN_FILE)));
			return true;
		} else {
			try {
				this.setUpdateTime( (Date)map.get(NetdiskRecycleMeta.UPDATE_TIME));
				this.setUserId( (String)map.get(NetdiskRecycleMeta.USER_ID));
				this.setVersion( (Integer)map.get(NetdiskRecycleMeta.VERSION));
				this.setFdType( (String)map.get(NetdiskRecycleMeta.FD_TYPE));
				this.setCreateBy( (String)map.get(NetdiskRecycleMeta.CREATE_BY));
				this.setDeleted( (Integer)map.get(NetdiskRecycleMeta.DELETED));
				this.setCreateTime( (Date)map.get(NetdiskRecycleMeta.CREATE_TIME));
				this.setUpdateBy( (String)map.get(NetdiskRecycleMeta.UPDATE_BY));
				this.setDeleteTime( (Date)map.get(NetdiskRecycleMeta.DELETE_TIME));
				this.setFdId( (String)map.get(NetdiskRecycleMeta.FD_ID));
				this.setTenantId( (String)map.get(NetdiskRecycleMeta.TENANT_ID));
				this.setDeleteBy( (String)map.get(NetdiskRecycleMeta.DELETE_BY));
				this.setDelTime( (Date)map.get(NetdiskRecycleMeta.DEL_TIME));
				this.setId( (String)map.get(NetdiskRecycleMeta.ID));
				// others
				this.setNetdiskFile( (NetdiskFile)map.get(NetdiskRecycleMeta.NETDISK_FILE));
				this.setNetdiskFolder( (NetdiskFolder)map.get(NetdiskRecycleMeta.NETDISK_FOLDER));
				this.setOwnerUser( (Employee)map.get(NetdiskRecycleMeta.OWNER_USER));
				this.setNetdiskOriginFile( (NetdiskOriginFile)map.get(NetdiskRecycleMeta.NETDISK_ORIGIN_FILE));
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
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(NetdiskRecycleMeta.UPDATE_TIME)));
			this.setUserId(DataParser.parse(String.class, r.getValue(NetdiskRecycleMeta.USER_ID)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(NetdiskRecycleMeta.VERSION)));
			this.setFdType(DataParser.parse(String.class, r.getValue(NetdiskRecycleMeta.FD_TYPE)));
			this.setCreateBy(DataParser.parse(String.class, r.getValue(NetdiskRecycleMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(NetdiskRecycleMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(NetdiskRecycleMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(NetdiskRecycleMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(NetdiskRecycleMeta.DELETE_TIME)));
			this.setFdId(DataParser.parse(String.class, r.getValue(NetdiskRecycleMeta.FD_ID)));
			this.setTenantId(DataParser.parse(String.class, r.getValue(NetdiskRecycleMeta.TENANT_ID)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(NetdiskRecycleMeta.DELETE_BY)));
			this.setDelTime(DataParser.parse(Date.class, r.getValue(NetdiskRecycleMeta.DEL_TIME)));
			this.setId(DataParser.parse(String.class, r.getValue(NetdiskRecycleMeta.ID)));
			return true;
		} else {
			try {
				this.setUpdateTime( (Date)r.getValue(NetdiskRecycleMeta.UPDATE_TIME));
				this.setUserId( (String)r.getValue(NetdiskRecycleMeta.USER_ID));
				this.setVersion( (Integer)r.getValue(NetdiskRecycleMeta.VERSION));
				this.setFdType( (String)r.getValue(NetdiskRecycleMeta.FD_TYPE));
				this.setCreateBy( (String)r.getValue(NetdiskRecycleMeta.CREATE_BY));
				this.setDeleted( (Integer)r.getValue(NetdiskRecycleMeta.DELETED));
				this.setCreateTime( (Date)r.getValue(NetdiskRecycleMeta.CREATE_TIME));
				this.setUpdateBy( (String)r.getValue(NetdiskRecycleMeta.UPDATE_BY));
				this.setDeleteTime( (Date)r.getValue(NetdiskRecycleMeta.DELETE_TIME));
				this.setFdId( (String)r.getValue(NetdiskRecycleMeta.FD_ID));
				this.setTenantId( (String)r.getValue(NetdiskRecycleMeta.TENANT_ID));
				this.setDeleteBy( (String)r.getValue(NetdiskRecycleMeta.DELETE_BY));
				this.setDelTime( (Date)r.getValue(NetdiskRecycleMeta.DEL_TIME));
				this.setId( (String)r.getValue(NetdiskRecycleMeta.ID));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}