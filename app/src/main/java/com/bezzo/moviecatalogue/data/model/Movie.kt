package com.bezzo.moviecatalogue.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val releaseYear: String,
    val duration: String,
    val genre: String,
    val userScore: String,
    val image: Int
) : Parcelable