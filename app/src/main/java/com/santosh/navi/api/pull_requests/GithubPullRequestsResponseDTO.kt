package com.santosh.navi.api.pull_requests

import com.google.gson.annotations.SerializedName

data class PullRequestsDTO(
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    val title: String,
    val user: UserDetails
)

data class UserDetails(
    @SerializedName("avatar_url") val profileImage: String,
    @SerializedName("login") val userId: String
)