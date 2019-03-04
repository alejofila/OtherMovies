package com.example.alejofila.themovies.movies.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.alejofila.data.network.TmdbService
import com.example.alejofila.themovies.common.uimodel.MoviesUiModel
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(movies: MoviesUiModel) {
        itemView.run {
            item_poster_title.text = movies.title
            item_poster_overview.text = movies.overview
            item_poster_release_date.text = movies.releaseDate
            movies.imagePath?.let {
                Picasso.with(this.context)
                    .load(TmdbService.getPosterPath(movies.imagePath))
                    .resize(300,300)
                    .noFade()
                    .into(object : Target {
                        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                        }

                        override fun onBitmapFailed(errorDrawable: Drawable?) {
                        }

                        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                            item_poster_post.setImageBitmap(bitmap)
                            Palette.from(bitmap)
                                .generate { palette ->
                                    val textWatch = palette.vibrantSwatch
                                    textWatch?.let {
                                        item_poster_palette.setBackgroundColor(textWatch.rgb)
                                        item_poster_release_date.setTextColor(textWatch.bodyTextColor)
                                        item_poster_title.setTextColor(textWatch.titleTextColor)
                                    }

                                }
                        }
                    })
            }


        }
    }
    fun clear(){
        itemView.item_poster_post.setImageDrawable(null)
    }

}