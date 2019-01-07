package com.wul.hlt_client.ui.myorder.paytype;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.mvp.MVPBaseFragment;
import com.wul.hlt_client.ui.myorder.ScreenPopWindow;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PayTypeFragment extends MVPBaseFragment<PayTypeContract.View, PayTypePresenter>
        implements PayTypeContract.View, View.OnClickListener {

    @BindView(R.id.order_type_zhengdan)
    TextView orderTypeZhengdan;
    @BindView(R.id.order_type_wancheng)
    TextView orderTypeWancheng;
    @BindView(R.id.order_type_time)
    TextView orderTypeTime;
    @BindView(R.id.all_price_buttom)
    TextView allPriceButtom;
    @BindView(R.id.shop_car_button)
    TextView shopCarButton;
    @BindView(R.id.all_price_layout)
    RelativeLayout allPriceLayout;
    @BindView(R.id.recycle)
    RecyclerView recycle;

    Unbinder unbinder;

    private String[] zhengdans = new String[]{"正单", "补单", "全部"};
    private String[] zhifus = new String[]{"未支付", "已支付", "全部"};
    private String[] times = new String[]{"按日显示", "按周显示", "按月显示"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_order_type, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        orderTypeWancheng.setText("未完成");

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager);

        orderTypeZhengdan.setOnClickListener(this);
        orderTypeWancheng.setOnClickListener(this);
        orderTypeTime.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_type_zhengdan:
                screenZhengdan();
                break;
            case R.id.order_type_wancheng:
                screenWancheng();
                break;
            case R.id.order_type_time:
                screenTime();
                break;
        }
    }


    /**
     * 筛选正单 ，补单
     */
    private void screenZhengdan() {
        ScreenPopWindow popWindow = new ScreenPopWindow(getActivity(), Arrays.asList(zhengdans));
        popWindow.setOnSelecte(new ScreenPopWindow.onSelecte() {
            @Override
            public void onClick(String select, int position) {
                orderTypeZhengdan.setText(select);
            }
        });
        popWindow.showAsDropDown(orderTypeZhengdan);
    }

    /**
     * 筛选完成状态
     */
    private void screenWancheng() {
        ScreenPopWindow popWindow = new ScreenPopWindow(getActivity(), Arrays.asList(zhifus));
        popWindow.setOnSelecte(new ScreenPopWindow.onSelecte() {
            @Override
            public void onClick(String select, int position) {
                orderTypeWancheng.setText(select);
            }
        });
        popWindow.showAsDropDown(orderTypeWancheng);
    }

    /**
     * 筛选时间状态
     */
    private void screenTime() {
        ScreenPopWindow popWindow = new ScreenPopWindow(getActivity(), Arrays.asList(times));
        popWindow.setOnSelecte(new ScreenPopWindow.onSelecte() {
            @Override
            public void onClick(String select, int position) {
                orderTypeTime.setText(select);
            }
        });
        popWindow.showAsDropDown(orderTypeTime);
    }
}
