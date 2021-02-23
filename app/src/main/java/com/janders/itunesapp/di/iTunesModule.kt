package com.janders.itunesapp.di

import com.janders.itunesapp.data.interactor.IiTunesInteractor
import com.janders.itunesapp.data.interactor.iTunesInteractor
import com.janders.itunesapp.data.repository.IiTunesRepository
import com.janders.itunesapp.data.repository.iTunesRepository
import dagger.Module
import dagger.Provides

@Module
class iTunesModule {

    @Provides
    fun provideInteractor(): IiTunesInteractor {
        return iTunesInteractor()
    }

    @Provides
    fun provideRepository(): IiTunesRepository {
        return iTunesRepository()
    }
}