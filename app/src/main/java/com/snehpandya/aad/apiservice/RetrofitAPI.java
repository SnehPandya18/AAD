package com.snehpandya.aad.apiservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sneh.pandya on 25/09/17.
 */

public class RetrofitAPI {

    private Retrofit mRetrofit = new Retrofit.Builder().baseUrl("http://api.themoviedb.org").addConverterFactory(GsonConverterFactory.create()).build();
    public TMDBApi mTMDBApi = mRetrofit.create(TMDBApi.class);
}
