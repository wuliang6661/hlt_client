package com.wul.hlt_client.entity;

/**
 * Created by dell on 2018/12/19.
 */

public class CityGongGao {


    /**
     * cityId : 2
     * content : 为确保及时配送，请您在每晚12点半前尽早下单。晚上12点半之后的订单，不能保证当天配送。敬请谅解！
     * createDate : 1543053273000
     * id : 1
     * updateDate : 1543053275000
     */

    private int cityId;
    private String content;
    private long createDate;
    private int id;
    private long updateDate;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }
}
