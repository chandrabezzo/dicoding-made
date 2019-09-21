package com.bezzo.moviecatalogue.features.movie

import com.androidnetworking.error.ANError
import com.bezzo.core.base.BaseViewModel
import com.bezzo.core.base.Error
import com.bezzo.core.base.Loading
import com.bezzo.core.base.Receive
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.data.network.ApiCallback
import com.bezzo.moviecatalogue.data.network.ApiHelper

class MovieViewModel(
    private val apiHelper: ApiHelper,
    sessionHelper: SessionHelper
) : BaseViewModel(sessionHelper) {

    fun getMovie() {
        state.postValue(Loading)
        apiHelper.getMovie(object : ApiCallback<Movie>{
            override fun onResponse(data: Movie) {
                state.postValue(Receive(data.results))
            }

            override fun onFailed(error: ANError) {
                state.postValue(Error(handleApiError(error)))
            }
        })
    }
}