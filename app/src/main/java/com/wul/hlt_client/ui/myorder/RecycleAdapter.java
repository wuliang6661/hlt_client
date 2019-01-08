package com.wul.hlt_client.ui.myorder;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.entity.OrderDayBo;
import com.wul.hlt_client.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.wul.hlt_client.widget.lgrecycleadapter.LGViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends LGRecycleViewAdapter<OrderDayBo.AddressMyOrderListBean> {

    private Context context;
    private boolean isBuDan = false;

    private List<Integer> ids;

    public RecycleAdapter(Context context, boolean isBuDan, List<OrderDayBo.AddressMyOrderListBean> dataList) {
        super(dataList);
        this.context = context;
        this.isBuDan = isBuDan;
        ids = new ArrayList<>();
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
        new Handler().post(() -> notifyDataSetChanged());
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_order_lishi;
    }

    @Override
    public void convert(LGViewHolder holder, OrderDayBo.AddressMyOrderListBean addressMyOrderListBean,
                        int position) {
        holder.setText(R.id.stop_time, addressMyOrderListBean.getRequireDeliverOn());
        TextView payStatus = (TextView) holder.getView(R.id.pay_type);
        TextView orderType = (TextView) holder.getView(R.id.order_type);
        CheckBox checkBox = (CheckBox) holder.getView(R.id.checkbox);
        switch ((int) addressMyOrderListBean.getPayStatus()) {
            case 0:    //未支付
                payStatus.setTextColor(Color.parseColor("#F5142F"));
                payStatus.setText("未支付");
                checkBox.setVisibility(View.VISIBLE);
                break;
            case 1:   //已支付
                payStatus.setTextColor(Color.parseColor("#61C95F"));
                payStatus.setText("已支付");
                checkBox.setVisibility(View.GONE);
                break;
        }
        switch ((int) addressMyOrderListBean.getStatusId()) {
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
                checkBox.setVisibility(View.GONE);
                break;
        }
        holder.setText(R.id.order_price, "¥ " + addressMyOrderListBean.getAmount());
        if (addressMyOrderListBean.getProductDetailList().size() >= 1) {
            holder.setImageUrl(context, R.id.shop_img1, addressMyOrderListBean.getProductDetailList().get(0).getImg());
        }
        if (addressMyOrderListBean.getProductDetailList().size() >= 2) {
            holder.setImageUrl(context, R.id.shop_img2, addressMyOrderListBean.getProductDetailList().get(1).getImg());
        }
        if (addressMyOrderListBean.getProductDetailList().size() >= 3) {
            holder.setImageUrl(context, R.id.shop_img3, addressMyOrderListBean.getProductDetailList().get(2).getImg());
        }
        if (addressMyOrderListBean.getProductDetailList().size() >= 4) {
            holder.setImageUrl(context, R.id.shop_img4, addressMyOrderListBean.getProductDetailList().get(3).getImg());
        }
        if (isBuDan) {
            holder.getView(R.id.order_budan).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.order_budan).setVisibility(View.GONE);
        }
        checkBox.setOnCheckedChangeListener(null);
        if (ids.contains((int) addressMyOrderListBean.getId())) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        checkBox.setTag(addressMyOrderListBean);
        checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            OrderDayBo.AddressMyOrderListBean addressMyOrderListBean1 = (OrderDayBo.AddressMyOrderListBean) compoundButton.getTag();
            if (b) {
                if (onSelector != null)
                    onSelector.select((int) addressMyOrderListBean1.getId());
            } else {
                if (onSelector != null)
                    onSelector.cancle((int) addressMyOrderListBean1.getId());
            }
        });
    }


    onSelector onSelector;

    public void setOnSelector(onSelector selector) {
        this.onSelector = selector;
    }


    public interface onSelector {

        void select(int id);

        void cancle(int id);
    }
}
