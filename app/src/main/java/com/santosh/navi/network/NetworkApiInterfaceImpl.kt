package com.santosh.navi.network

import com.santosh.navi.api.pull_requests.PullRequestsDTO
import com.santosh.navi.api.user.GitHubUserProfileDetailsResponseDTO
import com.santosh.navi.util.Result

class NetworkApiInterfaceImpl(private val retrofitInterface: RetrofitInterface) : NetworkApiInterface, AbstractNetworkAPI() {

    companion object {
        private const val CLOSED_STATE = "closed"
        private const val PER_PAGE = 25
    }

    override suspend fun fetchAllClosedPullRequests(page: Int): Result<List<PullRequestsDTO>> =
        getResult {
            retrofitInterface.
            getClosedPullRequestsByOwnerAndRepo(page.toString(), CLOSED_STATE, PER_PAGE)
        }

    override suspend fun getGitHubUserNameByUserId(userId: String): Result<GitHubUserProfileDetailsResponseDTO> =
        getResult {
            retrofitInterface.getUserDetails(userId)
        }
}