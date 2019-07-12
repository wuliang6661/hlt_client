package com.wul.hlt_client.util.rx;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;


import com.blankj.utilcode.util.ToastUtils;
import com.wul.hlt_client.api.DialogCallException;
import com.wul.hlt_client.api.DialogException;
import com.wul.hlt_client.entity.BaseResult;
import com.wul.hlt_client.ui.login.LoginActivity;
import com.wul.hlt_client.util.AppManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 作者 by wuliang 时间 16/11/24.
 */

public class RxResultHelper {
    private static final String TAG = "RxResultHelper";

    public static <T> Observable.Transformer<BaseResult<T>, T> httpRusult() {
        return apiResponseObservable -> apiResponseObservable.flatMap(
                (Func1<BaseResult<T>, Observable<T>>) mDYResponse -> {
                    Log.d(TAG, "call() called with: mDYResponse = [" + mDYResponse + "]");
                    if (mDYResponse.surcess()) {
                        return createData(mDYResponse.getData());
                    } else if (mDYResponse.getCode() == 421) {   //重新登录
                        Activity activity = AppManager.getAppManager().curremtActivity();
                        Intent intent = new Intent(activity, LoginActivity.class);
                        ToastUtils.showShort("登录已过期，请重新登录！");
                        AppManager.getAppManager().finishAllActivity();
                        activity.startActivity(intent);
                        return Observable.error(new RuntimeException("登录已过期，请重新登录！"));
                    } else if (mDYResponse.getCode() == 399) {   //弹窗提示
                        return Observable.error(new DialogException(mDYResponse.getMsg()));
                    } else if (mDYResponse.getCode() == 398) {  //可拨打电话的弹窗
                        return Observable.error(new DialogCallException(mDYResponse.getMsg()));
                    } else {
                        return Observable.error(new RuntimeException(mDYResponse.getMsg()));
                    }
                }
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    private static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }


    public static Observable.Transformer<ResponseBody, ResponseBody> downRequest(File file) {
        return apiResponseObservable -> apiResponseObservable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .flatMap(new Func1<ResponseBody, Observable<ResponseBody>>() {
                    @Override
                    public Observable<ResponseBody> call(ResponseBody responseBody) {
                        return write2File(responseBody, file);

                    }
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    private static Observable<ResponseBody> write2File(ResponseBody body, File file) {
        return Observable.create(subscriber -> {
            try {
                InputStream inputStream = body.byteStream();
                //long totalSize = body.contentLength();
                // long currentLength = 0;

                FileOutputStream fos = new FileOutputStream(file);
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                byte[] buffer = new byte[1024];
                int len;

                while ((len = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    fos.flush();
                    //currentLength += len;
                    //LogUtils.e("write2File", currentLength + "");
                    //subscriber.onNext((int) (100 * currentLength / totalSize));
                }
                fos.close();
                bis.close();
                inputStream.close();
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

}
