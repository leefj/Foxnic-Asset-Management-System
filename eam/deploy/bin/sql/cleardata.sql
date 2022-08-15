-- eam
delete from eam_asset where 1=1  and tenant_id='T001' ;
delete from eam_asset_item where 1=1;
delete from eam_asset_batch where 1=1;
delete from eam_asset_process_record where 1=1;
delete from eam_asset_selected_data where 1=1  and tenant_id='T001' ;
delete from eam_asset_data_change where 1=1  and tenant_id='T001' ;
delete from eam_asset_repair where 1=1  and tenant_id='T001' ;
delete from eam_asset_scrap where 1=1  and tenant_id='T001' ;
delete from eam_asset_allocation where 1=1  and tenant_id='T001' ;
delete from eam_asset_borrow where 1=1  and tenant_id='T001' ;
delete from eam_failure_registration where 1=1  and tenant_id='T001' ;
delete from eam_asset_borrow_return where 1=1;
delete from eam_asset_collection where 1=1  and tenant_id='T001' ;
delete from eam_asset_collection_return where 1=1  and tenant_id='T001' ;
delete from eam_asset_handle where 1=1  and tenant_id='T001' ;
delete from eam_asset_tranfer where 1=1  and tenant_id='T001' ;
delete from eam_asset_storage where 1=1  and tenant_id='T001' ;
delete from eam_purchase_apply  where 1=1  and tenant_id='T001' ;
delete from eam_purchase_apply_item  where 1=1  and tenant_id='T001' ;
delete from eam_purchase_check  where 1=1  and tenant_id='T001' ;
delete from eam_purchase_order_detail  where 1=1  and tenant_id='T001' ;
delete from eam_asset_storage  where 1=1  and tenant_id='T001' ;
delete from eam_asset_depreciation_oper  where 1=1  and tenant_id='T001' ;
delete from eam_asset_depreciation_detail  where 1=1;
delete from eam_purchase_order  where 1=1  and tenant_id='T001' ;
-- software
delete from eam_asset_software  where 1=1  and tenant_id='T001' ;
delete from eam_asset_software_change  where 1=1  and tenant_id='T001' ;
delete from eam_asset_software_change_detail  where 1=1 ;
delete from eam_asset_software_distribute  where 1=1  and tenant_id='T001' ;
delete from eam_asset_software_distribute_data  where 1=1 ;
delete from eam_asset_software_maintenance  where 1=1  and tenant_id='T001' ;
delete from eam_asset_software_maintenance_detail  where 1=1;
-- stock
delete from eam_asset_stock_deliver  where 1=1 and tenant_id='T001' ;
delete from eam_asset_stock_collection  where 1=1 and tenant_id='T001' ;
delete from eam_asset_stock_goods  where 1=1 and tenant_id='T001' ;
delete from eam_asset_stock_goods_adjust  where 1=1 and tenant_id='T001' ;
delete from eam_asset_stock_goods_detail  where 1=1  ;
delete from eam_asset_stock_goods_in  where 1=1 and tenant_id='T001' ;
delete from eam_asset_stock_goods_out  where 1=1 and tenant_id='T001' ;
delete from eam_asset_stock_goods_tranfer  where 1=1 and tenant_id='T001' ;
delete from eam_goods_stock where owner_code<>'goods';
-- inventory
delete from eam_inventory where 1=1 and tenant_id='T001' ;
delete from eam_inventory_asset where 1=1 ;
delete from eam_inventory_director where 1=1 ;
delete from eam_inventory_manager where 1=1 ;
delete from eam_inventory_user where 1=1 ;
delete from eam_inventory_plan where 1=1 and tenant_id='T001' ;
delete from eam_group_user;
-- eam_maintain
delete from eam_maintain_plan where tenant_id='T001';
delete from eam_maintain_group where tenant_id='T001';
delete from eam_maintain_project where tenant_id='T001';
delete from eam_maintain_project_select where 1=1;
delete from eam_maintain_task where tenant_id='T001';
delete from eam_maintain_task_project where tenant_id='T001';
-- repair
-- delete from eam_repair_category where tenant_id='T001';
-- delete from eam_repair_category_tpl where tenant_id='T001';
delete from eam_repair_group  where tenant_id='T001';
delete from eam_repair_order  where tenant_id='T001';
delete from eam_repair_order_acceptance  where tenant_id='T001';
delete from eam_repair_order_act  where tenant_id='T001';
delete from eam_repair_rule  where tenant_id='T001';
delete from eam_repair_rule_item;
-- inspection
-- delete from eam_inspection_group where tenant_id='T001';
delete from eam_inspection_group_user where 1=1;
delete from eam_inspection_plan  where tenant_id='T001';
delete from eam_inspection_plan_point  where tenant_id='T001';
delete from eam_inspection_point  where tenant_id='T001';
delete from eam_inspection_point_owner  where tenant_id='T001';
delete from eam_inspection_route  where tenant_id='T001';
delete from eam_inspection_task  where tenant_id='T001';
delete from eam_inspection_task_point  where tenant_id='T001';
-- stock
delete from eam_stock where 1=1 and tenant_id='T001' ;
delete from eam_asset_stock_collection where 1=1;
-- dc
-- cont
delete from cont_contract where 1=1 and tenant_id='T001' ;
delete from cont_contract_attachment where 1=1  and tenant_id='T001' ;
delete from cont_contract_performance where 1=1  and tenant_id='T001' ;
delete from cont_contract_signer where 1=1 and tenant_id='T001' ;
-- other
delete from eam_feedback where 1=1 ;
-- job
delete from sys_job_log;
-- kn
delete from kn_content where 1=1 and tenant_id='T001' ;
--  ops
delete from ops_host where 1=1 and tenant_id='T001' ;
delete from ops_host_db where 1=1 ;
delete from ops_host_mid where 1=1;
delete from ops_host_os where 1=1;
delete from ops_information_system where 1=1 and tenant_id='T001' ;
delete from ops_db_instance where 1=1 and tenant_id='T001' ;
delete from ops_voucher where 1=1 and tenant_id='T001' ;
delete from ops_voucher_history where 1=1 and tenant_id='T001' ;
delete from ops_voucher_owner where 1=1 and tenant_id='T001' ;
delete from ops_voucher_priv where 1=1 and tenant_id='T001' ;
delete from ops_person where 1=1 and tenant_id='T001' ;
delete from ops_ip_address_range where 1=1;

