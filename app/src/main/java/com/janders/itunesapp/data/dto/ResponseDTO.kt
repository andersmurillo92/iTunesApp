package com.janders.itunesapp.data.dto

import com.google.gson.annotations.SerializedName

class ResponseDTO {
    @SerializedName("resultCount")
    var resultCount: Int? = 0
    @SerializedName("results")
    var results: List<ResultsDTO>? = null
}