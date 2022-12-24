package com.dt.platform.eam.service.impl;

import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.constants.enums.common.CodeModuleEnum;
import com.dt.platform.constants.enums.eam.*;
import com.dt.platform.domain.eam.*;
import com.dt.platform.domain.eam.meta.AssetBorrowReturnMeta;
import com.dt.platform.domain.eam.meta.AssetCollectionMeta;
import com.dt.platform.eam.service.IAssetBorrowReturnService;
import com.dt.platform.eam.service.IAssetSelectedDataService;
import com.dt.platform.eam.service.IAssetService;
import com.dt.platform.eam.service.IOperateService;
import com.dt.platform.proxy.common.CodeModuleServiceProxy;
import com.github.foxnic.api.constant.CodeTextEnum;
import com.github.foxnic.api.error.ErrorDesc;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.commons.bean.BeanUtil;
import com.github.foxnic.commons.busi.id.IDGenerator;
import com.github.foxnic.commons.collection.MapUtil;
import com.github.foxnic.commons.lang.StringUtil;
import com.github.foxnic.commons.reflect.EnumUtil;
import com.github.foxnic.dao.data.PagedList;
import com.github.foxnic.dao.data.Rcd;
import com.github.foxnic.dao.data.SaveMode;
import com.github.foxnic.dao.entity.ReferCause;
import com.github.foxnic.dao.entity.SuperService;
import com.github.foxnic.dao.spec.DAO;
import com.github.foxnic.sql.expr.ConditionExpr;
import com.github.foxnic.sql.expr.SQL;
import com.github.foxnic.sql.meta.DBField;
import org.github.foxnic.web.domain.hrm.Person;
import org.github.foxnic.web.framework.dao.DBConfigs;
import org.github.foxnic.web.session.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;

/**
 * <p>
 * 资产归还服务实现
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-11-13 10:13:24
*/


@Service("EamAssetBorrowReturnService")
public class AssetBorrowReturnServiceImpl extends SuperService<AssetBorrowReturn> implements IAssetBorrowReturnService {

	/**
	 * 注入DAO对象
	 * */
	@Resource(name=DBConfigs.PRIMARY_DAO) 
	private DAO dao=null;

	@Autowired
	private IAssetService assetService;

	@Autowired
	private AssetItemServiceImpl assetItemService;


	@Autowired
	private IAssetSelectedDataService assetSelectedDataService;

	@Autowired
	private IOperateService operateService;

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
	 * @param assetBorrowReturn  数据对象
	 * @param throwsException 是否抛出异常，如果不抛出异常，则返回一个失败的 Result 对象
	 * @return 结果 , 如果失败返回 false，成功返回 true
	 */
	@Override
	public Result insert(AssetBorrowReturn assetBorrowReturn,boolean throwsException) {


		if(assetBorrowReturn.getAssetIds()==null||assetBorrowReturn.getAssetIds().size()==0){
			String assetSelectedCode=assetBorrowReturn.getSelectedCode();
			ConditionExpr condition=new ConditionExpr();
			condition.andIn("asset_selected_code",assetSelectedCode==null?"":assetSelectedCode);
			List<String> list=assetSelectedDataService.queryValues(EAMTables.EAM_ASSET_SELECTED_DATA.ASSET_ID,String.class,condition);
			assetBorrowReturn.setAssetIds(list);
		}


		//校验数据资产
		if(assetBorrowReturn.getAssetIds().size()==0){
			return ErrorDesc.failure().message("请选择资产");
		}
		Result ckResult=assetService.checkAssetDataForBusinessAction(AssetOperateEnum.EAM_ASSET_BORROW_RETURN.code(),assetBorrowReturn.getAssetIds());
		if(!ckResult.isSuccess()){
			return ckResult;
		}

		//制单人
		if(StringUtil.isBlank(assetBorrowReturn.getOriginatorId())){
			assetBorrowReturn.setOriginatorId(SessionUser.getCurrent().getUser().getActivatedEmployeeId());
		}

		//业务时间
		if(StringUtil.isBlank(assetBorrowReturn.getBusinessDate())){
			assetBorrowReturn.setBusinessDate(new Date());
		}

		//办理状态
		if(StringUtil.isBlank(assetBorrowReturn.getStatus())){
			assetBorrowReturn.setStatus(AssetHandleStatusEnum.INCOMPLETE.code());
		}

		//生成编码规则
		if(StringUtil.isBlank(assetBorrowReturn.getBusinessCode())){
			Result codeResult= CodeModuleServiceProxy.api().generateCode(AssetOperateEnum.EAM_ASSET_BORROW_RETURN.code());
			if(!codeResult.isSuccess()){
				return codeResult;
			}else{
				assetBorrowReturn.setBusinessCode(codeResult.getData().toString());
			}
		}


		Result r=super.insert(assetBorrowReturn,throwsException);

		if (r.isSuccess()){
			//保存资产数据
			List<AssetItem> saveList=new ArrayList<AssetItem>();
			for(int i=0;i<assetBorrowReturn.getAssetIds().size();i++){
				AssetItem asset=new AssetItem();
				asset.setId(IDGenerator.getSnowflakeIdString());
				asset.setHandleId(assetBorrowReturn.getId());
				asset.setAssetId(assetBorrowReturn.getAssetIds().get(i));
				saveList.add(asset);
			}
			Result batchInsertReuslt= assetItemService.insertList(saveList);
			if(!batchInsertReuslt.isSuccess()){
				return batchInsertReuslt;
			}

			//记录可能借用的单据
			//资产记录保存借用单据
			dao.execute("update eam_asset_item a,eam_asset b set a.flag=b.borrow_id where a.asset_id=b.id and a.handle_id=?",assetBorrowReturn.getId());
		}
		return r;
	}

