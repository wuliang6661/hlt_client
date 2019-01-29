package com.wul.hlt_client.ui.myorder.paytype;


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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
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
    @BindView(R.id.expand_list)
    ExpandableListView expandList;
    @BindView(R.id.checkbox)
    CheckBox checkbox;

    Unbinder unbinder;
    @BindView(R.id.no_message)
    LinearLayout noMessage;

    private String[] zhengdans = new String[]{"正单", "补单", "全部"};
    private String[] zhifus = new String[]{"未支付", "已支付", "全部"};
    private String[] times = new String[]{"按日显示", "按周显示", "按月显示"};

    private static final int SDK_PAY_FLAG = 1;

    private String orderTypes = "0,1";  //默认正单 0是正单， 1是补单   0,1是全部
    private int displayType = 1;   //默认按日显示  1是日  2是周  3是月
    private String payStatus = "0,1";    // 0是未支付   1是已支付  0,1是全部

    private List<Integer> orderIds;

    RecycleAdapter adapter;
    ExpandListAdapter adapter2;

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

        orderTypeWancheng.setText("全部");

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager);

        orderIds = new ArrayList<>();
        setListener();
        syncHttp();
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
        checkbox.setOnCheckedChangeListener(listener);
    }


    CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                if (displayType == 1) {
                    adapter.selectAll();
                    orderIds = adapter.getIds();
                } else {
                    adapter2.selectAll();
                    orderIds = adapter2.getIds();
                }
            } else {
                orderIds.clear();
                if (displayType == 1) {
                    adapter.setIds(orderIds);
                } else {
                    adapter2.setIds(orderIds);
                }
            }
            syncSelectPrice();
        }
    };


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
            syncHttp();
        });
        popWindow.setWidth(orderTypeZhengdan.getWidth());
        popWindow.showAsDropDown(orderTypeZhengdan);
    }

    /**
     * 筛选完成状态
     */
    private void screenWancheng() {
        ScreenPopWindow popWindow = new ScreenPopWindow(getActivity(), Arrays.asList(zhifus));
        popWindow.setOnSelecte((select, position) -> {
            orderTypeWancheng.setText(select);
            if (position == 2) {
                payStatus = "0,1";
            } else {
                payStatus = position + "";
            }
            syncHttp();
        });
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
        popWindow.setWidth(orderTypeTime.getWidth());
        popWindow.showAsDropDown(orderTypeTime);
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
    public void onSupportVisible() {
        super.onSupportVisible();
        syncHttp();
    }

    /**
     * 获取我的订单列表
     */
    private void syncHttp() {
        ScreenBO screenBO = new ScreenBO();
        screenBO.setDisplayType(displayType);
        screenBO.setOrderTypes(orderTypes);
        screenBO.setPayStatus(payStatus);
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
            checkbox.setChecked(false);
            shopCarButton.setEnabled(false);
        } else {
//            showProgress();
            mPresenter.getSelectOrderMoney(orderIds);
        }
    }


    @Override
    public void getOrderListDay(OrderDayBo orderDayBo) {
        if (orderDayBo.getAddressMyOrderList() == null) {
            orderDayBo.setAddressMyOrderList(new ArrayList<>());
            recycle.setVisibility(View.GONE);
            expandList.setVisibility(View.GONE);
            noMessage.setVisibility(View.VISIBLE);
            return;
        }
        noMessage.setVisibility(View.GONE);
        recycle.setVisibility(View.VISIBLE);
        expandList.setVisibility(View.GONE);
        if (adapter == null) {
            adapter = new RecycleAdapter(getActivity(), orderDayBo.getAddressMyOrderList());
            orderIds = adapter.getIds();
        } else {
            adapter.setData(orderDayBo.getAddressMyOrderList());
        }
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
        syncSelectPrice();
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
        noMessage.setVisibility(View.GONE);
        recycle.setVisibility(View.GONE);
        expandList.setVisibility(View.VISIBLE);
        adapter2 = new ExpandListAdapter(getActivity(), orderMonthBO.getAddressMyOrderList());
        adapter2.setIds(orderIds);
        adapter2.setOnSelector(new ExpandListAdapter.onSelector() {
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
        expandList.setAdapter(adapter2);
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
        checkbox.setOnCheckedChangeListener(null);
        checkbox.setChecked(true);
        checkbox.setOnCheckedChangeListener(listener);
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
                        AppManager.getAppManager().goHome();
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
