package com.wul.hlt_client.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.wul.hlt_client.entity.ShopCarBO;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import me.yokeyword.fragmentation.Fragmentation;

/**
 * 作者 by wuliang 时间 16/10/26.
 * <p>
 * 程序的application
 */

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    public static SPUtils spUtils;

    public static String token;

    public static ShopCarBO shopCarBO;

    @Override
    public void onCreate() {
        super.onCreate();
        CustomActivityOnCrash.install(this);
//        CustomActivityOnCrash.setErrorActivityClass(CustomErrorActivity.class);
        /***初始化工具类*/
        Utils.init(this);
        spUtils = SPUtils.getInstance(TAG);
//        Fragmentation.getDefault().setMode(Fragmentation.BUBBLE);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
