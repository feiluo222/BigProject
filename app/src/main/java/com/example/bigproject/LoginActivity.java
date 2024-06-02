package com.example.bigproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Intent goToMainPage,goToRegister,goToForget;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        rememberPassword = (CheckBox) findViewById(R.id.remember_pass);
        login = (Button) findViewById(R.id.goToMainPage);
        boolean isRemember = pref.getBoolean("remember_pass",false);
        //将账户号密码设置在文本框中
        if(isRemember){
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPassword.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                //使用SharedPreferences 设置一个默认账户admin，密码123456
                if(account.equals("admin") && password.equals("123456")){
                    editor = pref.edit();
                    //检查复选框是否选中
                    if(rememberPassword.isChecked()){
                        editor.putBoolean("remember_pass",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else{
                        editor.clear();
                    }
                    editor.apply();
                    //隐式Intent
                    goToMainPage=new Intent("com.example.activity.ACTION_START");
                    goToMainPage.addCategory("com.example.application.MYBIGPROJECT_CATEGORYTOMAINPAGE");
                    startActivity(goToMainPage);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "账号或密码错误，请重新输入",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void RegisterBtn(View v) {
        goToRegister = new Intent("com.example.activity.ACTION_START");
        goToRegister.addCategory("com.example.application.MYBIGPROJECT_CATEGORYTOREGISTER");
        startActivity(goToRegister);
        finish();
    }
    public void ForgetBtn(View v) {
        goToForget = new Intent("com.example.activity.ACTION_START");
        goToForget.addCategory("com.example.application.MYBIGPROJECT_CATEGORYTOFORGETPWD");
        startActivity(goToForget);
        finish();
    }
}