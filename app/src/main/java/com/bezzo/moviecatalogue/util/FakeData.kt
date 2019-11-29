package com.bezzo.moviecatalogue.util

import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.data.model.TvShow

object FakeData {

    fun dataMovies(): MutableList<Movie> {
        val movies: MutableList<Movie> = ArrayList()

        movies.add(Movie(243.884, 5169, false, "/lcq8dVxeeOqHvvgcte707K0KVx5.jpg",
            429617, false, "/5myQbDzw3l8K9yofUXRJ4UTVgam.jpg", "en",
            "Spider-Man: Far from Home", "Spider-Man: Far from Home", 7.6,
            "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
            "2019-06-28"))

        movies.add(Movie(243.884, 5169, false, "/lcq8dVxeeOqHvvgcte707K0KVx5.jpg",
            429617, false, "/5myQbDzw3l8K9yofUXRJ4UTVgam.jpg", "en",
            "Spider-Man: Far from Home", "Spider-Man: Far from Home", 7.6,
            "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
            "2019-06-28"))

        return movies
    }

    fun dataTvShows(): MutableList<TvShow> {
        val tvShows: MutableList<TvShow> = ArrayList()

        tvShows.add(TvShow("The Mandalorian", "The Mandalorian", 520.462,
            134, "2019-11-12", "/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
            "en", 82856, 7.7, "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.",
            "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg"))

        tvShows.add(TvShow("The Mandalorian", "The Mandalorian", 520.462,
            134, "2019-11-12", "/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
            "en", 82856, 7.7, "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.",
            "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg"))

        return tvShows
    }
}