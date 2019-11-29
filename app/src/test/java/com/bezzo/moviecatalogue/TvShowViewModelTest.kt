package com.bezzo.moviecatalogue

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.moviecatalogue.data.MovieRepository
import com.bezzo.moviecatalogue.data.model.TvShow
import com.bezzo.moviecatalogue.features.tvShow.TvShowViewModel
import com.bezzo.moviecatalogue.util.FakeData
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class TvShowViewModelTest {

    lateinit var viewModel: TvShowViewModel
    private val repository: MovieRepository = mock(MovieRepository::class.java)
    private val observer: Observer<MutableList<TvShow>> = mock(Observer::class.java) as Observer<MutableList<TvShow>>

    @Rule @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        viewModel = TvShowViewModel(repository, SessionHelper())
    }

    @Test
    fun getTvShow(){
        val dummyTvShow = FakeData.dataTvShows()
        val tvShows: MutableLiveData<MutableList<TvShow>> = MutableLiveData()
        tvShows.value = dummyTvShow
        `when`(repository.getTvShows()).thenReturn(tvShows)
        viewModel.getTv().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}