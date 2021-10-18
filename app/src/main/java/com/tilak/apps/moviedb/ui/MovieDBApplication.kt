/*
 * *
 *  * Created by Tilaka on 10/18/21, 10:52 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 10/18/21, 10:30 AM
 *
 */

package com.tilak.apps.moviedb.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp

class MovieDBApplication : Application() {

  override fun onCreate() {
    super.onCreate()
  }

}