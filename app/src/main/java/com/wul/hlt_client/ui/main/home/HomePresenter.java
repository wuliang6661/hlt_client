package com.wul.hlt_client.ui.main.home;

import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.entity.ClassifyBO;
import com.wul.hlt_client.entity.request.PageBO;
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
        HttpServiceIml.getBanner().subscribe(new HttpResultSubscriber<String>() {
            @Override
            public void onSuccess(String s) {

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
