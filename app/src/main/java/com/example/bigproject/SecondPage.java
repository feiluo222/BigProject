package com.example.bigproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
public class SecondPage extends AppCompatActivity {
    private Intent goToLogin;
    private RoleAdapter roleAdapter;
    private List<Role> roleList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        initRoles();
        roleAdapter = new RoleAdapter(SecondPage.this,
                R.layout.role_item, roleList);
        ListView listView = (ListView) findViewById(R.id.list_view_game);
        listView.setAdapter(roleAdapter);
    }
    private void initRoles() {
        Role kalin = new Role("觉醒：战争女神-卡琳·冯", R.drawable.kalin,"https://player.bilibili.com/player.html?isOutside=true&aid=995356405");
        roleList.add(kalin);
        Role qyxd = new Role("觉醒：疫病神之刃-七原千冬", R.drawable.qyxd,"https://player.bilibili.com/player.html?isOutside=true&aid=819446735");
        roleList.add(qyxd);
        Role alkxy = new Role("觉醒-寂静的艾丽克西亚", R.drawable.alkxy,"https://player.bilibili.com/player.html?isOutside=true&aid=233871562");
        roleList.add(alkxy);
        Role ljn = new Role("觉醒-蕾佳娜", R.drawable.ljn,"https://player.bilibili.com/player.html?isOutside=true&aid=389517074");
        roleList.add(ljn);
        Role lfmtn = new Role("加格谢布拉·伊芙·迈特纳", R.drawable.lfmtn,"https://player.bilibili.com/player.html?isOutside=true&aid=365774278");
        roleList.add(lfmtn);
        Role lmn = new Role("觉醒-刘米娜", R.drawable.lmn,"https://player.bilibili.com/player.html?isOutside=true&aid=844800719");
        roleList.add(lmn);
    }

    public void return_login(View v){
        //返回登录
        goToLogin = new Intent(this, LoginActivity.class);
        startActivity(goToLogin);
        finish();
    }
}
