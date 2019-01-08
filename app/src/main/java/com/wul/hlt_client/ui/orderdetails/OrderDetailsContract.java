package com.wul.hlt_client.ui.orderdetails;

import com.wul.hlt_client.entity.OrderDetailsBO;
import com.wul.hlt_client.mvp.BasePresenter;
import com.wul.hlt_client.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class OrderDetailsContract {
    interface View extends BaseRequestView {

        void getOrder(OrderDetailsBO orderDetailsBO);

        void goPay(String orderInfo);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
