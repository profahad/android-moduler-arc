package com.faaadi.apps.kotlindsl

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KotlinDslApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: KotlinDslApp? = null
    }
}