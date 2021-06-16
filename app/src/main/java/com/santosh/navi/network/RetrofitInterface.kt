package com.santosh.navi.network

import com.santosh.navi.api.pull_requests.PullRequestsDTO
import com.santosh.navi.api.user.GitHubUserProfileDetailsResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {
    companion object {
        const val GET_REPO_DETAILS = "/repos/jina-ai/jina"
        const val PARAM_PULLS = "/pulls"
        const val PARAM_STATE = "state"
        const val PARAM_PAGE = "page"
        const val PARAM_PER_PAGE = "per_page"
        const val PARAM_USERS = "users"
        const val PARAM_USER_ID = "users"
    }

    @GET("$GET_REPO_DETAILS$PARAM_PULLS")
    suspend fun getClosedPullRequestsByOwnerAndRepo(
        @Query(PARAM_PAGE) page: String,
        @Query(PARAM_STATE) state: String,
        @Query(PARAM_PER_PAGE) perPage: Int
    ): Response<List<PullRequestsDTO>>

    @GET("$PARAM_USERS/{$PARAM_USER_ID}")
    suspend fun getUserDetails(
        @Path(value = PARAM_USER_ID) userId: String
    ): Response<GitHubUserProfileDetailsResponseDTO>
}