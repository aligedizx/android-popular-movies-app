package com.kaput.popularmoviesapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaput.popularmoviesapp.model.MovieListItem;
import com.kaput.popularmoviesapp.api.APIService;
import com.kaput.popularmoviesapp.api.API;
import com.kaput.popularmoviesapp.model.Movie;
import com.kaput.popularmoviesapp.model.ResponseBody;
import com.kaput.popularmoviesapp.util.OnMovieClickListener;
import com.kaput.popularmoviesapp.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnMovieClickListener {

    private List<Movie> movieList;
    private RecyclerView movieRecyclerView;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRecyclerView = findViewById(R.id.movie_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        movieRecyclerView.setLayoutManager(linearLayoutManager);
        movieRecyclerView.setHasFixedSize(true);
        movieRecyclerView.setItemViewCacheSize(40);

        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        final MovieAdapter movieAdapter = new MovieAdapter(this, Glide.with(this));


        viewModel.movieList.observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(@Nullable PagedList<Movie> pagedList) {
                movieAdapter.submitList(pagedList);
            }
        });

        movieRecyclerView.setAdapter(movieAdapter);



    }

    @Override
    public void onClick(View view, Movie m) {
        Intent i = new Intent(this, MovieDetailActivity.class);
        i.putExtra("movie", m);
        startActivity(i);
    }
}