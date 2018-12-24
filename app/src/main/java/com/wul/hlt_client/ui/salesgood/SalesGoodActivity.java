package com.wul.hlt_client.ui.salesgood;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.XianShiBO;
import com.wul.hlt_client.entity.event.ShopCarRefresh;
import com.wul.hlt_client.mvp.MVPBaseActivity;
import com.wul.hlt_client.ui.DowmTimer;
import com.wul.hlt_client.ui.ShopAdapter;
import com.wul.hlt_client.ui.ordercommit.OrderCommitActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Timer;

import butterknife.BindView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SalesGoodActivity extends MVPBaseActivity<SalesGoodContract.View, SalesGoodPresenter>
        implements SalesGoodContract.View {

    @BindView(R.id.shop_car_img)
    ImageView shopCarImg;
    @BindView(R.id.shop_car_price)
    TextView shopCarPrice;
    @BindView(R.id.shop_car_button)
    TextView shopCarButton;
    @BindView(R.id.shop_car_layout)
    RelativeLayout shopCarLayout;
    @BindView(R.id.gonggao_text)
    TextView gonggaoText;
    @BindView(R.id.recycle)
    RecyclerView recycle;

    Timer timer;
    @BindView(R.id.down_time_text)
    TextView downTimeText;
    @BindView(R.id.down_time)
    TextView downTime;
    @BindView(R.id.tixing_button)
    TextView tixingButton;

    @Override
    protected int getLayout() {
        return R.layout.act_sales_good;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleText("限时促销专场");
        goBack();
        EventBus.getDefault().register(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager);

        mPresenter.getCityGongGao();
        mPresenter.getXianshiList();
        shopCarButton.setOnClickListener(view -> gotoActivity(OrderCommitActivity.class, false));
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ShopCarRefresh refresh) {
        initShopCar();
    }


    @Override
    public void onRequestError(String msg) {
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

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
    public void getXianshiList(XianShiBO shopBOS) {
        ShopAdapter adapter = new ShopAdapter(this, shopBOS.getList(), MyApplication.shopCarBO);
        recycle.setAdapter(adapter);
        timer = new Timer();
        timer.schedule(new DowmTimer(shopBOS.getStartTime(), shopBOS.getEndTime(), handler), 0, 1000);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String time = (String) msg.obj;
            switch (msg.what) {
                case 0x11:
                    downTimeText.setText("距离开始时间还有：");
                    break;
                case 0x22:
                    downTimeText.setText("距离结束时间还有：");
                    break;
            }
            downTime.setText(time);
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        EventBus.getDefault().unregister(this);
    }
}
