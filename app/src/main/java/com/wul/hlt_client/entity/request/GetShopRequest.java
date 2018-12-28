package com.wul.hlt_client.entity.request;

/**
 * Created by dell on 2018/12/28.
 */

public class GetShopRequest extends BaseRequest {


    /**
     * categoryId : 0
     * pageNum : 0
     * pageSize : 0
     * productName : string
     * subCategoryId : 0
     */

    public int categoryId;
    public int pageNum;
    public int pageSize;
    public String productName;
    public int subCategoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }
}
