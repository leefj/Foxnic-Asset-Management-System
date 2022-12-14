package com.dt.platform.eam.service.impl;


import com.dt.platform.constants.db.EAMTables;
import com.dt.platform.constants.enums.common.CodeModuleEnum;
import com.dt.platform.constants.enums.eam.*;
import com.dt.platform.domain.eam.Asset;
import com.dt.platform.domain.eam.AssetCollection;
import com.dt.platform.domain.eam.AssetCollectionReturn;
import com.dt.platform.domain.eam.AssetItem;
import com.dt.platform.domain.eam.meta.AssetCollectionMeta;
import com.dt.platform.domain.eam.meta.AssetCollectionReturnMeta;
import com.dt.platform.eam.service.*;
import com.dt.platform.eam.service.bpm.AssetCollectionReturnBpmEventAdaptor;
import com.dt.platform.proxy.common.CodeModuleServiceProxy;
import com.dt.platform.proxy.eam.AssetCollectionReturnServiceProxy;
import com.github.foxnic.api.constant.CodeTextEnum;
import com.github.foxnic.api.error.ErrorDesc;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.commons.bean.BeanUtil;
import com.github.foxnic.commons.busi.id.IDGenerator;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.commons.collection.MapUtil;
import com.github.foxnic.commons.lang.StringUtil;
import com.github.foxnic.commons.reflect.EnumUtil;
import com.github.foxnic.dao.data.PagedList;
import com.github.foxnic.dao.data.SaveMode;
import com.github.foxnic.dao.entity.ReferCause;
import com.github.foxnic.dao.entity.SuperService;
import com.github.foxnic.dao.excel.ExcelStructure;
import com.github.foxnic.dao.excel.ExcelWriter;
import com.github.foxnic.dao.excel.ValidateResult;
import com.github.foxnic.dao.spec.DAO;
import com.github.foxnic.sql.expr.ConditionExpr;
import com.github.foxnic.sql.expr.SQL;
import com.github.foxnic.sql.meta.DBField;
import org.github.foxnic.web.domain.bpm.BpmActionResult;
import org.github.foxnic.web.domain.bpm.BpmEvent;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.hrm.Person;
import org.github.foxnic.web.framework.bpm.BpmAssistant;
import org.github.foxnic.web.framework.dao.DBConfigs;
import org.github.foxnic.web.session.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * <p>
 * ???????????? ????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-08-21 15:45:51
*/


@Service("EamAssetCollectionReturnService")
public class AssetCollectionReturnServiceImpl extends SuperService<AssetCollectionReturn> implements IAssetCollectionReturnService {
	
	/**
	 * ??????DAO??????
	 * */
	@Resource(name=DBConfigs.PRIMARY_DAO) 
	private DAO dao=null;
	
	/**
	 * ?????? DAO ??????
	 * */
	public DAO dao() { return dao; }

	@Autowired
	private IAssetService assetService;

	@Autowired
	private AssetItemServiceImpl assetItemService;


	@Autowired
	private IAssetSelectedDataService assetSelectedDataService;

	@Autowired
	private IOperateService operateService;



	/**
	 * ??????????????????
	 * */
	public BpmActionResult onProcessCallback(BpmEvent event) {
		return (new AssetCollectionReturnBpmEventAdaptor(this)).onProcessCallback(event);
	}

	@Override
	public void joinProcess(AssetCollectionReturn assetCollectionReturn) {
		this.joinProcess(Arrays.asList(assetCollectionReturn));
	}

	@Override
	public void joinProcess(List<AssetCollectionReturn> assetCollectionReturnList) {
		BpmAssistant.joinProcess(assetCollectionReturnList, IAssetAllocationService.FORM_DEFINITION_CODE);
	}

	@Override
	public void joinProcess(PagedList<AssetCollectionReturn> assetCollectionReturnList) {
		this.joinProcess(assetCollectionReturnList.getList());
	}


	@Override
	public Map<String, Object> getBill(String id) {

		AssetCollectionReturn data= AssetCollectionReturnServiceProxy.api().getById(id).getData();
		join(data, AssetCollectionReturnMeta.ASSET_LIST);
		List<Asset> list=data.getAssetList();
		if(list!=null){
			assetService.joinData(list);
			data.setAssetList(list);
		}
		Map<String, Object> map= BeanUtil.toMap(data);
		if(data.getStatus()!=null){
			CodeTextEnum en= EnumUtil.parseByCode(AssetHandleStatusEnum.class,data.getStatus());
			map.put("statusName", en==null?data.getStatus():en.text());
		}
		return map;
	}


