package com.janders.itunesapp.views.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import com.janders.itunesapp.R
import com.janders.itunesapp.views.base.BaseActivity

class SearchSongsActivity : BaseActivity(), SearchView.OnQueryTextListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_songs)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search_songs, menu)
        val searchItem = menu!!.findItem(R.id.search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        // Search artist data
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun <T: Activity>goToActivity(classType: Class<T>) {
        startActivity(Intent(this, classType))
    }
}