package com.wul.hlt_client.api;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.wul.hlt_client.util.AppManager;
import com.wul.hlt_client.util.PhoneUtils;
import com.wul.hlt_client.widget.AlertDialog;

import rx.Subscriber;

/**
 * Created by wuliang on 2018/11/13.
 * <p>
 * 自定义的Subscriber订阅者
 */

public abstract class HttpResultSubscriber<T> extends Subscriber<T> {

    /**
     * 滚动的菊花
     */
    private SVProgressHUD svProgressHUD;


    public HttpResultSubscriber() {

    }

    public HttpResultSubscriber(Context context) {
        svProgressHUD = new SVProgressHUD(context);
        svProgressHUD.showWithStatus("加载中...", SVProgressHUD.SVProgressHUDMaskType.BlackCancel);
    }


    @Override
    public void onNext(T t) {
        onSuccess(t);
        if (svProgressHUD != null && svProgressHUD.isShowing()) {
            svProgressHUD.dismiss();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (svProgressHUD != null && svProgressHUD.isShowing()) {
            svProgressHUD.dismiss();
        }
        if (e instanceof DialogException) {
            DialogException exception = (DialogException) e;

            new AlertDialog(AppManager.getAppManager().curremtActivity()).builder().setGone().setMsg(exception.message)
                    .setNegativeButton("确定", null).show();
        } else if (e instanceof DialogCallException) {
            DialogCallException exception = (DialogCallException) e;
            String[] msgs = exception.message.split("_");
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < msgs.length; i++) {
                if (i == msgs.length - 1) {
                    builder.append("（").append(msgs[i]).append("）");
                } else {
                    builder.append(msgs[i]).append("\n");
                }
            }
            new AlertDialog(AppManager.getAppManager().curremtActivity()).builder().setGone().setMsg(builder.toString())
                    .setNegativeButton("取消", null)
                    .setPositiveButton("拨打", v -> PhoneUtils.callPhone(msgs[msgs.length - 1])).show();
        } else {
            onFiled(e.getMessage());
        }
    }





    @Override
    public void onCompleted() {

    }

    public abstract void onSuccess(T t);

    public abstract void onFiled(String message);
}
