package com.bezzo.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.error.ANError
import com.bezzo.core.BuildConfig
import com.bezzo.core.CoreModul
import com.bezzo.core.data.session.SessionConstants
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.AppLoggerUtil
import com.bezzo.core.util.CommonUtil
import com.google.gson.Gson
import java.util.concurrent.Executors

/**
 * Created by bezzo on 26/09/17.
 */

open class BaseViewModel(
    private val sessionHelper: SessionHelper
) : ViewModel(), BaseViewModelContract {

    val state = MutableLiveData<ViewModelState>()

    override fun handleApiError(error: ANError): String {
        var message = ""
        if (CommonUtil.isJSONValid(error.errorBody)){
            val apiError = Gson().fromJson(error.errorBody,
                    BaseResponse::class.java)

            if (apiError != null) {
                when {
                    error.errorCode == 401 -> logout()
                    error.errorCode == 422 -> {
                        message = apiError.toString()
                    }
                    else -> {
                        message = apiError.error ?: "Error"
                    }
                }
            } else {
                message = error.message ?: "Request Error"
            }
        }
        else {
            if (error.toString().contains("UnknownHost")){
                message = "UnknownHost"
            }
            else if (error.toString().contains("timed out") || error.toString().contains("timeout")){
                message = "timed out"
            }
            else if (error.toString().contains("java") || error.toString().contains("html")){
                message = "service error"
            }
            else if (error.errorBody != null) {
                message = if (error.errorBody.contains("html") || error.errorBody.contains("java")) {
                    "service error"
                } else {
                    error.errorBody
                }
            }
            else {
                message = error.errorDetail
            }
        }

        return message
    }

    override fun setUserAsLoggedOut() {

    }

    override fun clearLog() {
        sessionHelper.deleteSession(SessionConstants.TOKEN)
        sessionHelper.deleteSession(SessionConstants.USER_IDENTITY)
        sessionHelper.deleteSession(SessionConstants.NO_PHONE)
        sessionHelper.clearSession()

        val exec = Executors.newSingleThreadExecutor()
        exec.execute { }
    }

    override fun logout() {
        clearLog()
        CoreModul.getInstance().getCoreListener()?.onLogedOut()
    }

    companion object {
        private val TAG = "BaseViewModel"
    }

    override fun logging(message: String?) {
        if (message != null && BuildConfig.DEBUG){
            AppLoggerUtil.i(message)
        }
    }
}
