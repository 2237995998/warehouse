package com.ye.warehouse.entity;

import java.util.Date;

/**
 * @PackageName:com.ye.warehouse.entity
 * @ClassName:EquipmentRecord
 * @Description:购买/销毁器材记录
 * @author:何进业
 * @date:2021/3/23 20:11
 */
public class EquipmentRecord {
    /**
     * 器材编号
     */
    private int equipmentId;
    /**
     * 购买时间
     */
    private Date buyTime;
    /**
     * 销毁时间
     */
    private Date destroyTime;
    /**
     * 购买时仓库管理员账号
     */
    private int warehouseManagerBuyId;
    /**
     * 销毁时仓库管理员账号
     */
    private int warehouseManagerDestroyId;

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Date getDestroyTime() {
        return destroyTime;
    }

    public void setDestroyTime(Date destroyTime) {
        this.destroyTime = destroyTime;
    }


    public int getWarehouseManagerBuyId() {
        return warehouseManagerBuyId;
    }

    public void setWarehouseManagerBuyId(int warehouseManagerBuyId) {
        this.warehouseManagerBuyId = warehouseManagerBuyId;
    }

    public int getWarehouseManagerDestroyId() {
        return warehouseManagerDestroyId;
    }

    public void setWarehouseManagerDestroyId(int warehouseManagerDestroyId) {
        this.warehouseManagerDestroyId = warehouseManagerDestroyId;
    }

    @Override
    public String toString() {
        return "EquipmentRecord{" +
                "equipmentId=" + equipmentId +
                ", buyTime=" + buyTime +
                ", destroyTime=" + destroyTime +
                ", warehouseManagerBuyId=" + warehouseManagerBuyId +
                ", warehouseManagerDestroyId=" + warehouseManagerDestroyId +
                '}';
    }
}
