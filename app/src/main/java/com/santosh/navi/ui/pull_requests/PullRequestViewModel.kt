package com.santosh.navi.ui.pull_requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santosh.navi.api.pull_requests.PullRequestsDTO
import com.santosh.navi.network.NetworkApiProvider
import com.santosh.navi.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PullRequestViewModel : ViewModel() {

    private val githubClosedPullRequestDetails =
        MutableLiveData<Result<List<PullRequestsDTO>>>()

    val pullRequestDomain: LiveData<Result<List<PullRequestsDTO>>>
        get() = githubClosedPullRequestDetails

    private var pageNumber: Int = 1

    fun incrementPageCountOnSuccess() = pageNumber++

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            githubClosedPullRequestDetails.postValue(Result.inProgress())
            val fetchAllClosedPullRequests =
                NetworkApiProvider.getNetworkApi().fetchAllClosedPullRequests(pageNumber)
            githubClosedPullRequestDetails.postValue(fetchAllClosedPullRequests)
        }
    }
}