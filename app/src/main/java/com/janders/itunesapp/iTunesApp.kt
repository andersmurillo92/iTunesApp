package com.janders.itunesapp

import android.app.Application
import android.content.Context

class iTunesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: iTunesApp

        fun getInstance(): iTunesApp {
            return instance
        }

        fun getAppContext(): Context {
            return instance.applicationContext
        }
    }
}