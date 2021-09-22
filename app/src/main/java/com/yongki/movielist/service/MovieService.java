package com.yongki.movielist.service;

import com.yongki.movielist.model.BaseAPIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/popular")
    Call<BaseAPIResponse> getMovieData(
            @Query("api_key") String apikey,
            @Query("language") String language,
            @Query("page") String page,
            @Query("region") String region
    );
}
