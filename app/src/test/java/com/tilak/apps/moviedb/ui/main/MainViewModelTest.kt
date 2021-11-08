/*
 * *
 *  * Created by Tilaka on 08/11/21, 10:52 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 08/11/21, 10:52 PM
 *
 */

package com.tilak.apps.moviedb.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tilak.apps.moviedb.data.model.MovieModel
import com.tilak.apps.moviedb.data.repositories.MovieRepository
import com.tilak.apps.moviedb.utils.Logger
import com.tilak.apps.moviedb.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository: MovieRepository

    @Mock
    lateinit var logger: Logger

    @Mock
    private lateinit var apiUsersObserver: Observer<List<MovieModel>>

    @Mock
    lateinit var movieModel: MovieModel

    @Test
    fun getPopularMovies_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(movieModel)
                .`when`(repository)
                .getMovieDetails(1)
            val viewModel = MainViewModel(repository, logger)
            viewModel.listMovies.observeForever(apiUsersObserver)
            verify(repository).getPopularMovies(1)
            verify(apiUsersObserver).onChanged(emptyList())
            viewModel.listMovies.observeForever(apiUsersObserver)
        }
    }

}