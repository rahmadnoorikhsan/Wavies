package com.ranosan.wavies.ui.main.movies

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ranosan.wavies.core.ui.adapter.LoadingStateAdapter
import com.ranosan.wavies.core.ui.adapter.MoviePagingAdapter
import com.ranosan.wavies.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding
    private val viewModel: MoviesViewModel by viewModels()
    private val navArgs: MoviesFragmentArgs by navArgs()
    private lateinit var movieAdapter: MoviePagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        setUpView()
    }

    private fun setUpToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(binding?.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = navArgs.title
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {}

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                requireActivity().onBackPressedDispatcher.onBackPressed()
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.CREATED)
    }

    private fun setUpView() {
        movieAdapter = MoviePagingAdapter { movie, imageView ->
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),
                Pair(imageView, "iv_movie")
            )
            val extras = ActivityNavigatorExtras(options)
            val data = MoviesFragmentDirections.actionMoviesFragmentToDetailActivity(movie)
            findNavController().navigate(data, extras)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            movieAdapter.loadStateFlow.collectLatest {
                binding?.progressBar?.isVisible = it.refresh is LoadState.Loading
            }
        }
        val loadStateAdapter = LoadingStateAdapter { movieAdapter.retry() }
        val gridCount = if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 3 else 5
        binding?.rvMovies?.apply {
            layoutManager = GridLayoutManager(context, gridCount)
            adapter = movieAdapter.withLoadStateFooter(loadStateAdapter)
            setHasFixedSize(true)
        }
        viewModel.movieType.value = navArgs.movieType
        viewModel.getMoviesPaging.observe(viewLifecycleOwner) {
            movieAdapter.submitData(lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}