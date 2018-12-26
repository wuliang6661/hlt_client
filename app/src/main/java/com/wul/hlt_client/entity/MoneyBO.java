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

    private double balancePay;
    private double amount;
    private double distributionFee;

    public double getBalancePay() {
        return balancePay;
    }

    public void setBalancePay(double balancePay) {
        this.balancePay = balancePay;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDistributionFee() {
        return distributionFee;
    }

    public void setDistributionFee(double distributionFee) {
        this.distributionFee = distributionFee;
    }
}
