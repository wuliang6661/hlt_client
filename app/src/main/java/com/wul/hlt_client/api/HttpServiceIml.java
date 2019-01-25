package com.wul.hlt_client.api;

import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.entity.AddressBO;
import com.wul.hlt_client.entity.BannerBo;
import com.wul.hlt_client.entity.BaseResult;
import com.wul.hlt_client.entity.CityBO;
import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.CityRegionBO;
import com.wul.hlt_client.entity.ClassifyBO;
import com.wul.hlt_client.entity.MoneyBO;
import com.wul.hlt_client.entity.OrderDayBo;
import com.wul.hlt_client.entity.OrderDetailsBO;
import com.wul.hlt_client.entity.OrderMonthBO;
import com.wul.hlt_client.entity.ShopBO;
import com.wul.hlt_client.entity.ShopCarBO;
import com.wul.hlt_client.entity.ShopInfoBO;
import com.wul.hlt_client.entity.ShoppingCarBO;
import com.wul.hlt_client.entity.TousuBO;
import com.wul.hlt_client.entity.UserBo;
import com.wul.hlt_client.entity.XianShiBO;
import com.wul.hlt_client.entity.request.BaseRequest;
import com.wul.hlt_client.entity.request.ChildFlowBO;
import com.wul.hlt_client.entity.request.CommitOrderBO;
import com.wul.hlt_client.entity.request.GetOrderBO;
import com.wul.hlt_client.entity.request.GetShopRequest;
import com.wul.hlt_client.entity.request.LoginBo;
import com.wul.hlt_client.entity.request.PageBO;
import com.wul.hlt_client.entity.request.RegionBO;
import com.wul.hlt_client.entity.request.RegisterBO;
import com.wul.hlt_client.entity.request.ScreenBO;
import com.wul.hlt_client.entity.request.SelectMoneyBO;
import com.wul.hlt_client.entity.request.ShopCarSetBO;
import com.wul.hlt_client.entity.request.ShoppingListBO;
import com.wul.hlt_client.entity.request.TestTimeRequest;
import com.wul.hlt_client.entity.request.TuiKuanBO;
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
     * 清空购物车
     */
    public static Observable<String> clearShopCar() {
        BaseRequest request = new BaseRequest();
        request.token = MyApplication.token;
        return getService().clearShoppingCar(request).compose(RxResultHelper.httpRusult());
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

    /**
     * 获取结算时的金额
     */
    public static Observable<MoneyBO> getMoney(int orderType, int blanceStatus) {
        ShoppingListBO listBO = new ShoppingListBO();
        listBO.orderType = orderType;
        listBO.balancePayStatus = blanceStatus;
        listBO.token = MyApplication.token;
        return getService().getMoney(listBO).compose(RxResultHelper.httpRusult());
    }

    /**
     * 搜索商品
     */
    public static Observable<XianShiBO> searchList(String shopName) {
        GetShopRequest request = new GetShopRequest();
        request.token = MyApplication.token;
        request.pageNum = 1;
        request.pageSize = 1000;
        request.productName = shopName;
        return getService().searchList(request).compose(RxResultHelper.httpRusult());
    }


    /**
     * 根据子类型查询商品
     */
    public static Observable<XianShiBO> getShopList(XianshiBO xianshiBO) {
        xianshiBO.token = MyApplication.token;
        return getService().getShopList(xianshiBO).compose(RxResultHelper.httpRusult());
    }

    /**
     * 获取门店信息
     */
    public static Observable<ShopInfoBO> getShopInfo() {
        BaseRequest request = new BaseRequest();
        request.token = MyApplication.token;
        return getService().getShopInfo(request).compose(RxResultHelper.httpRusult());
    }


    /**
     * 投诉建议
     */
    public static Observable<String> tousu(String content) {
        TousuBO tousuBO = new TousuBO();
        tousuBO.token = MyApplication.token;
        tousuBO.setContent(content);
        return getService().addFeedback(tousuBO).compose(RxResultHelper.httpRusult());
    }


    /**
     * 获取我的订单(按日)
     */
    public static Observable<OrderDayBo> getMyOrderList(ScreenBO screenBO) {
        screenBO.token = MyApplication.token;
        return getService().getMyOrderList(screenBO).compose(RxResultHelper.httpRusult());
    }

    /**
     * 获取我的订单(按周、月)
     */
    public static Observable<OrderMonthBO> getMyOrderListByMonth(ScreenBO screenBO) {
        screenBO.token = MyApplication.token;
        return getService().getMyOrderListByMonth(screenBO).compose(RxResultHelper.httpRusult());
    }

    /**
     * 获取多个订单总计金额
     */
    public static Observable<String> getMoneyBySelector(String ids) {
        SelectMoneyBO moneyBO = new SelectMoneyBO();
        moneyBO.token = MyApplication.token;
        moneyBO.ids = ids;
        return getService().getMoneyBySelectionOrder(moneyBO).compose(RxResultHelper.httpRusult());
    }

    /**
     * 合并支付
     */
    public static Observable<String> combinePay(String ids) {
        SelectMoneyBO moneyBO = new SelectMoneyBO();
        moneyBO.token = MyApplication.token;
        moneyBO.ids = ids;
        return getService().combinePay(moneyBO).compose(RxResultHelper.httpRusult());
    }

    /**
     * 获取订单详情
     */
    public static Observable<OrderDetailsBO> getOrderDetails(int id) {
        GetOrderBO orderBO = new GetOrderBO();
        orderBO.id = id;
        orderBO.token = MyApplication.token;
        return getService().getOrderDetails(orderBO).compose(RxResultHelper.httpRusult());
    }

    /**
     * 取消订单
     */
    public static Observable<String> cancleOrder(int id) {
        GetOrderBO orderBO = new GetOrderBO();
        orderBO.id = id;
        orderBO.token = MyApplication.token;
        return getService().cancleOrder(orderBO).compose(RxResultHelper.httpRusult());
    }

    /**
     * 申请退款
     */
    public static Observable<String> orderTuiKuan(int id, String money) {
        TuiKuanBO tuiKuanBO = new TuiKuanBO();
        tuiKuanBO.token = MyApplication.token;
        tuiKuanBO.setId(id);
        tuiKuanBO.setRefundAmount(money);
        return getService().orderTuiKuan(tuiKuanBO).compose(RxResultHelper.httpRusult());
    }

    /**
     * 检测时间是否在范围里
     */
    public static Observable<String> testSkipe(TestTimeRequest request) {
        request.token = MyApplication.token;
        return getService().testSpike(request).compose(RxResultHelper.httpRusult());
    }

}
