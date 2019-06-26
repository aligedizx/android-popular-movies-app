package com.kaput.pupularmoviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaput.pupularmoviesapp.api.APIService;
import com.kaput.pupularmoviesapp.api.APIUrl;
import com.kaput.pupularmoviesapp.model.Movie;
import com.kaput.pupularmoviesapp.model.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movieList;
    private ListView movieListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieListView = findViewById(R.id.movie_list);

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
        List<String> movieTitleList = new ArrayList<>();
        for (Movie m: movieList) {
            movieTitleList.add(m.title);

        }
        ArrayAdapter<String> movieAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1
        , movieTitleList);
        movieListView.setAdapter(movieAdapter);
    }
}
