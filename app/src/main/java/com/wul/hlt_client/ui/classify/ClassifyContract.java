package com.wul.hlt_client.ui.classify;

import android.content.Context;

import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.ClassifyBO;
import com.wul.hlt_client.entity.ShopBO;
import com.wul.hlt_client.entity.XianShiBO;
import com.wul.hlt_client.mvp.BasePresenter;
import com.wul.hlt_client.mvp.BaseRequestView;
import com.wul.hlt_client.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ClassifyContract {
    interface View extends BaseRequestView {

        void getCityGongGao(List<CityGongGao> cityGongGaos);

        void getClassify(List<ClassifyBO> list);

        void getChildClassify(List<ClassifyBO> list);

        void getXianshiList(XianShiBO shopBOS);

    }

    interface Presenter extends BasePresenter<View> {

    }
}
