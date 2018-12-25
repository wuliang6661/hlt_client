package com.wul.hlt_client.ui.salesgood;

import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.XianShiBO;
import com.wul.hlt_client.mvp.BasePresenter;
import com.wul.hlt_client.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SalesGoodContract {
    interface View extends BaseRequestView {

        void getCityGongGao(List<CityGongGao> list);

        void getXianshiList(XianShiBO shopBOS);

        void testSuress();
    }

    interface Presenter extends BasePresenter<View> {

    }
}
