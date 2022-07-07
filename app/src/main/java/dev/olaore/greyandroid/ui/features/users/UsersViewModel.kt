package dev.olaore.greyandroid.ui.features.users

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.olaore.domain.common.Result
import dev.olaore.greyandroid.repositories.GitHubRepository
import dev.olaore.greyandroid.ui.features.users.state.UsersState
import dev.olaore.greyandroid.ui.features.users.state.UsersState.*
import dev.olaore.greyandroid.util.asLiveData
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class UsersViewModel @Inject constructor (
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    private val _state = MutableLiveData<UsersState>()
    val state: LiveData<UsersState>
        get() = _state.asLiveData()

    fun getUsers(query: String) {
        _state.value = Loading
        viewModelScope.launch {
            when (val result = gitHubRepository.getUsers(query)) {
                is Result.Success -> {
                    result.data.forEach {
                        Log.d("UsersViewModel", it.toString())
                    }
                    _state.value = DisplayUsers(users = result.data)
                }
                is Result.NetworkError -> {
                    Log.d("UsersViewModel", "Error: Network Error")
                    _state.value = UsersState.Error("Error: Network Error")
                }
                is Result.GenericError -> {
                    Log.d("UsersViewModel", "Error: ${ result.response ?: "Something went wrong." }")
                    _state.value = UsersState.Error("Error: ${ result.response ?: "Something went wrong." }")
                }
            }
        }
    }

}
