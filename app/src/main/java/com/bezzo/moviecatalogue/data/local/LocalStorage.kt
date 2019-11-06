package com.bezzo.moviecatalogue.data.local

import android.content.Context
import android.net.Uri
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.local.converter.ListIntConverter
import com.bezzo.moviecatalogue.data.local.converter.ListStringConverter
import com.bezzo.moviecatalogue.data.local.dao.FavoriteDao
import com.bezzo.moviecatalogue.data.local.dao.MovieDao
import com.bezzo.moviecatalogue.data.local.dao.TvShowDao
import com.bezzo.moviecatalogue.data.model.Favorite
import com.bezzo.moviecatalogue.data.model.ResultMovie
import com.bezzo.moviecatalogue.data.model.ResultTvShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [ResultMovie::class, ResultTvShow::class, Favorite::class], version = 1)
@TypeConverters(ListIntConverter::class, ListStringConverter::class)
abstract class LocalStorage: RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: LocalStorage? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope)
                : LocalStorage {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalStorage::class.java,
                    "movie_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(LocalStorageCallback(scope))
                    .build()

                INSTANCE = instance
                instance
            }
        }

        fun getDatabase(
            context: Context
        ): LocalStorage {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalStorage::class.java,
                    "movie_database"
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    private class LocalStorageCallback(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {
                scope.launch {

                }
            }
        }
    }
}