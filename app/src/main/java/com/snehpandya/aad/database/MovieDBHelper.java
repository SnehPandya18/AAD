package com.snehpandya.aad.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sneh.pandya on 25/09/17.
 */

public class MovieDBHelper extends SQLiteOpenHelper {

    public static final String DATBASE_NAME = "movies.db";
    public static final int DATBASE_VERSION = 1;

    public MovieDBHelper(Context context) {
        super(context, DATBASE_NAME, null, DATBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_TABLE = "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " ("
                + MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MovieContract.MovieEntry.COLUMN_MOVIE_TITLE + " TEXT NOT NULL, "
                + MovieContract.MovieEntry.COLUMN_MOVIE_OVERVIEW + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
