package com.bezzo.moviecatalogue.data.network

import androidx.lifecycle.LiveData
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.bezzo.core.data.network.ApiCallback
import com.bezzo.core.data.network.RestApi
import com.bezzo.moviecatalogue.data.local.dao.TvShowDao
import com.bezzo.moviecatalogue.data.model.ResultTvShow
import com.bezzo.moviecatalogue.data.model.TvShow

class TvShowRepository(private val tvShowDao: TvShowDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allTvShow: LiveData<MutableList<ResultTvShow>> = tvShowDao.getAll()


    // The suspend modifier tells the compiler that this must be called from a
    // coroutine or another suspend function.
    suspend fun insert(tvShow: ResultTvShow) {
        tvShowDao.insert(tvShow)
    }

    suspend fun inserts(movies: MutableList<ResultTvShow>){
        tvShowDao.inserts(movies)
    }

    fun getTvShow(callback: ApiCallback<TvShow>) {
        RestApi.get(ApiEndpoint.TV_SHOW, null, null, null)
            .getAsObject(TvShow::class.java, object : ParsedRequestListener<TvShow> {
                override fun onResponse(response: TvShow) {
                    callback.onResponse(response)
                }

                override fun onError(anError: ANError) {
                    callback.onFailed(anError)
                }
            })
    }
}