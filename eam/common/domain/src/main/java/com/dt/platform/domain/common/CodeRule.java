package com.dt.platform.domain.common;

import com.github.foxnic.dao.entity.Entity;
import javax.persistence.Table;
import com.github.foxnic.sql.meta.DBTable;
import com.dt.platform.constants.db.EAMTables.SYS_CODE_RULE;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.github.foxnic.web.domain.oauth.Menu;
import javax.persistence.Transient;
import java.util.Map;
import com.github.foxnic.dao.entity.EntityContext;



/**
 * null
 * @author 金杰 , maillank@qq.com
 * @since 2021-09-10 16:42:23
 * @sign D720B313194939748B92CB787611DCBC
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@Table(name = "sys_code_rule")
public class CodeRule extends Entity {

	private static final long serialVersionUID = 1L;

	public static final DBTable TABLE =SYS_CODE_RULE.$TABLE;
	
	/**
	 * 主键：主键
	*/
	@Id
	@ApiModelProperty(required = true,value="主键" , notes = "主键")
	private String id;
	
	/**
	 * 编码名称：编码名称
	*/
	@ApiModelProperty(required = false,value="编码名称" , notes = "编码名称")
	private String name;
	
	/**
	 * 业务模块：业务模块
	*/
	@ApiModelProperty(required = false,value="业务模块" , notes = "业务模块")
	private String moduleId;
	
	/**
	 * 编码规则：编码规则
	*/
	@ApiModelProperty(required = false,value="编码规则" , notes = "编码规则")
	private String rule;
	
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
	@ApiModelProperty(required = false,value="version" , notes = "version")
	private Integer version;
	
	/**
	 * 关联模块：关联模块
	*/
	@ApiModelProperty(required = false,value="关联模块" , notes = "关联模块")
	private Menu module;
	
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
	public CodeRule setId(String id) {
		this.id=id;
		return this;
	}
	
	/**
	 * 获得 编码名称<br>
	 * 编码名称
	 * @return 编码名称
	*/
	public String getName() {
		return name;
	}
	
	/**
	 * 设置 编码名称
	 * @param name 编码名称
	 * @return 当前对象
	*/
	public CodeRule setName(String name) {
		this.name=name;
		return this;
	}
	
	/**
	 * 获得 业务模块<br>
	 * 业务模块
	 * @return 业务模块
	*/
	public String getModuleId() {
		return moduleId;
	}
	
	/**
	 * 设置 业务模块
	 * @param moduleId 业务模块
	 * @return 当前对象
	*/
	public CodeRule setModuleId(String moduleId) {
		this.moduleId=moduleId;
		return this;
	}
	
	/**
	 * 获得 编码规则<br>
	 * 编码规则
	 * @return 编码规则
	*/
	public String getRule() {
		return rule;
	}
	
	/**
	 * 设置 编码规则
	 * @param rule 编码规则
	 * @return 当前对象
	*/
	public CodeRule setRule(String rule) {
		this.rule=rule;
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
	public CodeRule setNotes(String notes) {
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
	public CodeRule setCreateBy(String createBy) {
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
	public CodeRule setCreateTime(Date createTime) {
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
	public CodeRule setUpdateBy(String updateBy) {
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
	public CodeRule setUpdateTime(Date updateTime) {
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
	 * 设置 是否已删除
	 * @param deleted 是否已删除
	 * @return 当前对象
	*/
	public CodeRule setDeleted(Integer deleted) {
		this.deleted=deleted;
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
	public CodeRule setDeleteBy(String deleteBy) {
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
	public CodeRule setDeleteTime(Date deleteTime) {
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
	public CodeRule setVersion(Integer version) {
		this.version=version;
		return this;
	}
	
	/**
	 * 获得 关联模块<br>
	 * 关联模块
	 * @return 关联模块
	*/
	public Menu getModule() {
		return module;
	}
	
	/**
	 * 设置 关联模块
	 * @param module 关联模块
	 * @return 当前对象
	*/
	public CodeRule setModule(Menu module) {
		this.module=module;
		return this;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return CodeRule , 转换好的 CodeRule 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return CodeRule , 转换好的 PoJo 对象
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
	 * 将 Map 转换成 CodeRule
	 * @param codeRuleMap 包含实体信息的 Map 对象
	 * @return CodeRule , 转换好的的 CodeRule 对象
	*/
	@Transient
	public static CodeRule createFrom(Map<String,Object> codeRuleMap) {
		if(codeRuleMap==null) return null;
		CodeRule po = EntityContext.create(CodeRule.class, codeRuleMap);
		return po;
	}

	/**
	 * 将 Pojo 转换成 CodeRule
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return CodeRule , 转换好的的 CodeRule 对象
	*/
	@Transient
	public static CodeRule createFrom(Object pojo) {
		if(pojo==null) return null;
		CodeRule po = EntityContext.create(CodeRule.class,pojo);
		return po;
	}

	/**
	 * 创建一个 CodeRule，等同于 new
	 * @return CodeRule 对象
	*/
	@Transient
	public static CodeRule create() {
		return EntityContext.create(CodeRule.class);
	}
}