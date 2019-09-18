package com.bezzo.core.base

import android.content.Context

/**
 * Created by bezzo on 21/12/17.
 */

interface BaseActivityContract : BaseViewContract {

    fun getContext(): Context?

    fun displayHome()

    fun setActionBarTitle(title: String)

    fun onClickBack()

    fun showProgressDialog(message: String)

    fun hideProgressDialog()
}