package com.wul.hlt_client.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.base.BaseActivity;
import com.wul.hlt_client.entity.ShopInfoBO;

import butterknife.BindView;

public class ShopInfoActivty extends BaseActivity {

    @BindView(R.id.take_goods_person)
    TextView takeGoodsPerson;
    @BindView(R.id.person_phone)
    TextView personPhone;
    @BindView(R.id.take_shop_name)
    TextView takeShopName;
    @BindView(R.id.take_address)
    TextView takeAddress;
    @BindView(R.id.shop_num)
    TextView shopNum;
    @BindView(R.id.shop_type)
    TextView shopType;

    @Override
    protected int getLayout() {
        return R.layout.act_shop_info;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goBack();
        setTitleText("门店信息");

        ShopInfoBO infoBO = (ShopInfoBO) getIntent().getExtras().getSerializable("info");
        takeGoodsPerson.setText("收货人：" + infoBO.getContact());
        personPhone.setText("联系方式：" + infoBO.getContactPhone());
        takeShopName.setText("收货店名：" + infoBO.getName());
        takeAddress.setText("收货地址：" + infoBO.getAddress());
        shopNum.setText("门店编号：" + infoBO.getNumber());
        if (infoBO.getIsConfirmed() == 0) {
            shopType.setText("门店状态：未确认");
        } else {
            shopType.setText("门店状态：已确认");
        }
    }
}
