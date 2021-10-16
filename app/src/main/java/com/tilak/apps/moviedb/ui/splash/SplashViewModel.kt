package com.tilak.apps.moviedb.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tilak.apps.moviedb.common.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel   @Inject constructor() : ViewModel() {

    val liveData: LiveData<SplashState>
        get() = mutableLiveData
    private val mutableLiveData = MutableLiveData<SplashState>()

    init {
        GlobalScope.launch {
            delay(AppConstants.DURATION_SPLASH)
            mutableLiveData.postValue(SplashState.splashScreen)
        }
    }

    sealed class SplashState {
        object splashScreen : SplashState()
    }
}