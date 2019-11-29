package com.bezzo.moviecatalogue.data

import android.os.Handler
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.data.model.TvShow
import com.bezzo.moviecatalogue.util.EspressoIdlingResource
import com.bezzo.moviecatalogue.util.JsonHelper


class RemoteRepository(private val jsonHelper: JsonHelper) {

    private val SERVICE_LATENCY_IN_MILLIS = 2000

    companion object {
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(helper: JsonHelper): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(helper)
            }
            return INSTANCE
        }
    }

    fun getMovies(callback: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed(
            { callback.onDataReceived(jsonHelper.loadMovies())
              EspressoIdlingResource.decrement() },
            SERVICE_LATENCY_IN_MILLIS.toLong()
        )
    }

    fun getTvShows(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed(
            { callback.onDataReceived(jsonHelper.loadTvShow())
              EspressoIdlingResource.decrement() },
            SERVICE_LATENCY_IN_MILLIS.toLong())
    }
}

interface LoadMovieCallback {
    fun onDataReceived(values: MutableList<Movie>)

    fun onDataNotAvailable()
}

interface LoadTvShowCallback {
    fun onDataReceived(values: MutableList<TvShow>)

    fun onDataNotAvailable()
}