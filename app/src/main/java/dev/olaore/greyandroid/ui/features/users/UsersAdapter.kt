package dev.olaore.greyandroid.ui.features.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.olaore.domain.models.repositories.Repository
import dev.olaore.domain.models.users.User
import dev.olaore.greyandroid.R
import dev.olaore.greyandroid.databinding.ItemRepositoryBinding
import dev.olaore.greyandroid.databinding.ItemUserBinding
import dev.olaore.greyandroid.ui.common.adapters.addOwnerSpan
import dev.olaore.greyandroid.util.isVisible
import dev.olaore.greyandroid.util.loadImage

class UsersAdapter : ListAdapter<User, UsersAdapter.UserViewHolder>(
    UserDiffUtilCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = currentList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = currentList.size

    class UserViewHolder(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {
            binding.apply {
                userImage.loadImage(item.imageUrl)
                userUsername.text = item.username
                userFullname.text = item.fullname

                userBio.apply {
                    isVisible(item.bio.isNotEmpty())
                    text = item.bio
                }

                userDetails.apply {
                    isVisible(item.email.isNotEmpty() || item.location.isNotEmpty())
                    text = context.resources.getString(
                        R.string.user_details, item.location, item.email
                    )
                }
            }
        }

    }
}

object UserDiffUtilCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}
