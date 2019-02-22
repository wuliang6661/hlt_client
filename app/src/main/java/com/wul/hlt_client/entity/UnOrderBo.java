package com.wul.hlt_client.entity;

import java.util.List;

/**
 * Created by wuliang on 2018/12/11.
 */

public class UnOrderBo {


    private List<GreengrocerUnOrderListBean> greengrocerUnOrderList;

    public List<GreengrocerUnOrderListBean> getGreengrocerUnOrderList() {
        return greengrocerUnOrderList;
    }

    public void setGreengrocerUnOrderList(List<GreengrocerUnOrderListBean> greengrocerUnOrderList) {
        this.greengrocerUnOrderList = greengrocerUnOrderList;
    }

    public static class GreengrocerUnOrderListBean {
        /**
         * amount : 13.5
         * deliverRestName : 123123
         * id : 72553
         * endTime : 1520875927000
         * deliverAddress : 1231231231
         * createDate : 1520875627000
         */

        private String amount;
        private String deliverRestName;
        private int id;
        private long endTime;
        private String deliverAddress;
        private long createDate;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getDeliverRestName() {
            return deliverRestName;
        }

        public void setDeliverRestName(String deliverRestName) {
            this.deliverRestName = deliverRestName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getDeliverAddress() {
            return deliverAddress;
        }

        public void setDeliverAddress(String deliverAddress) {
            this.deliverAddress = deliverAddress;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }
    }
}
