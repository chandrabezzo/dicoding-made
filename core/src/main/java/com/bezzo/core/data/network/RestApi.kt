package com.bezzo.core.data.network

import com.androidnetworking.common.Priority
import com.bezzo.core.util.AppLoggerUtil
import com.rx2androidnetworking.Rx2ANRequest
import com.rx2androidnetworking.Rx2AndroidNetworking
import java.io.File
import java.util.concurrent.Executors

object RestApi {

    fun get(endpoint: String, params: Map<String, String>?,
            paths: Map<String, String>?, headers: Map<String, String>?): Rx2ANRequest {

        val getRequest = Rx2AndroidNetworking.get(endpoint)

        if (headers != null) {
            getRequest.addHeaders(headers)
        }

        if (params != null) {
            getRequest.addQueryParameter(params)
        }

        if (paths != null) {
            getRequest.addPathParameter(paths)
        }

        getRequest.setPriority(Priority.LOW)

        getRequest.setOkHttpClient(HttpClient.crudClient())

        return getRequest.build()
    }

    fun post(endpoint: String, params : Map<String, String>?, paths: Map<String, String>?,
             headers: Map<String, String>?, body: Any?): Rx2ANRequest {

        val postRequest = Rx2AndroidNetworking.post(endpoint)
        AppLoggerUtil.i("endpoint : $endpoint")

        if (params != null){
            postRequest.addQueryParameter(params)
        }

        if (paths != null) {
            postRequest.addPathParameter(paths)
        }

        if (headers != null) {
            postRequest.addHeaders(headers)
        }

        postRequest.addApplicationJsonBody(body)
        postRequest.setPriority(Priority.MEDIUM)

        postRequest.setOkHttpClient(HttpClient.crudClient())

        return postRequest.build()
    }

    fun put(endpoint: String, params : Map<String, String>?, paths: Map<String, String>?,
            headers: Map<String, String>?, body: Any?): Rx2ANRequest {

        val putRequest = Rx2AndroidNetworking.put(endpoint)

        if (params != null){
            putRequest.addQueryParameter(params)
        }

        if (headers != null) {
            putRequest.addHeaders(headers)
        }

        if (paths != null) {
            putRequest.addPathParameter(paths)
        }

        putRequest.addApplicationJsonBody(body)
        putRequest.setPriority(Priority.MEDIUM)

        putRequest.setOkHttpClient(HttpClient.crudClient())

        return putRequest.build()
    }

    fun delete(endpoint: String, params: Map<String, String>?, paths: Map<String, String>?,
               headers: Map<String, String>?, body: Any?): Rx2ANRequest {

        val deleteRequest = Rx2AndroidNetworking.delete(endpoint)

        if (params != null){
            deleteRequest.addQueryParameter(params)
        }

        if (headers != null) {
            deleteRequest.addHeaders(headers)
        }

        if (paths != null) {
            deleteRequest.addPathParameter(paths)
        }

        deleteRequest.addApplicationJsonBody(body)
        deleteRequest.setPriority(Priority.MEDIUM)
        deleteRequest.setOkHttpClient(HttpClient.crudClient())

        return deleteRequest.build()
    }

    fun download(endpoint: String, savedLocation: String, fileName: String,
                 params: Map<String, String>?, paths: Map<String, String>?,
                 headers: Map<String, String>?): Rx2ANRequest {

        val downloadBuilder = Rx2AndroidNetworking.download(endpoint,
                savedLocation, fileName)

        if (headers != null) {
            downloadBuilder.addHeaders(headers)
        }

        if (params != null) {
            downloadBuilder.addQueryParameter(params)
        }

        if (paths != null) {
            downloadBuilder.addPathParameter(paths)
        }

        downloadBuilder.setPercentageThresholdForCancelling(50)
        downloadBuilder.setExecutor(Executors.newSingleThreadExecutor())
        downloadBuilder.setPriority(Priority.MEDIUM)

        return downloadBuilder.build()
    }

    fun upload(endpoint: String, params: Map<String, String>?, paths: Map<String, String>?,
               headers: Map<String, String>?, parameterFile: String, file: File,
               multipart: Map<String, String>?): Rx2ANRequest {

        return Rx2AndroidNetworking.upload(endpoint)
                .addHeaders(headers)
                .addMultipartFile(parameterFile, file)
                .addMultipartParameter(multipart)
                .setExecutor(Executors.newSingleThreadExecutor())
                .setPriority(Priority.HIGH)
                .setOkHttpClient(HttpClient.uploadClient())
                .build()
    }

    fun uploads(endpoint: String, params: Map<String, String>, paths: Map<String, String>,
                headers: Map<String, String>, files: Map<String, File>, multipart: Map<String, String>): Rx2ANRequest {
        return Rx2AndroidNetworking.upload(endpoint)
                .addHeaders(headers)
                .addMultipartFile(files)
                .addMultipartParameter(multipart)
                .setExecutor(Executors.newSingleThreadExecutor())
                .setPriority(Priority.HIGH)
                .setOkHttpClient(HttpClient.uploadClient())
                .build()
    }
}