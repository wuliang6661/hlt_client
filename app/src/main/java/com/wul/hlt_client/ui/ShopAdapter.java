package com.wul.hlt_client.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/12/19.
 * <p>
 * 所有用于增加商品至购物车的列表
 */

public class ShopAdapter extends LGRecycleViewAdapter<ShopBO> {

    private Context context;

    Map<Integer, ShopBO> shopCars;


    public ShopAdapter(Context context, List<ShopBO> dataList, ShopCarBO carBO) {
        super(dataList);
        this.context = context;
        shopCars = new HashMap<>();
//        if (carBO != null && carBO.getShoppingCartList() != null) {
//            for (ShopBO shopBO : carBO.getShoppingCartList()) {
//                shopCars.put(shopBO.getProductId(), shopBO);
//            }
//        }
        getShopCarList();
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
        holder.setText(R.id.good_unit, shopBO.getProductDetail());
        holder.setText(R.id.good_unit2, "¥" + shopBO.getPrice1() + "元/" + shopBO.getMeasureUnitName1());
        holder.getView(R.id.kucun).setVisibility(View.INVISIBLE);
        setCuxiao(holder, shopBO);
        setMiaosha(holder, shopBO);
        shopAdd.setTag(shopBO);
        shopAdd.setOnClickListener(view -> {
            ShopBO body = (ShopBO) view.getTag();
            addShopCar(body.getId());
        });
        LinearLayout remove = (LinearLayout) holder.getView(R.id.shop_remove);
        TextView shopNum = (TextView) holder.getView(R.id.shop_num);
        TextView shopPrice = (TextView) holder.getView(R.id.shop_price);
        LinearLayout shopCarPriceLayout = (LinearLayout) holder.getView(R.id.shop_car_item_price);
        ShopBO carshop = shopCars.get(shopBO.getId());
        if (carshop != null) {
            if (Double.parseDouble(carshop.getQuantity()) > 0) {
                remove.setVisibility(View.VISIBLE);
                shopNum.setVisibility(View.VISIBLE);
                shopNum.setText(carshop.getQuantity() + "");
                shopCarPriceLayout.setVisibility(View.VISIBLE);
                shopPrice.setText("¥ " + carshop.getMoney());
            } else {
                shopCarPriceLayout.setVisibility(View.INVISIBLE);
                shopNum.setVisibility(View.GONE);
                remove.setVisibility(View.GONE);
            }
        } else {
            shopCarPriceLayout.setVisibility(View.INVISIBLE);
            shopNum.setVisibility(View.GONE);
            remove.setVisibility(View.GONE);
        }
        remove.setTag(shopBO);
        remove.setOnClickListener(view -> {
            ShopBO body = (ShopBO) view.getTag();
            removeShopCar(body.getId());
        });
    }

    /**
     * 促销商品处理
     */
    private void setCuxiao(LGViewHolder holder, ShopBO shopBO) {
        TextView yuanText = (TextView) holder.getView(R.id.good_unit2);
        yuanText.getPaint().setFlags(0);
        if (shopBO.getIsPromotion() == 1 && shopBO.getProductType() == 0) {   //促销商品
            holder.getView(R.id.good_type_cu).setVisibility(View.VISIBLE);
            holder.getView(R.id.good_price).setVisibility(View.VISIBLE);
            holder.getView(R.id.good_price2).setVisibility(View.VISIBLE);
            holder.setText(R.id.good_price, "¥" + shopBO.getPromotionPrice2() + "元/" + shopBO.getMeasureUnitName2());
            holder.setText(R.id.good_price2, "¥" + shopBO.getPromotionPrice1() + "元/" + shopBO.getMeasureUnitName1());
            yuanText.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        } else {
            holder.getView(R.id.good_type_cu).setVisibility(View.GONE);
        }
    }


    /**
     * 秒杀商品处理
     */
    private void setMiaosha(LGViewHolder holder, ShopBO shopBO) {
        ImageView shopAdd = (ImageView) holder.getView(R.id.shop_add);
        TextView yuanText = (TextView) holder.getView(R.id.good_unit2);
        shopAdd.setEnabled(true);
        if (shopBO.getProductType() == 1) {    //秒杀商品
            holder.getView(R.id.kucun).setVisibility(View.VISIBLE);
            holder.getView(R.id.good_price).setVisibility(View.VISIBLE);
            holder.getView(R.id.good_price2).setVisibility(View.VISIBLE);
            holder.getView(R.id.good_type_miao).setVisibility(View.VISIBLE);
            holder.setText(R.id.good_price, "¥" + shopBO.getPromotionPrice2() + "元/" + shopBO.getMeasureUnitName2());
            holder.setText(R.id.good_price2, "¥" + shopBO.getPromotionPrice1() + "元/" + shopBO.getMeasureUnitName1());
            yuanText.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
            if (shopBO.getSurplusStock() > 0) {  //库存大于0
                holder.setText(R.id.kucun, "仅剩" + shopBO.getSurplusStock() + shopBO.getMeasureUnitName2());
            } else {
                holder.setText(R.id.kucun, "今日已抢光");
                shopAdd.setEnabled(false);
            }
        } else if (shopBO.getProductType() == 0 && shopBO.getIsPromotion() == 0) {        //正常商品
            holder.getView(R.id.good_price).setVisibility(View.VISIBLE);
            holder.getView(R.id.good_unit2).setVisibility(View.GONE);
            holder.getView(R.id.good_type_miao).setVisibility(View.GONE);
            holder.setText(R.id.good_price, "¥" + shopBO.getPrice2() + "元/" + shopBO.getMeasureUnitName2());
            holder.setText(R.id.good_price2, "¥" + shopBO.getPrice1() + "元/" + shopBO.getMeasureUnitName1());
//            yuanText.setTextColor(Color.parseColor("#FF722B"));
//            yuanText.getPaint().setFlags(0);
        }
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
        HttpServiceIml.getShopCarList().subscribe(new HttpResultSubscriber<ShopCarBO>() {
            @Override
            public void onSuccess(ShopCarBO s) {
                shopCars.clear();
                MyApplication.shopCarBO = s;
                EventBus.getDefault().post(new ShopCarRefresh());
                if (s.getShoppingCartList() == null || s.getShoppingCartList().size() == 0) {
                    notifyDataSetChanged();
                    return;
                }
                for (ShopBO shopBO : s.getShoppingCartList()) {
                    shopCars.put(shopBO.getProductId(), shopBO);
                }
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