package com.ranosan.wavies.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ranosan.wavies.core.domain.model.Movie
import com.ranosan.wavies.core.ui.adapter.LoadingStateAdapter
import com.ranosan.wavies.core.ui.adapter.MoviePagingHorizontalAdapter
import com.ranosan.wavies.di.SearchModuleDependencies
import com.ranosan.wavies.search.databinding.FragmentSearchBinding
import com.ranosan.wavies.search.di.DaggerSearchComponent
import com.ranosan.wavies.search.viewModel.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: SearchViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        DaggerSearchComponent.builder()
            .context(requireActivity())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext,
                    SearchModuleDependencies::class.java
                )
            ).build().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView() {
        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.query.value = it
                }
                binding?.searchView?.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.query.value = it
                }
                return true
            }
        })
        val movieAdapter = MoviePagingHorizontalAdapter {movie, iv ->
            viewModel.insertMovie(movie)
            binding?.searchView?.clearFocus()
            moveToDetail(movie, iv)
        }
        binding?.rvSearch?.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = movieAdapter.withLoadStateFooter(
                LoadingStateAdapter {
                    movieAdapter.retry()
                }
            )
            setHasFixedSize(true)
        }
        isMoviesEmpty(movieAdapter.itemCount < 1)
        viewLifecycleOwner.lifecycleScope.launch {
            movieAdapter.loadStateFlow.collectLatest {
                isMoviesEmpty(false)
            }
        }
        viewModel.resultData.observe(viewLifecycleOwner) {
            movieAdapter.submitData(lifecycle, it)
        }
    }

    private fun moveToDetail(movie: Movie, imageView: ImageView) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            requireActivity(),
            Pair(imageView, "iv_movie")
        )
        val extras = ActivityNavigatorExtras(options)
        val data = SearchFragmentDirections.actionSearchFragmentToDetailActivity(movie)
        findNavController().navigate(data, extras)
    }

    private fun isMoviesEmpty(isEmpty: Boolean) {
        binding?.apply {
            tvSearchEmpty.isVisible = isEmpty
        }
    }
}