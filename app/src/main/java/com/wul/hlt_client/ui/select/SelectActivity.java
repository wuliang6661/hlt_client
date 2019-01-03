package com.wul.hlt_client.ui.select;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.mvp.MVPBaseActivity;
import com.wul.hlt_client.widget.MarqueTextView;

import butterknife.BindView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SelectActivity extends MVPBaseActivity<SelectContract.View, SelectPresenter>
        implements SelectContract.View {

    @BindView(R.id.gonggao_text)
    MarqueTextView gonggaoText;
    @BindView(R.id.shop_car_img)
    ImageView shopCarImg;
    @BindView(R.id.shop_car_price)
    TextView shopCarPrice;
    @BindView(R.id.shop_car_button)
    TextView shopCarButton;
    @BindView(R.id.shop_car_layout)
    RelativeLayout shopCarLayout;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.none_layout)
    LinearLayout noneLayout;

    @Override
    protected int getLayout() {
        return R.layout.act_select;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goBack();
    }
}
