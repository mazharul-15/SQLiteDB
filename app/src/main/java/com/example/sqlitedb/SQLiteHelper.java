package com.example.sqlitedb;

import android.content.Context;
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
        String createTableQuery = "CREATE TABLE userDetails(id PRIMARY KEY AUTOINCREMENT," +
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

}
