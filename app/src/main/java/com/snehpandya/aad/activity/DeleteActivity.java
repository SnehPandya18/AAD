package com.snehpandya.aad.activity;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.snehpandya.aad.R;
import com.snehpandya.aad.database.MovieContract;
import com.snehpandya.aad.databinding.ActivityDeleteBinding;

/**
 * Created by sneh.pandya on 26/09/17.
 */

public class DeleteActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_MOVIE_LOADER = 0;
    private Uri mUri;
    private ActivityDeleteBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_delete);

        Intent intent = getIntent();
        mUri = intent.getData();

        mBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMovie();
            }
        });

        mBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMovie();
            }
        });

        if (mUri == null) {
            Toast.makeText(this, "URI is null!", Toast.LENGTH_SHORT).show();
        } else {
            getLoaderManager().initLoader(EXISTING_MOVIE_LOADER, null, this);
        }
    }

    private void deleteMovie() {
        if (mUri != null) {
            int rowDeleted = getContentResolver().delete(mUri, null, null);

            if (rowDeleted == 0) {
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Entry deleted", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    private void updateMovie() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_TITLE, "Movie New");
        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_OVERVIEW, "This is a new movie.");

        int uri = getContentResolver().update(mUri, contentValues, null, null);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                MovieContract.MovieEntry._ID,
                MovieContract.MovieEntry.COLUMN_MOVIE_TITLE,
                MovieContract.MovieEntry.COLUMN_MOVIE_OVERVIEW
        };

        return new CursorLoader(this, mUri, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) return;

        if (cursor.moveToFirst()) {
            mBinding.textMovieTitle.setText(cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_TITLE)));
            mBinding.textMovieOverview.setText(cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_OVERVIEW)));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mBinding.textMovieTitle.setText("");
        mBinding.textMovieOverview.setText("");
    }
}
