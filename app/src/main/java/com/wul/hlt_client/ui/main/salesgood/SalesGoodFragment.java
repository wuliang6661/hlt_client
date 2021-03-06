package com.wul.hlt_client.ui.main.salesgood;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.wul.hlt_client.R;
import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.XianShiBO;
import com.wul.hlt_client.entity.event.ShopCarRefresh;
import com.wul.hlt_client.mvp.MVPBaseFragment;
import com.wul.hlt_client.ui.DowmTimer;
import com.wul.hlt_client.ui.ShopAdapter;
import com.wul.hlt_client.ui.ordercommit.OrderCommitActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SalesGoodFragment extends MVPBaseFragment<SalesGoodContract.View, SalesGoodPresenter>
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
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title_text)
    TextView titleText;
    Unbinder unbinder;
    @BindView(R.id.none_layout)
    LinearLayout noneLayout;
    @BindView(R.id.time_layout)
    LinearLayout timeLayout;
    @BindView(R.id.view_line)
    View viewLine;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.act_sales_good, null);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleText.setText("促销秒杀专场");
        back.setVisibility(View.GONE);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager);

        shopCarButton.setOnClickListener(v -> mPresenter.testSkipe());
        initShopCar();
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mPresenter.getCityGongGao();
        mPresenter.getXianshiList();
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
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
            shopCarPrice.setTextSize(13);
            shopCarButton.setEnabled(false);
        } else {
            shopCarImg.setImageResource(R.drawable.shop_car_img_blue);
            shopCarPrice.setText("¥ " + MyApplication.shopCarBO.getAmount());
            shopCarPrice.setTextColor(Color.parseColor("#F5142F"));
            shopCarPrice.setTextSize(20);
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
        if (timer != null) {
            timer.cancel();
            handler.removeCallbacksAndMessages(null);
        }
        if (shopBOS.getStartTime() == 0) {
            downTimeText.setText("暂无促销活动");
            timeLayout.setVisibility(View.GONE);
            viewLine.setVisibility(View.VISIBLE);
        } else {
            timeLayout.setVisibility(View.VISIBLE);
            viewLine.setVisibility(View.GONE);
            timer = new Timer();
            timer.schedule(new DowmTimer(getActivity(), shopBOS.getStartTime(), shopBOS.getEndTime(), handler), 0, 1000);
        }
        ShopAdapter adapter = new ShopAdapter(getActivity(), shopBOS.getList(), MyApplication.shopCarBO);
        recycle.setAdapter(adapter);
    }

    @Override
    public void testSuress() {
        gotoActivity(OrderCommitActivity.class, false);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String time = (String) msg.obj;
            switch (msg.what) {
                case 0x11:
                    downTimeText.setText("距离秒杀活动开始还有：");
                    break;
                case 0x22:
                    downTimeText.setText("距离秒杀活动结束还有：");
                    break;
                case 0x33:
                    mPresenter.getXianshiList();
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                    break;
            }
            downTime.setText(time);
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (timer != null) {
            timer.cancel();
        }
        handler.removeCallbacksAndMessages(null);
        EventBus.getDefault().unregister(this);
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
