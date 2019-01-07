package com.wul.hlt_client.entity.request;

public class ScreenBO extends BaseRequest{


    /**
     * displayType : 0
     * orderTypes : string
     * payStatus : string
     * statusIds : string
     */

    private int displayType;
    private String orderTypes;
    private String payStatus;
    private String statusIds;

    public int getDisplayType() {
        return displayType;
    }

    public void setDisplayType(int displayType) {
        this.displayType = displayType;
    }

    public String getOrderTypes() {
        return orderTypes;
    }

    public void setOrderTypes(String orderTypes) {
        this.orderTypes = orderTypes;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getStatusIds() {
        return statusIds;
    }

    public void setStatusIds(String statusIds) {
        this.statusIds = statusIds;
    }
}
