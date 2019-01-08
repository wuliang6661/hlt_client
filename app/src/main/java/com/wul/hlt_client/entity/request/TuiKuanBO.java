package com.wul.hlt_client.entity.request;

public class TuiKuanBO extends BaseRequest {


    /**
     * id : 0
     * refundAmount : string
     */

    private int id;
    private String refundAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }
}
