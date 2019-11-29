package com.bezzo.moviecatalogue.data.network

import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.bezzo.core.data.network.RestApi
import com.bezzo.core.util.SchedulerProviderUtil
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.data.model.TvShow

class ApiHelper constructor(private val schedulerProvider: SchedulerProviderUtil) {

    fun getMovie(callback: ApiCallback<Movie>) {
        RestApi.get(ApiEndpoint.MOVIE, null, null, null)
            .getAsObject(Movie::class.java, object : ParsedRequestListener<Movie> {
                override fun onResponse(response: Movie) {
                    callback.onResponse(response)
                }

                override fun onError(anError: ANError) {
                    callback.onFailed(anError)
                }
            })
    }

    fun getTvShow(callback: ApiCallback<TvShow>) {
        RestApi.get(ApiEndpoint.TV_SHOW, null, null, null)
            .getAsObject(TvShow::class.java, object : ParsedRequestListener<TvShow>{
                override fun onResponse(response: TvShow) {
                    callback.onResponse(response)
                }

                override fun onError(anError: ANError) {
                    callback.onFailed(anError)
                }
            })
    }
}