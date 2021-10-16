package com.tilak.apps.moviedb.ui

import android.os.Bundle
import com.tilak.apps.moviedb.R
import com.tilak.apps.moviedb.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}