package com.bezzo.moviecatalogue.features.movie

import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.core.base.BaseFragmentContract
import com.bezzo.core.base.BasePresenterContract

interface MovieViewContract: BaseFragmentContract {
    fun showMovies(values: ArrayList<Movie>)
}

interface MoviePresenterContract<V: MovieViewContract>: BasePresenterContract<V> {
    fun getMovie()
}