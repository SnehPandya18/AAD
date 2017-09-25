package com.snehpandya.aad.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by sneh.pandya on 25/09/17.
 */

public class MovieProvider extends ContentProvider {

    public static final int MOVIES = 100;
    public static final int MOVIE_ID = 101;
    public static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(MovieContract.CONTENT_AUTHORITY, MovieContract.PATH_MOVIES, MOVIES);
        sUriMatcher.addURI(MovieContract.CONTENT_AUTHORITY, MovieContract.PATH_MOVIES + "/#", MOVIE_ID);
    }

    private MovieDBHelper mMovieDBHelper;

    @Override
    public boolean onCreate() {
        mMovieDBHelper = new MovieDBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteDatabase sqLiteDatabase = mMovieDBHelper.getReadableDatabase();

        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case MOVIES:
                cursor = sqLiteDatabase.query(MovieContract.MovieEntry.TABLE_NAME, strings, s, strings1, null, null, s1);
                break;
            case MOVIE_ID:
                s = MovieContract.MovieEntry._ID + "=?";
                strings1 = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = sqLiteDatabase.query(MovieContract.MovieEntry.TABLE_NAME, strings, s, strings1, null, null, s1);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI:" + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MOVIES:
                return MovieContract.MovieEntry.CONTENT_LIST_TYPE;
            case MOVIE_ID:
                return MovieContract.MovieEntry.CONENT_ITEM_TPYE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MOVIES:
                return insertMovies(uri, contentValues);
            default: throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertMovies(Uri uri, ContentValues contentValues) {
        String title = contentValues.getAsString(MovieContract.MovieEntry.COLUMN_MOVIE_TITLE);
        if (title == null) {
            throw new IllegalArgumentException("Movie requires a name");
        }

        String overview = contentValues.getAsString(MovieContract.MovieEntry.COLUMN_MOVIE_OVERVIEW);
        if (overview == null) {
            throw new IllegalArgumentException("Movie requires an overview");
        }

        SQLiteDatabase sqLiteDatabase = mMovieDBHelper.getWritableDatabase();

        long id = sqLiteDatabase.insert(MovieContract.MovieEntry.TABLE_NAME, null, contentValues);
        if (id == -1) {
            Log.e("TAG", "insertMovies: Failed to insert row for" + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase sqLiteDatabase = mMovieDBHelper.getWritableDatabase();

        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MOVIES:
                rowsDeleted = sqLiteDatabase.delete(MovieContract.MovieEntry.TABLE_NAME, s, strings);
                break;
            case MOVIE_ID:
                s = MovieContract.MovieEntry._ID + "=?";
                strings = new String[] {String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = sqLiteDatabase.delete(MovieContract.MovieEntry.TABLE_NAME, s, strings);
                break;
            default: throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MOVIES:
                return updateMovies(uri, contentValues, s, strings);
            case MOVIE_ID:
                s = MovieContract.MovieEntry._ID + "=?";
                strings = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return updateMovies(uri, contentValues, s, strings);
            default: throw new IllegalArgumentException("Update is not supported for: " + uri);
        }
    }

    private int updateMovies(Uri uri, ContentValues contentValues, String s, String[] strings) {
        if (contentValues.containsKey(MovieContract.MovieEntry.COLUMN_MOVIE_TITLE)) {
            String title = contentValues.getAsString(MovieContract.MovieEntry.COLUMN_MOVIE_TITLE);
            if (title == null) {
                throw new IllegalArgumentException("Movie requires a name");
            }
        }

        if (contentValues.containsKey(MovieContract.MovieEntry.COLUMN_MOVIE_OVERVIEW)) {
            String overview = contentValues.getAsString(MovieContract.MovieEntry.COLUMN_MOVIE_OVERVIEW);
            if (overview == null) {
                throw new IllegalArgumentException("Movie requires an overview");
            }
        }

        if (contentValues.size() == 0) {
            return 0;
        }

        SQLiteDatabase sqLiteDatabase = mMovieDBHelper.getWritableDatabase();

        int rowsUpdated = sqLiteDatabase.update(MovieContract.MovieEntry.TABLE_NAME, contentValues, s, strings);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }
}
