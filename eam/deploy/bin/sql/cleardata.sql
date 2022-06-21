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
-- ops_certificate
delete from ops_certificate where 1=1 and tenant_id='T001' ;

