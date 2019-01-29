package com.wul.hlt_client.api;

import android.content.Context;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.wul.hlt_client.util.AppManager;
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
            String[] msgs = exception.message.split(" ");

            new AlertDialog(AppManager.getAppManager().curremtActivity()).builder().setGone().setMsg(exception.message)
                    .setNegativeButton("确定", null).show();
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
