package com.dt.platform.domain.common;

import com.github.foxnic.dao.entity.Entity;
import io.swagger.annotations.ApiModel;
import javax.persistence.Table;
import com.github.foxnic.sql.meta.DBTable;
import com.dt.platform.constants.db.SysTables.SYS_PAY_ORDER_EXT;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Transient;
import com.github.foxnic.api.swagger.EnumFor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.foxnic.commons.lang.DataParser;
import java.util.Map;
import com.github.foxnic.dao.entity.EntityContext;
import com.dt.platform.domain.common.meta.PayOrderExtMeta;
import com.github.foxnic.sql.data.ExprRcd;



/**
 * 支付订单
 * <p>支付订单 , 数据表 sys_pay_order_ext 的PO类型</p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-07-04 16:25:47
 * @sign 8774735780A0F4203A664B387D316217
 * 此文件由工具自动生成，请勿修改。若表结构或配置发生变动，请使用工具重新生成。
*/

@Table(name = "sys_pay_order_ext")
@ApiModel(description = "支付订单 ; 支付订单 , 数据表 sys_pay_order_ext 的PO类型")
public class PayOrderExt extends Entity {

	private static final long serialVersionUID = 1L;

	public static final DBTable TABLE =SYS_PAY_ORDER_EXT.$TABLE;
	
	/**
	 * 主键：主键
	*/
	@Id
	@ApiModelProperty(required = true,value="主键" , notes = "主键")
	private String id;
	
	/**
	 * 支付订单编号：支付订单编号
	*/
	@ApiModelProperty(required = true,value="支付订单编号" , notes = "支付订单编号")
	private String orderId;
	
	/**
	 * 支付渠道的额外参数：支付渠道的额外参数
	*/
	@ApiModelProperty(required = false,value="支付渠道的额外参数" , notes = "支付渠道的额外参数")
	private String channelExtras;
	
	/**
	 * 支付渠道异步通知的内容：支付渠道异步通知的内容
	*/
	@ApiModelProperty(required = false,value="支付渠道异步通知的内容" , notes = "支付渠道异步通知的内容")
	private String channelNotifyData;
	
	/**
	 * 创建人ID：创建人ID
	*/
	@ApiModelProperty(required = false,value="创建人ID" , notes = "创建人ID")
	private String createBy;
	
	/**
	 * 创建时间：创建时间
	*/
	@ApiModelProperty(required = false,value="创建时间" , notes = "创建时间")
	private Date createTime;
	
	/**
	 * 修改人ID：修改人ID
	*/
	@ApiModelProperty(required = false,value="修改人ID" , notes = "修改人ID")
	private String updateBy;
	
	/**
	 * 修改时间：修改时间
	*/
	@ApiModelProperty(required = false,value="修改时间" , notes = "修改时间")
	private Date updateTime;
	
	/**
	 * 是否已删除：是否已删除
	*/
	@ApiModelProperty(required = true,value="是否已删除" , notes = "是否已删除")
	private Integer deleted;
	@Transient
	@EnumFor("deleted")
	private Boolean deletedBool;
	
	/**
	 * 删除人ID：删除人ID
	*/
	@ApiModelProperty(required = false,value="删除人ID" , notes = "删除人ID")
	private String deleteBy;
	
	/**
	 * 删除时间：删除时间
	*/
	@ApiModelProperty(required = false,value="删除时间" , notes = "删除时间")
	private Date deleteTime;
	
	/**
	 * version：version
	*/
	@ApiModelProperty(required = true,value="version" , notes = "version")
	private Integer version;
	
	/**
	 * 租户：租户
	*/
	@ApiModelProperty(required = false,value="租户" , notes = "租户")
	private String tenantId;
	
	/**
	 * payOrder：payOrder
	*/
	@ApiModelProperty(required = false,value="payOrder" , notes = "payOrder")
	private PayOrder payOrder;
	
	/**
	 * 获得 主键<br>
	 * 主键
	 * @return 主键
	*/
	public String getId() {
		return id;
	}
	