	@Override
	public Map<String, Object> getBill(String id) {

		AssetBorrowReturn assetBorrowReturn=this.getById(id);
		dao().fill(assetBorrowReturn)
				.with("originator")
				.with(AssetBorrowReturnMeta.POSITION)
				.with(AssetBorrowReturnMeta.USE_USER)
				.with(AssetBorrowReturnMeta.USE_ORGANIZATION)
				.with(AssetBorrowReturnMeta.MANAGER)
				.with(AssetBorrowReturnMeta.ASSET_LIST)

				.execute();
		dao().join(assetBorrowReturn.getOriginator(), Person.class);
		dao().join(assetBorrowReturn.getManager(), Person.class);
		dao().join(assetBorrowReturn.getUseUser(), Person.class);


		Map<String, Object> map= BeanUtil.toMap(assetBorrowReturn);
		if(assetBorrowReturn.getStatus()!=null){
			CodeTextEnum en= EnumUtil.parseByCode(AssetHandleStatusEnum.class,assetBorrowReturn.getStatus());
			map.put("statusName", en==null?assetBorrowReturn.getStatus():en.text());
		}
		return map;

	}


	/**
	 * 操作
	 * @param id  ID
	 * @param result 结果
	 * @return
	 * */
	private Result operateResult(String id,String result,String status,String message) {
		if(AssetHandleConfirmOperationEnum.SUCCESS.code().equals(result)){
			Result verifyResult= verifyBillData(id);
			if(!verifyResult.isSuccess()) return verifyResult;

			Result applayResult=applyChange(id);
			if(!applayResult.isSuccess()) return applayResult;

			dao.execute("update eam_asset_borrow_return set status=? where id=?",status,id);
			return ErrorDesc.success();
		}else if(AssetHandleConfirmOperationEnum.FAILED.code().equals(result)){
			return ErrorDesc.failureMessage(message);
		}else{
			return ErrorDesc.failureMessage("返回未知结果");
		}
	}

