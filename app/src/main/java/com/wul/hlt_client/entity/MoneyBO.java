package com.wul.hlt_client.entity;

/**
 * Created by wuliang on 2018/12/26.
 */

public class MoneyBO {


    /**
     * balancePay : 3.3
     * amount : 3.3
     * distributionFee : 0.0
     */

    private String balancePay;
    private String amount;
    private String distributionFee;
    private String payableAmount;

    public String getBalancePay() {
        return balancePay;
    }

    public void setBalancePay(String balancePay) {
        this.balancePay = balancePay;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDistributionFee() {
        return distributionFee;
    }

    public void setDistributionFee(String distributionFee) {
        this.distributionFee = distributionFee;
    }

    public String getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(String payableAmount) {
        this.payableAmount = payableAmount;
    }
}
