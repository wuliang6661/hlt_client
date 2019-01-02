package com.wul.hlt_client.ui.ordercommit;

import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.entity.AddressBO;
import com.wul.hlt_client.entity.MoneyBO;
import com.wul.hlt_client.entity.ShoppingCarBO;
import com.wul.hlt_client.entity.request.CommitOrderBO;
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
        HttpServiceIml.getShoppingList(orderType).subscribe(new HttpResultSubscriber<ShoppingCarBO>() {
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
    public void commitOrder(CommitOrderBO orderBO) {
        HttpServiceIml.commitOrder(orderBO).subscribe(new HttpResultSubscriber<String>() {
            @Override
            public void onSuccess(String s) {
                if (mView != null) {
                    mView.paySourss(s);
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


    /**
     * 获取订单金额
     */
    public void getMoney(int orderType, int banlanceStatus) {
        HttpServiceIml.getMoney(orderType, banlanceStatus).subscribe(new HttpResultSubscriber<MoneyBO>() {
            @Override
            public void onSuccess(MoneyBO s) {
                if (mView != null) {
                    mView.getMoney(s);
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
