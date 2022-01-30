package com.dt.platform.domain.ops.meta;

import com.github.foxnic.api.bean.BeanProperty;
import com.dt.platform.domain.ops.MonitorNode;
import java.util.Date;
import com.dt.platform.domain.ops.MonitorNodeType;
import com.dt.platform.domain.ops.MonitorNodeSubtype;
import com.dt.platform.domain.ops.MonitorNodeHost;
import com.dt.platform.domain.ops.MonitorNodeValue;
import java.util.List;



/**
 * @author 金杰 , maillank@qq.com
 * @since 2022-01-31 06:41:56
 * @sign 33590C4E6A6FE1811173DAEC64E3F152
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

public class MonitorNodeMeta {
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final String ID="id";
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.lang.String> ID_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,ID, java.lang.String.class, "主键", "主键", java.lang.String.class, null);
	
	/**
	 * 父节点 , 类型: java.lang.String
	*/
	public static final String PID="pid";
	
	/**
	 * 父节点 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.lang.String> PID_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,PID, java.lang.String.class, "父节点", "父节点", java.lang.String.class, null);
	
	/**
	 * 类型 , 类型: java.lang.String
	*/
	public static final String TYPE="type";
	
	/**
	 * 类型 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.lang.String> TYPE_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,TYPE, java.lang.String.class, "类型", "类型", java.lang.String.class, null);
	
	/**
	 * 子类型 , 类型: java.lang.String
	*/
	public static final String SUB_TYPE="subType";
	
	/**
	 * 子类型 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.lang.String> SUB_TYPE_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,SUB_TYPE, java.lang.String.class, "子类型", "子类型", java.lang.String.class, null);
	
	/**
	 * IP , 类型: java.lang.String
	*/
	public static final String NODE_IP="nodeIp";
	
	/**
	 * IP , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.lang.String> NODE_IP_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,NODE_IP, java.lang.String.class, "IP", "IP", java.lang.String.class, null);
	
	/**
	 * 主机名 , 类型: java.lang.String
	*/
	public static final String NODE_NAME="nodeName";
	
	/**
	 * 主机名 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.lang.String> NODE_NAME_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,NODE_NAME, java.lang.String.class, "主机名", "主机名", java.lang.String.class, null);
	
	/**
	 * 可见主机名 , 类型: java.lang.String
	*/
	public static final String NODE_NAME_SHOW="nodeNameShow";
	
	/**
	 * 可见主机名 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.lang.String> NODE_NAME_SHOW_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,NODE_NAME_SHOW, java.lang.String.class, "可见主机名", "可见主机名", java.lang.String.class, null);
	
	/**
	 * 类型 , 类型: java.lang.String
	*/
	public static final String NODE_TYPE="nodeType";
	
	/**
	 * 类型 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.lang.String> NODE_TYPE_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,NODE_TYPE, java.lang.String.class, "类型", "类型", java.lang.String.class, null);
	
	/**
	 * 状态 , 类型: java.lang.String
	*/
	public static final String STATUS="status";
	
	/**
	 * 状态 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.lang.String> STATUS_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,STATUS, java.lang.String.class, "状态", "状态", java.lang.String.class, null);
	
	/**
	 * 监控方式 , 类型: java.lang.String
	*/
	public static final String MONITOR_METHOD="monitorMethod";
	
	/**
	 * 监控方式 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.lang.String> MONITOR_METHOD_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,MONITOR_METHOD, java.lang.String.class, "监控方式", "监控方式", java.lang.String.class, null);
	
	/**
	 * 备注 , 类型: java.lang.String
	*/
	public static final String NOTES="notes";
	
	/**
	 * 备注 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.lang.String> NOTES_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,NOTES, java.lang.String.class, "备注", "备注", java.lang.String.class, null);
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final String CREATE_BY="createBy";
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.lang.String> CREATE_BY_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,CREATE_BY, java.lang.String.class, "创建人ID", "创建人ID", java.lang.String.class, null);
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final String CREATE_TIME="createTime";
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.util.Date> CREATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,CREATE_TIME, java.util.Date.class, "创建时间", "创建时间", java.util.Date.class, null);
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final String UPDATE_BY="updateBy";
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.lang.String> UPDATE_BY_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,UPDATE_BY, java.lang.String.class, "修改人ID", "修改人ID", java.lang.String.class, null);
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final String UPDATE_TIME="updateTime";
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.util.Date> UPDATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,UPDATE_TIME, java.util.Date.class, "修改时间", "修改时间", java.util.Date.class, null);
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final String DELETED="deleted";
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.lang.Integer> DELETED_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,DELETED, java.lang.Integer.class, "是否已删除", "是否已删除", java.lang.Integer.class, null);
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final String DELETE_BY="deleteBy";
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.lang.String> DELETE_BY_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,DELETE_BY, java.lang.String.class, "删除人ID", "删除人ID", java.lang.String.class, null);
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final String DELETE_TIME="deleteTime";
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.util.Date> DELETE_TIME_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,DELETE_TIME, java.util.Date.class, "删除时间", "删除时间", java.util.Date.class, null);
	
	/**
	 * 版本 , 类型: java.lang.Integer
	*/
	public static final String VERSION="version";
	
	/**
	 * 版本 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.lang.Integer> VERSION_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,VERSION, java.lang.Integer.class, "版本", "版本", java.lang.Integer.class, null);
	
	/**
	 * 租户 , 类型: java.lang.String
	*/
	public static final String TENANT_ID="tenantId";
	
	/**
	 * 租户 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,java.lang.String> TENANT_ID_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,TENANT_ID, java.lang.String.class, "租户", "租户", java.lang.String.class, null);
	
	/**
	 * 节点类型 , 类型: com.dt.platform.domain.ops.MonitorNodeType
	*/
	public static final String MONITOR_NODE_TYPE="monitorNodeType";
	
	/**
	 * 节点类型 , 类型: com.dt.platform.domain.ops.MonitorNodeType
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,com.dt.platform.domain.ops.MonitorNodeType> MONITOR_NODE_TYPE_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,MONITOR_NODE_TYPE, com.dt.platform.domain.ops.MonitorNodeType.class, "节点类型", "节点类型", com.dt.platform.domain.ops.MonitorNodeType.class, null);
	
	/**
	 * 节点子类型 , 类型: com.dt.platform.domain.ops.MonitorNodeSubtype
	*/
	public static final String MONITOR_NODE_SUB_TYPE="monitorNodeSubType";
	
	/**
	 * 节点子类型 , 类型: com.dt.platform.domain.ops.MonitorNodeSubtype
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,com.dt.platform.domain.ops.MonitorNodeSubtype> MONITOR_NODE_SUB_TYPE_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,MONITOR_NODE_SUB_TYPE, com.dt.platform.domain.ops.MonitorNodeSubtype.class, "节点子类型", "节点子类型", com.dt.platform.domain.ops.MonitorNodeSubtype.class, null);
	
	/**
	 * 主机信息 , 类型: com.dt.platform.domain.ops.MonitorNodeHost
	*/
	public static final String MONITOR_NODE_HOST="monitorNodeHost";
	
	/**
	 * 主机信息 , 类型: com.dt.platform.domain.ops.MonitorNodeHost
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,com.dt.platform.domain.ops.MonitorNodeHost> MONITOR_NODE_HOST_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,MONITOR_NODE_HOST, com.dt.platform.domain.ops.MonitorNodeHost.class, "主机信息", "主机信息", com.dt.platform.domain.ops.MonitorNodeHost.class, null);
	
	/**
	 * 数值信息 , 集合类型: LIST , 类型: com.dt.platform.domain.ops.MonitorNodeValue
	*/
	public static final String MONITOR_NODE_VALUE_LIST="monitorNodeValueList";
	
	/**
	 * 数值信息 , 集合类型: LIST , 类型: com.dt.platform.domain.ops.MonitorNodeValue
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.MonitorNode,com.dt.platform.domain.ops.MonitorNodeValue> MONITOR_NODE_VALUE_LIST_PROP = new BeanProperty(com.dt.platform.domain.ops.MonitorNode.class ,MONITOR_NODE_VALUE_LIST, java.util.List.class, "数值信息", "数值信息", com.dt.platform.domain.ops.MonitorNodeValue.class, null);
	
	/**
	 * 全部属性清单
	*/
	public static final String[] $PROPS={ ID , PID , TYPE , SUB_TYPE , NODE_IP , NODE_NAME , NODE_NAME_SHOW , NODE_TYPE , STATUS , MONITOR_METHOD , NOTES , CREATE_BY , CREATE_TIME , UPDATE_BY , UPDATE_TIME , DELETED , DELETE_BY , DELETE_TIME , VERSION , TENANT_ID , MONITOR_NODE_TYPE , MONITOR_NODE_SUB_TYPE , MONITOR_NODE_HOST , MONITOR_NODE_VALUE_LIST };
	
	/**
	 * 代理类
	*/
	public static class $$proxy$$ extends com.dt.platform.domain.ops.MonitorNode {

		private static final long serialVersionUID = 1L;

		
		/**
		 * 设置 主键
		 * @param id 主键
		 * @return 当前对象
		*/
		public MonitorNode setId(String id) {
			super.change(ID,super.getId(),id);
			super.setId(id);
			return this;
		}
		
		/**
		 * 设置 父节点
		 * @param pid 父节点
		 * @return 当前对象
		*/
		public MonitorNode setPid(String pid) {
			super.change(PID,super.getPid(),pid);
			super.setPid(pid);
			return this;
		}
		
		/**
		 * 设置 类型
		 * @param type 类型
		 * @return 当前对象
		*/
		public MonitorNode setType(String type) {
			super.change(TYPE,super.getType(),type);
			super.setType(type);
			return this;
		}
		
		/**
		 * 设置 子类型
		 * @param subType 子类型
		 * @return 当前对象
		*/
		public MonitorNode setSubType(String subType) {
			super.change(SUB_TYPE,super.getSubType(),subType);
			super.setSubType(subType);
			return this;
		}
		
		/**
		 * 设置 IP
		 * @param nodeIp IP
		 * @return 当前对象
		*/
		public MonitorNode setNodeIp(String nodeIp) {
			super.change(NODE_IP,super.getNodeIp(),nodeIp);
			super.setNodeIp(nodeIp);
			return this;
		}
		
		/**
		 * 设置 主机名
		 * @param nodeName 主机名
		 * @return 当前对象
		*/
		public MonitorNode setNodeName(String nodeName) {
			super.change(NODE_NAME,super.getNodeName(),nodeName);
			super.setNodeName(nodeName);
			return this;
		}
		
		/**
		 * 设置 可见主机名
		 * @param nodeNameShow 可见主机名
		 * @return 当前对象
		*/
		public MonitorNode setNodeNameShow(String nodeNameShow) {
			super.change(NODE_NAME_SHOW,super.getNodeNameShow(),nodeNameShow);
			super.setNodeNameShow(nodeNameShow);
			return this;
		}
		
		/**
		 * 设置 类型
		 * @param nodeType 类型
		 * @return 当前对象
		*/
		public MonitorNode setNodeType(String nodeType) {
			super.change(NODE_TYPE,super.getNodeType(),nodeType);
			super.setNodeType(nodeType);
			return this;
		}
		
		/**
		 * 设置 状态
		 * @param status 状态
		 * @return 当前对象
		*/
		public MonitorNode setStatus(String status) {
			super.change(STATUS,super.getStatus(),status);
			super.setStatus(status);
			return this;
		}
		
		/**
		 * 设置 监控方式
		 * @param monitorMethod 监控方式
		 * @return 当前对象
		*/
		public MonitorNode setMonitorMethod(String monitorMethod) {
			super.change(MONITOR_METHOD,super.getMonitorMethod(),monitorMethod);
			super.setMonitorMethod(monitorMethod);
			return this;
		}
		
		/**
		 * 设置 备注
		 * @param notes 备注
		 * @return 当前对象
		*/
		public MonitorNode setNotes(String notes) {
			super.change(NOTES,super.getNotes(),notes);
			super.setNotes(notes);
			return this;
		}
		
		/**
		 * 设置 创建人ID
		 * @param createBy 创建人ID
		 * @return 当前对象
		*/
		public MonitorNode setCreateBy(String createBy) {
			super.change(CREATE_BY,super.getCreateBy(),createBy);
			super.setCreateBy(createBy);
			return this;
		}
		
		/**
		 * 设置 创建时间
		 * @param createTime 创建时间
		 * @return 当前对象
		*/
		public MonitorNode setCreateTime(Date createTime) {
			super.change(CREATE_TIME,super.getCreateTime(),createTime);
			super.setCreateTime(createTime);
			return this;
		}
		
		/**
		 * 设置 修改人ID
		 * @param updateBy 修改人ID
		 * @return 当前对象
		*/
		public MonitorNode setUpdateBy(String updateBy) {
			super.change(UPDATE_BY,super.getUpdateBy(),updateBy);
			super.setUpdateBy(updateBy);
			return this;
		}
		
		/**
		 * 设置 修改时间
		 * @param updateTime 修改时间
		 * @return 当前对象
		*/
		public MonitorNode setUpdateTime(Date updateTime) {
			super.change(UPDATE_TIME,super.getUpdateTime(),updateTime);
			super.setUpdateTime(updateTime);
			return this;
		}
		
		/**
		 * 设置 是否已删除
		 * @param deleted 是否已删除
		 * @return 当前对象
		*/
		public MonitorNode setDeleted(Integer deleted) {
			super.change(DELETED,super.getDeleted(),deleted);
			super.setDeleted(deleted);
			return this;
		}
		
		/**
		 * 设置 删除人ID
		 * @param deleteBy 删除人ID
		 * @return 当前对象
		*/
		public MonitorNode setDeleteBy(String deleteBy) {
			super.change(DELETE_BY,super.getDeleteBy(),deleteBy);
			super.setDeleteBy(deleteBy);
			return this;
		}
		
		/**
		 * 设置 删除时间
		 * @param deleteTime 删除时间
		 * @return 当前对象
		*/
		public MonitorNode setDeleteTime(Date deleteTime) {
			super.change(DELETE_TIME,super.getDeleteTime(),deleteTime);
			super.setDeleteTime(deleteTime);
			return this;
		}
		
		/**
		 * 设置 版本
		 * @param version 版本
		 * @return 当前对象
		*/
		public MonitorNode setVersion(Integer version) {
			super.change(VERSION,super.getVersion(),version);
			super.setVersion(version);
			return this;
		}
		
		/**
		 * 设置 租户
		 * @param tenantId 租户
		 * @return 当前对象
		*/
		public MonitorNode setTenantId(String tenantId) {
			super.change(TENANT_ID,super.getTenantId(),tenantId);
			super.setTenantId(tenantId);
			return this;
		}
		
		/**
		 * 设置 节点类型
		 * @param monitorNodeType 节点类型
		 * @return 当前对象
		*/
		public MonitorNode setMonitorNodeType(MonitorNodeType monitorNodeType) {
			super.change(MONITOR_NODE_TYPE,super.getMonitorNodeType(),monitorNodeType);
			super.setMonitorNodeType(monitorNodeType);
			return this;
		}
		
		/**
		 * 设置 节点子类型
		 * @param monitorNodeSubType 节点子类型
		 * @return 当前对象
		*/
		public MonitorNode setMonitorNodeSubType(MonitorNodeSubtype monitorNodeSubType) {
			super.change(MONITOR_NODE_SUB_TYPE,super.getMonitorNodeSubType(),monitorNodeSubType);
			super.setMonitorNodeSubType(monitorNodeSubType);
			return this;
		}
		
		/**
		 * 设置 主机信息
		 * @param monitorNodeHost 主机信息
		 * @return 当前对象
		*/
		public MonitorNode setMonitorNodeHost(MonitorNodeHost monitorNodeHost) {
			super.change(MONITOR_NODE_HOST,super.getMonitorNodeHost(),monitorNodeHost);
			super.setMonitorNodeHost(monitorNodeHost);
			return this;
		}
		
		/**
		 * 设置 数值信息
		 * @param monitorNodeValueList 数值信息
		 * @return 当前对象
		*/
		public MonitorNode setMonitorNodeValueList(List<MonitorNodeValue> monitorNodeValueList) {
			super.change(MONITOR_NODE_VALUE_LIST,super.getMonitorNodeValueList(),monitorNodeValueList);
			super.setMonitorNodeValueList(monitorNodeValueList);
			return this;
		}
	}
}