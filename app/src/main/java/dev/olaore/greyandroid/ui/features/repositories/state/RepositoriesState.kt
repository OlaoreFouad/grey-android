package dev.olaore.greyandroid.ui.features.repositories.state

import dev.olaore.domain.models.repositories.Repository

sealed class RepositoriesState {
    object Loading: RepositoriesState()

    data class DisplayRepositories(
        val repositories: List<Repository>
    ): RepositoriesState()

    data class Error(
        val message: String
    ): RepositoriesState()
}
