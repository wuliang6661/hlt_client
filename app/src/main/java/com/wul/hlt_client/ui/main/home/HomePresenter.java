package com.wul.hlt_client.ui.main.home;

import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.entity.BannerBo;
import com.wul.hlt_client.entity.ClassifyBO;
import com.wul.hlt_client.entity.ShopBO;
import com.wul.hlt_client.entity.request.PageBO;
import com.wul.hlt_client.entity.request.XianshiBO;
import com.wul.hlt_client.mvp.BasePresenterImpl;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HomePresenter extends BasePresenterImpl<HomeContract.View>
        implements HomeContract.Presenter {

    public void getClassifyList() {
        PageBO pageBO = new PageBO();
        pageBO.pageNum = 1;
        pageBO.pageSize = 8;
        HttpServiceIml.getCategorys(pageBO).subscribe(new HttpResultSubscriber<List<ClassifyBO>>(mView.getContext()) {
            @Override
            public void onSuccess(List<ClassifyBO> s) {
                if (mView != null) {
                    mView.getClassify(s);
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


    public void getBanner() {
        HttpServiceIml.getBanner().subscribe(new HttpResultSubscriber<List<BannerBo>>() {
            @Override
            public void onSuccess(List<BannerBo> s) {
                if (mView != null) {
                    mView.getBannerList(s);
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


    public void getComomPaseList() {
        PageBO pageBO = new PageBO();
        pageBO.pageNum = 1;
        pageBO.pageSize = 4;
        HttpServiceIml.getComstonList(pageBO).subscribe(new HttpResultSubscriber<List<ShopBO>>() {
            @Override
            public void onSuccess(List<ShopBO> s) {
                if (mView != null) {
                    mView.getChangyongShop(s);
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
        xianshiBO.pageSize = 4;
        HttpServiceIml.getXianshiList(xianshiBO).subscribe(new HttpResultSubscriber<List<ShopBO>>() {
            @Override
            public void onSuccess(List<ShopBO> list) {
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
}
