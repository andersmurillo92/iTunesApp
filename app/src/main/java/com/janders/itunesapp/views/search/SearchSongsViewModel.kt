package com.janders.itunesapp.views.search

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.janders.itunesapp.R
import com.janders.itunesapp.data.interactor.IiTunesInteractor
import com.janders.itunesapp.data.model.ResultsModel
import com.janders.itunesapp.di.DaggeriTunesComponent
import com.janders.itunesapp.utils.RUtil
import com.janders.itunesapp.views.base.SingleLiveEvent
import java.net.ConnectException
import javax.inject.Inject

class SearchSongsViewModel: ViewModel() {

    @Inject
    lateinit var interactor: IiTunesInteractor
    var songList: MutableLiveData<List<ResultsModel>> = MutableLiveData()
    var total: Int = 0

    init {
        DaggeriTunesComponent.builder().build().inject(this)
    }

    sealed class ViewEvent {
        class ResponseSuccess: ViewEvent()
        class ResponseError(val error: String?): ViewEvent()
    }

    var singleLiveEvent: SingleLiveEvent<ViewEvent> = SingleLiveEvent()

    @SuppressLint("CheckResult")
    fun getArtist(artist: String) {
        interactor.getArtist(artist)?.subscribe({
            if(it.resultCount != 0){
                it.resultCount?.let { it1 -> total = it1 }
                songList.value = it?.results
                singleLiveEvent.value = ViewEvent.ResponseSuccess()
            } else {
                singleLiveEvent.value = ViewEvent.ResponseError(RUtil.rString(R.string.message_no_result_were_found))
            }
        },{
            if(it is ConnectException)
                singleLiveEvent.value = ViewEvent.ResponseError(RUtil.rString(R.string.message_not_connected))
            else
                singleLiveEvent.value = ViewEvent.ResponseError(RUtil.rString(R.string.message_error_ocurred))
        })
    }
}