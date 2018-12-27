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

import com.blankj.utilcode.util.ToastUtils;
import com.wul.hlt_client.R;
import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.base.BaseActivity;
import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.entity.ShopCarBO;
import com.wul.hlt_client.ui.classify.ClassifyFragment;
import com.wul.hlt_client.ui.main.home.HomeFragment;
import com.wul.hlt_client.ui.main.shopcar.ShopCarFragment;
import com.wul.hlt_client.util.AppManager;
import com.xyz.tabitem.BottmTabItem;

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

    HomeFragment fragment1;    //学习
    ClassifyFragment fragment2;
    ShopCarFragment fragment3;

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

        buttms = new BottmTabItem[]{main1, main2, main3, main4};
        fragment1 = new HomeFragment();
        fragment2 = new ClassifyFragment();
        fragment3 = new ShopCarFragment();
        initFragment();
        main1.setOnClickListener(this);
        main2.setOnClickListener(this);
        main3.setOnClickListener(this);
        main4.setOnClickListener(this);
//        getPermission();
        getShopCarList();
    }


    /**
     * 初始化fragment
     */
    private void initFragment() {
        SupportFragment firstFragment = findFragment(HomeFragment.class);
        if (firstFragment == null) {
            mFragments[0] = new HomeFragment();
            mFragments[1] = new ClassifyFragment();
            mFragments[2] = new ShopCarFragment();
            //mFragments[3] = ZhihuFourthFragment.newInstance();

            loadMultipleRootFragment(R.id.fragment_container, 0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用
            mFragments[0] = firstFragment;
            mFragments[1] = findFragment(ClassifyFragment.class);
            mFragments[2] = findFragment(ShopCarFragment.class);
            //  mFragments[3] = findFragment(ZhihuFourthFragment.class);
        }
    }


    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main1:
                if (selectPosition != 0) {
                    showHideFragment(mFragments[0], mFragments[selectPosition]);
                    selectPosition = 0;
                    setButtom(0);
                }
                break;
            case R.id.main2:
                if (selectPosition != 1) {
                    showHideFragment(mFragments[1], mFragments[selectPosition]);
                    selectPosition = 1;
                    setButtom(1);
                }
                break;
            case R.id.main3:
                if (selectPosition != 2) {
                    showHideFragment(mFragments[2], mFragments[selectPosition]);
                    selectPosition = 2;
                    setButtom(2);
                }
                break;
            case R.id.main4:
                selectPosition = 3;
                setButtom(3);
                break;
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


    private Fragment mContent = null;

    /**
     * 修改显示的内容 不会重新加载
     **/
    public void goToFragment(Fragment to) {
        if (mContent != to) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
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
