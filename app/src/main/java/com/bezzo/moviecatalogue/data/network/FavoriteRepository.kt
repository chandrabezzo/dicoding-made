package com.bezzo.moviecatalogue.data.network

import com.bezzo.moviecatalogue.data.local.dao.FavoriteDao
import com.bezzo.moviecatalogue.data.model.Favorite

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    suspend fun getAll(): MutableList<Favorite> {
        return favoriteDao.getAll()
    }

    fun allFavorite(): MutableList<Favorite> {
        return favoriteDao.getFavorites()
    }

    suspend fun insert(favorite: Favorite) {
        favoriteDao.insert(favorite)
    }

    suspend fun inserts(favorite: MutableList<Favorite>){
        favoriteDao.inserts(favorite)
    }

    suspend fun delete(id: Int) {
        favoriteDao.delete(id)
    }
}