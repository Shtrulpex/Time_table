package com.example.hard;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper_days  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dayDb";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID = "_id";
    public static final String KEY_WEEKDAY = "weekday";
    public static final String KEY_HOURSTART = "hourstart";
    public static final String KEY_HOURSTOP = "hourstop";
    public static final String KEY_MINSTART = "minstart";
    public static final String KEY_MINSTOP = "minstop";
    public static final String KEY_EVENT = "event";
    public static final String KEY_NOTE = "note";
    public static final String KEY_REPEAT = "repeat";
    public static final String KEY_LOGIN = "login";

    public DBHelper_days(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID
                + " integer primary key," + KEY_LOGIN + "text," + KEY_WEEKDAY + " integer," + KEY_HOURSTART + " integer," + KEY_MINSTART + "integer," + KEY_HOURSTOP + "integer," + KEY_MINSTOP + "integer," + KEY_EVENT + "text," + KEY_NOTE + "text," + KEY_REPEAT + "integer" + ")");

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper_days.KEY_LOGIN, "123");
        db.insert(DBHelper_days.TABLE_CONTACTS, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(db);

    }
}
