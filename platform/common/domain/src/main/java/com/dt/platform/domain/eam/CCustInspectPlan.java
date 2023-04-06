package com.dt.platform.domain.eam;

import com.github.foxnic.dao.entity.Entity;
import io.swagger.annotations.ApiModel;
import javax.persistence.Table;
import com.github.foxnic.sql.meta.DBTable;
import com.dt.platform.constants.db.EAMTables.EAM_C_CUST_INSPECT_PLAN;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Transient;
import com.github.foxnic.api.swagger.EnumFor;
import org.github.foxnic.web.domain.hrm.Employee;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.foxnic.commons.lang.DataParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import com.github.foxnic.dao.entity.EntityContext;
import com.dt.platform.domain.eam.meta.CCustInspectPlanMeta;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 巡检计划
 * <p>巡检计划 , 数据表 eam_c_cust_inspect_plan 的PO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-04-06 10:34:32
 * @sign 2A682596C22737CC9D20DA72B1EA77B2
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@Table(name = "eam_c_cust_inspect_plan")
@ApiModel(description = "巡检计划 ; 巡检计划 , 数据表 eam_c_cust_inspect_plan 的PO类型")
public class CCustInspectPlan extends Entity {

	private static final long serialVersionUID = 1L;

	public static final DBTable TABLE =EAM_C_CUST_INSPECT_PLAN.$TABLE;
	
	/**
	 * 主键：主键
	*/
	@Id
	@ApiModelProperty(required = true,value="主键" , notes = "主键")
	private String id;
	
	/**
	 * 标题：标题
	*/
	@ApiModelProperty(required = false,value="标题" , notes = "标题")
	private String name;
	
	/**
	 * 状态：状态
	*/
	@ApiModelProperty(required = false,value="状态" , notes = "状态")
	private String status;
	
	/**
	 * 巡检类型：巡检类型
	*/
	@ApiModelProperty(required = false,value="巡检类型" , notes = "巡检类型")
	private String type;
	
	/**
	 * 负责人：负责人
	*/
	@ApiModelProperty(required = false,value="负责人" , notes = "负责人")
	private String inspectUserId;
	
	/**
	 * 模版：模版
	*/
	@ApiModelProperty(required = false,value="模版" , notes = "模版")
	private String tplId;
	
	/**
	 * 触发周期：触发周期
	*/
	@ApiModelProperty(required = false,value="触发周期" , notes = "触发周期")
	private String crontab;
	
	/**
	 * 备注：备注
	*/
	@ApiModelProperty(required = false,value="备注" , notes = "备注")
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
	@ApiModelProperty(required = false,value="租户" , notes = "租户")
	private String tenantId;
	
	/**
	 * version：version
	*/
	@ApiModelProperty(required = true,value="version" , notes = "version")
	private Integer version;
	
	/**
	 * custInspectTpl：custInspectTpl
	*/
	@ApiModelProperty(required = false,value="custInspectTpl" , notes = "custInspectTpl")
	private CCustInspectTpl custInspectTpl;
	
	/**
	 * leader：leader
	*/
	@ApiModelProperty(required = false,value="leader" , notes = "leader")
	private Employee leader;
	
	/**
	 * 巡检人：巡检人
	*/
	@ApiModelProperty(required = false,value="巡检人" , notes = "巡检人")
	private List<Employee> memberList;
	
	/**
	 * 巡检人：巡检人
	*/
	@ApiModelProperty(required = false,value="巡检人" , notes = "巡检人")
	private List<String> memberIds;
	
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
	public CCustInspectPlan setId(String id) {
		this.id=id;
		return this;
	}
	
	/**
	 * 获得 标题<br>
	 * 标题
	 * @return 标题
	*/
	public String getName() {
		return name;
	}
	
