package com.kaput.popularmoviesapp.model;

public class MovieListItem {
    int ranking;
    String rating;
    String title;
    String posterPath;

    public MovieListItem(int ranking, String rating, String title, String posterPath) {
        this.ranking = ranking;
        this.rating = rating;
        this.title = title;
        this.posterPath = posterPath;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getRanking() {
        return ranking;
    }

    public String getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
