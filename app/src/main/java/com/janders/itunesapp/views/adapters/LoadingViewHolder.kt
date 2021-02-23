package com.janders.itunesapp.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.janders.itunesapp.R
import com.janders.itunesapp.data.model.ResultsModel

class LoadingViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
    R.layout.item_progress, parent, false)) {

    private var context: Context? = null
    var progressBar: ProgressBar? = null

    init {
        context = parent.context
        progressBar = itemView.findViewById(R.id.progress)
    }

    fun bind(results: ResultsModel) {
        progressBar?.visibility = View.VISIBLE
    }
}