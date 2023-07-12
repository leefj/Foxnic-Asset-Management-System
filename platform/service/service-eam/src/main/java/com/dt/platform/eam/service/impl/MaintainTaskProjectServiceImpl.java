package com.dt.platform.eam.service.impl;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.dt.platform.constants.enums.eam.MaintainTaskStatusEnum;
import com.dt.platform.domain.eam.MaintainTask;
import com.dt.platform.domain.eam.MappingOwner;
import com.dt.platform.eam.service.IMaintainTaskService;
import com.dt.platform.eam.service.IMappingOwnerService;
import org.github.foxnic.web.session.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.foxnic.dao.entity.ReferCause;
import com.github.foxnic.commons.collection.MapUtil;
import java.util.Arrays;


import com.dt.platform.domain.eam.MaintainTaskProject;
import com.dt.platform.domain.eam.MaintainTaskProjectVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.github.foxnic.dao.entity.SuperService;
import com.github.foxnic.dao.spec.DAO;
import java.lang.reflect.Field;
import com.github.foxnic.commons.busi.id.IDGenerator;
import com.github.foxnic.sql.expr.ConditionExpr;
import com.github.foxnic.api.error.ErrorDesc;
import com.github.foxnic.dao.excel.ExcelWriter;
import com.github.foxnic.dao.excel.ValidateResult;
import com.github.foxnic.dao.excel.ExcelStructure;
import java.io.InputStream;
import com.github.foxnic.sql.meta.DBField;
import com.github.foxnic.dao.data.SaveMode;
import com.github.foxnic.dao.meta.DBColumnMeta;
import com.github.foxnic.sql.expr.Select;
import java.util.ArrayList;
import com.dt.platform.eam.service.IMaintainTaskProjectService;
import org.github.foxnic.web.framework.dao.DBConfigs;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 保养项目服务实现
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-07-10 15:51:09
*/


@Service("EamMaintainTaskProjectService")

public class MaintainTaskProjectServiceImpl extends SuperService<MaintainTaskProject> implements IMaintainTaskProjectService {



	@Autowired
	private IMaintainTaskService maintainTaskService;

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
	private IMappingOwnerService mappingOwnerService;


	@Override
	public Object generateId(Field field) {
		return IDGenerator.getSnowflakeIdString();
	}

	/**
	 * 添加，根据 throwsException 参数抛出异常或返回 Result 对象
	 *
	 * @param maintainTaskProject  数据对象
	 * @param throwsException 是否抛出异常，如果不抛出异常，则返回一个失败的 Result 对象
	 * @return 结果 , 如果失败返回 false，成功返回 true
	 */

	@Override
	public Result insert(MaintainTaskProject maintainTaskProject,boolean throwsException) {
		Result r=super.insert(maintainTaskProject,throwsException);
		return r;
	}

	@Override
	public Result execute(String id, String status, String content) {

		MaintainTaskProject sourceTaskProject=this.getById(id);
		MaintainTask task= maintainTaskService.getById(sourceTaskProject.getTaskId());

		if(MaintainTaskStatusEnum.WAIT.code().equals(task.getStatus())){
			//修改
			task.setStatus(MaintainTaskStatusEnum.ACTING.code());
			task.setExecutorId(SessionUser.getCurrent().getActivatedEmployeeId());
			task.setActStartTime(new Date());


			maintainTaskService.update(task,SaveMode.NOT_NULL_FIELDS,true);
		}else if(MaintainTaskStatusEnum.FINISH.code().equals(task.getStatus())
		||MaintainTaskStatusEnum.CANCEL.code().equals(task.getStatus())){
			return ErrorDesc.failureMessage("当前任务状态无法进行该操作");
		}

		sourceTaskProject.setId(id);
		sourceTaskProject.setContent(content);
		sourceTaskProject.setStatus(status);
		sourceTaskProject.setOperTime(new Date());
		sourceTaskProject.setOperUserId(SessionUser.getCurrent().getActivatedEmployeeId());
		super.update(sourceTaskProject,SaveMode.NOT_NULL_FIELDS,true);


		//继续更新,
		MaintainTaskProjectVO taskProjectVO=new MaintainTaskProjectVO();
		taskProjectVO.setSelectedCode("def");
		taskProjectVO.setProjectId(sourceTaskProject.getProjectId());
		taskProjectVO.setTaskId(sourceTaskProject.getTaskId());
		List<MaintainTaskProject> list=this.queryList(taskProjectVO);
		if(list!=null&&list.size()>0){
			MaintainTaskProject taskProject2=list.get(0);
			taskProject2.setContent(content);
			taskProject2.setStatus(status);
			taskProject2.setOperTime(new Date());
			taskProject2.setOperUserId(SessionUser.getCurrent().getActivatedEmployeeId());
			super.update(taskProject2,SaveMode.NOT_NULL_FIELDS,true);
		}

		return ErrorDesc.success();
	}

