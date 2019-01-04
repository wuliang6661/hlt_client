package com.wul.hlt_client.ui.main.mine;

import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.entity.ShopInfoBO;
import com.wul.hlt_client.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MinePresenter extends BasePresenterImpl<MineContract.View>
        implements MineContract.Presenter {

    public void getShopInfo() {
        HttpServiceIml.getShopInfo().subscribe(new HttpResultSubscriber<ShopInfoBO>() {
            @Override
            public void onSuccess(ShopInfoBO s) {
                if (mView != null) {
                    mView.getShopInfo(s);
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
