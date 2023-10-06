package com.dt.platform.domain.ops;

import com.github.foxnic.dao.entity.Entity;
import io.swagger.annotations.ApiModel;
import javax.persistence.Table;
import com.github.foxnic.sql.meta.DBTable;
import com.dt.platform.constants.db.OpsTables.OPS_MONITOR_TPL;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Transient;
import com.github.foxnic.api.swagger.EnumFor;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.foxnic.commons.lang.DataParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import com.github.foxnic.dao.entity.EntityContext;
import com.dt.platform.domain.ops.meta.MonitorTplMeta;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 监控模版
 * <p>监控模版 , 数据表 ops_monitor_tpl 的PO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-10-06 10:29:20
 * @sign 95C0708A60B6D2EA9726F319E49ADF0D
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@Table(name = "ops_monitor_tpl")
@ApiModel(description = "监控模版 ; 监控模版 , 数据表 ops_monitor_tpl 的PO类型")
public class MonitorTpl extends Entity {

	private static final long serialVersionUID = 1L;

	public static final DBTable TABLE =OPS_MONITOR_TPL.$TABLE;
	
	/**
	 * 主键：主键
	*/
	@Id
	@ApiModelProperty(required = true,value="主键" , notes = "主键" , example = "1")
	private String id;
	
	/**
	 * 名称：名称
	*/
	@ApiModelProperty(required = false,value="名称" , notes = "名称" , example = "主机_Linux监控zabbix模版")
	private String name;
	
	/**
	 * 编码：编码
	*/
	@ApiModelProperty(required = false,value="编码" , notes = "编码" , example = "tpl_host_linux_zabbix")
	private String code;
	
	/**
	 * 状态：状态
	*/
	@ApiModelProperty(required = false,value="状态" , notes = "状态" , example = "enable")
	private String status;
	
	/**
	 * 分类：分类
	*/
	@ApiModelProperty(required = false,value="分类" , notes = "分类" , example = "host")
	private String type;
	
	/**
	 * 备注：备注
	*/
	@ApiModelProperty(required = false,value="备注" , notes = "备注" , example = "Linux主机监控模版")
	private String notes;
	
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
	@ApiModelProperty(required = false,value="修改人ID" , notes = "修改人ID" , example = "110588348101165911")
	private String updateBy;
	
	/**
	 * 修改时间：修改时间
	*/
	@ApiModelProperty(required = false,value="修改时间" , notes = "修改时间" , example = "2023-10-02 11:23:51")
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
	 * 版本：版本
	*/
	@ApiModelProperty(required = true,value="版本" , notes = "版本" , example = "6")
	private Integer version;
	
	/**
	 * 节点模版类型：节点模版类型
	*/
	@ApiModelProperty(required = false,value="节点模版类型" , notes = "节点模版类型")
	private MonitorTplType tplType;
	
	/**
	 * 指标列表：指标列表
	*/
	@ApiModelProperty(required = false,value="指标列表" , notes = "指标列表")
	private List<MonitorTplIndicator> tplIndicatorList;
	
	/**
	 * 图形：图形
	*/
	@ApiModelProperty(required = false,value="图形" , notes = "图形")
	private List<MonitorTplGraph> graphList;
	
	/**
	 * 触发器：触发器
	*/
	@ApiModelProperty(required = false,value="触发器" , notes = "触发器")
	private List<MonitorTplTrigger> triggerList;
	
	/**
	 * indicatorCount：indicatorCount
	*/
	@ApiModelProperty(required = false,value="indicatorCount" , notes = "indicatorCount")
	private String indicatorCount;
	
	/**
	 * graphCount：graphCount
	*/
	@ApiModelProperty(required = false,value="graphCount" , notes = "graphCount")
	private String graphCount;
	
