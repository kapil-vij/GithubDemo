package com.demo.search.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReposResponseEntity(
    @Json(name = "id") val id: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "full_name") val full_name: String?,
    @Json(name = "html_url") val html_url: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "language") val language: String?
)