package com.wul.hlt_client.entity;

/**
 * Created by wuliang on 2018/12/25.
 */

public class AddressBO {


    /**
     * address : 重庆市万州区1号
     * contact : 李平
     * addressName : 烧烤青春
     * addressBalance : 0.0
     * contactPhone : 13336195265
     * addressId : 1401
     */

    private String address;
    private String contact;
    private String addressName;
    private double addressBalance;
    private String contactPhone;
    private int addressId;

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

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public double getAddressBalance() {
        return addressBalance;
    }

    public void setAddressBalance(double addressBalance) {
        this.addressBalance = addressBalance;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