	/**
	 * triggerCount：triggerCount
	*/
	@ApiModelProperty(required = false,value="triggerCount" , notes = "triggerCount")
	private String triggerCount;
	
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
	public MonitorTpl setId(String id) {
		this.id=id;
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
	public MonitorTpl setName(String name) {
		this.name=name;
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
	public MonitorTpl setCode(String code) {
		this.code=code;
		return this;
	}
	
	/**
	 * 获得 状态<br>
	 * 状态
	 * @return 状态
	*/
	public String getStatus() {
		return status;
	}
	
	/**
	 * 设置 状态
	 * @param status 状态
	 * @return 当前对象
	*/
	public MonitorTpl setStatus(String status) {
		this.status=status;
		return this;
	}
	
	/**
	 * 获得 分类<br>
	 * 分类
	 * @return 分类
	*/
	public String getType() {
		return type;
	}
	
	/**
	 * 设置 分类
	 * @param type 分类
	 * @return 当前对象
	*/
	public MonitorTpl setType(String type) {
		this.type=type;
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
	public MonitorTpl setNotes(String notes) {
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
	public MonitorTpl setCreateBy(String createBy) {
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
	public MonitorTpl setCreateTime(Date createTime) {
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
	public MonitorTpl setUpdateBy(String updateBy) {
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
	public MonitorTpl setUpdateTime(Date updateTime) {
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
	public MonitorTpl setDeleted(Integer deleted) {
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
	public MonitorTpl setDeleted(Boolean deletedBool) {
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
	public MonitorTpl setDeleteBy(String deleteBy) {
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
	public MonitorTpl setDeleteTime(Date deleteTime) {
		this.deleteTime=deleteTime;
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
	public MonitorTpl setVersion(Integer version) {
		this.version=version;
		return this;
	}
	
	/**
	 * 获得 节点模版类型<br>
	 * 节点模版类型
	 * @return 节点模版类型
	*/
	public MonitorTplType getTplType() {
		return tplType;
	}
	
	/**
	 * 设置 节点模版类型
	 * @param tplType 节点模版类型
	 * @return 当前对象
	*/
	public MonitorTpl setTplType(MonitorTplType tplType) {
		this.tplType=tplType;
		return this;
	}
	
	/**
	 * 获得 指标列表<br>
	 * 指标列表
	 * @return 指标列表
	*/
	public List<MonitorTplIndicator> getTplIndicatorList() {
		return tplIndicatorList;
	}
	
	/**
	 * 设置 指标列表
	 * @param tplIndicatorList 指标列表
	 * @return 当前对象
	*/
	public MonitorTpl setTplIndicatorList(List<MonitorTplIndicator> tplIndicatorList) {
		this.tplIndicatorList=tplIndicatorList;
		return this;
	}
	
	/**
	 * 添加 指标列表
	 * @param tplIndicator 指标列表
	 * @return 当前对象
	*/
	public MonitorTpl addTplIndicator(MonitorTplIndicator... tplIndicator) {
		if(this.tplIndicatorList==null) tplIndicatorList=new ArrayList<>();
		this.tplIndicatorList.addAll(Arrays.asList(tplIndicator));
		return this;
	}
	
	/**
	 * 获得 图形<br>
	 * 图形
	 * @return 图形
	*/
	public List<MonitorTplGraph> getGraphList() {
		return graphList;
	}
	
	/**
	 * 设置 图形
	 * @param graphList 图形
	 * @return 当前对象
	*/
	public MonitorTpl setGraphList(List<MonitorTplGraph> graphList) {
		this.graphList=graphList;
		return this;
	}
	
	/**
	 * 添加 图形
	 * @param graph 图形
	 * @return 当前对象
	*/
	public MonitorTpl addGraph(MonitorTplGraph... graph) {
		if(this.graphList==null) graphList=new ArrayList<>();
		this.graphList.addAll(Arrays.asList(graph));
		return this;
	}
	
	/**
	 * 获得 触发器<br>
	 * 触发器
	 * @return 触发器
	*/
	public List<MonitorTplTrigger> getTriggerList() {
		return triggerList;
	}
	
	/**
	 * 设置 触发器
	 * @param triggerList 触发器
	 * @return 当前对象
	*/
	public MonitorTpl setTriggerList(List<MonitorTplTrigger> triggerList) {
		this.triggerList=triggerList;
		return this;
	}
	
	/**
	 * 添加 触发器
	 * @param trigger 触发器
	 * @return 当前对象
	*/
	public MonitorTpl addTrigger(MonitorTplTrigger... trigger) {
		if(this.triggerList==null) triggerList=new ArrayList<>();
		this.triggerList.addAll(Arrays.asList(trigger));
		return this;
	}
	
	/**
	 * 获得 indicatorCount<br>
	 * indicatorCount
	 * @return indicatorCount
	*/
	public String getIndicatorCount() {
		return indicatorCount;
	}
	
	/**
	 * 设置 indicatorCount
	 * @param indicatorCount indicatorCount
	 * @return 当前对象
	*/
	public MonitorTpl setIndicatorCount(String indicatorCount) {
		this.indicatorCount=indicatorCount;
		return this;
	}
	
	/**
	 * 获得 graphCount<br>
	 * graphCount
	 * @return graphCount
	*/
	public String getGraphCount() {
		return graphCount;
	}
	
	/**
	 * 设置 graphCount
	 * @param graphCount graphCount
	 * @return 当前对象
	*/
	public MonitorTpl setGraphCount(String graphCount) {
		this.graphCount=graphCount;
		return this;
	}
	
	/**
	 * 获得 triggerCount<br>
	 * triggerCount
	 * @return triggerCount
	*/
	public String getTriggerCount() {
		return triggerCount;
	}
	
	/**
	 * 设置 triggerCount
	 * @param triggerCount triggerCount
	 * @return 当前对象
	*/
	public MonitorTpl setTriggerCount(String triggerCount) {
		this.triggerCount=triggerCount;
		return this;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return MonitorTpl , 转换好的 MonitorTpl 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return MonitorTpl , 转换好的 PoJo 对象
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
	public MonitorTpl clone() {
		return duplicate(true);
	}

	/**
	 * 复制当前对象
	 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
	*/
	@Transient
	public MonitorTpl duplicate(boolean all) {
		com.dt.platform.domain.ops.meta.MonitorTplMeta.$$proxy$$ inst = new com.dt.platform.domain.ops.meta.MonitorTplMeta.$$proxy$$();
		inst.setCode(this.getCode());
		inst.setNotes(this.getNotes());
		inst.setUpdateTime(this.getUpdateTime());
		inst.setType(this.getType());
		inst.setVersion(this.getVersion());
		inst.setCreateBy(this.getCreateBy());
		inst.setDeleted(this.getDeleted());
		inst.setCreateTime(this.getCreateTime());
		inst.setUpdateBy(this.getUpdateBy());
		inst.setDeleteTime(this.getDeleteTime());
		inst.setName(this.getName());
		inst.setDeleteBy(this.getDeleteBy());
		inst.setId(this.getId());
		inst.setStatus(this.getStatus());
		if(all) {
			inst.setTplType(this.getTplType());
			inst.setTplIndicatorList(this.getTplIndicatorList());
			inst.setTriggerList(this.getTriggerList());
			inst.setIndicatorCount(this.getIndicatorCount());
			inst.setGraphList(this.getGraphList());
			inst.setTriggerCount(this.getTriggerCount());
			inst.setGraphCount(this.getGraphCount());
		}
		inst.clearModifies();
		return inst;
	}

	/**
	 * 克隆当前对象
	*/
	@Transient
	public MonitorTpl clone(boolean deep) {
		return EntityContext.clone(MonitorTpl.class,this,deep);
	}

	/**
	 * 将 Map 转换成 MonitorTpl
	 * @param monitorTplMap 包含实体信息的 Map 对象
	 * @return MonitorTpl , 转换好的的 MonitorTpl 对象
	*/
	@Transient
	public static MonitorTpl createFrom(Map<String,Object> monitorTplMap) {
		if(monitorTplMap==null) return null;
		MonitorTpl po = create();
		EntityContext.copyProperties(po,monitorTplMap);
		po.clearModifies();
		return po;
	}

	/**
	 * 将 Pojo 转换成 MonitorTpl
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return MonitorTpl , 转换好的的 MonitorTpl 对象
	*/
	@Transient
	public static MonitorTpl createFrom(Object pojo) {
		if(pojo==null) return null;
		MonitorTpl po = create();
		EntityContext.copyProperties(po,pojo);
		po.clearModifies();
		return po;
	}

	/**
	 * 创建一个 MonitorTpl，等同于 new
	 * @return MonitorTpl 对象
	*/
	@Transient
	public static MonitorTpl create() {
		return new com.dt.platform.domain.ops.meta.MonitorTplMeta.$$proxy$$();
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
			this.setCode(DataParser.parse(String.class, map.get(MonitorTplMeta.CODE)));
			this.setNotes(DataParser.parse(String.class, map.get(MonitorTplMeta.NOTES)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(MonitorTplMeta.UPDATE_TIME)));
			this.setType(DataParser.parse(String.class, map.get(MonitorTplMeta.TYPE)));
			this.setVersion(DataParser.parse(Integer.class, map.get(MonitorTplMeta.VERSION)));
			this.setCreateBy(DataParser.parse(String.class, map.get(MonitorTplMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(MonitorTplMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(MonitorTplMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(MonitorTplMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(MonitorTplMeta.DELETE_TIME)));
			this.setName(DataParser.parse(String.class, map.get(MonitorTplMeta.NAME)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(MonitorTplMeta.DELETE_BY)));
			this.setId(DataParser.parse(String.class, map.get(MonitorTplMeta.ID)));
			this.setStatus(DataParser.parse(String.class, map.get(MonitorTplMeta.STATUS)));
			// others
			this.setTplType(DataParser.parse(MonitorTplType.class, map.get(MonitorTplMeta.TPL_TYPE)));
			this.setIndicatorCount(DataParser.parse(String.class, map.get(MonitorTplMeta.INDICATOR_COUNT)));
			this.setTriggerCount(DataParser.parse(String.class, map.get(MonitorTplMeta.TRIGGER_COUNT)));
			this.setGraphCount(DataParser.parse(String.class, map.get(MonitorTplMeta.GRAPH_COUNT)));
			return true;
		} else {
			try {
				this.setCode( (String)map.get(MonitorTplMeta.CODE));
				this.setNotes( (String)map.get(MonitorTplMeta.NOTES));
				this.setUpdateTime( (Date)map.get(MonitorTplMeta.UPDATE_TIME));
				this.setType( (String)map.get(MonitorTplMeta.TYPE));
				this.setVersion( (Integer)map.get(MonitorTplMeta.VERSION));
				this.setCreateBy( (String)map.get(MonitorTplMeta.CREATE_BY));
				this.setDeleted( (Integer)map.get(MonitorTplMeta.DELETED));
				this.setCreateTime( (Date)map.get(MonitorTplMeta.CREATE_TIME));
				this.setUpdateBy( (String)map.get(MonitorTplMeta.UPDATE_BY));
				this.setDeleteTime( (Date)map.get(MonitorTplMeta.DELETE_TIME));
				this.setName( (String)map.get(MonitorTplMeta.NAME));
				this.setDeleteBy( (String)map.get(MonitorTplMeta.DELETE_BY));
				this.setId( (String)map.get(MonitorTplMeta.ID));
				this.setStatus( (String)map.get(MonitorTplMeta.STATUS));
				// others
				this.setTplType( (MonitorTplType)map.get(MonitorTplMeta.TPL_TYPE));
				this.setIndicatorCount( (String)map.get(MonitorTplMeta.INDICATOR_COUNT));
				this.setTriggerCount( (String)map.get(MonitorTplMeta.TRIGGER_COUNT));
				this.setGraphCount( (String)map.get(MonitorTplMeta.GRAPH_COUNT));
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
			this.setCode(DataParser.parse(String.class, r.getValue(MonitorTplMeta.CODE)));
			this.setNotes(DataParser.parse(String.class, r.getValue(MonitorTplMeta.NOTES)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(MonitorTplMeta.UPDATE_TIME)));
			this.setType(DataParser.parse(String.class, r.getValue(MonitorTplMeta.TYPE)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(MonitorTplMeta.VERSION)));
			this.setCreateBy(DataParser.parse(String.class, r.getValue(MonitorTplMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(MonitorTplMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(MonitorTplMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(MonitorTplMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(MonitorTplMeta.DELETE_TIME)));
			this.setName(DataParser.parse(String.class, r.getValue(MonitorTplMeta.NAME)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(MonitorTplMeta.DELETE_BY)));
			this.setId(DataParser.parse(String.class, r.getValue(MonitorTplMeta.ID)));
			this.setStatus(DataParser.parse(String.class, r.getValue(MonitorTplMeta.STATUS)));
			return true;
		} else {
			try {
				this.setCode( (String)r.getValue(MonitorTplMeta.CODE));
				this.setNotes( (String)r.getValue(MonitorTplMeta.NOTES));
				this.setUpdateTime( (Date)r.getValue(MonitorTplMeta.UPDATE_TIME));
				this.setType( (String)r.getValue(MonitorTplMeta.TYPE));
				this.setVersion( (Integer)r.getValue(MonitorTplMeta.VERSION));
				this.setCreateBy( (String)r.getValue(MonitorTplMeta.CREATE_BY));
				this.setDeleted( (Integer)r.getValue(MonitorTplMeta.DELETED));
				this.setCreateTime( (Date)r.getValue(MonitorTplMeta.CREATE_TIME));
				this.setUpdateBy( (String)r.getValue(MonitorTplMeta.UPDATE_BY));
				this.setDeleteTime( (Date)r.getValue(MonitorTplMeta.DELETE_TIME));
				this.setName( (String)r.getValue(MonitorTplMeta.NAME));
				this.setDeleteBy( (String)r.getValue(MonitorTplMeta.DELETE_BY));
				this.setId( (String)r.getValue(MonitorTplMeta.ID));
				this.setStatus( (String)r.getValue(MonitorTplMeta.STATUS));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}