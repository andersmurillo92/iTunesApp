package com.janders.itunesapp.data.interactor

import com.janders.itunesapp.data.dto.ResultsDTO
import com.janders.itunesapp.data.model.ResponseModel
import com.janders.itunesapp.data.model.ResultsModel
import com.janders.itunesapp.data.repository.IiTunesRepository
import com.janders.itunesapp.di.DaggeriTunesComponent
import com.janders.itunesapp.di.iTunesModule
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface IiTunesInteractor {
    fun getArtist(artist: String): Observable<ResponseModel>?
}

class iTunesInteractor: IiTunesInteractor {

    @Inject
    lateinit var repository: IiTunesRepository

    init {
        DaggeriTunesComponent.builder()
            .iTunesModule(iTunesModule())
            .build().inject(this)
    }

    override fun getArtist(artist: String): Observable<ResponseModel>? {
        return repository.getArtist(artist)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.flatMap {
                val responseModel = ResponseModel().apply {
                    this.resultCount = it.resultCount
                    this.results = convertResultsDtoToModel(it.results)
                }
                Observable.just(responseModel)
            }
    }

    private fun convertResultsDtoToModel(list: List<ResultsDTO>?): List<ResultsModel> {
        val result= arrayListOf<ResultsModel>()
        list?.forEach {
            val resultsModel = ResultsModel().apply {
                this.artistName = it.artistName
                this.collectionName = it.collectionName
                this.trackName = it.trackName
                this.artworkUrl100 = it.artworkUrl100
                this.trackPrice = it.trackPrice
                this.releaseDate = it.releaseDate
                this.trackTimeMillis = it.trackTimeMillis
                this.primaryGenreName = it.primaryGenreName
            }
            result.add(resultsModel)
        }
        return result
    }
}