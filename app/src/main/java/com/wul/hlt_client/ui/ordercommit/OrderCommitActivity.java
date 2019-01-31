package com.wul.hlt_client.ui.ordercommit;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.StringUtils;
import com.contrarywind.view.WheelView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.wul.hlt_client.R;
import com.wul.hlt_client.base.GlideApp;
import com.wul.hlt_client.entity.AddressBO;
import com.wul.hlt_client.entity.MoneyBO;
import com.wul.hlt_client.entity.PayResult;
import com.wul.hlt_client.entity.ShoppingCarBO;
import com.wul.hlt_client.entity.request.CommitOrderBO;
import com.wul.hlt_client.entity.request.TestTimeRequest;
import com.wul.hlt_client.mvp.MVPBaseActivity;
import com.wul.hlt_client.ui.myorder.MyOrderActivity;
import com.wul.hlt_client.ui.ordershop.OrderShopActivity;
import com.wul.hlt_client.util.BarUtils;
import com.wul.hlt_client.widget.AlertDialog;
import com.wul.hlt_client.widget.PayDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    private int strPayType = 2;   //默认支付宝   1是货到付款

    private MoneyBO moneyBO;

    TimePickerView pvView;
    TimePickerBuilder builder;

    private String selectTime;    //选择的配送时间
    private Date selectDate = new Date();      //选择的时间 默认为今天

    private int orderType = 0;     //默认按正单计算

    private int PayStatus = 1;    //默认不使用余额

    @Override
    protected int getLayout() {
        return R.layout.act_order_commit;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goBack();
        setTitleText("确认订单");

        //  EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);   //沙箱版测试
        payType.setText("支付宝");
        goodLayout.setOnClickListener(this);
        payLayout.setOnClickListener(this);
        dispatchingTimeLayout.setOnClickListener(this);
        shopCarButton.setOnClickListener(this);
        mPresenter.getAddressInfo();
        mPresenter.getShoppingList(orderType);
        mPresenter.getMoney(orderType, PayStatus);
        setListener();
        requestPermission();
    }

    /**
     * 初始化
     */
    private void setListener() {
        checkbox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                if (strPayType != 2) {
                    showToast("余额抵扣只支持支付宝支付！");
                    checkbox.setChecked(false);
                    PayStatus = 1;
                } else {
                    checkbox.setChecked(true);
                    PayStatus = 0;
                }
            } else {
                PayStatus = 1;
            }
            mPresenter.getMoney(orderType, PayStatus);
        });
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
        Bundle bundle = new Bundle();
        bundle.putInt("orderType", orderType);
        gotoActivity(OrderShopActivity.class, bundle, false);
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
        this.moneyBO = moneyBO;
        blancePrice.setText("余额可抵用" + moneyBO.getBalancePay());
        dispatchingPrice.setText("¥ " + moneyBO.getDistributionFee());
        orderPrice.setText("¥ " + moneyBO.getAmount());
        orderPrice.setText("¥ " + moneyBO.getPayableAmount());
    }

    @Override
    public void paySourss(String orderInfo) {
        if (StringUtils.isEmpty(orderInfo)) {
            if (strPayType == 1) {
                showToast("下单成功！");
                gotoActivity(MyOrderActivity.class, true);
            } else {
                showToast("orderInfo为空！");
            }
        } else {
            aliPay(orderInfo);
        }
    }

    @Override
    public void testTimeSourss(String message) {
        dispatchingTime.setText(selectTime);
        if (isZhengDan()) {
            if (orderType == 1) {
                new AlertDialog(this).builder().setGone().setTitle("请注意")
                        .setMsg("该订单为临时补单\n采购价格将会上调")
                        .setCancelable(false)
                        .setNegativeButton("确定", v12 -> {
                            mPresenter.getShoppingList(orderType);
                            mPresenter.getMoney(orderType, PayStatus);
                            strPayType = 1;
                            payType.setText("货到付款");
                            checkbox.setChecked(false);
                            payLayout.setEnabled(false);
                        }).show();
            } else {
                mPresenter.getShoppingList(orderType);
                mPresenter.getMoney(orderType, PayStatus);
                payLayout.setEnabled(true);
            }
        } else {
            mPresenter.getShoppingList(orderType);
            mPresenter.getMoney(orderType, PayStatus);
            new AlertDialog(this).builder().setGone().setMsg("当前时间不能临时补单\n请重新选择配送时间")
                    .setNegativeButton("确定", null).show();
            dispatchingTime.setText("");
        }
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
                            payType.setText("货到付款");
                            checkbox.setChecked(false);
                            break;
                        case 2:
                            payType.setText("支付宝");
                            break;
                    }
                });
                dialog.showAtLocation(mianView, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.dispatching_time_layout:   //配送时间
                showTimeSelect();
                break;
            case R.id.shop_car_button:
                String time = dispatchingTime.getText().toString().trim();
                if (StringUtils.isEmpty(time)) {
                    showToast("请选择配送时间！");
                    return;
                }
                if (isZhengDan()) {   //可以下单
                    if (orderType == 1 && strPayType == 2) {
                        new AlertDialog(this).builder().setGone().setMsg("临时补单只支持货到付款")
                                .setNegativeButton("确定", null).show();
                        strPayType = 1;
                        payType.setText("货到付款");
                        checkbox.setChecked(false);
                    } else {
                        CommitOrderBO orderBO = new CommitOrderBO();
                        orderBO.balancePayStatus = checkbox.isChecked() ? 0 : 1;
                        orderBO.orderType = orderType;
                        orderBO.payChannel = strPayType;
                        orderBO.requireDeliverOn = selectTime.replaceAll(":00", "").replace(" ", "");
                        mPresenter.commitOrder(orderBO);
                    }
                } else {
                    new AlertDialog(this).builder().setGone().setMsg("当前时间不能临时补单\n请重新选择配送时间")
                            .setNegativeButton("确定", null).show();
                }
                break;
        }
    }


    TextView hourTime;
    private int selectView = 0;   //默认是滑动控件

    /**
     * 显示时间选择器
     */
    @SuppressLint("ClickableViewAccessibility")
    private void showTimeSelect() {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.set(Calendar.DAY_OF_YEAR, endDate.get(Calendar.DAY_OF_YEAR) + 7);   //默认设置可选择7天，可配置

        builder = new TimePickerBuilder(this, (date, v) -> {
            switch (selectView) {
                case 1:
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
                    selectTime = sf.format(date) + " " + hourTime.getText().toString();
                    selectDate = date;
                    dispatchingTime.setText("");
                    TestTimeRequest request = new TestTimeRequest();
                    request.requireDeliverOn = selectTime.replaceAll(":00", "").replace(" ", "");
                    mPresenter.testSkipeTime(request);
                    pvView.dismiss();
                    break;
                default:
                    if (isNow(date)) {
                        hourTime.setText("11:00-15:00点");
                    } else {
                        hourTime.setText("06:00-11:00点");
                    }
                    break;
            }
        });
        builder.setDate(startDate);
        builder.setTimeSelectChangeListener(date -> {
            pvView.returnData();
        });
        builder.setRangDate(startDate, endDate);
        builder.setLayoutRes(R.layout.dialog_time, v -> {
            TextView commit = v.findViewById(R.id.commit);
            WheelView year = v.findViewById(R.id.year);
            WheelView month = v.findViewById(R.id.month);
            WheelView day = v.findViewById(R.id.day);
            LinearLayout layout = v.findViewById(R.id.dialog_layout);
            layout.setOnTouchListener((v14, event) -> {
                hourTime = v14.findViewById(R.id.hour_time);
                return false;
            });
            year.setOnTouchListener((v13, event) -> {
                selectView = 0;
                hourTime = v.findViewById(R.id.hour_time);
                return false;
            });
            month.setOnTouchListener((v1, event) -> {
                selectView = 0;
                hourTime = v.findViewById(R.id.hour_time);
                return false;
            });
            day.setOnTouchListener((v1, event) -> {
                selectView = 0;
                hourTime = v.findViewById(R.id.hour_time);
                return false;
            });
            commit.setOnClickListener(view1 -> {
                selectView = 1;
                hourTime = v.findViewById(R.id.hour_time);
                pvView.returnData();
            });
        });
        builder.setType(new boolean[]{true, true, true, false, false, false});
        builder.isCenterLabel(false).setDividerColor(Color.WHITE);
        builder.setContentTextSize(15);
        builder.setLineSpacingMultiplier(3);
        //builder.setOutSideCancelable(false);
        pvView = builder.build();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) pvView.getDialogContainerLayout().getLayoutParams();
        layoutParams.bottomMargin = BarUtils.getNavigationBarHeight(this);
        pvView.getDialogContainerLayout().setLayoutParams(layoutParams);
        pvView.show(mianView);

    }


    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 判断时间是不是今天
     *
     * @param date
     * @return 是返回true，不是返回false
     */
    private static boolean isNow(Date date) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取今天的日期
        String nowDay = sf.format(now);
        //对比的时间
        String day = sf.format(date);
        return day.equals(nowDay);
    }


    /**
     * 是否能下单
     */
    @SuppressLint("SimpleDateFormat")
    private boolean isZhengDan() {
        String format = "HH:mm:ss";
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
        String now = sf.format(new Date());
        if (isNow(selectDate)) {   //如果时间是今天
            try {
                Date startTime = new SimpleDateFormat(format).parse("11:00:00");
                Date endTime = new SimpleDateFormat(format).parse("15:00:00");
                Date nowTime = new SimpleDateFormat(format).parse(now);
                if (isEffectiveDate(nowTime, startTime, endTime)) {
                    orderType = 1;
                    return true;
                } else {
                    orderType = 0;
                    return false;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            orderType = 0;
        }
        return true;
    }


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
                        showToast("支付成功！");
                        gotoActivity(MyOrderActivity.class, true);
                    } else {              //支付失败
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showToast("支付失败！");
                        gotoActivity(MyOrderActivity.class, true);
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
