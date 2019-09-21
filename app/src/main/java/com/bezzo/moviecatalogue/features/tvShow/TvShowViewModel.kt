package com.bezzo.moviecatalogue.features.tvShow

import com.androidnetworking.error.ANError
import com.bezzo.core.base.BaseViewModel
import com.bezzo.core.base.Error
import com.bezzo.core.base.Loading
import com.bezzo.core.base.Receive
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.moviecatalogue.data.model.TvShow
import com.bezzo.moviecatalogue.data.network.ApiCallback
import com.bezzo.moviecatalogue.data.network.ApiHelper

class TvShowViewModel(
    private val apiHelper: ApiHelper,
    sessionHelper: SessionHelper
) : BaseViewModel(sessionHelper) {

    fun getTv() {
        state.postValue(Loading)
        apiHelper.getTvShow(object : ApiCallback<TvShow>{
            override fun onResponse(data: TvShow) {
                state.postValue(Receive(data.results))
            }

            override fun onFailed(error: ANError) {
                state.postValue(Error(handleApiError(error)))
            }
        })
    }
}