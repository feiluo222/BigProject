package com.example.bigproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Intent goToMainPage, goToRegister, goToForget;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit,passwordEdit;
    private Button login;
    private CheckBox rememberPassword;

    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        rememberPassword = findViewById(R.id.remember_pass);
        login = findViewById(R.id.goToMainPage);

        dbHelper = new DatabaseHelper(getApplicationContext());
        db = dbHelper.getReadableDatabase();

        boolean isRemember = pref.getBoolean("remember_pass", false);
        //将账户号密码设置在文本框中
        if (isRemember) {
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPassword.setChecked(true);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();

//                //如果账号是admin，密码是123456，就认为登录成功
//                if(account.equals("admin") && password.equals("123456"))

                // 查询数据库中是否存在匹配的账户和密码
                Cursor cursor = db.query("users",
                        new String[]{"UserAccount", "UserPassword"},
                        "UserAccount = ? AND UserPassword = ?",
                        new String[]{account, password},
                        null, null, null);
                if (cursor.moveToFirst()) {
                    editor = pref.edit();
                    //检查复选框是否选中
                    if (rememberPassword.isChecked()) {
                        editor.putBoolean("remember_pass", true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    //隐式Intent
                    goToMainPage = new Intent("com.example.activity.ACTION_START");
                    goToMainPage.addCategory("com.example.application.MYBIGPROJECT_CATEGORYTOMAINPAGE");
                    startActivity(goToMainPage);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码错误，请重新输入",
                            Toast.LENGTH_SHORT).show();
                }
                cursor.close();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}