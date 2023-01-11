package com.dt.platform.domain.ops.meta;

import com.github.foxnic.api.bean.BeanProperty;
import com.dt.platform.domain.ops.DbInfoVO;
import java.util.List;
import com.dt.platform.domain.ops.DbInfo;
import java.math.BigDecimal;
import java.util.Date;
import com.dt.platform.domain.ops.DbBackupInfo;
import com.dt.platform.domain.ops.Host;
import com.dt.platform.domain.ops.ServiceInfo;
import org.github.foxnic.web.domain.system.DictItem;
import javax.persistence.Transient;



/**
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-11 11:52:07
 * @sign 62DFC6503B8B14397E15FD04C9DAC032
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

public class DbInfoVOMeta extends DbInfoMeta {
	
	/**
	 * 页码 , 类型: java.lang.Integer
	*/
	public static final String PAGE_INDEX="pageIndex";
	
	/**
	 * 页码 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.Integer> PAGE_INDEX_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,PAGE_INDEX, java.lang.Integer.class, "页码", "", java.lang.Integer.class, null);
	
	/**
	 * 分页大小 , 类型: java.lang.Integer
	*/
	public static final String PAGE_SIZE="pageSize";
	
	/**
	 * 分页大小 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.Integer> PAGE_SIZE_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,PAGE_SIZE, java.lang.Integer.class, "分页大小", "", java.lang.Integer.class, null);
	
	/**
	 * 搜索字段 , 类型: java.lang.String
	*/
	public static final String SEARCH_FIELD="searchField";
	
	/**
	 * 搜索字段 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> SEARCH_FIELD_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,SEARCH_FIELD, java.lang.String.class, "搜索字段", "", java.lang.String.class, null);
	
	/**
	 * 模糊搜索字段 , 类型: java.lang.String
	*/
	public static final String FUZZY_FIELD="fuzzyField";
	
	/**
	 * 模糊搜索字段 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> FUZZY_FIELD_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,FUZZY_FIELD, java.lang.String.class, "模糊搜索字段", "", java.lang.String.class, null);
	
	/**
	 * 搜索的值 , 类型: java.lang.String
	*/
	public static final String SEARCH_VALUE="searchValue";
	
	/**
	 * 搜索的值 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> SEARCH_VALUE_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,SEARCH_VALUE, java.lang.String.class, "搜索的值", "", java.lang.String.class, null);
	
	/**
	 * 已修改字段 , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final String DIRTY_FIELDS="dirtyFields";
	
	/**
	 * 已修改字段 , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> DIRTY_FIELDS_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,DIRTY_FIELDS, java.util.List.class, "已修改字段", "", java.lang.String.class, null);
	
	/**
	 * 排序字段 , 类型: java.lang.String
	*/
	public static final String SORT_FIELD="sortField";
	
	/**
	 * 排序字段 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> SORT_FIELD_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,SORT_FIELD, java.lang.String.class, "排序字段", "", java.lang.String.class, null);
	
	/**
	 * 排序方式 , 类型: java.lang.String
	*/
	public static final String SORT_TYPE="sortType";
	
	/**
	 * 排序方式 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> SORT_TYPE_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,SORT_TYPE, java.lang.String.class, "排序方式", "", java.lang.String.class, null);
	
	/**
	 * 数据来源 , 前端指定不同的来源，后端按来源执行不同的逻辑 , 类型: java.lang.String
	*/
	public static final String DATA_ORIGIN="dataOrigin";
	
	/**
	 * 数据来源 , 前端指定不同的来源，后端按来源执行不同的逻辑 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> DATA_ORIGIN_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,DATA_ORIGIN, java.lang.String.class, "数据来源", "前端指定不同的来源，后端按来源执行不同的逻辑", java.lang.String.class, null);
	
	/**
	 * 查询逻辑 , 默认and，可指定 or  , 类型: java.lang.String
	*/
	public static final String QUERY_LOGIC="queryLogic";
	
	/**
	 * 查询逻辑 , 默认and，可指定 or  , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> QUERY_LOGIC_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,QUERY_LOGIC, java.lang.String.class, "查询逻辑", "默认and，可指定 or ", java.lang.String.class, null);
	
	/**
	 * 主键清单 , 用于接收批量主键参数 , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final String IDS="ids";
	
	/**
	 * 主键清单 , 用于接收批量主键参数 , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> IDS_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,IDS, java.util.List.class, "主键清单", "用于接收批量主键参数", java.lang.String.class, null);
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final String ID="id";
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> ID_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,ID, java.lang.String.class, "主键", "主键", java.lang.String.class, null);
	
	/**
	 * 主机 , 类型: java.lang.String
	*/
	public static final String HOST_ID="hostId";
	
	/**
	 * 主机 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> HOST_ID_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,HOST_ID, java.lang.String.class, "主机", "主机", java.lang.String.class, null);
	
	/**
	 * 数据库类型 , 类型: java.lang.String
	*/
	public static final String TYPE_ID="typeId";
	
	/**
	 * 数据库类型 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> TYPE_ID_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,TYPE_ID, java.lang.String.class, "数据库类型", "数据库类型", java.lang.String.class, null);
	
	/**
	 * 数据库名 , 类型: java.lang.String
	*/
	public static final String NAME="name";
	
	/**
	 * 数据库名 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> NAME_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,NAME, java.lang.String.class, "数据库名", "数据库名", java.lang.String.class, null);
	
	/**
	 * 数据库状态 , 类型: java.lang.String
	*/
	public static final String STATUS="status";
	
	/**
	 * 数据库状态 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> STATUS_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,STATUS, java.lang.String.class, "数据库状态", "数据库状态", java.lang.String.class, null);
	
	/**
	 * 备份状态 , 类型: java.lang.String
	*/
	public static final String BACKUP_STATUS="backupStatus";
	
	/**
	 * 备份状态 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> BACKUP_STATUS_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,BACKUP_STATUS, java.lang.String.class, "备份状态", "备份状态", java.lang.String.class, null);
	
	/**
	 * 部署模式 , 类型: java.lang.String
	*/
	public static final String DEPLOY_MODE="deployMode";
	
	/**
	 * 部署模式 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> DEPLOY_MODE_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,DEPLOY_MODE, java.lang.String.class, "部署模式", "部署模式", java.lang.String.class, null);
	
	/**
	 * 大小(M) , 类型: java.math.BigDecimal
	*/
	public static final String DB_SIZE="dbSize";
	
	/**
	 * 大小(M) , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.math.BigDecimal> DB_SIZE_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,DB_SIZE, java.math.BigDecimal.class, "大小(M)", "大小(M)", java.math.BigDecimal.class, null);
	
	/**
	 * 日志模式 , 类型: java.lang.String
	*/
	public static final String LOG_METHOD="logMethod";
	
	/**
	 * 日志模式 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> LOG_METHOD_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,LOG_METHOD, java.lang.String.class, "日志模式", "日志模式", java.lang.String.class, null);
	
	/**
	 * 管理员账户 , 类型: java.lang.String
	*/
	public static final String ADMIN_USER_LIST="adminUserList";
	
	/**
	 * 管理员账户 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> ADMIN_USER_LIST_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,ADMIN_USER_LIST, java.lang.String.class, "管理员账户", "管理员账户", java.lang.String.class, null);
	
	/**
	 * 应用账户 , 类型: java.lang.String
	*/
	public static final String APP_USER_LIST="appUserList";
	
	/**
	 * 应用账户 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> APP_USER_LIST_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,APP_USER_LIST, java.lang.String.class, "应用账户", "应用账户", java.lang.String.class, null);
	
	/**
	 * 运维账户 , 类型: java.lang.String
	*/
	public static final String OPS_USER_LIST="opsUserList";
	
	/**
	 * 运维账户 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> OPS_USER_LIST_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,OPS_USER_LIST, java.lang.String.class, "运维账户", "运维账户", java.lang.String.class, null);
	
	/**
	 * 其他账户 , 类型: java.lang.String
	*/
	public static final String OTHER_USER_LIST="otherUserList";
	
	/**
	 * 其他账户 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> OTHER_USER_LIST_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,OTHER_USER_LIST, java.lang.String.class, "其他账户", "其他账户", java.lang.String.class, null);
	
	/**
	 * 使用情况 , 类型: java.lang.String
	*/
	public static final String USER_USE_INFO="userUseInfo";
	
	/**
	 * 使用情况 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> USER_USE_INFO_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,USER_USE_INFO, java.lang.String.class, "使用情况", "使用情况", java.lang.String.class, null);
	
	/**
	 * 凭证信息 , 类型: java.lang.String
	*/
	public static final String VOUCHER_STR="voucherStr";
	
	/**
	 * 凭证信息 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> VOUCHER_STR_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,VOUCHER_STR, java.lang.String.class, "凭证信息", "凭证信息", java.lang.String.class, null);
	
	/**
	 * 服务端口 , 类型: java.lang.String
	*/
	public static final String DB_PORT="dbPort";
	
	/**
	 * 服务端口 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> DB_PORT_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,DB_PORT, java.lang.String.class, "服务端口", "服务端口", java.lang.String.class, null);
	
	/**
	 * 数据存放 , 类型: java.lang.String
	*/
	public static final String DATA_LOC="dataLoc";
	
	/**
	 * 数据存放 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> DATA_LOC_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,DATA_LOC, java.lang.String.class, "数据存放", "数据存放", java.lang.String.class, null);
	
	/**
	 * 本地备份策略 , 类型: java.lang.String
	*/
	public static final String BACKUP_STRATEGY="backupStrategy";
	
	/**
	 * 本地备份策略 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> BACKUP_STRATEGY_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,BACKUP_STRATEGY, java.lang.String.class, "本地备份策略", "本地备份策略", java.lang.String.class, null);
	
	/**
	 * 工具备份策略 , 类型: java.lang.String
	*/
	public static final String TOOL_STRATEGY="toolStrategy";
	
	/**
	 * 工具备份策略 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> TOOL_STRATEGY_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,TOOL_STRATEGY, java.lang.String.class, "工具备份策略", "工具备份策略", java.lang.String.class, null);
	
	/**
	 * 灾备策略 , 类型: java.lang.String
	*/
	public static final String DISASTER_RECOVERY_STRATEGY="disasterRecoveryStrategy";
	
	/**
	 * 灾备策略 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> DISASTER_RECOVERY_STRATEGY_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,DISASTER_RECOVERY_STRATEGY, java.lang.String.class, "灾备策略", "灾备策略", java.lang.String.class, null);
	
	/**
	 * 清理策略 , 类型: java.lang.String
	*/
	public static final String CLEAR_STRATEGY="clearStrategy";
	
	/**
	 * 清理策略 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> CLEAR_STRATEGY_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,CLEAR_STRATEGY, java.lang.String.class, "清理策略", "清理策略", java.lang.String.class, null);
	
	/**
	 * 备份备注 , 类型: java.lang.String
	*/
	public static final String BACKUP_INFO="backupInfo";
	
	/**
	 * 备份备注 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> BACKUP_INFO_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,BACKUP_INFO, java.lang.String.class, "备份备注", "备份备注", java.lang.String.class, null);
	
	/**
	 * 备注 , 类型: java.lang.String
	*/
	public static final String NOTES="notes";
	
	/**
	 * 备注 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> NOTES_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,NOTES, java.lang.String.class, "备注", "备注", java.lang.String.class, null);
	
	/**
	 * 选择 , 类型: java.lang.String
	*/
	public static final String SELECTED_CODE="selectedCode";
	
	/**
	 * 选择 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> SELECTED_CODE_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,SELECTED_CODE, java.lang.String.class, "选择", "选择", java.lang.String.class, null);
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final String CREATE_BY="createBy";
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> CREATE_BY_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,CREATE_BY, java.lang.String.class, "创建人ID", "创建人ID", java.lang.String.class, null);
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final String CREATE_TIME="createTime";
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.util.Date> CREATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,CREATE_TIME, java.util.Date.class, "创建时间", "创建时间", java.util.Date.class, null);
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final String UPDATE_BY="updateBy";
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> UPDATE_BY_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,UPDATE_BY, java.lang.String.class, "修改人ID", "修改人ID", java.lang.String.class, null);
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final String UPDATE_TIME="updateTime";
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.util.Date> UPDATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,UPDATE_TIME, java.util.Date.class, "修改时间", "修改时间", java.util.Date.class, null);
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final String DELETED="deleted";
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.Integer> DELETED_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,DELETED, java.lang.Integer.class, "是否已删除", "是否已删除", java.lang.Integer.class, null);
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final String DELETE_BY="deleteBy";
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> DELETE_BY_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,DELETE_BY, java.lang.String.class, "删除人ID", "删除人ID", java.lang.String.class, null);
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final String DELETE_TIME="deleteTime";
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.util.Date> DELETE_TIME_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,DELETE_TIME, java.util.Date.class, "删除时间", "删除时间", java.util.Date.class, null);
	
	/**
	 * version , 类型: java.lang.Integer
	*/
	public static final String VERSION="version";
	
	/**
	 * version , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.Integer> VERSION_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,VERSION, java.lang.Integer.class, "version", "version", java.lang.Integer.class, null);
	
	/**
	 * backupInfoList , 集合类型: LIST , 类型: com.dt.platform.domain.ops.DbBackupInfo
	*/
	public static final String BACKUP_INFO_LIST="backupInfoList";
	
	/**
	 * backupInfoList , 集合类型: LIST , 类型: com.dt.platform.domain.ops.DbBackupInfo
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,com.dt.platform.domain.ops.DbBackupInfo> BACKUP_INFO_LIST_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,BACKUP_INFO_LIST, java.util.List.class, "backupInfoList", "backupInfoList", com.dt.platform.domain.ops.DbBackupInfo.class, null);
	
	/**
	 * backupInfoIds , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final String BACKUP_INFO_IDS="backupInfoIds";
	
	/**
	 * backupInfoIds , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> BACKUP_INFO_IDS_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,BACKUP_INFO_IDS, java.util.List.class, "backupInfoIds", "backupInfoIds", java.lang.String.class, null);
	
	/**
	 * host , 类型: com.dt.platform.domain.ops.Host
	*/
	public static final String HOST="host";
	
	/**
	 * host , 类型: com.dt.platform.domain.ops.Host
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,com.dt.platform.domain.ops.Host> HOST_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,HOST, com.dt.platform.domain.ops.Host.class, "host", "host", com.dt.platform.domain.ops.Host.class, null);
	
	/**
	 * type , 类型: com.dt.platform.domain.ops.ServiceInfo
	*/
	public static final String TYPE="type";
	
	/**
	 * type , 类型: com.dt.platform.domain.ops.ServiceInfo
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,com.dt.platform.domain.ops.ServiceInfo> TYPE_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,TYPE, com.dt.platform.domain.ops.ServiceInfo.class, "type", "type", com.dt.platform.domain.ops.ServiceInfo.class, null);
	
	/**
	 * deployModeDict , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final String DEPLOY_MODE_DICT="deployModeDict";
	
	/**
	 * deployModeDict , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,org.github.foxnic.web.domain.system.DictItem> DEPLOY_MODE_DICT_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,DEPLOY_MODE_DICT, org.github.foxnic.web.domain.system.DictItem.class, "deployModeDict", "deployModeDict", org.github.foxnic.web.domain.system.DictItem.class, null);
	
	/**
	 * labelList , 集合类型: LIST , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final String LABEL_LIST="labelList";
	
	/**
	 * labelList , 集合类型: LIST , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,org.github.foxnic.web.domain.system.DictItem> LABEL_LIST_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,LABEL_LIST, java.util.List.class, "labelList", "labelList", org.github.foxnic.web.domain.system.DictItem.class, null);
	
	/**
	 * labelIds , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final String LABEL_IDS="labelIds";
	
	/**
	 * labelIds , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> LABEL_IDS_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,LABEL_IDS, java.util.List.class, "labelIds", "labelIds", java.lang.String.class, null);
	
	/**
	 * dataLocData , 集合类型: LIST , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final String DATA_LOC_DATA="dataLocData";
	
	/**
	 * dataLocData , 集合类型: LIST , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,org.github.foxnic.web.domain.system.DictItem> DATA_LOC_DATA_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,DATA_LOC_DATA, java.util.List.class, "dataLocData", "dataLocData", org.github.foxnic.web.domain.system.DictItem.class, null);
	
	/**
	 * dataLocIds , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final String DATA_LOC_IDS="dataLocIds";
	
	/**
	 * dataLocIds , 集合类型: LIST , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.ops.DbInfoVO,java.lang.String> DATA_LOC_IDS_PROP = new BeanProperty(com.dt.platform.domain.ops.DbInfoVO.class ,DATA_LOC_IDS, java.util.List.class, "dataLocIds", "dataLocIds", java.lang.String.class, null);
	
	/**
	 * 全部属性清单
	*/
	public static final String[] $PROPS={ PAGE_INDEX , PAGE_SIZE , SEARCH_FIELD , FUZZY_FIELD , SEARCH_VALUE , DIRTY_FIELDS , SORT_FIELD , SORT_TYPE , DATA_ORIGIN , QUERY_LOGIC , IDS , ID , HOST_ID , TYPE_ID , NAME , STATUS , BACKUP_STATUS , DEPLOY_MODE , DB_SIZE , LOG_METHOD , ADMIN_USER_LIST , APP_USER_LIST , OPS_USER_LIST , OTHER_USER_LIST , USER_USE_INFO , VOUCHER_STR , DB_PORT , DATA_LOC , BACKUP_STRATEGY , TOOL_STRATEGY , DISASTER_RECOVERY_STRATEGY , CLEAR_STRATEGY , BACKUP_INFO , NOTES , SELECTED_CODE , CREATE_BY , CREATE_TIME , UPDATE_BY , UPDATE_TIME , DELETED , DELETE_BY , DELETE_TIME , VERSION , BACKUP_INFO_LIST , BACKUP_INFO_IDS , HOST , TYPE , DEPLOY_MODE_DICT , LABEL_LIST , LABEL_IDS , DATA_LOC_DATA , DATA_LOC_IDS };
	
	/**
	 * 代理类
	*/
	public static class $$proxy$$ extends com.dt.platform.domain.ops.DbInfoVO {

		private static final long serialVersionUID = 1L;

		
		/**
		 * 设置 页码
		 * @param pageIndex 页码
		 * @return 当前对象
		*/
		public DbInfoVO setPageIndex(Integer pageIndex) {
			super.change(PAGE_INDEX,super.getPageIndex(),pageIndex);
			super.setPageIndex(pageIndex);
			return this;
		}
		
		/**
		 * 设置 分页大小
		 * @param pageSize 分页大小
		 * @return 当前对象
		*/
		public DbInfoVO setPageSize(Integer pageSize) {
			super.change(PAGE_SIZE,super.getPageSize(),pageSize);
			super.setPageSize(pageSize);
			return this;
		}
		
		/**
		 * 设置 搜索字段
		 * @param searchField 搜索字段
		 * @return 当前对象
		*/
		public DbInfoVO setSearchField(String searchField) {
			super.change(SEARCH_FIELD,super.getSearchField(),searchField);
			super.setSearchField(searchField);
			return this;
		}
		
		/**
		 * 设置 模糊搜索字段
		 * @param fuzzyField 模糊搜索字段
		 * @return 当前对象
		*/
		public DbInfoVO setFuzzyField(String fuzzyField) {
			super.change(FUZZY_FIELD,super.getFuzzyField(),fuzzyField);
			super.setFuzzyField(fuzzyField);
			return this;
		}
		
		/**
		 * 设置 搜索的值
		 * @param searchValue 搜索的值
		 * @return 当前对象
		*/
		public DbInfoVO setSearchValue(String searchValue) {
			super.change(SEARCH_VALUE,super.getSearchValue(),searchValue);
			super.setSearchValue(searchValue);
			return this;
		}
		
		/**
		 * 设置 已修改字段
		 * @param dirtyFields 已修改字段
		 * @return 当前对象
		*/
		public DbInfoVO setDirtyFields(List<String> dirtyFields) {
			super.change(DIRTY_FIELDS,super.getDirtyFields(),dirtyFields);
			super.setDirtyFields(dirtyFields);
			return this;
		}
		
		/**
		 * 设置 排序字段
		 * @param sortField 排序字段
		 * @return 当前对象
		*/
		public DbInfoVO setSortField(String sortField) {
			super.change(SORT_FIELD,super.getSortField(),sortField);
			super.setSortField(sortField);
			return this;
		}
		
		/**
		 * 设置 排序方式
		 * @param sortType 排序方式
		 * @return 当前对象
		*/
		public DbInfoVO setSortType(String sortType) {
			super.change(SORT_TYPE,super.getSortType(),sortType);
			super.setSortType(sortType);
			return this;
		}
		
		/**
		 * 设置 数据来源
		 * @param dataOrigin 数据来源
		 * @return 当前对象
		*/
		public DbInfoVO setDataOrigin(String dataOrigin) {
			super.change(DATA_ORIGIN,super.getDataOrigin(),dataOrigin);
			super.setDataOrigin(dataOrigin);
			return this;
		}
		
		/**
		 * 设置 查询逻辑
		 * @param queryLogic 查询逻辑
		 * @return 当前对象
		*/
		public DbInfoVO setQueryLogic(String queryLogic) {
			super.change(QUERY_LOGIC,super.getQueryLogic(),queryLogic);
			super.setQueryLogic(queryLogic);
			return this;
		}
		
		/**
		 * 设置 主键清单
		 * @param ids 主键清单
		 * @return 当前对象
		*/
		public DbInfoVO setIds(List<String> ids) {
			super.change(IDS,super.getIds(),ids);
			super.setIds(ids);
			return this;
		}
		
		/**
		 * 设置 主键
		 * @param id 主键
		 * @return 当前对象
		*/
		public DbInfo setId(String id) {
			super.change(ID,super.getId(),id);
			super.setId(id);
			return this;
		}
		
		/**
		 * 设置 主机
		 * @param hostId 主机
		 * @return 当前对象
		*/
		public DbInfo setHostId(String hostId) {
			super.change(HOST_ID,super.getHostId(),hostId);
			super.setHostId(hostId);
			return this;
		}
		
		/**
		 * 设置 数据库类型
		 * @param typeId 数据库类型
		 * @return 当前对象
		*/
		public DbInfo setTypeId(String typeId) {
			super.change(TYPE_ID,super.getTypeId(),typeId);
			super.setTypeId(typeId);
			return this;
		}
		
		/**
		 * 设置 数据库名
		 * @param name 数据库名
		 * @return 当前对象
		*/
		public DbInfo setName(String name) {
			super.change(NAME,super.getName(),name);
			super.setName(name);
			return this;
		}
		
		/**
		 * 设置 数据库状态
		 * @param status 数据库状态
		 * @return 当前对象
		*/
		public DbInfo setStatus(String status) {
			super.change(STATUS,super.getStatus(),status);
			super.setStatus(status);
			return this;
		}
		
		/**
		 * 设置 备份状态
		 * @param backupStatus 备份状态
		 * @return 当前对象
		*/
		public DbInfo setBackupStatus(String backupStatus) {
			super.change(BACKUP_STATUS,super.getBackupStatus(),backupStatus);
			super.setBackupStatus(backupStatus);
			return this;
		}
		
		/**
		 * 设置 部署模式
		 * @param deployMode 部署模式
		 * @return 当前对象
		*/
		public DbInfo setDeployMode(String deployMode) {
			super.change(DEPLOY_MODE,super.getDeployMode(),deployMode);
			super.setDeployMode(deployMode);
			return this;
		}
		
		/**
		 * 设置 大小(M)
		 * @param dbSize 大小(M)
		 * @return 当前对象
		*/
		public DbInfo setDbSize(BigDecimal dbSize) {
			super.change(DB_SIZE,super.getDbSize(),dbSize);
			super.setDbSize(dbSize);
			return this;
		}
		
		/**
		 * 设置 日志模式
		 * @param logMethod 日志模式
		 * @return 当前对象
		*/
		public DbInfo setLogMethod(String logMethod) {
			super.change(LOG_METHOD,super.getLogMethod(),logMethod);
			super.setLogMethod(logMethod);
			return this;
		}
		
		/**
		 * 设置 管理员账户
		 * @param adminUserList 管理员账户
		 * @return 当前对象
		*/
		public DbInfo setAdminUserList(String adminUserList) {
			super.change(ADMIN_USER_LIST,super.getAdminUserList(),adminUserList);
			super.setAdminUserList(adminUserList);
			return this;
		}
		
		/**
		 * 设置 应用账户
		 * @param appUserList 应用账户
		 * @return 当前对象
		*/
		public DbInfo setAppUserList(String appUserList) {
			super.change(APP_USER_LIST,super.getAppUserList(),appUserList);
			super.setAppUserList(appUserList);
			return this;
		}
		
		/**
		 * 设置 运维账户
		 * @param opsUserList 运维账户
		 * @return 当前对象
		*/
		public DbInfo setOpsUserList(String opsUserList) {
			super.change(OPS_USER_LIST,super.getOpsUserList(),opsUserList);
			super.setOpsUserList(opsUserList);
			return this;
		}
		
		/**
		 * 设置 其他账户
		 * @param otherUserList 其他账户
		 * @return 当前对象
		*/
		public DbInfo setOtherUserList(String otherUserList) {
			super.change(OTHER_USER_LIST,super.getOtherUserList(),otherUserList);
			super.setOtherUserList(otherUserList);
			return this;
		}
		
		/**
		 * 设置 使用情况
		 * @param userUseInfo 使用情况
		 * @return 当前对象
		*/
		public DbInfo setUserUseInfo(String userUseInfo) {
			super.change(USER_USE_INFO,super.getUserUseInfo(),userUseInfo);
			super.setUserUseInfo(userUseInfo);
			return this;
		}
		
		/**
		 * 设置 凭证信息
		 * @param voucherStr 凭证信息
		 * @return 当前对象
		*/
		public DbInfo setVoucherStr(String voucherStr) {
			super.change(VOUCHER_STR,super.getVoucherStr(),voucherStr);
			super.setVoucherStr(voucherStr);
			return this;
		}
		
		/**
		 * 设置 服务端口
		 * @param dbPort 服务端口
		 * @return 当前对象
		*/
		public DbInfo setDbPort(String dbPort) {
			super.change(DB_PORT,super.getDbPort(),dbPort);
			super.setDbPort(dbPort);
			return this;
		}
		
		/**
		 * 设置 数据存放
		 * @param dataLoc 数据存放
		 * @return 当前对象
		*/
		public DbInfo setDataLoc(String dataLoc) {
			super.change(DATA_LOC,super.getDataLoc(),dataLoc);
			super.setDataLoc(dataLoc);
			return this;
		}
		
		/**
		 * 设置 本地备份策略
		 * @param backupStrategy 本地备份策略
		 * @return 当前对象
		*/
		public DbInfo setBackupStrategy(String backupStrategy) {
			super.change(BACKUP_STRATEGY,super.getBackupStrategy(),backupStrategy);
			super.setBackupStrategy(backupStrategy);
			return this;
		}
		
		/**
		 * 设置 工具备份策略
		 * @param toolStrategy 工具备份策略
		 * @return 当前对象
		*/
		public DbInfo setToolStrategy(String toolStrategy) {
			super.change(TOOL_STRATEGY,super.getToolStrategy(),toolStrategy);
			super.setToolStrategy(toolStrategy);
			return this;
		}
		
		/**
		 * 设置 灾备策略
		 * @param disasterRecoveryStrategy 灾备策略
		 * @return 当前对象
		*/
		public DbInfo setDisasterRecoveryStrategy(String disasterRecoveryStrategy) {
			super.change(DISASTER_RECOVERY_STRATEGY,super.getDisasterRecoveryStrategy(),disasterRecoveryStrategy);
			super.setDisasterRecoveryStrategy(disasterRecoveryStrategy);
			return this;
		}
		
		/**
		 * 设置 清理策略
		 * @param clearStrategy 清理策略
		 * @return 当前对象
		*/
		public DbInfo setClearStrategy(String clearStrategy) {
			super.change(CLEAR_STRATEGY,super.getClearStrategy(),clearStrategy);
			super.setClearStrategy(clearStrategy);
			return this;
		}
		
		/**
		 * 设置 备份备注
		 * @param backupInfo 备份备注
		 * @return 当前对象
		*/
		public DbInfo setBackupInfo(String backupInfo) {
			super.change(BACKUP_INFO,super.getBackupInfo(),backupInfo);
			super.setBackupInfo(backupInfo);
			return this;
		}
		
		/**
		 * 设置 备注
		 * @param notes 备注
		 * @return 当前对象
		*/
		public DbInfo setNotes(String notes) {
			super.change(NOTES,super.getNotes(),notes);
			super.setNotes(notes);
			return this;
		}
		
		/**
		 * 设置 选择
		 * @param selectedCode 选择
		 * @return 当前对象
		*/
		public DbInfo setSelectedCode(String selectedCode) {
			super.change(SELECTED_CODE,super.getSelectedCode(),selectedCode);
			super.setSelectedCode(selectedCode);
			return this;
		}
		
		/**
		 * 设置 创建人ID
		 * @param createBy 创建人ID
		 * @return 当前对象
		*/
		public DbInfo setCreateBy(String createBy) {
			super.change(CREATE_BY,super.getCreateBy(),createBy);
			super.setCreateBy(createBy);
			return this;
		}
		
		/**
		 * 设置 创建时间
		 * @param createTime 创建时间
		 * @return 当前对象
		*/
		public DbInfo setCreateTime(Date createTime) {
			super.change(CREATE_TIME,super.getCreateTime(),createTime);
			super.setCreateTime(createTime);
			return this;
		}
		
		/**
		 * 设置 修改人ID
		 * @param updateBy 修改人ID
		 * @return 当前对象
		*/
		public DbInfo setUpdateBy(String updateBy) {
			super.change(UPDATE_BY,super.getUpdateBy(),updateBy);
			super.setUpdateBy(updateBy);
			return this;
		}
		
		/**
		 * 设置 修改时间
		 * @param updateTime 修改时间
		 * @return 当前对象
		*/
		public DbInfo setUpdateTime(Date updateTime) {
			super.change(UPDATE_TIME,super.getUpdateTime(),updateTime);
			super.setUpdateTime(updateTime);
			return this;
		}
		
		/**
		 * 设置 是否已删除
		 * @param deleted 是否已删除
		 * @return 当前对象
		*/
		public DbInfo setDeleted(Integer deleted) {
			super.change(DELETED,super.getDeleted(),deleted);
			super.setDeleted(deleted);
			return this;
		}
		
		/**
		 * 设置 删除人ID
		 * @param deleteBy 删除人ID
		 * @return 当前对象
		*/
		public DbInfo setDeleteBy(String deleteBy) {
			super.change(DELETE_BY,super.getDeleteBy(),deleteBy);
			super.setDeleteBy(deleteBy);
			return this;
		}
		
		/**
		 * 设置 删除时间
		 * @param deleteTime 删除时间
		 * @return 当前对象
		*/
		public DbInfo setDeleteTime(Date deleteTime) {
			super.change(DELETE_TIME,super.getDeleteTime(),deleteTime);
			super.setDeleteTime(deleteTime);
			return this;
		}
		
		/**
		 * 设置 version
		 * @param version version
		 * @return 当前对象
		*/
		public DbInfo setVersion(Integer version) {
			super.change(VERSION,super.getVersion(),version);
			super.setVersion(version);
			return this;
		}
		
		/**
		 * 设置 backupInfoList
		 * @param backupInfoList backupInfoList
		 * @return 当前对象
		*/
		public DbInfo setBackupInfoList(List<DbBackupInfo> backupInfoList) {
			super.change(BACKUP_INFO_LIST,super.getBackupInfoList(),backupInfoList);
			super.setBackupInfoList(backupInfoList);
			return this;
		}
		
		/**
		 * 设置 backupInfoIds
		 * @param backupInfoIds backupInfoIds
		 * @return 当前对象
		*/
		public DbInfo setBackupInfoIds(List<String> backupInfoIds) {
			super.change(BACKUP_INFO_IDS,super.getBackupInfoIds(),backupInfoIds);
			super.setBackupInfoIds(backupInfoIds);
			return this;
		}
		
		/**
		 * 设置 host
		 * @param host host
		 * @return 当前对象
		*/
		public DbInfo setHost(Host host) {
			super.change(HOST,super.getHost(),host);
			super.setHost(host);
			return this;
		}
		
		/**
		 * 设置 type
		 * @param type type
		 * @return 当前对象
		*/
		public DbInfo setType(ServiceInfo type) {
			super.change(TYPE,super.getType(),type);
			super.setType(type);
			return this;
		}
		
		/**
		 * 设置 deployModeDict
		 * @param deployModeDict deployModeDict
		 * @return 当前对象
		*/
		public DbInfo setDeployModeDict(DictItem deployModeDict) {
			super.change(DEPLOY_MODE_DICT,super.getDeployModeDict(),deployModeDict);
			super.setDeployModeDict(deployModeDict);
			return this;
		}
		
		/**
		 * 设置 labelList
		 * @param labelList labelList
		 * @return 当前对象
		*/
		public DbInfo setLabelList(List<DictItem> labelList) {
			super.change(LABEL_LIST,super.getLabelList(),labelList);
			super.setLabelList(labelList);
			return this;
		}
		
		/**
		 * 设置 labelIds
		 * @param labelIds labelIds
		 * @return 当前对象
		*/
		public DbInfo setLabelIds(List<String> labelIds) {
			super.change(LABEL_IDS,super.getLabelIds(),labelIds);
			super.setLabelIds(labelIds);
			return this;
		}
		
		/**
		 * 设置 dataLocData
		 * @param dataLocData dataLocData
		 * @return 当前对象
		*/
		public DbInfo setDataLocData(List<DictItem> dataLocData) {
			super.change(DATA_LOC_DATA,super.getDataLocData(),dataLocData);
			super.setDataLocData(dataLocData);
			return this;
		}
		
		/**
		 * 设置 dataLocIds
		 * @param dataLocIds dataLocIds
		 * @return 当前对象
		*/
		public DbInfo setDataLocIds(List<String> dataLocIds) {
			super.change(DATA_LOC_IDS,super.getDataLocIds(),dataLocIds);
			super.setDataLocIds(dataLocIds);
			return this;
		}

		/**
		 * 克隆当前对象
		*/
		@Transient
		public DbInfoVO clone() {
			return duplicate(true);
		}

		/**
		 * 复制当前对象
		 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
		*/
		@Transient
		public DbInfoVO duplicate(boolean all) {
			$$proxy$$ inst=new $$proxy$$();
			inst.setNotes(this.getNotes());
			inst.setLogMethod(this.getLogMethod());
			inst.setSelectedCode(this.getSelectedCode());
			inst.setBackupStatus(this.getBackupStatus());
			inst.setBackupStrategy(this.getBackupStrategy());
			inst.setUpdateBy(this.getUpdateBy());
			inst.setVoucherStr(this.getVoucherStr());
			inst.setId(this.getId());
			inst.setAdminUserList(this.getAdminUserList());
			inst.setBackupInfo(this.getBackupInfo());
			inst.setUserUseInfo(this.getUserUseInfo());
			inst.setDisasterRecoveryStrategy(this.getDisasterRecoveryStrategy());
			inst.setToolStrategy(this.getToolStrategy());
			inst.setAppUserList(this.getAppUserList());
			inst.setDeployMode(this.getDeployMode());
			inst.setOpsUserList(this.getOpsUserList());
			inst.setOtherUserList(this.getOtherUserList());
			inst.setHostId(this.getHostId());
			inst.setUpdateTime(this.getUpdateTime());
			inst.setVersion(this.getVersion());
			inst.setDbSize(this.getDbSize());
			inst.setClearStrategy(this.getClearStrategy());
			inst.setDbPort(this.getDbPort());
			inst.setCreateBy(this.getCreateBy());
			inst.setDataLoc(this.getDataLoc());
			inst.setDeleted(this.getDeleted());
			inst.setCreateTime(this.getCreateTime());
			inst.setDeleteTime(this.getDeleteTime());
			inst.setName(this.getName());
			inst.setDeleteBy(this.getDeleteBy());
			inst.setTypeId(this.getTypeId());
			inst.setStatus(this.getStatus());
			if(all) {
				inst.setSearchField(this.getSearchField());
				inst.setFuzzyField(this.getFuzzyField());
				inst.setBackupInfoList(this.getBackupInfoList());
				inst.setPageSize(this.getPageSize());
				inst.setType(this.getType());
				inst.setLabelList(this.getLabelList());
				inst.setLabelIds(this.getLabelIds());
				inst.setDataLocData(this.getDataLocData());
				inst.setPageIndex(this.getPageIndex());
				inst.setSortType(this.getSortType());
				inst.setDirtyFields(this.getDirtyFields());
				inst.setSortField(this.getSortField());
				inst.setHost(this.getHost());
				inst.setDataOrigin(this.getDataOrigin());
				inst.setIds(this.getIds());
				inst.setQueryLogic(this.getQueryLogic());
				inst.setBackupInfoIds(this.getBackupInfoIds());
				inst.setDeployModeDict(this.getDeployModeDict());
				inst.setSearchValue(this.getSearchValue());
				inst.setDataLocIds(this.getDataLocIds());
			}
			inst.clearModifies();
			return inst;
		}

	}
}