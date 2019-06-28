package com.kaput.popularmoviesapp.util;

import android.view.View;

import com.kaput.popularmoviesapp.model.Movie;

public interface OnMovieClickListener {
    void onClick(View view, Movie m);
}
