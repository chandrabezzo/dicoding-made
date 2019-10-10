package com.bezzo.moviecatalogue.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bezzo.core.base.BaseHolder
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.core.util.GlideApp
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.data.model.ResultMovie
import kotlinx.android.synthetic.main.item_rv_movie.view.*

class MovieRVAdapter internal constructor(private val context: Context,
                                          private val list: MutableList<ResultMovie>)
    : RecyclerView.Adapter<MovieRVAdapter.Item>(){

    lateinit var listener: OnItemClickListener

    fun setOnClick(listener: OnItemClickListener){
        this.listener = listener
    }

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(inflater.inflate(R.layout.item_rv_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.model = list[position]
    }

    fun setItem(values: MutableList<ResultMovie>){
        list.clear()
        list.addAll(values)
    }

    inner class Item(itemView: View): BaseHolder<ResultMovie>(itemView){

        init {
            itemView.setOnClickListener { listener.onItemClick(it, layoutPosition) }
        }

        override fun setContent(model: ResultMovie) {
            itemView.tv_judul.text = "${model.title} (${model.releaseDate})"
            itemView.tv_desc.text = "${model.voteAverage}"
            val image = "https://image.tmdb.org/t/p/w185/${model.posterPath}"
            GlideApp.with(context).load(image).into(itemView.iv_profile)
        }
    }
}