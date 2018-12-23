package com.wul.hlt_client.entity;

import java.util.List;

/**
 * Created by wuliang on 2018/12/22.
 */

public class ShopCarBO {


    /**
     * amount : 4.2
     * shoppingCartList : [{"image":"product/YuMiZha_CQ.jpg","measureUnitName2":"袋","quantity":1,"isPromotion":1,"productId":920,"money":4.2,"httpUrl":"http://47.98.53.141:8099/hct_webservice/static/images/product/YuMiZha_CQ.jpg","productName":"玉米渣","productType":0,"price2":5,"promotionPrice2":4.2}]
     */

    private double amount;
    private List<ShopBO> shoppingCartList;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<ShopBO> getShoppingCartList() {
        return shoppingCartList;
    }

    public void setShoppingCartList(List<ShopBO> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
    }
}
