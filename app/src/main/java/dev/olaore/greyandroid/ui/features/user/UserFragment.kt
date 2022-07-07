package dev.olaore.greyandroid.ui.features.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.olaore.domain.models.user.UserDetail
import dev.olaore.greyandroid.R
import dev.olaore.greyandroid.databinding.FragmentUserBinding
import dev.olaore.greyandroid.ui.features.user.state.UserState
import dev.olaore.greyandroid.ui.features.user.state.UserState.*
import dev.olaore.greyandroid.util.isVisible
import dev.olaore.greyandroid.util.loadImage
import dev.olaore.greyandroid.util.showToast

@AndroidEntryPoint
class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    val binding: FragmentUserBinding
        get() = _binding!!

    private val viewModel: UserViewModel by viewModels()
    private val args: UserFragmentArgs by navArgs()

    private lateinit var userRepositoriesAdapter: UserRepositoriesAdapter

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        navController = findNavController()
        return FragmentUserBinding.inflate(
            inflater, container, false
        ).also { _binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupUi()
        observeViewModelEvents()

        val url = args.userUrl
        viewModel.getUser(url)
    }

    private fun setupUi() {
        binding.backButton.setOnClickListener { navController.popBackStack() }
        userRepositoriesAdapter = UserRepositoriesAdapter(requireContext())
        binding.userRepositoriesList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userRepositoriesAdapter
        }
    }

    private fun observeViewModelEvents() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.userInfoContainer.isVisible(state is ShowUser)
            binding.loadingProgress.isVisible(state is Loading)

            when (state) {
                is ShowUser -> {
                    handleUser(state.user)
                }
                is Error -> {
                    showToast(state.message)
                }
                else -> {}
            }

        }
    }

    private fun handleUser(user: UserDetail) {
        binding.apply {

            userUsername.text = user.username
            userFullname.text = user.fullname
            userImage.loadImage(user.imageUrl)

            userBio.text = user.bio

            userLocation.apply {
                isVisible(user.location.isNotEmpty())
                text = user.location
            }

            userWebsite.apply {
                isVisible(user.blog.isNotEmpty())
                text = user.blog
            }

            userFollowingFollowers.text = requireContext().resources.getString(
                R.string.user_following_followers, user.following, user.followers
            )

            repositoriesContainer.isVisible(user.repositories.isNotEmpty())
            userRepositoriesCount.text = user.repositories.size.toString()
            emptyState.isVisible(user.repositories.isEmpty())

            userRepositoriesAdapter.submitList(user.repositories)
        }
    }

}
