package com.example.hard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper_days1  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "daysDb";
    public static final String TABLE_DAYS = "days";

    public static final String KEY_ID = "_id";
    public static final String KEY_WEEKDAY = "weekday";
    public static final String KEY_HOURSTART = "hourst";
    public static final String KEY_HOURSTOP = "hours";
    public static final String KEY_MINSTART = "minstart";
    public static final String KEY_MINSTOP = "minsto";
    public static final String KEY_EVENT = "event";
    public static final String KEY_NOTE = "note";
    public static final String KEY_REP = "rep";
    public static final String KEY_LOGIN = "login";

    public DBHelper_days1(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_DAYS + "(" + KEY_ID
                + " INTEGER primary key," + KEY_LOGIN
                + " TEXT," + KEY_EVENT
                + " TEXT,"+ KEY_HOURSTART
                + " INTEGER," + KEY_HOURSTOP
                + " INTEGER," + KEY_MINSTART
                + " INTEGER," + KEY_MINSTOP
                + " INTEGER," + KEY_WEEKDAY
                + " INTEGER," + KEY_REP
                + " INTEGER," + KEY_NOTE + "TEXT" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_DAYS);

        onCreate(db);

    }
}
