package com.dt.platform.hr.service.impl;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.dt.platform.constants.enums.hr.SalaryActionStatusEnum;
import com.dt.platform.constants.enums.hr.SalaryPersonDetailStatusEnum;
import com.dt.platform.domain.hr.Person;
import com.dt.platform.domain.hr.SalaryAction;
import com.dt.platform.domain.hr.meta.SalaryActionMeta;
import com.dt.platform.domain.hr.meta.SalaryDetailMeta;
import com.dt.platform.hr.service.ISalaryActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.foxnic.dao.entity.ReferCause;
import com.github.foxnic.commons.collection.MapUtil;
import java.util.Arrays;


import com.dt.platform.domain.hr.SalaryDetail;
import com.dt.platform.domain.hr.SalaryDetailVO;
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
import com.dt.platform.hr.service.ISalaryDetailService;
import org.github.foxnic.web.framework.dao.DBConfigs;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 薪酬明细服务实现
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-06-04 13:01:46
*/


@Service("HrSalaryDetailService")

public class SalaryDetailServiceImpl extends SuperService<SalaryDetail> implements ISalaryDetailService {


	@Autowired
	private ISalaryActionService salaryActionService;

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
	 * @param salaryDetail  数据对象
	 * @param throwsException 是否抛出异常，如果不抛出异常，则返回一个失败的 Result 对象
	 * @return 结果 , 如果失败返回 false，成功返回 true
	 */
	@Override
	public Result insert(SalaryDetail salaryDetail,boolean throwsException) {
		Result r=super.insert(salaryDetail,throwsException);
		return r;
	}

	@Override
	public Result<JSONObject> queryStatisticalDataByActionId(String actionId) {
		Result<JSONObject> res=new Result<>();
		JSONObject data=new JSONObject();
		String sql="select \n" +
				"( select count(1) person_abnormal_cnt from hr_salary_detail where deleted=0 and status in ('abnormal') and action_id=? )person_abnormal_cnt,\n" +
				"( select count(1) total_pserson_cnt from hr_salary_detail where deleted=0 and action_id=?)total_pserson_cnt,\n" +
				"( select sum(total_amount) total_amount_sum from hr_salary_detail where deleted=0 and action_id=?)total_amount_sum,\n" +
				"( select sum(issued_amount) issued_amount_sum from hr_salary_detail where deleted=0 and action_id=?)issued_amount_sum";
		data=dao.queryRecord(sql,actionId,actionId,actionId,actionId).toJSONObject();
		res.success();
		res.data(data);
		return res;
	}

	@Override
	public Result valid(String actionId) {

		if(dao.query("select 1 from hr_salary_detail where action_id=? and status<>'invalid'",actionId).size()>0){
			return ErrorDesc.failureMessage("当前薪酬状态有异常，无法进行更新操作");
		}

		SalaryAction salaryAction=salaryActionService.getById(actionId);
		if(SalaryActionStatusEnum.FINISH.code().equals(salaryAction.getStatus())){
			return ErrorDesc.failureMessage("已生效，不可重复操作");
		}
		dao.execute("update hr_salary_detail set status=? where action_id=?", SalaryPersonDetailStatusEnum.VALID.code(),actionId);
		dao.execute("update hr_salary_action set status=? where id=?", SalaryActionStatusEnum.FINISH.code(),actionId);
		return ErrorDesc.success();
	}
	@Override
	public Result reset(String id) {
		SalaryDetail detail=this.getById(id);
		this.dao().fill(detail).with(SalaryDetailMeta.PERSON)
				.with(SalaryDetailMeta.SALARY_TPL)
				.with(SalaryDetailMeta.SALARY_ACTION).execute();
		Person person =detail.getPerson();
		SalaryAction act =detail.getSalaryAction();
		return salaryActionService.createPersonData(person,act);
	}

	/**
	 * 添加，如果语句错误，则抛出异常
	 * @param salaryDetail 数据对象
	 * @return 插入是否成功
	 * */
	@Override
	public Result insert(SalaryDetail salaryDetail) {
		return this.insert(salaryDetail,true);
	}

	/**
	 * 批量插入实体，事务内
	 * @param salaryDetailList 实体数据清单
	 * @return 插入是否成功
	 * */
	@Override
	public Result insertList(List<SalaryDetail> salaryDetailList) {
		return super.insertList(salaryDetailList);
	}

	
	/**
	 * 按主键删除薪酬明细
	 *
	 * @param id 主键
	 * @return 删除是否成功
	 */
	public Result deleteByIdPhysical(String id) {
		SalaryDetail salaryDetail = new SalaryDetail();
		if(id==null) return ErrorDesc.failure().message("id 不允许为 null 。");
		salaryDetail.setId(id);
		try {
			boolean suc = dao.deleteEntity(salaryDetail);
			return suc?ErrorDesc.success():ErrorDesc.failure();
		}
		catch(Exception e) {
			Result r= ErrorDesc.failure();
			r.extra().setException(e);
			return r;
		}
	}
	
