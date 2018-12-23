package com.wul.hlt_client.ui.register;

import com.wul.hlt_client.entity.CityBO;
import com.wul.hlt_client.entity.CityRegionBO;
import com.wul.hlt_client.mvp.BasePresenter;
import com.wul.hlt_client.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RegisterContract {
    interface View extends BaseRequestView {

        void getCityList(List<CityBO> cityBOS);

        void getRegionByCity(List<CityRegionBO> regionBOS);

        void registerSuress();
    }

    interface Presenter extends BasePresenter<View> {

    }
}
