package com.wul.hlt_client.ui;

import android.media.MediaPlayer;

public class MediaListener implements MediaPlayer.OnCompletionListener {

    int count = 1;


    @Override
    public void onCompletion(MediaPlayer mp) {
        if (count > 3) {
            count = 4;
            mp.stop();
            mp.setLooping(false);
            return;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mp.start();
        mp.setLooping(true);
        count++;
    }
}
