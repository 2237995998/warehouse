package com.ye.warehouse.entity;

import java.util.Date;

/**
 * @PackageName:com.ye.warehouse.entity
 * @ClassName:goodsApplyRecord
 * @Description:申请存放/取出商品记录表
 * @author:何进业
 * @date:2021/3/23 20:17
 */
public class GoodsApplyRecord {
    /**
     * 商品编号
     */
    private int goodsId;
    /**
     * 申请存放时间
     */
    private Date inApplyTime;
    /**
     * 申请取出时间
     */
    private Date outApplyTime;
    /**
     * 申请放入时的商品运输员账号
     */
    private int goodsManInId;
    /**
     * 申请取出时的商品运输员账号
     */
    private int goodsManOutId;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public Date getInApplyTime() {
        return inApplyTime;
    }

    public void setInApplyTime(Date inApplyTime) {
        this.inApplyTime = inApplyTime;
    }

    public Date getOutApplyTime() {
        return outApplyTime;
    }

    public void setOutApplyTime(Date outApplyTime) {
        this.outApplyTime = outApplyTime;
    }

    public int getGoodsManInId() {
        return goodsManInId;
    }

    public void setGoodsManInId(int goodsManInId) {
        this.goodsManInId = goodsManInId;
    }

    public int getGoodsManOutId() {
        return goodsManOutId;
    }

    public void setGoodsManOutId(int goodsManOutId) {
        this.goodsManOutId = goodsManOutId;
    }

    @Override
    public String toString() {
        return "GoodsApplyRecord{" +
                "goodsId=" + goodsId +
                ", inApplyTime=" + inApplyTime +
                ", outApplyTime=" + outApplyTime +
                ", goodsManInId=" + goodsManInId +
                ", goodsManOutId=" + goodsManOutId +
                '}';
    }
}
