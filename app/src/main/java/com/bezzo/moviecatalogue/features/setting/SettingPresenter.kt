package com.bezzo.moviecatalogue.features.setting

import com.bezzo.core.base.BasePresenter
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.SchedulerProviderUtil
import io.reactivex.disposables.CompositeDisposable

class SettingPresenter<V: SettingViewContract>(
    sessionHelper: SessionHelper,
    schedulerProvider: SchedulerProviderUtil,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V>(sessionHelper, schedulerProvider, compositeDisposable), SettingPresenterContract<V>