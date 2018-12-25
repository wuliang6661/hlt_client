package com.wul.hlt_client.ui.main.shopcar;

import com.wul.hlt_client.entity.ShopCarBO;
import com.wul.hlt_client.mvp.BasePresenter;
import com.wul.hlt_client.mvp.BaseRequestView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ShopCarContract {
    interface View extends BaseRequestView {

        void getShopCar(ShopCarBO carBO);

        void testSuress();


    }

    interface Presenter extends BasePresenter<View> {

    }
}
