package com.wul.hlt_client.ui.orderdetails;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.wul.hlt_client.R;
import com.wul.hlt_client.entity.OrderDetailsBO;
import com.wul.hlt_client.entity.PayResult;
import com.wul.hlt_client.mvp.MVPBaseActivity;
import com.wul.hlt_client.util.AppManager;
import com.wul.hlt_client.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.wul.hlt_client.widget.lgrecycleadapter.LGViewHolder;

import java.util.Map;

import butterknife.BindView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderDetailsActivity extends MVPBaseActivity<OrderDetailsContract.View, OrderDetailsPresenter>
        implements OrderDetailsContract.View, View.OnClickListener {

    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.all_price_buttom)
    TextView allPriceButtom;
    @BindView(R.id.shop_car_button)
    TextView shopCarButton;
    @BindView(R.id.all_price_layout)
    RelativeLayout allPriceLayout;
    @BindView(R.id.order_id)
    TextView orderId;
    @BindView(R.id.order_type)
    TextView orderType;
    @BindView(R.id.pay_type)
    TextView payType;
    @BindView(R.id.address_img)
    ImageView addressImg;
    @BindView(R.id.take_goods_person)
    TextView takeGoodsPerson;
    @BindView(R.id.take_person_phone)
    TextView takePersonPhone;
    @BindView(R.id.take_shop_name)
    TextView takeShopName;
    @BindView(R.id.take_address)
    TextView takeAddress;
    @BindView(R.id.take_time)
    TextView takeTime;
    @BindView(R.id.order_time)
    TextView orderTime;
    @BindView(R.id.kefu_phone)
    TextView kefuPhone;
    @BindView(R.id.good_list)
    RecyclerView goodList;
    @BindView(R.id.order_setting)
    TextView orderSetting;

    private int id;

    private OrderDetailsBO orderDetailsBO;

    @Override
    protected int getLayout() {
        return R.layout.act_order_details;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goBack();
        setTitleText("订单详情");

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        goodList.setLayoutManager(manager);

        id = getIntent().getExtras().getInt("id");
        mPresenter.getOrderDetals(id);

        shopCarButton.setOnClickListener(this);
        orderSetting.setOnClickListener(this);
    }

    @Override
    public void onRequestError(String msg) {
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getOrder(OrderDetailsBO orderDetailsBO) {
        this.orderDetailsBO = orderDetailsBO;
        orderId.setText("订单编号：" + orderDetailsBO.getOrderDisplayId());
        takeGoodsPerson.setText("收货人：" + orderDetailsBO.getConsigneeName());
        takePersonPhone.setText("联系方式：" + orderDetailsBO.getConsigneePhone());
        takeShopName.setText("收货店名：" + orderDetailsBO.getDeliverRestName());
        takeAddress.setText("收货地址：" + orderDetailsBO.getDeliverAddress());
        takeTime.setText(orderDetailsBO.getRequireDeliverOn());
        orderTime.setText(TimeUtils.millis2String(orderDetailsBO.getCreateDate()));
        if (orderDetailsBO.getOrderType() == 0 && orderDetailsBO.getPayStatus() == 0) {
            allPriceLayout.setVisibility(View.VISIBLE);
        } else {
            allPriceLayout.setVisibility(View.GONE);
        }
        allPriceButtom.setText("¥ " + orderDetailsBO.getAmount());
        setAdapter(orderDetailsBO);
//        kefuPhone.setText(orderDetailsBO.get);    //客服电话
        switch ((int) orderDetailsBO.getStatusId()) {
            case 0:    //待接单
                orderType.setText("待接单");
                orderType.setTextColor(Color.parseColor("#FF722B"));
                break;
            case 1:    //已接单
                orderType.setText("已接单");
                orderType.setTextColor(Color.parseColor("#61C95F"));
                break;
            case 2:   //已完成
                orderType.setText("已完成");
                orderType.setTextColor(Color.parseColor("#61C95F"));
                break;
            case 3:     //已终止
                orderType.setText("已终止");
                orderType.setTextColor(Color.parseColor("#CCCCCC"));
                break;
        }
        switch ((int) orderDetailsBO.getPayStatus()) {
            case 0:    //未支付
                payType.setTextColor(Color.parseColor("#F5142F"));
                payType.setText("未支付");
                break;
            case 1:   //已支付
                payType.setTextColor(Color.parseColor("#61C95F"));
                payType.setText("已支付");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shop_car_button:
                mPresenter.combinePay(id);
                break;
            case R.id.order_setting:
                showTuiKuanDialog();
                break;
        }
    }


    private void setAdapter(OrderDetailsBO orderDetailsBO) {
        LGRecycleViewAdapter<OrderDetailsBO.ProductDetailListBean> adapter =
                new LGRecycleViewAdapter<OrderDetailsBO.ProductDetailListBean>(orderDetailsBO.getProductDetailList()) {
                    @Override
                    public int getLayoutId(int viewType) {
                        return R.layout.item_good;
                    }

                    @Override
                    public void convert(LGViewHolder holder, OrderDetailsBO.ProductDetailListBean o, int position) {
                        holder.setText(R.id.good_name, o.getProductName());
                        holder.setText(R.id.good_price, "¥ " + o.getPrice2() + "元/" + o.getPrice2MeasureUnit());
                        holder.setText(R.id.goods_num, "数量：" + o.getQuantity() + o.getPrice2MeasureUnit());
                        holder.setText(R.id.goods_all_price, "¥ " + o.getAmountOfMoney());
                        holder.setImageUrl(OrderDetailsActivity.this, R.id.good_img, o.getImg());
                    }
                };
        goodList.setAdapter(adapter);
    }


    /**
     * 申请退款
     */
    private void showTuiKuanDialog() {
        // 创建对话框构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_tuikuan, null);
        EditText money = view.findViewById(R.id.edit_money);
        TextView cancle = view.findViewById(R.id.cancle);
        TextView commit = view.findViewById(R.id.commit);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        cancle.setOnClickListener(v -> dialog.dismiss());
        commit.setOnClickListener(v -> {
            String strMoney = money.getText().toString().trim();
            if (StringUtils.isEmpty(strMoney)) {
                showToast("请输入退款金额！");
            } else {
                mPresenter.tuikuan(id, strMoney);
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    public void goPay(String orderInfo) {
        aliPay(orderInfo);
    }


    private void aliPay(String orderInfo) {
        final Runnable payRunnable = () -> {
            PayTask alipay = new PayTask(OrderDetailsActivity.this);
            Map<String, String> result = alipay.payV2(orderInfo, true);
            Log.i("msp", result.toString());
            Message msg = new Message();
            msg.what = 1;
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
                case 1: {
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
