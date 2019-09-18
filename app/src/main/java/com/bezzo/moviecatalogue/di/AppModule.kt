package com.bezzo.moviecatalogue.di

import com.bezzo.moviecatalogue.adapter.MovieRVAdapter
import com.bezzo.moviecatalogue.data.network.ApiHelper
import com.bezzo.moviecatalogue.features.about.AboutPresenter
import com.bezzo.moviecatalogue.features.about.AboutViewContract
import com.bezzo.moviecatalogue.features.detail.DetailPresenter
import com.bezzo.moviecatalogue.features.detail.DetailViewContract
import com.bezzo.moviecatalogue.features.movie.MoviePresenter
import com.bezzo.moviecatalogue.features.movie.MovieViewContract
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.SchedulerProviderUtil
import com.bezzo.moviecatalogue.adapter.ListMovieVPAdapter
import com.bezzo.moviecatalogue.features.film.FilmPresenter
import com.bezzo.moviecatalogue.features.film.FilmViewContract
import com.bezzo.moviecatalogue.features.setting.SettingPresenter
import com.bezzo.moviecatalogue.features.setting.SettingViewContract
import com.bezzo.moviecatalogue.features.tvShow.TvShowPresenter
import com.bezzo.moviecatalogue.features.tvShow.TvShowViewContract
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {
    single { SessionHelper() }
    factory { CompositeDisposable() }
    single { Gson() }
    single { SchedulerProviderUtil() }
}

val presenterModule = module {
    factory { AboutPresenter<AboutViewContract>(get(), get(), get()) }
    factory { DetailPresenter<DetailViewContract>(get(), get(), get()) }
    factory { MoviePresenter<MovieViewContract>(get(), get(), get()) }
    factory { FilmPresenter<FilmViewContract>(get(), get(), get()) }
    factory { SettingPresenter<SettingViewContract>(get(), get(), get())}
    factory { TvShowPresenter<TvShowViewContract>(get(), get(), get()) }
}

val rvAdapterModule = module {
    factory { MovieRVAdapter(androidContext(), ArrayList()) }
}

val allModule = listOf(appModule, presenterModule, rvAdapterModule)