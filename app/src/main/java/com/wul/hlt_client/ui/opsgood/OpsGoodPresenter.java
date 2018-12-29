package com.wul.hlt_client.ui.opsgood;

import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.ShopBO;
import com.wul.hlt_client.entity.request.PageBO;
import com.wul.hlt_client.mvp.BasePresenterImpl;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OpsGoodPresenter extends BasePresenterImpl<OpsGoodContract.View>
        implements OpsGoodContract.Presenter {


    public void getChangyongList() {
        PageBO pageBO = new PageBO();
        pageBO.pageSize = 1000;
        pageBO.pageNum = 1;
        HttpServiceIml.getComstonList(pageBO).subscribe(new HttpResultSubscriber<List<ShopBO>>() {
            @Override
            public void onSuccess(List<ShopBO> shopBOS) {
                if (mView != null) {
                    mView.getOpsShop(shopBOS);
                }
            }

            @Override
            public void onFiled(String message) {
                if (mView != null) {
                    mView.onRequestError(message);
                }
            }
        });
    }


    public void getCityGongGao() {
        HttpServiceIml.getCityGongGao().subscribe(new HttpResultSubscriber<List<CityGongGao>>() {
            @Override
            public void onSuccess(List<CityGongGao> s) {
                if (mView != null) {
                    mView.getCityGongGao(s);
                }
            }

            @Override
            public void onFiled(String message) {
                if (mView != null) {
                    mView.onRequestError(message);
                }
            }
        });
    }

    public void testSkipe() {
        HttpServiceIml.testSpike().subscribe(new HttpResultSubscriber<String>(mView.getContext()) {
            @Override
            public void onSuccess(String s) {
                if (mView != null) {
                    mView.testSuress();
                }
            }

            @Override
            public void onFiled(String message) {
                if (mView != null) {
                    mView.onRequestError(message);
                }
            }
        });
    }
}
