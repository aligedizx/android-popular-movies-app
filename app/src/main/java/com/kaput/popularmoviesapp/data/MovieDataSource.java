package com.kaput.popularmoviesapp.data;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.kaput.popularmoviesapp.api.API;
import com.kaput.popularmoviesapp.api.APIService;
import com.kaput.popularmoviesapp.model.Movie;
import com.kaput.popularmoviesapp.model.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Integer, Movie> {

    APIService apiService;
    Integer pageNumber;
    private Executor executor;
    int ranking;
    int totalPageNumber;


    public MovieDataSource(Executor executor) {
        apiService = API.createAPIService();
        pageNumber = 1;
        this.executor = executor;
        ranking = 1;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Movie> callback) {
        final List<Movie> movieList = new ArrayList();

        apiService = API.createAPIService();
        apiService.getDatum(API.API_KEY, pageNumber).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                //for to stop get movies
                totalPageNumber = response.body().totalPages;

                //adding rankings to movies
                for (Movie m : response.body().getMovies()) {
                    m.setRanking(ranking++);
                    movieList.add(m);
                }

                pageNumber++;
                callback.onResult(movieList, null, pageNumber); //it set datas
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });

    }


    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Movie> callback) {
        final List<Movie> movieList = new ArrayList();
        if (params.key < totalPageNumber) {

            apiService.getDatum(API.API_KEY, params.key).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    //adding rankings to movies
                    for (Movie m : response.body().getMovies()) {
                        m.setRanking(ranking++);
                        movieList.add(m);
                    }
                    callback.onResult(movieList, params.key + 1); //it set datas
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                }


            });

        }

    }
}
