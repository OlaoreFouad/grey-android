package dev.olaore.greyandroid.ui.features.repositories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.olaore.greyandroid.R
import dev.olaore.greyandroid.databinding.FragmentRepositoriesBinding
import dev.olaore.greyandroid.ui.features.repositories.state.RepositoriesState.*
import dev.olaore.greyandroid.util.isVisible
import dev.olaore.greyandroid.util.showToast

@AndroidEntryPoint
class RepositoriesFragment : Fragment() {

    private var _binding: FragmentRepositoriesBinding? = null
    val binding: FragmentRepositoriesBinding
        get() = _binding!!

    private val viewModel: RepositoriesViewModel by viewModels()

    private lateinit var repositoriesAdapter: RepositoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentRepositoriesBinding.inflate(
            inflater, container, false
        ).also { _binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        observeViewModelEvents()
    }

    private fun setupUi() {
        binding.searchRepositories.setOnSearchClickListener { query ->
            viewModel.getRepositories(query)
        }

        repositoriesAdapter = RepositoriesAdapter(requireContext())
        binding.repositoriesList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = repositoriesAdapter
        }
    }

    private fun observeViewModelEvents() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.loadingProgress.isVisible(state is Loading)
            binding.emptyState.isVisible(state is DisplayRepositories && state.repositories.isEmpty())
            when (state) {
                is DisplayRepositories -> {
                    binding.repositoriesList.isVisible(state.repositories.isNotEmpty())
                    repositoriesAdapter.submitList(state.repositories)

                    if (state.repositories.isEmpty()) {
                        binding.emptyState.setEmptyText(
                            requireContext().resources.getString(R.string.search_github_empty)
                        )
                    }

                }
                is Error -> {
                    showToast(state.message)
                }
                else -> {
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
