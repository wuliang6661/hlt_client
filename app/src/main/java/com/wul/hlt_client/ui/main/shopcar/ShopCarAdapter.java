package com.wul.hlt_client.ui.main.shopcar;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.wul.hlt_client.R;
import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.entity.ShopBO;
import com.wul.hlt_client.entity.ShopCarBO;
import com.wul.hlt_client.entity.event.ShopCarRefresh;
import com.wul.hlt_client.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.wul.hlt_client.widget.lgrecycleadapter.LGViewHolder;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wuliang on 2018/12/22.
 */

public class ShopCarAdapter extends LGRecycleViewAdapter<ShopBO> {


    private Context context;

    public ShopCarAdapter(Context context, ShopCarBO carBO) {
        super(carBO.getShoppingCartList());
        this.context = context;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_shop_car;
    }

    @Override
    public void convert(LGViewHolder holder, ShopBO shopBO, int position) {

        ImageView shopAdd = (ImageView) holder.getView(R.id.shop_add);
        holder.setImageUrl(context, R.id.good_img, shopBO.getImage());
        holder.setText(R.id.good_name, shopBO.getProductName());
        TextView goodUnit = (TextView) holder.getView(R.id.good_unit);
        TextView goodUnit2 = (TextView) holder.getView(R.id.good_unit2);

        goodUnit.setTextColor(Color.parseColor("#FF722B"));
        goodUnit2.setText("数量：" + shopBO.getQuantity() + shopBO.getMeasureUnitName2());
        holder.getView(R.id.kucun).setVisibility(View.INVISIBLE);
        holder.getView(R.id.good_price).setVisibility(View.GONE);
        holder.getView(R.id.good_price2).setVisibility(View.GONE);
        if (shopBO.getIsPromotion() == 1 && shopBO.getProductType() == 0) {   //促销商品
            goodUnit.setText("¥" + shopBO.getPromotionPrice2() + "元/" + shopBO.getMeasureUnitName2());
        } else if (shopBO.getProductType() == 1) {    //秒杀商品
            goodUnit.setText("¥" + shopBO.getPromotionPrice2() + "元/" + shopBO.getMeasureUnitName2());
        } else if (shopBO.getProductType() == 0 && shopBO.getIsPromotion() == 0) {        //正常商品
            goodUnit.setText("¥" + shopBO.getPrice2() + "元/" + shopBO.getMeasureUnitName2());
        }

        shopAdd.setTag(shopBO);
        shopAdd.setOnClickListener(view -> {
            ShopBO body = (ShopBO) view.getTag();
            addShopCar(body.getId());
        });
        LinearLayout remove = (LinearLayout) holder.getView(R.id.shop_remove);
        TextView shopNum = (TextView) holder.getView(R.id.shop_num);
        TextView shopPrice = (TextView) holder.getView(R.id.shop_price);
        LinearLayout shopCarPriceLayout = (LinearLayout) holder.getView(R.id.shop_car_item_price);
        if (shopBO.getQuantity() > 0) {
            remove.setVisibility(View.VISIBLE);
            shopNum.setVisibility(View.VISIBLE);
            shopNum.setText(shopBO.getQuantity() + "");
            shopCarPriceLayout.setVisibility(View.VISIBLE);
            shopPrice.setText("¥ " + shopBO.getMoney());
        } else {
            shopCarPriceLayout.setVisibility(View.INVISIBLE);
            shopNum.setVisibility(View.GONE);
            remove.setVisibility(View.GONE);
        }
        remove.setTag(shopBO);
        remove.setOnClickListener(view ->
        {
            ShopBO body = (ShopBO) view.getTag();
            removeShopCar(body.getId());
        });
    }


    /**
     * 增加商品到购物车
     */
    private void addShopCar(int shopId) {
        HttpServiceIml.addShopCar(shopId).subscribe(new HttpResultSubscriber<String>() {
            @Override
            public void onSuccess(String s) {
                getShopCarList();
            }

            @Override
            public void onFiled(String message) {
                ToastUtils.showShort(message);
            }
        });
    }


    /**
     * 查询购物车商品
     */
    private void getShopCarList() {
        HttpServiceIml.getShopCarList().subscribe(new HttpResultSubscriber<ShopCarBO>(context) {
            @Override
            public void onSuccess(ShopCarBO s) {
                MyApplication.shopCarBO = s;
                setData(s.getShoppingCartList());
                EventBus.getDefault().post(new ShopCarRefresh());
                notifyDataSetChanged();
            }

            @Override
            public void onFiled(String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    /**
     * 减少商品数量
     */
    private void removeShopCar(int shopId) {
        HttpServiceIml.removeShop(shopId).subscribe(new HttpResultSubscriber<String>() {
            @Override
            public void onSuccess(String s) {
                getShopCarList();
            }

            @Override
            public void onFiled(String message) {
                ToastUtils.showShort(message);
            }
        });
    }


}
