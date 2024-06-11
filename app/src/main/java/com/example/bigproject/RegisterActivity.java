package com.example.bigproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private Intent goToLogin,goToForget;
    private EditText account,password,confitm_password,phone;
    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        account = (EditText)findViewById(R.id.account);
        password = (EditText)findViewById(R.id.password);
        confitm_password = (EditText)findViewById(R.id.confirm_password);
        phone = (EditText)findViewById(R.id.phone_num);

        dbHelper = new DatabaseHelper(getApplicationContext());
        db = dbHelper.getReadableDatabase();
    }

    public void register(View v){
        // 获取用户输入的数据
        String accountText = account.getText().toString();
        String passwordText = password.getText().toString();
        String confirmPasswordText = confitm_password.getText().toString();
        String phoneText = phone.getText().toString();

        // 检查是否所有字段都已填写
        if (accountText.isEmpty() || passwordText.isEmpty() || confirmPasswordText.isEmpty() || phoneText.isEmpty()) {
            Toast.makeText(this, "所有信息都必须填写", Toast.LENGTH_SHORT).show();
            return;
        }

        // 检查密码和确认密码是否一致
        if (!passwordText.equals(confirmPasswordText)) {
            Toast.makeText(this, "密码和确认密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }

        // 查询用户名是否已经存在
        Cursor cursor = db.query("users", new String[]{"UserAccount"}, "UserAccount = ?",
                         new String[]{accountText}, null, null, null);
        if (cursor.moveToFirst()) {
            Toast.makeText(this, "用户名已存在", Toast.LENGTH_SHORT).show();
            return;
        }
        cursor.close();

        // 插入新用户数据
        ContentValues userValues = new ContentValues();
        userValues.put("UserAccount", accountText);
        userValues.put("UserPassword", passwordText);
        userValues.put("UserPhone", phoneText);

        long result = db.insert("users", null, userValues);

        if (result == -1) {
            Toast.makeText(this, "注册失败，请重试", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            goToLogin = new Intent(this, LoginActivity.class);
            startActivity(goToLogin);
            finish();
        }
    }

    public void forget_pwd(View v) {
        goToForget = new Intent("com.example.activity.ACTION_START");
        goToForget.addCategory("com.example.application.MYBIGPROJECT_CATEGORYTOFORGETPWD");
        startActivity(goToForget);
        finish();
    }

    public void rlogin(View v){
        //返回登录
        goToLogin = new Intent(this, LoginActivity.class);
        startActivity(goToLogin);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
