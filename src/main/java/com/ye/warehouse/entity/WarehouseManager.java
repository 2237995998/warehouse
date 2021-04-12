package com.ye.warehouse.entity;

import org.springframework.stereotype.Component;

/**
 * @PackageName:com.ye.warehouse.dao
 * @ClassName:WarehousManager
 * @Description:仓库管理员类
 * @author:何进业
 * @date:2021/3/22 21:53
 */
@Component
public class WarehouseManager {
    /**
     * 仓库管理员账号
     */
    private int warehouseManagerId;
    /**
     * 仓库管理员密码
     */
    private String warehouseManagerPwd;
    /**
     * 仓库管理员昵称
     */
    private String warehouseManagerName;
    /**
     * 仓库管理员头像地址
     */
    private String warehouseManagerHeader;
    /**
     * 仓库管理员电话
     */
    private String warehouseManagerTel;
    /**
     * 仓库管理员状态
     */
    private int warehouseManagerStatus;


    public int getWarehouseManagerId() {
        return warehouseManagerId;
    }

    public void setWarehouseManagerId(int warehouseManagerId) {
        this.warehouseManagerId = warehouseManagerId;
    }

    public String getWarehouseManagerPwd() {
        return warehouseManagerPwd;
    }

    public void setWarehouseManagerPwd(String warehouseManagerPwd) {
        this.warehouseManagerPwd = warehouseManagerPwd;
    }

    public String getWarehouseManagerName() {
        return warehouseManagerName;
    }

    public void setWarehouseManagerName(String warehouseManagerName) {
        this.warehouseManagerName = warehouseManagerName;
    }

    public String getWarehouseManagerHeader() {
        return warehouseManagerHeader;
    }

    public void setWarehouseManagerHeader(String warehouseManagerHeader) {
        this.warehouseManagerHeader = warehouseManagerHeader;
    }

    public int getWarehouseManagerStatus() {
        return warehouseManagerStatus;
    }

    public void setWarehouseManagerStatus(int warehouseManagerStatus) {
        this.warehouseManagerStatus = warehouseManagerStatus;
    }


    public String getWarehouseManagerTel() {
        return warehouseManagerTel;
    }

    public void setWarehouseManagerTel(String warehouseManagerTel) {
        this.warehouseManagerTel = warehouseManagerTel;
    }

    @Override
    public String toString() {
        return "WarehouseManager{" +
                "warehouseManagerId=" + warehouseManagerId +
                ", warehouseManagerPwd='" + warehouseManagerPwd + '\'' +
                ", warehouseManagerName='" + warehouseManagerName + '\'' +
                ", warehouseManagerHeader='" + warehouseManagerHeader + '\'' +
                ", warehouseManagerTel='" + warehouseManagerTel + '\'' +
                ", warehouseManagerStatus=" + warehouseManagerStatus +
                '}';
    }
}