	/**
	 * 设置 主键
	 * @param id 主键
	 * @return 当前对象
	*/
	public PayOrderExt setId(String id) {
		this.id=id;
		return this;
	}
	
	/**
	 * 获得 支付订单编号<br>
	 * 支付订单编号
	 * @return 支付订单编号
	*/
	public String getOrderId() {
		return orderId;
	}
	
	/**
	 * 设置 支付订单编号
	 * @param orderId 支付订单编号
	 * @return 当前对象
	*/
	public PayOrderExt setOrderId(String orderId) {
		this.orderId=orderId;
		return this;
	}
	
	/**
	 * 获得 支付渠道的额外参数<br>
	 * 支付渠道的额外参数
	 * @return 支付渠道的额外参数
	*/
	public String getChannelExtras() {
		return channelExtras;
	}
	
	/**
	 * 设置 支付渠道的额外参数
	 * @param channelExtras 支付渠道的额外参数
	 * @return 当前对象
	*/
	public PayOrderExt setChannelExtras(String channelExtras) {
		this.channelExtras=channelExtras;
		return this;
	}
	
	/**
	 * 获得 支付渠道异步通知的内容<br>
	 * 支付渠道异步通知的内容
	 * @return 支付渠道异步通知的内容
	*/
	public String getChannelNotifyData() {
		return channelNotifyData;
	}
	
	/**
	 * 设置 支付渠道异步通知的内容
	 * @param channelNotifyData 支付渠道异步通知的内容
	 * @return 当前对象
	*/
	public PayOrderExt setChannelNotifyData(String channelNotifyData) {
		this.channelNotifyData=channelNotifyData;
		return this;
	}
	
	/**
	 * 获得 创建人ID<br>
	 * 创建人ID
	 * @return 创建人ID
	*/
	public String getCreateBy() {
		return createBy;
	}
	
	/**
	 * 设置 创建人ID
	 * @param createBy 创建人ID
	 * @return 当前对象
	*/
	public PayOrderExt setCreateBy(String createBy) {
		this.createBy=createBy;
		return this;
	}
	
	/**
	 * 获得 创建时间<br>
	 * 创建时间
	 * @return 创建时间
	*/
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置 创建时间
	 * @param createTime 创建时间
	 * @return 当前对象
	*/
	public PayOrderExt setCreateTime(Date createTime) {
		this.createTime=createTime;
		return this;
	}
	
	/**
	 * 获得 修改人ID<br>
	 * 修改人ID
	 * @return 修改人ID
	*/
	public String getUpdateBy() {
		return updateBy;
	}
	
	/**
	 * 设置 修改人ID
	 * @param updateBy 修改人ID
	 * @return 当前对象
	*/
	public PayOrderExt setUpdateBy(String updateBy) {
		this.updateBy=updateBy;
		return this;
	}
	
	/**
	 * 获得 修改时间<br>
	 * 修改时间
	 * @return 修改时间
	*/
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/**
	 * 设置 修改时间
	 * @param updateTime 修改时间
	 * @return 当前对象
	*/
	public PayOrderExt setUpdateTime(Date updateTime) {
		this.updateTime=updateTime;
		return this;
	}
	
	/**
	 * 获得 是否已删除<br>
	 * 是否已删除
	 * @return 是否已删除
	*/
	public Integer getDeleted() {
		return deleted;
	}
	
	/**
	 * 获得 是否已删除 的投影属性<br>
	 * 等价于 getDeleted 方法，获得对应的枚举类型
	 * @return 是否已删除
	*/
	@Transient
	public Boolean isDeleted() {
		if(this.deletedBool==null) {
			this.deletedBool=DataParser.parseBoolean(deleted);
		}
		return this.deletedBool ;
	}
	
	/**
	 * 设置 是否已删除
	 * @param deleted 是否已删除
	 * @return 当前对象
	*/
	@JsonProperty("deleted")
	public PayOrderExt setDeleted(Integer deleted) {
		this.deleted=deleted;
		this.deletedBool=DataParser.parseBoolean(deleted);
		return this;
	}
	
