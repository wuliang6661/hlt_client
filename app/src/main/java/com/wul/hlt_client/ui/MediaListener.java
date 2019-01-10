package com.wul.hlt_client.ui;

import android.media.MediaPlayer;

public class MediaListener implements MediaPlayer.OnCompletionListener {

    int count = 1;


    @Override
    public void onCompletion(MediaPlayer mp) {
        if (count <= 2) {
            mp.start();
            count++;
        }
    }
}
