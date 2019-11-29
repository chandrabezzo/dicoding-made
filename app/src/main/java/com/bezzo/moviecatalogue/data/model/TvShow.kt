package com.bezzo.moviecatalogue.data.model

import android.os.Parcelable
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
    var results: @RawValue List<ResultTvShow>
)

@Parcelize
data class ResultTvShow(
    @SerializedName("original_name")
    @Expose
    var originalName: String,
    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int>,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("popularity")
    @Expose
    var popularity: Double,
    @SerializedName("origin_country")
    @Expose
    var originCountry: List<String>,
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int,
    @SerializedName("first_air_date")
    @Expose
    var firstAirDate: String,
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String,
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String,
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double,
    @SerializedName("overview")
    @Expose
    var overview: String,
    @SerializedName("poster_path")
    @Expose
    var posterPath: String
) : Parcelable