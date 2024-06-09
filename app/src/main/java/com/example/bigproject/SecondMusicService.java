package com.example.bigproject;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

//这个文件我是问AI的，我想在登陆，注册，忘记密码界面加一个打开就播放音乐的效果，以登入密码为主页，进行播放，
// 到其他两个界面不会使得音乐停止，只有按登入界面的登入按钮，到下一页的时候，才会停止
public class SecondMusicService extends Service {
   private static final String TAG = "SecondMusicService";  // 常用于日志记录 定义了一个名为 TAG 的字符串常量，值为 SecondMusicService
    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.wolvesbane);
        mediaPlayer.setLooping(true); // 设置循环播放
        Log.d(TAG, "Music Service Created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        Log.d(TAG, "Music Service Started");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        Log.d(TAG, "Music Service Destroyed");
    }
}

