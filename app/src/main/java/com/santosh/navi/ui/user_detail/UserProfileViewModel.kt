package com.santosh.navi.ui.user_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santosh.navi.api.user.GitHubUserProfileDetailsResponseDTO
import com.santosh.navi.network.NetworkApiProvider
import com.santosh.navi.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserProfileViewModel : ViewModel() {

    private val githubUserProfileDetails =
        MutableLiveData<Result<GitHubUserProfileDetailsResponseDTO>>()

    val getUserProfileDetail: LiveData<Result<GitHubUserProfileDetailsResponseDTO>>
        get() = githubUserProfileDetails

    fun fetchData(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            githubUserProfileDetails.postValue(Result.inProgress())
            val fetchAllClosedPullRequests =
                NetworkApiProvider.getNetworkApi().getGitHubUserNameByUserId(userId)
            githubUserProfileDetails.postValue(fetchAllClosedPullRequests)
        }
    }
}