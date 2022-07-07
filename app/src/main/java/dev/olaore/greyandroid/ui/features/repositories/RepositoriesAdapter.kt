package dev.olaore.greyandroid.ui.features.repositories

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.olaore.domain.models.repositories.Repository
import dev.olaore.greyandroid.databinding.ItemRepositoryBinding
import dev.olaore.greyandroid.ui.common.adapters.addOwnerSpan
import dev.olaore.greyandroid.util.ChipsHelper
import dev.olaore.greyandroid.util.isVisible
import dev.olaore.greyandroid.util.loadImage

class RepositoriesAdapter(
    private val context: Context
) : ListAdapter<Repository, RepositoriesAdapter.RepositoryViewHolder>(
    RepositoryDiffUtilCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            ItemRepositoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repo = currentList[position]
        holder.bind(repo)
    }

    override fun getItemCount(): Int = currentList.size

    inner class RepositoryViewHolder(
        private val binding: ItemRepositoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var chipsHelper: ChipsHelper = ChipsHelper(context, binding.topicsGroup)

        fun bind(item: Repository) {
            binding.apply {
                repositoryImage.loadImage(item.imageUrl)
                repositoryFullname.text = item.fullname
                repositoryFullname.addOwnerSpan()
                repositoryStars.text = item.stars.toString()
                repositoryLang.text = item.lang
                repositoryDescription.text = item.description

                item.topics.forEach { topic ->
                    val chip = chipsHelper.createChip(topic)
                    topicsGroup.addView(chip)
                }
                topicsGroup.isVisible(item.topics.isNotEmpty())

            }
        }

    }
}

object RepositoryDiffUtilCallback : DiffUtil.ItemCallback<Repository>() {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem == newItem
    }
}
