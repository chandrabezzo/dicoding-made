package com.bezzo.moviecatalogue.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bezzo.moviecatalogue.constanta.AppConstant
import kotlinx.android.parcel.Parcelize

@Entity(tableName = AppConstant.FAVORITE)
@Parcelize
data class Favorite(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "image")
    var image: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "release_date")
    var releaseDate: String,
    @ColumnInfo(name = "user_score")
    var userScore: Double,
    @ColumnInfo(name = "popularity")
    var popularity: Double,
    @ColumnInfo(name = "desc")
    var desc: String
): Parcelable