package com.snehpandya.aad.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by sneh.pandya on 25/09/17.
 */

public class MovieContract {

    public static final String CONTENT_AUTHORITY = "com.snehpandya.aad";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MOVIES = "movies";

    private MovieContract() {
    }

    public static final class MovieEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_MOVIES);

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;

        public static final String CONENT_ITEM_TPYE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;

        public static final String TABLE_NAME = "movies";

        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_MOVIE_TITLE = "title";

        public static final String COLUMN_MOVIE_OVERVIEW = "overview";
    }
}
