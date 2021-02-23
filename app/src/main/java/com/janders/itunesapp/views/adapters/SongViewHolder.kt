package com.janders.itunesapp.views.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.janders.itunesapp.R
import com.janders.itunesapp.data.model.ResultsModel
import com.janders.itunesapp.utils.RUtil
import com.janders.itunesapp.utils.TextUtil.Companion.convertMillisToMinSec
import com.janders.itunesapp.views.detail.SongDetailActivity
import com.squareup.picasso.Picasso

class SongViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_song, parent, false)) {

    private var context: Context? = null
    var imageView: ImageView? = null
    var title: TextView? = null
    var artist: TextView? = null
    var genre: TextView? = null
    var duration: TextView? = null

    init {
        context = parent.context
        imageView = itemView.findViewById(R.id.iconIv)
        title = itemView.findViewById(R.id.trackName_txt)
        artist = itemView.findViewById(R.id.artist_txt)
        genre = itemView.findViewById(R.id.genre_txt)
        duration = itemView.findViewById(R.id.duration_txt)
    }

    fun bind(results: ResultsModel) {
        Picasso.with(context).isLoggingEnabled = true
        Picasso.with(context)
            .load(results.artworkUrl100)
            .into(imageView)

        title?.text = results.trackName
        artist?.text = results.artistName
        genre?.text = "${RUtil.rString(R.string.genre)} ${results.primaryGenreName}"
        duration?.text = "${RUtil.rString(R.string.duration)} ${convertMillisToMinSec(results.trackTimeMillis)}"

        itemView.setOnClickListener {
            showMovieDetails(context, results)
        }
    }

    private fun showMovieDetails(context: Context?, results: ResultsModel){
        context?.startActivity(
            Intent(context, SongDetailActivity::class.java)
                .putExtra("banner", results.artworkUrl100)
                .putExtra("title", results.trackName)
                .putExtra("artistName", results.artistName)
                .putExtra("collectionName", results.collectionName)
                .putExtra("genre", results.primaryGenreName)
                .putExtra("duration", results.trackTimeMillis)
        )
    }
}