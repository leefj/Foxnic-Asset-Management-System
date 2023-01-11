INSERT INTO `sys_dict` (`id`, `is_tree`, `name`, `code`, `module`, `notes`, `create_by`, `create_time`, `update_by`, `update_time`, `deleted`, `delete_by`, `delete_time`, `version`)
VALUES
('665509322247110656', 0, '数据库备份数据存放', 'ops_db_data_loc', '472036556115279872', '', '110588348101165911', '2023-01-11 10:56:27', NULL, NULL, 0, NULL, NULL, 1);

INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `parent_id`, `code`, `label`, `sort`, `create_by`, `create_time`, `update_by`, `update_time`, `deleted`, `delete_by`, `delete_time`, `version`, `valid`)
VALUES
('665509595942223872', '665509322247110656', 'ops_db_data_loc', '', 'host', '主机', 1, '110588348101165911', '2023-01-11 10:57:32', NULL, NULL, 0, NULL, NULL, 1, 1),
('665509621942714368', '665509322247110656', 'ops_db_data_loc', '', 'nbu', 'NBU', 2, '110588348101165911', '2023-01-11 10:57:38', NULL, NULL, 0, NULL, NULL, 1, 1),
('665509825974632448', '665509322247110656', 'ops_db_data_loc', '', 'nbu_other_site', 'NBU(灾备)', 3, '110588348101165911', '2023-01-11 10:58:27', NULL, NULL, 0, NULL, NULL, 1, 1),
('665509924184260608', '665509322247110656', 'ops_db_data_loc', '', 'nbu_full', 'NBU及灾备', 3, '110588348101165911', '2023-01-11 10:58:51', NULL, NULL, 0, NULL, NULL, 1, 1);



alter table ops_db_info add column data_loc varchar(500);


CREATE TABLE `ops_db_data_loc` (
                                   `id` varchar(50) NOT NULL COMMENT '主键',
                                   `loc_id` varchar(50) DEFAULT NULL COMMENT '存放位置',
                                   db_info_id  varchar(50) DEFAULT NULL COMMENT '数据库',
                                   `create_by` varchar(18) DEFAULT NULL COMMENT '创建人ID',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                   `update_by` varchar(18) DEFAULT NULL COMMENT '修改人ID',
                                   `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                   `deleted` tinyint(1) DEFAULT '0' COMMENT '是否已删除',
                                   `delete_by` varchar(18) DEFAULT NULL COMMENT '删除人ID',
                                   `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
                                   `version` int(11) NOT NULL DEFAULT '1',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据库存放位置';
