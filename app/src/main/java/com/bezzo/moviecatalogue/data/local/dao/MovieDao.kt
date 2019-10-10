package com.bezzo.moviecatalogue.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.model.ResultMovie

@Dao
interface MovieDao {
    @Query("SELECT * FROM ${AppConstant.MOVIE}")
    fun getAll(): LiveData<MutableList<ResultMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: ResultMovie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserts(movies: MutableList<ResultMovie>)

    @Query("DELETE FROM ${AppConstant.MOVIE}")
    suspend fun deleteAll()
}