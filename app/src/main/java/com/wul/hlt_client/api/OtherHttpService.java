package com.wul.hlt_client.api;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wuliang on 2017/3/20.
 * <p>
 * 所有外部请求的处理接口
 */

public interface OtherHttpService {

    /**
     * 微信的登陆获取数据请求
     */
    String WXurL = "https://api.weixin.qq.com/";

    @FormUrlEncoded
    @POST("sns/oauth2/access_token")
    Observable<String> getWxMessage(@Field("appid") String appid,
                                    @Field("secret") String secret,
                                    @Field("code") String code,
                                    @Field("grant_type") String grant_type);

    @FormUrlEncoded
    @POST("sns/userinfo")
    Observable<String> getUserMessage(@Field("access_token") String access_token,
                                      @Field("openid") String openid);


}
