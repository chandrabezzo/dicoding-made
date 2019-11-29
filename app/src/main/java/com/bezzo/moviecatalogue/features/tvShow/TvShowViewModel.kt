package com.bezzo.moviecatalogue.features.tvShow

import androidx.lifecycle.LiveData
import com.bezzo.core.base.BaseViewModel
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.moviecatalogue.data.MovieRepository
import com.bezzo.moviecatalogue.data.model.TvShow

class TvShowViewModel(
    private val movieRepository: MovieRepository,
    sessionHelper: SessionHelper
) : BaseViewModel(sessionHelper) {

    fun getTv(): LiveData<MutableList<TvShow>> {
        return movieRepository.getTvShows()
    }
}