package com.ye.warehouse.entity;

/**
 * @PackageName:com.ye.warehouse.entity
 * @ClassName:EquipmentCategory
 * @Description:器材种别类
 * @author:何进业
 * @date:2021/3/23 20:08
 */
public class EquipmentCategory {
    /**
     * 器材种别码
     */
    private int equipmentCategoryId;
    /**
     * 器材种别名称
     */
    private String equipmentCategoryName;
    /**
     * 器材种别图片地址
     */

    private String equipmentCategoryPicture;
    /**
     * 器材种别状态
     */
    private int equipmentCategoryStatus;

    public int getEquipmentCategoryId() {
        return equipmentCategoryId;
    }

    public void setEquipmentCategoryId(int equipmentCategoryId) {
        this.equipmentCategoryId = equipmentCategoryId;
    }

    public String getEquipmentCategoryName() {
        return equipmentCategoryName;
    }

    public void setEquipmentCategoryName(String equipmentCategoryName) {
        this.equipmentCategoryName = equipmentCategoryName;
    }

    public String getEquipmentCategoryPicture() {
        return equipmentCategoryPicture;
    }

    public void setEquipmentCategoryPicture(String equipmentCategoryPicture) {
        this.equipmentCategoryPicture = equipmentCategoryPicture;
    }


    public int getEquipmentCategoryStatus() {
        return equipmentCategoryStatus;
    }

    public void setEquipmentCategoryStatus(int equipmentCategoryStatus) {
        this.equipmentCategoryStatus = equipmentCategoryStatus;
    }

    @Override
    public String toString() {
        return "EquipmentCategory{" +
                "equipmentCategoryId=" + equipmentCategoryId +
                ", equipmentCategoryName='" + equipmentCategoryName + '\'' +
                ", equipmentCategoryPicture='" + equipmentCategoryPicture + '\'' +
                ", equipmentCategoryStatus=" + equipmentCategoryStatus +
                '}';
    }
}
