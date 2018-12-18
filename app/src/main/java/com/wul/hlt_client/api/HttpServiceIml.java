package com.wul.hlt_client.api;

import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.entity.ClassifyBO;
import com.wul.hlt_client.entity.UserBo;
import com.wul.hlt_client.entity.request.BaseRequest;
import com.wul.hlt_client.entity.request.LoginBo;
import com.wul.hlt_client.entity.request.PageBO;
import com.wul.hlt_client.util.rx.RxResultHelper;

import java.util.List;

import rx.Observable;

/**
 * Created by wuliang on 2017/4/19.
 * <p>
 * 所有网络请求方法
 */

public class HttpServiceIml {

    private static HttpService service;

    /**
     * 获取代理对象
     *
     * @return
     */
    public static HttpService getService() {
        if (service == null)
            service = ApiManager.getInstance().configRetrofit(HttpService.class, HttpService.URL);
        return service;
    }


    /**
     * 登录接口
     */
    public static Observable<UserBo> login(String number, String phone, String password) {
        LoginBo loginBo = new LoginBo();
        loginBo.number = number;
        loginBo.password = password;
        loginBo.contactPhone = phone;
        return getService().login(loginBo).compose(RxResultHelper.<UserBo>httpRusult());
    }

    /**
     * 首页获取全部商品类型
     */
    public static Observable<List<ClassifyBO>> getCategorys(PageBO pageBO) {
        pageBO.token = MyApplication.token;
        return getService().getCategorys(pageBO).compose(RxResultHelper.<List<ClassifyBO>>httpRusult());
    }

    /**
     * 获取轮播广告
     */
    public static Observable<String> getBanner() {
        BaseRequest request = new BaseRequest();
        request.token = MyApplication.token;
        return getService().getBanner(request).compose(RxResultHelper.<String>httpRusult());
    }
}
