package com.wul.hlt_client.entity.request;

/**
 * Created by wuliang on 2018/12/25.
 */

public class CommitOrderBO extends BaseRequest {


    /**
     * balancePay : string
     * distributionFee : string
     * orderType : 0
     * payChannel : 0
     * requireDeliverOn : string
     * uaddress : {}
     */

    public String balancePay;
    public String distributionFee;
    public int orderType;
    public int payChannel;
    public String requireDeliverOn;
}
