package com.ye.warehouse.entity;

/**
 * @PackageName:com.ye.warehouse.entity
 * @ClassName:GoodsMan
 * @Description:商品运输员类
 * @author:何进业
 * @date:2021/3/23 20:26
 */
public class GoodsMan {
    /**
     * 商品运输员账号
     */
    private int goodsManId;
    /**
     * 商品运输员密码
     */
    private String goodsManPwd;
    /**
     * 商品运输员昵称
     */
    private String goodsManName;
    /**
     * 商品运输员头像地址
     */
    private String goodsManHeader;
    /**
     * 商品运输员电话
     */
    private String goodsManTel;
    /**
     * 商品运输员状态
     */
    private int goodsManStatus;

    public int getGoodsManId() {
        return goodsManId;
    }

    public void setGoodsManId(int goodsManId) {
        this.goodsManId = goodsManId;
    }

    public String getGoodsManPwd() {
        return goodsManPwd;
    }

    public void setGoodsManPwd(String goodsManPwd) {
        this.goodsManPwd = goodsManPwd;
    }

    public String getGoodsManName() {
        return goodsManName;
    }

    public void setGoodsManName(String goodsManName) {
        this.goodsManName = goodsManName;
    }

    public String getGoodsManHeader() {
        return goodsManHeader;
    }

    public void setGoodsManHeader(String goodsManHeader) {
        this.goodsManHeader = goodsManHeader;
    }

    public int getGoodsManStatus() {
        return goodsManStatus;
    }

    public void setGoodsManStatus(int goodsManStatus) {
        this.goodsManStatus = goodsManStatus;
    }


    public String getGoodsManTel() {
        return goodsManTel;
    }

    public void setGoodsManTel(String goodsManTel) {
        this.goodsManTel = goodsManTel;
    }

    @Override
    public String toString() {
        return "GoodsMan{" +
                "goodsManId=" + goodsManId +
                ", goodsManPwd='" + goodsManPwd + '\'' +
                ", goodsManName='" + goodsManName + '\'' +
                ", goodsManHeader='" + goodsManHeader + '\'' +
                ", goodsManTel='" + goodsManTel + '\'' +
                ", goodsManStatus=" + goodsManStatus +
                '}';
    }
}
