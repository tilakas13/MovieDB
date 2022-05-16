/*
 * *
 *  * Created by Tilaka on 5/13/22, 11:43 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 5/13/22, 9:14 PM
 *
 */

package com.tilak.apps.moviedb.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tilak.apps.moviedb.common.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    val splashScreenStus: LiveData<SplashState>
        get() = mutableLiveData
    private val mutableLiveData = MutableLiveData<SplashState>()

    init {
        viewModelScope.launch {
            delay(AppConstants.DURATION_SPLASH)
            mutableLiveData.postValue(SplashState.splashScreen)
        }
    }

    sealed class SplashState {
        object splashScreen : SplashState()
    }
}