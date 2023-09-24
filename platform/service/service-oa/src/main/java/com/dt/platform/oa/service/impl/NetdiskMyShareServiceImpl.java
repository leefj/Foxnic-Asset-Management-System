package com.dt.platform.oa.service.impl;

import javax.annotation.Resource;

import com.dt.platform.domain.oa.NetdiskShareMe;
import com.dt.platform.domain.oa.NetdiskShareMeVO;
import com.dt.platform.oa.service.INetdiskShareMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.foxnic.dao.entity.ReferCause;
import com.github.foxnic.commons.collection.MapUtil;
import java.util.Arrays;


import com.dt.platform.domain.oa.NetdiskMyShare;
import com.dt.platform.domain.oa.NetdiskMyShareVO;
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
import com.dt.platform.oa.service.INetdiskMyShareService;
import org.github.foxnic.web.framework.dao.DBConfigs;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 我的分享服务实现
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-09-18 14:20:31
*/


@Service("OaNetdiskMyShareService")

public class NetdiskMyShareServiceImpl extends SuperService<NetdiskMyShare> implements INetdiskMyShareService {

	/**
	 * 注入DAO对象
	 * */
	@Resource(name=DBConfigs.PRIMARY_DAO) 
	private final DAO dao=null;

	/**
	 * 获得 DAO 对象
	 * */
	public DAO dao() { return dao; }

	@Autowired
	private INetdiskShareMeService netdiskShareMeService;


	@Override
	public Object generateId(Field field) {
		return IDGenerator.getSnowflakeIdString();
	}

	/**
	 * 添加，根据 throwsException 参数抛出异常或返回 Result 对象
	 *
	 * @param netdiskMyShare  数据对象
	 * @param throwsException 是否抛出异常，如果不抛出异常，则返回一个失败的 Result 对象
	 * @return 结果 , 如果失败返回 false，成功返回 true
	 */
	@Override
	public Result insert(NetdiskMyShare netdiskMyShare,boolean throwsException) {
		Result r=super.insert(netdiskMyShare,throwsException);
		return r;
	}

	@Override
	public PagedList<NetdiskShareMe> queryShareMember(NetdiskShareMeVO sample, int pageSize, int pageIndex) {
		return netdiskShareMeService.queryPagedList(sample,pageSize,pageIndex);
	}

	@Override
	public Result removeShareMember(List<String> ids) {
		for(int i=0;i<ids.size();i++){
			netdiskShareMeService.deleteByIdPhysical(ids.get(i));
		}
		return ErrorDesc.success();
	}

	@Override
	public Result cancel(String id) {
		this.deleteByIdPhysical(id);
		dao.execute("delete from oa_netdisk_share_me where share_id=?",id);
		return ErrorDesc.success();
	}

	/**
	 * 添加，如果语句错误，则抛出异常
	 * @param netdiskMyShare 数据对象
	 * @return 插入是否成功
	 * */
	@Override
	public Result insert(NetdiskMyShare netdiskMyShare) {
		return this.insert(netdiskMyShare,true);
	}

	/**
	 * 批量插入实体，事务内
	 * @param netdiskMyShareList 实体数据清单
	 * @return 插入是否成功
	 * */
	@Override
	public Result insertList(List<NetdiskMyShare> netdiskMyShareList) {
		return super.insertList(netdiskMyShareList);
	}

	
	/**
	 * 按主键删除我的分享
	 *
	 * @param id 主键
	 * @return 删除是否成功
	 */
	public Result deleteByIdPhysical(String id) {
		NetdiskMyShare netdiskMyShare = new NetdiskMyShare();
		if(id==null) return ErrorDesc.failure().message("id 不允许为 null 。");
		netdiskMyShare.setId(id);
		try {
			boolean suc = dao.deleteEntity(netdiskMyShare);
			return suc?ErrorDesc.success():ErrorDesc.failure();
		}
		catch(Exception e) {
			Result r= ErrorDesc.failure();
			r.extra().setException(e);
			return r;
		}
	}
	
