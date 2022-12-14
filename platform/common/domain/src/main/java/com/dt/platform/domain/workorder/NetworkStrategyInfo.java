package com.dt.platform.domain.workorder;

import com.github.foxnic.dao.entity.Entity;
import javax.persistence.Table;
import com.github.foxnic.sql.meta.DBTable;
import com.dt.platform.constants.db.WorkorderTables.WO_NETWORK_STRATEGY_INFO;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Transient;
import com.github.foxnic.commons.lang.DataParser;
import java.util.Map;
import com.github.foxnic.dao.entity.EntityContext;



/**
 * 网络策略
 * @author 金杰 , maillank@qq.com
 * @since 2022-07-06 06:16:46
 * @sign 68905C1676F1D6517B0DBC155EDAC4EE
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@Table(name = "wo_network_strategy_info")
public class NetworkStrategyInfo extends Entity {

	private static final long serialVersionUID = 1L;

	public static final DBTable TABLE =WO_NETWORK_STRATEGY_INFO.$TABLE;
	
	/**
	 * 主键：主键
	*/
	@Id
	@ApiModelProperty(required = true,value="主键" , notes = "主键")
	private String id;
	
	/**
	 * 源名称：源名称
	*/
	@ApiModelProperty(required = false,value="源名称" , notes = "源名称")
	private String sourceName;
	
	/**
	 * 所属：所属
	*/
	@ApiModelProperty(required = false,value="所属" , notes = "所属")
	private String ownerId;
	
	/**
	 * 源IP：源IP
	*/
	@ApiModelProperty(required = false,value="源IP" , notes = "源IP")
	private String sourceIp;
	
	/**
	 * 目标名称：目标名称
	*/
	@ApiModelProperty(required = false,value="目标名称" , notes = "目标名称")
	private String targetName;
	
	/**
	 * 目标IP：目标IP
	*/
	@ApiModelProperty(required = false,value="目标IP" , notes = "目标IP")
	private String targetIp;
	
	/**
	 * 目标端口：目标端口
	*/
	@ApiModelProperty(required = false,value="目标端口" , notes = "目标端口")
	private String targetPorts;
	
	/**
	 * 使用协议：使用协议
	*/
	@ApiModelProperty(required = false,value="使用协议" , notes = "使用协议")
	private String usedProtocolType;
	
	/**
	 * 连接类型：连接类型
	*/
	@ApiModelProperty(required = false,value="连接类型" , notes = "连接类型")
	private String sessionType;
	
	/**
	 * 备注：备注
	*/
	@ApiModelProperty(required = false,value="备注" , notes = "备注")
	private String notes;
	
	/**
	 * 选择：选择
	*/
	@ApiModelProperty(required = false,value="选择" , notes = "选择")
	private String selectedCode;
	
	/**
	 * 创建人ID：创建人ID
	*/
	@ApiModelProperty(required = false,value="创建人ID" , notes = "创建人ID")
	private String createBy;
	
	/**
	 * 创建时间：创建时间
	*/
	@ApiModelProperty(required = false,value="创建时间" , notes = "创建时间")
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
	@ApiModelProperty(required = true,value="是否已删除" , notes = "是否已删除")
	private Integer deleted;
	@Transient
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
	@ApiModelProperty(required = true,value="version" , notes = "version")
	private Integer version;
	
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
	public NetworkStrategyInfo setId(String id) {
		this.id=id;
		return this;
	}
	
	/**
	 * 获得 源名称<br>
	 * 源名称
	 * @return 源名称
	*/
	public String getSourceName() {
		return sourceName;
	}
	
	/**
	 * 设置 源名称
	 * @param sourceName 源名称
	 * @return 当前对象
	*/
	public NetworkStrategyInfo setSourceName(String sourceName) {
		this.sourceName=sourceName;
		return this;
	}
	
	/**
	 * 获得 所属<br>
	 * 所属
	 * @return 所属
	*/
	public String getOwnerId() {
		return ownerId;
	}
	
	/**
	 * 设置 所属
	 * @param ownerId 所属
	 * @return 当前对象
	*/
	public NetworkStrategyInfo setOwnerId(String ownerId) {
		this.ownerId=ownerId;
		return this;
	}
	
	/**
	 * 获得 源IP<br>
	 * 源IP
	 * @return 源IP
	*/
	public String getSourceIp() {
		return sourceIp;
	}
	
	/**
	 * 设置 源IP
	 * @param sourceIp 源IP
	 * @return 当前对象
	*/
	public NetworkStrategyInfo setSourceIp(String sourceIp) {
		this.sourceIp=sourceIp;
		return this;
	}
	
	/**
	 * 获得 目标名称<br>
	 * 目标名称
	 * @return 目标名称
	*/
	public String getTargetName() {
		return targetName;
	}
	
	/**
	 * 设置 目标名称
	 * @param targetName 目标名称
	 * @return 当前对象
	*/
	public NetworkStrategyInfo setTargetName(String targetName) {
		this.targetName=targetName;
		return this;
	}
	
	/**
	 * 获得 目标IP<br>
	 * 目标IP
	 * @return 目标IP
	*/
	public String getTargetIp() {
		return targetIp;
	}
	
	/**
	 * 设置 目标IP
	 * @param targetIp 目标IP
	 * @return 当前对象
	*/
	public NetworkStrategyInfo setTargetIp(String targetIp) {
		this.targetIp=targetIp;
		return this;
	}
	
	/**
	 * 获得 目标端口<br>
	 * 目标端口
	 * @return 目标端口
	*/
	public String getTargetPorts() {
		return targetPorts;
	}
	
	/**
	 * 设置 目标端口
	 * @param targetPorts 目标端口
	 * @return 当前对象
	*/
	public NetworkStrategyInfo setTargetPorts(String targetPorts) {
		this.targetPorts=targetPorts;
		return this;
	}
	
	/**
	 * 获得 使用协议<br>
	 * 使用协议
	 * @return 使用协议
	*/
	public String getUsedProtocolType() {
		return usedProtocolType;
	}
	
	/**
	 * 设置 使用协议
	 * @param usedProtocolType 使用协议
	 * @return 当前对象
	*/
	public NetworkStrategyInfo setUsedProtocolType(String usedProtocolType) {
		this.usedProtocolType=usedProtocolType;
		return this;
	}
	
	/**
	 * 获得 连接类型<br>
	 * 连接类型
	 * @return 连接类型
	*/
	public String getSessionType() {
		return sessionType;
	}
	
	/**
	 * 设置 连接类型
	 * @param sessionType 连接类型
	 * @return 当前对象
	*/
	public NetworkStrategyInfo setSessionType(String sessionType) {
		this.sessionType=sessionType;
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
	public NetworkStrategyInfo setNotes(String notes) {
		this.notes=notes;
		return this;
	}
	
	/**
	 * 获得 选择<br>
	 * 选择
	 * @return 选择
	*/
	public String getSelectedCode() {
		return selectedCode;
	}
	
	/**
	 * 设置 选择
	 * @param selectedCode 选择
	 * @return 当前对象
	*/
	public NetworkStrategyInfo setSelectedCode(String selectedCode) {
		this.selectedCode=selectedCode;
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
	public NetworkStrategyInfo setCreateBy(String createBy) {
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
	public NetworkStrategyInfo setCreateTime(Date createTime) {
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
	public NetworkStrategyInfo setUpdateBy(String updateBy) {
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
	public NetworkStrategyInfo setUpdateTime(Date updateTime) {
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
	public NetworkStrategyInfo setDeleted(Integer deleted) {
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
	public NetworkStrategyInfo setDeleted(Boolean deletedBool) {
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
	public NetworkStrategyInfo setDeleteBy(String deleteBy) {
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
	public NetworkStrategyInfo setDeleteTime(Date deleteTime) {
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
	public NetworkStrategyInfo setVersion(Integer version) {
		this.version=version;
		return this;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return NetworkStrategyInfo , 转换好的 NetworkStrategyInfo 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return NetworkStrategyInfo , 转换好的 PoJo 对象
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
	 * 将 Map 转换成 NetworkStrategyInfo
	 * @param networkStrategyInfoMap 包含实体信息的 Map 对象
	 * @return NetworkStrategyInfo , 转换好的的 NetworkStrategyInfo 对象
	*/
	@Transient
	public static NetworkStrategyInfo createFrom(Map<String,Object> networkStrategyInfoMap) {
		if(networkStrategyInfoMap==null) return null;
		NetworkStrategyInfo po = EntityContext.create(NetworkStrategyInfo.class, networkStrategyInfoMap);
		return po;
	}

	/**
	 * 将 Pojo 转换成 NetworkStrategyInfo
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return NetworkStrategyInfo , 转换好的的 NetworkStrategyInfo 对象
	*/
	@Transient
	public static NetworkStrategyInfo createFrom(Object pojo) {
		if(pojo==null) return null;
		NetworkStrategyInfo po = EntityContext.create(NetworkStrategyInfo.class,pojo);
		return po;
	}

	/**
	 * 创建一个 NetworkStrategyInfo，等同于 new
	 * @return NetworkStrategyInfo 对象
	*/
	@Transient
	public static NetworkStrategyInfo create() {
		return EntityContext.create(NetworkStrategyInfo.class);
	}
}