package com.wul.hlt_client.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wul.hlt_client.R;
import com.wul.hlt_client.base.BaseActivity;

public class ZiZhiActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.act_zizhi;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goBack();
        setTitleText("我的资质");
    }
}
