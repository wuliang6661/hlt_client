package com.wul.hlt_client.entity;

import java.util.List;

/**
 * Created by wuliang on 2018/12/11.
 */

public class OrderDetails {


    /**
     * consigneeName : 123123
     * amount : 12.0
     * statusId : 1
     * orderDisplayId : 02304800072552
     * deliverRestName : 123123
     * productDetailList : [{"price0MeasureUnit":"斤","img":"http://47.98.53.141:8099/hct_webservice/static/images/product/XiaoCong_HongTou.jpg","measureUnit1And2Relation":1,"quantity":2,"cost":3.4,"productId":616,"productDetail":"1斤袋装","price2MeasureUnit":"袋","datedProductId":"20180317_616","consumption":2,"source":"待定","number2":2,"productName":"小葱（红头）","amountOfMoney":4,"price0":1.7,"price1MeasureUnit":"斤","price1":2,"price2":2}]
     * consigneePhone : 13312341234
     * id : 72552
     * deliverAddress : 1231231231
     * payStatus : 0
     * createDate : 1520785907000
     */

    private String consigneeName;
    private double amount;
    private int statusId;
    private String orderDisplayId;
    private String deliverRestName;
    private String consigneePhone;
    private int id;
    private String deliverAddress;
    private int payStatus;
    private long createDate;
    private List<ProductDetailListBean> productDetailList;
    private long endTime;

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getOrderDisplayId() {
        return orderDisplayId;
    }

    public void setOrderDisplayId(String orderDisplayId) {
        this.orderDisplayId = orderDisplayId;
    }

    public String getDeliverRestName() {
        return deliverRestName;
    }

    public void setDeliverRestName(String deliverRestName) {
        this.deliverRestName = deliverRestName;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeliverAddress() {
        return deliverAddress;
    }

    public void setDeliverAddress(String deliverAddress) {
        this.deliverAddress = deliverAddress;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public List<ProductDetailListBean> getProductDetailList() {
        return productDetailList;
    }

    public void setProductDetailList(List<ProductDetailListBean> productDetailList) {
        this.productDetailList = productDetailList;
    }

    public static class ProductDetailListBean {
        /**
         * price0MeasureUnit : 斤
         * img : http://47.98.53.141:8099/hct_webservice/static/images/product/XiaoCong_HongTou.jpg
         * measureUnit1And2Relation : 1.0
         * quantity : 2
         * cost : 3.4
         * productId : 616
         * productDetail : 1斤袋装
         * price2MeasureUnit : 袋
         * datedProductId : 20180317_616
         * consumption : 2
         * source : 待定
         * number2 : 2
         * productName : 小葱（红头）
         * amountOfMoney : 4.0
         * price0 : 1.7
         * price1MeasureUnit : 斤
         * price1 : 2.0
         * price2 : 2.0
         */

        private String price0MeasureUnit;
        private String img;
        private double measureUnit1And2Relation;
        private int quantity;
        private double cost;
        private int productId;
        private String productDetail;
        private String price2MeasureUnit;
        private String datedProductId;
        private int consumption;
        private String source;
        private int number2;
        private String productName;
        private double amountOfMoney;
        private double price0;
        private String price1MeasureUnit;
        private double price1;
        private double price2;

        public String getPrice0MeasureUnit() {
            return price0MeasureUnit;
        }

        public void setPrice0MeasureUnit(String price0MeasureUnit) {
            this.price0MeasureUnit = price0MeasureUnit;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public double getMeasureUnit1And2Relation() {
            return measureUnit1And2Relation;
        }

        public void setMeasureUnit1And2Relation(double measureUnit1And2Relation) {
            this.measureUnit1And2Relation = measureUnit1And2Relation;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getProductDetail() {
            return productDetail;
        }

        public void setProductDetail(String productDetail) {
            this.productDetail = productDetail;
        }

        public String getPrice2MeasureUnit() {
            return price2MeasureUnit;
        }

        public void setPrice2MeasureUnit(String price2MeasureUnit) {
            this.price2MeasureUnit = price2MeasureUnit;
        }

        public String getDatedProductId() {
            return datedProductId;
        }

        public void setDatedProductId(String datedProductId) {
            this.datedProductId = datedProductId;
        }

        public int getConsumption() {
            return consumption;
        }

        public void setConsumption(int consumption) {
            this.consumption = consumption;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public int getNumber2() {
            return number2;
        }

        public void setNumber2(int number2) {
            this.number2 = number2;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getAmountOfMoney() {
            return amountOfMoney;
        }

        public void setAmountOfMoney(double amountOfMoney) {
            this.amountOfMoney = amountOfMoney;
        }

        public double getPrice0() {
            return price0;
        }

        public void setPrice0(double price0) {
            this.price0 = price0;
        }

        public String getPrice1MeasureUnit() {
            return price1MeasureUnit;
        }

        public void setPrice1MeasureUnit(String price1MeasureUnit) {
            this.price1MeasureUnit = price1MeasureUnit;
        }

        public double getPrice1() {
            return price1;
        }

        public void setPrice1(double price1) {
            this.price1 = price1;
        }

        public double getPrice2() {
            return price2;
        }

        public void setPrice2(double price2) {
            this.price2 = price2;
        }
    }
}
