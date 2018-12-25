package com.wul.hlt_client.ui.ordercommit;

import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.entity.AddressBO;
import com.wul.hlt_client.entity.ShoppingCarBO;
import com.wul.hlt_client.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderCommitPresenter extends BasePresenterImpl<OrderCommitContract.View>
        implements OrderCommitContract.Presenter {

    /**
     * 获取门店信息
     */
    public void getAddressInfo() {
        HttpServiceIml.getAddressInfo().subscribe(new HttpResultSubscriber<AddressBO>() {
            @Override
            public void onSuccess(AddressBO s) {
                if (mView != null) {
                    mView.getAddressInfo(s);
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


    /**
     * 获取购物车购物清单
     */
    public void getShoppingList(int orderType) {
        HttpServiceIml.getShoppingList(orderType).subscribe(new HttpResultSubscriber<ShoppingCarBO>(mView.getContext()) {
            @Override
            public void onSuccess(ShoppingCarBO s) {
                if (mView != null) {
                    mView.getShoppingList(s);
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

    /**
     * 确认订单
     */
    public void commitOrder() {
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
