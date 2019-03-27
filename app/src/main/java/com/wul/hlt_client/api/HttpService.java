package com.wul.hlt_client.api;

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
import com.wul.hlt_client.entity.request.GetShopRequest;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wuliang on 2017/3/9.
 * <p>
 * 此处存放后台服务器的所有接口数据
 */

public interface HttpService {

    String URL = "http://47.98.53.141:8099";   //测试服
//    String URL = "http://192.168.1.200:8091/";  //测试服2
//    String URL = "http://api.open.yinghezhong.com/";  //正式环境
//    String URL = "http://mapi.open.yinghezhong.com/";  //正式环境2


    /**
     * 登录接口
     */
    @POST("/hct_webservice/app/address/login")
    Observable<BaseResult<UserBo>> login(@Body LoginBo body);

    /**
     * 注册接口
     */
    @POST("/hct_webservice/app/address/register")
    Observable<BaseResult<String>> register(@Body RegisterBO body);


    /**
     * 首页获取全部商品类型
     */
    @POST("/hct_webservice/app/address/home/getAllProductCategoryList")
    Observable<BaseResult<List<ClassifyBO>>> getCategorys(@Body PageBO body);

    /**
     * 根据商品类型id查询商品子类型
     */
    @POST("/hct_webservice/app/address/home/getAllProductSubCategorysByCategoryId")
    Observable<BaseResult<List<ClassifyBO>>> getChildCategorys(@Body ChildFlowBO childFlowBO);

    /**
     * 获取轮播广告
     */
    @POST("/hct_webservice/app/address/home/getAllAdvertiseList")
    Observable<BaseResult<List<BannerBo>>> getBanner(@Body BaseRequest body);

    /**
     * 获取常用清单
     */
    @POST("/hct_webservice/app/address/home/getCommonPurchaseList")
    Observable<BaseResult<List<ShopBO>>> getComminPurchase(@Body PageBO pageBO);

    /**
     * 限时抢购和促销商品列表
     */
    @POST("/hct_webservice/app/address/home/getSpikeProductAndPromotionProductList")
    Observable<BaseResult<XianShiBO>> getSpikeAndProductList(@Body XianshiBO body);

    /**
     * 获取城市公告
     */
    @POST("/hct_webservice/app/address/home/getAnnouncementByAddressCityId")
    Observable<BaseResult<List<CityGongGao>>> getCityGongGao(@Body BaseRequest body);

    /**
     * 获取城市列表
     */
    @POST("/hct_webservice/app/address/getAllCityList")
    Observable<BaseResult<List<CityBO>>> getCityList();

    /**
     * 查询城市所属区域
     */
    @POST("/hct_webservice/app/address/getAllRegionListByCityId")
    Observable<BaseResult<List<CityRegionBO>>> getQuyuByCity(@Body RegionBO regionBO);

    /**
     * 获取购物车商品列表
     */
    @POST("/hct_webservice/app/address/shoppingCart/getShoppingCartList")
    Observable<BaseResult<ShopCarBO>> getShopCarList(@Body BaseRequest body);

    /**
     * 增加购物车商品数量
     */
    @POST("/hct_webservice/app/address/shoppingCart/addProductQuantity")
    Observable<BaseResult<String>> addShopCarNum(@Body ShopCarSetBO body);


    /**
     * 减少购物车商品数量
     */
    @POST("/hct_webservice/app/address/shoppingCart/reduceProductQuantity")
    Observable<BaseResult<String>> reduceShopCarNum(@Body ShopCarSetBO body);

    /**
     * 清空购物车
     */
    @POST("/hct_webservice/app/address/shoppingCart/clearShoppingCartList")
    Observable<BaseResult<String>> clearShoppingCar(@Body BaseRequest request);


    /**
     * 检测商品是否被秒杀光
     */
    @POST("/hct_webservice/app/address/settlement/testSpikeProductCount")
    Observable<BaseResult<String>> testSpikeProduct(@Body BaseRequest body);

