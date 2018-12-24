package com.wul.hlt_client.ui.ordercommit;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.wul.hlt_client.R;
import com.wul.hlt_client.mvp.MVPBaseActivity;

import butterknife.BindView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderCommitActivity extends MVPBaseActivity<OrderCommitContract.View, OrderCommitPresenter>
        implements OrderCommitContract.View {

    @BindView(R.id.order_price)
    TextView orderPrice;
    @BindView(R.id.shop_car_button)
    TextView shopCarButton;
    @BindView(R.id.person)
    TextView person;
    @BindView(R.id.person_phone)
    TextView personPhone;
    @BindView(R.id.shop_name)
    TextView shopName;
    @BindView(R.id.shop_address)
    TextView shopAddress;
    @BindView(R.id.dispatching_time)
    TextView dispatchingTime;
    @BindView(R.id.dispatching_time_layout)
    LinearLayout dispatchingTimeLayout;
    @BindView(R.id.pay_type)
    TextView payType;
    @BindView(R.id.pay_layout)
    LinearLayout payLayout;
    @BindView(R.id.good_img1)
    RoundedImageView goodImg1;
    @BindView(R.id.good_img2)
    RoundedImageView goodImg2;
    @BindView(R.id.good_img3)
    RoundedImageView goodImg3;
    @BindView(R.id.good_img4)
    RoundedImageView goodImg4;
    @BindView(R.id.all_good_num)
    TextView allGoodNum;
    @BindView(R.id.good_layout)
    LinearLayout goodLayout;
    @BindView(R.id.dispatching_text)
    TextView dispatchingText;
    @BindView(R.id.dispatching_price)
    TextView dispatchingPrice;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.blance_price)
    TextView blancePrice;
    @BindView(R.id.balance_layout)
    LinearLayout balanceLayout;

    @Override
    protected int getLayout() {
        return R.layout.act_order_commit;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goBack();
        setTitleText("确认订单");


    }
}
