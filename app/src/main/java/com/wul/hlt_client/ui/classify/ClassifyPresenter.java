package com.wul.hlt_client.ui.classify;

import android.content.Context;

import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.entity.CityGongGao;
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

public class ClassifyPresenter extends BasePresenterImpl<ClassifyContract.View>
        implements ClassifyContract.Presenter {


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


    public void getClassifyList() {
        PageBO pageBO = new PageBO();
        pageBO.pageNum = 1;
        pageBO.pageSize = 1000;
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


    public void getChildClassify(int childId) {
        HttpServiceIml.getChildCategorys(childId).subscribe(new HttpResultSubscriber<List<ClassifyBO>>() {
            @Override
            public void onSuccess(List<ClassifyBO> s) {
                if (mView != null) {
                    mView.getChildClassify(s);
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


    public void getXianshiList(int id, int childId) {
        XianshiBO xianshiBO = new XianshiBO();
        xianshiBO.pageNum = 1;
        xianshiBO.pageSize = 1000;
        xianshiBO.categoryId = id + "";
        xianshiBO.subCategoryId = childId + "";
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
