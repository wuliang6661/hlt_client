package com.wul.hlt_client.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.wul.hlt_client.R;
import com.wul.hlt_client.base.BaseActivity;
import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.ui.login.LoginActivity;
import com.wul.hlt_client.util.AppManager;
import com.wul.hlt_client.util.DataCleanManager;
import com.wul.hlt_client.widget.AlertDialog;

import java.io.File;

import butterknife.BindView;

public class SettingActivty extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.cache_size)
    TextView cacheSize;
    @BindView(R.id.cache_layout)
    LinearLayout cacheLayout;
    @BindView(R.id.version_name)
    TextView versionName;
    @BindView(R.id.login_out)
    LinearLayout loginOut;

    @Override
    protected int getLayout() {
        return R.layout.act_setting;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitleText("设置");

        versionName.setText(AppUtils.getAppVersionName());
        File file = new File(this.getCacheDir().getPath());
        try {
            cacheSize.setText(DataCleanManager.getCacheSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        cacheLayout.setOnClickListener(this);
        loginOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_out:
                new AlertDialog(this).builder().setGone().setTitle("请注意")
                        .setMsg("是否确认退出登录？")
                        .setCancelable(false)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", v1 -> {
                            MyApplication.spUtils.remove("pwd");
                            MyApplication.spUtils.remove("phone");
                            gotoActivity(LoginActivity.class, true);
                            AppManager.getAppManager().finishAllActivity();
                        }).show();
                break;
            case R.id.cache_layout:
                new AlertDialog(this).builder().setGone().setTitle("请注意")
                        .setMsg("是否确认清除缓存？")
                        .setCancelable(false)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", v12 -> {
                            DataCleanManager.cleanInternalCache(SettingActivty.this);
                            DataCleanManager.cleanDatabases(SettingActivty.this);
                            DataCleanManager.cleanSharedPreference(SettingActivty.this);
                            showToast("清除成功");
                            cacheSize.setText("0K");
                        }).show();
                break;
        }
    }
}
