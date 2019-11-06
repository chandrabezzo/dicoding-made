package com.bezzo.moviecatalogue.data.network

import androidx.lifecycle.LiveData
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.bezzo.core.data.network.ApiCallback
import com.bezzo.core.data.network.RestApi
import com.bezzo.moviecatalogue.data.local.dao.MovieDao
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.data.model.ResultMovie
import java.util.*
import kotlin.collections.HashMap

class MovieRepository(private val movieDao: MovieDao) {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allMovie: LiveData<MutableList<ResultMovie>> = movieDao.getAll()


    // The suspend modifier tells the compiler that this must be called from a
    // coroutine or another suspend function.
    suspend fun insert(movie: ResultMovie) {
        movieDao.insert(movie)
    }

    suspend fun inserts(movies: MutableList<ResultMovie>){
        movieDao.inserts(movies)
    }

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

    fun searchMovie(query: String?, callback: ApiCallback<Movie>){
        val params = HashMap<String, String>()
        query?.let { params["query"] = query }
        RestApi.get(ApiEndpoint.SEARCH_MOVIE, params, null, null)
            .getAsObject(Movie::class.java, object : ParsedRequestListener<Movie>{
                override fun onResponse(response: Movie) {
                    callback.onResponse(response)
                }

                override fun onError(anError: ANError) {
                    callback.onFailed(anError)
                }
            })
    }
}