	/**
	 * ????????????
	 * @param id ID
	 * @return ????????????
	 * */
	@Override
	public Result confirmOperation(String id) {
		AssetCollectionReturn billData=getById(id);
		if(AssetHandleStatusEnum.INCOMPLETE.code().equals(billData.getStatus())){
			if(operateService.approvalRequired(AssetOperateEnum.EAM_ASSET_COLLECTION_RETURN.code()) ) {
				return ErrorDesc.failureMessage("????????????????????????,?????????");
			}else{
				return operateResult(id,AssetHandleConfirmOperationEnum.SUCCESS.code(),AssetHandleStatusEnum.COMPLETE.code(),"????????????");
			}
		}else{
			return ErrorDesc.failureMessage("???????????????:"+billData.getStatus()+",?????????????????????");
		}
	}


	@Override
	public Object generateId(Field field) {
		return IDGenerator.getSnowflakeIdString();
	}




	/**
	 * ??????
	 * @param id  ID
	 * @param result ??????
	 * @return
	 * */
	private Result operateResult(String id,String result,String status,String message) {
		if(AssetHandleConfirmOperationEnum.SUCCESS.code().equals(result)){
			Result verifyResult= verifyBillData(id);
			if(!verifyResult.isSuccess()) return verifyResult;

			Result applayResult=applyChange(id);
			if(!applayResult.isSuccess()) return applayResult;
			AssetCollectionReturn bill=new AssetCollectionReturn();
			bill.setId(id);
			bill.setStatus(status);
			return super.update(bill,SaveMode.NOT_NULL_FIELDS);
		}else if(AssetHandleConfirmOperationEnum.FAILED.code().equals(result)){
			return ErrorDesc.failureMessage(message);
		}else{
			return ErrorDesc.failureMessage("??????????????????");
		}
	}

	private Result applyChange(String id){
		AssetCollectionReturn billData=getById(id);
		join(billData, AssetCollectionReturnMeta.ASSET_LIST);
		HashMap<String,Object> map=new HashMap<>();
		map.put("asset_status", AssetStatusEnum.IDLE.code());
		map.put("use_user_id","");
		map.put("position_id",billData.getPositionId());
		map.put("position_detail",billData.getPositionDetail());
		map.put("use_organization_id",billData.getUseOrganizationId());


		HashMap<String,List<SQL>> resultMap=assetService.parseAssetChangeRecordWithChangeAsset(billData.getAssetList(),map,billData.getBusinessCode(),AssetOperateEnum.EAM_ASSET_COLLECTION_RETURN.code(),"");
		List<SQL> updateSqls=resultMap.get("update");
		List<SQL> changeSqls=resultMap.get("change");
		for(String key:resultMap.keySet()){
			List<SQL> sqls=resultMap.get(key);
			if(sqls.size()>0){
				dao.batchExecute(sqls);
			}
		}

		//????????????
		AssetCollectionReturn afterData=getById(id);
		join(afterData, AssetCollectionMeta.ASSET_LIST);
		for(Asset asset:afterData.getAssetList()){
			String oldAssetId=asset.getId();
			String newAssetId=IDGenerator.getSnowflakeIdString();
			asset.setId(newAssetId);
			asset.setOwnerCode(AssetOwnerCodeEnum.ASSET_DATE_AFTER.code());
			assetService.sourceInsert(asset);
			dao.execute("update eam_asset_item a set a.asset_id=?,flag=? where a.asset_id=? and a.handle_id=?",newAssetId,oldAssetId,oldAssetId,id);
		}

		//
		//????????????
		dao.execute("update eam_asset_item a,eam_asset b set b.collection_id='' where b.owner_code='asset' and a.asset_id=b.id and a.handle_id=?",id);
		return ErrorDesc.success();
	}




