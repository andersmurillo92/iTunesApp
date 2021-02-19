package com.janders.itunesapp.views.favorites

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.janders.itunesapp.R
import com.janders.itunesapp.views.base.BaseActivity
import com.janders.itunesapp.views.search.SearchSongsActivity
import kotlinx.android.synthetic.main.activity_favorites.*

class FavoriteActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        setListeners()
    }

    private fun setListeners(){
        favoriteFAB.setOnClickListener {
            goToActivity(SearchSongsActivity::class.java)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorites, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {
                //goToActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun <T: Activity>goToActivity(classType: Class<T>) {
        startActivity(Intent(this, classType))
    }
}