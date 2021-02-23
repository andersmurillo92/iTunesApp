package com.janders.itunesapp.data.repository

import com.janders.itunesapp.data.dto.ResponseDTO
import com.janders.itunesapp.network.Adapter
import io.reactivex.Observable

interface IiTunesRepository {
    fun getArtist(artist: String): Observable<ResponseDTO>?
}

class iTunesRepository: IiTunesRepository {
    override fun getArtist(artist: String): Observable<ResponseDTO>? {
        return Adapter().getService()?.getArtist(artist, "song", "200")
    }
}