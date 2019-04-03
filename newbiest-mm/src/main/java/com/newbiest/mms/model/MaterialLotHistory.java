package com.newbiest.mms.model;

import com.newbiest.base.model.NBHis;
import com.newbiest.base.utils.StringUtils;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by guoxunbo on 2019/2/26.
 */
@Entity
@Table(name="MMS_MATERIAL_LOT_HIS")
@Data
public class MaterialLotHistory extends NBHis {

    public static final String TRANS_TYPE_RECEIVE = "Receive";
    public static final String TRANS_TYPE_STOCK_IN = "StockIn";
    public static final String TRANS_TYPE_STOCK_OUT = "StockOut";
    public static final String TRANS_TYPE_PICK = "Pick";
    public static final String TRANS_TYPE_TRANSFER = "Transfer";
    public static final String TRANS_TYPE_CHECK = "Check";
    public static final String TRANS_TYPE_CONSUME = "Consume";

    public static final String TRANS_TYPE_PACKAGE = "Package";

    /**
     * 因为包装产生的批次
     */
    public static final String TRANS_TYPE_PACKAGE_CREATE = "PackageCreate";

    /**
     * 物料批次号
     */
    @Column(name="MATERIAL_LOT_ID")
    private String materialLotId;

    /**
     * 等级
     */
    @Column(name="GRADE")
    private String grade;

    /**
     * 状态模型主键
     * 从物料上携带
     */
    @Column(name="STATUS_MODEL_RRN")
    private Long statusModelRrn;

    /**
     * 状态大类
     */
    @Column(name="STATUS_CATEGORY")
    private String statusCategory;

    /**
     * 状态
     */
    @Column(name="STATUS")
    private String status;

    /**
     * 前置状态大类
     */
    @Column(name="PRE_STATUS_CATEGORY")
    private String preStatusCategory;

    /**
     * 前置状态
     */
    @Column(name="PRE_STATUS")
    private String preStatus;

    /**
     * Hold状态
     */
    @Column(name="HOLD_STATE")
    private String holdState;

    /**
     * 批次接收数量
     * 只做记录使用不做任何运算
     */
    @Column(name="RECEIVE_QTY")
    private BigDecimal receiveQty = BigDecimal.ZERO;

    /**
     * 主数量
     */
    @Column(name="CURRENT_QTY")
    private BigDecimal currentQty = BigDecimal.ZERO;

    /**
     * 预留数量
     */
    @Column(name="RESERVED_QTY")
    private BigDecimal reservedQty = BigDecimal.ZERO;

    /**
     * 如果是子批，对应的母批的批号
     */
    @Column(name="PARENT_MATERIAL_LOT_ID")
    private String parentMaterialLotId;

    /**
     * 如果是子批，对应的母批的主键
     */
    @Column(name="PARENT_MATERIAL_LOT_RRN")
    private String parentMaterialLotRrn;

    /**
     * 是否是子批的标记
     */
    @Column(name="SUB_MATERIAL_LOT_FLAG")
    private String subMaterialLotFlag;

    /**
     * 是否被包装
     */
    @Column(name="PACKED_FLAG")
    private String packedFlag;

    /**
     * 工单号。
     * 有些情况下工单会直接指定消耗的物料批次
     */
    @Column(name="WORK_ORDER_ID")
    private String workOrderId;

    /**
     * 物料主键
     */
    @Column(name="MATERIAL_RRN")
    private Long materialRrn;

    /**
     * 物料名称
     */
    @Column(name="MATERIAL_NAME")
    private String materialName;

    /**
     * 物料版本
     */
    @Column(name="MATERIAL_VERSION")
    private Long materialVersion;

    /**
     * 物料描述
     */
    @Column(name="MATERIAL_DESC")
    private String materialDesc;

    /**
     * 物料类别
     */
    @Column(name="MATERIAL_CATEGORY")
    private String materialCategory;

    /**
     * 物料类型
     */
    @Column(name="MATERIAL_TYPE")
    private String materialType;

    /**
     * 库存单位
     */
    @Column(name="STORE_UOM")
    private String storeUom;

    /**
     * 接收日期
     */
    @Column(name="RECEIVE_DATE")
    private Date receiveDate;

    /**
     * 有效时长
     */
    @Column(name="EFFECTIVE_LIFE")
    private Long effectiveLife;

    /**
     * 警告时长
     * 当达到此时长的时候触发警告
     */
    @Column(name="WARNING_LIFE")
    private Long warningLife;

    /**
     * 有效时长单位
     */
    @Column(name="EFFECTIVE_UNIT")
    private String effectiveUnit;

    /**
     * 操作数量
     */
    @Column(name="TRANS_QTY")
    private BigDecimal transQty;

    /**
     * 操作仓库
     */
    @Column(name="TRANS_WAREHOUSE_ID")
    private String transWarehouseId;

    /**
     * 操作库位类型
     */
    @Column(name="TRANS_STORAGE_TYPE")
    private String transStorageType;

    /**
     * 操作库位号
     */
    @Column(name="TRANS_STORAGE_ID")
    private String transStorageId;

    /**
     * 目标仓库
     */
    @Column(name="TARGET_WAREHOUSE_ID")
    private String targetWarehouseId;

    /**
     * 目标库位类型
     */
    @Column(name="TARGET_STORAGE_TYPE")
    private String targetStorageType;

    /**
     * 目标库位号
     */
    @Column(name="TARGET_STORAGE_ID")
    private String targetStorageId;


    @Column(name="RESERVED1")
    private String reserved1;

    @Column(name="RESERVED2")
    private String reserved2;

    @Column(name="RESERVED3")
    private String reserved3;

    @Column(name="RESERVED4")
    private String reserved4;

    @Column(name="RESERVED5")
    private String reserved5;

    @Column(name="RESERVED6")
    private String reserved6;

    @Column(name="RESERVED7")
    private String reserved7;

    @Column(name="RESERVED8")
    private String reserved8;

    @Column(name="RESERVED9")
    private String reserved9;

    @Column(name="RESERVED10")
    private String reserved10;

    public void setSubMaterialLotFlag(Boolean subMaterialLotFlag) {
        this.subMaterialLotFlag = subMaterialLotFlag ? StringUtils.YES : StringUtils.NO;
    }

    public boolean getSubMaterialLotFlag() {
        return StringUtils.YES.equalsIgnoreCase(this.subMaterialLotFlag);
    }

    public void setPackedFlag(Boolean packedFlag) {
        this.packedFlag = packedFlag ? StringUtils.YES : StringUtils.NO;
    }

    public boolean getPackedFlag() {
        return StringUtils.YES.equalsIgnoreCase(this.packedFlag);
    }
}