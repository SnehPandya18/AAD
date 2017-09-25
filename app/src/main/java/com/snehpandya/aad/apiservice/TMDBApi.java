package com.snehpandya.aad.apiservice;

import com.snehpandya.aad.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sneh.pandya on 25/09/17.
 */

public interface TMDBApi {

    @GET("/3/movie/popular")
    Call<Response> popularResponse(@Query("api_key") String key, @Query("page") int page);
}