	/**
	 * 按主键删除我的分享
	 *
	 * @param id 主键
	 * @return 删除是否成功
	 */
	public Result deleteByIdLogical(String id) {
		NetdiskMyShare netdiskMyShare = new NetdiskMyShare();
		if(id==null) return ErrorDesc.failure().message("id 不允许为 null 。");
		netdiskMyShare.setId(id);
		netdiskMyShare.setDeleted(true);
		netdiskMyShare.setDeleteBy((String)dao.getDBTreaty().getLoginUserId());
		netdiskMyShare.setDeleteTime(new Date());
		try {
			boolean suc = dao.updateEntity(netdiskMyShare,SaveMode.NOT_NULL_FIELDS);
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
	 * @param netdiskMyShare 数据对象
	 * @param mode 保存模式
	 * @return 保存是否成功
	 * */
	@Override
	public Result update(NetdiskMyShare netdiskMyShare , SaveMode mode) {
		return this.update(netdiskMyShare,mode,true);
	}

	/**
	 * 更新，根据 throwsException 参数抛出异常或返回 Result 对象
	 * @param netdiskMyShare 数据对象
	 * @param mode 保存模式
	 * @param throwsException 是否抛出异常，如果不抛出异常，则返回一个失败的 Result 对象
	 * @return 保存是否成功
	 * */
	@Override
	public Result update(NetdiskMyShare netdiskMyShare , SaveMode mode,boolean throwsException) {
		Result r=super.update(netdiskMyShare , mode , throwsException);
		return r;
	}

	/**
	 * 更新实体集，事务内
	 * @param netdiskMyShareList 数据对象列表
	 * @param mode 保存模式
	 * @return 保存是否成功
	 * */
	@Override
	public Result updateList(List<NetdiskMyShare> netdiskMyShareList , SaveMode mode) {
		return super.updateList(netdiskMyShareList , mode);
	}

	
	/**
	 * 按主键更新我的分享
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
	 * 按主键获取我的分享
	 *
	 * @param id 主键
	 * @return NetdiskMyShare 数据对象
	 */
	public NetdiskMyShare getById(String id) {
		NetdiskMyShare sample = new NetdiskMyShare();
		if(id==null) throw new IllegalArgumentException("id 不允许为 null ");
		sample.setId(id);
		return dao.queryEntity(sample);
	}

	/**
	 * 等价于 queryListByIds
	 * */
	@Override
	public List<NetdiskMyShare> getByIds(List<String> ids) {
		return this.queryListByIds(ids);
	}

	@Override
	public List<NetdiskMyShare> queryListByIds(List<String> ids) {
		return super.queryListByUKeys("id",ids);
	}

	@Override
	public Map<String, NetdiskMyShare> queryMapByIds(List<String> ids) {
		return super.queryMapByUKeys("id",ids, NetdiskMyShare::getId);
	}



	/**
	 * 查询实体集合，默认情况下，字符串使用模糊匹配，非字符串使用精确匹配
	 *
	 * @param sample  查询条件
	 * @return 查询结果
	 * */
	@Override
	public List<NetdiskMyShare> queryList(NetdiskMyShareVO sample) {
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
	public PagedList<NetdiskMyShare> queryPagedList(NetdiskMyShareVO sample, int pageSize, int pageIndex) {
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
	public PagedList<NetdiskMyShare> queryPagedList(NetdiskMyShare sample, ConditionExpr condition, int pageSize, int pageIndex) {
		return super.queryPagedList(sample, condition, pageSize, pageIndex);
	}

	/**
	 * 检查 实体 是否已经存在 , 判断 主键值不同，但指定字段的值相同的记录是否存在
	 *
	 * @param netdiskMyShare 数据对象
	 * @return 判断结果
	 */
	public Boolean checkExists(NetdiskMyShare netdiskMyShare) {
		//TDOD 此处添加判断段的代码
		//boolean exists=super.checkExists(netdiskMyShare, SYS_ROLE.NAME);
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