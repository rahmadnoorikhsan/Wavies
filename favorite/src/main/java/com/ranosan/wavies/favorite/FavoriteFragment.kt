package com.ranosan.wavies.favorite

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ranosan.wavies.core.ui.adapter.MovieGridAdapter
import com.ranosan.wavies.di.FavoriteModuleDependencies
import com.ranosan.wavies.favorite.databinding.FragmentFavoriteBinding
import com.ranosan.wavies.favorite.di.DaggerFavoriteComponent
import com.ranosan.wavies.favorite.viewModel.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    override fun onAttach(context: Context) {
        DaggerFavoriteComponent.builder()
            .context(requireActivity())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            ).build().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView() {
        val adapter = MovieGridAdapter { movie, iv ->
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),
                Pair(iv, "iv_movie")
            )
            val extras = ActivityNavigatorExtras(options)
            val data = FavoriteFragmentDirections.actionFavoriteFragmentToDetailActivity(movie)
            findNavController().navigate(data, extras)
        }
        val gridCount = if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 3 else 5
        viewModel.favoriteMovies().observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding?.rvFavorite?.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(context, gridCount)
                this.adapter = adapter
            }
            isMoviesEmpty(it.isEmpty())
        }
    }

    private fun isMoviesEmpty(isEmpty: Boolean) {
        binding?.apply {
            tvNoData.isVisible = isEmpty
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}