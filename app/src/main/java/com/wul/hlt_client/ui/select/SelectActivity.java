package com.wul.hlt_client.ui.select;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.wul.hlt_client.R;
import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.XianShiBO;
import com.wul.hlt_client.entity.event.ShopCarRefresh;
import com.wul.hlt_client.mvp.MVPBaseActivity;
import com.wul.hlt_client.ui.ShopAdapter;
import com.wul.hlt_client.widget.MarqueTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SelectActivity extends MVPBaseActivity<SelectContract.View, SelectPresenter>
        implements SelectContract.View, View.OnClickListener {

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
    @BindView(R.id.txt_select)
    TextView txtSelect;
    @BindView(R.id.edit_shop)
    EditText editShop;

    @Override
    protected int getLayout() {
        return R.layout.act_select;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
        goBack();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager);
        initShopCar();
        txtSelect.setOnClickListener(this);
        mPresenter.getCityGongGao();
    }

    @Override
    public void getCityGongGao(List<CityGongGao> cityGongGaos) {
        StringBuilder buffer = new StringBuilder();
        for (CityGongGao cityGongGao : cityGongGaos) {
            buffer.append(cityGongGao.getContent()).append("                      ");
        }
        gonggaoText.setText(buffer.toString());
    }

    @Override
    public void getSeachList(XianShiBO xianShiBO) {
        if (xianShiBO.getList() == null || xianShiBO.getList().size() == 0) {
            recycle.setVisibility(View.GONE);
            noneLayout.setVisibility(View.VISIBLE);
        } else {
            recycle.setVisibility(View.VISIBLE);
            noneLayout.setVisibility(View.GONE);
            ShopAdapter adapter = new ShopAdapter(this, xianShiBO.getList(), MyApplication.shopCarBO);
            recycle.setAdapter(adapter);
        }
    }

    @Override
    public void onRequestError(String msg) {
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ShopCarRefresh refresh) {
        initShopCar();
    }


    /**
     * 初始化购物车
     */
    private void initShopCar() {
        if (MyApplication.shopCarBO == null ||
                MyApplication.shopCarBO.getShoppingCartList() == null ||
                MyApplication.shopCarBO.getShoppingCartList().size() == 0) {
            shopCarImg.setImageResource(R.drawable.shop_car_notice);
            shopCarPrice.setText("购物车是空的！");
            shopCarPrice.setTextColor(Color.parseColor("#999999"));
            shopCarButton.setEnabled(false);
        } else {
            shopCarImg.setImageResource(R.drawable.shop_car_img_blue);
            shopCarPrice.setText("¥ " + MyApplication.shopCarBO.getAmount());
            shopCarPrice.setTextColor(Color.parseColor("#F5142F"));
            shopCarButton.setEnabled(true);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_select:
                String shopName = editShop.getText().toString().trim();
                if (!StringUtils.isEmpty(shopName)) {
                    mPresenter.searchName(shopName);
                }
                break;
        }
    }
}
