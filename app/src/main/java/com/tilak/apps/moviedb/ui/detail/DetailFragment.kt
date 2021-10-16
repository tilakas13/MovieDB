package com.tilak.apps.moviedb.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tilak.apps.moviedb.databinding.DetailFragmentBinding
import com.tilak.apps.moviedb.ui.base.BaseFragment
import com.tilak.apps.moviedb.utils.Logger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    @Inject
    lateinit var logger: Logger

    companion object {
        fun newInstance() = DetailFragment()
    }

    lateinit var _binding: DetailFragmentBinding

    private val viewModel: DetailViewModel by viewModels()

    val args: DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DetailFragmentBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = args.movieId

        logger.logInfo( "Movie Id : " + movieId)

        viewModel.getMovieDetails(movieId)

    }

}