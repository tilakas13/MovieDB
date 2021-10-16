package com.tilak.apps.moviedb.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp

class MovieDBApplication : Application() {

  override fun onCreate() {
    super.onCreate()
  }

}