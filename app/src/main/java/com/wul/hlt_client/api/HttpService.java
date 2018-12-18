package com.wul.hlt_client.api;

import com.wul.hlt_client.entity.BaseResult;
import com.wul.hlt_client.entity.HistoryOrderBo;
import com.wul.hlt_client.entity.OrderDetails;
import com.wul.hlt_client.entity.UnOrderBo;
import com.wul.hlt_client.entity.UserBo;
import com.wul.hlt_client.entity.request.BaseRequest;
import com.wul.hlt_client.entity.request.LoginBo;
import com.wul.hlt_client.entity.request.PageBO;
import com.wul.hlt_client.entity.request.RegisterBO;

import okhttp3.RequestBody;
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
    Observable<BaseResult<String>> getCategorys(@Body PageBO body);

    /**
     * 获取轮播广告
     */
    @POST("/hct_webservice/app/address/home/getAllAdvertiseList")
    Observable<BaseResult<String>> getBanner(@Body BaseRequest body);





}
