package com.bezzo.moviecatalogue.data.model

import android.os.Parcelable
import androidx.room.*
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.local.converter.ListIntConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Movie(
    @SerializedName("page")
    @Expose
    val page: Int,
    @SerializedName("total_results")
    @Expose
    val totalResults: Int,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int,
    @SerializedName("results")
    @Expose
    val results: MutableList<ResultMovie>
)

@Parcelize
@Entity(tableName = AppConstant.MOVIE)
data class ResultMovie(
    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    @Expose
    val popularity: Double,

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    @Expose
    val voteCount: Int,

    @SerializedName("video")
    @ColumnInfo(name = "video")
    @Expose
    val video: Boolean,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    @Expose
    val posterPath: String,

    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @Expose
    val id: Int,

    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    @Expose
    val adult: Boolean,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    @Expose
    val backdropPath: String,

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    @Expose
    val originalLanguage: String,

    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    @Expose
    val originalTitle: String,

    @SerializedName("genre_ids")
    @TypeConverters(ListIntConverter::class)
    @ColumnInfo(name = "genre_ids")
    @Expose
    val genreIds: List<Int>?,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    @Expose
    val title: String,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    @Expose
    val voteAverage: Double,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    @Expose
    val overview: String,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    @Expose
    val releaseDate: String
) : Parcelable