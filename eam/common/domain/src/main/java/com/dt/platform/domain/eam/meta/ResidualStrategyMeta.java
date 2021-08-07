package com.dt.platform.domain.eam.meta;

import com.github.foxnic.api.bean.BeanProperty;
import com.dt.platform.domain.eam.ResidualStrategy;
import java.math.BigDecimal;
import java.util.Date;



/**
 * @author 金杰 , maillank@qq.com
 * @since 2021-08-06 09:13:21
 * @sign 42BAAF943FDCE93ECDB73800DA62D5B7
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

public class ResidualStrategyMeta {
	
	/**
	 * 主键
	*/
	public static final String ID="id";
	
	/**
	 * 主键
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.ResidualStrategy,java.lang.String> ID_PROP=new BeanProperty(com.dt.platform.domain.eam.ResidualStrategy.class ,ID, java.lang.String.class, "主键", "主键", java.lang.String.class, null);
	
	/**
	 * 名称
	*/
	public static final String NAME="name";
	
	/**
	 * 名称
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.ResidualStrategy,java.lang.String> NAME_PROP=new BeanProperty(com.dt.platform.domain.eam.ResidualStrategy.class ,NAME, java.lang.String.class, "名称", "名称", java.lang.String.class, null);
	
	/**
	 * 状态
	*/
	public static final String STATUS="status";
	
	/**
	 * 状态
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.ResidualStrategy,java.lang.String> STATUS_PROP=new BeanProperty(com.dt.platform.domain.eam.ResidualStrategy.class ,STATUS, java.lang.String.class, "状态", "状态", java.lang.String.class, null);
	
	/**
	 * 策略描述
	*/
	public static final String STRATEGY_DESCRIBE="strategyDescribe";
	
	/**
	 * 策略描述
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.ResidualStrategy,java.lang.String> STRATEGY_DESCRIBE_PROP=new BeanProperty(com.dt.platform.domain.eam.ResidualStrategy.class ,STRATEGY_DESCRIBE, java.lang.String.class, "策略描述", "策略描述", java.lang.String.class, null);
	
	/**
	 * 残值率 , -1则使用资产设置的残值
	*/
	public static final String RESIDUALVALUE_RATE="residualvalueRate";
	
	/**
	 * 残值率 , -1则使用资产设置的残值
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.ResidualStrategy,java.math.BigDecimal> RESIDUALVALUE_RATE_PROP=new BeanProperty(com.dt.platform.domain.eam.ResidualStrategy.class ,RESIDUALVALUE_RATE, java.math.BigDecimal.class, "残值率", "-1则使用资产设置的残值", java.math.BigDecimal.class, null);
	
	/**
	 * 折旧率
	*/
	public static final String DEPRECIATION_RATE="depreciationRate";
	
	/**
	 * 折旧率
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.ResidualStrategy,java.math.BigDecimal> DEPRECIATION_RATE_PROP=new BeanProperty(com.dt.platform.domain.eam.ResidualStrategy.class ,DEPRECIATION_RATE, java.math.BigDecimal.class, "折旧率", "折旧率", java.math.BigDecimal.class, null);
	
	/**
	 * 设置值
	*/
	public static final String VALUE="value";
	
	/**
	 * 设置值
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.ResidualStrategy,java.lang.String> VALUE_PROP=new BeanProperty(com.dt.platform.domain.eam.ResidualStrategy.class ,VALUE, java.lang.String.class, "设置值", "设置值", java.lang.String.class, null);
	
	/**
	 * 备注
	*/
	public static final String NOTES="notes";
	
	/**
	 * 备注
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.ResidualStrategy,java.lang.String> NOTES_PROP=new BeanProperty(com.dt.platform.domain.eam.ResidualStrategy.class ,NOTES, java.lang.String.class, "备注", "备注", java.lang.String.class, null);
	
	/**
	 * 创建人ID
	*/
	public static final String CREATE_BY="createBy";
	
	/**
	 * 创建人ID
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.ResidualStrategy,java.lang.String> CREATE_BY_PROP=new BeanProperty(com.dt.platform.domain.eam.ResidualStrategy.class ,CREATE_BY, java.lang.String.class, "创建人ID", "创建人ID", java.lang.String.class, null);
	
	/**
	 * 创建时间
	*/
	public static final String CREATE_TIME="createTime";
	
	/**
	 * 创建时间
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.ResidualStrategy,java.util.Date> CREATE_TIME_PROP=new BeanProperty(com.dt.platform.domain.eam.ResidualStrategy.class ,CREATE_TIME, java.util.Date.class, "创建时间", "创建时间", java.util.Date.class, null);
	
	/**
	 * 修改人ID
	*/
	public static final String UPDATE_BY="updateBy";
	
	/**
	 * 修改人ID
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.ResidualStrategy,java.lang.String> UPDATE_BY_PROP=new BeanProperty(com.dt.platform.domain.eam.ResidualStrategy.class ,UPDATE_BY, java.lang.String.class, "修改人ID", "修改人ID", java.lang.String.class, null);
	
	/**
	 * 修改时间
	*/
	public static final String UPDATE_TIME="updateTime";
	
	/**
	 * 修改时间
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.ResidualStrategy,java.util.Date> UPDATE_TIME_PROP=new BeanProperty(com.dt.platform.domain.eam.ResidualStrategy.class ,UPDATE_TIME, java.util.Date.class, "修改时间", "修改时间", java.util.Date.class, null);
	
	/**
	 * 是否已删除
	*/
	public static final String DELETED="deleted";
	
	/**
	 * 是否已删除
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.ResidualStrategy,java.lang.Integer> DELETED_PROP=new BeanProperty(com.dt.platform.domain.eam.ResidualStrategy.class ,DELETED, java.lang.Integer.class, "是否已删除", "是否已删除", java.lang.Integer.class, null);
	
	/**
	 * 删除人ID
	*/
	public static final String DELETE_BY="deleteBy";
	
	/**
	 * 删除人ID
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.ResidualStrategy,java.lang.String> DELETE_BY_PROP=new BeanProperty(com.dt.platform.domain.eam.ResidualStrategy.class ,DELETE_BY, java.lang.String.class, "删除人ID", "删除人ID", java.lang.String.class, null);
	
	/**
	 * 删除时间
	*/
	public static final String DELETE_TIME="deleteTime";
	
	/**
	 * 删除时间
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.ResidualStrategy,java.util.Date> DELETE_TIME_PROP=new BeanProperty(com.dt.platform.domain.eam.ResidualStrategy.class ,DELETE_TIME, java.util.Date.class, "删除时间", "删除时间", java.util.Date.class, null);
	
	/**
	 * version
	*/
	public static final String VERSION="version";
	
	/**
	 * version
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.ResidualStrategy,java.lang.Integer> VERSION_PROP=new BeanProperty(com.dt.platform.domain.eam.ResidualStrategy.class ,VERSION, java.lang.Integer.class, "version", "version", java.lang.Integer.class, null);
	
	/**
	 * 全部属性清单
	*/
	public static final String[] $PROPS={ ID , NAME , STATUS , STRATEGY_DESCRIBE , RESIDUALVALUE_RATE , DEPRECIATION_RATE , VALUE , NOTES , CREATE_BY , CREATE_TIME , UPDATE_BY , UPDATE_TIME , DELETED , DELETE_BY , DELETE_TIME , VERSION };
	
	/**
	 * 代理类
	*/
	public static class $$proxy$$ extends com.dt.platform.domain.eam.ResidualStrategy {

		private static final long serialVersionUID = 1L;

		
		/**
		 * 设置 主键
		 * @param id 主键
		 * @return 当前对象
		*/
		public ResidualStrategy setId(String id) {
			super.change(ID,super.getId(),id);
			super.setId(id);
			return this;
		}
		
		/**
		 * 设置 名称
		 * @param name 名称
		 * @return 当前对象
		*/
		public ResidualStrategy setName(String name) {
			super.change(NAME,super.getName(),name);
			super.setName(name);
			return this;
		}
		
		/**
		 * 设置 状态
		 * @param status 状态
		 * @return 当前对象
		*/
		public ResidualStrategy setStatus(String status) {
			super.change(STATUS,super.getStatus(),status);
			super.setStatus(status);
			return this;
		}
		
		/**
		 * 设置 策略描述
		 * @param strategyDescribe 策略描述
		 * @return 当前对象
		*/
		public ResidualStrategy setStrategyDescribe(String strategyDescribe) {
			super.change(STRATEGY_DESCRIBE,super.getStrategyDescribe(),strategyDescribe);
			super.setStrategyDescribe(strategyDescribe);
			return this;
		}
		
		/**
		 * 设置 残值率
		 * @param residualvalueRate 残值率
		 * @return 当前对象
		*/
		public ResidualStrategy setResidualvalueRate(BigDecimal residualvalueRate) {
			super.change(RESIDUALVALUE_RATE,super.getResidualvalueRate(),residualvalueRate);
			super.setResidualvalueRate(residualvalueRate);
			return this;
		}
		
		/**
		 * 设置 折旧率
		 * @param depreciationRate 折旧率
		 * @return 当前对象
		*/
		public ResidualStrategy setDepreciationRate(BigDecimal depreciationRate) {
			super.change(DEPRECIATION_RATE,super.getDepreciationRate(),depreciationRate);
			super.setDepreciationRate(depreciationRate);
			return this;
		}
		
		/**
		 * 设置 设置值
		 * @param value 设置值
		 * @return 当前对象
		*/
		public ResidualStrategy setValue(String value) {
			super.change(VALUE,super.getValue(),value);
			super.setValue(value);
			return this;
		}
		
		/**
		 * 设置 备注
		 * @param notes 备注
		 * @return 当前对象
		*/
		public ResidualStrategy setNotes(String notes) {
			super.change(NOTES,super.getNotes(),notes);
			super.setNotes(notes);
			return this;
		}
		
		/**
		 * 设置 创建人ID
		 * @param createBy 创建人ID
		 * @return 当前对象
		*/
		public ResidualStrategy setCreateBy(String createBy) {
			super.change(CREATE_BY,super.getCreateBy(),createBy);
			super.setCreateBy(createBy);
			return this;
		}
		
		/**
		 * 设置 创建时间
		 * @param createTime 创建时间
		 * @return 当前对象
		*/
		public ResidualStrategy setCreateTime(Date createTime) {
			super.change(CREATE_TIME,super.getCreateTime(),createTime);
			super.setCreateTime(createTime);
			return this;
		}
		
		/**
		 * 设置 修改人ID
		 * @param updateBy 修改人ID
		 * @return 当前对象
		*/
		public ResidualStrategy setUpdateBy(String updateBy) {
			super.change(UPDATE_BY,super.getUpdateBy(),updateBy);
			super.setUpdateBy(updateBy);
			return this;
		}
		
		/**
		 * 设置 修改时间
		 * @param updateTime 修改时间
		 * @return 当前对象
		*/
		public ResidualStrategy setUpdateTime(Date updateTime) {
			super.change(UPDATE_TIME,super.getUpdateTime(),updateTime);
			super.setUpdateTime(updateTime);
			return this;
		}
		
		/**
		 * 设置 是否已删除
		 * @param deleted 是否已删除
		 * @return 当前对象
		*/
		public ResidualStrategy setDeleted(Integer deleted) {
			super.change(DELETED,super.getDeleted(),deleted);
			super.setDeleted(deleted);
			return this;
		}
		
		/**
		 * 设置 删除人ID
		 * @param deleteBy 删除人ID
		 * @return 当前对象
		*/
		public ResidualStrategy setDeleteBy(String deleteBy) {
			super.change(DELETE_BY,super.getDeleteBy(),deleteBy);
			super.setDeleteBy(deleteBy);
			return this;
		}
		
		/**
		 * 设置 删除时间
		 * @param deleteTime 删除时间
		 * @return 当前对象
		*/
		public ResidualStrategy setDeleteTime(Date deleteTime) {
			super.change(DELETE_TIME,super.getDeleteTime(),deleteTime);
			super.setDeleteTime(deleteTime);
			return this;
		}
		
		/**
		 * 设置 version
		 * @param version version
		 * @return 当前对象
		*/
		public ResidualStrategy setVersion(Integer version) {
			super.change(VERSION,super.getVersion(),version);
			super.setVersion(version);
			return this;
		}
	}
}