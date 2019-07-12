package com.wul.hlt_client.entity;

/**
 * author : wuliang
 * e-mail : wuliang6661@163.com
 * date   : 2019/7/1216:15
 * desc   :
 * version: 1.0
 */
public class VersionBo {


    /**
     * createDate : 1562845091000
     * id : 1
     * updateDate : 1562845091000
     * versionCode : 0
     * versionNum : v1.0.1
     * versionUrl : http://47.98.53.141:8099/hct_webservice/apk/address.apk
     * vsersionType : 0
     */

    private long createDate;
    private int id;
    private long updateDate;
    private int versionCode;
    private String versionNum;
    private String versionUrl;
    private int vsersionType;

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public String getVersionUrl() {
        return versionUrl;
    }

    public void setVersionUrl(String versionUrl) {
        this.versionUrl = versionUrl;
    }

    public int getVsersionType() {
        return vsersionType;
    }

    public void setVsersionType(int vsersionType) {
        this.vsersionType = vsersionType;
    }
}
