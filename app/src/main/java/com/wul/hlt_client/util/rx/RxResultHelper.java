package com.wul.hlt_client.util.rx;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;


import com.blankj.utilcode.util.ToastUtils;
import com.wul.hlt_client.entity.BaseResult;
import com.wul.hlt_client.ui.login.LoginActivity;
import com.wul.hlt_client.util.AppManager;

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
        return new Observable.Transformer<BaseResult<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseResult<T>> apiResponseObservable) {
                return apiResponseObservable.flatMap(
                        new Func1<BaseResult<T>, Observable<T>>() {
                            @Override
                            public Observable<T> call(BaseResult<T> mDYResponse) {
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
                                } else {
                                    return Observable.error(new RuntimeException(mDYResponse.getMsg()));
                                }
                            }
                        }
                ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
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
}
