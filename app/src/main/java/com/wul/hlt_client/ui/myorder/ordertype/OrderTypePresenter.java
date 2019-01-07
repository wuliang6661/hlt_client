package com.wul.hlt_client.ui.myorder.ordertype;

import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.entity.OrderDayBo;
import com.wul.hlt_client.entity.OrderMonthBO;
import com.wul.hlt_client.entity.request.ScreenBO;
import com.wul.hlt_client.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderTypePresenter extends BasePresenterImpl<OrderTypeContract.View>
        implements OrderTypeContract.Presenter {


    public void getMyOrderList(ScreenBO screenBO) {
        HttpServiceIml.getMyOrderList(screenBO).subscribe(new HttpResultSubscriber<OrderDayBo>() {
            @Override
            public void onSuccess(OrderDayBo s) {
                if (mView != null) {
                    mView.getOrderListDay(s);
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


    public void getMyOrderByMonth(ScreenBO screenBO) {
        HttpServiceIml.getMyOrderListByMonth(screenBO).subscribe(new HttpResultSubscriber<OrderMonthBO>() {
            @Override
            public void onSuccess(OrderMonthBO orderMonthBO) {
                if (mView != null) {
                    mView.getOrderListMonth(orderMonthBO);
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
