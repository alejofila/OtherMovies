package com.example.alejofila.themovies.movies.adapter

import android.graphics.Bitmap
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.example.alejofila.data.network.TmdbService
import com.example.alejofila.themovies.common.uimodel.MoviesUiModel
import kotlinx.android.synthetic.main.movie_item.view.*


class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(movies: MoviesUiModel) {
        itemView.run {
            item_poster_title.text = movies.title
            item_poster_overview.text = movies.overview
            item_poster_release_date.text = movies.releaseDate
            if (movies.imagePath.isNullOrBlank()) {
                return
            }
            Glide.with(this)
                .asBitmap()
                .load(TmdbService.getPosterPath(movies.imagePath))
                .into(CustomBitMapImageViewTarget(this, item_poster_post))
        }
    }
    fun clear() {
        itemView.item_poster_post.setImageDrawable(null)
    }

}

class CustomBitMapImageViewTarget(private val itemView: View, imageView: ImageView) : BitmapImageViewTarget(imageView) {
    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
        super.onResourceReady(resource, transition)
        Palette.PaletteAsyncListener { palette ->
            val textWatch = palette?.vibrantSwatch ?: return@PaletteAsyncListener
            textWatch.let {
                itemView.run {
                    item_poster_palette.setBackgroundColor(textWatch.rgb)
                    item_poster_release_date.setTextColor(textWatch.bodyTextColor)
                    item_poster_title.setTextColor(textWatch.titleTextColor)
                }

            }
        }
    }
}