	private Result applyChange(String id){
		AssetBorrowReturn bill=getById(id);
		join(bill, AssetBorrowReturnMeta.ASSET_ITEM_LIST);

		String str="";
		for(AssetItem item:bill.getAssetItemList()){
			Asset asset=assetService.getById(item.getAssetId());
			if(!AssetStatusEnum.BORROW.code().equals(asset.getAssetStatus())){
				str=str+"资产编码"+asset.getAssetCode()+",当前状态为非借用状态,不在变更  ";
				continue;
			}
			List<Asset> changeList=new ArrayList<>();
			changeList.add(asset);
			HashMap<String,Object> map=new HashMap<>();

			//领用ID
			String collectionId=item.getFlag();
			String beforeAssetStatus=AssetStatusEnum.IDLE.code();
			if(!StringUtil.isBlank(collectionId)){
				Rcd rs=dao.queryRecord("select * from eam_asset_item where handle_id=? and asset_id=? and deleted=0 ",collectionId,item.getAssetId());
				if(rs!=null){
					beforeAssetStatus=rs.getString("before_asset_status");
				}

			}
			map.put("asset_status",beforeAssetStatus);

			//如果单据选了，按照单据更新，如果没选，则相应数据置空
			if(StringUtil.isBlank(bill.getUseUserId())){
				map.put("use_user_id","");
			}else{
				map.put("use_user_id",bill.getUseUserId());
			}
			if(StringUtil.isBlank(bill.getManagerId())){
				map.put("manager_id","");
			}else{
				map.put("manager_id",bill.getManagerId());
			}
			if(StringUtil.isBlank(bill.getUseOrganizationId())){
				map.put("use_organization_id","");
			}else{
				map.put("use_organization_id",bill.getUseOrganizationId());
			}
			if(StringUtil.isBlank(bill.getPositionDetail())){
				map.put("position_detail","");
			}else{
				map.put("position_detail",bill.getPositionDetail());
			}
			if(StringUtil.isBlank(bill.getPositionId())){
				map.put("position_id","");
			}else{
				map.put("position_id",bill.getPositionId());
			}

			HashMap<String,List<SQL>> resultMap=assetService.parseAssetChangeRecordWithChangeAsset(changeList,map,bill.getBusinessCode(),AssetOperateEnum.EAM_ASSET_BORROW_RETURN.code(),"资产归还");
			for(String key:resultMap.keySet()){
				List<SQL> sqls=resultMap.get(key);
				if(sqls.size()>0){
					dao.batchExecute(sqls);
				}
			}
		}


		//保存快照
		AssetBorrowReturn afterData=getById(id);
		join(afterData, AssetBorrowReturnMeta.ASSET_LIST);
		for(Asset asset:afterData.getAssetList()){
			String oldAssetId=asset.getId();
			String newAssetId=IDGenerator.getSnowflakeIdString();
			asset.setId(newAssetId);
			asset.setOwnerCode(AssetOwnerCodeEnum.ASSET_DATE_AFTER.code());
			assetService.sourceInsert(asset);
			dao.execute("update eam_asset_item a set a.asset_id=?,flag=? where a.asset_id=? and a.handle_id=?",newAssetId,oldAssetId,oldAssetId,id);
		}
		dao.execute("update eam_asset_item a,eam_asset b set b.borrow_id='' where b.owner_code='asset'  and a.flag=b.id and a.handle_id=?",id);
		return  ErrorDesc.success();
	}



	@Override
	public Result confirmOperation(String id) {
		AssetBorrowReturn billData=getById(id);
		if(AssetHandleStatusEnum.INCOMPLETE.code().equals(billData.getStatus())){
			return operateResult(id, AssetHandleConfirmOperationEnum.SUCCESS.code(),AssetHandleStatusEnum.COMPLETE.code(),"操作成功");
		}else{
			return ErrorDesc.failureMessage("当前状态为:"+billData.getStatus()+",不能进行该操作");
		}
	}

	private Result verifyBillData(String handleId){
		//c  新建,r  原纪录,d  删除,cd 新建删除
		//验证数据
		ConditionExpr itemRecordcondition=new ConditionExpr();
		itemRecordcondition.andIn("handle_id",handleId);
		itemRecordcondition.andIn("crd","c","r");
		List<String> ckDatalist=assetItemService.queryValues(EAMTables.EAM_ASSET_ITEM.ASSET_ID,String.class,itemRecordcondition);
		if(ckDatalist.size()==0){
			return ErrorDesc.failure().message("请选择资产");
		}
		return assetService.checkAssetDataForBusinessAction(CodeModuleEnum.EAM_ASSET_BORROW_RETURN.code(),ckDatalist);
	}


	/**
	 * 添加，如果语句错误，则抛出异常
	 * @param assetBorrowReturn 数据对象
	 * @return 插入是否成功
	 * */
	@Override
	public Result insert(AssetBorrowReturn assetBorrowReturn) {
		return this.insert(assetBorrowReturn,true);
	}

	/**
	 * 批量插入实体，事务内
	 * @param assetBorrowReturnList 实体数据清单
	 * @return 插入是否成功
	 * */
	@Override
	public Result insertList(List<AssetBorrowReturn> assetBorrowReturnList) {
		return super.insertList(assetBorrowReturnList);
	}

	
	/**
	 * 按主键删除资产归还
	 *
	 * @param id 主键
	 * @return 删除是否成功
	 */
	public Result deleteByIdPhysical(String id) {
		AssetBorrowReturn assetBorrowReturn = new AssetBorrowReturn();
		if(id==null) return ErrorDesc.failure().message("id 不允许为 null 。");
		assetBorrowReturn.setId(id);
		try {
			boolean suc = dao.deleteEntity(assetBorrowReturn);
			return suc?ErrorDesc.success():ErrorDesc.failure();
		}
		catch(Exception e) {
			Result r= ErrorDesc.failure();
			r.extra().setException(e);
			return r;
		}
	}
	
