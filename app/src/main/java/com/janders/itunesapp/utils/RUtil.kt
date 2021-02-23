package com.janders.itunesapp.utils

import com.janders.itunesapp.iTunesApp

class RUtil {
    companion object {
        fun rString(resId: Int): String {
            return iTunesApp.getInstance().getString(resId)
        }
    }
}