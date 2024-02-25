package org.example.userrepodetails.entity.git_api_json.repos

import com.fasterxml.jackson.annotation.JsonProperty

data class Owner(
    @JsonProperty("login") val login: String = "null",
    @JsonProperty("id") val id: Int = -1,
    @JsonProperty("node_id") val nodeId: String = "null",
    @JsonProperty("avatar_url") val avatarUrl: String = "null",
    @JsonProperty("gravatar_id") val gravatarId: String = "null",
    @JsonProperty("url") val url: String = "null",
    @JsonProperty("html_url") val htmlUrl: String = "null",
    @JsonProperty("followers_url") val followersUrl: String = "null",
    @JsonProperty("following_url") val followingUrl: String = "null",
    @JsonProperty("gists_url") val gistsUrl: String = "null",
    @JsonProperty("starred_url") val starredUrl: String = "null",
    @JsonProperty("subscriptions_url") val subscriptionsUrl: String = "null",
    @JsonProperty("organizations_url") val organizationsUrl: String = "null",
    @JsonProperty("repos_url") val reposUrl: String = "null",
    @JsonProperty("events_url") val eventsUrl: String = "null",
    @JsonProperty("received_events_url") val receivedEventsUrl: String = "null",
    @JsonProperty("type") val type: String = "null",
    @JsonProperty("site_admin") val siteAdmin: Boolean = false
)