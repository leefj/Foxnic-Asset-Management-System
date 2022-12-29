package com.dt.platform.domain.hr.meta;

import com.github.foxnic.api.bean.BeanProperty;
import com.dt.platform.domain.hr.PersonContract;
import java.util.Date;
import org.github.foxnic.web.domain.system.DictItem;
import javax.persistence.Transient;



/**
 * @author 金杰 , maillank@qq.com
 * @since 2022-12-29 15:52:03
 * @sign 69106E93419AB85D5AEC800FB78E5E93
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

public class PersonContractMeta {
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final String ID="id";
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> ID_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,ID, java.lang.String.class, "主键", "主键", java.lang.String.class, null);
	
	/**
	 * 人员 , 类型: java.lang.String
	*/
	public static final String PERSON_ID="personId";
	
	/**
	 * 人员 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> PERSON_ID_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,PERSON_ID, java.lang.String.class, "人员", "人员", java.lang.String.class, null);
	
	/**
	 * 状态 , 类型: java.lang.String
	*/
	public static final String STATUS="status";
	
	/**
	 * 状态 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> STATUS_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,STATUS, java.lang.String.class, "状态", "状态", java.lang.String.class, null);
	
	/**
	 * 合同周期 , 类型: java.lang.String
	*/
	public static final String CONTRACT_DURATION="contractDuration";
	
	/**
	 * 合同周期 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> CONTRACT_DURATION_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,CONTRACT_DURATION, java.lang.String.class, "合同周期", "合同周期", java.lang.String.class, null);
	
	/**
	 * 合同开始时间 , 类型: java.util.Date
	*/
	public static final String CONTRACT_START_DATE="contractStartDate";
	
	/**
	 * 合同开始时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.util.Date> CONTRACT_START_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,CONTRACT_START_DATE, java.util.Date.class, "合同开始时间", "合同开始时间", java.util.Date.class, null);
	
	/**
	 * 合同结束时间 , 类型: java.util.Date
	*/
	public static final String CONTRACT_FINISH_DATE="contractFinishDate";
	
	/**
	 * 合同结束时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.util.Date> CONTRACT_FINISH_DATE_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,CONTRACT_FINISH_DATE, java.util.Date.class, "合同结束时间", "合同结束时间", java.util.Date.class, null);
	
	/**
	 * 合同 , 类型: java.lang.String
	*/
	public static final String FILE_ID="fileId";
	
	/**
	 * 合同 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> FILE_ID_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,FILE_ID, java.lang.String.class, "合同", "合同", java.lang.String.class, null);
	
	/**
	 * 备注 , 类型: java.lang.String
	*/
	public static final String NOTES="notes";
	
	/**
	 * 备注 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> NOTES_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,NOTES, java.lang.String.class, "备注", "备注", java.lang.String.class, null);
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final String CREATE_BY="createBy";
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> CREATE_BY_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,CREATE_BY, java.lang.String.class, "创建人ID", "创建人ID", java.lang.String.class, null);
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final String CREATE_TIME="createTime";
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.util.Date> CREATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,CREATE_TIME, java.util.Date.class, "创建时间", "创建时间", java.util.Date.class, null);
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final String UPDATE_BY="updateBy";
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> UPDATE_BY_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,UPDATE_BY, java.lang.String.class, "修改人ID", "修改人ID", java.lang.String.class, null);
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final String UPDATE_TIME="updateTime";
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.util.Date> UPDATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,UPDATE_TIME, java.util.Date.class, "修改时间", "修改时间", java.util.Date.class, null);
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final String DELETED="deleted";
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.Integer> DELETED_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,DELETED, java.lang.Integer.class, "是否已删除", "是否已删除", java.lang.Integer.class, null);
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final String DELETE_BY="deleteBy";
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.String> DELETE_BY_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,DELETE_BY, java.lang.String.class, "删除人ID", "删除人ID", java.lang.String.class, null);
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final String DELETE_TIME="deleteTime";
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.util.Date> DELETE_TIME_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,DELETE_TIME, java.util.Date.class, "删除时间", "删除时间", java.util.Date.class, null);
	
	/**
	 * 数据版本号 , 类型: java.lang.Integer
	*/
	public static final String VERSION="version";
	
	/**
	 * 数据版本号 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,java.lang.Integer> VERSION_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,VERSION, java.lang.Integer.class, "数据版本号", "数据版本号", java.lang.Integer.class, null);
	
	/**
	 * contractDurationDict , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final String CONTRACT_DURATION_DICT="contractDurationDict";
	
	/**
	 * contractDurationDict , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final BeanProperty<com.dt.platform.domain.hr.PersonContract,org.github.foxnic.web.domain.system.DictItem> CONTRACT_DURATION_DICT_PROP = new BeanProperty(com.dt.platform.domain.hr.PersonContract.class ,CONTRACT_DURATION_DICT, org.github.foxnic.web.domain.system.DictItem.class, "contractDurationDict", "contractDurationDict", org.github.foxnic.web.domain.system.DictItem.class, null);
	
	/**
	 * 全部属性清单
	*/
	public static final String[] $PROPS={ ID , PERSON_ID , STATUS , CONTRACT_DURATION , CONTRACT_START_DATE , CONTRACT_FINISH_DATE , FILE_ID , NOTES , CREATE_BY , CREATE_TIME , UPDATE_BY , UPDATE_TIME , DELETED , DELETE_BY , DELETE_TIME , VERSION , CONTRACT_DURATION_DICT };
	
	/**
	 * 代理类
	*/
	public static class $$proxy$$ extends com.dt.platform.domain.hr.PersonContract {

		private static final long serialVersionUID = 1L;

		
		/**
		 * 设置 主键
		 * @param id 主键
		 * @return 当前对象
		*/
		public PersonContract setId(String id) {
			super.change(ID,super.getId(),id);
			super.setId(id);
			return this;
		}
		
		/**
		 * 设置 人员
		 * @param personId 人员
		 * @return 当前对象
		*/
		public PersonContract setPersonId(String personId) {
			super.change(PERSON_ID,super.getPersonId(),personId);
			super.setPersonId(personId);
			return this;
		}
		
		/**
		 * 设置 状态
		 * @param status 状态
		 * @return 当前对象
		*/
		public PersonContract setStatus(String status) {
			super.change(STATUS,super.getStatus(),status);
			super.setStatus(status);
			return this;
		}
		
		/**
		 * 设置 合同周期
		 * @param contractDuration 合同周期
		 * @return 当前对象
		*/
		public PersonContract setContractDuration(String contractDuration) {
			super.change(CONTRACT_DURATION,super.getContractDuration(),contractDuration);
			super.setContractDuration(contractDuration);
			return this;
		}
		
		/**
		 * 设置 合同开始时间
		 * @param contractStartDate 合同开始时间
		 * @return 当前对象
		*/
		public PersonContract setContractStartDate(Date contractStartDate) {
			super.change(CONTRACT_START_DATE,super.getContractStartDate(),contractStartDate);
			super.setContractStartDate(contractStartDate);
			return this;
		}
		
		/**
		 * 设置 合同结束时间
		 * @param contractFinishDate 合同结束时间
		 * @return 当前对象
		*/
		public PersonContract setContractFinishDate(Date contractFinishDate) {
			super.change(CONTRACT_FINISH_DATE,super.getContractFinishDate(),contractFinishDate);
			super.setContractFinishDate(contractFinishDate);
			return this;
		}
		
		/**
		 * 设置 合同
		 * @param fileId 合同
		 * @return 当前对象
		*/
		public PersonContract setFileId(String fileId) {
			super.change(FILE_ID,super.getFileId(),fileId);
			super.setFileId(fileId);
			return this;
		}
		
		/**
		 * 设置 备注
		 * @param notes 备注
		 * @return 当前对象
		*/
		public PersonContract setNotes(String notes) {
			super.change(NOTES,super.getNotes(),notes);
			super.setNotes(notes);
			return this;
		}
		
		/**
		 * 设置 创建人ID
		 * @param createBy 创建人ID
		 * @return 当前对象
		*/
		public PersonContract setCreateBy(String createBy) {
			super.change(CREATE_BY,super.getCreateBy(),createBy);
			super.setCreateBy(createBy);
			return this;
		}
		
		/**
		 * 设置 创建时间
		 * @param createTime 创建时间
		 * @return 当前对象
		*/
		public PersonContract setCreateTime(Date createTime) {
			super.change(CREATE_TIME,super.getCreateTime(),createTime);
			super.setCreateTime(createTime);
			return this;
		}
		
		/**
		 * 设置 修改人ID
		 * @param updateBy 修改人ID
		 * @return 当前对象
		*/
		public PersonContract setUpdateBy(String updateBy) {
			super.change(UPDATE_BY,super.getUpdateBy(),updateBy);
			super.setUpdateBy(updateBy);
			return this;
		}
		
		/**
		 * 设置 修改时间
		 * @param updateTime 修改时间
		 * @return 当前对象
		*/
		public PersonContract setUpdateTime(Date updateTime) {
			super.change(UPDATE_TIME,super.getUpdateTime(),updateTime);
			super.setUpdateTime(updateTime);
			return this;
		}
		
		/**
		 * 设置 是否已删除
		 * @param deleted 是否已删除
		 * @return 当前对象
		*/
		public PersonContract setDeleted(Integer deleted) {
			super.change(DELETED,super.getDeleted(),deleted);
			super.setDeleted(deleted);
			return this;
		}
		
		/**
		 * 设置 删除人ID
		 * @param deleteBy 删除人ID
		 * @return 当前对象
		*/
		public PersonContract setDeleteBy(String deleteBy) {
			super.change(DELETE_BY,super.getDeleteBy(),deleteBy);
			super.setDeleteBy(deleteBy);
			return this;
		}
		
		/**
		 * 设置 删除时间
		 * @param deleteTime 删除时间
		 * @return 当前对象
		*/
		public PersonContract setDeleteTime(Date deleteTime) {
			super.change(DELETE_TIME,super.getDeleteTime(),deleteTime);
			super.setDeleteTime(deleteTime);
			return this;
		}
		
		/**
		 * 设置 数据版本号
		 * @param version 数据版本号
		 * @return 当前对象
		*/
		public PersonContract setVersion(Integer version) {
			super.change(VERSION,super.getVersion(),version);
			super.setVersion(version);
			return this;
		}
		
		/**
		 * 设置 contractDurationDict
		 * @param contractDurationDict contractDurationDict
		 * @return 当前对象
		*/
		public PersonContract setContractDurationDict(DictItem contractDurationDict) {
			super.change(CONTRACT_DURATION_DICT,super.getContractDurationDict(),contractDurationDict);
			super.setContractDurationDict(contractDurationDict);
			return this;
		}

		/**
		 * 克隆当前对象
		*/
		@Transient
		public PersonContract clone() {
			return duplicate(true);
		}

		/**
		 * 复制当前对象
		 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
		*/
		@Transient
		public PersonContract duplicate(boolean all) {
			$$proxy$$ inst=new $$proxy$$();
			inst.setNotes(this.getNotes());
			inst.setContractDuration(this.getContractDuration());
			inst.setContractStartDate(this.getContractStartDate());
			inst.setContractFinishDate(this.getContractFinishDate());
			inst.setUpdateTime(this.getUpdateTime());
			inst.setVersion(this.getVersion());
			inst.setCreateBy(this.getCreateBy());
			inst.setDeleted(this.getDeleted());
			inst.setCreateTime(this.getCreateTime());
			inst.setUpdateBy(this.getUpdateBy());
			inst.setDeleteTime(this.getDeleteTime());
			inst.setDeleteBy(this.getDeleteBy());
			inst.setPersonId(this.getPersonId());
			inst.setId(this.getId());
			inst.setStatus(this.getStatus());
			inst.setFileId(this.getFileId());
			if(all) {
				inst.setContractDurationDict(this.getContractDurationDict());
			}
			inst.clearModifies();
			return inst;
		}

	}
}