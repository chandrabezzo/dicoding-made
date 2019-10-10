package com.bezzo.moviecatalogue.data.model

import android.os.Parcelable
import androidx.room.*
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.local.converter.ListIntConverter
import com.bezzo.moviecatalogue.data.local.converter.ListStringConverter
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.RawValue

data class TvShow(
    @SerializedName("page")
    @Expose
    var page: Int,
    @SerializedName("total_results")
    @Expose
    var totalResults: Int,
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int,
    @SerializedName("results")
    @Expose
    var results: @RawValue MutableList<ResultTvShow>
)

@Parcelize
@Entity(tableName = AppConstant.TV_SHOW)
data class ResultTvShow(
    @SerializedName("original_name")
    @ColumnInfo(name = "original_name")
    @Expose
    var originalName: String,

    @SerializedName("genre_ids")
    @ColumnInfo(name = "genre_ids")
    @Expose
    @TypeConverters(ListIntConverter::class)
    var genreIds: List<Int>,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    @Expose
    var name: String,

    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    @Expose
    var popularity: Double,

    @SerializedName("origin_country")
    @ColumnInfo(name = "origin_country")
    @Expose
    @TypeConverters(ListStringConverter::class)
    var originCountry: List<String>,

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    @Expose
    var voteCount: Int,

    @SerializedName("first_air_date")
    @ColumnInfo(name = "first_air_date")
    @Expose
    var firstAirDate: String,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    @Expose
    var backdropPath: String,

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    @Expose
    var originalLanguage: String,

    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @Expose
    var id: Int,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    @Expose
    var voteAverage: Double,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    @Expose
    var overview: String,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    @Expose
    var posterPath: String
) : Parcelable