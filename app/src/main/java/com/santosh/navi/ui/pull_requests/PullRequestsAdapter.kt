package com.santosh.navi.ui.pull_requests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.santosh.navi.databinding.LayoutPullRequestItemBinding
import com.santosh.navi.model.GithubClosedPullRequestDomain
import com.santosh.navi.util.TimeManager
import com.santosh.navi.util.setOptionalText

class PullRequestsAdapter(private val listener: PullRequestItemListener) : RecyclerView.Adapter<PullRequestsAdapter.PullRequestViewHolder>() {

    interface PullRequestItemListener {
        fun onClickedPullRequest(userId: String)
    }
    private val items = ArrayList<GithubClosedPullRequestDomain>()

    fun setItems(newItems: MutableList<GithubClosedPullRequestDomain>) {
        this.items.addAll(newItems)
        val positionStart = this.items.count() - newItems.count()
        notifyItemRangeChanged(positionStart, newItems.count())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val binding: LayoutPullRequestItemBinding = LayoutPullRequestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PullRequestViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) = holder.bind(items[position])

    inner class PullRequestViewHolder(private val itemBinding: LayoutPullRequestItemBinding) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var githubClosedPullRequestDomain: GithubClosedPullRequestDomain

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: GithubClosedPullRequestDomain) {
            this.githubClosedPullRequestDomain = item
            itemBinding.closedDate.text = TimeManager.formatTime(item.closedAt)
            itemBinding.createdDate.text = TimeManager.formatTime(item.createdAt)
            itemBinding.title.setOptionalText(item.title, itemBinding.titleLabel)
            Glide.with(itemBinding.root)
                .load(item.userImage)
                .transform(CircleCrop())
                .into(itemBinding.userImage)
        }

        override fun onClick(v: View?) {
            listener.onClickedPullRequest(githubClosedPullRequestDomain.userId)
        }
    }
}


