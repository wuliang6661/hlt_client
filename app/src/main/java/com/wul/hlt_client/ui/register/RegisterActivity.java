package com.wul.hlt_client.ui.register;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wul.hlt_client.R;
import com.wul.hlt_client.entity.CityBO;
import com.wul.hlt_client.entity.CityRegionBO;
import com.wul.hlt_client.entity.request.RegisterBO;
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
    List<CityRegionBO> regionBOS;
    CityBO selectCity;
    CityRegionBO selectCityRegionBO;


    private String strShopName;
    private String strAddress;
    private String strPerson;
    private String strPhone;

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
        registerButton.setOnClickListener(this);
        mPresenter.getCity();
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
        this.cityBOS = cityBOS;
    }

    @Override
    public void getRegionByCity(List<CityRegionBO> regionBOS) {
        this.regionBOS = regionBOS;
    }

    @Override
    public void registerSuress() {
        ToastUtils.showLong("注册成功！请等待管理员联系获取登录账号！");
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.city:
                if (cityBOS == null || cityBOS.size() == 0) {
                    mPresenter.getCity();
                    return;
                }
                CityPopWindow popWindow = new CityPopWindow(this, cityBOS);
                popWindow.setOnSelecte(cityBO -> {
                    selectCity = cityBO;
                    popWindow.dismiss();
                    mPresenter.getRegionByCity(selectCity.getId());
                    city.setText(selectCity.getCityName());
                });
                popWindow.showAsDropDown(city);
                break;
            case R.id.city_qu:
                if (selectCity == null) {
                    showToast("请先选择城市！");
                    return;
                }
                if (regionBOS == null || regionBOS.size() == 0) {
                    mPresenter.getRegionByCity(selectCity.getId());
                    return;
                }
                RegionPopWindow popWindow1 = new RegionPopWindow(this, regionBOS);
                popWindow1.setOnSelecte(cityBO -> {
                    selectCityRegionBO = cityBO;
                    popWindow1.dismiss();
                    cityQu.setText(selectCityRegionBO.getRegionName());
                });
                popWindow1.showAsDropDown(cityQu, -20, 0);
                break;
            case R.id.register_button:
                strShopName = editShopName.getText().toString().trim();
                strAddress = editAddress.getText().toString().trim();
                strPerson = editPerson.getText().toString().trim();
                strPhone = editPhone.getText().toString().trim();
                if (isRegister()) {
                    RegisterBO registerBO = new RegisterBO();
                    registerBO.setAddress(strAddress);
                    registerBO.setCityId(selectCityRegionBO.getCityId() + "");
                    registerBO.setRegionId(selectCityRegionBO.getAreaId() + "");
                    registerBO.setContact(strPerson);
                    registerBO.setContactPhone(strPhone);
                    registerBO.setName(strShopName);
                    mPresenter.registerUser(registerBO);
                }
                break;
        }
    }


    /**
     * 判断是否可注册
     */
    private boolean isRegister() {
        if (selectCity == null) {
            showToast("请选择城市！");
            return false;
        }
        if (selectCityRegionBO == null) {
            showToast("请选择城市区域！");
            return false;
        }
        if (StringUtils.isEmpty(strShopName)) {
            showToast("请输入门店名称！");
            return false;
        }
        if (StringUtils.isEmpty(strAddress)) {
            showToast("请输入门店详细地址！");
            return false;
        }
        if (StringUtils.isEmpty(strPerson)) {
            showToast("请输入联系人！");
            return false;
        }
        if (StringUtils.isEmpty(strPhone)) {
            showToast("请输入联系人电话！");
            return false;
        }
        if (!checkbox.isChecked()) {
            showToast("请同意用户协议！");
            return false;
        }
        return true;
    }


}
