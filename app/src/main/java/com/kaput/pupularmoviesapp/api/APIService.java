package com.kaput.pupularmoviesapp.api;

import com.kaput.pupularmoviesapp.model.ResponseBody;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("3/movie/popular")
    Call<ResponseBody> getDatum(@Query("api_key") String apiKey,
                                @Query("page") int page);
}