	/**
	 * 按主键删除薪酬明细
	 *
	 * @param id 主键
	 * @return 删除是否成功
	 */
	public Result deleteByIdLogical(String id) {
		SalaryDetail salaryDetail = new SalaryDetail();
		if(id==null) return ErrorDesc.failure().message("id 不允许为 null 。");
		salaryDetail.setId(id);
		salaryDetail.setDeleted(true);
		salaryDetail.setDeleteBy((String)dao.getDBTreaty().getLoginUserId());
		salaryDetail.setDeleteTime(new Date());
		try {
			boolean suc = dao.updateEntity(salaryDetail,SaveMode.NOT_NULL_FIELDS);
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
	 * @param salaryDetail 数据对象
	 * @param mode 保存模式
	 * @return 保存是否成功
	 * */
	@Override
	public Result update(SalaryDetail salaryDetail , SaveMode mode) {
		return this.update(salaryDetail,mode,true);
	}

	/**
	 * 更新，根据 throwsException 参数抛出异常或返回 Result 对象
	 * @param salaryDetail 数据对象
	 * @param mode 保存模式
	 * @param throwsException 是否抛出异常，如果不抛出异常，则返回一个失败的 Result 对象
	 * @return 保存是否成功
	 * */
	@Override
	public Result update(SalaryDetail salaryDetail , SaveMode mode,boolean throwsException) {
		Result r=super.update(salaryDetail , mode , throwsException);
		return r;
	}

	/**
	 * 更新实体集，事务内
	 * @param salaryDetailList 数据对象列表
	 * @param mode 保存模式
	 * @return 保存是否成功
	 * */
	@Override
	public Result updateList(List<SalaryDetail> salaryDetailList , SaveMode mode) {
		return super.updateList(salaryDetailList , mode);
	}

	
	/**
	 * 按主键更新薪酬明细
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
	 * 按主键获取薪酬明细
	 *
	 * @param id 主键
	 * @return SalaryDetail 数据对象
	 */
	public SalaryDetail getById(String id) {
		SalaryDetail sample = new SalaryDetail();
		if(id==null) throw new IllegalArgumentException("id 不允许为 null ");
		sample.setId(id);
		return dao.queryEntity(sample);
	}

	/**
	 * 等价于 queryListByIds
	 * */
	@Override
	public List<SalaryDetail> getByIds(List<String> ids) {
		return this.queryListByIds(ids);
	}

	@Override
	public List<SalaryDetail> queryListByIds(List<String> ids) {
		return super.queryListByUKeys("id",ids);
	}

	@Override
	public Map<String, SalaryDetail> queryMapByIds(List<String> ids) {
		return super.queryMapByUKeys("id",ids, SalaryDetail::getId);
	}



	/**
	 * 查询实体集合，默认情况下，字符串使用模糊匹配，非字符串使用精确匹配
	 *
	 * @param sample  查询条件
	 * @return 查询结果
	 * */
	@Override
	public List<SalaryDetail> queryList(SalaryDetailVO sample) {
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
	public PagedList<SalaryDetail> queryPagedList(SalaryDetailVO sample, int pageSize, int pageIndex) {
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
	public PagedList<SalaryDetail> queryPagedList(SalaryDetail sample, ConditionExpr condition, int pageSize, int pageIndex) {
		return super.queryPagedList(sample, condition, pageSize, pageIndex);
	}

	/**
	 * 检查 实体 是否已经存在 , 判断 主键值不同，但指定字段的值相同的记录是否存在
	 *
	 * @param salaryDetail 数据对象
	 * @return 判断结果
	 */
	public Boolean checkExists(SalaryDetail salaryDetail) {
		//TDOD 此处添加判断段的代码
		//boolean exists=super.checkExists(salaryDetail, SYS_ROLE.NAME);
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
	@Override
	public ExcelWriter exportExcel(SalaryDetail sample) {
		return super.exportExcel(sample);
	}
	@Override
	public ExcelWriter exportExcelTemplate() {
		return super.exportExcelTemplate();
	}

	@Override
	public List<ValidateResult> importExcel(InputStream input,int sheetIndex,boolean batch) {
		return super.importExcel(input,sheetIndex,batch);
	}
	@Override
	public ExcelStructure buildExcelStructure(boolean isForExport) {
		return super.buildExcelStructure(isForExport);
	}





}