	/**
	 * ????????????
	 * @param assetCollectionReturn ????????????
	 * @return ??????????????????
	 * */
	@Override
	@Transactional
	public Result insert(AssetCollectionReturn assetCollectionReturn) {

		if(assetCollectionReturn.getAssetIds()==null||assetCollectionReturn.getAssetIds().size()==0){
			String assetSelectedCode=assetCollectionReturn.getSelectedCode();
			ConditionExpr condition=new ConditionExpr();
			condition.andIn("asset_selected_code",assetSelectedCode==null?"":assetSelectedCode);
			List<String> list=assetSelectedDataService.queryValues(EAMTables.EAM_ASSET_SELECTED_DATA.ASSET_ID,String.class,condition);
			assetCollectionReturn.setAssetIds(list);
		}


		//??????????????????
		if(assetCollectionReturn.getAssetIds().size()==0){
			return ErrorDesc.failure().message("???????????????");
		}
		Result ckResult=assetService.checkAssetDataForBusinessAction(AssetOperateEnum.EAM_ASSET_COLLECTION_RETURN.code(),assetCollectionReturn.getAssetIds());
		if(!ckResult.isSuccess()){
			return ckResult;
		}



		//?????????
		if(StringUtil.isBlank(assetCollectionReturn.getOriginatorId())){
			assetCollectionReturn.setOriginatorId(SessionUser.getCurrent().getUser().getActivatedEmployeeId());
		}

		//????????????
		if(StringUtil.isBlank(assetCollectionReturn.getBusinessDate())){
			assetCollectionReturn.setBusinessDate(new Date());
		}

		//????????????
		if(StringUtil.isBlank(assetCollectionReturn.getStatus())){
			assetCollectionReturn.setStatus(AssetHandleStatusEnum.INCOMPLETE.code());
		}

		//??????????????????
		if(StringUtil.isBlank(assetCollectionReturn.getBusinessCode())){
			Result codeResult=CodeModuleServiceProxy.api().generateCode(AssetOperateEnum.EAM_ASSET_COLLECTION_RETURN.code());
			if(!codeResult.isSuccess()){
				return codeResult;
			}else{
				assetCollectionReturn.setBusinessCode(codeResult.getData().toString());
			}
		}


		Result r=super.insert(assetCollectionReturn);
		if (r.isSuccess()){
			//??????????????????
			List<AssetItem> saveList=new ArrayList<AssetItem>();
			for(int i=0;i<assetCollectionReturn.getAssetIds().size();i++){
				AssetItem asset=new AssetItem();
				asset.setId(IDGenerator.getSnowflakeIdString());
				asset.setHandleId(assetCollectionReturn.getId());
				asset.setAssetId(assetCollectionReturn.getAssetIds().get(i));
				saveList.add(asset);
			}
			Result batchInsertReuslt= assetItemService.insertList(saveList);
			if(!batchInsertReuslt.isSuccess()){
				return batchInsertReuslt;
			}

			dao.execute("update eam_asset_item a,eam_asset b set a.flag=b.borrow_id where a.asset_id=b.id and a.handle_id=?",assetCollectionReturn.getId());
		}
		return r;
	}
	
	/**
	 * ??????????????????????????????
	 * @param assetCollectionReturnList ??????????????????
	 * @return ??????????????????
	 * */
	@Override
	public Result insertList(List<AssetCollectionReturn> assetCollectionReturnList) {
		return super.insertList(assetCollectionReturnList);
	}
	
	
	/**
	 * ??????????????? ????????????
	 *
	 * @param id ??????
	 * @return ??????????????????
	 */
	public Result deleteByIdPhysical(String id) {
		AssetCollectionReturn assetCollectionReturn = new AssetCollectionReturn();
		if(id==null) return ErrorDesc.failure().message("id ???????????? null ???");
		assetCollectionReturn.setId(id);
		try {
			boolean suc = dao.deleteEntity(assetCollectionReturn);
			return suc?ErrorDesc.success():ErrorDesc.failure();
		}
		catch(Exception e) {
			Result r= ErrorDesc.failure();
			r.extra().setException(e);
			return r;
		}
	}
	
	/**
	 * ??????????????? ????????????
	 *
	 * @param id ??????
	 * @return ??????????????????
	 */
	public Result deleteByIdLogical(String id) {
		AssetCollectionReturn assetCollectionReturn = new AssetCollectionReturn();
		if(id==null) return ErrorDesc.failure().message("id ???????????? null ???");
		assetCollectionReturn.setId(id);
		assetCollectionReturn.setDeleted(1);
		assetCollectionReturn.setDeleteBy((String)dao.getDBTreaty().getLoginUserId());
		assetCollectionReturn.setDeleteTime(new Date());
		try {
			boolean suc = dao.updateEntity(assetCollectionReturn,SaveMode.NOT_NULL_FIELDS);
			return suc?ErrorDesc.success():ErrorDesc.failure();
		}
		catch(Exception e) {
			Result r= ErrorDesc.failure();
			r.extra().setException(e);
			return r;
		}
	}

	private Result verifyBillData(String handleId){
		//c  ??????,r  ?????????,d  ??????,cd ????????????
		//????????????
		ConditionExpr itemRecordcondition=new ConditionExpr();
		itemRecordcondition.andIn("handle_id",handleId);
		itemRecordcondition.andIn("crd","c","r");
		List<String> ckDatalist=assetItemService.queryValues(EAMTables.EAM_ASSET_ITEM.ASSET_ID,String.class,itemRecordcondition);
		if(ckDatalist.size()==0){
			return ErrorDesc.failure().message("???????????????");
		}
		return assetService.checkAssetDataForBusinessAction(CodeModuleEnum.EAM_ASSET_COLLECTION_RETURN.code(),ckDatalist);
	}

