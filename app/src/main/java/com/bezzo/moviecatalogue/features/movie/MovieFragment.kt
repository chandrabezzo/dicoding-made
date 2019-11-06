package com.bezzo.moviecatalogue.features.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bezzo.core.base.*
import com.bezzo.core.extension.hide
import com.bezzo.core.extension.launchActivity
import com.bezzo.core.extension.show
import com.bezzo.core.extension.toast
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.adapter.MovieRVAdapter
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.model.ResultMovie
import com.bezzo.moviecatalogue.features.detailMovie.DetailMovieActivity
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.android.ext.android.inject


class MovieFragment : BaseFragment() {

    private val viewModel: MovieViewModel by inject()
    private val adapter: MovieRVAdapter by inject()
    private val list : MutableList<ResultMovie> = ArrayList()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        context?.let {
            val layoutManager = LinearLayoutManager(it)
            rv_movie.layoutManager = layoutManager
            rv_movie.adapter = adapter
            viewModel.state.observe(this, movies)
            viewModel.getMovie()

            adapter.setOnClick(object : OnItemClickListener{
                override fun onItemClick(itemView: View, position: Int) {
                    launchActivity<DetailMovieActivity>{
                        putExtra(AppConstant.DATA_MOVIE, list[position])
                    }
                }

                override fun onItemLongClick(itemView: View, position: Int): Boolean {
                    return true
                }
            })
        }
    }

    override fun setLayout(): Int {
        return R.layout.fragment_movie
    }

    private val movies = Observer<ViewModelState> {

        when(it){
            is Loading -> {
                rv_movie.hide()
                mb_retry.hide()
                pb_movie.show()
            }
            is Receive<*> -> {
                pb_movie.hide()
                rv_movie.show()
                mb_retry.hide()

                list.clear()
                list.addAll(it.data as MutableList<ResultMovie>)
                adapter.setItem(list)
                adapter.notifyDataSetChanged()
            }
            is Error -> {
                context?.toast(it.message)

                mb_retry.show()
                pb_movie.hide()
                rv_movie.hide()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val mSearchmenuItem = menu.findItem(R.id.nav_search)
        val searchView = mSearchmenuItem.actionView as SearchView
        searchView.queryHint = getString(R.string.name)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query?.isEmpty() == true){
                    viewModel.getMovie()
                }
                else {
                    viewModel.searchMovie(query)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if(query?.isEmpty() == true){
                    viewModel.getMovie()
                }
                else {
                    viewModel.searchMovie(query)
                }
                return true
            }
        })
    }
}