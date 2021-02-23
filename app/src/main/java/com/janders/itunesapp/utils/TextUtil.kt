package com.janders.itunesapp.utils

import android.util.Log

class TextUtil {

    companion object {
        fun convertMillisToMinSec(millis: Int?): String {
            var result = ""

            try {

                var min = millis?.div(60000)
                var sec = millis?.rem(60)
                result = "${min.toString()}:${sec.toString().padStart(2,'0')}"

            } catch (e: Exception){
                Log.i("ConvertMillisToMinSec","Inconvertible quantity")
            }

            return result
        }
    }
}