	/**
	 * ????????????
	 * @param assetCollectionReturn ????????????
	 * @param mode ????????????
	 * @return ??????????????????
	 * */
	@Override
	@Transactional
	public Result update(AssetCollectionReturn assetCollectionReturn , SaveMode mode) {

		Result verifyResult = verifyBillData(assetCollectionReturn.getId());
		if(!verifyResult.isSuccess())return verifyResult;

		Result r=super.update(assetCollectionReturn,mode);
		if(r.success()){
			//??????????????????
			dao.execute("update eam_asset_item set crd='r' where crd='c' and handle_id=?",assetCollectionReturn.getId());
			dao.execute("delete from eam_asset_item where crd in ('d','rd') and  handle_id=?",assetCollectionReturn.getId());
		}
		return r;
	}
	
	/**
	 * ???????????????????????????
	 * @param assetCollectionReturnList ??????????????????
	 * @param mode ????????????
	 * @return ??????????????????
	 * */
	@Override
	public Result updateList(List<AssetCollectionReturn> assetCollectionReturnList , SaveMode mode) {
		return super.updateList(assetCollectionReturnList , mode);
	}
	
	
	/**
	 * ????????????????????? ????????????
	 *
	 * @param id ??????
	 * @return ??????????????????
	 */
	public boolean update(DBField field,Object value , String id) {
		if(id==null) throw new IllegalArgumentException("id ???????????? null ");
		if(!field.table().name().equals(this.table())) throw new IllegalArgumentException("??????????????????["+field.table().name()+"]???????????????????????????["+this.table()+"]?????????");
		int suc=dao.update(field.table().name()).set(field.name(), value).where().and("id = ? ",id).top().execute();
		return suc>0;
	} 
	
	
	/**
	 * ??????????????? ????????????
	 *
	 * @param id ??????
	 * @return AssetCollectionReturn ????????????
	 */
	public AssetCollectionReturn getById(String id) {
		AssetCollectionReturn sample = new AssetCollectionReturn();
		if(id==null) throw new IllegalArgumentException("id ???????????? null ");
		sample.setId(id);
		return dao.queryEntity(sample);
	}

	@Override
	public List<AssetCollectionReturn> getByIds(List<String> ids) {
		return super.queryListByUKeys("id",ids);
	}



	/**
	 * ???????????????????????????????????????????????????????????????????????????????????????????????????
	 * 
	 * @param sample  ????????????
	 * @return ????????????
	 * */
	@Override
	public List<AssetCollectionReturn> queryList(AssetCollectionReturn sample) {
		return super.queryList(sample);
	}
	
	
	/**
	 * ????????????????????????????????????????????????????????????????????????????????????
	 * 
	 * @param sample  ????????????
	 * @param pageSize ????????????
	 * @param pageIndex ??????
	 * @return ????????????
	 * */
	@Override
	public PagedList<AssetCollectionReturn> queryPagedList(AssetCollectionReturn sample, int pageSize, int pageIndex) {
		String dp=AssetOperateEnum.EAM_ASSET_COLLECTION_RETURN.code();
		return super.queryPagedList(sample, pageSize, pageIndex,dp);
	}
	
	/**
	 * ????????????????????????????????????????????????????????????????????????????????????
	 * 
	 * @param sample  ????????????
	 * @param condition ????????????
	 * @param pageSize ????????????
	 * @param pageIndex ??????
	 * @return ????????????
	 * */
	@Override
	public PagedList<AssetCollectionReturn> queryPagedList(AssetCollectionReturn sample, ConditionExpr condition, int pageSize, int pageIndex) {
		String dp=AssetOperateEnum.EAM_ASSET_COLLECTION_RETURN.code();
		return super.queryPagedList(sample, condition, pageSize, pageIndex,dp);
	}
	
	/**
	 * ?????? ?????? ??????????????????
	 *
	 * @param assetCollectionReturn ????????????
	 * @return ????????????
	 */
	public Result<AssetCollectionReturn> checkExists(AssetCollectionReturn assetCollectionReturn) {
		//TDOD ??????????????????????????????
		//boolean exists=this.checkExists(assetCollectionReturn, SYS_ROLE.NAME);
		//return exists;
		return ErrorDesc.success();
	}

	@Override
	public ExcelWriter exportExcel(AssetCollectionReturn sample) {
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

/**
	 * ??????????????????
	 * @param ids  ????????????ID???????????????????????????
	 * */
	@Override
	public <T> Map<T, ReferCause> hasRefers(List<T> ids) {
		// ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
		return MapUtil.asMap(ids,new ReferCause(false));
		// return super.hasRefers(FoxnicWeb.BPM_PROCESS_INSTANCE.FORM_DEFINITION_ID,ids);
	}

	@Override
	public ExcelStructure buildExcelStructure(boolean isForExport) {
		return super.buildExcelStructure(isForExport);
	}


}
