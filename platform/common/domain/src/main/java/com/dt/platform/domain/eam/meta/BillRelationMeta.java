package com.dt.platform.domain.eam.meta;

import com.github.foxnic.api.bean.BeanProperty;
import com.dt.platform.domain.eam.BillRelation;
import java.util.Date;



/**
 * @author 金杰 , maillank@qq.com
 * @since 2022-06-24 19:16:17
 * @sign 77CE9AFA213BB5A240A1236ECACEA72E
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

public class BillRelationMeta {
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final String ID="id";
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.BillRelation,java.lang.String> ID_PROP = new BeanProperty(com.dt.platform.domain.eam.BillRelation.class ,ID, java.lang.String.class, "主键", "主键", java.lang.String.class, null);
	
	/**
	 * 主节点 , 类型: java.lang.String
	*/
	public static final String MASTER_ID="masterId";
	
	/**
	 * 主节点 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.BillRelation,java.lang.String> MASTER_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.BillRelation.class ,MASTER_ID, java.lang.String.class, "主节点", "主节点", java.lang.String.class, null);
	
	/**
	 * 从节点 , 类型: java.lang.String
	*/
	public static final String SLAVER_ID="slaverId";
	
	/**
	 * 从节点 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.BillRelation,java.lang.String> SLAVER_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.BillRelation.class ,SLAVER_ID, java.lang.String.class, "从节点", "从节点", java.lang.String.class, null);
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final String CREATE_BY="createBy";
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.BillRelation,java.lang.String> CREATE_BY_PROP = new BeanProperty(com.dt.platform.domain.eam.BillRelation.class ,CREATE_BY, java.lang.String.class, "创建人ID", "创建人ID", java.lang.String.class, null);
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final String CREATE_TIME="createTime";
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.BillRelation,java.util.Date> CREATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.eam.BillRelation.class ,CREATE_TIME, java.util.Date.class, "创建时间", "创建时间", java.util.Date.class, null);
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final String UPDATE_BY="updateBy";
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.BillRelation,java.lang.String> UPDATE_BY_PROP = new BeanProperty(com.dt.platform.domain.eam.BillRelation.class ,UPDATE_BY, java.lang.String.class, "修改人ID", "修改人ID", java.lang.String.class, null);
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final String UPDATE_TIME="updateTime";
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.BillRelation,java.util.Date> UPDATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.eam.BillRelation.class ,UPDATE_TIME, java.util.Date.class, "修改时间", "修改时间", java.util.Date.class, null);
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final String DELETED="deleted";
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.BillRelation,java.lang.Integer> DELETED_PROP = new BeanProperty(com.dt.platform.domain.eam.BillRelation.class ,DELETED, java.lang.Integer.class, "是否已删除", "是否已删除", java.lang.Integer.class, null);
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final String DELETE_BY="deleteBy";
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.BillRelation,java.lang.String> DELETE_BY_PROP = new BeanProperty(com.dt.platform.domain.eam.BillRelation.class ,DELETE_BY, java.lang.String.class, "删除人ID", "删除人ID", java.lang.String.class, null);
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final String DELETE_TIME="deleteTime";
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.BillRelation,java.util.Date> DELETE_TIME_PROP = new BeanProperty(com.dt.platform.domain.eam.BillRelation.class ,DELETE_TIME, java.util.Date.class, "删除时间", "删除时间", java.util.Date.class, null);
	
	/**
	 * 数据版本号 , 类型: java.lang.Integer
	*/
	public static final String VERSION="version";
	
	/**
	 * 数据版本号 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.BillRelation,java.lang.Integer> VERSION_PROP = new BeanProperty(com.dt.platform.domain.eam.BillRelation.class ,VERSION, java.lang.Integer.class, "数据版本号", "数据版本号", java.lang.Integer.class, null);
	
	/**
	 * 全部属性清单
	*/
	public static final String[] $PROPS={ ID , MASTER_ID , SLAVER_ID , CREATE_BY , CREATE_TIME , UPDATE_BY , UPDATE_TIME , DELETED , DELETE_BY , DELETE_TIME , VERSION };
	
	/**
	 * 代理类
	*/
	public static class $$proxy$$ extends com.dt.platform.domain.eam.BillRelation {

		private static final long serialVersionUID = 1L;

		
		/**
		 * 设置 主键
		 * @param id 主键
		 * @return 当前对象
		*/
		public BillRelation setId(String id) {
			super.change(ID,super.getId(),id);
			super.setId(id);
			return this;
		}
		
		/**
		 * 设置 主节点
		 * @param masterId 主节点
		 * @return 当前对象
		*/
		public BillRelation setMasterId(String masterId) {
			super.change(MASTER_ID,super.getMasterId(),masterId);
			super.setMasterId(masterId);
			return this;
		}
		
		/**
		 * 设置 从节点
		 * @param slaverId 从节点
		 * @return 当前对象
		*/
		public BillRelation setSlaverId(String slaverId) {
			super.change(SLAVER_ID,super.getSlaverId(),slaverId);
			super.setSlaverId(slaverId);
			return this;
		}
		
		/**
		 * 设置 创建人ID
		 * @param createBy 创建人ID
		 * @return 当前对象
		*/
		public BillRelation setCreateBy(String createBy) {
			super.change(CREATE_BY,super.getCreateBy(),createBy);
			super.setCreateBy(createBy);
			return this;
		}
		
		/**
		 * 设置 创建时间
		 * @param createTime 创建时间
		 * @return 当前对象
		*/
		public BillRelation setCreateTime(Date createTime) {
			super.change(CREATE_TIME,super.getCreateTime(),createTime);
			super.setCreateTime(createTime);
			return this;
		}
		
		/**
		 * 设置 修改人ID
		 * @param updateBy 修改人ID
		 * @return 当前对象
		*/
		public BillRelation setUpdateBy(String updateBy) {
			super.change(UPDATE_BY,super.getUpdateBy(),updateBy);
			super.setUpdateBy(updateBy);
			return this;
		}
		
		/**
		 * 设置 修改时间
		 * @param updateTime 修改时间
		 * @return 当前对象
		*/
		public BillRelation setUpdateTime(Date updateTime) {
			super.change(UPDATE_TIME,super.getUpdateTime(),updateTime);
			super.setUpdateTime(updateTime);
			return this;
		}
		
		/**
		 * 设置 是否已删除
		 * @param deleted 是否已删除
		 * @return 当前对象
		*/
		public BillRelation setDeleted(Integer deleted) {
			super.change(DELETED,super.getDeleted(),deleted);
			super.setDeleted(deleted);
			return this;
		}
		
		/**
		 * 设置 删除人ID
		 * @param deleteBy 删除人ID
		 * @return 当前对象
		*/
		public BillRelation setDeleteBy(String deleteBy) {
			super.change(DELETE_BY,super.getDeleteBy(),deleteBy);
			super.setDeleteBy(deleteBy);
			return this;
		}
		
		/**
		 * 设置 删除时间
		 * @param deleteTime 删除时间
		 * @return 当前对象
		*/
		public BillRelation setDeleteTime(Date deleteTime) {
			super.change(DELETE_TIME,super.getDeleteTime(),deleteTime);
			super.setDeleteTime(deleteTime);
			return this;
		}
		
		/**
		 * 设置 数据版本号
		 * @param version 数据版本号
		 * @return 当前对象
		*/
		public BillRelation setVersion(Integer version) {
			super.change(VERSION,super.getVersion(),version);
			super.setVersion(version);
			return this;
		}
	}
}