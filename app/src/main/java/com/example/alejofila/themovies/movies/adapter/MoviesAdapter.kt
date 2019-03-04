package com.example.alejofila.themovies.movies.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.alejofila.themovies.R
import com.example.alejofila.themovies.common.uimodel.MoviesUiModel

class MoviesAdapter : RecyclerView.Adapter<MoviesViewHolder>() {

    private  var movies = mutableListOf<MoviesUiModel>()
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.movie_item,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder?, position: Int) {
        holder?.bind(movies[position])
    }

    fun appendData(moreViews: List<MoviesUiModel>) {
        val currentSize = movies.size
        movies.addAll(moreViews)
        val countSize = moreViews.size
        notifyItemRangeInserted(currentSize, countSize)
    }

    override fun onViewRecycled(holder: MoviesViewHolder?) {
        super.onViewRecycled(holder)
        holder?.clear()
    }
}