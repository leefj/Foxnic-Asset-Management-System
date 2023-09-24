package com.dt.platform.domain.oa.meta;

import com.github.foxnic.api.bean.BeanProperty;
import com.dt.platform.domain.oa.NetdiskMenu;
import java.util.Date;
import javax.persistence.Transient;



/**
 * @author 金杰 , maillank@qq.com
 * @since 2023-09-18 14:20:12
 * @sign 056DFD88C97765EEE666683E3C546D05
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

public class NetdiskMenuMeta {
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final String ID="id";
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.oa.NetdiskMenu,java.lang.String> ID_PROP = new BeanProperty(com.dt.platform.domain.oa.NetdiskMenu.class ,ID, java.lang.String.class, "主键", "主键", java.lang.String.class, null);
	
	/**
	 * 编码 , 类型: java.lang.String
	*/
	public static final String CODE="code";
	
	/**
	 * 编码 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.oa.NetdiskMenu,java.lang.String> CODE_PROP = new BeanProperty(com.dt.platform.domain.oa.NetdiskMenu.class ,CODE, java.lang.String.class, "编码", "编码", java.lang.String.class, null);
	
	/**
	 * 名称 , 类型: java.lang.String
	*/
	public static final String NAME="name";
	
	/**
	 * 名称 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.oa.NetdiskMenu,java.lang.String> NAME_PROP = new BeanProperty(com.dt.platform.domain.oa.NetdiskMenu.class ,NAME, java.lang.String.class, "名称", "名称", java.lang.String.class, null);
	
	/**
	 * 图标 , 类型: java.lang.String
	*/
	public static final String ICON_CODE="iconCode";
	
	/**
	 * 图标 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.oa.NetdiskMenu,java.lang.String> ICON_CODE_PROP = new BeanProperty(com.dt.platform.domain.oa.NetdiskMenu.class ,ICON_CODE, java.lang.String.class, "图标", "图标", java.lang.String.class, null);
	
	/**
	 * 类型 , 类型: java.lang.String
	*/
	public static final String TYPE="type";
	
	/**
	 * 类型 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.oa.NetdiskMenu,java.lang.String> TYPE_PROP = new BeanProperty(com.dt.platform.domain.oa.NetdiskMenu.class ,TYPE, java.lang.String.class, "类型", "类型", java.lang.String.class, null);
	
	/**
	 * 是否显示 , Y|N , 类型: java.lang.String
	*/
	public static final String SHOW_VALUE="showValue";
	
	/**
	 * 是否显示 , Y|N , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.oa.NetdiskMenu,java.lang.String> SHOW_VALUE_PROP = new BeanProperty(com.dt.platform.domain.oa.NetdiskMenu.class ,SHOW_VALUE, java.lang.String.class, "是否显示", "Y|N", java.lang.String.class, null);
	
	/**
	 * 排序 , 类型: java.lang.Integer
	*/
	public static final String SORT_VALUE="sortValue";
	
	/**
	 * 排序 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.oa.NetdiskMenu,java.lang.Integer> SORT_VALUE_PROP = new BeanProperty(com.dt.platform.domain.oa.NetdiskMenu.class ,SORT_VALUE, java.lang.Integer.class, "排序", "排序", java.lang.Integer.class, null);
	
	/**
	 * 上一级 , 类型: java.lang.String
	*/
	public static final String PARENT_ID="parentId";
	
	/**
	 * 上一级 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.oa.NetdiskMenu,java.lang.String> PARENT_ID_PROP = new BeanProperty(com.dt.platform.domain.oa.NetdiskMenu.class ,PARENT_ID, java.lang.String.class, "上一级", "上一级", java.lang.String.class, null);
	
	/**
	 * 版本 , 类型: java.lang.Integer
	*/
	public static final String VERSION="version";
	
	/**
	 * 版本 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.oa.NetdiskMenu,java.lang.Integer> VERSION_PROP = new BeanProperty(com.dt.platform.domain.oa.NetdiskMenu.class ,VERSION, java.lang.Integer.class, "版本", "版本", java.lang.Integer.class, null);
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final String CREATE_BY="createBy";
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.oa.NetdiskMenu,java.lang.String> CREATE_BY_PROP = new BeanProperty(com.dt.platform.domain.oa.NetdiskMenu.class ,CREATE_BY, java.lang.String.class, "创建人ID", "创建人ID", java.lang.String.class, null);
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final String CREATE_TIME="createTime";
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.oa.NetdiskMenu,java.util.Date> CREATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.oa.NetdiskMenu.class ,CREATE_TIME, java.util.Date.class, "创建时间", "创建时间", java.util.Date.class, null);
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final String UPDATE_BY="updateBy";
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.oa.NetdiskMenu,java.lang.String> UPDATE_BY_PROP = new BeanProperty(com.dt.platform.domain.oa.NetdiskMenu.class ,UPDATE_BY, java.lang.String.class, "修改人ID", "修改人ID", java.lang.String.class, null);
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final String UPDATE_TIME="updateTime";
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.oa.NetdiskMenu,java.util.Date> UPDATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.oa.NetdiskMenu.class ,UPDATE_TIME, java.util.Date.class, "修改时间", "修改时间", java.util.Date.class, null);
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final String DELETED="deleted";
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.oa.NetdiskMenu,java.lang.Integer> DELETED_PROP = new BeanProperty(com.dt.platform.domain.oa.NetdiskMenu.class ,DELETED, java.lang.Integer.class, "是否已删除", "是否已删除", java.lang.Integer.class, null);
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final String DELETE_BY="deleteBy";
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.oa.NetdiskMenu,java.lang.String> DELETE_BY_PROP = new BeanProperty(com.dt.platform.domain.oa.NetdiskMenu.class ,DELETE_BY, java.lang.String.class, "删除人ID", "删除人ID", java.lang.String.class, null);
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final String DELETE_TIME="deleteTime";
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.oa.NetdiskMenu,java.util.Date> DELETE_TIME_PROP = new BeanProperty(com.dt.platform.domain.oa.NetdiskMenu.class ,DELETE_TIME, java.util.Date.class, "删除时间", "删除时间", java.util.Date.class, null);
	
	/**
	 * 租户 , 类型: java.lang.String
	*/
	public static final String TENANT_ID="tenantId";
	
	/**
	 * 租户 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.oa.NetdiskMenu,java.lang.String> TENANT_ID_PROP = new BeanProperty(com.dt.platform.domain.oa.NetdiskMenu.class ,TENANT_ID, java.lang.String.class, "租户", "租户", java.lang.String.class, null);
	
	/**
	 * 全部属性清单
	*/
	public static final String[] $PROPS={ ID , CODE , NAME , ICON_CODE , TYPE , SHOW_VALUE , SORT_VALUE , PARENT_ID , VERSION , CREATE_BY , CREATE_TIME , UPDATE_BY , UPDATE_TIME , DELETED , DELETE_BY , DELETE_TIME , TENANT_ID };
	
	/**
	 * 代理类
	*/
	public static class $$proxy$$ extends com.dt.platform.domain.oa.NetdiskMenu {

		private static final long serialVersionUID = 1L;

		
		/**
		 * 设置 主键
		 * @param id 主键
		 * @return 当前对象
		*/
		public NetdiskMenu setId(String id) {
			super.change(ID,super.getId(),id);
			super.setId(id);
			return this;
		}
		
		/**
		 * 设置 编码
		 * @param code 编码
		 * @return 当前对象
		*/
		public NetdiskMenu setCode(String code) {
			super.change(CODE,super.getCode(),code);
			super.setCode(code);
			return this;
		}
		
		/**
		 * 设置 名称
		 * @param name 名称
		 * @return 当前对象
		*/
		public NetdiskMenu setName(String name) {
			super.change(NAME,super.getName(),name);
			super.setName(name);
			return this;
		}
		
		/**
		 * 设置 图标
		 * @param iconCode 图标
		 * @return 当前对象
		*/
		public NetdiskMenu setIconCode(String iconCode) {
			super.change(ICON_CODE,super.getIconCode(),iconCode);
			super.setIconCode(iconCode);
			return this;
		}
		
		/**
		 * 设置 类型
		 * @param type 类型
		 * @return 当前对象
		*/
		public NetdiskMenu setType(String type) {
			super.change(TYPE,super.getType(),type);
			super.setType(type);
			return this;
		}
		
		/**
		 * 设置 是否显示
		 * @param showValue 是否显示
		 * @return 当前对象
		*/
		public NetdiskMenu setShowValue(String showValue) {
			super.change(SHOW_VALUE,super.getShowValue(),showValue);
			super.setShowValue(showValue);
			return this;
		}
		
		/**
		 * 设置 排序
		 * @param sortValue 排序
		 * @return 当前对象
		*/
		public NetdiskMenu setSortValue(Integer sortValue) {
			super.change(SORT_VALUE,super.getSortValue(),sortValue);
			super.setSortValue(sortValue);
			return this;
		}
		
		/**
		 * 设置 上一级
		 * @param parentId 上一级
		 * @return 当前对象
		*/
		public NetdiskMenu setParentId(String parentId) {
			super.change(PARENT_ID,super.getParentId(),parentId);
			super.setParentId(parentId);
			return this;
		}
		
		/**
		 * 设置 版本
		 * @param version 版本
		 * @return 当前对象
		*/
		public NetdiskMenu setVersion(Integer version) {
			super.change(VERSION,super.getVersion(),version);
			super.setVersion(version);
			return this;
		}
		
		/**
		 * 设置 创建人ID
		 * @param createBy 创建人ID
		 * @return 当前对象
		*/
		public NetdiskMenu setCreateBy(String createBy) {
			super.change(CREATE_BY,super.getCreateBy(),createBy);
			super.setCreateBy(createBy);
			return this;
		}
		
		/**
		 * 设置 创建时间
		 * @param createTime 创建时间
		 * @return 当前对象
		*/
		public NetdiskMenu setCreateTime(Date createTime) {
			super.change(CREATE_TIME,super.getCreateTime(),createTime);
			super.setCreateTime(createTime);
			return this;
		}
		
		/**
		 * 设置 修改人ID
		 * @param updateBy 修改人ID
		 * @return 当前对象
		*/
		public NetdiskMenu setUpdateBy(String updateBy) {
			super.change(UPDATE_BY,super.getUpdateBy(),updateBy);
			super.setUpdateBy(updateBy);
			return this;
		}
		
		/**
		 * 设置 修改时间
		 * @param updateTime 修改时间
		 * @return 当前对象
		*/
		public NetdiskMenu setUpdateTime(Date updateTime) {
			super.change(UPDATE_TIME,super.getUpdateTime(),updateTime);
			super.setUpdateTime(updateTime);
			return this;
		}
		
		/**
		 * 设置 是否已删除
		 * @param deleted 是否已删除
		 * @return 当前对象
		*/
		public NetdiskMenu setDeleted(Integer deleted) {
			super.change(DELETED,super.getDeleted(),deleted);
			super.setDeleted(deleted);
			return this;
		}
		
		/**
		 * 设置 删除人ID
		 * @param deleteBy 删除人ID
		 * @return 当前对象
		*/
		public NetdiskMenu setDeleteBy(String deleteBy) {
			super.change(DELETE_BY,super.getDeleteBy(),deleteBy);
			super.setDeleteBy(deleteBy);
			return this;
		}
		
		/**
		 * 设置 删除时间
		 * @param deleteTime 删除时间
		 * @return 当前对象
		*/
		public NetdiskMenu setDeleteTime(Date deleteTime) {
			super.change(DELETE_TIME,super.getDeleteTime(),deleteTime);
			super.setDeleteTime(deleteTime);
			return this;
		}
		
		/**
		 * 设置 租户
		 * @param tenantId 租户
		 * @return 当前对象
		*/
		public NetdiskMenu setTenantId(String tenantId) {
			super.change(TENANT_ID,super.getTenantId(),tenantId);
			super.setTenantId(tenantId);
			return this;
		}

		/**
		 * 克隆当前对象
		*/
		@Transient
		public NetdiskMenu clone() {
			return duplicate(true);
		}

		/**
		 * 复制当前对象
		 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
		*/
		@Transient
		public NetdiskMenu duplicate(boolean all) {
			$$proxy$$ inst=new $$proxy$$();
			inst.setCode(this.getCode());
			inst.setUpdateTime(this.getUpdateTime());
			inst.setType(this.getType());
			inst.setVersion(this.getVersion());
			inst.setParentId(this.getParentId());
			inst.setSortValue(this.getSortValue());
			inst.setShowValue(this.getShowValue());
			inst.setCreateBy(this.getCreateBy());
			inst.setDeleted(this.getDeleted());
			inst.setCreateTime(this.getCreateTime());
			inst.setUpdateBy(this.getUpdateBy());
			inst.setDeleteTime(this.getDeleteTime());
			inst.setName(this.getName());
			inst.setTenantId(this.getTenantId());
			inst.setDeleteBy(this.getDeleteBy());
			inst.setIconCode(this.getIconCode());
			inst.setId(this.getId());
			inst.clearModifies();
			return inst;
		}

	}
}