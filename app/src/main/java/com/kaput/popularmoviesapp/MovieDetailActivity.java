package com.kaput.popularmoviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.kaput.popularmoviesapp.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        titleView = findViewById(R.id.movie_title);

        Movie m = getIntent().getParcelableExtra("movie");
        titleView.setText(m.title);
    }
}
