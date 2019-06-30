package com.kaput.popularmoviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kaput.popularmoviesapp.api.API;
import com.kaput.popularmoviesapp.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    TextView titleText, overviewText, releaseDateText, ratingText;
    ImageView posterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        titleText = findViewById(R.id.movie_title);
        overviewText = findViewById(R.id.overview);
        releaseDateText = findViewById(R.id.release_date);
        posterView = findViewById(R.id.poster_view);
        ratingText = findViewById(R.id.rating);

        Movie m = getIntent().getParcelableExtra("movie");
        titleText.setText(m.title);
        overviewText.setText(m.overview);
        releaseDateText.setText(m.releaseDate);
        ratingText.setText(m.rating);

        if (m.posterPath != null)
            Glide.with(this).load(API.BIG_IMAGE_BASE_URL + m.posterPath).into(posterView);
    }
}
