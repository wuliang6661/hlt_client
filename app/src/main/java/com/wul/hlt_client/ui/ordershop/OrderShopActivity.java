package com.wul.hlt_client.ui.ordershop;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.entity.ShoppingCarBO;
import com.wul.hlt_client.mvp.MVPBaseActivity;
import com.wul.hlt_client.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.wul.hlt_client.widget.lgrecycleadapter.LGViewHolder;

import butterknife.BindView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderShopActivity extends MVPBaseActivity<OrderShopContract.View, OrderShopPresenter>
        implements OrderShopContract.View {

    @BindView(R.id.recycle)
    RecyclerView recycle;

    @Override
    protected int getLayout() {
        return R.layout.act_order_shop;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goBack();
        setTitleText("商品信息");

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager);

        mPresenter.getShoppingList(0);
    }

    @Override
    public void onRequestError(String msg) {
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getShopList(ShoppingCarBO carBO) {
        LGRecycleViewAdapter<ShoppingCarBO.ShoppingCartListBean> adapter =
                new LGRecycleViewAdapter<ShoppingCarBO.ShoppingCartListBean>(carBO.getShoppingCartList()) {
                    @Override
                    public int getLayoutId(int viewType) {
                        return R.layout.item_order_shop;
                    }

                    @Override
                    public void convert(LGViewHolder holder, ShoppingCarBO.ShoppingCartListBean o, int position) {
                        holder.setText(R.id.shop_name, o.getProductName());
                        if (o.getProductType() == 0 && o.getIsPromotion() == 0) {
                            holder.setText(R.id.shop_unit, "¥ " + o.getPrice2() + "元/" + o.getMeasureUnitName2());
                        } else {
                            holder.setText(R.id.shop_unit, "¥ " + o.getPromotionPrice2() + "元/" + o.getMeasureUnitName2());
                        }
                        holder.setText(R.id.shop_num, "数量：" + o.getQuantity() + o.getMeasureUnitName2());
                        holder.setText(R.id.shop_price, "¥ " + o.getMoney());
                        holder.setImageUrl(OrderShopActivity.this, R.id.shop_img, o.getHttpUrl());
                    }
                };
        recycle.setAdapter(adapter);
    }
}
