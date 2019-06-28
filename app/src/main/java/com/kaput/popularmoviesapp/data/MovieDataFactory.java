package com.kaput.popularmoviesapp.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import java.util.concurrent.Executor;

public class MovieDataFactory extends DataSource.Factory {


    MutableLiveData<MovieDataSource> mutableLiveData;
    MovieDataSource movieDataSource;
    Executor executor;

    public MovieDataFactory(Executor executor) {
        this.mutableLiveData = new MutableLiveData<MovieDataSource>();
        this.executor = executor;

    }


    @Override
    public DataSource create() {
        movieDataSource = new MovieDataSource(executor);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

}
