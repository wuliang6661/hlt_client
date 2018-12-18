package com.wul.hlt_client.ui.register;


import com.wul.hlt_client.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 * 注册页面
 */

public class RegisterActivity extends MVPBaseActivity<RegisterContract.View, RegisterPresenter>
        implements RegisterContract.View {

    @Override
    protected int getLayout() {
        return 0;
    }
}