	/**
	 * 设置 标题
	 * @param name 标题
	 * @return 当前对象
	*/
	public CCustInspectPlan setName(String name) {
		this.name=name;
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
	public CCustInspectPlan setStatus(String status) {
		this.status=status;
		return this;
	}
	
	/**
	 * 获得 巡检类型<br>
	 * 巡检类型
	 * @return 巡检类型
	*/
	public String getType() {
		return type;
	}
	
	/**
	 * 设置 巡检类型
	 * @param type 巡检类型
	 * @return 当前对象
	*/
	public CCustInspectPlan setType(String type) {
		this.type=type;
		return this;
	}
	
	/**
	 * 获得 负责人<br>
	 * 负责人
	 * @return 负责人
	*/
	public String getInspectUserId() {
		return inspectUserId;
	}
	
	/**
	 * 设置 负责人
	 * @param inspectUserId 负责人
	 * @return 当前对象
	*/
	public CCustInspectPlan setInspectUserId(String inspectUserId) {
		this.inspectUserId=inspectUserId;
		return this;
	}
	
	/**
	 * 获得 模版<br>
	 * 模版
	 * @return 模版
	*/
	public String getTplId() {
		return tplId;
	}
	
	/**
	 * 设置 模版
	 * @param tplId 模版
	 * @return 当前对象
	*/
	public CCustInspectPlan setTplId(String tplId) {
		this.tplId=tplId;
		return this;
	}
	
	/**
	 * 获得 触发周期<br>
	 * 触发周期
	 * @return 触发周期
	*/
	public String getCrontab() {
		return crontab;
	}
	
	/**
	 * 设置 触发周期
	 * @param crontab 触发周期
	 * @return 当前对象
	*/
	public CCustInspectPlan setCrontab(String crontab) {
		this.crontab=crontab;
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
	public CCustInspectPlan setNotes(String notes) {
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
	public CCustInspectPlan setCreateBy(String createBy) {
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
	public CCustInspectPlan setCreateTime(Date createTime) {
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
	public CCustInspectPlan setUpdateBy(String updateBy) {
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
	public CCustInspectPlan setUpdateTime(Date updateTime) {
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
	public CCustInspectPlan setDeleted(Integer deleted) {
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
	public CCustInspectPlan setDeleted(Boolean deletedBool) {
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
	public CCustInspectPlan setDeleteBy(String deleteBy) {
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
	public CCustInspectPlan setDeleteTime(Date deleteTime) {
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
	public CCustInspectPlan setTenantId(String tenantId) {
		this.tenantId=tenantId;
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
	public CCustInspectPlan setVersion(Integer version) {
		this.version=version;
		return this;
	}
	
	/**
	 * 获得 custInspectTpl<br>
	 * custInspectTpl
	 * @return custInspectTpl
	*/
	public CCustInspectTpl getCustInspectTpl() {
		return custInspectTpl;
	}
	
	/**
	 * 设置 custInspectTpl
	 * @param custInspectTpl custInspectTpl
	 * @return 当前对象
	*/
	public CCustInspectPlan setCustInspectTpl(CCustInspectTpl custInspectTpl) {
		this.custInspectTpl=custInspectTpl;
		return this;
	}
	
	/**
	 * 获得 leader<br>
	 * leader
	 * @return leader
	*/
	public Employee getLeader() {
		return leader;
	}
	
	/**
	 * 设置 leader
	 * @param leader leader
	 * @return 当前对象
	*/
	public CCustInspectPlan setLeader(Employee leader) {
		this.leader=leader;
		return this;
	}
	
	/**
	 * 获得 巡检人<br>
	 * 巡检人
	 * @return 巡检人
	*/
	public List<Employee> getMemberList() {
		return memberList;
	}
	
	/**
	 * 设置 巡检人
	 * @param memberList 巡检人
	 * @return 当前对象
	*/
	public CCustInspectPlan setMemberList(List<Employee> memberList) {
		this.memberList=memberList;
		return this;
	}
	
	/**
	 * 添加 巡检人
	 * @param member 巡检人
	 * @return 当前对象
	*/
	public CCustInspectPlan addMember(Employee... member) {
		if(this.memberList==null) memberList=new ArrayList<>();
		this.memberList.addAll(Arrays.asList(member));
		return this;
	}
	
	/**
	 * 获得 巡检人<br>
	 * 巡检人
	 * @return 巡检人
	*/
	public List<String> getMemberIds() {
		return memberIds;
	}
	
	/**
	 * 设置 巡检人
	 * @param memberIds 巡检人
	 * @return 当前对象
	*/
	public CCustInspectPlan setMemberIds(List<String> memberIds) {
		this.memberIds=memberIds;
		return this;
	}
	
	/**
	 * 添加 巡检人
	 * @param memberId 巡检人
	 * @return 当前对象
	*/
	public CCustInspectPlan addMemberId(String... memberId) {
		if(this.memberIds==null) memberIds=new ArrayList<>();
		this.memberIds.addAll(Arrays.asList(memberId));
		return this;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return CCustInspectPlan , 转换好的 CCustInspectPlan 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return CCustInspectPlan , 转换好的 PoJo 对象
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
	public CCustInspectPlan clone() {
		return duplicate(true);
	}

	/**
	 * 复制当前对象
	 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
	*/
	@Transient
	public CCustInspectPlan duplicate(boolean all) {
		com.dt.platform.domain.eam.meta.CCustInspectPlanMeta.$$proxy$$ inst = new com.dt.platform.domain.eam.meta.CCustInspectPlanMeta.$$proxy$$();
		inst.setNotes(this.getNotes());
		inst.setCrontab(this.getCrontab());
		inst.setUpdateTime(this.getUpdateTime());
		inst.setType(this.getType());
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
		inst.setTplId(this.getTplId());
		inst.setInspectUserId(this.getInspectUserId());
		inst.setStatus(this.getStatus());
		if(all) {
			inst.setLeader(this.getLeader());
			inst.setMemberList(this.getMemberList());
			inst.setCustInspectTpl(this.getCustInspectTpl());
			inst.setMemberIds(this.getMemberIds());
		}
		inst.clearModifies();
		return inst;
	}

	/**
	 * 克隆当前对象
	*/
	@Transient
	public CCustInspectPlan clone(boolean deep) {
		return EntityContext.clone(CCustInspectPlan.class,this,deep);
	}

	/**
	 * 将 Map 转换成 CCustInspectPlan
	 * @param cCustInspectPlanMap 包含实体信息的 Map 对象
	 * @return CCustInspectPlan , 转换好的的 CCustInspectPlan 对象
	*/
	@Transient
	public static CCustInspectPlan createFrom(Map<String,Object> cCustInspectPlanMap) {
		if(cCustInspectPlanMap==null) return null;
		CCustInspectPlan po = create();
		EntityContext.copyProperties(po,cCustInspectPlanMap);
		po.clearModifies();
		return po;
	}

	/**
	 * 将 Pojo 转换成 CCustInspectPlan
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return CCustInspectPlan , 转换好的的 CCustInspectPlan 对象
	*/
	@Transient
	public static CCustInspectPlan createFrom(Object pojo) {
		if(pojo==null) return null;
		CCustInspectPlan po = create();
		EntityContext.copyProperties(po,pojo);
		po.clearModifies();
		return po;
	}

	/**
	 * 创建一个 CCustInspectPlan，等同于 new
	 * @return CCustInspectPlan 对象
	*/
	@Transient
	public static CCustInspectPlan create() {
		return new com.dt.platform.domain.eam.meta.CCustInspectPlanMeta.$$proxy$$();
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
			this.setNotes(DataParser.parse(String.class, map.get(CCustInspectPlanMeta.NOTES)));
			this.setCrontab(DataParser.parse(String.class, map.get(CCustInspectPlanMeta.CRONTAB)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(CCustInspectPlanMeta.UPDATE_TIME)));
			this.setType(DataParser.parse(String.class, map.get(CCustInspectPlanMeta.TYPE)));
			this.setVersion(DataParser.parse(Integer.class, map.get(CCustInspectPlanMeta.VERSION)));
			this.setCreateBy(DataParser.parse(String.class, map.get(CCustInspectPlanMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(CCustInspectPlanMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(CCustInspectPlanMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(CCustInspectPlanMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(CCustInspectPlanMeta.DELETE_TIME)));
			this.setName(DataParser.parse(String.class, map.get(CCustInspectPlanMeta.NAME)));
			this.setTenantId(DataParser.parse(String.class, map.get(CCustInspectPlanMeta.TENANT_ID)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(CCustInspectPlanMeta.DELETE_BY)));
			this.setId(DataParser.parse(String.class, map.get(CCustInspectPlanMeta.ID)));
			this.setTplId(DataParser.parse(String.class, map.get(CCustInspectPlanMeta.TPL_ID)));
			this.setInspectUserId(DataParser.parse(String.class, map.get(CCustInspectPlanMeta.INSPECT_USER_ID)));
			this.setStatus(DataParser.parse(String.class, map.get(CCustInspectPlanMeta.STATUS)));
			// others
			this.setLeader(DataParser.parse(Employee.class, map.get(CCustInspectPlanMeta.LEADER)));
			this.setCustInspectTpl(DataParser.parse(CCustInspectTpl.class, map.get(CCustInspectPlanMeta.CUST_INSPECT_TPL)));
			return true;
		} else {
			try {
				this.setNotes( (String)map.get(CCustInspectPlanMeta.NOTES));
				this.setCrontab( (String)map.get(CCustInspectPlanMeta.CRONTAB));
				this.setUpdateTime( (Date)map.get(CCustInspectPlanMeta.UPDATE_TIME));
				this.setType( (String)map.get(CCustInspectPlanMeta.TYPE));
				this.setVersion( (Integer)map.get(CCustInspectPlanMeta.VERSION));
				this.setCreateBy( (String)map.get(CCustInspectPlanMeta.CREATE_BY));
				this.setDeleted( (Integer)map.get(CCustInspectPlanMeta.DELETED));
				this.setCreateTime( (Date)map.get(CCustInspectPlanMeta.CREATE_TIME));
				this.setUpdateBy( (String)map.get(CCustInspectPlanMeta.UPDATE_BY));
				this.setDeleteTime( (Date)map.get(CCustInspectPlanMeta.DELETE_TIME));
				this.setName( (String)map.get(CCustInspectPlanMeta.NAME));
				this.setTenantId( (String)map.get(CCustInspectPlanMeta.TENANT_ID));
				this.setDeleteBy( (String)map.get(CCustInspectPlanMeta.DELETE_BY));
				this.setId( (String)map.get(CCustInspectPlanMeta.ID));
				this.setTplId( (String)map.get(CCustInspectPlanMeta.TPL_ID));
				this.setInspectUserId( (String)map.get(CCustInspectPlanMeta.INSPECT_USER_ID));
				this.setStatus( (String)map.get(CCustInspectPlanMeta.STATUS));
				// others
				this.setLeader( (Employee)map.get(CCustInspectPlanMeta.LEADER));
				this.setCustInspectTpl( (CCustInspectTpl)map.get(CCustInspectPlanMeta.CUST_INSPECT_TPL));
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
			this.setNotes(DataParser.parse(String.class, r.getValue(CCustInspectPlanMeta.NOTES)));
			this.setCrontab(DataParser.parse(String.class, r.getValue(CCustInspectPlanMeta.CRONTAB)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(CCustInspectPlanMeta.UPDATE_TIME)));
			this.setType(DataParser.parse(String.class, r.getValue(CCustInspectPlanMeta.TYPE)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(CCustInspectPlanMeta.VERSION)));
			this.setCreateBy(DataParser.parse(String.class, r.getValue(CCustInspectPlanMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(CCustInspectPlanMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(CCustInspectPlanMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(CCustInspectPlanMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(CCustInspectPlanMeta.DELETE_TIME)));
			this.setName(DataParser.parse(String.class, r.getValue(CCustInspectPlanMeta.NAME)));
			this.setTenantId(DataParser.parse(String.class, r.getValue(CCustInspectPlanMeta.TENANT_ID)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(CCustInspectPlanMeta.DELETE_BY)));
			this.setId(DataParser.parse(String.class, r.getValue(CCustInspectPlanMeta.ID)));
			this.setTplId(DataParser.parse(String.class, r.getValue(CCustInspectPlanMeta.TPL_ID)));
			this.setInspectUserId(DataParser.parse(String.class, r.getValue(CCustInspectPlanMeta.INSPECT_USER_ID)));
			this.setStatus(DataParser.parse(String.class, r.getValue(CCustInspectPlanMeta.STATUS)));
			return true;
		} else {
			try {
				this.setNotes( (String)r.getValue(CCustInspectPlanMeta.NOTES));
				this.setCrontab( (String)r.getValue(CCustInspectPlanMeta.CRONTAB));
				this.setUpdateTime( (Date)r.getValue(CCustInspectPlanMeta.UPDATE_TIME));
				this.setType( (String)r.getValue(CCustInspectPlanMeta.TYPE));
				this.setVersion( (Integer)r.getValue(CCustInspectPlanMeta.VERSION));
				this.setCreateBy( (String)r.getValue(CCustInspectPlanMeta.CREATE_BY));
				this.setDeleted( (Integer)r.getValue(CCustInspectPlanMeta.DELETED));
				this.setCreateTime( (Date)r.getValue(CCustInspectPlanMeta.CREATE_TIME));
				this.setUpdateBy( (String)r.getValue(CCustInspectPlanMeta.UPDATE_BY));
				this.setDeleteTime( (Date)r.getValue(CCustInspectPlanMeta.DELETE_TIME));
				this.setName( (String)r.getValue(CCustInspectPlanMeta.NAME));
				this.setTenantId( (String)r.getValue(CCustInspectPlanMeta.TENANT_ID));
				this.setDeleteBy( (String)r.getValue(CCustInspectPlanMeta.DELETE_BY));
				this.setId( (String)r.getValue(CCustInspectPlanMeta.ID));
				this.setTplId( (String)r.getValue(CCustInspectPlanMeta.TPL_ID));
				this.setInspectUserId( (String)r.getValue(CCustInspectPlanMeta.INSPECT_USER_ID));
				this.setStatus( (String)r.getValue(CCustInspectPlanMeta.STATUS));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}