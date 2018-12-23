package com.wul.hlt_client.entity;

import java.util.List;

/**
 * Created by wuliang on 2018/12/22.
 */

public class XianShiBO {


    /**
     * startTime : 1545445769000
     * endTime : 1545473580000
     * time : 27811000
     * list : [{"image":"http://47.98.53.141:8099/hct_webservice/static/images/product/YuMiZha_CQ.jpg","measureUnitName2":"袋","measureUnitName1":"斤","isPromotion":1,"ordinalNumber":100,"productDetail":"2斤袋装","promotionPrice1":2.1,"categoryName":"米面粮油","maxBuyNum":0,"productName":"玉米渣","promotionPrice2":4.2,"subCategoryName":"米面","surplusStock":0,"id":920,"price1":2.5,"productType":0,"price2":5},{"image":"http://47.98.53.141:8099/hct_webservice/static/images/product/JingShuiDouYa.jpg","measureUnitName2":"袋","measureUnitName1":"斤","isPromotion":1,"ordinalNumber":480,"productDetail":"5斤袋装","promotionPrice1":1.5,"categoryName":"蔬菜","maxBuyNum":0,"productName":"井水豆芽","promotionPrice2":7.5,"subCategoryName":"其他","surplusStock":0,"id":927,"price1":1.8,"productType":0,"price2":9},{"image":"http://47.98.53.141:8099/hct_webservice/static/images/product/HuaiShanYao_CQ.jpg","measureUnitName2":"袋","measureUnitName1":"斤","isPromotion":1,"ordinalNumber":88,"productDetail":"5斤袋装","promotionPrice1":4.2,"categoryName":"蔬菜","maxBuyNum":0,"productName":"淮山药","promotionPrice2":21,"subCategoryName":"根茎类","surplusStock":0,"id":913,"price1":4.8,"productType":0,"price2":24},{"image":"http://47.98.53.141:8099/hct_webservice/static/images/product/YuanHongLuoBo.jpg","measureUnitName2":"袋","measureUnitName1":"斤","isPromotion":1,"ordinalNumber":657,"productDetail":"5斤袋装","promotionPrice1":0.8,"categoryName":"蔬菜","maxBuyNum":0,"productName":"圆红萝卜","promotionPrice2":4,"subCategoryName":"根茎类","surplusStock":0,"id":915,"price1":1.1,"productType":0,"price2":5.5}]
     */

    private long startTime;
    private long endTime;
    private int time;
    private List<ShopBO> list;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<ShopBO> getList() {
        return list;
    }

    public void setList(List<ShopBO> list) {
        this.list = list;
    }


}
