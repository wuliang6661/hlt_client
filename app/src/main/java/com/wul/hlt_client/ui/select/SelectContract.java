package com.wul.hlt_client.ui.select;

import android.content.Context;

import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.XianShiBO;
import com.wul.hlt_client.mvp.BasePresenter;
import com.wul.hlt_client.mvp.BaseRequestView;
import com.wul.hlt_client.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SelectContract {
    interface View extends BaseRequestView {

        void getCityGongGao(List<CityGongGao> cityGongGaos);

        void getSeachList(XianShiBO xianShiBO);

        void testSuress();

    }

    interface Presenter extends BasePresenter<View> {

    }
}
