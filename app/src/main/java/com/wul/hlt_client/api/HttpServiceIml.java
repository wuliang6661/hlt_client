package com.wul.hlt_client.api;

import com.wul.hlt_client.base.MyApplication;
import com.wul.hlt_client.entity.HistoryOrderBo;
import com.wul.hlt_client.entity.OrderDetails;
import com.wul.hlt_client.entity.UnOrderBo;
import com.wul.hlt_client.entity.UserBo;
import com.wul.hlt_client.util.rx.RxResultHelper;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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
        JSONObject object = new JSONObject();
        try {
            object.put("number", number);
            object.put("password", password);
            object.put("phone", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), object.toString());
        return getService().login(body).compose(RxResultHelper.<UserBo>httpRusult());
    }


    /**
     * 获取待接单订单列表
     */
    public static Observable<UnOrderBo> getUnOrderList() {
        JSONObject object = new JSONObject();
        try {
            object.put("token", MyApplication.spUtils.getString("token"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), object.toString());
        return getService().getUnOrderList(body).compose(RxResultHelper.<UnOrderBo>httpRusult());
    }


    /**
     * 获取历史订单列表
     */
    public static Observable<HistoryOrderBo> getHistoryList() {
        JSONObject object = new JSONObject();
        try {
            object.put("token", MyApplication.spUtils.getString("token"));
            object.put("pageNum", 1);
            object.put("pageSize", 1000);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), object.toString());
        return getService().getHistoryList(body).compose(RxResultHelper.<HistoryOrderBo>httpRusult());
    }


    /**
     * 接单
     */
    public static Observable<String> orderTaking(String orderId) {
        JSONObject object = new JSONObject();
        try {
            object.put("token", MyApplication.spUtils.getString("token"));
            object.put("id", orderId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), object.toString());
        return getService().orderTaking(body).compose(RxResultHelper.<String>httpRusult());
    }


    /**
     * 获取订单详情
     */
    public static Observable<OrderDetails> getOrderDetails(String orderId) {
        JSONObject object = new JSONObject();
        try {
            object.put("token", MyApplication.spUtils.getString("token"));
            object.put("id", orderId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), object.toString());
        return getService().getGreengrocerOrder(body).compose(RxResultHelper.<OrderDetails>httpRusult());
    }


}
