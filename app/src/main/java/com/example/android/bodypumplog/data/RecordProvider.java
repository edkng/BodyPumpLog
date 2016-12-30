package com.example.android.bodypumplog.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.example.android.bodypumplog.data.RecordContract.RecordEntry.TABLE_NAME;

/**
 * Created by eng on 12/28/2016.
 */

public class RecordProvider extends ContentProvider{
    public static final int CODE_RECORD = 100;
    public static final int CODE_RECORD_WITH_DATE = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private RecordDbHelper mOpenHelper;

    public static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = RecordContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, RecordContract.PATH_RECORDS, CODE_RECORD);
        matcher.addURI(authority, RecordContract.PATH_RECORDS + "/#", CODE_RECORD_WITH_DATE);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new RecordDbHelper(getContext());
        return true;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch(match) {
            case CODE_RECORD:
                long id = db.insert(TABLE_NAME, null, values);
                if( id > 0 ) {
                    returnUri = ContentUris.withAppendedId(RecordContract.RecordEntry.CONTENT_URI, id);
                } else {
                    throw new SQLException("Failed to insert: " + uri);
                }
                break;
            //case CODE_RECORD_WITH_DATE:
            //    break;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        Cursor retCursor;

        switch(match) {
            case CODE_RECORD:
                retCursor = db.query(TABLE_NAME
                        ,projection
                        ,selection
                        ,selectionArgs
                        ,null
                        ,null
                        ,sortOrder);
                break;
            //case CODE_RECORD_WITH_DATE:
            //    break;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        int tasksDeleted;

        switch(match) {
            case CODE_RECORD_WITH_DATE:
                String id = uri.getPathSegments().get(1);
                tasksDeleted = db.delete(TABLE_NAME, "_id=?", new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri");
        }

        if(tasksDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return tasksDeleted;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
