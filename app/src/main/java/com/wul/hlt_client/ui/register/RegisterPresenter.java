package com.wul.hlt_client.ui.register;

import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.entity.CityBO;
import com.wul.hlt_client.mvp.BasePresenterImpl;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RegisterPresenter extends BasePresenterImpl<RegisterContract.View>
        implements RegisterContract.Presenter {

    public void getCity() {
        HttpServiceIml.getAllCity().subscribe(new HttpResultSubscriber<List<CityBO>>(mView.getContext()) {
            @Override
            public void onSuccess(List<CityBO> s) {
                if (mView != null) {
                    mView.getCityList(s);
                }
            }

            @Override
            public void onFiled(String message) {
                if (mView != null) {
                    mView.onRequestError(message);
                }
            }
        });
    }
}
