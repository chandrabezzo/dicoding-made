package com.bezzo.moviecatalogue.util

import android.app.Application
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.data.model.TvShow
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private var application: Application) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val inputStream = application.assets.open(fileName)
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)
        } catch (e: IOException){
            e.printStackTrace()
            null
        }
    }

    fun loadMovies(): MutableList<Movie> {
        val list = ArrayList<Movie>()
        try {
            val responseObject = JSONObject(parsingFileToString("movie.json"))
            val jsonArray = responseObject.getJSONArray("movies")
            for(counter: Int in 0 until jsonArray.length()){
                val value = jsonArray.getJSONObject(counter)
                val popularity = value.getDouble("popularity")
                val voteCount = value.getInt("vote_count")
                val video = value.getBoolean("video")
                val posterPath = value.getString("poster_path")
                val id = value.getInt("id")
                val adult = value.getBoolean("adult")
                val backdropPath = value.getString("backdrop_path")
                val originalLanguage = value.getString("original_language")
                val originalTitle = value.getString("original_title")
                val title = value.getString("title")
                val voteAverage = value.getDouble("vote_average")
                val overview = value.getString("overview")
                val releaseDate = value.getString("release_date")

                val movie = Movie(popularity, voteCount, video, posterPath, id,
                    adult, backdropPath, originalLanguage, originalTitle, title, voteAverage,
                    overview, releaseDate)

                list.add(movie)
            }
        } catch (e: JSONException){
            e.printStackTrace()
        }

        return list
    }

    fun loadTvShow(): MutableList<TvShow> {
        val list = ArrayList<TvShow>()
        try {
            val responseObject = JSONObject(parsingFileToString("tv_show.json"))
            val jsonArray = responseObject.getJSONArray("tvShows")
            for(counter: Int in 0 until jsonArray.length()){
                val value = jsonArray.getJSONObject(counter)
                val originalName = value.getString("original_name")
                val name = value.getString("name")
                val popularity = value.getDouble("popularity")
                val voteCount = value.getInt("vote_count")
                val firstAirDate = value.getString("first_air_date")
                val backdropPath = value.getString("backdrop_path")
                val originalLanguage = value.getString("original_language")
                val id = value.getInt("id")
                val voteAverage = value.getDouble("vote_average")
                val overview = value.getString("overview")
                val posterPath = value.getString("poster_path")

                val tvShow = TvShow(originalName, name, popularity, voteCount, firstAirDate,
                    backdropPath, originalLanguage, id, voteAverage, overview, posterPath)
                list.add(tvShow)
            }
        } catch (e: JSONException){
            e.printStackTrace()
        }

        return list
    }
}