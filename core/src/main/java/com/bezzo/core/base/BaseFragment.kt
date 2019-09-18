package com.bezzo.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bezzo.core.extension.AppExtensions
import kotlinx.android.synthetic.main.default_toolbar.*

/**
 * Created by bezzo on 21/12/17.
 */

abstract class BaseFragment : Fragment(), BaseFragmentContract {

    var baseActivity: BaseActivity? = null
    var mProgressDialog: AlertDialog? = null
    var dataReceived: Bundle? = null
    private lateinit var rootView: View
    var mContext: Context? = null

    protected abstract fun onViewInitialized(savedInstanceState: Bundle?)

    protected fun showOptionMenu() {
        setHasOptionsMenu(true)
    }

    protected fun hideOptionMenu() {
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(setLayout(), container, false)
        dataReceived = arguments
        mContext = activity

        if ((activity as BaseActivity).default_toolbar != null){
            (activity as BaseActivity).default_toolbar.setNavigationOnClickListener(View.OnClickListener { view: View? ->
                onBackPressed()
            })
        }

        onViewInitialized(savedInstanceState)
        return rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.baseActivity = activity
            activity?.onFragmentAttached()
        }
    }

    override fun getContext(): Context? {
        return this.mContext
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    abstract fun setLayout() : Int

    override fun showProgressDialog(message: String) {
        context?.let { context ->
            mProgressDialog = AppExtensions.showProgressDialog(context, message)
            mProgressDialog?.show()
        }
    }

    override fun hideProgressDialog() {
        mProgressDialog?.dismiss()
    }

    fun setActionBarTitle(title: String) {
        (activity as BaseActivity).setActionBarTitle(title)
    }

    interface Callback {
        fun onFragmentAttached()

        fun onFragmentDetached(TAG: String)
    }

    override fun onBackPressed() {
        (activity as BaseActivity).onNavigationClick()
    }

    override fun handleError(case: Int, message: String) {
        (activity as BaseActivity).handleError(case, message)
    }
}
