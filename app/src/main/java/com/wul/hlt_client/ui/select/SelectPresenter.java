package com.wul.hlt_client.ui.select;

import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.XianShiBO;
import com.wul.hlt_client.mvp.BasePresenterImpl;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SelectPresenter extends BasePresenterImpl<SelectContract.View>
        implements SelectContract.Presenter {


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


    public void searchName(String shopName) {
        HttpServiceIml.searchList(shopName).subscribe(new HttpResultSubscriber<XianShiBO>() {
            @Override
            public void onSuccess(XianShiBO xianShiBO) {
                if (mView != null) {
                    mView.getSeachList(xianShiBO);
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
