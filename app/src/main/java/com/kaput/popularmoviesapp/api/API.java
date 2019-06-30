package com.kaput.popularmoviesapp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public static final String BASE_URL = "https://api.themoviedb.org/";
    public static final int DEFAULT_PER_PAGE = 20;
    public static final String SMALL_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w92";
    public static final String BIG_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w780";

    public static APIService createAPIService(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        return retrofit.create(APIService.class);
    }
}
