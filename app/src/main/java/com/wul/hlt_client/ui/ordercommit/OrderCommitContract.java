package com.wul.hlt_client.ui.ordercommit;

import com.wul.hlt_client.entity.AddressBO;
import com.wul.hlt_client.entity.MoneyBO;
import com.wul.hlt_client.entity.ShoppingCarBO;
import com.wul.hlt_client.mvp.BasePresenter;
import com.wul.hlt_client.mvp.BaseRequestView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderCommitContract {
    interface View extends BaseRequestView {

        void getAddressInfo(AddressBO addressBO);

        void testSuress();

        void getShoppingList(ShoppingCarBO carBO);

        void getMoney(MoneyBO moneyBO);

        void paySourss(String orderInfo);

    }

    interface Presenter extends BasePresenter<View> {

    }
}
