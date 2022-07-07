package dev.olaore.greyandroid.ui.features.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.olaore.greyandroid.R
import dev.olaore.greyandroid.databinding.FragmentRepositoriesBinding
import dev.olaore.greyandroid.databinding.FragmentUsersBinding
import dev.olaore.greyandroid.ui.features.repositories.RepositoriesAdapter
import dev.olaore.greyandroid.ui.features.repositories.RepositoriesViewModel

@AndroidEntryPoint
class UsersFragment : Fragment() {
    private var _binding: FragmentUsersBinding? = null
    val binding: FragmentUsersBinding
        get() = _binding!!

    private val viewModel: UsersViewModel by viewModels()

//    private val repositoriesAdapter = RepositoriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentUsersBinding.inflate(
            inflater, container, false
        ).also { _binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUsers("fouad")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
