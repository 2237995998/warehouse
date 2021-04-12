package com.ye.warehouse.entity;

/**
 * @PackageName:com.ye.warehouse.entity
 * @ClassName:QualityMan
 * @Description:质检员类
 * @author:何进业
 * @date:2021/3/23 20:39
 */
public class QualityMan {
    /**
     * 质检员账号
     */
    private int qualityManId;
    /**
     * 质检员密码
     */
    private String qualityManPwd;
    /**
     * 质检员昵称
     */
    private String qualityManName;
    /**
     * 质检员头像地址
     */
    private String qualityManHeader;
    /**
     * 质检员电话
     */
    private String qualityManTel;
    /**
     * 质检员状态
     */
    private int qualityManStatus;

    public int getQualityManId() {
        return qualityManId;
    }

    public void setQualityManId(int qualityManId) {
        this.qualityManId = qualityManId;
    }

    public String getQualityManPwd() {
        return qualityManPwd;
    }

    public void setQualityManPwd(String qualityManPwd) {
        this.qualityManPwd = qualityManPwd;
    }

    public String getQualityManName() {
        return qualityManName;
    }

    public void setQualityManName(String qualityManName) {
        this.qualityManName = qualityManName;
    }

    public String getQualityManHeader() {
        return qualityManHeader;
    }

    public void setQualityManHeader(String qualityManHeader) {
        this.qualityManHeader = qualityManHeader;
    }

    public int getQualityManStatus() {
        return qualityManStatus;
    }

    public void setQualityManStatus(int qualityManStatus) {
        this.qualityManStatus = qualityManStatus;
    }


    public String getQualityManTel() {
        return qualityManTel;
    }

    public void setQualityManTel(String qualityManTel) {
        this.qualityManTel = qualityManTel;
    }

    @Override
    public String toString() {
        return "QualityMan{" +
                "qualityManId=" + qualityManId +
                ", qualityManPwd='" + qualityManPwd + '\'' +
                ", qualityManName='" + qualityManName + '\'' +
                ", qualityManHeader='" + qualityManHeader + '\'' +
                ", qualityManTel='" + qualityManTel + '\'' +
                ", qualityManStatus=" + qualityManStatus +
                '}';
    }
}
