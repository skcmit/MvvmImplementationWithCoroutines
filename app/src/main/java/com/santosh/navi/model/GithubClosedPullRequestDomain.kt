package com.santosh.navi.model

import com.santosh.navi.api.pull_requests.PullRequestsDTO

data class GithubClosedPullRequestDomain(
    val title: String,
    val userImage: String,
    val createdAt: String,
    val closedAt: String,
    val userId: String
) {
    companion object {
        fun createFrom(pullRequestsDTO: PullRequestsDTO): GithubClosedPullRequestDomain {
            return GithubClosedPullRequestDomain(
                pullRequestsDTO.title,
                pullRequestsDTO.user.profileImage,
                pullRequestsDTO.createdAt,
                pullRequestsDTO.updatedAt,
                pullRequestsDTO.user.userId
            )
        }
    }
}