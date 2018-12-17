package com.wul.hlt_client.api;

import com.wul.hlt_client.entity.BaseResult;
import com.wul.hlt_client.entity.HistoryOrderBo;
import com.wul.hlt_client.entity.OrderDetails;
import com.wul.hlt_client.entity.UnOrderBo;
import com.wul.hlt_client.entity.UserBo;

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
    @POST("/hct_webservice/app/greengrocer/login")
    Observable<BaseResult<UserBo>> login(@Body RequestBody body);

    /**
     * 查询商户历史订单列表
     */
    @POST("/hct_webservice/app/greengrocer/getGreengrocerHistoryOrderList")
    Observable<BaseResult<HistoryOrderBo>> getHistoryList(@Body RequestBody body);


    /**
     * 查询待接单列表
     */
    @POST("/hct_webservice/app/greengrocer/getGreengrocerUnOrderList")
    Observable<BaseResult<UnOrderBo>> getUnOrderList(@Body RequestBody body);


    /**
     * 接单
     */
    @POST("/hct_webservice/app/greengrocer/updateOrderStatus")
    Observable<BaseResult<String>> orderTaking(@Body RequestBody body);


    /**
     * 获取订单详情
     */
    @POST("/hct_webservice/app/greengrocer/getGreengrocerOrder")
    Observable<BaseResult<OrderDetails>> getGreengrocerOrder(@Body RequestBody body);


}
