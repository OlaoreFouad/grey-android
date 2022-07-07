package dev.olaore.greyandroid.ui.features.user

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.olaore.domain.models.repositories.RepositoryDetail
import dev.olaore.greyandroid.R
import dev.olaore.greyandroid.databinding.ItemUserRepositoryBinding
import dev.olaore.greyandroid.ui.common.adapters.addOwnerSpan
import dev.olaore.greyandroid.util.getTimeDifference

class UserRepositoriesAdapter(
    private val context: Context
)
    : ListAdapter<RepositoryDetail, UserRepositoriesAdapter.UserRepositoryViewHolder>(
    RepositoryDiffUtilCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepositoryViewHolder {
        return UserRepositoryViewHolder(
            ItemUserRepositoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserRepositoryViewHolder, position: Int) {
        val userRepository = currentList[position]
        holder.bind(userRepository)
    }

    override fun getItemCount(): Int = currentList.size

    inner class UserRepositoryViewHolder(
        private val binding: ItemUserRepositoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RepositoryDetail) {
            binding.apply {

                repositoryFullname.text = item.fullname
                repositoryFullname.addOwnerSpan()
                repositoryStars.text = item.stars.toString()
                repositoryLang.text = item.language
                repositoryDescription.text = item.description

                repositoryStatus.text = if (item.private) "Private" else "Public"

                val dateWithoutTimestamp = item.lastUpdatedAt.split("T")[0]
                Log.d("UserRepositoriesAdapter", dateWithoutTimestamp)
                repositoryLastUpdated.text = context.getString(
                    R.string.last_updated_text, dateWithoutTimestamp.getTimeDifference()
                )

            }
        }

    }
}

object RepositoryDiffUtilCallback : DiffUtil.ItemCallback<RepositoryDetail>() {
    override fun areItemsTheSame(oldItem: RepositoryDetail, newItem: RepositoryDetail): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RepositoryDetail, newItem: RepositoryDetail): Boolean {
        return oldItem == newItem
    }
}
