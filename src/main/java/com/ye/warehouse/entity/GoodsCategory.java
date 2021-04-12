package com.ye.warehouse.entity;

/**
 * @PackageName:com.ye.warehouse.entity
 * @ClassName:GoodsCategory
 * @Description:商品种别类
 * @author:何进业
 * @date:2021/3/23 20:21
 */
public class GoodsCategory {
    /**
     * 商品种别码
     */
    private int goodsCategoryId;
    /**
     * 商品种别名称
     */
    private String goodsCategoryName;
    /**
     * 商品种别状态
     */
    private int goodsCategoryStatus;
    /**
     * 商品种别图片地址
     */
    private String goodsCategoryPicture;

    public int getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(int goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public String getGoodsCategoryName() {
        return goodsCategoryName;
    }

    public void setGoodsCategoryName(String goodsCategoryName) {
        this.goodsCategoryName = goodsCategoryName;
    }

    public String getGoodsCategoryPicture() {
        return goodsCategoryPicture;
    }

    public void setGoodsCategoryPicture(String goodsCategoryPicture) {
        this.goodsCategoryPicture = goodsCategoryPicture;
    }


    public int getGoodsCategoryStatus() {
        return goodsCategoryStatus;
    }

    public void setGoodsCategoryStatus(int goodsCategoryStatus) {
        this.goodsCategoryStatus = goodsCategoryStatus;
    }

    @Override
    public String toString() {
        return "GoodsCategory{" +
                "goodsCategoryId=" + goodsCategoryId +
                ", goodsCategoryName='" + goodsCategoryName + '\'' +
                ", goodsCategoryStatus=" + goodsCategoryStatus +
                ", goodsCategoryPicture='" + goodsCategoryPicture + '\'' +
                '}';
    }
}
