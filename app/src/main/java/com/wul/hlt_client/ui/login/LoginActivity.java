package com.wul.hlt_client.ui.login;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.wul.hlt_client.R;
import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.entity.UserBo;
import com.wul.hlt_client.mvp.MVPBaseActivity;
import com.wul.hlt_client.ui.main.MainActivity;
import com.wul.hlt_client.ui.register.RegisterActivity;

import butterknife.BindView;


/**
 * MVPPlugin
 * 登录页面
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter>
        implements LoginContract.View, View.OnClickListener {

    EditText editShop;
    EditText editPassword;
    EditText editPhone;
    CheckBox checkbox;
    TextView btnLogin;
    @BindView(R.id.tx_register)
    TextView txRegister;

    private String strPhone;
    private String strShop;
    private String strPwd;

    private boolean isHaveMe = false;   //记住我，默认false

    @Override
    protected int getLayout() {
        return R.layout.act_login;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        editShop = (EditText) findViewById(R.id.edit_shop);
        editPassword = (EditText) findViewById(R.id.edit_password);
        editPhone = (EditText) findViewById(R.id.edit_phone);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        btnLogin = (TextView) findViewById(R.id.btn_login);
        if (MyApplication.spUtils.getBoolean("isHaveMe", false)) {
            editPhone.setText(MyApplication.spUtils.getString("phone"));
            editPassword.setText(MyApplication.spUtils.getString("pwd"));
            editShop.setText(MyApplication.spUtils.getString("shopNum"));
            checkbox.setChecked(true);
        }
        btnLogin.setOnClickListener(this);
        txRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        strPhone = editPhone.getText().toString().trim();
        strPwd = editPassword.getText().toString().trim();
        strShop = editShop.getText().toString().trim();
        isHaveMe = checkbox.isChecked();
        switch (view.getId()) {
            case R.id.btn_login:
                if (isLogin()) {
                    mPresenter.login(strPhone, strShop, strPwd);
                    showProgress();
                }
                break;
            case R.id.tx_register:
                gotoActivity(RegisterActivity.class, false);
                break;
        }
    }


    /**
     * 判断是否可登录
     */
    private boolean isLogin() {
        if (StringUtils.isEmpty(strShop)) {
            showToast("请输入门店编号！");
            return false;
        }
        if (StringUtils.isEmpty(strPwd)) {
            showToast("请输入密码！");
            return false;
        }
        if (StringUtils.isEmpty(strPhone)) {
            showToast("请输入手机号！");
            return false;
        }
        if (!RegexUtils.isMobileExact(strPhone)) {
            showToast("请输入正确手机号！");
            return false;
        }
        return true;
    }

    @Override
    public void onRequestError(String msg) {
        stopProgress();
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void loginSuress(UserBo userBo) {
        stopProgress();
        if (isHaveMe) {
            MyApplication.spUtils.put("phone", strPhone);
            MyApplication.spUtils.put("pwd", strPwd);
            MyApplication.spUtils.put("shopNum", strShop);
            MyApplication.spUtils.put("isHaveMe", true);
        } else {
            MyApplication.spUtils.clear();
        }
        MyApplication.spUtils.put("token", userBo.getToken());
        MyApplication.token = userBo.getToken();
        gotoActivity(MainActivity.class, true);
    }
}
