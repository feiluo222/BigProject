package com.example.bigproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPwdActivity extends AppCompatActivity {

    private EditText account,password,confitm_password,phone;
    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        account = (EditText)findViewById(R.id.account);
        password = (EditText)findViewById(R.id.password);
        confitm_password = (EditText)findViewById(R.id.affirm_password);
        phone = (EditText)findViewById(R.id.phone_num);

        dbHelper = new DatabaseHelper(this);

    }
    public void reset_password(View v) {
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
        // 验证用户名和手机号是否存在
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("users", null, "UserAccount=? AND UserPhone=?",
                new String[]{accountText, phoneText}, null, null, null);
        if (cursor.moveToFirst()) {
            // 更新密码
            ContentValues values = new ContentValues();
            values.put("UserPassword",passwordText);
            db.update("users", values, "UserAccount=? AND UserPhone=?",
                    new String[]{accountText, phoneText});
            Toast.makeText(this, "密码重置成功", Toast.LENGTH_SHORT).show();
            Intent goToLogin = new Intent(this, LoginActivity.class);
            startActivity(goToLogin);
            finish();
        } else {
            Toast.makeText(this, "用户名或手机号不正确", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        db.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
