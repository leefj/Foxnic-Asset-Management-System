package com.dt.platform.ops.service.impl;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.dt.platform.domain.ops.MonitorNode;
import com.dt.platform.domain.ops.MonitorNodeTplItem;
import com.dt.platform.domain.ops.MonitorNodeTplItemVO;
import com.dt.platform.domain.ops.MonitorNodeVO;
import com.dt.platform.ops.service.IMonitorNodeService;
import com.github.foxnic.api.error.ErrorDesc;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.commons.busi.id.IDGenerator;
import com.github.foxnic.commons.collection.MapUtil;
import com.github.foxnic.dao.data.PagedList;
import com.github.foxnic.dao.data.SaveMode;
import com.github.foxnic.dao.entity.ReferCause;
import com.github.foxnic.dao.entity.SuperService;
import com.github.foxnic.dao.spec.DAO;
import com.github.foxnic.sql.expr.ConditionExpr;
import com.github.foxnic.sql.meta.DBField;
import org.github.foxnic.web.framework.dao.DBConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 节点 服务实现
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-07-14 16:24:29
*/


@Service("OpsMonitorNodeService")
public class MonitorNodeServiceImpl extends SuperService<MonitorNode> implements IMonitorNodeService {

	/**
	 * 注入DAO对象
	 * */
	@Resource(name=DBConfigs.PRIMARY_DAO) 
	private DAO dao=null;

	/**
	 * 获得 DAO 对象
	 * */
	public DAO dao() { return dao; }

	@Autowired 
	private MonitorNodeTplItemServiceImpl monitorNodeTplItemServiceImpl;


	@Override
	public Object generateId(Field field) {
		return IDGenerator.getSnowflakeIdString();
	}

	/**
	 * 添加，根据 throwsException 参数抛出异常或返回 Result 对象
	 *
	 * @param monitorNode  数据对象
	 * @param throwsException 是否抛出异常，如果不抛出异常，则返回一个失败的 Result 对象
	 * @return 结果 , 如果失败返回 false，成功返回 true
	 */
	@Override
	@Transactional
	public Result insert(MonitorNode monitorNode,boolean throwsException) {
		Result r=super.insert(monitorNode,throwsException);
		//保存关系
		if(r.success()) {
			monitorNodeTplItemServiceImpl.saveRelation(monitorNode.getId(), monitorNode.getMonitorTplIds());
		}
		return r;
	}

	@Override
	public Result copy(String id, String num) {
		MonitorNode node=this.getById(id);
		MonitorNodeTplItemVO tplItem=new MonitorNodeTplItemVO();
		tplItem.setNodeId(id);
		List<MonitorNodeTplItem> tplItemList=monitorNodeTplItemServiceImpl.queryList(tplItem);
		if(node==null){
			return ErrorDesc.failureMessage("当前选择的节点不存在");
		}
		if(StringUtil.isBlank(num)){
			return ErrorDesc.failureMessage("请选择要复制的数量");
		}
		int n=Integer.parseInt(num);
		if(n==0){
			return ErrorDesc.failureMessage("复制的数量必须大于0");
		}
		String nodeName=node.getNodeNameShow();
		for(int i=0;i<n;i++){
			node.setId(null);
			node.setNodeIp("127.0.0.1");
			node.setNodeNameShow(nodeName+"复制"+(i+1));
			node.setStatus("offline");
			node.setNodeEnabled("disabled");
			this.insert(node,true);
			String nodeId=node.getId();
			if(tplItemList!=null&&tplItemList.size()>0){
				for(int j=0;j<tplItemList.size();j++){
					MonitorNodeTplItem tpl = new MonitorNodeTplItem();
					tpl.setNodeId(nodeId);
					tpl.setTplCode(tplItemList.get(j).getTplCode());
					monitorNodeTplItemServiceImpl.insert(tpl, true);
				}
			}
		}
		return ErrorDesc.success();
	}

	/**
	 * 添加，如果语句错误，则抛出异常
	 * @param monitorNode 数据对象
	 * @return 插入是否成功
	 * */
	@Override
	@Transactional
	public Result insert(MonitorNode monitorNode) {
		return this.insert(monitorNode,true);
	}

	/**
	 * 批量插入实体，事务内
	 * @param monitorNodeList 实体数据清单
	 * @return 插入是否成功
	 * */
	@Override
	public Result insertList(List<MonitorNode> monitorNodeList) {
		return super.insertList(monitorNodeList);
	}

	
	/**
	 * 按主键删除 节点
	 *
	 * @param id 主键
	 * @return 删除是否成功
	 */
	public Result deleteByIdPhysical(String id) {
		MonitorNode monitorNode = new MonitorNode();
		if(id==null) return ErrorDesc.failure().message("id 不允许为 null 。");
		monitorNode.setId(id);
		try {
			boolean suc = dao.deleteEntity(monitorNode);
			return suc?ErrorDesc.success():ErrorDesc.failure();
		}
		catch(Exception e) {
			Result r= ErrorDesc.failure();
			r.extra().setException(e);
			return r;
		}
	}
	
	/**
	 * 按主键删除 节点
	 *
	 * @param id 主键
	 * @return 删除是否成功
	 */
	public Result deleteByIdLogical(String id) {
		MonitorNode monitorNode = new MonitorNode();
		if(id==null) return ErrorDesc.failure().message("id 不允许为 null 。");
		monitorNode.setId(id);
		monitorNode.setDeleted(true);
		monitorNode.setDeleteBy((String)dao.getDBTreaty().getLoginUserId());
		monitorNode.setDeleteTime(new Date());
		try {
			boolean suc = dao.updateEntity(monitorNode,SaveMode.NOT_NULL_FIELDS);
			return suc?ErrorDesc.success():ErrorDesc.failure();
		}
		catch(Exception e) {
			Result r= ErrorDesc.failure();
			r.extra().setException(e);
			return r;
		}
	}

