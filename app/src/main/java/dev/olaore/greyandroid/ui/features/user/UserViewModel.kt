package dev.olaore.greyandroid.ui.features.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.olaore.domain.common.Result
import dev.olaore.greyandroid.repositories.GitHubRepository
import dev.olaore.greyandroid.ui.features.user.state.UserState
import dev.olaore.greyandroid.ui.features.user.state.UserState.*
import dev.olaore.greyandroid.util.asLiveData
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class UserViewModel @Inject constructor(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    private val _state = MutableLiveData<UserState>()
    val state: LiveData<UserState>
        get() = _state.asLiveData()

    fun getUser(url: String) {
        _state.value = Loading
        viewModelScope.launch {
            when (val result = gitHubRepository.getUser(url)) {
                is Result.Success -> {
                    Log.d("UserViewModel", "User: ${ result.data }")
                    _state.value = ShowUser(user = result.data)
                }
                is Result.NetworkError -> {
                    _state.value = Error("Error: Network Error")
                }
                is Result.GenericError -> {
                    _state.value =
                        Error("Error: ${result.response ?: "Something went wrong."}")
                }
            }
        }
    }

}
