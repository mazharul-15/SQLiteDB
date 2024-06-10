package com.example.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE = "SQLiteDB";
    private static final int VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {

        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE userDetails(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                            "name TEXT, " +
                                                            "email TEXT, " +
                                                            "phone TEXT, " +
                                                            "password TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS userDetails");
        onCreate(db);
    }

    public boolean userRegistrationDataInsert(String name, String email, String phone, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        boolean registrationStatus = false;

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("phone", phone);
        values.put("password", password);

        long  status = db.insert("userDetails", null, values);
        db.close();

        if(status > 0) registrationStatus = true;
        return registrationStatus;
    }

    public Cursor userLogInData(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM userDetails WHERE email = '"+email+"' AND password = '"+password+"'";
        Cursor cursor = db.rawQuery(sql, null);

        return cursor;
    }
}
