package com.wul.hlt_client.ui.main.mine;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.mvp.MVPBaseFragment;
import com.wul.hlt_client.ui.SettingActivty;

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
    ImageView settingImg;
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


        }

    }
}
