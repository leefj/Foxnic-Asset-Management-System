package com.dt.platform.ops.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.platform.domain.ops.DbInfo;
import com.dt.platform.domain.ops.DbInfoVO;
import com.dt.platform.ops.service.IDbInfoService;
import com.github.foxnic.api.error.ErrorDesc;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.commons.busi.id.IDGenerator;
import com.github.foxnic.commons.collection.MapUtil;
import com.github.foxnic.commons.lang.StringUtil;
import com.github.foxnic.dao.data.PagedList;
import com.github.foxnic.dao.data.SaveMode;
import com.github.foxnic.dao.entity.ReferCause;
import com.github.foxnic.dao.entity.SuperService;
import com.github.foxnic.dao.spec.DAO;
import com.github.foxnic.sql.expr.ConditionExpr;
import com.github.foxnic.sql.expr.Insert;
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
 * 数据库 服务实现
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-09-10 14:39:11
*/


@Service("OpsDbInfoService")
public class DbInfoServiceImpl extends SuperService<DbInfo> implements IDbInfoService {

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
	 * @param dbInfo  数据对象
	 * @param throwsException 是否抛出异常，如果不抛出异常，则返回一个失败的 Result 对象
	 * @return 结果 , 如果失败返回 false，成功返回 true
	 */
	@Override
	public Result insert(DbInfo dbInfo,boolean throwsException) {
		Result r=super.insert(dbInfo,throwsException);
		if(r.isSuccess()){
			dao.execute("update ops_db_backup_info set database_id=? where selected_code=?",dbInfo.getId(),dbInfo.getSelectedCode());
			dao.execute("delete from ops_db_info_label where db_id=?",dbInfo.getId());
			dao.execute("delete from ops_db_data_loc where db_info_id=?",dbInfo.getId());
			if(dbInfo.getLabelIds()!=null){
				for(String labelId:dbInfo.getLabelIds()){
					Insert ins=new Insert("ops_db_info_label");
					ins.set("id",IDGenerator.getSnowflakeIdString());
					ins.set("db_id",dbInfo.getId());
					ins.set("label",labelId);
					dao.execute(ins);
				}
			}

			if(dbInfo.getDataLocIds()!=null){
				for(String value:dbInfo.getDataLocIds()){
					Insert ins=new Insert("ops_db_data_loc");
					ins.set("id",IDGenerator.getSnowflakeIdString());
					ins.set("db_info_id",dbInfo.getId());
					ins.set("loc_id",value);
					dao.execute(ins);
				}
			}


		}
		return r;
	}

	/**
	 * 添加，如果语句错误，则抛出异常
	 * @param dbInfo 数据对象
	 * @return 插入是否成功
	 * */
	@Override
	public Result insert(DbInfo dbInfo) {
		return this.insert(dbInfo,true);
	}

	/**
	 * 批量插入实体，事务内
	 * @param dbInfoList 实体数据清单
	 * @return 插入是否成功
	 * */
	@Override
	public Result insertList(List<DbInfo> dbInfoList) {
		return super.insertList(dbInfoList);
	}

	
	/**
	 * 按主键删除 数据库
	 *
	 * @param id 主键
	 * @return 删除是否成功
	 */
	public Result deleteByIdPhysical(String id) {
		DbInfo dbInfo = new DbInfo();
		if(id==null) return ErrorDesc.failure().message("id 不允许为 null 。");
		dbInfo.setId(id);
		try {
			boolean suc = dao.deleteEntity(dbInfo);
			if(suc){
				dao.execute("update ops_db_backup_info set deleted=1 where database_id=?",id);
			}
			return suc?ErrorDesc.success():ErrorDesc.failure();
		}
		catch(Exception e) {
			Result r= ErrorDesc.failure();
			r.extra().setException(e);
			return r;
		}
	}
	
