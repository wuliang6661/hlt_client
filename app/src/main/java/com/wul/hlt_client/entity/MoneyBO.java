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

    private float balancePay;
    private float amount;
    private float distributionFee;

    public float getBalancePay() {
        return balancePay;
    }

    public void setBalancePay(float balancePay) {
        this.balancePay = balancePay;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getDistributionFee() {
        return distributionFee;
    }

    public void setDistributionFee(float distributionFee) {
        this.distributionFee = distributionFee;
    }
}
