package com.wul.hlt_client.entity.request;

/**
 * Created by dell on 2018/12/18.
 */

public class RegisterBO {


    /**
     * cityId : 1
     * regionId : 1
     * name : “烧烤人家”
     * address : “上海市黄浦区同仁路1号”
     * contact : “张三”
     * contactPhone : 1345784514
     * password : 123
     */

    private String cityId;
    private String regionId;
    private String name;
    private String address;
    private String contact;
    private String contactPhone;
    private String password;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
