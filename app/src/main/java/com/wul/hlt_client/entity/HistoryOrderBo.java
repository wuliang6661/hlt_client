package com.wul.hlt_client.entity;

import java.util.List;

/**
 * Created by wuliang on 2018/12/10.
 */

public class HistoryOrderBo {


    /**
     * greengrocerHistoryOrderList : [{"amount":13.5,"updateDate":1544276030000,"statusId":1,"productDetailList":[{"price0MeasureUnit":"斤","img":"http://47.98.53.141:8099/hct_webservice/static/images/product/D006_Da_Cong.jpg","measureUnit1And2Relation":1,"quantity":3,"cost":12,"productId":614,"productDetail":"1斤袋装","price2MeasureUnit":"袋","datedProductId":"20180317_614","consumption":2,"source":"待定","number2":3,"productName":"香菜（本地）","amountOfMoney":13.5,"price0":4,"price1MeasureUnit":"斤","price1":4.5,"price2":4.5}],"id":72554},{"amount":11.4,"updateDate":1544276030000,"statusId":1,"productDetailList":[{"price0MeasureUnit":"斤","img":"http://47.98.53.141:8099/hct_webservice/static/images/product/XiaoCong_BaiTou.jpg","measureUnit1And2Relation":1,"quantity":6,"cost":9.6,"productId":615,"productDetail":"1斤袋装","price2MeasureUnit":"袋","datedProductId":"20180317_615","consumption":2,"source":"待定","number2":6,"productName":"小葱（白头）","amountOfMoney":11.4,"price0":1.6,"price1MeasureUnit":"斤","price1":1.9,"price2":1.9}],"id":72553},{"amount":4,"updateDate":1544275500000,"statusId":1,"productDetailList":[{"price0MeasureUnit":"斤","img":"http://47.98.53.141:8099/hct_webservice/static/images/product/XiaoCong_HongTou.jpg","measureUnit1And2Relation":1,"quantity":2,"cost":3.4,"productId":616,"productDetail":"1斤袋装","price2MeasureUnit":"袋","datedProductId":"20180317_616","consumption":2,"source":"待定","number2":2,"productName":"小葱（红头）","amountOfMoney":4,"price0":1.7,"price1MeasureUnit":"斤","price1":2,"price2":2}],"id":72552},{"amount":12.8,"updateDate":1520487646000,"statusId":2,"productDetailList":[{"price0MeasureUnit":"斤","img":"http://47.98.53.141:8099/hct_webservice/static/images/product/YangCong.jpg","measureUnit1And2Relation":2,"quantity":8,"cost":10.4,"productId":617,"productDetail":"2斤袋装","price2MeasureUnit":"袋","datedProductId":"20180317_617","consumption":2,"source":"待定","number2":4,"productName":"紫洋葱","amountOfMoney":12.8,"price0":1.3,"price1MeasureUnit":"斤","price1":1.6,"price2":3.2}],"id":72551},{"amount":15,"updateDate":1520487593000,"statusId":3,"productDetailList":[{"price0MeasureUnit":"斤","img":"http://47.98.53.141:8099/hct_webservice/static/images/product/LaoJiang.jpg","measureUnit1And2Relation":2,"quantity":5,"cost":13,"productId":618,"productDetail":"2斤袋装","price2MeasureUnit":"袋","datedProductId":"20180317_618","consumption":2,"source":"待定","number2":3,"productName":"老姜","amountOfMoney":15,"price0":2.6,"price1MeasureUnit":"斤","price1":3,"price2":6}],"id":72550},{"amount":2.5,"updateDate":1520064057000,"statusId":2,"productDetailList":[{"price0MeasureUnit":"斤","img":"http://47.98.53.141:8099/hct_webservice/static/images/product/SuanTai.jpg","measureUnit1And2Relation":1,"quantity":1,"cost":2.1,"productId":619,"productDetail":"1斤袋装","price2MeasureUnit":"袋","datedProductId":"20180317_619","consumption":2,"source":"待定","number2":1,"productName":"蒜苔","amountOfMoney":2.5,"price0":2.1,"price1MeasureUnit":"斤","price1":2.5,"price2":2.5}],"id":72549},{"amount":26.4,"updateDate":1520062567000,"statusId":2,"productDetailList":[{"price0MeasureUnit":"斤","img":"http://47.98.53.141:8099/hct_webservice/static/images/product/SuanMiao.jpg","measureUnit1And2Relation":1,"quantity":8,"cost":23.2,"productId":619,"productDetail":"1斤袋装","price2MeasureUnit":"袋","datedProductId":"20180317_619","consumption":2,"source":"待定","number2":8,"productName":"蒜苗","amountOfMoney":26.4,"price0":2.9,"price1MeasureUnit":"斤","price1":3.3,"price2":3.3}],"id":72548}]
     * aCountMoneyTotal : 85.6
     */

    private double aCountMoneyTotal;
    private List<GreengrocerHistoryOrderListBean> greengrocerHistoryOrderList;

    public double getACountMoneyTotal() {
        return aCountMoneyTotal;
    }

    public void setACountMoneyTotal(double aCountMoneyTotal) {
        this.aCountMoneyTotal = aCountMoneyTotal;
    }

    public List<GreengrocerHistoryOrderListBean> getGreengrocerHistoryOrderList() {
        return greengrocerHistoryOrderList;
    }

    public void setGreengrocerHistoryOrderList(List<GreengrocerHistoryOrderListBean> greengrocerHistoryOrderList) {
        this.greengrocerHistoryOrderList = greengrocerHistoryOrderList;
    }

    public static class GreengrocerHistoryOrderListBean {
        /**
         * amount : 13.5
         * updateDate : 1544276030000
         * statusId : 1
         * productDetailList : [{"price0MeasureUnit":"斤","img":"http://47.98.53.141:8099/hct_webservice/static/images/product/D006_Da_Cong.jpg","measureUnit1And2Relation":1,"quantity":3,"cost":12,"productId":614,"productDetail":"1斤袋装","price2MeasureUnit":"袋","datedProductId":"20180317_614","consumption":2,"source":"待定","number2":3,"productName":"香菜（本地）","amountOfMoney":13.5,"price0":4,"price1MeasureUnit":"斤","price1":4.5,"price2":4.5}]
         * id : 72554
         */

        private double amount;
        private long updateDate;
        private int statusId;
        private int id;
        private List<ProductDetailListBean> productDetailList;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public int getStatusId() {
            return statusId;
        }

        public void setStatusId(int statusId) {
            this.statusId = statusId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
             * img : http://47.98.53.141:8099/hct_webservice/static/images/product/D006_Da_Cong.jpg
             * measureUnit1And2Relation : 1.0
             * quantity : 3
             * cost : 12.0
             * productId : 614
             * productDetail : 1斤袋装
             * price2MeasureUnit : 袋
             * datedProductId : 20180317_614
             * consumption : 2
             * source : 待定
             * number2 : 3
             * productName : 香菜（本地）
             * amountOfMoney : 13.5
             * price0 : 4.0
             * price1MeasureUnit : 斤
             * price1 : 4.5
             * price2 : 4.5
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
}
