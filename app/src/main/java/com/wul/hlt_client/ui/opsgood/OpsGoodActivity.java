package com.wul.hlt_client.ui.opsgood;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.wul.hlt_client.R;
import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.ShopBO;
import com.wul.hlt_client.mvp.MVPBaseActivity;
import com.wul.hlt_client.ui.ShopAdapter;

import java.util.List;

import butterknife.BindView;


/**
 * MVPPlugin
 * 常用清单列表
 */

public class OpsGoodActivity extends MVPBaseActivity<OpsGoodContract.View, OpsGoodPresenter>
        implements OpsGoodContract.View {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.gonggao_text)
    TextView gonggaoText;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.shop_car_img)
    ImageView shopCarImg;
    @BindView(R.id.shop_car_price)
    TextView shopCarPrice;
    @BindView(R.id.shop_car_button)
    TextView shopCarButton;

    @Override
    protected int getLayout() {
        return R.layout.act_ops_good;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitleText("常用清单列表");

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(manager);

        mPresenter.getChangyongList();
        mPresenter.getCityGongGao();
    }

    @Override
    public void onRequestError(String msg) {
        showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getOpsShop(List<ShopBO> list) {
        ShopAdapter adapter = new ShopAdapter(this, list);
        recycle.setAdapter(adapter);
    }

    @Override
    public void getCityGongGao(List<CityGongGao> cityGongGaos) {
        StringBuilder buffer = new StringBuilder();
        for (CityGongGao cityGongGao : cityGongGaos) {
            buffer.append(cityGongGao.getContent()).append("                      ");
        }
        gonggaoText.setText(buffer.toString());
    }
}
