package com.wul.hlt_client.ui.login;

import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.entity.UserBo;
import com.wul.hlt_client.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {

    @Override
    public void login(String phone, String shopNum, String password) {
        HttpServiceIml.login(shopNum, phone, password).subscribe(new HttpResultSubscriber<UserBo>() {

            @Override
            public void onSuccess(UserBo userBo) {
                if (mView != null) {
                    mView.loginSuress(userBo);
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
