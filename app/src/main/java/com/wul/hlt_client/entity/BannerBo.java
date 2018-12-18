package com.wul.hlt_client.entity;

/**
 * Created by dell on 2018/12/18.
 */

public class BannerBo {


    /**
     * cityId : 2
     * createDate : 1543930500000
     * id : 3
     * image : home/3.jpg
     * orderNum : 3
     * updateDate : 1543930500000
     */

    private int cityId;
    private long createDate;
    private int id;
    private String image;
    private int orderNum;
    private long updateDate;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }
}
