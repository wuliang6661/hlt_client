package com.wul.hlt_client.ui;

import android.media.MediaPlayer;

import com.blankj.utilcode.util.LogUtils;

public class MediaListener implements MediaPlayer.OnCompletionListener {

    private int count = 1;


    @Override
    public void onCompletion(MediaPlayer mp) {
        if (count <= 2) {
            LogUtils.e("循环一次啦~~~~~~");
            mp.start();
            count++;
        }
    }
}
