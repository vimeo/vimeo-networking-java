package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

/**
 * A permission policy which represents a collection or permutation of certain [PermissionActions].
 *
 * @property uri The uri for the permission policy
 * @property name The displayable name for the permission policy
 * @property createdOn The date the policy was created
 * @property modifiedOn The date the policy was last modified on
 * @property permissionActions The actions the user can take.
 */
@JsonClass(generateAdapter = true)
data class PermissionPolicy(

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "created_on")
    val createdOn: Date? = null,

    @Json(name = "modified_on")
    val modifiedOn: Date? = null,

    @Json(name = "permission_actions")
    val permissionActions: PermissionActions? = null
)
