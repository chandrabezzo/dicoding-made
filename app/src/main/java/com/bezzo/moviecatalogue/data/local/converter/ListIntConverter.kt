package com.bezzo.moviecatalogue.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ListIntConverter {
    @TypeConverter
    fun fromListToJson(list: MutableList<Int>): String? {
        val gson = Gson()
        val type = object : TypeToken<MutableList<Int>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun fromJsonToList(json: String?): MutableList<Int>? {
        if (json == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<MutableList<Int>>() {}.type
        return gson.fromJson(json, type)
    }
}