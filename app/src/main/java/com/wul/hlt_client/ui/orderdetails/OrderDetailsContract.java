package com.wul.hlt_client.ui.orderdetails;

import com.wul.hlt_client.entity.OrderDetailsBO;
import com.wul.hlt_client.mvp.BasePresenter;
import com.wul.hlt_client.mvp.BaseRequestView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderDetailsContract {
    interface View extends BaseRequestView {

        void getOrder(OrderDetailsBO orderDetailsBO);

        void goPay(String orderInfo);

        void cancleSuress(String message);

        void tuikuanSuress(String message);

        void testTuiKuanSouress();
    }

    interface Presenter extends BasePresenter<View> {

    }
}
