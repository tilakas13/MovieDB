/*
 * *
 *  * Created by Tilaka on 10/18/21, 10:52 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 10/18/21, 10:12 AM
 *
 */

package com.tilak.apps.moviedb.utils

import android.util.Log
import com.tilak.apps.moviedb.BuildConfig
import com.tilak.apps.moviedb.common.AppConstants
import javax.inject.Inject

class Logger @Inject constructor() {

    fun logInfo(message: String) {
        if (BuildConfig.DEBUG) {
            Log.i(AppConstants.TAG, message)
        }
    }

    fun logVerbose(message: String) {
        if (BuildConfig.DEBUG) {
            Log.v(AppConstants.TAG, message)
        }
    }
}