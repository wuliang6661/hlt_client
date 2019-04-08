package com.wul.hlt_client.entity.request;

/**
 * Created by wuliang on 2018/12/25.
 */

public class CommitOrderBO extends BaseRequest {


    public int balancePayStatus;   //是否余额抵扣
    public int orderType;
    public int payChannel;
    public String requireDeliverOn;
    public int isFreeSign;
}
