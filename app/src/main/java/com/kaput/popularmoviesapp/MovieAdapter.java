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

import com.kaput.popularmoviesapp.model.Movie;
import com.kaput.popularmoviesapp.R;
import com.kaput.popularmoviesapp.model.MovieListItem;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MyViewHolder> {

    //ArrayList<Movie> movieList;
    LayoutInflater inflater;
    Context context;

    public MovieAdapter() {
        super(Movie.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
       /* View vieww = inflater.inflate(R.layout.custom_item_card, parent, false);
        MyViewHolder holder = new MyViewHolder(vieww);
        return holder;*/





        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;




        view = layoutInflater.inflate(R.layout.custom_item_card, parent, false);
        return new MyViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.setData(getItem(i));
    }

   /* @Override
    public long getItemId(int position) {
        return movieList.get(position).ranking;
    }
*/

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

        public void setData(Movie m) {
            this.title.setText(m.title);
            this.rating.setText(m.rating);
            this.ranking.setText(String.valueOf(m.ranking + "."));
            this.poster.setImageDrawable(LoadImageFromWebOperations(m.posterPath));
        }

        public Drawable LoadImageFromWebOperations(String path) {

            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
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
