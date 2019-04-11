package com.wul.hlt_client.ui.myorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
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

    private List<Integer> ids;
    List<OrderDayBo.AddressMyOrderListBean> dataList;

    public RecycleAdapter(Context context, List<OrderDayBo.AddressMyOrderListBean> dataList) {
        super(dataList);
        this.context = context;
        this.dataList = dataList;
        ids = new ArrayList<>();
        for (OrderDayBo.AddressMyOrderListBean bean : dataList) {
            if (bean.getPayStatus() == 0 && bean.getStatusId() != 3 && "0".equals(bean.getOrderType())) {
                ids.add(bean.getId());
            }
        }
    }


    public List<Integer> getIds() {
        ids = new ArrayList<>();
        for (OrderDayBo.AddressMyOrderListBean bean : dataList) {
            if (bean.getPayStatus() == 0 && bean.getStatusId() != 3 && "0".equals(bean.getOrderType())) {
                ids.add(bean.getId());
            }
        }
        return ids;
    }


    public void setIds(List<Integer> ids) {
        this.ids = ids;
        new Handler().post(() -> notifyDataSetChanged());
    }


    public void selectAll() {
        ids.clear();
        for (OrderDayBo.AddressMyOrderListBean bean : dataList) {
            if (bean.getPayStatus() == 0 && bean.getStatusId() != 3 && "0".equals(bean.getOrderType())) {
                ids.add((int) bean.getId());
            }
        }
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
        holder.getView(R.id.item_layout).setTag(addressMyOrderListBean);
        switch ((int) addressMyOrderListBean.getPayStatus()) {
            case 0:    //未支付
                payStatus.setTextColor(Color.parseColor("#F5142F"));
                payStatus.setText("未支付");
                checkBox.setVisibility(View.VISIBLE);
                break;
            case 1:   //已支付
                payStatus.setTextColor(ContextCompat.getColor(context, R.color.zhu_color));
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
                orderType.setTextColor(ContextCompat.getColor(context, R.color.zhu_color));
                break;
            case 2:   //已完成
                orderType.setText("已完成");
                orderType.setTextColor(ContextCompat.getColor(context, R.color.zhu_color));
                break;
            case 3:     //已终止
                orderType.setText("已终止");
                orderType.setTextColor(Color.parseColor("#CCCCCC"));
                checkBox.setVisibility(View.GONE);
                break;
        }
        holder.setText(R.id.order_price, "¥ " + addressMyOrderListBean.getAmount());
        holder.getView(R.id.shop_img1).setVisibility(View.INVISIBLE);
        holder.getView(R.id.shop_img2).setVisibility(View.INVISIBLE);
        holder.getView(R.id.shop_img3).setVisibility(View.INVISIBLE);
        holder.getView(R.id.shop_img4).setVisibility(View.INVISIBLE);
        if (addressMyOrderListBean.getProductDetailList().size() >= 1) {
            holder.getView(R.id.shop_img1).setVisibility(View.VISIBLE);
            holder.setImageUrl(context, R.id.shop_img1, addressMyOrderListBean.getProductDetailList().get(0).getImg());
        }
        if (addressMyOrderListBean.getProductDetailList().size() >= 2) {
            holder.getView(R.id.shop_img2).setVisibility(View.VISIBLE);
            holder.setImageUrl(context, R.id.shop_img2, addressMyOrderListBean.getProductDetailList().get(1).getImg());
        }
        if (addressMyOrderListBean.getProductDetailList().size() >= 3) {
            holder.getView(R.id.shop_img3).setVisibility(View.VISIBLE);
            holder.setImageUrl(context, R.id.shop_img3, addressMyOrderListBean.getProductDetailList().get(2).getImg());
        }
        if (addressMyOrderListBean.getProductDetailList().size() >= 4) {
            holder.getView(R.id.shop_img4).setVisibility(View.VISIBLE);
            holder.setImageUrl(context, R.id.shop_img4, addressMyOrderListBean.getProductDetailList().get(3).getImg());
        }
        if ("1".equals(addressMyOrderListBean.getOrderType())) {
            holder.getView(R.id.order_budan).setVisibility(View.VISIBLE);
            checkBox.setVisibility(View.GONE);
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
