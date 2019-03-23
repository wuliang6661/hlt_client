package com.wul.hlt_client.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.wul.hlt_client.R;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.base.BaseActivity;
import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.entity.UserBo;
import com.wul.hlt_client.ui.login.LoginActivity;
import com.wul.hlt_client.ui.main.MainActivity;

import rx.Subscriber;


public class SplashAct extends BaseActivity {


    private String strPhone;
    private String strPwd;
    private String strShopNum;


    @Override
    protected int getLayout() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.act_splash;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (MyApplication.spUtils.getBoolean("isHaveMe", false)) {
            strPhone = MyApplication.spUtils.getString("phone");
            strPwd = MyApplication.spUtils.getString("pwd");
            strShopNum = MyApplication.spUtils.getString("shopNum");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    login(strPhone, strPwd, strShopNum);
                }
            }, 2000);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    gotoActivity(LoginActivity.class, true);
                }
            }, 2000);
        }
    }


    public void login(String phone, String shopNum, String password) {
        HttpServiceIml.login(shopNum, phone, password).subscribe(new Subscriber<UserBo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                gotoActivity(LoginActivity.class, true);
            }

            @Override
            public void onNext(UserBo userBo) {
                MyApplication.spUtils.put("pwd", strPwd);
                MyApplication.spUtils.put("shopNum", strShopNum);
                MyApplication.spUtils.put("isHaveMe", true);
                MyApplication.spUtils.put("token", userBo.getToken());
                MyApplication.token = userBo.getToken();
                gotoActivity(MainActivity.class, true);
            }
        });
    }
}
