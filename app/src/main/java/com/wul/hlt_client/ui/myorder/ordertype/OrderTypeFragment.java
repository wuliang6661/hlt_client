package com.wul.hlt_client.ui.myorder.ordertype;


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
import com.wul.hlt_client.entity.request.ScreenBO;
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

public class OrderTypeFragment extends MVPBaseFragment<OrderTypeContract.View, OrderTypePresenter>
        implements OrderTypeContract.View, View.OnClickListener {

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
    private String[] wanchengs = new String[]{"已接单", "已完成", "已终止"};
    private String[] wanchengs1 = new String[]{"待接单", "已接单", "已完成", "已终止"};
    private String[] times = new String[]{"按日显示", "按周显示", "按月显示"};

    private String orderTypes = "0";  //默认正单 0是正单， 1是补单   0,1是全部
    private int displayType = 1;   //默认按日显示  1是日  2是周  3是月
    private String statusIds = "1";    // 默认已接单   1是已接单 ，2是已完成  3是已终止  0是待接单（只有补单状态才有）


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

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager);

        orderTypeZhengdan.setOnClickListener(this);
        orderTypeWancheng.setOnClickListener(this);
        orderTypeTime.setOnClickListener(this);
        syncHttp();
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
        popWindow.setOnSelecte((select, position) -> {
            orderTypeZhengdan.setText(select);
            if (position == 2) {
                orderTypes = "0,1";
            } else {
                orderTypes = position + "";
            }
            syncHttp();
        });
        popWindow.showAsDropDown(orderTypeZhengdan);
    }

    /**
     * 筛选完成状态
     */
    private void screenWancheng() {

        ScreenPopWindow popWindow = new ScreenPopWindow(getActivity(), Arrays.asList(wanchengs));
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
        popWindow.setOnSelecte((select, position) -> {
            orderTypeTime.setText(select);
            displayType = position + 1;
            syncHttp();
        });
        popWindow.showAsDropDown(orderTypeTime);
    }

    @Override
    public void onRequestError(String msg) {
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    private void syncHttp() {
        ScreenBO screenBO = new ScreenBO();
        screenBO.setDisplayType(displayType);
        screenBO.setOrderTypes(orderTypes);
        screenBO.setStatusIds(statusIds);
        mPresenter.getMyOrderList(screenBO);
    }

}
