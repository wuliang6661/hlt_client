package com.wul.hlt_client.ui.myorder.ordertype;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.LogUtils;
import com.wul.hlt_client.R;
import com.wul.hlt_client.entity.OrderDayBo;
import com.wul.hlt_client.entity.OrderMonthBO;
import com.wul.hlt_client.entity.PayResult;
import com.wul.hlt_client.entity.request.ScreenBO;
import com.wul.hlt_client.mvp.MVPBaseFragment;
import com.wul.hlt_client.ui.myorder.ExpandListAdapter;
import com.wul.hlt_client.ui.myorder.RecycleAdapter;
import com.wul.hlt_client.ui.myorder.ScreenPopWindow;
import com.wul.hlt_client.ui.orderdetails.OrderDetailsActivity;
import com.wul.hlt_client.util.AppManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.expand_list)
    ExpandableListView expandList;
    @BindView(R.id.image1)
    ImageView image1;

    private static final int SDK_PAY_FLAG = 1;
    @BindView(R.id.no_message)
    LinearLayout noMessage;

    private String[] zhengdans = new String[]{"正单", "补单"};
    private String[] times = new String[]{"按日显示", "按周显示", "按月显示"};

    private String orderTypes = "0";  //默认正单 0是正单， 1是补单   0,1是全部
    private int displayType = 1;   //默认按日显示  1是日  2是周  3是月
    private String statusIds = "1";    // 默认已接单   1是已接单 ，2是已完成  3是已终止  0是待接单（只有补单状态才有）

    private List<Integer> orderIds;


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

        orderIds = new ArrayList<>();
        setListener();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void setListener() {
        orderTypeZhengdan.setOnClickListener(this);
        orderTypeWancheng.setOnClickListener(this);
        orderTypeTime.setOnClickListener(this);
        shopCarButton.setOnClickListener(this);
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
            case R.id.shop_car_button:   //去付款
                mPresenter.combinePay(orderIds);
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
            if (position == 0 && "0".equals(statusIds)) {   //如果选择正单则需要把补单的待接单状态更改
                statusIds = "1";
                orderTypeWancheng.setText("已接单");
            }
            syncHttp();
        });
        popWindow.setWidth(orderTypeZhengdan.getWidth());
        popWindow.showAsDropDown(orderTypeZhengdan);
    }

    /**
     * 筛选完成状态
     */
    private void screenWancheng() {
        List<String> types = new ArrayList<>();
        if (!"0".equals(orderTypes)) {   //包含补单
            types.add("待接单");
            types.add("已接单");
            types.add("已完成");
            types.add("已终止");
        } else {
            types.add("已接单");
            types.add("已完成");
            types.add("已终止");
        }
        ScreenPopWindow popWindow = new ScreenPopWindow(getActivity(), types);
        popWindow.setOnSelecte((select, position) -> {
            orderTypeWancheng.setText(select);
            if ("0".equals(orderTypes)) {
                statusIds = position + 1 + "";
            } else {
                statusIds = position + "";
            }
            syncHttp();
        });
        LogUtils.e(orderTypeWancheng.getWidth());
        popWindow.setWidth(orderTypeWancheng.getWidth());
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
        LogUtils.e(orderTypeTime.getWidth());
        popWindow.setWidth(orderTypeTime.getWidth());
        popWindow.showAsDropDown(orderTypeTime);
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        syncHttp();
    }

    @Override
    public void onRequestError(String msg) {
        stopProgress();
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    /**
     * 获取我的订单列表
     */
    private void syncHttp() {
        ScreenBO screenBO = new ScreenBO();
        screenBO.setDisplayType(displayType);
        screenBO.setOrderTypes(orderTypes);
        screenBO.setStatusIds(statusIds);
        if (displayType == 1) {
            mPresenter.getMyOrderList(screenBO);
        } else {
            mPresenter.getMyOrderByMonth(screenBO);
        }
    }


    /**
     * 获取选中的价格
     */
    private void syncSelectPrice() {
        if (orderIds.size() == 0) {
            allPriceButtom.setText("¥ 0.00");
            image1.setImageResource(R.drawable.check_box_nomer);
            shopCarButton.setEnabled(false);
        } else {
            showProgress();
            mPresenter.getSelectOrderMoney(orderIds);
        }
    }


    @Override
    public void getOrderListDay(OrderDayBo orderDayBo) {
        recycle.setVisibility(View.VISIBLE);
        expandList.setVisibility(View.GONE);
        if (orderDayBo.getAddressMyOrderList() == null) {
            orderDayBo.setAddressMyOrderList(new ArrayList<>());
            recycle.setVisibility(View.GONE);
            expandList.setVisibility(View.GONE);
            noMessage.setVisibility(View.VISIBLE);
            return;
        }
        recycle.setVisibility(View.VISIBLE);
        expandList.setVisibility(View.GONE);
        noMessage.setVisibility(View.GONE);
        RecycleAdapter adapter = new RecycleAdapter(getActivity(), "1".equals(orderTypes), orderDayBo.getAddressMyOrderList());
        adapter.setIds(orderIds);
        adapter.setOnSelector(new RecycleAdapter.onSelector() {
            @Override
            public void select(int id) {
                orderIds.add(id);
                adapter.setIds(orderIds);
                syncSelectPrice();
            }

            @Override
            public void cancle(int id) {
                for (int i = 0; i < orderIds.size(); i++) {
                    if (orderIds.get(i) == id) {
                        orderIds.remove(i);
                    }
                }
                adapter.setIds(orderIds);
                syncSelectPrice();
            }
        });
        adapter.setOnItemClickListener(R.id.item_layout, (view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", (int) orderDayBo.getAddressMyOrderList().get(position).getId());
            gotoActivity(OrderDetailsActivity.class, bundle, false);
        });
        recycle.setAdapter(adapter);
    }

    @Override
    public void getOrderListMonth(OrderMonthBO orderMonthBO) {
        if (orderMonthBO.getAddressMyOrderList() == null) {
            orderMonthBO.setAddressMyOrderList(new ArrayList<>());
            recycle.setVisibility(View.GONE);
            expandList.setVisibility(View.GONE);
            noMessage.setVisibility(View.VISIBLE);
            return;
        }
        recycle.setVisibility(View.GONE);
        expandList.setVisibility(View.VISIBLE);
        noMessage.setVisibility(View.GONE);
        ExpandListAdapter adapter = new ExpandListAdapter(getActivity(), "1".equals(orderTypes), orderMonthBO.getAddressMyOrderList());
        adapter.setIds(orderIds);
        adapter.setOnSelector(new ExpandListAdapter.onSelector() {
            @Override
            public void select(int id) {
                orderIds.add(id);
                adapter.setIds(orderIds);
                syncSelectPrice();
            }

            @Override
            public void cancle(int id) {
                for (int i = 0; i < orderIds.size(); i++) {
                    if (orderIds.get(i) == id) {
                        orderIds.remove(i);
                    }
                }
                adapter.setIds(orderIds);
                syncSelectPrice();
            }
        });
        expandList.setAdapter(adapter);
        for (int i = 0; i < orderMonthBO.getAddressMyOrderList().size(); i++) {
            expandList.expandGroup(i);
        }
        expandList.setOnGroupClickListener((expandableListView, view, i, l) -> true);
        expandList.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", (int) orderMonthBO.getAddressMyOrderList().get(groupPosition)
                    .getOrderList().get(childPosition).getId());
            gotoActivity(OrderDetailsActivity.class, bundle, false);
            return true;
        });
    }

    @Override
    public void getSelectMoney(String money) {
        stopProgress();
        allPriceButtom.setText("¥ " + money);
        image1.setImageResource(R.drawable.checked);
        shopCarButton.setEnabled(true);
    }

    @Override
    public void goPay(String orderInfo) {
        aliPay(orderInfo);
    }


    private void aliPay(String orderInfo) {
        final Runnable payRunnable = () -> {
            PayTask alipay = new PayTask(getActivity());
            Map<String, String> result = alipay.payV2(orderInfo, true);
            Log.i("msp", result.toString());
            Message msg = new Message();
            msg.what = SDK_PAY_FLAG;
            msg.obj = result;
            mHandler.sendMessage(msg);
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {    //支付成功
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        showToast("支付成功！");
                        AppManager.getAppManager().goHome();
                    } else {              //支付失败
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showToast("支付失败！");
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

}
