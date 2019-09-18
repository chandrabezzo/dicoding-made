package com.bezzo.core.data.network

import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.OkHttpResponseAndParsedRequestListener
import okhttp3.Response

/**
 * Created by bezzo on 19/10/17.
 */

abstract class ResponseOkHttp<M> constructor(val successCode : Int)
    : OkHttpResponseAndParsedRequestListener<M> {

    override fun onResponse(okHttpResponse: Response, response: M) {
        when {
            okHttpResponse.code() == 200 -> onSuccess(okHttpResponse, response)
            okHttpResponse.code() == 401 -> onUnauthorized()
            else -> onFailed(okHttpResponse)
        }
    }

    override fun onError(anError: ANError) {
        onHasError(anError)
    }

    abstract fun onSuccess(response: Response, model: M)

    abstract fun onUnauthorized()

    abstract fun onFailed(response: Response)

    abstract fun onHasError(error : ANError)
}