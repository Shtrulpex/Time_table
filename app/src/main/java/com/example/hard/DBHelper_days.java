package com.example.hard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper_days  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dayDb";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID = "_id";
    public static final String KEY_WEEKDAY = "weekday";
    public static final String KEY_TIME = "time";
    public static final String KEY_EVENT = "event";
    public static final String KEY_NOTE = "note";
    public static final String KEY_REPEAT = "repeat";

    public DBHelper_days(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID
                + " integer primary key," + KEY_WEEKDAY + " text," + KEY_TIME + " text" + KEY_EVENT + "text" + KEY_NOTE + "text" + KEY_REPEAT + "integer" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(db);

    }
}