	/**
	 * 更新，如果执行错误，则抛出异常
	 * @param monitorNode 数据对象
	 * @param mode 保存模式
	 * @return 保存是否成功
	 * */
	@Override
	@Transactional
	public Result update(MonitorNode monitorNode , SaveMode mode) {
		return this.update(monitorNode,mode,true);
	}

	/**
	 * 更新，根据 throwsException 参数抛出异常或返回 Result 对象
	 * @param monitorNode 数据对象
	 * @param mode 保存模式
	 * @param throwsException 是否抛出异常，如果不抛出异常，则返回一个失败的 Result 对象
	 * @return 保存是否成功
	 * */
	@Override
	@Transactional
	public Result update(MonitorNode monitorNode , SaveMode mode,boolean throwsException) {
		Result r=super.update(monitorNode , mode , throwsException);
		//保存关系
		if(r.success()) {
			monitorNodeTplItemServiceImpl.saveRelation(monitorNode.getId(), monitorNode.getMonitorTplIds());
		}
		return r;
	}

	/**
	 * 更新实体集，事务内
	 * @param monitorNodeList 数据对象列表
	 * @param mode 保存模式
	 * @return 保存是否成功
	 * */
	@Override
	public Result updateList(List<MonitorNode> monitorNodeList , SaveMode mode) {
		return super.updateList(monitorNodeList , mode);
	}

	
	/**
	 * 按主键更新字段 节点
	 *
	 * @param id 主键
	 * @return 是否更新成功
	 */
	public boolean update(DBField field,Object value , String id) {
		if(id==null) throw new IllegalArgumentException("id 不允许为 null ");
		if(!field.table().name().equals(this.table())) throw new IllegalArgumentException("更新的数据表["+field.table().name()+"]与服务对应的数据表["+this.table()+"]不一致");
		int suc=dao.update(field.table().name()).set(field.name(), value).where().and("id = ? ",id).top().execute();
		return suc>0;
	}

	
	/**
	 * 按主键获取 节点
	 *
	 * @param id 主键
	 * @return MonitorNode 数据对象
	 */
	public MonitorNode getById(String id) {
		MonitorNode sample = new MonitorNode();
		if(id==null) throw new IllegalArgumentException("id 不允许为 null ");
		sample.setId(id);
		return dao.queryEntity(sample);
	}

	/**
	 * 等价于 queryListByIds
	 * */
	@Override
	public List<MonitorNode> getByIds(List<String> ids) {
		return this.queryListByIds(ids);
	}

	@Override
	public List<MonitorNode> queryListByIds(List<String> ids) {
		return super.queryListByUKeys("id",ids);
	}

	@Override
	public Map<String, MonitorNode> queryMapByIds(List<String> ids) {
		return super.queryMapByUKeys("id",ids, MonitorNode::getId);
	}



	/**
	 * 查询实体集合，默认情况下，字符串使用模糊匹配，非字符串使用精确匹配
	 *
	 * @param sample  查询条件
	 * @return 查询结果
	 * */
	@Override
	public List<MonitorNode> queryList(MonitorNodeVO sample) {
		return super.queryList(sample);
	}


	/**
	 * 分页查询实体集，字符串使用模糊匹配，非字符串使用精确匹配
	 *
	 * @param sample  查询条件
	 * @param pageSize 分页条数
	 * @param pageIndex 页码
	 * @return 查询结果
	 * */
	@Override
	public PagedList<MonitorNode> queryPagedList(MonitorNodeVO sample, int pageSize, int pageIndex) {
		return super.queryPagedList(sample, pageSize, pageIndex);
	}

	/**
	 * 分页查询实体集，字符串使用模糊匹配，非字符串使用精确匹配
	 *
	 * @param sample  查询条件
	 * @param condition 其它条件
	 * @param pageSize 分页条数
	 * @param pageIndex 页码
	 * @return 查询结果
	 * */
	@Override
	public PagedList<MonitorNode> queryPagedList(MonitorNode sample, ConditionExpr condition, int pageSize, int pageIndex) {
		return super.queryPagedList(sample, condition, pageSize, pageIndex);
	}

	/**
	 * 检查 实体 是否已经存在 , 判断 主键值不同，但指定字段的值相同的记录是否存在
	 *
	 * @param monitorNode 数据对象
	 * @return 判断结果
	 */
	public Boolean checkExists(MonitorNode monitorNode) {
		//TDOD 此处添加判断段的代码
		//boolean exists=super.checkExists(monitorNode, SYS_ROLE.NAME);
		//return exists;
		return false;
	}




	/**
	 * 批量检查引用
	 * @param ids  检查这些ID是否又被外部表引用
	 * */
	@Override
	public <T> Map<T, ReferCause> hasRefers(List<T> ids) {
		// 默认无业务逻辑，返回此行；有业务逻辑需要校验时，请修改并使用已注释的行代码！！！
		return MapUtil.asMap(ids,new ReferCause(false));
		// return super.hasRefers(FoxnicWeb.BPM_PROCESS_INSTANCE.FORM_DEFINITION_ID,ids);
	}






}