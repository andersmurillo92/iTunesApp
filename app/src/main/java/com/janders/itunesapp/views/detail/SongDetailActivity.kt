package com.janders.itunesapp.views.detail

import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.janders.itunesapp.R
import com.janders.itunesapp.utils.RUtil
import com.janders.itunesapp.utils.TextUtil.Companion.convertMillisToMinSec
import com.janders.itunesapp.views.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_song_detail.*

class SongDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loadImage()
        setData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadImage(){
        Picasso.with(supportActionBar?.themedContext)
            .load(intent.getStringExtra("banner"))
            .resize(800, 470)
            .into(songImage)
    }

    private fun setData(){
        collapsing_toolbar.title = intent.getStringExtra("title")
        collapsing_toolbar.setExpandedTitleColor(resources.getColor(android.R.color.transparent))

        songName.text = intent.getStringExtra("title")

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            artistName.text = Html.fromHtml("<b>${RUtil.rString(R.string.artist)}</b> ".plus(intent.getStringExtra("artistName") ?: RUtil.rString(R.string.not_available)), Html.FROM_HTML_MODE_LEGACY)
            collectionName.text = Html.fromHtml("<b>${RUtil.rString(R.string.album)}</b> ".plus(intent.getStringExtra("collectionName") ?: RUtil.rString(R.string.not_available)), Html.FROM_HTML_MODE_LEGACY)
            songGenre.text = Html.fromHtml("<b>${RUtil.rString(R.string.genre)}</b> ".plus(intent.getStringExtra("genre") ?: RUtil.rString(R.string.not_available)), Html.FROM_HTML_MODE_LEGACY)
            songDuration.text = Html.fromHtml("<b>${RUtil.rString(R.string.duration)}</b> ".plus(convertMillisToMinSec(intent?.getIntExtra("duration", 0))), Html.FROM_HTML_MODE_LEGACY)
        } else {
            artistName.text = Html.fromHtml("<b>${RUtil.rString(R.string.artist)}</b> ".plus(intent.getStringExtra("artistName")))
            collectionName.text = Html.fromHtml("<b>${RUtil.rString(R.string.album)}</b> ".plus(intent.getStringExtra("collectionName") ?: RUtil.rString(R.string.not_available)))
            songGenre.text = Html.fromHtml("<b>${RUtil.rString(R.string.genre)}</b> ".plus(intent.getStringExtra("genre") ?: RUtil.rString(R.string.not_available)))
            songDuration.text = Html.fromHtml("<b>${RUtil.rString(R.string.duration)}</b> ".plus(convertMillisToMinSec(intent?.getIntExtra("duration", 0))))
        }
    }
}