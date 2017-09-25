package com.snehpandya.aad.fragment;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snehpandya.aad.R;
import com.snehpandya.aad.adapter.MoviesCursorAdapter;
import com.snehpandya.aad.database.MovieContract;
import com.snehpandya.aad.databinding.FragmentTwoBinding;

/**
 * Created by Sneh on 24-09-2017.
 */

public class SecondFragment extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> {

    public static final int MOVIE_LOADER = 0;
    MoviesCursorAdapter mMoviesCursorAdapter;
    FragmentTwoBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_two, container, false);

        mMoviesCursorAdapter = new MoviesCursorAdapter(getActivity(), null);
        binding.list.setAdapter(mMoviesCursorAdapter);

        getLoaderManager().initLoader(MOVIE_LOADER, null, SecondFragment.this);
        return binding.getRoot();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                MovieContract.MovieEntry._ID,
                MovieContract.MovieEntry.COLUMN_MOVIE_TITLE,
                MovieContract.MovieEntry.COLUMN_MOVIE_OVERVIEW
        };

        return new CursorLoader(getActivity(), MovieContract.MovieEntry.CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.d("TAG", "onLoadFinished: " + cursor.getCount());
        mMoviesCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mMoviesCursorAdapter.swapCursor(null);
    }
}
