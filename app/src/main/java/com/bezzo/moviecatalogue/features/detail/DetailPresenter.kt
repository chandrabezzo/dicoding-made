package com.bezzo.moviecatalogue.features.detail

import com.bezzo.core.base.BasePresenter
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.SchedulerProviderUtil
import io.reactivex.disposables.CompositeDisposable

class DetailPresenter<V: DetailViewContract>(
    sessionHelper: SessionHelper,
    schedulerProvider: SchedulerProviderUtil,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V>(sessionHelper, schedulerProvider, compositeDisposable), DetailPresenterContract<V>{
}