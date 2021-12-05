/*
 * *
 *  * Created by Tilaka on 10/18/21, 10:52 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 10/18/21, 10:12 AM
 *
 */

package com.tilak.apps.moviedb.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tilak.apps.moviedb.R
import com.tilak.apps.moviedb.databinding.MainFragmentBinding
import com.tilak.apps.moviedb.ui.base.BaseFragment
import com.tilak.apps.moviedb.utils.Logger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : BaseFragment() {

    @Inject
    lateinit var adapter: MovieAdapter

    @Inject
    lateinit var logger: Logger

    private val viewModel: MainViewModel by viewModels()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lytManager = GridLayoutManager(activity, 2)
        binding.tbListHeader.title = getString(R.string.title_popular_movies)
        binding.rvMovieList.layoutManager = lytManager
        binding.rvMovieList.adapter = adapter

        binding.rvMovieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (lytManager.findLastCompletelyVisibleItemPosition() >= adapter.itemCount - 2
                    && binding.pbLoader.visibility == View.GONE
                ) {
                    viewModel.getPopularMovies()
                }

            }
        })
        observeStateChange()
        viewModel.getPopularMovies()

    }

    private fun observeStateChange() {
        viewModel.screenState.observe(viewLifecycleOwner) {
            when (it) {
                is MainViewModel.MovieListingState.Loading -> {
                    binding.pbLoader.visibility = View.VISIBLE
                    displayProgress(View.VISIBLE)
                }

                is MainViewModel.MovieListingState.Error -> {
                    displayProgress(View.GONE)
                    Toast.makeText(this.context, it.message, Toast.LENGTH_LONG).show()
                }
                is MainViewModel.MovieListingState.Success -> {
                    displayProgress(View.GONE)
                    adapter.setListMovies(it.data)
                }
            }
        }
    }

    private fun displayProgress(visibility: Int) {
        binding.pbLoader.visibility = visibility
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}