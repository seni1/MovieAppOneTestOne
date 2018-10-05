package com.example.android.movieapponetestone.api;

import com.example.android.movieapponetestone.model.popular.Popular;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("movie/popular")
    Call<Popular> getPopularMovies(
            @Query("api_key") String key);

}
