package com.ye.warehouse.entity;

import java.util.Date;

/**
 * @PackageName:com.ye.warehouse.entity
 * @ClassName:GoodsQuailtyRecord
 * @Description:商品质检记录类
 * @author:何进业
 * @date:2021/3/23 20:31
 */
public class GoodsQualityRecord {
    /**
     * 商品编号
     */
    private int goodsId;
    /**
     * 商品入库质检结果
     */
    private int goodsQualityInResult;
    /**
     * 商品出库质检结果
     */
    private int goodsQualityOutResult;
    /**
     * 商品入库质检时间
     */
    private Date goodsQualityInTime;
    /**
     * 商品出库质检时间
     */
    private Date goodsQualityOutTime;
    /**
     * 入库质检员账号
     */
    private int qualityManInId;
    /**
     * 出库质检员账号
     */
    private int qualityManOutId;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsQualityInResult() {
        return goodsQualityInResult;
    }

    public void setGoodsQualityInResult(int goodsQualityInResult) {
        this.goodsQualityInResult = goodsQualityInResult;
    }

    public int getGoodsQualityOutResult() {
        return goodsQualityOutResult;
    }

    public void setGoodsQualityOutResult(int goodsQualityOutResult) {
        this.goodsQualityOutResult = goodsQualityOutResult;
    }

    public Date getGoodsQualityInTime() {
        return goodsQualityInTime;
    }

    public void setGoodsQualityInTime(Date goodsQualityInTime) {
        this.goodsQualityInTime = goodsQualityInTime;
    }

    public Date getGoodsQualityOutTime() {
        return goodsQualityOutTime;
    }

    public void setGoodsQualityOutTime(Date goodsQualityOutTime) {
        this.goodsQualityOutTime = goodsQualityOutTime;
    }

    public int getQualityManInId() {
        return qualityManInId;
    }

    public void setQualityManInId(int qualityManInId) {
        this.qualityManInId = qualityManInId;
    }

    public int getQualityManOutId() {
        return qualityManOutId;
    }

    public void setQualityManOutId(int qualityManOutId) {
        this.qualityManOutId = qualityManOutId;
    }

    @Override
    public String toString() {
        return "GoodsQualityRecord{" +
                "goodsId=" + goodsId +
                ", goodsQualityInResult=" + goodsQualityInResult +
                ", goodsQualityOutResult=" + goodsQualityOutResult +
                ", goodsQualityInTime=" + goodsQualityInTime +
                ", goodsQualityOutTime=" + goodsQualityOutTime +
                ", qualityManInId=" + qualityManInId +
                ", qualityManOutId=" + qualityManOutId +
                '}';
    }
}
