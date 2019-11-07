package com.bezzo.moviecatalogue.data.model

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bezzo.moviecatalogue.constanta.AppConstant

@Entity(tableName = AppConstant.FAVORITE)
class Favorite {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null

    @ColumnInfo(name = "image")
    var image: String? = null

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null

    @ColumnInfo(name = "user_score")
    var userScore: Double? = null

    @ColumnInfo(name = "popularity")
    var popularity: Double? = null

    @ColumnInfo(name = "desc")
    var desc: String? = null

    constructor()

    constructor(cursor: Cursor){
        id = cursor.getInt(cursor.getColumnIndexOrThrow(FavoriteColumn.FAVORITE_ID))
        image = cursor.getString(cursor.getColumnIndexOrThrow(FavoriteColumn.FAVORITE_IMAGE))
        title = cursor.getString(cursor.getColumnIndexOrThrow(FavoriteColumn.FAVORITE_TITLE))
        releaseDate = cursor.getString(cursor.getColumnIndexOrThrow(FavoriteColumn.FAVORITE_RELEASE_DATE))
        userScore = cursor.getDouble(cursor.getColumnIndexOrThrow(FavoriteColumn.FAVORITE_USER_SCORE))
        popularity = cursor.getDouble(cursor.getColumnIndexOrThrow(FavoriteColumn.FAVORITE_POPULARITY))
        desc = cursor.getString(cursor.getColumnIndexOrThrow(FavoriteColumn.FAVORITE_DESC))
    }

    companion object {
        fun fromContentValues(values: ContentValues): Favorite {
            val favorite = Favorite()
            favorite.id = values.getAsInteger("id")
            favorite.image = values.getAsString("image")
            favorite.title = values.getAsString("title")
            favorite.releaseDate = values.getAsString("release_date")
            favorite.userScore = values.getAsDouble("user_score")
            favorite.popularity = values.getAsDouble("popularity")
            favorite.desc = values.getAsString("desc")

            return favorite
        }
    }
}

object FavoriteColumn: BaseColumns {
    val FAVORITE_ID = "id"
    val FAVORITE_IMAGE = "image"
    val FAVORITE_TITLE = "title"
    val FAVORITE_RELEASE_DATE = "release_date"
    val FAVORITE_USER_SCORE = "user_score"
    val FAVORITE_POPULARITY = "popularity"
    val FAVORITE_DESC = "desc"
}