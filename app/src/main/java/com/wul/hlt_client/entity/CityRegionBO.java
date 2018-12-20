package com.wul.hlt_client.entity;

/**
 * Created by wuliang on 2018/12/20.
 */

public class CityRegionBO {


    /**
     * areaId : 2
     * cityId : 1
     * createDate : 1542939012000
     * id : 1
     * regionName : 黄浦区
     * updateDate : 1542939012000
     */

    private int areaId;
    private int cityId;
    private long createDate;
    private int id;
    private String regionName;
    private long updateDate;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

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

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }
}
