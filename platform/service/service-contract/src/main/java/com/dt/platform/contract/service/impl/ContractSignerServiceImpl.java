package com.dt.platform.contract.service.impl;

import com.dt.platform.contract.service.IContractSignerService;
import com.dt.platform.domain.contract.ContractSigner;
import com.dt.platform.domain.contract.ContractSignerVO;
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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 合同签订方服务实现
 * </p>
 * @author 李方捷 , leefangjie@qq.com
 * @since 2022-10-21 15:39:33
*/


@Service("ContContractSignerService")
public class ContractSignerServiceImpl extends SuperService<ContractSigner> implements IContractSignerService {

	/**
	 * 注入DAO对象
	 * */
	@Resource(name=DBConfigs.PRIMARY_DAO) 
	private DAO dao=null;

	/**
	 * 获得 DAO 对象
	 * */
	public DAO dao() { return dao; }



	@Override
	public Object generateId(Field field) {
		return IDGenerator.getSnowflakeIdString();
	}

	/**
	 * 添加，根据 throwsException 参数抛出异常或返回 Result 对象
	 *
	 * @param contractSigner  数据对象
	 * @param throwsException 是否抛出异常，如果不抛出异常，则返回一个失败的 Result 对象
	 * @return 结果 , 如果失败返回 false，成功返回 true
	 */
	@Override
	public Result insert(ContractSigner contractSigner,boolean throwsException) {
		Result r=super.insert(contractSigner,throwsException);
		return r;
	}

	/**
	 * 添加，如果语句错误，则抛出异常
	 * @param contractSigner 数据对象
	 * @return 插入是否成功
	 * */
	@Override
	public Result insert(ContractSigner contractSigner) {
		return this.insert(contractSigner,true);
	}

	/**
	 * 批量插入实体，事务内
	 * @param contractSignerList 实体数据清单
	 * @return 插入是否成功
	 * */
	@Override
	public Result insertList(List<ContractSigner> contractSignerList) {
		return super.insertList(contractSignerList);
	}

	
	/**
	 * 按主键删除合同签订方
	 *
	 * @param id id
	 * @return 删除是否成功
	 */
	public Result deleteByIdPhysical(String id) {
		ContractSigner contractSigner = new ContractSigner();
		if(id==null) return ErrorDesc.failure().message("id 不允许为 null 。");
		contractSigner.setId(id);
		try {
			boolean suc = dao.deleteEntity(contractSigner);
			return suc?ErrorDesc.success():ErrorDesc.failure();
		}
		catch(Exception e) {
			Result r= ErrorDesc.failure();
			r.extra().setException(e);
			return r;
		}
	}
	
	/**
	 * 按主键删除合同签订方
	 *
	 * @param id id
	 * @return 删除是否成功
	 */
	public Result deleteByIdLogical(String id) {
		ContractSigner contractSigner = new ContractSigner();
		if(id==null) return ErrorDesc.failure().message("id 不允许为 null 。");
		contractSigner.setId(id);
		contractSigner.setDeleted(true);
		contractSigner.setDeleteBy((String)dao.getDBTreaty().getLoginUserId());
		contractSigner.setDeleteTime(new Date());
		try {
			boolean suc = dao.updateEntity(contractSigner,SaveMode.NOT_NULL_FIELDS);
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
	 * @param contractSigner 数据对象
	 * @param mode 保存模式
	 * @return 保存是否成功
	 * */
	@Override
	public Result update(ContractSigner contractSigner , SaveMode mode) {
		return this.update(contractSigner,mode,true);
	}

	/**
	 * 更新，根据 throwsException 参数抛出异常或返回 Result 对象
	 * @param contractSigner 数据对象
	 * @param mode 保存模式
	 * @param throwsException 是否抛出异常，如果不抛出异常，则返回一个失败的 Result 对象
	 * @return 保存是否成功
	 * */
	@Override
	public Result update(ContractSigner contractSigner , SaveMode mode,boolean throwsException) {
		Result r=super.update(contractSigner , mode , throwsException);
		return r;
	}

	/**
	 * 更新实体集，事务内
	 * @param contractSignerList 数据对象列表
	 * @param mode 保存模式
	 * @return 保存是否成功
	 * */
	@Override
	public Result updateList(List<ContractSigner> contractSignerList , SaveMode mode) {
		return super.updateList(contractSignerList , mode);
	}

	
	/**
	 * 按主键更新合同签订方
	 *
	 * @param id id
	 * @return 是否更新成功
	 */
	public boolean update(DBField field,Object value , String id) {
		if(id==null) throw new IllegalArgumentException("id 不允许为 null ");
		if(!field.table().name().equals(this.table())) throw new IllegalArgumentException("更新的数据表["+field.table().name()+"]与服务对应的数据表["+this.table()+"]不一致");
		int suc=dao.update(field.table().name()).set(field.name(), value).where().and("id = ? ",id).top().execute();
		return suc>0;
	}

	
	/**
	 * 按主键获取合同签订方
	 *
	 * @param id id
	 * @return ContractSigner 数据对象
	 */
	public ContractSigner getById(String id) {
		ContractSigner sample = new ContractSigner();
		if(id==null) throw new IllegalArgumentException("id 不允许为 null ");
		sample.setId(id);
		return dao.queryEntity(sample);
	}

	/**
	 * 等价于 queryListByIds
	 * */
	@Override
	public List<ContractSigner> getByIds(List<String> ids) {
		return this.queryListByIds(ids);
	}

	@Override
	public List<ContractSigner> queryListByIds(List<String> ids) {
		return super.queryListByUKeys("id",ids);
	}

	@Override
	public Map<String, ContractSigner> queryMapByIds(List<String> ids) {
		return super.queryMapByUKeys("id",ids, ContractSigner::getId);
	}



	/**
	 * 查询实体集合，默认情况下，字符串使用模糊匹配，非字符串使用精确匹配
	 *
	 * @param sample  查询条件
	 * @return 查询结果
	 * */
	@Override
	public List<ContractSigner> queryList(ContractSignerVO sample) {
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
	public PagedList<ContractSigner> queryPagedList(ContractSignerVO sample, int pageSize, int pageIndex) {
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
	public PagedList<ContractSigner> queryPagedList(ContractSigner sample, ConditionExpr condition, int pageSize, int pageIndex) {
		return super.queryPagedList(sample, condition, pageSize, pageIndex);
	}

	/**
	 * 检查 实体 是否已经存在 , 判断 主键值不同，但指定字段的值相同的记录是否存在
	 *
	 * @param contractSigner 数据对象
	 * @return 判断结果
	 */
	public Boolean checkExists(ContractSigner contractSigner) {
		//TDOD 此处添加判断段的代码
		//boolean exists=super.checkExists(contractSigner, SYS_ROLE.NAME);
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