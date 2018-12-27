package com.wul.hlt_client.ui.ordercommit;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.makeramen.roundedimageview.RoundedImageView;
import com.wul.hlt_client.R;
import com.wul.hlt_client.base.GlideApp;
import com.wul.hlt_client.entity.AddressBO;
import com.wul.hlt_client.entity.MoneyBO;
import com.wul.hlt_client.entity.PayResult;
import com.wul.hlt_client.entity.ShoppingCarBO;
import com.wul.hlt_client.mvp.MVPBaseActivity;
import com.wul.hlt_client.ui.ordershop.OrderShopActivity;
import com.wul.hlt_client.widget.PayDialog;
import com.wul.hlt_client.widget.TimeDialog;

import java.util.Map;

import butterknife.BindView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderCommitActivity extends MVPBaseActivity<OrderCommitContract.View, OrderCommitPresenter>
        implements OrderCommitContract.View, View.OnClickListener {

    private static final int SDK_PAY_FLAG = 1;

    @BindView(R.id.order_price)
    TextView orderPrice;
    @BindView(R.id.shop_car_button)
    TextView shopCarButton;
    @BindView(R.id.person)
    TextView person;
    @BindView(R.id.person_phone)
    TextView personPhone;
    @BindView(R.id.shop_name)
    TextView shopName;
    @BindView(R.id.shop_address)
    TextView shopAddress;
    @BindView(R.id.dispatching_time)
    TextView dispatchingTime;
    @BindView(R.id.dispatching_time_layout)
    LinearLayout dispatchingTimeLayout;
    @BindView(R.id.pay_type)
    TextView payType;
    @BindView(R.id.pay_layout)
    LinearLayout payLayout;
    @BindView(R.id.good_img1)
    RoundedImageView goodImg1;
    @BindView(R.id.good_img2)
    RoundedImageView goodImg2;
    @BindView(R.id.good_img3)
    RoundedImageView goodImg3;
    @BindView(R.id.good_img4)
    RoundedImageView goodImg4;
    @BindView(R.id.all_good_num)
    TextView allGoodNum;
    @BindView(R.id.good_layout)
    LinearLayout goodLayout;
    @BindView(R.id.dispatching_text)
    TextView dispatchingText;
    @BindView(R.id.dispatching_price)
    TextView dispatchingPrice;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.blance_price)
    TextView blancePrice;
    @BindView(R.id.balance_layout)
    LinearLayout balanceLayout;
    @BindView(R.id.good_price)
    TextView goodPrice;
    @BindView(R.id.mian_view)
    RelativeLayout mianView;

    private int strPayType = 1;   //默认支付宝

    @Override
    protected int getLayout() {
        return R.layout.act_order_commit;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goBack();
        setTitleText("确认订单");

        goodLayout.setOnClickListener(this);
        payLayout.setOnClickListener(this);
        dispatchingTimeLayout.setOnClickListener(this);
        mPresenter.getAddressInfo();
        mPresenter.getShoppingList(0);
        mPresenter.getMoney(0);
        requestPermission();
    }


    private void requestPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 1);

        }
    }

    /**
     * 权限获取回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // 用户取消了权限弹窗
                if (grantResults.length == 0) {
                    return;
                }

                // 用户拒绝了某些权限
                for (int x : grantResults) {
                    if (x == PackageManager.PERMISSION_DENIED) {
                        return;
                    }
                }
            }
        }
    }


    @Override
    public void onRequestError(String msg) {
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getAddressInfo(AddressBO addressBO) {
        person.setText("联系人：" + addressBO.getContact());
        personPhone.setText("联系方式：" + addressBO.getContactPhone());
        shopName.setText("收货店名：" + addressBO.getAddressName());
        shopAddress.setText("联系地址：" + addressBO.getAddress());
    }

    @Override
    public void testSuress() {
        gotoActivity(OrderShopActivity.class, false);
    }

    @Override
    public void getShoppingList(ShoppingCarBO carBO) {
        allGoodNum.setText("共" + carBO.getShoppingCartList().size() + "种商品");
        goodPrice.setText("¥ " + carBO.getAmount());
        int size = carBO.getShoppingCartList().size();
        if (size >= 1) {
            GlideApp.with(this).load(carBO.getShoppingCartList().get(0).getHttpUrl())
                    .placeholder(R.drawable.zhanwei1)
                    .error(R.drawable.zhanwei1)
                    .into(goodImg1);
        }
        if (size >= 2) {
            GlideApp.with(this).load(carBO.getShoppingCartList().get(1).getHttpUrl())
                    .placeholder(R.drawable.zhanwei1)
                    .error(R.drawable.zhanwei1)
                    .into(goodImg2);
        }
        if (size >= 3) {
            GlideApp.with(this).load(carBO.getShoppingCartList().get(2).getHttpUrl())
                    .placeholder(R.drawable.zhanwei1)
                    .error(R.drawable.zhanwei1)
                    .into(goodImg3);
        }
        if (size >= 4) {
            GlideApp.with(this).load(carBO.getShoppingCartList().get(3).getHttpUrl())
                    .placeholder(R.drawable.zhanwei1)
                    .error(R.drawable.zhanwei1)
                    .into(goodImg4);
        }
    }

    @Override
    public void getMoney(MoneyBO moneyBO) {
        blancePrice.setText("余额可抵用" + moneyBO.getBalancePay());
        dispatchingPrice.setText("¥ " + moneyBO.getDistributionFee());
        orderPrice.setText("¥ " + moneyBO.getAmount());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.good_layout:   //进入商品清单
                mPresenter.testSkipe();
                break;
            case R.id.pay_layout:    //支付方式
                PayDialog dialog = new PayDialog(this, strPayType);
                dialog.setOnComitListener(type -> {
                    strPayType = type;
                    switch (type) {
                        case 1:
                            payType.setText("支付宝");
                            break;
                        case 2:
                            payType.setText("货到付款");
                            break;
                    }
                });
                dialog.showAtLocation(mianView, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.dispatching_time_layout:   //配送时间
                TimeDialog dialog1 = new TimeDialog(this);
                dialog1.setOnSelectListener(new TimeDialog.onSelectListener() {
                    @Override
                    public void onCommit(String date) {
                        dispatchingTime.setText(date);
                    }
                });
                dialog1.showAtLocation(mianView, Gravity.BOTTOM, 0, 0);
                break;
        }
    }


    /**
     * 调用支付宝支付
     */
    private void aliPay(String orderInfo) {
        final Runnable payRunnable = () -> {
            PayTask alipay = new PayTask(OrderCommitActivity.this);
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
                    } else {              //支付失败
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
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
