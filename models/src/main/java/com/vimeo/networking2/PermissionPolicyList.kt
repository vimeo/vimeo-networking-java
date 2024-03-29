package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents a list of [PermissionPolicy].
 *
 * @param total the number of policies in the list returned in [data]
 * @param data the actual list of [PermissionPolicies][PermissionPolicy]
 */
@JsonClass(generateAdapter = true)
data class PermissionPolicyList(
    @Json(name = "total")
    val total: Int? = null,

    @Json(name = "data")
    val data: List<PermissionPolicy>? = null
)
