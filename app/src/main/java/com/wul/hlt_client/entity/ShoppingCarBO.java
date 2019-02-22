package com.wul.hlt_client.entity;

import java.util.List;

/**
 * Created by wuliang on 2018/12/25.
 */

public class ShoppingCarBO {


    /**
     * amount : 52.7
     * shoppingCartList : [{"image":"product/B002_Xi_Lan_Hua.jpg","measureUnitName2":"袋","quantity":1,"isPromotion":0,"productId":6,"money":16,"httpUrl":"http://47.98.53.141:8099/hct_webservice/static/images/product/B002_Xi_Lan_Hua.jpg","productName":"西兰花（优质）","productType":1,"price2":17,"promotionPrice2":16},{"image":"product/YuMiZha_CQ.jpg","measureUnitName2":"袋","quantity":1,"isPromotion":1,"productId":920,"money":4.2,"httpUrl":"http://47.98.53.141:8099/hct_webservice/static/images/product/YuMiZha_CQ.jpg","productName":"玉米渣","productType":0,"price2":5,"promotionPrice2":4.2},{"image":"product/JingShuiDouYa.jpg","measureUnitName2":"袋","quantity":1,"isPromotion":1,"productId":927,"money":7.5,"httpUrl":"http://47.98.53.141:8099/hct_webservice/static/images/product/JingShuiDouYa.jpg","productName":"井水豆芽","productType":0,"price2":9,"promotionPrice2":7.5},{"image":"product/HuaiShanYao_CQ.jpg","measureUnitName2":"袋","quantity":1,"isPromotion":1,"productId":913,"money":21,"httpUrl":"http://47.98.53.141:8099/hct_webservice/static/images/product/HuaiShanYao_CQ.jpg","productName":"淮山药","productType":0,"price2":24,"promotionPrice2":21},{"image":"product/YuanHongLuoBo.jpg","measureUnitName2":"袋","quantity":1,"isPromotion":1,"productId":915,"money":4,"httpUrl":"http://47.98.53.141:8099/hct_webservice/static/images/product/YuanHongLuoBo.jpg","productName":"圆红萝卜","productType":0,"price2":5.5,"promotionPrice2":4}]
     */

    private String amount;
    private List<ShoppingCartListBean> shoppingCartList;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public List<ShoppingCartListBean> getShoppingCartList() {
        return shoppingCartList;
    }

    public void setShoppingCartList(List<ShoppingCartListBean> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
    }

    public static class ShoppingCartListBean {
        /**
         * image : product/B002_Xi_Lan_Hua.jpg
         * measureUnitName2 : 袋
         * quantity : 1
         * isPromotion : 0
         * productId : 6
         * money : 16.0
         * httpUrl : http://47.98.53.141:8099/hct_webservice/static/images/product/B002_Xi_Lan_Hua.jpg
         * productName : 西兰花（优质）
         * productType : 1
         * price2 : 17.0
         * promotionPrice2 : 16.0
         */

        private String image;
        private String measureUnitName2;
        private int quantity;
        private int isPromotion;
        private int productId;
        private String money;
        private String httpUrl;
        private String productName;
        private int productType;
        private String price2;
        private String promotionPrice2;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getMeasureUnitName2() {
            return measureUnitName2;
        }

        public void setMeasureUnitName2(String measureUnitName2) {
            this.measureUnitName2 = measureUnitName2;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getIsPromotion() {
            return isPromotion;
        }

        public void setIsPromotion(int isPromotion) {
            this.isPromotion = isPromotion;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getHttpUrl() {
            return httpUrl;
        }

        public void setHttpUrl(String httpUrl) {
            this.httpUrl = httpUrl;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getProductType() {
            return productType;
        }

        public void setProductType(int productType) {
            this.productType = productType;
        }

        public String getPrice2() {
            return price2;
        }

        public void setPrice2(String price2) {
            this.price2 = price2;
        }

        public String getPromotionPrice2() {
            return promotionPrice2;
        }

        public void setPromotionPrice2(String promotionPrice2) {
            this.promotionPrice2 = promotionPrice2;
        }
    }
}