	@Override
	public Result selectDeleteByIds(String ownerId, String ids, String selectedCode) {

		JSONArray idsArr=JSONArray.parseArray(ids);
		for(int i=0;i<idsArr.size();i++){
			String id=idsArr.getString(i);
			dao.execute("update eam_maintain_task_project set deleted=1 where deleted=0 and id=?",id);
		}
		return ErrorDesc.success();
	}

	@Override
	public Result selectSaveIds(String ownerId, String ids, String selectedCode) {
		return ErrorDesc.failureMessage("未实现");
	}

	@Override
	public PagedList<MaintainTaskProject> queryPagedListBySelected(MaintainTaskProjectVO sample, String ownerId, String ownerType) {
		String selectCode=sample.getSelectedCode();
		sample.setSelectedCode(null);
		ConditionExpr expr=new ConditionExpr();
		if(dao().queryRecord("select count(1) cnt from eam_mapping_owner where deleted=0 and owner_id=? and selected_code=?",ownerId,selectCode).getInteger("cnt")==0){
			//做一份转
			MaintainTaskProjectVO vo=new MaintainTaskProjectVO();
			vo.setSelectedCode("def");
			vo.setTaskId(ownerId);
			List<MaintainTaskProject> list=this.queryList(vo);
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					MaintainTaskProject newObj=list.get(i);
					newObj.setId(IDGenerator.getSnowflakeIdString());
					newObj.setSelectedCode(selectCode);
					super.insert(newObj,true);
				}
			}
			MappingOwner mappingOwner=new MappingOwner();
			mappingOwner.setOwnerId(ownerId);
			mappingOwner.setSelectedCode(selectCode);
			mappingOwnerService.insert(mappingOwner,true);

		}
		expr.and(" task_id=? and selected_code=? ",ownerId,selectCode);
		return super.queryPagedList(sample,expr,sample.getPageSize(),sample.getPageIndex());
	}

	/**
	 * 添加，如果语句错误，则抛出异常
	 * @param maintainTaskProject 数据对象
	 * @return 插入是否成功
	 * */
	@Override
	public Result insert(MaintainTaskProject maintainTaskProject) {
		return this.insert(maintainTaskProject,true);
	}

	/**
	 * 批量插入实体，事务内
	 * @param maintainTaskProjectList 实体数据清单
	 * @return 插入是否成功
	 * */
	@Override
	public Result insertList(List<MaintainTaskProject> maintainTaskProjectList) {
		return super.insertList(maintainTaskProjectList);
	}

	
	/**
	 * 按主键删除保养项目
	 *
	 * @param id 主键
	 * @return 删除是否成功
	 */
	public Result deleteByIdPhysical(String id) {
		MaintainTaskProject maintainTaskProject = new MaintainTaskProject();
		if(id==null) return ErrorDesc.failure().message("id 不允许为 null 。");
		maintainTaskProject.setId(id);
		try {
			boolean suc = dao.deleteEntity(maintainTaskProject);
			return suc?ErrorDesc.success():ErrorDesc.failure();
		}
		catch(Exception e) {
			Result r= ErrorDesc.failure();
			r.extra().setException(e);
			return r;
		}
	}
	
	/**
	 * 按主键删除保养项目
	 *
	 * @param id 主键
	 * @return 删除是否成功
	 */
	public Result deleteByIdLogical(String id) {
		MaintainTaskProject maintainTaskProject = new MaintainTaskProject();
		if(id==null) return ErrorDesc.failure().message("id 不允许为 null 。");
		maintainTaskProject.setId(id);
		maintainTaskProject.setDeleted(true);
		maintainTaskProject.setDeleteBy((String)dao.getDBTreaty().getLoginUserId());
		maintainTaskProject.setDeleteTime(new Date());
		try {
			boolean suc = dao.updateEntity(maintainTaskProject,SaveMode.NOT_NULL_FIELDS);
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
	 * @param maintainTaskProject 数据对象
	 * @param mode 保存模式
	 * @return 保存是否成功
	 * */
	@Override
	public Result update(MaintainTaskProject maintainTaskProject , SaveMode mode) {
		return this.update(maintainTaskProject,mode,true);
	}

	/**
	 * 更新，根据 throwsException 参数抛出异常或返回 Result 对象
	 * @param maintainTaskProject 数据对象
	 * @param mode 保存模式
	 * @param throwsException 是否抛出异常，如果不抛出异常，则返回一个失败的 Result 对象
	 * @return 保存是否成功
	 * */
	@Override
	public Result update(MaintainTaskProject maintainTaskProject , SaveMode mode,boolean throwsException) {
		Result r=super.update(maintainTaskProject , mode , throwsException);
		return r;
	}

	/**
	 * 更新实体集，事务内
	 * @param maintainTaskProjectList 数据对象列表
	 * @param mode 保存模式
	 * @return 保存是否成功
	 * */
	@Override
	public Result updateList(List<MaintainTaskProject> maintainTaskProjectList , SaveMode mode) {
		return super.updateList(maintainTaskProjectList , mode);
	}

	
	/**
	 * 按主键更新保养项目
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
	 * 按主键获取保养项目
	 *
	 * @param id 主键
	 * @return MaintainTaskProject 数据对象
	 */
	public MaintainTaskProject getById(String id) {
		MaintainTaskProject sample = new MaintainTaskProject();
		if(id==null) throw new IllegalArgumentException("id 不允许为 null ");
		sample.setId(id);
		return dao.queryEntity(sample);
	}

	/**
	 * 等价于 queryListByIds
	 * */
	@Override
	public List<MaintainTaskProject> getByIds(List<String> ids) {
		return this.queryListByIds(ids);
	}

	@Override
	public List<MaintainTaskProject> queryListByIds(List<String> ids) {
		return super.queryListByUKeys("id",ids);
	}

	@Override
	public Map<String, MaintainTaskProject> queryMapByIds(List<String> ids) {
		return super.queryMapByUKeys("id",ids, MaintainTaskProject::getId);
	}



	/**
	 * 查询实体集合，默认情况下，字符串使用模糊匹配，非字符串使用精确匹配
	 *
	 * @param sample  查询条件
	 * @return 查询结果
	 * */
	@Override
	public List<MaintainTaskProject> queryList(MaintainTaskProjectVO sample) {
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
	public PagedList<MaintainTaskProject> queryPagedList(MaintainTaskProjectVO sample, int pageSize, int pageIndex) {
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
	public PagedList<MaintainTaskProject> queryPagedList(MaintainTaskProject sample, ConditionExpr condition, int pageSize, int pageIndex) {
		return super.queryPagedList(sample, condition, pageSize, pageIndex);
	}

	/**
	 * 检查 实体 是否已经存在 , 判断 主键值不同，但指定字段的值相同的记录是否存在
	 *
	 * @param maintainTaskProject 数据对象
	 * @return 判断结果
	 */
	public Boolean checkExists(MaintainTaskProject maintainTaskProject) {
		//TDOD 此处添加判断段的代码
		//boolean exists=super.checkExists(maintainTaskProject, SYS_ROLE.NAME);
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