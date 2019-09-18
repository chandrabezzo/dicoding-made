package com.bezzo.moviecatalogue.features.about

import android.os.Bundle
import android.view.View
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.data.model.Profile
import com.bezzo.core.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_about.*
import org.koin.android.ext.android.inject

class AboutFragment : BaseFragment(), AboutViewContract {

    private val presenter: AboutPresenter<AboutViewContract> by inject()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getProfile()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.fragment_about
    }

    override fun showProfile(value: Profile) {
        context?.let {
            iv_profile.setImageResource(value.image)
        }

        tv_nama.text = value.nama
        tv_email.text = value.email
    }
}
