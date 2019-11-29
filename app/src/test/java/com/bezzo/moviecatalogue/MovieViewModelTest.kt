package com.bezzo.moviecatalogue

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.moviecatalogue.data.MovieRepository
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.features.movie.MovieViewModel
import com.bezzo.moviecatalogue.util.FakeData
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class MovieViewModelTest {

    lateinit var viewModel: MovieViewModel
    private val repository: MovieRepository = mock(MovieRepository::class.java)
    private val observer: Observer<MutableList<Movie>> = mock(Observer::class.java) as Observer<MutableList<Movie>>

    @Rule @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        viewModel = MovieViewModel(repository, SessionHelper())
    }

    @Test
    fun getMovie(){
        val dummyMovie = FakeData.dataMovies()
        val movies: MutableLiveData<MutableList<Movie>> = MutableLiveData()
        movies.value = dummyMovie
        `when`(repository.getMovies()).thenReturn(movies)
        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}