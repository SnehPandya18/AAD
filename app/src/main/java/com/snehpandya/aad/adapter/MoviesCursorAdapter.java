package com.snehpandya.aad.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snehpandya.aad.R;
import com.snehpandya.aad.database.MovieContract;

/**
 * Created by sneh.pandya on 25/09/17.
 */

public class MoviesCursorAdapter extends CursorAdapter {

    private LayoutInflater mLayoutInflater;

    public MoviesCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return mLayoutInflater.inflate(R.layout.cursor_list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView movieTitle = (TextView) view.findViewById(R.id.movieTitle);
        TextView movieOverview = (TextView) view.findViewById(R.id.movieOverview);

        int titleColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_TITLE);
        int overviewColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_OVERVIEW);

        String title = cursor.getString(titleColumnIndex);
        String overview = cursor.getString(overviewColumnIndex);
        Log.d("TAG", "bindView: title:" + title);

        movieTitle.setText(title);
        movieOverview.setText(overview);
    }
}
