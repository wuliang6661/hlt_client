package com.wul.hlt_client.entity;

import java.io.Serializable;

public class ShopInfoBO implements Serializable {


    /**
     * number : 810525
     * address : 重庆市万州区1号
     * balance : 5.00
     * customerServicePhone : 17784256797
     * contact : 李平
     * name : 烧烤青春
     * isConfirmed : 1
     * contactPhone : 13336195265
     */

    private String number;
    private String address;
    private String balance;
    private String customerServicePhone;
    private String contact;
    private String name;
    private int isConfirmed;
    private String contactPhone;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCustomerServicePhone() {
        return customerServicePhone;
    }

    public void setCustomerServicePhone(String customerServicePhone) {
        this.customerServicePhone = customerServicePhone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(int isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
