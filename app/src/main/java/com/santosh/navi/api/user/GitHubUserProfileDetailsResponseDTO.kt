package com.santosh.navi.api.user

import com.google.gson.annotations.SerializedName

data class GitHubUserProfileDetailsResponseDTO(
    @SerializedName("avatar_url") val imageUrl: String,
    val name: String,
    val bio: String,
    val location: String,
    val followers: Int,
    val following: Int
)