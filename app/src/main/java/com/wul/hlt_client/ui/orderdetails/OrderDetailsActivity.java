package com.wul.hlt_client.ui.orderdetails;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wul.hlt_client.R;
import com.wul.hlt_client.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class OrderDetailsActivity extends MVPBaseActivity<OrderDetailsContract.View, OrderDetailsPresenter>
        implements OrderDetailsContract.View {

    @Override
    protected int getLayout() {
        return R.layout.act_order_details;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
