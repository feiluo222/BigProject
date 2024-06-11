package com.example.bigproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RoleAdapter extends ArrayAdapter<Role> {
    private int resourceId;

    public RoleAdapter(Context context, int textViewResourceId, List<Role> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Role role = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView roleImage = (ImageView) view.findViewById(R.id.role_image);
        TextView roleName = (TextView) view.findViewById(R.id.role_name);
        WebView myWebView = (WebView) view.findViewById(R.id.web_view);
        roleName.setText(role.getName());
        roleImage.setImageResource((role.getImageId()));

        roleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebView.setVisibility(View.VISIBLE);//可见
                roleName.setVisibility(View.GONE); //不可见
                // 设置WebView播放外部视频
                WebSettings webSettings = myWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                myWebView.setWebViewClient(new WebViewClient());
                myWebView.loadUrl(role.getVideoUrl());
            }
        });
        return view;
    }
}
