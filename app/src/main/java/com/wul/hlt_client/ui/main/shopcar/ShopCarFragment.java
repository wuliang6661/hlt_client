package com.wul.hlt_client.ui.main.shopcar;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.wul.hlt_client.R;
import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.entity.ShopCarBO;
import com.wul.hlt_client.entity.event.ShopCarRefresh;
import com.wul.hlt_client.mvp.MVPBaseFragment;
import com.wul.hlt_client.ui.classify.ClassifyFragment;
import com.wul.hlt_client.ui.ordercommit.OrderCommitActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 购物车
 */

public class ShopCarFragment extends MVPBaseFragment<ShopCarContract.View, ShopCarPresenter>
        implements ShopCarContract.View {


    @BindView(R.id.clear_car)
    LinearLayout clearCar;
    @BindView(R.id.shop_car_recycle)
    RecyclerView shopCarRecycle;
    @BindView(R.id.shop_car_price)
    TextView shopCarPrice;
    @BindView(R.id.shop_car_button)
    TextView shopCarButton;
    Unbinder unbinder;
    @BindView(R.id.buttom_layout)
    LinearLayout buttomLayout;
    @BindView(R.id.go_shopping)
    TextView goShopping;
    @BindView(R.id.none_layout)
    LinearLayout noneLayout;
    @BindView(R.id.main_layout)
    LinearLayout mainLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_shop_car, null);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        shopCarRecycle.setLayoutManager(manager);

        shopCarButton.setOnClickListener(view1 -> {
//            showProgress();
            mPresenter.testSkipe();
        });
        clearCar.setOnClickListener(v -> {
            showProgress();
            mPresenter.clearShoppingCar();
        });
        goShopping.setOnClickListener(v -> start(ClassifyFragment.getInstanse(0)));
        mPresenter.getShopCarList();
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
//        showProgress();
        mPresenter.getShopCarList();
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ShopCarRefresh refresh) {
        if (MyApplication.shopCarBO.getShoppingCartList() == null || MyApplication.shopCarBO.getShoppingCartList().size() == 0) {
            clearCar.setVisibility(View.GONE);
            buttomLayout.setVisibility(View.GONE);
            mainLayout.setVisibility(View.GONE);
            noneLayout.setVisibility(View.VISIBLE);
        } else {
            clearCar.setVisibility(View.VISIBLE);
            buttomLayout.setVisibility(View.VISIBLE);
            mainLayout.setVisibility(View.VISIBLE);
            noneLayout.setVisibility(View.GONE);
            shopCarPrice.setText("¥ " + MyApplication.shopCarBO.getAmount());
            ShopCarAdapter adapter = new ShopCarAdapter(getActivity(), MyApplication.shopCarBO);
            shopCarRecycle.setAdapter(adapter);
        }
    }


    @Override
    public void onRequestError(String msg) {
        stopProgress();
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getShopCar(ShopCarBO carBO) {
        stopProgress();
        MyApplication.shopCarBO = carBO;
        EventBus.getDefault().post(new ShopCarRefresh());
        if (carBO.getShoppingCartList() == null || carBO.getShoppingCartList().size() == 0) {
            clearCar.setVisibility(View.GONE);
            buttomLayout.setVisibility(View.GONE);
            mainLayout.setVisibility(View.GONE);
            noneLayout.setVisibility(View.VISIBLE);
        } else {
            clearCar.setVisibility(View.VISIBLE);
            buttomLayout.setVisibility(View.VISIBLE);
            mainLayout.setVisibility(View.VISIBLE);
            noneLayout.setVisibility(View.GONE);
            shopCarPrice.setText("¥ " + (StringUtils.isEmpty(carBO.getAmount()) ? "0.00" : carBO.getAmount()));
            ShopCarAdapter adapter = new ShopCarAdapter(getActivity(), carBO);
            shopCarRecycle.setAdapter(adapter);
        }
    }

    @Override
    public void testSuress() {
        stopProgress();
        gotoActivity(OrderCommitActivity.class, false);
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
