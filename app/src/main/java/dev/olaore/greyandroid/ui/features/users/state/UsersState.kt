package dev.olaore.greyandroid.ui.features.users.state

import dev.olaore.domain.models.users.User

sealed class UsersState {
    object Loading: UsersState()

    data class DisplayUsers(
        val users: List<User>
    ): UsersState()

    data class Error(
        val message: String
    ): UsersState()
}
