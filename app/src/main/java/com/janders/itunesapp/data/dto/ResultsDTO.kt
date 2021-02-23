package com.janders.itunesapp.data.dto

import com.google.gson.annotations.SerializedName

class ResultsDTO {
    @SerializedName("artistName")
    var artistName: String? = null
    @SerializedName("collectionName")
    var collectionName: String? = null
    @SerializedName("trackName")
    var trackName: String? = null
    @SerializedName("artworkUrl100")
    var artworkUrl100: String? = null
    @SerializedName("trackPrice")
    var trackPrice: Double? = 0.0
    @SerializedName("releaseDate")
    var releaseDate: String? = null
    @SerializedName("trackTimeMillis")
    var trackTimeMillis: Int? = 0
    @SerializedName("primaryGenreName")
    var primaryGenreName: String? = null
}