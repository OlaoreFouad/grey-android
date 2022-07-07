package dev.olaore.greyandroid.ui.features.user.state

import dev.olaore.domain.models.user.UserDetail

sealed class UserState {

    object Loading: UserState()

    data class ShowUser(
        val user: UserDetail
    ): UserState()

    data class Error(
        val message: String
    ): UserState()

}
