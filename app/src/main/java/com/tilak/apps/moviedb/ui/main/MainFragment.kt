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
    lateinit var movieListAdapter: MovieAdapter

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
        val lytManager = GridLayoutManager(this.context, 2)
        binding.tbListHeader.title = getString(R.string.title_popular_movies)

        binding.rvMovieList.apply {
            layoutManager = lytManager
            adapter = movieListAdapter
        }

        binding.rvMovieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (lytManager.findLastCompletelyVisibleItemPosition() >= movieListAdapter.itemCount - 2 && !viewModel.isLoading.value!!) {
                    viewModel.getPopularMovies()
                }

            }
        })

        viewModel.getPopularMovies()
        viewModel.listMovies.observe(viewLifecycleOwner, { it ->
            it.let {
                movieListAdapter.setListMovies(it)
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, { it ->
            it.let {
                binding.pbLoader.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }

    override fun onDestroy() {
        binding.rvMovieList.adapter = null
        _binding = null
        super.onDestroy()
    }

}