	/**
	 * 按主键删除 数据库
	 *
	 * @param id 主键
	 * @return 删除是否成功
	 */
	public Result deleteByIdLogical(String id) {
		DbInfo dbInfo = new DbInfo();
		if(id==null) return ErrorDesc.failure().message("id 不允许为 null 。");
		dbInfo.setId(id);
		dbInfo.setDeleted(true);
		dbInfo.setDeleteBy((String)dao.getDBTreaty().getLoginUserId());
		dbInfo.setDeleteTime(new Date());
		try {
			boolean suc = dao.updateEntity(dbInfo,SaveMode.NOT_NULL_FIELDS);
			if(suc){
				dao.execute("update ops_db_backup_info set deleted=1 where database_id=?",id);
			}
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
	 * @param dbInfo 数据对象
	 * @param mode 保存模式
	 * @return 保存是否成功
	 * */
	@Override
	public Result update(DbInfo dbInfo , SaveMode mode) {
		return this.update(dbInfo,mode,true);
	}

	/**
	 * 更新，根据 throwsException 参数抛出异常或返回 Result 对象
	 * @param dbInfo 数据对象
	 * @param mode 保存模式
	 * @param throwsException 是否抛出异常，如果不抛出异常，则返回一个失败的 Result 对象
	 * @return 保存是否成功
	 * */
	@Override
	public Result update(DbInfo dbInfo , SaveMode mode,boolean throwsException) {
		Result r=super.update(dbInfo , mode , throwsException);
		if(r.isSuccess()){
			dao.execute("delete from ops_db_info_label where db_id=?",dbInfo.getId());
			dao.execute("delete from ops_db_data_loc where db_info_id=?",dbInfo.getId());
			if(dbInfo.getLabelIds()!=null){
				for(String labelId:dbInfo.getLabelIds()){
					Insert ins=new Insert("ops_db_info_label");
					ins.set("id",IDGenerator.getSnowflakeIdString());
					ins.set("db_id",dbInfo.getId());
					ins.set("label",labelId);
					dao.execute(ins);
				}
			}

			if(dbInfo.getDataLocIds()!=null){
				for(String value:dbInfo.getDataLocIds()){
					Insert ins=new Insert("ops_db_data_loc");
					ins.set("id",IDGenerator.getSnowflakeIdString());
					ins.set("db_info_id",dbInfo.getId());
					ins.set("loc_id",value);
					dao.execute(ins);
				}
			}

		}

		return r;
	}

	/**
	 * 更新实体集，事务内
	 * @param dbInfoList 数据对象列表
	 * @param mode 保存模式
	 * @return 保存是否成功
	 * */
	@Override
	public Result updateList(List<DbInfo> dbInfoList , SaveMode mode) {
		return super.updateList(dbInfoList , mode);
	}

	
	/**
	 * 按主键更新字段 数据库
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
	 * 按主键获取 数据库
	 *
	 * @param id 主键
	 *
	 *
	 *
	 *
	 * @return DbInfo 数据对象
	 */
	public DbInfo getById(String id) {
		DbInfo sample = new DbInfo();
		if(id==null) throw new IllegalArgumentException("id 不允许为 null ");
		sample.setId(id);
		return dao.queryEntity(sample);
	}

	/**
	 * 等价于 queryListByIds
	 * */
	@Override
	public List<DbInfo> getByIds(List<String> ids) {
		return this.queryListByIds(ids);
	}

	@Override
	public List<DbInfo> queryListByIds(List<String> ids) {
		return super.queryListByUKeys("id",ids);
	}

	@Override
	public Map<String, DbInfo> queryMapByIds(List<String> ids) {
		return super.queryMapByUKeys("id",ids, DbInfo::getId);
	}



	/**
	 * 查询实体集合，默认情况下，字符串使用模糊匹配，非字符串使用精确匹配
	 *
	 * @param sample  查询条件
	 * @return 查询结果
	 * */
	@Override
	public List<DbInfo> queryList(DbInfoVO sample) {
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
	public PagedList<DbInfo> queryPagedList(DbInfoVO sample, int pageSize, int pageIndex) {
		ConditionExpr expr=new ConditionExpr();
		expr.and("1=1");
		String searchValue=sample.getSearchValue();
		JSONObject searchObj=null;
		JSONObject labelIdsObj=null;
		JSONArray labelIdsValueArr=null;
		if(!StringUtil.isBlank(searchValue)){
			searchObj=JSONObject.parseObject(searchValue);
			if(searchObj.containsKey("labelIds")){
				labelIdsObj=searchObj.getJSONObject("labelIds");
				if(labelIdsObj.containsKey("value")){
					labelIdsValueArr=labelIdsObj.getJSONArray("value");
				}
			}
		}
		if(labelIdsValueArr!=null&&labelIdsValueArr.size()>0){
			String labels="";
			for(int i=0;i<labelIdsValueArr.size();i++){
				if(i==0){
					labels=labels+"'"+labelIdsValueArr.get(i)+"'";
				}else{
					labels=labels+",'"+labelIdsValueArr.get(i)+"'";
				}
			}
			expr.and("id in (select db_id from ops_db_info_label where label in ("+labels+") and deleted=0 )");
		}
		return super.queryPagedList(sample, expr,pageSize, pageIndex);
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
	public PagedList<DbInfo> queryPagedList(DbInfo sample, ConditionExpr condition, int pageSize, int pageIndex) {
		return super.queryPagedList(sample, condition, pageSize, pageIndex);
	}

	/**
	 * 检查 实体 是否已经存在 , 判断 主键值不同，但指定字段的值相同的记录是否存在
	 *
	 * @param dbInfo 数据对象
	 * @return 判断结果
	 */
	public Boolean checkExists(DbInfo dbInfo) {
		//TDOD 此处添加判断段的代码
		//boolean exists=super.checkExists(dbInfo, SYS_ROLE.NAME);
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