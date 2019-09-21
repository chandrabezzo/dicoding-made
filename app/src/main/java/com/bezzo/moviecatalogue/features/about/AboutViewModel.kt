package com.bezzo.moviecatalogue.features.about

import androidx.lifecycle.MutableLiveData
import com.bezzo.core.base.BaseViewModel
import com.bezzo.core.base.Receive
import com.bezzo.core.base.ViewModelState
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.SchedulerProviderUtil
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.data.model.Profile
import io.reactivex.disposables.CompositeDisposable

class AboutViewModel(
    sessionHelper: SessionHelper,
    schedulerProvider: SchedulerProviderUtil,
    compositeDisposable: CompositeDisposable
) : BaseViewModel(sessionHelper) {

    val movieState = MutableLiveData<ViewModelState>()

    fun getProfile() {
        val profile = Profile(
            R.drawable.foto_profile,
            "Chandra Abdul Fattah",
            "chandraaf28@gmail.com"
        )

        movieState.postValue(Receive(profile))
    }
}