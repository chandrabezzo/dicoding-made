package com.bezzo.core.data.session

import com.orhanobut.hawk.Hawk

/**
 * Created by bezzo on 11/01/18.
 */

class SessionHelper {

    fun getSession(key: String, defaultValue: String): String {
        return Hawk.get<String>(key, defaultValue)
    }

    fun getSession(key: String, defaultValue: Int?): Int? {
        return Hawk.get<Int>(key, defaultValue)
    }

    fun getSession(key: String, defaultValue: Double?): Double? {
        return Hawk.get<Double>(key, defaultValue)
    }

    fun getSession(key: String, defaultValue: Boolean?): Boolean? {
        return Hawk.get<Boolean>(key, defaultValue)
    }

    fun getSession(key: String, defaultValue: Long?): Long? {
        return Hawk.get<Long>(key, defaultValue)
    }

    fun getSession(key: String, defaultValue: Short?): Short? {
        return Hawk.get<Short>(key, defaultValue)
    }

    fun getSession(key: String, defaultValue: Byte?): Byte? {
        return Hawk.get<Byte>(key, defaultValue)
    }

    fun getSession(key: String, defaultValue: Char?): Char? {
        return Hawk.get<Char>(key, defaultValue)
    }

    fun getSession(key: String, defaultValue: Float?): Float? {
        return Hawk.get<Float>(key, defaultValue)
    }

    fun addSession(key: String, value: String) {
        Hawk.put(key, value)
    }

    fun addSession(key: String, value: Int?) {
        Hawk.put<Int>(key, value)
    }

    fun addSession(key: String, value: Double?) {
        Hawk.put<Double>(key, value)
    }

    fun addSession(key: String, value: Boolean?) {
        Hawk.put<Boolean>(key, value)
    }

    fun addSession(key: String, value: Long?) {
        Hawk.put<Long>(key, value)
    }

    fun addSession(key: String, value: Short?) {
        Hawk.put<Short>(key, value)
    }

    fun addSession(key: String, value: Byte?) {
        Hawk.put<Byte>(key, value)
    }

    fun addSession(key: String, value: Char?) {
        Hawk.put<Char>(key, value)
    }

    fun addSession(key: String, value: Float?) {
        Hawk.put<Float>(key, value)
    }

    fun deleteSession(key: String) {
        Hawk.delete(key)
    }

    fun clearSession() {
        Hawk.deleteAll()
    }
}
