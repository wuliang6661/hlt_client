package com.wul.hlt_client.ui.ordershop;

import com.wul.hlt_client.entity.ShoppingCarBO;
import com.wul.hlt_client.mvp.BasePresenter;
import com.wul.hlt_client.mvp.BaseRequestView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderShopContract {
    interface View extends BaseRequestView {

        void getShopList(ShoppingCarBO carBO);

    }

    interface Presenter extends BasePresenter<View> {

    }
}
