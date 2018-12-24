package com.wul.hlt_client.ui.ordershop;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.mvp.MVPBaseActivity;

import butterknife.BindView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderShopActivity extends MVPBaseActivity<OrderShopContract.View, OrderShopPresenter>
        implements OrderShopContract.View {

    @BindView(R.id.recycle)
    RecyclerView recycle;

    @Override
    protected int getLayout() {
        return R.layout.act_order_shop;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goBack();
        setTitleText("商品信息");

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager);
    }
}
