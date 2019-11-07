package com.bezzo.moviecatalogue.data.local.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.model.Favorite

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM ${AppConstant.FAVORITE}")
    suspend fun getAll(): MutableList<Favorite>

    @Query("SELECT * FROM ${AppConstant.FAVORITE}")
    fun getFavorites(): MutableList<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: Favorite)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserts(favorite: MutableList<Favorite>)

    @Query("DELETE FROM ${AppConstant.FAVORITE}")
    suspend fun deleteAll()

    @Query("DELETE FROM ${AppConstant.FAVORITE} WHERE id=:id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM ${AppConstant.FAVORITE}")
    fun allFavorites(): Cursor

    @Query("SELECT * FROM ${AppConstant.FAVORITE} WHERE id=:id")
    fun getFavorite(id: Int): Cursor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(favorite: Favorite): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(favorite: Array<Favorite>): Array<Long>

    @Query("DELETE FROM ${AppConstant.FAVORITE} WHERE id=:id")
    fun deleteById(id: Int): Int
}