-- ops_certificate
delete from ops_certificate where 1=1 and tenant_id='T001' ;

-- eam employ
delete from eam_asset_employee_apply where 1=1 and tenant_id='T001' ;
delete from eam_asset_employee_handover where 1=1 and tenant_id='T001' ;
delete from eam_asset_employee_loss where 1=1 and tenant_id='T001' ;
delete from eam_asset_employee_repair where 1=1 and tenant_id='T001' ;

-- workorder
delete from wo_network_strategy_apply where 1=1 and tenant_id='T001' ;
delete from wo_network_strategy_info ;
delete from wo_server_apply where 1=1 and tenant_id='T001' ;
delete from wo_server_info ;
delete from wo_slb_strategy_apply where 1=1 and tenant_id='T001' ;
delete from wo_slb_strategy_info ;

-- vehicle
delete from vehicle_a_select_item where 1=1;
delete from vehicle_apply where 1=1;
delete from vehicle_info where 1=1;
delete from vehicle_insurance_record where 1=1;
delete from vehicle_m_select_item where 1=1;
delete from vehicle_maintenance where 1=1;
delete from vehicle_select_item where 1=1;

-- BPM
delete from bpm_demo_leave;
delete from bpm_demo_common;
delete from bpm_form_instance;
delete from bpm_form_instance_bill;
delete from bpm_process_instance;
delete from bpm_task;
delete from bpm_task_approval;
delete from bpm_process_error;
delete from bpm_task_assignee;
delete from bpm_process_definition_deploy;
delete from bpm_process_definition_node_assignee where node_id not in (select id from bpm_process_definition_node where process_definition_file_id in (select id from bpm_process_definition_file where activated=1));
delete from bpm_process_definition_node where process_definition_file_id in (select id from bpm_process_definition_file where activated=0);
delete from bpm_process_definition_file where activated=0;


delete from bpm_form_definition where deleted=1;
-- PCM
delete from pcm_catalog where id='484764974338543617';
delete from pcm_catalog where id='484764976855126017';

-- HRM
delete from hrm_employee  where deleted=1;
delete from hrm_person  where deleted=1;
delete from hrm_company  where deleted=1;
delete from hrm_organization  where deleted=1;
delete from hrm_position  where deleted=1;
delete from hrm_employee_position  where deleted=1;
delete from hrm_favourite_group  where deleted=1;
delete from hrm_favourite_group_item  where deleted=1;

update hrm_person set deleted=2 where id in ( select t.person_id from hrm_employee t WHERE t.company_id= '002' AND exists(select 1 from hrm_position p,hrm_employee_position ep,hrm_organization org where org.id=p.org_id and p.id=ep.position_id and ep.employee_id=t.id and ep.deleted=0 and p.deleted=0 and (org.hierarchy like '586963971924295680/%' or p.org_id= '586963971924295680' )) AND ( ( t.deleted= 0 AND t.tenant_id= 'T001' )));
delete from sys_user  where id in( select c.user_id from sys_user_tenant c where c.employee_id in (select h.id from hrm_employee h where h.person_id in (select cc.id from hrm_person cc  where cc.deleted=2)));
delete from sys_user_tenant where employee_id in (select id from hrm_employee where person_id in (select id from hrm_person where deleted=2));
delete from hrm_employee where person_id in (select id from hrm_person where deleted=2);
delete from hrm_person where deleted=2;
delete from hrm_organization where id='586963971924295680';
delete from hrm_organization where id='500994919175819264';
commit;
