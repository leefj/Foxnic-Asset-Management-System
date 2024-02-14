package com.dt.platform.hr.service.bpm;

import com.dt.platform.constants.enums.eam.AssetHandleStatusEnum;
import org.github.foxnic.web.domain.bpm.BpmActionResult;
import org.github.foxnic.web.domain.bpm.BpmEvent;
import org.github.foxnic.web.framework.bpm.BpmEventAdaptor;
import org.github.foxnic.web.framework.bpm.BpmAssistant;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.foxnic.commons.collection.MapUtil;
import java.util.Arrays;


import com.dt.platform.domain.hr.PersonOfficialBusiness;
import com.dt.platform.hr.service.IPersonOfficialBusinessService;

/**
 * <p>
 * 出差申请流程回调事件适配器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2024-02-13 21:10:56
*/

public class PersonOfficialBusinessBpmEventAdaptor extends BpmEventAdaptor<PersonOfficialBusiness,IPersonOfficialBusinessService> {

	public String BPM_TABLE="hr_person_official_business";

	private void updateBillStatus(String status ,String id){
		this.dao().execute("update "+BPM_TABLE+" set status=? where id=?", status, id);
	}

	public PersonOfficialBusinessBpmEventAdaptor(IPersonOfficialBusinessService service) {
		super(service);
	}

	/***
	 * 流程暂存开始，通过返回 BpmActionResult  的 success 或  failure 控制暂存过程是否继续进行
	 * */
	protected BpmActionResult onTemporarySaveStart(BpmEvent event) {
		return event.getActionResult();
	}


	/***
	 * 流程提交/启动开始，通过返回 BpmActionResult  的 success 或  failure 控制流程提交/启动过程是否继续进行
	 * */
	protected BpmActionResult onProcessSubmitStart(BpmEvent event) {
		return event.getActionResult();
	}

	/***
	 * 流程待办开始，通过返回 BpmActionResult  的 success 或  failure 控制流程待办处理过程是否继续进行
	 * */
	protected BpmActionResult onTaskStart(BpmEvent event) {
		if(event.getActionResult().isSuccess()){
			updateBillStatus(AssetHandleStatusEnum.APPROVAL.code(), event.getBillId());
		}
		return event.getActionResult();
	}

	/***
	 * 流程节点执行前，此事件由 camunda 提供，返回值仅在 act_ru_event_callback 表记录，不做其它控制
	 * */
	protected BpmActionResult onNodeStart(BpmEvent event) {
		return event.getActionResult();
	}

	/***
	 * 流程节点执行后，此事件由 camunda 提供，返回值仅在 act_ru_event_callback 表记录，不做其它控制
	 * */
	protected BpmActionResult onNodeEnd(BpmEvent event) {

		if("END".equals(event.getNodeId())){
			updateBillStatus(AssetHandleStatusEnum.COMPLETE.code(), event.getBillId());
		}

		return event.getActionResult();
	}

	/***
	 * 流程撤回开始，通过返回 BpmActionResult  的 success 或  failure 控制流程撤回处理过程是否继续进行
	 * */
	protected BpmActionResult onFetchBackStart(BpmEvent event) {
		return event.getActionResult();
	}

	/***
	 * 流程撤回结束，返回值无意义
	 * */
	protected BpmActionResult onFetchBackEnd(BpmEvent event) {
		updateBillStatus(AssetHandleStatusEnum.INCOMPLETE.code(), event.getBillId());
		return event.getActionResult();
	}

	/***
	 * 流程跳转开始，通过返回 BpmActionResult  的 success 或  failure 控制流程跳转处理过程是否继续进行
	 * */
	protected BpmActionResult onJumpStart(BpmEvent event) {
		return event.getActionResult();
	}

	/***
	 * 流程跳转结束， 返回值无意义
	 * */
	protected BpmActionResult onJumpEnd(BpmEvent event) {
		return event.getActionResult();
	}

	/***
	 * 流程转办开始，通过返回 BpmActionResult  的 success 或  failure 控制流程转办处理过程是否继续进行
	 * */
	protected BpmActionResult onDelegateStart(BpmEvent event) {
		return event.getActionResult();
	}

	/***
	 * 流程转办处理结束，返回值无意义
	 * */
	protected BpmActionResult onDelegateEnd(BpmEvent event) {
		return event.getActionResult();
	}

	/***
	 * 流程废弃开始，通过返回 BpmActionResult  的 success 或  failure 控制流程废弃处理过程是否继续进行
	 * */
	protected BpmActionResult onProcessAbandonStart(BpmEvent event) {

		updateBillStatus(AssetHandleStatusEnum.CANCEL.code(), event.getBillId());

		return event.getActionResult();
	}

	/***
	 * 流程废弃结束，返回值无意义
	 * */
	protected BpmActionResult onProcessAbandonEnd(BpmEvent event) {
		return event.getActionResult();
	}

}