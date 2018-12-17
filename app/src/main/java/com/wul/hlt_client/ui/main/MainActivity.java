package com.wul.hlt_client.ui.main;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;

import com.wul.hlt_client.R;
import com.wul.hlt_client.base.BaseActivity;
import com.wul.hlt_client.util.AppManager;
import com.xyz.tabitem.BottmTabItem;

import butterknife.Bind;


/**
 * 主页面
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.main1)
    BottmTabItem main1;
    @Bind(R.id.main2)
    BottmTabItem main2;
    @Bind(R.id.main3)
    BottmTabItem main3;
    @Bind(R.id.main4)
    BottmTabItem main4;

//    StudyFragment fragment1;    //学习
//    ClassLibraayFragment fragment2;   //课程库
//    DiscoverFragment fragment3;     //发现

    private BottmTabItem[] buttms;

    @Override
    protected int getLayout() {
        return R.layout.act_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buttms = new BottmTabItem[]{main1, main2, main3, main4};
//        fragment1 = new StudyFragment();
//        fragment2 = new ClassLibraayFragment();
//        fragment3 = new DiscoverFragment();
//        goToFragment(fragment1);
        main1.setOnClickListener(this);
        main2.setOnClickListener(this);
        main3.setOnClickListener(this);
        main4.setOnClickListener(this);
//        getPermission();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.main1:
//                goToFragment(fragment1);
//                setButtom(0);
//                break;
//            case R.id.main2:
//                goToFragment(fragment2);
//                setButtom(1);
//                break;
//            case R.id.main3:
//                goToFragment(fragment3);
//                setButtom(2);
//                break;
//            case R.id.main4:
//                setButtom(3);
//                break;
        }
    }

    /**
     * 设置底部按钮显示
     */
    private void setButtom(int position) {
        for (int i = 0; i < buttms.length; i++) {
            if (position == i) {
                buttms[i].setSelectState(true);
            } else {
                buttms[i].setSelectState(false);
            }
        }
    }


    /**
     * 检查定位权限
     */
    private void getPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull
            int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 1) {   //授权成功
//        }
    }

    /**
     * 用户拒绝权限，重新申请
     */
    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission) ||
                super.shouldShowRequestPermissionRationale(permission);
    }


    private Fragment mContent = null;

    /**
     * 修改显示的内容 不会重新加载
     **/
    public void goToFragment(Fragment to) {
        if (mContent != to) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                if (mContent != null)
                    transaction.hide(mContent).add(R.id.fragment_container, to).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
                else
                    transaction.add(R.id.fragment_container, to).commitAllowingStateLoss();
            } else {
                if (mContent != null)
                    transaction.hide(mContent).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
                else
                    transaction.show(to).commitAllowingStateLoss();
            }
            mContent = to;
        }
    }


    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    showToast("再按一次退出程序");
                    firstTime = secondTime;
                    return true;
                } else {
//                    MyApplication.SESSIONID = null;
                    AppManager.getAppManager().finishAllActivity();
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
}