    /**
     * 结算时获取门店信息
     */
    @POST("/hct_webservice/app/address/settlement/getAddressInfo")
    Observable<BaseResult<AddressBO>> getAddressInfo(@Body BaseRequest body);

    /**
     * 获取订单结算清单
     */
    @POST("/hct_webservice/app/address/settlement/getShoppingCartListAndCalculationPrice")
    Observable<BaseResult<ShoppingCarBO>> getShoppingList(@Body ShoppingListBO listBO);

    /**
     * 确认订单
     */
    @POST("/hct_webservice/app/address/settlement/confirmOrder")
    Observable<BaseResult<String>> commitOrder(@Body CommitOrderBO commitOrderBO);


    /**
     * 获取结算时的金额
     */
    @POST("/hct_webservice/app/address/settlement/getMoney")
    Observable<BaseResult<MoneyBO>> getMoney(@Body ShoppingListBO listBO);

    /**
     * 搜索接口
     */
    @POST("/hct_webservice/app/address/home/search")
    Observable<BaseResult<XianShiBO>> searchList(@Body GetShopRequest request);

    /**
     * 根据子类型查询商品
     */
    @POST("/hct_webservice/app/address/home/getProductListByCategoryAndSubCategory")
    Observable<BaseResult<XianShiBO>> getShopList(@Body XianshiBO xianshiBO);

    /**
     * 获取门店信息
     */
    @POST("/hct_webservice/app/address/my/getAddressInfo")
    Observable<BaseResult<ShopInfoBO>> getShopInfo(@Body BaseRequest request);

    /**
     * 投诉建议
     */
    @POST("/hct_webservice/app/address/my/addFeedback")
    Observable<BaseResult<String>> addFeedback(@Body TousuBO tousuBO);


    /**
     * 获取我的订单列表(按日)
     */
    @POST("/hct_webservice/app/address/my/getMyOrderList")
    Observable<BaseResult<OrderDayBo>> getMyOrderList(@Body ScreenBO screenBO);


    /**
     * 获取我的订单列表(按周或月)
     */
    @POST("/hct_webservice/app/address/my/getMyOrderList")
    Observable<BaseResult<OrderMonthBO>> getMyOrderListByMonth(@Body ScreenBO screenBO);

    /**
     * 合算多个订单总计金额
     */
    @POST("/hct_webservice/app/address/my/getMoneyBySelectionOrder")
    Observable<BaseResult<String>> getMoneyBySelectionOrder(@Body SelectMoneyBO moneyBO);

    /**
     * 合并支付
     */
    @POST("/hct_webservice/app/address/my/combinedPayment")
    Observable<BaseResult<String>> combinePay(@Body SelectMoneyBO moneyBO);

    /**
     * 获取订单详情
     */
    @POST("/hct_webservice/app/address/my/getAddressOrder")
    Observable<BaseResult<OrderDetailsBO>> getOrderDetails(@Body GetOrderBO orderBO);

    /**
     * 取消订单
     */
    @POST("/hct_webservice/app/address/my/updateAddressOrderStatus")
    Observable<BaseResult<String>> cancleOrder(@Body GetOrderBO orderBO);

    /**
     * 点击申请退款之前判断
     */
    @POST("/hct_webservice/app/address/my/clickOrderRefundTest")
    Observable<BaseResult<String>> testRefund(@Body TuiKuanBO tuiKuanBO);


    /**
     * 申请退款
     */
    @POST("/hct_webservice/app/address/my/updateAddressOrderRefundStatus")
    Observable<BaseResult<String>> orderTuiKuan(@Body TuiKuanBO tuiKuanBO);

    /**
     * 检测时间是否在范围内
     */
    @POST("/hct_webservice/app/address/settlement/testSpikeDeliverDate")
    Observable<BaseResult<String>> testSpike(@Body TestTimeRequest request);
}