	/**
	 * 设置 是否已删除的投影属性，等同于设置 是否已删除
	 * @param deletedBool 是否已删除
	 * @return 当前对象
	*/
	@Transient
	public PayOrderExt setDeleted(Boolean deletedBool) {
		if(deletedBool==null) {
			this.deleted=null;
		} else {
			this.deleted=deletedBool?1:0;
		}
		this.deletedBool=deletedBool;
		return this;
	}
	
	/**
	 * 获得 删除人ID<br>
	 * 删除人ID
	 * @return 删除人ID
	*/
	public String getDeleteBy() {
		return deleteBy;
	}
	
	/**
	 * 设置 删除人ID
	 * @param deleteBy 删除人ID
	 * @return 当前对象
	*/
	public PayOrderExt setDeleteBy(String deleteBy) {
		this.deleteBy=deleteBy;
		return this;
	}
	
	/**
	 * 获得 删除时间<br>
	 * 删除时间
	 * @return 删除时间
	*/
	public Date getDeleteTime() {
		return deleteTime;
	}
	
	/**
	 * 设置 删除时间
	 * @param deleteTime 删除时间
	 * @return 当前对象
	*/
	public PayOrderExt setDeleteTime(Date deleteTime) {
		this.deleteTime=deleteTime;
		return this;
	}
	
	/**
	 * 获得 version<br>
	 * version
	 * @return version
	*/
	public Integer getVersion() {
		return version;
	}
	
	/**
	 * 设置 version
	 * @param version version
	 * @return 当前对象
	*/
	public PayOrderExt setVersion(Integer version) {
		this.version=version;
		return this;
	}
	
	/**
	 * 获得 租户<br>
	 * 租户
	 * @return 租户
	*/
	public String getTenantId() {
		return tenantId;
	}
	
	/**
	 * 设置 租户
	 * @param tenantId 租户
	 * @return 当前对象
	*/
	public PayOrderExt setTenantId(String tenantId) {
		this.tenantId=tenantId;
		return this;
	}
	
	/**
	 * 获得 payOrder<br>
	 * payOrder
	 * @return payOrder
	*/
	public PayOrder getPayOrder() {
		return payOrder;
	}
	
	/**
	 * 设置 payOrder
	 * @param payOrder payOrder
	 * @return 当前对象
	*/
	public PayOrderExt setPayOrder(PayOrder payOrder) {
		this.payOrder=payOrder;
		return this;
	}

	/**
	 * 将自己转换成指定类型的PO
	 * @param poType  PO类型
	 * @return PayOrderExt , 转换好的 PayOrderExt 对象
	*/
	@Transient
	public <T extends Entity> T toPO(Class<T> poType) {
		return EntityContext.create(poType, this);
	}

