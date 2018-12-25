package com.wul.hlt_client.api;

import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.entity.AddressBO;
import com.wul.hlt_client.entity.BannerBo;
import com.wul.hlt_client.entity.CityBO;
import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.CityRegionBO;
import com.wul.hlt_client.entity.ClassifyBO;
import com.wul.hlt_client.entity.ShopBO;
import com.wul.hlt_client.entity.ShopCarBO;
import com.wul.hlt_client.entity.ShoppingCarBO;
import com.wul.hlt_client.entity.UserBo;
import com.wul.hlt_client.entity.XianShiBO;
import com.wul.hlt_client.entity.request.BaseRequest;
import com.wul.hlt_client.entity.request.ChildFlowBO;
import com.wul.hlt_client.entity.request.CommitOrderBO;
import com.wul.hlt_client.entity.request.LoginBo;
import com.wul.hlt_client.entity.request.PageBO;
import com.wul.hlt_client.entity.request.RegionBO;
import com.wul.hlt_client.entity.request.RegisterBO;
import com.wul.hlt_client.entity.request.ShopCarSetBO;
import com.wul.hlt_client.entity.request.ShoppingListBO;
import com.wul.hlt_client.entity.request.XianshiBO;
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
        return getService().login(loginBo).compose(RxResultHelper.httpRusult());
    }

    /**
     * 注册接口
     */
    public static Observable<String> register(RegisterBO registerBO) {
        return getService().register(registerBO).compose(RxResultHelper.httpRusult());
    }


    /**
     * 首页获取全部商品类型
     */
    public static Observable<List<ClassifyBO>> getCategorys(PageBO pageBO) {
        pageBO.token = MyApplication.token;
        return getService().getCategorys(pageBO).compose(RxResultHelper.httpRusult());
    }

    /**
     * 根据商品类型id查询商品子类型
     */
    public static Observable<List<ClassifyBO>> getChildCategorys(int cateId) {
        ChildFlowBO childFlowBO = new ChildFlowBO();
        childFlowBO.token = MyApplication.token;
        childFlowBO.categoryId = cateId;
        return getService().getChildCategorys(childFlowBO).compose(RxResultHelper.httpRusult());
    }


    /**
     * 获取轮播广告
     */
    public static Observable<List<BannerBo>> getBanner() {
        BaseRequest request = new BaseRequest();
        request.token = MyApplication.token;
        return getService().getBanner(request).compose(RxResultHelper.httpRusult());
    }

    /**
     * 获取常用清单
     */
    public static Observable<List<ShopBO>> getComstonList(PageBO pageBO) {
        pageBO.token = MyApplication.token;
        return getService().getComminPurchase(pageBO).compose(RxResultHelper.httpRusult());
    }

    /**
     * 获取限时清单
     */
    public static Observable<XianShiBO> getXianshiList(XianshiBO pageBO) {
        pageBO.token = MyApplication.token;
        return getService().getSpikeAndProductList(pageBO).compose(RxResultHelper.httpRusult());
    }

    /**
     * 获取城市公告
     */
    public static Observable<List<CityGongGao>> getCityGongGao() {
        BaseRequest request = new BaseRequest();
        request.token = MyApplication.token;
        return getService().getCityGongGao(request).compose(RxResultHelper.httpRusult());
    }

    /**
     * 获取城市列表
     */
    public static Observable<List<CityBO>> getAllCity() {
        return getService().getCityList().compose(RxResultHelper.httpRusult());
    }


    /**
     * 根据城市ID获取区域
     */
    public static Observable<List<CityRegionBO>> getRegionByCity(int cityId) {
        RegionBO regionBO = new RegionBO();
        regionBO.token = MyApplication.token;
        regionBO.cityId = cityId;
        return getService().getQuyuByCity(regionBO).compose(RxResultHelper.httpRusult());
    }


    /**
     * 获取购物车商品
     */
    public static Observable<ShopCarBO> getShopCarList() {
        BaseRequest request = new BaseRequest();
        request.token = MyApplication.token;
        return getService().getShopCarList(request).compose(RxResultHelper.httpRusult());
    }


    /**
     * 添加商品到购物车
     */
    public static Observable<String> addShopCar(int shopId) {
        ShopCarSetBO setBO = new ShopCarSetBO();
        setBO.productId = shopId;
        setBO.token = MyApplication.token;
        return getService().addShopCarNum(setBO).compose(RxResultHelper.httpRusult());
    }

    /**
     * 减伤购物车商品数量
     */
    public static Observable<String> removeShop(int shopId) {
        ShopCarSetBO setBO = new ShopCarSetBO();
        setBO.productId = shopId;
        setBO.token = MyApplication.token;
        return getService().reduceShopCarNum(setBO).compose(RxResultHelper.httpRusult());
    }


    /**
     * 检测商品是否被秒杀光
     */
    public static Observable<String> testSpike() {
        BaseRequest request = new BaseRequest();
        request.token = MyApplication.token;
        return getService().testSpikeProduct(request).compose(RxResultHelper.httpRusult());
    }

    /**
     * 去结算时获取用户信息
     */
    public static Observable<AddressBO> getAddressInfo() {
        BaseRequest request = new BaseRequest();
        request.token = MyApplication.token;
        return getService().getAddressInfo(request).compose(RxResultHelper.httpRusult());
    }

    /**
     * 获取订单结算清单
     */
    public static Observable<ShoppingCarBO> getShoppingList(int orderType) {
        ShoppingListBO listBO = new ShoppingListBO();
        listBO.orderType = orderType;
        listBO.token = MyApplication.token;
        return getService().getShoppingList(listBO).compose(RxResultHelper.httpRusult());
    }

    /**
     * 确认订单
     */
    public static Observable<String> commitOrder(CommitOrderBO commitOrderBO) {
        commitOrderBO.token = MyApplication.token;
        return getService().commitOrder(commitOrderBO).compose(RxResultHelper.httpRusult());
    }


}
