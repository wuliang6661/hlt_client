package com.wul.hlt_client.api;

import com.wul.hlt_client.entity.BannerBo;
import com.wul.hlt_client.entity.BaseResult;
import com.wul.hlt_client.entity.CityGongGao;
import com.wul.hlt_client.entity.ClassifyBO;
import com.wul.hlt_client.entity.ShopBO;
import com.wul.hlt_client.entity.UserBo;
import com.wul.hlt_client.entity.request.BaseRequest;
import com.wul.hlt_client.entity.request.LoginBo;
import com.wul.hlt_client.entity.request.PageBO;
import com.wul.hlt_client.entity.request.RegisterBO;
import com.wul.hlt_client.entity.request.XianshiBO;

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
//    String URL = "http://mapi.platform.yinghezhong.com/";  //测试服2
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
    Observable<BaseResult<List<ShopBO>>> getSpikeAndProductList(@Body XianshiBO body);

    /**
     * 获取城市公告
     */
    @POST("/hct_webservice/app/address/home/getAnnouncementByAddressCityId")
    Observable<BaseResult<List<CityGongGao>>> getCityGongGao(@Body BaseRequest body);
}
