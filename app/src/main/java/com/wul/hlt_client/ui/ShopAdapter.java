package com.wul.hlt_client.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

        holder.setImageUrl(context, R.id.good_img, shopBO.getImage());
        holder.setText(R.id.good_name, shopBO.getProductName());
        holder.setText(R.id.good_unit, shopBO.getProductDetail());
        holder.setText(R.id.good_unit2, "¥" + shopBO.getPrice1() + "元/" + shopBO.getMeasureUnitName1());
        holder.getView(R.id.kucun).setVisibility(View.INVISIBLE);
        setCuxiao(holder, shopBO);
        setMiaosha(holder, shopBO);
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
            yuanText.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
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
            yuanText.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
            if (shopBO.getSurplusStock() > 0) {  //库存大于0
                holder.setText(R.id.kucun, "仅剩" + shopBO.getSurplusStock() + shopBO.getMeasureUnitName2());
            } else {
                holder.setText(R.id.kucun, "今日已抢光");
                shopAdd.setEnabled(false);
            }
        } else if (shopBO.getProductType() == 0 && shopBO.getIsPromotion() == 0) {        //正常商品
            holder.getView(R.id.good_price).setVisibility(View.GONE);
            holder.getView(R.id.good_price2).setVisibility(View.GONE);
            holder.getView(R.id.good_type_miao).setVisibility(View.GONE);
            yuanText.setText("¥" + shopBO.getPrice1() + "元/" + shopBO.getMeasureUnitName1());
            yuanText.setTextColor(Color.parseColor("#61C95F"));
            yuanText.getPaint().setFlags(0);
        }
    }
}
