package com.wul.hlt_client.ui.myorder.ordertype;

import com.wul.hlt_client.entity.OrderDayBo;
import com.wul.hlt_client.entity.OrderMonthBO;
import com.wul.hlt_client.mvp.BasePresenter;
import com.wul.hlt_client.mvp.BaseRequestView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderTypeContract {
    interface View extends BaseRequestView {

        void getOrderListDay(OrderDayBo orderDayBo);

        void getOrderListMonth(OrderMonthBO orderMonthBO);

        void getSelectMoney(String money);

        void goPay(String orderInfo);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
