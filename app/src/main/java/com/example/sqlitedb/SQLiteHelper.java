package com.example.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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

    /// user authentication
    public int userAuthencation(String email, String password) {

        int userId = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM userDetails WHERE email = '" +email+ "'password = '" +password+ "'";
        Cursor cursor = db.rawQuery(sql, null);

        try {
            if(cursor.moveToFirst()){
                userId = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            }

        }finally {
            cursor.close();
        }

        return userId;
    }

    /// user details
    public ArrayList<UserModelClass> userLogInData(int userId) {

        ArrayList<UserModelClass> userData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM userDetails WHERE id = '" + userId +"'";
        Cursor cursor = db.rawQuery(sql, null);

        try {
            if(cursor.moveToFirst()) {
                do {
                    // retrieving data from cursor into variables
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String loginEmail = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                    String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                    String loginPassword = cursor.getString(cursor.getColumnIndexOrThrow("password"));

                    // set data to object of UserModelClass
                    UserModelClass logInData = new UserModelClass();
                    logInData.setId(id);
                    logInData.setName(name);
                    logInData.setEmail(loginEmail);
                    logInData.setPhone(phone);
                    logInData.setPassword(loginPassword);

                    // add data to arrayList
                    userData.add(logInData);

                }while(cursor.moveToNext());
            }

        }finally {

            cursor.close();
        }

        return userData;
    }
}
