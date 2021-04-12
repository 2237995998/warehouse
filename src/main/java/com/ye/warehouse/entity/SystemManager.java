package com.ye.warehouse.entity;

/**
 * @PackageName:com.ye.warehouse.entity
 * @ClassName:SystemManager
 * @Description:系统管理员类
 * @author:何进业
 * @date:2021/3/23 20:47
 */
public class SystemManager {
    /**
     * 系统管理员账号
     */
    private int systemManagerId;
    /**
     * 系统管理员密码
     */
    private String systemManagerPwd;
    /**
     * 系统管理员昵称
     */
    private String systemManagerName;
    /**
     * 系统管理员头像地址
     */
    private String systemManagerHeader;
    /**
     * 系统管理员状态
     */
    private int systemManagerStatus;
    /**
     * 系统管理员电话
     */
    private String systemManagerTel;

    public int getSystemManagerId() {
        return systemManagerId;
    }

    public void setSystemManagerId(int systemManagerId) {
        this.systemManagerId = systemManagerId;
    }

    public String getSystemManagerPwd() {
        return systemManagerPwd;
    }

    public void setSystemManagerPwd(String systemManagerPwd) {
        this.systemManagerPwd = systemManagerPwd;
    }

    public String getSystemManagerName() {
        return systemManagerName;
    }

    public void setSystemManagerName(String systemManagerName) {
        this.systemManagerName = systemManagerName;
    }

    public String getSystemManagerHeader() {
        return systemManagerHeader;
    }

    public void setSystemManagerHeader(String systemManagerHeader) {
        this.systemManagerHeader = systemManagerHeader;
    }

    public int getSystemManagerStatus() {
        return systemManagerStatus;
    }

    public void setSystemManagerStatus(int systemManagerStatus) {
        this.systemManagerStatus = systemManagerStatus;
    }

    public String getSystemManagerTel() {
        return systemManagerTel;
    }

    public void setSystemManagerTel(String systemManagerTel) {
        this.systemManagerTel = systemManagerTel;
    }

    @Override
    public String toString() {
        return "SystemManager{" +
                "systemManagerId=" + systemManagerId +
                ", systemManagerPwd='" + systemManagerPwd + '\'' +
                ", systemManagerName='" + systemManagerName + '\'' +
                ", systemManagerHeader='" + systemManagerHeader + '\'' +
                ", systemManagerStatus=" + systemManagerStatus +
                ", systemManagerTel='" + systemManagerTel + '\'' +
                '}';
    }
}
