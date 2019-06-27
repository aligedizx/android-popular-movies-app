package com.kaput.popularmoviesapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
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

import java.io.InputStream;
import java.net.URL;
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

    @Override
    public long getItemId(int position) {
        return movieList.get(position).getRanking();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, rating, ranking;
        ImageView poster;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            rating = (TextView) itemView.findViewById(R.id.rating);
            ranking= (TextView) itemView.findViewById(R.id.ranking);
            poster = (ImageView) itemView.findViewById(R.id.poster);

        }

        public void setData(MovieListItem m, int position) {
            this.title.setText(m.getTitle());
            this.rating.setText(m.getRating());
            this.ranking.setText(String.valueOf(m.getRanking() + "."));
            this.poster.setImageDrawable(LoadImageFromWebOperations(m.getPosterPath()));
        }

        public Drawable LoadImageFromWebOperations(String path) {

            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                System.out.println("https://image.tmdb.org/t/p/w92" + path);
                InputStream is = (InputStream) new URL("https://image.tmdb.org/t/p/w185" + path).getContent();
                Drawable d = Drawable.createFromStream(is, "themoviedb");
                return d;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

    }

}
