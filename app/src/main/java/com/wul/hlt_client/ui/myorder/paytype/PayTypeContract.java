package com.wul.hlt_client.ui.myorder.paytype;

import android.content.Context;

import com.wul.hlt_client.entity.OrderDayBo;
import com.wul.hlt_client.entity.OrderMonthBO;
import com.wul.hlt_client.mvp.BasePresenter;
import com.wul.hlt_client.mvp.BaseRequestView;
import com.wul.hlt_client.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PayTypeContract {
    interface View extends BaseRequestView {

        void getOrderListDay(OrderDayBo orderDayBo);

        void getOrderListMonth(OrderMonthBO orderMonthBO);

        void getSelectMoney(String money);

        void goPay(String orderInfo);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
