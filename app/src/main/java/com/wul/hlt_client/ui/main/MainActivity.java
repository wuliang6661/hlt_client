package com.wul.hlt_client.ui.main;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.wul.hlt_client.R;
import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.base.BaseActivity;
import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.entity.ShopCarBO;
import com.wul.hlt_client.entity.event.FinishEvent;
import com.wul.hlt_client.entity.event.SwithFragment;
import com.wul.hlt_client.ui.NoneFragment1;
import com.wul.hlt_client.ui.NoneFragment2;
import com.wul.hlt_client.ui.NoneFragment3;
import com.wul.hlt_client.ui.NoneFragment4;
import com.wul.hlt_client.util.AppManager;
import com.xyz.tabitem.BottmTabItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * 主页面
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.main1)
    BottmTabItem main1;
    @BindView(R.id.main2)
    BottmTabItem main2;
    @BindView(R.id.main3)
    BottmTabItem main3;
    @BindView(R.id.main4)
    BottmTabItem main4;

    private int selectPosition = 0;

    private BottmTabItem[] buttms;

    private SupportFragment[] mFragments = new SupportFragment[4];

    @Override
    protected int getLayout() {
        return R.layout.act_main_new;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
        buttms = new BottmTabItem[]{main1, main2, main3, main4};
        initFragment();
        main1.setOnClickListener(this);
        main2.setOnClickListener(this);
        main3.setOnClickListener(this);
        main4.setOnClickListener(this);
        getShopCarList();
        requestPermission();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        SupportFragment firstFragment = findFragment(NoneFragment1.class);
        if (firstFragment == null) {
            mFragments[0] = NoneFragment1.newInstance();
            mFragments[1] = new NoneFragment2();
            mFragments[2] = new NoneFragment3();
            mFragments[3] = new NoneFragment4();

            loadMultipleRootFragment(R.id.fragment_container, 0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2],
                    mFragments[3]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用
            mFragments[0] = firstFragment;
            mFragments[1] = findFragment(NoneFragment2.class);
            mFragments[2] = findFragment(NoneFragment3.class);
            mFragments[3] = findFragment(NoneFragment4.class);
        }
    }


//    @Override
//    public void onBackPressedSupport() {
//        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
//            pop();
//        } else {
//            ActivityCompat.finishAfterTransition(this);
//        }
//    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post(new FinishEvent());
        switch (v.getId()) {
            case R.id.main1:
                showHideFragment(mFragments[0], mFragments[selectPosition]);
                selectPosition = 0;
                setButtom(0);
                break;
            case R.id.main2:
                showHideFragment(mFragments[1], mFragments[selectPosition]);
                selectPosition = 1;
                setButtom(1);
                break;
            case R.id.main3:
                showHideFragment(mFragments[2], mFragments[selectPosition]);
                selectPosition = 2;
                setButtom(2);
                break;
            case R.id.main4:
                showHideFragment(mFragments[3], mFragments[selectPosition]);
                selectPosition = 3;
                setButtom(3);
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SwithFragment swithFragment) {
        showHideFragment(mFragments[swithFragment.goFragment], mFragments[selectPosition]);
        selectPosition = swithFragment.goFragment;
        setButtom(swithFragment.goFragment);
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


    private void requestPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 1);

        }
    }

    /**
     * 权限获取回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // 用户取消了权限弹窗
                if (grantResults.length == 0) {
                    return;
                }
                // 用户拒绝了某些权限
                for (int x : grantResults) {
                    if (x == PackageManager.PERMISSION_DENIED) {
                        return;
                    }
                }
            }
        }
    }

    /**
     * 查询购物车商品
     */
    private void getShopCarList() {
        HttpServiceIml.getShopCarList().subscribe(new HttpResultSubscriber<ShopCarBO>() {
            @Override
            public void onSuccess(ShopCarBO s) {
                MyApplication.shopCarBO = s;
            }

            @Override
            public void onFiled(String message) {
                ToastUtils.showShort(message);
            }
        });
    }


    /**
     * 用户拒绝权限，重新申请
     */
    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission) ||
                super.shouldShowRequestPermissionRationale(permission);
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
