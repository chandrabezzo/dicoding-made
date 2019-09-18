package com.bezzo.moviecatalogue.features.tvShow

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bezzo.core.base.BaseFragment
import com.bezzo.core.extension.launchActivity
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.adapter.MovieRVAdapter
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.features.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.koin.android.ext.android.inject

class TvShowFragment : BaseFragment(), TvShowViewContract {

    private val presenter: TvShowPresenter<TvShowViewContract> by inject()
    private val adapter: MovieRVAdapter by inject()
    private val list = ArrayList<Movie>()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            val layoutManager = LinearLayoutManager(it)
            rv_tv_show.layoutManager = layoutManager
            rv_tv_show.adapter = adapter
            presenter.getTvShows()

            adapter.setOnClick(object : OnItemClickListener {
                override fun onItemClick(itemView: View, position: Int) {
                    launchActivity<DetailActivity>{
                        putExtra(AppConstant.DATA_MOVIE, list[position])
                    }
                }

                override fun onItemLongClick(itemView: View, position: Int): Boolean {
                    return true
                }
            })
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.fragment_tv_show
    }

    override fun showTvShow(values: ArrayList<Movie>) {
        list.clear()
        list.addAll(values)
        adapter.setItem(list)
        adapter.notifyDataSetChanged()
    }
}