	/**
	 * 将自己转换成任意指定类型
	 * @param pojoType  Pojo类型
	 * @return PayOrderExt , 转换好的 PoJo 对象
	*/
	@Transient
	public <T> T toPojo(Class<T> pojoType) {
		if(Entity.class.isAssignableFrom(pojoType)) {
			return (T)this.toPO((Class<Entity>)pojoType);
		}
		try {
			T pojo=pojoType.newInstance();
			EntityContext.copyProperties(pojo, this);
			return pojo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 克隆当前对象
	*/
	@Transient
	public PayOrderExt clone() {
		return duplicate(true);
	}

	/**
	 * 复制当前对象
	 * @param all 是否复制全部属性，当 false 时，仅复制来自数据表的属性
	*/
	@Transient
	public PayOrderExt duplicate(boolean all) {
		com.dt.platform.domain.common.meta.PayOrderExtMeta.$$proxy$$ inst = new com.dt.platform.domain.common.meta.PayOrderExtMeta.$$proxy$$();
		inst.setOrderId(this.getOrderId());
		inst.setChannelNotifyData(this.getChannelNotifyData());
		inst.setUpdateTime(this.getUpdateTime());
		inst.setVersion(this.getVersion());
		inst.setCreateBy(this.getCreateBy());
		inst.setDeleted(this.getDeleted());
		inst.setCreateTime(this.getCreateTime());
		inst.setUpdateBy(this.getUpdateBy());
		inst.setDeleteTime(this.getDeleteTime());
		inst.setTenantId(this.getTenantId());
		inst.setDeleteBy(this.getDeleteBy());
		inst.setChannelExtras(this.getChannelExtras());
		inst.setId(this.getId());
		if(all) {
			inst.setPayOrder(this.getPayOrder());
		}
		inst.clearModifies();
		return inst;
	}

	/**
	 * 克隆当前对象
	*/
	@Transient
	public PayOrderExt clone(boolean deep) {
		return EntityContext.clone(PayOrderExt.class,this,deep);
	}

	/**
	 * 将 Map 转换成 PayOrderExt
	 * @param payOrderExtMap 包含实体信息的 Map 对象
	 * @return PayOrderExt , 转换好的的 PayOrderExt 对象
	*/
	@Transient
	public static PayOrderExt createFrom(Map<String,Object> payOrderExtMap) {
		if(payOrderExtMap==null) return null;
		PayOrderExt po = create();
		EntityContext.copyProperties(po,payOrderExtMap);
		po.clearModifies();
		return po;
	}

	/**
	 * 将 Pojo 转换成 PayOrderExt
	 * @param pojo 包含实体信息的 Pojo 对象
	 * @return PayOrderExt , 转换好的的 PayOrderExt 对象
	*/
	@Transient
	public static PayOrderExt createFrom(Object pojo) {
		if(pojo==null) return null;
		PayOrderExt po = create();
		EntityContext.copyProperties(po,pojo);
		po.clearModifies();
		return po;
	}

	/**
	 * 创建一个 PayOrderExt，等同于 new
	 * @return PayOrderExt 对象
	*/
	@Transient
	public static PayOrderExt create() {
		return new com.dt.platform.domain.common.meta.PayOrderExtMeta.$$proxy$$();
	}

	/**
	 * 从 Map 读取
	 * @param map 记录数据
	 * @param cast 是否用 DataParser 进行类型转换
	 * @return  是否读取成功
	*/
	public boolean read(Map<String, Object> map,boolean cast) {
		if(map==null) return false;
		if(cast) {
			this.setOrderId(DataParser.parse(String.class, map.get(PayOrderExtMeta.ORDER_ID)));
			this.setChannelNotifyData(DataParser.parse(String.class, map.get(PayOrderExtMeta.CHANNEL_NOTIFY_DATA)));
			this.setUpdateTime(DataParser.parse(Date.class, map.get(PayOrderExtMeta.UPDATE_TIME)));
			this.setVersion(DataParser.parse(Integer.class, map.get(PayOrderExtMeta.VERSION)));
			this.setCreateBy(DataParser.parse(String.class, map.get(PayOrderExtMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, map.get(PayOrderExtMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, map.get(PayOrderExtMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, map.get(PayOrderExtMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, map.get(PayOrderExtMeta.DELETE_TIME)));
			this.setTenantId(DataParser.parse(String.class, map.get(PayOrderExtMeta.TENANT_ID)));
			this.setDeleteBy(DataParser.parse(String.class, map.get(PayOrderExtMeta.DELETE_BY)));
			this.setChannelExtras(DataParser.parse(String.class, map.get(PayOrderExtMeta.CHANNEL_EXTRAS)));
			this.setId(DataParser.parse(String.class, map.get(PayOrderExtMeta.ID)));
			// others
			this.setPayOrder(DataParser.parse(PayOrder.class, map.get(PayOrderExtMeta.PAY_ORDER)));
			return true;
		} else {
			try {
				this.setOrderId( (String)map.get(PayOrderExtMeta.ORDER_ID));
				this.setChannelNotifyData( (String)map.get(PayOrderExtMeta.CHANNEL_NOTIFY_DATA));
				this.setUpdateTime( (Date)map.get(PayOrderExtMeta.UPDATE_TIME));
				this.setVersion( (Integer)map.get(PayOrderExtMeta.VERSION));
				this.setCreateBy( (String)map.get(PayOrderExtMeta.CREATE_BY));
				this.setDeleted( (Integer)map.get(PayOrderExtMeta.DELETED));
				this.setCreateTime( (Date)map.get(PayOrderExtMeta.CREATE_TIME));
				this.setUpdateBy( (String)map.get(PayOrderExtMeta.UPDATE_BY));
				this.setDeleteTime( (Date)map.get(PayOrderExtMeta.DELETE_TIME));
				this.setTenantId( (String)map.get(PayOrderExtMeta.TENANT_ID));
				this.setDeleteBy( (String)map.get(PayOrderExtMeta.DELETE_BY));
				this.setChannelExtras( (String)map.get(PayOrderExtMeta.CHANNEL_EXTRAS));
				this.setId( (String)map.get(PayOrderExtMeta.ID));
				// others
				this.setPayOrder( (PayOrder)map.get(PayOrderExtMeta.PAY_ORDER));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}

	/**
	 * 从 Map 读取
	 * @param r 记录数据
	 * @param cast 是否用 DataParser 进行类型转换
	 * @return  是否读取成功
	*/
	public boolean read(ExprRcd r,boolean cast) {
		if(r==null) return false;
		if(cast) {
			this.setOrderId(DataParser.parse(String.class, r.getValue(PayOrderExtMeta.ORDER_ID)));
			this.setChannelNotifyData(DataParser.parse(String.class, r.getValue(PayOrderExtMeta.CHANNEL_NOTIFY_DATA)));
			this.setUpdateTime(DataParser.parse(Date.class, r.getValue(PayOrderExtMeta.UPDATE_TIME)));
			this.setVersion(DataParser.parse(Integer.class, r.getValue(PayOrderExtMeta.VERSION)));
			this.setCreateBy(DataParser.parse(String.class, r.getValue(PayOrderExtMeta.CREATE_BY)));
			this.setDeleted(DataParser.parse(Integer.class, r.getValue(PayOrderExtMeta.DELETED)));
			this.setCreateTime(DataParser.parse(Date.class, r.getValue(PayOrderExtMeta.CREATE_TIME)));
			this.setUpdateBy(DataParser.parse(String.class, r.getValue(PayOrderExtMeta.UPDATE_BY)));
			this.setDeleteTime(DataParser.parse(Date.class, r.getValue(PayOrderExtMeta.DELETE_TIME)));
			this.setTenantId(DataParser.parse(String.class, r.getValue(PayOrderExtMeta.TENANT_ID)));
			this.setDeleteBy(DataParser.parse(String.class, r.getValue(PayOrderExtMeta.DELETE_BY)));
			this.setChannelExtras(DataParser.parse(String.class, r.getValue(PayOrderExtMeta.CHANNEL_EXTRAS)));
			this.setId(DataParser.parse(String.class, r.getValue(PayOrderExtMeta.ID)));
			return true;
		} else {
			try {
				this.setOrderId( (String)r.getValue(PayOrderExtMeta.ORDER_ID));
				this.setChannelNotifyData( (String)r.getValue(PayOrderExtMeta.CHANNEL_NOTIFY_DATA));
				this.setUpdateTime( (Date)r.getValue(PayOrderExtMeta.UPDATE_TIME));
				this.setVersion( (Integer)r.getValue(PayOrderExtMeta.VERSION));
				this.setCreateBy( (String)r.getValue(PayOrderExtMeta.CREATE_BY));
				this.setDeleted( (Integer)r.getValue(PayOrderExtMeta.DELETED));
				this.setCreateTime( (Date)r.getValue(PayOrderExtMeta.CREATE_TIME));
				this.setUpdateBy( (String)r.getValue(PayOrderExtMeta.UPDATE_BY));
				this.setDeleteTime( (Date)r.getValue(PayOrderExtMeta.DELETE_TIME));
				this.setTenantId( (String)r.getValue(PayOrderExtMeta.TENANT_ID));
				this.setDeleteBy( (String)r.getValue(PayOrderExtMeta.DELETE_BY));
				this.setChannelExtras( (String)r.getValue(PayOrderExtMeta.CHANNEL_EXTRAS));
				this.setId( (String)r.getValue(PayOrderExtMeta.ID));
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}