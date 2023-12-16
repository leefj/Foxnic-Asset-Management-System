package com.dt.platform.domain.eam.meta;

import com.github.foxnic.api.bean.BeanProperty;
import com.dt.platform.domain.eam.GoodsStock;
import java.math.BigDecimal;
import java.util.Date;
import org.github.foxnic.web.domain.pcm.Catalog;
import com.dt.platform.domain.eam.Manufacturer;
import com.dt.platform.domain.eam.Brand;
import org.github.foxnic.web.domain.hrm.Organization;
import com.dt.platform.domain.eam.Warehouse;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.hrm.Employee;
import java.util.List;
import javax.persistence.Transient;



/**
 * @author 金杰 , maillank@qq.com
 * @since 2023-12-16 22:49:59
 * @sign D035975DDE8DE6A681A26C0BC8A77F54
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

public class GoodsStockMeta {
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final String ID="id";
	
	/**
	 * 主键 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> ID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,ID, java.lang.String.class, "主键", "主键", java.lang.String.class, null);
	
	/**
	 * 所属 , 类型: java.lang.String
	*/
	public static final String OWNER_ID="ownerId";
	
	/**
	 * 所属 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> OWNER_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,OWNER_ID, java.lang.String.class, "所属", "所属", java.lang.String.class, null);
	
	/**
	 * 所属 , 类型: java.lang.String
	*/
	public static final String OWNER_TMP_ID="ownerTmpId";
	
	/**
	 * 所属 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> OWNER_TMP_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,OWNER_TMP_ID, java.lang.String.class, "所属", "所属", java.lang.String.class, null);
	
	/**
	 * 库存所属 , 类型: java.lang.String
	*/
	public static final String OWNER_CODE="ownerCode";
	
	/**
	 * 库存所属 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> OWNER_CODE_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,OWNER_CODE, java.lang.String.class, "库存所属", "库存所属", java.lang.String.class, null);
	
	/**
	 * 所属类型 , 类型: java.lang.String
	*/
	public static final String OWNER_TYPE="ownerType";
	
	/**
	 * 所属类型 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> OWNER_TYPE_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,OWNER_TYPE, java.lang.String.class, "所属类型", "所属类型", java.lang.String.class, null);
	
	/**
	 * 业务编号 , 类型: java.lang.String
	*/
	public static final String BUSINESS_CODE="businessCode";
	
	/**
	 * 业务编号 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> BUSINESS_CODE_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,BUSINESS_CODE, java.lang.String.class, "业务编号", "业务编号", java.lang.String.class, null);
	
	/**
	 * 办理状态 , 类型: java.lang.String
	*/
	public static final String STATUS="status";
	
	/**
	 * 办理状态 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> STATUS_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,STATUS, java.lang.String.class, "办理状态", "办理状态", java.lang.String.class, null);
	
	/**
	 * 状态 , 类型: java.lang.String
	*/
	public static final String GOODS_STATUS="goodsStatus";
	
	/**
	 * 状态 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> GOODS_STATUS_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,GOODS_STATUS, java.lang.String.class, "状态", "状态", java.lang.String.class, null);
	
	/**
	 * 档案分类 , 类型: java.lang.String
	*/
	public static final String CATEGORY_ID="categoryId";
	
	/**
	 * 档案分类 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> CATEGORY_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,CATEGORY_ID, java.lang.String.class, "档案分类", "档案分类", java.lang.String.class, null);
	
	/**
	 * 资产分类 , 类型: java.lang.String
	*/
	public static final String ASSET_CATEGORY_ID="assetCategoryId";
	
	/**
	 * 资产分类 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> ASSET_CATEGORY_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,ASSET_CATEGORY_ID, java.lang.String.class, "资产分类", "资产分类", java.lang.String.class, null);
	
	/**
	 * 物品名称 , 类型: java.lang.String
	*/
	public static final String NAME="name";
	
	/**
	 * 物品名称 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> NAME_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,NAME, java.lang.String.class, "物品名称", "物品名称", java.lang.String.class, null);
	
	/**
	 * 规格型号 , 类型: java.lang.String
	*/
	public static final String MODEL="model";
	
	/**
	 * 规格型号 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> MODEL_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,MODEL, java.lang.String.class, "规格型号", "规格型号", java.lang.String.class, null);
	
	/**
	 * 物品编码 , 类型: java.lang.String
	*/
	public static final String CODE="code";
	
	/**
	 * 物品编码 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> CODE_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,CODE, java.lang.String.class, "物品编码", "物品编码", java.lang.String.class, null);
	
	/**
	 * 物品条码 , 类型: java.lang.String
	*/
	public static final String BAR_CODE="barCode";
	
	/**
	 * 物品条码 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> BAR_CODE_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,BAR_CODE, java.lang.String.class, "物品条码", "物品条码", java.lang.String.class, null);
	
	/**
	 * 厂商 , 类型: java.lang.String
	*/
	public static final String MANUFACTURER_ID="manufacturerId";
	
	/**
	 * 厂商 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> MANUFACTURER_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,MANUFACTURER_ID, java.lang.String.class, "厂商", "厂商", java.lang.String.class, null);
	
	/**
	 * 品牌商标 , 类型: java.lang.String
	*/
	public static final String BRAND_ID="brandId";
	
	/**
	 * 品牌商标 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> BRAND_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,BRAND_ID, java.lang.String.class, "品牌商标", "品牌商标", java.lang.String.class, null);
	
	/**
	 * 默认单价 , 类型: java.math.BigDecimal
	*/
	public static final String UNIT_PRICE="unitPrice";
	
	/**
	 * 默认单价 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.math.BigDecimal> UNIT_PRICE_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,UNIT_PRICE, java.math.BigDecimal.class, "默认单价", "默认单价", java.math.BigDecimal.class, null);
	
	/**
	 * 计量单位 , 类型: java.lang.String
	*/
	public static final String UNIT="unit";
	
	/**
	 * 计量单位 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> UNIT_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,UNIT, java.lang.String.class, "计量单位", "计量单位", java.lang.String.class, null);
	
	/**
	 * 安全库存下限 , 类型: java.math.BigDecimal
	*/
	public static final String STOCK_MIN="stockMin";
	
	/**
	 * 安全库存下限 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.math.BigDecimal> STOCK_MIN_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,STOCK_MIN, java.math.BigDecimal.class, "安全库存下限", "安全库存下限", java.math.BigDecimal.class, null);
	
	/**
	 * 安全库存上限 , 类型: java.math.BigDecimal
	*/
	public static final String STOCK_MAX="stockMax";
	
	/**
	 * 安全库存上限 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.math.BigDecimal> STOCK_MAX_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,STOCK_MAX, java.math.BigDecimal.class, "安全库存上限", "安全库存上限", java.math.BigDecimal.class, null);
	
	/**
	 * 安全库存 , 类型: java.math.BigDecimal
	*/
	public static final String STOCK_SECURITY="stockSecurity";
	
	/**
	 * 安全库存 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.math.BigDecimal> STOCK_SECURITY_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,STOCK_SECURITY, java.math.BigDecimal.class, "安全库存", "安全库存", java.math.BigDecimal.class, null);
	
	/**
	 * 物品图片 , 类型: java.lang.String
	*/
	public static final String PICTURE_ID="pictureId";
	
	/**
	 * 物品图片 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> PICTURE_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,PICTURE_ID, java.lang.String.class, "物品图片", "物品图片", java.lang.String.class, null);
	
	/**
	 * 备注 , 类型: java.lang.String
	*/
	public static final String NOTES="notes";
	
	/**
	 * 备注 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> NOTES_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,NOTES, java.lang.String.class, "备注", "备注", java.lang.String.class, null);
	
	/**
	 * 批次号 , 类型: java.lang.String
	*/
	public static final String BATCH_CODE="batchCode";
	
	/**
	 * 批次号 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> BATCH_CODE_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,BATCH_CODE, java.lang.String.class, "批次号", "批次号", java.lang.String.class, null);
	
	/**
	 * 所属公司 , 类型: java.lang.String
	*/
	public static final String OWN_COMPANY_ID="ownCompanyId";
	
	/**
	 * 所属公司 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> OWN_COMPANY_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,OWN_COMPANY_ID, java.lang.String.class, "所属公司", "所属公司", java.lang.String.class, null);
	
	/**
	 * 使用组织 , 类型: java.lang.String
	*/
	public static final String USE_ORG_ID="useOrgId";
	
	/**
	 * 使用组织 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> USE_ORG_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,USE_ORG_ID, java.lang.String.class, "使用组织", "使用组织", java.lang.String.class, null);
	
	/**
	 * 供应商 , 类型: java.lang.String
	*/
	public static final String SUPPLIER_NAME="supplierName";
	
	/**
	 * 供应商 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> SUPPLIER_NAME_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,SUPPLIER_NAME, java.lang.String.class, "供应商", "供应商", java.lang.String.class, null);
	
	/**
	 * 仓库 , 类型: java.lang.String
	*/
	public static final String WAREHOUSE_ID="warehouseId";
	
	/**
	 * 仓库 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> WAREHOUSE_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,WAREHOUSE_ID, java.lang.String.class, "仓库", "仓库", java.lang.String.class, null);
	
	/**
	 * 来源 , 类型: java.lang.String
	*/
	public static final String SOURCE_ID="sourceId";
	
	/**
	 * 来源 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> SOURCE_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,SOURCE_ID, java.lang.String.class, "来源", "来源", java.lang.String.class, null);
	
	/**
	 * 物品 , 类型: java.lang.String
	*/
	public static final String GOODS_ID="goodsId";
	
	/**
	 * 物品 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> GOODS_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,GOODS_ID, java.lang.String.class, "物品", "物品", java.lang.String.class, null);
	
	/**
	 * 入库数量 , 类型: java.math.BigDecimal
	*/
	public static final String STOCK_IN_NUMBER="stockInNumber";
	
	/**
	 * 入库数量 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.math.BigDecimal> STOCK_IN_NUMBER_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,STOCK_IN_NUMBER, java.math.BigDecimal.class, "入库数量", "入库数量", java.math.BigDecimal.class, null);
	
	/**
	 * 当前数量 , 类型: java.math.BigDecimal
	*/
	public static final String STOCK_CUR_NUMBER="stockCurNumber";
	
	/**
	 * 当前数量 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.math.BigDecimal> STOCK_CUR_NUMBER_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,STOCK_CUR_NUMBER, java.math.BigDecimal.class, "当前数量", "当前数量", java.math.BigDecimal.class, null);
	
	/**
	 * 总金额 , 类型: java.math.BigDecimal
	*/
	public static final String AMOUNT="amount";
	
	/**
	 * 总金额 , 类型: java.math.BigDecimal
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.math.BigDecimal> AMOUNT_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,AMOUNT, java.math.BigDecimal.class, "总金额", "总金额", java.math.BigDecimal.class, null);
	
	/**
	 * 管理人 , 类型: java.lang.String
	*/
	public static final String MANAGER_ID="managerId";
	
	/**
	 * 管理人 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> MANAGER_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,MANAGER_ID, java.lang.String.class, "管理人", "管理人", java.lang.String.class, null);
	
	/**
	 * 入库时间 , 类型: java.util.Date
	*/
	public static final String STORAGE_DATE="storageDate";
	
	/**
	 * 入库时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.util.Date> STORAGE_DATE_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,STORAGE_DATE, java.util.Date.class, "入库时间", "入库时间", java.util.Date.class, null);
	
	/**
	 * 库存数据 , 类型: java.lang.String
	*/
	public static final String REAL_STOCK_ID="realStockId";
	
	/**
	 * 库存数据 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> REAL_STOCK_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,REAL_STOCK_ID, java.lang.String.class, "库存数据", "库存数据", java.lang.String.class, null);
	
	/**
	 * 制单人 , 类型: java.lang.String
	*/
	public static final String ORIGINATOR_ID="originatorId";
	
	/**
	 * 制单人 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> ORIGINATOR_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,ORIGINATOR_ID, java.lang.String.class, "制单人", "制单人", java.lang.String.class, null);
	
	/**
	 * 父级物品 , 类型: java.lang.String
	*/
	public static final String PID="pid";
	
	/**
	 * 父级物品 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> PID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,PID, java.lang.String.class, "父级物品", "父级物品", java.lang.String.class, null);
	
	/**
	 * 选择 , 类型: java.lang.String
	*/
	public static final String SELECTED_CODE="selectedCode";
	
	/**
	 * 选择 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> SELECTED_CODE_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,SELECTED_CODE, java.lang.String.class, "选择", "选择", java.lang.String.class, null);
	
	/**
	 * 操作类型 , 类型: java.lang.String
	*/
	public static final String INTER_OPER_TYPE="interOperType";
	
	/**
	 * 操作类型 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> INTER_OPER_TYPE_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,INTER_OPER_TYPE, java.lang.String.class, "操作类型", "操作类型", java.lang.String.class, null);
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final String CREATE_BY="createBy";
	
	/**
	 * 创建人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> CREATE_BY_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,CREATE_BY, java.lang.String.class, "创建人ID", "创建人ID", java.lang.String.class, null);
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final String CREATE_TIME="createTime";
	
	/**
	 * 创建时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.util.Date> CREATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,CREATE_TIME, java.util.Date.class, "创建时间", "创建时间", java.util.Date.class, null);
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final String UPDATE_BY="updateBy";
	
	/**
	 * 修改人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> UPDATE_BY_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,UPDATE_BY, java.lang.String.class, "修改人ID", "修改人ID", java.lang.String.class, null);
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final String UPDATE_TIME="updateTime";
	
	/**
	 * 修改时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.util.Date> UPDATE_TIME_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,UPDATE_TIME, java.util.Date.class, "修改时间", "修改时间", java.util.Date.class, null);
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final String DELETED="deleted";
	
	/**
	 * 是否已删除 , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.Integer> DELETED_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,DELETED, java.lang.Integer.class, "是否已删除", "是否已删除", java.lang.Integer.class, null);
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final String DELETE_BY="deleteBy";
	
	/**
	 * 删除人ID , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> DELETE_BY_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,DELETE_BY, java.lang.String.class, "删除人ID", "删除人ID", java.lang.String.class, null);
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final String DELETE_TIME="deleteTime";
	
	/**
	 * 删除时间 , 类型: java.util.Date
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.util.Date> DELETE_TIME_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,DELETE_TIME, java.util.Date.class, "删除时间", "删除时间", java.util.Date.class, null);
	
	/**
	 * version , 类型: java.lang.Integer
	*/
	public static final String VERSION="version";
	
	/**
	 * version , 类型: java.lang.Integer
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.Integer> VERSION_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,VERSION, java.lang.Integer.class, "version", "version", java.lang.Integer.class, null);
	
	/**
	 * 租户 , 类型: java.lang.String
	*/
	public static final String TENANT_ID="tenantId";
	
	/**
	 * 租户 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> TENANT_ID_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,TENANT_ID, java.lang.String.class, "租户", "租户", java.lang.String.class, null);
	
	/**
	 * 资产分类 , 类型: org.github.foxnic.web.domain.pcm.Catalog
	*/
	public static final String CATEGORY="category";
	
	/**
	 * 资产分类 , 类型: org.github.foxnic.web.domain.pcm.Catalog
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,org.github.foxnic.web.domain.pcm.Catalog> CATEGORY_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,CATEGORY, org.github.foxnic.web.domain.pcm.Catalog.class, "资产分类", "资产分类", org.github.foxnic.web.domain.pcm.Catalog.class, null);
	
	/**
	 * 生产厂商 , 类型: com.dt.platform.domain.eam.Manufacturer
	*/
	public static final String MANUFACTURER="manufacturer";
	
	/**
	 * 生产厂商 , 类型: com.dt.platform.domain.eam.Manufacturer
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,com.dt.platform.domain.eam.Manufacturer> MANUFACTURER_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,MANUFACTURER, com.dt.platform.domain.eam.Manufacturer.class, "生产厂商", "生产厂商", com.dt.platform.domain.eam.Manufacturer.class, null);
	
	/**
	 * 品牌 , 类型: com.dt.platform.domain.eam.Brand
	*/
	public static final String BRAND="brand";
	
	/**
	 * 品牌 , 类型: com.dt.platform.domain.eam.Brand
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,com.dt.platform.domain.eam.Brand> BRAND_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,BRAND, com.dt.platform.domain.eam.Brand.class, "品牌", "品牌", com.dt.platform.domain.eam.Brand.class, null);
	
	/**
	 * 物品 , 类型: com.dt.platform.domain.eam.GoodsStock
	*/
	public static final String GOODS="goods";
	
	/**
	 * 物品 , 类型: com.dt.platform.domain.eam.GoodsStock
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,com.dt.platform.domain.eam.GoodsStock> GOODS_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,GOODS, com.dt.platform.domain.eam.GoodsStock.class, "物品", "物品", com.dt.platform.domain.eam.GoodsStock.class, null);
	
	/**
	 * 所属公司 , 类型: org.github.foxnic.web.domain.hrm.Organization
	*/
	public static final String OWNER_COMPANY="ownerCompany";
	
	/**
	 * 所属公司 , 类型: org.github.foxnic.web.domain.hrm.Organization
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,org.github.foxnic.web.domain.hrm.Organization> OWNER_COMPANY_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,OWNER_COMPANY, org.github.foxnic.web.domain.hrm.Organization.class, "所属公司", "所属公司", org.github.foxnic.web.domain.hrm.Organization.class, null);
	
	/**
	 * 使用公司/部门 , 类型: org.github.foxnic.web.domain.hrm.Organization
	*/
	public static final String USE_ORGANIZATION="useOrganization";
	
	/**
	 * 使用公司/部门 , 类型: org.github.foxnic.web.domain.hrm.Organization
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,org.github.foxnic.web.domain.hrm.Organization> USE_ORGANIZATION_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,USE_ORGANIZATION, org.github.foxnic.web.domain.hrm.Organization.class, "使用公司/部门", "使用公司/部门", org.github.foxnic.web.domain.hrm.Organization.class, null);
	
	/**
	 * 仓库 , 类型: com.dt.platform.domain.eam.Warehouse
	*/
	public static final String WAREHOUSE="warehouse";
	
	/**
	 * 仓库 , 类型: com.dt.platform.domain.eam.Warehouse
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,com.dt.platform.domain.eam.Warehouse> WAREHOUSE_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,WAREHOUSE, com.dt.platform.domain.eam.Warehouse.class, "仓库", "仓库", com.dt.platform.domain.eam.Warehouse.class, null);
	
	/**
	 * 来源 , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final String SOURCE="source";
	
	/**
	 * 来源 , 类型: org.github.foxnic.web.domain.system.DictItem
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,org.github.foxnic.web.domain.system.DictItem> SOURCE_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,SOURCE, org.github.foxnic.web.domain.system.DictItem.class, "来源", "来源", org.github.foxnic.web.domain.system.DictItem.class, null);
	
	/**
	 * 管理人 , 类型: org.github.foxnic.web.domain.hrm.Employee
	*/
	public static final String MANAGER="manager";
	
	/**
	 * 管理人 , 类型: org.github.foxnic.web.domain.hrm.Employee
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,org.github.foxnic.web.domain.hrm.Employee> MANAGER_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,MANAGER, org.github.foxnic.web.domain.hrm.Employee.class, "管理人", "管理人", org.github.foxnic.web.domain.hrm.Employee.class, null);
	
	/**
	 * 制单人 , 类型: org.github.foxnic.web.domain.hrm.Employee
	*/
	public static final String ORIGINATOR="originator";
	
	/**
	 * 制单人 , 类型: org.github.foxnic.web.domain.hrm.Employee
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,org.github.foxnic.web.domain.hrm.Employee> ORIGINATOR_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,ORIGINATOR, org.github.foxnic.web.domain.hrm.Employee.class, "制单人", "制单人", org.github.foxnic.web.domain.hrm.Employee.class, null);
	
	/**
	 * 库存数据 , 类型: com.dt.platform.domain.eam.GoodsStock
	*/
	public static final String REAL_GOODS="realGoods";
	
	/**
	 * 库存数据 , 类型: com.dt.platform.domain.eam.GoodsStock
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,com.dt.platform.domain.eam.GoodsStock> REAL_GOODS_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,REAL_GOODS, com.dt.platform.domain.eam.GoodsStock.class, "库存数据", "库存数据", com.dt.platform.domain.eam.GoodsStock.class, null);
	
	/**
	 * 类型 , 类型: java.lang.String
	*/
	public static final String GOODS_MODEL="goodsModel";
	
	/**
	 * 类型 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> GOODS_MODEL_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,GOODS_MODEL, java.lang.String.class, "类型", "类型", java.lang.String.class, null);
	
	/**
	 * 名称 , 类型: java.lang.String
	*/
	public static final String GOODS_NAME="goodsName";
	
	/**
	 * 名称 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> GOODS_NAME_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,GOODS_NAME, java.lang.String.class, "名称", "名称", java.lang.String.class, null);
	
	/**
	 * 分类 , 类型: java.lang.String
	*/
	public static final String GOODS_CATEGORY_NAME="goodsCategoryName";
	
	/**
	 * 分类 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> GOODS_CATEGORY_NAME_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,GOODS_CATEGORY_NAME, java.lang.String.class, "分类", "分类", java.lang.String.class, null);
	
	/**
	 * 计算单位 , 类型: java.lang.String
	*/
	public static final String GOODS_UNIT="goodsUnit";
	
	/**
	 * 计算单位 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> GOODS_UNIT_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,GOODS_UNIT, java.lang.String.class, "计算单位", "计算单位", java.lang.String.class, null);
	
	/**
	 * 物品编码 , 类型: java.lang.String
	*/
	public static final String GOODS_CODE="goodsCode";
	
	/**
	 * 物品编码 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> GOODS_CODE_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,GOODS_CODE, java.lang.String.class, "物品编码", "物品编码", java.lang.String.class, null);
	
	/**
	 * 物品条码 , 类型: java.lang.String
	*/
	public static final String GOODS_BAR_CODE="goodsBarCode";
	
	/**
	 * 物品条码 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> GOODS_BAR_CODE_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,GOODS_BAR_CODE, java.lang.String.class, "物品条码", "物品条码", java.lang.String.class, null);
	
	/**
	 * 物品条码 , 类型: java.lang.String
	*/
	public static final String GOODS_STOCK_MAX="goodsStockMax";
	
	/**
	 * 物品条码 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> GOODS_STOCK_MAX_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,GOODS_STOCK_MAX, java.lang.String.class, "物品条码", "物品条码", java.lang.String.class, null);
	
	/**
	 * 物品条码 , 类型: java.lang.String
	*/
	public static final String GOODS_STOCK_MIN="goodsStockMin";
	
	/**
	 * 物品条码 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> GOODS_STOCK_MIN_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,GOODS_STOCK_MIN, java.lang.String.class, "物品条码", "物品条码", java.lang.String.class, null);
	
	/**
	 * 物品条码 , 类型: java.lang.String
	*/
	public static final String GOODS_STOCK_SECURITY="goodsStockSecurity";
	
	/**
	 * 物品条码 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> GOODS_STOCK_SECURITY_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,GOODS_STOCK_SECURITY, java.lang.String.class, "物品条码", "物品条码", java.lang.String.class, null);
	
	/**
	 * 库存备注 , 类型: java.lang.String
	*/
	public static final String GOODS_STOCK_NOTES="goodsStockNotes";
	
	/**
	 * 库存备注 , 类型: java.lang.String
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,java.lang.String> GOODS_STOCK_NOTES_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,GOODS_STOCK_NOTES, java.lang.String.class, "库存备注", "库存备注", java.lang.String.class, null);
	
	/**
	 * parentGoodsStock , 类型: com.dt.platform.domain.eam.GoodsStock
	*/
	public static final String PARENT_GOODS_STOCK="parentGoodsStock";
	
	/**
	 * parentGoodsStock , 类型: com.dt.platform.domain.eam.GoodsStock
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,com.dt.platform.domain.eam.GoodsStock> PARENT_GOODS_STOCK_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,PARENT_GOODS_STOCK, com.dt.platform.domain.eam.GoodsStock.class, "parentGoodsStock", "parentGoodsStock", com.dt.platform.domain.eam.GoodsStock.class, null);
	
	/**
	 * subGoodsStockList , 集合类型: LIST , 类型: com.dt.platform.domain.eam.GoodsStock
	*/
	public static final String SUB_GOODS_STOCK_LIST="subGoodsStockList";
	
	/**
	 * subGoodsStockList , 集合类型: LIST , 类型: com.dt.platform.domain.eam.GoodsStock
	*/
	public static final BeanProperty<com.dt.platform.domain.eam.GoodsStock,com.dt.platform.domain.eam.GoodsStock> SUB_GOODS_STOCK_LIST_PROP = new BeanProperty(com.dt.platform.domain.eam.GoodsStock.class ,SUB_GOODS_STOCK_LIST, java.util.List.class, "subGoodsStockList", "subGoodsStockList", com.dt.platform.domain.eam.GoodsStock.class, null);
	
	/**
	 * 全部属性清单
	*/
	public static final String[] $PROPS={ ID , OWNER_ID , OWNER_TMP_ID , OWNER_CODE , OWNER_TYPE , BUSINESS_CODE , STATUS , GOODS_STATUS , CATEGORY_ID , ASSET_CATEGORY_ID , NAME , MODEL , CODE , BAR_CODE , MANUFACTURER_ID , BRAND_ID , UNIT_PRICE , UNIT , STOCK_MIN , STOCK_MAX , STOCK_SECURITY , PICTURE_ID , NOTES , BATCH_CODE , OWN_COMPANY_ID , USE_ORG_ID , SUPPLIER_NAME , WAREHOUSE_ID , SOURCE_ID , GOODS_ID , STOCK_IN_NUMBER , STOCK_CUR_NUMBER , AMOUNT , MANAGER_ID , STORAGE_DATE , REAL_STOCK_ID , ORIGINATOR_ID , PID , SELECTED_CODE , INTER_OPER_TYPE , CREATE_BY , CREATE_TIME , UPDATE_BY , UPDATE_TIME , DELETED , DELETE_BY , DELETE_TIME , VERSION , TENANT_ID , CATEGORY , MANUFACTURER , BRAND , GOODS , OWNER_COMPANY , USE_ORGANIZATION , WAREHOUSE , SOURCE , MANAGER , ORIGINATOR , REAL_GOODS , GOODS_MODEL , GOODS_NAME , GOODS_CATEGORY_NAME , GOODS_UNIT , GOODS_CODE , GOODS_BAR_CODE , GOODS_STOCK_MAX , GOODS_STOCK_MIN , GOODS_STOCK_SECURITY , GOODS_STOCK_NOTES , PARENT_GOODS_STOCK , SUB_GOODS_STOCK_LIST };
	
	/**
	 * 代理类
	*/
	public static class $$proxy$$ extends com.dt.platform.domain.eam.GoodsStock {

		private static final long serialVersionUID = 1L;

		
		/**
		 * 设置 主键
		 * @param id 主键
		 * @return 当前对象
		*/
		public GoodsStock setId(String id) {
			super.change(ID,super.getId(),id);
			super.setId(id);
			return this;
		}
		
		/**
		 * 设置 所属
		 * @param ownerId 所属
		 * @return 当前对象
		*/
		public GoodsStock setOwnerId(String ownerId) {
			super.change(OWNER_ID,super.getOwnerId(),ownerId);
			super.setOwnerId(ownerId);
			return this;
		}
		
		/**
		 * 设置 所属
		 * @param ownerTmpId 所属
		 * @return 当前对象
		*/
		public GoodsStock setOwnerTmpId(String ownerTmpId) {
			super.change(OWNER_TMP_ID,super.getOwnerTmpId(),ownerTmpId);
			super.setOwnerTmpId(ownerTmpId);
			return this;
		}
		
		/**
		 * 设置 库存所属
		 * @param ownerCode 库存所属
		 * @return 当前对象
		*/
		public GoodsStock setOwnerCode(String ownerCode) {
			super.change(OWNER_CODE,super.getOwnerCode(),ownerCode);
			super.setOwnerCode(ownerCode);
			return this;
		}
		
		/**
		 * 设置 所属类型
		 * @param ownerType 所属类型
		 * @return 当前对象
		*/
		public GoodsStock setOwnerType(String ownerType) {
			super.change(OWNER_TYPE,super.getOwnerType(),ownerType);
			super.setOwnerType(ownerType);
			return this;
		}
		
		/**
		 * 设置 业务编号
		 * @param businessCode 业务编号
		 * @return 当前对象
		*/
		public GoodsStock setBusinessCode(String businessCode) {
			super.change(BUSINESS_CODE,super.getBusinessCode(),businessCode);
			super.setBusinessCode(businessCode);
			return this;
		}
		
		/**
		 * 设置 办理状态
		 * @param status 办理状态
		 * @return 当前对象
		*/
		public GoodsStock setStatus(String status) {
			super.change(STATUS,super.getStatus(),status);
			super.setStatus(status);
			return this;
		}
		
		/**
		 * 设置 状态
		 * @param goodsStatus 状态
		 * @return 当前对象
		*/
		public GoodsStock setGoodsStatus(String goodsStatus) {
			super.change(GOODS_STATUS,super.getGoodsStatus(),goodsStatus);
			super.setGoodsStatus(goodsStatus);
			return this;
		}
		
		/**
		 * 设置 档案分类
		 * @param categoryId 档案分类
		 * @return 当前对象
		*/
		public GoodsStock setCategoryId(String categoryId) {
			super.change(CATEGORY_ID,super.getCategoryId(),categoryId);
			super.setCategoryId(categoryId);
			return this;
		}
		
		/**
		 * 设置 资产分类
		 * @param assetCategoryId 资产分类
		 * @return 当前对象
		*/
		public GoodsStock setAssetCategoryId(String assetCategoryId) {
			super.change(ASSET_CATEGORY_ID,super.getAssetCategoryId(),assetCategoryId);
			super.setAssetCategoryId(assetCategoryId);
			return this;
		}
		
		/**
		 * 设置 物品名称
		 * @param name 物品名称
		 * @return 当前对象
		*/
		public GoodsStock setName(String name) {
			super.change(NAME,super.getName(),name);
			super.setName(name);
			return this;
		}
		
		/**
		 * 设置 规格型号
		 * @param model 规格型号
		 * @return 当前对象
		*/
		public GoodsStock setModel(String model) {
			super.change(MODEL,super.getModel(),model);
			super.setModel(model);
			return this;
		}
		
		/**
		 * 设置 物品编码
		 * @param code 物品编码
		 * @return 当前对象
		*/
		public GoodsStock setCode(String code) {
			super.change(CODE,super.getCode(),code);
			super.setCode(code);
			return this;
		}
		
		/**
		 * 设置 物品条码
		 * @param barCode 物品条码
		 * @return 当前对象
		*/
		public GoodsStock setBarCode(String barCode) {
			super.change(BAR_CODE,super.getBarCode(),barCode);
			super.setBarCode(barCode);
			return this;
		}
		
		/**
		 * 设置 厂商
		 * @param manufacturerId 厂商
		 * @return 当前对象
		*/
		public GoodsStock setManufacturerId(String manufacturerId) {
			super.change(MANUFACTURER_ID,super.getManufacturerId(),manufacturerId);
			super.setManufacturerId(manufacturerId);
			return this;
		}
		
		/**
		 * 设置 品牌商标
		 * @param brandId 品牌商标
		 * @return 当前对象
		*/
		public GoodsStock setBrandId(String brandId) {
			super.change(BRAND_ID,super.getBrandId(),brandId);
			super.setBrandId(brandId);
			return this;
		}
		
		/**
		 * 设置 默认单价
		 * @param unitPrice 默认单价
		 * @return 当前对象
		*/
		public GoodsStock setUnitPrice(BigDecimal unitPrice) {
			super.change(UNIT_PRICE,super.getUnitPrice(),unitPrice);
			super.setUnitPrice(unitPrice);
			return this;
		}
		
		/**
		 * 设置 计量单位
		 * @param unit 计量单位
		 * @return 当前对象
		*/
		public GoodsStock setUnit(String unit) {
			super.change(UNIT,super.getUnit(),unit);
			super.setUnit(unit);
			return this;
		}
		
		/**
		 * 设置 安全库存下限
		 * @param stockMin 安全库存下限
		 * @return 当前对象
		*/
		public GoodsStock setStockMin(BigDecimal stockMin) {
			super.change(STOCK_MIN,super.getStockMin(),stockMin);
			super.setStockMin(stockMin);
			return this;
		}
		
		/**
		 * 设置 安全库存上限
		 * @param stockMax 安全库存上限
		 * @return 当前对象
		*/
		public GoodsStock setStockMax(BigDecimal stockMax) {
			super.change(STOCK_MAX,super.getStockMax(),stockMax);
			super.setStockMax(stockMax);
			return this;
		}
		
		/**
		 * 设置 安全库存
		 * @param stockSecurity 安全库存
		 * @return 当前对象
		*/
		public GoodsStock setStockSecurity(BigDecimal stockSecurity) {
			super.change(STOCK_SECURITY,super.getStockSecurity(),stockSecurity);
			super.setStockSecurity(stockSecurity);
			return this;
		}
		
		/**
		 * 设置 物品图片
		 * @param pictureId 物品图片
		 * @return 当前对象
		*/
		public GoodsStock setPictureId(String pictureId) {
			super.change(PICTURE_ID,super.getPictureId(),pictureId);
			super.setPictureId(pictureId);
			return this;
		}
		
		/**
		 * 设置 备注
		 * @param notes 备注
		 * @return 当前对象
		*/
		public GoodsStock setNotes(String notes) {
			super.change(NOTES,super.getNotes(),notes);
			super.setNotes(notes);
			return this;
		}
		
		/**
		 * 设置 批次号
		 * @param batchCode 批次号
		 * @return 当前对象
		*/
		public GoodsStock setBatchCode(String batchCode) {
			super.change(BATCH_CODE,super.getBatchCode(),batchCode);
			super.setBatchCode(batchCode);
			return this;
		}
		
		/**
		 * 设置 所属公司
		 * @param ownCompanyId 所属公司
		 * @return 当前对象
		*/
		public GoodsStock setOwnCompanyId(String ownCompanyId) {
			super.change(OWN_COMPANY_ID,super.getOwnCompanyId(),ownCompanyId);
			super.setOwnCompanyId(ownCompanyId);
			return this;
		}
		
		/**
		 * 设置 使用组织
		 * @param useOrgId 使用组织
		 * @return 当前对象
		*/
		public GoodsStock setUseOrgId(String useOrgId) {
			super.change(USE_ORG_ID,super.getUseOrgId(),useOrgId);
			super.setUseOrgId(useOrgId);
			return this;
		}
		
		/**
		 * 设置 供应商
		 * @param supplierName 供应商
		 * @return 当前对象
		*/
		public GoodsStock setSupplierName(String supplierName) {
			super.change(SUPPLIER_NAME,super.getSupplierName(),supplierName);
			super.setSupplierName(supplierName);
			return this;
		}
		
		/**
		 * 设置 仓库
		 * @param warehouseId 仓库
		 * @return 当前对象
		*/
		public GoodsStock setWarehouseId(String warehouseId) {
			super.change(WAREHOUSE_ID,super.getWarehouseId(),warehouseId);
			super.setWarehouseId(warehouseId);
			return this;
		}
		
		/**
		 * 设置 来源
		 * @param sourceId 来源
		 * @return 当前对象
		*/
		public GoodsStock setSourceId(String sourceId) {
			super.change(SOURCE_ID,super.getSourceId(),sourceId);
			super.setSourceId(sourceId);
			return this;
		}
		
		/**
		 * 设置 物品
		 * @param goodsId 物品
		 * @return 当前对象
		*/
		public GoodsStock setGoodsId(String goodsId) {
			super.change(GOODS_ID,super.getGoodsId(),goodsId);
			super.setGoodsId(goodsId);
			return this;
		}
		
		/**
		 * 设置 入库数量
		 * @param stockInNumber 入库数量
		 * @return 当前对象
		*/
		public GoodsStock setStockInNumber(BigDecimal stockInNumber) {
			super.change(STOCK_IN_NUMBER,super.getStockInNumber(),stockInNumber);
			super.setStockInNumber(stockInNumber);
			return this;
		}
		
		/**
		 * 设置 当前数量
		 * @param stockCurNumber 当前数量
		 * @return 当前对象
		*/
		public GoodsStock setStockCurNumber(BigDecimal stockCurNumber) {
			super.change(STOCK_CUR_NUMBER,super.getStockCurNumber(),stockCurNumber);
			super.setStockCurNumber(stockCurNumber);
			return this;
		}
		
		/**
		 * 设置 总金额
		 * @param amount 总金额
		 * @return 当前对象
		*/
		public GoodsStock setAmount(BigDecimal amount) {
			super.change(AMOUNT,super.getAmount(),amount);
			super.setAmount(amount);
			return this;
		}
		
		/**
		 * 设置 管理人
		 * @param managerId 管理人
		 * @return 当前对象
		*/
		public GoodsStock setManagerId(String managerId) {
			super.change(MANAGER_ID,super.getManagerId(),managerId);
			super.setManagerId(managerId);
			return this;
		}
		
		/**
		 * 设置 入库时间
		 * @param storageDate 入库时间
		 * @return 当前对象
		*/
		public GoodsStock setStorageDate(Date storageDate) {
			super.change(STORAGE_DATE,super.getStorageDate(),storageDate);
			super.setStorageDate(storageDate);
			return this;
		}
		
		/**
		 * 设置 库存数据
		 * @param realStockId 库存数据
		 * @return 当前对象
		*/
		public GoodsStock setRealStockId(String realStockId) {
			super.change(REAL_STOCK_ID,super.getRealStockId(),realStockId);
			super.setRealStockId(realStockId);
			return this;
		}
		
		/**
		 * 设置 制单人
		 * @param originatorId 制单人
		 * @return 当前对象
		*/
		public GoodsStock setOriginatorId(String originatorId) {
			super.change(ORIGINATOR_ID,super.getOriginatorId(),originatorId);
			super.setOriginatorId(originatorId);
			return this;
		}
		
		/**
		 * 设置 父级物品
		 * @param pid 父级物品
		 * @return 当前对象
		*/
		public GoodsStock setPid(String pid) {
			super.change(PID,super.getPid(),pid);
			super.setPid(pid);
			return this;
		}
		
		/**
		 * 设置 选择
		 * @param selectedCode 选择
		 * @return 当前对象
		*/
		public GoodsStock setSelectedCode(String selectedCode) {
			super.change(SELECTED_CODE,super.getSelectedCode(),selectedCode);
			super.setSelectedCode(selectedCode);
			return this;
		}
		
		/**
		 * 设置 操作类型
		 * @param interOperType 操作类型
		 * @return 当前对象
		*/
		public GoodsStock setInterOperType(String interOperType) {
			super.change(INTER_OPER_TYPE,super.getInterOperType(),interOperType);
			super.setInterOperType(interOperType);
			return this;
		}
		
		/**
		 * 设置 创建人ID
		 * @param createBy 创建人ID
		 * @return 当前对象
		*/
		public GoodsStock setCreateBy(String createBy) {
			super.change(CREATE_BY,super.getCreateBy(),createBy);
			super.setCreateBy(createBy);
			return this;
		}
		
		/**
		 * 设置 创建时间
		 * @param createTime 创建时间
		 * @return 当前对象
		*/
		public GoodsStock setCreateTime(Date createTime) {
			super.change(CREATE_TIME,super.getCreateTime(),createTime);
			super.setCreateTime(createTime);
			return this;
		}
		
		/**
		 * 设置 修改人ID
		 * @param updateBy 修改人ID
		 * @return 当前对象
		*/
		public GoodsStock setUpdateBy(String updateBy) {
			super.change(UPDATE_BY,super.getUpdateBy(),updateBy);
			super.setUpdateBy(updateBy);
			return this;
		}
		
		/**
		 * 设置 修改时间
		 * @param updateTime 修改时间
		 * @return 当前对象
		*/
		public GoodsStock setUpdateTime(Date updateTime) {
			super.change(UPDATE_TIME,super.getUpdateTime(),updateTime);
			super.setUpdateTime(updateTime);
			return this;
		}
		
		/**
		 * 设置 是否已删除
		 * @param deleted 是否已删除
		 * @return 当前对象
		*/
		public GoodsStock setDeleted(Integer deleted) {
			super.change(DELETED,super.getDeleted(),deleted);
			super.setDeleted(deleted);
			return this;
		}
		
		/**
		 * 设置 删除人ID
		 * @param deleteBy 删除人ID
		 * @return 当前对象
		*/
		public GoodsStock setDeleteBy(String deleteBy) {
			super.change(DELETE_BY,super.getDeleteBy(),deleteBy);
			super.setDeleteBy(deleteBy);
			return this;
		}
		
		/**
		 * 设置 删除时间
		 * @param deleteTime 删除时间
		 * @return 当前对象
		*/
		public GoodsStock setDeleteTime(Date deleteTime) {
			super.change(DELETE_TIME,super.getDeleteTime(),deleteTime);
			super.setDeleteTime(deleteTime);
			return this;
		}
		
		/**
		 * 设置 version
		 * @param version version
		 * @return 当前对象
		*/
		public GoodsStock setVersion(Integer version) {
			super.change(VERSION,super.getVersion(),version);
			super.setVersion(version);
			return this;
		}
		
		/**
		 * 设置 租户
		 * @param tenantId 租户
		 * @return 当前对象
		*/
		public GoodsStock setTenantId(String tenantId) {
			super.change(TENANT_ID,super.getTenantId(),tenantId);
			super.setTenantId(tenantId);
			return this;
		}
		
		/**
		 * 设置 资产分类
		 * @param category 资产分类
		 * @return 当前对象
		*/
		public GoodsStock setCategory(Catalog category) {
			super.change(CATEGORY,super.getCategory(),category);
			super.setCategory(category);
			return this;
		}
		
		/**
		 * 设置 生产厂商
		 * @param manufacturer 生产厂商
		 * @return 当前对象
		*/
		public GoodsStock setManufacturer(Manufacturer manufacturer) {
			super.change(MANUFACTURER,super.getManufacturer(),manufacturer);
			super.setManufacturer(manufacturer);
			return this;
		}
		
		/**
		 * 设置 品牌
		 * @param brand 品牌
		 * @return 当前对象
		*/
		public GoodsStock setBrand(Brand brand) {
			super.change(BRAND,super.getBrand(),brand);
			super.setBrand(brand);
			return this;
		}
		
		/**
		 * 设置 物品
		 * @param goods 物品
		 * @return 当前对象
		*/
		public GoodsStock setGoods(GoodsStock goods) {
			super.change(GOODS,super.getGoods(),goods);
			super.setGoods(goods);
			return this;
		}
		
		/**
		 * 设置 所属公司
		 * @param ownerCompany 所属公司
		 * @return 当前对象
		*/
		public GoodsStock setOwnerCompany(Organization ownerCompany) {
			super.change(OWNER_COMPANY,super.getOwnerCompany(),ownerCompany);
			super.setOwnerCompany(ownerCompany);
			return this;
		}
		
		/**
		 * 设置 使用公司/部门
		 * @param useOrganization 使用公司/部门
		 * @return 当前对象
		*/
		public GoodsStock setUseOrganization(Organization useOrganization) {
			super.change(USE_ORGANIZATION,super.getUseOrganization(),useOrganization);
			super.setUseOrganization(useOrganization);
			return this;
		}
		
		/**
		 * 设置 仓库
		 * @param warehouse 仓库
		 * @return 当前对象
		*/
		public GoodsStock setWarehouse(Warehouse warehouse) {
			super.change(WAREHOUSE,super.getWarehouse(),warehouse);
			super.setWarehouse(warehouse);
			return this;
		}
		
		/**
		 * 设置 来源
		 * @param source 来源
		 * @return 当前对象
		*/
		public GoodsStock setSource(DictItem source) {
			super.change(SOURCE,super.getSource(),source);
			super.setSource(source);
			return this;
		}
		
		/**
		 * 设置 管理人
		 * @param manager 管理人
		 * @return 当前对象
		*/
		public GoodsStock setManager(Employee manager) {
			super.change(MANAGER,super.getManager(),manager);
			super.setManager(manager);
			return this;
		}
		
		/**
		 * 设置 制单人
		 * @param originator 制单人
		 * @return 当前对象
		*/
		public GoodsStock setOriginator(Employee originator) {
			super.change(ORIGINATOR,super.getOriginator(),originator);
			super.setOriginator(originator);
			return this;
		}
		
		/**
		 * 设置 库存数据
		 * @param realGoods 库存数据
		 * @return 当前对象
		*/
		public GoodsStock setRealGoods(GoodsStock realGoods) {
			super.change(REAL_GOODS,super.getRealGoods(),realGoods);
			super.setRealGoods(realGoods);
			return this;
		}
		
		/**
		 * 设置 类型
		 * @param goodsModel 类型
		 * @return 当前对象
		*/
		public GoodsStock setGoodsModel(String goodsModel) {
			super.change(GOODS_MODEL,super.getGoodsModel(),goodsModel);
			super.setGoodsModel(goodsModel);
			return this;
		}
		
		/**
		 * 设置 名称
		 * @param goodsName 名称
		 * @return 当前对象
		*/
		public GoodsStock setGoodsName(String goodsName) {
			super.change(GOODS_NAME,super.getGoodsName(),goodsName);
			super.setGoodsName(goodsName);
			return this;
		}
		
		/**
		 * 设置 分类
		 * @param goodsCategoryName 分类
		 * @return 当前对象
		*/
		public GoodsStock setGoodsCategoryName(String goodsCategoryName) {
			super.change(GOODS_CATEGORY_NAME,super.getGoodsCategoryName(),goodsCategoryName);
			super.setGoodsCategoryName(goodsCategoryName);
			return this;
		}
		
		/**
		 * 设置 计算单位
		 * @param goodsUnit 计算单位
		 * @return 当前对象
		*/
		public GoodsStock setGoodsUnit(String goodsUnit) {
			super.change(GOODS_UNIT,super.getGoodsUnit(),goodsUnit);
			super.setGoodsUnit(goodsUnit);
			return this;
		}
		
		/**
		 * 设置 物品编码
		 * @param goodsCode 物品编码
		 * @return 当前对象
		*/
		public GoodsStock setGoodsCode(String goodsCode) {
			super.change(GOODS_CODE,super.getGoodsCode(),goodsCode);
			super.setGoodsCode(goodsCode);
			return this;
		}
		
		/**
		 * 设置 物品条码
		 * @param goodsBarCode 物品条码
		 * @return 当前对象
		*/
		public GoodsStock setGoodsBarCode(String goodsBarCode) {
			super.change(GOODS_BAR_CODE,super.getGoodsBarCode(),goodsBarCode);
			super.setGoodsBarCode(goodsBarCode);
			return this;
		}
		
		/**
		 * 设置 物品条码
		 * @param goodsStockMax 物品条码
		 * @return 当前对象
		*/
		public GoodsStock setGoodsStockMax(String goodsStockMax) {
			super.change(GOODS_STOCK_MAX,super.getGoodsStockMax(),goodsStockMax);
			super.setGoodsStockMax(goodsStockMax);
			return this;
		}
		
		/**
		 * 设置 物品条码
		 * @param goodsStockMin 物品条码
		 * @return 当前对象
		*/
		public GoodsStock setGoodsStockMin(String goodsStockMin) {
			super.change(GOODS_STOCK_MIN,super.getGoodsStockMin(),goodsStockMin);
			super.setGoodsStockMin(goodsStockMin);
			return this;
		}
		
		/**
		 * 设置 物品条码
		 * @param goodsStockSecurity 物品条码
		 * @return 当前对象
		*/
		public GoodsStock setGoodsStockSecurity(String goodsStockSecurity) {
			super.change(GOODS_STOCK_SECURITY,super.getGoodsStockSecurity(),goodsStockSecurity);
			super.setGoodsStockSecurity(goodsStockSecurity);
			return this;
		}
		
		/**
		 * 设置 库存备注
		 * @param goodsStockNotes 库存备注
		 * @return 当前对象
		*/
		public GoodsStock setGoodsStockNotes(String goodsStockNotes) {
			super.change(GOODS_STOCK_NOTES,super.getGoodsStockNotes(),goodsStockNotes);
			super.setGoodsStockNotes(goodsStockNotes);
			return this;
		}
		
		/**
		 * 设置 parentGoodsStock
		 * @param parentGoodsStock parentGoodsStock
		 * @return 当前对象
		*/
		public GoodsStock setParentGoodsStock(GoodsStock parentGoodsStock) {
			super.change(PARENT_GOODS_STOCK,super.getParentGoodsStock(),parentGoodsStock);
			super.setParentGoodsStock(parentGoodsStock);
			return this;
		}
		
		/**
		 * 设置 subGoodsStockList
		 * @param subGoodsStockList subGoodsStockList
		 * @return 当前对象
		*/
		public GoodsStock setSubGoodsStockList(List<GoodsStock> subGoodsStockList) {
			super.change(SUB_GOODS_STOCK_LIST,super.getSubGoodsStockList(),subGoodsStockList);
			super.setSubGoodsStockList(subGoodsStockList);
			return this;
		}

		/**
		 * 克隆当前对象
		*/
		@Transient
		public GoodsStock clone() {
			return duplicate(true);
		}

		/**
		 * 复制当前对象
		 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
		*/
		@Transient
		public GoodsStock duplicate(boolean all) {
			$$proxy$$ inst=new $$proxy$$();
			inst.setOwnerType(this.getOwnerType());
			inst.setSourceId(this.getSourceId());
			inst.setInterOperType(this.getInterOperType());
			inst.setNotes(this.getNotes());
			inst.setStockSecurity(this.getStockSecurity());
			inst.setOwnerTmpId(this.getOwnerTmpId());
			inst.setPid(this.getPid());
			inst.setOwnerId(this.getOwnerId());
			inst.setBusinessCode(this.getBusinessCode());
			inst.setModel(this.getModel());
			inst.setId(this.getId());
			inst.setUnitPrice(this.getUnitPrice());
			inst.setVersion(this.getVersion());
			inst.setBarCode(this.getBarCode());
			inst.setUnit(this.getUnit());
			inst.setOwnCompanyId(this.getOwnCompanyId());
			inst.setWarehouseId(this.getWarehouseId());
			inst.setDeleteTime(this.getDeleteTime());
			inst.setBrandId(this.getBrandId());
			inst.setAssetCategoryId(this.getAssetCategoryId());
			inst.setName(this.getName());
			inst.setDeleteBy(this.getDeleteBy());
			inst.setStatus(this.getStatus());
			inst.setCode(this.getCode());
			inst.setOwnerCode(this.getOwnerCode());
			inst.setGoodsId(this.getGoodsId());
			inst.setSelectedCode(this.getSelectedCode());
			inst.setStockMin(this.getStockMin());
			inst.setPictureId(this.getPictureId());
			inst.setUpdateBy(this.getUpdateBy());
			inst.setOriginatorId(this.getOriginatorId());
			inst.setSupplierName(this.getSupplierName());
			inst.setStockInNumber(this.getStockInNumber());
			inst.setAmount(this.getAmount());
			inst.setStockMax(this.getStockMax());
			inst.setUseOrgId(this.getUseOrgId());
			inst.setBatchCode(this.getBatchCode());
			inst.setManufacturerId(this.getManufacturerId());
			inst.setUpdateTime(this.getUpdateTime());
			inst.setManagerId(this.getManagerId());
			inst.setStorageDate(this.getStorageDate());
			inst.setStockCurNumber(this.getStockCurNumber());
			inst.setCreateBy(this.getCreateBy());
			inst.setRealStockId(this.getRealStockId());
			inst.setDeleted(this.getDeleted());
			inst.setGoodsStatus(this.getGoodsStatus());
			inst.setCreateTime(this.getCreateTime());
			inst.setTenantId(this.getTenantId());
			inst.setCategoryId(this.getCategoryId());
			if(all) {
				inst.setOwnerCompany(this.getOwnerCompany());
				inst.setGoodsStockSecurity(this.getGoodsStockSecurity());
				inst.setManager(this.getManager());
				inst.setGoods(this.getGoods());
				inst.setSource(this.getSource());
				inst.setOriginator(this.getOriginator());
				inst.setGoodsModel(this.getGoodsModel());
				inst.setGoodsStockMin(this.getGoodsStockMin());
				inst.setParentGoodsStock(this.getParentGoodsStock());
				inst.setWarehouse(this.getWarehouse());
				inst.setRealGoods(this.getRealGoods());
				inst.setManufacturer(this.getManufacturer());
				inst.setGoodsBarCode(this.getGoodsBarCode());
				inst.setUseOrganization(this.getUseOrganization());
				inst.setGoodsCategoryName(this.getGoodsCategoryName());
				inst.setSubGoodsStockList(this.getSubGoodsStockList());
				inst.setGoodsStockNotes(this.getGoodsStockNotes());
				inst.setGoodsUnit(this.getGoodsUnit());
				inst.setGoodsCode(this.getGoodsCode());
				inst.setCategory(this.getCategory());
				inst.setBrand(this.getBrand());
				inst.setGoodsName(this.getGoodsName());
				inst.setGoodsStockMax(this.getGoodsStockMax());
			}
			inst.clearModifies();
			return inst;
		}

	}
}