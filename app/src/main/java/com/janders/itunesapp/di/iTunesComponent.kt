package com.janders.itunesapp.di

import com.janders.itunesapp.data.interactor.iTunesInteractor
import com.janders.itunesapp.views.search.SearchSongsViewModel
import dagger.Component

@Component(modules = arrayOf(iTunesModule::class))
interface iTunesComponent {
    fun inject(searchSongsViewModel: SearchSongsViewModel)
    fun inject(marvelInteractor: iTunesInteractor)
}