package com.wul.hlt_client.ui.ordershop;

import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.entity.ShoppingCarBO;
import com.wul.hlt_client.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderShopPresenter extends BasePresenterImpl<OrderShopContract.View>
        implements OrderShopContract.Presenter {


    /**
     * 获取购物车购物清单
     */
    public void getShoppingList(int orderType) {
        HttpServiceIml.getShoppingList(orderType).subscribe(new HttpResultSubscriber<ShoppingCarBO>() {
            @Override
            public void onSuccess(ShoppingCarBO s) {
                if (mView != null) {
                    mView.getShopList(s);
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
