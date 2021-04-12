package com.ye.warehouse.entity;

/**
 * @PackageName:com.ye.warehouse.entity
 * @ClassName:Warehouse
 * @Description:仓库类
 * @author:何进业
 * @date:2021/3/23 20:50
 */
public class Warehouse {
    /**
     * 仓库编号
     */
    private int warehouseId;
    /**
     * 可用容量
     */
    private int usableCapacity;
    /**
     * 总容量
     */
    private int totalCapacity;
    /**
     * 系统管理员账号
     */
    private int systemManagerId;

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getUsableCapacity() {
        return usableCapacity;
    }

    public void setUsableCapacity(int usableCapacity) {
        this.usableCapacity = usableCapacity;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getSystemManagerId() {
        return systemManagerId;
    }

    public void setSystemManagerId(int systemManagerId) {
        this.systemManagerId = systemManagerId;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "warehouseId=" + warehouseId +
                ", usableCapacity=" + usableCapacity +
                ", totalCapacity=" + totalCapacity +
                ", systemManagerId=" + systemManagerId +
                '}';
    }
}
