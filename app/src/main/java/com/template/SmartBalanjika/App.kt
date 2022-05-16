package com.template.SmartBalanjika

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.template.SmartBalanjika.utils.isNight
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){


    override fun onCreate() {
        super.onCreate()

        // Get UI mode and set
        val mode = if (isNight()) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        AppCompatDelegate.setDefaultNightMode(mode)
    }


}