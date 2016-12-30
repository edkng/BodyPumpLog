package com.example.android.bodypumplog.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by eng on 12/28/2016.
 */

public class RecordContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.bodypumplog";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_RECORDS = "records";

    public static final class RecordEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_RECORDS)
                .build();

        public static final String TABLE_NAME = "records";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_WARMUP = "warmup";
        public static final String COLUMN_SQUATS = "squats";
        public static final String COLUMN_CHEST = "chest";
        public static final String COLUMN_BACK = "back";
        public static final String COLUMN_TRICEPS = "triceps";
        public static final String COLUMN_BICEPS = "biceps";
        public static final String COLUMN_LUNGES = "lunges";
        public static final String COLUMN_SHOULDERS = "shoulders";
        public static final String COLUMN_ABS = "abs";

        public static Uri buildRecordUriWithDate(long date) {
            return CONTENT_URI.buildUpon()
                    .appendPath(Long.toString(date))
                    .build();
        }


    }


}
