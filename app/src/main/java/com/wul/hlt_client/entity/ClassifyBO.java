package com.wul.hlt_client.entity;

/**
 * Created by dell on 2018/12/18.
 */

public class ClassifyBO {


    /**
     * active : 1
     * categoryName : 蔬菜
     * createDate : 1542939012000
     * id : 1
     * image : http://47.98.53.141:8099/hct_webservice/static/images/http://192.168.1.110:8089/images/1543221067121.png
     * updateDate : 1543170668000
     */

    private int active;
    private String categoryName;
    private long createDate;
    private int id;
    private String image;
    private long updateDate;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }
}
