package com.bezzo.moviecatalogue.features.tvShow

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.androidnetworking.error.ANError
import com.bezzo.core.base.*
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.moviecatalogue.data.local.LocalStorage
import com.bezzo.moviecatalogue.data.model.ResultTvShow
import com.bezzo.moviecatalogue.data.model.TvShow
import com.bezzo.core.data.network.ApiCallback
import com.bezzo.moviecatalogue.data.network.TvShowRepository
import kotlinx.coroutines.launch

class TvShowViewModel(
    sessionHelper: SessionHelper,
    application: Application
) : BaseViewModel(sessionHelper, application) {

    private val tvShowRepository: TvShowRepository

    init {
        val tvShowDao = LocalStorage.getDatabase(application, viewModelScope).tvShowDao()
        tvShowRepository = TvShowRepository(tvShowDao)
    }

    // Gets reference to WordDao from WordRoomDatabase to construct
    // the correct WordRepository.
    fun getLocalTv() {
        if(tvShowRepository.allTvShow.value?.isEmpty() == false){
            state.postValue(Receive(tvShowRepository.allTvShow))
        }
    }

    // The implementation of insert() is completely hidden from the UI.
    // We don't want insert to block the main thread, so we're launching a new
    // coroutine. ViewModels have a coroutine scope based on their lifecycle called
    // viewModelScope which we can use here.
    fun addLocalTv(allTvShow: MutableList<ResultTvShow>) = viewModelScope.launch {
        tvShowRepository.inserts(allTvShow)
    }

    fun getTv() {
        state.postValue(Loading)
        getLocalTv()
        tvShowRepository.getTvShow(object : ApiCallback<TvShow> {
            override fun onResponse(data: TvShow) {
                if(data.results.isEmpty()){
                    state.postValue(Empty)
                }
                else {
                    addLocalTv(data.results)
                    state.postValue(Receive(data.results))
                }
            }

            override fun onFailed(error: ANError) {
                state.postValue(Error(handleApiError(error)))
            }
        })
    }
}