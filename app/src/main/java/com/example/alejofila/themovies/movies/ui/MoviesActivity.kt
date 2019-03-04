package com.example.alejofila.themovies.movies.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import android.widget.Toast
import com.example.alejofila.themovies.R
import com.example.alejofila.themovies.common.ext.hideKeyboard
import com.example.alejofila.themovies.common.uimodel.MoviesUiModel
import com.example.alejofila.themovies.movies.adapter.MoviesAdapter
import com.example.alejofila.themovies.movies.presenter.PopularMoviesPresenter
import com.example.alejofila.themovies.movies.viewcontract.PopularMoviesView
import com.example.alejofila.themovies.movies.viewutils.EndlessRecyclerViewScrollListener
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit


class MoviesActivity : AppCompatActivity(), PopularMoviesView,
    SearchView.OnQueryTextListener {


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
        presenter.queryPopularMovies(true)


    }

    @SuppressLint("CheckResult")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.searchBar)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_menu_text)
        searchView.isIconified = true
        RxSearchView.queryTextChanges(searchView)
            .filter { it.length >= 2 }
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribe { presenter.queryMoviesByKeyword(it.toString(), true) }
        searchView.setOnCloseListener {
            presenter.queryPopularMovies(true)
            searchView.hideKeyboard()
            return@setOnCloseListener false
        }
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        empty_view.hideKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            if (newText.isNotBlank()) presenter.queryMoviesByKeyword(newText, true)
        }
        return true
    }

    override fun showNextPageOfMovies(movies: List<MoviesUiModel>) {
        adapter.appendData(movies)
    }


    override fun showNoMoreMoviesMessage() {
        Toast.makeText(this, R.string.no_more_shows, Toast.LENGTH_SHORT)
            .show()
    }

    override fun showServerError() {
        Toast.makeText(this, R.string.server_error, Toast.LENGTH_SHORT)
            .show()
    }

    override fun showEmptyView() {
        empty_view.visibility = View.VISIBLE
        recycler.visibility = View.GONE
    }

    override fun hideEmptyView() {
        empty_view.visibility = View.GONE
        recycler.visibility = View.VISIBLE
    }

    override fun resetMovieList() {
        adapter.resetAdapter()
    }
}
