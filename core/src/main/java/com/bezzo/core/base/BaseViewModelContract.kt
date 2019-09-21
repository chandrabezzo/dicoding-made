package com.bezzo.core.base

import com.androidnetworking.error.ANError

/**
 * Created by bezzo on 21/12/17.
 */

interface BaseViewModelContract {

    fun handleApiError(error: ANError): String

    fun setUserAsLoggedOut()

    fun logout()

    fun clearLog()

    fun logging(message : String?)
}