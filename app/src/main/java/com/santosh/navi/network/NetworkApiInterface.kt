package com.santosh.navi.network

import com.santosh.navi.api.pull_requests.PullRequestsDTO
import com.santosh.navi.api.user.GitHubUserProfileDetailsResponseDTO
import com.santosh.navi.util.Result

interface NetworkApiInterface {
    suspend fun fetchAllClosedPullRequests(page: Int): Result<List<PullRequestsDTO>>
    suspend fun getGitHubUserNameByUserId(userId: String): Result<GitHubUserProfileDetailsResponseDTO>
}