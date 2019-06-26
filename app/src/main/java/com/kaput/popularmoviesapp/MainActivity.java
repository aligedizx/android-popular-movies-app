package com.kaput.popularmoviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaput.popularmoviesapp.model.MovieListItem;
import com.kaput.popularmoviesapp.R;
import com.kaput.popularmoviesapp.api.APIService;
import com.kaput.popularmoviesapp.api.APIUrl;
import com.kaput.popularmoviesapp.model.Movie;
import com.kaput.popularmoviesapp.model.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movieList;
    private RecyclerView movieRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRecyclerView = findViewById(R.id.movie_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        movieRecyclerView.setLayoutManager(linearLayoutManager);

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIUrl.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        APIService apis = retrofit.create(APIService.class);
        Call<ResponseBody> call = apis.getDatum("f44ae99022116e67fae910b2c8a2a3c2", 1);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                movieList = response.body().getMovies();
                listToListView();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    private void listToListView() {

        ArrayList<MovieListItem> itemList = new ArrayList<>();
        for (int i = 0 ; i<movieList.size(); i ++) {
            itemList.add(new MovieListItem(i + 1,
                                            movieList.get(i).rating,
                                            movieList.get(i).title,
                                            movieList.get(i).posterPath));
        }

        MovieAdapter movieAdapter = new MovieAdapter(this, itemList);
        movieRecyclerView.setAdapter(movieAdapter);
    }
}