package com.example.android.bodypumplog.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.bodypumplog.data.RecordContract.RecordEntry;
/**
 * Created by eng on 12/28/2016.
 */

public class RecordDbHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME  = "record.db";
    private static final int DATABASE_VERSION = 1;

    public RecordDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_RECORD_TABLE =
                "CREATE TABLE " + RecordEntry.TABLE_NAME + " (" +
                RecordEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RecordEntry.COLUMN_USER + " TEXT NOT NULL, " +
                RecordEntry.COLUMN_DATE + " DATE NOT NULL, " +
                RecordEntry.COLUMN_WARMUP + " DOUBLE NULL, " +
                RecordEntry.COLUMN_SQUATS + " DOUBLE NULL, " +
                RecordEntry.COLUMN_CHEST + " DOUBLE NULL, " +
                RecordEntry.COLUMN_BACK + " DOUBLE NULL, " +
                RecordEntry.COLUMN_TRICEPS + " DOUBLE NULL, " +
                RecordEntry.COLUMN_BICEPS + " DOUBLE NULL, " +
                RecordEntry.COLUMN_LUNGES + " DOUBLE NULL, " +
                RecordEntry.COLUMN_SHOULDERS + " DOUBLE NULL, " +
                RecordEntry.COLUMN_ABS + " DOUBLE NULL)";
        db.execSQL(SQL_CREATE_RECORD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RecordEntry.TABLE_NAME);
        onCreate(db);
    }
}
