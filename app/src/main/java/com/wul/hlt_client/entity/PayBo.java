package com.wul.hlt_client.entity;

public class PayBo {


    /**
     * isAliPay : 1
     * orderId : 124863
     * aliPayUrl : alipay_sdk=alipay-sdk-java-3.1.0&app_id=2016102502325428&biz_content=%7B%22body%22%3A%22%E5%A5%BD%E8%8F%9C%E9%80%9A%E8%AE%A2%E5%8D%95%22%2C%22out_trade_no%22%3A%22hct_pay_dev_2019061621022380406%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%A5%BD%E8%8F%9C%E9%80%9A%E8%AE%A2%E5%8D%95%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F47.98.53.141%3A8099%2Fhct_webservice%2Fapp%2Faddress%2Fsettlement%2FgetAlipayNotifyInfo&sign=oYCmR9cT%2FCOUBWLYg33THjGx8AQtGlEOW7p%2FSAjRfAG4H4yeit9NX5oslbieWVg6xnZ%2F2Upv7TlSYFuskikhVHD5ZzAeaT6FAr%2Btp3I6L8FddPjkD4TZpZ3CwIOGH95%2FURktIxFK4sGlHMzS%2B6wSbpJCQQm%2FTpAs3m0o8%2BsFtvE%3D&sign_type=RSA&timestamp=2019-06-16+21%3A02%3A23&version=1.0
     */

    private int isAliPay;
    private int orderId;
    private String aliPayUrl;

    public int getIsAliPay() {
        return isAliPay;
    }

    public void setIsAliPay(int isAliPay) {
        this.isAliPay = isAliPay;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getAliPayUrl() {
        return aliPayUrl;
    }

    public void setAliPayUrl(String aliPayUrl) {
        this.aliPayUrl = aliPayUrl;
    }
}
