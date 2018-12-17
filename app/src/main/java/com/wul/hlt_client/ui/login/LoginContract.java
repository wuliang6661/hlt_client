package com.wul.hlt_client.ui.login;

import com.wul.hlt_client.entity.UserBo;
import com.wul.hlt_client.mvp.BasePresenter;
import com.wul.hlt_client.mvp.BaseRequestView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginContract {
    interface View extends BaseRequestView {

        void loginSuress(UserBo userBo);

    }

    interface Presenter extends BasePresenter<View> {

        void login(String phone, String shopNum, String password);
    }
}
