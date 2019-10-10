package com.bezzo.moviecatalogue.features.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bezzo.core.base.BaseViewModel
import com.bezzo.core.base.Empty
import com.bezzo.core.base.Loading
import com.bezzo.core.base.Receive
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.data.local.LocalStorage
import com.bezzo.moviecatalogue.data.model.Favorite
import com.bezzo.moviecatalogue.data.network.FavoriteRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(
    sessionHelper: SessionHelper,
    application: Application
): BaseViewModel(sessionHelper, application) {

    private var favoriteRepository: FavoriteRepository

    init {
        val favoriteDao = LocalStorage.getDatabase(application, viewModelScope)
            .favoriteDao()
        favoriteRepository = FavoriteRepository(favoriteDao)
    }

    fun getFavorite() = viewModelScope.launch {
        state.postValue(Loading)
        if(favoriteRepository.getAll().size == 0){
            state.postValue(Empty)
        }
        else {
            state.postValue(Receive(favoriteRepository.getAll()))
        }
    }

    fun remove(id: Int) = viewModelScope.launch {
        favoriteRepository.delete(id)
        state.postValue(Receive(app.getString(R.string.berhasil_dihapus_favorite)))
    }

    fun addFavorite(favorite: Favorite) = viewModelScope.launch {
        favoriteRepository.insert(favorite)
        state.postValue(Receive(app.getString(R.string.berhasil_ditambahkan_favorite)))
    }
}