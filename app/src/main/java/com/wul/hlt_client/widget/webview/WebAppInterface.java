package com.wul.hlt_client.widget.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;


/**
 * Created by wuliang on 2017/4/11.
 * <p>
 * 此处存放所有Html页面js调用的方法
 */

public class WebAppInterface {

    Context mContext;

    public WebAppInterface(Context context) {
        mContext = context;
    }

    /**
     * 此方法为例
     *
     * @param message
     */
    @JavascriptInterface
    public void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void logDebug(String logo) {
        LogUtils.i(logo);
    }


}
