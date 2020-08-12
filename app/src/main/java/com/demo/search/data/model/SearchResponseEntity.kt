package com.demo.search.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponseEntity(
    @Json(name = "total_count") val total_count: Int?,
    @Json(name = "incomplete_results") val incompleteResults: Boolean?,
    @Json(name = "items") val items: List<SearchResultData>?
)

@JsonClass(generateAdapter = true)
data class SearchResultData(
    @Json(name = "login") val login: String?,
    @Json(name = "id") val id: String?,
    @Json(name = "node_id") val nodeId: String?,
    @Json(name = "avatar_url") val avatar_url: String?,
    @Json(name = "url") val url: String?,
    @Json(name = "html_url") val htmlUrl: String?,
    @Json(name = "followers_url") val followersUrl: String?,
    @Json(name = "following_url") val followingUrl: String?,
    @Json(name = "gists_url") val gistsUrl: String?,
    @Json(name = "starred_url") val starredUrl: String?,
    @Json(name = "subscriptions_url") val subscriptionsUrl: String?,
    @Json(name = "organizations_url") val organizationsUrl: String?,
    @Json(name = "repos_url") val reposUrl: String?,
    @Json(name = "events_url") val eventsUrl: String?,
    @Json(name = "received_events_url") val received_events_url: String?,
    @Json(name = "site_admin") val siteAdmin: Boolean?,
    @Json(name = "score") val score: Float?,
    @Json(name = "type") val type: String?
)