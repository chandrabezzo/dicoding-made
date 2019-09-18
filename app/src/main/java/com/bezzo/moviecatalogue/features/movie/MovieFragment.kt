package com.bezzo.moviecatalogue.features.movie

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
import kotlinx.android.synthetic.main.fragment_friend.*
import org.koin.android.ext.android.inject

class MovieFragment : BaseFragment(), MovieViewContract {

    private val presenter: MoviePresenter<MovieViewContract> by inject()
    val adapter: MovieRVAdapter by inject()
    val list = ArrayList<Movie>()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        context?.let {
            val layoutManager = LinearLayoutManager(it)
            rv_friend.layoutManager = layoutManager
            rv_friend.adapter = adapter
            presenter.getMovie()

            adapter.setOnClick(object : OnItemClickListener{
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
        return R.layout.fragment_friend
    }

    override fun showMovies(values: ArrayList<Movie>) {
        list.clear()
        list.addAll(values)
        adapter.setItem(list)
        adapter.notifyDataSetChanged()
    }
}