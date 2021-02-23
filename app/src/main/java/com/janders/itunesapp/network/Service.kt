package com.janders.itunesapp.network

import com.janders.itunesapp.data.dto.ResponseDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("search")
    fun getArtist(@Query("term") artist: String,
                  @Query("entity") entity: String,
                  @Query("limit") limit: String): Observable<ResponseDTO>
}