package com.wul.hlt_client.ui.opsgood;

import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.ShopBO;
import com.wul.hlt_client.mvp.BasePresenter;
import com.wul.hlt_client.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OpsGoodContract {
    interface View extends BaseRequestView {

        void getOpsShop(List<ShopBO> list);

        void getCityGongGao(List<CityGongGao> cityGongGaos);

        void testSuress();
    }

    interface Presenter extends BasePresenter<View> {

    }
}
