package com.bezzo.moviecatalogue.features.film

import com.bezzo.core.base.BasePresenter
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.SchedulerProviderUtil
import io.reactivex.disposables.CompositeDisposable

class FilmPresenter<V: FilmViewContract>(
    sessionHelper: SessionHelper,
    schedulerProvider: SchedulerProviderUtil,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V>(sessionHelper, schedulerProvider, compositeDisposable), FilmPresenterContract<V>