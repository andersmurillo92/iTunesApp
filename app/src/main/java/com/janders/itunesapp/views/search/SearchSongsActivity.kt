package com.janders.itunesapp.views.search

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.janders.itunesapp.R
import com.janders.itunesapp.data.model.ResultsModel
import com.janders.itunesapp.utils.Animations
import com.janders.itunesapp.views.adapters.PaginationAdapter
import com.janders.itunesapp.views.base.BaseActivity
import com.janders.itunesapp.views.pagination.PaginationScrollListener
import kotlinx.android.synthetic.main.activity_search_songs.*

class SearchSongsActivity : BaseActivity(), SearchView.OnQueryTextListener {

    lateinit var viewModel: SearchSongsViewModel
    var adapter: PaginationAdapter? = null
    var animations = Animations()
    private val list = ArrayList<ResultsModel>()
    private var currentOffset = 0
    private var limit = 7
    private var newLimit = 7
    private var total = 0
    private var thereIsNoMoreItems = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_songs)
        initializeView()
        initializeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.singleLiveEvent.observe(this, Observer {
            hideProgressDialog()
            when(it){
                is SearchSongsViewModel.ViewEvent.ResponseSuccess -> {
                    if(total == 0)
                        total = viewModel.total
                    updateList()
                }
                is SearchSongsViewModel.ViewEvent.ResponseError -> {
                    showSimpleToast(it.error.toString())
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search_songs, menu)
        val searchItem = menu!!.findItem(R.id.search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            hideKeyboard(this)
            it.replace(" ","+")
            showProgressDialog(getString(R.string.message_title_getting_data), getString(R.string.message_please_wait), indeterminate = true, cancelable = false)
            resetForNewSearch()
            makeRequest(it)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun resetForNewSearch(){
        adapter = null
        currentOffset = 0
        newLimit = 7
        total = 0
        thereIsNoMoreItems = false
    }

    private fun makeRequest(search: String){
        viewModel.getArtist(search)
    }

    private fun updateList(){
        if(!thereIsNoMoreItems){
            list.clear()

            viewModel.songList.value?.subList(currentOffset, newLimit)?.forEach {
                list.add(it)
            }

            if(adapter != null){
                adapter?.removeLoadingFooter()
                adapter?.addAll(list)
            } else {
                adapter = PaginationAdapter(list)
                searchSongsRecycler.adapter = adapter
                animations.runRecyclerAnimation(searchSongsRecycler)
            }
        } else
            adapter?.removeLoadingFooter()
    }

    private fun loadNextPage(){
        adapter?.addLoadingFooter()
        updateList()
    }

    private fun initializeView(){
        val linearLayoutManager = LinearLayoutManager(this)
        searchSongsRecycler.layoutManager = linearLayoutManager
        searchSongsRecycler.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override var isLastPage: Boolean = false

            override var isLoading: Boolean = false

            override fun loadMoreItems() {
                currentOffset += limit
                newLimit = currentOffset+limit
                if(currentOffset < total){
                    when {
                        newLimit < total -> {
                            loadNextPage()
                        }
                        newLimit > total -> {
                            newLimit = total
                        }
                        else -> {
                            thereIsNoMoreItems = true
                        }
                    }
                }
            }
        })
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this).get(SearchSongsViewModel::class.java)
    }
}