package com.bezzo.moviecatalogue.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ListStringConverter {
    @TypeConverter
    fun fromListToJson(list: MutableList<String>): String? {
        val gson = Gson()
        val type = object : TypeToken<MutableList<String>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun fromJsonToList(json: String?): MutableList<String>? {
        if (json == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<MutableList<String>>() {}.type
        return gson.fromJson(json, type)
    }
}