package com.kaput.popularmoviesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaput.popularmoviesapp.model.Movie;
import com.kaput.popularmoviesapp.R;
import com.kaput.popularmoviesapp.model.MovieListItem;

import java.util.ArrayList;
import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    ArrayList<MovieListItem> movieList;
    LayoutInflater inflater;

    public MovieAdapter(Context context, ArrayList<MovieListItem> movieList) {
        this.movieList = movieList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = inflater.inflate(R.layout.custom_item_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        MovieListItem selectedMovie = movieList.get(i);
        myViewHolder.setData(selectedMovie, i);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, rating, ranking;
        ImageView productImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            rating = (TextView) itemView.findViewById(R.id.rating);
            ranking= (TextView) itemView.findViewById(R.id.ranking);
            productImage = (ImageView) itemView.findViewById(R.id.poster);

        }

        public void setData(MovieListItem m, int position) {
            this.title.setText(m.getTitle());
            this.rating.setText(m.getRating());
            this.ranking.setText(String.valueOf(m.getRanking() + "."));
        }

    }

}
