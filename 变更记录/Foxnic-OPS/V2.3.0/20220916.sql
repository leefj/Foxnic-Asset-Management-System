CREATE TABLE `ops_safety_baseline` (
                                       `id` varchar(50) NOT NULL COMMENT '主键',
                                       `type_id` varchar(50) DEFAULT NULL COMMENT '类型',
                                       `name` varchar(50) DEFAULT NULL COMMENT '名称',
                                       `base_version` varchar(50) DEFAULT NULL COMMENT '版本',
                                       `status` varchar(50) DEFAULT NULL COMMENT '状态',
                                       `usage_scenarios` varchar(50) DEFAULT NULL COMMENT '使用场景',
                                       `file_id` varchar(50) DEFAULT NULL COMMENT '基线文件',
                                       `check_file_id` varchar(50) DEFAULT NULL COMMENT '检查文件',
                                       `notes` varchar(500) DEFAULT NULL COMMENT '备注',
                                       `create_by` varchar(18) DEFAULT NULL COMMENT '创建人ID',
                                       `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                       `update_by` varchar(18) DEFAULT NULL COMMENT '修改人ID',
                                       `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                       `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
                                       `delete_by` varchar(18) DEFAULT NULL COMMENT '删除人ID',
                                       `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
                                       `version` int(11) NOT NULL DEFAULT '1',
                                       `tenant_id` varchar(18) DEFAULT NULL COMMENT '租户',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='安全基线';