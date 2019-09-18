package com.bezzo.moviecatalogue.features.about

import com.bezzo.moviecatalogue.data.model.Profile
import com.bezzo.core.base.BaseFragmentContract
import com.bezzo.core.base.BasePresenterContract

interface AboutViewContract : BaseFragmentContract {
    fun showProfile(value: Profile)
}

interface AboutPresenterContracts<V: AboutViewContract>: BasePresenterContract<V> {
    fun getProfile()
}