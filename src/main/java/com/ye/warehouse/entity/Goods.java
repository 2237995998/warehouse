package com.ye.warehouse.entity;

/**
 * @PackageName:com.ye.warehouse.entity
 * @ClassName:Goods
 * @Description:商品类
 * @author:何进业
 * @date:2021/3/23 20:15
 */
public class Goods {
    /**
     * 商品编号
     */
    private int goodsId;
    /**
     * 商品状态:
     * 1.申请入库
     * 2.入库质检成功，准备入库
     * 3.入库质检失败
     * 4.在库中
     * 5.申请出库
     * 6.出库质检成功，准备出库
     * 7.出库质检失败
     * 8.已出库
     */
    private int goodsStatus;
    /**
     * 商品种别码
     */
    private int goodsCategoryId;

    /**
     * 位置
     */
    private int goodsLocation;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public int getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(int goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public int getGoodsLocation() {
        return goodsLocation;
    }

    public void setGoodsLocation(int goodsLocation) {
        this.goodsLocation = goodsLocation;
    }



    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsStatus=" + goodsStatus +
                ", goodsCategoryId=" + goodsCategoryId +
                ", location=" + goodsLocation +
                '}';
    }
}
