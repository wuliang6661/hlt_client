package com.wul.hlt_client.ui.salesgood;

import android.content.Context;

import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.ShopBO;
import com.wul.hlt_client.mvp.BasePresenter;
import com.wul.hlt_client.mvp.BaseRequestView;
import com.wul.hlt_client.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SalesGoodContract {
    interface View extends BaseRequestView {

        void getCityGongGao(List<CityGongGao> list);

        void getXianshiList(List<ShopBO> shopBOS);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
