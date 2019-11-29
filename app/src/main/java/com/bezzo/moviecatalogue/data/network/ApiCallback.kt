package com.bezzo.moviecatalogue.data.network

import com.androidnetworking.error.ANError

interface ApiCallback<M> {
    fun onResponse(data: M)

    fun onFailed(error: ANError)
}