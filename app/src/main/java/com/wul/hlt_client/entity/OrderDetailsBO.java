package com.wul.hlt_client.entity;

import java.util.List;

public class OrderDetailsBO {


    /**
     * orderType : 0
     * amount : 10.6
     * requireDeliverOn : 2019年01月06日06-11点
     * orderDisplayId : 02353500072694
     * deliverAddress : 重庆市万州区1号
     * consigneeName : 李平
     * statusId : 1
     * deliverRestName : 烧烤青春
     * productDetailList : [{"price0MeasureUnit":"斤","img":"http://47.98.53.141:8099/hct_webservice/static/images/product/ZheErGenYe.jpg","measureUnit1And2Relation":2,"quantity":2,"cost":6,"productId":874,"productDetail":"2斤袋装","price2MeasureUnit":"袋","datedProductId":"20190105_874","consumption":2,"source":"待定","number2":1,"productName":"折耳根叶","amountOfMoney":6.8,"price0":3,"price1MeasureUnit":"斤","price1":3.4,"price2":6.8},{"price0MeasureUnit":"斤","img":"http://47.98.53.141:8099/hct_webservice/static/images/product/DongHanCai.jpg","measureUnit1And2Relation":2,"quantity":2,"cost":3.2,"productId":866,"productDetail":"2斤袋装","price2MeasureUnit":"袋","datedProductId":"20190105_866","consumption":2,"source":"待定","number2":1,"productName":"冬寒菜","amountOfMoney":4,"price0":1.6,"price1MeasureUnit":"斤","price1":2,"price2":3.8}]
     * consigneePhone : 13336195265
     * id : 72694
     * payStatus : 1
     * createDate : 1546675696000
     */

    private int orderType;
    private String amount;
    private String requireDeliverOn;
    private String orderDisplayId;
    private String deliverAddress;
    private String consigneeName;
    private int statusId;
    private String deliverRestName;
    private String consigneePhone;
    private String id;
    private int payStatus;
    private long createDate;
    private List<ProductDetailListBean> productDetailList;
    private String greengrocerPhone;
    private String customerServicePhone;
    private int isDisplay;

    public int getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(int isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getCustomerServicePhone() {
        return customerServicePhone;
    }

    public void setCustomerServicePhone(String customerServicePhone) {
        this.customerServicePhone = customerServicePhone;
    }

    public String getGreengrocerPhone() {
        return greengrocerPhone;
    }

    public void setGreengrocerPhone(String greengrocerPhone) {
        this.greengrocerPhone = greengrocerPhone;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRequireDeliverOn() {
        return requireDeliverOn;
    }

    public void setRequireDeliverOn(String requireDeliverOn) {
        this.requireDeliverOn = requireDeliverOn;
    }

    public String getOrderDisplayId() {
        return orderDisplayId;
    }

    public void setOrderDisplayId(String orderDisplayId) {
        this.orderDisplayId = orderDisplayId;
    }

    public String getDeliverAddress() {
        return deliverAddress;
    }

    public void setDeliverAddress(String deliverAddress) {
        this.deliverAddress = deliverAddress;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
         * img : http://47.98.53.141:8099/hct_webservice/static/images/product/ZheErGenYe.jpg
         * measureUnit1And2Relation : 2
         * quantity : 2
         * cost : 6
         * productId : 874
         * productDetail : 2斤袋装
         * price2MeasureUnit : 袋
         * datedProductId : 20190105_874
         * consumption : 2
         * source : 待定
         * number2 : 1
         * productName : 折耳根叶
         * amountOfMoney : 6.8
         * price0 : 3
         * price1MeasureUnit : 斤
         * price1 : 3.4
         * price2 : 6.8
         */

        private String price0MeasureUnit;
        private String img;
        private String measureUnit1And2Relation;
        private String quantity;
        private String cost;
        private String productId;
        private String productDetail;
        private String price2MeasureUnit;
        private String datedProductId;
        private String consumption;
        private String source;
        private String number2;
        private String productName;
        private String amountOfMoney;
        private String price0;
        private String price1MeasureUnit;
        private String price1;
        private String price2;

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

        public String getMeasureUnit1And2Relation() {
            return measureUnit1And2Relation;
        }

        public void setMeasureUnit1And2Relation(String measureUnit1And2Relation) {
            this.measureUnit1And2Relation = measureUnit1And2Relation;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
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

        public String getConsumption() {
            return consumption;
        }

        public void setConsumption(String consumption) {
            this.consumption = consumption;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getNumber2() {
            return number2;
        }

        public void setNumber2(String number2) {
            this.number2 = number2;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getAmountOfMoney() {
            return amountOfMoney;
        }

        public void setAmountOfMoney(String amountOfMoney) {
            this.amountOfMoney = amountOfMoney;
        }

        public String getPrice0() {
            return price0;
        }

        public void setPrice0(String price0) {
            this.price0 = price0;
        }

        public String getPrice1MeasureUnit() {
            return price1MeasureUnit;
        }

        public void setPrice1MeasureUnit(String price1MeasureUnit) {
            this.price1MeasureUnit = price1MeasureUnit;
        }

        public String getPrice1() {
            return price1;
        }

        public void setPrice1(String price1) {
            this.price1 = price1;
        }

        public String getPrice2() {
            return price2;
        }

        public void setPrice2(String price2) {
            this.price2 = price2;
        }
    }
}
