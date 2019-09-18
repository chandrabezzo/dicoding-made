package com.bezzo.moviecatalogue.features.about

import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.data.model.Profile
import com.bezzo.core.base.BasePresenter
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.SchedulerProviderUtil
import io.reactivex.disposables.CompositeDisposable

class AboutPresenter<V: AboutViewContract>(
    sessionHelper: SessionHelper,
    schedulerProvider: SchedulerProviderUtil,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V>(sessionHelper, schedulerProvider, compositeDisposable), AboutPresenterContracts<V> {

    override fun getProfile() {
        val profile = Profile(
            R.drawable.foto_profile,
            "Chandra Abdul Fattah",
            "chandraaf28@gmail.com"
        )

        view?.showProfile(profile)
    }
}