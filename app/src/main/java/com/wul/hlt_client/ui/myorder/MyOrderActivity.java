package com.wul.hlt_client.ui.myorder;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wul.hlt_client.R;
import com.wul.hlt_client.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyOrderActivity extends MVPBaseActivity<MyOrderContract.View, MyOrderPresenter>
        implements MyOrderContract.View {

    @Override
    protected int getLayout() {
        return R.layout.act_myorder;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
