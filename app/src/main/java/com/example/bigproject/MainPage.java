package com.example.bigproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainPage extends AppCompatActivity {
    private Intent RoleDescription;
    private TextView title;
    private TextView description;
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        startService(new Intent(this, SecondMusicService.class));//打开这个页面就播放背景音乐

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        myWebView = findViewById(R.id.webView);

    }

    public void main_image(View v) {
        title.setVisibility(View.GONE);  //设置标题不可见
        description.setVisibility(View.GONE);//设置介绍不可见
        myWebView.setVisibility(View.VISIBLE);//设置视频可见

        //点击图片会停止背景音乐
        stopService(new Intent(MainPage.this, SecondMusicService.class));

        // 设置WebView播放外部视频
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://player.bilibili.com/player.html?isOutside=true&aid=203674910");
        //myWebView.loadUrl("https://cdnimg.gamekee.com/wiki2.0/video/148/72324/2023/0/4/605692.mp4");
    }

    public void goToRoleDescription(View v) {
        //点击角色介绍按钮也关闭音乐
        stopService(new Intent(MainPage.this, SecondMusicService.class));

        RoleDescription = new Intent("com.example.activity.ACTION_START");
        RoleDescription.addCategory("com.example.application.MYBIGPROJECT_CATEGORYTOSECONDPAGE");
        startActivity(RoleDescription);
        finish(); //用于结束当前的Activity并将其从任务堆栈中移除
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) { // 应用关闭时停止音乐服务
            stopService(new Intent(MainPage.this, SecondMusicService.class));
        }
    }
}