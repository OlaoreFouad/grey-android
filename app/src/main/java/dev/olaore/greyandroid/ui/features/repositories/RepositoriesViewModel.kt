package dev.olaore.greyandroid.ui.features.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.olaore.domain.common.Result
import dev.olaore.greyandroid.repositories.GitHubRepository
import dev.olaore.greyandroid.ui.features.repositories.state.RepositoriesState
import dev.olaore.greyandroid.ui.features.repositories.state.RepositoriesState.*
import dev.olaore.greyandroid.util.asLiveData
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RepositoriesViewModel @Inject constructor (
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    private val _state = MutableLiveData<RepositoriesState>()
    val state: LiveData<RepositoriesState>
        get() = _state.asLiveData()

    fun getRepositories(query: String) {
        _state.value = Loading
        viewModelScope.launch {
            when (val result = gitHubRepository.getRepositories(query)) {
                is Result.Success -> {
                    _state.value = DisplayRepositories(repositories = result.data)
                }
                is Result.NetworkError -> {
                    _state.value = Error("Error: Network Error")
                }
                is Result.GenericError -> {
                    _state.value = Error("Error: ${ result.response ?: "Something went wrong." }")
                }
            }
        }
    }

}
