package com.example.alejofila.themovies.movies.ui


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.example.alejofila.themovies.R
import com.example.alejofila.themovies.common.uimodel.MoviesUiModel
import com.example.alejofila.themovies.movies.adapter.MoviesAdapter
import com.example.alejofila.themovies.movies.presenter.PopularMoviesPresenter
import com.example.alejofila.themovies.movies.viewcontract.PopularMoviesView
import com.example.alejofila.themovies.populartv.viewutils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MoviesActivity : AppCompatActivity(), PopularMoviesView {


    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private lateinit var adapter: MoviesAdapter

    private val presenter: PopularMoviesPresenter by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = MoviesAdapter()
        recycler.adapter = adapter
        presenter.view = this
        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager
        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                presenter.queryPopularMovies()
            }
        }
        recycler.addOnScrollListener(scrollListener)
        presenter.queryPopularMovies()


    }

    override fun showNextPageOfMovies(movies: List<MoviesUiModel>) {
        adapter.appendData(movies)
    }


    override fun showNoMoreMoviesMessage() {
        Toast.makeText(this,R.string.no_more_shows,Toast.LENGTH_SHORT)
            .show()
    }
    override fun showServerError() {
        Toast.makeText(this,R.string.server_error,Toast.LENGTH_SHORT)
            .show()
    }
    override fun showEmptyView() {
        empty_view.visibility = View.VISIBLE
        recycler.visibility = View.GONE

    }
}
