package com.bezzo.moviecatalogue.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.model.ResultTvShow

@Dao
interface TvShowDao {
    @Query("SELECT * FROM ${AppConstant.TV_SHOW}")
    fun getAll(): LiveData<MutableList<ResultTvShow>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: ResultTvShow)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserts(movies: MutableList<ResultTvShow>)

    @Query("DELETE FROM ${AppConstant.TV_SHOW}")
    suspend fun deleteAll()
}