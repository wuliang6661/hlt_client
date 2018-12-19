package com.wul.hlt_client.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.entity.ShopBO;
import com.wul.hlt_client.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.wul.hlt_client.widget.lgrecycleadapter.LGViewHolder;

import java.util.List;

/**
 * Created by dell on 2018/12/19.
 * <p>
 * 所有用于增加商品至购物车的列表
 */

public class ShopAdapter extends LGRecycleViewAdapter<ShopBO> {

    private Context context;


    public ShopAdapter(Context context, List<ShopBO> dataList) {
        super(dataList);
        this.context = context;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_shop_car;
    }

    @Override
    public void convert(LGViewHolder holder, ShopBO shopBO, int position) {
        ImageView shopAdd = (ImageView) holder.getView(R.id.shop_add);
        shopAdd.setEnabled(true);
        holder.setImageUrl(context, R.id.good_img, shopBO.getImage());
        holder.setText(R.id.good_name, shopBO.getProductName());
        holder.setText(R.id.good_unit, shopBO.getProductDetail());
        holder.getView(R.id.kucun).setVisibility(View.GONE);
        holder.setText(R.id.good_price, "¥" + shopBO.getPrice2() + "元/" + shopBO.getMeasureUnitName2());
        holder.setText(R.id.good_price2, "¥" + shopBO.getPrice1() + "元/" + shopBO.getMeasureUnitName1());
        if (shopBO.getIsPromotion() == 1) {   //促销商品
            holder.getView(R.id.good_type_cu).setVisibility(View.VISIBLE);
            holder.setText(R.id.good_price, "¥" + shopBO.getPromotionPrice2() + "元/" + shopBO.getMeasureUnitName2());
            holder.setText(R.id.good_price2, "¥" + shopBO.getPromotionPrice1() + "元/" + shopBO.getMeasureUnitName1());
        } else {
            holder.getView(R.id.good_type_cu).setVisibility(View.GONE);
            holder.getView(R.id.good_price).setVisibility(View.GONE);
            holder.getView(R.id.good_price2).setVisibility(View.GONE);
        }
        if (shopBO.getProductType() == 0) {    //正常商品
            holder.getView(R.id.good_price).setVisibility(View.GONE);
            holder.getView(R.id.good_price2).setVisibility(View.GONE);
            holder.getView(R.id.good_type_miao).setVisibility(View.GONE);
            holder.setText(R.id.good_unit2, "¥" + shopBO.getPrice1() + "元/" + shopBO.getMeasureUnitName1());
        } else {        //秒杀商品
            holder.getView(R.id.kucun).setVisibility(View.VISIBLE);
            holder.getView(R.id.good_price).setVisibility(View.VISIBLE);
            holder.getView(R.id.good_price2).setVisibility(View.VISIBLE);
            holder.getView(R.id.good_type_miao).setVisibility(View.VISIBLE);
            holder.setText(R.id.good_price, "¥" + shopBO.getPromotionPrice2() + "元/" + shopBO.getMeasureUnitName2());
            holder.setText(R.id.good_price2, "¥" + shopBO.getPromotionPrice1() + "元/" + shopBO.getMeasureUnitName1());
            if (shopBO.getSurplusStock() > 0) {  //库存大于0
                holder.setText(R.id.kucun, "仅剩" + shopBO.getSurplusStock() + shopBO.getMeasureUnitName2());
            } else {
                holder.setText(R.id.kucun, "今日已抢光");
                shopAdd.setEnabled(false);
            }
        }
    }
}
