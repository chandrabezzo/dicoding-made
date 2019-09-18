package com.bezzo.core.base

import com.androidnetworking.error.ANError

/**
 * Created by bezzo on 21/12/17.
 */

interface BasePresenterContract<in V : BaseViewContract> {

    fun onAttach(mvpView: V)

    fun onDetach()

    fun handleApiError(error: ANError)

    fun setUserAsLoggedOut()

    fun logout()

    fun clearLog()

    fun logging(message : String?)
}