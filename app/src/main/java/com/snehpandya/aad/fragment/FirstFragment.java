package com.snehpandya.aad.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.snehpandya.aad.R;
import com.snehpandya.aad.adapter.MoviesAdapter;
import com.snehpandya.aad.apiservice.RetrofitAPI;
import com.snehpandya.aad.database.MovieContract;
import com.snehpandya.aad.databinding.FragmentOneBinding;
import com.snehpandya.aad.model.Response;
import com.snehpandya.aad.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Sneh on 24-09-2017.
 */

public class FirstFragment extends Fragment {

    private int page;
    private MoviesAdapter mMoviesAdapter;
    private FragmentOneBinding binding;
    private SharedPreferences mSharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_one, container, false);

        mSharedPreferences = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        page = mSharedPreferences.getInt("page", 1);
        Log.d("TAG", "onCreateView: page: " + page);

        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showMovies(page);
            }
        });

        binding.swipe.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorAccent,
                R.color.colorPrimaryDark);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMovies(page);
                binding.fab.hide();
            }
        });
        return binding.getRoot();
    }

    private void showMovies(int page) {
        RetrofitAPI retrofitAPI = new RetrofitAPI();
        Call<Response> response = retrofitAPI.mTMDBApi.popularResponse("20538a1ec60bfd1df41d9b08e00e26e8", page);
        response.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Response response1 = response.body();
                    mMoviesAdapter = new MoviesAdapter(response1.getResults());
                    binding.recyclerview.setAdapter(mMoviesAdapter);

                    deleteMovies();
                    insertMovies(response1.getResults());

                    binding.swipe.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertMovies(List<Result> results) {
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < results.size(); i++) {
            contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_TITLE, results.get(i).getTitle());
            contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_OVERVIEW, results.get(i).getOverview());

            Uri uri = getContext().getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, contentValues);
        }
    }

    private void deleteMovies() {
        int rowsDeleted = getContext().getContentResolver().delete(MovieContract.MovieEntry.CONTENT_URI, null, null);
    }
}
