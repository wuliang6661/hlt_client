package com.wul.hlt_client.ui;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;

import com.blankj.utilcode.util.LogUtils;
import com.wul.hlt_client.R;

import java.util.TimerTask;

/**
 * Created by wuliang on 2018/12/22.
 * <p>
 * 计时器
 */

public class DowmTimer extends TimerTask {

    private long startTime, endTime;
    private Handler handler;
    private Context context;
    private MediaPlayer mediaPlayer;

    public DowmTimer(Context context, long startTime, long endTime, Handler handler) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.handler = handler;
        this.context = context;
    }

    @Override
    public void run() {
        long date = System.currentTimeMillis();
        if (startTime > date) {     //活动还没开始
            Message message = new Message();
            message.obj = getTime(startTime - date);
            message.what = 0x11;
            handler.sendMessage(message);
            if (getTime(startTime - date).equals("00:00:00")) {
                LogUtils.e("开始一次");
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }
                mediaPlayer = MediaPlayer.create(context, R.raw.miaosha_start);
                mediaPlayer.setOnCompletionListener(new MediaListener());
                mediaPlayer.start();
            }
        }
        if (date >= startTime && date <= endTime) {    //活动还没结束
            Message message = new Message();
            message.obj = getTime(endTime - date);
            message.what = 0x22;
            handler.sendMessage(message);
            if (getTime(endTime - date).equals("00:00:00")) {
                LogUtils.e("结束一次");
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }
                mediaPlayer = MediaPlayer.create(context, R.raw.miaosha_end);
                mediaPlayer.setOnCompletionListener(new MediaListener());
                mediaPlayer.start();
            }
        }
        if (date > endTime) {
            Message message = new Message();
            message.obj = getTime(endTime - date);
            message.what = 0x33;
            handler.sendMessage(message);
        }
    }

    private String getTime(long downTime) {
        long allScond = downTime / 1000;
        long hour = allScond / 3600;   //剩余小时
        long minute = (allScond - (hour * 3600)) / 60;
        long scond = allScond - (hour * 3600) - (minute * 60);
        return format(hour) + ":" + format(minute) + ":" + format(scond);
    }

    private String format(long time) {
        if (time <= 0) {
            return "00";
        }
        if (time < 10) {
            return "0" + time;
        }
        return time + "";
    }
}