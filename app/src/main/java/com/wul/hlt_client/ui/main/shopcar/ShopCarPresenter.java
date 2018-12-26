package com.wul.hlt_client.ui.main.shopcar;

import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.entity.ShopCarBO;
import com.wul.hlt_client.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ShopCarPresenter extends BasePresenterImpl<ShopCarContract.View>
        implements ShopCarContract.Presenter {


    public void getShopCarList() {
        HttpServiceIml.getShopCarList().subscribe(new HttpResultSubscriber<ShopCarBO>(mView.getContext()) {
            @Override
            public void onSuccess(ShopCarBO s) {
                if (mView != null) {
                    mView.getShopCar(s);
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


    public void clearShoppingCar() {
        HttpServiceIml.clearShopCar().subscribe(new HttpResultSubscriber<String>() {
            @Override
            public void onSuccess(String s) {
                if (mView != null) {
                    getShopCarList();
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
