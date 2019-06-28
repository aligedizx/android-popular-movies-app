package com.kaput.popularmoviesapp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public static final String BASE_URL = "https://api.themoviedb.org/";
    public static final int DEFAULT_PER_PAGE = 20;

    public static APIService createAPIService(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        return retrofit.create(APIService.class);
    }
}
