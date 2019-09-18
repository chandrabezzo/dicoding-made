package com.bezzo.moviecatalogue.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val judul: String,
    val deskripsi: String,
    val tahunRilis: String,
    val durasi: String,
    val genre: String,
    val userScore: String,
    val image: Int
) : Parcelable