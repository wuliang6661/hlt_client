package com.wul.hlt_client.ui.myorder;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.base.GlideApp;
import com.wul.hlt_client.entity.OrderMonthBO;

import java.util.ArrayList;
import java.util.List;

public class ExpandListAdapter extends BaseExpandableListAdapter {

    private List<OrderMonthBO.AddressMyOrderListBean> lists;
    private boolean isBudan;
    private Context context;
    List<Integer> ids;

    public ExpandListAdapter(Context context, boolean isBuDan, List<OrderMonthBO.AddressMyOrderListBean> lists) {
        this.lists = lists;
        this.context = context;
        this.isBudan = isBuDan;
        ids = new ArrayList<>();
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
        notifyDataSetChanged();
    }


    @Override
    public int getGroupCount() {
        return lists.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return lists.get(i).getOrderList().size();
    }

    @Override
    public Object getGroup(int i) {
        return lists.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return lists.get(i).getOrderList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GraupHodler hodler;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_orderlist_group, null);
            hodler = new GraupHodler();
            hodler.date = view.findViewById(R.id.date_text);
            view.setTag(hodler);
        } else {
            hodler = (GraupHodler) view.getTag();
        }
        hodler.date.setText(lists.get(i).getDate());
        return view;
    }

    class GraupHodler {

        TextView date;

    }


    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHodler hodler;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_order_lishi, null);
            hodler = new ChildViewHodler(view);
            view.setTag(hodler);
        } else {
            hodler = (ChildViewHodler) view.getTag();
        }
        OrderMonthBO.AddressMyOrderListBean.OrderListBean orderBo = lists.get(i).getOrderList().get(i1);
        hodler.time.setText(orderBo.getRequireDeliverOn());
        switch ((int) orderBo.getPayStatus()) {
            case 0:    //未支付
                hodler.payStatus.setTextColor(Color.parseColor("#F5142F"));
                hodler.payStatus.setText("未支付");
                hodler.checkbox.setVisibility(View.VISIBLE);
                break;
            case 1:   //已支付
                hodler.payStatus.setTextColor(Color.parseColor("#61C95F"));
                hodler.payStatus.setText("已支付");
                hodler.checkbox.setVisibility(View.GONE);
                break;
        }
        switch ((int) orderBo.getStatusId()) {
            case 0:    //待接单
                hodler.orderStatus.setText("待接单");
                hodler.orderStatus.setTextColor(Color.parseColor("#FF722B"));
                break;
            case 1:    //已接单
                hodler.orderStatus.setText("已接单");
                hodler.orderStatus.setTextColor(Color.parseColor("#61C95F"));
                break;
            case 2:   //已完成
                hodler.orderStatus.setText("已完成");
                hodler.orderStatus.setTextColor(Color.parseColor("#61C95F"));
                break;
            case 3:     //已终止
                hodler.orderStatus.setText("已终止");
                hodler.orderStatus.setTextColor(Color.parseColor("#CCCCCC"));
                break;
        }
        hodler.orderPrice.setText("¥ " + orderBo.getAmount());
        if (orderBo.getProductDetailList().size() >= 1) {
            GlideApp.with(context).load(orderBo.getProductDetailList().get(0).getImg())
                    .placeholder(R.drawable.zhanwei1)
                    .error(R.drawable.zhanwei1)
                    .into(hodler.shopImg1);
        }
        if (orderBo.getProductDetailList().size() >= 2) {
            GlideApp.with(context).load(orderBo.getProductDetailList().get(1).getImg())
                    .placeholder(R.drawable.zhanwei1)
                    .error(R.drawable.zhanwei1)
                    .into(hodler.shopImg2);
        }
        if (orderBo.getProductDetailList().size() >= 3) {
            GlideApp.with(context).load(orderBo.getProductDetailList().get(2).getImg())
                    .placeholder(R.drawable.zhanwei1)
                    .error(R.drawable.zhanwei1)
                    .into(hodler.shopImg3);
        }
        if (orderBo.getProductDetailList().size() >= 4) {
            GlideApp.with(context).load(orderBo.getProductDetailList().get(3).getImg())
                    .placeholder(R.drawable.zhanwei1)
                    .error(R.drawable.zhanwei1)
                    .into(hodler.shopImg4);
        }
        if (isBudan) {
            hodler.budan.setVisibility(View.VISIBLE);
        } else {
            hodler.budan.setVisibility(View.GONE);
        }
        if (ids.contains((int) orderBo.getId())) {
            hodler.checkbox.setChecked(true);
        } else {
            hodler.checkbox.setChecked(false);
        }
        hodler.checkbox.setTag(orderBo);
        hodler.checkbox.setOnCheckedChangeListener((compoundButton, bis) -> {
            OrderMonthBO.AddressMyOrderListBean.OrderListBean orderBo1 = (OrderMonthBO.AddressMyOrderListBean.OrderListBean) compoundButton.getTag();
            if (bis) {
                if (onSelector != null)
                    onSelector.select((int) orderBo1.getId());
            } else {
                if (onSelector != null)
                    onSelector.cancle((int) orderBo1.getId());
            }
        });
        return view;
    }


    onSelector onSelector;

    public void setOnSelector(onSelector selector) {
        this.onSelector = selector;
    }


    public interface onSelector {

        void select(int id);

        void cancle(int id);
    }


    class ChildViewHodler {
        TextView budan;
        CheckBox checkbox;
        TextView time;
        TextView payStatus;
        TextView orderStatus;
        TextView orderPrice;
        ImageView shopImg1, shopImg2, shopImg3, shopImg4;

        ChildViewHodler(View view) {
            budan = view.findViewById(R.id.order_budan);
            checkbox = view.findViewById(R.id.checkbox);
            time = view.findViewById(R.id.stop_time);
            payStatus = view.findViewById(R.id.pay_type);
            orderStatus = view.findViewById(R.id.order_type);
            orderPrice = view.findViewById(R.id.order_price);
            shopImg1 = view.findViewById(R.id.shop_img1);
            shopImg2 = view.findViewById(R.id.shop_img2);
            shopImg3 = view.findViewById(R.id.shop_img3);
            shopImg4 = view.findViewById(R.id.shop_img4);
        }
    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
