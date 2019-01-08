package com.wul.hlt_client.ui.orderdetails;

import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.entity.OrderDetailsBO;
import com.wul.hlt_client.mvp.BasePresenterImpl;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderDetailsPresenter extends BasePresenterImpl<OrderDetailsContract.View>
        implements OrderDetailsContract.Presenter {


    public void getOrderDetals(int id) {
        HttpServiceIml.getOrderDetails(id).subscribe(new HttpResultSubscriber<OrderDetailsBO>() {
            @Override
            public void onSuccess(OrderDetailsBO s) {
                if (mView != null) {
                    mView.getOrder(s);
                }
            }

            @Override
            public void onFiled(String message) {
                if (mView != null) {
                    mView.onRequestError(message);
                }
            }
        });
    }


    /**
     * 合并支付
     */
    public void combinePay(int id) {
        HttpServiceIml.combinePay(id + "").subscribe(new HttpResultSubscriber<String>() {
            @Override
            public void onSuccess(String s) {
                if (mView != null) {
                    mView.goPay(s);
                }
            }

            @Override
            public void onFiled(String message) {
                if (mView != null) {
                    mView.onRequestError(message);
                }
            }
        });
    }

    /**
     * 取消订单
     */
    public void cancleOrder(int id) {
        HttpServiceIml.cancleOrder(id).subscribe(new HttpResultSubscriber<String>() {
            @Override
            public void onSuccess(String s) {
                if (mView != null) {
                    mView.cancleSuress(s);
                }
            }

            @Override
            public void onFiled(String message) {
                if (mView != null) {
                    mView.onRequestError(message);
                }
            }
        });
    }


    /**
     * 申请退款
     */
    public void tuikuan(int id, String money) {
        HttpServiceIml.orderTuiKuan(id, money).subscribe(new HttpResultSubscriber<String>() {
            @Override
            public void onSuccess(String s) {
                if (mView != null) {

                }
            }

            @Override
            public void onFiled(String message) {
                if (mView != null) {
                    mView.onRequestError(message);
                }
            }
        });
    }
}
