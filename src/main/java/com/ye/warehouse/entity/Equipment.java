package com.ye.warehouse.entity;

/**
 * @PackageName:com.ye.warehouse.entity
 * @ClassName:equipment
 * @Description:器材类
 * @author:何进业
 * @date:2021/3/23 19:43
 */
public class Equipment {
    /**
     *器材编号
     */
    private int equipmentId;
    /**
     * 器材状态 1:在使用 0：已销毁
     */
    private int equipmentStatus;
    /**
     * 器材种别id
     */
    private int equipmentCategoryId;

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(int equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public int getEquipmentCategoryId() {
        return equipmentCategoryId;
    }

    public void setEquipmentCategoryId(int equipmentCategoryId) {
        this.equipmentCategoryId = equipmentCategoryId;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId=" + equipmentId +
                ", equipmentStatus=" + equipmentStatus +
                ", equipmentCategoryId=" + equipmentCategoryId +
                '}';
    }
}
