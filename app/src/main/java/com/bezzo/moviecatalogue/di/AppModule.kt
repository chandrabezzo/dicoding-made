package com.bezzo.moviecatalogue.di

import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.SchedulerProviderUtil
import com.bezzo.moviecatalogue.adapter.FavoriteRvAdapter
import com.bezzo.moviecatalogue.adapter.MovieRVAdapter
import com.bezzo.moviecatalogue.adapter.TvShowRVAdapter
import com.bezzo.moviecatalogue.features.about.AboutViewModel
import com.bezzo.moviecatalogue.features.favorite.FavoriteViewModel
import com.bezzo.moviecatalogue.features.movie.MovieViewModel
import com.bezzo.moviecatalogue.features.tvShow.TvShowViewModel
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { SessionHelper() }
    factory { CompositeDisposable() }
    single { Gson() }
    single { SchedulerProviderUtil() }
}

val viewModelModule = module {
    viewModel { AboutViewModel(get(), get()) }
    viewModel { MovieViewModel(get(), androidApplication()) }
    viewModel { TvShowViewModel(get(), androidApplication()) }
    viewModel { FavoriteViewModel(get(), androidApplication()) }
}
val rvAdapterModule = module {
    factory { MovieRVAdapter(androidContext(), ArrayList()) }
    factory { TvShowRVAdapter(androidContext(), ArrayList()) }
    factory { FavoriteRvAdapter(androidContext(), ArrayList()) }
}

val allModule = listOf(appModule, viewModelModule, rvAdapterModule)