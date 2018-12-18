package com.wul.hlt_client.ui.register;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.mvp.MVPBaseActivity;

import butterknife.BindView;


/**
 * MVPPlugin
 * 注册页面
 */

public class RegisterActivity extends MVPBaseActivity<RegisterContract.View, RegisterPresenter>
        implements RegisterContract.View {

    @BindView(R.id.edit_shop_name)
    EditText editShopName;
    @BindView(R.id.edit_address)
    EditText editAddress;
    @BindView(R.id.edit_person)
    EditText editPerson;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.yonghu_xieyi)
    TextView yonghuXieyi;
    @BindView(R.id.register_button)
    TextView registerButton;

    @Override
    protected int getLayout() {
        return R.layout.act_register;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
