package com.kaput.popularmoviesapp;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.kaput.popularmoviesapp.model.Movie;
import com.kaput.popularmoviesapp.R;
import com.kaput.popularmoviesapp.model.MovieListItem;
import com.kaput.popularmoviesapp.util.OnMovieClickListener;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MyViewHolder> {

    //ArrayList<Movie> movieList;
    LayoutInflater inflater;
    Context context;
    private OnMovieClickListener listener;
    private RequestManager glide;

    public MovieAdapter(OnMovieClickListener listener, RequestManager glide) {
        super(Movie.DIFF_CALLBACK);
        this.listener = listener;
        this.glide = glide;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_item_card, parent, false);
        return new MyViewHolder(view, listener);



    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.setData(getItem(i), glide);
    }

   /* @Override
    public long getItemId(int position) {
        return movieList.get(position).ranking;
    }
*/

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, rating, ranking;
        ImageView poster;

        public MyViewHolder(final View itemView, final OnMovieClickListener listener) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            rating = (TextView) itemView.findViewById(R.id.rating);
            ranking= (TextView) itemView.findViewById(R.id.ranking);
            poster = (ImageView) itemView.findViewById(R.id.poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(itemView, getItem(getAdapterPosition()));
                }
            });

        }

        public void setData(Movie m, RequestManager glide) {
            this.title.setText(m.title);
            this.rating.setText(m.rating);
            this.ranking.setText(String.valueOf(m.ranking + "."));
            if (m.posterPath != null)
                loadImageFromUrl(glide, m.posterPath, this.poster);
        }


        private void loadImageFromUrl(RequestManager glide, String path, ImageView view ){
            //System.out.println("https://image.tmdb.org/t/p/w185" + path);
            glide.load("https://image.tmdb.org/t/p/w185" + path).override(185,278).into(view);
        }

    }

}
