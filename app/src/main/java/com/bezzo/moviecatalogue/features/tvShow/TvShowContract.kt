package com.bezzo.moviecatalogue.features.tvShow

import com.bezzo.core.base.BaseFragmentContract
import com.bezzo.core.base.BasePresenterContract
import com.bezzo.moviecatalogue.data.model.Movie

interface TvShowViewContract: BaseFragmentContract {
    fun showTvShow(values: ArrayList<Movie>)
}

interface TvShowPresenterContract<V: TvShowViewContract>: BasePresenterContract<V> {
    fun getTvShows()
}