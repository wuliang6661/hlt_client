package com.wul.hlt_client.ui.register;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wul.hlt_client.R;
import com.wul.hlt_client.base.BaseActivity;

/**
 * Created by wuliang on 2018/12/30.
 * <p>
 * 用户协议的activity
 */

public class PersonXieyIAct extends BaseActivity {


    @Override
    protected int getLayout() {
        return R.layout.act_person_xieyi;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goBack();
        setTitleText("用户协议");
    }
}
