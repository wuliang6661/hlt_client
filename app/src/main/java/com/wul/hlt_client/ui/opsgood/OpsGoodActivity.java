package com.wul.hlt_client.ui.opsgood;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.ShopBO;
import com.wul.hlt_client.entity.event.FinishEvent;
import com.wul.hlt_client.entity.event.ShopCarRefresh;
import com.wul.hlt_client.mvp.MVPBaseFragment;
import com.wul.hlt_client.ui.ShopAdapter;
import com.wul.hlt_client.ui.ordercommit.OrderCommitActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * MVPPlugin
 * 常用清单列表
 */

public class OpsGoodActivity extends MVPBaseFragment<OpsGoodContract.View, OpsGoodPresenter>
        implements OpsGoodContract.View {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.gonggao_text)
    TextView gonggaoText;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.shop_car_img)
    ImageView shopCarImg;
    @BindView(R.id.shop_car_price)
    TextView shopCarPrice;
    @BindView(R.id.shop_car_button)
    TextView shopCarButton;

    private ShopAdapter adapter;
    Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.act_ops_good, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EventBus.getDefault().register(this);
        titleText.setText("常用清单");
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager);

        mPresenter.getChangyongList();
        mPresenter.getCityGongGao();
        shopCarButton.setOnClickListener(v -> mPresenter.testSkipe());
        back.setOnClickListener(v -> pop());
        initShopCar();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FinishEvent event) {
        pop();
    }

    @Override
    public void onRequestError(String msg) {
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void getOpsShop(List<ShopBO> list) {
        adapter = new ShopAdapter(getActivity(), list, MyApplication.shopCarBO);
        recycle.setAdapter(adapter);
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
    public void testSuress() {
        gotoActivity(OrderCommitActivity.class, false);
    }
}
