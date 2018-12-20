package com.wul.hlt_client.entity;

/**
 * Created by dell on 2018/12/20.
 */

public class CityBO {


    /**
     * cityName : 上海
     * createDate : 1542939012000
     * id : 1
     * phone : 18916163210
     * updateDate : 1542939012000
     */

    private String cityName;
    private long createDate;
    private int id;
    private String phone;
    private long updateDate;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }
}
