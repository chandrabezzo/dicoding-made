package com.bezzo.moviecatalogue.features.movie

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.androidnetworking.error.ANError
import com.bezzo.core.base.*
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.moviecatalogue.data.local.LocalStorage
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.data.model.ResultMovie
import com.bezzo.core.data.network.ApiCallback
import com.bezzo.moviecatalogue.data.network.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(
    sessionHelper: SessionHelper,
    application: Application
) : BaseViewModel(sessionHelper, application) {

    private var movieRepository: MovieRepository

    init {
        val movieDao = LocalStorage.getDatabase(application, viewModelScope).movieDao()
        movieRepository = MovieRepository(movieDao)
    }

    // Gets reference to WordDao from WordRoomDatabase to construct
    // the correct WordRepository.
    private fun getLocalMovie() {
        if(movieRepository.allMovie.value?.isEmpty() == false){
            state.postValue(Receive(movieRepository.allMovie))
        }
    }

    // The implementation of insert() is completely hidden from the UI.
    // We don't want insert to block the main thread, so we're launching a new
    // coroutine. ViewModels have a coroutine scope based on their lifecycle called
    // viewModelScope which we can use here.
    fun addLocalMovie(allTvShow: MutableList<ResultMovie>) = viewModelScope.launch {
        movieRepository.inserts(allTvShow)
    }

    fun getMovie() {
        state.postValue(Loading)
        getLocalMovie()
        movieRepository.getMovie(object : ApiCallback<Movie> {
            override fun onResponse(data: Movie) {
                if(data.results.isEmpty()){
                    state.postValue(Empty)
                }
                else {
                    addLocalMovie(data.results)
                    state.postValue(Receive(data.results))
                }
            }

            override fun onFailed(error: ANError) {
                state.postValue(Error(handleApiError(error)))
            }
        })
    }
}