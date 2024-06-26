package com.example.bigproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "wengjunye2311722";
    private static final int DB_VERSION = 2;
    private Context context;

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE users (UserNo INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "UserAccount TEXT, "
                    + "UserPassword TEXT, "
                    + "UserPhone TEXT);");
            insertUser(db, "admin", "123456", "1524-9823-8754");
        }
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE USERS ADD COLUMN FAVORITE NUMERIC;");
        }
    }

    private static void insertUser(SQLiteDatabase db, String account,
                                    String password, String phone) {
        ContentValues userValues = new ContentValues();
        userValues.put("UserAccount", account);
        userValues.put("UserPassword", password);
        userValues.put("UserPhone", phone);
        db.insert("users", null, userValues);
    }

}
