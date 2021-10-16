package com.tilak.apps.moviedb.utils

import android.util.Log
import com.tilak.apps.moviedb.BuildConfig
import com.tilak.apps.moviedb.common.AppConstants
import javax.inject.Inject

class Logger @Inject constructor() {

    fun logInfo(message: String): Unit {
        if (BuildConfig.DEBUG) {
            Log.i(AppConstants.TAG, message)
        }
    }

    fun logVerbose(message: String): Unit {
        if (BuildConfig.DEBUG) {
            Log.v(AppConstants.TAG, message)
        }
    }
}