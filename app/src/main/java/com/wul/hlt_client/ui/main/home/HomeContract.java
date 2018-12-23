package com.wul.hlt_client.ui.main.home;

import com.wul.hlt_client.entity.BannerBo;
import com.wul.hlt_client.entity.ClassifyBO;
import com.wul.hlt_client.entity.ShopBO;
import com.wul.hlt_client.entity.XianShiBO;
import com.wul.hlt_client.mvp.BasePresenter;
import com.wul.hlt_client.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HomeContract {
    interface View extends BaseRequestView {

        void getClassify(List<ClassifyBO> list);

        void getBannerList(List<BannerBo> list);

        void getChangyongShop(List<ShopBO> list);

        void getXianshiList(XianShiBO list);

    }

    interface Presenter extends BasePresenter<View> {

    }
}
