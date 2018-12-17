package com.wul.hlt_client.entity;

/**
 * Created by wuliang on 2018/12/9.
 *
 * 用户信息bean
 */

public class UserBo {


    /**
     * number : 811959
     * phone : 13878487477
     * name : 小西
     * id : 5
     * token : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4MTE5NTkiLCJjcmVhdGVkIjoxNTQ0NDQ1NzYzMjYwLCJleHAiOjE1NTMwODU3NjN9.8ojkbXXK0q3Y9pqDnR-D34hU0FBjy8NOhO24HE-t7hrUKR8D2PyzMn-BMSVZpUzQffKizr-6axP93-dLFjGvKg
     */

    private String number;
    private String phone;
    private String name;
    private int id;
    private String token;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
