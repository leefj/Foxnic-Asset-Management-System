package com.dt.platform.eam.service.impl;


import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dt.platform.domain.eam.InventoryAsset;
import com.dt.platform.domain.eam.InventoryAssetVO;
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
import com.dt.platform.eam.service.IInventoryAssetService;
import org.github.foxnic.web.framework.dao.DBConfigs;
import java.util.Date;

/**
 * <p>
 * 盘点明细 服务实现
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2021-11-19 13:14:22
*/


@Service("EamInventoryAssetService")
public class InventoryAssetServiceImpl extends SuperService<InventoryAsset> implements IInventoryAssetService {

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
	 * @param inventoryAsset  数据对象
	 * @param throwsException 是否抛出异常，如果不抛出异常，则返回一个失败的 Result 对象
	 * @return 结果 , 如果失败返回 false，成功返回 true
	 */
	@Override
	public Result insert(InventoryAsset inventoryAsset,boolean throwsException) {
		Result r=super.insert(inventoryAsset,throwsException);
		return r;
	}

	/**
	 * 添加，如果语句错误，则抛出异常
	 * @param inventoryAsset 数据对象
	 * @return 插入是否成功
	 * */
	@Override
	public Result insert(InventoryAsset inventoryAsset) {
		return this.insert(inventoryAsset,true);
	}

	/**
	 * 批量插入实体，事务内
	 * @param inventoryAssetList 实体数据清单
	 * @return 插入是否成功
	 * */
	@Override
	public Result insertList(List<InventoryAsset> inventoryAssetList) {
		return super.insertList(inventoryAssetList);
	}

	
	/**
	 * 按主键删除 盘点明细
	 *
	 * @param id 主键
	 * @return 删除是否成功
	 */
	public Result deleteByIdPhysical(String id) {
		InventoryAsset inventoryAsset = new InventoryAsset();
		if(id==null) return ErrorDesc.failure().message("id 不允许为 null 。");
		inventoryAsset.setId(id);
		try {
			boolean suc = dao.deleteEntity(inventoryAsset);
			return suc?ErrorDesc.success():ErrorDesc.failure();
		}
		catch(Exception e) {
			Result r= ErrorDesc.failure();
			r.extra().setException(e);
			return r;
		}
	}
	
	/**
	 * 按主键删除 盘点明细
	 *
	 * @param id 主键
	 * @return 删除是否成功
	 */
	public Result deleteByIdLogical(String id) {
		InventoryAsset inventoryAsset = new InventoryAsset();
		if(id==null) return ErrorDesc.failure().message("id 不允许为 null 。");
		inventoryAsset.setId(id);
		inventoryAsset.setDeleted(dao.getDBTreaty().getTrueValue());
		inventoryAsset.setDeleteBy((String)dao.getDBTreaty().getLoginUserId());
		inventoryAsset.setDeleteTime(new Date());
		try {
			boolean suc = dao.updateEntity(inventoryAsset,SaveMode.NOT_NULL_FIELDS);
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
	 * @param inventoryAsset 数据对象
	 * @param mode 保存模式
	 * @return 保存是否成功
	 * */
	@Override
	public Result update(InventoryAsset inventoryAsset , SaveMode mode) {
		return this.update(inventoryAsset,mode,true);
	}

	/**
	 * 更新，根据 throwsException 参数抛出异常或返回 Result 对象
	 * @param inventoryAsset 数据对象
	 * @param mode 保存模式
	 * @param throwsException 是否抛出异常，如果不抛出异常，则返回一个失败的 Result 对象
	 * @return 保存是否成功
	 * */
	@Override
	public Result update(InventoryAsset inventoryAsset , SaveMode mode,boolean throwsException) {
		Result r=super.update(inventoryAsset , mode , throwsException);
		return r;
	}

	/**
	 * 更新实体集，事务内
	 * @param inventoryAssetList 数据对象列表
	 * @param mode 保存模式
	 * @return 保存是否成功
	 * */
	@Override
	public Result updateList(List<InventoryAsset> inventoryAssetList , SaveMode mode) {
		return super.updateList(inventoryAssetList , mode);
	}

	
	/**
	 * 按主键更新字段 盘点明细
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
	 * 按主键获取 盘点明细
	 *
	 * @param id 主键
	 * @return InventoryAsset 数据对象
	 */
	public InventoryAsset getById(String id) {
		InventoryAsset sample = new InventoryAsset();
		if(id==null) throw new IllegalArgumentException("id 不允许为 null ");
		sample.setId(id);
		return dao.queryEntity(sample);
	}

	@Override
	public List<InventoryAsset> getByIds(List<String> ids) {
		return new ArrayList<>(getByIdsMap(ids).values());
	}



	/**
	 * 查询实体集合，默认情况下，字符串使用模糊匹配，非字符串使用精确匹配
	 *
	 * @param sample  查询条件
	 * @return 查询结果
	 * */
	@Override
	public List<InventoryAsset> queryList(InventoryAsset sample) {
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
	public PagedList<InventoryAsset> queryPagedList(InventoryAsset sample, int pageSize, int pageIndex) {
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
	public PagedList<InventoryAsset> queryPagedList(InventoryAsset sample, ConditionExpr condition, int pageSize, int pageIndex) {
		return super.queryPagedList(sample, condition, pageSize, pageIndex);
	}

	/**
	 * 检查 角色 是否已经存在
	 *
	 * @param inventoryAsset 数据对象
	 * @return 判断结果
	 */
	public Result<InventoryAsset> checkExists(InventoryAsset inventoryAsset) {
		//TDOD 此处添加判断段的代码
		//boolean exists=this.checkExists(inventoryAsset, SYS_ROLE.NAME);
		//return exists;
		return ErrorDesc.success();
	}

	@Override
	public ExcelWriter exportExcel(InventoryAsset sample) {
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