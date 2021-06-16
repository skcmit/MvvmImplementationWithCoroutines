package com.santosh.navi.ui.pull_requests

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.santosh.navi.R
import com.santosh.navi.api.pull_requests.PullRequestsDTO
import com.santosh.navi.databinding.PullRequestLayoutBinding
import com.santosh.navi.model.GithubClosedPullRequestDomain
import com.santosh.navi.ui.user_detail.UserProfileDetailFragment
import com.santosh.navi.util.*

class PullRequestListingFragment : Fragment(), PullRequestsAdapter.PullRequestItemListener {

    private val viewModel: PullRequestViewModel by lazy { getViewModel(PullRequestViewModel::class.java) }
    private lateinit var adapter: PullRequestsAdapter
    private lateinit var binding: PullRequestLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PullRequestLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        fetchData()
    }

    private fun setupRecyclerView() {
        adapter = PullRequestsAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.onScrolledToEnd {
            fetchData()
        }
    }

    private fun setupObservers() {
        viewModel.pullRequestDomain.observe(viewLifecycleOwner, {
            when (it.state) {
                STATE.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    onSuccess(it)
                }
                STATE.ERROR -> {
                    binding.progress.visibility = View.GONE
                    onError(it.message)
                }
                STATE.INPROGRESS ->
                    binding.progress.visibility = View.VISIBLE
            }
        })
    }

    private fun onSuccess(it: Result<List<PullRequestsDTO>>) {
        viewModel.incrementPageCountOnSuccess()
        val items =
            it.data?.map { GithubClosedPullRequestDomain.createFrom(it) } ?: emptyList()
        adapter.setItems(items.toMutableList())
    }

    private fun onError(message: String?) {
        val errorMessage = if (message.isNullOrEmpty()) {
            withContext { getString(R.string.generic_error) } ?: ""
        } else message
        Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
            .setAction(withContext {
                getString(R.string.retry)
            }) { fetchData() }.show()
    }

    private fun <R> withContext(f: (context: Context) -> R?): R? {
        return view?.let {
            context?.let { context ->
                f(context)
            }
        }
    }

    private fun fetchData() {
        viewModel.fetchData()
    }

    override fun onClickedPullRequest(userId: String) {
        (activity as? FragmentNavigationHandler)?.switchFragment(UserProfileDetailFragment.getInstance(userId))
    }

    companion object {
        fun getInstance(): PullRequestListingFragment  = PullRequestListingFragment()
    }
}
