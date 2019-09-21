package com.bezzo.moviecatalogue.data.model

import android.os.Parcelable
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
data class ResultMovie(
    @SerializedName("popularity")
    @Expose
    val popularity: Double,
    @SerializedName("vote_count")
    @Expose
    val voteCount: Int,
    @SerializedName("video")
    @Expose
    val video: Boolean,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("adult")
    @Expose
    val adult: Boolean,
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String,
    @SerializedName("original_language")
    @Expose
    val originalLanguage: String,
    @SerializedName("original_title")
    @Expose
    val originalTitle: String,
    @SerializedName("genre_ids")
    @Expose
    val genreIds: List<Int>,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double,
    @SerializedName("overview")
    @Expose
    val overview: String,
    @SerializedName("release_date")
    @Expose
    val releaseDate: String
) : Parcelable