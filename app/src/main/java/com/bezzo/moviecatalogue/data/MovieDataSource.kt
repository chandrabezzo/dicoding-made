package com.bezzo.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.data.model.TvShow

interface MovieDataSource {
    fun getMovies(): LiveData<MutableList<Movie>>

    fun getTvShows(): LiveData<MutableList<TvShow>>
}