package com.bezzo.moviecatalogue.di

import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.SchedulerProviderUtil
import com.bezzo.moviecatalogue.adapter.MovieRVAdapter
import com.bezzo.moviecatalogue.adapter.TvShowRVAdapter
import com.bezzo.moviecatalogue.data.network.ApiHelper
import com.bezzo.moviecatalogue.features.about.AboutViewModel
import com.bezzo.moviecatalogue.features.movie.MovieViewModel
import com.bezzo.moviecatalogue.features.tvShow.TvShowViewModel
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { SessionHelper() }
    factory { CompositeDisposable() }
    single { Gson() }
    single { SchedulerProviderUtil() }
    single { ApiHelper(get()) }
}

val viewModelModule = module {
    viewModel { AboutViewModel(get(), get(), get()) }
    viewModel { MovieViewModel(get(), get()) }
    viewModel { TvShowViewModel(get(), get()) }
}
val rvAdapterModule = module {
    factory { MovieRVAdapter(androidContext(), ArrayList()) }
    factory { TvShowRVAdapter(androidContext(), ArrayList()) }
}

val allModule = listOf(appModule, viewModelModule, rvAdapterModule)