package com.wul.hlt_client.ui.main.salesgood;

import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.XianShiBO;
import com.wul.hlt_client.entity.request.XianshiBO;
import com.wul.hlt_client.mvp.BasePresenterImpl;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SalesGoodPresenter extends BasePresenterImpl<SalesGoodContract.View>
        implements SalesGoodContract.Presenter {


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


    public void getXianshiList() {
        XianshiBO xianshiBO = new XianshiBO();
        xianshiBO.pageNum = 1;
        xianshiBO.pageSize = 1000;
        HttpServiceIml.getXianshiList(xianshiBO).subscribe(new HttpResultSubscriber<XianShiBO>(mView.getContext()) {
            @Override
            public void onSuccess(XianShiBO list) {
                if (mView != null) {
                    mView.getXianshiList(list);
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
        HttpServiceIml.testSpike().subscribe(new HttpResultSubscriber<String>() {
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
