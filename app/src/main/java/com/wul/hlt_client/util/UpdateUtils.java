package com.wul.hlt_client.util;

import android.os.Environment;
import android.view.View;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wul.hlt_client.api.DownloadResponseBody;
import com.wul.hlt_client.api.HttpResultSubscriber;
import com.wul.hlt_client.api.HttpServiceIml;
import com.wul.hlt_client.entity.VersionBo;
import com.wul.hlt_client.widget.AlertDialog;

import java.io.File;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * author : wuliang
 * e-mail : wuliang6661@163.com
 * date   : 2019/6/2111:14
 * desc   : App检查更新的工具类
 * version: 1.0
 */
public class UpdateUtils {


    public static void checkUpdate() {
        HttpServiceIml.getVersionInfo().subscribe(new HttpResultSubscriber<VersionBo>() {
            @Override
            public void onSuccess(VersionBo s) {
                if (s.getVersionCode() > AppUtils.getAppVersionCode()) {
                    new AlertDialog(AppManager.getAppManager().curremtActivity()).builder().setGone().
                            setMsg("发现新版本")
                            .setPositiveButton("立即更新", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    updateAPK(s);
                                }
                            })
                            .setCancelable(false).show();
                }
            }

            @Override
            public void onFiled(String message) {
                ToastUtils.showShort(message);
            }
        });
    }


    private static void updateAPK(VersionBo versionBO) {
        LogUtils.e("updateAPK");

        String loadUrl = versionBO.getVersionUrl();
        File file = new File(FILE_APK_PATH, "hitClient" + System.currentTimeMillis() + ".apk");
        boolean existsFile = FileUtils.createOrExistsFile(file);

        if (!existsFile) {
            ToastUtils.showShort("IO异常");
            return;
        }
        HttpServiceIml.downLoad(loadUrl, new DownloadResponseBody.DownloadListener() {
            @Override
            public void onProgress(String progress) {

//                progressView.setCurrentProgress(Float.valueOf(progress));
            }
        }, file).subscribe(new Subscriber<ResponseBody>() {

            @Override
            public void onCompleted() {

//                baseDialog.dismiss();
//                progressView.setCurrentProgress(100F);
                AppUtils.installApp(file);
                //LogUtils.e("安装");

            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort(e.getMessage());
            }

            @Override
            public void onNext(ResponseBody integer) {


            }
        });
    }

    /**
     * 更新apk存放地址
     */
    public final static String FILE_APK_PATH = getFolderPath() + "/_APK";

    /**
     * sdb/SynwayOSC
     */
    public static final String getFolderPath() {
        return Environment.getExternalStorageDirectory().getPath()
                + "/hitClient";
    }
}
