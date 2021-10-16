package com.tilak.apps.moviedb.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.tilak.apps.moviedb.databinding.MainFragmentBinding
import com.tilak.apps.moviedb.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : BaseFragment() {

    private val TAG = "MainFragment"

    private lateinit var _binding: MainFragmentBinding


    @Inject
    lateinit var adapter: MovieAdapter

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.rvMovieList.layoutManager = GridLayoutManager(activity, 2)
        _binding.rvMovieList.adapter = adapter

        viewModel.getPopularMovies(1)

        viewModel.listMovies.observe(viewLifecycleOwner, Observer { it ->
            Log.i(TAG, "Fetched list movies : " + it.let {
                it.size
            })
            it.let {
                Log.i(TAG, "List of movies : " + it.size)
                adapter.setListMovies(it)
            }
        })


    }

}