	/**
	 * 按主键删除资产归还
	 *
	 * @param id 主键
	 * @return 删除是否成功
	 */
	public Result deleteByIdLogical(String id) {
		AssetBorrowReturn assetBorrowReturn = new AssetBorrowReturn();
		if(id==null) return ErrorDesc.failure().message("id 不允许为 null 。");
		assetBorrowReturn.setId(id);
		assetBorrowReturn.setDeleted(true);
		assetBorrowReturn.setDeleteBy((String)dao.getDBTreaty().getLoginUserId());
		assetBorrowReturn.setDeleteTime(new Date());
		try {
			boolean suc = dao.updateEntity(assetBorrowReturn,SaveMode.NOT_NULL_FIELDS);
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
	 * @param assetBorrowReturn 数据对象
	 * @param mode 保存模式
	 * @return 保存是否成功
	 * */
	@Override
	public Result update(AssetBorrowReturn assetBorrowReturn , SaveMode mode) {
		return this.update(assetBorrowReturn,mode,true);
	}

	/**
	 * 更新，根据 throwsException 参数抛出异常或返回 Result 对象
	 * @param assetBorrowReturn 数据对象
	 * @param mode 保存模式
	 * @param throwsException 是否抛出异常，如果不抛出异常，则返回一个失败的 Result 对象
	 * @return 保存是否成功
	 * */
	@Override
	public Result update(AssetBorrowReturn assetBorrowReturn , SaveMode mode,boolean throwsException) {

		Result verifyResult = verifyBillData(assetBorrowReturn.getId());
		if(!verifyResult.isSuccess())return verifyResult;

		Result r=super.update(assetBorrowReturn , mode , throwsException);
		if(r.success()){
			//保存表单数据
			dao.execute("update eam_asset_item set crd='r' where crd='c' and handle_id=?",assetBorrowReturn.getId());
			dao.execute("delete from eam_asset_item where crd in ('d','rd') and  handle_id=?",assetBorrowReturn.getId());
		}

		return r;
	}

	/**
	 * 更新实体集，事务内
	 * @param assetBorrowReturnList 数据对象列表
	 * @param mode 保存模式
	 * @return 保存是否成功
	 * */
	@Override
	public Result updateList(List<AssetBorrowReturn> assetBorrowReturnList , SaveMode mode) {
		return super.updateList(assetBorrowReturnList , mode);
	}

	
	/**
	 * 按主键更新资产归还
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
	 * 按主键获取资产归还
	 *
	 * @param id 主键
	 * @return AssetBorrowReturn 数据对象
	 */
	public AssetBorrowReturn getById(String id) {
		AssetBorrowReturn sample = new AssetBorrowReturn();
		if(id==null) throw new IllegalArgumentException("id 不允许为 null ");
		sample.setId(id);
		return dao.queryEntity(sample);
	}

	/**
	 * 等价于 queryListByIds
	 * */
	@Override
	public List<AssetBorrowReturn> getByIds(List<String> ids) {
		return this.queryListByIds(ids);
	}

	@Override
	public List<AssetBorrowReturn> queryListByIds(List<String> ids) {
		return super.queryListByUKeys("id",ids);
	}

	@Override
	public Map<String, AssetBorrowReturn> queryMapByIds(List<String> ids) {
		return super.queryMapByUKeys("id",ids, AssetBorrowReturn::getId);
	}



	/**
	 * 查询实体集合，默认情况下，字符串使用模糊匹配，非字符串使用精确匹配
	 *
	 * @param sample  查询条件
	 * @return 查询结果
	 * */
	@Override
	public List<AssetBorrowReturn> queryList(AssetBorrowReturnVO sample) {
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
	public PagedList<AssetBorrowReturn> queryPagedList(AssetBorrowReturnVO sample, int pageSize, int pageIndex) {
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
	public PagedList<AssetBorrowReturn> queryPagedList(AssetBorrowReturn sample, ConditionExpr condition, int pageSize, int pageIndex) {
		return super.queryPagedList(sample, condition, pageSize, pageIndex);
	}

	/**
	 * 检查 实体 是否已经存在 , 判断 主键值不同，但指定字段的值相同的记录是否存在
	 *
	 * @param assetBorrowReturn 数据对象
	 * @return 判断结果
	 */
	public Boolean checkExists(AssetBorrowReturn assetBorrowReturn) {
		//TDOD 此处添加判断段的代码
		//boolean exists=super.checkExists(assetBorrowReturn, SYS_ROLE.NAME);
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