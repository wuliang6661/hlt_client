package com.wul.hlt_client.ui.main.mine;

import com.wul.hlt_client.entity.ShopInfoBO;
import com.wul.hlt_client.mvp.BasePresenter;
import com.wul.hlt_client.mvp.BaseRequestView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MineContract {
    interface View extends BaseRequestView {

        void getShopInfo(ShopInfoBO infoBO);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
