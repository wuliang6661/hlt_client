package com.wul.hlt_client.ui.register;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.entity.CityBO;
import com.wul.hlt_client.mvp.MVPBaseActivity;

import java.util.List;

import butterknife.BindView;


/**
 * MVPPlugin
 * 注册页面
 */

public class RegisterActivity extends MVPBaseActivity<RegisterContract.View, RegisterPresenter>
        implements RegisterContract.View, View.OnClickListener {

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
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.city_qu)
    TextView cityQu;

    List<CityBO> cityBOS;

    @Override
    protected int getLayout() {
        return R.layout.act_register;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();

        city.setOnClickListener(this);
        cityQu.setOnClickListener(this);
    }

    @Override
    public void onRequestError(String msg) {
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getCityList(List<CityBO> cityBOS) {
        CityPopWindow popWindow = new CityPopWindow(this, cityBOS);
        popWindow.showAsDropDown(city);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.city:
                mPresenter.getCity();
                break;
            case R.id.city_qu:

                break;
        }
    }
}
