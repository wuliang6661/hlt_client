package com.wul.hlt_client.ui.main.mine;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.wul.hlt_client.R;
import com.wul.hlt_client.entity.ShopInfoBO;
import com.wul.hlt_client.mvp.MVPBaseFragment;
import com.wul.hlt_client.ui.SettingActivty;
import com.wul.hlt_client.ui.ShopInfoActivty;
import com.wul.hlt_client.ui.ZiZhiActivity;
import com.wul.hlt_client.ui.myorder.MyOrderActivity;
import com.wul.hlt_client.ui.tousu.TousuActivity;
import com.wul.hlt_client.util.PhoneUtils;
import com.wul.hlt_client.widget.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MineFragment extends MVPBaseFragment<MineContract.View, MinePresenter>
        implements MineContract.View, View.OnClickListener {


    @BindView(R.id.setting_img)
    LinearLayout settingImg;
    @BindView(R.id.shop_num)
    TextView shopNum;
    @BindView(R.id.shop_type)
    TextView shopType;
    @BindView(R.id.shop_person)
    TextView shopPerson;
    @BindView(R.id.my_order)
    LinearLayout myOrder;
    @BindView(R.id.shop_message)
    LinearLayout shopMessage;
    @BindView(R.id.tousu_layout)
    LinearLayout tousuLayout;
    @BindView(R.id.zizhi_layout)
    LinearLayout zizhiLayout;
    @BindView(R.id.yue_price)
    TextView yuePrice;
    @BindView(R.id.phone_num)
    TextView phoneNum;
    @BindView(R.id.phone_layout)
    LinearLayout phoneLayout;

    Unbinder unbinder;

    ShopInfoBO infoBO;
    private boolean goinfo = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fra_mine, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        settingImg.setOnClickListener(this);
        myOrder.setOnClickListener(this);
        shopMessage.setOnClickListener(this);
        tousuLayout.setOnClickListener(this);
        zizhiLayout.setOnClickListener(this);
        phoneLayout.setOnClickListener(this);
        phoneNum.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mPresenter.getShopInfo();
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_img:
                gotoActivity(SettingActivty.class, false);
                break;
            case R.id.my_order:   //我的订单
                gotoActivity(MyOrderActivity.class, false);
                break;
            case R.id.shop_message:   //门店信息
                if (infoBO != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("info", infoBO);
                    gotoActivity(ShopInfoActivty.class, bundle, false);
                } else {
                    goinfo = true;
                    mPresenter.getShopInfo();
                }
                break;
            case R.id.tousu_layout:   //投诉建议
                gotoActivity(TousuActivity.class, false);
                break;
            case R.id.zizhi_layout:   //好菜通资质
                gotoActivity(ZiZhiActivity.class, false);
                break;
            case R.id.phone_layout:    //联系客服
                new AlertDialog(getActivity()).builder().setGone().setTitle("拨打")
                        .setMsg(phoneNum.getText().toString().trim())
                        .setCancelable(false)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", v1 -> {
                            PhoneUtils.callPhone(phoneNum.getText().toString().trim());
                        }).show();
                break;
        }

    }

    @Override
    public void onRequestError(String msg) {
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getShopInfo(ShopInfoBO infoBO) {
        this.infoBO = infoBO;
        if (goinfo) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("info", infoBO);
            gotoActivity(ShopInfoActivty.class, bundle, false);
            goinfo = false;
        }
        shopNum.setText("门店编号：" + infoBO.getNumber());
        shopPerson.setText("登录用户：" + infoBO.getContact());
        yuePrice.setText("¥ " + infoBO.getBalance());
        phoneNum.setText(infoBO.getCustomerServicePhone());
        if (infoBO.getIsConfirmed() == 0) {
            shopType.setText("门店状态：未确认");
        } else {
            shopType.setText("门店状态：已确认");
        }
    }


    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarColor(R.color.green_color)
                .statusBarDarkFont(true).keyboardEnable(true).init();   //解决虚拟按键与状态栏沉浸冲突
    }

    private boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

}
