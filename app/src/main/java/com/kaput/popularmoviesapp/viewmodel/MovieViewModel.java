package com.kaput.popularmoviesapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.kaput.popularmoviesapp.api.API;
import com.kaput.popularmoviesapp.data.MovieDataFactory;
import com.kaput.popularmoviesapp.data.MovieDataSource;
import com.kaput.popularmoviesapp.model.Movie;

import java.util.concurrent.Executor;

public class MovieViewModel extends ViewModel {

    public LiveData<PagedList<Movie>> movieList;
    Executor executor = new Executor() {
        private final Handler mHandler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(@NonNull Runnable command) {
            mHandler.post(command);
        }
    };

    public MovieViewModel(){
        MovieDataFactory movieDataFactory = new MovieDataFactory(executor);

        //Its config of get movies page by page
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(API.DEFAULT_PER_PAGE)
                .setInitialLoadSizeHint(API.DEFAULT_PER_PAGE)
                .setEnablePlaceholders(true)
                .build();


        movieList = (new LivePagedListBuilder(movieDataFactory, config))
                .build();

    }
}
