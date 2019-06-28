package com.kaput.popularmoviesapp.model;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Movie {
    public static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie movie, @NonNull Movie t1) {
            return movie.posterPath.equals(t1.posterPath);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie movie, @NonNull Movie t1) {
            return movie.posterPath.equals(t1.posterPath);
        }
    };

    public int ranking;

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("vote_average")
    @Expose
    public String rating;

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
