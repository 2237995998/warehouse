package com.ye.warehouse.entity;

import java.util.Date;

/**
 * @PackageName:com.ye.warehouse.entity
 * @ClassName:GoodsHouseRecord
 * @Description:商品出库/入库记录表
 * @author:何进业
 * @date:2021/3/23 20:24
 */
public class GoodsHouseRecord {
    /**
     * 商品编号
     */
    private int goodsId;
    /**
     * 入库时间
     */
    private Date inTime;
    /**
     * 出库时间
     */
    private Date outTime;
    /**
     * 入库时仓库管理员账号
     */
    private int warehouseManagerInId;
    /**
     * 出库时仓库管理员账号
     */
    private int warehouseManagerOutId;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public int getWarehouseManagerInId() {
        return warehouseManagerInId;
    }

    public void setWarehouseManagerInId(int warehouseManagerInId) {
        this.warehouseManagerInId = warehouseManagerInId;
    }

    public int getWarehouseManagerOutId() {
        return warehouseManagerOutId;
    }

    public void setWarehouseManagerOutId(int warehouseManagerOutId) {
        this.warehouseManagerOutId = warehouseManagerOutId;
    }

    @Override
    public String toString() {
        return "GoodsHouseRecord{" +
                "goodsId=" + goodsId +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                ", warehouseManagerInId=" + warehouseManagerInId +
                ", warehouseManagerOutId=" + warehouseManagerOutId +
                '}';
    }
}
