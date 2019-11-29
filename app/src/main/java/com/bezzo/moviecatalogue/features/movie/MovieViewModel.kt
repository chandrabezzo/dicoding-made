package com.bezzo.moviecatalogue.features.movie

import androidx.lifecycle.LiveData
import com.bezzo.core.base.BaseViewModel
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.moviecatalogue.data.MovieRepository
import com.bezzo.moviecatalogue.data.model.Movie

class MovieViewModel(
    private val movieRepository: MovieRepository,
    sessionHelper: SessionHelper
) : BaseViewModel(sessionHelper) {

    fun getMovie(): LiveData<MutableList<Movie>> {
        return movieRepository.getMovies()
    }
}