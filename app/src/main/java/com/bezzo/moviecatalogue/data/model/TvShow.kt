package com.bezzo.moviecatalogue.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.RawValue

@Parcelize
data class TvShow(
    @SerializedName("original_name")
    @Expose
    var originalName: String,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("popularity")
    @Expose
    var popularity: Double,
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