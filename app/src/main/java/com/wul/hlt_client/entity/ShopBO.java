package com.wul.hlt_client.entity;

/**
 * Created by wuliang on 2018/12/18.
 */

public class ShopBO {


    /**
     * image : http://47.98.53.141:8099/hct_webservice/static/images/product/SuanMiao.jpg
     * measureUnitName2 : 袋
     * measureUnitName1 : 斤
     * isPromotion : 0
     * ordinalNumber : 190
     * productDetail : 1斤袋装
     * categoryName : 蔬菜
     * productName : 蒜苗
     * subCategoryName : 葱姜蒜
     * surplusStock : 0
     * id : 620
     * price1 : 3.3
     * productType : 0
     */

    private String image;
    private String measureUnitName2;
    private String measureUnitName1;
    private int isPromotion;
    private int ordinalNumber;
    private String productDetail;
    private String categoryName;
    private String productName;
    private String subCategoryName;
    private int surplusStock;
    private int id;
    private double price1;
    private int productType;

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

    public String getMeasureUnitName1() {
        return measureUnitName1;
    }

    public void setMeasureUnitName1(String measureUnitName1) {
        this.measureUnitName1 = measureUnitName1;
    }

    public int getIsPromotion() {
        return isPromotion;
    }

    public void setIsPromotion(int isPromotion) {
        this.isPromotion = isPromotion;
    }

    public int getOrdinalNumber() {
        return ordinalNumber;
    }

    public void setOrdinalNumber(int ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public int getSurplusStock() {
        return surplusStock;
    }

    public void setSurplusStock(int surplusStock) {
        this.surplusStock = surplusStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice1() {
        return price1;
    }

    public void setPrice1(double price1) {
        this.price1 = price1;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }
}
