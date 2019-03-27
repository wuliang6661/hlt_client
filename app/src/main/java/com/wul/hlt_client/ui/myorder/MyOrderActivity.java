package com.wul.hlt_client.ui.myorder;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wul.hlt_client.R;
import com.wul.hlt_client.mvp.MVPBaseActivity;
import com.wul.hlt_client.ui.FragmentPaerAdapter;
import com.wul.hlt_client.ui.myorder.ordertype.OrderTypeFragment;
import com.wul.hlt_client.ui.myorder.paytype.PayTypeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyOrderActivity extends MVPBaseActivity<MyOrderContract.View, MyOrderPresenter>
        implements MyOrderContract.View {

    @BindView(R.id.radio_dingdan)
    RadioButton radioDingdan;
    @BindView(R.id.radio_zhifu)
    RadioButton radioZhifu;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private int radioType = 0;    //默认订单状态

    @Override
    protected int getLayout() {
        return R.layout.act_myorder;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goBack();
        setTitleText("我的订单");

        OrderTypeFragment fragment = new OrderTypeFragment();
        PayTypeFragment fragment1 = new PayTypeFragment();
        List<Fragment> fragments = new ArrayList<>();
//        fragments.add(fragment);
        fragments.add(fragment1);
        viewPager.setAdapter(new FragmentPaerAdapter(getSupportFragmentManager(), fragments));
        setListener();
        requestPermission();
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
     * 设置监听
     */
    private void setListener() {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radio_dingdan:
                    radioDingdan.setTextColor(Color.parseColor("#ffffff"));
                    radioZhifu.setTextColor(ContextCompat.getColor(MyOrderActivity.this, R.color.zhu_color));
                    radioType = 0;
                    viewPager.setCurrentItem(0);
                    //请求接口
                    break;
                case R.id.radio_zhifu:
                    radioZhifu.setTextColor(Color.parseColor("#ffffff"));
                    radioDingdan.setTextColor(ContextCompat.getColor(MyOrderActivity.this, R.color.zhu_color));
                    viewPager.setCurrentItem(1);
                    break;
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    radioDingdan.setChecked(true);
                } else {
                    radioZhifu.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

}
