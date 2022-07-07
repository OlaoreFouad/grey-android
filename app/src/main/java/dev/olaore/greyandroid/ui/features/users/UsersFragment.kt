package dev.olaore.greyandroid.ui.features.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.olaore.greyandroid.R
import dev.olaore.greyandroid.databinding.FragmentUsersBinding
import dev.olaore.greyandroid.ui.features.users.state.UsersState
import dev.olaore.greyandroid.util.isVisible
import dev.olaore.greyandroid.util.showToast

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    val binding: FragmentUsersBinding
        get() = _binding!!

    private val viewModel: UsersViewModel by viewModels()

    private val usersAdapter = UsersAdapter(onUserClicked = { url ->
        navigateToUserScreen(url)
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUsersBinding.inflate(
            inflater, container, false
        ).also { _binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        observeViewModelEvents()
    }

    private fun setupUi() {
        binding.searchUsers.setOnSearchClickListener { query ->
            viewModel.getUsers(query)
        }

        binding.usersList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usersAdapter
        }
    }

    private fun observeViewModelEvents() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.loadingProgress.isVisible(state is UsersState.Loading)
            binding.emptyState.isVisible(state is UsersState.DisplayUsers && state.users.isEmpty())
            when (state) {
                is UsersState.DisplayUsers -> {
                    binding.usersList.isVisible(state.users.isNotEmpty())
                    usersAdapter.submitList(state.users)

                    if (state.users.isEmpty()) {
                        binding.emptyState.setEmptyText(
                            requireContext().resources.getString(R.string.search_users_empty)
                        )
                    }

                }
                is UsersState.Error -> {
                    showToast(state.message)
                }
                else -> {
                }
            }
        }
    }

    private fun navigateToUserScreen(userUrl: String) {
        findNavController().navigate(
            UsersFragmentDirections.actionUsersFragmentToUserFragment(userUrl)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
