package com.tilak.apps.moviedb.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
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

    private var currentPage = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lytManager = GridLayoutManager(activity, 2);
        binding.tbListHeader.title = getString(R.string.title_popular_movies)
        binding.rvMovieList.layoutManager = lytManager
        binding.rvMovieList.adapter = adapter

        viewModel.getPopularMovies(currentPage)
        viewModel.listMovies.observe(viewLifecycleOwner, { it ->
            it.let {
                logger.logInfo("List of movies : " + it.size)
                adapter.setListMovies(it)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}