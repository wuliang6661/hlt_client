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

import com.wul.hlt_client.R;
import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.entity.ShopCarBO;
import com.wul.hlt_client.mvp.MVPBaseFragment;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_shop_car, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        shopCarRecycle.setLayoutManager(manager);

    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getShopCarList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRequestError(String msg) {
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getShopCar(ShopCarBO carBO) {
        MyApplication.shopCarBO = carBO;
        ShopCarAdapter adapter = new ShopCarAdapter(getActivity(),carBO);
        shopCarRecycle.setAdapter(adapter);
    }
}
