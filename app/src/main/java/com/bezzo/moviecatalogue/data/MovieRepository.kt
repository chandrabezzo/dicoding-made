package com.bezzo.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.data.model.TvShow

class MovieRepository(private val remoteRepository: RemoteRepository): MovieDataSource {

    companion object {
        @Volatile
        private var INSTANCE: MovieRepository? = null

        fun getInstance(remoteData: RemoteRepository?): MovieRepository? {
            if (INSTANCE == null) {
                synchronized(MovieRepository::class.java) {
                    if (INSTANCE == null) {
                        remoteData?.let {
                            INSTANCE = MovieRepository(it)
                        }
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun getMovies(): LiveData<MutableList<Movie>> {
        val movieResult: MutableLiveData<MutableList<Movie>> = MutableLiveData()
        remoteRepository.getMovies(object: LoadMovieCallback{
            override fun onDataReceived(values: MutableList<Movie>) {
                val movies: MutableList<Movie> = ArrayList()
                for(counter: Int in 0 until values.size){
                    val response = values[counter]
                    val movie = Movie(response.popularity, response.voteCount, response.video,
                        response.posterPath, response.id, response.adult, response.backdropPath,
                        response.originalLanguage, response.originalTitle, response.title,
                        response.voteAverage, response.overview, response.releaseDate)
                    movies.add(movie)
                }
                movieResult.postValue(movies)
            }

            override fun onDataNotAvailable() {

            }
        })

        return movieResult
    }

    override fun getTvShows(): LiveData<MutableList<TvShow>> {
        val tvShowResult: MutableLiveData<MutableList<TvShow>> = MutableLiveData()
        remoteRepository.getTvShows(object: LoadTvShowCallback{
            override fun onDataReceived(values: MutableList<TvShow>) {
                val tvShows: MutableList<TvShow> = ArrayList()
                for(counter: Int in 0 until values.size){
                    val response = values[counter]
                    val tvShow = TvShow(response.originalName, response.name, response.popularity,
                        response.voteCount, response.firstAirDate, response.backdropPath, response.originalLanguage,
                        response.id, response.voteAverage, response.overview, response.posterPath)
                    tvShows.add(tvShow)
                }
                tvShowResult.postValue(tvShows)
            }

            override fun onDataNotAvailable() {

            }
        })

        return tvShowResult
    }
}